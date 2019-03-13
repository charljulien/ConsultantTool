package com.charljulien.simpleloginspringbootservlet.controllers;

import com.charljulien.simpleloginspringbootservlet.beans.Project;
import com.charljulien.simpleloginspringbootservlet.service.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class ProjectController {

    ProjectServiceImpl projectService;

    @Autowired
    public ProjectController(ProjectServiceImpl projectService){
        this.projectService = projectService;
    }

    @PostMapping(value = "/project")
    public Project createProject(@RequestBody Project project){
        System.out.println(project);
        return projectService.save(project);
    }

    @GetMapping(value = "/project")
    public List <Project> getAllProject(){
        return projectService.getAllProjects();
    }

    @GetMapping(value = "/project/{name}")
    public List<Project> findByName(@PathVariable String name){
        List<Project> projects = projectService.findProjectByName(name);
        System.out.println(String.valueOf(projects));
        return projects;
    }

    @DeleteMapping(value = "/project")
    public ResponseEntity<String> deleteProject(@RequestBody Project project){
        projectService.deleteProject(project.getId());
        System.out.println("Project " + project.getId() + " has been deleted");
        return new ResponseEntity<>("Project " + project.getId() + " has been deleted", HttpStatus.OK);
    }

    @DeleteMapping(value = "/project/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable long id){
        projectService.deleteProject(id);
        System.out.println("Project " + id + " has been deleted");
        return new ResponseEntity<>("Project " + id + " has been deleted", HttpStatus.OK);
    }

    @PutMapping(value = "/project")
    public ResponseEntity<Project> updateProject(@RequestBody Project project){
        Optional<Project> projectData = projectService.verifyProject(project.getId());
        if(projectData.isPresent()){
            Project _project = projectData.get();
            System.out.print("updating " + _project.getName());
            _project.setName(project.getName());
            _project.setDescription(project.getDescription());
            _project.setActive(project.isActive());
            _project.setDeadline(project.getDeadline());
            _project.setTasks(project.getTasks());
            return new ResponseEntity<>(projectService.update(_project), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
