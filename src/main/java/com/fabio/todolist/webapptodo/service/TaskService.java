package com.fabio.todolist.webapptodo.service;

import com.fabio.todolist.webapptodo.entity.Task;
import com.fabio.todolist.webapptodo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task create(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> readAll() {
        return taskRepository.findAll();
    }

    public Optional<Task> readById(Long id) {
        return taskRepository.findById(id);
    }

    public Task update(Long id, String title, String description) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isEmpty()) {
            return null;
        }

        Task oldTask = optionalTask.get();
        if (title != null && !title.isEmpty()) {
            oldTask.setTitle(title);
        }
        if (description != null && !description.isEmpty()) {
            oldTask.setDescription(description);
        }

        return taskRepository.save(oldTask);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
