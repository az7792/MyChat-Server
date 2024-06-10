package org.mychat.mychatserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.mychat.mychatserver.entity.Group;

import java.util.List;

@Mapper
public interface GroupMapper extends BaseMapper<Group>{

    //根据群组id查询群组
    @Select("SELECT * FROM mygroups WHERE groupid = #{groupid}")
    Group selectById(int groupid);

    //根据群组名称查询群组
    @Select("SELECT * FROM mygroups WHERE groupname = #{groupname}")
    List<Group> selectByName(String groupname);

    //添加新群组
    @Insert("INSERT INTO mygroups VALUES (#{groupid},#{groupname},#{ownerid})")
    int creatGroup(Group group);

    //修改群组名称
    @Update("UPDATE mygroups SET groupname = #{groupname} WHERE groupid = #{groupid};")
    int updateGroupName(int groupid,String groupname);

    //根据群组id删除群组
    @Delete("DELETE FROM mygroups WHERE groupid = #{groupid}")
    Integer deleteByGroupId(int groupid);

    //判断群组是否存在
    @Select("SELECT COUNT(*) > 0 FROM mygroups WHERE groupid = #{groupid}")
    boolean isGroupExist(int groupid);

    @Select("SELECT ownerid FROM mygroups WHERE groupid = #{groupId}")
    Integer getOwnerIdByGroupId(Integer groupId);


}
