/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lucasj.DAO;

import br.com.lucasj.interfaces.DAOInterface;
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
public class DaoModelo implements DAOInterface {

    private Connection conn;

    public DaoModelo() {
        this.conn = (Connection) Conexao.getInstance().getConn();
    }

    public void salvar(Object model) {
        if (model instanceof Modelo) {
            Modelo mod = (Modelo) model;
            String sql = "update modelo set titulo = ?, idmarca = ? where idmodelo = ? ;";
            if (mod.getIdModelo() == -1) {
                mod.setIdModelo(0);
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
    }

    public ArrayList<Object> getAll() {
        ArrayList<Object> minhaLista = new ArrayList<>();
        String sql = "select * from modelo;";
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Modelo modelo = new Modelo();
                modelo.setIdModelo(rs.getInt("idmodelo"));
                modelo.setTitulo(rs.getString("titulo"));
                DaoMarca dm = new DaoMarca();
                Marca marca = new Marca();
                marca.setIdMarca(rs.getInt("idmarca"));

                modelo.setMarca((Marca) dm.getByID(marca));
                minhaLista.add(modelo);
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
        if (model instanceof Modelo && model != null) {
            String sql = "select * from modelo where idmodelo = ?;";
            try {
                PreparedStatement ps = this.conn.prepareStatement(sql);
                Modelo modelo = (Modelo) model;
                ps.setInt(1, modelo.getIdModelo());
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    modelo.setIdModelo(rs.getInt("idmodelo"));
                    modelo.setTitulo(rs.getString("titulo"));
                    DaoMarca dm = new DaoMarca();
                    Marca marca = new Marca();
                    marca.setIdMarca(rs.getInt("idmarca"));
                    modelo.setMarca((Marca) dm.getByID(marca));
                    return modelo;
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
        if (model instanceof Modelo) {
            Modelo modelo = (Modelo) model;
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

    @Override
    public Object getLast() {
        String sql = "select * from modelo where idmodelo = (select max(idmodelo) from modelo);";
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Modelo modelo = new Modelo();
                modelo.setIdModelo(rs.getInt("idmodelo"));
                modelo.setTitulo(rs.getString("titulo"));
                DaoMarca dm = new DaoMarca();
                Marca marca = new Marca();
                marca.setIdMarca(rs.getInt("idmarca"));
                modelo.setMarca((Marca) dm.getByID(marca));

                return modelo;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("#01");
        }
        return null;

    }
}
