package modulo.campanhas.relatorio;

import modulo.versao.Versao;
import java.awt.Dimension;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modulo.campanhas.meta.metaCampanha;
import modulo.campanhas.meta.MetasCampanhasDAO;
import modulo.campanhas.acompanhamento.CadastroCampanhaDia;
import modulo.campanhas.acompanhamento.JifAcompanhamentoCampanhas;
import static modulo.configuracoes.JifConfig.getProp;
import modulo.imprimePDF.PrintPdf;
import modulo.loja.LojaDAO;
import modulo.metodos.Funcao;
import modulo.usuarios.UsuarioDAO;

/**
 *
 * @author Marcos Junior
 */
public final class JifImprime extends javax.swing.JInternalFrame {

    private int numLoja;
    private String impressora = "prop.server.impressora";
    private String diretorioRel = "prop.server.diretorioRel";
    private Versao ver;
    private Funcao fun;
    private UsuarioDAO useDAO;
    private LojaDAO DAO;
    private dataFrames frams;
    private Properties properties;
    private geraRelatorio gera;
    private relatorioCampDAO DAOREL;
    private List<CadastroCampanhaDia> campDia;
    private MetasCampanhasDAO CADCAMP_DAO;
    int matricula;

    public JifImprime() {
        initComponents();
        ver = new Versao();
        properties = new Properties();
        setTitle("Relatório: " + ver.getVersao());
        fun = new Funcao();
        DAO = new LojaDAO();
        useDAO = new UsuarioDAO();
        gera = new geraRelatorio();
        DAOREL = new relatorioCampDAO();
        CADCAMP_DAO = new MetasCampanhasDAO();
        matricula = frams.getMatricula();
        CarregaLoja();

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

    public String CarregaDadosImpre() throws IOException {
        Properties prop = getProp();
        return prop.getProperty(impressora);
    }

    public String CarregaDadosDireto() throws IOException {
        Properties prop = getProp();
        return prop.getProperty(diretorioRel);
    }

    public int verificaMatricula() {

        return 0;
    }

    private List<String> listaCampanhasAtivas() {
        List<metaCampanha> CadCamp;
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

    private void imprimirRelatorio() throws IOException, PrinterException {
        PrintPdf.PrintPDF(CarregaDadosImpre(), CarregaDadosDireto(), CarregaDadosDireto(), "Termico");
    }

    public void gerarTextoLoja() {
        java.sql.Date dataInicio = frams.getDataInicio();
        java.sql.Date dataFim = frams.getDataFim();
        try {
            String cabecario = "\n\n\nRELATÓRIO DE CAMPANHAS LOJA: " + numLoja + "\n\n"
                    + "PERÍODO: " + fun.convertDataSQLToDateString(dataInicio)
                    + " a " + fun.convertDataSQLToDateString(dataFim) + "\n"
                    + "\nMATRÍCULA - QTD - CAMPANHA - DATA\n"
                    + "-----------------------------------------------------------------------------\n";
            jTextArea.insert(cabecario, jTextArea.getCaretPosition());
            listaCampanhasAtivas().forEach((a) -> {
                try {
                    campDia = DAOREL.TabelaPesquisaTodosCamp(a, dataInicio, dataFim);
                    campDia.forEach((p) -> {
                        try {
                            if (a.equals("ÚLTIMA CHANCE")) {
                                jTextArea.insert(String.format("%s - %s - %s - %s",
                                        Integer.toString(p.getMatricula()),
                                        fun.convertDoubleToStringMoeda(p.getUltimaChance()),
                                        p.getDesc_campanha(),
                                        fun.convertDataSQLToDateString(p.getData_registro())),
                                        jTextArea.getCaretPosition());
                                jTextArea.append("\n");
                            } else {
                                jTextArea.insert(String.format("%s - %s - %s - %s",
                                        Integer.toString(p.getMatricula()),
                                        p.getQuantidade(),
                                        p.getDesc_campanha(),
                                        fun.convertDataSQLToDateString(p.getData_registro())),
                                        jTextArea.getCaretPosition());
                                jTextArea.append("\n");
                            }

                        } catch (Exception ex) {
                            Logger.getLogger(JifImprime.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        jTextArea.append("\n");
                    });
                } catch (Exception ex) {
                    Logger.getLogger(JifImprime.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            String rodape = "-----------------------------------------------------------------------------"
                    + String.format("\n\n\n\nRESULTADO POR CAMPANHA.") + "\n\n\n";
            jTextArea.insert(rodape, jTextArea.getCaretPosition());
            listaCampanhasAtivas().forEach((c) -> {
                try {
                    if (c.equals("ÚLTIMA CHANCE")) {
                        jTextArea.insert(c + "  - TOTAL: " + fun.convertDoubleToStringMoeda(DAOREL.TabelaPesquisaRowsUltima(c, dataInicio, dataFim)), jTextArea.getCaretPosition());
                    } else {
                        jTextArea.insert(c + "  - TOTAL: " + DAOREL.TabelaPesquisaRowsCamp(c, dataInicio, dataFim), jTextArea.getCaretPosition());

                    }
                    jTextArea.append("\n-----------------------------------------------------------------------------\n");
                } catch (Exception ex) {
                    Logger.getLogger(JifImprime.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            jTextArea.insert("\n\nFINAL DO RELATÓRIO", jTextArea.getCaretPosition());
            gera.relatorioPDF(jTextArea.getText());

        } catch (Exception ex) {
            Logger.getLogger(JifImprime.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gerarTextoVendedor() {
        java.sql.Date dataInicio = frams.getDataInicio();
        java.sql.Date dataFim = frams.getDataFim();
        try {
            String cabecario = "\n\n\nRELATÓRIO DE CAMPANHAS LOJA: " + numLoja + "\n\n"
                    + "PERÍODO: " + fun.convertDataSQLToDateString(dataInicio)
                    + " a " + fun.convertDataSQLToDateString(dataFim) + "\n\n"
                    + "VENDEDOR: " + matricula + " - " + useDAO.PesquisaMatriculaR(matricula).getNome() + "\n"
                    + "\nMATRÍCULA - QTD - CAMPANHA - DATA\n"
                    + "-----------------------------------------------------------------------------\n";
            jTextArea.insert(cabecario, jTextArea.getCaretPosition());
            listaCampanhasAtivas().forEach((a) -> {
                try {
                    campDia = DAOREL.TabelaPesquisaTodosMatricula(a, dataInicio, dataFim, matricula);
                    campDia.forEach((p) -> {
                        try {
                            if (a.equals("ÚLTIMA CHANCE")) {
                                jTextArea.insert(String.format("%s - %s - %s - %s",
                                        Integer.toString(p.getMatricula()),
                                        fun.convertDoubleToStringMoeda(p.getUltimaChance()),
                                        p.getDesc_campanha(),
                                        fun.convertDataSQLToDateString(p.getData_registro())),
                                        jTextArea.getCaretPosition());
                                jTextArea.append("\n");
                            } else {
                                jTextArea.insert(String.format("%s - %s - %s - %s",
                                        Integer.toString(p.getMatricula()),
                                        p.getQuantidade(),
                                        p.getDesc_campanha(),
                                        fun.convertDataSQLToDateString(p.getData_registro())),
                                        jTextArea.getCaretPosition());
                                jTextArea.append("\n");
                            }

                        } catch (Exception ex) {
                            Logger.getLogger(JifImprime.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        jTextArea.append("\n");
                    });
                } catch (Exception ex) {
                    Logger.getLogger(JifImprime.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            String rodape = "-----------------------------------------------------------------------------"
                    + String.format("\n\n\n\nRESULTADO POR CAMPANHA.") + "\n\n\n";
            jTextArea.insert(rodape, jTextArea.getCaretPosition());
            listaCampanhasAtivas().forEach((c) -> {
                try {
                    if (c.equals("ÚLTIMA CHANCE")) {
                        jTextArea.insert(c + "  - TOTAL: " + fun.convertDoubleToStringMoeda(DAOREL.TabelaPesquisaRowsUltimaMatri(c, dataInicio, dataFim, matricula)), jTextArea.getCaretPosition());
                    } else {
                        jTextArea.insert(c + "  - TOTAL: " + DAOREL.TabelaPesquisaRowsCampMatri(c, dataInicio, dataFim, matricula), jTextArea.getCaretPosition());

                    }
                    jTextArea.append("\n-----------------------------------------------------------------------------\n");
                } catch (Exception ex) {
                    Logger.getLogger(JifImprime.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            jTextArea.insert("\n\nFINAL DO RELATÓRIO", jTextArea.getCaretPosition());
            gera.relatorioPDF(jTextArea.getText());

        } catch (Exception ex) {
            Logger.getLogger(JifImprime.class.getName()).log(Level.SEVERE, null, ex);
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
        jtInfo = new javax.swing.JTextField();

        setClosable(true);

        jPanel1.setBackground(new java.awt.Color(51, 255, 51));

        jbImprimir.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jbImprimir.setText("Gerar Dados");
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

        jTextArea.setEditable(false);
        jTextArea.setColumns(20);
        jTextArea.setRows(5);
        jTextArea.setEnabled(false);
        jScrollPane2.setViewportView(jTextArea);

        jtInfo.setEditable(false);
        jtInfo.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jtInfo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtInfo)
                .addGap(18, 18, 18)
                .addComponent(jbImprimir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbSair)
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbImprimir)
                    .addComponent(jbSair)
                    .addComponent(jtInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        if (jbImprimir.getText().equals("Gerar Dados")) {
            if (matricula == 0) {
                gerarTextoLoja();
            } else {
                gerarTextoVendedor();
            }
            jbImprimir.setText("Imprimir");
            jtInfo.setText("Dados Gerados com Sucesso!!");
        } else if (jbImprimir.getText().equals("Imprimir")) {
            try {
                imprimirRelatorio();
                jtInfo.setText("Aguarde Imprimindo!!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erro IOException: " + ex.getMessage());
            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(this, "Erro PrinterException: " + ex.getMessage());
            }
        }

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
    private javax.swing.JTextField jtInfo;
    // End of variables declaration//GEN-END:variables
}
