package modulo.configuracoes;

import modulo.metodos.SegurancaFuncoes;
import modulo.DAO.UsuarioDAO;
import ConexaoData.Conexao;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import javax.swing.JOptionPane;
import modulo.metodos.ConvertMD5;
import modulo.metodos.CryptoBase64;
import modulo.versao.Versao;
import modulo.login.UserLogado;
import modulo.entidades.Usuario;
import javax.swing.ImageIcon;
import modulo.DAO.SegurancaDAO;
import modulo.entidades.Seguranca;
import modulo.metodos.Funcao;

/**
 *
 * @author Marcos Junior
 */
public final class JfConfig extends javax.swing.JFrame {

    private String loja = "prop.server.loja";
    private String host = "prop.server.host";
    private String port = "prop.server.port";
    private String user = "prop.server.user";
    private String pass = "prop.server.password";
    private String base = "prop.server.base";
    private Properties properties;
    private ConvertMD5 md5;
    private Versao ver;
    private UserLogado login;
    private UsuarioDAO DAOUser;
    private Usuario userAut;
    private Config conf;
    private Conexao con;
    private CryptoBase64 cry;
    private String senhadoBanco = null;
    private String funcao = null;
    private String senha = "1234";
    private SegurancaFuncoes funSerg;
    private SegurancaDAO SEGDAO;
    private Seguranca s;
    private Funcao fun;

    public JfConfig() {
        initComponents();
        properties = new Properties();
        md5 = new ConvertMD5();
        DAOUser = new UsuarioDAO();
        login = new UserLogado();
        ver = new Versao();
        cry = new CryptoBase64();
        funSerg = new SegurancaFuncoes();
        SEGDAO = new SegurancaDAO();
        fun = new Funcao();
        setTitle("Configuração: " + ver.getVersao());
        Desabilita();
        try {
            CarregaDados();
            TestaConexao();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Carregar Arquivo!" + ex.getMessage());
        } catch (Exception ex) {
            jtStatus.setText("Offline");
            jtStatus.setBackground(Color.RED);
        }
        jtLoja.requestFocus();
        jPanel1.setVisible(false);
        jPanel2.setVisible(true);
        jlAviso.setText("Configuração de Acesso da Base de Dados!!");
        SegurancaDados();
    }

    private void SegurancaDados() {
        try {
            jtSerialPlaca.setText(funSerg.getHDSerial());
            jtAutorizacao.setText("");
            if (SEGDAO.PesquisaRegistro(funSerg.getHDSerial()).getStatus() != null) {
                jtAutorizacao.setText(SEGDAO.PesquisaRegistro(funSerg.getHDSerial()).getStatus());
            } else {
                jtAutorizacao.setText("PENDENTE");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro Segurança! IOE " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro Segurança! EX " + ex.getMessage());
        }
    }

    public void preencherObjetos() {
        s = new Seguranca();
        s.setSeriralPlacaMae(jtSerialPlaca.getText());
        s.setDataRegistro(fun.atualDateSQL());
    }

