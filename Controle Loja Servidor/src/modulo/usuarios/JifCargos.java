package modulo.usuarios;

import modulo.versao.Versao;
import java.awt.Dimension;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Marcos Junior
 */
public final class JifCargos extends javax.swing.JInternalFrame {

    private Versao ver;
    private CargosDAO DAOCARGO;
    private Cargos objCargo;

    public JifCargos() {
        initComponents();
        DAOCARGO = new CargosDAO();
        ver = new Versao();
        setTitle("Cadastro de Funções: " + ver.getVersao());
        try {
            PreencheTabelaDaView();
        } catch (Exception ex) {
            Logger.getLogger(JifCargos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    public void limparCampos() {
        jtId.setText("");
        jtDescricao.setText("");
        jtNivel.setSelectedIndex(0);
    }

    public void HabilitaEdicao() {
        jbSalvar.setEnabled(false);
        jbEditar.setEnabled(true);
        jbExcluir.setEnabled(true);
    }

    public void Desabilita() {
        jbSalvar.setEnabled(true);
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
    }

    public void Cancelar() {
        limparCampos();
        jtDescricao.setEnabled(true);
        jbSalvar.setEnabled(true);
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        jbCancelar.setEnabled(true);
    }

    public boolean validarCampos() {
        boolean check = true;
        if (jtDescricao.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Descrição!");
            check = false;
        }
        if (jtNivel.getSelectedItem() == "Selecione") {
            JOptionPane.showMessageDialog(this, "Selecione o Nivel!");
            check = false;
        }

        return check;
    }

    public boolean Salvar() throws Exception {
        return DAOCARGO.Insert(objCargo);
    }

    public boolean Editar() throws Exception {
        return DAOCARGO.Update(objCargo);
    }

    public boolean Exluir() throws Exception {
        return DAOCARGO.Delete(Integer.parseInt(jtId.getText()));
    }
    int cont = 0;

    public void Pesquisar() {
        List<Cargos> cliente;
        try {
            cliente = DAOCARGO.PesquisaNome(jtDescricao.getText().toUpperCase());
            PreencheTabelaDaViewNome(cliente);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro Pesquisa: " + ex.getMessage());
        }

    }

    public boolean preencherObjetosSalvar() throws ParseException {
        objCargo = new Cargos();
        objCargo.setCargos(jtDescricao.getText().toUpperCase());
        objCargo.setNivel(Integer.parseInt(jtNivel.getSelectedItem().toString()));
        return objCargo != null;
    }

    public boolean preencherObjetosEditar() {
        objCargo = new Cargos();
        objCargo.setId(Integer.parseInt(jtId.getText()));
        objCargo.setCargos(jtDescricao.getText().toUpperCase());
        objCargo.setNivel(Integer.parseInt(jtNivel.getSelectedItem().toString()));
        return objCargo != null;
    }

    public void PreencheTabelaDaView() throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jtTabela.getModel();
        modelo.setNumRows(0);
        List<Cargos> cargo = DAOCARGO.Pesquisa();
        cargo.forEach((p) -> {
            modelo.addRow(new Object[]{
                p.getId(), p.getCargos(), p.getNivel()
            });
        });
    }

    public void PreencheTabelaDaViewNome(List<Cargos> cargos) throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jtTabela.getModel();
        modelo.setNumRows(0);
        cargos.forEach((p) -> {
            modelo.addRow(new Object[]{
                p.getId(), p.getCargos(), p.getNivel()
            });
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtTabela = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtDescricao = new javax.swing.JTextField();
        jtId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtNivel = new javax.swing.JComboBox<>();
        jbSalvar = new javax.swing.JButton();
        jbEditar = new javax.swing.JButton();
        jbCancelar = new javax.swing.JButton();
        jbExcluir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(102, 255, 204));

        jtTabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "CARGO", "NIVEL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtTabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtTabelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtTabela);
        if (jtTabela.getColumnModel().getColumnCount() > 0) {
            jtTabela.getColumnModel().getColumn(0).setMinWidth(60);
            jtTabela.getColumnModel().getColumn(0).setMaxWidth(60);
            jtTabela.getColumnModel().getColumn(2).setMinWidth(90);
            jtTabela.getColumnModel().getColumn(2).setMaxWidth(90);
        }

        jLabel1.setText("ID");

        jLabel2.setText("CARGO");

        jtDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtDescricaoKeyPressed(evt);
            }
        });

