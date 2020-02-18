package com.homework.passionfactory.domain.todo.dto;

import com.homework.passionfactory.domain.todo.domain.Todo;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TodoSaveRequest {
    @NotNull
    private String name;

    private Boolean completed;

    public Todo toEntity() {
        return Todo.builder()
                .name(name)
                .completed(completed)
                .build();

    }
}
