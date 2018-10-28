package modulo.relatorio;

import modulo.versao.Versao;
import java.awt.Dimension;
import java.awt.print.PrinterException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import modulo.campanhas.meta.CadastroMetasCampanhas;
import modulo.campanhas.meta.CadastroMetasCampanhasDAO;
import modulo.campanhas.vendaD.CadastroCampanhaDia;
import modulo.campanhas.vendaD.JifAcompanhamentoCampanhas;
import modulo.imprimePDF.PrintPdf;
import modulo.loja.LojaDAO;
import modulo.metodos.Funcao;

/**
 *
 * @author Marcos Junior
 */
public final class JifTelaRelatorio extends javax.swing.JInternalFrame {

    private int numLoja;
    private Versao ver;
    private Funcao fun;
    private LojaDAO DAO;
    private geraRelatorio gera;
    private DefaultListModel dlm;
    private relatorioCampDAO DAOREL;
    private List<CadastroCampanhaDia> campDia;
    private CadastroMetasCampanhasDAO CADCAMP_DAO;

    public JifTelaRelatorio() {
        initComponents();
        ver = new Versao();
        fun = new Funcao();
        DAO = new LojaDAO();
        gera = new geraRelatorio();
        dlm = new DefaultListModel();
        DAOREL = new relatorioCampDAO();
        CADCAMP_DAO = new CadastroMetasCampanhasDAO();
        setTitle("Relatório: " + ver.getVersao());
        CarregaLoja();
        Texto(fun.atualDateSQL());

    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    private void CarregaLoja() {
        try {
            numLoja = DAO.PesquisaNumeroLoja(1).getNumero_loja();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Iniciar Loja!! \n Não Cadastrada.\n" + ex.getMessage());
        }
    }

    private boolean validadeCampanha(java.sql.Date data) throws Exception {
        String a, b;
        a = fun.convertDataSQLToDateString(data);
        b = fun.convertDataSQLToDateString(fun.atualDateSQL());
        return data.compareTo(fun.atualDateSQL()) == 1 || a.equals(b);
    }

    private List<String> listaCampanhasAtivas() {
        List<CadastroMetasCampanhas> CadCamp;
        List<String> t = new ArrayList<>();
        try {
            CadCamp = CADCAMP_DAO.TabelaPesquisaTodos();
            CadCamp.forEach((c) -> {
                try {
                    if (validadeCampanha(c.getData_fim())) {
                        t.add(c.getDescricao_Campanha());
                    }
                } catch (Exception ex) {
                    Logger.getLogger(JifAcompanhamentoCampanhas.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Pesquisar Campanhas! " + ex);
        }
        return t;
    }

    private void imprimirRelatorio() {
//        try {
//            PrintPdf.PrintPDF(impressora, Arquivo, Arquivo, opcao);
//        } catch (PrinterException ex) {
//            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
//        }
    }

    private void Texto(Date data) {
        try {

            jTextArea.insert("Relatório De Campanhas Loja: " + numLoja, jTextArea.getCaretPosition());
            jTextArea.append("\n");
            jTextArea.insert("Data: " + fun.convertDataSQLToDateString(data), jTextArea.getCaretPosition());
            jTextArea.append("\n\n");
            jTextArea.insert("Matricula Quantidade Campanha", jTextArea.getCaretPosition());
            jTextArea.append("\n");
            jTextArea.insert("-----------------------------------------------", jTextArea.getCaretPosition());
            jTextArea.append("\n");
            listaCampanhasAtivas().forEach((a) -> {
                try {
                    campDia = DAOREL.TabelaPesquisaTodosCamp(a, data);
                    campDia.forEach((p) -> {
                        try {
                            jTextArea.insert(String.format("%s - %s - %s - %s",
                                    Integer.toString(p.getMatricula()),
                                    p.getQuantidade(),
                                    p.getDesc_campanha(),
                                    fun.convertDataSQLToDateString(p.getData_registro())),
                                    jTextArea.getCaretPosition());
                        } catch (Exception ex) {
                            Logger.getLogger(JifTelaRelatorio.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        jTextArea.append("\n");
                    });

                } catch (Exception ex) {
                    Logger.getLogger(JifTelaRelatorio.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            jTextArea.append("\n");
            jTextArea.insert("-----------------------------------------------", jTextArea.getCaretPosition());
            jTextArea.append("\n");
            jTextArea.insert(String.format("Total por Campanha."), jTextArea.getCaretPosition());
            jTextArea.append("\n");
            listaCampanhasAtivas().forEach((c) -> {
                try {
                    jTextArea.insert(c + " - Total: " + DAOREL.TabelaPesquisaRowsCamp(c, data), jTextArea.getCaretPosition());
                    jTextArea.append("\n");
                } catch (Exception ex) {
                    Logger.getLogger(JifTelaRelatorio.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            jTextArea.append("\n");
            jTextArea.insert("Final do Relatório", jTextArea.getCaretPosition());
            jTextArea.append("\n");

            gera.relatorioPDF(jTextArea.getText());

        } catch (Exception ex) {
            Logger.getLogger(JifTelaRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jbImprimir = new javax.swing.JButton();
        jbSair = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();
        jpBarraDeProgresso = new javax.swing.JProgressBar();

        setClosable(true);

        jPanel1.setBackground(new java.awt.Color(51, 255, 51));

        jbImprimir.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jbImprimir.setText("Imprimir");
        jbImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbImprimirActionPerformed(evt);
            }
        });

        jbSair.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jbSair.setText("Sair");
        jbSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSairActionPerformed(evt);
            }
        });

        jTextArea.setColumns(20);
        jTextArea.setRows(5);
        jScrollPane2.setViewportView(jTextArea);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpBarraDeProgresso, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbImprimir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbSair)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbImprimir)
                    .addComponent(jbSair)
                    .addComponent(jpBarraDeProgresso, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbImprimirActionPerformed


    }//GEN-LAST:event_jbImprimirActionPerformed

    private void jbSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_jbSairActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea;
    private javax.swing.JButton jbImprimir;
    private javax.swing.JButton jbSair;
    private javax.swing.JProgressBar jpBarraDeProgresso;
    // End of variables declaration//GEN-END:variables
}
