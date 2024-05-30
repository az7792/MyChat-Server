package org.mychat.mychatserver.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mychat.mychatserver.entity.User;

@Mapper
public interface UserMapper {
    @Select("SELECT COUNT(*) > 0 FROM users WHERE uid = #{uid}")
    boolean isUserExist(int uid);

    @Select("SELECT COUNT(*) > 0 FROM users WHERE email = #{email}")
    boolean isEmailExist(String email);

    @Insert("INSERT INTO users (username, email,password) VALUES (#{username}, #{email},#{password})")
    int addUser(User user);

    @Select("SELECT COUNT(*) > 0 FROM users WHERE uid = #{uid} AND password = #{password}")
    boolean matchByUidAndPassword(int uid, String password);

    @Select("SELECT COUNT(*) > 0 FROM users WHERE email = #{email} AND password = #{password}")
    boolean matchByEmailAndPassword(String email, String password);
}
