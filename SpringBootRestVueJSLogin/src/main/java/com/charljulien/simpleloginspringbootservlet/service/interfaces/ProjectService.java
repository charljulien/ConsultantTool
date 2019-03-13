package com.charljulien.simpleloginspringbootservlet.service.interfaces;

import com.charljulien.simpleloginspringbootservlet.beans.Project;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProjectService {
    Iterable<Project> getAllProjects();

    Project save(Project project);

    Project update(Project project);


    ResponseEntity<Project> deleteProject(long projectId);

    Optional<Project> verifyProject(long id);

    List<Project> findProjectByName(String name);
}
