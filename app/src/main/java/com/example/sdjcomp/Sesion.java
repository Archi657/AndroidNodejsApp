package com.example.sdjcomp;

import android.app.Application;

public class Sesion extends Application {
    private String codigo,nombre,correo,clave,Rseguridad;
    private int Rol_id,Pseguridad,idBici;
    private boolean validado;

    private int cupo, bicicleta;
    private String codio,seccion;

    public Sesion() {
        this.codigo = "";
        this.nombre = "";
        this.correo = "";
        this.clave = "";
        Rseguridad = "";
        Rol_id = 0;
        Pseguridad = 0;
        this.validado = false;
        this.cupo = 0;
        this.bicicleta = 0;
        this.codio = "";
        this.seccion="";
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

    public Sesion(int cupo, int bicicleta, String codio) {
        this.cupo = cupo;
        this.bicicleta = bicicleta;
        this.codio = codio;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public int getBicicleta() {
        return bicicleta;
    }

    public void setBicicleta(int bicicleta) {
        this.bicicleta = bicicleta;
    }

    public String getCodio() {
        return codio;
    }

    public void setCodio(String codio) {
        this.codio = codio;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public int getIdBici() {
        return idBici;
    }

    public void setIdBici(int idBici) {
        this.idBici = idBici;
    }
}
