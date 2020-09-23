/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classes.Consulta;
import classes.Medico;
import classes.Paciente;
import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.services.ConsultaServices;
import model.services.MedicoServices;
import model.services.PacienteServices;
import view.Agendamento;
import view.TelaPrincipal;

/**
 *
 * @author Marlon Quilante
 */
public class ConsultaController implements ActionListener {

    Agendamento agendamento;
    public static Consulta consulta = new Consulta();
    public static boolean formIncluindo;
    public static boolean estadoComponentes = false;
    view.CadastroPaciente cadastroPaciente = new view.CadastroPaciente();
    view.CadastroMedico cadastroMedico = new view.CadastroMedico();
    
    private ConsultaServices consultaServices = new ConsultaServices();
    private model.services.PacienteServices pacienteServices = new PacienteServices();
    private model.services.MedicoServices medicoServices = new MedicoServices();

    public ConsultaController(Agendamento agendamento) {
        this.agendamento = agendamento;
        this.agendamento.getBtnNovo().addActionListener(this);
        this.agendamento.getBtnAgendar().addActionListener(this);
        this.agendamento.getBtnCancelar().addActionListener(this);
        this.agendamento.getBtnBuscarPaciente().addActionListener(this);
        this.agendamento.getBtnBuscarMedico().addActionListener(this);
        this.agendamento.getBtnSair().addActionListener(this);
        this.agendamento.getTxtMedicoId().addActionListener(this);
        this.agendamento.getTxtPacienteId().addActionListener(this);

        habilitarEdicao(false);
        
        DefaultTableModel modeloTabela = (DefaultTableModel) cadastroPaciente.getjTablePacientes().getModel();
            modeloTabela.setNumRows(0);
            for (Paciente pacienteAtual : pacienteServices.Buscar()) {
            modeloTabela.addRow(new Object[]{pacienteAtual.getId(),
                    pacienteAtual.getNome(),
                    pacienteAtual.getCpf(),
                    pacienteAtual.getRg()});
        }
            
            cadastroMedico.getjTableMedicos().getModel();
            modeloTabela.setNumRows(0);

            habilitarEdicao(false);
                for (Medico medicoAtual : medicoServices.Buscar()) {
                modeloTabela.addRow(new Object[]{medicoAtual.getId(),
                    medicoAtual.getNome(),
                    medicoAtual.getCrm(),
                    medicoAtual.getCpf()});
                }        
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.agendamento.getBtnNovo()) {
            habilitarEdicao(true);
            this.formIncluindo = true;
            this.agendamento.getjTextAreaMotivo().setEnabled(true);

        } else if (e.getSource() == this.agendamento.getBtnCancelar()) {
            habilitarEdicao(false);
            this.agendamento.getjTextAreaMotivo().setEnabled(false);

        } else if (e.getSource() == this.agendamento.getBtnAgendar()) {

            Consulta consulta = new Consulta();
            Paciente paciente ;
            Medico medico ;
            

            paciente = model.DAO.PacienteDAO.RetrieveFilterId((int) this.agendamento.getjTablePacientes().getValueAt(this.agendamento.getjTablePacientes().getSelectedRow(), 0));
            consulta.setPaciente(paciente);
                     
            medico = model.DAO.MedicoDAO.RetrieveFilterId((int) this.agendamento.getjTableMedicos().getValueAt(this.agendamento.getjTableMedicos().getSelectedRow(), 0));
            consulta.setMedico(medico);

            consulta.setData(this.agendamento.getjDateChooserData().getDate());
            consulta.setHorario(this.agendamento.getjFThora().getText());
            consulta.setMotivo(this.agendamento.getjTextAreaMotivo().getText());

            consultaServices.Agendar(consulta);
            boolean sucesso = true;
            
            if (sucesso == true) {
                JOptionPane.showMessageDialog(null,
                        "Inclusão/Alteração bem sucedida.",
                        "Cadastro de funcionários",
                        JOptionPane.INFORMATION_MESSAGE);
                habilitarEdicao(false);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Erro no processo de Inclusão/Alteração.",
                        "Cadastro de funcionários",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } else if (e.getSource() == this.agendamento.getBtnBuscarPaciente()) {
            DefaultTableModel modeloTabela = (DefaultTableModel) this.agendamento.getjTablePacientes().getModel();
            modeloTabela.setNumRows(0);
            List<Paciente> listaPacientes;

            listaPacientes = pacienteServices.BuscarFiltrando(this.agendamento.getTxtPaciente().getText());
            for (Paciente pacienteAtual : listaPacientes) {
                modeloTabela.addRow(new Object[]{pacienteAtual.getId(),
                    pacienteAtual.getNome(),
                    pacienteAtual.getCpf(),
                    pacienteAtual.getRg()});
            }

        } else if (e.getSource() == this.agendamento.getBtnBuscarMedico()) {
            DefaultTableModel modeloTabela = (DefaultTableModel) this.agendamento.getjTableMedicos().getModel();
            modeloTabela.setNumRows(0);
            List<Medico> listaMedicos;

            listaMedicos = medicoServices.BuscarFiltrando(this.agendamento.getTxtMedico().getText());
            for (Medico medicoAtual : listaMedicos) {
                modeloTabela.addRow(new Object[]{medicoAtual.getId(),
                    medicoAtual.getNome(),
                    medicoAtual.getCrm(),
                    medicoAtual.getCpf()});
            }
        } else if (e.getSource() == this.agendamento.getBtnSair()) {
            this.agendamento.dispose();
            TelaPrincipal tela = new TelaPrincipal();
            tela.setVisible(true);
        }

    }

