package modulo.view.painel;

import java.awt.BorderLayout;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modulo.metodos.DadosGraficos;
import org.jfree.chart.ChartPanel;
import javax.swing.ImageIcon;
import modulo.versao.Versao;

/**
 *
 * @author Marcos Junior
 */
public final class JfPainel extends javax.swing.JFrame {

    private DadosGraficos dg;
    private int nume = 4;
    private Versao ver = new Versao();

    public JfPainel() {
        initComponents();
        setTitle(ver.getNomesys() + " Gráficos " + ver.getVersao());
        dg = new DadosGraficos();
        try {
            painelGraficoRuptura();
        } catch (Exception ex) {
            Logger.getLogger(JfPainel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void MouseClik(int num) throws PropertyVetoException, Exception {

        if (num != nume) {
            //Pimba
            nume = num;

            switch (num) {
                case 0:
                    //Painel Vendas

                    break;
                case 1:
                    //Painel Power

                    break;
                case 2:
                    //Painel Faltas
                    
                    break;
                case 3:
                    //Painel Desconto Vip

                    break;
                case 4:
                    //Painel Campanhas

                    break;

                default:
                    break;
            }
        } else {
            //Nada a fazer
        }
    }

    public void CarregaGraf2() {

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    painelGraficoRuptura();
                    jlErros.setText("");
                } catch (Exception ex) {
                    jlErros.setText(ex.getMessage());

                }
            }
        };
        t.start();
    }

    public String CarregaAnoSelecionado() {
        int ano = jYearChooser1.getYear();
        String anoVigente = Integer.toString(ano);
        return anoVigente;
    }

