package com.example.springmvc.repository;

import com.example.springmvc.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by tatsuya on 2014/05/31.
 */
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
