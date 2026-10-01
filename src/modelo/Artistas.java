/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ixpat
 */
public class Artistas {
    int idArtista;
    String nombreArtistico;
    String generoMusical;
    String paisOrigen;

    public Artistas() {
        this.idArtista = 0;
        this.nombreArtistico = "";
        this.generoMusical = "";
        this.paisOrigen = "";
    }

    public Artistas(int idArtista, String nombreArtistico, String generoMusical, String paisOrigen) {
        this.idArtista = idArtista;
        this.nombreArtistico = nombreArtistico;
        this.generoMusical = generoMusical;
        this.paisOrigen = paisOrigen;
    }

    public int getIdArtista() {
        return idArtista;
    }

    public String getNombreArtistico() {
        return nombreArtistico;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    public void setNombreArtistico(String nombreArtistico) {
        this.nombreArtistico = nombreArtistico;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }
    
}
