package com.charljulien.simpleloginspringbootservlet.repo;

import com.charljulien.simpleloginspringbootservlet.beans.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository <Project, Long> {
    List<Project> findProjectByName(String name);
}
