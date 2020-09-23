/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import classes.Medico;
import classes.Paciente;
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
public class PacienteDAO {

//    private static Paciente paciente;
    public static boolean Create(Paciente paciente) {
        boolean estadoDaOperacao = true;

        Connection con = model.DAO.ConnectionFactory.getConnection();
        String sqlAExecutar = "INSERT INTO paciente (nome, cpf, rg, email, telefone, celular, sexo) values (?,?,?,?,?,?,?)";
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sqlAExecutar);
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            stmt.setString(3, paciente.getRg());
            stmt.setString(4, paciente.getEmail());
            stmt.setString(5, paciente.getTelefone());
            stmt.setString(6, paciente.getCelular());
            stmt.setString(7, paciente.getSexo());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            estadoDaOperacao = false;
        }
        ConnectionFactory.closeConnection(con, stmt);
        return estadoDaOperacao;
    }

    public static List<Paciente> Retrieve() {
        Connection con = ConnectionFactory.getConnection();
        String sqlAExecutar = "SELECT * FROM PACIENTE";
        ResultSet rs = null;
        PreparedStatement stmt = null;
        List<Paciente> listaPacientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sqlAExecutar);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setRg(rs.getString("rg"));
                paciente.setEmail(rs.getString("email"));
                paciente.setTelefone(rs.getString("telefone"));
                paciente.setCelular(rs.getString("celular"));
                paciente.setSexo(rs.getString("sexo"));

                listaPacientes.add(paciente);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
            return listaPacientes;
        }
    }

    public static boolean Update(Paciente paciente) {
        boolean estadoDaOperacao = true;

        PreparedStatement stmt = null;
        Connection con = ConnectionFactory.getConnection();
        String sqlAExecutar = "UPDATE paciente set nome = ?, cpf = ?, rg = ?, email = ?, telefone = ?, celular = ?, sexo = ?";

        try {
            stmt = con.prepareStatement(sqlAExecutar);
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            stmt.setString(3, paciente.getRg());
            stmt.setString(4, paciente.getEmail());
            stmt.setString(5, paciente.getTelefone());
            stmt.setString(6, paciente.getCelular());
            stmt.setString(7, paciente.getSexo());

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
        String sqlAExecutar = "DELETE FROM paciente WHERE id = ?";

        try {
            stmt = con.prepareStatement(sqlAExecutar);
            stmt.setInt(1, id);
            stmt.executeLargeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            estadoDaOperacao = false;
        }
        return estadoDaOperacao;
    }

    public static List<Paciente> RetrieveFilter(String nome) {
        Connection con = ConnectionFactory.getConnection();
        String sqlAExecutar = "SELECT * FROM PACIENTE WHERE nome LIKE( ? )";
        ResultSet rs = null;
        PreparedStatement stmt = null;
        List<Paciente> listaPacientes = new ArrayList<>();
        try {
            stmt = con.prepareStatement(sqlAExecutar);
            stmt.setString(1, '%' + nome + '%');
            rs = stmt.executeQuery();

            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setRg(rs.getString("rg"));
                paciente.setEmail(rs.getString("email"));
                paciente.setTelefone(rs.getString("telefone"));
                paciente.setCelular(rs.getString("celular"));
                paciente.setSexo(rs.getString("sexo"));
                listaPacientes.add(paciente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
            return listaPacientes;
        }
    }

    public static Paciente RetrieveFilterId(int id) {
        Connection con = ConnectionFactory.getConnection();
        String sqlAExecutar = "SELECT * FROM PACIENTE WHERE id = ?";
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Paciente paciente = new Paciente();
        try {
            stmt = con.prepareStatement(sqlAExecutar);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {

                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setRg(rs.getString("rg"));
                paciente.setEmail(rs.getString("email"));
                paciente.setTelefone(rs.getString("telefone"));
                paciente.setCelular(rs.getString("celular"));
                paciente.setSexo(rs.getString("sexo"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
            return paciente;
        }
    }
}
