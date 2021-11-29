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

import Institucion_Objetos.EncuestaAspirante;
import Institucion_Objetos.Aspirante;
import Institucion_Objetos.Gusto;
import Institucion_Objetos.Carreras;
import Institucion_Objetos.CarrerasAdministrativasyContables;
import Institucion_Objetos.CarrerasArtísticas;
import Institucion_Objetos.CarrerasCienciasExactasyAgrarias;
import Institucion_Objetos.CarrerasDefensaySeguridad;
import Institucion_Objetos.CarrerasHumanísticasySociales;
import Institucion_Objetos.CarrerasIngenieriayComputacion;
import Institucion_Objetos.CarrerasMedicina;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class InstitucionEncuestaAspirante {

    private EncuestaAspirante encuestaAspirante;
    private static FileWriter fw;
    private static BufferedWriter bw;
    private int cont = 0;

    public InstitucionEncuestaAspirante(EncuestaAspirante encuestaAspirante) {
        this.encuestaAspirante = encuestaAspirante;
        agregarGustos();
    }

    public void agregarGustos() {
        // Se obtienen los trastornos ya creados y seteados por el motor, con las respuestas en false por defecto
        encuestaAspirante.getRespuestasGustos().add(CarrerasAdministrativasyContables.getCarrerasAdministrativasyContables());
        encuestaAspirante.getRespuestasGustos().add(CarrerasArtísticas.getCarrerasArtísticas());
        encuestaAspirante.getRespuestasGustos().add(CarrerasCienciasExactasyAgrarias.getCarrerasCienciasExactasyAgrarias());
        encuestaAspirante.getRespuestasGustos().add(CarrerasDefensaySeguridad.getCarrerasDefensaySeguridad());
        encuestaAspirante.getRespuestasGustos().add(CarrerasHumanísticasySociales.getCarrerasHumanísticasySociales());
        encuestaAspirante.getRespuestasGustos().add(CarrerasIngenieriayComputacion.getCarrerasngenieriayComputacion());
        encuestaAspirante.getRespuestasGustos().add(CarrerasMedicina.getCarrerasMedicina());

    }

    public void setRespuestaGustoCarrera(boolean respuesta, int indiceCarrera, int indiceGusto) {
        encuestaAspirante.getRespuestasGustos().get(indiceCarrera).getGustos().get(indiceGusto).setRespuesta(respuesta);
    }

    public Gusto getInclinacionCarrera(int indiceCarrera, int IndicesGusto) {
        return encuestaAspirante.getRespuestasGustos().get(indiceCarrera).getGustos().get(IndicesGusto);
    }

    public void agregarEncuestaAspirante(String encuesta) {
        encuestaAspirante.getResultados().add(encuesta);
    }

    public void eliminarEncuesta(String encuesta) {
        encuestaAspirante.getResultados().remove(encuesta);
    }

    public void verificarDiagnisticoSinPreferencia() {
        if (encuestaAspirante.getResultados().contains("Sin preferencia") && encuestaAspirante.getResultados().size() > 1) {
            eliminarEncuesta("Sin preferencia");
        }
    }

    // Se inserta aspirante, gustos y encuesta
    public void guardarEncuesta() {
        try {
            fw = new FileWriter("C:\\Users\\Edifica\\Desktop\\2021B\\Sistemas expertos\\MecanismoInferenciaJESS\\Resultados del aspirante.txt");
            bw = new BufferedWriter(fw);
            
            bw.write("===========================");
            bw.newLine();
            bw.append("RESULTADOS DEL ASPIRANTE");
            bw.newLine();
            bw.append("===========================");
            bw.newLine();

            bw.append("FECHA: " + Ayudas.getFechaActual());
            bw.newLine();
            bw.append("HORA: " + Ayudas.getHoraActual());
            bw.newLine();

            bw.append("===========================");
            bw.newLine();
            bw.append("ASPIRANTE");
            bw.newLine();
            bw.append("===========================");
            bw.newLine();
            insertarAspirante(encuestaAspirante.getAspirante());

            bw.append("===========================");
            bw.newLine();
            bw.append("GUSTOS");
            bw.newLine();
            bw.append("===========================");
            bw.newLine();

            bw.append("===========================================");
            bw.newLine();
            bw.append(encuestaAspirante.getRespuestasGustos().get(0).getNombre());
            bw.newLine();
            bw.append("===========================================");
            bw.newLine();
            insertarGustos(encuestaAspirante.getRespuestasGustos().get(0).getGustos());

            bw.append("===========================================");
            bw.newLine();
            bw.append(encuestaAspirante.getRespuestasGustos().get(1).getNombre());
            bw.newLine();
            bw.append("===========================================");
            bw.newLine();
            insertarGustos(encuestaAspirante.getRespuestasGustos().get(1).getGustos());

            bw.append("===========================================");
            bw.newLine();
            bw.append(encuestaAspirante.getRespuestasGustos().get(2).getNombre());
            bw.newLine();
            bw.append("===========================================");
            bw.newLine();
            insertarGustos(encuestaAspirante.getRespuestasGustos().get(2).getGustos());

            bw.append("===========================================");
            bw.newLine();
            bw.append(encuestaAspirante.getRespuestasGustos().get(3).getNombre());
            bw.newLine();
            bw.append("===========================================");
            bw.newLine();
            insertarGustos(encuestaAspirante.getRespuestasGustos().get(3).getGustos());

            bw.append("===========================================");
            bw.newLine();
            bw.append(encuestaAspirante.getRespuestasGustos().get(4).getNombre());
            bw.newLine();
            bw.append("===========================================");
            bw.newLine();
            insertarGustos(encuestaAspirante.getRespuestasGustos().get(4).getGustos());

            bw.append("===========================================");
            bw.newLine();
            bw.append(encuestaAspirante.getRespuestasGustos().get(5).getNombre());
            bw.newLine();
            bw.append("===========================================");
            bw.newLine();
            insertarGustos(encuestaAspirante.getRespuestasGustos().get(5).getGustos());

            bw.append("===========================================");
            bw.newLine();
            bw.append(encuestaAspirante.getRespuestasGustos().get(6).getNombre());
            bw.newLine();
            bw.append("===========================================");
            bw.newLine();
            insertarGustos(encuestaAspirante.getRespuestasGustos().get(6).getGustos());

            
            bw.append("===========================");
            bw.newLine();
            bw.append("Encuesta resultados");
            bw.newLine();
            bw.append("===========================");
            bw.newLine();
            insertarEncuesta(encuestaAspirante.getResultados());

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void insertarAspirante(Aspirante aspirante) {
        try {
            bw.append("Aspirante: " + aspirante.getApellido() + ", " + aspirante.getNombre());
            bw.newLine();
            bw.append("País: " + aspirante.getPais());
            bw.newLine();
            bw.append("Sexo: " + aspirante.getSexo());
            bw.newLine();
            bw.append("Edad: " + aspirante.getEdad());
            bw.newLine();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void insertarGustos(ArrayList<Gusto> gustosporCarrera) {
        cont = 0;
        for (Gusto sint : gustosporCarrera) {
            try {
                bw.append(++cont + ". " + sint.getPregunta() + ": " + sint.getRespuesta());
                bw.newLine();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private void insertarEncuesta(ArrayList<String> encuestas) {
        cont = 0;
        for (String encuesta : encuestas) {
            try {
                bw.append(++cont + ". " + encuesta);
                bw.newLine();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
