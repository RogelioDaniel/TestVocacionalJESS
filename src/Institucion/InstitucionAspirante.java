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
public class InstitucionAspirante {

    public InstitucionAspirante() {
    }

    public boolean aspiranteValido(String nombre, String apellido) {
        nombre = nombre.replace(" ", "");
        apellido = apellido.replace(" ", "");
        return !(nombre.isEmpty() || apellido.isEmpty()
                || !Ayudas.soloLetras(nombre) || !Ayudas.soloLetras(apellido));
    }
}
