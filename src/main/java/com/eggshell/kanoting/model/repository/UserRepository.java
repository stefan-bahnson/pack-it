package com.eggshell.kanoting.model.repository;


import com.eggshell.kanoting.model.authentication.PasswordHashes;
import com.eggshell.kanoting.model.model.Packlist;
import com.eggshell.kanoting.model.model.User;
import com.eggshell.kanoting.model.repository.parent.Repository;

import javax.ejb.Stateless;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@Stateless
public class UserRepository extends Repository {

    public boolean authenticate(User user, String password) {
        try {
            return PasswordHashes.validatePassword(password, user.password);
        } catch(NoSuchAlgorithmException | InvalidKeySpecException  e) {
            throw new RuntimeException(e);
        }
    }

    public List<Packlist> findPacklistsByUserId(long userId) {
        return getEm().createQuery("SELECT p FROM Packlist p WHERE p.user.id=:id", Packlist.class)
                .setParameter("id", userId)
                .getResultList();
    }
}