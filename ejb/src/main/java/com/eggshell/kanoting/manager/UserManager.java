package com.eggshell.kanoting.manager;

import com.eggshell.kanoting.converter.UserConverter;
import com.eggshell.kanoting.data.User;
import com.eggshell.kanoting.repository.UserRepository;

import javax.inject.Inject;

public class UserManager {

    @Inject
    UserConverter userConverter;

    @Inject
    UserRepository userRepository;

    public User getUserById(long id) {
        return userConverter.convert(userRepository.findUser(id));
    }

    public void addUser(User user) {
        userRepository.addUser(userConverter.convert(user));
    }
}
