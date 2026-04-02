package org.example.Modelo.Arma;

public class ArmaRadial extends Arma {
    public static final int COSTO = 100;
    private static final int RADIO_DE_ALCANZE = 1;
    private static final int RADIO_DE_DANIO = 1;
    private static final int DANIO = 10;
    public ArmaRadial(){
        super(TipoArma.RADIAL,COSTO);
    }
}
