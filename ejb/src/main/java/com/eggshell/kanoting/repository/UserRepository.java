package com.eggshell.kanoting.repository;


import com.eggshell.kanoting.authentication.PasswordHashes;
import com.eggshell.kanoting.model.User;
import com.eggshell.kanoting.repository.parent.Repository;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Iterator;
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
}