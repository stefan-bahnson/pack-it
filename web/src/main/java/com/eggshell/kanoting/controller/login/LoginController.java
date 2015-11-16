package com.eggshell.kanoting.controller.login;


import com.eggshell.kanoting.model.User;
import com.eggshell.kanoting.repository.UserRepository;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;
@Path("/login")
public class LoginController {

    @Inject
    UserRepository userRepository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> login(User user) {
        System.out.println(user.email);
        String password = user.password;
        user = userRepository.findUserByEmail(user.email);
        Map<String, Object> result = new HashMap<>();
        if(userRepository.authenticate(user, password)) {
            result.put("success", true);
            result.put("object", user );
        } else {
            result.put("succuess", false);
            result.put("object", null);
        }

        return  result;
    }

}
