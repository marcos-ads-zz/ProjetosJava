package modulo.planodevoo;

import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modulo.DAO.LojaDAO;
import javax.swing.ImageIcon;
import modulo.DAO.SegurancaDAO;
import modulo.DAO.UsuarioDAO;
import modulo.camapanha.DAO.PlanoVooDAO;
import modulo.entidades.CampanhasPlanoDeVoo;
import modulo.entidades.Loja;
import modulo.entidades.Usuario;
import modulo.metodos.ConvertMD5;
import modulo.metodos.Funcao;
import modulo.metodos.SegurancaFuncoes;
import modulo.versao.Versao;

/**
 *
 * @author Marcos Junior
 */
public class JfPlanoDeVoo extends javax.swing.JFrame {

    private Funcao fun;
    private JFormattedTextField[] planoVoo;
    private PlanoVooDAO DAOVOO;
    private UsuarioDAO DAOUSER;
    private Usuario objUSER;
    private LojaDAO DAOLOJA;
    private DateFormat formatoHora;
    private DateFormat formatoDIA;
    private Versao ver;
    private CampanhasPlanoDeVoo objVoo;
    private SegurancaDAO SEGDAO;
    private SegurancaFuncoes segu;
    private ConvertMD5 MD5;
    private int numeroLoja = 1;

    /**
     * Creates new form JfPlanoDeVoo
     */
    public JfPlanoDeVoo() {
        initComponents();
        DAOUSER = new UsuarioDAO();
        DAOLOJA = new LojaDAO();
        DAOVOO = new PlanoVooDAO();
        SEGDAO = new SegurancaDAO();
        segu = new SegurancaFuncoes();
        fun = new Funcao();
        ver = new Versao();
        MD5 = new ConvertMD5();
        this.formatoHora = new SimpleDateFormat("EEEEEEEEEEEEEE, dd/MM/yyyy    HH:mm:ss");
        this.formatoDIA = new SimpleDateFormat("dd/MM/yyyy");
        criarUmVetorDeCampos();
        carregaLoja();
        jtDataAtual.setText(formatoDIA.format(new Date()));
        setTitle(ver.getNomesys() + " - Plano de Voo Colaboradores " + ver.getVersao());

    }

    private void carregaLoja() {

        try {
            Loja f = DAOLOJA.PesquisaNumeroLoja(numeroLoja);
            String nl = Integer.toString(f.getNumero_loja());
            jtNumeroLoja.setText(nl);
            jlTituloPlanoDeVoo.setText("Plano de Voo!");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Carregar Dados Loja. " + ex.getMessage());
            //jTabbedPane1.setVisible(false);
        }
    }

    private void criarUmVetorDeCampos() {//Função Secundária
        planoVoo = new JFormattedTextField[]{
            jfVooCliPDV1,
            jfVooCliPDV2,
            jfVooCliPDV3,
            jfVooCliPDV4,
            jfVendaPDV1,
            jfVendaPDV2,
            jfVendaPDV3,
            jfVendaPDV4,};

    }

    private boolean HabilitaDesabilita(int valor) {
        boolean check = true;

        for (JFormattedTextField voo : planoVoo) {
            if (valor == 1) {
                voo.setEnabled(true);
                jbPesquisarDadosUser.setText("Salvar");
                jbEditarVoo.setEnabled(false);

            } else if (valor == 0) {
                voo.setEnabled(false);
                jbPesquisarDadosUser.setText("Pesquisar");
                jbEditarVoo.setEnabled(true);
            }
        }
        return check;
    }

    private boolean setZeroNosCamposLimposVoo() {
        boolean check = true;
        for (JFormattedTextField voo : planoVoo) {
            if (voo.getText().equals("") || voo.getText().equals("0")) {
                voo.setText("0");
                //Seta Azul neste caso
                voo.setBackground(new java.awt.Color(0, 0, 255));
            } else {
                //Seta Branco neste caso
                voo.setBackground(new java.awt.Color(255, 255, 255));
            }
        }
        return check;
    }

