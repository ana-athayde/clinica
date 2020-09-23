/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import classes.Consulta;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Marlon Quilante
 */
public class ConsultaDAO {

    public static boolean Create(Consulta consulta) {
        boolean estadoDaOperacao = true;
        Connection con = model.DAO.ConnectionFactory.getConnection();
        String sqlAExecutar = "INSERT INTO agenda (idPaciente, idMedico, data, horario, motivo) values (?,?,?,?,?)";
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sqlAExecutar);
            stmt.setInt(1, consulta.getPaciente().getId());
            stmt.setInt(2, consulta.getMedico().getId());
            stmt.setDate(3, (Date) consulta.getData());
            stmt.setString(4, consulta.getHorario());
            stmt.setString(5, consulta.getMotivo());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            estadoDaOperacao = false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
            return estadoDaOperacao = false;
        }
    }

}
