package Entidades;

import java.util.ArrayList;
import java.util.Date;

public class Gasto {

    //Variables Individuales
    private String mensajeError, categoria = "", monto = "", fecha = "", descripcion = "", fechaformat;
    private String ingresoMen, ingresoAnu, año;
    private int row, numeracion = 0;
    private Object[] fila;
    private String[] meses;
    Double Total;

    //ArrayLists
    private ArrayList<Object[]> listaGastos = new ArrayList<>();
    private ArrayList<Date> dateList = new ArrayList<>();

    //GET Y SETS
    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double Total) {
        this.Total = Total;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getIngresoMen() {
        return ingresoMen;
    }

    public void setIngresoMen(String ingresoMen) {
        this.ingresoMen = ingresoMen;
    }

    public String getIngresoAnu() {
        return ingresoAnu;
    }

    public void setIngresoAnu(String ingresoAnu) {
        this.ingresoAnu = ingresoAnu;
    }

    public String[] getMeses() {
        return meses;
    }

    public void setMeses(String[] meses) {
        this.meses = meses;
    }

    public Object[] getFila() {
        return fila;
    }

    public void setFila(Object[] fila) {
        this.fila = fila;
    }

    public ArrayList<Date> getDateList() {
        return dateList;
    }

    public void addDateList(Date date) {
        this.dateList.add(date);
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getFechaformat() {
        return fechaformat;
    }

    public void setFechaformat(String fechaformat) {
        this.fechaformat = fechaformat;

    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getNumeracion() {
        return numeracion;
    }

    public void setNumeracion(int numeracion) {
        this.numeracion = numeracion;
    }

    public ArrayList<Object[]> getListaGastos() {
        return listaGastos;
    }

    public void addListaGastos(Object[] spend) {
        this.listaGastos.add(spend);
    }

    public void limpiarLista() {
        this.listaGastos = new ArrayList<>();
    }

}
