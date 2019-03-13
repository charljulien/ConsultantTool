package com.charljulien.simpleloginspringbootservlet.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public boolean validateUser(String userid, String password) {
        // in28minutes, dummy
        return userid.equalsIgnoreCase("username")
                && password.equalsIgnoreCase("password");
    }

}
