/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classes.Funcionario;
import classes.Paciente;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.services.PacienteServices;
import view.CadastroPaciente;
import view.TelaPrincipal;

/**
 *
 * @author Marlon Quilante
 */
public class PacienteController implements ActionListener{
    private CadastroPaciente cadastroPaciente;
    private PacienteServices pacienteServices = new PacienteServices();
    public static Paciente paciente = new Paciente();
    public static boolean formIncluindo;

    public PacienteController(CadastroPaciente cadastroPaciente) {

        this.cadastroPaciente = cadastroPaciente;
        this.cadastroPaciente.getBtnNovo().addActionListener(this);
        this.cadastroPaciente.getBtnCancelar().addActionListener(this);
        this.cadastroPaciente.getBtnCadastrar().addActionListener(this);
        this.cadastroPaciente.getBtnSair().addActionListener(this);
        this.cadastroPaciente.getBtnPesquisar().addActionListener(this);
        this.cadastroPaciente.getBtnExcluir().addActionListener(this);
        habilitarEdicao(false);
        
        DefaultTableModel modeloTabela = (DefaultTableModel) this.cadastroPaciente.getjTablePacientes().getModel();
        modeloTabela.setNumRows(0);
            for (Paciente pacienteAtual : pacienteServices.Buscar()) {
            modeloTabela.addRow(new Object[]{
                pacienteAtual.getId(),
                pacienteAtual.getNome(), 
                pacienteAtual.getCpf(), 
                pacienteAtual.getRg(), 
                pacienteAtual.getTelefone(), 
                pacienteAtual.getCelular(), 
                pacienteAtual.getEmail(),
                pacienteAtual.getSexo()});
        }
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.cadastroPaciente.getBtnPesquisar()) {

            habilitarEdicao(false);

            DefaultTableModel modeloTabela = (DefaultTableModel) this.cadastroPaciente.getjTablePacientes().getModel();
            modeloTabela.setNumRows(0);

            List<Paciente> listaPacientes;
            listaPacientes = this.pacienteServices.BuscarFiltrando(this.cadastroPaciente.getTxtPesquisa().getText());
            for (Paciente pacienteAtual : listaPacientes) {
               
            modeloTabela.addRow(new Object[]{
                pacienteAtual.getId(),
                pacienteAtual.getNome(),
                pacienteAtual.getCpf(),
                pacienteAtual.getRg(),
                pacienteAtual.getTelefone(),
                pacienteAtual.getCelular(),
                pacienteAtual.getEmail(),
                pacienteAtual.getSexo()});
            
            }
           
        
        } else if (e.getSource() == this.cadastroPaciente.getBtnNovo()) {
            habilitarEdicao(true);
            this.formIncluindo = true;

        } else if (e.getSource() == this.cadastroPaciente.getBtnCancelar()) {
            habilitarEdicao(false);

        } else if (e.getSource() == this.cadastroPaciente.getBtnCadastrar()) {
            String sexo;
            if (this.cadastroPaciente.getjCheckBoxFeminino().isSelected()) {
                sexo = "FEMININO";
            } else {
                sexo = "MASCULINO";
            }
            Paciente paciente = new Paciente();
            paciente.setId(this.paciente.getId());
            paciente.setNome(this.cadastroPaciente.getTxtNome().getText());
            paciente.setCpf(this.cadastroPaciente.getjFTcpf().getText());
            paciente.setRg(this.cadastroPaciente.getjFTrg().getText());
            paciente.setEmail(this.cadastroPaciente.getTxtEmail().getText());
            paciente.setTelefone(this.cadastroPaciente.getjFTtelefone().getText());
            paciente.setCelular(this.cadastroPaciente.getjFTcelular().getText());
            paciente.setSexo(sexo);
            pacienteServices.cadastrar(paciente);
            boolean sucesso = true;
            
            DefaultTableModel modeloTabela = (DefaultTableModel) this.cadastroPaciente.getjTablePacientes().getModel();
            modeloTabela.setNumRows(0);
            for (Paciente pacienteAtual : pacienteServices.Buscar()) {
            modeloTabela.addRow(new Object[]{
                pacienteAtual.getId(),
                pacienteAtual.getNome(),
                pacienteAtual.getCpf(), 
                pacienteAtual.getRg(), 
                pacienteAtual.getTelefone(), 
                pacienteAtual.getCelular(), 
                pacienteAtual.getEmail(),
                pacienteAtual.getSexo()});
        }
            
            if (sucesso == true){
                habilitarEdicao(false);
                JOptionPane.showMessageDialog(null,
                        "Cadastro bem sucedido.",
                        "Cadastro de pacientes",
                        JOptionPane.INFORMATION_MESSAGE);
                habilitarEdicao(false);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Erro no processo de cadastro",
                        "Cadastro de pacientes",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            

            
        } else if (e.getSource() == this.cadastroPaciente.getBtnExcluir()) {
            DefaultTableModel modelo = (DefaultTableModel) this.cadastroPaciente.getjTablePacientes().getModel();
            
            int objeto = (int) this.cadastroPaciente.getjTablePacientes().getValueAt((int) this.cadastroPaciente.getjTablePacientes().getSelectedRow(), 0);
            pacienteServices.Delete(objeto);
            modelo.setNumRows(0);
            
                for (Paciente pacienteAtual : pacienteServices.Buscar()) {
                modelo.addRow(new Object[]{
                    pacienteAtual.getId(),
                    pacienteAtual.getNome(), 
                    pacienteAtual.getCpf(), 
                    pacienteAtual.getRg(), 
                    pacienteAtual.getTelefone(), 
                    pacienteAtual.getCelular(), 
                    pacienteAtual.getEmail(),
                    pacienteAtual.getSexo()});
            }
            
        } else if (e.getSource() == this.cadastroPaciente.getBtnSair()){
            this.cadastroPaciente.dispose();
            TelaPrincipal tela = new TelaPrincipal();
            tela.setVisible(true);
        }    
    }
    
    private void habilitarEdicao(boolean tipoOperacao) {
        if (tipoOperacao == true) {
            this.cadastroPaciente.getBtnNovo().setEnabled(false);
            this.cadastroPaciente.getBtnCancelar().setEnabled(true);
            this.cadastroPaciente.getBtnCadastrar().setEnabled(true);
            this.cadastroPaciente.getBtnSair().setEnabled(false);
            desLigaComponentesForm(true);
        } else {
            this.cadastroPaciente.getBtnNovo().setEnabled(true);
            this.cadastroPaciente.getBtnCancelar().setEnabled(false);
            this.cadastroPaciente.getBtnCadastrar().setEnabled(false);
            this.cadastroPaciente.getBtnSair().setEnabled(true);
            desLigaComponentesForm(false);
        }
    }

    private void desLigaComponentesForm(boolean estadoComponentes) {

        Component[] componentes = this.cadastroPaciente.getjPanelDadosPaciente().getComponents();
        for (Component componente : componentes) {
            if (componente instanceof JTextField) {
                componente.setEnabled(estadoComponentes);
                ((JTextField) componente).setText("");
            } else if (componente instanceof JCheckBox) {
                componente.setEnabled(estadoComponentes);
                ((JCheckBox) componente).setSelected(false);
            } else if (componente instanceof JFormattedTextField) {
                componente.setEnabled(estadoComponentes);
                ((JFormattedTextField) componente).setText("");
            }
        }
        
        this.cadastroPaciente.getjPanelPesquisaPaciente().getComponents();
        for (Component componente : componentes){
            if (componente instanceof JTextField){
                componente.setEnabled(estadoComponentes);
                ((JTextField) componente).setText("");
            }
        }
    }
}
