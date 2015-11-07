package com.eggshell.kanoting.filter;


import com.eggshell.kanoting.model.Role;
import com.eggshell.kanoting.model.User;
import com.eggshell.kanoting.security.Roles;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class MySecurityContext implements SecurityContext {

    private User user;

    public MySecurityContext(User user) {
        this.user = user;
    }

    public MySecurityContext() {}

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }

    @Override
    public Principal getUserPrincipal() {
        return user;
    }

    @Override
    public boolean isSecure() {
        return true;
    }

    @Override
    public boolean isUserInRole(String role) {
        if(role.equals(Roles.LOGGED_IN) && user != null) {
            return true;
        }

        boolean isRole = false;
        // this user has this role?
        for (Role r : user.roles) {
            if (r.name.equals(role)) {
                isRole = true;
            }
        }

        return isRole;

    }
}
