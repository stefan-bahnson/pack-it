package com.eggshell.kanoting.repository;


import com.eggshell.kanoting.authentication.PasswordHashes;
import com.eggshell.kanoting.model.User;
import com.eggshell.kanoting.repository.parent.Repository;
import javax.ejb.Stateless;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Stateless
public class UserRepository extends Repository {


    public User findUserById(long id) {
        return find(User.class, id);
    }

    public User findUserByEmail(String email) {
        return getEm().createNamedQuery(User.FIND_BY_EMAIL, User.class).setParameter("email", email).getSingleResult();
    }

    public void addUser(User user) {
        add(user);
    }

    public void deleteUser(User user) {
        delete(User.class, user.id);
    }

    public boolean login(User user, String password) {

        boolean succcess = false;

        try {
            if(PasswordHashes.validatePassword(password, user.password)) {
                succcess = true;
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException  e) {
            e.printStackTrace();
        }

        return succcess;
    }
}