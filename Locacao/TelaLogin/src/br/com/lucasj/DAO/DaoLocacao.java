/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lucasj.DAO;

import br.com.lucasj.model.Automovel;
import br.com.lucasj.model.Locacao;
import br.com.lucasj.model.Modelo;
import br.com.lucasj.services.Conexao;
import com.mysql.jdbc.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author lukas
 */
public class DaoLocacao {
    
    private Connection conn;
    
    public DaoLocacao() {
        this.conn = (Connection) Conexao.getInstance().getConn();
    }
    
    public void salvar(Locacao locacao) {
        Locacao loc = locacao;
        String sql = "update locacao set idcliente = ?, idautomovel = ?, "
                + "horainicio = ?, horafim = ?, kminicio = ?, kmfim = ?, kmrodado = ?, "
                + "tempohoras = ?, vlhora = ?,vlkm = ?,valortotal = ?,situacao = ? where idlocacao = ?;";
        if (loc.getIdLocacao() == -1) {
            sql = "insert into locacao(idcliente,idautomovel,horainicio,horafim,"
                    + "kminicio,kmfim,kmrodado,tempohoras,vlhora,vlkm,valortotal,"
                    + "situacao,idlocacao) "
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?);";
        }
        
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, loc.getCliente().getIdCliente());
            ps.setInt(2, loc.getAutomovel().getIdAutomovel());
            ps.setDate(3, (Date) loc.getHoraInicio());
            ps.setDate(4, (Date) loc.getHoraFim());
            ps.setDouble(5, loc.getKmInicio());
            ps.setDouble(6, loc.getKmFim());
            ps.setDouble(7, loc.getKmRodado());
            ps.setDouble(8, loc.getTempoHoras());
            ps.setDouble(9, loc.getVlHora());
            ps.setDouble(10, loc.getVlKm());
            ps.setDouble(11, loc.getValorTotal());
            ps.setString(12, loc.getSituacao());
            ps.setInt(13, loc.getIdLocacao());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public ArrayList<Locacao> getAll() {
        ArrayList<Locacao> minhaLista = new ArrayList<>();
        String sql = "select * from locacao;";
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Locacao loc = new Locacao();
                loc.setAutomovel(new DaoAutomovel().getByID(rs.getInt("idautomovel")));
                loc.setClinte(new DaoCliente().getByID(rs.getInt("idcliente")));
                loc.setHoraFim(rs.getDate("horafim"));
                loc.setHoraInicio(rs.getDate("horainicio"));
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
            } else {
                return null;
            }
            return minhaLista;
            
        } catch (SQLException ex) {
            System.out.println("#01");
        }
        return null;
    }
    
    public Locacao getByID(int id) {
        String sql = "select * from locacao where idlocacao = ?;";
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Locacao loc = new Locacao();
                loc.setAutomovel(new DaoAutomovel().getByID(rs.getInt("idautomovel")));
                loc.setClinte(new DaoCliente().getByID(rs.getInt("idcliente")));
                loc.setHoraFim(rs.getDate("horafim"));
                loc.setHoraInicio(rs.getDate("horainicio"));
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
        return null;
    }
    
    public void remover(Locacao loc) {
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
