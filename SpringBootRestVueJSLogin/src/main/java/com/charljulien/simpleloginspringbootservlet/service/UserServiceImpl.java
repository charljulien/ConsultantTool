package com.charljulien.simpleloginspringbootservlet.service;

import com.charljulien.simpleloginspringbootservlet.beans.User;
import com.charljulien.simpleloginspringbootservlet.repo.UserRepository;
import com.charljulien.simpleloginspringbootservlet.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * @return List of Users
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Save user
     *
     * @param user
     * @return User entity
     */
    @Override
    public User save(User user) {
        return !userRepository.existsById(user.getId())? userRepository.save(user): null;
    }

    /**
     * Update user
     *
     * @param user
     * @return User entity
     */
    @Override
    public User update(User user) {
        return userRepository.existsById(user.getId())? userRepository.save(user): null;
    }


    /**
     * Delete user
     *
     * @param userId
     * @return status of a HTTP response
     */
    @Override
    public ResponseEntity<User> deleteUser(long userId) {
        userRepository.deleteById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Find User by id
     *
     * @param id
     * @return User
     */
    @Override
    public Optional<User> verifyUser(long id) {
        return userRepository.findById(id);
    }

    /**
     * Find user by username
     *
     * @param username
     * @return User
     */
    @Override
    public List<User> findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
