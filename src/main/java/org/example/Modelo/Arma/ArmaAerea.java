package org.example.Modelo.Arma;

import org.example.Modelo.Enemigo.Enemigo;
import org.example.Modelo.Posicion;

import java.util.HashSet;

public class ArmaAerea extends Arma{
    private static final int DANIO = 50;
    private static final int RADIO_DE_ALCANZE = 2;

    public ArmaAerea(Posicion posicion){
        super(TipoArma.AEREO, posicion);
    }

    @Override
    public void disparar(HashSet<Enemigo> enemigos){

    }
}
