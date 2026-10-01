/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ixpat
 */
public class Concierto {
     int idConcierto;
    int idArtista;
    String tituloEvento;
    String fechaConcierto;
    String recinto;
    String estado;

    public Concierto() {
        this.idConcierto = 0;
        this.idArtista = 0;
        this.tituloEvento = "";
        this.fechaConcierto = "";
        this.recinto = "";
        this.estado = "";
    }

    public Concierto(int idConcierto, int idArtista, String tituloEvento, String fechaConcierto, String recinto, String estado) {
        this.idConcierto = idConcierto;
        this.idArtista = idArtista;
        this.tituloEvento = tituloEvento;
        this.fechaConcierto = fechaConcierto;
        this.recinto = recinto;
        this.estado = estado;
    }

    public int getIdConcierto() {
        return idConcierto;
    }

    public int getIdArtista() {
        return idArtista;
    }

    public String getTituloEvento() {
        return tituloEvento;
    }

    public String getFechaConcierto() {
        return fechaConcierto;
    }

    public String getRecinto() {
        return recinto;
    }

    public String getEstado() {
        return estado;
    }

    public void setIdConcierto(int idConcierto) {
        this.idConcierto = idConcierto;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    public void setTituloEvento(String tituloEvento) {
        this.tituloEvento = tituloEvento;
    }

    public void setFechaConcierto(String fechaConcierto) {
        this.fechaConcierto = fechaConcierto;
    }

    public void setRecinto(String recinto) {
        this.recinto = recinto;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
