/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import dao.LocalidadesDao;
import modelo.Localidad;
import java.util.List;

/**
 *
 * @author ixpat
 */
public class LocalidadController {
    
    private LocalidadesDao localidadDao;
    
    public LocalidadController (){
        localidadDao = new LocalidadesDao();
    }
    public boolean guardar(Localidad localidad){
        return localidadDao.guardar(localidad);
    }
    public boolean actualizar(Localidad localidad){
        return localidadDao.actualizar(localidad);
    }
    public boolean eliminar(int id){
        return localidadDao.eliminar(id);
    }
    public List<Localidad> listar(){
        return localidadDao.listar();
    }
}
