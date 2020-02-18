package com.homework.passionfactory.domain.todo.dao;

import com.homework.passionfactory.domain.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Long> {
}
