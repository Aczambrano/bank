package com.devsu.bank_client.application.usecases.gateway;

public interface IRabbitListener {
    Object receiveMessage(String request);
}
