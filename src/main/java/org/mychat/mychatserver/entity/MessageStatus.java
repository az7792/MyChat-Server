package org.mychat.mychatserver.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("message_status")
public class MessageStatus {
    private int uid;
    private int messageId;
    private String status;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
