package com.example.sdjcomp;

public class Usuario {
    private String codigo;
    private String nombre;
    private String correo;
    private String clave;
    private int Pseguridad;
    private String Rseguridad;
    private int Rol_id;

    public Usuario(String codigo, String nombre, String correo, String clave, int pseguridad, String rseguridad, int rol_id) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.correo = correo;
        this.clave = clave;
        Pseguridad = pseguridad;
        Rseguridad = rseguridad;
        Rol_id = rol_id;
    }

    public Usuario(String nombre, String correo, String clave, int pseguridad, String rseguridad, int rol_id) {
        this.nombre = nombre;
        this.correo = correo;
        this.clave = clave;
        Pseguridad = pseguridad;
        Rseguridad = rseguridad;
        Rol_id = rol_id;
    }

    public Usuario(String codigo, String clave, int pseguridad, String rseguridad) {
        this.codigo = codigo;
        this.clave = clave;
        Pseguridad = pseguridad;
        Rseguridad = rseguridad;
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

    public int getPseguridad() {
        return Pseguridad;
    }

    public void setPseguridad(int pseguridad) {
        Pseguridad = pseguridad;
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
}
