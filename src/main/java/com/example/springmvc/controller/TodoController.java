package com.example.springmvc.controller;

import com.example.springmvc.dto.TodoDTO;
import com.example.springmvc.model.Todo;
import com.example.springmvc.repository.TodoRepository;
import com.example.springmvc.service.NotFoundException;
import com.example.springmvc.service.RepositoryTodoService;
import com.example.springmvc.service.TodoService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by tatsuya on 2014/05/25.
 */
@Controller
public final class TodoController extends AbstractController {

    private static final String TODO_LISTS_MODEL     = "todolists";
    private static final String TODO_MODEL           = "todo";

    private static final String TODO_INDEX_VIEW      = "todo/index";
    private static final String TODO_CREATE_VIEW     = "todo/create";
    private static final String TODO_EDIT_VIEW       = "todo/edit";

    private static final String BEGIN_INDEX          = "beginIndex";
    private static final String END_INDEX            = "endIndex";
    private static final String CURRENT_INDEX        = "currentIndex";
    private static final String TOTAL_PAGES          = "totalPages";

    private static final String REQUEST_MAPPING_LIST = "/todo";

    private static final int DEFAULT_PAGE_NUMBER     = 1;

    @Resource
    private TodoService todoService;

    @RequestMapping("/todo")
    public String index(@RequestParam(value = "p", required = false) String p, Model model) {

        int pageNumber = DEFAULT_PAGE_NUMBER;
        if (p != null) {
            pageNumber = Integer.parseInt(p);
        }

        Page<Todo> todos = todoService.getTodos(pageNumber);

        int current = todos.getNumber() + 1;
        int begin   = Math.max(1, current - 5);
        int end     = Math.min(begin + 10, todos.getTotalPages());

        model.addAttribute(TODO_LISTS_MODEL, todos);
        model.addAttribute(BEGIN_INDEX, begin);
        model.addAttribute(END_INDEX, end);
        model.addAttribute(CURRENT_INDEX, current);
        model.addAttribute(TOTAL_PAGES, todos.getTotalPages());

        return TODO_INDEX_VIEW;
    }

    @RequestMapping(value = "/todo/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute(TODO_MODEL, new TodoDTO());
        return TODO_CREATE_VIEW;
    }

    @RequestMapping(value = "/todo/create", method=RequestMethod.POST)
    public String create(@Valid @ModelAttribute(TODO_MODEL) TodoDTO created, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if ( bindingResult.hasErrors()) {
            return TODO_CREATE_VIEW;
        }

        try {
            Todo todo = Todo.getBuilder(created.getTitle(), created.getDescription());
            todoService.create(created);
            addFeedbackMessage(redirectAttributes, "todo.create.successfully.message");
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        } catch (Exception e) {
            addErrorMessage(redirectAttributes, "todo.create.failed.message");
            return TODO_CREATE_VIEW;
        }
    }
    @RequestMapping(value = "/todo/edit", method=RequestMethod.POST)
    public String update(@Valid @ModelAttribute(TODO_MODEL) TodoDTO updated, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if ( bindingResult.hasErrors()) {
            return TODO_EDIT_VIEW;
        }

        try {
            todoService.update(updated);
            addFeedbackMessage(redirectAttributes, "todo.update.successfully.message");
        } catch(NotFoundException e) {
            addErrorMessage(redirectAttributes, "todo.notfound.message");
        }
        return createRedirectViewPath(REQUEST_MAPPING_LIST);
    }

    @RequestMapping(value = "/todo/{id}/edit", method=RequestMethod.GET)
    public String edit(@PathVariable("id") long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute(TODO_MODEL, createFormObject(todoService.findById(id)));
        } catch(Exception e) {
            addErrorMessage(redirectAttributes, "todo.notfound.message");
        }

        return TODO_EDIT_VIEW;
    }

    @RequestMapping(value = "/todo/{id}/delete", method=RequestMethod.GET)
    public String delete(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        try {
            todoService.delete(id);
            addFeedbackMessage(redirectAttributes, "todo.delete.successfully.message");
        } catch(NotFoundException e) {
            addErrorMessage(redirectAttributes, "todo.notfound.message");
        }
        return createRedirectViewPath(REQUEST_MAPPING_LIST);
    }

    private TodoDTO createFormObject(Todo todo) {
        TodoDTO formObject = new TodoDTO();

        formObject.setId(todo.getId());
        formObject.setTitle(todo.getTitle());
        formObject.setDescription(todo.getDescription());

        return formObject;
    }
}
