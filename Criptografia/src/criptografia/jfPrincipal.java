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
        jtAreaRecebidoJoao.setText("");
        jTextAreaInterceptJoao.setText("");
        jtAreaEnviadoJoao.setText("");
        jtAreaRecebidoMaria.setText("");
        jTextAreaInterceptMaria.setText("");
        jtAreaEnviadoMaria.setText("");
        jtChave.setText("");
    }

    //------------------------------------Java Cifra de Cesar-----------------------
    private String getTextoJoaoEncry() {
        return fun.encriptar(Integer.parseInt(jtChave.getText()), jtAreaEnviadoJoao.getText());
    }

    private String getTextoMariaEncry() {
        return fun.encriptar(Integer.parseInt(jtChave.getText()), jtAreaEnviadoMaria.getText());
    }

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
                    if (!jtAreaEnviadoMaria.getText().equals("") & jtChave.getText().equals("")) {
                        jtAreaRecebidoJoao.insert(getTextoMariaDcry(), jtAreaRecebidoJoao.getCaretPosition());
                        jtAreaRecebidoJoao.append("\n");

                        jTextAreaInterceptJoao.insert(getTextoMariaEncry(), jTextAreaInterceptJoao.getCaretPosition());
                        jTextAreaInterceptJoao.append("\n");

                        jtAreaEnviadoMaria.setText("");
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
                    if (!jtAreaEnviadoJoao.getText().equals("")) {
                        jtAreaRecebidoMaria.insert(getTextoJoaoDcry(), jtAreaRecebidoMaria.getCaretPosition());
                        jtAreaRecebidoMaria.append("\n");

                        jTextAreaInterceptMaria.insert(getTextoJoaoEncry(), jTextAreaInterceptMaria.getCaretPosition());
                        jTextAreaInterceptMaria.append("\n");

                        jtAreaEnviadoJoao.setText("");
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
        jtAreaRecebidoJoao = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtAreaEnviadoMaria = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtAreaEnviadoJoao = new javax.swing.JTextArea();
        jbEnviarPorMaria = new javax.swing.JButton();
        jbEnviarPorJoao = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtAreaRecebidoMaria = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaInterceptJoao = new javax.swing.JTextArea();
        jComboBoxTipoCripto = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextAreaInterceptMaria = new javax.swing.JTextArea();
        jlInfo = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jtChave = new javax.swing.JTextField();

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

        jtAreaRecebidoJoao.setEditable(false);
        jtAreaRecebidoJoao.setBackground(new java.awt.Color(255, 255, 153));
        jtAreaRecebidoJoao.setColumns(20);
        jtAreaRecebidoJoao.setRows(5);
        jScrollPane1.setViewportView(jtAreaRecebidoJoao);

        jtAreaEnviadoMaria.setColumns(20);
        jtAreaEnviadoMaria.setRows(5);
        jScrollPane2.setViewportView(jtAreaEnviadoMaria);

        jtAreaEnviadoJoao.setColumns(20);
        jtAreaEnviadoJoao.setRows(5);
        jScrollPane3.setViewportView(jtAreaEnviadoJoao);

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

        jtAreaRecebidoMaria.setEditable(false);
        jtAreaRecebidoMaria.setBackground(new java.awt.Color(255, 255, 153));
        jtAreaRecebidoMaria.setColumns(20);
        jtAreaRecebidoMaria.setRows(5);
        jScrollPane4.setViewportView(jtAreaRecebidoMaria);

        jLabel6.setFont(new java.awt.Font("sansserif", 3, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Messagem Digitada por Maria");

        jTextAreaInterceptJoao.setEditable(false);
        jTextAreaInterceptJoao.setBackground(new java.awt.Color(51, 102, 255));
        jTextAreaInterceptJoao.setColumns(20);
        jTextAreaInterceptJoao.setRows(5);
        jScrollPane5.setViewportView(jTextAreaInterceptJoao);

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

        jTextAreaInterceptMaria.setEditable(false);
        jTextAreaInterceptMaria.setBackground(new java.awt.Color(51, 102, 255));
        jTextAreaInterceptMaria.setColumns(20);
        jTextAreaInterceptMaria.setRows(5);
        jScrollPane6.setViewportView(jTextAreaInterceptMaria);

        jlInfo.setFont(new java.awt.Font("sansserif", 3, 36)); // NOI18N
        jlInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlInfo.setText("Selecione um tipo de Criptografia!");

        jLabel8.setText("Chave");

        jtChave.setBackground(new java.awt.Color(102, 255, 204));
        jtChave.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jtChave.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                    .addComponent(jlInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jComboBoxTipoCripto, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtChave, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(174, Short.MAX_VALUE))
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
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbEnviarPorMaria)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbEnviarPorJoao)
                        .addComponent(jButton1)))
                .addGap(61, 61, 61)
                .addComponent(jlInfo)
                .addGap(29, 29, 29))
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

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
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
    private javax.swing.JTextArea jTextAreaInterceptJoao;
    private javax.swing.JTextArea jTextAreaInterceptMaria;
    private javax.swing.JButton jbEnviarPorJoao;
    private javax.swing.JButton jbEnviarPorMaria;
    private javax.swing.JLabel jlInfo;
    private javax.swing.JTextArea jtAreaEnviadoJoao;
    private javax.swing.JTextArea jtAreaEnviadoMaria;
    private javax.swing.JTextArea jtAreaRecebidoJoao;
    private javax.swing.JTextArea jtAreaRecebidoMaria;
    private javax.swing.JTextField jtChave;
    // End of variables declaration//GEN-END:variables
}
