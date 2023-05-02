package com.makariyp.weatherbot.bot;

import com.makariyp.weatherbot.config.BotCommands;
import com.makariyp.weatherbot.button.HaveButtonProcessing;
import com.makariyp.weatherbot.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Map;

/**
 * Работа с ботом по методу long polling
 *
 * @author Petrov Makariy
 */
@Component
public class TelegramBot extends TelegramLongPollingBot implements BotCommands {
    final String botName;
    private static final Logger LOGGER = LoggerFactory.getLogger(TelegramBot.class);
    private final Map<String, Command> commands;
    private final Map<String, HaveButtonProcessing> buttons;

    public TelegramBot(String botToken,
                       String botName,
                       TelegramBotsApi telegramBotsApi,
                       Map<String, Command> commands,
                       Map<String, HaveButtonProcessing> buttons) throws TelegramApiException {
        super(botToken);
        this.botName = botName;
        this.commands = commands;
        this.buttons = buttons;
        try {
            this.execute(new SetMyCommands(LIST_OF_COMMANDS, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e){
            LOGGER.error(e.getMessage());
        }
        telegramBotsApi.registerBot(this);
    }

    @Override
    public void onUpdateReceived(Update update) {
        long chatId;
        String command;

        if (update.hasMessage() && update.getMessage().hasText()) { // когда поступила команда
            chatId = update.getMessage().getChatId();
            command = update.getMessage().getText();

            commands.getOrDefault(command, commands.get("default"))
                    .processing(chatId);
        } else if (update.hasCallbackQuery()) { // когда нажата кнопка города
            chatId = update.getCallbackQuery().getMessage().getChatId();
            command = update.getCallbackQuery().getData().split("\s")[0];
            String id = update.getCallbackQuery().getId();
            String message = update.getCallbackQuery().getData().split("\s")[1];

            buttons.getOrDefault(command, buttons.get("default"))
                    .processingButton(id, chatId, message);
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }
}