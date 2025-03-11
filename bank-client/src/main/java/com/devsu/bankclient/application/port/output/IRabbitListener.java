package com.devsu.bankclient.application.port.output;

public interface IRabbitListener {
    Object receiveMessage(String request);
}
