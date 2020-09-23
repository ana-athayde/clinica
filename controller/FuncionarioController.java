/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classes.Funcionario;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.services.FuncionarioServices;
import view.CadastroFuncionario;
import view.TelaPrincipal;

/**
 *
 * @author Marlon Quilante
 */
public class FuncionarioController implements ActionListener {
    CadastroFuncionario cadastroFuncionario;
    private FuncionarioServices funcionarioServices = new FuncionarioServices();
    public static Funcionario funcionario = new Funcionario();
    public static boolean estadoComponentes = false;

    public FuncionarioController(CadastroFuncionario cadastroFuncionario) {

        this.cadastroFuncionario = cadastroFuncionario;
        this.cadastroFuncionario.getBtnNovo().addActionListener(this);
        this.cadastroFuncionario.getBtnCancelar().addActionListener(this);
        this.cadastroFuncionario.getBtnCadastrar().addActionListener(this);
        this.cadastroFuncionario.getBtnSair().addActionListener(this);
        this.cadastroFuncionario.getBtnPesquisar().addActionListener(this);
        this.cadastroFuncionario.getBtnExcluir().addActionListener(this);

        DefaultTableModel modeloTabela = (DefaultTableModel) this.cadastroFuncionario.getjTableFuncionarios().getModel();
        modeloTabela.setNumRows(0);

        habilitarEdicao(false);
        for (Funcionario funcionarioAtual : this.funcionarioServices.Buscar()) {
            modeloTabela.addRow(new Object[]{
                funcionarioAtual.getId(),
                funcionarioAtual.getNome(),
                funcionarioAtual.getCpf(),
                funcionarioAtual.getRg(),
                funcionarioAtual.getTelefone(),
                funcionarioAtual.getCelular(),
                funcionarioAtual.getEmail(),
                funcionarioAtual.getSexo()});
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.cadastroFuncionario.getBtnPesquisar()) {

            habilitarEdicao(false);

            DefaultTableModel modeloTabela = (DefaultTableModel) this.cadastroFuncionario.getjTableFuncionarios().getModel();
            modeloTabela.setNumRows(0);

            List<Funcionario> listaFuncionario;

            listaFuncionario = this.funcionarioServices.buscarFiltrando(this.cadastroFuncionario.getTxtPesquisa().getText());

            for (Funcionario funcionarioAtual : listaFuncionario) {

                modeloTabela.addRow(new Object[]{
                funcionarioAtual.getId(),
                funcionarioAtual.getNome(),
                funcionarioAtual.getCpf(),
                funcionarioAtual.getRg(),
                funcionarioAtual.getTelefone(),
                funcionarioAtual.getCelular(),
                funcionarioAtual.getEmail(),
                funcionarioAtual.getSexo()});

            }
        } else if (e.getSource() == this.cadastroFuncionario.getBtnNovo()) {
            habilitarEdicao(true);

        } else if (e.getSource() == this.cadastroFuncionario.getBtnCancelar()) {
            habilitarEdicao(false);

        } else if (e.getSource() == this.cadastroFuncionario.getBtnCadastrar()) {
            String sexo;
            if (this.cadastroFuncionario.getjCheckBoxFeminino().isSelected()) {
                sexo = "FEMININO";
            } else {
                sexo = "MASCULINO";
            }

            Funcionario funcionario = new Funcionario();
            funcionario.setId(this.funcionario.getId());
            funcionario.setNome(this.cadastroFuncionario.getTxtNome().getText());
            funcionario.setCpf(this.cadastroFuncionario.getjFTcpf().getText());
            funcionario.setRg(this.cadastroFuncionario.getjFTrg().getText());
            funcionario.setEmail(this.cadastroFuncionario.getTxtEmail().getText());
            funcionario.setTelefone(this.cadastroFuncionario.getjFTtelefone().getText());
            funcionario.setCelular(this.cadastroFuncionario.getjFTcelular().getText());
            funcionario.setSexo(sexo);
            funcionarioServices.cadastrar(funcionario);
            boolean sucesso = true;
            DefaultTableModel modeloTabela = (DefaultTableModel) this.cadastroFuncionario.getjTableFuncionarios().getModel();
            modeloTabela.setNumRows(0);

            habilitarEdicao(false);
            for (Funcionario funcionarioAtual : this.funcionarioServices.Buscar()) {
                modeloTabela.addRow(new Object[]{
                    funcionarioAtual.getId(),
                    funcionarioAtual.getNome(),
                    funcionarioAtual.getCpf(),
                    funcionarioAtual.getRg(),
                    funcionarioAtual.getTelefone(),
                    funcionarioAtual.getCelular(),
                    funcionarioAtual.getEmail(),
                    funcionarioAtual.getSexo()});
        }
            if (sucesso == true) {
                JOptionPane.showMessageDialog(null,
                        "Cadastro bem sucedido.",
                        "Cadastro de funcionários",
                        JOptionPane.INFORMATION_MESSAGE);
                habilitarEdicao(false);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Erro no processo de cadastro",
                        "Cadastro de funcionários",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } else if (e.getSource() == this.cadastroFuncionario.getBtnSair()) {
            this.cadastroFuncionario.dispose();
            TelaPrincipal tela = new TelaPrincipal();
            tela.setVisible(true);
            
        } else if (e.getSource() == this.cadastroFuncionario.getBtnExcluir()){
            DefaultTableModel modelo = (DefaultTableModel) this.cadastroFuncionario.getjTableFuncionarios().getModel();            
            
            int objeto = (int) this.cadastroFuncionario.getjTableFuncionarios().getValueAt((int)this.cadastroFuncionario.getjTableFuncionarios().getSelectedRow(), 0);
            funcionarioServices.Delete(objeto);
              
            modelo.setNumRows(0);
            
            for (Funcionario funcionarioAtual : this.funcionarioServices.Buscar()) {
            modelo.addRow(new Object[]{
                funcionarioAtual.getId(),
                funcionarioAtual.getNome(),
                funcionarioAtual.getCpf(),
                funcionarioAtual.getRg(),
                funcionarioAtual.getTelefone(),
                funcionarioAtual.getCelular(),
                funcionarioAtual.getEmail(),
                funcionarioAtual.getSexo()});
            }
        }    
    }

    private void habilitarEdicao(boolean tipoOperacao) {
        if (tipoOperacao == true) {
            this.cadastroFuncionario.getBtnNovo().setEnabled(false);
            this.cadastroFuncionario.getBtnCancelar().setEnabled(true);
            this.cadastroFuncionario.getBtnCadastrar().setEnabled(true);
            this.cadastroFuncionario.getBtnSair().setEnabled(false);
            desLigaComponentesForm(true);
        } else {
            this.cadastroFuncionario.getBtnNovo().setEnabled(true);
            this.cadastroFuncionario.getBtnCancelar().setEnabled(false);
            this.cadastroFuncionario.getBtnCadastrar().setEnabled(false);
            this.cadastroFuncionario.getBtnSair().setEnabled(true);
            desLigaComponentesForm(false);
        }
    }

    private void desLigaComponentesForm(boolean estadoComponentes) {

        Component[] componentes = this.cadastroFuncionario.getjPanelDadosFuncionario().getComponents();
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

        this.cadastroFuncionario.getjPanelPesquisaFuncionario().getComponents();
        for (Component componente : componentes) {
            if (componente instanceof JTextField) {
                componente.setEnabled(estadoComponentes);
                ((JTextField) componente).setText("");
            }
        }
    }
}
