/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.CRUDFerreteria;
import model.Ferreteria;
import view.tienda;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author usuario1
 */
public class Ferreteria_Controller implements ActionListener{
    //Atributos
    private tienda viewTienda;
    private Ferreteria modelFerreteria;
    private CRUDFerreteria modelCRUD;
    DefaultTableModel modelo= new DefaultTableModel(); 
    
    //constructor
    public Ferreteria_Controller(tienda viewTienda,Ferreteria modelVideojuego,CRUDFerreteria modelCRUD){
        this.modelFerreteria= modelVideojuego;
        this.modelCRUD= modelCRUD;
        this.viewTienda= viewTienda;
        this.viewTienda.buttonGuardar3.addActionListener(this);
        this.viewTienda.buttonEliminar.addActionListener(this);
        this.viewTienda.buttonModificar.addActionListener(this);
        this.viewTienda.buttonListar.addActionListener(this);
        this.viewTienda.buttonLimpiar.addActionListener(this);
        this.viewTienda.buttonBuscar.addActionListener(this);
        /*this.viewTienda.jComboBoxCategoria.addItem("Estrategia");
        this.viewTienda.jComboBoxCategoria.addItem("Shooters");
        this.viewTienda.jComboBoxCategoria.addItem("Deportes");
        this.viewTienda.jComboBoxCategoria.addItem("RPG");*/
    }
    
    //Metodos
    
    public void iniciar(){
        viewTienda.setTitle("Ferreteria R5");
        viewTienda.setLocationRelativeTo(null);
        //viewTienda.buttonBuscar.setVisible(false);
    }
    
    @Override 
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==viewTienda.buttonBuscar){
            modelFerreteria.setId(Integer.parseInt(viewTienda.jTextField3.getText()));
            try {
                if(modelCRUD.buscar(modelFerreteria)){
                    viewTienda.jTextFieldID.setText(String.valueOf(modelFerreteria.getId()));
                    viewTienda.jTextFieldNombre.setText(String.valueOf(modelFerreteria.getNombre()));
                    viewTienda.jComboBoxCategoria.setSelectedItem(String.valueOf(modelFerreteria.getCategoria()));
                    viewTienda.jTextFieldPrecio.setText(String.valueOf(modelFerreteria.getPrecio()));
                    viewTienda.jTextFieldCantidad.setText(String.valueOf(modelFerreteria.getCantidad()));
                    
                }else{
                    JOptionPane.showMessageDialog(null, "no se encontro registro");
                    limpiar();
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(Ferreteria_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }else if(e.getSource()==viewTienda.buttonEliminar){
            modelFerreteria.setId(Integer.parseInt(viewTienda.jTextFieldID.getText()));
            
            try{
                if(modelCRUD.borrarProducto(modelFerreteria)){
                    JOptionPane.showMessageDialog(null,"Se Elimino el producto");
                }else{
                   JOptionPane.showMessageDialog(null, "Error al eliminar"); 
                }
            
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Ferreteria_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(e.getSource()==viewTienda.buttonModificar){
            modelFerreteria.setId(Integer.parseInt(viewTienda.jTextFieldID.getText()));
            modelFerreteria.setNombre(viewTienda.jTextFieldNombre.getText());
            modelFerreteria.setCategoria((String)viewTienda.jComboBoxCategoria.getSelectedItem());
            modelFerreteria.setPrecio(Double.parseDouble(viewTienda.jTextFieldPrecio.getText()));
            modelFerreteria.setCantidad(Integer.parseInt(viewTienda.jTextFieldCantidad.getText()));
            
            try{
               if(modelCRUD.actualizarProducto(modelFerreteria)){
                    JOptionPane.showMessageDialog(null,"Se Actualizo el producto");
                }else{
                   JOptionPane.showMessageDialog(null, "Error al actualizar"); 
                }
               } catch (ClassNotFoundException ex) {
                Logger.getLogger(Ferreteria_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
             
        }else if(e.getSource()==viewTienda.buttonGuardar3){
            modelFerreteria.setId(Integer.parseInt(viewTienda.jTextFieldID.getText()));
            modelFerreteria.setNombre(viewTienda.jTextFieldNombre.getText());
            modelFerreteria.setCategoria((String)viewTienda.jComboBoxCategoria.getSelectedItem());
            modelFerreteria.setPrecio(Double.parseDouble(viewTienda.jTextFieldPrecio.getText()));
            modelFerreteria.setCantidad(Integer.parseInt(viewTienda.jTextFieldCantidad.getText()));
            
            try{
                if(modelCRUD.agregarProducto(modelFerreteria)){
                   JOptionPane.showMessageDialog(null,"Se guardo el producto");
                }else{
                   JOptionPane.showMessageDialog(null, "Error al guardar, puede que el id ya esté ocupado"); 
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Ferreteria_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }else if(e.getSource()==viewTienda.buttonListar){  
            listarTabla(viewTienda.jTable1);
        }else if(e.getSource()==viewTienda.buttonLimpiar){
            limpiar();
        }
    }
    
    public void listarTabla(JTable tabla){
        modelo= (DefaultTableModel)tabla.getModel();
        List<Ferreteria> lista= modelCRUD.listar();
        Object[] objeto= new Object[5];
        if (modelo.getRowCount()==0) {
            for(int i=0; i<lista.size();i++){
            objeto[0]= lista.get(i).getId();
            objeto[1]= lista.get(i).getNombre();
            objeto[2]= lista.get(i).getCategoria();
            objeto[3]= lista.get(i).getPrecio();
            objeto[4]= lista.get(i).getCantidad();   
            
            modelo.addRow(objeto);
        }
        viewTienda.jTable1.setModel(modelo);
            
        }
    }
    
    public void limpiar(){
    viewTienda.jTextFieldID.setText(null);
    viewTienda.jTextFieldNombre.setText(null);
    viewTienda.jTextFieldPrecio.setText(null);
    viewTienda.jTextFieldCantidad.setText(null);
    
    DefaultTableModel tb= (DefaultTableModel)viewTienda.jTable1.getModel();
    int a= viewTienda.jTable1.getRowCount()-1;
    for(int i=a; i>=0; i--){
        tb.removeRow(tb.getRowCount()-1);
      }
        viewTienda.buttonListar.setVisible(true);
    }   
}
