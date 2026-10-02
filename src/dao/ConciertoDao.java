/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import conexion.CreateConecction;
import modelo.Concierto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author ixpat
 */
public class ConciertoDao {
    
    CreateConecction connFactory = new CreateConecction();
    
    public boolean guardar(Concierto concierto){
        String sql = "INSERT INTO conciertos "
                    +"(id_artista, titulo_evento, fecha_concierto, recinto, estado) "
                    +"VALUES (?, ?, ?, ?, ? )";
        Connection conn = connFactory.getConecction();
        
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, concierto.getIdArtista());
            ps.setString(2, concierto.getTituloEvento());
            ps.setString(3, concierto.getFechaConcierto());
            ps.setString(4, concierto.getRecinto());
            ps.setString(5, concierto.getEstado());
            
            ps.executeUpdate();
            
            return true;
        }catch (SQLException e){
            System.out.println("Error al guardar contacto "+ e.getMessage());
            return false;
        }
       
    }
    public Concierto consultar(int id) {

        String sql = "SELECT * FROM conciertos "
                + "WHERE id_concierto = ?";

        Connection conn = connFactory.getConecction();

        try {

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Concierto concierto = new Concierto();

                concierto.setIdConcierto(
                        rs.getInt("id_concierto"));

                concierto.setIdArtista(
                        rs.getInt("id_artista"));

                concierto.setTituloEvento(
                        rs.getString("titulo_evento"));

                concierto.setFechaConcierto(
                        rs.getString("fecha_concierto"));

                concierto.setRecinto(
                        rs.getString("recinto"));

                concierto.setEstado(
                        rs.getString("estado"));

                return concierto;
            }

        } catch (SQLException e) {

            System.out.println("Error al consultar concierto: "
                    + e.getMessage());
        }

        return null;
    }
    public boolean actualizar(Concierto concierto) {

        String sql = "UPDATE conciertos SET "
                + "id_artista = ?, "
                + "titulo_evento = ?, "
                + "fecha_concierto = ?::timestamp, "
                + "recinto = ?, "
                + "estado = ? "
                + "WHERE id_concierto = ?";

        Connection conn = connFactory.getConecction();

        try {

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, concierto.getIdArtista());
            ps.setString(2, concierto.getTituloEvento());
            ps.setString(3, concierto.getFechaConcierto());
            ps.setString(4, concierto.getRecinto());
            ps.setString(5, concierto.getEstado());

            ps.setInt(6, concierto.getIdConcierto());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println("Error al actualizar concierto: "
                    + e.getMessage());

            return false;
        }
    }
    public boolean eliminar(int id) {

        String sql = "DELETE FROM conciertos "
                + "WHERE id_concierto = ?";

        Connection conn = connFactory.getConecction();

        try {

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println("Error al eliminar concierto: "
                    + e.getMessage());

            return false;
        }
    }

}