    public void painelGraficoRuptura() throws PropertyVetoException, Exception {
        String anoVigente = CarregaAnoSelecionado();
        jPanelGraficoFaltas.removeAll();
        jPanelGraficoFaltas.add(new ChartPanel(dg.painelGraficoRuptura(anoVigente)), BorderLayout.CENTER);
        jPanelGraficoFaltas.validate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtPainelDeMenus = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jYearChooser3 = new com.toedter.calendar.JYearChooser();
        jBAtualizar2 = new javax.swing.JButton();
        jPanelGraficoFaltas2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jYearChooser2 = new com.toedter.calendar.JYearChooser();
        jBAtualizar1 = new javax.swing.JButton();
        jPanelGraficoFaltas1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanelGraficoFaltas = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jBAtualizar = new javax.swing.JButton();
        jlErros = new javax.swing.JLabel();
        jPanel4Vip = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jYearChooser5 = new com.toedter.calendar.JYearChooser();
        jBAtualizar4 = new javax.swing.JButton();
        jPanelGraficoFaltas4 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jYearChooser4 = new com.toedter.calendar.JYearChooser();
        jBAtualizar3 = new javax.swing.JButton();
        jPanelGraficoFaltas3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setExtendedState(6);
        setIconImage(new ImageIcon(getClass().getResource("/icons/icone_sistema.png")).getImage());
        setPreferredSize(new java.awt.Dimension(1185, 514));

        jtPainelDeMenus.setBackground(new java.awt.Color(204, 255, 204));
        jtPainelDeMenus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtPainelDeMenusMouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jPanel7.setBackground(new java.awt.Color(204, 255, 204));
        jPanel7.setBorder(null);

        jLabel35.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel35.setText("SELECIONE O ANO VIGENTE");

        jYearChooser3.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N

        jBAtualizar2.setBackground(new java.awt.Color(255, 0, 51));
        jBAtualizar2.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jBAtualizar2.setText("ATUALIZA");
        jBAtualizar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAtualizar2ActionPerformed(evt);
            }
        });

        jPanelGraficoFaltas2.setBackground(new java.awt.Color(102, 255, 204));
        jPanelGraficoFaltas2.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel2.setText("Matricula");

        jFormattedTextField2.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelGraficoFaltas2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jYearChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBAtualizar2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jYearChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jBAtualizar2))
                .addGap(0, 0, 0)
                .addComponent(jPanelGraficoFaltas2, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jtPainelDeMenus.addTab("Venda", jPanel1);

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));

        jPanel6.setBackground(new java.awt.Color(255, 255, 204));
        jPanel6.setBorder(null);

        jLabel34.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel34.setText("SELECIONE O ANO VIGENTE");

        jYearChooser2.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N

        jBAtualizar1.setBackground(new java.awt.Color(255, 0, 51));
        jBAtualizar1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jBAtualizar1.setText("ATUALIZA");
        jBAtualizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAtualizar1ActionPerformed(evt);
            }
        });

        jPanelGraficoFaltas1.setBackground(new java.awt.Color(102, 255, 204));
        jPanelGraficoFaltas1.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(83, 314, Short.MAX_VALUE)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jYearChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBAtualizar1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
            .addComponent(jPanelGraficoFaltas1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBAtualizar1)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jYearChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jPanelGraficoFaltas1, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jtPainelDeMenus.addTab("Power", jPanel2);

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));

        jPanelGraficoFaltas.setBackground(new java.awt.Color(102, 255, 204));
        jPanelGraficoFaltas.setLayout(new java.awt.BorderLayout());

        jLabel33.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel33.setText("SELECIONE O ANO VIGENTE");

        jYearChooser1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N

        jBAtualizar.setBackground(new java.awt.Color(255, 0, 51));
        jBAtualizar.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jBAtualizar.setText("ATUALIZA");
        jBAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAtualizarActionPerformed(evt);
            }
        });

        jlErros.setText("-");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelGraficoFaltas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlErros, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlErros))
                    .addComponent(jBAtualizar))
                .addGap(0, 0, 0)
                .addComponent(jPanelGraficoFaltas, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE))
        );

        jtPainelDeMenus.addTab("Faltas", jPanel3);

        jPanel4Vip.setBackground(new java.awt.Color(204, 255, 204));

        jPanel9.setBackground(new java.awt.Color(204, 255, 204));
        jPanel9.setBorder(null);

        jLabel37.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel37.setText("SELECIONE O ANO VIGENTE");

        jYearChooser5.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N

        jBAtualizar4.setBackground(new java.awt.Color(255, 0, 51));
        jBAtualizar4.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jBAtualizar4.setText("ATUALIZA");
        jBAtualizar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAtualizar4ActionPerformed(evt);
            }
        });

        jPanelGraficoFaltas4.setBackground(new java.awt.Color(102, 255, 204));
        jPanelGraficoFaltas4.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelGraficoFaltas4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(314, Short.MAX_VALUE)
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jYearChooser5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBAtualizar4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jYearChooser5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBAtualizar4)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(jPanelGraficoFaltas4, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel4VipLayout = new javax.swing.GroupLayout(jPanel4Vip);
        jPanel4Vip.setLayout(jPanel4VipLayout);
        jPanel4VipLayout.setHorizontalGroup(
            jPanel4VipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4VipLayout.setVerticalGroup(
            jPanel4VipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jtPainelDeMenus.addTab("Desc Vip", jPanel4Vip);

        jPanel4.setBackground(new java.awt.Color(204, 255, 204));

        jPanel8.setBackground(new java.awt.Color(204, 255, 204));
        jPanel8.setBorder(null);

        jLabel36.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel36.setText("SELECIONE O ANO VIGENTE");

        jBAtualizar3.setBackground(new java.awt.Color(255, 0, 51));
        jBAtualizar3.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jBAtualizar3.setText("ATUALIZA");
        jBAtualizar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAtualizar3ActionPerformed(evt);
            }
        });

        jPanelGraficoFaltas3.setBackground(new java.awt.Color(102, 255, 204));
        jPanelGraficoFaltas3.setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel3.setText("Matricula");

        jFormattedTextField3.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione Uma Campanha" }));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelGraficoFaltas3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, 0, 134, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jYearChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBAtualizar3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jYearChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jBAtualizar3))
                .addGap(0, 0, 0)
                .addComponent(jPanelGraficoFaltas3, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jtPainelDeMenus.addTab("Campanhas", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtPainelDeMenus)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtPainelDeMenus, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAtualizarActionPerformed
        CarregaGraf2();
    }//GEN-LAST:event_jBAtualizarActionPerformed

    private void jtPainelDeMenusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtPainelDeMenusMouseClicked
        int num = jtPainelDeMenus.getSelectedIndex();
        try {
            MouseClik(num);
        } catch (PropertyVetoException ex) {
            JOptionPane.showMessageDialog(this, "Erro MouseClik 1:" + num + ex);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro MouseClik 2: " + num + ex);
        }
    }//GEN-LAST:event_jtPainelDeMenusMouseClicked

    private void jBAtualizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAtualizar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBAtualizar1ActionPerformed

    private void jBAtualizar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAtualizar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBAtualizar2ActionPerformed

    private void jBAtualizar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAtualizar3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBAtualizar3ActionPerformed

    private void jBAtualizar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAtualizar4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBAtualizar4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAtualizar;
    private javax.swing.JButton jBAtualizar1;
    private javax.swing.JButton jBAtualizar2;
    private javax.swing.JButton jBAtualizar3;
    private javax.swing.JButton jBAtualizar4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JFormattedTextField jFormattedTextField3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel4Vip;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelGraficoFaltas;
    private javax.swing.JPanel jPanelGraficoFaltas1;
    private javax.swing.JPanel jPanelGraficoFaltas2;
    private javax.swing.JPanel jPanelGraficoFaltas3;
    private javax.swing.JPanel jPanelGraficoFaltas4;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private com.toedter.calendar.JYearChooser jYearChooser2;
    private com.toedter.calendar.JYearChooser jYearChooser3;
    private com.toedter.calendar.JYearChooser jYearChooser4;
    private com.toedter.calendar.JYearChooser jYearChooser5;
    private javax.swing.JLabel jlErros;
    private javax.swing.JTabbedPane jtPainelDeMenus;
    // End of variables declaration//GEN-END:variables
}
