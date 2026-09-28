/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import java.sql.SQLException;

/**
 *
 * @author User
 */
public class Test {
    public static void main (String [] args) throws SQLException{
        CreateConecction conexion = new CreateConecction();
        conexion.getConecction();
        
    }
    
}
