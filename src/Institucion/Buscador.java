/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Institucion;

/**
 *
 * @author Rogelio
 */

import java.util.Iterator;
import jess.Fact;
import jess.Rete;

public class Buscador {

    private Rete motor;

    public Buscador(Rete motor) {
        this.motor = motor;
    }

    //Busca y retorna un hecho dado el nombre de un hecho
    public Fact buscarHechoPorNombre(String nombreHecho) {
        Fact fact = null;
        Iterator it = motor.listFacts();
        while (it.hasNext()) {
            Fact f = (Fact) it.next();
            if (f.getName().equals(nombreHecho)) {
                fact = f;
                break;
            }
        }
        return fact;
    }
}
