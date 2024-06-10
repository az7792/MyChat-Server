package org.mychat.mychatserver.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mychat.mychatserver.entity.GroupConnect;
import org.mychat.mychatserver.entity.User;

import java.util.List;

@Mapper
public interface GroupConnectMapper {

    //在群组中添加新用户
    @Insert("INSERT INTO groupconnect (groupid,uid) VALUES (#{groupid}, #{uid})")
    int insertGroupMember(Integer groupid, Integer uid);

    //在群组中以用户名查询用户
    @Select("Select uid FROM groupconnect WHERE groupid=#{groupid} AND username=#{username}")
    List<User> selectInGroupByName(Integer groupid,String username);

    //在群组中以Uid查询用户
    @Select("Select uid FROM groupconnect WHERE groupid=#{groupid} AND uid=#{uid}")
    User selectInGroupByUid(Integer groupid,Integer uid);

    //在群组中删除用户
    @Delete("DELETE FROM groupconnect WHERE groupid=#{groupid} AND uid=#{uid}")
    int deleteContactById(Integer groupid,Integer uid);

    //判断群组中是否存在该用户
    @Select("SELECT COUNT(*) > 0 FROM groupconnect WHERE groupid = #{groupid} AND uid = #{uid}")
    boolean isMemberExist(Integer groupid,Integer uid);

    //根据群id查找所有群与用户的关系
    @Select("SELECT * FROM groupconnect WHERE groupid = #{groupid}")
    List<GroupConnect> selectAllUserInGroup(Integer groupid);

    //根据用户id查询所有所在群
    @Select("SELECT groupid FROM groupconnect WHERE uid = #{uid}")
    List<Integer> selectAllGroupOfUser(Integer uid);

    //根据群id查找所有用户uid
    @Select("SELECT uid FROM groupconnect WHERE groupid = #{groupid}")
    List<Integer> getAllUidBygroupid(Integer groupid);

    @Select("SELECT * FROM groupconnect WHERE groupid = #{groupid}")
    List<User> getAllUserBygroupid(Integer groupid);
}
