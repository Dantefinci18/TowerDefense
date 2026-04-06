package org.example.Modelo.Arma;

public enum TipoArma {
    SIMPLE(50),
    RADIAL(100),
    AEREO(150);

    private final int costo;

    TipoArma(int costo) {
        this.costo = costo;
    }

    public int getCosto() {
        return this.costo;
    }
}
