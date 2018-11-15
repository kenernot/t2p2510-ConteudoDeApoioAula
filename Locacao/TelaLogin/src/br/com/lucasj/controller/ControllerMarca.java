/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lucasj.controller;

import br.com.lucasj.DAO.DaoMarca;
import br.com.lucasj.interfaces.ControllerInterface;
import br.com.lucasj.model.Marca;
import br.com.lucasj.view.TelaMarca;

/**
 *
 * @author lukas
 */
public class ControllerMarca implements ControllerInterface {

    private TelaMarca telaMarca;
    private DaoMarca daoMarca;

    public TelaMarca getTelaMarca() {
        return telaMarca;
    }

    public ControllerMarca() {
        daoMarca = new DaoMarca();
        telaMarca = new TelaMarca();
        ControllerCentral CM = new ControllerCentral(this, telaMarca, daoMarca);

    }

    @Override
    public String verificarCamposObrigatorios() {
        Marca marca = (Marca) telaMarca.getModel();
        if (!marca.getTitulo().trim().equals("")) {
            return null;
        }
        return "Campo título vazio!";
    }

}
