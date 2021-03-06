package br.com.lucasj.controller;

import br.com.lucasj.model.Usuario;
import br.com.lucasj.view.TelaPrincipal;
import br.com.lucasj.view.Telinha;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JLabel;

public class ControllerPrincipal implements ActionListener {

    private TelaPrincipal minhaView;
    private Usuario user;

    public ControllerPrincipal(Usuario user) {
        this.user = user;
        minhaView = new TelaPrincipal(this);
        minhaView.abrir();
        this.minhaView.addControllerActionListener(this);
        this.minhaView.getmPainel().add(new JLabel("Usuário: " + this.user.getLogin()));
        this.minhaView.getmPainel().add(new JLabel(new Date().toString()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if (e.getActionCommand().equals("pessoa")) {
            minhaView.getmDesktop().add(new Telinha());
        } else if (e.getActionCommand().equals("marca")) {
            ControllerMarca controllerMarca = new ControllerMarca();
            minhaView.getmDesktop().add(controllerMarca.getTelaMarca());
        } else if (e.getActionCommand().equals("cliente")) {
            ControllerCliente controllerCliente = new ControllerCliente();
            minhaView.getmDesktop().add(controllerCliente.getTelaCliente());
        } else if (e.getActionCommand().equals("modelo")) {
            ControllerModelo controllerModelo = new ControllerModelo();
            minhaView.getmDesktop().add(controllerModelo.getTelaModelo());
        } else if (e.getActionCommand().equals("automovel")) {
            ControllerAutomovel controllerAutomovel = new ControllerAutomovel();
            minhaView.getmDesktop().add(controllerAutomovel.getTelaAutomovel());
        } else if (e.getActionCommand().equals("locacao")) {
            ControllerLocacao controllerLocacao = new ControllerLocacao();
            minhaView.getmDesktop().add(controllerLocacao.getTelaLocacao());
        }
    }
}
