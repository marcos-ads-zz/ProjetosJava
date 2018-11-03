package modulo.campanhas.relatorio;

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
import modulo.campanhas.meta.metaCampanha;
import modulo.campanhas.meta.MetasCampanhasDAO;
import modulo.campanhas.acompanhamento.CadastroCampanhaDia;
import modulo.campanhas.acompanhamento.JifAcompanhamentoCampanhas;
import modulo.campanhas.acompanhamento.graficosCampanha;
import modulo.metodos.Funcao;
import modulo.versao.Versao;
import modulo.view.principal.JfPrincipal;
import modulo.metodos.JifCarregamento;
import org.jfree.chart.ChartPanel;

/**
 *
 * @author Marcos Junior
 */
public final class JifGeraRelatorios extends javax.swing.JInternalFrame {

    private CadastroCampanhaDia cadCamp;
    private MetasCampanhasDAO CADCAMP_DAO;
    private relatorioCampDAO DAOREL;
    private graficosCampanha grfCamp;
    private UsuarioDAO DAOUSER;
    private Usuario objUSER;
    private JasperDAO rl;
    private Funcao fun;
    private dataFrames fram;
    DateFormat formatoHora;
    DateFormat formatoDIA;
    private JifCarregamento jfC = null;
    private JifImprime jifRel = null;
    private Versao ver;
    private int numLoja;

    public JifGeraRelatorios() {
        initComponents();
        DAOUSER = new UsuarioDAO();
        grfCamp = new graficosCampanha();
        fun = new Funcao();
        ver = new Versao();
        rl = new JasperDAO();
        DAOREL = new relatorioCampDAO();
        CADCAMP_DAO = new MetasCampanhasDAO();
        Thread relogioThred = new Thread(new JifGeraRelatorios.clsDataHora());
        relogioThred.start();
        setTitle("Relatórios de Campanhas: " + ver.getVersao());
        carregaDatasCampos();
        exibeDados();
    }

    private void carregaDatasCampos() {
        //jdDataPesquisaInicio.setDate(fun.primeiroDiaMesAtual());
        jdDataPesquisaInicio.setDate(new Date());
        jdDataPesquisaFim.setDate(new Date());
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
        String u = jListCampanhas.getSelectedItem().toString();
        if (u.equals("ÚLTIMA CHANCE")) {
            int uc1 = 0;
            jPanelGrafico.removeAll();
            jPanelGrafico.add(new ChartPanel(grfCamp.painelGraficoCampanhas(matricula, campanha, anoY(), uc1)), BorderLayout.CENTER);
            jPanelGrafico.validate();
        } else {
            int uc2 = 1;
            jPanelGrafico.removeAll();
            jPanelGrafico.add(new ChartPanel(grfCamp.painelGraficoCampanhas(matricula, campanha, anoY(), uc2)), BorderLayout.CENTER);
            jPanelGrafico.validate();
        }
    }

    private void PesquisaRegistros() {
        String a, b;
        a = jtMatricula.getText();
        b = jListCampanhas.getSelectedItem().toString();
        if (!jtMatricula.getText().equals("") || jListCampanhas.getSelectedItem().toString().equals("")) {
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
        } else {
            JOptionPane.showMessageDialog(this, "Há Campos não preenchidos!");
        }
    }

    private void preencheTabela(List<CadastroCampanhaDia> cadlist) {
        DefaultTableModel modelo = (DefaultTableModel) jtTabela.getModel();
        modelo.setNumRows(0);
        cadlist.forEach((p) -> {
            modelo.addRow(new Object[]{
                p.getId(), p.getMatricula(), p.getDesc_campanha(),
                p.getQuantidade(), p.getUltimaChance(), p.getData_registro()
            });
        });
    }

