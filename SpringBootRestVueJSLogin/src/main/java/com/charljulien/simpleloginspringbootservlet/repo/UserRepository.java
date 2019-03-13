package com.charljulien.simpleloginspringbootservlet.repo;

import com.charljulien.simpleloginspringbootservlet.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <User, Long>  {
    List<User> findUserByUsername(String username);
}
