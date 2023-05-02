package com.makariyp.weatherbot.command;

import com.makariyp.weatherbot.button.HaveButtonProcessing;
import com.makariyp.weatherbot.message.MessageSender;
import org.springframework.stereotype.Component;

@Component("default")
public class DefaultCommand extends Command implements HaveButtonProcessing {
    protected DefaultCommand(MessageSender messageSender) {
        super(messageSender);
    }

    @Override
    public void processing(long chatId) {
        sendMessage(chatId, "Такой команды я не знаю. Попробуй использовать меню");
    }

    @Override
    public void processingButton(String id, long chatId, String receivedMessage) {
        answerCallbackQuery(id, "Не могу определить какая кнопка нажата.");
    }
}
