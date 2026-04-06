package org.example.Modelo.Arma;

import org.example.Modelo.Enemigo.Enemigo;
import org.example.Modelo.Posicion;

import java.util.HashSet;

public class ArmaRadial extends Arma {
    private static final int RADIO_DE_ALCANZE = 1;
    private static final int RADIO_DE_DANIO = 1;
    private static final int DANIO = 10;
    public ArmaRadial(Posicion posicion){
        super(TipoArma.RADIAL,posicion);
    }

    @Override
    public void disparar(HashSet<Enemigo> enemigos){

    }
}
