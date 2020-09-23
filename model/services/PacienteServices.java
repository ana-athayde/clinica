/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.services;

import classes.Paciente;
import java.util.List;
import model.DAO.PacienteDAO;

/**
 *
 * @author Marlon Quilante
 */
public class PacienteServices {
    private PacienteDAO pacienteDAO = new PacienteDAO();
    
    public boolean cadastrar(Paciente paciente){
        return pacienteDAO.Create(paciente);
    }
    
     public List<Paciente> Buscar(){
        return pacienteDAO.Retrieve();
    }
    
    public boolean Atualizar(Paciente paciente){
        return pacienteDAO.Update(paciente);
    }
    
    public boolean Delete(int id){
        return pacienteDAO.Delete(id);
    }
    
    public List<Paciente> BuscarFiltrando (String nome){
        return pacienteDAO.RetrieveFilter(nome);
    }
    
    public Paciente BuscarFiltrandoId (int id){
        return pacienteDAO.RetrieveFilterId(id);
    }    

}
