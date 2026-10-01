/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ixpat
 */
public class InventarioBoleto {
    int idInventario;
    int idConcierto;
    int idLocalidad;
    double precio;
    int cantidadTotal;
    int cantidadDisponible;

    public InventarioBoleto() {
        this.idInventario = 0;
        this.idConcierto = 0;
        this.idLocalidad = 0;
        this.precio = 0.0;
        this.cantidadTotal = 0;
        this.cantidadDisponible = 0;
    }

    public InventarioBoleto(int idInventario, int idConcierto, int idLocalidad, double precio, int cantidadTotal, int cantidadDisponible) {
        this.idInventario = idInventario;
        this.idConcierto = idConcierto;
        this.idLocalidad = idLocalidad;
        this.precio = precio;
        this.cantidadTotal = cantidadTotal;
        this.cantidadDisponible = cantidadDisponible;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public int getIdConcierto() {
        return idConcierto;
    }

    public int getIdLocalidad() {
        return idLocalidad;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public void setIdConcierto(int idConcierto) {
        this.idConcierto = idConcierto;
    }

    public void setIdLocalidad(int idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }
    
}
