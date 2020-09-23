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
public class Medico extends Pessoa{
    private String crm;
    private String especialidade;

    public Medico(int id, String nome, String cpf, String rg, String telefone, String celular, String email, String sexo) {
        this.crm = crm;
        this.especialidade = especialidade;
    }

    public Medico() {
        
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
      
}
