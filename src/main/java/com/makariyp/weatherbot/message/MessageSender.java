package com.makariyp.weatherbot.message;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public interface MessageSender {
    void sendMessage(long chatId, String msg);
    void sendMessage(long chatId, String msg, ReplyKeyboard replyMarkup);
    void answerCallbackQuery(String id, String msg);
}
