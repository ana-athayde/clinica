/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.services;

import classes.Funcionario;
import java.util.List;
import model.DAO.FuncionarioDAO;

/**
 *
 * @author Marlon Quilante
 */
public class FuncionarioServices {
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    
    public boolean cadastrar(Funcionario funcionario){
        return funcionarioDAO.Create(funcionario);
    }
    
     public List<Funcionario> Buscar(){
        return funcionarioDAO.Retrieve();
    }
    
    public boolean Atualizar(Funcionario funcionario){
        return funcionarioDAO.Update(funcionario);
    }
    
    public boolean Delete(int id){
        return funcionarioDAO.Delete(id);
    }
    
    public List<Funcionario> buscarFiltrando(String nome){
     
        return funcionarioDAO.RetrieveFilter(nome);
    }
    
    
    
}
