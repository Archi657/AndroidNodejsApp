package com.example.sdjcomp;

public class BicicletaParaBorrar {
    private String nombre, tipo,color;
    private int idBicicleta;

    public BicicletaParaBorrar(int idBicicleta, String nombre, String tipo, String color) {
        this.idBicicleta = this.idBicicleta;
        this.nombre = nombre;
        this.tipo = tipo;
        this.color = color;
    }
    public BicicletaParaBorrar() {
        this.idBicicleta =0;
        this.nombre = "";
        this.tipo = "";
        this.color = "";
    }

    public int getIdBicicleta() {
        return idBicicleta;
    }

    public void setIdBicicleta(int idBicicleta) {
        this.idBicicleta = idBicicleta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
