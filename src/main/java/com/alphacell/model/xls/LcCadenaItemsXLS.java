package com.alphacell.model.xls;


public class LcCadenaItemsXLS {
	
    private String codigo;
	private String descripcion;


    public LcCadenaItemsXLS(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public LcCadenaItemsXLS() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



    @Override
    public String toString() {
        return  String.format("LcCadenaItemsXLS[%s,%s]",codigo,descripcion);
        //return "com.attinae.model.secretaria.Registrorequisitoaspirante[ registrorequisitoaspiranteId=" + registrorequisitoaspiranteId + " ]";
    }

}
