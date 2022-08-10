package com.example.sdjcomp;

public class Pregunta {
    private String codigo, pregunta;

    public Pregunta(String codigo, String pregunta) {
        this.codigo = codigo;
        this.pregunta = pregunta;
    }

    public Pregunta() {
        this.codigo = "";
        this.pregunta = "";
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
}
