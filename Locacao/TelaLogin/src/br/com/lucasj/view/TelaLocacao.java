/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lucasj.view;

import br.com.lucasj.DAO.DaoAutomovel;
import br.com.lucasj.DAO.DaoCliente;
import br.com.lucasj.DAO.DaoLocacao;
import br.com.lucasj.interfaces.CRUDViewInterface;
import br.com.lucasj.model.Automovel;
import br.com.lucasj.model.Cliente;
import br.com.lucasj.model.Locacao;
import br.com.lucasj.services.Util;
import java.awt.Color;
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
public class TelaLocacao extends ViewModelo implements CRUDViewInterface {

    private JTextField edIdLocacao, edHoraInicio, edHoraFim, edKmInicio, edKmFim,
            edKmRodado, edTempoHoras, edVlHora, edVlKm, edValorTotal;
    private JComboBox comboCliente, comboAutomovel;
    private ArrayList<Object> meusCliente, meusAutomoveis;
    private JRadioButton radioAberto, radioFechado;
    private ButtonGroup radioGroup;
    private String gHoraInicio, gHoraFim;
    private Locacao fooModel;

    public TelaLocacao() {
        super(":Tela Locação");
        init();
    }

    private void setCamposObservacao() {
        edHoraFim.setEnabled(false);
        edHoraInicio.setEnabled(false);
        edIdLocacao.setEnabled(false);
        edKmInicio.setEnabled(false);
        edKmRodado.setEnabled(false);
        edTempoHoras.setEnabled(false);
        edVlHora.setEnabled(false);
        edVlKm.setEnabled(false);
        edValorTotal.setEnabled(false);
    }

    private void init() {
        gHoraFim = null;
        gHoraInicio = null;

        fooModel = new Locacao();
        fooModel.setIdLocacao(-1);
        Automovel auto = new Automovel();
        auto.setIdAutomovel(-1);
        fooModel.setAutomovel(auto);

        edIdLocacao = new JTextField();
        edHoraInicio = new JTextField();
        edHoraFim = new JTextField();
        edKmInicio = new JTextField();
        edKmFim = new JTextField();
        edKmFim.setBackground(Color.GREEN);
        edKmRodado = new JTextField();
        edTempoHoras = new JTextField();
        edVlHora = new JTextField();
        edVlKm = new JTextField();
        edValorTotal = new JTextField();

        setCamposObservacao();

        comboAutomovel = new JComboBox();
        comboAutomovel.setBackground(Color.GREEN);
        comboCliente = new JComboBox();
        comboCliente.setBackground(Color.GREEN);

        meusAutomoveis = new ArrayList<>();
        meusCliente = new ArrayList<>();

        radioAberto = new JRadioButton("Aberto");
        radioFechado = new JRadioButton("Fechado");

        radioGroup = new ButtonGroup();
        radioGroup.add(radioAberto);
        radioGroup.add(radioFechado);

        JPanel radioPanel = new JPanel(new FlowLayout());
        radioPanel.add(radioAberto);
        radioPanel.add(radioFechado);

        radioAberto.setSelected(true);

        this.panelMID.add(new JLabel("ID LOCAÇÃO:"));
        this.panelMID.add(edIdLocacao);
        this.panelMID.add(new JLabel("CLIENTE:"));
        this.panelMID.add(comboCliente);
        this.panelMID.add(new JLabel("AUTOMOVEL:"));
        this.panelMID.add(comboAutomovel);
        this.panelMID.add(new JLabel("TEMPO INICIO:"));
        this.panelMID.add(edHoraInicio);
        this.panelMID.add(new JLabel("TEMPO FIM:"));
        this.panelMID.add(edHoraFim);
        this.panelMID.add(new JLabel("TEMPO HORAS:"));
        this.panelMID.add(edTempoHoras);
        this.panelMID.add(new JLabel("KM INICIO:"));
        this.panelMID.add(edKmInicio);
        this.panelMID.add(new JLabel("KM FIM:"));
        this.panelMID.add(edKmFim);
        this.panelMID.add(new JLabel("KM RODADO:"));
        this.panelMID.add(edKmRodado);
        this.panelMID.add(new JLabel("VALOR HORA (R$):"));
        this.panelMID.add(edVlHora);
        this.panelMID.add(new JLabel("VALOR KM (R$):"));
        this.panelMID.add(edVlKm);
        this.panelMID.add(new JLabel("VALOR TOTAL (R$):"));
        this.panelMID.add(edValorTotal);
        this.panelMID.add(new JLabel("SITUAÇÃO:"));
        this.panelMID.add(radioPanel);

        atualizaComboBox();
        setTable(new String[]{"idLocacao", "Cliente", "Automovel", "Hora Inicio", "valorTotal", "Situação"});
        this.pack();
        this.setSize(new Dimension(800, 500));
        this.setVisible(true);
        setPanelComponentState(false);
    }

