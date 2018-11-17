/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lucasj.controller;

import br.com.lucasj.DAO.DaoAutomovel;
import br.com.lucasj.DAO.DaoLocacao;
import br.com.lucasj.DAO.DaoModelo;
import br.com.lucasj.interfaces.ControllerInterface;
import br.com.lucasj.model.Automovel;
import br.com.lucasj.model.Locacao;
import br.com.lucasj.model.Modelo;
import br.com.lucasj.view.TelaAutomovel;
import br.com.lucasj.view.TelaLocacao;
import br.com.lucasj.view.TelaModelo;
import java.util.ArrayList;

/**
 *
 * @author lukas
 */
public class ControllerLocacao implements ControllerInterface {

    private TelaLocacao telaLocacao;
    private DaoLocacao daoLocacao;

    public TelaLocacao getTelaLocacao() {
        return telaLocacao;
    }

    public ControllerLocacao() {
        daoLocacao = new DaoLocacao();
        telaLocacao = new TelaLocacao();
        ControllerCentral CM = new ControllerCentral(this, telaLocacao, daoLocacao);

    }

    @Override
    public String verificarCamposObrigatorios() {
        Locacao loc = (Locacao) telaLocacao.getModel();

        DaoAutomovel DA = new DaoAutomovel();
        Automovel auto = (Automovel) DA.getByID(loc.getAutomovel());

        String msg = null;
        if (loc.getAutomovel() == null) {
            msg = "Campo automóvel inválido!";
        } else if (loc.getCliente() == null) {
            msg = "Campo cliente inválido!";
        } else if (loc.getSituacao().equals("fechado") && (loc.getKmFim() == null || loc.getKmFim() == 0.00 || loc.getKmFim() < loc.getKmInicio())) {
            msg = "Campo KM FIM inválido!";
        } else if (!auto.getSituacao().equals("livre")) {
            DaoLocacao DL = new DaoLocacao();
            ArrayList<Object> lista = DL.getAll();
            msg = "Automovel não mais disponível!";
            for (int i = 0; i < lista.size(); i++) {
                Locacao loki = (Locacao) lista.get(i);
                if (loki.getAutomovel().getIdAutomovel() == loc.getAutomovel().getIdAutomovel()) {
                    msg = null;
                    break;
                }
            }

        }
        return msg;

    }

}