    public void inserirSerial() {
        try {
            if (!jtSerialPlaca.getText().equals("")) {
                if (!SEGDAO.CheckSelect(jtSerialPlaca.getText())) {
                    preencherObjetos();
                    if (SEGDAO.InsertSerial(s)) {
                        JOptionPane.showMessageDialog(this, "Salvo Com Sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Não foi Possível Salvar!");
                    }
                }
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(this, "Erro ao Salvar: " + erro.getMessage());
        }
    }

    public void TestaConexao() {
        try {
            con = new Conexao();
            if (con.getCONEXAO().isValid(0)) {
                jtStatus.setText("Online");
                jtStatus.setBackground(Color.GREEN);
            } else {
                jtStatus.setText("Offline");
                jtStatus.setBackground(Color.RED);
            }
        } catch (Exception ex) {
            jtStatus.setText("Offline");
            jtStatus.setBackground(Color.RED);
        }

    }

    public void AutorizaEdicao() throws NoSuchAlgorithmException, Exception {
        Habilita();
    }

    public void Habilita() {
        boolean h = true;
        jtLoja.setEnabled(h);
        jtHost.setEnabled(h);
        jtPort.setEnabled(h);
        jtUser.setEnabled(h);
        jtPass.setEnabled(h);
        jtBase.setEnabled(h);
        jbSalvar.setEnabled(h);
        jbEditar.setEnabled(false);
        jbAtualizar.setEnabled(false);
        try {
            Decrypt();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Falha ao Descryptografar Dados!" + ex.getMessage());
        }
    }

    public void Desabilita() {
        boolean h = false;
        jtLoja.setEnabled(h);
        jtHost.setEnabled(h);
        jtPort.setEnabled(h);
        jtUser.setEnabled(h);
        jtPass.setEnabled(h);
        jtBase.setEnabled(h);
        jbEditar.setEnabled(true);
        jbAtualizar.setEnabled(true);
    }

    public static Properties getProp() throws IOException {
        Properties props = new Properties();
        FileInputStream file = new FileInputStream(
                "./properties/dados.properties");
        props.load(file);
        return props;
    }

    public void CarregaDados() throws IOException {
        Properties prop = getProp();
        jtLoja.setText(prop.getProperty(loja));
        jtHost.setText(prop.getProperty(host));
        jtPort.setText(prop.getProperty(port));
        jtUser.setText(prop.getProperty(user));
        jtPass.setText(prop.getProperty(pass));
        jtBase.setText(prop.getProperty(base));
    }

    public void Decrypt() throws IOException {
        Properties prop = getProp();
        jtLoja.setText(prop.getProperty(loja));
        jtHost.setText(cry.decrypt(prop.getProperty(host)));
        jtPort.setText(cry.decrypt(prop.getProperty(port)));
        jtUser.setText(cry.decrypt(prop.getProperty(user)));
        jtPass.setText(cry.decrypt(prop.getProperty(pass)));
        jtBase.setText(cry.decrypt(prop.getProperty(base)));
    }

    public void EditaDados() throws FileNotFoundException, IOException {
        File file = new File("./properties/dados.properties");
        FileInputStream fis = new FileInputStream(file);
        properties.load(fis);
        properties.setProperty(loja, jtLoja.getText());
        properties.setProperty(host, cry.encrypt(jtHost.getText()));
        properties.setProperty(port, cry.encrypt(jtPort.getText()));
        properties.setProperty(user, cry.encrypt(jtUser.getText()));
        properties.setProperty(pass, cry.encrypt(jtPass.getText()));
        properties.setProperty(base, cry.encrypt(jtBase.getText()));
        FileOutputStream fos = new FileOutputStream(file);
        properties.store(fos, "FILE DADOS PROPERTIES:");
        fos.close();
        conf.CarregaDados();
    }

    public boolean VerificaCampos() {
        boolean chek = true;
        if (jtLoja.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Digite o campo Loja!");
            chek = false;
        }
        if (jtHost.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Digite o campo Host!");
            chek = false;
        }
        if (jtPort.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Digite o campo Porta!");
            chek = false;
        }
        if (jtUser.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Digite o campo User!");
            chek = false;
        }
        if (jtPass.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Digite o campo Password!");
            chek = false;
        }
        if (jtBase.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Digite o campo Base!");
            chek = false;
        }
        return chek;
    }

    public boolean VerificaPass() {
        boolean very;
        if ("1234".equals(senha)) {
            jPanel1.setVisible(true);
            jPanel2.setVisible(false);
            very = true;
        } else {
            jlAviso.setText("Senha Inválida!!");
            very = false;
            jPasswordConfig.requestFocus();
        }
        return very;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPasswordConfig = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        jlAviso = new javax.swing.JLabel();
        jbEntrar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtLoja = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtHost = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtPort = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtUser = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtPass = new javax.swing.JTextField();
        jtBase = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jtAutorizacao = new javax.swing.JTextField();
        jtStatus = new javax.swing.JTextField();
        jtSerialPlaca = new javax.swing.JTextField();
        jbEditar = new javax.swing.JButton();
        jbSalvar = new javax.swing.JButton();
        jbAtualizar = new javax.swing.JButton();
        jbCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setIconImage(new ImageIcon(getClass().getResource("/icons/icone_sistema.png")).getImage());
        setLocationByPlatform(true);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(0, 204, 255));

        jPasswordConfig.setFont(new java.awt.Font("sansserif", 2, 18)); // NOI18N
        jPasswordConfig.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPasswordConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordConfigActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("sansserif", 3, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Senha de Configuração.");

        jlAviso.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jlAviso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlAviso.setText("-");

        jbEntrar.setBackground(new java.awt.Color(102, 255, 204));
        jbEntrar.setFont(new java.awt.Font("sansserif", 3, 18)); // NOI18N
        jbEntrar.setText("Entrar");
        jbEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEntrarActionPerformed(evt);
            }
        });
        jbEntrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jbEntrarKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jbEntrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlAviso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(jPasswordConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(jPasswordConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlAviso)
                .addGap(26, 26, 26)
                .addComponent(jbEntrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        jLabel1.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Loja");

        jtLoja.setBackground(new java.awt.Color(153, 255, 204));
        jtLoja.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jtLoja.setForeground(new java.awt.Color(0, 0, 255));
        jtLoja.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtLoja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtLojaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Host");

        jtHost.setBackground(new java.awt.Color(153, 255, 204));
        jtHost.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jtHost.setForeground(new java.awt.Color(0, 0, 255));
        jtHost.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtHost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtHostActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Port");

        jtPort.setBackground(new java.awt.Color(153, 255, 204));
        jtPort.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jtPort.setForeground(new java.awt.Color(0, 0, 255));
        jtPort.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtPortActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("User");

        jtUser.setBackground(new java.awt.Color(153, 255, 204));
        jtUser.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jtUser.setForeground(new java.awt.Color(0, 0, 255));
        jtUser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtUserActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Pass");

        jtPass.setBackground(new java.awt.Color(153, 255, 204));
        jtPass.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jtPass.setForeground(new java.awt.Color(0, 0, 255));
        jtPass.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtPassActionPerformed(evt);
            }
        });

        jtBase.setBackground(new java.awt.Color(153, 255, 204));
        jtBase.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jtBase.setForeground(new java.awt.Color(0, 0, 255));
        jtBase.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtBaseActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("BD");

        jLabel8.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Registro");

        jLabel9.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Conexão");

        jLabel10.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Aut");

        jtAutorizacao.setBackground(new java.awt.Color(204, 255, 153));
        jtAutorizacao.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jtAutorizacao.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtAutorizacao.setEnabled(false);

        jtStatus.setEditable(false);
        jtStatus.setBackground(new java.awt.Color(51, 255, 51));
        jtStatus.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jtStatus.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jtSerialPlaca.setBackground(new java.awt.Color(204, 255, 153));
        jtSerialPlaca.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jtSerialPlaca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtSerialPlaca.setEnabled(false);

        jbEditar.setBackground(new java.awt.Color(51, 255, 102));
        jbEditar.setText("Editar");
        jbEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditarActionPerformed(evt);
            }
        });

        jbSalvar.setBackground(new java.awt.Color(0, 255, 204));
        jbSalvar.setText("Salvar");
        jbSalvar.setEnabled(false);
        jbSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvarActionPerformed(evt);
            }
        });

        jbAtualizar.setBackground(new java.awt.Color(153, 153, 255));
        jbAtualizar.setText("Atualiza");
        jbAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAtualizarActionPerformed(evt);
            }
        });

        jbCancelar.setBackground(new java.awt.Color(0, 255, 204));
        jbCancelar.setText("Fechar");
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtLoja, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtHost, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtPort, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtAutorizacao)
                            .addComponent(jtSerialPlaca)
                            .addComponent(jtStatus)
                            .addComponent(jtBase))
                        .addGap(14, 14, 14))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jbEditar)
                        .addGap(18, 18, 18)
                        .addComponent(jbAtualizar)
                        .addGap(18, 18, 18)
                        .addComponent(jbSalvar)
                        .addGap(18, 18, 18)
                        .addComponent(jbCancelar)
                        .addContainerGap(52, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jtBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jtSerialPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(68, 68, 68))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jtLoja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jtHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(jtAutorizacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(jtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbEditar)
                    .addComponent(jbSalvar)
                    .addComponent(jbAtualizar)
                    .addComponent(jbCancelar))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jtLojaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtLojaActionPerformed
        jtHost.requestFocus();
    }//GEN-LAST:event_jtLojaActionPerformed

    private void jtHostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtHostActionPerformed
        jtPort.requestFocus();
    }//GEN-LAST:event_jtHostActionPerformed

    private void jtPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtPortActionPerformed
        jtUser.requestFocus();
    }//GEN-LAST:event_jtPortActionPerformed

    private void jtUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtUserActionPerformed
        jtPass.requestFocus();
    }//GEN-LAST:event_jtUserActionPerformed

    private void jtPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtPassActionPerformed
        jtBase.requestFocus();
    }//GEN-LAST:event_jtPassActionPerformed

    private void jtBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtBaseActionPerformed
        jbSalvar.requestFocus();
    }//GEN-LAST:event_jtBaseActionPerformed

    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarActionPerformed
        try {
            AutorizaEdicao();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Falha Erro!" + ex.getMessage());
        }
    }//GEN-LAST:event_jbEditarActionPerformed

    private void jbSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarActionPerformed
        if (VerificaCampos()) {
            try {
                EditaDados();
                Desabilita();
                TestaConexao();
                inserirSerial();
                JOptionPane.showMessageDialog(this, "Arquivo editado com sucesso!");
                jbSalvar.setEnabled(false);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao Salvar Arquivo!" + ex.getMessage());
            }
        }
    }//GEN-LAST:event_jbSalvarActionPerformed

    private void jbAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAtualizarActionPerformed
        try {
            CarregaDados();
            TestaConexao();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Carregar o Arquivo!" + ex.getMessage());
        } catch (Exception ex) {
            jtStatus.setText("Offline");
            jtStatus.setBackground(Color.RED);
        }
    }//GEN-LAST:event_jbAtualizarActionPerformed

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        if (jtAutorizacao.getText().equals("PENDENTE") || jtAutorizacao.getText().equals("DESATIVADO") || jtAutorizacao.getText().equals("")) {
            System.exit(0);
        } else {
            this.dispose();
        }
    }//GEN-LAST:event_jbCancelarActionPerformed

    private void jbEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEntrarActionPerformed
        VerificaPass();

    }//GEN-LAST:event_jbEntrarActionPerformed

    private void jbEntrarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jbEntrarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            VerificaPass();
        }
    }//GEN-LAST:event_jbEntrarKeyPressed

    private void jPasswordConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordConfigActionPerformed
        jbEntrar.requestFocus();
    }//GEN-LAST:event_jPasswordConfigActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordConfig;
    private javax.swing.JButton jbAtualizar;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbEntrar;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JLabel jlAviso;
    private javax.swing.JTextField jtAutorizacao;
    private javax.swing.JTextField jtBase;
    private javax.swing.JTextField jtHost;
    private javax.swing.JTextField jtLoja;
    private javax.swing.JTextField jtPass;
    private javax.swing.JTextField jtPort;
    private javax.swing.JTextField jtSerialPlaca;
    private javax.swing.JTextField jtStatus;
    private javax.swing.JTextField jtUser;
    // End of variables declaration//GEN-END:variables
}
