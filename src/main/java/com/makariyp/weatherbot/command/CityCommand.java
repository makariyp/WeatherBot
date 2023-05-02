package com.makariyp.weatherbot.command;

import com.makariyp.weatherbot.button.Buttons;
import com.makariyp.weatherbot.button.HaveButtonProcessing;
import com.makariyp.weatherbot.message.MessageSender;
import com.makariyp.weatherbot.service.UserService;
import org.springframework.stereotype.Component;

@Component("/setcity")
public class CityCommand extends Command implements HaveButtonProcessing {
    private final UserService userService;
    protected CityCommand(MessageSender messageSender, UserService userService) {
        super(messageSender);
        this.userService = userService;
    }

    @Override
    public void processing(long chatId) {
        sendMessage(chatId, "Выбери свой город:", Buttons.inlineMarkup());
    }


    @Override
    public void processingButton(String id, long chatId, String receivedMessage) {
        userService.save(chatId, receivedMessage);
        answerCallbackQuery(id, "Отлично, город я сохранил, теперь можешь посмотреть прогноз");
        sendMessage(chatId, "Посмотри текущий прогноз, нажми /getactual");
    }
}
