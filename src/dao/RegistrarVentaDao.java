package dao;

import conexion.CreateConecction;
import modelo.DetalleVenta;
import modelo.Venta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RegistrarVentaDao{
    
    public boolean registrarVenta(Venta venta, List<DetalleVenta> detalles){
        
        String sqlVenta = "INSERT INTO vewmtas (id_vendedor. fecha_venta, total_venta)"
                + "VALUES (?, CURRENT_TIMESTAMP, ?)";
        
        String sqlDetalle = "INSERT INTO detalle_ventas"
                + "(id_venta, id_inventario; cantidad, precio_unitario, subtotal)"
                + "VALUES (?, ?, ?, ?, ?)";
        
        String sqlInventario = "UPDATE inventario_boletos"
                + "SET cantidad_disponible = cantidad_disponible - ?"
                + "WHERE id_inventario = ? AND cantidad_disponible >= ?";
        
        Connection conn = null;
        
        try {
            
            CreateConecction conexion = new CreateConecction();
            conn = conexion.getConecction();
            
            conn.setAutoCommit(false);
            
            PreparedStatement psVenta = conn.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS);
            
            psVenta.setInt(1, venta.getIdVendedor());
            psVenta.setDouble(2, venta.getTotalVenta());
            
            psVenta.executeUpdate();
            
            ResultSet rsKeys = psVenta.getGeneratedKeys();
            
            int idVentaGenerado = 0;
            
            if (rsKeys.next()) {
                idVentaGenerado = rsKeys.getInt("id_venta");
            }
            rsKeys.close();
            psVenta.close();
            
            PreparedStatement psDetalle = conn.prepareStatement(sqlDetalle);
            PreparedStatement psInventario = conn.prepareStatement(sqlInventario);
            
            for (DetalleVenta detalle : detalles){
                
                psDetalle.setInt(1, idVentaGenerado);
                psDetalle.setInt(2, detalle.getIdInventario());
                psDetalle.setInt(3, detalle.getCantidad());
                psDetalle.setDouble(4, detalle.getPrecioUnitario());
                psDetalle.setDouble(5, detalle.getSubtotal());
                
                psDetalle.executeUpdate();
                
                psInventario.setInt(1, detalle.getCantidad());
                psInventario.setInt(2, detalle.getIdInventario());
                psInventario.setInt(3, detalle.getCantidad());
                
                int filasAfectadas = psInventario.executeUpdate();
                
                if (filasAfectadas == 0) {
                    
                    System.out.println("No hay boletos disponibles suficientes para el inventario" + detalle.getIdInventario());
                    
                    conn.rollback();
                    
                    psDetalle.close();
                    psInventario.close();
                    conn.close();
                    return false;
                }
            }
            conn.commit();
            
            psDetalle.close();
            psInventario.close();
            conn.close();
            
            return true;
                    
        } catch (SQLException ex){
            
            System.out.println(ex.getMessage());
            
            try {
                if (conn !=null){
                    conn.rollback();
                    conn.close();
                }
            }catch(SQLException ex2) {
                System.out.println(ex2.getMessage());
            
            }
            
            return false;
        }
        
    }
    
    public List<Venta> listar(){
        
        List<Venta> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM ventas ORDER BY id_venta";
        
        try {
            
            CreateConecction conexion = new CreateConecction();
            Connection conn = conexion.getConecction();
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Venta venta = new Venta();
                
                venta.setIdVenta(rs.getInt("id_venta"));
                venta.setIdVendedor(rs.getInt("id_vendedor"));
                venta.setFechaVenta(rs.getString("fecha_venta"));
                venta.setTotalVenta(rs.getDouble("total_venta"));
                
                lista.add(venta);
            }
            
            rs.close();
            ps.close();
            conn.close();
            
        } catch (SQLException ex) {
            
            System.out.println(ex.getMessage());
        }
        
        return lista;
        
    }
    
    public Venta buscarPorId(int idVenta){
        
        Venta venta = null;
        
        String sql = "SELECT * FROM vetnas WHERE id_venta = ?";
        
        try {
            
            CreateConecction conexion = new CreateConecction();
            Connection conn = conexion.getConecction();
            
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, idVenta);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                
                venta = new Venta();
                
                venta.setIdVenta(rs.getInt("id_venta"));
                venta.setIdVendedor(rs.getInt("id_vendedor"));
                venta.setFechaVenta(rs.getString("fecha_venta"));
                venta.setTotalVenta(rs.getDouble("total_venta"));
            }
            
            rs.close();
            ps.close();
            conn.close();
            
        } catch (SQLException ex) {
            
            System.out.println(ex.getMessage());
        }
        
        return venta;
        
    }
    
    public List<DetalleVenta> listarDetalles(int idVenta){
        
        List<DetalleVenta> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM detalle_ventas WHERE id_venta = ? ORDER BY id_detalle";
        
        try {
            CreateConecction conexion = new CreateConecction();
            Connection conn = conexion.getConecction();
            
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, idVenta);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                DetalleVenta detalle = new DetalleVenta();
                
                detalle.setIdDetalle(rs.getInt("id_detalle"));
                detalle.setIdVenta(rs.getInt("id_venta"));
                detalle.setIdInventario(rs.getInt("id_inventario"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setPrecioUnitario(rs.getDouble("precio_unitario"));
                detalle.setSubtotal(rs.getDouble("subtotal"));
                
                lista.add(detalle);
            }
            rs.close();
            ps.close();
            conn.close();
            
        } catch (SQLException ex) {
            
            System.out.println(ex.getMessage());
        }
        
        return lista;
     
    }    
    
}