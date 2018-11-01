/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lucasj.DAO;

import br.com.lucasj.model.Marca;
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
public class DaoModelo {

    private Connection conn;

    public DaoModelo() {
        this.conn = (Connection) Conexao.getInstance().getConn();
    }

    public void salvar(Modelo modelo) {
        Modelo mod = modelo;
        String sql = "update modelo set titulo = ?, idmarca where idmodelo = ? ;";
        if (mod.getIdModelo()== -1) {
            sql = "insert into modelo(titulo, idmarca, idmodelo) values(?,?,?);";
        }

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, mod.getTitulo());
            ps.setInt(2, mod.getMarca().getIdMarca());
            ps.setInt(3, mod.getIdModelo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Modelo> getAll() {
        ArrayList<Modelo> minhaLista = new ArrayList<>();
        String sql = "select * from modelo;";
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Modelo modelo = new Modelo();
                modelo.setIdModelo(rs.getInt("idmodelo"));
                modelo.setTitulo(rs.getString("titulo"));
                DaoMarca dm = new DaoMarca();
                modelo.setMarca(dm.getByID(rs.getInt("idmarca")));
                minhaLista.add(modelo);
            } else {
                return null;
            }
            return minhaLista;

        } catch (SQLException ex) {
            System.out.println("#01");
        }
        return null;
    }

    public Modelo getByID(int id) {
        String sql = "select * from modelo where idemodelo = ?;";
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Modelo modelo = new Modelo();
                modelo.setIdModelo(rs.getInt("idmodelo"));
                modelo.setTitulo(rs.getString("titulo"));
                DaoMarca dm = new DaoMarca();
                modelo.setMarca(dm.getByID(rs.getInt("idmarca")));
                return modelo;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("#01");
        }
        return null;
    }

    public void remover(Modelo modelo) {
        String sql = "delete from modelo where idmodelo = ?;";
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            
            ps.setInt(1, modelo.getIdModelo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}