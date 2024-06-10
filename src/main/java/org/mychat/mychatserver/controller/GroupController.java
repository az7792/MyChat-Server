package org.mychat.mychatserver.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mychat.mychatserver.entity.Contact;
import org.mychat.mychatserver.entity.Group;
import org.mychat.mychatserver.entity.User;
import org.mychat.mychatserver.mapper.GroupConnectMapper;
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

    @Autowired
    private GroupConnectMapper groupConnectMapper;

    @Operation(summary = "创建群组")
    @PostMapping("/creatgroup")
    Map<Group,Object> addGroupById(Group group){
        Map<Group, Object> response = new HashMap<>();
        if((!userMapper.isUserExist(group.getOwnerid()))){
            response.put(group,false);
            return response;
        }
        Integer status1=groupMapper.insert(group);
        Integer status2=groupConnectMapper.insertGroupMember(group.getGroupid(),group.getOwnerid());
//        System.out.println(group);
        response.put(group,status1&status2);
        return response;
    }

    @Operation(summary = "根据群id查询群组")
    @PostMapping("/selectgroup/uid")
    Group getContactByUid(Integer groupid){
        if(!groupMapper.isGroupExist(groupid)){
            return null;
        }
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
    Map<String,Object> getContactByName(String groupname,Integer groupid){
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
    Map<String,Object> deleteContactByUid(Integer groupid){
        Map<String, Object> response = new HashMap<>();
        if(!groupMapper.isGroupExist(groupid)) {
            response.put("success",false);
            return response;
        }
        response.put("success",groupMapper.deleteByGroupId(groupid));
        return response;
    }

}
