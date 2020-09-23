/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Marlon Quilante
 */
public class ConnectionFactory {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/clinica";
    private static final String USER = "root";
    private static final String PASSWORD = "aluno";

    public static Connection getConnection() {

        try {
            return DriverManager.getConnection(URL + "?verifyServerCertificate=false"
                    + "&useSSL=false"
                    + "&requireSSL=false"
                    + "&USER=" + USER + "&password=" + PASSWORD + "&serverTimezone=UTC");
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void closeConnection(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void closeConnection(Connection conexao, PreparedStatement pstm) {
        if (pstm != null) {
            try {
                pstm.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        closeConnection(conexao);
    }
    
    public static void closeConnection(Connection conexao, PreparedStatement pstm, ResultSet rs){
        
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        closeConnection(conexao, pstm);
    }
}
