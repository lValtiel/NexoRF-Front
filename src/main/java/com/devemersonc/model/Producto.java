package com.devemersonc.model;

public class Producto {
    private String sku;
    private String nombre;
    private int cantidad;
    private String ubicacion;

    public Producto(String sku, String nombre, int cantidad, String ubicacion) {
        this.sku = sku;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.ubicacion = ubicacion;
    }

    public String getSku(){return this.sku;}
    public String getNombre(){return this.nombre;}
    public int getCantidad(){return this.cantidad;}
    public String getUbicacion(){return this.ubicacion;}

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
