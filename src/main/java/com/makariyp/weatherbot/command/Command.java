package com.makariyp.weatherbot.command;

import com.makariyp.weatherbot.message.MessageSender;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public abstract class Command {
    private final MessageSender messageSender;

    protected Command(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public abstract void processing(long chatId);

    protected void sendMessage(long chatId, String msg) {
        messageSender.sendMessage(chatId, msg);
    }
    protected void sendMessage(long chatId, String msg, ReplyKeyboard replyMarkup) {
        messageSender.sendMessage(chatId, msg, replyMarkup);
    }
    protected void answerCallbackQuery(String id, String msg) {
        messageSender.answerCallbackQuery(id, msg);
    }
}
