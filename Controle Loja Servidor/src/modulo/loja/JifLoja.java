package modulo.loja;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modulo.versao.Versao;

/**
 *
 * @author Marcos Junior
 */
public class JifLoja extends javax.swing.JInternalFrame {

    private LojaDAO DAO;
    private Loja objFun;
    private int acao;
    private Versao ver = new Versao();

    public JifLoja() {
        initComponents();
        setTitle("Cadastrar Loja: " + ver.getVersao());
        DAO = new LojaDAO();
        DefaultTableModel modelo = (DefaultTableModel) jtTabelaLoja.getModel();
        jtTabelaLoja.setRowSorter(new TableRowSorter(modelo));
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    public void limparCampos() {
        jtId.setText("");
        jtNomeDaLoja.setText("");
        jtNumeroDaLoja.setText("");
        jtGerenteDaLoja.setText("");
    }

    public boolean validarCampos() {
        if (jtNumeroDaLoja.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Número da Loja!");
            jtNumeroDaLoja.requestFocus();
            return false;
        }
        if (jtNomeDaLoja.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Nome da Loja!");
            jtNomeDaLoja.requestFocus();
            return false;
        }
        if (jtGerenteDaLoja.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Gerente da Loja!");
            jtGerenteDaLoja.requestFocus();
            return false;
        }
        return true;
    }

    public boolean preencherObjetosSalvar() {
        objFun = new Loja();
        objFun.setNome_loja(jtNomeDaLoja.getText());
        objFun.setNumero_loja(Integer.parseInt(jtNumeroDaLoja.getText()));
        objFun.setGerente_loja(jtGerenteDaLoja.getText());
        return true;
    }

    public boolean preencherObjetosEditar() {
        objFun = new Loja();
        objFun.setId(Integer.parseInt(jtId.getText()));
        objFun.setNome_loja(jtNomeDaLoja.getText());
        objFun.setNumero_loja(Integer.parseInt(jtNumeroDaLoja.getText()));
        objFun.setGerente_loja(jtGerenteDaLoja.getText());
        return true;
    }

    public void preencheTabela() throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jtTabelaLoja.getModel();
        modelo.setNumRows(0);
        LojaDAO func = new LojaDAO();
        for (Loja p : func.TabelaPesquisa()) {
            modelo.addRow(new Object[]{
                p.getId(), p.getNome_loja(), p.getNumero_loja(), p.getGerente_loja()
            });
        }
    }

    public void preencheTabela2() throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jtTabelaLoja.getModel();
        modelo.setNumRows(0);
        LojaDAO func = new LojaDAO();
        for (Loja p : func.TabelaPesquisa2(Integer.parseInt(jtNumeroDaLoja.getText()))) {
            modelo.addRow(new Object[]{
                p.getId(), p.getNome_loja(), p.getNumero_loja(), p.getGerente_loja()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtId = new javax.swing.JTextField();
        jtNomeDaLoja = new javax.swing.JTextField();
        jbNovo = new javax.swing.JButton();
        jbSalvar = new javax.swing.JButton();
        jbEditar = new javax.swing.JButton();
        jbExcluir = new javax.swing.JButton();
        jbCancelar = new javax.swing.JButton();
        jbPesquisar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtTabelaLoja = new javax.swing.JTable();
        jbAtualizar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtNumeroDaLoja = new javax.swing.JTextField();
        jtGerenteDaLoja = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jPanel1.setBackground(new java.awt.Color(0, 0, 204));

        jLabel1.setText("ID");

        jLabel5.setText("Nome Loja");

        jtId.setEnabled(false);

        jbNovo.setText("Novo");
        jbNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNovoActionPerformed(evt);
            }
        });

