package modulo.view.principal;

import modulo.metodos.JifCarregamento;
import modulo.login.JfLoginUi;
import java.awt.Image;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import modulo.configuracoes.JifConfig;
import modulo.sobre.JifSobre;
import modulo.usuarios.UsuarioDAO;
import java.text.DateFormat;
import java.util.Date;
import modulo.jasper.JasperDAO;
import modulo.loja.JifLoja;
import javax.swing.JOptionPane;
import modulo.desenvolvimento.frames.JifAniversariantes;
import modulo.desenvolvimento.frames.JifEmail;
import modulo.desenvolvimento.frames.JifAvariasKibom;
import modulo.desenvolvimento.frames.JifAvariasYork;
import modulo.clientes.JifClientes;
import modulo.contas.JifControleDeContas;
import modulo.inventario.JifInventario;
import modulo.faturamento.JifFaturamento;
import modulo.desenvolvimento.frames.JifNotasDoSistema;
import modulo.desenvolvimento.frames.JifBackup;
import modulo.desenvolvimento.frames.JifCancelamentos;
import modulo.usuarios.JifCargos;
import modulo.desenvolvimento.frames.JifCreditoDigital;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import modulo.login.UserLogado;
import modulo.usuarios.JifUsuarios;
import modulo.sobre.Versao;
import java.text.SimpleDateFormat;
import modulo.ajuda.JfAjuda;
import modulo.dashboard.Dashboard;
import modulo.loja.LojaDAO;
import modulo.usuarios.Usuario;

/**
 *
 * @author Marcos Junior
 */
public final class JfPrincipal extends javax.swing.JFrame {

    private Versao ver = new Versao();
    private UserLogado login = new UserLogado();
    private DateFormat formatoHora;
    private String funcao = null;
    private String admin = "ASSISTENTE DE GERENTE",
            gerente = "GERENTE",
            farmaceuticos = "FARMACÊUTICO",
            balconista = "AUXILIAR DE FARMÁCIA",
            caixa = "CAIXA";
    private JifCarregamento jfC = null;
    private UsuarioDAO DAOUser;
    private LojaDAO DAOLoja;
    private JfAjuda Ajud = null;
    private Usuario userAut;
    private String Aviso = "Atenção";
    private JifUsuarios jfUser = null;
    private JifLoja jfLj = null;
    private JifSobre jfSob = null;
    private JifConfig jfConf = null;
    private JifClientes jfCli = null;
    private JifAvariasKibom jfAvKibom = null;
    private JifAvariasYork jfAvYork = null;
    private JifNotasDoSistema jfNotas = null;
    private JifInventario jfInv = null;
    private JifControleDeContas jfContas = null;
    private JifBackup jfBack = null;
    private JifEmail jfEmail = null;
    private JifCreditoDigital jfCrediDig = null;
    private JifAniversariantes jfAniver = null;
    private JifCancelamentos jfCancela = null;
    private JifFaturamento jfFatCaixa = null;
    private JifCargos jfCargo = null;
    private Dashboard dash = null;

    public JfPrincipal() {
        initComponents();
        DAOUser = new UsuarioDAO();
        DAOLoja = new LojaDAO();
        this.formatoHora = new SimpleDateFormat("EEEEEEEEEEEEEE, dd/MM/yyyy    HH:mm:ss");
        Thread TabThred = new Thread(new JfPrincipal.clsDataHora());
        TabThred.start();
        Thread TabThred2 = new Thread(new JfPrincipal.clsTitle());
        TabThred2.start();
    }

