package com.homework.passionfactory.domain.todo.api;

import com.homework.passionfactory.domain.todo.application.TodoService;
import com.homework.passionfactory.domain.todo.domain.Todo;
import com.homework.passionfactory.domain.todo.dto.TodoSaveRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * API 명세서에 Request Body로 User를 같이 안던져주는데,
 * 임의로 하면될려나,,.. 음..
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoApi {

    private final TodoService todoService;

    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getTodo(@PathVariable(value = "todoId") Long todoId) {
        Optional<Todo> todo = todoService.findTodo(todoId);
        return  todo.map(t -> new ResponseEntity(t,HttpStatus.OK)).orElse(new ResponseEntity<Todo>(new Todo(),HttpStatus.NOT_FOUND));
    }

    //1. 요구사항 : api key
    //2. Validation
    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodo(@PathVariable(value = "todoId") Long todoId,
                                           @RequestBody @ModelAttribute TodoSaveRequest todoSaveRequest)  {
        Optional<Todo> todo = todoService.updateTodo(todoId, todoSaveRequest);
        return todo.map(t -> new ResponseEntity(t,HttpStatus.OK)).orElse(new ResponseEntity<Todo>(new Todo(),HttpStatus.NOT_FOUND));
    }
    //API KEY
    // 응답 204
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{todoId}")
    public void deleteTodo(@PathVariable(value = "todoId") Long todoId)  {
        todoService.deleteTodo(todoId);
    }

    //API KEY
    @PostMapping("/todos")
    public ResponseEntity<Todo> createTodo(@RequestBody @ModelAttribute TodoSaveRequest todoSaveRequest) {
        Optional<Todo> todo = todoService.insertTodo(todoSaveRequest);
        return todo.map(t -> new ResponseEntity(t,HttpStatus.CREATED)).orElse(new ResponseEntity<Todo>(new Todo(),HttpStatus.NOT_FOUND));
    }

    //1. 페이지네이션
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos(@RequestParam("skip") int skip, @RequestParam("limit") int limit) {
        List<Todo> todoList = new ArrayList<>();
        Pageable pageable = PageRequest.of(skip, limit);
        Optional<List<Todo>> todo = todoService.findAllTodos(pageable);
        return todo.map(t -> new ResponseEntity(t,HttpStatus.CREATED)).orElse(new ResponseEntity<Todo>(new Todo(),HttpStatus.NOT_FOUND));
    }


}
