/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lucasj.view;

import br.com.lucasj.DAO.DaoMarca;
import br.com.lucasj.interfaces.CRUDViewInterface;
import br.com.lucasj.model.Marca;
import br.com.lucasj.model.Modelo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author lukas
 */
public class TelaModelo extends ViewModelo implements CRUDViewInterface {

    private JTextField edIdModelo, edTitulo;
    private JComboBox comboMarca;
    private ArrayList<Object> minhasMarcas;

    public TelaModelo() {
        super(":Tela Modelo");
        init();
    }

    private void init() {
        comboMarca = new JComboBox();
        comboMarca.setBackground(Color.GREEN);
        atualizaComboBox();
        edIdModelo = new JTextField();
        edIdModelo.setEnabled(false);
        edTitulo = new JTextField();
        edTitulo.setBackground(Color.GREEN);

        this.panelMID.add(new JLabel("ID MODELO:"));
        this.panelMID.add(edIdModelo);
        this.panelMID.add(new JLabel("TITULO:"));
        this.panelMID.add(edTitulo);
        this.panelMID.add(new JLabel("MARCA:"));
        this.panelMID.add(comboMarca);

        setTable(new String[]{"idMarca", "Titulo", "Marca"});
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
                Modelo modelo = (Modelo) listaItensEncontrados.get(i);
                this.tm.addRow(new Object[]{
                    modelo.getIdModelo(),
                    modelo.getTitulo(),
                    modelo.getMarca().getTitulo()
                });
            }
        }
    }

    @Override
    public void preencherCampos(Object model) {
        if (model instanceof Modelo) {
            atualizaComboBox();
            Modelo modelo = (Modelo) model;
            edIdModelo.setText(Integer.toString(modelo.getIdModelo()));
            edTitulo.setText(modelo.getTitulo());
            int indexMarca = -1;
            for (int i = 0; i < minhasMarcas.size(); i++) {
                Marca minhaMarcaCasted = (Marca) minhasMarcas.get(i);
                if (modelo.getMarca().getIdMarca() == minhaMarcaCasted.getIdMarca()) {
                    indexMarca = i;
                    break;
                }
            }
            comboMarca.setSelectedIndex(indexMarca);
        }
    }

    @Override
    public void limparCampos() {
        limpaFields();
        atualizaComboBox();
    }

    @Override
    public boolean doCRUD(String CRUDCommand) {
        if (CRUDCommand.equals("excluir") && edIdModelo.getText().equals("")) {
            return false;
        } else {
            this.setCRUDButtons(CRUDCommand);
            return true;
        }
    }

    @Override
    public Object getModel() {
        Modelo modelo = new Modelo();
        if (edIdModelo.getText().trim().equals("")) {
            modelo.setIdModelo(-1);
        } else {
            modelo.setIdModelo(Integer.parseInt(edIdModelo.getText()));
        }
        modelo.setTitulo(edTitulo.getText());
        Marca marca = null;
        if (minhasMarcas != null && minhasMarcas.size() > 0) {
            marca = (Marca) minhasMarcas.get(comboMarca.getSelectedIndex());
            modelo.setMarca(marca);
        }
        return modelo;
    }

    @Override
    public Object getSelectedModel() {
        Modelo modelo = null;
        if (this.tabela.getSelectedRow() != -1) {
            modelo = new Modelo();
            modelo.setIdModelo((int) this.tm.getValueAt(tabela.getSelectedRow(), 0));
            modelo.setTitulo((String) this.tm.getValueAt(tabela.getSelectedRow(), 1));

        }
        return modelo;
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
        edIdModelo.setEnabled(false);
    }

    private void atualizaComboBox() {
        comboMarca.removeAllItems();
        DaoMarca DM = new DaoMarca();
        minhasMarcas = DM.getAll();
        if (minhasMarcas != null && minhasMarcas.size() > 0) {
            for (int i = 0; i < minhasMarcas.size(); i++) {
                Marca marca = (Marca) minhasMarcas.get(i);
                comboMarca.addItem(marca.getTitulo());
            }
        }
    }

}
