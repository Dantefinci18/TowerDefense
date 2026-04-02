import org.example.Modelo.LogicaDeNiveles.NivelLoader;
import org.example.Modelo.Celda.TipoCelda;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static org.example.Modelo.Celda.TipoCelda.*;
import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

public class NivelLoaderTest {
    @Test
    public void seCargaCorrectameteNivel1() throws FileNotFoundException {
        TipoCelda[][] mapaEsperado = {{VACIO, VACIO, CAMINO, CAMINO, CAMINO,VACIO, BASE},
                {TORRE, TORRE, CAMINO, VACIO, CAMINO, TORRE, VACIO},
                {VACIO, VACIO, CAMINO, VACIO, CAMINO, VACIO, VACIO},
                {SPAWN, CAMINO, CAMINO, VACIO, CAMINO, VACIO, VACIO},
                {VACIO, VACIO, VACIO, VACIO, CAMINO, CAMINO, VACIO}};

        NivelLoader loader = new NivelLoader("Niveles/Nivel1.json");
        TipoCelda[][] mapa = loader.getMapa();

        assertArrayEquals(mapaEsperado,mapa);
    }
}
