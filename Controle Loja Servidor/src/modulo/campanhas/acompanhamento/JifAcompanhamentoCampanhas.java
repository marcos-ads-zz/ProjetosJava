package modulo.campanhas.acompanhamento;

import java.awt.BorderLayout;
import modulo.versao.Versao;
import java.awt.Dimension;
import java.text.ParseException;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modulo.campanhas.meta.metaCampanha;
import modulo.campanhas.meta.MetasCampanhasDAO;
import modulo.dashboardD.DadosGraficos;
import modulo.metodos.Funcao;
import modulo.usuarios.Usuario;
import modulo.usuarios.UsuarioDAO;
import org.jfree.chart.ChartPanel;

/**
 *
 * @author Marcos Junior
 */
public class JifAcompanhamentoCampanhas extends javax.swing.JInternalFrame {

    private int soma = 0;
    private Versao ver;
    private Funcao fun;
    private UsuarioDAO DAOUSER;
    private Usuario objUSER;
    private DadosGraficos dg;
    private CadastroCampanhaDia obj;
    private CadastroCampanhaDiaDAO CAMPDIA_DAO;
    private MetasCampanhasDAO CADCAMP_DAO;

    public JifAcompanhamentoCampanhas() {
        initComponents();
        ver = new Versao();
        fun = new Funcao();
        dg = new DadosGraficos();
        DAOUSER = new UsuarioDAO();
        CADCAMP_DAO = new MetasCampanhasDAO();
        CAMPDIA_DAO = new CadastroCampanhaDiaDAO();
        setTitle("Acompanhamento de Campanhas: " + ver.getVersao());
        exibeDados();
    }

    private void exibeDados() {
        try {
            listaCampanhasAtivas();
            PreencheTabelaDaView();
            jListCampanhas.setSelectedIndex(0);
            painelGraficoRuptura(jListCampanhas.getSelectedValue().toUpperCase());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Carregar Lista: " + ex.getMessage());
        }
    }

    private void limparCampos() {
        jtId.setText("");
        jcCampanha.setSelectedIndex(0);
        jtMatricula.setText("");
        jfQuantidadeVendida.setText("");
        jfDataRegistro.setText("");
        jtNomeUsuario.setText("");
    }

    public void Habilita() {
        jcCampanha.setEnabled(true);
        jtMatricula.setEnabled(true);
        jfQuantidadeVendida.setEnabled(true);
        jfDataRegistro.setEnabled(true);
        jbSalvar.setEnabled(false);
        jbEditar.setEnabled(true);
        jbExcluir.setEnabled(true);
        jbCancelar.setEnabled(true);
    }

    private void CancelarELimpar() {
        limparCampos();
        jbSalvar.setEnabled(true);
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        jbCancelar.setEnabled(true);
    }

