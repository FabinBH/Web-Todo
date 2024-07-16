package com.fabio.todolist.webapptodo.repository;

import com.fabio.todolist.webapptodo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
