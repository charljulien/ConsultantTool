package com.charljulien.simpleloginspringbootservlet.service.interfaces;

import com.charljulien.simpleloginspringbootservlet.beans.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TaskService {
    Iterable<Task> getAllTasks();

    Task save (Task task);

    Task update (Task task);

    ResponseEntity<Task> delete(long taskId);

    Optional<Task> verifyTask(long id);

    List<Task> findByName(String name);
}
