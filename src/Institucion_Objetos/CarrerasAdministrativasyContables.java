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

public class CarrerasAdministrativasyContables extends Carreras {

    private static CarrerasAdministrativasyContables carrerasAdministrativasyContables;

    public static CarrerasAdministrativasyContables getCarrerasAdministrativasyContables() {
        if (carrerasAdministrativasyContables == null) {
            carrerasAdministrativasyContables = new CarrerasAdministrativasyContables();
            carrerasAdministrativasyContables.setGustos(new ArrayList<>());
        }
        return carrerasAdministrativasyContables;
    }
}