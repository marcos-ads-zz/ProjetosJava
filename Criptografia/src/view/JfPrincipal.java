package view;

import cifra.cesar.CifraDeCesar;
import cifra.monoalfabetica.CifraDeCesarMono;
import cifra.vigenere.cifraPoliVigenere;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos Júnior <marcosneri@outlook.com.br>
 */
public class JfPrincipal extends javax.swing.JFrame {

    private CifraDeCesar cifra;
    private CifraDeCesarMono cifraMono;

    public JfPrincipal() {
        initComponents();
        cifra = new CifraDeCesar();
        cifraMono = new CifraDeCesarMono();
        jcChavePoli.setVisible(false);
        jtChave.setVisible(false);
        jlTexto.setVisible(false);
        jbDecrypt.setVisible(false);
    }

    private void infoSelecao() {
        jlInfo.setText(jComboBoxTipoCripto.getSelectedItem().toString());
        int v = jComboBoxTipoCripto.getSelectedIndex();
        switch (v) {
            case 0:
                jtChave.setVisible(false);
                jlTexto.setVisible(false);
                jcChavePoli.setVisible(false);
                jbDecrypt.setVisible(false);
                break;
            case 1:
                jtChave.setVisible(true);
                jlTexto.setVisible(true);
                jcChavePoli.setVisible(false);
                jbDecrypt.setVisible(false);
                break;
            case 2:
                jtChave.setText("");
                jtChave.setVisible(false);
                jlTexto.setVisible(false);
                jcChavePoli.setVisible(false);
                jbDecrypt.setVisible(false);
                break;
            case 3:
                jtChave.setText("");
                jtChave.setVisible(false);
                jlTexto.setVisible(false);
                jcChavePoli.setVisible(true);
                jbDecrypt.setVisible(true);
                break;
            case 4:

                break;
            default:
                break;
        }
    }

    private void limpar() {
        jlInfo.setText("Selecione um tipo de Cifra");
        jComboBoxTipoCripto.setSelectedIndex(0);
        jtAreaDescript.setText("");
        jTextAreaEncri.setText("");
        jtAreaDigitacao.setText("");
        jtChave.setText("");
    }

//------------------------------------Java Cifra de Cesar----------------------------------------------
    private String getTextoEncry() {
        return cifra.encriptar(Integer.parseInt(jtChave.getText()), jtAreaDigitacao.getText());
    }

    private String getTextoDecry() {
        return cifra.decriptar(Integer.parseInt(jtChave.getText()), getTextoEncry());
    }

//-------------------------------------------Fim--------------------------------------------------------
//------------------------------------Java Cifra de Cesar Mono------------------------------------------
    private String getTextoEncryMono() {
        return cifraMono.encriptarMono(jtAreaDigitacao.getText());
    }

    private String getTextoDecryMono() {
        return cifraMono.decriptarMono(jTextAreaEncri.getText());
    }
//------------------------------------Java Cifra de Cesar Mono Fim--------------------------------------
//------------------------------------Java Cifra de Cesar Poli------------------------------------------

    private void getTextoCifradoPoli() {
        if (jcChavePoli.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma Chave.");
        } else {
            cifraPoliVigenere cifaPoli = new cifraPoliVigenere(jtAreaDigitacao.getText(), jcChavePoli.getSelectedItem().toString().toLowerCase());
            jTextAreaEncri.insert(cifaPoli.Cifrar(), jTextAreaEncri.getCaretPosition());
            jtAreaDigitacao.setText("");
        }
    }

    private void getTextDecifradoPoli() {
        if (jcChavePoli.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma Chave.");
        } else {
            cifraPoliVigenere cifaPoli2 = new cifraPoliVigenere(jTextAreaEncri.getText(), jcChavePoli.getSelectedItem().toString().toLowerCase());
            jtAreaDescript.insert(cifaPoli2.DesCifrar(), jtAreaDescript.getCaretPosition());
        }
    }
//------------------------------------Java Cifra de Cesar Poli Fim--------------------------------------

