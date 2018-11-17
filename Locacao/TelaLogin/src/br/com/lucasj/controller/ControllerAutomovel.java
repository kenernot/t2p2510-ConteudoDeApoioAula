/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lucasj.controller;

import br.com.lucasj.DAO.DaoAutomovel;
import br.com.lucasj.DAO.DaoModelo;
import br.com.lucasj.interfaces.ControllerInterface;
import br.com.lucasj.model.Automovel;
import br.com.lucasj.model.Modelo;
import br.com.lucasj.view.TelaAutomovel;
import br.com.lucasj.view.TelaModelo;

/**
 *
 * @author lukas
 */
public class ControllerAutomovel implements ControllerInterface {

    private TelaAutomovel telaAutomovel;
    private DaoAutomovel daoAutomovel;

    public TelaAutomovel getTelaAutomovel() {
        return telaAutomovel;
    }

    public ControllerAutomovel() {
        daoAutomovel = new DaoAutomovel();
        telaAutomovel = new TelaAutomovel();
        ControllerCentral CM = new ControllerCentral(this, telaAutomovel, daoAutomovel);

    }

    @Override
    public String verificarCamposObrigatorios() {
        Automovel auto = (Automovel) telaAutomovel.getModel();
        String msg = null;
        if (auto.getKmAtual() == null) {
            msg = "Campo km atual inválido!";
        } else if (auto.getModelo()== null) {
            msg = "Campo modelo inválido!";
        } else if (auto.getPlaca().trim().equals("")) {
            msg = "Campo placa vazio!";
        } 
        return msg;

    }

}
