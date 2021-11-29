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

import Institucion_Objetos.Gusto;
import Institucion_Objetos.Carreras;
import Institucion_Objetos.CarrerasAdministrativasyContables;
import Institucion_Objetos.CarrerasArtísticas;
import Institucion_Objetos.CarrerasCienciasExactasyAgrarias;
import Institucion_Objetos.CarrerasDefensaySeguridad;
import Institucion_Objetos.CarrerasHumanísticasySociales;
import Institucion_Objetos.CarrerasIngenieriayComputacion;
import Institucion_Objetos.CarrerasMedicina;
import java.util.Iterator;
import jess.Activation;
import jess.Deffacts;
import jess.Defrule;
import jess.Fact;
import jess.JessEvent;
import jess.JessException;
import jess.Rete;

public class InstitucionMotor {

    private final Rete motor;

    public InstitucionMotor() throws JessException {
        motor = new Rete();
        motor.batch("C:\\Users\\Edifica\\Desktop\\2021B\\Sistemas expertos\\MecanismoInferenciaJESS\\src\\CLP\\reglas.clp");
        motor.reset();
        System.out.println("============================================");
        System.out.println("Motor de inferencia iniciado correctamente...");
        System.out.println("============================================");
        System.out.println("============== Basado en: ==============");
        listarDeffacts();
        System.out.println("============== Hechos ==============");
        listarHechos();
        System.out.println("============== Reglas ==============");
        listarReglas();
        System.out.println("===================================");
    }

    public void agregarGusto(Gusto gusto) throws JessException {
        motor.add(gusto);
        //listarHechos();
        //listarActivacionesReglas();
        // Con un run se ejecuta la regla
        // Con el otro, se obtiene el hecho (JessEvent)
        motor.run();
        motor.run();
    }

    public void ejecutar() throws JessException {
        System.out.println("============================================");
        System.out.println("Ejecutando...");
        System.out.println("============================================");
        motor.run();
    }

    public void agregarEscuchador(ManejadorEvento eventHandler) {
        motor.addJessListener(eventHandler);
        motor.setEventMask(JessEvent.RUN);
    }

    public void listarDeffacts() {
        Iterator it = motor.listDeffacts();
        while (it.hasNext()) {
            Deffacts deffact = (Deffacts) it.next();
            System.out.println(deffact.getName());
        }
    }

    public void listarActivacionesReglas() throws JessException {
        Iterator it = motor.listActivations();
        while (it.hasNext()) {
            Activation activation = (Activation) it.next();
            System.out.println(activation.getRule());
        }
    }

    public void listarHechos() throws JessException {
        Iterator it = motor.listFacts();
        while (it.hasNext()) {
            Fact fact = (Fact) it.next();
            switch (fact.getName()) {
                case "MAIN::Gusto":
                    System.out.println(fact.getSlotValue("carrera").toString() + ", " + fact.getSlotValue("nombre").toString()
                            + "," + fact.getSlotValue("respuesta").toString());
                    break;
                default:
                    System.out.println(fact.getName());
                    break;
            }
        }
    }

    public void listarReglas() {
        Iterator it = motor.listDefrules();
        while (it.hasNext()) {
            Defrule defrule = (Defrule) it.next();
            System.out.println(defrule.getName());
        }
    }

    public void cargarCarrerasdeClips() {
        Carreras carrerasAdministrativasyContables = CarrerasAdministrativasyContables.getCarrerasAdministrativasyContables();
        carrerasAdministrativasyContables.setNombre("Carreras administracion y contabilidad");
        Carreras carrerasArtísticas = CarrerasArtísticas.getCarrerasArtísticas();
        carrerasArtísticas.setNombre("Carreras de artes");
        Carreras carrerasCienciasExactasyAgrarias = CarrerasCienciasExactasyAgrarias.getCarrerasCienciasExactasyAgrarias();
        carrerasCienciasExactasyAgrarias.setNombre("Carreras de ciencias exactas y agrarias");
        Carreras carrerasDefensaySeguridad = CarrerasDefensaySeguridad.getCarrerasDefensaySeguridad();
        carrerasDefensaySeguridad.setNombre("Carreras de defensa y seguridad");
        Carreras carrerasHumanísticasySociales = CarrerasHumanísticasySociales.getCarrerasHumanísticasySociales();
        carrerasHumanísticasySociales.setNombre("Carreras de humanidad y sociales");
        Carreras carrerasIngenieriayComputacion = CarrerasIngenieriayComputacion.getCarrerasngenieriayComputacion();
        carrerasIngenieriayComputacion.setNombre("Carreras de ingenieria y computacion");
        Carreras carrerasMedicina = CarrerasMedicina.getCarrerasMedicina();
        carrerasMedicina.setNombre("Carreras de medicina");
        
        carrerasAdministrativasyContables.getGustos().clear();
        carrerasArtísticas.getGustos().clear();
        carrerasCienciasExactasyAgrarias.getGustos().clear();
        carrerasDefensaySeguridad.getGustos().clear();
        carrerasHumanísticasySociales.getGustos().clear();
        carrerasIngenieriayComputacion.getGustos().clear();
        carrerasMedicina.getGustos().clear();
        
        Gusto gusto = null;
        String carrera = "";

        Iterator it = motor.listFacts();

        while (it.hasNext()) {
            Fact fact = (Fact) it.next();
            if (fact.getName().equals("MAIN::Gusto")) {
                try {
                    // respuesta por defecto en falso
                    carrera = fact.getSlotValue("carrera").toString();
                    gusto = new Gusto(carrera, fact.getSlotValue("nombre").toString(), fact.getSlotValue("pregunta").toString(), false);
                } catch (JessException ex) {
                    System.out.println(ex.getMessage());
                }
                // Se quitan las comillas del string
                carrera = carrera.substring(1, carrera.length() - 1);
                switch (carrera) {
                    case "carreras_administrativas_y_contables":
                       carrerasAdministrativasyContables.getGustos().add(gusto);
                        break;
                    case "carreras_artisticas":
                        carrerasArtísticas.getGustos().add(gusto);
                        break;
                    case "carreras_cienciasexactas_y_agrarias":
                        carrerasCienciasExactasyAgrarias.getGustos().add(gusto);
                        break;
                    case "carreras_defensa_y_seguridad":
                        carrerasDefensaySeguridad.getGustos().add(gusto);
                        break;
                    case "carreras_humanisticas_y_sociales":
                        carrerasHumanísticasySociales.getGustos().add(gusto);
                        break;
                    case "carreras_ingenieria_y_computacion":
                        carrerasIngenieriayComputacion.getGustos().add(gusto);
                        break;
                    case "carreras_medicina":
                        carrerasMedicina.getGustos().add(gusto);
                        break;
                }
            }
        }
    }
}

