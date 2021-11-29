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
import Interfaces.FormularioPrincipal;
import jess.Fact;
import jess.JessEvent;
import jess.JessException;
import jess.JessListener;
import jess.Rete;

public class ManejadorEvento implements JessListener {

    private FormularioPrincipal vista;
    private Buscador buscador;

    public ManejadorEvento(FormularioPrincipal vista) {
        this.vista = vista;
    }

    @Override
    public void eventHappened(JessEvent je) throws JessException {
        // A partir de un evento Jess, se obtiene el tipo de evento y la fuente
        int tipo = je.getType();
        Rete rete = (Rete) je.getSource();
        buscador = new Buscador(rete);
        String nombreHecho;
        // Cuando se ejecuta un run (después de agregar un gusto) se verifica 
        // si hay hechos equivalentes a las reglas definidas
        if (tipo == JessEvent.RUN) {
            nombreHecho = "MAIN::administrativas_y_Contables";
            buscarHechoPorNombre(nombreHecho);

            nombreHecho = "MAIN::carreras_artisticas";
            buscarHechoPorNombre(nombreHecho);

            nombreHecho = "MAIN::carreras_ciencias_exactas";
            buscarHechoPorNombre(nombreHecho);
            
            nombreHecho = "MAIN::carreras_defensa_y_seguridad";
            buscarHechoPorNombre(nombreHecho);
                       
            nombreHecho = "MAIN::carreras_ingenieria_y_computacion";
            buscarHechoPorNombre(nombreHecho);
                
            nombreHecho = "MAIN::sin_preferencia";
            buscarHechoPorNombre(nombreHecho);

  


        }
    }

    private void buscarHechoPorNombre(String nombreHecho) {
        String respuesta;
        Fact respuestaGusto = buscador.buscarHechoPorNombre(nombreHecho);
        if (respuestaGusto != null) {
            try {
                respuesta = respuestaGusto.get(0).toString();
                enviarRespuestaVista(respuesta);
            } catch (JessException ex) {
                System.out.println("Error al obtener la respuesta " + ex.getMessage());
            }
        }
    }

    private void enviarRespuestaVista(String respuesta) {
       vista.agregarRespuesta(respuesta);
    }
}
