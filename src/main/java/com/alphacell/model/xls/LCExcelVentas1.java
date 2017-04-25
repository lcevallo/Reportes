package com.alphacell.model.xls;

/**
 * Created by luis.cevallos on 21/3/2017.
 */
public class LCExcelVentas1 {

  Integer row;
  String cadena;
  String marca;
  String modelo_unificado;
  String cod_alpha;
  String descripcion_alpha;
  String descripcion_cadena;

    public LCExcelVentas1() {
    }

    public LCExcelVentas1(String cod_alpha, String descripcion_alpha) {
        this.cod_alpha = cod_alpha;
        this.descripcion_alpha = descripcion_alpha;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo_unificado() {
        return modelo_unificado;
    }

    public void setModelo_unificado(String modelo_unificado) {
        this.modelo_unificado = modelo_unificado;
    }

    public String getCod_alpha() {
        return cod_alpha;
    }

    public void setCod_alpha(String cod_alpha) {
        this.cod_alpha = cod_alpha;
    }

    public String getDescripcion_alpha() {
        return descripcion_alpha;
    }

    public void setDescripcion_alpha(String descripcion_alpha) {
        this.descripcion_alpha = descripcion_alpha;
    }

    public String getDescripcion_cadena() {
        return descripcion_cadena;
    }

    public void setDescripcion_cadena(String descripcion_cadena) {
        this.descripcion_cadena = descripcion_cadena;
    }

    @Override
    public String toString() {
        return  String.format("LCExcelVentas1[%s,%s]",cod_alpha,descripcion_alpha);
    }

}
