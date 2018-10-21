package modulo.view.painel;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import modulo.DAO.FaltasDAO;
import modulo.DAO.LojaDAO;
import modulo.DAO.ProdutoDAO;
import modulo.DAO.UsuarioDAO;
import modulo.entidades.Faltas;
import modulo.entidades.Loja;
import modulo.entidades.Produto;
import modulo.entidades.Usuario;
import modulo.versao.Versao;
import javax.swing.ImageIcon;

/**
 *
 * @author Marcos Junior
 */
public final class jfFaltas extends javax.swing.JFrame {

    private FaltasDAO DAOLista;
    private Faltas objLista;
    private UsuarioDAO DAOUSER;
    private Usuario objUSER;
    private LojaDAO DAOLOJA;
    private ProdutoDAO DAOPRO;
    private Produto objPRO;
    private DateFormat formatoHora;
    private DateFormat formatoDIA;
    private Versao ver;
    private int numeroLoja = 340;
    private boolean tipo = true;
    private String Aviso = "Atenção";

    public jfFaltas() {
        initComponents();
        DAOUSER = new UsuarioDAO();
        DAOLOJA = new LojaDAO();
        DAOPRO = new ProdutoDAO();
        DAOLista = new FaltasDAO();
        ver = new Versao();
        setTitle("Registro de Faltas: " + ver.getVersao());
        this.formatoHora = new SimpleDateFormat("EEEEEEEEEEEEEE, dd/MM/yyyy    HH:mm:ss");
        this.formatoDIA = new SimpleDateFormat("dd/MM/yyyy");
        Thread relogioThred = new Thread(new jfFaltas.clsDataHora());
        relogioThred.start();
        converteDataUtilToSql();
        CarregaLoja();
    }

    class clsDataHora implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    jtDataDoRegistro.setText(formatoDIA.format(new Date()));
                    carregaUser();
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println("Thread não foi finalizada:" + ex);
                } catch (Exception ex) {
                }
            }
        }
    }

    public void limparCampo() {
        jtCod_Interno.setText("");
        jtDescricaoItem.setText("");
        jtQTD_Perdida.setText("");
    }

    public void cancelar() {
        if ("Limpar".equals(jbCancelar.getText())) {
            limparCampo();
        } else if ("Cancelar".equals(jbCancelar.getText())) {
            limparCampo();
            jbSalvar.setText("Salvar Falta");
            jbCancelar.setText("Limpar");
            jtObservacao.setEnabled(true);
            jtDescricaoItem.setEditable(false);
            jtCod_Interno.setEnabled(true);
            jtMatricula.setEnabled(true);
            jtQTD_Perdida.setEnabled(true);
            jtCod_Interno.requestFocus();
            tipo = true;
        }
    }

    public void VerificaAoSalvar() {
        if ("Salvar Falta".equals(jbSalvar.getText())) {
            if (validarCampo()) {
                try {
                    carregaPro();
                    SalvarFalta();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao Carregar Produto: " + ex.getMessage());
                }
            } else if ("Cadastrar Produto".equals(jbSalvar.getText())) {
                SalvarProduto();
            }
        }
    }

