package com.alphacell.util.jsf;


import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * Created by luis.cevallos on 15/2/2017.
 */
public class FacesMessages implements Serializable {

    private static final long serialVersionUID = 1L;

    private void add(String message, Severity severity) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(message);
        msg.setSeverity(severity);

        context.addMessage(null, msg);
    }

    public void info(String message) {
        add(message, FacesMessage.SEVERITY_INFO);
    }

    public void error(String message) {
        add(message, FacesMessage.SEVERITY_ERROR);
    }

}
