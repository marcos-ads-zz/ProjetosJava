package modulo.campanhas.meta;

import modulo.versao.Versao;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modulo.campanhas.cadastro.CadastroDescricaoCampanhas;
import modulo.metodos.Funcao;

/**
 * @author Marcos Junior
 */
public final class JifCadastroMetasCampanhas extends javax.swing.JInternalFrame {

    private int acao;
    private CadastroMetasCampanhasDAO CAMPDAO;
    private CadastroMetasCampanhas obj;
    private Funcao fun;
    private Versao ver;
    private JTextField[] campanha1;
    private JFormattedTextField[] campanha2;

    public JifCadastroMetasCampanhas() {
        initComponents();
        fun = new Funcao();
        ver = new Versao();
        CAMPDAO = new CadastroMetasCampanhasDAO();
        Thread relogioThred = new Thread(new JifCadastroMetasCampanhas.clsDataHora());
        relogioThred.start();
        setTitle("Cadastro de Metas Para Produtos de Campanhas: " + ver.getVersao());
        Cancelar();
        criarUmVetorDeCampos();
        exibeDados();
    }

    private void exibeDados() {
        try {
            carregaLista();
            PreencheTabelaDaView();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Carregar Lista: " + ex.getMessage());
        }
    }

    public void limparCampos() {
        jtId.setText("");
        jcCampanha.setSelectedIndex(0);
        jtDataFim.setText("");
        jtDataInicio.setText("");
        jtMetaAnual.setText("");
        jtMetaMes.setText("");
        jtMetaSemestre.setText("");
        jtMetaTrimestre.setText("");
        jtObservacao.setText("");
    }

    public void HabilitaEdicao() {
        jcCampanha.setEnabled(true);
        jtDataFim.setEnabled(true);
        jtDataInicio.setEnabled(true);
        jtMetaAnual.setEnabled(true);
        jtMetaMes.setEnabled(true);
        jtMetaSemestre.setEnabled(true);
        jtMetaTrimestre.setEnabled(true);
        jtObservacao.setEnabled(true);
    }

    public void Cancelar() {
        limparCampos();
        jcCampanha.setEnabled(false);
        jtDataFim.setEnabled(false);
        jtDataInicio.setEnabled(false);
        jtMetaAnual.setEnabled(false);
        jtMetaMes.setEnabled(false);
        jtMetaSemestre.setEnabled(false);
        jtMetaTrimestre.setEnabled(false);
        jtObservacao.setEnabled(false);

        jbNovo.setEnabled(true);
        jbSalvar.setEnabled(false);
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        jbCancelar.setEnabled(true);
        acao = 0;
    }

    public void Novo() {
        limparCampos();
        jcCampanha.setEnabled(true);
        jtDataFim.setEnabled(true);
        jtDataInicio.setEnabled(true);
        jtMetaAnual.setEnabled(true);
        jtMetaMes.setEnabled(true);
        jtMetaSemestre.setEnabled(true);
        jtMetaTrimestre.setEnabled(true);
        jtObservacao.setEnabled(true);
        jbNovo.setEnabled(false);
        jbSalvar.setEnabled(true);
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        jbCancelar.setEnabled(true);
        acao = 1;
    }

//    private boolean validarCampos() {
//        boolean check = true;
//
//        for (JFormattedTextField camp : campanha2) {
//            if (camp.getText().equals("") || camp.getText().equals("0")) {
//                camp.setText("0");
//                camp.setBackground(new java.awt.Color(0, 0, 255));
//            } else {
//                camp.setBackground(new java.awt.Color(255, 255, 255));
//                check = false;
//            }
//        }
//        for (JTextField camp : campanha1) {
//            if (camp.getText().equals("") || camp.getText().equals("0")) {
//                camp.setText("0");
//                camp.setBackground(new java.awt.Color(0, 0, 255));
//            } else {
//                camp.setBackground(new java.awt.Color(255, 255, 255));
//                check = false;
//            }
//        }
//        return check;
//    }
    private void criarUmVetorDeCampos() {//Função Secundária
        campanha1 = new JTextField[]{
            jtMetaAnual,
            jtMetaMes,
            jtMetaSemestre,
            jtMetaTrimestre,
            jtObservacao};

        campanha2 = new JFormattedTextField[]{
            jtDataFim,
            jtDataInicio};

    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    public boolean Salvar() throws Exception {
        return CAMPDAO.Insert(obj);
    }

    public boolean Editar() throws Exception {
        return CAMPDAO.Update(obj);
    }

    public boolean Exluir() throws Exception {
        return CAMPDAO.Delete(Integer.parseInt(jtId.getText()));
    }
    int cont = 0;

    public void Pesquisar() {
        List<CadastroMetasCampanhas> obj;
        try {
            obj = CAMPDAO.PesquisaNome(jcCampanha.getSelectedItem().toString().toUpperCase());
            PreencheTabelaDaViewNome(obj);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro Pesquisa: " + ex.getMessage());

        }

    }

    class clsDataHora implements Runnable {

        @Override
        public void run() {
            while (true) {
                SetValores();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println("Thread não foi finalizada:" + ex);
                }
            }
        }
    }

