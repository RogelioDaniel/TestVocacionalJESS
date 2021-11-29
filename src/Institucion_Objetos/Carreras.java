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

public abstract class Carreras {

    protected String nombre;
    protected ArrayList<Gusto> gustos;

    public Carreras() {
    }

    public Carreras(String nombre, ArrayList<Gusto> gustos) {
        this.nombre = nombre;
        this.gustos = gustos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Gusto> getGustos() {
        return gustos;
    }

    public void setGustos(ArrayList<Gusto> gustos) {
        this.gustos = gustos;
    }

    public void mostrarGustos() {
        for (Gusto gusto : gustos) {
            System.out.println(gusto);
        }
    }

    @Override
    public String toString() {
        return "Carreras{" + "nombre=" + nombre + ", gustos=" + gustos + '}';
    }
}