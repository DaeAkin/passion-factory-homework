package com.homework.passionfactory.domain.todo.application;

import com.homework.passionfactory.domain.todo.domain.Todo;
import com.homework.passionfactory.domain.todo.dto.TodoSaveRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface TodoService {

    Optional<Todo> findTodo(Long todoId);

    Optional<Todo> updateTodo(Long todoId, TodoSaveRequest todoSaveRequest);

    Optional<Todo> insertTodo(TodoSaveRequest todoSaveRequest);

    void deleteTodo(Long todoId);

    Optional<List<Todo>> findAllTodos(Pageable pageable);

}