//    public void ConsultaEan() {
//        if ("Salvar Falta".equals(jbSalvar.getText())) {
//            try {
//                objPRO = DAOPRO.PesquisarPorCodProduto(jtCod_EAN.getText());
//                if (objUSER == null || jtCod_EAN.getText().equals("")) {
//                    jtCod_EAN.setText("");
//                    jtCod_Interno.requestFocus();
//                } else {
//                    jtDescricaoItem.setText(objPRO.getDescricao());
//                    jtCod_Interno.setText(Integer.toString(objPRO.getCod_interno()));
//                    jtObservacao.requestFocus();
//                }
//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(this, "Erro ao Pesquiar EAN." + ex.getMessage());
//            }
//        } else if ("Cadastrar Produto".equals(jbSalvar.getText())) {
//            jtDescricaoItem.requestFocus();
//        }
//    }
    public void keyReleased(KeyEvent ke, String test) {
        if (ke.getKeyCode() == KeyEvent.VK_ENTER & "obs".equals(test)) {
            jtQTD_Perdida.requestFocus();
        }
        if (ke.getKeyCode() == KeyEvent.VK_TAB || ke.getKeyCode() == KeyEvent.VK_ENTER & "matri".equals(test)) {
            jtCod_Interno.requestFocus();
        }
    }

    public void carregaPro() throws Exception {
        if (DAOPRO.CheckSelect(Integer.parseInt(jtCod_Interno.getText()))) {
            objPRO = DAOPRO.PesquisarPorCodInterno(Integer.parseInt(jtCod_Interno.getText()));
            jtDescricaoItem.setText(objPRO.getDescricao());
//            jtCod_EAN.setText(objPRO.getCod_produto());
        } else {
            jtDescricaoItem.setText("Código Não Cadastrado!");
        }
    }

    public void carregaProduto() {
        try {
            if (jtCod_Interno.getText().equals("")) {
                jtDescricaoItem.setText("Código Não Cadastrado!");
            } else if (DAOPRO.PesquisarPorCodInterno(Integer.parseInt(jtCod_Interno.getText())) != null) {
                objPRO = DAOPRO.PesquisarPorCodInterno(Integer.parseInt(jtCod_Interno.getText()));
                jtDescricaoItem.setText(objPRO.getDescricao());
//                jtCod_EAN.setText(objPRO.getCod_produto());
                jtObservacao.requestFocus();
            } else {
                jtDescricaoItem.setText("");
                tipo = false;
                int resposta;
                resposta = JOptionPane.showConfirmDialog(this, "Deseja Cadadastrar o Produto? ", Aviso, JOptionPane.YES_NO_OPTION);
                if (resposta == 0) {
                    jtDescricaoItem.setText("");
                    jtDescricaoItem.requestFocus();
                    jbSalvar.setText("Cadastrar Produto");
                    jbCancelar.setText("Cancelar");
//                    jtCod_EAN.setEnabled(false);
                    jtCod_Interno.setEnabled(false);
                    jtDescricaoItem.setEditable(true);
                    jtObservacao.setEnabled(false);
                    jtMatricula.setEnabled(false);
                    jtQTD_Perdida.setEnabled(false);
                } else if (resposta == 1) {
                    jbSalvar.setText("Salvar Falta");
                    jbCancelar.setText("Limpar");
                    jtCod_Interno.setEnabled(true);
                    jtMatricula.setEnabled(true);
                    jtQTD_Perdida.setEnabled(true);
                    jtCod_Interno.requestFocus();
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Pesquiar Código Interno." + ex.getMessage());
        }
    }

    public void carregaUser() {
        try {
            objUSER = DAOUSER.PesquisaMatriculaR(Integer.parseInt(jtMatricula.getText()));
            if (objUSER == null || jtMatricula.getText().equals("")) {
                jtNomeUsuario.setText("Não localizado!");
                jtMatricula.requestFocus();
            } else {
                jtNomeUsuario.setText(objUSER.getNome());
            }
        } catch (Exception ex) {
            //jtNomeUsuario.setText(ex.getMessage());
        }
    }

    public void CarregaLoja() {
        try {
            Loja f = DAOLOJA.PesquisaNumeroLoja(numeroLoja);
            String nl = Integer.toString(f.getNumero_loja());
            jtLoja.setText(nl);
            jlTitulo.setText("Lista de Ruptura Loja " + nl + " " + ver.getVersao());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Iniciar readTable Matricula." + ex.getMessage());
        }
    }

    public boolean validarCampos() {
        if (jtCod_Interno.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Código Interno!");
            jtMatricula.requestFocus();
            return false;
        }
        if (jtDescricaoItem.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Não é possível cadastrar um produto sem descrição!");
            jtCod_Interno.requestFocus();
            return false;
        }
        return true;
    }

    public boolean validarCampo() {
        if (jtCod_Interno.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Código Interno!");
            jtMatricula.requestFocus();
            return false;
        }
        if (jtMatricula.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Matrícula!");
            jtMatricula.requestFocus();
            return false;
        }
        if (jtDescricaoItem.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Não é possível cadastrar um produto sem descrição!");
            jtCod_Interno.requestFocus();
            return false;
        }
        if (jtQTD_Perdida.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Quantidade Perdida!");
            jtQTD_Perdida.requestFocus();
            return false;
        }
        if (jtObservacao.getSelectedItem().equals("Selecione Uma Observação")) {
            JOptionPane.showMessageDialog(this, "Selecione Uma Observação!");
            jtObservacao.requestFocus();
            return false;
        }
        return true;
    }

    public void SalvarFalta() {
        try {
            try {
                if (validarCampo()) {
                    carregaUser();
                    carregaProduto();
                    if (preencherObjetosSalvarLista()) {
                        if (DAOLista.Insert(objLista)) {
                            limparCampo();
                            jtMatricula.setEnabled(true);
                            jtCod_Interno.setEnabled(true);
                            //jtCod_EAN.setEnabled(true);
                            jtObservacao.setEnabled(true);
                            jtQTD_Perdida.setEnabled(true);
                            jbCancelar.setText("Limpar");
                            jtObservacao.setSelectedIndex(0);
                            jtCod_Interno.requestFocus();
                            JOptionPane.showMessageDialog(this, "Salvo Com Sucesso!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Não Foi Possível Salvar!");
                        jtObservacao.setSelectedIndex(0);
                        jtCod_Interno.requestFocus();
                    }
                }
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(this, "Erro ao Salvar: " + erro.getMessage());
            }
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Iniciar readTable. " + ex.getMessage());
        }
    }

    public boolean preencherObjetosSalvarProduto() {
        objPRO = new Produto();
        objPRO.setCod_interno(Integer.parseInt(jtCod_Interno.getText()));
        //objPRO.setCod_produto(jtCod_EAN.getText());
        objPRO.setDescricao(jtDescricaoItem.getText());
        objPRO.setObserv(jtObservacao.getSelectedItem().toString());
        return true;
    }

    public boolean preencherObjetosSalvarLista() {
        objLista = new Faltas();
        objLista.setCod_interno(Integer.parseInt(jtCod_Interno.getText()));
        //objLista.setCod_produto(jtCod_EAN.getText());
        objLista.setData(converteDataUtilToSql());
        objLista.setDescricao(jtDescricaoItem.getText());
        objLista.setMatricula(Integer.parseInt(jtMatricula.getText()));
        objLista.setObservacao(jtObservacao.getSelectedItem().toString());
        objLista.setQtd_perdida(Integer.parseInt(jtQTD_Perdida.getText()));
        return true;
    }

    public java.sql.Date converteDataUtilToSql() {
        Date data = new Date();
        Date dataUtil = data;
        java.sql.Date dataSql = null;
        try {
            dataUtil = new java.sql.Date(dataUtil.getTime());
            dataSql = (java.sql.Date) dataUtil;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao converte data para sql: " + e.getMessage());
        }
        return dataSql;
    }

    public java.sql.Date Ultimos30Dias() {
        Date dataFinal = new Date();
        Calendar calendarData = Calendar.getInstance();
        calendarData.setTime(dataFinal);
        int numeroDiasParaSubtrair = -0;
        calendarData.add(Calendar.DATE, numeroDiasParaSubtrair);
        Date dataInicial = calendarData.getTime();
        Date dataUtil = dataInicial;
        java.sql.Date dataSql = null;
        try {
            dataUtil = new java.sql.Date(dataUtil.getTime());
            dataSql = (java.sql.Date) dataUtil;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao converte data para sql: " + e.getMessage());
        }
        //System.out.println("Data: " + dataSql);
        return dataSql;
    }

    public boolean PesquisaUltmos30Dias() {
        boolean chek = false;
        int cod_int = Integer.parseInt(jtCod_Interno.getText());
        try {
            chek = DAOLista.CheckSelect(cod_int, Ultimos30Dias(), converteDataUtilToSql());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Carregar Data!" + ex);
        }
        return chek;
    }

    public void SalvarProduto() {
        try {
            if (validarCampos()) {
                if (!DAOPRO.CheckSelect(Integer.parseInt(jtCod_Interno.getText()))) {
                    if (preencherObjetosSalvarProduto()) {
                        if (DAOPRO.Insert(objPRO)) {
                            JOptionPane.showMessageDialog(this, "Salvo Com Sucesso!");
                            //SetarCamposAoCancelar();
                            jtDescricaoItem.setEditable(true);
                            jtQTD_Perdida.setEnabled(true);
                            jbSalvar.setText("Salvar Falta");
                            jbCancelar.setText("Cancelar");
                            jtObservacao.setEnabled(true);
                            jtDescricaoItem.setEditable(false);
                            jtObservacao.requestFocus();
                            tipo = true;
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Não Foi Possível Salvar!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Produto ja Cadastrado!");
                    carregaPro();
                }
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(this, "Erro ao Salvar: " + erro.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jtCod_Interno = new javax.swing.JTextField();
        jbSalvar = new javax.swing.JButton();
        jbCancelar = new javax.swing.JButton();
        jlMat = new javax.swing.JLabel();
        jtDescricaoItem = new javax.swing.JTextField();
        jtMatricula = new javax.swing.JTextField();
        jtNomeUsuario = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jtQTD_Perdida = new javax.swing.JTextField();
        jtDataDoRegistro = new javax.swing.JTextField();
        jtLoja = new javax.swing.JTextField();
        jtObservacao = new javax.swing.JComboBox<>();
        jlTitulo = new javax.swing.JLabel();
        lbl_close = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/icons/icone_sistema.png")).getImage());
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel5.setBackground(new java.awt.Color(153, 255, 0));
        jLabel5.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 153));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Código");

        jtCod_Interno.setBackground(new java.awt.Color(255, 255, 102));
        jtCod_Interno.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jtCod_Interno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtCod_Interno.setDisabledTextColor(new java.awt.Color(255, 153, 51));
        jtCod_Interno.setFocusTraversalPolicyProvider(true);
        jtCod_Interno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtCod_InternoActionPerformed(evt);
            }
        });
        jtCod_Interno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtCod_InternoKeyPressed(evt);
            }
        });

        jbSalvar.setBackground(new java.awt.Color(0, 0, 102));
        jbSalvar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jbSalvar.setForeground(new java.awt.Color(255, 255, 255));
        jbSalvar.setText("Salvar Falta");
        jbSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbSalvarMouseClicked(evt);
            }
        });
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

        jbCancelar.setBackground(new java.awt.Color(204, 0, 0));
        jbCancelar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jbCancelar.setForeground(new java.awt.Color(255, 255, 255));
        jbCancelar.setText("Limpar");
        jbCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
            }
        });

        jlMat.setBackground(new java.awt.Color(153, 255, 0));
        jlMat.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jlMat.setForeground(new java.awt.Color(0, 0, 153));
        jlMat.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlMat.setText("Matrícula");

        jtDescricaoItem.setBackground(new java.awt.Color(102, 255, 153));
        jtDescricaoItem.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jtDescricaoItem.setForeground(new java.awt.Color(0, 153, 51));
        jtDescricaoItem.setDisabledTextColor(new java.awt.Color(255, 153, 51));
        jtDescricaoItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtDescricaoItemActionPerformed(evt);
            }
        });

        jtMatricula.setBackground(new java.awt.Color(255, 255, 102));
        jtMatricula.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jtMatricula.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtMatricula.setDisabledTextColor(new java.awt.Color(255, 153, 51));
        jtMatricula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtMatriculaMouseClicked(evt);
            }
        });
        jtMatricula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtMatriculaKeyPressed(evt);
            }
        });

        jtNomeUsuario.setBackground(new java.awt.Color(102, 255, 153));
        jtNomeUsuario.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jtNomeUsuario.setForeground(new java.awt.Color(255, 0, 0));
        jtNomeUsuario.setDisabledTextColor(new java.awt.Color(255, 153, 51));

        jLabel7.setBackground(new java.awt.Color(153, 255, 0));
        jLabel7.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 153));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Perdida");

        jtQTD_Perdida.setBackground(new java.awt.Color(255, 255, 102));
        jtQTD_Perdida.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jtQTD_Perdida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtQTD_Perdida.setDisabledTextColor(new java.awt.Color(255, 153, 51));
        jtQTD_Perdida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtQTD_PerdidaActionPerformed(evt);
            }
        });

        jtDataDoRegistro.setBackground(new java.awt.Color(255, 153, 102));
        jtDataDoRegistro.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jtDataDoRegistro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtDataDoRegistro.setDisabledTextColor(new java.awt.Color(255, 153, 51));

        jtLoja.setBackground(new java.awt.Color(255, 153, 102));
        jtLoja.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jtLoja.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtLoja.setDisabledTextColor(new java.awt.Color(255, 153, 51));

        jtObservacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione Uma Observação", "Falta", "Deixei de Vender", "Desativado", "Muita Procura", "Encomenda", "Falta Temporaria Pelo Fabricante", "Falta no CD" }));
        jtObservacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtObservacaoActionPerformed(evt);
            }
        });
        jtObservacao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtObservacaoKeyPressed(evt);
            }
        });

        jlTitulo.setBackground(new java.awt.Color(51, 255, 51));
        jlTitulo.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jlTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTitulo.setText("Título");

        lbl_close.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        lbl_close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_close.setText("X");
        lbl_close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbl_closeMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jlTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_close, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jlMat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtQTD_Perdida, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                                    .addComponent(jtCod_Interno)
                                    .addComponent(jtMatricula))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtDescricaoItem)
                                    .addComponent(jtObservacao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtNomeUsuario)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jbSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jbCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jtLoja, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtDataDoRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_close)
                    .addComponent(jlTitulo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlMat, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtCod_Interno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtDescricaoItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtQTD_Perdida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtLoja, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtDataDoRegistro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbSalvar)
                        .addComponent(jbCancelar)))
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

    private void jtCod_InternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtCod_InternoActionPerformed
        carregaProduto();
    }//GEN-LAST:event_jtCod_InternoActionPerformed

    private void jtCod_InternoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtCod_InternoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            carregaProduto();
        }
    }//GEN-LAST:event_jtCod_InternoKeyPressed

    private void jbSalvarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbSalvarMouseClicked
        //VerificaAoSalvar();
        if ("Salvar Falta".equals(jbSalvar.getText())) {
            SalvarFalta();
        } else if ("Cadastrar Produto".equals(jbSalvar.getText())) {
            SalvarProduto();
        }
    }//GEN-LAST:event_jbSalvarMouseClicked

    private void jbSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarActionPerformed
        //VerificaAoSalvar();
        if ("Salvar Falta".equals(jbSalvar.getText())) {
            SalvarFalta();
        } else if ("Cadastrar Produto".equals(jbSalvar.getText())) {
            SalvarProduto();
        }
    }//GEN-LAST:event_jbSalvarActionPerformed

    private void jbSalvarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jbSalvarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if ("Salvar Falta".equals(jbSalvar.getText())) {
                SalvarFalta();
            } else if ("Cadastrar Produto".equals(jbSalvar.getText())) {
                SalvarProduto();
            }
        }
    }//GEN-LAST:event_jbSalvarKeyPressed

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        cancelar();
    }//GEN-LAST:event_jbCancelarActionPerformed

    private void jtDescricaoItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtDescricaoItemActionPerformed
        if ("Salvar Falta".equals(jbSalvar.getText())) {
            jtObservacao.requestFocus();
        } else if ("Cadastrar Produto".equals(jbSalvar.getText())) {
            jbSalvar.requestFocus();
        }
    }//GEN-LAST:event_jtDescricaoItemActionPerformed

    private void jtMatriculaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtMatriculaKeyPressed
        keyReleased(evt, "matri");
    }//GEN-LAST:event_jtMatriculaKeyPressed

    private void jtQTD_PerdidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtQTD_PerdidaActionPerformed
        jbSalvar.requestFocus();
    }//GEN-LAST:event_jtQTD_PerdidaActionPerformed

    private void jtObservacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtObservacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtObservacaoActionPerformed

    private void jtObservacaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtObservacaoKeyPressed
        keyReleased(evt, "obs");
    }//GEN-LAST:event_jtObservacaoKeyPressed

    private void lbl_closeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_closeMousePressed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_lbl_closeMousePressed

    private void jtMatriculaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtMatriculaMouseClicked
        jtMatricula.setText("");
        jtNomeUsuario.setText("");
    }//GEN-LAST:event_jtMatriculaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JLabel jlMat;
    private javax.swing.JLabel jlTitulo;
    private javax.swing.JTextField jtCod_Interno;
    private javax.swing.JTextField jtDataDoRegistro;
    private javax.swing.JTextField jtDescricaoItem;
    private javax.swing.JTextField jtLoja;
    private javax.swing.JTextField jtMatricula;
    private javax.swing.JTextField jtNomeUsuario;
    private javax.swing.JComboBox<String> jtObservacao;
    private javax.swing.JTextField jtQTD_Perdida;
    private javax.swing.JLabel lbl_close;
    // End of variables declaration//GEN-END:variables
}
