package org.mychat.mychatserver.service.impl;

import org.mychat.mychatserver.entity.Message;
import org.mychat.mychatserver.entity.MessageStatus;
import org.mychat.mychatserver.mapper.MessageMapper;
import org.mychat.mychatserver.mapper.MessageStatusMapper;
import org.mychat.mychatserver.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private MessageStatusMapper messageStatusMapper;

    @Override
    public Integer saveMessage(Message message) {
        return messageMapper.insertMessage(message);
    }

    @Override
    public Integer saveMessageStatus(MessageStatus messageStatus) {
        return messageStatusMapper.insertMessageStatus(messageStatus);
    }

    @Override
    public Integer updateMessageStstus(MessageStatus messageStatus) {
        return messageStatusMapper.updateMessageStatus(messageStatus);
    }

    @Override
    public Message getMessage(Integer messageId) {
        return messageMapper.getMessageByMessageId(messageId);
    }

    @Override
    public List<Message> getMessage(List<Integer> messageIds) {
        List<Message> messages = new ArrayList<>();
        for (Integer messageId : messageIds) {
            messages.add(getMessage(messageId));
        }
        return messages;
    }

    @Override
    public List<Integer> getMessageIds(Integer uid, String status) {
        return messageStatusMapper.getMessageIds(uid, status);
    }

}
