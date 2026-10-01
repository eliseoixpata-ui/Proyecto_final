/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ixpat
 */
public class Venta {
    int idVenta;
    int idVendedor;
    String fechaVenta;
    double totalVenta;

    public Venta() {
        this.idVenta = 0;
        this.idVendedor = 0;
        this.fechaVenta = "";
        this.totalVenta = 0.0;
    }

    public Venta(int idVenta, int idVendedor, String fechaVenta, double totalVenta) {
        this.idVenta = idVenta;
        this.idVendedor = idVendedor;
        this.fechaVenta = fechaVenta;
        this.totalVenta = totalVenta;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }
    
}
