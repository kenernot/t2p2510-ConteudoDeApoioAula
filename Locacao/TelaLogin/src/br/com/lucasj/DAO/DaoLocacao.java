/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lucasj.DAO;

import br.com.lucasj.interfaces.DAOInterface;
import br.com.lucasj.model.Automovel;
import br.com.lucasj.model.Cliente;
import br.com.lucasj.model.Locacao;
import br.com.lucasj.services.Conexao;
import com.mysql.jdbc.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lukas
 */
public class DaoLocacao implements DAOInterface {

    private Connection conn;

    public DaoLocacao() {
        this.conn = (Connection) Conexao.getInstance().getConn();
    }

    public void salvar(Object model) {

        if (model instanceof Locacao) {

            Locacao loc = (Locacao) model;
            String sql = "update locacao set idcliente = ?, idautomovel = ?, "
                    + "kmfim = ?, situacao = ? where idlocacao = ?;";
            if (loc.getIdLocacao() == -1) {
                loc.setIdLocacao(0);
                sql = "insert into locacao(idcliente,idautomovel,"
                        + "kmfim,situacao,idlocacao) "
                        + "values(?,?,?,?,?);";
            }

            try {
                PreparedStatement ps = this.conn.prepareStatement(sql);
                ps.setInt(1, loc.getCliente().getIdCliente());
                ps.setInt(2, loc.getAutomovel().getIdAutomovel());
                ps.setObject(3, loc.getKmFim());
                ps.setString(4, loc.getSituacao());
                ps.setInt(5, loc.getIdLocacao());

                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public ArrayList<Object> getAll() {
        ArrayList<Object> minhaLista = new ArrayList<>();
        String sql = "select * from locacao;";
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Locacao loc = new Locacao();

                DaoAutomovel daoAutomovel = new DaoAutomovel();
                Automovel auto = new Automovel();
                auto.setIdAutomovel(rs.getInt("idautomovel"));

                DaoCliente daoCliente = new DaoCliente();
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idcliente"));

                loc.setAutomovel((Automovel) daoAutomovel.getByID(auto));
                loc.setClinte((Cliente) daoCliente.getByID(cliente));

                loc.setHoraFim(rs.getString("horafim"));
                loc.setHoraInicio(rs.getString("horainicio"));

                loc.setIdLocacao(rs.getInt("idlocacao"));
                loc.setKmFim(rs.getDouble("kmfim"));
                loc.setKmInicio(rs.getDouble("kminicio"));
                loc.setKmRodado(rs.getDouble("kmrodado"));
                loc.setSituacao(rs.getString("situacao"));
                loc.setTempoHoras(rs.getDouble("tempohoras"));
                loc.setValorTotal(rs.getDouble("valortotal"));
                loc.setVlHora(rs.getDouble("vlhora"));
                loc.setVlKm(rs.getDouble("vlkm"));

                minhaLista.add(loc);
            }
            if (minhaLista.size() > 0) {
                return minhaLista;
            }

        } catch (SQLException ex) {
            System.out.println("#01");
        }
        return null;
    }

    public Object getByID(Object model) {
        if (model instanceof Locacao && model != null) {
            String sql = "select * from locacao where idlocacao = ?;";
            try {
                PreparedStatement ps = this.conn.prepareStatement(sql);
                Locacao loc = (Locacao) model;
                ps.setInt(1, loc.getIdLocacao());
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    DaoAutomovel daoAutomovel = new DaoAutomovel();
                    Automovel auto = new Automovel();
                    auto.setIdAutomovel(rs.getInt("idautomovel"));

                    DaoCliente daoCliente = new DaoCliente();
                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(rs.getInt("idcliente"));

                    loc.setAutomovel((Automovel) daoAutomovel.getByID(auto));
                    loc.setClinte((Cliente) daoCliente.getByID(cliente));

                    loc.setHoraFim(rs.getString("horafim"));
                    loc.setHoraInicio(rs.getString("horainicio"));

                    loc.setIdLocacao(rs.getInt("idlocacao"));
                    loc.setKmFim(rs.getDouble("kmfim"));
                    loc.setKmInicio(rs.getDouble("kminicio"));
                    loc.setKmRodado(rs.getDouble("kmrodado"));
                    loc.setSituacao(rs.getString("situacao"));
                    loc.setTempoHoras(rs.getDouble("tempohoras"));
                    loc.setValorTotal(rs.getDouble("valortotal"));
                    loc.setVlHora(rs.getDouble("vlhora"));
                    loc.setVlKm(rs.getDouble("vlkm"));

                    return loc;
                } else {
                    return null;
                }

            } catch (SQLException ex) {
                System.out.println("#01");
            }
        }
        return null;
    }

    public void remover(Object model) {
        if (model instanceof Locacao) {
            Locacao loc = (Locacao) model;
            String sql = "delete from locacao where idlocacao = ?;";
            try {
                PreparedStatement ps = this.conn.prepareStatement(sql);

                ps.setInt(1, loc.getIdLocacao());
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public Object getLast() {
        String sql = "select * from locacao where idlocacao = (select max(idlocacao) from locacao);";
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Locacao loc = new Locacao();

                DaoAutomovel daoAutomovel = new DaoAutomovel();
                Automovel auto = new Automovel();
                auto.setIdAutomovel(rs.getInt("idautomovel"));

                DaoCliente daoCliente = new DaoCliente();
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idcliente"));

                loc.setAutomovel((Automovel) daoAutomovel.getByID(auto));
                loc.setClinte((Cliente) daoCliente.getByID(cliente));

                loc.setHoraFim(rs.getString("horafim"));
                loc.setHoraInicio(rs.getString("horainicio"));
                
                loc.setIdLocacao(rs.getInt("idlocacao"));
                loc.setKmFim((Double) rs.getObject("kmfim"));
                loc.setKmInicio(rs.getDouble("kminicio"));
                loc.setKmRodado(rs.getDouble("kmrodado"));
                loc.setSituacao(rs.getString("situacao"));
                loc.setTempoHoras(rs.getDouble("tempohoras"));
                loc.setValorTotal(rs.getDouble("valortotal"));
                loc.setVlHora(rs.getDouble("vlhora"));
                loc.setVlKm(rs.getDouble("vlkm"));

                return loc;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("#01");
        }
        return null;
    }

}
