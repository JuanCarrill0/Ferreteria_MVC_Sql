/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario1
 */
public class CRUDFerreteria extends Conexion{
    
    //Create --> Insert 
    
    public boolean agregarProducto(Ferreteria Producto) throws ClassNotFoundException{
        //Metodo para definir una consulta
        PreparedStatement ps= null;
        Connection con=getConexionSinConector();
        int id= Producto.getId();
        String nombre= Producto.getNombre();
        String categoria= Producto.getCategoria();
        double precio= Producto.getPrecio();
        int cantidad= Producto.getCantidad();
        System.out.println(id+nombre+categoria+precio+cantidad);
        String sql= "INSERT INTO producto (id,nombre,precio,categoria,cantidad)"
                + "VALUES(?,?,?,?,?)";                                                                                                                      ;
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, nombre);
            ps.setDouble(3, precio);
            ps.setString(4, categoria);
            ps.setInt(5, cantidad);
            ps.execute();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        
    }
    
    //Read --> Select
    public List listar(){
        List<Ferreteria> datos= new ArrayList<>();
        String sql= "select * from producto";
        try{
            Connection con=getConexionSinConector();
            PreparedStatement ps= con.prepareStatement(sql);
            //Metodo para capturar resultado de una consulta a la BD
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                /* 1. 1-Halo-50000-Shooters-50
                   2. 3-Batalla Naval-80000-Estrategia-60    
                */
                Ferreteria Producto= new Ferreteria();
                Producto.setId(rs.getInt(1));
                Producto.setNombre(rs.getString(2));
                Producto.setPrecio(rs.getDouble(3));
                Producto.setCategoria(rs.getString(4));
                Producto.setCantidad(rs.getInt(5));
                datos.add(Producto);
                //datos= [[1,Halo,50000,Shooters,50],]
            }
               
        }catch(Exception e){
      
        }
        return datos;
        
    }
    
    //Update
    
    public boolean actualizarProducto(Ferreteria Producto) throws ClassNotFoundException{
        //Metodo para definir una consulta
        PreparedStatement ps= null;
        Connection con=getConexionSinConector();
              
        String sql= "UPDATE producto SET id=?,nombre=?, precio=?, categoria=?, cantidad=? where id=?";                                                                                                                      ;
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1, Producto.getId());
            ps.setString(2, Producto.getNombre());
            ps.setDouble(3, Producto.getPrecio());
            ps.setString(4, Producto.getCategoria());
            ps.setInt(5, Producto.getCantidad());
            ps.setInt(6, Producto.getId());
            ps.execute();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        
    }
        //Delete
    
    public boolean borrarProducto(Ferreteria Producto) throws ClassNotFoundException{
        //Metodo para definir una consulta
        PreparedStatement ps= null;
        Connection con=getConexionSinConector();
              
        String sql= "DELETE FROM producto WHERE id=?";                                                                                                                      ;
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1, Producto.getId());
            ps.execute();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean buscar(Ferreteria Producto) throws SQLException{
        
        PreparedStatement ps= null;
        ResultSet rs= null;
        Connection con = getConexionConConector();
        
        String sql= "SELECT * FROM producto WHERE id=?";
        
        
        try{
             ps= con.prepareStatement(sql);
             ps.setInt(1, Producto.getId());
             rs= ps.executeQuery();
             
             if(rs.next()){
                 Producto.setId(Integer.parseInt(rs.getString("id")));
                 Producto.setNombre(rs.getString("nombre"));
                 Producto.setCategoria(rs.getString("categoria"));
                 Producto.setPrecio(Double.parseDouble(rs.getString("precio")));
                 Producto.setCantidad(Integer.parseInt(rs.getString("cantidad")));
                 
                 return true;
             }
             return false;
            
            }catch(SQLException e){
               e.printStackTrace();
               return false; 
            } finally{
               try{
                   con.close();
               }catch(SQLException e){
                   e.printStackTrace();
               }
            }
        
        
    }
    
    
    
}
