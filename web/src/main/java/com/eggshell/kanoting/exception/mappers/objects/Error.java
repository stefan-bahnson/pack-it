package com.eggshell.kanoting.exception.mappers.objects;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Error {
    
    public String errorMessage;
    
    public Error(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
}
