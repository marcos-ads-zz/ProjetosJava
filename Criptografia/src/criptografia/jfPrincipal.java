package criptografia;

import javax.swing.JOptionPane;

/**
 *
 * @author Marcos Júnior <marcosneri@outlook.com.br>
 */
public class jfPrincipal extends javax.swing.JFrame {

    private CifraDeCesar fun;

    public jfPrincipal() {
        fun = new CifraDeCesar();
        initComponents();
    }

    private void infoSelecao() {
        jlInfo.setText(jComboBoxTipoCripto.getSelectedItem().toString());
    }

    private void limpar() {
        jlInfo.setText("Selecione um tipo de Cifra");
        jComboBoxTipoCripto.setSelectedIndex(0);
        jtAreaRecebidoDeDeJoao.setText("");
        jTextAreaInterceptDeJoao.setText("");
        jtAreaEnviadoDeJoao.setText("");
        jtAreaRecebidoDeMaria.setText("");
        jTextAreaInterceptDeMaria.setText("");
        jtAreaEnviadoDeMaria.setText("");
        jtChave.setText("");
    }

    //------------------------------------Java Cifra de Cesar-----------------------
    private String getTextoJoaoEncry() {
        return fun.encriptar(Integer.parseInt(jtChave.getText()), jtAreaEnviadoDeJoao.getText());
    }

    private String getTextoMariaEncry() {
        return fun.encriptar(Integer.parseInt(jtChave.getText()), jtAreaEnviadoDeMaria.getText());
    }
//---------------------------------------------------------------------------------------------------

    private String getTextoJoaoDcry() {
        return fun.decriptar(Integer.parseInt(jtChave.getText()), getTextoJoaoEncry());
    }

    private String getTextoMariaDcry() {
        return fun.decriptar(Integer.parseInt(jtChave.getText()), getTextoMariaEncry());
    }
    //------------------------------------Java Cifra de Cesar-----------------------

