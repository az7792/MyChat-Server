package org.mychat.mychatserver.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.mychat.mychatserver.entity.Message;
import org.mychat.mychatserver.entity.MessageStatus;

@Mapper
public interface MessageStatusMapper {
    @Insert("INSERT INTO message_status (uid,message_id,status) VALUES (#{uid}, #{messageId},#{status})")
    int insertMessageStatus(MessageStatus messageStatus);
}
