package com.alphacell.controller.cartera;

import com.alphacell.model.cartera.JpaAlphaLimitecredito;
import com.alphacell.repository.LimiteCreditoRepository;
import com.alphacell.util.jsf.FormatoExcelPoi;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;

/**
 * Created by luis.cevallos on 28/3/2016.
 */

@Named
@ViewScoped
public class LimiteCreditoBean implements Serializable {

    private static final long serialVersionUID = -3106740355682532102L;


    private String clienteStr;
    private List<JpaAlphaLimitecredito> listaJpaAlphaLimiteCredito;
    private JpaAlphaLimitecredito jpaAlphaLimitecredito;
    private List<JpaAlphaLimitecredito> filteredListaLimiteCredito;


    @Inject
    private LimiteCreditoRepository limiteCreditoRepository;



    public String getClienteStr() {
        return clienteStr;
    }

    public void setClienteStr(String clienteStr) {
        this.clienteStr = clienteStr;
    }

    public List<JpaAlphaLimitecredito> getListaJpaAlphaLimiteCredito() {
        return listaJpaAlphaLimiteCredito;
    }

    public void setListaJpaAlphaLimiteCredito(List<JpaAlphaLimitecredito> listaJpaAlphaLimiteCredito) {
        this.listaJpaAlphaLimiteCredito = listaJpaAlphaLimiteCredito;
    }

    public JpaAlphaLimitecredito getJpaAlphaLimitecredito() {
        return jpaAlphaLimitecredito;
    }

    public void setJpaAlphaLimitecredito(JpaAlphaLimitecredito jpaAlphaLimitecredito) {
        this.jpaAlphaLimitecredito = jpaAlphaLimitecredito;
    }


    @PostConstruct
   public void inicializar()
   {

       this.listaJpaAlphaLimiteCredito  = limiteCreditoRepository.ObtenerTodos();
   }


    public List<JpaAlphaLimitecredito> getFilteredListaLimiteCredito() {
        return filteredListaLimiteCredito;
    }

    public void setFilteredListaLimiteCredito(List<JpaAlphaLimitecredito> filteredListaLimiteCredito) {
        this.filteredListaLimiteCredito = filteredListaLimiteCredito;
    }


    public void postProcessXLS(Object document) {

        HashSet omitirColumnas = new HashSet();
        //add elements to HashSet object
        omitirColumnas.add(new Integer("0"));
        omitirColumnas.add(new Integer("1"));

        FormatoExcelPoi.formatearArchivoExcel(document,omitirColumnas);

    }

}
