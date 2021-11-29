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

public class CarrerasCienciasExactasyAgrarias extends Carreras {

    private static CarrerasCienciasExactasyAgrarias carrerasCienciasExactasyAgrarias;

    public static CarrerasCienciasExactasyAgrarias getCarrerasCienciasExactasyAgrarias() {
        if (carrerasCienciasExactasyAgrarias == null) {
            carrerasCienciasExactasyAgrarias = new CarrerasCienciasExactasyAgrarias();
            carrerasCienciasExactasyAgrarias.setGustos(new ArrayList<>());
        }
        return carrerasCienciasExactasyAgrarias;
    }
}