        jbSalvar.setText("Salvar");
        jbSalvar.setEnabled(false);
        jbSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvarActionPerformed(evt);
            }
        });

        jbEditar.setText("Editar");
        jbEditar.setEnabled(false);
        jbEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditarActionPerformed(evt);
            }
        });

        jbExcluir.setText("Excluir");
        jbExcluir.setEnabled(false);
        jbExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExcluirActionPerformed(evt);
            }
        });

        jbCancelar.setText("Cancelar");
        jbCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
            }
        });

        jbPesquisar.setText("Pesquisar");
        jbPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPesquisarActionPerformed(evt);
            }
        });

        jtTabelaLoja.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome da Loja", "Número da Loja", "Gerente da Loja"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtTabelaLoja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtTabelaLojaMouseClicked(evt);
            }
        });
        jtTabelaLoja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtTabelaLojaKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jtTabelaLoja);

        jbAtualizar.setText("Lista Todos");
        jbAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAtualizarActionPerformed(evt);
            }
        });

        jLabel2.setText("Número Loja");

        jLabel3.setText("Gerente da Loja");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jbCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(jbEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jtId, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(171, 171, 171)
                                .addComponent(jbNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtNumeroDaLoja, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jtNomeDaLoja, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jtGerenteDaLoja, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(108, 108, 108)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jbAtualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jbPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
                                    .addComponent(jbSalvar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbNovo))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtNomeDaLoja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbSalvar))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtNumeroDaLoja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbPesquisar))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtGerenteDaLoja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbAtualizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbExcluir)
                    .addComponent(jbEditar)
                    .addComponent(jbCancelar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNovoActionPerformed
        limparCampos();
        jtId.setEnabled(false);
        jtNomeDaLoja.setEnabled(true);
        jbNovo.setEnabled(false);
        jbSalvar.setEnabled(true);
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        jbPesquisar.setEnabled(false);
        jbCancelar.setEnabled(true);
        acao = 1;
    }//GEN-LAST:event_jbNovoActionPerformed

    private void jbSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarActionPerformed
        try {
            if (acao == 1) {
                if (validarCampos()) {
                    if (preencherObjetosSalvar()) {
                        if (DAO.Insert(objFun)) {
                            JOptionPane.showMessageDialog(this, "Salvo Com Sucesso!");
                            jbCancelarActionPerformed(evt);
                            preencheTabela();
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Não Foi Possível Salvar!");
                    }
                }
            }
            if (acao == 2) {
                if (validarCampos()) {
                    if (preencherObjetosEditar()) {
                        if (DAO.Update(objFun)) {
                            JOptionPane.showMessageDialog(this, "Editado Com Sucesso!");
                            jbCancelarActionPerformed(evt);
                            preencheTabela();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Não Foi Possível Editar o Registro!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao Salvar: ");
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(this, "Erro ao Salvar: " + erro.getMessage());
        }
    }//GEN-LAST:event_jbSalvarActionPerformed

    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarActionPerformed
        jtId.setEnabled(false);
        jtNumeroDaLoja.setEnabled(true);
        jtNomeDaLoja.setEnabled(true);
        jtGerenteDaLoja.setEnabled(true);

        jbNovo.setEnabled(false);
        jbSalvar.setEnabled(true);
        jbEditar.setEnabled(false);

        jbPesquisar.setEnabled(false);
        jbCancelar.setEnabled(true);

        acao = 2;
    }//GEN-LAST:event_jbEditarActionPerformed

    private void jbExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExcluirActionPerformed
        try {
            if (jtNumeroDaLoja.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Preencha o Campo Matricula");
            } else {
                if (DAO.Delete(Integer.parseInt(jtNumeroDaLoja.getText()))) {
                    JOptionPane.showMessageDialog(this, "Registro Excluido com Sucesso");
                    limparCampos();
                    preencheTabela();
                } else {
                    JOptionPane.showMessageDialog(this, "Não Foi Possível Excluir o Registro");

                }
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(this, "Erro ao Excluir: " + erro.getMessage());
        }
    }//GEN-LAST:event_jbExcluirActionPerformed

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        limparCampos();
        jtId.setEnabled(false);
        jtNomeDaLoja.setEnabled(true);
        jbNovo.setEnabled(true);
        jbSalvar.setEnabled(false);
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        jbPesquisar.setEnabled(true);
        jbCancelar.setEnabled(true);
    }//GEN-LAST:event_jbCancelarActionPerformed

    private void jbPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPesquisarActionPerformed
        try {
            if (jtNumeroDaLoja.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Preencha o Campo Número da Loja!");
                jtNumeroDaLoja.requestFocus();
            } else {
                Loja objFun = DAO.PesquisaNumeroLoja(Integer.parseInt(jtNumeroDaLoja.getText()));
                if (objFun == null) {
                    JOptionPane.showMessageDialog(this, "Não Foi Possível Encontrar o Registro!");
                    jtNumeroDaLoja.setText("");//Limpa ID
                    jtNumeroDaLoja.requestFocus();
                } else {
                    try {
                        preencheTabela2();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao Iniciar readTable." + ex.getMessage());
                    }
                    jtId.setText(Integer.toString(objFun.getId()));
                    jtNomeDaLoja.setText(objFun.getNome_loja());
                    jtGerenteDaLoja.setText(objFun.getGerente_loja());
                    jbEditar.setEnabled(true);
                    jbCancelar.setEnabled(true);
                    jbExcluir.setEnabled(true);
                    jbPesquisar.setEnabled(true);
                }
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(this, "Erro ao Pesquisar: " + erro.getMessage());
        }
    }//GEN-LAST:event_jbPesquisarActionPerformed

    private void jtTabelaLojaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtTabelaLojaMouseClicked
        if (jtTabelaLoja.getSelectedRow() != -1) {
            Object id0, nome1, endereco2, telefone3;

            id0 = jtTabelaLoja.getValueAt(jtTabelaLoja.getSelectedRow(), 0);
            String id = id0.toString();
            jtId.setText(id);

            nome1 = jtTabelaLoja.getValueAt(jtTabelaLoja.getSelectedRow(), 1);
            String nome = nome1.toString();
            jtNomeDaLoja.setText(nome);

            endereco2 = jtTabelaLoja.getValueAt(jtTabelaLoja.getSelectedRow(), 2);
            String endereco = endereco2.toString();
            jtNumeroDaLoja.setText(endereco);

            telefone3 = jtTabelaLoja.getValueAt(jtTabelaLoja.getSelectedRow(), 3);
            String telefone = telefone3.toString();
            jtGerenteDaLoja.setText(telefone);

            jbEditar.setEnabled(true);
            jbExcluir.setEnabled(true);
        }
    }//GEN-LAST:event_jtTabelaLojaMouseClicked

    private void jtTabelaLojaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtTabelaLojaKeyReleased
        if (jtTabelaLoja.getSelectedRow() != -1) {
            Object id0, nome_loja, cod_loja, gerente;

            id0 = jtTabelaLoja.getValueAt(jtTabelaLoja.getSelectedRow(), 0);
            String id = id0.toString();
            jtId.setText(id);

            nome_loja = jtTabelaLoja.getValueAt(jtTabelaLoja.getSelectedRow(), 1);
            String nome = nome_loja.toString();
            jtNomeDaLoja.setText(nome);

            cod_loja = jtTabelaLoja.getValueAt(jtTabelaLoja.getSelectedRow(), 2);
            String endereco = cod_loja.toString();
            jtNumeroDaLoja.setText(endereco);

            gerente = jtTabelaLoja.getValueAt(jtTabelaLoja.getSelectedRow(), 3);
            String telefone = gerente.toString();
            jtGerenteDaLoja.setText(telefone);

            jbEditar.setEnabled(true);
            jbExcluir.setEnabled(true);
        }
    }//GEN-LAST:event_jtTabelaLojaKeyReleased

    private void jbAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAtualizarActionPerformed
        try {
            preencheTabela();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Iniciar readTable." + ex.getMessage());
        }
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
    }//GEN-LAST:event_jbAtualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbAtualizar;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JButton jbNovo;
    private javax.swing.JButton jbPesquisar;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JTextField jtGerenteDaLoja;
    private javax.swing.JTextField jtId;
    private javax.swing.JTextField jtNomeDaLoja;
    private javax.swing.JTextField jtNumeroDaLoja;
    private javax.swing.JTable jtTabelaLoja;
    // End of variables declaration//GEN-END:variables
}
