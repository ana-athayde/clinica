/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.services;

import classes.Medico;
import java.util.List;
import model.DAO.MedicoDAO;

/**
 *
 * @author Marlon Quilante
 */
public class MedicoServices {
    private MedicoDAO medicoDAO = new MedicoDAO();
    
    public boolean cadastrar(Medico medico){
        return medicoDAO.Create(medico);
    }
    
    public List<Medico> Buscar(){
        return medicoDAO.Retrieve();
    }
    
    public boolean Atualizar(Medico medico){
        return medicoDAO.Update(medico);
    }
    
    public boolean Delete(int id){
        return medicoDAO.Delete(id);
    }
    
    public List<Medico> BuscarFiltrando (String nome){
        return medicoDAO.RetrieveFilter(nome);
    }
    
    public Medico BuscarFiltrandoId (int id){
        return medicoDAO.RetrieveFilterId(id);
    }
}
