package modulo.imprimePDF;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import static java.lang.Thread.sleep;
import javax.print.PrintService;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.ImageIcon;
import modulo.versao.Versao;
import modulo.view.painel.jfCarregando;

/**
 *
 * @author Marcos Junior
 */
public final class JTelaPrintPDF extends javax.swing.JFrame {

    private String impressora = null;
    //private String impressoraLocal = "SWEDA SI-300S";
    private String Arquivo = null;
    private String opcao;
    private Versao ver = new Versao();
    //private boolean chek;
    private String Pasta = "C:\\PmenosF\\ArquivosPDF";
    private int quantidade = 0;
    private int cont = 0, cont2 = 0, reg = 0;

    public JTelaPrintPDF() {
        initComponents();
        setTitle("Sistema de Impressão Simples: " + ver.getVersao());
        DefaultTableModel modelo = (DefaultTableModel) jTableArquivos.getModel();
        jTableArquivos.setRowSorter(new TableRowSorter(modelo));
        DefaultTableModel modelo2 = (DefaultTableModel) jTableImpressora.getModel();
        jTableImpressora.setRowSorter(new TableRowSorter(modelo2));
        QuantidadedeImpressoes();
        jLStatus.setText("Sistema Pronto!!");
        ClockRunnable();
    }

    private void QuantidadedeImpressoes() {
        new Thread() {
            @Override
            public void run() {
                ListaArquivos();
                listarImpressorasDisponíveis();
                try {
                    sleep(5000);
                } catch (InterruptedException ex) {
                    jLStatus.setText("Erro no Sleep: " + ex.getMessage());
                }
            }
        }.start();
    }

    private void Imprimir() throws InterruptedException {
        if (jTquantidade.getText() == null || jTquantidade.getText().trim().equals("")) {
        } else {
            quantidade = Integer.parseInt(jTquantidade.getText());
        }

        if (quantidade == 0) {
            if (jTImpressoraSelecionada.getText() == null || jTImpressoraSelecionada.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Selecione Uma Impressora.");
            } else if (jTArquivoSelecionado.getText() == null || jTArquivoSelecionado.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Selecione Um Arquivo");
            } else {
                try {
                    Carregar();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
                }
            }
        } else {
            if (jTImpressoraSelecionada.getText() == null || jTImpressoraSelecionada.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Selecione Uma Impressora.");
            } else if (jTArquivoSelecionado.getText() == null || jTArquivoSelecionado.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Selecione Um Arquivo");
            } else {
                for (int i = 1; i <= quantidade; i++) {
                    try {
                        jLReg.setText(Integer.toString(quantidade - i));
                        sleep(1000);
                        Carregar();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
                    } catch (InterruptedException ex) {
                        JOptionPane.showMessageDialog(this, "Erro Sleep: " + ex.getMessage());
                    }
                }
            }
        }
        jLStatus.setText("Processo Finalizado.");
        jLStatus.setForeground(Color.green);
    }

    private void ListaArquivos() {
        File file = new File(Pasta);
        String local = file.getPath();
        DefaultTableModel modelo = (DefaultTableModel) jTableArquivos.getModel();
        modelo.setNumRows(0);
        if (file.exists()) {
            for (String p : file.list()) {
                cont++;
                modelo.addRow(new String[]{
                    Integer.toString(cont), local.concat("\\" + p)
                });
            }
        } else {
            jTArquivoSelecionado.setText("Sem Arquivos Disponíveis!");
        }

    }

    private void listarImpressorasDisponíveis() {
        PrintService[] pservices = PrinterJob.lookupPrintServices();
        DefaultTableModel modelo = (DefaultTableModel) jTableImpressora.getModel();
        modelo.setNumRows(0);
        if (pservices.length > 0) {
            for (PrintService ps : pservices) {
                cont2++;
                modelo.addRow(new String[]{
                    Integer.toString(cont2), ps.getName()
                });
            }
        } else {
            jTImpressoraSelecionada.setText("Sem Impreessoras Disponíveis!");
        }
    }