    public void painelGraficoRuptura(String campanha) throws PropertyVetoException, Exception {
        jPanelGrafico.removeAll();
        jPanelGrafico.add(new ChartPanel(dg.painelGraficoCampanhaQT(fun.primeiroDiaMesAtual(), fun.atualDateSQL(), campanha)), BorderLayout.CENTER);
        jPanelGrafico.validate();
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    private boolean Salvar() throws Exception {
        return CAMPDIA_DAO.Insert(obj);
    }

    private boolean Editar() throws Exception {
        return CAMPDIA_DAO.Update(obj);
    }

    private boolean Exluir() throws Exception {
        return CAMPDIA_DAO.Delete(Integer.parseInt(jtId.getText()));
    }

    private void verificaOpcaoSelecionada(KeyEvent ke, String test) {
        if (ke.getKeyCode() == KeyEvent.VK_TAB || ke.getKeyCode() == KeyEvent.VK_ENTER & "matri".equals(test)) {
            jcCampanha.requestFocus();
        }
        if (ke.getKeyCode() == KeyEvent.VK_ENTER & "campanha".equals(test)) {
            jfQuantidadeVendida.requestFocus();
        }
    }

    private void Pesquisar() {
        List<CadastroCampanhaDia> objr;
        try {
            String selecionado = jListCampanhas.getSelectedValue().toUpperCase();
            painelGraficoRuptura(selecionado);
            soma = 0;
            jfTotalHoje.setText("");
            jfDescricaoCampanhaACOMP.setText(selecionado);
            //objr = CAMPDIA_DAO.PesquisaNome(jcCampanha.getSelectedItem().toString().toUpperCase());
            objr = CAMPDIA_DAO.PesquisaNome(selecionado);
            //JOptionPane.showMessageDialog(this, "Campanha Selecionada: " + jListCampanhas.getSelectedValue().toUpperCase());
            PreencheTabelaDaViewNome(objr);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro Pesquisa: " + ex.getMessage());
        }
    }

    private boolean preencherObjetosSalvar() throws ParseException {
        obj = new CadastroCampanhaDia();
        obj.setMatricula(fun.convertToInt(jtMatricula.getText()));
        obj.setDesc_campanha(jcCampanha.getSelectedItem().toString().toUpperCase());
        if (jfQuantidadeVendida.getText().equals("")) {
            obj.setQuantidade(0);
        }
        obj.setQuantidade(fun.convertToInt(jfQuantidadeVendida.getText()));
        if (jfValorUltimaChance.getText().equals("")) {
            obj.setUltimaChance(0);
        }
        obj.setUltimaChance(fun.convertDoubleStringToDouble(jfValorUltimaChance.getText()));
        obj.setData_registro(fun.convertDateStringToDateSQL(jfDataRegistro.getText()));
        return obj != null;
    }

    private boolean preencherObjetosEditar() throws ParseException {
        obj = new CadastroCampanhaDia();
        obj.setId(Integer.parseInt(jtId.getText()));
        obj.setMatricula(fun.convertToInt(jtMatricula.getText()));
        obj.setDesc_campanha(jcCampanha.getSelectedItem().toString().toUpperCase());
        if (jfQuantidadeVendida.getText().equals("")) {
            obj.setQuantidade(0);
        }
        obj.setQuantidade(fun.convertToInt(jfQuantidadeVendida.getText()));
        if (jfValorUltimaChance.getText().equals("")) {
            obj.setUltimaChance(0);
        }
        obj.setUltimaChance(fun.getDoubledaString(jfValorUltimaChance.getText()));
        obj.setData_registro(fun.convertDateStringToDateSQL(jfDataRegistro.getText()));
        return obj != null;
    }

    private boolean validadeCampanha(java.sql.Date data) throws Exception {
        String a, b;
        a = fun.convertDataSQLToDateString(data);
        b = fun.convertDataSQLToDateString(fun.atualDateSQL());
        //System.out.println("Compare " + a + " com " + b + " Tipo " + (data.compareTo(fun.atualDateSQL())) + " Equal " + a.equals(b));
        return data.compareTo(fun.atualDateSQL()) == 1 || a.equals(b);
    }

    private void listaCampanhasAtivas() {
        DefaultListModel dlm = new DefaultListModel();
        List<metaCampanha> CadCamp;
        try {
            CadCamp = CADCAMP_DAO.TabelaPesquisaTodos();
            CadCamp.forEach((c) -> {
                try {
                    if (validadeCampanha(c.getData_fim())) {
                        jcCampanha.addItem(c.getDescricao_Campanha());
                        dlm.addElement(c.getDescricao_Campanha());
                    }
                } catch (Exception ex) {
                    Logger.getLogger(JifAcompanhamentoCampanhas.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Pesquisar Campanhas! " + ex);
        }
        jListCampanhas.setModel(dlm);
    }

    private void PreencheTabelaDaViewNome(List<CadastroCampanhaDia> campanha) throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jtTabela.getModel();
        modelo.setNumRows(0);
        campanha.forEach((p) -> {
            jfTotalHoje.setText(Integer.toString(soma += p.getQuantidade()));
            try {
                modelo.addRow(new Object[]{
                    p.getId(), p.getMatricula(), p.getDesc_campanha(), p.getQuantidade(),
                    p.getUltimaChance(), fun.convertDataSQLToDateString(p.getData_registro())
                });
            } catch (Exception ex) {
                Logger.getLogger(JifAcompanhamentoCampanhas.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    private void PreencheTabelaDaView() throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jtTabela.getModel();
        modelo.setNumRows(0);
        List<CadastroCampanhaDia> campanha = CAMPDIA_DAO.TabelaPesquisa();
        campanha.forEach((p) -> {
            try {
                modelo.addRow(new Object[]{
                    p.getId(), p.getMatricula(), p.getDesc_campanha(), p.getQuantidade(),
                    p.getUltimaChance(), fun.convertDataSQLToDateString(p.getData_registro())
                });
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao Pesquisar Campanhas! " + ex.getMessage());
            }
        });
    }

    private void PreencheTabelaDaViewDia() throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jtTabela.getModel();
        modelo.setNumRows(0);
        List<CadastroCampanhaDia> campanha = CAMPDIA_DAO.TabelaPesquisaDia(fun.atualDateSQL());
        campanha.forEach((p) -> {
            try {
                modelo.addRow(new Object[]{
                    p.getId(), p.getMatricula(), p.getDesc_campanha(), p.getQuantidade(),
                    p.getUltimaChance(), fun.convertDataSQLToDateString(p.getData_registro())
                });
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao Pesquisar Campanhas! " + ex.getMessage());
            }
        });
    }

    private void PreencheTabelaDaViewMes() throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jtTabela.getModel();
        modelo.setNumRows(0);
        List<CadastroCampanhaDia> campanha = CAMPDIA_DAO.TabelaPesquisaMes(fun.primeiroDiaMesAtual(), fun.atualDateSQL());
        campanha.forEach((p) -> {
            try {
                modelo.addRow(new Object[]{
                    p.getId(), p.getMatricula(), p.getDesc_campanha(), p.getQuantidade(),
                    p.getUltimaChance(), fun.convertDataSQLToDateString(p.getData_registro())
                });
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao Pesquisar Campanhas! " + ex.getMessage());
            }
        });
    }

    private void CorregaDadosParaOsCampos() throws ParseException, Exception {
        Habilita();
        if (jtTabela.getSelectedRow() != -1) {
            Object idRegistro, matricula, campanha, quantidade, dataRegistro;

            idRegistro = jtTabela.getValueAt(jtTabela.getSelectedRow(), 0);
            jtId.setText(idRegistro.toString());

            matricula = jtTabela.getValueAt(jtTabela.getSelectedRow(), 1);
            jtMatricula.setText(matricula.toString());

            campanha = jtTabela.getValueAt(jtTabela.getSelectedRow(), 2);
            jcCampanha.setSelectedItem(campanha);

            quantidade = jtTabela.getValueAt(jtTabela.getSelectedRow(), 3);
            jfQuantidadeVendida.setText(quantidade.toString());

            dataRegistro = jtTabela.getValueAt(jtTabela.getSelectedRow(), 4);
            jfDataRegistro.setText(dataRegistro.toString());

            jbEditar.setEnabled(true);
            jbExcluir.setEnabled(true);
        }
    }

    private boolean metodoDeBusca(String valor, JTextField NomeUser, JTextField campo) {
        boolean chek = false;
        try {
            if (fun.testaNumerosInteiros(valor)) {
                objUSER = DAOUSER.PesquisaMatriculaR(Integer.parseInt(valor));
                if (objUSER == null) {
                    NomeUser.setText("Não localizado!");
                    campo.requestFocus();
                    chek = false;
                } else {
                    NomeUser.setText(objUSER.getNome());
                    chek = true;
                }
            } else {
                NomeUser.setText("Não localizado!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Falha ao buscar: " + ex);
        }
        return chek;
    }

    public boolean pesquisarUsuarioNoBanco() {
        return metodoDeBusca(jtMatricula.getText(), jtNomeUsuario, jtMatricula);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtTabela = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtMatricula = new javax.swing.JFormattedTextField();
        jfQuantidadeVendida = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jfDataRegistro = new javax.swing.JFormattedTextField();
        jbSalvar = new javax.swing.JButton();
        jbEditar = new javax.swing.JButton();
        jbExcluir = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListCampanhas = new javax.swing.JList<>();
        jLabel8 = new javax.swing.JLabel();
        jfDescricaoCampanhaACOMP = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jfObs = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jfTotalHoje = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jfTotalMes = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        jfTotalTrimestre = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jfTotalSemestre = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        jfTotalAnual = new javax.swing.JFormattedTextField();
        jPanelGrafico = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jfDataRegistro1 = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        jtId = new javax.swing.JFormattedTextField();
        jcCampanha = new javax.swing.JComboBox<>();
        jbPesquisar = new javax.swing.JButton();
        jMonthChooserMes = new com.toedter.calendar.JMonthChooser();
        jbCancelar = new javax.swing.JButton();
        jtNomeUsuario = new javax.swing.JTextField();
        jRadioButtonMes = new javax.swing.JRadioButton();
        jRadioButtonDia = new javax.swing.JRadioButton();
        jfValorUltimaChance = new javax.swing.JFormattedTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(102, 255, 102));

        jtTabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "VENDEDOR", "CAMPANHA", "UNIDADE VENDIDA", "VALOR ÚLTIMA CHANCE", "DATA DO REGISTRO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtTabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtTabelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtTabela);
        if (jtTabela.getColumnModel().getColumnCount() > 0) {
            jtTabela.getColumnModel().getColumn(0).setMinWidth(60);
            jtTabela.getColumnModel().getColumn(0).setMaxWidth(60);
            jtTabela.getColumnModel().getColumn(1).setMinWidth(150);
            jtTabela.getColumnModel().getColumn(1).setMaxWidth(150);
            jtTabela.getColumnModel().getColumn(3).setMinWidth(150);
            jtTabela.getColumnModel().getColumn(3).setMaxWidth(150);
            jtTabela.getColumnModel().getColumn(4).setMinWidth(150);
            jtTabela.getColumnModel().getColumn(4).setMaxWidth(150);
            jtTabela.getColumnModel().getColumn(5).setMinWidth(200);
            jtTabela.getColumnModel().getColumn(5).setMaxWidth(200);
        }

        jLabel1.setText("Vendedor");

        jLabel2.setText("Quantidade Vendida");

        jtMatricula.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtMatricula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtMatriculaMouseClicked(evt);
            }
        });
        jtMatricula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtMatriculaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtMatriculaKeyReleased(evt);
            }
        });

        jfQuantidadeVendida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfQuantidadeVendida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfQuantidadeVendidaActionPerformed(evt);
            }
        });

        jLabel5.setText("Data do Registro");

        try {
            jfDataRegistro.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jfDataRegistro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfDataRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfDataRegistroActionPerformed(evt);
            }
        });

        jbSalvar.setText("Salvar");
        jbSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvarActionPerformed(evt);
            }
        });
        jbSalvar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jbSalvarKeyPressed(evt);
            }
        });

        jbEditar.setText("Editar");
        jbEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditarActionPerformed(evt);
            }
        });

        jbExcluir.setText("Excluir");
        jbExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExcluirActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acompanhamento Campanhas Ativas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 3, 18))); // NOI18N

        jListCampanhas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListCampanhasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jListCampanhas);

        jLabel8.setText("Descrição da Campanha");

        jfDescricaoCampanhaACOMP.setEditable(false);
        jfDescricaoCampanhaACOMP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfDescricaoCampanhaACOMP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfDescricaoCampanhaACOMP.setFont(new java.awt.Font("sansserif", 3, 12)); // NOI18N

        jLabel9.setText("Observação");

        jfObs.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfObs.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfObs.setEnabled(false);

        jLabel3.setText("Total Vendido Hoje");

        jfTotalHoje.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfTotalHoje.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfTotalHoje.setEnabled(false);
        jfTotalHoje.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Total Mês");

        jfTotalMes.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfTotalMes.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfTotalMes.setEnabled(false);
        jfTotalMes.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Total Trimeste");

        jfTotalTrimestre.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfTotalTrimestre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfTotalTrimestre.setEnabled(false);
        jfTotalTrimestre.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Total Semestre");

        jfTotalSemestre.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfTotalSemestre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfTotalSemestre.setEnabled(false);
        jfTotalSemestre.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Total Anual");

        jfTotalAnual.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfTotalAnual.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfTotalAnual.setEnabled(false);
        jfTotalAnual.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N

        jPanelGrafico.setBackground(new java.awt.Color(51, 255, 51));
        jPanelGrafico.setLayout(new java.awt.BorderLayout());

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Validade");

        try {
            jfDataRegistro1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jfDataRegistro1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfDataRegistro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfDataRegistro1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jfTotalHoje, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jfTotalMes, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jfDescricaoCampanhaACOMP))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jfTotalTrimestre, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jfTotalSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jfTotalAnual, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(36, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jfDataRegistro1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jfObs))))
                    .addComponent(jPanelGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jfDescricaoCampanhaACOMP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jfObs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jfDataRegistro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jfTotalHoje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jfTotalMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jfTotalTrimestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jfTotalSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jfTotalAnual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jPanelGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("ID");

        jtId.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jtId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtId.setEnabled(false);

        jcCampanha.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione Uma Campanha" }));
        jcCampanha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcCampanhaKeyPressed(evt);
            }
        });

        jbPesquisar.setText("Pesquisar");
        jbPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPesquisarActionPerformed(evt);
            }
        });

        jbCancelar.setText("Cancelar");
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
            }
        });

        jtNomeUsuario.setEditable(false);

        buttonGroup1.add(jRadioButtonMes);
        jRadioButtonMes.setText("Mostrar Registro de Campanhas do Mês Autal");
        jRadioButtonMes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButtonMesMouseClicked(evt);
            }
        });
        jRadioButtonMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMesActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonDia);
        jRadioButtonDia.setText("Mostrar Registro de Campanhas de Hoje");
        jRadioButtonDia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButtonDiaMouseClicked(evt);
            }
        });
        jRadioButtonDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonDiaActionPerformed(evt);
            }
        });

        jfValorUltimaChance.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfValorUltimaChance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfValorUltimaChanceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jtId, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtNomeUsuario))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jcCampanha, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jfValorUltimaChance, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jfQuantidadeVendida, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jfDataRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(63, 63, 63)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jMonthChooserMes, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jbPesquisar))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jbSalvar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jbEditar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jbCancelar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jbExcluir))))
                            .addComponent(jSeparator1)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jRadioButtonDia)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButtonMes))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jMonthChooserMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbPesquisar))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)
                        .addComponent(jtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jfQuantidadeVendida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jcCampanha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jfDataRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbSalvar)
                    .addComponent(jbEditar)
                    .addComponent(jbExcluir)
                    .addComponent(jbCancelar)
                    .addComponent(jfValorUltimaChance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonMes)
                    .addComponent(jRadioButtonDia))
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void jcCampanhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcCampanhaKeyPressed
        verificaOpcaoSelecionada(evt, "campanha");
    }//GEN-LAST:event_jcCampanhaKeyPressed

    private void jtTabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtTabelaMouseClicked
        try {
            CorregaDadosParaOsCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Corregar Dados Para os Campos: " + ex.getMessage());
        }
    }//GEN-LAST:event_jtTabelaMouseClicked

    private void jbSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarActionPerformed
        try {
            if (preencherObjetosSalvar()) {
                if (Salvar()) {
                    JOptionPane.showMessageDialog(this, "Item Salvo com Sucesso!");
                    CancelarELimpar();
                    PreencheTabelaDaView();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Não foi Possível Salvar!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Não foi Possível Salvar Erro: " + ex.getMessage());
        }
    }//GEN-LAST:event_jbSalvarActionPerformed

    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarActionPerformed
        try {
            if (preencherObjetosEditar()) {
                if (Editar()) {
                    JOptionPane.showMessageDialog(this, "Item Editado com Sucesso!");
                    CancelarELimpar();
                    PreencheTabelaDaView();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Não foi Possível Editar!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Não foi Possível Editar Erro: " + ex.getMessage());
        }
    }//GEN-LAST:event_jbEditarActionPerformed

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        CancelarELimpar();
    }//GEN-LAST:event_jbCancelarActionPerformed

    private void jbExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExcluirActionPerformed
        try {
            if (Exluir()) {
                PreencheTabelaDaView();
                JOptionPane.showMessageDialog(this, "Item Excluido com Sucesso!");
                CancelarELimpar();
            } else {
                JOptionPane.showMessageDialog(this, "Não foi Possível Exluir!");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Não foi Possível Exluir Erro: " + ex.getMessage());
        }
    }//GEN-LAST:event_jbExcluirActionPerformed

    private void jtMatriculaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtMatriculaKeyPressed
        verificaOpcaoSelecionada(evt, "matri");
    }//GEN-LAST:event_jtMatriculaKeyPressed

    private void jtMatriculaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtMatriculaKeyReleased
        pesquisarUsuarioNoBanco();
    }//GEN-LAST:event_jtMatriculaKeyReleased

    private void jtMatriculaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtMatriculaMouseClicked
        jtMatricula.setText("");
        jtNomeUsuario.setText("");
    }//GEN-LAST:event_jtMatriculaMouseClicked

    private void jfQuantidadeVendidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfQuantidadeVendidaActionPerformed
        jfDataRegistro.requestFocus();
    }//GEN-LAST:event_jfQuantidadeVendidaActionPerformed

    private void jfDataRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfDataRegistroActionPerformed
        jbSalvar.requestFocus();
    }//GEN-LAST:event_jfDataRegistroActionPerformed

    private void jListCampanhasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListCampanhasMouseClicked
        Pesquisar();
    }//GEN-LAST:event_jListCampanhasMouseClicked

    private void jRadioButtonDiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButtonDiaMouseClicked
        try {
            PreencheTabelaDaViewDia();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Não foi Possível Atualizar Tabela: " + ex.getMessage());
        }
    }//GEN-LAST:event_jRadioButtonDiaMouseClicked

    private void jRadioButtonMesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButtonMesMouseClicked
        try {
            PreencheTabelaDaViewMes();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Não foi Possível Atualizar Tabela: " + ex.getMessage());
        }
    }//GEN-LAST:event_jRadioButtonMesMouseClicked

    private void jRadioButtonDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonDiaActionPerformed
        try {
            PreencheTabelaDaViewDia();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Não foi Possível Atualizar Tabela: " + ex.getMessage());
        }
    }//GEN-LAST:event_jRadioButtonDiaActionPerformed

    private void jRadioButtonMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMesActionPerformed
        try {
            PreencheTabelaDaViewMes();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Não foi Possível Atualizar Tabela: " + ex.getMessage());
        }
    }//GEN-LAST:event_jRadioButtonMesActionPerformed

    private void jfDataRegistro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfDataRegistro1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jfDataRegistro1ActionPerformed

    private void jbPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPesquisarActionPerformed
        try {
            CAMPDIA_DAO.PesquisaNomeGraficoCampanha(fun.atualDateSQL(), fun.atualDateSQL(), "BALANCE").forEach((p) -> {
                System.out.println("Teste " + p.getDesc_campanha() + " " + p.getQuantidade());

            });
        } catch (Exception ex) {
            Logger.getLogger(JifAcompanhamentoCampanhas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jbPesquisarActionPerformed

    private void jbSalvarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jbSalvarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbSalvarKeyPressed

    private void jfValorUltimaChanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfValorUltimaChanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jfValorUltimaChanceActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jListCampanhas;
    private com.toedter.calendar.JMonthChooser jMonthChooserMes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelGrafico;
    private javax.swing.JRadioButton jRadioButtonDia;
    private javax.swing.JRadioButton jRadioButtonMes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JButton jbPesquisar;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JComboBox<String> jcCampanha;
    private javax.swing.JFormattedTextField jfDataRegistro;
    private javax.swing.JFormattedTextField jfDataRegistro1;
    private javax.swing.JFormattedTextField jfDescricaoCampanhaACOMP;
    private javax.swing.JFormattedTextField jfObs;
    private javax.swing.JFormattedTextField jfQuantidadeVendida;
    private javax.swing.JFormattedTextField jfTotalAnual;
    private javax.swing.JFormattedTextField jfTotalHoje;
    private javax.swing.JFormattedTextField jfTotalMes;
    private javax.swing.JFormattedTextField jfTotalSemestre;
    private javax.swing.JFormattedTextField jfTotalTrimestre;
    private javax.swing.JFormattedTextField jfValorUltimaChance;
    private javax.swing.JFormattedTextField jtId;
    private javax.swing.JFormattedTextField jtMatricula;
    private javax.swing.JTextField jtNomeUsuario;
    private javax.swing.JTable jtTabela;
    // End of variables declaration//GEN-END:variables
}
