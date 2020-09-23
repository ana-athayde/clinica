/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classes.Medico;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.services.MedicoServices;
import view.CadastroMedico;
import view.TelaPrincipal;

/**
 *
 * @author Marlon Quilante
 */
public class MedicoController implements ActionListener{
    CadastroMedico cadastroMedico;
    private MedicoServices medicoServices = new MedicoServices();
    public static Medico medico = new Medico();
    public static boolean formIncluindo;
    public static boolean estadoComponentes = false;

    public MedicoController(CadastroMedico cadastroMedico) {

        this.cadastroMedico = cadastroMedico;
        this.cadastroMedico.getBtnNovo().addActionListener(this);
        this.cadastroMedico.getBtnCancelar().addActionListener(this);
        this.cadastroMedico.getBtnCadastrar().addActionListener(this);
        this.cadastroMedico.getBtnSair().addActionListener(this);
        this.cadastroMedico.getBtnPesquisar().addActionListener(this);
        this.cadastroMedico.getBtnExcluir().addActionListener(this);
        
        DefaultTableModel modeloTabela = (DefaultTableModel) this.cadastroMedico.getjTableMedicos().getModel();
        modeloTabela.setNumRows(0);
        
        habilitarEdicao(false);
            for (Medico medicoAtual : medicoServices.Buscar()) {
            modeloTabela.addRow(new Object[]{
                medicoAtual.getId(),
                medicoAtual.getNome(),
                medicoAtual.getCrm(),
                medicoAtual.getCpf(),
                medicoAtual.getRg(),
                medicoAtual.getEspecialidade(),
                medicoAtual.getTelefone(),
                medicoAtual.getCelular(),
                medicoAtual.getEmail(),
                medicoAtual.getSexo()});
        }
                
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.cadastroMedico.getBtnPesquisar()) {

            habilitarEdicao(false);

            DefaultTableModel modeloTabela = (DefaultTableModel) this.cadastroMedico.getjTableMedicos().getModel();
            modeloTabela.setNumRows(0);

            List<Medico> listaMedicos;
            listaMedicos = this.medicoServices.BuscarFiltrando(this.cadastroMedico.getTxtPesquisa().getText());
            for (Medico medicoAtual : listaMedicos) {
               
            modeloTabela.addRow(new Object[]{
                medicoAtual.getId(),
                medicoAtual.getNome(),
                medicoAtual.getCrm(),
                medicoAtual.getCpf(),
                medicoAtual.getRg(),
                medicoAtual.getEspecialidade(),
                medicoAtual.getTelefone(),
                medicoAtual.getCelular(),
                medicoAtual.getEmail(),
                medicoAtual.getSexo()});
            
            }
        } else if (e.getSource() == this.cadastroMedico.getBtnNovo()) {
            habilitarEdicao(true);
            this.formIncluindo = true;

        } else if (e.getSource() == this.cadastroMedico.getBtnCancelar()) {
            habilitarEdicao(false);

        } else if (e.getSource() == this.cadastroMedico.getBtnCadastrar()) {
            
            String sexo;
            if (this.cadastroMedico.getjCheckBoxFeminino().isSelected()) {
                sexo = "FEMININO";
            } else {
                sexo = "MASCULINO";
            }
            
            Medico medico = new Medico();
            medico.setId(this.medico.getId());
            medico.setNome(this.cadastroMedico.getTxtNome().getText());
            medico.setCrm(this.cadastroMedico.getjFTcrm().getText());
            medico.setCpf(this.cadastroMedico.getjFTcpf().getText());
            medico.setRg(this.cadastroMedico.getjFTrg().getText());
            medico.setEspecialidade((String)this.cadastroMedico.getjComboBoxEspecialidade().getSelectedItem());
            medico.setTelefone(this.cadastroMedico.getjFTtelefone().getText());
            medico.setCelular(this.cadastroMedico.getjFTcelular().getText());
            medico.setEmail(this.cadastroMedico.getTxtEmail().getText());
            medico.setSexo(sexo);
            medicoServices.cadastrar(medico);
            boolean sucesso = true;
            
            DefaultTableModel modeloTabela = (DefaultTableModel) this.cadastroMedico.getjTableMedicos().getModel();
            modeloTabela.setNumRows(0);

            habilitarEdicao(false);
                for (Medico medicoAtual : medicoServices.Buscar()) {
                modeloTabela.addRow(new Object[]{
                    medicoAtual.getId(),
                    medicoAtual.getNome(),
                    medicoAtual.getCrm(),
                    medicoAtual.getCpf(),
                    medicoAtual.getRg(),
                    medicoAtual.getEspecialidade(),
                    medicoAtual.getTelefone(),
                    medicoAtual.getCelular(),
                    medicoAtual.getEmail(),
                    medicoAtual.getSexo()});
        }
                
            if (sucesso == true){
                habilitarEdicao(false);
                JOptionPane.showMessageDialog(null,
                        "Cadastro bem sucedido.",
                        "Cadastro de medicos",
                        JOptionPane.INFORMATION_MESSAGE);
                habilitarEdicao(false);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Erro no processo de cadastro",
                        "Cadastro de medicos",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } else if (e.getSource() == this.cadastroMedico.getBtnSair()) {
            this.cadastroMedico.dispose();
            TelaPrincipal tela = new TelaPrincipal();
            tela.setVisible(true);
            
        } else if (e.getSource() == this.cadastroMedico.getBtnExcluir()){
            DefaultTableModel modelo = (DefaultTableModel) this.cadastroMedico.getjTableMedicos().getModel();
            medicoServices.Delete((int) this.cadastroMedico.getjTableMedicos().getValueAt((int) this.cadastroMedico.getjTableMedicos().getSelectedRow(), 0));
            modelo.setNumRows(0);
            
            for (Medico medicoAtual : medicoServices.Buscar()) {
            modelo.addRow(new Object[]{
                medicoAtual.getId(),
                medicoAtual.getNome(),
                medicoAtual.getCrm(),
                medicoAtual.getCpf(),
                medicoAtual.getRg(),
                medicoAtual.getEspecialidade(),
                medicoAtual.getTelefone(),
                medicoAtual.getCelular(),
                medicoAtual.getEmail(),
                medicoAtual.getSexo()});
        }
            
            
        }
            
    }

