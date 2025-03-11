package com.devsu.bank_client.application.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

public class RabbitConfig {

    private final RabbitProperties rabbitProperties;

    public RabbitConfig(RabbitProperties envProperties) {
        this.rabbitProperties = envProperties;
    }

    @Bean
    public TopicExchange customerExchange() {
        return new TopicExchange(rabbitProperties.getCustomerExchange(), true, false);
    }


    @Bean
    public Queue customerQueue() {
        return new Queue(rabbitProperties.getCustomerQueue(), true);
    }

    @Bean
    public Binding accountBinding() {
        return BindingBuilder.bind(customerQueue())
                .to(customerExchange())
                .with(rabbitProperties.getCustomerRoutingKey());
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> initializeBeans(AmqpAdmin amqpAdmin) {
        return event -> {
            amqpAdmin.declareExchange(customerExchange());
            amqpAdmin.declareQueue(customerQueue());
            amqpAdmin.declareBinding(accountBinding());
        };
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate rabbitTemplateBean(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
