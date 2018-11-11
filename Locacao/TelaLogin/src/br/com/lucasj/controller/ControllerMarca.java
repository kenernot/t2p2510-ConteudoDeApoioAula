/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lucasj.controller;

import br.com.lucasj.DAO.DaoMarca;
import br.com.lucasj.interfaces.CRUDViewInterface;
import br.com.lucasj.interfaces.ControllerInterface;
import br.com.lucasj.interfaces.DAOInterface;
import br.com.lucasj.model.Marca;
import br.com.lucasj.view.TelaMarca;

/**
 *
 * @author lukas
 */
public class ControllerMarca extends ControllerModelo implements ControllerInterface {
    
    TelaMarca telaMarca;
    DaoMarca daoMarca;

    public TelaMarca getTelaMarca() {
        return telaMarca;
    }
    
    public ControllerMarca() {
        super();
        falseConstruct(this, myCrudView, myDAO);
        
        myDAO = new DaoMarca();
        telaMarca = new TelaMarca(this);
        
    }
    
    @Override
    public String verificarCamposObrigatorios() {
        Marca marca = new Marca();
        marca = (Marca) telaMarca.getModel();
        
        if (!marca.getTitulo().equals("")) {
            return null;
        }
        return "Campo título vazio!";
    }
    
}