    public void SetValores() {
        jtDataRegistro.setText(fun.dataAtualDate());
    }

    public boolean preencherObjetosSalvar() throws ParseException {
        obj = new CadastroMetasCampanhas();
        obj.setDescricao_Campanha(jcCampanha.getSelectedItem().toString().toUpperCase());
        obj.setData_fim(fun.convertDateStringToDateSQL(jtDataFim.getText()));
        obj.setData_inicio(fun.convertDateStringToDateSQL(jtDataInicio.getText()));
        obj.setMeta_mes(fun.convertToInt(jtMetaMes.getText()));
        obj.setMeta_trimestre(fun.convertToInt(jtMetaTrimestre.getText()));
        obj.setMeta_semestre(fun.convertToInt(jtMetaSemestre.getText()));
        obj.setMeta_anual(fun.convertToInt(jtMetaAnual.getText()));
        obj.setData_registro(fun.convertDateStringToDateSQL(jtDataRegistro.getText()));
        obj.setObs(jtObservacao.getText().toUpperCase());
        return obj != null;
    }

    public boolean preencherObjetosEditar() throws ParseException {
        obj = new CadastroMetasCampanhas();
        obj.setId(Integer.parseInt(jtId.getText()));
        obj.setDescricao_Campanha(jcCampanha.getSelectedItem().toString().toUpperCase());
        obj.setData_fim(fun.convertDateStringToDateSQL(jtDataFim.getText()));
        obj.setData_inicio(fun.convertDateStringToDateSQL(jtDataInicio.getText()));
        obj.setMeta_mes(fun.convertToInt(jtMetaMes.getText()));
        obj.setMeta_trimestre(fun.convertToInt(jtMetaTrimestre.getText()));
        obj.setMeta_semestre(fun.convertToInt(jtMetaSemestre.getText()));
        obj.setMeta_anual(fun.convertToInt(jtMetaAnual.getText()));
        obj.setData_registro(fun.convertDateStringToDateSQL(jtDataRegistro.getText()));
        obj.setObs(jtObservacao.getText().toUpperCase());
        return obj != null;
    }

    private void carregaLista() throws Exception {
        List<CadastroDescricaoCampanhas> campanha = CAMPDAO.TabelaPesquisaDescricao();
        campanha.forEach((p) -> {
            jcCampanha.addItem(p.getDescricaoCampanha());
        });
    }

