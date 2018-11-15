/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lucasj.controller;

import br.com.lucasj.DAO.DaoCliente;
import br.com.lucasj.interfaces.ControllerInterface;
import br.com.lucasj.model.Cliente;
import br.com.lucasj.view.TelaCliente;

/**
 *
 * @author lukas
 */
public class ControllerCliente implements ControllerInterface {

    private TelaCliente telaCliente;
    private DaoCliente daoCliente;

    public TelaCliente getTelaCliente() {
        return telaCliente;
    }

    public ControllerCliente() {
        daoCliente = new DaoCliente();
        telaCliente = new TelaCliente();
        ControllerCentral CM = new ControllerCentral(this, telaCliente, daoCliente);

    }

    @Override
    public String verificarCamposObrigatorios() {
        Cliente cliente = (Cliente) telaCliente.getModel();
        if (!cliente.getNome().trim().equals("")) {
            return null;
        }
        return "Campo nome vazio!";
    }

}
