package com.devsu.bankaccount.application.port.ouput;

public interface IRabbitMessage {
    Object sendMessage(Integer request);
}
