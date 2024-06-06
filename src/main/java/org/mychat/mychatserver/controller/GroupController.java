package org.mychat.mychatserver.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mychat.mychatserver.entity.Contact;
import org.mychat.mychatserver.entity.Group;
import org.mychat.mychatserver.entity.User;
import org.mychat.mychatserver.mapper.GroupMapper;
import org.mychat.mychatserver.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "群组管理")
@RestController
public class GroupController {

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private UserMapper userMapper;

    @Operation(summary = "创建群组")
    @PostMapping("/creatgroup")
    Map<String,Object> addGroupById(String groupname,int ownerid){
        Map<String, Object> response = new HashMap<>();
        if((!userMapper.isUserExist(ownerid))){
            response.put("success",false);
            return response;
        }
        response.put("success",groupMapper.creatGroup(groupname,ownerid)==1);
        return response;
    }

    @Operation(summary = "根据群id查询群组")
    @GetMapping("/selectgroup/uid")
    Group getContactByUid(int groupid){
        Group group=groupMapper.selectById(groupid);
        return group;
    }

    @Operation(summary = "根据群名称查询群组")
    @GetMapping("/selectgroup/name")
    List<Group> getContactByName(String groupname){
        List<Group> list=groupMapper.selectByName(groupname);
        return list;
    }

    @Operation(summary = "修改群名称")
    @PutMapping("/updategroup")
    Map<String,Object> getContactByName(String groupname,int groupid){
        Map<String, Object> response = new HashMap<>();
        if(!groupMapper.isGroupExist(groupid)){
            response.put("success",false);
            return response;
        }
        response.put("success",groupMapper.updateGroupName(groupid,groupname));
        return response;
    }

    @Operation(summary = "根据群组id删除群组")
    @DeleteMapping("deletegroup/uid")
    Map<String,Object> deleteContactByUid(int groupid){
        Map<String, Object> response = new HashMap<>();
        if(!groupMapper.isGroupExist(groupid)) {
            response.put("success",false);
            return response;
        }
        response.put("success",groupMapper.deleteByGroupId(groupid));
        return response;
    }



}
