/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lucasj.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

/**
 *
 * @author lukas
 */
public class ViewModelo extends JInternalFrame {

    protected JPanel panelTOP, panelMID, panelBOT, panelINTERNO;
    protected JButton btInserir, btEditar, btSalvar, btRemover, btExit;
    protected ActionListener al;

    public ViewModelo(String titulo, ActionListener al) {
        super(titulo);
        this.al = al;
        init();
    }

    private void init() {
        this.setLayout(new BorderLayout());
        this.panelTOP = new JPanel();
        this.panelMID = new JPanel();
        this.panelBOT = new JPanel();
        this.panelINTERNO = new JPanel();
        this.add(panelTOP,BorderLayout.NORTH);
        this.add(panelMID,BorderLayout.CENTER);
        this.add(panelBOT,BorderLayout.SOUTH);
        this.panelTOP.setLayout(new FlowLayout());
        
        btInserir = new JButton("Inserir");
        btInserir.addActionListener(al);
        
        btEditar = new JButton("Editar");
        btEditar.addActionListener(al);
        
        btSalvar = new JButton("Salvar");
        btSalvar.addActionListener(al);
        
        btRemover = new JButton("Excluir");
        btRemover.addActionListener(al);
        
        btExit = new JButton("Sair");
        btExit.addActionListener(al);
        
        panelTOP.add(btInserir);
        panelTOP.add(btEditar);
        panelTOP.add(btSalvar);
        panelTOP.add(btRemover);
        panelTOP.add(panelINTERNO);
        panelTOP.setLayout(new BorderLayout());
        panelINTERNO.add(btExit, BorderLayout.EAST);
        
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

}
