package com.eggshell.kanoting.exceptions;
// Daniel Laine was here
public class UnauthorizedException extends RuntimeException {

    @Override
    public String getMessage() {
        return "User is not authorized";
    }
}
