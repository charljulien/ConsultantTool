package com.charljulien.simpleloginspringbootservlet.beans;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private boolean active;

    @Column(name = "creation")
    private Date creation = new Date();

    @Column(name = "deadline")
    private Date deadline;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "project_origin")
    private Project projectOrigin;

    @ManyToMany(mappedBy = "assignedTasks")
    @Column(name = "assigned_to")
    private List<User> users;

    public Task() {
    }

    public Task(String name, String description, boolean active, Date deadline) {
        this.name = name;
        this.description = description;
        this.active = active;
        this.deadline = deadline;
        this.users = new ArrayList<>();
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

    public Date getCreation() {
        return creation;
    }

    public Date getDeadline() {
        return deadline;
    }
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Project getProjectOrigin() {
        return projectOrigin;
    }

    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Task {" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", description = '" + description + '\'' +
                ", active = " + active +
                ", creation = " + creation +
                ", deadline=  " + deadline +
                ", projectOrigin = " + projectOrigin +
                ", \nusers = " + users +
                '}';
    }
}
