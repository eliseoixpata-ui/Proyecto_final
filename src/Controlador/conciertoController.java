/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import dao.ConciertoDao;
import modelo.Concierto;


/**
 *
 * @author ixpat
 */
public class conciertoController {
     private ConciertoDao conciertoDao;

    public conciertoController() {
        conciertoDao = new ConciertoDao();
    }
    public boolean guardar(Concierto concierto) {
    return conciertoDao.guardar(concierto);
    }

    public Concierto consultar(int id) {
        return conciertoDao.consultar(id);
    }

    public boolean actualizar(Concierto concierto) {
        return conciertoDao.actualizar(concierto);
    }

    public boolean eliminar(int id) {
        return conciertoDao.eliminar(id);
    }
    
}
