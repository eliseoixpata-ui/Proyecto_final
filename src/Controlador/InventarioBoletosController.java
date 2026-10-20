package Controlador;

import dao.InventarioBoletosDao;
import modelo.InventarioBoleto;
import java.util.List;

public class InventarioBoletosController {
    
    private InventarioBoletosDao inventarioBoletosDao;
    
    public InventarioBoletosController(){
        inventarioBoletosDao= new InventarioBoletosDao();
    }
    
    public List<InventarioBoleto> listar(){
        return inventarioBoletosDao.listar();
    }
    public List<InventarioBoleto> listarPorConcierto(int idConcierto){
        return inventarioBoletosDao.buscarPorConcierto(idConcierto);
    }
    public boolean insertar(InventarioBoleto inventario){
        return inventarioBoletosDao.insertar(inventario);
    }
    public InventarioBoleto buscarPorId(int idInventario){
        return inventarioBoletosDao.buscarPorId(idInventario);
    }
    public boolean actualizar(InventarioBoleto inventario){
        return inventarioBoletosDao.actualizar(inventario);
    }
    public boolean eliminar (int idInventario) {
        return inventarioBoletosDao.eliminar(idInventario);
    }
}