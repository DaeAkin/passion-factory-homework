package com.homework.passionfactory.test;

import com.homework.passionfactory.domain.todo.dao.TodoRepository;
import com.homework.passionfactory.domain.todo.domain.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TodoTestFixture {

    public static List<Todo> makeTodosYouWant(TodoRepository todoRepository ,int n) {
        List<Todo> todoList = new ArrayList<>();
        for(int i=0; i<n; i++) {
            Todo todo = Todo.builder()
                    .name(UUID.randomUUID().toString())
                    .build();
            todoList.add(todo);
        }
    return todoRepository.saveAll(todoList);
    }
}
