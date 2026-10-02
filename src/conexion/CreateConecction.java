/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class CreateConecction {
    static Properties config = new Properties();
    String hostname = null;
    String port = null;
    String database = null;
    String username = null;
    String password = null;

public CreateConecction(){
    String path ="C:\\Users\\ixpat\\Contacts\\Documents\\NetBeansProjects\\Proyecto_final\\src\\conexion\\db_config.properties";
    InputStream in = null;
    
        try{
            in = Files.newInputStream(Paths.get(path));
            config.load(in);
            loadProperties();
            
        } catch(IOException ex){
        System.out.println (ex.getMessage());
        } 
}
    public void loadProperties(){
        this.hostname= config.getProperty("hostname");
        this.port= config.getProperty("port");
        this.database= config.getProperty("database");
        this.username= config.getProperty("username");
        this.password= config.getProperty("password");
        
    }
    public Connection getConecction() {
    Connection conn = null;
        try {
            
            String jdbcUrl = "jdbc:postgresql://"+this.hostname+":"+this.port + "/" + this.database;
            
            conn = DriverManager.getConnection(jdbcUrl,username,password);
            System.out.println("Conexion estblecida");
            
            return conn;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
        
    
    }
}
