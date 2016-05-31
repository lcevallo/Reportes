package com.alphacell.util.jsf;

/**
 * Created by luis.cevallos on 1/4/2016.
 */
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtil {

    public static void addErrorMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
    }

}