package com.eggshell.kanoting.converter;

import com.eggshell.kanoting.data.User;
import com.eggshell.kanoting.model.UserEntity;

public class UserConverter {

    public User convert(UserEntity userEntity) {
        User user = new User();
        user.setFirstName(userEntity.getName());
        return user;
    }

    public UserEntity convert(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(user.getFirstName());
        return userEntity;
    }
}
