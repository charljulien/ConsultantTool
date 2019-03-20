package com.charljulien.simpleloginspringbootservlet.controllers;

import com.charljulien.simpleloginspringbootservlet.beans.User;
import com.charljulien.simpleloginspringbootservlet.service.LoginService;
import com.charljulien.simpleloginspringbootservlet.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class UserController {

    /**
     * Temporary login test
     *
     * @info To be deleted as soon as real Login implemented
     */
    @Autowired
    LoginService service;

    UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/user")
    public User createUser(@RequestBody User user){
        System.out.println(user);
        return userService.save(user);
    }

    @GetMapping(value = "/user")
    public List<User> getAllUser(){
        return userService.getAllUsers();
    }

    @GetMapping(value = "/user/{username}")
    public List<User> findByUsername(@PathVariable String username){
        List<User> users = userService.findByUsername(username);
        System.out.println(String.valueOf(users));
        return users;
    }

    @DeleteMapping(value = "/user")
    public ResponseEntity<String> deleteUser(@RequestBody User user){
        userService.deleteUser(user.getId());
        System.out.println("User " + user.getId() + " has been deleted");
        return new ResponseEntity<>("User " + user.getId() + " has been deleted",
                HttpStatus.OK);
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable long id){
        userService.deleteUser(id);
        System.out.println("User with id : " + id + " has been deleted");
        return new ResponseEntity<>("User with id : " + id + " has been deleted",
                HttpStatus.OK);
    }

    @PutMapping(value = "/user")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        Optional<User> userData = userService.verifyUser(user.getId());
        if (userData.isPresent()){
            User _user = userData.get();
            System.out.print("updating " + _user.getUsername());
            _user.setUsername(user.getUsername());
            _user.setPassword(user.getPassword());
            _user.setParticipeInProjects(user.getParticipeInProjects());
            System.out.println(" to " + _user.getUsername());
            return new ResponseEntity<>(userService.update(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }





    /********WHATEVER ****BELOW***********/
    /********** TO BE UPDATED*************/

//    @GetMapping("/login")
//    public User getLogin() {
//        // Do something
//        User user = new User();
//        return user;
//    }

    @PostMapping("/login")
    public User postLogin (@RequestBody User user)  {
        boolean isValidUser = service.validateUser(user.getUsername(), user.getPassword());

        if (isValidUser){
            System.out.println("user " + user + " logged in");
            return user;
        }
        else{
            System.out.println("erreur");
            return null;
        }
    }

//    @GetMapping("/home")
//    public String getHome(ModelMap model){
//        return "home";
//    }
//
//    @PostMapping("/home")
//    public String postHome(ModelMap model){
//        return "home";
//    }
}
