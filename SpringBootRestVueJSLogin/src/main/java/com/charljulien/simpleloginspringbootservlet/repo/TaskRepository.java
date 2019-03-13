package com.charljulien.simpleloginspringbootservlet.repo;

import com.charljulien.simpleloginspringbootservlet.beans.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository <Task, Long> {
    List<Task> findTaskByName(String name);
}
