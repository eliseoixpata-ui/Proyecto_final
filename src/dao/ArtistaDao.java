/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.CreateConecction;
import modelo.Artistas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtistaDao {
    
    

    public boolean insertar(Artistas artista) {

        String sql = "INSERT INTO artistas "
                + "(nombre_artistico, genero_musical, pais_origen) "
                + "VALUES (?, ?, ?)";

        try {

            CreateConecction conexion = new CreateConecction();
            Connection conn = conexion.getConecction();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, artista.getNombreArtistico());
            ps.setString(2, artista.getGeneroMusical());
            ps.setString(3, artista.getPaisOrigen());

            ps.executeUpdate();

            ps.close();
            conn.close();

            return true;

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
            return false;
        }
    }

    public List<Artistas> listar() {

        List<Artistas> lista = new ArrayList<>();

        String sql = "SELECT * FROM artistas ORDER BY id_artista";

        try {

            CreateConecction conexion = new CreateConecction();
            Connection conn = conexion.getConecction();

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Artistas artista = new Artistas();

                artista.setIdArtista(rs.getInt("id_artista"));
                artista.setNombreArtistico(rs.getString("nombre_artistico"));
                artista.setGeneroMusical(rs.getString("genero_musical"));
                artista.setPaisOrigen(rs.getString("pais_origen"));

                lista.add(artista);
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }

        return lista;
    }

    public boolean actualizar(Artistas artista) {

        String sql = "UPDATE artistas SET "
                + "nombre_artistico = ?, "
                + "genero_musical = ?, "
                + "pais_origen = ? "
                + "WHERE id_artista = ?";

        try {

            CreateConecction conexion = new CreateConecction();
            Connection conn = conexion.getConecction();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, artista.getNombreArtistico());
            ps.setString(2, artista.getGeneroMusical());
            ps.setString(3, artista.getPaisOrigen());
            ps.setInt(4, artista.getIdArtista());

            ps.executeUpdate();

            ps.close();
            conn.close();

            return true;

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idArtista) {

        String sql = "DELETE FROM artistas WHERE id_artista = ?";

        try {

            CreateConecction conexion = new CreateConecction();
            Connection conn = conexion.getConecction();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, idArtista);

            ps.executeUpdate();

            ps.close();
            conn.close();

            return true;

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
            return false;
        }
    }

    public Artistas buscarPorId(int idArtista) {

        Artistas artista = null;

        String sql = "SELECT * FROM artistas WHERE id_artista = ?";

        try {

            CreateConecction conexion = new CreateConecction();
            Connection conn = conexion.getConecction();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, idArtista);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                artista = new Artistas();

                artista.setIdArtista(rs.getInt("id_artista"));
                artista.setNombreArtistico(rs.getString("nombre_artistico"));
                artista.setGeneroMusical(rs.getString("genero_musical"));
                artista.setPaisOrigen(rs.getString("pais_origen"));
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }

        return artista;
    }
}