        jtId.setEditable(false);
        jtId.setEnabled(false);

        jLabel3.setText("NIVEL");

        jtNivel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "1", "2", "3", "4", "5", "6", "7", "8", "9" }));

        jbSalvar.setText("Salvar");
        jbSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvarActionPerformed(evt);
            }
        });

        jbEditar.setText("Editar");
        jbEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditarActionPerformed(evt);
            }
        });

        jbCancelar.setText("Cancelar");
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
            }
        });

        jbExcluir.setText("Excluir");
        jbExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtId, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jbSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbExcluir)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jtNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbSalvar)
                    .addComponent(jbEditar)
                    .addComponent(jbCancelar)
                    .addComponent(jbExcluir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtDescricaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtDescricaoKeyPressed
        Pesquisar();
    }//GEN-LAST:event_jtDescricaoKeyPressed

    private void jtTabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtTabelaMouseClicked

        if (jtTabela.getSelectedRow() != -1) {
            Object id, descricao, nivel;

            id = jtTabela.getValueAt(jtTabela.getSelectedRow(), 0);
            jtId.setText(id.toString());

            descricao = jtTabela.getValueAt(jtTabela.getSelectedRow(), 1);
            jtDescricao.setText(descricao.toString());

            nivel = jtTabela.getValueAt(jtTabela.getSelectedRow(), 2);
            jtNivel.setSelectedItem(nivel.toString());
            HabilitaEdicao();
        }
    }//GEN-LAST:event_jtTabelaMouseClicked

    private void jbExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExcluirActionPerformed
        try {
            if (!jtId.getText().equals("")) {
                if (Exluir()) {
                    JOptionPane.showMessageDialog(this, "Registro Excluido com Sucesso");
                    Desabilita();
                    Cancelar();
                    PreencheTabelaDaView();
                } else {
                    JOptionPane.showMessageDialog(this, "Não Foi Possível Excluir o Registro");
                }
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(this, "Erro: " + erro.getMessage());
        }
    }//GEN-LAST:event_jbExcluirActionPerformed

    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarActionPerformed
        try {
            if (validarCampos() & !jtId.getText().equals("")) {
                if (preencherObjetosEditar()) {
                    if (Editar()) {
                        JOptionPane.showMessageDialog(this, "Editado Com Sucesso!");
                        Desabilita();
                        Pesquisar();
                        Cancelar();
                    } else {
                        JOptionPane.showMessageDialog(this, "Não Foi Possível Editar o Registro!");
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro!" + e.getMessage());
        }
    }//GEN-LAST:event_jbEditarActionPerformed

    private void jbSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarActionPerformed
        try {
            if (validarCampos()) {
                if (preencherObjetosSalvar()) {
                    if (Salvar()) {
                        JOptionPane.showMessageDialog(this, "Salvo Com Sucesso!");
                        Desabilita();
                        Cancelar();
                        PreencheTabelaDaView();
                    } else {
                        JOptionPane.showMessageDialog(this, "Não Foi Possível Salvar!");
                    }
                }
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(this, "Erro ao Salvar: " + erro.getMessage());
        }
    }//GEN-LAST:event_jbSalvarActionPerformed

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        Cancelar();
    }//GEN-LAST:event_jbCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JTextField jtDescricao;
    private javax.swing.JTextField jtId;
    private javax.swing.JComboBox<String> jtNivel;
    private javax.swing.JTable jtTabela;
    // End of variables declaration//GEN-END:variables
}
