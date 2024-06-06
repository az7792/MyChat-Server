package org.mychat.mychatserver.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mychat.mychatserver.entity.User;

import java.util.List;

@Mapper
public interface GroupConnectMapper {

    //在群组中添加新用户
    @Insert("INSERT INTO groupconnect (groupid,uid) VALUES (#{groupid}, #{uid})")
    int insertGroupMember(int groupid,int uid);

    //在群组中查询用户
    @Select("Select uid FROM groupconnect WHERE groupid=#{groupid} AND username=#{username}")
    List<User> selectInGroupByName(int groupid,String username);

    @Select("Select uid FROM groupconnect WHERE groupid=#{groupid} AND uid=#{uid}")
    User selectInGroupByUid(int groupid,int uid);

    //在群组中删除用户
    @Delete("DELETE FROM groupconnect WHERE groupid=#{groupid} AND uid=#{uid}")
    int deleteContactById(int groupid,int uid);

    //判断群组中是否存在该用户
    @Select("SELECT COUNT(*) > 0 FROM groupconnect WHERE groupid = #{groupid} AND uid = #{uid}")
    boolean isMemberExist(int groupid,int uid);
}
