package com.homework.passionfactory.domain.api;

import com.homework.passionfactory.domain.domain.Todo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoApi {

    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getTodo(@PathVariable(value = "todoId") long todoId) {
        return  null;
    }

    //1. 요구사항 : api key
    //2. Validation
    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodo(@PathVariable(value = "todoId") long todoId)  {

        return null;
    }
    //API KEY
    // 응답 204
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{todoId}")
    public void deleteTodo(@PathVariable(value = "todoId") long todoId)  {

    }

    //API KEY
    @PostMapping("/todos")
    public ResponseEntity<Todo> createTodo(@RequestBody @ModelAttribute Todo todo) {

        return new ResponseEntity<>(todo,HttpStatus.CREATED);
    }

    //1. 페이지네이션
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {

        List<Todo> todoList = new ArrayList<>();

        return new ResponseEntity<>(todoList,HttpStatus.OK);
    }


}
