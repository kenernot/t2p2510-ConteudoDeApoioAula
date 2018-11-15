/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lucasj.view;

import br.com.lucasj.interfaces.CRUDViewInterface;
import br.com.lucasj.model.Cliente;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author lukas
 */
public class TelaCliente extends ViewModelo implements CRUDViewInterface {

    private JTextField edIdCliente, edNome;

    public TelaCliente() {
        super(":Tela Cliente");
        init();
    }

    private void init() {
        edIdCliente = new JTextField();
        edIdCliente.setEnabled(false);
        edNome = new JTextField();

        this.panelMID.add(new JLabel("ID CLIENTE:"));
        this.panelMID.add(edIdCliente);
        this.panelMID.add(new JLabel("NOME:"));
        this.panelMID.add(edNome);

        setTable(new String[]{"idCliente", "Nome"});
        this.pack();
        this.setSize(new Dimension(600, 400));
        this.setVisible(true);
        setPanelComponentState(false);
    }

    @Override
    public void preencherTabelaPesquisar(ArrayList<Object> listaItensEncontrados) {
        this.tm.setRowCount(0);
        if (listaItensEncontrados != null) {
            for (int i = 0; i < listaItensEncontrados.size(); i++) {
                Cliente cliente = (Cliente) listaItensEncontrados.get(i);
                this.tm.addRow(new Object[]{
                    cliente.getIdCliente(),
                    cliente.getNome()
                });
            }
        }
    }

    @Override
    public void preencherCampos(Object model) {
        if (model instanceof Cliente) {
            Cliente cliente = (Cliente) model;
            edIdCliente.setText(Integer.toString(cliente.getIdCliente()));
            edNome.setText(cliente.getNome());
        }
    }

    @Override
    public void limparCampos() {
        edIdCliente.setText("");
        edNome.setText("");
    }

    @Override
    public boolean doCRUD(String CRUDCommand) {
        if (CRUDCommand.equals("excluir") && edIdCliente.getText().equals("")) {
            return false;
        } else {
            this.setCRUDButtons(CRUDCommand);
            return true;
        }
    }

    @Override
    public Object getModel() {
        Cliente cliente = new Cliente();
        if (edIdCliente.getText().trim().equals("")) {
            cliente.setIdCliente(-1);
        } else {
            cliente.setIdCliente(Integer.parseInt(edIdCliente.getText()));
        }
        cliente.setNome(edNome.getText());
        return cliente;
    }

    @Override
    public Object getSelectedModel() {
        Cliente cliente = null;
        if (this.tabela.getSelectedRow() != -1) {
            cliente = new Cliente();
            cliente.setIdCliente((int) this.tm.getValueAt(tabela.getSelectedRow(), 0));
            cliente.setNome((String) this.tm.getValueAt(tabela.getSelectedRow(), 1));
        }
        return cliente;
    }

    @Override
    public void doMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    @Override
    public void setActionListener(ActionListener al) {
        setActionListenerPAI(al);
    }

    @Override
    public void setPanelComponentState(Boolean state) {
        setPanelCompEnable(state);
        edIdCliente.setEnabled(false);
    }

}
