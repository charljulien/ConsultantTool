package com.charljulien.simpleloginspringbootservlet.controllers;

import com.charljulien.simpleloginspringbootservlet.beans.Project;
import com.charljulien.simpleloginspringbootservlet.beans.User;
import com.charljulien.simpleloginspringbootservlet.service.ProjectServiceImpl;
import com.charljulien.simpleloginspringbootservlet.service.UserServiceImpl;
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
    UserServiceImpl userService;

    @Autowired
    public ProjectController(ProjectServiceImpl projectService){
        this.projectService = projectService;
    }

    /**
     * User service here is added only to test one to many relationship
     * between the creator and the created projects.
     * Below, an existing user in the method "verifyUser()" creates a project
     * This step will be deleted in the finalisation of the project
     * @param project
     * @return project
     */
    @PostMapping(value = "/project")
    public Project createProject(@RequestBody Project project){

        //Add created project to Creator
        Optional<User> user = userService.verifyUser(project.getCreator().getId());
        User _creator = user.get();
        _creator.addCreatedProject(project);

        //Add project to Collaborator
        for(User collab : project.getCollaborators()){
            System.out.println("COLLLLLAAAAAAAAAAABORAAAAAAATORRRRRRRRRRRRRRR: " + collab);
            Optional<User> collaborator = userService.verifyUser(collab.getId());
            User _collaborator = collaborator.get();
            _collaborator.addParticipeInProject(project);
//            userService.update(_collaborator);
        }


//        System.out.println(project);
        projectService.save(project);
//        System.out.println(_user);
        userService.update(_creator);
        return project;
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
    public ResponseEntity<String> deleteProjectById(@PathVariable long id){
        projectService.deleteProject(id);
        System.out.println("Project " + id + " has been deleted");
        return new ResponseEntity<>("Project " + id + " has been deleted", HttpStatus.OK);
    }

    @PutMapping(value = "/project")
    public ResponseEntity<Project> updateProject(@RequestBody Project project){
        Optional<Project> projectData = projectService.verifyProject(project.getId());
        Optional<User> userData;
        if(projectData.isPresent()){
            Project _project = projectData.get();


            System.out.print("updating " + _project.getName());
            System.out.println("/**************PROJECT IN PARAMETER**************/");
            System.out.println(project + "\n");
            System.out.println("/**************PROJECT TO BE UPDATED**************/");
            System.out.println(_project + "\n");


//            System.out.println("/**************COLLABORATOR IN PROJECT IN PARAMETER**************/");
//            System.out.println(project.getCollaborators().get(0) + "\n");


            _project.setName(project.getName());
            _project.setDescription(project.getDescription());
            _project.setActive(project.isActive());
            _project.setDeadline(project.getDeadline());
            if(project.getCollaborators()!=null){
                userData = userService.verifyUser(project.getCollaborators().get(0).getId());
                User _user = userData.get();
                _project.addCollaborator(_user);
                System.out.println("/**************COLLABORATOR IN PROJECT IN PARAMETER**************/");
                System.out.println(_user + "\n");
                userService.update(_user);
            }
            _project.setTasks(project.getTasks());

            System.out.println("/**************PROJECT UPDATED**************/");
            System.out.println(_project + "\n");

            projectService.update(_project);
            return new ResponseEntity<>(project, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

