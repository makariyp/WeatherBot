package com.makariyp.weatherbot.command;

import com.makariyp.weatherbot.message.MessageSender;
import org.springframework.stereotype.Component;

@Component("/help")
public class HelpCommand extends Command{
    protected HelpCommand(MessageSender messageSender) {
        super(messageSender);
    }

    @Override
    public void processing(long chatId) {
        sendMessage(chatId,
                "Я подсказываю, что лучше сегодня надеть. " +
                        "Установи свой город /setcity " +
                        "и можешь получить прогноз на сегодня /getactual");
    }
}
