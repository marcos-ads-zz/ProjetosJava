package modulo.faceamento;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modulo.produtos.ProdutoDAO;
import modulo.usuarios.UsuarioDAO;
import modulo.usuarios.Usuario;
import modulo.produtos.Produto;
import modulo.jasper.JasperDAO;
import java.awt.Dimension;
import java.util.List;
import modulo.metodos.Funcao;
import modulo.versao.Versao;
import modulo.view.principal.JfPrincipal;
import modulo.metodos.JifCarregamento;

/**
 *
 * @author Marcos Junior
 */
public final class JifRelatorioFaltas extends javax.swing.JInternalFrame {

    private ListaDeRupturaDAO DAOLISTA;
    private ListaDeRuptura objLista;
    private UsuarioDAO DAOUSER;
    private Usuario objUSER;
    private ProdutoDAO DAOPRO;
    private Produto objPRO;
    private JasperDAO rl;
    private Funcao fun;
    DateFormat formatoHora;
    DateFormat formatoDIA;
    private JifCarregamento jfC = null;
    private Versao ver;
    private int acao, numeroLoja = 340;

    public JifRelatorioFaltas() {
        initComponents();
        DAOUSER = new UsuarioDAO();
        DAOPRO = new ProdutoDAO();
        DAOLISTA = new ListaDeRupturaDAO();
        fun = new Funcao();
        ver = new Versao();
        rl = new JasperDAO();
        this.formatoHora = new SimpleDateFormat("HH:mm:ss");
        this.formatoDIA = new SimpleDateFormat("dd/MM/yyyy");
        Thread relogioThred = new Thread(new JifRelatorioFaltas.clsDataHora());
        relogioThred.start();
        setTitle("Relatórios de Faltas: " + ver.getVersao());
        jdDataPesquisaInicio.setDate(new Date());
        jdDataPesquisaFim.setDate(new Date());
        CarregaLoja();
    }

    public void limparCampo() {
        jtCod_Interno.setText("");
        jtDescricaoItem.setText("");
        jtMatricula.setText("");
        jtObservacao.setSelectedIndex(0);
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    public void CarregaLoja() {
        try {
            jtLoja.setText(Integer.toString(numeroLoja));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Iniciar readTable Matricula." + ex.getMessage());
        }
    }

    public int validarCampoNovoRelatorio() {
        String opcao = GrupoDeRadioButoes.getSelection().getActionCommand();
        int chek = 0;
        if ("matricula".equals(opcao)) {
            jtMatricula.setEnabled(true);
            jtCod_Interno.setEnabled(false);
            jtDescricaoItem.setEnabled(false);
            jtObservacao.setEnabled(false);
            //jtMatricula.requestFocus();
            chek = 1;
        }
        if ("interno".equals(opcao)) {
            jtMatricula.setEnabled(false);
            jtCod_Interno.setEnabled(true);
            jtDescricaoItem.setEnabled(false);
            jtObservacao.setEnabled(false);
            //jtCod_Interno.requestFocus();
            chek = 2;
        }
        if ("descricao".equals(opcao)) {
            jtMatricula.setEnabled(false);
            jtCod_Interno.setEnabled(false);
            jtDescricaoItem.setEnabled(true);
            jtObservacao.setEnabled(false);
            //jtDescricaoItem.requestFocus();
            chek = 3;
        }
        if ("observacao".equals(opcao)) {
            jtMatricula.setEnabled(false);
            jtCod_Interno.setEnabled(false);
            jtDescricaoItem.setEnabled(false);
            jtObservacao.setEnabled(true);
            //jtObservacao.requestFocus();
            chek = 4;
        }
        if ("data".equals(opcao)) {
            jtMatricula.setEnabled(false);
            jtCod_Interno.setEnabled(false);
            jtDescricaoItem.setEnabled(false);
            jtObservacao.setEnabled(false);
            //jdDataPesquisaInicio.requestFocus();
            chek = 5;
        }
        return chek;
    }

    public void preencheTabela() throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jtTabela.getModel();
        modelo.setNumRows(0);
        ListaDeRupturaDAO listaR = new ListaDeRupturaDAO();
        for (ListaDeRuptura p : listaR.TabelaPesquisa()) {
            modelo.addRow(new Object[]{
                p.getCod_interno(), p.getDescricao(), p.getQtd_perdida(),
                p.getLancado(), p.getData(), p.getObservacao(), p.getMatricula()
            });
        }
    }

