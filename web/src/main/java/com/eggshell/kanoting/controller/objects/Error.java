package com.eggshell.kanoting.controller.objects;

import javax.xml.bind.annotation.XmlRootElement;
// Daniel Laine was here
@XmlRootElement
public class Error {
    
    public String errorMessage;
    
    public Error(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
}
