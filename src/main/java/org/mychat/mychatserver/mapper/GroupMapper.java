package org.mychat.mychatserver.mapper;

import org.apache.ibatis.annotations.*;
import org.mychat.mychatserver.entity.Group;

import java.util.List;

@Mapper
public interface GroupMapper {

    //根据群组id查询群组
    @Select("SELECT * FROM `group` WHERE groupid = #{groupid}")
    Group selectById(int groupid);

    //根据群组名称查询群组
    @Select("SELECT * FROM `group` WHERE groupname = #{groupname}")
    List<Group> selectByName(String groupname);

    //添加新群组
    @Insert("INSERT INTO `group` (groupname,ownerid) VALUES (#{groupname},#{ownerid})")
    int creatGroup(String groupname,int ownerid);

    //修改群组名称
    @Update("UPDATE `group` SET groupname = #{groupname} WHERE groupid = #{groupid};")
    int updateGroupName(int groupid,String groupname);

    //根据群组id删除群组
    @Delete("DELETE FROM `group` WHERE groupid = #{groupid}")
    int deleteByGroupId(int groupid);

    //判断群组是否存在
    @Select("SELECT COUNT(*) > 0 FROM `group` WHERE groupid = #{groupid}")
    boolean isGroupExist(int groupid);


}
