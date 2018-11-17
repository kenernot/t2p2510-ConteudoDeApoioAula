/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lucasj.view;

import br.com.lucasj.DAO.DaoMarca;
import br.com.lucasj.DAO.DaoModelo;
import br.com.lucasj.interfaces.CRUDViewInterface;
import br.com.lucasj.model.Automovel;
import br.com.lucasj.model.Marca;
import br.com.lucasj.model.Modelo;
import br.com.lucasj.services.Util;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author lukas
 */
public class TelaAutomovel extends ViewModelo implements CRUDViewInterface {

    private JTextField edIdAutomovel, edPlaca, edCor, edAno, edTipoCombustivel,
            edKmAtual, edRenavam, edChasis, edVlLocacaoHora, edVlLocacaoKm;
    private JComboBox comboModelo;
    private ArrayList<Object> meusModelos;
    private JRadioButton radioInativo, radioLivre, radioLocado;
    private ButtonGroup radioGroup;

    public TelaAutomovel() {
        super(":Tela Automovel");
        init();
    }

    private void init() {
        edIdAutomovel = new JTextField();
        edPlaca = new JTextField();
        edCor = new JTextField();
        edAno = new JTextField();
        edTipoCombustivel = new JTextField();
        edKmAtual = new JTextField();
        edRenavam = new JTextField();
        edChasis = new JTextField();
        edVlLocacaoHora = new JTextField();
        edVlLocacaoKm = new JTextField();

        edIdAutomovel.setEnabled(false);

        comboModelo = new JComboBox();
        meusModelos = null;
        radioInativo = new JRadioButton("Inativo");
        radioLivre = new JRadioButton("Livre");
        radioLocado = new JRadioButton("Locado");

        radioGroup = new ButtonGroup();
        radioGroup.add(radioInativo);
        radioGroup.add(radioLivre);
        radioGroup.add(radioLocado);

        JPanel radioPanel = new JPanel(new FlowLayout());
        radioPanel.add(radioInativo);
        radioPanel.add(radioLivre);
        radioPanel.add(radioLocado);

        radioInativo.setSelected(true);

        this.panelMID.add(new JLabel("ID AUTOMOVEL:"));
        this.panelMID.add(edIdAutomovel);
        this.panelMID.add(new JLabel("MODELO:"));
        this.panelMID.add(comboModelo);
        this.panelMID.add(new JLabel("PLACA:"));
        this.panelMID.add(edPlaca);
        this.panelMID.add(new JLabel("RENAVAM:"));
        this.panelMID.add(edRenavam);
        this.panelMID.add(new JLabel("CHASIS:"));
        this.panelMID.add(edChasis);
        this.panelMID.add(new JLabel("COR:"));
        this.panelMID.add(edCor);
        this.panelMID.add(new JLabel("ANO:"));
        this.panelMID.add(edAno);
        this.panelMID.add(new JLabel("TIPO COMBUSTIVEL:"));
        this.panelMID.add(edTipoCombustivel);
        this.panelMID.add(new JLabel("KM ATUAL:"));
        this.panelMID.add(edKmAtual);
        this.panelMID.add(new JLabel("VL LOCAÇÃO HORA:"));
        this.panelMID.add(edVlLocacaoHora);
        this.panelMID.add(new JLabel("VL LOCAÇÃO KM:"));
        this.panelMID.add(edVlLocacaoKm);
        this.panelMID.add(new JLabel("SITUAÇÃO:"));

        this.panelMID.add(radioPanel);
        atualizaComboBox();
        setTable(new String[]{"idAutomovel", "Modelo", "Placa", "KmAtual", "Situação"});
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
                Automovel auto = (Automovel) listaItensEncontrados.get(i);
                this.tm.addRow(new Object[]{
                    auto.getIdAutomovel(),
                    auto.getModelo().getTitulo(),
                    auto.getPlaca(),
                    auto.getKmAtual(),
                    auto.getSituacao()
                });
            }
        }
    }

    @Override
    public void preencherCampos(Object model) {
        if (model instanceof Automovel) {
            atualizaComboBox();
            Automovel auto = (Automovel) model;
            edIdAutomovel.setText(Integer.toString(auto.getIdAutomovel()));
            edAno.setText(auto.getAno());
            edChasis.setText(auto.getChasis());
            edCor.setText(auto.getCor());
            edKmAtual.setText(auto.getKmAtual().toString());
            edPlaca.setText(auto.getPlaca());
            edRenavam.setText(auto.getRenavam());
            edTipoCombustivel.setText(auto.getTipoCombustivel());
            edVlLocacaoHora.setText(auto.getVlLocacaoHora().toString());
            edVlLocacaoKm.setText(auto.getVlLocacaoKm().toString());

            if (auto.getSituacao().toLowerCase().equals("inativo")) {
                radioInativo.setSelected(true);
            } else if (auto.getSituacao().toLowerCase().equals("livre")) {
                radioLivre.setSelected(true);
            } else {
                radioLocado.setSelected(true);
                radioLivre.setEnabled(false);
                radioInativo.setEnabled(false);
                radioLocado.setEnabled(false);
            }

            int indexModelo = -1;
            for (int i = 0; i < meusModelos.size(); i++) {
                Modelo meuModeloCasted = (Modelo) meusModelos.get(i);
                if (auto.getModelo().getIdModelo() == meuModeloCasted.getIdModelo()) {
                    indexModelo = i;
                    break;
                }
            }
            comboModelo.setSelectedIndex(indexModelo);
        }
    }

    @Override
    public void limparCampos() {
        JTextField[] arrayField = {edIdAutomovel, edPlaca, edCor, edAno,
            edTipoCombustivel, edKmAtual, edRenavam, edChasis, edVlLocacaoHora, edVlLocacaoKm};

        for (int i = 0; i < arrayField.length; i++) {
            arrayField[i].setText("");
        }

        radioInativo.setSelected(true);

        atualizaComboBox();
    }

    @Override
    public boolean doCRUD(String CRUDCommand) {
        if (CRUDCommand.equals("excluir") && edIdAutomovel.getText().equals("")) {
            return false;
        } else {
            this.setCRUDButtons(CRUDCommand);
            return true;
        }
    }

    @Override
    public Object getModel() {
        Automovel auto = new Automovel();
        if (edIdAutomovel.getText().trim().equals("")) {
            auto.setIdAutomovel(-1);
        } else {
            auto.setIdAutomovel(Integer.parseInt(edIdAutomovel.getText()));
        }

        auto.setAno(edAno.getText());
        auto.setChasis(edChasis.getText());
        auto.setCor(edCor.getText());
        auto.setPlaca(edPlaca.getText().toUpperCase());
        auto.setRenavam(edRenavam.getText());
        auto.setTipoCombustivel(edTipoCombustivel.getText());

        String situacao = "Locado";
        if (radioInativo.isSelected()) {
            situacao = "Inativo";
        } else if (radioLivre.isSelected()) {
            situacao = "Livre";
        }

        auto.setSituacao(situacao);

        Double kmAtual = null;
        Double vlLocacaoHora = null;
        Double vlLocacaoKm = null;

        if (Util.isNumeric(edKmAtual.getText())) {
            kmAtual = Double.parseDouble(edKmAtual.getText());
        }
        if (Util.isNumeric(edVlLocacaoHora.getText())) {
            vlLocacaoHora = Double.parseDouble(edVlLocacaoHora.getText());
        }
        if (Util.isNumeric(edVlLocacaoKm.getText())) {
            vlLocacaoKm = Double.parseDouble(edVlLocacaoKm.getText());
        }
        auto.setKmAtual(kmAtual);
        auto.setVlLocacaoHora(vlLocacaoHora);
        auto.setVlLocacaoKm(vlLocacaoKm);

        Modelo modelo = null;
        if (meusModelos != null) {
            modelo = (Modelo) meusModelos.get(comboModelo.getSelectedIndex());
            auto.setModelo(modelo);
        }
        return auto;
    }

    @Override
    public Object getSelectedModel() {
        Automovel auto = null;
        if (this.tabela.getSelectedRow() != -1) {
            auto = new Automovel();
            auto.setIdAutomovel((int) this.tm.getValueAt(tabela.getSelectedRow(), 0));
        }
        return auto;
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
        edIdAutomovel.setEnabled(false);
        radioLocado.setEnabled(false);
        if (radioLocado.isSelected()) {
            radioLivre.setEnabled(false);
            radioInativo.setEnabled(false);
        }
    }

    private void atualizaComboBox() {
        comboModelo.removeAllItems();
        DaoModelo DM = new DaoModelo();
        meusModelos = DM.getAll();
        if (meusModelos != null) {
            for (int i = 0; i < meusModelos.size(); i++) {
                Modelo modelo = (Modelo) meusModelos.get(i);
                comboModelo.addItem(modelo.getTitulo());
            }
        }
    }

}
