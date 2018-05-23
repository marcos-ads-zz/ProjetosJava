package modulo.base.dados;

import java.awt.Dimension;
import java.text.ParseException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modulo.metodos.Funcao;

/**
 *
 * @author Marcos
 */
public final class JifBaseDados extends javax.swing.JInternalFrame {

    private Base objBase;
    private BaseDAO DAOBASE;
    private Funcao fun;

    public JifBaseDados() {
        initComponents();
        DAOBASE = new BaseDAO();
        fun = new Funcao();
        setTitle("Base de Dados v1.0");
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    public void limparCampos() {
        jfId.setText("");
        jfDados.setText("");
        jfPeso.setText("");
        jfValor0.setText("");
        jfValor1.setText("");
        jfValor2.setText("");
        jfValor3.setText("");
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
        jbSalvar.setEnabled(true);
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        jbCancelar.setEnabled(true);
    }

    public boolean validarCampos() {
        boolean check = true;
        if (jfDados.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Valor do Dado!");
            check = false;
        }
        if (jfPeso.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Peso!");
            check = false;
        }
        return check;
    }

    public void CarregarEditar() {
        if (jTabelaBase.getSelectedRow() != -1) {
            Object id, dados, peso, valor0, valor1, valor2, valor3;

            id = jTabelaBase.getValueAt(jTabelaBase.getSelectedRow(), 0);
            jfId.setText(id.toString());

            dados = jTabelaBase.getValueAt(jTabelaBase.getSelectedRow(), 1);
            jfDados.setText(dados.toString());

            peso = jTabelaBase.getValueAt(jTabelaBase.getSelectedRow(), 2);
            jfPeso.setText(peso.toString());

            valor0 = jTabelaBase.getValueAt(jTabelaBase.getSelectedRow(), 3);
            jfValor0.setText(valor0.toString());

            valor1 = jTabelaBase.getValueAt(jTabelaBase.getSelectedRow(), 4);
            jfValor1.setText(valor1.toString());

            valor2 = jTabelaBase.getValueAt(jTabelaBase.getSelectedRow(), 5);
            jfValor2.setText(valor2.toString());

            valor3 = jTabelaBase.getValueAt(jTabelaBase.getSelectedRow(), 6);
            jfValor3.setText(valor3.toString());
            HabilitaEdicao();
        }
    }

    public boolean Salvar() throws Exception {
        return DAOBASE.Insert(objBase);
    }

    public boolean Editar() throws Exception {
        return DAOBASE.Update(objBase);
    }

    public boolean Exluir() throws Exception {
        return DAOBASE.Delete(Integer.parseInt(jfId.getText()));
    }
    int cont = 0;

    public void Pesquisar() {
        List<Base> base;
        try {
            base = DAOBASE.PesquisaValor(fun.convertToInt(jfDados.getText()));
            PreencheTabelaDaViewNome(base);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro Pesquisa: " + ex.getMessage());
        }

    }

    public boolean preencherObjetosSalvar() throws ParseException {
        objBase = new Base();
        objBase.setDados(fun.convertToInt(jfDados.getText()));
        objBase.setPeso(fun.convertToInt(jfPeso.getText()));
        objBase.setValor0(fun.convertToInt(jfValor0.getText()));
        objBase.setValor1(fun.convertToInt(jfValor1.getText()));
        objBase.setValor2(fun.convertToInt(jfValor2.getText()));
        objBase.setValor3(fun.convertToInt(jfValor3.getText()));
        return objBase != null;
    }

    public boolean preencherObjetosEditar() {
        objBase = new Base();
        objBase.setId(fun.convertToInt(jfId.getText()));
        objBase.setDados(fun.convertToInt(jfDados.getText()));
        objBase.setPeso(fun.convertToInt(jfPeso.getText()));
        objBase.setValor0(fun.convertToInt(jfValor0.getText()));
        objBase.setValor1(fun.convertToInt(jfValor1.getText()));
        objBase.setValor2(fun.convertToInt(jfValor2.getText()));
        objBase.setValor3(fun.convertToInt(jfValor3.getText()));
        return objBase != null;
    }

    public void PreencheTabelaDaView() throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jTabelaBase.getModel();
        modelo.setNumRows(0);
        List<Base> base = DAOBASE.Pesquisa();
        base.forEach((p) -> {
            modelo.addRow(new Object[]{
                p.getId(), p.getDados(), p.getPeso(),
                p.getValor0(), p.getValor1(), p.getValor2(),
                p.getValor3()
            });
        });
    }

