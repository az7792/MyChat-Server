package org.mychat.mychatserver.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mychat.mychatserver.entity.Contact;

import java.util.List;

@Mapper
public interface ContactMapper {

    //添加新好友
    @Insert("INSERT INTO contact (uid1, uid2,status) VALUES (#{uid1}, #{uid2},1),(#{uid2}, #{uid1},1)")
    int addContactByUid(Integer  uid1,Integer  uid2);

    //获取好友
    @Select("Select * FROM contact WHERE uid1=#{uid1}")
    List<Contact> selectByUid(Integer  uid1);

    //删除好友
    @Delete("DELETE FROM contact WHERE uid1=#{uid1} AND uid2 =#{uid2}")
    Integer deleteContactById(Integer uid1,Integer uid2);

    @Select("SELECT COUNT(*) > 0 FROM contact WHERE uid1 = #{uid1} AND uid2 = #{uid2}")
    boolean isContactExist(Integer uid1, Integer uid2);


}
