package com.homework.passionfactory.domain.todo.api;

import com.homework.passionfactory.domain.todo.dao.TodoRepository;
import com.homework.passionfactory.domain.todo.domain.Todo;
import com.homework.passionfactory.domain.todo.dto.TodoSaveRequest;
import com.homework.passionfactory.domain.user.domain.User;
import com.homework.passionfactory.global.error.NotFoundException;
import com.homework.passionfactory.test.IntegrationTest;
import com.homework.passionfactory.test.TodoTestFixture;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class TodoApiTests extends IntegrationTest {
    @Autowired
    TodoApi todoApi;

    @Autowired
    TodoRepository todoRepository;
    @MockBean
    Authentication authentication;

    @Before
    public void setUp() {

    }
    @Test
    public void Todo_가져오기_테스트() throws NotFoundException {
        //given
        List<Todo> todoList = TodoTestFixture.makeTodosYouWant(todoRepository, 5);
        //when
        ResponseEntity<Todo> todo = todoApi.getTodo(todoList.get(0).getId());
        //then
        assertThat(todo.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(todo.getBody().getName()).isEqualTo(todoList.get(0).getName());

    }
    @Test(expected = NotFoundException.class)
    public void Todo_가져왔는데_없을때_테스트() throws NotFoundException {
        //when
        ResponseEntity<Todo> todo = todoApi.getTodo(1);
        //then
        assertThat(todo.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void Todo_업데이트_테스트() throws NotFoundException {
        //given
        List<Todo> todoList = TodoTestFixture.makeTodosYouWant(todoRepository, 5);
        TodoSaveRequest todoSaveRequest = TodoSaveRequest.builder()
                .name("코딩좋아요")
                .build();
        //when
        ResponseEntity<Todo> response = todoApi.updateTodo(todoList.get(0).getId(), todoSaveRequest);
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isEqualTo(todoSaveRequest.getName());

    }

    @Test
    public void Todo_삭제_테스트() {
        //given
        List<Todo> todoList = TodoTestFixture.makeTodosYouWant(todoRepository, 5);
        //when
        Integer id = todoList.get(0).getId();
        ResponseEntity<Void> response = todoApi.deleteTodo(id);
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(optionalTodo.isPresent()).isFalse();
    }

    @Test
    public void Todo_삽입_테스트() throws NotFoundException {
        //given
        given(authentication.getPrincipal()).willReturn(new User("donghyeon","1234",25));
        TodoSaveRequest todoSaveRequest = TodoSaveRequest.builder()
                .name("코딩조조조아아요")
                .completed(true)
                .build();
        //when
        ResponseEntity<Todo> response = todoApi.createTodo(todoSaveRequest, authentication);
//        Optional<Todo> optionalTodo = todoRepository.findById(response.getBody().getId());
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getName()).isEqualTo(todoSaveRequest.getName());
        assertThat(response.getBody().getCompleted()).isEqualTo(todoSaveRequest.getCompleted());
    }

    @Test
    public void Todo_전부가져오기_테스트() throws NotFoundException {
        //given
        List<Todo> todoList = TodoTestFixture.makeTodosYouWant(todoRepository, 5);
        //when
        ResponseEntity<List<Todo>> response = todoApi.getAllTodos(0, 5);
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().size()).isEqualTo(5);

    }
}
