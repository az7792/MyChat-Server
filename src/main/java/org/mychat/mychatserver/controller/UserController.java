package org.mychat.mychatserver.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mychat.mychatserver.entity.User;
import org.mychat.mychatserver.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Tag(name = "用户管理")
@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @Operation(summary = "根据id查询用户是否存在")
    @GetMapping("/exists/uid")
    public Map<String, Object> isUserExist(Integer uid) {
        Map<String, Object> response = new HashMap<>();
        response.put("exist", userMapper.isUserExist(uid));
        return response;
    }

    @Operation(summary = "根据email查询用户是否存在")
    @GetMapping("/exists/email")
    public Map<String, Object> isEmailExist(String email) {
        Map<String, Object> response = new HashMap<>();
        response.put("exist", userMapper.isEmailExist(email));
        return response;
    }

    @Operation(summary = "根据UID查询用户信息")
    @PostMapping("/getUser/uid")
    public User getUserByUid(Integer uid) {
        return userMapper.getUserByUid(uid);
    }

    @Operation(summary = "根据邮箱查询用户信息")
    @PostMapping("/getUser/email")
    public User getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    @Operation(summary = "注册新用户")
    @PostMapping("/register")
    public Map<String, Object> register(User user) {
        Map<String, Object> response = new HashMap<>();
        if(userMapper.isEmailExist(user.getEmail())) {// 邮箱未使用时才能注册
            response.put("success",false);
            return response;
        }
        response.put("success",userMapper.addUser(user)==1);
        return response;
    }

    @Operation(summary = "通过UID登录")
    @PostMapping("/login/uid")
    public Map<String, Object> loginByUID(Integer uid, String password) {
        Map<String, Object> response = new HashMap<>();
        response.put("success",userMapper.matchByUidAndPassword(uid,password));
        return response;
    }

    @Operation(summary = "通过邮箱登录")
    @PostMapping("/login/email")
    public Map<String, Object> loginByEmail(String email, String password) {
        Map<String, Object> response = new HashMap<>();
        response.put("success",userMapper.matchByEmailAndPassword(email,password));
        return response;
    }

    @Operation(summary = "通过邮箱修改密码")
    @PostMapping("/updatePassword")
    public Map<String, Object> updatePassword(String email, String password) {
        Map<String, Object> response = new HashMap<>();
        response.put("success",userMapper.updatePassword(email,password)==1);
        return response;
    }

    @Operation(summary = "根据UID获取头像")
    @PostMapping("/getAvatar")
    public Map<String, Object> getAvatarByUid(Integer uid) {
        Map<String, Object> response = new HashMap<>();
            String avatar = userMapper.getAvatarByUid(uid);
            if (avatar == null) {
                response.put("success", false);
                response.put("message", "Avatar not found");
            } else {
                response.put("success", true);
                response.put("avatar", avatar);
            }
        return response;
    }

    @Operation(summary = "根据UID更新头像")
    @PostMapping("/updateAvatar")
    public Map<String, Object> updateAvatarByUid(Integer uid, String avatar) {
        Map<String, Object> response = new HashMap<>();
        Integer res = userMapper.updateAvatarByUid(uid,avatar);
        if (res == 1) {
            response.put("success", true);
        }else {
            response.put("success", false);
        }
        return response;
    }
}

