/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ixpat
 */
public class usuarios {
    int idUsuario;
    String nombreUsuario;
    String contrasenaHash;
    String nombreCompleto;
    String rol;
    String fechaCreacion;

    public usuarios() {
        this.idUsuario = 0;
        this.nombreUsuario = "";
        this.contrasenaHash = "";
        this.nombreCompleto = "";
        this.rol = "";
        this.fechaCreacion = "";
    }

    public usuarios(int idUsuario, String nombreUsuario, String contrasenaHash, String nombreCompleto, String rol, String fechaCreacion) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contrasenaHash = contrasenaHash;
        this.nombreCompleto = nombreCompleto;
        this.rol = rol;
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasenaHash() {
        return contrasenaHash;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getRol() {
        return rol;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setContrasenaHash(String contrasenaHash) {
        this.contrasenaHash = contrasenaHash;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
}
