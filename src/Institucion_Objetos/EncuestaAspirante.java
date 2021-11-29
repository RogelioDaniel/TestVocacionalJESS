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

public class EncuestaAspirante {

    private Aspirante aspirante;
    private ArrayList<Carreras> respuestasGustos = new ArrayList<>();
    private ArrayList<String> resultados = new ArrayList<>();

    public EncuestaAspirante() {
    }

    public EncuestaAspirante(Aspirante aspirante) {
        this.aspirante = aspirante;
    }

    public Aspirante getAspirante() {
        return aspirante;
    }

    public void setAspirante(Aspirante aspirante) {
        this.aspirante = aspirante;
    }

    public ArrayList<String> getResultados() {
        return resultados;
    }

    public void setResultados(ArrayList<String> resultados) {
        this.resultados = resultados;
    }

    public ArrayList<Carreras> getRespuestasGustos() {
        return respuestasGustos;
    }

    public void setRespuestasGustos(ArrayList<Carreras> respuestasGustos) {
        this.respuestasGustos = respuestasGustos;
    }

    @Override
    public String toString() {
        return "EncuestaAspirante{" + "aspirante=" + aspirante + ", respuestasGustos=" + respuestasGustos + ", resultados=" + resultados + '}';
    }

}

