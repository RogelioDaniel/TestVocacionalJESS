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

public class CarrerasDefensaySeguridad extends Carreras {

    private static CarrerasDefensaySeguridad carrerasDefensaySeguridad;

    public static CarrerasDefensaySeguridad getCarrerasDefensaySeguridad() {
        if (carrerasDefensaySeguridad == null) {
            carrerasDefensaySeguridad = new CarrerasDefensaySeguridad();
            carrerasDefensaySeguridad.setGustos(new ArrayList<>());
        }
        return carrerasDefensaySeguridad;
    }
}