    @Override
    public void preencherTabelaPesquisar(ArrayList<Object> listaItensEncontrados) {
        this.tm.setRowCount(0);
        if (listaItensEncontrados != null) {
            for (int i = 0; i < listaItensEncontrados.size(); i++) {
                Locacao loc = (Locacao) listaItensEncontrados.get(i);

                this.tm.addRow(new Object[]{
                    loc.getIdLocacao(),
                    loc.getCliente().getNome(),
                    loc.getAutomovel().getPlaca(),
                    loc.getHoraInicio(),
                    loc.getValorTotal(),
                    loc.getSituacao()
                });
            }
        }
    }

    @Override
    public void preencherCampos(Object model) {
        if (model instanceof Locacao) {

            Locacao loc = (Locacao) model;
            fooModel = loc;
            edIdLocacao.setText(Integer.toString(loc.getIdLocacao()));
            gHoraFim = loc.getHoraFim();
            gHoraInicio = loc.getHoraInicio();
            if (loc.getHoraFim() != null) {
                edHoraFim.setText(loc.getHoraFim());
            }
            if (loc.getHoraInicio() != null) {
                edHoraInicio.setText(loc.getHoraInicio());
            }
            if (loc.getKmFim() != null) {
                edKmFim.setText(loc.getKmFim().toString());
            }
            if (loc.getKmInicio() != null) {
                edKmInicio.setText(loc.getKmInicio().toString());
            }
            if (loc.getKmRodado() != null) {
                edKmRodado.setText(loc.getKmRodado().toString());
            }
            if (loc.getTempoHoras() != null) {
                edTempoHoras.setText(loc.getTempoHoras().toString());
            }
            if (loc.getValorTotal() != null) {
                edValorTotal.setText(loc.getValorTotal().toString());
            }
            if (loc.getVlHora() != null) {
                edVlHora.setText(loc.getVlHora().toString());
            }
            if (loc.getVlKm() != null) {
                edVlKm.setText(loc.getVlKm().toString());
            }

            if (loc.getSituacao().toLowerCase().equals("aberto")) {
                radioAberto.setSelected(true);
            } else if (loc.getSituacao().toLowerCase().equals("fechado")) {
                radioFechado.setSelected(true);
                setPanelComponentState(false);
            }
            atualizaComboBox();
            int indexCombo = -1;
            for (int i = 0; i < meusAutomoveis.size(); i++) {
                Automovel meuAutomovelCasted = (Automovel) meusAutomoveis.get(i);
                if (loc.getAutomovel().getIdAutomovel() == meuAutomovelCasted.getIdAutomovel()) {
                    indexCombo = i;
                    break;
                }
            }
            comboAutomovel.setSelectedIndex(indexCombo);
            indexCombo = -1;
            for (int i = 0; i < meusCliente.size(); i++) {
                Cliente meuClienteCasted = (Cliente) meusCliente.get(i);
                if (loc.getCliente().getIdCliente() == meuClienteCasted.getIdCliente()) {
                    indexCombo = i;
                    break;
                }
            }
            comboCliente.setSelectedIndex(indexCombo);

        }
    }

    @Override
    public void limparCampos() {

        limpaFields();
        gHoraFim = null;
        gHoraInicio = null;
        fooModel.setIdLocacao(-1);
        fooModel.getAutomovel().setIdAutomovel(-1);

        radioAberto.setSelected(true);

        atualizaComboBox();
    }

    @Override
    public boolean doCRUD(String CRUDCommand) {
        if (CRUDCommand.equals("excluir") && edIdLocacao.getText().equals("")) {
            return false;
        } else if (CRUDCommand.equals("editar") && !edIdLocacao.getText().equals("")) {
            comboAutomovel.setEnabled(false);
            comboCliente.setEnabled(false);
            this.setCRUDButtons(CRUDCommand);
            return true;
        } else {
            this.setCRUDButtons(CRUDCommand);
            return true;
        }
    }

