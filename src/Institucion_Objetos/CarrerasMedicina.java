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

public class CarrerasMedicina extends Carreras {

    private static CarrerasMedicina carrerasMedicina;

    public static CarrerasMedicina getCarrerasMedicina() {
        if (carrerasMedicina == null) {
            carrerasMedicina = new CarrerasMedicina();
            carrerasMedicina.setGustos(new ArrayList<>());
        }
        return carrerasMedicina;
    }
}
