package br.com.lucasj.controller;

import br.com.lucasj.DAO.DaoUsuario;
import br.com.lucasj.model.Usuario;
import br.com.lucasj.services.Conexao;
import br.com.lucasj.view.TelaPrincipal;
import br.com.lucasj.view.TelaUsuario;
import br.com.lucasj.view.Telinha;
import com.sun.prism.j2d.J2DPipeline;
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
        if (e.getActionCommand().equals("pessoa")) {
            minhaView.getmDesktop().add( new Telinha());
           
        }
    }
    
}
