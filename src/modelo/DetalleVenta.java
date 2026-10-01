/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ixpat
 */
public class DetalleVenta {
     int idDetalle;
    int idVenta;
    int idInventario;
    int cantidad;
    double precioUnitario;
    double subtotal;

    public DetalleVenta() {
        this.idDetalle = 0;
        this.idVenta = 0;
        this.idInventario = 0;
        this.cantidad = 0;
        this.precioUnitario = 0.0;
        this.subtotal = 0.0;
    }

    public DetalleVenta(int idDetalle, int idVenta, int idInventario, int cantidad, double precioUnitario, double subtotal) {
        this.idDetalle = idDetalle;
        this.idVenta = idVenta;
        this.idInventario = idInventario;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
}
