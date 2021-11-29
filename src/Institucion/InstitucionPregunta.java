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
import Interfaces.FormularioPrincipal;

public class InstitucionPregunta {

    // Se utilizan los tamaños de los arrays y contadores

    private int tamañoCarreraAdministrativasyContables, tamañoCarrerasArtísticas, tamañoCarrerasCienciasExactasyAgrarias, tamañoCarrerasDefensaySeguridad , tamañoCarrerasHumanísticasySociales , tamañoCarrerasIngenieriayComputacion, tamañoCarrerasMedicina;

    private int contPregCarreraAdministrativasyContables = 0, contPregCarrerasArtísticas = -1, contPregCarrerasCienciasExactasyAgrarias = -1, contPregCarrerasDefensaySeguridad = -1, contPregCarrerasHumanísticasySociales = -1 , contPregCarrerasIngenieriayComputacion = -1 , contPregCarrerasMedicinas = -1 ;
    
    private FormularioPrincipal vista;
    private static InstitucionPregunta institucionPregunta;

    private InstitucionPregunta(FormularioPrincipal vista) {
        this.vista = vista;
        setTamañosArraysGustos();
    }

    public static InstitucionPregunta getInstitucionPregunta(FormularioPrincipal vista) {
        if (institucionPregunta == null) {
            institucionPregunta = new InstitucionPregunta(vista);
        }
        return institucionPregunta;
    }

    private void setTamañosArraysGustos() {
        tamañoCarreraAdministrativasyContables = CarrerasAdministrativasyContables.getCarrerasAdministrativasyContables().getGustos().size();
        tamañoCarrerasArtísticas = CarrerasArtísticas.getCarrerasArtísticas().getGustos().size();
        tamañoCarrerasCienciasExactasyAgrarias = CarrerasCienciasExactasyAgrarias.getCarrerasCienciasExactasyAgrarias().getGustos().size();
        tamañoCarrerasDefensaySeguridad = CarrerasDefensaySeguridad.getCarrerasDefensaySeguridad().getGustos().size();
        tamañoCarrerasHumanísticasySociales = CarrerasHumanísticasySociales.getCarrerasHumanísticasySociales().getGustos().size();
        tamañoCarrerasIngenieriayComputacion = CarrerasIngenieriayComputacion.getCarrerasngenieriayComputacion().getGustos().size();
        tamañoCarrerasMedicina = CarrerasMedicina.getCarrerasMedicina().getGustos().size();
    }

    // Para el manejo de las preguntas y carreras se utilizan contadores y tamaños de arrays
    // Por cada pregunta mostrada de cada carrera se incrementa su contador
    // Si el contador iguala el tamaño del array entonces se recorrieron todas las preguntas de esa carrera
    // Y se pasa a la siguiente carrera
    // El -1 indica que los gustos de esa carrera no se deben mostrar
    // Por defecto, comienza con la gustos para la carrera de administrativas y contables
   
