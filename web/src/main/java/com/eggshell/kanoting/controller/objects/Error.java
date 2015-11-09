package com.eggshell.kanoting.controller.objects;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Error {
    
    public String errorMessage;
    
    public Error(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
}
