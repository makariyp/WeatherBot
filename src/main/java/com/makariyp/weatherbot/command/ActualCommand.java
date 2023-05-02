package com.makariyp.weatherbot.command;

import com.makariyp.weatherbot.message.MessageSender;
import com.makariyp.weatherbot.model.User;
import com.makariyp.weatherbot.service.UserService;
import com.makariyp.weatherbot.service.WeatherService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("/getactual")
public class ActualCommand extends Command{
    private final UserService userService;
    private final WeatherService weatherService;
    protected ActualCommand(MessageSender messageSender, UserService userService, WeatherService weatherService) {
        super(messageSender);
        this.userService = userService;
        this.weatherService = weatherService;
    }

    @Override
    public void processing(long chatId) {
        Optional<User> user = userService.findByChatId(chatId);
        String msg;
        if (user.isPresent()) {
            msg = weatherService.getActual(user.get().getCity());
        } else {
            msg = "Сначала выбери свой город /setcity";
        }
        sendMessage(chatId, msg);
    }
}