    public void determinarSiguientePreguntaoCarrera() {

        if (contPregCarreraAdministrativasyContables == tamañoCarreraAdministrativasyContables) {
            contPregCarreraAdministrativasyContables = -1;
            contPregCarrerasArtísticas = 0;
        } else if (contPregCarreraAdministrativasyContables > -1) {
            vista.mostrarCarreras(CarrerasAdministrativasyContables.getCarrerasAdministrativasyContables().getNombre());
            enviarIndicesVista(0, contPregCarreraAdministrativasyContables);
            vista.mostrarPregunta(CarrerasAdministrativasyContables.getCarrerasAdministrativasyContables().getGustos().get(contPregCarreraAdministrativasyContables++).getPregunta());
        }

        if (contPregCarrerasArtísticas == tamañoCarrerasArtísticas) {
            contPregCarrerasArtísticas = -1;
            contPregCarrerasCienciasExactasyAgrarias = 0;
        } else if (contPregCarrerasArtísticas > -1) {
            vista.mostrarCarreras(CarrerasArtísticas.getCarrerasArtísticas().getNombre());
            enviarIndicesVista(1, contPregCarrerasArtísticas);
            vista.mostrarPregunta(CarrerasArtísticas.getCarrerasArtísticas().getGustos().get(contPregCarrerasArtísticas++).getPregunta());
        }
        if (contPregCarrerasCienciasExactasyAgrarias == tamañoCarrerasCienciasExactasyAgrarias) {
            contPregCarrerasCienciasExactasyAgrarias = -1;
            contPregCarrerasDefensaySeguridad = 0;
        } else if (contPregCarrerasCienciasExactasyAgrarias > -1) {
            vista.mostrarCarreras(CarrerasCienciasExactasyAgrarias.getCarrerasCienciasExactasyAgrarias().getNombre());
            enviarIndicesVista(2, contPregCarrerasCienciasExactasyAgrarias);
            vista.mostrarPregunta(CarrerasCienciasExactasyAgrarias.getCarrerasCienciasExactasyAgrarias().getGustos().get(contPregCarrerasCienciasExactasyAgrarias++).getPregunta());
        }
        if (contPregCarrerasDefensaySeguridad == tamañoCarrerasDefensaySeguridad) {
            contPregCarrerasDefensaySeguridad = -1;
        } else if (contPregCarrerasDefensaySeguridad > -1) {
            vista.mostrarCarreras(CarrerasDefensaySeguridad.getCarrerasDefensaySeguridad().getNombre());
            enviarIndicesVista(3, contPregCarrerasDefensaySeguridad);
            vista.mostrarPregunta(CarrerasDefensaySeguridad.getCarrerasDefensaySeguridad().getGustos().get(contPregCarrerasDefensaySeguridad++).getPregunta());
        }
        if (contPregCarrerasIngenieriayComputacion == tamañoCarrerasIngenieriayComputacion) {
            contPregCarrerasIngenieriayComputacion = -1;
        } else if (contPregCarrerasIngenieriayComputacion > -1) {
            vista.mostrarCarreras(CarrerasIngenieriayComputacion.getCarrerasngenieriayComputacion().getNombre());
            enviarIndicesVista(4, contPregCarrerasIngenieriayComputacion);
            vista.mostrarPregunta(CarrerasIngenieriayComputacion.getCarrerasngenieriayComputacion().getGustos().get(contPregCarrerasIngenieriayComputacion++).getPregunta());
        }
        // 
        if (contPregCarreraAdministrativasyContables == -1 && contPregCarrerasArtísticas == -1 && contPregCarrerasCienciasExactasyAgrarias == -1 && contPregCarrerasDefensaySeguridad == -1  && contPregCarrerasIngenieriayComputacion == -1) {
            vista.finalizarEncuesta();
        }
    }

    // Comienza por defecto a preguntar los gustos de la primera carrera
    public void reiniciarPreguntas() {
        contPregCarreraAdministrativasyContables = 0;
        determinarSiguientePreguntaoCarrera();
    }

    // La vista requiere saber el gusto y carrera actual
    // para establecer la respuesta a la carrera correcta
    public void enviarIndicesVista(int indiceCarreras, int indiceGusto) {
        vista.setIndiceCarreras(indiceCarreras);
        vista.setIndiceGusto(indiceGusto);
    }

    // Si se responde que no, se salta al siguiente árbol o rama
    public void pasarSiguienteRamaOrArbol() {
        if (contPregCarreraAdministrativasyContables > -1 && contPregCarreraAdministrativasyContables < tamañoCarreraAdministrativasyContables) {
            contPregCarreraAdministrativasyContables = -1;
            contPregCarrerasArtísticas = 0;
        } else if (contPregCarrerasArtísticas > -1 && contPregCarrerasArtísticas < tamañoCarrerasArtísticas) {
            contPregCarrerasArtísticas = -1;
            contPregCarrerasCienciasExactasyAgrarias = 0;
        } else if (contPregCarrerasCienciasExactasyAgrarias > -1 && contPregCarrerasCienciasExactasyAgrarias < tamañoCarrerasCienciasExactasyAgrarias) {
            if (contPregCarrerasCienciasExactasyAgrarias < 4) {
                contPregCarrerasCienciasExactasyAgrarias = 4;
            } else {
                contPregCarrerasCienciasExactasyAgrarias = -1;
                contPregCarrerasDefensaySeguridad = 0;
            }
        } else if (contPregCarrerasDefensaySeguridad > -1 && contPregCarrerasDefensaySeguridad < tamañoCarrerasDefensaySeguridad) {
            if (contPregCarrerasDefensaySeguridad < 6) {
                contPregCarrerasDefensaySeguridad = 6;
            } else {
                contPregCarrerasDefensaySeguridad = -1;
                contPregCarrerasIngenieriayComputacion = 0;
            }
        }  else if (contPregCarrerasIngenieriayComputacion > -1 && contPregCarrerasIngenieriayComputacion < tamañoCarrerasIngenieriayComputacion) {
            contPregCarrerasIngenieriayComputacion = 8;
            
        } 
        
    }
}

