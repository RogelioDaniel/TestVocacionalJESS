package Institucion_Objetos;
/**
 *
 * @author Rogelio
 */

public class Gusto  { // Creamos una clase que represente el gusto del encuestado
                      // Con 3 caracteristicas principales, nombre 

    private String nombre, carrera, pregunta;
    private boolean respuesta;

    public Gusto() {
    }

    public Gusto(String carrera, String nombre, String pregunta, boolean respuesta) {
        this.carrera = carrera;
        this.nombre = nombre;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public boolean getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(boolean respuesta) {
        this.respuesta = respuesta;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return "Gusto{" + "carrera=" + carrera + ", nombre=" + nombre + ", pregunta=" + pregunta + ", respuesta=" + respuesta + '}';
    }
}
