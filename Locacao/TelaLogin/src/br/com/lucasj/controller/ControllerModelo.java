/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lucasj.controller;

import br.com.lucasj.interfaces.CRUDViewInterface;
import br.com.lucasj.interfaces.ControllerInterface;
import br.com.lucasj.interfaces.DAOInterface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author lukas
 */
public class ControllerModelo implements ActionListener {

    ControllerInterface myController;
    CRUDViewInterface myCrudView;
    DAOInterface myDAO;

    public ControllerModelo() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().toLowerCase().equals("inserir")) {
            this.myCrudView.doCRUD("inserir");
            this.myCrudView.limparCampos();
        } else if (e.getActionCommand().toLowerCase().equals("editar")) {
            this.myCrudView.doCRUD("editar");
        } else if (e.getActionCommand().toLowerCase().equals("salvar")) {
            String msg = this.myController.verificarCamposObrigatorios();
            if (msg == null) {
                this.myCrudView.doCRUD("salvar");
                Object myObject = this.myCrudView.getModel();
                myDAO.salvar(myObject);
                this.myCrudView.preencherCampos(myDAO.getLast());;
            } else {
                myCrudView.doMsg(msg);
            }

        } else if (e.getActionCommand().toLowerCase().equals("excluir")) {
            if (this.myCrudView.doCRUD("excluir")) {
                myDAO.remover(myCrudView.getModel());
                this.myCrudView.limparCampos();
            }
        } else if (e.getActionCommand().toLowerCase().equals("cancelar")) {
            this.myCrudView.doCRUD("cancelar");
            myCrudView.limparCampos();
        } else if (e.getActionCommand().toLowerCase().equals("pesquisar")) {
            this.myCrudView.preencherTabelaPesquisar(myDAO.getAll());
        } else if (e.getActionCommand().toLowerCase().equals("abrir")) {
            this.myCrudView.limparCampos();
            this.myCrudView.preencherCampos(myDAO.getByID(myCrudView.getSelectedModel()));
        }
    }

    protected void falseConstruct(ControllerInterface myController, CRUDViewInterface myCrudView, DAOInterface myDAO) {
        this.myController = myController;
        this.myCrudView = myCrudView;
        this.myDAO = myDAO;
    }
}
