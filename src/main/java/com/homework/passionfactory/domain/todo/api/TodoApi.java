package com.homework.passionfactory.domain.todo.api;

import com.homework.passionfactory.domain.todo.application.TodoService;
import com.homework.passionfactory.domain.todo.domain.Todo;
import com.homework.passionfactory.domain.todo.dto.TodoSaveRequest;
import com.homework.passionfactory.domain.user.domain.User;
import com.homework.passionfactory.global.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
@RequestMapping("/todos")
public class TodoApi {

    private final TodoService todoService;

    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getTodo(@PathVariable(value = "todoId") Integer todoId) throws NotFoundException {
        Optional<Todo> todo = todoService.findTodo(todoId);

//        return  todo.map(t -> new ResponseEntity(t,HttpStatus.OK)).orElse(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
        return  todo.map(t -> new ResponseEntity(t,HttpStatus.OK)).orElseThrow(NotFoundException::new);
    }

    //1. 요구사항 : api key
    //2. Validation
    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodo(@PathVariable(value = "todoId") Integer todoId,
                                           @RequestBody @Valid TodoSaveRequest todoSaveRequest) throws NotFoundException {
        Optional<Todo> todo = todoService.updateTodo(todoId, todoSaveRequest);
        return todo.map(t -> new ResponseEntity(t,HttpStatus.OK)).orElseThrow(NotFoundException::new);
    }
    //API KEY
    // 응답 204

    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable(value = "todoId") Integer todoId)  {
        todoService.deleteTodo(todoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //API KEY
    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody @Valid TodoSaveRequest todoSaveRequest , Authentication authentication) throws NotFoundException {
        User user = (User) authentication.getPrincipal();
        System.out.println("user :" + user.toString());
        Optional<Todo> todo = todoService.insertTodo(todoSaveRequest,user);
        return todo.map(t -> new ResponseEntity(t,HttpStatus.CREATED)).orElseThrow(NotFoundException::new);
    }

    //1. 페이지네이션
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos(@RequestParam("skip") int skip, @RequestParam("limit") int limit) throws NotFoundException {
        List<Todo> todoList = new ArrayList<>();
        Pageable pageable = PageRequest.of(skip, limit);
        Optional<List<Todo>> todo = todoService.findAllTodos(pageable);
        return todo.map(t -> new ResponseEntity(t,HttpStatus.OK)).orElseThrow(NotFoundException::new);
    }


}
