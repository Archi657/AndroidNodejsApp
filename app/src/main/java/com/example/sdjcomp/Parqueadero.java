package com.example.sdjcomp;

public class Parqueadero {
    private int Bicicleta_idBicicleta, Cupo_idCupo;

    public Parqueadero(int bicicleta_idBicicleta, int cupo_idCupo) {
        Bicicleta_idBicicleta = bicicleta_idBicicleta;
        Cupo_idCupo = cupo_idCupo;
    }
    public Parqueadero() {
        Bicicleta_idBicicleta = 0;
        Cupo_idCupo = 0;
    }

    public int getBicicleta_idBicicleta() {
        return Bicicleta_idBicicleta;
    }

    public void setBicicleta_idBicicleta(int bicicleta_idBicicleta) {
        Bicicleta_idBicicleta = bicicleta_idBicicleta;
    }

    public int getCupo_idCupo() {
        return Cupo_idCupo;
    }

    public void setCupo_idCupo(int cupo_idCupo) {
        Cupo_idCupo = cupo_idCupo;
    }
}
