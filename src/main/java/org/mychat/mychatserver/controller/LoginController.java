package org.mychat.mychatserver.controller;

import org.mychat.mychatserver.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/exists/uid")// 根据id查询用户是否存在
    @ResponseBody
    public Map<String, Object> isUserExist(int uid) {
        Map<String, Object> response = new HashMap<>();
        response.put("isUserExist", userMapper.isUserExist(uid));
        return response;
    }

    @GetMapping("/exists/email")// 根据email查询用户是否存在
    @ResponseBody
    public Map<String, Object> isEmailExist(String email) {
        Map<String, Object> response = new HashMap<>();
        response.put("isEmailExist", userMapper.isEmailExist(email));
        return response;
    }
}