    public void PreencheTabelaDaViewNome(List<Base> cargos) throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jTabelaBase.getModel();
        modelo.setNumRows(0);
        cargos.forEach((p) -> {
            modelo.addRow(new Object[]{
                p.getId(), p.getDados(), p.getPeso(),
                p.getValor0(), p.getValor1(), p.getValor2(),
                p.getValor3()
            });
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabelaBase = new javax.swing.JTable();
        jbListar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jfId = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jfDados = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jfPeso = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jfValor0 = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jfValor1 = new javax.swing.JFormattedTextField();
        jfValor2 = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jfValor3 = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jbExcluir = new javax.swing.JButton();
        jbEditar = new javax.swing.JButton();
        jbSalvar = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jbCancelar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(102, 255, 204));

        jTabelaBase.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "VALOR_A", "VALOR_B", "VALOR_0", "VALOR_1", "VALOR_2", "VALOR_3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTabelaBase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabelaBaseMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTabelaBase);
        if (jTabelaBase.getColumnModel().getColumnCount() > 0) {
            jTabelaBase.getColumnModel().getColumn(0).setMaxWidth(60);
        }

        jbListar.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jbListar.setText("Listar Todos");
        jbListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbListarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ID");

        jfId.setEditable(false);
        jfId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfId.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Valor Dados");

        jfDados.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfDados.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Valor Peso");

        jfPeso.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfPeso.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Valor_0");

        jfValor0.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfValor0.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Valor_1");

        jfValor1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfValor1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jfValor2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfValor2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Valor_2");

        jfValor3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfValor3.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel7.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Valor_3");

        jbExcluir.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jbExcluir.setText("Excluir");
        jbExcluir.setEnabled(false);
        jbExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExcluirActionPerformed(evt);
            }
        });

        jbEditar.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jbEditar.setText("Editar");
        jbEditar.setEnabled(false);
        jbEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditarActionPerformed(evt);
            }
        });

        jbSalvar.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jbSalvar.setText("Salvar");
        jbSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvarActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(102, 255, 51));
        jButton5.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jButton5.setText("Carregar Dados");

        jbCancelar.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jbCancelar.setText("Cancelar");
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbListar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jfId)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jfDados)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jfPeso)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jfValor0)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jfValor1)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jfValor2)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jfValor3)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jfDados, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jfPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jfValor0, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jfValor1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jfValor2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jfValor3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbListar)
                    .addComponent(jbExcluir)
                    .addComponent(jbEditar)
                    .addComponent(jbSalvar)
                    .addComponent(jbCancelar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTabelaBaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabelaBaseMouseClicked
        CarregarEditar();
    }//GEN-LAST:event_jTabelaBaseMouseClicked

    private void jbSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarActionPerformed
        try {
            Salvar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Salvar! " + ex);
        }
    }//GEN-LAST:event_jbSalvarActionPerformed

    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarActionPerformed
        try {
            Editar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Editar! " + ex);
        }
    }//GEN-LAST:event_jbEditarActionPerformed

    private void jbExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExcluirActionPerformed
        try {
            Exluir();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Exluir! " + ex);
        }
    }//GEN-LAST:event_jbExcluirActionPerformed

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        Cancelar();
    }//GEN-LAST:event_jbCancelarActionPerformed

    private void jbListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbListarActionPerformed
        try {
            PreencheTabelaDaView();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao PreencheTabelaDaView! " + ex);
        }
    }//GEN-LAST:event_jbListarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTabelaBase;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JButton jbListar;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JFormattedTextField jfDados;
    private javax.swing.JFormattedTextField jfId;
    private javax.swing.JFormattedTextField jfPeso;
    private javax.swing.JFormattedTextField jfValor0;
    private javax.swing.JFormattedTextField jfValor1;
    private javax.swing.JFormattedTextField jfValor2;
    private javax.swing.JFormattedTextField jfValor3;
    // End of variables declaration//GEN-END:variables
}
