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

public class CarrerasHumanísticasySociales extends Carreras {

    private static CarrerasHumanísticasySociales carrerasHumanísticasySociales;

    public static CarrerasHumanísticasySociales getCarrerasHumanísticasySociales() {
        if (carrerasHumanísticasySociales == null) {
            carrerasHumanísticasySociales = new CarrerasHumanísticasySociales();
            carrerasHumanísticasySociales.setGustos(new ArrayList<>());
        }
        return carrerasHumanísticasySociales;
    }
}