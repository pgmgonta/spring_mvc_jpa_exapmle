package com.example.springmvc.service;

import com.example.springmvc.dto.TodoDTO;
import com.example.springmvc.model.Todo;
import com.example.springmvc.repository.TodoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by tatsuya on 2014/05/31.
 */
@Service
public class RepositoryTodoService implements TodoService {



    @Resource
    private TodoRepository todoRepository;

    @Transactional(rollbackFor = NotFoundException.class)
    @Override
    public Todo create(TodoDTO created) {
        Todo todo = Todo.getBuilder(created.getTitle(), created.getDescription());
        return todoRepository.save(todo);
    }

    @Transactional
    @Override
    public Todo delete(Long todoId) throws NotFoundException {
        Todo deleted = todoRepository.findOne(todoId);

        if (deleted == null) {
            throw new NotFoundException(String.format("No Todo found with id=%s", todoId));
        }

        todoRepository.delete(deleted);
        return deleted;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Todo> getTodos(int pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, PAGE_SIZE);
        return todoRepository.findAll(pageRequest);
    }

    @Transactional(readOnly = true)
    @Override
    public Todo findById(Long todoId) {
        return todoRepository.findOne(todoId);
    }

    @Transactional(rollbackFor = NotFoundException.class)
    @Override
    public Todo update(TodoDTO updated) throws NotFoundException {
        Todo todo = todoRepository.findOne(updated.getId());

        if (todo == null) {
            throw new NotFoundException(String.format("No Todo found with id=%s", updated.getId()));
        }
        todo.update(updated.getTitle(), updated.getDescription());

        return todo;
    }


}
