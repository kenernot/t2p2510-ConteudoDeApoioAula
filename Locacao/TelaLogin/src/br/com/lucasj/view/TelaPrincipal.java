package br.com.lucasj.view;

import br.com.lucasj.controller.ControllerPrincipal;
import br.com.lucasj.model.Usuario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class TelaPrincipal extends JFrame {

    private JMenuBar menuBar;

    public JDesktopPane getmDesktop() {
        return mDesktop;
    }
    private JPanel mPainel;
    private JDesktopPane mDesktop;
    private JMenu menuCadastros;
    private JMenuItem cadastroPessoa, cadastroEmpresa, cadastroMarca, cadastroCliente, cadastroModelo, cadastroAutomovel, cadastroLocacao;
    private ActionListener al;

    public TelaPrincipal(ActionListener al) throws HeadlessException {
        super("Tela Principal");
        this.al = al;
        init();
    }

    public JPanel getmPainel() {
        return mPainel;
    }

    private void init() {
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        menuBar = new JMenuBar();
        mPainel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        mDesktop = new JDesktopPane();
        menuCadastros = new JMenu("Cadastros");
        cadastroPessoa = new JMenuItem("Pessoa");
        cadastroMarca = new JMenuItem("Marca");
        cadastroMarca.addActionListener(al);
        cadastroMarca.setActionCommand("marca");
        cadastroCliente = new JMenuItem("Cliente");
        cadastroCliente.addActionListener(al);
        cadastroCliente.setActionCommand("cliente");
        cadastroModelo = new JMenuItem("Modelo");
        cadastroModelo.addActionListener(al);
        cadastroModelo.setActionCommand("modelo");
        cadastroAutomovel = new JMenuItem("Automovel");
        cadastroAutomovel.addActionListener(al);
        cadastroAutomovel.setActionCommand("automovel");
        cadastroLocacao = new JMenuItem("Locação");
        cadastroLocacao.addActionListener(al);
        cadastroLocacao.setActionCommand("locacao");
        cadastroPessoa.setActionCommand("pessoa");
        cadastroPessoa.addActionListener(al);
        cadastroEmpresa = new JMenuItem("Empresa");
        this.mDesktop.setBackground(Color.DARK_GRAY);

        this.getContentPane().add(menuBar, BorderLayout.NORTH);
        menuBar.add(menuCadastros);
        menuCadastros.add(cadastroPessoa);
        menuCadastros.add(cadastroEmpresa);
        menuCadastros.add(cadastroMarca);
        menuCadastros.add(cadastroModelo);
        menuCadastros.add(cadastroCliente);
        menuCadastros.add(cadastroAutomovel);
        menuCadastros.add(cadastroLocacao);
        this.getContentPane().add(mDesktop, BorderLayout.CENTER);
        this.getContentPane().add(mPainel, BorderLayout.SOUTH);

    }

    public void abrir() {
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);
    }

    public void addControllerActionListener(ControllerPrincipal aThis) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
