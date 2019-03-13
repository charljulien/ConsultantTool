package com.charljulien.simpleloginspringbootservlet.service;


import com.charljulien.simpleloginspringbootservlet.beans.Task;
import com.charljulien.simpleloginspringbootservlet.repo.TaskRepository;
import com.charljulien.simpleloginspringbootservlet.service.interfaces.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    /**
     *
     * @return List of Tasks
     */
    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Save task
     *
     * @param task
     * @return Task entity
     */
    @Override
    public Task save(Task task) {
        return !taskRepository.existsById(task.getId())?taskRepository.save(task): null;
    }

    /**
     * Update task
     *
     * @param task
     * @return Task entity
     */
    @Override
    public Task update(Task task) {
        return taskRepository.existsById(task.getId())?taskRepository.save(task): null;
    }

    /**
     * Delete task
     *
     * @param taskId
     * @return status of a HTTP response
     */
    @Override
    public ResponseEntity<Task> delete(long taskId) {
        taskRepository.deleteById(taskId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Find Task by id
     *
     * @param id
     * @return Task
     */
    @Override
    public Optional<Task> verifyTask(long id) {
        return taskRepository.findById(id);
    }

    /**
     * Find task by name
     *
     * @param name
     * @return Task
     */
    @Override
    public List<Task> findByName(String name) {
        return taskRepository.findTaskByName(name);
    }
}
