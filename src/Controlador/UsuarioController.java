/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import dao.UsuarioDao;
import modelo.usuarios;
import java.util.List;

public class UsuarioController {

    private UsuarioDao usuarioDao;

    public UsuarioController() {
        usuarioDao = new UsuarioDao();
    }

    public List<usuarios> listar() {
        return usuarioDao.listar();
    }

    public boolean guardar(usuarios usuario) {
        return usuarioDao.insertar(usuario);
    }

    public usuarios consultar(int id) {
        return usuarioDao.buscarPorId(id);
    }

    public boolean actualizar(usuarios usuario) {
        return usuarioDao.actualizar(usuario);
    }

    public boolean eliminar(int id) {
        return usuarioDao.eliminar(id);
    }
}