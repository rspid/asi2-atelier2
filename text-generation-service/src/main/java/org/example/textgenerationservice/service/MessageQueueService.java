package org.example.textgenerationservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.textgenerationservice.model.GenerationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageQueueService {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    public MessageQueueService(JmsTemplate jmsTemplate, ObjectMapper objectMapper) {
        this.jmsTemplate = jmsTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendMessageToQueue(GenerationRequest request) {
        try {
            String messageJson = objectMapper.writeValueAsString(request);  // Conversion en JSON
            jmsTemplate.convertAndSend("textGenerationQueue", messageJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
