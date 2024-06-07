package org.mychat.mychatserver.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mychat.mychatserver.entity.Request;

@Mapper
public interface RequestMapper {

    // 插入请求（好友请求或群组申请）
    @Insert("INSERT INTO requests (requester_id, target_user_id, target_group_id, message, created_at, request_type, approved) " +
            "VALUES (#{requesterId}, #{targetUserId}, #{targetGroupId}, #{message}, #{createdAt}, #{requestType}, #{approved})")
    int insertRequest(Request request);

    // 检查两个用户是否已经是好友（通过联系人表）
    @Select("SELECT COUNT(*) > 0 FROM contact WHERE (uid1 = #{uid1} AND uid2 = #{uid2}) OR (uid1 = #{uid2} AND uid2 = #{uid1})")
    boolean isFriend(Integer uid1, Integer uid2);

    // 检查是否存在重复的好友请求
    @Select("SELECT COUNT(*) > 0 FROM requests WHERE requester_id = #{requesterId} AND target_user_id = #{targetUserId} AND request_type = 'FRIEND' AND approved = false")
    boolean isFriendRequestExist(Integer requesterId, Integer targetUserId);

    // 检查是否存在重复的群组申请
    @Select("SELECT COUNT(*) > 0 FROM requests WHERE requester_id = #{requesterId} AND target_group_id = #{targetGroupId} AND request_type = 'GROUP' AND approved = false")
    boolean isGroupRequestExist(Integer requesterId, Integer targetGroupId);

}
