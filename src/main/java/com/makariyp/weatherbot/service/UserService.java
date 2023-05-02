package com.makariyp.weatherbot.service;

import com.makariyp.weatherbot.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByChatId(Long id);
    void save(long chatId, String city);
}
