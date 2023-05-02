package com.makariyp.weatherbot.service;

import com.makariyp.weatherbot.model.User;
import com.makariyp.weatherbot.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByChatId(Long id) {
        return userRepository.findByChatId(id);
    }

    @Override
    public void save(long chatId, String city) {
        User user;
        Optional<User> optionalUser = userRepository.findByChatId(chatId);
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
            user.setCity(city);
        } else {
            user = new User();
            user.setCity(city);
            user.setChatId(chatId);
        }

        userRepository.save(user);
    }
}