package com.eggshell.kanoting.repository;


import com.eggshell.kanoting.model.User;
import com.eggshell.kanoting.repository.parent.Repository;
import javax.ejb.Stateless;

@Stateless
public class UserRepository extends Repository {


    public User findUserById(long id) {
        return find(User.class, id);
    }

    public void addUser(User user) {
        add(user);
    }
}