    private void enviar() {
        jtAreaDescript.setText("");
        jTextAreaEncri.setText("");
        int v = jComboBoxTipoCripto.getSelectedIndex();
        switch (v) {
            case 1:
                if (!jtChave.getText().equals("")) {
                    if (!jtAreaDigitacao.getText().equals("")) {
                        jTextAreaEncri.insert(getTextoEncry(), jTextAreaEncri.getCaretPosition());
                        jtAreaDescript.insert(getTextoDecry(), jtAreaDescript.getCaretPosition());
                        jtAreaDigitacao.setText("");
                    } else {
                        JOptionPane.showMessageDialog(this, "Informe uma Cifra.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Informe uma chave.");
                }

                break;
            case 2:
                if (!jtAreaDigitacao.getText().equals("")) {
                    jTextAreaEncri.insert(getTextoEncryMono(), jTextAreaEncri.getCaretPosition());
                    jtAreaDescript.insert(getTextoDecryMono(), jtAreaDescript.getCaretPosition());
                    jtAreaDigitacao.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Informe um Texto a ser Cifrado.");
                }
                break;
            case 3:
                if (!jtAreaDigitacao.getText().equals("")) {
                    getTextoCifradoPoli();
                } else {
                    JOptionPane.showMessageDialog(this, "Informe um Texto a ser Cifrado.");
                }
                break;
            default:
                JOptionPane.showMessageDialog(this, "Selecione um tipo de cifra.");
                break;
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtAreaDescript = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtAreaDigitacao = new javax.swing.JTextArea();
        jbEnviarPorJoao = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaEncri = new javax.swing.JTextArea();
        jComboBoxTipoCripto = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jlInfo = new javax.swing.JLabel();
        jlTexto = new javax.swing.JLabel();
        jtChave = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jbConfigurar = new javax.swing.JButton();
        jcChavePoli = new javax.swing.JComboBox<>();
        jbDecrypt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Teste de Criptografia");

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));

        jLabel1.setFont(new java.awt.Font("sansserif", 3, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Menssagem Descriptografada");

        jLabel2.setBackground(new java.awt.Color(255, 255, 102));
        jLabel2.setFont(new java.awt.Font("sansserif", 3, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Menssagem criptografada");

        jtAreaDescript.setEditable(false);
        jtAreaDescript.setBackground(new java.awt.Color(255, 255, 153));
        jtAreaDescript.setColumns(20);
        jtAreaDescript.setRows(5);
        jScrollPane1.setViewportView(jtAreaDescript);

        jtAreaDigitacao.setColumns(20);
        jtAreaDigitacao.setRows(5);
        jScrollPane3.setViewportView(jtAreaDigitacao);

        jbEnviarPorJoao.setBackground(new java.awt.Color(0, 153, 0));
        jbEnviarPorJoao.setFont(new java.awt.Font("sansserif", 3, 14)); // NOI18N
        jbEnviarPorJoao.setText("Encripta");
        jbEnviarPorJoao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEnviarPorJoaoActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("sansserif", 3, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Messagem Digitada!");

        jTextAreaEncri.setEditable(false);
        jTextAreaEncri.setBackground(new java.awt.Color(153, 255, 153));
        jTextAreaEncri.setColumns(20);
        jTextAreaEncri.setRows(5);
        jScrollPane5.setViewportView(jTextAreaEncri);

        jComboBoxTipoCripto.setFont(new java.awt.Font("sansserif", 3, 14)); // NOI18N
        jComboBoxTipoCripto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o Tipo de Criptografia", "Cifra de Cesar Simples", "Cifra Monoalfabética", "Cifra Polialfabética Vigenère", "Base64", "AES", "MD5", "Blowfish" }));
        jComboBoxTipoCripto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoCriptoActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("sansserif", 3, 14)); // NOI18N
        jButton1.setText("Limpar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jlInfo.setFont(new java.awt.Font("sansserif", 3, 36)); // NOI18N
        jlInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlInfo.setText("Selecione um tipo de Criptografia!");

        jlTexto.setText("Chave");

        jtChave.setBackground(new java.awt.Color(102, 255, 204));
        jtChave.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jtChave.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jButton2.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jButton2.setText("Sair");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jbConfigurar.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jbConfigurar.setText("Configurar Base");
        jbConfigurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbConfigurarActionPerformed(evt);
            }
        });

        jcChavePoli.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jcChavePoli.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione uma Chave", "LIMAO", "MARACUJA", "COPO", "FRUTA" }));

        jbDecrypt.setBackground(new java.awt.Color(255, 51, 51));
        jbDecrypt.setFont(new java.awt.Font("sansserif", 3, 14)); // NOI18N
        jbDecrypt.setText("Decrypt");
        jbDecrypt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDecryptActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(jlInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane5)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbEnviarPorJoao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbDecrypt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBoxTipoCripto, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jlTexto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtChave, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jbConfigurar)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jcChavePoli, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbEnviarPorJoao)
                    .addComponent(jButton1)
                    .addComponent(jbDecrypt))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxTipoCripto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlTexto)
                    .addComponent(jtChave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcChavePoli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbConfigurar)
                    .addComponent(jButton2))
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbEnviarPorJoaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEnviarPorJoaoActionPerformed
        enviar();
    }//GEN-LAST:event_jbEnviarPorJoaoActionPerformed

    private void jComboBoxTipoCriptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoCriptoActionPerformed
        infoSelecao();
    }//GEN-LAST:event_jComboBoxTipoCriptoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        limpar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jbConfigurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbConfigurarActionPerformed
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfConfig.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new JfConfig().setVisible(true);
        });
    }//GEN-LAST:event_jbConfigurarActionPerformed

    private void jbDecryptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDecryptActionPerformed
        getTextDecifradoPoli();
    }//GEN-LAST:event_jbDecryptActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBoxTipoCripto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jTextAreaEncri;
    private javax.swing.JButton jbConfigurar;
    private javax.swing.JButton jbDecrypt;
    private javax.swing.JButton jbEnviarPorJoao;
    private javax.swing.JComboBox<String> jcChavePoli;
    private javax.swing.JLabel jlInfo;
    private javax.swing.JLabel jlTexto;
    private javax.swing.JTextArea jtAreaDescript;
    private javax.swing.JTextArea jtAreaDigitacao;
    private javax.swing.JTextField jtChave;
    // End of variables declaration//GEN-END:variables
}
