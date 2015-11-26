package com.eggshell.kanoting.model.exceptions;
public class UnauthorizedException extends RuntimeException {

    @Override
    public String getMessage() {
        return "User is not authorized";
    }
}
