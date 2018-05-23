package modulo.medias.aritimeticas;

import java.awt.Dimension;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modulo.base.dados.Base;
import modulo.base.dados.BaseDAO;
import modulo.metodos.Funcao;

/**
 *
 * @author Marcos
 */
public final class JifMediaGeometrica extends javax.swing.JInternalFrame {

    private List<Base> base = null;
    private BaseDAO DAOBASE;
    private Funcao fun;

    public JifMediaGeometrica() {
        initComponents();
        DAOBASE = new BaseDAO();
        fun = new Funcao();
        setTitle("Média Geométrica v1.0");
        PreencheTabelaDaView();
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    public List<Base> listarDados() {
        try {
            base = DAOBASE.Pesquisa();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listarDados! " + ex);
        }
        return base;
    }

    public BigInteger MultiplicaDados() {
        BigInteger bi, n = BigInteger.ONE;
        listarDados();
        for (Base v : base) {
            bi = BigInteger.valueOf(v.getDados());
            n = n.multiply(bi);
        }
        jtResultBig.setText(n.toString());
        return n;
    }

    public void CarregaDados() {
        int resultQtd = base.size();
        BigDecimal bi = BigDecimal.valueOf(resultQtd);
        BigDecimal bigdec = new BigDecimal(MultiplicaDados());
        //BigDecimal b = fun.powerBig(bigdec, bi);
        //System.out.println("Valorda multiplicação B " + bi + "\n" + bigdec);
        //jfResultado.setText(b.toPlainString());
        jfTotaldeNumeros.setText(Integer.toString(resultQtd));
    }

    public void PreencheTabelaDaView() {
        DefaultTableModel modelo = (DefaultTableModel) jTabelaBase.getModel();
        modelo.setNumRows(0);
        listarDados().forEach((p) -> {
            modelo.addRow(new Object[]{
                p.getDados()
            });
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabelaBase = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jfTotaldeNumeros = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jfResultado = new javax.swing.JFormattedTextField();
        jbAtualizaTabela = new javax.swing.JButton();
        jbCalcular = new javax.swing.JButton();
        jbTeste = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtResultBig = new javax.swing.JTextArea();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jTabelaBase.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DADOS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTabelaBase);

        jLabel1.setFont(new java.awt.Font("sansserif", 3, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Total de Números da Amostra");

        jfTotaldeNumeros.setEditable(false);
        jfTotaldeNumeros.setBackground(new java.awt.Color(102, 255, 204));
        jfTotaldeNumeros.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfTotaldeNumeros.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfTotaldeNumeros.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("sansserif", 3, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Resultado da Multiplicação dos Valores");

        jLabel3.setFont(new java.awt.Font("sansserif", 3, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Resultado da Média Aritimética");

        jfResultado.setEditable(false);
        jfResultado.setBackground(new java.awt.Color(0, 255, 102));
        jfResultado.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfResultado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfResultado.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N

        jbAtualizaTabela.setBackground(new java.awt.Color(102, 255, 102));
        jbAtualizaTabela.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jbAtualizaTabela.setText("Atualizar");
        jbAtualizaTabela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAtualizaTabelaActionPerformed(evt);
            }
        });

        jbCalcular.setBackground(new java.awt.Color(255, 0, 0));
        jbCalcular.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jbCalcular.setText("Calcular");
        jbCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCalcularActionPerformed(evt);
            }
        });

        jbTeste.setText("Teste");
        jbTeste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbTesteActionPerformed(evt);
            }
        });

        jtResultBig.setColumns(20);
        jtResultBig.setLineWrap(true);
        jtResultBig.setRows(5);
        jScrollPane2.setViewportView(jtResultBig);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jfTotaldeNumeros)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jfResultado)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(7, 7, 7))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jbCalcular)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbAtualizaTabela)
                            .addContainerGap()))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jbTeste)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 22, Short.MAX_VALUE)
                        .addComponent(jbTeste)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbAtualizaTabela)
                            .addComponent(jbCalcular))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jfTotaldeNumeros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jfResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbAtualizaTabelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAtualizaTabelaActionPerformed
        PreencheTabelaDaView();
    }//GEN-LAST:event_jbAtualizaTabelaActionPerformed

    private void jbCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCalcularActionPerformed
        CarregaDados();
    }//GEN-LAST:event_jbCalcularActionPerformed

    private void jbTesteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTesteActionPerformed
        MultiplicaDados();
    }//GEN-LAST:event_jbTesteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTabelaBase;
    private javax.swing.JButton jbAtualizaTabela;
    private javax.swing.JButton jbCalcular;
    private javax.swing.JButton jbTeste;
    private javax.swing.JFormattedTextField jfResultado;
    private javax.swing.JFormattedTextField jfTotaldeNumeros;
    private javax.swing.JTextArea jtResultBig;
    // End of variables declaration//GEN-END:variables
}
