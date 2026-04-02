package org.example.Modelo.Arma;

public class ArmaSimple extends Arma {
    public static final int COSTO = 50;
    private static final int DANIO = 20;
    private static  final int RADIO_DE_ALCAZE = 3;

    public ArmaSimple() {
        super(TipoArma.SIMPLE,COSTO);
    }
}
