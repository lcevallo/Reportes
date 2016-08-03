package com.alphacell.controller;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by luis on 28/07/16.
 */
@Named
@ApplicationScoped
public class ExporterController implements Serializable {

    private static final long serialVersionUID = 20120316L;

    private Boolean customExporter;


    public ExporterController() {
        customExporter=false;
    }

    public Boolean getCustomExporter() {
        return customExporter;
    }

    public void setCustomExporter(Boolean customExporter) {
        this.customExporter = customExporter;
    }
}
