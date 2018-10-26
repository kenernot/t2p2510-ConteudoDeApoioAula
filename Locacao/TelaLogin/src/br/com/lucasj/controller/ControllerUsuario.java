package br.com.lucasj.controller;

import br.com.lucasj.DAO.DaoUsuario;
import br.com.lucasj.model.Usuario;
import br.com.lucasj.services.Conexao;
import br.com.lucasj.view.TelaUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ControllerUsuario implements ActionListener {

    private TelaUsuario minhaView;

    public ControllerUsuario() {
        minhaView = new TelaUsuario();
        this.minhaView.addControllerActionListener(this);
    }

    public void abrir() {
        minhaView.abrir();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("login.logar")) {
            if (minhaView != null) {
                this.Autenticar();
            }
        }
    }

    private void Autenticar() {
        Usuario user = minhaView.getUsuario();
        DaoUsuario du = new DaoUsuario();
        String isAuth = du.Autenticar(user);
        
        if (isAuth == null) {
            JOptionPane.showMessageDialog(null, "Erro! Usuário ou senha incorretos.");
        } else if (isAuth.equals("0") || isAuth.equals("00")) {
            JOptionPane.showMessageDialog(null, "Usuário inativo!");
        } else {
            JOptionPane.showMessageDialog(null, "LOGADO COM SUCESSO!");
            minhaView.terminate();
            ControllerPrincipal cp = new ControllerPrincipal(user);
        }
    }

}
