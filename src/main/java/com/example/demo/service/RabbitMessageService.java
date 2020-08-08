package com.example.demo.service;

import com.example.demo.dto.SimpleMessageDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.RabbitMQConfig.*;


@Service
public class RabbitMessageService {
  @Autowired
  ConnectionFactory connectionFactory;

  @Autowired
  private ObjectMapper objectMapper;

  public void sendMessage(String messageName, String messageDescription) throws Exception{
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

    SimpleMessageDto simpleMessage = new SimpleMessageDto();
    simpleMessage.setName(messageName);
    simpleMessage.setDescription(messageDescription);
    String jsonStr = objectMapper.writeValueAsString(simpleMessage);
    System.out.println(jsonStr);
    rabbitTemplate.convertAndSend(STUDENT_EXCHANGE,STUDENT_ROUTING_KEY,jsonStr);
  }
}
