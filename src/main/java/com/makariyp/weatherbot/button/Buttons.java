package com.makariyp.weatherbot.button;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

/**
 * Кнопки городов, которые на данный момент доступны
 *
 * @author Petrov Makariy
 */
public class Buttons {
    private static final InlineKeyboardButton MOSCOW = new InlineKeyboardButton("Москва");
    private static final InlineKeyboardButton SPB = new InlineKeyboardButton("Санкт-Петербург");

    public static InlineKeyboardMarkup inlineMarkup() {
        MOSCOW.setCallbackData("Москва");
        SPB.setCallbackData("Санкт-Петербург");

        List<InlineKeyboardButton> rowInline = List.of(MOSCOW, SPB);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(rowsInLine);

        return markupInline;
    }
}