    public void preencheTabelaMatricula() throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jtTabela.getModel();
        modelo.setNumRows(0);
        Date data1 = jdDataPesquisaInicio.getDate();
        Date data2 = jdDataPesquisaFim.getDate();
        int mat = Integer.parseInt(jtMatricula.getText());
        for (ListaDeRuptura p : DAOLISTA.TabelaPesquisaMatricula(mat, fun.convertDateToDateSql(data1), fun.convertDateToDateSql(data2))) {
            modelo.addRow(new Object[]{
                p.getCod_interno(), p.getDescricao(), p.getQtd_perdida(),
                p.getLancado(), p.getData(), p.getObservacao()
            });
        }
    }

    public void preencheTabelaCodInterno() throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jtTabela.getModel();
        modelo.setNumRows(0);
        Date data1 = jdDataPesquisaInicio.getDate();
        Date data2 = jdDataPesquisaFim.getDate();
        int interno = Integer.parseInt(jtCod_Interno.getText());
        for (ListaDeRuptura p : DAOLISTA.TabelaPesquisaCodInterno(interno, fun.convertDateToDateSql(data1), fun.convertDateToDateSql(data2))) {
            modelo.addRow(new Object[]{
                p.getCod_interno(), p.getDescricao(), p.getQtd_perdida(),
                p.getLancado(), p.getData(), p.getObservacao(), p.getMatricula()
            });
        }
    }

    public void preencheTabelaDescricao() throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jtTabela.getModel();
        modelo.setNumRows(0);
        Date data1 = jdDataPesquisaInicio.getDate();
        Date data2 = jdDataPesquisaFim.getDate();
        String descricao = jtDescricaoItem.getText().toUpperCase();
        for (ListaDeRuptura p : DAOLISTA.TabelaPesquisaDescricao(descricao, fun.convertDateToDateSql(data1), fun.convertDateToDateSql(data2))) {
            modelo.addRow(new Object[]{
                p.getCod_interno(), p.getDescricao(), p.getQtd_perdida(),
                p.getLancado(), p.getData(), p.getObservacao(), p.getMatricula()
            });
        }
    }

    public void preencheTabelaObservacao() throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jtTabela.getModel();
        modelo.setNumRows(0);
        Date data1 = jdDataPesquisaInicio.getDate();
        Date data2 = jdDataPesquisaFim.getDate();
        String observa = jtObservacao.getSelectedItem().toString().toUpperCase();
        for (ListaDeRuptura p : DAOLISTA.TabelaPesquisaObservacao(observa, fun.convertDateToDateSql(data1), fun.convertDateToDateSql(data2))) {
            modelo.addRow(new Object[]{
                p.getCod_interno(), p.getDescricao(), p.getQtd_perdida(),
                p.getLancado(), p.getData(), p.getObservacao(), p.getMatricula()
            });
        }
    }

    public void preencheTabelaData() throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jtTabela.getModel();
        modelo.setNumRows(0);
        Date data1 = jdDataPesquisaInicio.getDate();
        Date data2 = jdDataPesquisaFim.getDate();
        List<ListaDeRuptura> ruptura = DAOLISTA.TabelaPesquisaData(fun.convertDateToDateSql(data1), fun.convertDateToDateSql(data2));
        ruptura.forEach((p) -> {
            modelo.addRow(new Object[]{
                p.getCod_interno(), p.getDescricao(), p.getQtd_perdida(),
                p.getLancado(), p.getData(), p.getObservacao(), p.getMatricula()
            });
        });
    }

    public void RelatorioMatricula() {
        Date data1 = jdDataPesquisaInicio.getDate();
        Date data2 = jdDataPesquisaFim.getDate();
        int mat = Integer.parseInt(jtMatricula.getText());
        rl.RelatorioMatricula(mat, numeroLoja, fun.convertDateToDateSql(data1), fun.convertDateToDateSql(data2));
    }

    public void RelatorioCodigoInterno() {
        Date data1 = jdDataPesquisaInicio.getDate();
        Date data2 = jdDataPesquisaFim.getDate();
        int cod = Integer.parseInt(jtCod_Interno.getText());
        rl.RelatorioCodigoInterno(cod, numeroLoja, fun.convertDateToDateSql(data1), fun.convertDateToDateSql(data2));
    }

    public void RelatorioDescicao() {
        Date data1 = jdDataPesquisaInicio.getDate();
        Date data2 = jdDataPesquisaFim.getDate();
        String descri = jtDescricaoItem.getText().toUpperCase();
        rl.RelatorioDescricao(descri, numeroLoja, fun.convertDateToDateSql(data1), fun.convertDateToDateSql(data2));
    }

    public void RelatorioObservacao() {
        Date data1 = jdDataPesquisaInicio.getDate();
        Date data2 = jdDataPesquisaFim.getDate();
        String obs = jtObservacao.getSelectedItem().toString().toUpperCase();
        rl.RelatorioObservacao(obs, numeroLoja, fun.convertDateToDateSql(data1), fun.convertDateToDateSql(data2));
    }

    public void RelatorioData() {
        Date data1 = jdDataPesquisaInicio.getDate();
        Date data2 = jdDataPesquisaFim.getDate();
        rl.RelatorioData(fun.convertDateToDateSql(data1), fun.convertDateToDateSql(data2), numeroLoja);
    }

    public void RelatorioDataReforco() {
        Date data1 = jdDataPesquisaInicio.getDate();
        Date data2 = jdDataPesquisaFim.getDate();
        rl.RelatorioReforcoData(fun.convertDateToDateSql(data1), fun.convertDateToDateSql(data2));
    }

    public void RelatorioDataEnvio() {
        Date data1 = jdDataPesquisaInicio.getDate();
        Date data2 = jdDataPesquisaFim.getDate();
        rl.RelatorioDataEnvio(fun.convertDateToDateSql(data1), fun.convertDateToDateSql(data2), numeroLoja);
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

    public void Carregando() {
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

    public void fechaCarregamento() {
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
        jLabel5 = new javax.swing.JLabel();
        jtCod_Interno = new javax.swing.JTextField();
        jbCancelar = new javax.swing.JButton();
        jbPesquisar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtTabela = new javax.swing.JTable();
        jbListar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jlMat = new javax.swing.JLabel();
        jtDescricaoItem = new javax.swing.JTextField();
        jtMatricula = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jlDataPesquisa = new javax.swing.JLabel();
        jdDataPesquisaFim = new com.toedter.calendar.JDateChooser("dd/MM/yyyy", "##/##/#####", '-');
        jdDataPesquisaInicio = new com.toedter.calendar.JDateChooser("dd/MM/yyyy", "##/##/#####", '-');
        jlInicio = new javax.swing.JLabel();
        jlFim = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtLoja = new javax.swing.JTextField();
        jbPesquisa = new javax.swing.JButton();
        jrMatricula = new javax.swing.JRadioButton();
        jrInterno = new javax.swing.JRadioButton();
        jrDescricao = new javax.swing.JRadioButton();
        jrObservacao = new javax.swing.JRadioButton();
        jtObservacao = new javax.swing.JComboBox<>();
        jrData = new javax.swing.JRadioButton();
        jbEnvio = new javax.swing.JButton();
        jbPesquisaReforco = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Codigo Interno");

        jtCod_Interno.setEnabled(false);
        jtCod_Interno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtCod_InternoActionPerformed(evt);
            }
        });

        jbCancelar.setText("Limpar Pesquisa");
        jbCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
            }
        });

        jbPesquisar.setText("Pesquisar");
        jbPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPesquisarActionPerformed(evt);
            }
        });

        jtTabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIÇÃO", "QTD PERDIDA", "LANÇADO", "DATA", "OBS", "MATRÍCULA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jtTabela);
        if (jtTabela.getColumnModel().getColumnCount() > 0) {
            jtTabela.getColumnModel().getColumn(0).setMaxWidth(70);
            jtTabela.getColumnModel().getColumn(1).setMinWidth(150);
        }

        jbListar.setText("Listar Todos");
        jbListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbListarActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Descrição");

        jlMat.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlMat.setText("Matrícula");

        jtDescricaoItem.setEnabled(false);

        jtMatricula.setEnabled(false);
        jtMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtMatriculaActionPerformed(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Observação");

        jlDataPesquisa.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jlDataPesquisa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlDataPesquisa.setText("Filtros");

        jlInicio.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jlInicio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlInicio.setText("Data Inicio");

        jlFim.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jlFim.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlFim.setText("Data Fim");

        jLabel3.setText("Loja");

        jtLoja.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtLoja.setEnabled(false);

        jbPesquisa.setText("Imprimir a Pesquisa");
        jbPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPesquisaActionPerformed(evt);
            }
        });

        GrupoDeRadioButoes.add(jrMatricula);
        jrMatricula.setText("Pesquisar por matrícula");
        jrMatricula.setActionCommand("matricula");

        GrupoDeRadioButoes.add(jrInterno);
        jrInterno.setText("Pesquisar por codigo interno");
        jrInterno.setActionCommand("interno");

        GrupoDeRadioButoes.add(jrDescricao);
        jrDescricao.setText("Pesquisar por descrição");
        jrDescricao.setActionCommand("descricao");

        GrupoDeRadioButoes.add(jrObservacao);
        jrObservacao.setText("Pesquisar por tipo de observação");
        jrObservacao.setActionCommand("observacao");

        jtObservacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione Uma Observação", "Falta", "Deixei de Vender", "Desativado", "Muita Procura", "Encomenda" }));

        GrupoDeRadioButoes.add(jrData);
        jrData.setSelected(true);
        jrData.setText("Pesquisar Somente Por Data");
        jrData.setActionCommand("data");

        jbEnvio.setText("Gerar Modelo de  Envio");
        jbEnvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEnvioActionPerformed(evt);
            }
        });

        jbPesquisaReforco.setText("Fazer Aumento de Estoque");
        jbPesquisaReforco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPesquisaReforcoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbPesquisaReforco, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlDataPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jlInicio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlMat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlFim, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jrMatricula))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jtCod_Interno, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jrInterno))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jtDescricaoItem, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jrDescricao))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jdDataPesquisaFim, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jdDataPesquisaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jrData)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel3)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jtLoja, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jbPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jbListar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jtObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jrObservacao)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jbCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbPesquisa)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlDataPesquisa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlMat, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jrMatricula))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtCod_Interno)
                    .addComponent(jLabel5)
                    .addComponent(jrInterno))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtDescricaoItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jrDescricao))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jtObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jrObservacao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdDataPesquisaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jrData))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdDataPesquisaFim, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jtLoja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)
                                .addComponent(jbListar)
                                .addComponent(jbCancelar)
                                .addComponent(jbPesquisar)
                                .addComponent(jbPesquisa))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jlFim, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbEnvio)
                    .addComponent(jbPesquisaReforco))
                .addContainerGap())
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

    private void jtCod_InternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtCod_InternoActionPerformed
        try {
            objPRO = DAOPRO.PesquisarPorCodInterno(Integer.parseInt(jtCod_Interno.getText()));
            if (objUSER == null || jtCod_Interno.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Não Foi Possível Encontrar o Registro!");
                jtCod_Interno.setText("");//Limpa ID
                jtCod_Interno.requestFocus();
            } else {
                jtDescricaoItem.setText(objPRO.getDescricao());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro na readTable Código Interno." + ex.getMessage());
        }
    }//GEN-LAST:event_jtCod_InternoActionPerformed

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        limparCampo();
        jtCod_Interno.setEnabled(false);
        jtDescricaoItem.setEnabled(false);
        jtObservacao.setEnabled(false);
        jbListar.setEnabled(true);
        jbPesquisar.setEnabled(true);
        jbPesquisar.setEnabled(true);
        jbCancelar.setEnabled(true);
        acao = 0;
    }//GEN-LAST:event_jbCancelarActionPerformed

    private void jbPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPesquisarActionPerformed
        try {
            if (validarCampoNovoRelatorio() == 1) {//Matrícula e Data
                preencheTabelaMatricula();
            }
            if (validarCampoNovoRelatorio() == 2) {//Codigo Interno e Data
                preencheTabelaCodInterno();
            }
            if (validarCampoNovoRelatorio() == 3) {//Descrição e Data
                preencheTabelaDescricao();
            }
            if (validarCampoNovoRelatorio() == 4) {//Observação e Data
                preencheTabelaObservacao();
            }
            if (validarCampoNovoRelatorio() == 5) {//Somente Data
                preencheTabelaData();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Pesquisar. " + ex);
        }
    }//GEN-LAST:event_jbPesquisarActionPerformed

    private void jbListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbListarActionPerformed
        Carregando();
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    preencheTabela();
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
                if (validarCampoNovoRelatorio() == 2) {
                    RelatorioCodigoInterno();
                }
                if (validarCampoNovoRelatorio() == 3) {
                    RelatorioDescicao();
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
                }
                fechaCarregamento();
            }
        };
        t.start();
    }//GEN-LAST:event_jbEnvioActionPerformed

    private void jbPesquisaReforcoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPesquisaReforcoActionPerformed
        Carregando();
        Thread t = new Thread() {
            @Override
            public void run() {
                if (validarCampoNovoRelatorio() == 5) {
                    RelatorioDataReforco();
                } else if (validarCampoNovoRelatorio() != 5) {
                    JOptionPane.showMessageDialog(null, "Pesquisa Somente Por Data!");
                }
                fechaCarregamento();
            }
        };
        t.start();
    }//GEN-LAST:event_jbPesquisaReforcoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup GrupoDeRadioButoes;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbEnvio;
    private javax.swing.JButton jbListar;
    private javax.swing.JButton jbPesquisa;
    private javax.swing.JButton jbPesquisaReforco;
    private javax.swing.JButton jbPesquisar;
    private com.toedter.calendar.JDateChooser jdDataPesquisaFim;
    private com.toedter.calendar.JDateChooser jdDataPesquisaInicio;
    private javax.swing.JLabel jlDataPesquisa;
    private javax.swing.JLabel jlFim;
    private javax.swing.JLabel jlInicio;
    private javax.swing.JLabel jlMat;
    private javax.swing.JRadioButton jrData;
    private javax.swing.JRadioButton jrDescricao;
    private javax.swing.JRadioButton jrInterno;
    private javax.swing.JRadioButton jrMatricula;
    private javax.swing.JRadioButton jrObservacao;
    private javax.swing.JTextField jtCod_Interno;
    private javax.swing.JTextField jtDescricaoItem;
    private javax.swing.JTextField jtLoja;
    private javax.swing.JTextField jtMatricula;
    private javax.swing.JComboBox<String> jtObservacao;
    private javax.swing.JTable jtTabela;
    // End of variables declaration//GEN-END:variables
}
