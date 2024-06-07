package org.mychat.mychatserver.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.mychat.mychatserver.entity.Message;

@Mapper
public interface MessageMapper {
    //插入一条消息
    @Insert("INSERT INTO messages (from_user_uid, to_receiver, text, type, receiver_type) VALUES (#{fromUserUid}, #{toReceiver}, #{text}, #{type}, #{receiverType})")
    @Options(useGeneratedKeys = true, keyProperty = "messageId", keyColumn = "message_id")
    int insertMessage(Message message);
}
