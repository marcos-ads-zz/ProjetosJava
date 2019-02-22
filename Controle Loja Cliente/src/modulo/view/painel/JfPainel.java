package modulo.view.painel;

import java.awt.BorderLayout;
import java.beans.PropertyVetoException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modulo.dados.graficos.DadosGraficos;
import org.jfree.chart.ChartPanel;
import javax.swing.ImageIcon;
import modulo.DAO.Cargos;
import modulo.DAO.CargosDAO;
import modulo.camapanha.DAO.metasCampanhasDAO;
import modulo.campanhas.CadastroMetasCampanhas;
import modulo.metodos.Funcao;
import modulo.versao.Versao;
import modulo.view.principal.JfPrincipal;

/**
 *
 * @author Marcos Junior
 */
public final class JfPainel extends javax.swing.JFrame {

    private metasCampanhasDAO CADCAMP_DAO;
    private CargosDAO DAOCAR;
    private List<Cargos> objCar;
    private DadosGraficos dg;
    private int nume = 6;
    private Versao ver = new Versao();
    private Funcao fun;
    private static int a, b, c, d, e, f;

    public JfPainel() {
        initComponents();
        DAOCAR = new CargosDAO();
        fun = new Funcao();
        setTitle(ver.getNomesys() + " Gráficos " + ver.getVersao());
        dg = new DadosGraficos();
        CADCAMP_DAO = new metasCampanhasDAO();
        carregaCargos();
        listaCampanhasAtivas();
    }

    public void carregaCargos() {
        try {
            objCar = DAOCAR.Pesquisa();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Pesquisar Cargos! " + ex);
        }
        objCar.forEach((cc) -> {
            jcGnano.addItem(cc.getCargos());
            jcPower.addItem(cc.getCargos());
            jcCampCargos.addItem(cc.getCargos());
        });
    }

    private boolean validadeCampanha(java.sql.Date data) throws Exception {
        String aa, bb;
        aa = fun.converteDataSQLemDateString(data);
        bb = fun.converteDataSQLemDateString(fun.atualDateSQL());
        return data.compareTo(fun.atualDateSQL()) == 1 || aa.equals(bb);
    }

