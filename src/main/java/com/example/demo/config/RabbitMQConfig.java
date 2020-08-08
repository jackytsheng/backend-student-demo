package com.example.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

@SpringBootApplication
public class RabbitMQConfig {

  public static final String STUDENT_QUEUE = "StudentQueue";
  public static final String STUDENT_EXCHANGE = "StudentExchange";
  public static final String STUDENT_ROUTING_KEY = "StudentRouting";
  @Bean
  ObjectMapper getObjectMapper(){
    return new ObjectMapper();
  }
  @Bean
  Queue myQueue(){
    return new Queue(STUDENT_QUEUE,true);
  }
  @Bean
  Exchange myExchange(){
    return ExchangeBuilder.directExchange(STUDENT_EXCHANGE).durable(true).build();
  }

  @Bean
  Binding binding(){
    return BindingBuilder
      .bind(myQueue())
      .to(myExchange())
      .with(STUDENT_ROUTING_KEY)
      .noargs();
  }
  @Bean
  public MessageConverter jsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  ConnectionFactory connectionFactory(){
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    factory.setPort(5672);
    factory.setVirtualHost("/");
    factory.setUsername("guest");
    factory.setPassword("guest");
    return factory;
  }
}

