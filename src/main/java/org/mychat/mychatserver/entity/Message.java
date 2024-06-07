package org.mychat.mychatserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

@TableName("messages")
public class Message {
    @TableId(type = IdType.AUTO)
    private int messageId;
    private int fromUserUid;
    private int toReceiver;
    private String receiverType;
    private String text;
    private LocalDateTime snetTime;
    private String type;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getFromUserUid() {
        return fromUserUid;
    }

    public void setFromUserUid(int fromUserUid) {
        this.fromUserUid = fromUserUid;
    }

    public int getToReceiver() {
        return toReceiver;
    }

    public void setToReceiver(int toReceiver) {
        this.toReceiver = toReceiver;
    }

    public String getReceiverType() {
        return receiverType;
    }

    public void setReceiverType(String receiverType) {
        this.receiverType = receiverType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getSnetTime() {
        return snetTime;
    }

    public void setSnetTime(LocalDateTime snetTime) {
        this.snetTime = snetTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



}
