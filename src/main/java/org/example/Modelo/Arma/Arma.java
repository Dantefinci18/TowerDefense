package org.example.Modelo.Arma;

public abstract class Arma {
    private int nivel;
    private final int costo;
    private final TipoArma tipo;
    private static final int NIVEL_INICIAL = 1;

    public Arma(TipoArma tipo, int costo){
        this.tipo = tipo;
        this.nivel = NIVEL_INICIAL;
        this.costo = costo;
    }

    public TipoArma getTipo(){return this.tipo;}

    public int getCosto(){return this.costo;}

    public int getNivel(){return this.nivel;}

    public boolean subirDeNivel(){
        if(this.nivel < 4){
            this.nivel++;
            return true;
        }

        return false;
    }

    public boolean comprobarCompra(int dinero){
        return dinero >= this.costo;
    }
}
