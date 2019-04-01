package com.charljulien.simpleloginspringbootservlet.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "created_projects")
    private List<Project> createdProjects;

//    @JsonIgnore
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable(name = "user_project",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "participe_in_projects")
    private List<Project> participeInProjects;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable(name = "user_task",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    @Column(name = "assigned_tasks")
    private List<Task> assignedTasks;

    public User() {
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        createdProjects = new ArrayList<>();
        participeInProjects = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public List<Project> getCreatedProjects() {
        return createdProjects;
    }
    public void setCreatedProjects(List<Project> createdProjects) {
        this.createdProjects = createdProjects;
    }

    public List<Project> getParticipeInProjects() {
        return participeInProjects;
    }
    public void setParticipeInProjects(List<Project> participeInProjects) {
        this.participeInProjects = participeInProjects;
    }

    public void addCreatedProject(Project project) {
        if (createdProjects != null) {
            createdProjects.add(project);
            project.setCreator(this);
        }
    }

    public void addParticipeInProject(Project project) {
        if (participeInProjects != null) {
            participeInProjects.add(project);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id = " + id +
                ", username = '" + username + '\'' +
                ", password = '" + password + '\'' +
                ", \nList of createdProjects = " + createdProjects +
                ", \nList of participeInProjects = " + participeInProjects +
                ", \nList of assignedTasks = " + assignedTasks +
                '}';
    }
}
