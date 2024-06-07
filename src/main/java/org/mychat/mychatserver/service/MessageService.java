package org.mychat.mychatserver.service;

import org.mychat.mychatserver.entity.Message;
import org.mychat.mychatserver.entity.MessageStatus;

public interface MessageService {
    Integer saveMessage(Message message);
    Integer saveMessageStatus(MessageStatus messageStatus);
}