    private void habilitarEdicao(boolean tipoOperacao) {
        if (tipoOperacao == true) {
            this.cadastroMedico.getBtnNovo().setEnabled(false);
            this.cadastroMedico.getBtnCancelar().setEnabled(true);
            this.cadastroMedico.getBtnCadastrar().setEnabled(true);
            this.cadastroMedico.getBtnSair().setEnabled(false);
            desLigaComponentesForm(true);
        } else {
            this.cadastroMedico.getBtnNovo().setEnabled(true);
            this.cadastroMedico.getBtnCancelar().setEnabled(false);
            this.cadastroMedico.getBtnCadastrar().setEnabled(false);
            this.cadastroMedico.getBtnSair().setEnabled(true);
            desLigaComponentesForm(false);
        }
    }

    private void desLigaComponentesForm(boolean estadoComponentes) {

        Component[] componentes = this.cadastroMedico.getjPanelDadosMedico().getComponents();
        for (Component componente : componentes) {
            if (componente instanceof JTextField) {
                componente.setEnabled(estadoComponentes);
                ((JTextField) componente).setText("");
            } else if (componente instanceof JCheckBox) {
                componente.setEnabled(estadoComponentes);
                ((JCheckBox) componente).setSelected(false);
            } else if (componente instanceof JComboBox) {
                componente.setEnabled(estadoComponentes);
                ((JComboBox) componente).setSelectedItem(false);
            } else if (componente instanceof JFormattedTextField) {
                componente.setEnabled(estadoComponentes);
                ((JFormattedTextField) componente).setText("");
            }
        }
        this.cadastroMedico.getjPanelPesquisaMedico().getComponents();
        for (Component componente : componentes) {
            if (componente instanceof JTextField) {
                componente.setEnabled(estadoComponentes);
                ((JTextField) componente).setText("");
            } 
        }
    }
}
