package com.charljulien.simpleloginspringbootservlet.service;

import com.charljulien.simpleloginspringbootservlet.beans.Project;
import com.charljulien.simpleloginspringbootservlet.repo.ProjectRepository;
import com.charljulien.simpleloginspringbootservlet.service.interfaces.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    /**
     * @return List of Projects
     */
    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    /**
     * Save Project
     *
     * @param project
     * @return Proejct entity
     */
    @Override
    public Project save(Project project) {
        return !projectRepository.existsById(project.getId()) ? projectRepository.save(project) : null;
    }

    /**
     * Update Project
     *
     * @param project
     * @return Proejct entity
     */
    @Override
    public Project update(Project project) {
        return projectRepository.existsById(project.getId()) ? projectRepository.save(project) : null;
    }

    /**
     * Delete Project
     *
     * @param projectId
     * @return status of a HTTP response
     */
    @Override
    public ResponseEntity<Project> deleteProject(long projectId) {
        projectRepository.deleteById(projectId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Find Project by id
     *
     * @param id
     * @return Project
     */
    @Override
    public Optional<Project> verifyProject(long id) {
        return projectRepository.findById(id);
    }

    /**
     * Find Project by name
     * @param name
     * @return Project
     */
    @Override
    public List<Project> findProjectByName(String name) {
        return projectRepository.findProjectByName(name);
    }
}
