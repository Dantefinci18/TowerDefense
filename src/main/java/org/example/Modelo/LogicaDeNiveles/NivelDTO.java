package org.example.Modelo.LogicaDeNiveles;


import org.example.Modelo.Enemigo.TipoEnemigo;
import org.example.Modelo.Posicion;

class NivelDTO {
    private TipoCeldaDTO[][] mapa;
    private TipoEnemigo[][] oleadas;
    private Posicion spawn;
    private Posicion[] camino;

    public TipoCeldaDTO[][] getMapa() { return this.mapa; }
    public Posicion getPosicionSpawn() {return this.spawn;}
    public TipoEnemigo[][] getOleadas() { return this.oleadas; }
    public Posicion[] getCamino(){return this.camino;}
}
