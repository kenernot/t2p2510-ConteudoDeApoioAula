/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lucasj.view;

import br.com.lucasj.interfaces.CRUDViewInterface;
import br.com.lucasj.model.Marca;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author lukas
 */
public class TelaMarca extends ViewModelo implements CRUDViewInterface {

    private JTextField edIdMarca, edTitulo;

    public TelaMarca() {
        super(":Tela Marca");
        init();
    }

    private void init() {
        edIdMarca = new JTextField();
        edIdMarca.setEnabled(false);
        edTitulo = new JTextField();

        this.panelMID.add(new JLabel("ID MARCA:"));
        this.panelMID.add(edIdMarca);
        this.panelMID.add(new JLabel("TITULO:"));
        this.panelMID.add(edTitulo);

        setTable(new String[]{"idMarca", "Titulo"});
        this.pack();
        this.setSize(new Dimension(600, 400));
        this.setVisible(true);
        setPanelComponentState(false);
    }

    @Override
    public void preencherTabelaPesquisar(ArrayList<Object> listaItensEncontrados) {
        System.out.println(listaItensEncontrados.size());
        for (int i = 0; i < listaItensEncontrados.size(); i++) {
            Marca marca = (Marca) listaItensEncontrados.get(i);
            this.tm.addRow(new Object[]{
                marca.getIdMarca(),
                marca.getTitulo()
            });
        }
    }

    @Override
    public void preencherCampos(Object model) {
        if (model instanceof Marca) {
            Marca marca = (Marca) model;
            edIdMarca.setText(Integer.toString(marca.getIdMarca()));
            edTitulo.setText(marca.getTitulo());
        }
    }

    @Override
    public void limparCampos() {
        edIdMarca.setText("");
        edTitulo.setText("");
    }

    @Override
    public boolean doCRUD(String CRUDCommand) {
        if (CRUDCommand.equals("excluir") && edIdMarca.getText().equals("")) {
            return false;
        } else {
            this.setCRUDButtons(CRUDCommand);
            return true;
        }
    }

    @Override
    public Object getModel() {
        Marca marca = new Marca();
        if (edIdMarca.getText().trim().equals("")) {
            marca.setIdMarca(-1);
        } else {
            marca.setIdMarca(Integer.parseInt(edIdMarca.getText()));
        }
        marca.setTitulo(edTitulo.getText());
        return marca;
    }

    @Override
    public Object getSelectedModel() {
        Marca marca = null;
        if (this.tabela.getSelectedRow() != -1) {
            marca = new Marca();
            marca.setIdMarca((int) this.tm.getValueAt(tabela.getSelectedRow(), 0));
            marca.setTitulo((String) this.tm.getValueAt(tabela.getSelectedRow(), 1));
        }
        return marca;
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
        edIdMarca.setEnabled(false);
    }

}
