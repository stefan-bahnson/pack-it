package com.eggshell.kanoting.filter.helper;
import javax.xml.bind.DatatypeConverter;
// Daniel Laine was here
public class BasicAuthorization {

    private String username;
    private String password;

    public BasicAuthorization(String authorizationHeader) {
        String decoded = decode(authorizationHeader);
        String[] splitted = decoded.split(":");
        this.username = splitted[0];
        // Since the hashed password might contain char ':' we must concatenate the rest of the array
        password = "";
        for(int i = 1; i < splitted.length; i++) {
            password += splitted[i];
        }
        if(!isSet(username) || !isSet(password)) {
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

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }
}
