/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import conexion.CreateConecction;
import modelo.Localidad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ixpat
 */
public class LocalidadesDao {
    CreateConecction connFactory = new CreateConecction();
    
    public boolean guardar (Localidad localidad){
       String sql = "INSERT INTO localidades (nombre) VALUES (?)";

        try (Connection conn = connFactory.getConecction();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, localidad.getNombre());

            int filas = ps.executeUpdate();

            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al guardar: " + e.getMessage());
            return false;
        }
    }
    public boolean actualizar (Localidad localidad ){
        String sql = "UPDATE localidades SET nombre = ? WHERE id_localidad = ?";

        try (Connection conn = connFactory.getConecction();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, localidad.getNombre());
            ps.setInt(2, localidad.getIdLocalidad());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
            return false;
        }
    }
    public boolean eliminar (int id ){
        String sql = "DELETE FROM localidades WHERE id_localidad = ?";

        try (Connection conn = connFactory.getConecction();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }
    public  List<Localidad> listar(){
        List <Localidad> lista=  new ArrayList<>();
        
        String sql = "SELECT * FROM localidades"
                +" ORDER BY id_localidad";
        Connection conn = connFactory.getConecction();
        
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                Localidad localidad = new Localidad();
                localidad.setIdLocalidad(rs.getInt("id_localidad"));
                localidad.setNombre(rs.getString("nombre"));
                lista.add(localidad);
            }
        }catch (SQLException e){
            System.out.println("Error al listar localidades" + e.getMessage());
        }
        return lista;      
    }
    
}
