/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.CreateConecction;
import modelo.usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    public boolean insertar(usuarios usuario) {

        String sql = "INSERT INTO usuarios "
                + "(nombre_usuario, contrasena_hash, nombre_completo, rol) "
                + "VALUES (?, ?, ?, ?)";

        try {

            CreateConecction conexion = new CreateConecction();
            Connection conn = conexion.getConecction();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getContrasenaHash());
            ps.setString(3, usuario.getNombreCompleto());
            ps.setString(4, usuario.getRol());

            ps.executeUpdate();

            ps.close();
            conn.close();

            return true;

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

            return false;
        }
    }

    public List<usuarios> listar() {

        List<usuarios> lista = new ArrayList<>();

        String sql = "SELECT * FROM usuarios ORDER BY id_usuario";

        try {

            CreateConecction conexion = new CreateConecction();
            Connection conn = conexion.getConecction();

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                usuarios usuario = new usuarios();

                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                usuario.setContrasenaHash(rs.getString("contrasena_hash"));
                usuario.setNombreCompleto(rs.getString("nombre_completo"));
                usuario.setRol(rs.getString("rol"));
                usuario.setFechaCreacion(rs.getString("fecha_creacion"));

                lista.add(usuario);
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }

        return lista;
    }

    public boolean actualizar(usuarios usuario) {

        String sql = "UPDATE usuarios SET "
                + "nombre_usuario = ?, "
                + "contrasena_hash = ?, "
                + "nombre_completo = ?, "
                + "rol = ? "
                + "WHERE id_usuario = ?";

        try {

            CreateConecction conexion = new CreateConecction();
            Connection conn = conexion.getConecction();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getContrasenaHash());
            ps.setString(3, usuario.getNombreCompleto());
            ps.setString(4, usuario.getRol());
            ps.setInt(5, usuario.getIdUsuario());

            ps.executeUpdate();

            ps.close();
            conn.close();

            return true;

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

            return false;
        }
    }

    public boolean eliminar(int idUsuario) {

        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";

        try {

            CreateConecction conexion = new CreateConecction();
            Connection conn = conexion.getConecction();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, idUsuario);

            ps.executeUpdate();

            ps.close();
            conn.close();

            return true;

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

            return false;
        }
    }

    public usuarios buscarPorId(int idUsuario) {

        usuarios usuario = null;

        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";

        try {

            CreateConecction conexion = new CreateConecction();
            Connection conn = conexion.getConecction();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, idUsuario);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                usuario = new usuarios();

                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                usuario.setContrasenaHash(rs.getString("contrasena_hash"));
                usuario.setNombreCompleto(rs.getString("nombre_completo"));
                usuario.setRol(rs.getString("rol"));
                usuario.setFechaCreacion(rs.getString("fecha_creacion"));
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }

        return usuario;
    }
}