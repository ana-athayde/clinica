/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.Date;

/**
 *
 * @author Aluno
 */
public class Agenda {
    String pacienteNome;
    String medicoNome;
    Date data;
    String horario;
    String motivo;
    
    public Agenda(){
        
    }

    public Agenda(String pacienteNome, String medicoNome, Date data, String horario, String motivo) {
        this.pacienteNome = pacienteNome;
        this.medicoNome = medicoNome;
        this.data = data;
        this.horario = horario;
        this.motivo = motivo;
    }

    public String getPacienteNome() {
        return pacienteNome;
    }

    public void setPacienteNome(String pacienteNome) {
        this.pacienteNome = pacienteNome;
    }

    public String getMedicoNome() {
        return medicoNome;
    }

    public void setMedicoNome(String medicoNome) {
        this.medicoNome = medicoNome;
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
    
    
}
