package com.makariyp.weatherbot.message;

import com.makariyp.weatherbot.bot.TelegramBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class MessageSenderImpl implements MessageSender{
    private TelegramBot telegramBot;
    private final ApplicationContext context;
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageSenderImpl.class);

    public MessageSenderImpl(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void sendMessage(long chatId, String msg) {
        SendMessage message = getSimplyMsg(chatId, msg);

        try {
            getTelegramBot().execute(message);
        } catch (TelegramApiException e){
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void sendMessage(long chatId, String msg, ReplyKeyboard replyMarkup) {
        SendMessage message = getSimplyMsg(chatId, msg);
        message.setReplyMarkup(replyMarkup);

        try {
            getTelegramBot().execute(message);
        } catch (TelegramApiException e){
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void answerCallbackQuery(String id, String msg) {
        AnswerCallbackQuery message = new AnswerCallbackQuery();
        message.setCallbackQueryId(id);
        message.setShowAlert(true);
        message.setText(msg);

        try {
            getTelegramBot().execute(message);
        } catch (TelegramApiException e){
            LOGGER.error(e.getMessage());
        }
    }

    private SendMessage getSimplyMsg(long chatId, String msg) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(msg);
        return message;
    }

    private TelegramBot getTelegramBot() {
        if (telegramBot == null) telegramBot = context.getBean(TelegramBot.class);
        return telegramBot;
    }
}
