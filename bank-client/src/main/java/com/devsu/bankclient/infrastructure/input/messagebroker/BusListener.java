package com.devsu.bankclient.infrastructure.input.messagebroker;

import com.devsu.bankclient.application.config.RabbitProperties;
import com.devsu.bankclient.application.port.input.GetCustomerByIdUseCase;
import com.devsu.bankclient.application.port.output.IRabbitListener;
import com.devsu.bankclient.domain.model.Customer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BusListener implements IRabbitListener {
    private final RabbitProperties rabbitEnvProperties;
    private final GetCustomerByIdUseCase getCustomerByIdUseCase;

    public BusListener(RabbitProperties rabbitEnvProperties, GetCustomerByIdUseCase getCustomerByIdUseCase) {
        this.rabbitEnvProperties = rabbitEnvProperties;
        this.getCustomerByIdUseCase = getCustomerByIdUseCase;
    }

    @Override
    @RabbitListener(queues = "#{rabbitProperties.getCustomerQueue()}")
    public Object receiveMessage(Integer request) {

        Integer opt = getCustomerByIdUseCase.execute(request)
                .map(Customer::getCustomerId)
                .orElse(null);
        System.out.println(opt);
        return opt;
    }

}
