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

/**
 *
 * @author Marcos Junior
 */
public final class JTelaPrintPDF extends javax.swing.JFrame {

    String impressora = null;
    String impressoraLocal = "SWEDA SI-300S";
    String Arquivo = null;
    String opcao;
    boolean chek;
    String Pasta = "C:\\PmenosF\\ArquivosPDF";
    int quantidade = 0;
    int cont = 0, cont2 = 0, reg = 0;

    public JTelaPrintPDF() {
        initComponents();
        DefaultTableModel modelo = (DefaultTableModel) jTableArquivos.getModel();
        jTableArquivos.setRowSorter(new TableRowSorter(modelo));
        DefaultTableModel modelo2 = (DefaultTableModel) jTableImpressora.getModel();
        jTableImpressora.setRowSorter(new TableRowSorter(modelo2));
        QuantidadedeImpressoes();
        jLStatus.setText("Sistema Pronto");
        ClockRunnable();
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
        jBSair = new javax.swing.JButton();
        jTquantidade = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableArquivos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableImpressora = new javax.swing.JTable();
        jTArquivoSelecionado = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sistema de Impressão Simples v 1.0.0");
        setAlwaysOnTop(true);
        setFocusable(false);
        setIconImage(new ImageIcon(getClass().getResource("/icons/asterisk_orange.png")).getImage());
        setResizable(false);

        jTImpressoraSelecionada.setEditable(false);
        jTImpressoraSelecionada.setBackground(new java.awt.Color(153, 153, 153));
        jTImpressoraSelecionada.setForeground(new java.awt.Color(255, 255, 0));

        jLabel1.setText("Impressora Selecionada");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 204, 0)), "Relatório Disponíveris", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 14), new java.awt.Color(0, 0, 204))); // NOI18N

        jBImprimir.setText("Imprimir");
        jBImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBImprimirActionPerformed(evt);
            }
        });

        jLabel3.setText("Quantidade De Impressões");

        jLReg.setBackground(new java.awt.Color(255, 255, 102));
        jLReg.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLReg.setForeground(new java.awt.Color(0, 0, 204));
        jLReg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        buttonGrupo.add(jrPapelTermico);
        jrPapelTermico.setSelected(true);
        jrPapelTermico.setText("Papel Termico");
        jrPapelTermico.setActionCommand("Termico");

        buttonGrupo.add(jrPapelA4);
        jrPapelA4.setText("Papel A4");
        jrPapelA4.setActionCommand("A4");

        jtPapel.setEnabled(false);

        jBSair.setText("Sair");
        jBSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSairActionPerformed(evt);
            }
        });

        jTquantidade.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTquantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBImprimir)
                        .addGap(18, 18, 18)
                        .addComponent(jBSair)
                        .addGap(18, 18, 18)
                        .addComponent(jLReg, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jrPapelTermico)
                        .addGap(18, 18, 18)
                        .addComponent(jrPapelA4)
                        .addGap(18, 18, 18)
                        .addComponent(jtPapel, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jrPapelTermico)
                        .addComponent(jrPapelA4)
                        .addComponent(jtPapel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jBImprimir)
                        .addComponent(jBSair)
                        .addComponent(jTquantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLReg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 102, 0)), "Selecione a Impressora e o Arquivo", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 14), new java.awt.Color(0, 204, 102))); // NOI18N

        jTableArquivos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Descrição"
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
                "Nº", "Descrição"
            }
        ));
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))
        );

        jTArquivoSelecionado.setEditable(false);
        jTArquivoSelecionado.setBackground(new java.awt.Color(153, 153, 153));
        jTArquivoSelecionado.setForeground(new java.awt.Color(255, 255, 0));

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

    private void jBSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBSairActionPerformed

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
                                    chek = true;
                                    Imprimir();
                                    break;
                                case "A4":
                                    chek = false;
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

    public void QuantidadedeImpressoes() {
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

    public void Imprimir() throws InterruptedException {
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
                for (int i = 0; i < quantidade; i++) {
                    try {
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

    public void ListaArquivos() {
        File file = new File(Pasta);
        String local = file.getPath();
        DefaultTableModel modelo = (DefaultTableModel) jTableArquivos.getModel();
        modelo.setNumRows(0);
        for (String p : file.list()) {
            cont++;
            modelo.addRow(new String[]{
                Integer.toString(cont), local.concat("\\" + p)
            });
        }
    }

    public void listarImpressorasDisponíveis() {
        PrintService[] pservices = PrinterJob.lookupPrintServices();
        String nome = "";
        DefaultTableModel modelo = (DefaultTableModel) jTableImpressora.getModel();
        modelo.setNumRows(0);
        if (pservices.length > 0) {
            for (PrintService ps : pservices) {
                cont2++;
                nome += ps.getName() + "\n";
                modelo.addRow(new String[]{
                    Integer.toString(cont2), ps.getName()
                });
            }
        }
    }

    public void Carregar() throws IOException {
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
                            jLStatus.setText("Sistema Pronto");
                        }
                    }
                }
            }
        }.start();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGrupo;
    private javax.swing.JButton jBImprimir;
    private javax.swing.JButton jBSair;
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
    private javax.swing.JRadioButton jrPapelA4;
    private javax.swing.JRadioButton jrPapelTermico;
    private javax.swing.JTextField jtPapel;
    // End of variables declaration//GEN-END:variables
}