    private boolean validaCamposVoo() {
        boolean check;
        int cont = 0;
        for (JFormattedTextField voo : planoVoo) {
            if (voo.getText().equals("")) {
                //Seta Vermelho
                voo.setBackground(new java.awt.Color(255, 0, 0));
                cont++;
            } else {
                if (!fun.testaNumerosDecimais(voo.getText())) {
                    voo.setText("");
                }
                //Seta Branco
                voo.setBackground(new java.awt.Color(255, 255, 255));
            }
        }
        check = cont == 0;
        return check;
    }

    private void somaValores() {
        double somadb;
        int somait;
        double db1 = fun.convertToDouble(jfVendaPDV1.getText());
        double db2 = fun.convertToDouble(jfVendaPDV2.getText());
        double db3 = fun.convertToDouble(jfVendaPDV3.getText());
        double db4 = fun.convertToDouble(jfVendaPDV4.getText());
        somadb = db1 + db2 + db3 + db4;
        int it1 = fun.convertToInt(jfVooCliPDV1.getText());
        int it2 = fun.convertToInt(jfVooCliPDV2.getText());
        int it3 = fun.convertToInt(jfVooCliPDV3.getText());
        int it4 = fun.convertToInt(jfVooCliPDV4.getText());
        somait = it1 + it2 + it3 + it4;

        jfVooVendaTotal.setText(fun.convertDoubleToString(somadb));
        jfVooCliTotal.setText(Integer.toString(somait));

    }

    private void limparCampos() {
        int v = jTabbedPane1.getSelectedIndex();

        switch (v) {
            case 0: {
                //Grafico

                break;
            }
            case 1: {
                //Plano de Voo
                jfVooCliPDV1.setText("");
                jfVooCliPDV2.setText("");
                jfVooCliPDV3.setText("");
                jfVooCliPDV4.setText("");
                jfVooVendaTotal.setText("");
                jfVendaPDV1.setText("");
                jfVendaPDV2.setText("");
                jfVendaPDV3.setText("");
                jfVendaPDV4.setText("");
                jfVooCliTotal.setText("");
                HabilitaDesabilita(0);
                jtDataAtual.setText(formatoDIA.format(new Date()));
//                try {
//                    PesquisaPlanoDeVoo();
//                } catch (Exception ex) {
//                    Logger.getLogger(JfPlanoDeVoo.class.getName()).log(Level.SEVERE, null, ex);
//                }
                break;
            }
            case 2: {
                //Metas Plano de Voo

                break;
            }
            default:
                break;
        }
    }

