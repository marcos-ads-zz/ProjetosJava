package modulo.medias.aritimeticas;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modulo.base.dados.Base;
import modulo.base.dados.BaseDAO;
import modulo.metodos.MMC;

/**
 *
 * @author Marcos
 */
public final class JifMediaHarmonica extends javax.swing.JInternalFrame {

    private List<Base> base = null;
    private BaseDAO DAOBASE;
    private MMC mmc;

    public JifMediaHarmonica() {
        initComponents();
        DAOBASE = new BaseDAO();
        mmc = new MMC();
        setTitle("Média Harmonica v1.0");
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

    public List<Integer> ContaDadosNaTabela() {
        List<Integer> lista = new ArrayList<>();
        for (Base b : base) {
            lista.add(b.getDados());
        }
        Collections.sort(lista);
        return lista;
    }

    public List<Integer> Dados2() {
        List<Integer> lista;
        List<Integer> lista2 = new ArrayList<>();
        lista = ContaDadosNaTabela();

        int resultQtd = base.size();
        long g = mmc.carregaMMC(lista);
        int h = (int) g;
        String s = String.valueOf(g);

        jfMMC.setText(s);
        jfTotaldeNumeros.setText(Integer.toString(resultQtd));

        for (Integer i : lista) {
            lista2.add(h / i);
        }
        return lista2;
    }

    public int Dados3() {
        int result = 0;
        List<Integer> lista;
        lista = Dados2();
        for (Integer i : lista) {
            result += i;
        }
        return result;
    }

    public void CarregaDados() {
        jfSomaDeDados.setText(Integer.toString(Dados3()));
        //mmc * numerado Quantidade de numeros
        //Resultado acima dividido pela soma dos dados3
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
        jLabel5 = new javax.swing.JLabel();
        jfMMC = new javax.swing.JFormattedTextField();
        jfSomaDeDados = new javax.swing.JFormattedTextField();

        setClosable(true);
        setIconifiable(true);
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
        jLabel2.setText("MMC");

        jLabel3.setFont(new java.awt.Font("sansserif", 3, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Resultado da Média Harmônica");

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

        jLabel5.setFont(new java.awt.Font("sansserif", 3, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Soma dos Dados Divididos Pelo MMC");

        jfMMC.setEditable(false);
        jfMMC.setBackground(new java.awt.Color(255, 255, 51));
        jfMMC.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfMMC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfMMC.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N

        jfSomaDeDados.setEditable(false);
        jfSomaDeDados.setBackground(new java.awt.Color(255, 255, 51));
        jfSomaDeDados.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfSomaDeDados.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfSomaDeDados.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jfResultado)
                            .addComponent(jfTotaldeNumeros)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 558, Short.MAX_VALUE)
                                .addComponent(jbCalcular)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jbAtualizaTabela))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jfMMC)))
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jfSomaDeDados, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jfTotaldeNumeros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jfMMC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jfSomaDeDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jfResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbAtualizaTabela)
                    .addComponent(jbCalcular))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void jbAtualizaTabelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAtualizaTabelaActionPerformed
        PreencheTabelaDaView();
    }//GEN-LAST:event_jbAtualizaTabelaActionPerformed

    private void jbCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCalcularActionPerformed
        CarregaDados();
    }//GEN-LAST:event_jbCalcularActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTabelaBase;
    private javax.swing.JButton jbAtualizaTabela;
    private javax.swing.JButton jbCalcular;
    private javax.swing.JFormattedTextField jfMMC;
    private javax.swing.JFormattedTextField jfResultado;
    private javax.swing.JFormattedTextField jfSomaDeDados;
    private javax.swing.JFormattedTextField jfTotaldeNumeros;
    // End of variables declaration//GEN-END:variables
}
