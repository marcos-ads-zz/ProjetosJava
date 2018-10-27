package modulo.campanhas.vendaD;

import java.awt.BorderLayout;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modulo.usuarios.UsuarioDAO;
import modulo.usuarios.Usuario;
import modulo.jasper.JasperDAO;
import java.awt.Dimension;
import java.beans.PropertyVetoException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import modulo.campanhas.meta.CadastroMetasCampanhas;
import modulo.campanhas.meta.CadastroMetasCampanhasDAO;
import modulo.metodos.Funcao;
import modulo.versao.Versao;
import modulo.view.principal.JfPrincipal;
import modulo.metodos.JifCarregamento;
import org.jfree.chart.ChartPanel;

/**
 *
 * @author Marcos Junior
 */
public final class JifCampanhaRelatorio extends javax.swing.JInternalFrame {

    private CadastroCampanhaDia cadCamp;
    private CadastroMetasCampanhasDAO CADCAMP_DAO;
    private relatorioCampDAO DAOREL;
    private graficosCampanha grfCamp;
    private UsuarioDAO DAOUSER;
    private Usuario objUSER;
    private JasperDAO rl;
    private Funcao fun;
    DateFormat formatoHora;
    DateFormat formatoDIA;
    private JifCarregamento jfC = null;
    private Versao ver;
    private int numLoja;

    public JifCampanhaRelatorio() {
        initComponents();
        DAOUSER = new UsuarioDAO();
        grfCamp = new graficosCampanha();
        fun = new Funcao();
        ver = new Versao();
        rl = new JasperDAO();
        DAOREL = new relatorioCampDAO();
        CADCAMP_DAO = new CadastroMetasCampanhasDAO();
        Thread relogioThred = new Thread(new JifCampanhaRelatorio.clsDataHora());
        relogioThred.start();
        setTitle("Relatórios de Campanhas: " + ver.getVersao());
        carregaDatasCampos();
        exibeDados();
    }