    private void listaCampanhasAtivas() {
        List<CadastroMetasCampanhas> CadCamp;
        try {
            CadCamp = CADCAMP_DAO.TabelaPesquisaTodos();
            CadCamp.forEach((result) -> {
                try {
                    if (validadeCampanha(result.getData_fim())) {
                        if (!"POWER VITA".equals(result.getDescricao_Campanha()) & !"GNANO".equals(result.getDescricao_Campanha())) {
                            jcCampanhas.addItem(result.getDescricao_Campanha());
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(JfPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Pesquisar Campanhas! " + ex);
        }
    }

    public void MouseClik(int num) throws PropertyVetoException, Exception {
        if (num != nume) {
            //Pimba
            nume = num;

            switch (num) {
                case 0:
                    //Painel Vendas
                    if (a == 0) {
                        a = num;
                    }
                    break;
                case 1:
                    //Painel Power
                    if (b == 0) {
                        CarregaPower();
                        b = num;
                    }

                    break;
                case 2:
                    //Painel Gnano
                    if (c == 0) {
                        CarregaGnano();
                        c = num;
                    }

                    break;
                case 3:
                    //Painel Faltas
                    if (d == 0) {
                        painelGraficoRuptura();
                        d = num;
                    }

                    break;
                case 4:
                    //Painel Desconto Vip
                    if (e == 0) {
                        e = num;
                    }

                    break;
                case 5:
                    //Painel Campanhas
                    if (f == 0) {
                        CarregaCampanhas();
                        f = num;
                    }

                    break;

                default:
                    break;
            }
        } else {
            //Nada a fazer
        }
    }

    public void CarregaGnano() throws Exception {
        painelBalcaoGnano("GNANO", jcGnano.getSelectedItem().toString());
    }

    public void CarregaPower() throws Exception {
        painelBalcaoPower("POWER VITA", jcPower.getSelectedItem().toString());
    }

    public void CarregaCampanhas() throws Exception {
        painelBalcaoCampanhas(jcCampanhas.getSelectedItem().toString(), jcCampCargos.getSelectedItem().toString());
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

    public void painelBalcaoPower(String campanha, String cargo) throws PropertyVetoException, Exception {
        jPanelGraficoPower.removeAll();
        jPanelGraficoPower.add(new ChartPanel(dg.painelGraficoBarraPowerBalcao(campanha, cargo)), BorderLayout.CENTER);
        jPanelGraficoPower.validate();
    }

    public void painelBalcaoGnano(String campanha, String cargo) throws PropertyVetoException, Exception {
        jPanelGraficoGnano.removeAll();
        jPanelGraficoGnano.add(new ChartPanel(dg.painelGraficoBarraGnanoBalcao(campanha, cargo)), BorderLayout.CENTER);
        jPanelGraficoGnano.validate();
    }

    public void painelBalcaoCampanhas(String campanha, String cargo) throws PropertyVetoException, Exception {
        jPanelGraficoCampanhas.removeAll();
        jPanelGraficoCampanhas.add(new ChartPanel(dg.painelGraficoBarraCampanhaBalcao(campanha, cargo)), BorderLayout.CENTER);
        jPanelGraficoCampanhas.validate();
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
        jBAtualizarPower = new javax.swing.JButton();
        jPanelGraficoPower = new javax.swing.JPanel();
        jcPower = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jYearChooser6 = new com.toedter.calendar.JYearChooser();
        jBAtualizarGnano = new javax.swing.JButton();
        jPanelGraficoGnano = new javax.swing.JPanel();
        jcGnano = new javax.swing.JComboBox<>();
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
        jBAtualizarCamp = new javax.swing.JButton();
        jPanelGraficoCampanhas = new javax.swing.JPanel();
        jcCampanhas = new javax.swing.JComboBox<>();
        jcCampCargos = new javax.swing.JComboBox<>();

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

        jYearChooser3.setEnabled(false);

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

        jFormattedTextField2.setEnabled(false);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 582, Short.MAX_VALUE)
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

        jYearChooser2.setEnabled(false);

        jBAtualizarPower.setBackground(new java.awt.Color(255, 0, 51));
        jBAtualizarPower.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jBAtualizarPower.setText("ATUALIZA");
        jBAtualizarPower.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAtualizarPowerActionPerformed(evt);
            }
        });

        jPanelGraficoPower.setBackground(new java.awt.Color(102, 255, 204));
        jPanelGraficoPower.setLayout(new java.awt.BorderLayout());

        jcPower.setBackground(new java.awt.Color(204, 255, 204));
        jcPower.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TODOS" }));
        jcPower.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcPowerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 440, Short.MAX_VALUE)
                .addComponent(jcPower, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jYearChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBAtualizarPower, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
            .addComponent(jPanelGraficoPower, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBAtualizarPower)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jcPower, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jYearChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jPanelGraficoPower, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE))
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

        jPanel5.setBackground(new java.awt.Color(204, 255, 204));

        jPanel10.setBackground(new java.awt.Color(255, 255, 204));
        jPanel10.setBorder(null);

        jLabel38.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel38.setText("SELECIONE O ANO VIGENTE");

        jYearChooser6.setEnabled(false);

        jBAtualizarGnano.setBackground(new java.awt.Color(255, 0, 51));
        jBAtualizarGnano.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jBAtualizarGnano.setText("ATUALIZA");
        jBAtualizarGnano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAtualizarGnanoActionPerformed(evt);
            }
        });

        jPanelGraficoGnano.setBackground(new java.awt.Color(102, 255, 204));
        jPanelGraficoGnano.setLayout(new java.awt.BorderLayout());

        jcGnano.setBackground(new java.awt.Color(204, 255, 204));
        jcGnano.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TODOS" }));
        jcGnano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcGnanoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jcGnano, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jYearChooser6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBAtualizarGnano, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
            .addComponent(jPanelGraficoGnano, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBAtualizarGnano)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jcGnano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jYearChooser6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jPanelGraficoGnano, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jtPainelDeMenus.addTab("Gnano", jPanel5);

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));

        jPanelGraficoFaltas.setBackground(new java.awt.Color(102, 255, 204));
        jPanelGraficoFaltas.setLayout(new java.awt.BorderLayout());

        jLabel33.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel33.setText("SELECIONE O ANO VIGENTE");

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
                .addComponent(jlErros, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
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
                .addContainerGap(750, Short.MAX_VALUE)
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

        jYearChooser4.setEnabled(false);

        jBAtualizarCamp.setBackground(new java.awt.Color(255, 0, 51));
        jBAtualizarCamp.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jBAtualizarCamp.setText("ATUALIZA");
        jBAtualizarCamp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAtualizarCampActionPerformed(evt);
            }
        });

        jPanelGraficoCampanhas.setBackground(new java.awt.Color(102, 255, 204));
        jPanelGraficoCampanhas.setLayout(new java.awt.BorderLayout());

        jcCampanhas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TODAS" }));

        jcCampCargos.setBackground(new java.awt.Color(204, 255, 204));
        jcCampCargos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TODOS" }));
        jcCampCargos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcCampCargosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelGraficoCampanhas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jcCampCargos, 0, 379, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcCampanhas, 0, 353, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jYearChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBAtualizarCamp, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBAtualizarCamp)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcCampanhas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcCampCargos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jYearChooser4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addComponent(jPanelGraficoCampanhas, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                .addGap(0, 0, 0))
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
        try {
            painelGraficoRuptura();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro Painel Ruptura:" +ex);
        }
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

    private void jBAtualizarPowerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAtualizarPowerActionPerformed
        try {
            CarregaPower();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro Painel Power:" +ex);
        }
    }//GEN-LAST:event_jBAtualizarPowerActionPerformed

    private void jBAtualizar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAtualizar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBAtualizar2ActionPerformed

    private void jBAtualizarCampActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAtualizarCampActionPerformed
        try {
            CarregaCampanhas();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro Painel Camapanhas:" +ex);
        }
    }//GEN-LAST:event_jBAtualizarCampActionPerformed

    private void jBAtualizar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAtualizar4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBAtualizar4ActionPerformed

    private void jBAtualizarGnanoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAtualizarGnanoActionPerformed
        try {
            CarregaGnano();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro Painel Gnano:" +ex);
        }
    }//GEN-LAST:event_jBAtualizarGnanoActionPerformed

    private void jcGnanoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcGnanoActionPerformed

    }//GEN-LAST:event_jcGnanoActionPerformed

    private void jcPowerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcPowerActionPerformed

    }//GEN-LAST:event_jcPowerActionPerformed

    private void jcCampCargosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcCampCargosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcCampCargosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAtualizar;
    private javax.swing.JButton jBAtualizar2;
    private javax.swing.JButton jBAtualizar4;
    private javax.swing.JButton jBAtualizarCamp;
    private javax.swing.JButton jBAtualizarGnano;
    private javax.swing.JButton jBAtualizarPower;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel4Vip;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelGraficoCampanhas;
    private javax.swing.JPanel jPanelGraficoFaltas;
    private javax.swing.JPanel jPanelGraficoFaltas2;
    private javax.swing.JPanel jPanelGraficoFaltas4;
    private javax.swing.JPanel jPanelGraficoGnano;
    private javax.swing.JPanel jPanelGraficoPower;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private com.toedter.calendar.JYearChooser jYearChooser2;
    private com.toedter.calendar.JYearChooser jYearChooser3;
    private com.toedter.calendar.JYearChooser jYearChooser4;
    private com.toedter.calendar.JYearChooser jYearChooser5;
    private com.toedter.calendar.JYearChooser jYearChooser6;
    private javax.swing.JComboBox<String> jcCampCargos;
    private javax.swing.JComboBox<String> jcCampanhas;
    private javax.swing.JComboBox<String> jcGnano;
    private javax.swing.JComboBox<String> jcPower;
    private javax.swing.JLabel jlErros;
    private javax.swing.JTabbedPane jtPainelDeMenus;
    // End of variables declaration//GEN-END:variables
}
