package com.charljulien.simpleloginspringbootservlet.controllers;

import com.charljulien.simpleloginspringbootservlet.beans.Task;
import com.charljulien.simpleloginspringbootservlet.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class TaskController {

    TaskServiceImpl taskService;

    @Autowired
    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @PostMapping (value = "/task")
    public Task createTask(@RequestBody Task task){
        System.out.println(task);
        return taskService.save(task);
    }

    @GetMapping (value = "/task")
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping(value = "/task/{name}")
    public List<Task> findByName(@PathVariable String name){
        List<Task> tasks = taskService.findByName(name);
        System.out.println(String.valueOf(tasks));
        return tasks;
    }

    @DeleteMapping(value = "/task")
    public ResponseEntity<String> deleteTask(@RequestBody Task task){
        taskService.delete(task.getId());
        System.out.println("Task with id : " + task.getId() + " has been deleted");
        return new ResponseEntity<>("Task " + task.getId() + " has been deleted",
                HttpStatus.OK);
    }

    @DeleteMapping(value = "/task/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable long id){
        taskService.delete(id);
        System.out.println("Task with id : " + id + " has been deleted");
        return new ResponseEntity<>("Task " + id + " has been deleted",
                HttpStatus.OK);
    }

    @PutMapping(value = "/task")
    public ResponseEntity<Task> updateTask(@RequestBody Task task){
        Optional<Task> taskData = taskService.verifyTask(task.getId());
        if(taskData.isPresent()){
            Task _task = taskData.get();
            System.out.println("updating " + _task.getName());
            _task.setName(task.getName());
            _task.setDescription(task.getDescription());
            _task.setActive(task.isActive());
            _task.setDeadline(task.getDeadline());
            _task.setUsers(task.getUsers());
            return new ResponseEntity<>(taskService.update(_task), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
