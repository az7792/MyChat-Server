package org.mychat.mychatserver.controller;

import org.mychat.mychatserver.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/exists/uid")
    public boolean isUserExist(int uid) {
        return userMapper.isUserExist(uid);
    }

    @GetMapping("/exists/email")
    public boolean isEmailExist(String email) {
        return userMapper.isEmailExist(email);
    }
}

