package com.codeup.springblog.controllers;

import com.codeup.springblog.repositories.UserRepository;

public class UserController {

    private final UserRepository userDoa;

    public UserController(UserRepository userDoa){
        this.userDoa = userDoa;

    }


}
