/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Aluno
 */
public class Consulta {

    private int id;
    private Paciente paciente;
    private Medico medico;
    private Date data;
    private String horario;
    private String motivo;

    public Consulta() {

    }

    public Consulta(int id, Paciente paciente, Medico medico, Date data, String horario, String motivo) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.data = data;
        this.horario = horario;
        this.motivo = motivo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return  this.getId() + "|" +this.getPaciente().getNome() + "|" + this.getMedico().getNome() + "|" + this.getData() + "|" + this.getHorario() + "|" + this.getMotivo();
    }
    
    

    

}
