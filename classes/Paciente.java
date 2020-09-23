/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Marlon Quilante
 */
public class Paciente extends Pessoa{
    

    public Paciente(int id, String nome, String cpf, String rg, String email, String telefone, String celular, String sexo) {
        super (id, nome, cpf, rg, email, telefone, celular, sexo);
    }
    
    public Paciente(){
        
    } 
    
}
