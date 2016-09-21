package com.alphacell.controller.ventas;

import org.primefaces.event.FileUploadEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.swing.*;
import java.io.*;

/**
 * Created by luis on 20/09/16.
 */
@Named
@ViewScoped
public class CargarTxtBean implements Serializable{


    private static final long serialVersionUID = -7500535820447830614L;

    private JFileChooser fileChooser;


    @PostConstruct
    public void init(){
        this.fileChooser = new JFileChooser();
    }

    public void abrirArchivo() {

        this.fileChooser.setCurrentDirectory(new java.io.File("."));
        this.fileChooser.setDialogTitle("Escoja el directorio donde estan los archivos");
        this.fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        this.fileChooser.setAcceptAllFileFilterUsed(false);




        if (this.fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): " + this.fileChooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : " + this.fileChooser.getSelectedFile());

            FacesMessage msg = new FacesMessage("Exito! ", this.fileChooser.getSelectedFile()+ "ha seleccionado.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } else {
            FacesMessage msg = new FacesMessage("No selecciono! ", this.fileChooser.getSelectedFile()+ "ha seleccionado.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }


    public void upload(FileUploadEvent event) {


        File outFile = new File(event.getFile().getFileName());
        try {
            FileOutputStream outputStream = new FileOutputStream(outFile);
            System.out.println(outFile.getAbsoluteFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        // Do what you want with the file

        System.out.println("Ruta completa: "+ event.getFile().getFileName());


    }





}
