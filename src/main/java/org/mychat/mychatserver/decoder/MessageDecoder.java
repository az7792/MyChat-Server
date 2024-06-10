package org.mychat.mychatserver.decoder;

import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;
import jakarta.websocket.EndpointConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mychat.mychatserver.entity.Message;

public class MessageDecoder implements Decoder.Text<Message> {
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Message decode(String s) throws DecodeException {
        try {
            return objectMapper.readValue(s, Message.class);
        } catch (Exception e) {
            throw new DecodeException(s, "Unable to decode text to Message", e);
        }
    }

    @Override
    public boolean willDecode(String s) {
        return (s != null);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        // No initialization needed
    }

    @Override
    public void destroy() {
        // No resource to close
    }
}