    private void enviaMaria() {
        int v = jComboBoxTipoCripto.getSelectedIndex();
        switch (v) {
            case 1:
                if (!jtChave.getText().equals("")) {

                    if (!jtAreaEnviadoDeMaria.getText().equals("")) {
                        jtAreaRecebidoDeDeJoao.insert(getTextoMariaDcry(), jtAreaRecebidoDeDeJoao.getCaretPosition());
                        jtAreaRecebidoDeDeJoao.append("\n");

                        jTextAreaInterceptDeJoao.insert(getTextoMariaEncry(), jTextAreaInterceptDeJoao.getCaretPosition());
                        jTextAreaInterceptDeJoao.append("\n");

                        jtAreaEnviadoDeMaria.setText("");
                    } else {
                        JOptionPane.showMessageDialog(this, "Informe uma Cifra.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Informe uma chave.");
                }
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }

    }

    private void enviaJoao() {
        int v = jComboBoxTipoCripto.getSelectedIndex();
        switch (v) {
            case 1:
                if (!jtChave.getText().equals("")) {
                    if (!jtAreaEnviadoDeJoao.getText().equals("")) {
                        jTextAreaInterceptDeMaria.insert(getTextoJoaoEncry(), jTextAreaInterceptDeMaria.getCaretPosition());
                        jTextAreaInterceptDeMaria.append("\n");

                        jtAreaRecebidoDeMaria.insert(getTextoJoaoDcry(), jtAreaRecebidoDeMaria.getCaretPosition());
                        jtAreaRecebidoDeMaria.append("\n");
                        jtAreaEnviadoDeJoao.setText("");
                    } else {
                        JOptionPane.showMessageDialog(this, "Informe uma Cifra.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Informe uma chave.");
                }

                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }

    }

    private void capturaCrip() {

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtAreaRecebidoDeDeJoao = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtAreaEnviadoDeMaria = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtAreaEnviadoDeJoao = new javax.swing.JTextArea();
        jbEnviarPorMaria = new javax.swing.JButton();
        jbEnviarPorJoao = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtAreaRecebidoDeMaria = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaInterceptDeJoao = new javax.swing.JTextArea();
        jComboBoxTipoCripto = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextAreaInterceptDeMaria = new javax.swing.JTextArea();
        jlInfo = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jtChave = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Teste de Criptografia");

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setFont(new java.awt.Font("sansserif", 3, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Messagem de Maria");

        jLabel2.setBackground(new java.awt.Color(255, 255, 102));
        jLabel2.setFont(new java.awt.Font("sansserif", 3, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Texto interceptado");

        jtAreaRecebidoDeDeJoao.setEditable(false);
        jtAreaRecebidoDeDeJoao.setBackground(new java.awt.Color(255, 255, 153));
        jtAreaRecebidoDeDeJoao.setColumns(20);
        jtAreaRecebidoDeDeJoao.setRows(5);
        jScrollPane1.setViewportView(jtAreaRecebidoDeDeJoao);

        jtAreaEnviadoDeMaria.setColumns(20);
        jtAreaEnviadoDeMaria.setRows(5);
        jScrollPane2.setViewportView(jtAreaEnviadoDeMaria);

        jtAreaEnviadoDeJoao.setColumns(20);
        jtAreaEnviadoDeJoao.setRows(5);
        jScrollPane3.setViewportView(jtAreaEnviadoDeJoao);

        jbEnviarPorMaria.setText("Enviar \"Maria\"");
        jbEnviarPorMaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEnviarPorMariaActionPerformed(evt);
            }
        });

        jbEnviarPorJoao.setText("Enviar \"João\"");
        jbEnviarPorJoao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEnviarPorJoaoActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("sansserif", 3, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Messagem Digitada por João");

        jLabel5.setFont(new java.awt.Font("sansserif", 3, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Messagem de João");

        jtAreaRecebidoDeMaria.setEditable(false);
        jtAreaRecebidoDeMaria.setBackground(new java.awt.Color(255, 255, 153));
        jtAreaRecebidoDeMaria.setColumns(20);
        jtAreaRecebidoDeMaria.setRows(5);
        jScrollPane4.setViewportView(jtAreaRecebidoDeMaria);

        jLabel6.setFont(new java.awt.Font("sansserif", 3, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Messagem Digitada por Maria");

        jTextAreaInterceptDeJoao.setEditable(false);
        jTextAreaInterceptDeJoao.setBackground(new java.awt.Color(51, 102, 255));
        jTextAreaInterceptDeJoao.setColumns(20);
        jTextAreaInterceptDeJoao.setRows(5);
        jScrollPane5.setViewportView(jTextAreaInterceptDeJoao);

        jComboBoxTipoCripto.setFont(new java.awt.Font("sansserif", 3, 14)); // NOI18N
        jComboBoxTipoCripto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o Tipo de Criptografia", "Java Cifra de Cesar", "Java Monoalfabética", "Java Polialfabética", "Base64", "AES", "MD5", "Blowfish" }));
        jComboBoxTipoCripto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoCriptoActionPerformed(evt);
            }
        });

        jButton1.setText("Limpar Tudo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 102));
        jLabel3.setFont(new java.awt.Font("sansserif", 3, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Texto interceptado");

        jTextAreaInterceptDeMaria.setEditable(false);
        jTextAreaInterceptDeMaria.setBackground(new java.awt.Color(204, 255, 204));
        jTextAreaInterceptDeMaria.setColumns(20);
        jTextAreaInterceptDeMaria.setRows(5);
        jScrollPane6.setViewportView(jTextAreaInterceptDeMaria);

        jlInfo.setFont(new java.awt.Font("sansserif", 3, 36)); // NOI18N
        jlInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlInfo.setText("Selecione um tipo de Criptografia!");

        jLabel8.setText("Chave");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jComboBoxTipoCripto, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtChave, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(174, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane4)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jScrollPane6))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jbEnviarPorJoao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbEnviarPorMaria))
                    .addComponent(jlInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxTipoCripto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jtChave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbEnviarPorMaria)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbEnviarPorJoao)
                        .addComponent(jButton1)))
                .addGap(61, 61, 61)
                .addComponent(jlInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
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

    private void jbEnviarPorMariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEnviarPorMariaActionPerformed
        enviaMaria();
    }//GEN-LAST:event_jbEnviarPorMariaActionPerformed

    private void jbEnviarPorJoaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEnviarPorJoaoActionPerformed
        enviaJoao();
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

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBoxTipoCripto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextArea jTextAreaInterceptDeJoao;
    private javax.swing.JTextArea jTextAreaInterceptDeMaria;
    private javax.swing.JButton jbEnviarPorJoao;
    private javax.swing.JButton jbEnviarPorMaria;
    private javax.swing.JLabel jlInfo;
    private javax.swing.JTextArea jtAreaEnviadoDeJoao;
    private javax.swing.JTextArea jtAreaEnviadoDeMaria;
    private javax.swing.JTextArea jtAreaRecebidoDeDeJoao;
    private javax.swing.JTextArea jtAreaRecebidoDeMaria;
    private javax.swing.JTextField jtChave;
    // End of variables declaration//GEN-END:variables
}
