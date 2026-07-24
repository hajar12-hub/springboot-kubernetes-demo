package org.todo.springbootapp.service;

import org.todo.springbootapp.entity.Task;
import org.todo.springbootapp.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Récupérer toutes les tâches
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Ajouter une nouvelle tâche
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    // Supprimer une tâche
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}