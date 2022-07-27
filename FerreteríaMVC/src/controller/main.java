/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.CRUDFerreteria;
import model.Ferreteria;
import view.tienda;

/**
 *
 * @author usuario1
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CRUDFerreteria modelCRUD= new CRUDFerreteria();
        Ferreteria ModelFerreteria= new Ferreteria();
        tienda viewTienda = new tienda();
        Ferreteria_Controller ControlFerreteria= new Ferreteria_Controller(viewTienda,ModelFerreteria,modelCRUD);
        ControlFerreteria.iniciar();
        viewTienda.setVisible(true);
    }   
    
}
