package org.example.Modelo.LogicaDeNiveles;
import com.google.gson.Gson;
import org.example.Modelo.Celda.Base;
import org.example.Modelo.Celda.Celda;
import org.example.Modelo.Celda.TipoCelda;
import org.example.Modelo.Celda.Torre;
import org.example.Modelo.Posicion;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

import static org.example.Modelo.Celda.TipoCelda.TORRE;

public class NivelLoader {
    private final Celda[][] mapa;
    private Posicion posicionBase;
    private Posicion posicionSpawn;
    private List<Posicion> camino;

    public NivelLoader(String nivel) {

        InputStream input = getClass().getResourceAsStream("/" + nivel);
        Gson gson = new Gson();
        NivelDTO nivelDto = gson.fromJson(new InputStreamReader(Objects.requireNonNull(input)), NivelDTO.class);
        TipoCeldaDTO[][] mapaDto = nivelDto.getMapa();
        this.mapa = new Celda[mapaDto.length][mapaDto[0].length];

        for(int i = 0; i < mapaDto.length;i++){
            for(int j = 0; j < mapaDto[0].length; j++){
                switch (mapaDto[i][j]){
                    case B -> {
                        this.mapa[i][j] = new Base(80);
                        this.posicionBase = new Posicion(i,j);
                    }

                    case H -> this.mapa[i][j] = new Celda(TipoCelda.CAMINO_HORIZONTAL);
                    case V -> this.mapa[i][j] = new Celda(TipoCelda.CAMINO_VERTICAL);
                    case ABD -> this.mapa[i][j] = new Celda(TipoCelda.CURVA_ABAJO_DERECHA);
                    case ABI -> this.mapa[i][j] = new Celda(TipoCelda.CURVA_ABAJO_IZQUIERDA);
                    case ARD -> this.mapa[i][j] = new Celda(TipoCelda.CURVA_ARRIBA_DERECHA);
                    case ARI -> this.mapa[i][j] = new Celda(TipoCelda.CURVA_ARRIBA_IZQUIERDA);
                    case T -> this.mapa[i][j] = new Torre();
                    case O-> this.mapa[i][j] = new Celda(TipoCelda.VACIO);
                }
            }
        }

    }

    public Celda[][] getMapa(){
        return this.mapa;
    }

    public Posicion getPosicionSpawn(){return this.posicionSpawn;}

    public Posicion getPosicionBase(){return this.posicionBase;}
}
