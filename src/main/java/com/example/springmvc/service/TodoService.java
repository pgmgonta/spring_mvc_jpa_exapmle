package com.example.springmvc.service;

import com.example.springmvc.dto.TodoDTO;
import com.example.springmvc.model.Todo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by tatsuya on 2014/05/31.
 */
public interface TodoService {

    public static final int PAGE_SIZE = 5;

    public Todo create(TodoDTO created);

    public Todo delete(Long todoId) throws NotFoundException;

    public List<Todo> findAll();

    public Page<Todo> getTodos(int pageNumber);

    public Todo findById(Long todoId);

    public Todo update(TodoDTO updated) throws NotFoundException;

}
