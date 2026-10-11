/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import dao.ArtistaDao;
import modelo.Artistas;
import java.util.List;

public class ArtistaController {

    private ArtistaDao artistaDao;

    public ArtistaController() {
        artistaDao = new ArtistaDao();
    }

    public List<Artistas> listar() {
        return artistaDao.listar();
    }

    public boolean guardar(Artistas artista) {
        return artistaDao.insertar(artista);
    }

    public Artistas consultar(int id) {
        return artistaDao.buscarPorId(id);
    }

    public boolean actualizar(Artistas artista) {
        return artistaDao.actualizar(artista);
    }

    public boolean eliminar(int id) {
        return artistaDao.eliminar(id);
    }
}