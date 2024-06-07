package org.mychat.mychatserver.mapper;

import org.apache.ibatis.annotations.*;
import org.mychat.mychatserver.entity.MessageStatus;

import java.util.List;

@Mapper
public interface MessageStatusMapper {
    @Insert("INSERT INTO message_status (uid,message_id,status) VALUES (#{uid}, #{messageId},#{status})")
    int insertMessageStatus(MessageStatus messageStatus);

    @Update("UPDATE message_status SET status = #{status} WHERE uid = #{uid} AND message_id = #{messageId}")
    int updateMessageStatus(MessageStatus messageStatus);

    @Select("SELECT message_id FROM message_status WHERE uid = #{uid} AND status = #{status}")
    List<Integer> getMessageIds(Integer uid, String status);
}
