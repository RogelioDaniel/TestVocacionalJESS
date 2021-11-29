/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Institucion_Objetos;

/**
 *
 * @author Rogelio
 */
import java.util.ArrayList;

public class CarrerasArtísticas extends Carreras {

    private static CarrerasArtísticas carrerasArtísticas;

    public static CarrerasArtísticas getCarrerasArtísticas() {
        if (carrerasArtísticas == null) {
            carrerasArtísticas = new CarrerasArtísticas();
            carrerasArtísticas.setGustos(new ArrayList<>());
        }
        return carrerasArtísticas;
    }
}
