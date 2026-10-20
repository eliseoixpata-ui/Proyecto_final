package Controlador;

import dao.RegistrarVentaDao;
import modelo.DetalleVenta;
import modelo.Venta;
import java.util.List;

public class RegistrarVentaController {
    
    private RegistrarVentaDao registrarVentaDao;
    
    public RegistrarVentaController (){
        registrarVentaDao = new RegistrarVentaDao();
        
    }
    
    public boolean registrarVenta(Venta venta, List<DetalleVenta> detalles){
        return registrarVentaDao.registrarVenta(venta, detalles);
        
    }
    public List<Venta> listar(){
        return registrarVentaDao.listar();
    }
    public Venta buscarPorId(int idVenta){
        return registrarVentaDao.buscarPorId(idVenta);
                
    }
    public List<DetalleVenta> listarDetalles(int idVenta){
        return registrarVentaDao.listarDetalles(idVenta);
    }
       
}