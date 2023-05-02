package com.makariyp.weatherbot.command;

import com.makariyp.weatherbot.message.MessageSender;
import org.springframework.stereotype.Component;

@Component("/start")
public class StartCommand extends Command{

    public StartCommand(MessageSender messageSender) {
        super(messageSender);
    }

    @Override
    public void processing(long chatId) {
        sendMessage(chatId, "Привет) Я бот, который подскажет как сегодня одеться.");
    }
}
