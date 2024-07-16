package com.fabio.todolist.webapptodo.controller;


import com.fabio.todolist.webapptodo.entity.Task;
import com.fabio.todolist.webapptodo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/all")
    public List<Task> getAllTasks() {
        return taskService.readAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getOneTask(@PathVariable Long id) {
        return taskService.readById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Task> postTask(@RequestBody Task task) {
        var newTask = taskService.create(task);
        return ResponseEntity.ok(newTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        var newTask = taskService.update(id, task.getTitle(), task.getDescription());
        return ResponseEntity.ok(newTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
