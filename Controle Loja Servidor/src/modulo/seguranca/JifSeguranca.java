package modulo.seguranca;

import modulo.versao.Versao;
import java.awt.Dimension;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modulo.login.UserLogado;
import modulo.metodos.ConvertMD5;
import modulo.metodos.Funcao;

/**
 *
 * @author Marcos Junior
 */
public class JifSeguranca extends javax.swing.JInternalFrame {

    private Versao ver;
    private Funcao fun;
    private ConvertMD5 MD5;
    private Seguranca s;
    private SegurancaDAO SEGDAO;
    private UserLogado login;

    public JifSeguranca() {
        initComponents();
        ver = new Versao();
        fun = new Funcao();
        MD5 = new ConvertMD5();
        SEGDAO = new SegurancaDAO();
        login = new UserLogado();
        CarregaDados();
        jfMatAutorizador.setText(login.getMatricula());
        DesabilitaEdicao();
    }

    private void CarregaDados() {
        setTitle("Painel de Liberação de Terminal: " + ver.getVersao());
        try {
            ListaPesquisa();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Pesquisa: " + ex.getMessage());
        }
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    private void HabilitaEdicao() {
        boolean l = true;
        jbGerarMD5.setEnabled(l);
        jbDesativar.setEnabled(l);
        jbAtivarTerminal.setEnabled(l);
        jbDesativar.setEnabled(l);
        jbSalvar.setEnabled(false);
        jfObs.setEnabled(l);
    }

    private void DesabilitaEdicao() {
        boolean l = false;
        jbGerarMD5.setEnabled(l);
        jbDesativar.setEnabled(l);
        jbAtivarTerminal.setEnabled(l);
        jbSalvar.setEnabled(false);
        jfObs.setEnabled(l);
        jfMD5Criado.setText("");
        jfMD5cli.setText("");
        jfObs.setText("");
        jfSerialCliente.setText("");
        jfStatus.setText("");
    }

    private void GerarMD5() {
        try {
            jfMD5Criado.setText(MD5.MD5String(jfSerialCliente.getText()));
            jbAtivarTerminal.setEnabled(true);
            jbDesativar.setEnabled(true);
            jbSalvar.setEnabled(true);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Converter em MD5: " + ex.getMessage());
        }
    }

    private void AtivarTerminal() {
        jfStatus.setText("ATIVADO");
        jbSalvar.setEnabled(true);
    }

    private void DesativarTerminal() {
        jfStatus.setText("DESATIVADO");
        jbSalvar.setEnabled(true);
    }

    private void Verifica() {
        switch (jfStatus.getText()) {
            case "ATIVADO":
                jbDesativar.setEnabled(true);
                jbAtivarTerminal.setEnabled(false);
                break;
            case "DESATIVADO":
                jbDesativar.setEnabled(false);
                jbAtivarTerminal.setEnabled(true);
                break;
            case "":
                jbDesativar.setEnabled(true);
                jbAtivarTerminal.setEnabled(true);
                break;
            default:
                break;
        }
    }

    private void CarregaDadosTabela() {

        if (jTabela1.getRowCount() >= 1) {
            if (jTabela1.getSelectedRow() != -1) {
                Object placaSerial, md5cli, status, aut, obs;
                
                placaSerial = jTabela1.getValueAt(jTabela1.getSelectedRow(), 0);
                if (placaSerial == null) {

                } else {
                    jfSerialCliente.setText(placaSerial.toString());
                }

                md5cli = jTabela1.getValueAt(jTabela1.getSelectedRow(), 1);
                if (md5cli == null) {

                } else {
                    jfMD5cli.setText(md5cli.toString());
                }

                status = jTabela1.getValueAt(jTabela1.getSelectedRow(), 2);
                if (status == null) {

                } else {
                    jfStatus.setText(status.toString());
                }

//                aut = jTabela1.getValueAt(jTabela1.getSelectedRow(), 3);
//                if (aut == null) {
//
//                } else {
//                    //jfMatAutorizador.setText(aut.toString());
//                   
//                }
                obs = jTabela1.getValueAt(jTabela1.getSelectedRow(), 4);
                if (obs == null) {

                } else {
                    jfObs.setText(obs.toString());
                }
                HabilitaEdicao();
                Verifica();
            }
        }
    }

    private void Salvar() {
        if (PreencheDadosSalvar()) {
            try {
                if (SEGDAO.Update(s)) {
                    JOptionPane.showMessageDialog(this, "Dados Salvo com Sucesso!!");
                    DesabilitaEdicao();
                    ListaPesquisa();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao Salvar os Dados: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Dados Preenchidos Incorretamente:\nCompos em Branco");
        }
    }

    private boolean PreencheDadosSalvar() {
        boolean check;
        s = new Seguranca();
        s.setAutorizador(Integer.parseInt(jfMatAutorizador.getText()));
        s.setDataRegistro(fun.atualDateSQL());
        s.setObs(jfObs.getText());
        if (!jfMD5Criado.getText().equals("")) {
            s.setMD5DeAutorizacao(jfMD5Criado.getText());
        } else {
            s.setMD5DeAutorizacao(jfMD5cli.getText());
        }
        s.setSeriralPlacaMae(jfSerialCliente.getText());
        s.setStatus(jfStatus.getText());
        check = s != null;
        return check;
    }

    private void ListaPesquisa() throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jTabela1.getModel();
        modelo.setNumRows(0);
        List<Seguranca> segu = SEGDAO.PesquisaGeral();
        segu.forEach((p) -> {
            try {
                modelo.addRow(new Object[]{
                    p.getSeriralPlacaMae(), p.getMD5DeAutorizacao(), p.getStatus(),
                    p.getAutorizador(), p.getObs(), fun.convertDataSQLToDateString(p.getDataRegistro())
                });
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao Pesquisar: " + ex.getMessage());
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jfMD5cli = new javax.swing.JFormattedTextField();
        jfStatus = new javax.swing.JFormattedTextField();
        jfMatAutorizador = new javax.swing.JFormattedTextField();
        jfMD5Criado = new javax.swing.JFormattedTextField();
        jfObs = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabela1 = new javax.swing.JTable();
        jbSalvar = new javax.swing.JButton();
        jbDesativar = new javax.swing.JButton();
        jbGerarMD5 = new javax.swing.JButton();
        jbAtivarTerminal = new javax.swing.JButton();
        jbCancelar = new javax.swing.JButton();
        jfSerialCliente = new javax.swing.JFormattedTextField();

        setClosable(true);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));

        jLabel1.setText("SERIAL CLIENTE");

        jLabel2.setText("MD5 CLIENTE");

        jLabel3.setText("STATUS");

        jLabel4.setText("AUTORIZADOR");

        jLabel5.setText("MD5 GERADO");

        jLabel6.setText("OBS");

        jfMD5cli.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfMD5cli.setEnabled(false);

        jfStatus.setEnabled(false);

        jfMatAutorizador.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfMatAutorizador.setEnabled(false);

        jfMD5Criado.setEnabled(false);

        jfObs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jfObsKeyPressed(evt);
            }
        });

        jTabela1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "SERIAL", "MD5 CLI", "STATUS", "AUTORIZADOR", "OBS", "Validado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTabela1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabela1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTabela1);
        if (jTabela1.getColumnModel().getColumnCount() > 0) {
            jTabela1.getColumnModel().getColumn(4).setMinWidth(200);
        }

        jbSalvar.setText("Salvar");
        jbSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvarActionPerformed(evt);
            }
        });

        jbDesativar.setText("Desativar");
        jbDesativar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDesativarActionPerformed(evt);
            }
        });

        jbGerarMD5.setText("Gerar MD5");
        jbGerarMD5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGerarMD5ActionPerformed(evt);
            }
        });

        jbAtivarTerminal.setText("Ativar Terminal");
        jbAtivarTerminal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAtivarTerminalActionPerformed(evt);
            }
        });

        jbCancelar.setBackground(new java.awt.Color(204, 255, 51));
        jbCancelar.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jbCancelar.setText("Cancelar");
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
            }
        });

        jfSerialCliente.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 886, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jfObs, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jfMatAutorizador)
                                    .addComponent(jfMD5cli, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jfStatus, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jfMD5Criado)
                                    .addComponent(jfSerialCliente))
                                .addGap(196, 196, 196)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jbDesativar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jbAtivarTerminal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jbGerarMD5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jbCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jbSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jbGerarMD5)
                    .addComponent(jfSerialCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jfMD5cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbAtivarTerminal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jfStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbDesativar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jfMD5Criado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbSalvar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jfMatAutorizador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbCancelar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jfObs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTabela1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabela1MouseClicked
        CarregaDadosTabela();
    }//GEN-LAST:event_jTabela1MouseClicked

    private void jbGerarMD5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGerarMD5ActionPerformed
        GerarMD5();
    }//GEN-LAST:event_jbGerarMD5ActionPerformed

    private void jbAtivarTerminalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAtivarTerminalActionPerformed
        AtivarTerminal();
    }//GEN-LAST:event_jbAtivarTerminalActionPerformed

    private void jbDesativarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDesativarActionPerformed
        DesativarTerminal();
    }//GEN-LAST:event_jbDesativarActionPerformed

    private void jbSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarActionPerformed
        Salvar();
    }//GEN-LAST:event_jbSalvarActionPerformed

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        DesabilitaEdicao();
    }//GEN-LAST:event_jbCancelarActionPerformed

    private void jfObsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jfObsKeyPressed
        jbSalvar.setEnabled(true);
    }//GEN-LAST:event_jfObsKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTabela1;
    private javax.swing.JButton jbAtivarTerminal;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbDesativar;
    private javax.swing.JButton jbGerarMD5;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JFormattedTextField jfMD5Criado;
    private javax.swing.JFormattedTextField jfMD5cli;
    private javax.swing.JFormattedTextField jfMatAutorizador;
    private javax.swing.JFormattedTextField jfObs;
    private javax.swing.JFormattedTextField jfSerialCliente;
    private javax.swing.JFormattedTextField jfStatus;
    // End of variables declaration//GEN-END:variables
}