    class clsTitle implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    setTitle(ver.getNomesys() + " " + ver.getVersao() + "     EMPRESA: " + DAOLoja.PesquisaIdLoja().getNome_loja());
                } catch (Exception ex) {
                    Logger.getLogger(JfPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println("Thread não foi finalizada:" + ex);
                }
            }
        }
    }

    public void iniciaDados() throws Exception {
        int matricula = Integer.parseInt(login.getMatricula());
        userAut = DAOUser.PesquisaPorMatricula(matricula);
        funcao = userAut.getFuncao();
    }

    public void Habilita(int tipo) {

    }

    public void Desabilita(int tipo) {

    }

    public void verificaAutorizacao() {
        if (funcao.equals(admin)) {
            Habilita(1);
            Desabilita(1);
        }
        if (funcao.equals(gerente)) {
            Habilita(2);
            Desabilita(2);
        }
        if (funcao.equals(caixa)) {
            Habilita(3);
            Desabilita(3);
        }
        if (funcao.equals(balconista)) {
            Habilita(4);
            Desabilita(4);
        }
        if (funcao.equals(farmaceuticos)) {
            Habilita(5);
            Desabilita(5);
        }

    }

    class clsDataHora implements Runnable {

        @Override
        public void run() {
            while (true) {
                jlInformacao.setText("BEM VINDO!  " + login.getNomelogin());
                jlHoraAtual.setText(formatoHora.format(new Date()) + " ");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println("Thread não foi finalizada:" + ex);
                }
            }
        }
    }

    public void Logoff() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfLoginUi.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new JfLoginUi().setVisible(true);
        });
    }

    void doMove(MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - evt.getX(), y - evt.getY());
    }

    public void setLblColor(JLabel lbl) {
        lbl.setBackground(new Color(22, 42, 57));
    }

    public void resetLblColor(JLabel lbl) {
        lbl.setBackground(new Color(33, 63, 86));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jlHoraAtual = new javax.swing.JLabel();
        jlInformacao = new javax.swing.JLabel();
        ImageIcon icon = new ImageIcon("img/desk/principal.jpg");
        Image image = icon.getImage();
        try {
            jDesktopPrincipal =(javax.swing.JDesktopPane)java.beans.Beans.instantiate(getClass().getClassLoader(), "modulo/view/principal.jfPrincipal_jDesktopPrincipal");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        jMenuBar1 = new javax.swing.JMenuBar();
        jmArquivo = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jmBackup = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jmCadastro = new javax.swing.JMenu();
        jMLoja = new javax.swing.JMenuItem();
        jmCargos = new javax.swing.JMenuItem();
        jmClientes = new javax.swing.JMenuItem();
        jmGerencia_User = new javax.swing.JMenuItem();
        jmFaturamento = new javax.swing.JMenu();
        jmFaturamentoCaixa = new javax.swing.JMenuItem();
        jmPlanoDeVoo = new javax.swing.JMenuItem();
        jmEnergiaEletrica = new javax.swing.JMenuItem();
        jmMetas = new javax.swing.JMenuItem();
        jmCancelamentos = new javax.swing.JMenuItem();
        jmPendenciaCTF = new javax.swing.JMenuItem();
        jmPopular = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jmEnviaEmail = new javax.swing.JMenuItem();
        lmLojasCadastradas = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jmRelatorioLoja = new javax.swing.JMenuItem();
        jmRelatorioUsuarios = new javax.swing.JMenuItem();
        jmRelatorioProdutos = new javax.swing.JMenuItem();
        jmRelatorioClientes = new javax.swing.JMenuItem();
        jmRelatorioDeTrocasEDevolucoes = new javax.swing.JMenuItem();
        jmRegistros = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuPainel = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jmAdicionarNotaSistema = new javax.swing.JMenuItem();
        jmNotasSistema = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jmMenuAjuda = new javax.swing.JMenu();
        jmSobre = new javax.swing.JMenuItem();
        jmManual = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(6);
        setIconImage(new ImageIcon(getClass().getResource("/icons/icone_sistema.png")).getImage());

        jlHoraAtual.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlHoraAtual.setText("jLabel1");

        jlInformacao.setText("jLabel2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jlInformacao, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 571, Short.MAX_VALUE)
                .addComponent(jlHoraAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jlHoraAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jlInformacao, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jDesktopPrincipal = new javax.swing.JDesktopPane(){
            public void paintComponent(Graphics g){
                g.drawImage(image,0,0,getWidth(),getHeight(),jDesktopPrincipal);
            }
        };

        javax.swing.GroupLayout jDesktopPrincipalLayout = new javax.swing.GroupLayout(jDesktopPrincipal);
        jDesktopPrincipal.setLayout(jDesktopPrincipalLayout);
        jDesktopPrincipalLayout.setHorizontalGroup(
            jDesktopPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jDesktopPrincipalLayout.setVerticalGroup(
            jDesktopPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
        );

        jmArquivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home.png"))); // NOI18N
        jmArquivo.setText("Arquivo");

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/configuracao.png"))); // NOI18N
        jMenuItem7.setText("Configurações");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jmArquivo.add(jMenuItem7);

        jmBackup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/drive_disk.png"))); // NOI18N
        jmBackup.setText("Backup");
        jmBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmBackupActionPerformed(evt);
            }
        });
        jmArquivo.add(jmBackup);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logof2.png"))); // NOI18N
        jMenuItem6.setText("Logof");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jmArquivo.add(jMenuItem6);

        jMenuBar1.add(jmArquivo);

        jmCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user_suit.png"))); // NOI18N
        jmCadastro.setText("Gerenciar");

        jMLoja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/layers.png"))); // NOI18N
        jMLoja.setText("Loja");
        jMLoja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMLojaActionPerformed(evt);
            }
        });
        jmCadastro.add(jMLoja);

        jmCargos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/status_online.png"))); // NOI18N
        jmCargos.setText("Cargos");
        jmCargos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmCargosActionPerformed(evt);
            }
        });
        jmCadastro.add(jmCargos);

        jmClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user.png"))); // NOI18N
        jmClientes.setText("Clientes");
        jmClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmClientesActionPerformed(evt);
            }
        });
        jmCadastro.add(jmClientes);

        jmGerencia_User.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/group.png"))); // NOI18N
        jmGerencia_User.setText("Usuários");
        jmGerencia_User.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmGerencia_UserActionPerformed(evt);
            }
        });
        jmCadastro.add(jmGerencia_User);

        jMenuBar1.add(jmCadastro);

        jmFaturamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/money.png"))); // NOI18N
        jmFaturamento.setText("Financeiro");

        jmFaturamentoCaixa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/money_add.png"))); // NOI18N
        jmFaturamentoCaixa.setText("Caixa");
        jmFaturamentoCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmFaturamentoCaixaActionPerformed(evt);
            }
        });
        jmFaturamento.add(jmFaturamentoCaixa);

        jmPlanoDeVoo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/money_add.png"))); // NOI18N
        jmPlanoDeVoo.setText("Planos de Assinatura");
        jmPlanoDeVoo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmPlanoDeVooActionPerformed(evt);
            }
        });
        jmFaturamento.add(jmPlanoDeVoo);

        jmEnergiaEletrica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/lightning.png"))); // NOI18N
        jmEnergiaEletrica.setText("Água e Luz");
        jmEnergiaEletrica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmEnergiaEletricaActionPerformed(evt);
            }
        });
        jmFaturamento.add(jmEnergiaEletrica);

        jmMetas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/chart_line_edit.png"))); // NOI18N
        jmMetas.setText("Contas a Pagar");
        jmMetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmMetasActionPerformed(evt);
            }
        });
        jmFaturamento.add(jmMetas);

        jmCancelamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/money_delete.png"))); // NOI18N
        jmCancelamentos.setText("Contas a Receber");
        jmCancelamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmCancelamentosActionPerformed(evt);
            }
        });
        jmFaturamento.add(jmCancelamentos);

        jmPendenciaCTF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/creditcards.png"))); // NOI18N
        jmPendenciaCTF.setText("Pendência CTF");
        jmPendenciaCTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmPendenciaCTFActionPerformed(evt);
            }
        });
        jmFaturamento.add(jmPendenciaCTF);

        jmPopular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/rosette.png"))); // NOI18N
        jmPopular.setText("Popular");
        jmPopular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmPopularActionPerformed(evt);
            }
        });
        jmFaturamento.add(jmPopular);

        jMenuBar1.add(jmFaturamento);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pill_go.png"))); // NOI18N
        jMenu3.setText("E-mail");
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        jmEnviaEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/tick.png"))); // NOI18N
        jmEnviaEmail.setText("Enviar E-mail");
        jmEnviaEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmEnviaEmailActionPerformed(evt);
            }
        });
        jMenu3.add(jmEnviaEmail);

        jMenuBar1.add(jMenu3);

        lmLojasCadastradas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/page.png"))); // NOI18N
        lmLojasCadastradas.setText("Relatórios");

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/page.png"))); // NOI18N
        jMenu5.setText("Relatório Gerencial");

        jmRelatorioLoja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/page.png"))); // NOI18N
        jmRelatorioLoja.setText("Relatório de Lojas");
        jmRelatorioLoja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmRelatorioLojaActionPerformed(evt);
            }
        });
        jMenu5.add(jmRelatorioLoja);

        jmRelatorioUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/page.png"))); // NOI18N
        jmRelatorioUsuarios.setText("Relatório de Usuários");
        jmRelatorioUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmRelatorioUsuariosActionPerformed(evt);
            }
        });
        jMenu5.add(jmRelatorioUsuarios);

        jmRelatorioProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/page.png"))); // NOI18N
        jmRelatorioProdutos.setText("Relatório de Produtos");
        jmRelatorioProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmRelatorioProdutosActionPerformed(evt);
            }
        });
        jMenu5.add(jmRelatorioProdutos);

        jmRelatorioClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/page.png"))); // NOI18N
        jmRelatorioClientes.setText("Relatorio de Clientes");
        jMenu5.add(jmRelatorioClientes);

        lmLojasCadastradas.add(jMenu5);

        jmRelatorioDeTrocasEDevolucoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/page.png"))); // NOI18N
        jmRelatorioDeTrocasEDevolucoes.setText("Relatório de Trocas e Devoluções");
        lmLojasCadastradas.add(jmRelatorioDeTrocasEDevolucoes);

        jmRegistros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/page.png"))); // NOI18N
        jmRegistros.setText("Registros Reforço Geral");
        jmRegistros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmRegistrosActionPerformed(evt);
            }
        });
        lmLojasCadastradas.add(jmRegistros);

        jMenuBar1.add(lmLojasCadastradas);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/layout_content.png"))); // NOI18N
        jMenu1.setText("Dashboard");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuPainel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/chart_line_add.png"))); // NOI18N
        jMenuPainel.setText("Painel");
        jMenuPainel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuPainelActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuPainel);

        jMenuBar1.add(jMenu1);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/flag_green.png"))); // NOI18N
        jMenu4.setText("Desenvolvimento");

        jmAdicionarNotaSistema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/comment_add.png"))); // NOI18N
        jmAdicionarNotaSistema.setText("Adicionar Notas");
        jmAdicionarNotaSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmAdicionarNotaSistemaActionPerformed(evt);
            }
        });
        jMenu4.add(jmAdicionarNotaSistema);

        jmNotasSistema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/comment.png"))); // NOI18N
        jmNotasSistema.setText("Notas do Sistema");
        jmNotasSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmNotasSistemaActionPerformed(evt);
            }
        });
        jMenu4.add(jmNotasSistema);

        jMenuBar1.add(jMenu4);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/page_find.png"))); // NOI18N
        jMenu6.setText("Atalhos");
        jMenuBar1.add(jMenu6);

        jmMenuAjuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ajuda.png"))); // NOI18N
        jmMenuAjuda.setText("Ajuda");

        jmSobre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/information.png"))); // NOI18N
        jmSobre.setText("Sobre");
        jmSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmSobreActionPerformed(evt);
            }
        });
        jmMenuAjuda.add(jmSobre);

        jmManual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/layout_content.png"))); // NOI18N
        jmManual.setText("Ajuda");
        jmManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmManualActionPerformed(evt);
            }
        });
        jmMenuAjuda.add(jmManual);

        jMenuBar1.add(jmMenuAjuda);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 1185, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setSize(new java.awt.Dimension(1201, 553));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jmGerencia_UserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmGerencia_UserActionPerformed
        if (jfUser == null) {
            jfUser = new JifUsuarios();
            jDesktopPrincipal.add(jfUser);
            jfUser.setVisible(true);
            jfUser.setPosicao();
        } else if (!jfUser.isVisible()) {
            jfUser = new JifUsuarios();
            jDesktopPrincipal.add(jfUser);
            jfUser.setVisible(true);
            jfUser.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmGerencia_UserActionPerformed

    private void jmRelatorioUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmRelatorioUsuariosActionPerformed
        int resposta;
        resposta = JOptionPane.showConfirmDialog(this, "Deseja Gerar o Relatório? ", Aviso, JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            Carregando();
            Thread t = new Thread() {
                @Override
                public void run() {
                    JasperDAO rlu = new JasperDAO();
                    rlu.RelatorioUsuário();
                    fechaCarregamento();
                }
            };
            t.start();
        }
    }//GEN-LAST:event_jmRelatorioUsuariosActionPerformed

    private void jMLojaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMLojaActionPerformed
        if (jfLj == null) {
            jfLj = new JifLoja();
            jDesktopPrincipal.add(jfLj);
            jfLj.setVisible(true);
            jfLj.setPosicao();
        } else if (!jfLj.isVisible()) {
            jfLj = new JifLoja();
            jDesktopPrincipal.add(jfLj);
            jfLj.setVisible(true);
            jfLj.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jMLojaActionPerformed

    private void jmRelatorioLojaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmRelatorioLojaActionPerformed
        int resposta;
        resposta = JOptionPane.showConfirmDialog(this, "Deseja Gerar o Relatório? ", Aviso, JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            Carregando();
            Thread t = new Thread() {
                @Override
                public void run() {
                    JasperDAO rlu = new JasperDAO();
                    rlu.RelatorioLoja();
                    fechaCarregamento();
                }
            };
            t.start();
        }
    }//GEN-LAST:event_jmRelatorioLojaActionPerformed

    private void jmRelatorioProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmRelatorioProdutosActionPerformed
        int resposta;
        resposta = JOptionPane.showConfirmDialog(this, "Deseja Gerar o Relatório? ", Aviso, JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            Carregando();
            Thread t = new Thread() {
                @Override
                public void run() {
                    JasperDAO rlu = new JasperDAO();
                    rlu.RelatorioProduto();
                    fechaCarregamento();
                }
            };
            t.start();
        }
    }//GEN-LAST:event_jmRelatorioProdutosActionPerformed

    private void jmRegistrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmRegistrosActionPerformed
        int resposta;
        resposta = JOptionPane.showConfirmDialog(this, "Deseja Gerar o Relatório? ", Aviso, JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            Carregando();
            Thread t = new Thread() {
                @Override
                public void run() {
                    JasperDAO rlu = new JasperDAO();
                    rlu.RelatorioReforco();
                    fechaCarregamento();
                }
            };
            t.start();
        }
    }//GEN-LAST:event_jmRegistrosActionPerformed

    private void jmSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmSobreActionPerformed
        if (jfSob == null) {
            jfSob = new JifSobre();
            jDesktopPrincipal.add(jfSob);
            jfSob.setVisible(true);
            jfSob.setPosicao();
        } else if (!jfSob.isVisible()) {
            jfSob = new JifSobre();
            jDesktopPrincipal.add(jfSob);
            jfSob.setVisible(true);
            jfSob.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmSobreActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        this.dispose();
        Logoff();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        if (jfConf == null) {
            jfConf = new JifConfig();
            jDesktopPrincipal.add(jfConf);
            jfConf.setVisible(true);
            jfConf.setPosicao();
        } else if (!jfConf.isVisible()) {
            jfConf = new JifConfig();
            jDesktopPrincipal.add(jfConf);
            jfConf.setVisible(true);
            jfConf.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jmFaturamentoCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmFaturamentoCaixaActionPerformed
        if (jfFatCaixa == null) {
            jfFatCaixa = new JifFaturamento();
            jDesktopPrincipal.add(jfFatCaixa);
            try {
                jfFatCaixa.setMaximum(true);

            } catch (PropertyVetoException ex) {
                Logger.getLogger(JfPrincipal.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            jfFatCaixa.setVisible(true);
            jfFatCaixa.setPosicao();
        } else if (!jfFatCaixa.isVisible()) {
            jfFatCaixa = new JifFaturamento();
            jDesktopPrincipal.add(jfFatCaixa);
            try {
                jfFatCaixa.setMaximum(true);

            } catch (PropertyVetoException ex) {
                Logger.getLogger(JfPrincipal.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            jfFatCaixa.setVisible(true);
            jfFatCaixa.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmFaturamentoCaixaActionPerformed

    private void jmClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmClientesActionPerformed
        if (jfCli == null) {
            jfCli = new JifClientes();
            jDesktopPrincipal.add(jfCli);
            jfCli.setVisible(true);
            jfCli.setPosicao();
        } else if (!jfCli.isVisible()) {
            jfCli = new JifClientes();
            jDesktopPrincipal.add(jfCli);
            jfCli.setVisible(true);
            jfCli.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmClientesActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed

    }//GEN-LAST:event_jMenu3ActionPerformed

    private void jmPlanoDeVooActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmPlanoDeVooActionPerformed

    }//GEN-LAST:event_jmPlanoDeVooActionPerformed

    private void jmPendenciaCTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmPendenciaCTFActionPerformed
 
    }//GEN-LAST:event_jmPendenciaCTFActionPerformed

    private void jmPopularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmPopularActionPerformed
        
    }//GEN-LAST:event_jmPopularActionPerformed

    private void jmEnergiaEletricaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmEnergiaEletricaActionPerformed
        if (jfContas == null) {
            jfContas = new JifControleDeContas();
            jDesktopPrincipal.add(jfContas);
            jfContas.setVisible(true);
            jfContas.setPosicao();
        } else if (!jfContas.isVisible()) {
            jfContas = new JifControleDeContas();
            jDesktopPrincipal.add(jfContas);
            jfContas.setVisible(true);
            jfContas.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmEnergiaEletricaActionPerformed

    private void jmBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmBackupActionPerformed
        if (jfBack == null) {
            jfBack = new JifBackup();
            jDesktopPrincipal.add(jfBack);
            jfBack.setVisible(true);
            jfBack.setPosicao();
        } else if (!jfBack.isVisible()) {
            jfBack = new JifBackup();
            jDesktopPrincipal.add(jfBack);
            jfBack.setVisible(true);
            jfBack.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmBackupActionPerformed

    private void jmNotasSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmNotasSistemaActionPerformed
        if (jfNotas == null) {
            jfNotas = new JifNotasDoSistema();
            jDesktopPrincipal.add(jfNotas);
            jfNotas.setVisible(true);
            jfNotas.setPosicao();
        } else if (!jfNotas.isVisible()) {
            jfNotas = new JifNotasDoSistema();
            jDesktopPrincipal.add(jfNotas);
            jfNotas.setVisible(true);
            jfNotas.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmNotasSistemaActionPerformed

    private void jmAdicionarNotaSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmAdicionarNotaSistemaActionPerformed

    }//GEN-LAST:event_jmAdicionarNotaSistemaActionPerformed

    private void jmEnviaEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmEnviaEmailActionPerformed
        if (jfEmail == null) {
            jfEmail = new JifEmail();
            jDesktopPrincipal.add(jfEmail);
            jfEmail.setVisible(true);
            jfEmail.setPosicao();
        } else if (!jfEmail.isVisible()) {
            jfEmail = new JifEmail();
            jDesktopPrincipal.add(jfEmail);
            jfEmail.setVisible(true);
            jfEmail.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmEnviaEmailActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jmCancelamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmCancelamentosActionPerformed
        if (jfCancela == null) {
            jfCancela = new JifCancelamentos();
            jDesktopPrincipal.add(jfCancela);
            jfCancela.setVisible(true);
            jfCancela.setPosicao();
        } else if (!jfCancela.isVisible()) {
            jfCancela = new JifCancelamentos();
            jDesktopPrincipal.add(jfCancela);
            jfCancela.setVisible(true);
            jfCancela.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmCancelamentosActionPerformed

    private void jmCargosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmCargosActionPerformed
        if (jfCargo == null) {
            jfCargo = new JifCargos();
            jDesktopPrincipal.add(jfCargo);
            jfCargo.setVisible(true);
            jfCargo.setPosicao();
        } else if (!jfCargo.isVisible()) {
            jfCargo = new JifCargos();
            jDesktopPrincipal.add(jfCargo);
            jfCargo.setVisible(true);
            jfCargo.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmCargosActionPerformed

    private void jmMetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmMetasActionPerformed

    }//GEN-LAST:event_jmMetasActionPerformed

    private void jMenuPainelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPainelActionPerformed
        if (dash == null) {
            dash = new Dashboard();
            jDesktopPrincipal.add(dash);
            try {
                dash.setMaximum(true);

            } catch (PropertyVetoException ex) {
                Logger.getLogger(JfPrincipal.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            dash.setVisible(true);
            dash.setPosicao();
        } else if (!dash.isVisible()) {
            dash = new Dashboard();
            jDesktopPrincipal.add(dash);
            try {
                dash.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(JfPrincipal.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            dash.setVisible(true);
            dash.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jMenuPainelActionPerformed

    private void jmManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmManualActionPerformed
        if (Ajud == null) {
            Ajud = new JfAjuda();
            jDesktopPrincipal.add(Ajud);
            try {
                Ajud.setMaximum(true);

            } catch (PropertyVetoException ex) {
                Logger.getLogger(JfPrincipal.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            Ajud.setVisible(true);
            Ajud.setPosicao();
        } else if (!Ajud.isVisible()) {
            Ajud = new JfAjuda();
            jDesktopPrincipal.add(Ajud);
            try {
                Ajud.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(JfPrincipal.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            Ajud.setVisible(true);
            Ajud.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmManualActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane jDesktopPrincipal;
    private javax.swing.JMenuItem jMLoja;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuPainel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jlHoraAtual;
    private javax.swing.JLabel jlInformacao;
    private javax.swing.JMenuItem jmAdicionarNotaSistema;
    private javax.swing.JMenu jmArquivo;
    private javax.swing.JMenuItem jmBackup;
    private javax.swing.JMenu jmCadastro;
    private javax.swing.JMenuItem jmCancelamentos;
    private javax.swing.JMenuItem jmCargos;
    private javax.swing.JMenuItem jmClientes;
    private javax.swing.JMenuItem jmEnergiaEletrica;
    private javax.swing.JMenuItem jmEnviaEmail;
    private javax.swing.JMenu jmFaturamento;
    private javax.swing.JMenuItem jmFaturamentoCaixa;
    private javax.swing.JMenuItem jmGerencia_User;
    private javax.swing.JMenuItem jmManual;
    private javax.swing.JMenu jmMenuAjuda;
    private javax.swing.JMenuItem jmMetas;
    private javax.swing.JMenuItem jmNotasSistema;
    private javax.swing.JMenuItem jmPendenciaCTF;
    private javax.swing.JMenuItem jmPlanoDeVoo;
    private javax.swing.JMenuItem jmPopular;
    private javax.swing.JMenuItem jmRegistros;
    private javax.swing.JMenuItem jmRelatorioClientes;
    private javax.swing.JMenuItem jmRelatorioDeTrocasEDevolucoes;
    private javax.swing.JMenuItem jmRelatorioLoja;
    private javax.swing.JMenuItem jmRelatorioProdutos;
    private javax.swing.JMenuItem jmRelatorioUsuarios;
    private javax.swing.JMenuItem jmSobre;
    private javax.swing.JMenu lmLojasCadastradas;
    // End of variables declaration//GEN-END:variables
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
}
