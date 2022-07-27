package com.example.sdjcomp;

import android.app.Application;

public class Sesion extends Application {
    private String codigo,nombre,correo,clave,Rseguridad;
    private int Rol_id,Pseguridad;
    private boolean validado;

    public Sesion() {
    }

    public Sesion(String codigo, String nombre, String correo, String clave, String rseguridad, int rol_id, int pseguridad, boolean validado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.correo = correo;
        this.clave = clave;
        Rseguridad = rseguridad;
        Rol_id = rol_id;
        Pseguridad = pseguridad;
        this.validado = validado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRseguridad() {
        return Rseguridad;
    }

    public void setRseguridad(String rseguridad) {
        Rseguridad = rseguridad;
    }

    public int getRol_id() {
        return Rol_id;
    }

    public void setRol_id(int rol_id) {
        Rol_id = rol_id;
    }

    public int getPseguridad() {
        return Pseguridad;
    }

    public void setPseguridad(int pseguridad) {
        Pseguridad = pseguridad;
    }

    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }
}
