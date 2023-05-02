package com.makariyp.weatherbot.config;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;

/**
 * Комманды, доступные пользователю
 *
 * @author Petrov Makariy
 */
public interface BotCommands {
    List<BotCommand> LIST_OF_COMMANDS = List.of(
            new BotCommand("/setcity", "Указать свой город"),
            new BotCommand("/getactual", "Получить прогноз"),
            new BotCommand("/help", "Помощь")
    );
}