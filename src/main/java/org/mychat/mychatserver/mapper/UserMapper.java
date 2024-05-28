package org.mychat.mychatserver.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT COUNT(*) > 0 FROM users WHERE uid = #{uid}")
    boolean isUserExist(@Param("uid") int uid);

    @Select("SELECT COUNT(*) > 0 FROM users WHERE email = #{email}")
    boolean isEmailExist(@Param("email") String email);
}
