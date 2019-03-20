package com.charljulien.simpleloginspringbootservlet.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "project")
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private boolean active;

    @Column(name = "creationDate")
    private Date creationDate = new Date();

    @Column(name = "deadline")
    private Date deadline;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creator")
    private User creator;

    @OneToMany(mappedBy = "projectOrigin")
    @Column(name = "tasks")
    private List<Task> tasks;

    @ManyToMany(mappedBy = "participeInProjects")
    @Column(name = "assigned_projects")
    private List<User> collaborators;

    public Project() {
    }

    public Project(String name, String description, boolean active, Date deadline) {
        this.name = name;
        this.description = description;
        this.active = active;
        this.deadline = deadline;
        this.tasks = new ArrayList<>();
        this.collaborators = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getDeadline() {
        return deadline;
    }
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public User getCreator() {
        return creator;
    }
    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<Task> getTasks() {
        return tasks;
    }
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", description = '" + description + '\'' +
                ", active = " + active +
                ", creationDate = " + creationDate +
                ", deadline = " + deadline +
                ", creator = " + creator.getUsername() +
                ", \nList of tasks = " + tasks +
                ", \nList of collaborators = " + collaborators +
                '}';
    }
}
