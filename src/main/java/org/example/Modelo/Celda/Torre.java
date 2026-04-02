package org.example.Modelo.Celda;

import org.example.Modelo.Arma.Arma;

public class Torre extends Celda{
    private Arma arma;

    public Torre(){
        super(TipoCelda.TORRE);
    }

    public boolean agregarArma(Arma arma, int dinero) {
        if (arma.comprobarCompra(dinero)){
            if (this.arma == null) {
                this.arma = arma;
                return true;
            }

            return this.arma.subirDeNivel();
        }

        return false;
    }

    public Arma getArma(){ return this.arma;}
}