    public void PreencheTabelaDaView() throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jtTabela.getModel();
        modelo.setNumRows(0);
        List<CadastroMetasCampanhas> campanha = CAMPDAO.TabelaPesquisa();
        campanha.forEach((p) -> {
            try {
                modelo.addRow(new Object[]{
                    p.getId(), p.getDescricao_Campanha(), fun.convertDataSQLToDateString(p.getData_inicio()), fun.convertDataSQLToDateString(p.getData_fim()),
                    p.getMeta_mes(), p.getMeta_trimestre(), p.getMeta_semestre(), p.getMeta_anual(),
                    fun.convertDataSQLToDateString(p.getData_registro()), p.getObs()
                });
            } catch (Exception ex) {
                Logger.getLogger(JifCadastroMetasCampanhas.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void PreencheTabelaDaViewNome(List<CadastroMetasCampanhas> campanha) throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jtTabela.getModel();
        modelo.setNumRows(0);
        if (acao != 1) {
            campanha.forEach((p) -> {
                try {
                    modelo.addRow(new Object[]{
                        p.getId(), p.getDescricao_Campanha(), fun.convertDataSQLToDateString(p.getData_inicio()), fun.convertDataSQLToDateString(p.getData_fim()),
                        p.getMeta_mes(), p.getMeta_trimestre(), p.getMeta_semestre(), p.getMeta_anual(),
                        fun.convertDataSQLToDateString(p.getData_registro()), p.getObs()
                    });
                } catch (Exception ex) {
                    Logger.getLogger(JifCadastroMetasCampanhas.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
    }

    private void CorregaDadosParaOsCampos() throws ParseException, Exception {
        HabilitaEdicao();
        if (jtTabela.getSelectedRow() != -1) {
            Object idRegistro, desc, dataFim, dataInic, metaMes, metaTri,
                    metaSem, metaAnual, dataRegistro, obs;

            idRegistro = jtTabela.getValueAt(jtTabela.getSelectedRow(), 0);
            jtId.setText(idRegistro.toString());

            desc = jtTabela.getValueAt(jtTabela.getSelectedRow(), 1);
            jcCampanha.setSelectedItem(desc);

            dataInic = jtTabela.getValueAt(jtTabela.getSelectedRow(), 2);
            jtDataInicio.setText(dataInic.toString());

            dataFim = jtTabela.getValueAt(jtTabela.getSelectedRow(), 3);
            jtDataFim.setText(dataFim.toString());

            metaMes = jtTabela.getValueAt(jtTabela.getSelectedRow(), 4);
            jtMetaMes.setText(metaMes.toString());

            metaTri = jtTabela.getValueAt(jtTabela.getSelectedRow(), 5);
            jtMetaTrimestre.setText(metaTri.toString());

            metaSem = jtTabela.getValueAt(jtTabela.getSelectedRow(), 6);
            jtMetaSemestre.setText(metaSem.toString());

            metaAnual = jtTabela.getValueAt(jtTabela.getSelectedRow(), 7);
            jtMetaAnual.setText(metaAnual.toString());

            dataRegistro = jtTabela.getValueAt(jtTabela.getSelectedRow(), 8);
            jtDataRegistro.setText(dataRegistro.toString());

            obs = jtTabela.getValueAt(jtTabela.getSelectedRow(), 9);
            jtObservacao.setText(obs.toString());

            jbEditar.setEnabled(true);
            jbExcluir.setEnabled(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtId = new javax.swing.JTextField();
        jbNovo = new javax.swing.JButton();
        jbSalvar = new javax.swing.JButton();
        jbEditar = new javax.swing.JButton();
        jbExcluir = new javax.swing.JButton();
        jbCancelar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtTabela = new javax.swing.JTable();
        jbAtualizar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtMetaMes = new javax.swing.JTextField();
        jtDataRegistro = new javax.swing.JFormattedTextField();
        jtObservacao = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtDataInicio = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jtMetaTrimestre = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jtMetaSemestre = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jtMetaAnual = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jtDataFim = new javax.swing.JFormattedTextField();
        jcCampanha = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(153, 204, 0));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel1.setText("ID");

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Descrição");

        jtId.setEnabled(false);

        jbNovo.setText("Novo");
        jbNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNovoActionPerformed(evt);
            }
        });

        jbSalvar.setText("Salvar");
        jbSalvar.setEnabled(false);
        jbSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvarActionPerformed(evt);
            }
        });

        jbEditar.setText("Editar");
        jbEditar.setEnabled(false);
        jbEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditarActionPerformed(evt);
            }
        });

        jbExcluir.setText("Excluir");
        jbExcluir.setEnabled(false);
        jbExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExcluirActionPerformed(evt);
            }
        });

        jbCancelar.setText("Cancelar");
        jbCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
            }
        });

        jtTabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Descrição", "Data Inicio", "Data Fim", "Meta Mês", "Meta Tri", "Meta Sem", "Meta Anual", "Data Reg", "Obs"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
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
        jtTabela.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtTabelaKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jtTabela);
        if (jtTabela.getColumnModel().getColumnCount() > 0) {
            jtTabela.getColumnModel().getColumn(0).setMaxWidth(70);
        }

        jbAtualizar.setText("Listar");
        jbAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAtualizarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel2.setText("Data Inicio");

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel3.setText("Meta Mês");

        jtMetaMes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtMetaMesKeyPressed(evt);
            }
        });

        jtDataRegistro.setEditable(false);
        try {
            jtDataRegistro.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtDataRegistro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtDataRegistro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtDataRegistroKeyPressed(evt);
            }
        });

        jtObservacao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtObservacaoKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Observação");

        try {
            jtDataInicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtDataInicio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtDataInicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtDataInicioKeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel9.setText("Data Fim");

        jtMetaTrimestre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtMetaTrimestreKeyPressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel10.setText("Meta Trimestre");

        jtMetaSemestre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtMetaSemestreKeyPressed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel11.setText("Meta Semestre");

        jLabel12.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel12.setText("Meta Anual");

        jtMetaAnual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtMetaAnualKeyPressed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel13.setText("Data de Registro");

        try {
            jtDataFim.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtDataFim.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtDataFim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtDataFimKeyPressed(evt);
            }
        });

        jcCampanha.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione Uma Campanha" }));
        jcCampanha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcCampanhaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(jbAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addComponent(jLabel3)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtObservacao)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtId, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jtMetaMes, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jtMetaTrimestre, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jtMetaSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jtMetaAnual, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jcCampanha, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jtDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jtDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(24, 24, 24)
                                            .addComponent(jLabel13)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jtDataRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jcCampanha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(jtDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtDataRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel9)
                        .addComponent(jtDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtMetaMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jtMetaTrimestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jtMetaSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jtMetaAnual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jtObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbCancelar)
                    .addComponent(jbExcluir)
                    .addComponent(jbAtualizar)
                    .addComponent(jbEditar)
                    .addComponent(jbSalvar)
                    .addComponent(jbNovo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void jbNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNovoActionPerformed
        Novo();
    }//GEN-LAST:event_jbNovoActionPerformed

    private void jbSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarActionPerformed
        try {
            if (preencherObjetosSalvar()) {
                if (Salvar()) {
                    JOptionPane.showMessageDialog(this, "Salvo Com Sucesso!");
                    Cancelar();
                    PreencheTabelaDaView();
                } else {
                    JOptionPane.showMessageDialog(this, "Não Foi Possível Salvar!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Verifique os dados digitados!");
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(this, "Erro ao Salvar: " + erro.getMessage());
        }
    }//GEN-LAST:event_jbSalvarActionPerformed

    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarActionPerformed
        try {
            if (!jtId.getText().equals("")) {
                if (preencherObjetosEditar()) {
                    if (Editar()) {
                        JOptionPane.showMessageDialog(this, "Editado Com Sucesso!");
                        Pesquisar();
                        Cancelar();
                    } else {
                        JOptionPane.showMessageDialog(this, "Não Foi Possível Editar o Registro!");
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro!" + e.getMessage());
        }
    }//GEN-LAST:event_jbEditarActionPerformed

    private void jbExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExcluirActionPerformed
        try {
            if (!jtId.getText().equals("")) {
                if (Exluir()) {
                    JOptionPane.showMessageDialog(this, "Registro Excluido com Sucesso");
                    Cancelar();
                    PreencheTabelaDaView();
                } else {
                    JOptionPane.showMessageDialog(this, "Não Foi Possível Excluir o Registro");
                }
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(this, "Erro: " + erro.getMessage());
        }
    }//GEN-LAST:event_jbExcluirActionPerformed

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        Cancelar();
    }//GEN-LAST:event_jbCancelarActionPerformed

    private void jtTabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtTabelaMouseClicked
        try {
            CorregaDadosParaOsCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao CorregaDadosParaOsCampos: " + ex.getMessage());
        }
    }//GEN-LAST:event_jtTabelaMouseClicked

    private void jtTabelaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtTabelaKeyReleased
        try {
            CorregaDadosParaOsCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao CorregaDadosParaOsCampos: " + ex.getMessage());
        }
    }//GEN-LAST:event_jtTabelaKeyReleased

    private void jbAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAtualizarActionPerformed
        acao = 1;
        try {
            PreencheTabelaDaView();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Iniciar readTable." + ex.getMessage());
        }
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
    }//GEN-LAST:event_jbAtualizarActionPerformed

    private void jtObservacaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtObservacaoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jbSalvar.requestFocus();
        }
    }//GEN-LAST:event_jtObservacaoKeyPressed

    private void jcCampanhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcCampanhaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jtDataInicio.requestFocus();
        }
    }//GEN-LAST:event_jcCampanhaKeyPressed

    private void jtDataInicioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtDataInicioKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jtDataFim.requestFocus();
        }
    }//GEN-LAST:event_jtDataInicioKeyPressed

    private void jtDataFimKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtDataFimKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jtDataRegistro.requestFocus();
        }
    }//GEN-LAST:event_jtDataFimKeyPressed

    private void jtDataRegistroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtDataRegistroKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jtMetaMes.requestFocus();
        }
    }//GEN-LAST:event_jtDataRegistroKeyPressed

    private void jtMetaMesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtMetaMesKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jtMetaTrimestre.requestFocus();
        }
    }//GEN-LAST:event_jtMetaMesKeyPressed

    private void jtMetaTrimestreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtMetaTrimestreKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jtMetaSemestre.requestFocus();
        }
    }//GEN-LAST:event_jtMetaTrimestreKeyPressed

    private void jtMetaSemestreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtMetaSemestreKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jtMetaAnual.requestFocus();
        }
    }//GEN-LAST:event_jtMetaSemestreKeyPressed

    private void jtMetaAnualKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtMetaAnualKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jtObservacao.requestFocus();
        }
    }//GEN-LAST:event_jtMetaAnualKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbAtualizar;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JButton jbNovo;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JComboBox<String> jcCampanha;
    private javax.swing.JFormattedTextField jtDataFim;
    private javax.swing.JFormattedTextField jtDataInicio;
    private javax.swing.JFormattedTextField jtDataRegistro;
    private javax.swing.JTextField jtId;
    private javax.swing.JTextField jtMetaAnual;
    private javax.swing.JTextField jtMetaMes;
    private javax.swing.JTextField jtMetaSemestre;
    private javax.swing.JTextField jtMetaTrimestre;
    private javax.swing.JTextField jtObservacao;
    private javax.swing.JTable jtTabela;
    // End of variables declaration//GEN-END:variables
}
