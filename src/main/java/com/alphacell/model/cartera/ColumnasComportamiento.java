package com.alphacell.model.cartera;

/**
 * Created by luis.cevallos on 11/4/2016.
 */
public class ColumnasComportamiento {
    private String header;
    private String property;
    private Integer colspan;
    private Integer rowspan;


    public ColumnasComportamiento(String header, String property) {
        this.header = header;
        this.property = property;
    }

    public ColumnasComportamiento(String header, String property,Integer rowspan) {
        this.header = header;
        this.property = property;
        this.rowspan=rowspan;
    }


    public ColumnasComportamiento(String header, Integer colspan) {
        this.header = header;
        this.colspan = colspan;
    }

    public String getHeader() {
        return header;
    }
    public String getProperty() {
        return property;
    }

    public Integer getColspan() {
        return colspan;
    }

    public void setColspan(Integer colspan) {
        this.colspan = colspan;
    }

    public Integer getRowspan() {
        return rowspan;
    }

    public void setRowspan(Integer rowspan) {
        this.rowspan = rowspan;
    }
}