    private void Carregar() throws IOException {
        try {
            PrintPdf.PrintPDF(impressora, Arquivo, Arquivo, opcao);
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void ClockRunnable() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        EventQueue.invokeLater(() -> {
                        });
                        sleep(100);
                        opcao = buttonGrupo.getSelection().getActionCommand();
                        jtPapel.setText(opcao);
                    } catch (InterruptedException ex) {
                        JOptionPane.showMessageDialog(null, "Erro no Sleep: " + ex.getMessage());
                    }
                    if (reg >= 0) {
                        int t = reg--;
                        String k = Integer.toString(t);
                        jLReg.setText(k);
                        if (t == 0) {
                            jLStatus.setText("Sistema Pronto!!");
                        }
                    }
                }
            }
        }.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGrupo = new javax.swing.ButtonGroup();
        jTImpressoraSelecionada = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jBImprimir = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLStatus = new javax.swing.JLabel();
        jLReg = new javax.swing.JLabel();
        jrPapelTermico = new javax.swing.JRadioButton();
        jrPapelA4 = new javax.swing.JRadioButton();
        jtPapel = new javax.swing.JTextField();
        jTquantidade = new javax.swing.JFormattedTextField();
        jbAtualizar = new javax.swing.JButton();
        jbSair = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableArquivos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableImpressora = new javax.swing.JTable();
        jTArquivoSelecionado = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setFocusable(false);
        setIconImage(new ImageIcon(getClass().getResource("/icons/asterisk_orange.png")).getImage());

        jTImpressoraSelecionada.setEditable(false);
        jTImpressoraSelecionada.setBackground(new java.awt.Color(153, 153, 153));
        jTImpressoraSelecionada.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jTImpressoraSelecionada.setForeground(new java.awt.Color(255, 255, 0));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel1.setText("Impressora Selecionada");

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 204, 0)), "Relatório Disponíveris", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 14), new java.awt.Color(0, 0, 204))); // NOI18N

        jBImprimir.setBackground(new java.awt.Color(51, 51, 255));
        jBImprimir.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jBImprimir.setForeground(new java.awt.Color(0, 204, 153));
        jBImprimir.setText("Imprimir");
        jBImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBImprimirActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel3.setText("Quantidade De Impressões");

        jLStatus.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLStatus.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLReg.setBackground(new java.awt.Color(255, 255, 102));
        jLReg.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLReg.setForeground(new java.awt.Color(0, 0, 204));
        jLReg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLReg.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        buttonGrupo.add(jrPapelTermico);
        jrPapelTermico.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jrPapelTermico.setSelected(true);
        jrPapelTermico.setText("Papel Termico");
        jrPapelTermico.setActionCommand("Termico");

        buttonGrupo.add(jrPapelA4);
        jrPapelA4.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jrPapelA4.setText("Papel A4");
        jrPapelA4.setActionCommand("A4");

        jtPapel.setBackground(new java.awt.Color(204, 204, 204));
        jtPapel.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jtPapel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtPapel.setEnabled(false);

        jTquantidade.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jTquantidade.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N

        jbAtualizar.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jbAtualizar.setText("Atualizar Arquivos");
        jbAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAtualizarActionPerformed(evt);
            }
        });

        jbSair.setBackground(new java.awt.Color(204, 255, 204));
        jbSair.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jbSair.setForeground(new java.awt.Color(51, 0, 255));
        jbSair.setText("Sair");
        jbSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jrPapelTermico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jrPapelA4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtPapel, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTquantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jBImprimir)
                        .addGap(18, 18, 18)
                        .addComponent(jbAtualizar)
                        .addGap(18, 18, 18)
                        .addComponent(jbSair)
                        .addGap(0, 16, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLReg, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jrPapelTermico)
                        .addComponent(jrPapelA4)
                        .addComponent(jtPapel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLReg, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jBImprimir)
                    .addComponent(jTquantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbAtualizar)
                    .addComponent(jbSair))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 102, 0)), "Selecione uma Impressora e um Arquivo para Impressão!", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(0, 204, 102))); // NOI18N

        jTableArquivos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Descrição dos Arquivos na Pasta ArquivosPDF"
            }
        ));
        jTableArquivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableArquivosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableArquivos);
        if (jTableArquivos.getColumnModel().getColumnCount() > 0) {
            jTableArquivos.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jTableImpressora.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Descrição das Impressoras Instaladas"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableImpressora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableImpressoraMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableImpressora);
        if (jTableImpressora.getColumnModel().getColumnCount() > 0) {
            jTableImpressora.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
        );

        jTArquivoSelecionado.setEditable(false);
        jTArquivoSelecionado.setBackground(new java.awt.Color(153, 153, 153));
        jTArquivoSelecionado.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jTArquivoSelecionado.setForeground(new java.awt.Color(255, 255, 0));

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel2.setText("Arquivo Selecionado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTImpressoraSelecionada)
                            .addComponent(jTArquivoSelecionado))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTImpressoraSelecionada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTArquivoSelecionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTableImpressoraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableImpressoraMouseClicked
        if (jTableImpressora.getSelectedRow() != -1) {
            Object nome1;
            nome1 = jTableImpressora.getValueAt(jTableImpressora.getSelectedRow(), 1);
            String nome = nome1.toString();
            jTImpressoraSelecionada.setText(nome);
            impressora = nome;
        }
    }//GEN-LAST:event_jTableImpressoraMouseClicked

    private void jTableArquivosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableArquivosMouseClicked
        if (jTableArquivos.getSelectedRow() != -1) {
            Object nome1;
            nome1 = jTableArquivos.getValueAt(jTableArquivos.getSelectedRow(), 1);
            String nome = nome1.toString();
            jTArquivoSelecionado.setText(nome);
            Arquivo = nome;
        }
    }//GEN-LAST:event_jTableArquivosMouseClicked

    private void jBImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBImprimirActionPerformed
        jfCarregando frame = new jfCarregando();
        frame.setVisible(true);
        Thread t = new Thread() {
            @Override
            public void run() {
                jLStatus.setText("Processo Finalizado.");
                jLStatus.setForeground(Color.green);
                try {
                    if (!jTquantidade.getText().equals("")) {

                        if (null == opcao) {
                            jLStatus.setText("Selecione um tipo de papel!");
                            jLStatus.setForeground(Color.red);
                        } else {
                            switch (opcao) {
                                case "Termico":
                                    //chek = true;
                                    Imprimir();
                                    break;
                                case "A4":
                                    //chek = false;
                                    Imprimir();
                                    break;
                                default:
                                    jLStatus.setText("Selecione um tipo de papel!");
                                    jLStatus.setForeground(Color.red);
                                    break;
                            }
                        }
                    } else {
                        jLStatus.setText("Quantidade de Impressões não definida!");
                        jLStatus.setForeground(Color.red);
                    }
                } catch (InterruptedException ex) {
                    jLStatus.setText("Erro ao Imprimir: " + ex.getMessage());
                    jLStatus.setForeground(Color.red);
                }
                frame.dispose();
            }
        };
        t.start();
    }//GEN-LAST:event_jBImprimirActionPerformed

    private void jbAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAtualizarActionPerformed
        if (cont <= 0 || cont2 <= 0) {
            QuantidadedeImpressoes();
        } else {
            cont = 0;
            cont2 = 0;
            QuantidadedeImpressoes();
        }
    }//GEN-LAST:event_jbAtualizarActionPerformed

    private void jbSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_jbSairActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGrupo;
    private javax.swing.JButton jBImprimir;
    private javax.swing.JLabel jLReg;
    private javax.swing.JLabel jLStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTArquivoSelecionado;
    private javax.swing.JTextField jTImpressoraSelecionada;
    private javax.swing.JTable jTableArquivos;
    private javax.swing.JTable jTableImpressora;
    private javax.swing.JFormattedTextField jTquantidade;
    private javax.swing.JButton jbAtualizar;
    private javax.swing.JButton jbSair;
    private javax.swing.JRadioButton jrPapelA4;
    private javax.swing.JRadioButton jrPapelTermico;
    private javax.swing.JTextField jtPapel;
    // End of variables declaration//GEN-END:variables
}
