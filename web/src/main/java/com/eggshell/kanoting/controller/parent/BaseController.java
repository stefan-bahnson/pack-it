package com.eggshell.kanoting.controller.parent;

import com.eggshell.kanoting.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

public abstract class BaseController {

    @Context
    private HttpServletRequest context;

    protected long loggedInUserId() {
        User user = (User) context.getAttribute(User.class.getName());
        return user.id;
    }
}
