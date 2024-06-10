package org.mychat.mychatserver.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mychat.mychatserver.entity.Group;
import org.mychat.mychatserver.entity.GroupConnect;
import org.mychat.mychatserver.entity.User;
import org.mychat.mychatserver.mapper.GroupConnectMapper;
import org.mychat.mychatserver.mapper.GroupMapper;
import org.mychat.mychatserver.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "群组成员管理")
@RestController
public class GroupConnectController {

    @Autowired
    private GroupConnectMapper groupConnectMapper;

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private UserMapper userMapper;

    @Operation(summary = "群组中加入新成员")
    @PostMapping("/addusertogroup")
    Map<String,Object> addUserToGroup(Integer groupid, Integer userid){
        Map<String, Object> response = new HashMap<>();
        if((!groupMapper.isGroupExist(groupid))||(!userMapper.isUserExist(userid))){
            response.put("success",false);
            return response;
        }
        if(groupConnectMapper.isMemberExist(groupid,userid)){
            response.put("success",false);
            return response;
        }
        response.put("success",groupConnectMapper.insertGroupMember(groupid,userid)==1);
        return response;
    }

    @Operation(summary = "根据群id与用户id查询群组成员")
    @GetMapping("/selectuseringroup/uid")
    User getContactByUid(Integer groupid, Integer userid){
        if((!groupMapper.isGroupExist(groupid))||(!userMapper.isUserExist(userid))){
            return null;
        }
        if(!groupConnectMapper.isMemberExist(groupid,userid)){
            return null;
        }
        User user = userMapper.getUserByUid(userid);
        return user;
    }

    @Operation(summary = "根据群id与用户名查询群组成员")
    @GetMapping("/selectuseringroup/username")
    List<User> getContactByName(Integer groupid, String username){
        List<User> list = userMapper.getUserByUsername(username);
        List<User> userList = new ArrayList<User>();
        for(User it : list){
            if(groupConnectMapper.isMemberExist(groupid,it.getUid())){
                userList.add(it);
            }
        }
        return userList;
    }

    @Operation(summary = "根据群id拉取所有用户Uid")
    @GetMapping("/getgroupmembers/uid")
    List<Integer> getAllUidByGroupId(Integer groupid){
        return groupConnectMapper.getAllUidBygroupid(groupid);
    }

    @Operation(summary = "根据群id拉取所有用户")
    @GetMapping("/getgroupmembers/user")
    List<User> getAllUserByGroupId(Integer groupid){
        List<Integer> idList=groupConnectMapper.getAllUidBygroupid(groupid);
        List<User> userList = new ArrayList<User>();
        for(Integer it : idList){
            userList.add(userMapper.getUserByUid(it));
        }
        return userList;
    }

    @Operation(summary = "根据用户uid查询所有群")
    @PostMapping("/getgroups/uid")
    List<Group> getAllGroupOfUser(Integer uid){
        List<Integer> list=groupConnectMapper.selectAllGroupOfUser(uid);
        List<Group> glist=new ArrayList<Group>();
        for(Integer it:list){
            glist.add(groupMapper.selectById(it));
        }
        return glist;
    }

    @Operation(summary = "根据群组id与用户id删除用户")
    @DeleteMapping("deletemember/uid")
    Map<String,Object> deleteMemberByUid(Integer groupid,Integer uid){
        Map<String, Object> response = new HashMap<>();
        if(!groupConnectMapper.isMemberExist(groupid, uid)){
            response.put("success",false);
            return response;
        }
        response.put("success",groupConnectMapper.deleteContactById(groupid, uid));
        return response;
    }




}