    public boolean pesquisarUsuarioNoBanco() {
        int v = jTabbedPane1.getSelectedIndex();
        boolean chek = false;
        switch (v) {
            case 0: {
                //Grafico
                break;
            }
            case 1: {
                //Plano de Voo
                chek = metodoDeBusca(jtMatriculaVoo.getText(), jtNomeUsuario, jtMatriculaVoo);
                break;
            }
            case 2: {
                //Meta
                break;
            }
            default:
                break;
        }
        return chek;
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

    private boolean verificaMatriculaAntesDeSalvar() {
        int v = jTabbedPane1.getSelectedIndex();
        boolean check = false;
        try {
            switch (v) {
                case 0: {
                    //Grafico

                    break;
                }
                case 1: {
                    //Plano de Voo
                    if (!jtMatriculaVoo.getText().equals("")) {
                        check = DAOUSER.CheckSelect(fun.convertToInt(jtMatriculaVoo.getText()));
                    }
                    break;
                }
                case 2: {
                    //Metas
                }
                default:
                    break;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Falha ao verificaMatriculaAntesDeSalvar: " + ex);
        }
        return check;
    }

    private void carregaCampos() throws Exception {
        Date dataSetada = jDateChooser.getDate();
        CampanhasPlanoDeVoo objVooLocal;
        objVooLocal = DAOVOO.PesquisarUserIndividual(fun.convertToInt(jtMatriculaVoo.getText()),
                fun.convertDateStringToDateSQL(formatoDIA.format(dataSetada)));
        
       
        jfVooCliPDV1.setText(String.valueOf(objVooLocal.getCli1()));
        jfVooCliPDV2.setText(String.valueOf(objVooLocal.getCli2()));
        jfVooCliPDV3.setText(String.valueOf(objVooLocal.getCli3()));
        jfVooCliPDV4.setText(String.valueOf(objVooLocal.getCli4()));
        jfVooCliTotal.setText(String.valueOf(objVooLocal.getTotalcli()));
        
        jfVendaPDV1.setText(String.valueOf(objVooLocal.getPdv1()));
        
        System.out.println("Teste Valor: " + String.valueOf(objVooLocal.getPdv1()));
        System.out.println("Teste Valor: " + objVooLocal.getPdv1());
        
        jfVendaPDV2.setText(fun.SubPontoPorVirgula(String.valueOf(objVooLocal.getPdv2())));
        jfVendaPDV3.setText(fun.SubPontoPorVirgula(String.valueOf(objVooLocal.getPdv3())));
        jfVendaPDV4.setText(fun.SubPontoPorVirgula(String.valueOf(objVooLocal.getPdv4())));
        jfVooVendaTotal.setText(String.valueOf(objVooLocal.getTotalpdvs()));

        jbEditarVoo.setEnabled(true);
    }

    private boolean carregaDadosNaVariavelVoo() throws ParseException {//Função Secundária
        objVoo = new CampanhasPlanoDeVoo();
        objVoo.setCli1(fun.convertToInt(jfVooCliPDV1.getText()));
        objVoo.setCli2(fun.convertToInt(jfVooCliPDV2.getText()));
        objVoo.setCli3(fun.convertToInt(jfVooCliPDV3.getText()));
        objVoo.setCli4(fun.convertToInt(jfVooCliPDV4.getText()));
        objVoo.setTotalcli(fun.convertToInt(jfVooCliTotal.getText()));
        objVoo.setPdv1(fun.convertToDouble(jfVendaPDV1.getText()));
        objVoo.setPdv2(fun.convertToDouble(jfVendaPDV2.getText()));
        objVoo.setPdv3(fun.convertToDouble(jfVendaPDV3.getText()));
        objVoo.setPdv4(fun.convertToDouble(jfVendaPDV4.getText()));
        objVoo.setTotalpdvs(fun.convertToDouble(jfVooVendaTotal.getText()));
        objVoo.setMatricula(fun.convertToInt(jtMatriculaVoo.getText()));
        objVoo.setData_registro(fun.converteDateStringEmSQL(jtDataAtual.getText()));

        return objVoo != null;
    }

    private void salvarPlanoDeVoo() {//Função Principal
        try {
            if (validaCamposVoo()) {
                if (verificaMatriculaAntesDeSalvar()) {
                    if (setZeroNosCamposLimposVoo()) {
                        if (carregaDadosNaVariavelVoo()) {
                            if (DAOVOO.Update(objVoo)) {
                                JOptionPane.showMessageDialog(this, "Alterado com Sucesso!");
                                limparCampos();
                            } else {
                                JOptionPane.showMessageDialog(this, "Não foi possível alterar!");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Não pode alterar se todos os campos forem zero!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Matrícula Incorreta!");
                    jtMatriculaVoo.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Campos não validados!");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Falha ao salvar dados: " + ex);
        }
    }

    private void verificaOpcaoSelecionada(KeyEvent ke, String testAbaSelecionada) {
//        if (ke.getKeyCode() == KeyEvent.VK_ENTER & "obs".equals(test)) {
//            jtQTD_Perdida.requestFocus();
//        }
//        if (ke.getKeyCode() == KeyEvent.VK_TAB || ke.getKeyCode() == KeyEvent.VK_ENTER & "matri".equals(test)) {
//            jtCod_Interno.requestFocus();
//        }
//        if (ke.getKeyCode() == KeyEvent.VK_TAB || ke.getKeyCode() == KeyEvent.VK_ENTER & "matriCamp".equals(test)) {
//            jtQtdProdutoCampanha.requestFocus();
//        }
        if (ke.getKeyCode() == KeyEvent.VK_TAB || ke.getKeyCode() == KeyEvent.VK_ENTER & "matriVoo".equals(testAbaSelecionada)) {
            jDateChooser.requestFocus();
        }
//        if (ke.getKeyCode() == KeyEvent.VK_TAB || ke.getKeyCode() == KeyEvent.VK_ENTER & "obsCamp".equals(test)) {
//            jbSalvarCampanha.requestFocus();
//        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3PlanoDeVoo = new javax.swing.JPanel();
        jtNomeUsuario = new javax.swing.JTextField();
        jtMatriculaVoo = new javax.swing.JTextField();
        jlMat26 = new javax.swing.JLabel();
        jbPesquisarDadosUser = new javax.swing.JButton();
        jbLimparVoo = new javax.swing.JButton();
        jtNumeroLoja = new javax.swing.JTextField();
        jlMat28 = new javax.swing.JLabel();
        jlMat29 = new javax.swing.JLabel();
        jfVendaPDV1 = new javax.swing.JFormattedTextField();
        jfVendaPDV2 = new javax.swing.JFormattedTextField();
        jfVendaPDV4 = new javax.swing.JFormattedTextField();
        jfVendaPDV3 = new javax.swing.JFormattedTextField();
        jfVooCliPDV1 = new javax.swing.JFormattedTextField();
        jfVooCliPDV2 = new javax.swing.JFormattedTextField();
        jfVooCliPDV3 = new javax.swing.JFormattedTextField();
        jfVooCliPDV4 = new javax.swing.JFormattedTextField();
        jfVooVendaTotal = new javax.swing.JFormattedTextField();
        jfVooCliTotal = new javax.swing.JFormattedTextField();
        jtDataAtual = new javax.swing.JFormattedTextField();
        jbEditarVoo = new javax.swing.JButton();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        jlMat30 = new javax.swing.JLabel();
        jfVooVenda5 = new javax.swing.JFormattedTextField();
        jlTituloPlanoDeVoo = new javax.swing.JLabel();
        jlMat31 = new javax.swing.JLabel();
        jfVooVenda6 = new javax.swing.JFormattedTextField();
        jlMat32 = new javax.swing.JLabel();
        jfVooCli5 = new javax.swing.JFormattedTextField();
        jlTituloPlanoDeVoo1 = new javax.swing.JLabel();
        jlTituloPlanoDeVoo2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setExtendedState(6);
        setIconImage(new ImageIcon(getClass().getResource("/icons/asterisk_orange.png")).getImage());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 904, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 447, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Gráfico", jPanel1);

        jPanel3PlanoDeVoo.setBackground(new java.awt.Color(0, 153, 153));

        jtNomeUsuario.setEditable(false);
        jtNomeUsuario.setBackground(new java.awt.Color(102, 255, 153));
        jtNomeUsuario.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jtNomeUsuario.setForeground(new java.awt.Color(255, 0, 0));
        jtNomeUsuario.setDisabledTextColor(new java.awt.Color(255, 153, 51));

        jtMatriculaVoo.setBackground(new java.awt.Color(255, 255, 102));
        jtMatriculaVoo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jtMatriculaVoo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtMatriculaVoo.setDisabledTextColor(new java.awt.Color(255, 153, 51));
        jtMatriculaVoo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtMatriculaVooMouseClicked(evt);
            }
        });
        jtMatriculaVoo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtMatriculaVooKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtMatriculaVooKeyReleased(evt);
            }
        });

        jlMat26.setBackground(new java.awt.Color(153, 255, 0));
        jlMat26.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jlMat26.setForeground(new java.awt.Color(0, 0, 153));
        jlMat26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlMat26.setText("Matrícula");

        jbPesquisarDadosUser.setBackground(new java.awt.Color(0, 0, 102));
        jbPesquisarDadosUser.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jbPesquisarDadosUser.setForeground(new java.awt.Color(255, 255, 255));
        jbPesquisarDadosUser.setText("Pesquisar");
        jbPesquisarDadosUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPesquisarDadosUserActionPerformed(evt);
            }
        });
        jbPesquisarDadosUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jbPesquisarDadosUserKeyPressed(evt);
            }
        });

        jbLimparVoo.setBackground(new java.awt.Color(204, 0, 0));
        jbLimparVoo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jbLimparVoo.setForeground(new java.awt.Color(255, 255, 255));
        jbLimparVoo.setText("Limpar");
        jbLimparVoo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbLimparVoo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLimparVooActionPerformed(evt);
            }
        });

        jtNumeroLoja.setEditable(false);
        jtNumeroLoja.setBackground(new java.awt.Color(255, 153, 102));
        jtNumeroLoja.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jtNumeroLoja.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtNumeroLoja.setDisabledTextColor(new java.awt.Color(255, 153, 51));

        jlMat28.setBackground(new java.awt.Color(153, 255, 0));
        jlMat28.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jlMat28.setForeground(new java.awt.Color(0, 0, 153));
        jlMat28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlMat28.setText("Venda");

        jlMat29.setBackground(new java.awt.Color(153, 255, 0));
        jlMat29.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jlMat29.setForeground(new java.awt.Color(0, 0, 153));
        jlMat29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlMat29.setText("Cliente");

        jfVendaPDV1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jfVendaPDV1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVendaPDV1.setEnabled(false);
        jfVendaPDV1.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVendaPDV1.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVendaPDV1.setPreferredSize(new java.awt.Dimension(12, 26));
        jfVendaPDV1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfVendaPDV1ActionPerformed(evt);
            }
        });

        jfVendaPDV2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jfVendaPDV2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVendaPDV2.setEnabled(false);
        jfVendaPDV2.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVendaPDV2.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVendaPDV2.setPreferredSize(new java.awt.Dimension(12, 26));
        jfVendaPDV2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfVendaPDV2ActionPerformed(evt);
            }
        });

        jfVendaPDV4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jfVendaPDV4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVendaPDV4.setEnabled(false);
        jfVendaPDV4.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVendaPDV4.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVendaPDV4.setPreferredSize(new java.awt.Dimension(12, 26));
        jfVendaPDV4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfVendaPDV4ActionPerformed(evt);
            }
        });

        jfVendaPDV3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jfVendaPDV3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVendaPDV3.setEnabled(false);
        jfVendaPDV3.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVendaPDV3.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVendaPDV3.setPreferredSize(new java.awt.Dimension(12, 26));
        jfVendaPDV3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfVendaPDV3ActionPerformed(evt);
            }
        });

        jfVooCliPDV1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfVooCliPDV1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVooCliPDV1.setEnabled(false);
        jfVooCliPDV1.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVooCliPDV1.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVooCliPDV1.setPreferredSize(new java.awt.Dimension(12, 26));
        jfVooCliPDV1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfVooCliPDV1ActionPerformed(evt);
            }
        });

        jfVooCliPDV2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfVooCliPDV2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVooCliPDV2.setEnabled(false);
        jfVooCliPDV2.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVooCliPDV2.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVooCliPDV2.setPreferredSize(new java.awt.Dimension(12, 26));
        jfVooCliPDV2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfVooCliPDV2ActionPerformed(evt);
            }
        });

        jfVooCliPDV3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfVooCliPDV3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVooCliPDV3.setEnabled(false);
        jfVooCliPDV3.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVooCliPDV3.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVooCliPDV3.setPreferredSize(new java.awt.Dimension(12, 26));
        jfVooCliPDV3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfVooCliPDV3ActionPerformed(evt);
            }
        });

        jfVooCliPDV4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfVooCliPDV4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVooCliPDV4.setEnabled(false);
        jfVooCliPDV4.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVooCliPDV4.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVooCliPDV4.setPreferredSize(new java.awt.Dimension(12, 26));
        jfVooCliPDV4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfVooCliPDV4ActionPerformed(evt);
            }
        });

        jfVooVendaTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfVooVendaTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVooVendaTotal.setEnabled(false);
        jfVooVendaTotal.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVooVendaTotal.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jfVooVendaTotal.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVooVendaTotal.setPreferredSize(new java.awt.Dimension(12, 26));

        jfVooCliTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfVooCliTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVooCliTotal.setEnabled(false);
        jfVooCliTotal.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVooCliTotal.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jfVooCliTotal.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVooCliTotal.setPreferredSize(new java.awt.Dimension(12, 26));

        try {
            jtDataAtual.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtDataAtual.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtDataAtual.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jtDataAtual.setMinimumSize(new java.awt.Dimension(12, 26));
        jtDataAtual.setPreferredSize(new java.awt.Dimension(127, 26));
        jtDataAtual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtDataAtualActionPerformed(evt);
            }
        });

        jbEditarVoo.setBackground(new java.awt.Color(0, 0, 102));
        jbEditarVoo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jbEditarVoo.setForeground(new java.awt.Color(255, 255, 255));
        jbEditarVoo.setText("Alterar");
        jbEditarVoo.setEnabled(false);
        jbEditarVoo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditarVooActionPerformed(evt);
            }
        });
        jbEditarVoo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jbEditarVooKeyPressed(evt);
            }
        });

        jlMat30.setBackground(new java.awt.Color(153, 255, 0));
        jlMat30.setFont(new java.awt.Font("Consolas", 3, 24)); // NOI18N
        jlMat30.setForeground(new java.awt.Color(0, 0, 153));
        jlMat30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlMat30.setText("TKM Atual");

        jfVooVenda5.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfVooVenda5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVooVenda5.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVooVenda5.setFont(new java.awt.Font("sansserif", 3, 18)); // NOI18N
        jfVooVenda5.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVooVenda5.setPreferredSize(new java.awt.Dimension(12, 26));
        jfVooVenda5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfVooVenda5ActionPerformed(evt);
            }
        });

        jlTituloPlanoDeVoo.setBackground(new java.awt.Color(51, 255, 51));
        jlTituloPlanoDeVoo.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jlTituloPlanoDeVoo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTituloPlanoDeVoo.setText("Plano de Voo Individual");

        jlMat31.setBackground(new java.awt.Color(153, 255, 0));
        jlMat31.setFont(new java.awt.Font("Consolas", 3, 24)); // NOI18N
        jlMat31.setForeground(new java.awt.Color(0, 0, 153));
        jlMat31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlMat31.setText("Acumulado Venda");

        jfVooVenda6.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfVooVenda6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVooVenda6.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVooVenda6.setFont(new java.awt.Font("sansserif", 3, 18)); // NOI18N
        jfVooVenda6.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVooVenda6.setPreferredSize(new java.awt.Dimension(12, 26));
        jfVooVenda6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfVooVenda6ActionPerformed(evt);
            }
        });

        jlMat32.setBackground(new java.awt.Color(153, 255, 0));
        jlMat32.setFont(new java.awt.Font("Consolas", 3, 24)); // NOI18N
        jlMat32.setForeground(new java.awt.Color(0, 0, 153));
        jlMat32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlMat32.setText("Acumulado Clientes");

        jfVooCli5.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfVooCli5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVooCli5.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVooCli5.setFont(new java.awt.Font("sansserif", 3, 18)); // NOI18N
        jfVooCli5.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVooCli5.setPreferredSize(new java.awt.Dimension(12, 26));
        jfVooCli5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfVooCli5ActionPerformed(evt);
            }
        });

        jlTituloPlanoDeVoo1.setBackground(new java.awt.Color(51, 255, 51));
        jlTituloPlanoDeVoo1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jlTituloPlanoDeVoo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTituloPlanoDeVoo1.setText("Gráfico Anual Individual");

        jlTituloPlanoDeVoo2.setBackground(new java.awt.Color(51, 255, 51));
        jlTituloPlanoDeVoo2.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jlTituloPlanoDeVoo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTituloPlanoDeVoo2.setText("Acumulado");

        javax.swing.GroupLayout jPanel3PlanoDeVooLayout = new javax.swing.GroupLayout(jPanel3PlanoDeVoo);
        jPanel3PlanoDeVoo.setLayout(jPanel3PlanoDeVooLayout);
        jPanel3PlanoDeVooLayout.setHorizontalGroup(
            jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlTituloPlanoDeVoo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3PlanoDeVooLayout.createSequentialGroup()
                .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3PlanoDeVooLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlTituloPlanoDeVoo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3PlanoDeVooLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jfVooCli5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jfVooVenda5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jfVooVenda6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlMat30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlMat32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlMat31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jlTituloPlanoDeVoo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3PlanoDeVooLayout.createSequentialGroup()
                        .addContainerGap(16, Short.MAX_VALUE)
                        .addComponent(jlMat26, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtMatriculaVoo, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbPesquisarDadosUser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbEditarVoo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbLimparVoo)
                        .addGap(0, 10, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3PlanoDeVooLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jtNumeroLoja, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtDataAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3PlanoDeVooLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlMat28, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlMat29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jfVendaPDV1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jfVooCliPDV1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jfVooCliPDV2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jfVendaPDV2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jfVooCliPDV3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jfVendaPDV3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jfVendaPDV4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jfVooCliPDV4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jfVooCliTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jfVooVendaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3PlanoDeVooLayout.setVerticalGroup(
            jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3PlanoDeVooLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlTituloPlanoDeVoo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtMatriculaVoo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlMat26, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbPesquisarDadosUser)
                        .addComponent(jbEditarVoo)
                        .addComponent(jbLimparVoo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3PlanoDeVooLayout.createSequentialGroup()
                                .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jfVendaPDV1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlMat28, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jfVooCliPDV1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlMat29, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3PlanoDeVooLayout.createSequentialGroup()
                                .addComponent(jfVendaPDV2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jfVooCliPDV2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jfVendaPDV3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3PlanoDeVooLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jfVooCliPDV3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jfVendaPDV4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jfVooVendaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3PlanoDeVooLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jfVooCliPDV4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jfVooCliTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlTituloPlanoDeVoo2)
                .addGap(27, 27, 27)
                .addComponent(jlMat31, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jfVooVenda6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jlMat32, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jfVooCli5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jlMat30, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jfVooVenda5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlTituloPlanoDeVoo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtNumeroLoja, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtDataAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3PlanoDeVoo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3PlanoDeVoo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Plano de Voo Individual", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 904, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 447, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Metas Colaboradores", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jtMatriculaVooMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtMatriculaVooMouseClicked
        jtMatriculaVoo.setText("");
        jtNomeUsuario.setText("");
    }//GEN-LAST:event_jtMatriculaVooMouseClicked

    private void jtMatriculaVooKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtMatriculaVooKeyPressed
        verificaOpcaoSelecionada(evt, "matriVoo");
    }//GEN-LAST:event_jtMatriculaVooKeyPressed

    private void jtMatriculaVooKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtMatriculaVooKeyReleased
        pesquisarUsuarioNoBanco();
    }//GEN-LAST:event_jtMatriculaVooKeyReleased

    private void jbPesquisarDadosUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPesquisarDadosUserActionPerformed
        if ("Pesquisar".equals(jbPesquisarDadosUser.getText())) {
            try {
                carregaCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Falha: " + ex);
            }
        } else if ("Salvar".equals(jbPesquisarDadosUser.getText())) {
            try {
                salvarPlanoDeVoo();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Falha: " + ex);
            }
        }

    }//GEN-LAST:event_jbPesquisarDadosUserActionPerformed

    private void jbPesquisarDadosUserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jbPesquisarDadosUserKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                //salvarPlanoDeVoo();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Falha: " + ex);
            }
        }
    }//GEN-LAST:event_jbPesquisarDadosUserKeyPressed

    private void jbLimparVooActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLimparVooActionPerformed
        limparCampos();
    }//GEN-LAST:event_jbLimparVooActionPerformed

    private void jfVendaPDV1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfVendaPDV1ActionPerformed
        jfVooCliPDV1.requestFocus();
        somaValores();
    }//GEN-LAST:event_jfVendaPDV1ActionPerformed

    private void jfVendaPDV2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfVendaPDV2ActionPerformed
        jfVooCliPDV2.requestFocus();
        somaValores();
    }//GEN-LAST:event_jfVendaPDV2ActionPerformed

    private void jfVendaPDV4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfVendaPDV4ActionPerformed
        jfVooCliPDV4.requestFocus();
        somaValores();
    }//GEN-LAST:event_jfVendaPDV4ActionPerformed

    private void jfVendaPDV3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfVendaPDV3ActionPerformed
        jfVooCliPDV3.requestFocus();
        somaValores();
    }//GEN-LAST:event_jfVendaPDV3ActionPerformed

    private void jfVooCliPDV1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfVooCliPDV1ActionPerformed
        jfVendaPDV2.requestFocus();
        somaValores();
    }//GEN-LAST:event_jfVooCliPDV1ActionPerformed

    private void jfVooCliPDV2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfVooCliPDV2ActionPerformed
        jfVendaPDV3.requestFocus();
        somaValores();
    }//GEN-LAST:event_jfVooCliPDV2ActionPerformed

    private void jfVooCliPDV3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfVooCliPDV3ActionPerformed
        jfVendaPDV4.requestFocus();
        somaValores();
    }//GEN-LAST:event_jfVooCliPDV3ActionPerformed

    private void jfVooCliPDV4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfVooCliPDV4ActionPerformed
        jtDataAtual.requestFocus();
        somaValores();
    }//GEN-LAST:event_jfVooCliPDV4ActionPerformed

    private void jtDataAtualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtDataAtualActionPerformed
        jbPesquisarDadosUser.requestFocus();
        somaValores();
    }//GEN-LAST:event_jtDataAtualActionPerformed

    private void jbEditarVooActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarVooActionPerformed
        HabilitaDesabilita(1);
    }//GEN-LAST:event_jbEditarVooActionPerformed

    private void jbEditarVooKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jbEditarVooKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbEditarVooKeyPressed

    private void jfVooVenda5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfVooVenda5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jfVooVenda5ActionPerformed

    private void jfVooVenda6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfVooVenda6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jfVooVenda6ActionPerformed

    private void jfVooCli5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfVooCli5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jfVooCli5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel3PlanoDeVoo;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton jbEditarVoo;
    private javax.swing.JButton jbLimparVoo;
    private javax.swing.JButton jbPesquisarDadosUser;
    private javax.swing.JFormattedTextField jfVendaPDV1;
    private javax.swing.JFormattedTextField jfVendaPDV2;
    private javax.swing.JFormattedTextField jfVendaPDV3;
    private javax.swing.JFormattedTextField jfVendaPDV4;
    private javax.swing.JFormattedTextField jfVooCli5;
    private javax.swing.JFormattedTextField jfVooCliPDV1;
    private javax.swing.JFormattedTextField jfVooCliPDV2;
    private javax.swing.JFormattedTextField jfVooCliPDV3;
    private javax.swing.JFormattedTextField jfVooCliPDV4;
    private javax.swing.JFormattedTextField jfVooCliTotal;
    private javax.swing.JFormattedTextField jfVooVenda5;
    private javax.swing.JFormattedTextField jfVooVenda6;
    private javax.swing.JFormattedTextField jfVooVendaTotal;
    private javax.swing.JLabel jlMat26;
    private javax.swing.JLabel jlMat28;
    private javax.swing.JLabel jlMat29;
    private javax.swing.JLabel jlMat30;
    private javax.swing.JLabel jlMat31;
    private javax.swing.JLabel jlMat32;
    private javax.swing.JLabel jlTituloPlanoDeVoo;
    private javax.swing.JLabel jlTituloPlanoDeVoo1;
    private javax.swing.JLabel jlTituloPlanoDeVoo2;
    private javax.swing.JFormattedTextField jtDataAtual;
    private javax.swing.JTextField jtMatriculaVoo;
    private javax.swing.JTextField jtNomeUsuario;
    private javax.swing.JTextField jtNumeroLoja;
    // End of variables declaration//GEN-END:variables

}