    private void listaCampanhasAtivas() {
        DefaultListModel dlm = new DefaultListModel();
        List<metaCampanha> CadCamp;
        try {
            CadCamp = CADCAMP_DAO.TabelaPesquisaTodos();
            CadCamp.forEach((c) -> {
                try {
                    if (validadeCampanha(c.getData_fim())) {
                        jListCampanhas.addItem(c.getDescricao_Campanha());
                        dlm.addElement(c.getDescricao_Campanha());
                    }
                } catch (Exception ex) {
                    Logger.getLogger(JifAcompanhamentoCampanhas.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Pesquisar Campanhas! " + ex);
        }
    }

    public java.sql.Date RInicio() {
        Date data1 = jdDataPesquisaInicio.getDate();
        System.out.println("RInicio(): " + data1);
        fram.setDataInicio(fun.convertDateToDateSql(data1));

        return fun.convertDateToDateSql(data1);
    }

    public void RMatri() {
        if (jtMatricula.getText().equals("")) {
            fram.setMatricula(0);
        } else {
            int matri = Integer.parseInt(jtMatricula.getText());
            fram.setMatricula(matri);
        }
    }

    public java.sql.Date RFim() {
        Date data2 = jdDataPesquisaFim.getDate();
        fram.setDataFim(fun.convertDateToDateSql(data2));
        //System.out.println("modulo.relatorio.JifGeraRelatorios.RFim()" + data2);
        return fun.convertDateToDateSql(data2);
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

    private void abreTelaDeTexto() {
        if (jifRel == null) {
            jifRel = new JifImprime();
            JfPrincipal.jDesktopPrincipal.add(jifRel);
            jifRel.setVisible(true);
            jifRel.setPosicao();
        } else if (!jifRel.isVisible()) {
            jifRel = new JifImprime();
            JfPrincipal.jDesktopPrincipal.add(jifRel);
            jifRel.setVisible(true);
            jifRel.setPosicao();
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
        jPanelGrafico = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtTabela = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jbEnvio = new javax.swing.JButton();
        jbPesquisa = new javax.swing.JButton();
        jbPesquisar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jYearChooserAno = new com.toedter.calendar.JYearChooser();
        jlInicio = new javax.swing.JLabel();
        jdDataPesquisaInicio = new com.toedter.calendar.JDateChooser("dd/MM/yyyy", "##/##/#####", '-');
        jlFim = new javax.swing.JLabel();
        jdDataPesquisaFim = new com.toedter.calendar.JDateChooser("dd/MM/yyyy", "##/##/#####", '-');
        jListCampanhas = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jtMatricula = new javax.swing.JTextField();
        jlMat = new javax.swing.JLabel();
        jrMatricula = new javax.swing.JRadioButton();
        jrObservacao = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jlDataPesquisa = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        jPanelGrafico.setBackground(new java.awt.Color(51, 255, 51));
        jPanelGrafico.setLayout(new java.awt.BorderLayout());

        jtTabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "VENDEDOR", "CAMPANHA", "VENDA", "VENDA ÚLTIMA CHANCE", "DATA DO REGISTRO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtTabela);

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jbEnvio.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jbEnvio.setText("Gerar Parcial");
        jbEnvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEnvioActionPerformed(evt);
            }
        });

        jbPesquisa.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jbPesquisa.setText("Imprimir a Pesquisa");
        jbPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPesquisaActionPerformed(evt);
            }
        });

        jbPesquisar.setBackground(new java.awt.Color(255, 0, 0));
        jbPesquisar.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jbPesquisar.setForeground(new java.awt.Color(255, 255, 255));
        jbPesquisar.setText("Pesquisar");
        jbPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPesquisarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("sansserif", 3, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 51, 0));
        jLabel6.setText("Ano do Gráfico");

        jlInicio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlInicio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlInicio.setText("Data Inicio");

        jdDataPesquisaInicio.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N

        jlFim.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlFim.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlFim.setText("Data Fim");

        jdDataPesquisaFim.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N

        jListCampanhas.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jListCampanhas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione Uma Campanha" }));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Campanha");

        jtMatricula.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jtMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtMatriculaActionPerformed(evt);
            }
        });

        jlMat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlMat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlMat.setText("Matrícula");

        GrupoDeRadioButoes.add(jrMatricula);
        jrMatricula.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jrMatricula.setSelected(true);
        jrMatricula.setText("Pesquisar por matrícula");
        jrMatricula.setActionCommand("matricula");

        GrupoDeRadioButoes.add(jrObservacao);
        jrObservacao.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jrObservacao.setText("Pesquisar por Campanha");
        jrObservacao.setActionCommand("campanha");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jrMatricula)
                        .addGap(18, 18, 18)
                        .addComponent(jrObservacao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbPesquisa))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jlMat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jListCampanhas, 0, 227, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jYearChooserAno, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlInicio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdDataPesquisaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlFim)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jdDataPesquisaFim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbEnvio, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jdDataPesquisaFim, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jdDataPesquisaInicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlFim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jListCampanhas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(jtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlMat, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jYearChooserAno, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jrMatricula)
                        .addComponent(jrObservacao))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbEnvio)
                        .addComponent(jbPesquisa)
                        .addComponent(jbPesquisar)))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(51, 51, 255));
        jTextField1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Meta Dia");

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

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Venda Dia");

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(255, 0, 0));
        jTextField2.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField4.setEditable(false);
        jTextField4.setBackground(new java.awt.Color(255, 0, 0));
        jTextField4.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField3)
                    .addComponent(jTextField1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField4)
                    .addComponent(jTextField2))
                .addGap(253, 253, 253))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9))
        );

        jPanel4.setBackground(new java.awt.Color(153, 153, 0));

        jlDataPesquisa.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jlDataPesquisa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlDataPesquisa.setText("Filtros");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlDataPesquisa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlDataPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanelGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanelGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE))
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

    }//GEN-LAST:event_jbPesquisaActionPerformed

    private void jbEnvioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEnvioActionPerformed
        Carregando();

        Thread t = new Thread() {
            @Override
            public void run() {
                RInicio();
                RFim();
                RMatri();
                abreTelaDeTexto();
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelGrafico;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private com.toedter.calendar.JYearChooser jYearChooserAno;
    private javax.swing.JButton jbEnvio;
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
