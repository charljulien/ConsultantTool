package com.charljulien.simpleloginspringbootservlet.service.interfaces;

import com.charljulien.simpleloginspringbootservlet.beans.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    Iterable<User> getAllUsers();

    User save(User user);

    User update(User user);

    ResponseEntity<User> deleteUser(long userId);

    Optional<User> verifyUser(long id);

    List<User> findByUsername(String username);
}
