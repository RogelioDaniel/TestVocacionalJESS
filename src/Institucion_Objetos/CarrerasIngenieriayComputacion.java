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

public class CarrerasIngenieriayComputacion extends Carreras {

    private static CarrerasIngenieriayComputacion carrerasingenieriayComputacion;

    public static CarrerasIngenieriayComputacion getCarrerasngenieriayComputacion() {
        if (carrerasingenieriayComputacion == null) {
            carrerasingenieriayComputacion = new CarrerasIngenieriayComputacion();
            carrerasingenieriayComputacion.setGustos(new ArrayList<>());
        }
        return carrerasingenieriayComputacion;
    }
}