    private void carregaDatasCampos() {
        try {
            jdDataPesquisaInicio.setDate(fun.primeiroDiaMesAtual());
            jdDataPesquisaFim.setDate(new Date());
        } catch (ParseException ex) {
            Logger.getLogger(JifCampanhaRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private boolean verificaDatas() {
        return false;
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    private void exibeDados() {
        try {
            listaCampanhasAtivas();
            jListCampanhas.setSelectedIndex(0);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Carregar Lista: " + ex.getMessage());
        }
    }

    private boolean validadeCampanha(java.sql.Date data) throws Exception {
        String a, b;
        a = fun.convertDataSQLToDateString(data);
        b = fun.convertDataSQLToDateString(fun.atualDateSQL());
        return data.compareTo(fun.atualDateSQL()) == 1 || a.equals(b);
    }

    private int validarCampoNovoRelatorio() {
        String opcao = GrupoDeRadioButoes.getSelection().getActionCommand();
        int chek = 0;
        if (null != opcao) {
            switch (opcao) {
                case "matricula":
                    jtMatricula.setEnabled(true);
                    chek = 1;
                    break;
                case "campanha":
                    jtMatricula.setEnabled(false);
                    chek = 2;
                    break;
                default:
                    break;
            }
        }
        return chek;
    }

    private String anoY() {
        int ano = jYearChooserAno.getYear();
        System.out.println("Ano: " + Integer.toString(ano));
        return Integer.toString(ano);
    }

    private void painelGraficoCampanha(int matricula, String campanha) throws PropertyVetoException, Exception {
        jPanelGrafico.removeAll();
        jPanelGrafico.add(new ChartPanel(grfCamp.painelGraficoCampanhas(matricula, campanha, anoY())), BorderLayout.CENTER);
        jPanelGrafico.validate();
    }

    private void PesquisaRegistros() {
        String a, b;
        a = jtMatricula.getText();
        b = jListCampanhas.getSelectedItem().toString();
        int mat = Integer.parseInt(a);
        try {
            preencheTabela(DAOREL.pesquisaCampanhasPorMes(Integer.parseInt(a), b, fun.convertDateToDateSql(jdDataPesquisaInicio.getDate())));
            if (!jtMatricula.getText().equals("") || jListCampanhas.getSelectedIndex() == 0) {
                painelGraficoCampanha(mat, b);
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao Pesquisar Campanhas!");
            }
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Pesquisar Campanhas Parse! " + ex);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Pesquisar Campanhas Exception! " + ex);
        }
    }

    private void preencheTabela(List<CadastroCampanhaDia> cadlist) {
        DefaultTableModel modelo = (DefaultTableModel) jtTabela.getModel();
        modelo.setNumRows(0);
        cadlist.forEach((p) -> {
            modelo.addRow(new Object[]{
                p.getId(), p.getMatricula(), p.getDesc_campanha(),
                p.getQuantidade(), p.getData_registro()
            });
        });
    }

    private void listaCampanhasAtivas() {
        DefaultListModel dlm = new DefaultListModel();
        List<CadastroMetasCampanhas> CadCamp;
        try {
            CadCamp = CADCAMP_DAO.TabelaPesquisaTodos();
            CadCamp.forEach((c) -> {
                try {
                    if (validadeCampanha(c.getData_fim())) {
                        jListCampanhas.addItem(c.getDescricao_Campanha());
                        dlm.addElement(c.getDescricao_Campanha());
                    }
                } catch (Exception ex) {
                    Logger.getLogger(JifCadastroCampanhaDia.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Pesquisar Campanhas! " + ex);
        }
    }

    private void RelatorioMatricula() {
        Date data1 = jdDataPesquisaInicio.getDate();
        Date data2 = jdDataPesquisaFim.getDate();
        int mat = Integer.parseInt(jtMatricula.getText());
        rl.RelatorioMatricula(mat, numLoja, fun.convertDateToDateSql(data1), fun.convertDateToDateSql(data2));
    }

    private void RelatorioObservacao() {
        Date data1 = jdDataPesquisaInicio.getDate();
        Date data2 = jdDataPesquisaFim.getDate();
        String obs = jListCampanhas.getSelectedItem().toString().toUpperCase();
        rl.RelatorioObservacao(obs, numLoja, fun.convertDateToDateSql(data1), fun.convertDateToDateSql(data2));
    }

    private void RelatorioData() {
        Date data1 = jdDataPesquisaInicio.getDate();
        Date data2 = jdDataPesquisaFim.getDate();
        rl.RelatorioData(fun.convertDateToDateSql(data1), fun.convertDateToDateSql(data2), numLoja);
    }

    private void RelatorioDataReforco() {
        Date data1 = jdDataPesquisaInicio.getDate();
        Date data2 = jdDataPesquisaFim.getDate();
        rl.RelatorioReforcoData(fun.convertDateToDateSql(data1), fun.convertDateToDateSql(data2));
    }

    private void RelatorioDataEnvio() {
        Date data1 = jdDataPesquisaInicio.getDate();
        Date data2 = jdDataPesquisaFim.getDate();
        rl.RelatorioDataEnvio(fun.convertDateToDateSql(data1), fun.convertDateToDateSql(data2), numLoja);
    }

    class clsDataHora implements Runnable {

        @Override
        public void run() {
            while (true) {
                validarCampoNovoRelatorio();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println("Thread não foi finalizada:" + ex);
                }
            }
        }
    }

    private void Carregando() {
        if (jfC == null) {
            jfC = new JifCarregamento();
            JfPrincipal.jDesktopPrincipal.add(jfC);
            jfC.setVisible(true);
            jfC.setPosicao();
        } else if (!jfC.isVisible()) {
            jfC = new JifCarregamento();
            JfPrincipal.jDesktopPrincipal.add(jfC);
            jfC.setVisible(true);
            jfC.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Erro!");
        }
    }

    private void fechaCarregamento() {
        if (jfC == null) {
            jfC.dispose();
        } else if (!jfC.isVisible()) {
            jfC.dispose();
        } else {
            jfC.dispose();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GrupoDeRadioButoes = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jbPesquisar = new javax.swing.JButton();
        jbListar = new javax.swing.JButton();
        jlMat = new javax.swing.JLabel();
        jtMatricula = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jlDataPesquisa = new javax.swing.JLabel();
        jdDataPesquisaFim = new com.toedter.calendar.JDateChooser("dd/MM/yyyy", "##/##/#####", '-');
        jdDataPesquisaInicio = new com.toedter.calendar.JDateChooser("dd/MM/yyyy", "##/##/#####", '-');
        jlInicio = new javax.swing.JLabel();
        jlFim = new javax.swing.JLabel();
        jbPesquisa = new javax.swing.JButton();
        jrMatricula = new javax.swing.JRadioButton();
        jrObservacao = new javax.swing.JRadioButton();
        jListCampanhas = new javax.swing.JComboBox<>();
        jbEnvio = new javax.swing.JButton();
        jPanelGrafico = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtTabela = new javax.swing.JTable();
        jYearChooserAno = new com.toedter.calendar.JYearChooser();
        jLabel6 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        jbPesquisar.setBackground(new java.awt.Color(255, 0, 0));
        jbPesquisar.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jbPesquisar.setForeground(new java.awt.Color(255, 255, 255));
        jbPesquisar.setText("Pesquisar");
        jbPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPesquisarActionPerformed(evt);
            }
        });

        jbListar.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jbListar.setText("Gerar Todos");
        jbListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbListarActionPerformed(evt);
            }
        });

        jlMat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlMat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlMat.setText("Matrícula");

        jtMatricula.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jtMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtMatriculaActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Campanha");

        jlDataPesquisa.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jlDataPesquisa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlDataPesquisa.setText("Filtros");

        jdDataPesquisaFim.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N

        jdDataPesquisaInicio.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N

        jlInicio.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlInicio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlInicio.setText("Data Inicio");

        jlFim.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlFim.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlFim.setText("Data Fim");

        jbPesquisa.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jbPesquisa.setText("Imprimir a Pesquisa");
        jbPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPesquisaActionPerformed(evt);
            }
        });

        GrupoDeRadioButoes.add(jrMatricula);
        jrMatricula.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jrMatricula.setSelected(true);
        jrMatricula.setText("Pesquisar por matrícula");
        jrMatricula.setActionCommand("matricula");

        GrupoDeRadioButoes.add(jrObservacao);
        jrObservacao.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jrObservacao.setText("Pesquisar por Campanha");
        jrObservacao.setActionCommand("campanha");

        jListCampanhas.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jListCampanhas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione Uma Campanha" }));

        jbEnvio.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jbEnvio.setText("Gerar Parcial");
        jbEnvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEnvioActionPerformed(evt);
            }
        });

        jPanelGrafico.setBackground(new java.awt.Color(51, 255, 51));
        jPanelGrafico.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Meta Dia");

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(51, 51, 255));
        jTextField1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Venda Dia");

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(255, 0, 0));
        jTextField2.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Meta Mês");

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(51, 51, 255));
        jTextField3.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Venda Mês");

        jTextField4.setEditable(false);
        jTextField4.setBackground(new java.awt.Color(255, 0, 0));
        jTextField4.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jtTabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "VENDEDOR", "CAMPANHA", "VENDA", "DATA DO REGISTRO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtTabela);

        jLabel6.setFont(new java.awt.Font("sansserif", 3, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 51, 0));
        jLabel6.setText("Ano do Gráfico");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanelGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jbPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbListar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbPesquisa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jlDataPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jlMat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jListCampanhas, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jdDataPesquisaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jlFim)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jdDataPesquisaFim, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jrMatricula)
                                .addGap(18, 18, 18)
                                .addComponent(jrObservacao)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField3)
                                    .addComponent(jTextField1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField4)
                                    .addComponent(jTextField2)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 76, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jYearChooserAno, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlDataPesquisa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jrMatricula)
                    .addComponent(jrObservacao))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jListCampanhas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlMat, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jdDataPesquisaFim, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                        .addComponent(jdDataPesquisaInicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlFim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlInicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jYearChooserAno, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbListar)
                    .addComponent(jbPesquisar)
                    .addComponent(jbPesquisa)
                    .addComponent(jbEnvio))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanelGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPesquisarActionPerformed
        PesquisaRegistros();
    }//GEN-LAST:event_jbPesquisarActionPerformed

    private void jbListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbListarActionPerformed
        Carregando();
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    // preencheTabela();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao Iniciar readTable." + ex.getMessage());
                }
                fechaCarregamento();
            }
        };
        t.start();
    }//GEN-LAST:event_jbListarActionPerformed

    private void jtMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtMatriculaActionPerformed
        try {
            objUSER = DAOUSER.PesquisaPorMatricula(Integer.parseInt(jtMatricula.getText()));
            if (objUSER == null || jtMatricula.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Não Foi Possível Encontrar o Registro!");
                jtMatricula.setText("");//Limpa ID
                jtMatricula.requestFocus();
            } else {
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Iniciar readTable Matricula." + ex.getMessage());
        }
    }//GEN-LAST:event_jtMatriculaActionPerformed

    private void jbPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPesquisaActionPerformed
        Carregando();
        Thread t = new Thread() {
            @Override
            public void run() {
                if (validarCampoNovoRelatorio() == 1) {
                    RelatorioMatricula();
                }
                if (validarCampoNovoRelatorio() == 4) {
                    RelatorioObservacao();
                }
                if (validarCampoNovoRelatorio() == 5) {
                    RelatorioData();
                }
                fechaCarregamento();
            }
        };
        t.start();
    }//GEN-LAST:event_jbPesquisaActionPerformed

    private void jbEnvioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEnvioActionPerformed
        Carregando();
        Thread t = new Thread() {
            @Override
            public void run() {
                if (validarCampoNovoRelatorio() == 5) {
                    RelatorioDataEnvio();
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione a pesquisa por data para emitir o relatório.");
                }
                fechaCarregamento();
            }
        };
        t.start();
    }//GEN-LAST:event_jbEnvioActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup GrupoDeRadioButoes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JComboBox<String> jListCampanhas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelGrafico;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private com.toedter.calendar.JYearChooser jYearChooserAno;
    private javax.swing.JButton jbEnvio;
    private javax.swing.JButton jbListar;
    private javax.swing.JButton jbPesquisa;
    private javax.swing.JButton jbPesquisar;
    private com.toedter.calendar.JDateChooser jdDataPesquisaFim;
    private com.toedter.calendar.JDateChooser jdDataPesquisaInicio;
    private javax.swing.JLabel jlDataPesquisa;
    private javax.swing.JLabel jlFim;
    private javax.swing.JLabel jlInicio;
    private javax.swing.JLabel jlMat;
    private javax.swing.JRadioButton jrMatricula;
    private javax.swing.JRadioButton jrObservacao;
    private javax.swing.JTextField jtMatricula;
    private javax.swing.JTable jtTabela;
    // End of variables declaration//GEN-END:variables
}
