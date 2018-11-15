/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lucasj.controller;

import br.com.lucasj.DAO.DaoModelo;
import br.com.lucasj.interfaces.ControllerInterface;
import br.com.lucasj.model.Modelo;
import br.com.lucasj.view.TelaModelo;

/**
 *
 * @author lukas
 */
public class ControllerModelo implements ControllerInterface {

    private TelaModelo telaModelo;
    private DaoModelo daoModelo;

    public TelaModelo getTelaModelo() {
        return telaModelo;
    }

    public ControllerModelo() {
        daoModelo = new DaoModelo();
        telaModelo = new TelaModelo();
        ControllerCentral CM = new ControllerCentral(this, telaModelo, daoModelo);

    }

    @Override
    public String verificarCamposObrigatorios() {
        Modelo modelo = (Modelo) telaModelo.getModel();
        String msg = null;
        if (modelo.getTitulo().trim().equals("")) {
            msg = "Campo título vazio!";
        } else if (modelo.getMarca() == null) {
            msg = "Campo marca vazio!";
        }
        return msg;
        a// LEMBRAR QUE PODE SER QUE NÃO TENHAM MARCAS CADASTRADAS E PODE DAR PAU NO COMBOBOX/SALVAR MARCA VAZIA!
    }

}
