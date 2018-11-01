/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lucasj.DAO;

import br.com.lucasj.model.Automovel;
import br.com.lucasj.model.Modelo;
import br.com.lucasj.services.Conexao;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author lukas
 */
public class DaoAutomovel {

    private Connection conn;

    public DaoAutomovel() {
        this.conn = (Connection) Conexao.getInstance().getConn();
    }

    public void salvar(Automovel automovel) {
        Automovel auto = automovel;
        String sql = "update automovel set idModelo = ?, placa = ?, cor = ?, "
                + " ano = ?, tipoCombustivel = ?, kmAtual = ?, renavam = ?, "
                + "chasis = ?, vlLocacaoHora = ?, vlLocacaoKm = ?, situacao = ? where idAutomovel = ? ;";
        if (auto.getIdAutomovel() == -1) {
            sql = "insert into automovel(idModelo, placa, cor, ano,"
                    + "tipoCombustivel, kmAtual, renavam, chasis,"
                    + " vlLocacaoHora, vlLocacaoKm, situacao, idAutomovel) values(?,?,?,?,?,?,?,?,?,?,?,?);";
        }

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, auto.getModelo().getIdModelo());
            ps.setString(2, auto.getPlaca());
            ps.setString(3, auto.getCor());
            ps.setString(4, auto.getAno());
            ps.setString(5, auto.getTipoCombustivel());
            ps.setDouble(6, auto.getKmAtual());
            ps.setString(7, auto.getRenavam());
            ps.setString(8, auto.getChasis());
            ps.setDouble(9, auto.getVlLocacaoHora());
            ps.setDouble(10, auto.getVlLocacaoKm());
            ps.setString(11, auto.getSituacao());
            ps.setInt(12, auto.getIdAutomovel());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Automovel> getAll() {
        ArrayList<Automovel> minhaLista = new ArrayList<>();
        String sql = "select * from automovel;";
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Automovel auto = new Automovel();
                auto.setAno(rs.getString("ano"));
                auto.setChasis(rs.getString("chasis"));
                auto.setCor(rs.getString("cor"));
                auto.setKmAtual(rs.getDouble("kmatual"));
                auto.setPlaca(rs.getString("placa"));
                auto.setRenavam(rs.getString("renavam"));
                auto.setSituacao(rs.getString("situacao"));
                auto.setTipoCombustivel(rs.getString("tipocombustivel"));
                auto.setVlLocacaoHora(rs.getDouble("vllocacaohora"));
                auto.setVlLocacaoKm(rs.getDouble("vllocacaokm"));
                auto.setIdAutomovel(rs.getInt("idautomovel"));
                
                DaoModelo modelo = new DaoModelo();
                auto.setModelo(modelo.getByID(rs.getInt("idmodelo")));
                
                minhaLista.add(auto);
            } else {
                return null;
            }
            return minhaLista;

        } catch (SQLException ex) {
            System.out.println("#01");
        }
        return null;
    }

    public Automovel getByID(int id) {
        String sql = "select * from automovel where idautomovel = ?;";
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Automovel auto = new Automovel();
                auto.setAno(rs.getString("ano"));
                auto.setChasis(rs.getString("chasis"));
                auto.setCor(rs.getString("cor"));
                auto.setKmAtual(rs.getDouble("kmatual"));
                auto.setPlaca(rs.getString("placa"));
                auto.setRenavam(rs.getString("renavam"));
                auto.setSituacao(rs.getString("situacao"));
                auto.setTipoCombustivel(rs.getString("tipocombustivel"));
                auto.setVlLocacaoHora(rs.getDouble("vllocacaohora"));
                auto.setVlLocacaoKm(rs.getDouble("vllocacaokm"));
                auto.setIdAutomovel(rs.getInt("idautomovel"));
                
                DaoModelo modelo = new DaoModelo();
                auto.setModelo(modelo.getByID(rs.getInt("idmodelo")));
                return auto;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("#01");
        }
        return null;
    }

    public void remover(Automovel auto) {
        String sql = "delete from automovel where idautomovel = ?;";
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);

            ps.setInt(1, auto.getIdAutomovel());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
