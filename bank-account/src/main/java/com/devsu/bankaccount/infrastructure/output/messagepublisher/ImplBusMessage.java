package com.devsu.bankaccount.infrastructure.output.messagepublisher;

import com.devsu.bankaccount.application.config.RabbitProperties;
import com.devsu.bankaccount.application.port.ouput.IRabbitMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ImplBusMessage implements IRabbitMessage {

    private  final RabbitTemplate rabbitTemplate;
    private  final RabbitProperties rabbitEnvProperties;

    public ImplBusMessage(RabbitTemplate rabbitTemplate, RabbitProperties rabbitEnvProperties) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitEnvProperties = rabbitEnvProperties;
    }

    @Override
    public Object sendMessage(Integer request) {

        Object response = rabbitTemplate.convertSendAndReceive(
                rabbitEnvProperties.getCustomerExchange(),
                rabbitEnvProperties.getCustomerRoutingKey(),
                request
        );

        if (response != null) {

            if (response instanceof Integer) {
                return (Integer) response;
            }else {
                throw new IllegalStateException("Unexpected response type from bus: " + response.getClass().getName());
            }
        }
        return null;
    }

}
