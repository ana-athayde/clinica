/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.services;

import classes.Consulta;
import classes.Paciente;
import java.util.List;
import model.DAO.ConsultaDAO;

/**
 *
 * @author Marlon Quilante
 */
public class ConsultaServices {
    private ConsultaDAO consultaDAO = new ConsultaDAO();
    
    public boolean Agendar(Consulta consulta){
        return consultaDAO.Create(consulta);
    }
    

}
