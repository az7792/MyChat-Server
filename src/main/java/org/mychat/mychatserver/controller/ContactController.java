package org.mychat.mychatserver.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mychat.mychatserver.entity.Contact;
import org.mychat.mychatserver.entity.User;
import org.mychat.mychatserver.mapper.ContactMapper;
import org.mychat.mychatserver.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Tag(name="好友管理")
@RestController
public class ContactController {
    @Autowired
    private ContactMapper contactmapper;

    @Autowired
    private UserMapper usermapper;

    @Operation(summary = "根据uid为用户添加新联系人")
    @PostMapping("/addcontact/uid")
    public Map<String,Object> addContactById(int uid1,int uid2){
        Map<String, Object> response = new HashMap<>();
        if(contactmapper.isContactExist(uid1,uid2)) {
            response.put("success",false);
            return response;
        }
        if((!usermapper.isUserExist(uid1))||(!usermapper.isUserExist(uid2))){
            response.put("success",false);
            return response;
        }
        contactmapper.addContactByUid(uid1,uid2);
        response.put("success",true);
        return response;
    }

    @Operation(summary = "根据uid获取用户所有联系人")
    @PostMapping("/selectcontact/uid")
    List<User> getContactByUid(int uid){
        List<Contact> list=contactmapper.selectByUid(uid);
        List<User> userList=new ArrayList<>();
        for(Contact o:list){
            if(usermapper.isUserExist(o.getUid2())){
                userList.add(usermapper.getUserByUid(o.getUid2()));
            }
        }
        return userList;
    }

    @Operation(summary = "根据uid删除联系人")
    @DeleteMapping("/deletecontact/uid")
    Map<String,Object> deleteContactByUid(int uid1,int uid2){
        Map<String, Object> response = new HashMap<>();
        if(!contactmapper.isContactExist(uid1,uid2)) {
            response.put("success",false);
            return response;
        }
        if(!contactmapper.isContactExist(uid2,uid1)) {
            response.put("success",false);
            return response;
        }
        response.put("success",contactmapper.deleteContactById(uid1,uid2)==1);
        response.put("success",contactmapper.deleteContactById(uid2,uid1)==1);
        return response;
    }



}
