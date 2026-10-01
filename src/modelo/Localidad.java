/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ixpat
 */
public class Localidad {
    int idLocalidad;
    String nombre;

    public Localidad() {
        this.idLocalidad = 0;
        this.nombre = "";
    }

    public Localidad(int idLocalidad, String nombre) {
        this.idLocalidad = idLocalidad;
        this.nombre = nombre;
    }

    public int getIdLocalidad() {
        return idLocalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setIdLocalidad(int idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