    @Override
    public Object getModel() {
        Locacao loc = new Locacao();
        if (edIdLocacao.getText().trim().equals("")) {
            loc.setIdLocacao(-1);
        } else {
            loc.setIdLocacao(Integer.parseInt(edIdLocacao.getText()));
        }

        String situacao = "fechado";
        if (radioAberto.isSelected()) {
            situacao = "aberto";
        }

        loc.setSituacao(situacao);

        String horaInicio = gHoraInicio;
        String horaFim = gHoraFim;
        Double kmInicio = null;
        Double kmFim = null;
        Double kmRodado = null;
        Double tempoHoras = null;
        Double vlHora = null;
        Double vlKm = null;
        Double valorTotal = null;

        if (Util.isNumeric(edKmInicio.getText())) {
            kmInicio = Double.parseDouble(edKmInicio.getText());
        }
        if (Util.isNumeric(edKmFim.getText())) {
            kmFim = Double.parseDouble(edKmFim.getText());
        }
        if (Util.isNumeric(edKmRodado.getText())) {
            kmRodado = Double.parseDouble(edKmRodado.getText());
        }
        if (Util.isNumeric(edTempoHoras.getText())) {
            tempoHoras = Double.parseDouble(edTempoHoras.getText());
        }
        if (Util.isNumeric(edVlHora.getText())) {
            vlHora = Double.parseDouble(edVlHora.getText());
        }
        if (Util.isNumeric(edVlKm.getText())) {
            vlKm = Double.parseDouble(edVlKm.getText());
        }
        if (Util.isNumeric(edValorTotal.getText())) {
            valorTotal = Double.parseDouble(edValorTotal.getText());
        }
        loc.setHoraFim(horaFim);
        loc.setHoraInicio(horaInicio);
        loc.setKmFim(kmFim);
        loc.setKmInicio(kmInicio);
        loc.setKmRodado(kmRodado);
        loc.setTempoHoras(tempoHoras);
        loc.setValorTotal(valorTotal);
        loc.setVlHora(vlHora);
        loc.setVlKm(vlKm);

        Cliente cliente = null;
        if (meusCliente != null && meusCliente.size() > 0) {
            cliente = (Cliente) meusCliente.get(comboCliente.getSelectedIndex());
            loc.setClinte(cliente);
        }
        Automovel auto = null;
        if (meusAutomoveis != null && meusAutomoveis.size() > 0) {
            auto = (Automovel) meusAutomoveis.get(comboAutomovel.getSelectedIndex());
            loc.setAutomovel(auto);
            System.out.println(auto.getPlaca());
            System.out.println(comboAutomovel.getSelectedIndex());
        }
        return loc;
    }

    @Override
    public Object getSelectedModel() {
        Locacao loc = null;
        if (this.tabela.getSelectedRow() != -1) {
            loc = new Locacao();
            loc.setIdLocacao((int) this.tm.getValueAt(tabela.getSelectedRow(), 0));
        }
        return loc;
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
        if (radioFechado.isSelected()) {
            setPanelCompEnable(false);
        } else {
            setPanelCompEnable(state);
            edIdLocacao.setEnabled(false);
            edHoraFim.setEnabled(false);
            edHoraInicio.setEnabled(false);
            edKmInicio.setEnabled(false);
            edKmRodado.setEnabled(false);
            edTempoHoras.setEnabled(false);
            edValorTotal.setEnabled(false);
            edVlHora.setEnabled(false);
            edVlKm.setEnabled(false);
        }

    }

    private void atualizaComboBox() {
        comboAutomovel.removeAllItems();
        comboCliente.removeAllItems();

        DaoAutomovel DA = new DaoAutomovel();
        ArrayList<Object> TempMeusAutomoveis = DA.getAll();

        DaoCliente DC = new DaoCliente();
        meusCliente = DC.getAll();

        if (TempMeusAutomoveis != null) {
            for (int i = 0; i < TempMeusAutomoveis.size(); i++) {
                Automovel auto = (Automovel) TempMeusAutomoveis.get(i);
                if (auto.getSituacao().equals("livre")) {
                    meusAutomoveis.add(auto);
                    comboAutomovel.addItem(auto.getPlaca());

                }
            }
            if (!edIdLocacao.getText().trim().equals("")) {
                DaoLocacao DL = new DaoLocacao();
                Locacao l = new Locacao();
                l.setIdLocacao(Integer.parseInt(edIdLocacao.getText()));
                l = (Locacao) DL.getByID(l);
                meusAutomoveis.add(l.getAutomovel());
                comboAutomovel.addItem(l.getAutomovel().getPlaca());
            }
        }

        if (meusCliente != null) {
            for (int i = 0; i < meusCliente.size(); i++) {
                Cliente cliente = (Cliente) meusCliente.get(i);
                comboCliente.addItem(cliente.getNome());
            }
        }

    }

}