    private void habilitarEdicao(boolean tipoOperacao) {
        if (tipoOperacao == true) {
            this.agendamento.getBtnNovo().setEnabled(false);
            this.agendamento.getBtnAgendar().setEnabled(true);
            this.agendamento.getBtnCancelar().setEnabled(true);
            this.agendamento.getBtnBuscarPaciente().setEnabled(true);
            this.agendamento.getBtnBuscarMedico().setEnabled(true);
            this.agendamento.getTxtMedicoId().setEnabled(false);
            this.agendamento.getTxtPacienteId().setEnabled(false);
            this.agendamento.getBtnSair().setEnabled(false);
            desLigaComponentesForm(true);
            desligarID(false);
        } else {
            this.agendamento.getBtnNovo().setEnabled(true);
            this.agendamento.getBtnAgendar().setEnabled(false);
            this.agendamento.getBtnCancelar().setEnabled(false);
            this.agendamento.getBtnBuscarPaciente().setEnabled(false);
            this.agendamento.getBtnBuscarMedico().setEnabled(false);
            this.agendamento.getTxtMedicoId().setEnabled(false);
            this.agendamento.getTxtPacienteId().setEnabled(false);
            this.agendamento.getBtnSair().setEnabled(true);
            desLigaComponentesForm(false);
            desligarID(false);
        }

    }

    private void desLigaComponentesForm(boolean estadoComponentes) {
        Component[] componentes = this.agendamento.getjPanelAgendamento().getComponents();
        for (Component componente : componentes) {
            if (componente instanceof JTextField) {
                componente.setEnabled(estadoComponentes);
                ((JTextField) componente).setText("");
            } else if (componente instanceof JFormattedTextField) {
                componente.setEnabled(estadoComponentes);
                ((JFormattedTextField) componente).setText("");
            } else if (componente instanceof JDateChooser) {
                componente.setEnabled(estadoComponentes);
                ((JDateChooser) componente).setDate(null);
            }
        }
    }

    private void desligarID(boolean estadoComponentes) {
        Component[] componentes = this.agendamento.getjPanelId().getComponents();
        for (Component componente : componentes) {
            if (componente instanceof JTextField) {
                componente.setEnabled(estadoComponentes);
                ((JTextField) componente).setText("");
            }
        }
    }
}
