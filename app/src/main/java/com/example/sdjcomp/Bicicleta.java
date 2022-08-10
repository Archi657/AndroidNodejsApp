package com.example.sdjcomp;

public class Bicicleta {
    private int idBicicleta;
    private String cedulaPropietario;
    private String fechaRegistro;
    private String lugarRegistro;
    private String marca;
    private String numSerie;
    private String tipo;
    private String color;
    private String Estudiante_id;

    public Bicicleta() {
    }

    public Bicicleta(int idBicicleta, String cedulaPropietario, String fechaRegistro, String lugarRegistro, String marca, String numSerie, String tipo, String color, String estudiante_id) {
        this.idBicicleta = idBicicleta;
        this.cedulaPropietario = cedulaPropietario;
        this.fechaRegistro = fechaRegistro;
        this.lugarRegistro = lugarRegistro;
        this.marca = marca;
        this.numSerie = numSerie;
        this.tipo = tipo;
        this.color = color;
    }

    public Bicicleta(String marca, String tipo, String color) {
        this.marca = marca;
        this.tipo = tipo;
        this.color = color;
    }

    public int getIdBicicleta() {
        return idBicicleta;
    }

    public void setIdBicicleta(int idBicicleta) {
        this.idBicicleta = idBicicleta;
    }

    public String getCedulaPropietario() {
        return cedulaPropietario;
    }

    public void setCedulaPropietario(String cedulaPropietario) {
        this.cedulaPropietario = cedulaPropietario;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getLugarRegistro() {
        return lugarRegistro;
    }

    public void setLugarRegistro(String lugarRegistro) {
        this.lugarRegistro = lugarRegistro;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEstudiante_id() {
        return Estudiante_id;
    }

    public void setEstudiante_id(String estudiante_id) {
        Estudiante_id = estudiante_id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
