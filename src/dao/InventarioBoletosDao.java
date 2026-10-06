package dao;

import conexion.CreateConecction;
import modelo.InventarioBoleto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventarioBoletosDao{
    
    public boolean insertar(InventarioBoleto inventario){
        
        String sql = "INSERT INTO inventario_boletos"
                + "(id_concierto, id_localidad, precio, cantidad_total, cantidad_disponible)"
                + "VALUES (?, ?, ?, ?, ?)";
        
        try{
            
           CreateConecction conexion = new CreateConecction();
           Connection conn = conexion.getConecction();
           
           PreparedStatement ps = conn.prepareStatement(sql);
           
           ps.setInt(1, inventario.getIdConcierto());
           ps.setInt(2, inventario.getIdLocalidad());
           ps.setDouble(3, inventario.getPrecio());
           ps.setInt(4, inventario.getCantidadTotal());
           ps.setInt(5, inventario.getCantidadDisponible());
           
           ps.executeUpdate();
           
           ps.close();
           conn.close();
           
           return true;
           
       } catch (SQLException ex){
           
           System.out.println(ex.getMessage());
           return false;
       }
                
    }
    
    public List<InventarioBoleto> listar(){
        
        List<InventarioBoleto> lista = new ArrayList<>();
        
        String sql= "SELECT * FROM INVENTARIO_BOLETOS ORDER BY id_inventario";
        
        try {
            
            CreateConecction conexion = new CreateConecction();
            Connection conn = conexion.getConecction();
            
            PreparedStatement ps = conn.prepareStatement (sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                InventarioBoleto inventario = new InventarioBoleto();
                
                inventario.setIdInventario(rs.getInt("id_inventario"));
                inventario.setIdConcierto(rs.getInt("id_concierto"));
                inventario.setIdLocalidad(rs.getInt("id_localidad"));
                inventario.setPrecio(rs.getInt("precio"));
                inventario.setCantidadTotal(rs.getInt("cantidad_total"));
                inventario.setCantidadDisponible(rs.getInt("cantidad_disponible"));
                
                lista.add(inventario);
                
            }
            rs.close();
            ps.close();
            conn.close();
            
        } catch(SQLException ex){
            
            System.out.println(ex.getMessage());
            
        }
        return lista;
    }
    public boolean actualizar (InventarioBoleto inventario){
        
        String sql ="UPDATE inventario_boletos SET"
                + "id_concierto = ?, "
                + "id_localidad = ?, "
                + "precio = ?, "
                + "cantidad_total = ?, "
                + "cantidad_disponible = ?"
                + "WHERE id_inventario = ?";
                
        try{
            
           CreateConecction conexion = new CreateConecction();
           Connection conn = conexion.getConecction();
           
           PreparedStatement ps = conn.prepareStatement(sql);
           
           ps.setInt(1, inventario.getIdConcierto());
           ps.setInt(2, inventario.getIdLocalidad());
           ps.setDouble(3, inventario.getPrecio());
           ps.setInt(4, inventario.getCantidadTotal());
           ps.setInt(5, inventario.getCantidadDisponible());
           ps.setInt(6, inventario.getIdInventario());
           
           ps.executeUpdate();
           
           ps.close();
           conn.close();
           
           return true;
           
       } catch (SQLException ex){
           
           System.out.println(ex.getMessage());
           return false;
       }       
    }
    public InventarioBoleto buscarPorId(int idInventario){
        
        InventarioBoleto inventario = null;
        
        String sql = "SELECT * FROM inventario_boletos WHERE ID_inventario =?";
        
        try {
            
            CreateConecction conexion = new CreateConecction();
            Connection conn = conexion.getConecction();
            
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, idInventario);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                
                inventario = new InventarioBoleto();
                
                inventario.setIdInventario(rs.getInt("id_inventario"));
                inventario.setIdConcierto(rs.getInt("id_concierto"));
                inventario.setIdLocalidad(rs.getInt("id_localidad"));
                inventario.setPrecio(rs.getDouble("precio"));
                inventario.setCantidadTotal(rs.getInt("cantidad_total"));
                inventario.setCantidadDisponible(rs.getInt("cantidad_disponible"));
            }
            
            rs.close();
            ps.close();
            conn.close();
            
        } catch (SQLException ex){
            
            System.out.println(ex.getMessage());
            
        }
        
        return inventario;
    }
    
    public List<InventarioBoleto> buscarPorConcierto (int idConcierto){
        
        List<InventarioBoleto> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM inventario_boletos WHERE id_concierto = ? ORDER BY id_inventario";
        
        try {
            CreateConecction conexion = new CreateConecction();
            Connection conn = conexion.getConecction();
            
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, idConcierto);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                
                InventarioBoleto inventario = new InventarioBoleto();
                
                inventario.setIdInventario(rs.getInt("id_inventario"));
                inventario.setIdConcierto(rs.getInt("id_concierto"));
                inventario.setIdLocalidad(rs.getInt("id_localidad"));
                inventario.setPrecio(rs.getDouble("precio"));
                inventario.setCantidadTotal(rs.getInt("cantidad_total"));
                
                lista.add(inventario);
            }
            rs.close();
            ps.close();
            conn.close();
            
        } catch (SQLException ex){
            
            System.out.println(ex.getMessage());
        }
        return lista;
    }
}
