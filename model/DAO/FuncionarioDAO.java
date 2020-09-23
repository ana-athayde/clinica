/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import classes.Funcionario;
import static controller.FuncionarioController.funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marlon Quilante
 */
public class FuncionarioDAO {

    public static boolean Create(Funcionario funcionario) {
        boolean estadoDaOperacao = true;

        Connection con = model.DAO.ConnectionFactory.getConnection();
        String sqlAExecutar = "INSERT INTO funcionario (nome, cpf, rg, email, telefone, celular, sexo) values (?,?,?,?,?,?,?)";
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sqlAExecutar);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getRg());
            stmt.setString(4, funcionario.getEmail());
            stmt.setString(5, funcionario.getTelefone());
            stmt.setString(6, funcionario.getCelular());
            stmt.setString(7, funcionario.getSexo());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            estadoDaOperacao = false;
        }
        ConnectionFactory.closeConnection(con, stmt);
        return estadoDaOperacao;
    }

    public static List<Funcionario> Retrieve() {
        Connection con = ConnectionFactory.getConnection();
        String sqlAExecutar = "SELECT * FROM FUNCIONARIO";
        ResultSet rs = null;
        PreparedStatement stmt = null;
        List<Funcionario> listaFuncionarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sqlAExecutar);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setRg(rs.getString("rg"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setCelular(rs.getString("celular"));
                funcionario.setSexo(rs.getString("sexo"));
                listaFuncionarios.add(funcionario);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
            return listaFuncionarios;
        }
    }

    public static boolean Update(Funcionario funcionario) {
        boolean estadoDaOperacao = true;

        PreparedStatement stmt = null;
        Connection con = ConnectionFactory.getConnection();
        String sqlAExecutar = "UPDATE funcionario SET nome = ?, cpf = ?, rg = ?, email = ?, telefone = ?, celular = ?, sexo = ? WHERE id = ?";

        try {
            stmt = con.prepareStatement(sqlAExecutar);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getRg());
            stmt.setString(4, funcionario.getEmail());
            stmt.setString(5, funcionario.getTelefone());
            stmt.setString(6, funcionario.getCelular());
            stmt.setString(7, funcionario.getSexo());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            estadoDaOperacao = false;
        }
        ConnectionFactory.closeConnection(con, stmt);
        return estadoDaOperacao;
    }

    public static boolean Delete(int id) {
        boolean estadoDaOperacao = true;
        PreparedStatement stmt = null;
        Connection con = model.DAO.ConnectionFactory.getConnection();
        String sqlAExecutar = "DELETE FROM funcionario WHERE id = ?";
        try {
            stmt = con.prepareStatement(sqlAExecutar);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            estadoDaOperacao = false;
        }
        return estadoDaOperacao;
    }

    public static List<Funcionario> RetrieveFilter(String nome) {
        Connection con = ConnectionFactory.getConnection();
        String sqlAExecutar = "SELECT * FROM FUNCIONARIO WHERE nome LIKE( ? )";
        ResultSet rs = null;
        PreparedStatement stmt = null;
        List<Funcionario> listaFuncionarios = new ArrayList<>();
        try {
            stmt = con.prepareStatement(sqlAExecutar);
            stmt.setString(1, '%' + nome + '%');
            rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setRg(rs.getString("rg"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setCelular(rs.getString("celular"));
                funcionario.setSexo(rs.getString("sexo"));
                listaFuncionarios.add(funcionario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
            return listaFuncionarios;
        }

    }

}
