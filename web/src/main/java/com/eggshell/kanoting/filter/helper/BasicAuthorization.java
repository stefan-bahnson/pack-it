package com.eggshell.kanoting.filter.helper;

import javax.xml.bind.DatatypeConverter;

public class BasicAuthorization {

    private String username;
    private String hashedPassword;

    public BasicAuthorization(String authorizationHeader) {
        String decoded = decode(authorizationHeader);
        String[] splitted = decoded.split(":");
        this.username = splitted[0];
        // Since the hashed password might contain char ':' we must concatenate the rest of the array
        hashedPassword = "";
        for(int i = 1; i < splitted.length; i++) {
            hashedPassword += splitted[i];
        }
        if(!isSet(username) || !isSet(hashedPassword)) {
            throw new IllegalArgumentException("Username or password cannot be empty");
        }
    }

    private String decode(String authorizationHeader) {
        //Replacing "Basic THE_BASE_64" to "THE_BASE_64" directly
        authorizationHeader = authorizationHeader.replaceFirst("[B|b]asic ", "");
        byte[] decoded = DatatypeConverter.parseBase64Binary(authorizationHeader);
        if(decoded == null || decoded.length == 0) {
            throw new IllegalArgumentException("Could not decode basic authorization header");
        } else {
            return new String(decoded);
        }
    }

    private boolean isSet(String str) {
        return str != null && !str.isEmpty();
    }

    public String getUsername() {
        return username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }
}
