package org.example.Modelo.Arma;

public class ArmaAerea extends Arma{
    public static final int COSTO = 150;
    private static final int DANIO = 50;
    private static final int RADIO_DE_ALCANZE = 3;

    public ArmaAerea(){
        super(TipoArma.AEREO,COSTO);
    }
}
