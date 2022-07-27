/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author usuario1
 */
public class Conexion {
     public static Connection getConexionConConector(){
        //Conectar
        
        Connection con=null;
        try{ 
        con= DriverManager.getConnection(Properties.URL,Properties.USERNAME,Properties.PASSWORD); 
        if(con!=null){
            System.out.println("*** Connected to database***");
        }
       }
       catch(SQLException ex){
           ex.printStackTrace();
       }
       return con;
    }
    
    public static Connection getConexionSinConector() throws ClassNotFoundException{
        //Conectar
        String controlador = "com.mysql.cj.jdbc.Driver";
        Connection con=null;
        try{ 
        Class.forName(controlador);    
        con= DriverManager.getConnection(Properties.URL,Properties.USERNAME,Properties.PASSWORD); 
        if(con!=null){
            System.out.println("*** Connected to database***");
        }
       }
       catch(SQLException ex){
           ex.printStackTrace();
       }
       return con;
    }
}
