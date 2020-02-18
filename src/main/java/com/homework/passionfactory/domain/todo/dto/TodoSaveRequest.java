package com.homework.passionfactory.domain.todo.dto;

import com.homework.passionfactory.domain.todo.domain.Todo;
import com.homework.passionfactory.domain.user.domain.User;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class TodoSaveRequest {
    @NotNull
    private String name;

    private Boolean completed;

    public Todo toEntity(User user) {
        return Todo.builder()
                .name(name)
                .completed(completed)
                .user(user)
                .build();

    }
}
