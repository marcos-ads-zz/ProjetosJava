package modulo.view.principal;

import modulo.metodos.JifCarregamento;
import modulo.login.JfLoginUi;
import java.awt.Image;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import modulo.configuracoes.JifConfig;
import modulo.faceamento.JifPainelFaltas;
import modulo.versao.JifSobre;
import modulo.usuarios.UsuarioDAO;
import java.text.DateFormat;
import java.util.Date;
import modulo.jasper.JasperDAO;
import modulo.loja.JifLoja;
import modulo.produtos.JifProdutos;
import javax.swing.JOptionPane;
import modulo.desenvolvimento.frames.JifAniversariantes;
import modulo.campanhas.vendaD.JifAcompanhamentoCampanhas;
import modulo.planodevoo.JifCampanhasCaixa;
import modulo.emailD.JifEmail;
import modulo.avariasD.JifAvariasKibom;
import modulo.avariasD.JifAvariasYork;
import modulo.vasilhames.JifControleVasilhames;
import modulo.indisponiveis.JifControleIndisponiveis;
import modulo.clientes.JifClientes;
import modulo.ctf.JifCTF;
import modulo.contas.JifControleDeContas;
import modulo.inventario.JifInventario;
import modulo.faturamento.JifFaturamento;
import modulo.popular.JifPopular;
import modulo.versao.JifNotasDoSistema;
import modulo.desenvolvimento.frames.JifBackup;
import modulo.desenvolvimento.frames.JifCancelamentos;
import modulo.usuarios.JifCargos;
import modulo.desenvolvimento.frames.JifCreditoDigital;
import modulo.metas.JifMetas;
import modulo.planodevoo.JifPlanodeVooBalcao;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import modulo.login.UserLogado;
import modulo.faceamento.JifFaltas;
import modulo.troca.JifTrocas;
import modulo.usuarios.JifUsuarios;
import modulo.faceamento.JifRelatorioFaltas;
import modulo.versao.Versao;
import java.text.SimpleDateFormat;
import java.util.List;
import modulo.ajuda.JifAjuda;
import modulo.almoxarifado.JifAlmoxarifado;
import modulo.atualizacao.JifAjusteParaAtualizacao;
import modulo.campanhas.meta.JifCadastroMetasCampanhas;
import modulo.campanhas.cadastro.JifCadastroDescricaoCampanhas;
import modulo.relatorio.JifGeraRelatorios;
import modulo.dashboardD.Dashboard;
import modulo.metodos.Funcao;
import modulo.seguranca.JifSeguranca;
import modulo.usuarios.CargosDAO;
import modulo.usuarios.Cargos;
import modulo.usuarios.Usuario;

/**
 *
 * @author Marcos Junior
 */
public final class JfPrincipal extends javax.swing.JFrame {

    private Versao ver = new Versao();
    private UserLogado login = new UserLogado();
    private DateFormat formatoHora;

    private JifCarregamento jfC = null;

    private UsuarioDAO DAOUser;
    private Usuario userAut;
    private Dashboard dash = null;
    private Funcao fun;
    private CargosDAO DAOCargo;

    private String Aviso = "Atenção";
    private String funcao = null;
    private int Nivel = 0;
    private boolean g = false;
    private boolean ret = true;

    private JifAjuda Ajud = null;
    private JifUsuarios jfUser = null;
    private JifFaltas jfFal = null;
    private JifLoja jfLj = null;
    private JifProdutos jfPro = null;
    private JifRelatorioFaltas jfRel = null;
    private JifPainelFaltas jfPain = null;
    private JifTrocas jfTroc = null;
    private JifSobre jfSob = null;
    private JifConfig jfConf = null;
    private JifClientes jfCli = null;
    private JifCTF jfCtf = null;
    private JifPopular jfPop = null;
    private JifAvariasKibom jfAvKibom = null;
    private JifAvariasYork jfAvYork = null;
    private JifControleVasilhames jfControlVasi = null;
    private JifControleIndisponiveis jfControlIndisponiveis = null;
    private JifNotasDoSistema jfNotas = null;
    private JifPlanodeVooBalcao jfPlaVoo = null;
    private JifInventario jfInv = null;
    private JifControleDeContas jfContas = null;
    private JifBackup jfBack = null;
    private JifAcompanhamentoCampanhas jfPower = null;
    private JifCampanhasCaixa jfCampC = null;
    private JifEmail jfEmail = null;
    private JifCreditoDigital jfCrediDig = null;
    private JifAniversariantes jfAniver = null;
    private JifCancelamentos jfCancela = null;
    private JifFaturamento jfFatCaixa = null;
    private JifCargos jfCargo = null;
    private JifMetas jfMetas = null;
    private JifAjusteParaAtualizacao Ajustes = null;
    private JifSeguranca jfSeguranca = null;
    private JifCadastroDescricaoCampanhas jfCampanhasCadProd = null;
    private JifCadastroMetasCampanhas jfCampanhasMetas = null;
    private JifAcompanhamentoCampanhas jfCampanhasVendaDia = null;
    private JifGeraRelatorios jfCampanhaRelatorio = null;
    private JifAlmoxarifado jfAlmox = null;

    public JfPrincipal() {
        initComponents();
        DAOCargo = new CargosDAO();
        DAOUser = new UsuarioDAO();
        iniciaDados();
        fun = new Funcao();
        setTitle(ver.getNomesys() + " " + ver.getVersao());
        this.formatoHora = new SimpleDateFormat("EEEEEEEEEEEEEE, dd/MM/yyyy    HH:mm:ss");
        Thread TabThred = new Thread(new JfPrincipal.clsDataHora());
        TabThred.start();
    }

    public void iniciaDados() {
        List<Cargos> cargo;
        int matricula = Integer.parseInt(login.getMatricula());
        try {
            userAut = DAOUser.PesquisaPorMatricula(matricula);
            funcao = userAut.getFuncao();
            cargo = DAOCargo.PesquisaNome(funcao);
            cargo.forEach((result) -> {
                Nivel = result.getNivel();
            });
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro! " + ex.getMessage());
        }
        verificaAutorizacao(Nivel);
    }

    public void verificaAutorizacao(int valorNivel) {
        //Nivel 1 A 9
        if (valorNivel <= 9) {
            //System.out.println("2 Valor de G é: " + g);
            jmpAjuda.setEnabled(true);//TODOS
            jmpAtalhos.setEnabled(true);//TODOS
            jmpControleLoja.setEnabled(true);//TODOS
            jmpDashboard.setEnabled(true);//TODOS
            jmpDesemvolvimento.setEnabled(true);//TODOS
            jmpEmail.setEnabled(true);//TODOS
            jmpFaturamento.setEnabled(true);//TODOS
            jmpGerencia.setEnabled(true);//TODOS
            jmpHome.setEnabled(true);//TODOS
            jmpReforco.setEnabled(true);//TODOS
            jmpRelatorios.setEnabled(true);//TODOS

            jmNotasSistema.setEnabled(true);//TODOS
            jmLogof.setEnabled(true);//TODOS
            jmClientes.setEnabled(true);//TODOS
            jmAjuda.setEnabled(true);//TODOS
            jmAdicionarNotaSistema.setEnabled(true);//TODOS
            jmpRelatoriosGerencial.setEnabled(true);//TODOS
            jmRegistrarFaltas.setEnabled(true);//TODOS
            jmSobre.setEnabled(true);//TODOS
            jmDashboardMenu.setEnabled(true);//TODOS

        } else {
            g = false;
        }
        if (valorNivel >= 8) {
            g = true;
        }
        if (valorNivel >= 8 || g == false) {
            //Gerente, Assistente.
            jmRelatorioDeTrocasEDevolucoes.setEnabled(g);//9 E 8
            jmRelatorioLoja.setEnabled(g);//9 E 8
            jmRelatorioProdutos.setEnabled(g);//9 E 8 
            jmRelatorioUsuarios.setEnabled(g);//9 E 8
            jmConfiguracoes.setEnabled(g);//9 E 8
            jmCargos.setEnabled(g);//9 E 8
            jmRelatorioClientes.setEnabled(g);//9 E 8
            jmUsuarios.setEnabled(g);//9 E 8
            jmMetas.setEnabled(g);//9 E 8
            jmLoja.setEnabled(g);//9 E 8
            jmInventario.setEnabled(g);//9 E 8
            jmLiberarTerminal.setEnabled(g);//9 E 8
            jmRegistrodeVendasCampanhas.setEnabled(g);//9 E 8
            jmCampanhasCadProdutos.setEnabled(g);//9 E 8
            jmCampanhasMetasProd.setEnabled(g);//9 E 8
        }
        if (valorNivel >= 7) {
            g = true;
        }
        if (valorNivel >= 7 || g == false) {
            //Gerente, Assistente, Farmaceutico.
            jmIndisponiveis.setEnabled(g);//9 A 7
            jmBackup.setEnabled(g);//9 A 7
            jmIndisponiveis.setEnabled(g);//9 A 7
            jmAguaELuz.setEnabled(g);// 9 A 7
            jmAvariasKibom.setEnabled(g);//9 A 7
            jmAvariasYork.setEnabled(g);//9 A 7
            jmVasilhames.setEnabled(g);//9 A 7
            jmCampanhasCadProdutos.setEnabled(g);//9 A 7
            jmCampanhasMetasProd.setEnabled(g);//9 A 7
            jmRegistrodeVendasCampanhas.setEnabled(g);//9 A 7
        }
        if (valorNivel >= 6) {
            g = true;
        }
        if (valorNivel >= 6 || g == false) {
            //Gerente, Assistente, Farmaceutico, Aux Farmacia.
            jmEnviaEmail.setEnabled(g);//9 A 6
            jmProdutos.setEnabled(g);//9 A 6
            jmRegistrarTrocas.setEnabled(g);//9 A 6
            jmRegistrosReforcoGeral.setEnabled(g);//9 A 6
            jmRelatorioDeFaltas.setEnabled(g);//9 A 6

        }
        if (valorNivel >= 4) {
            g = true;
        }
        if (valorNivel >= 4 || g == false) {
            jmCancelamentos.setEnabled(g);//9 A 4 
            jmFaturamentoCaixa.setEnabled(g);//9 A 4
            jmPendenciaCTF.setEnabled(g);//9 A 4
            jmPlanoDeVoo.setEnabled(g);//9 A 4
            jmPopular.setEnabled(g);//9 A 4
        }
        if (valorNivel < 4) {
            jmDashboardMenu.setEnabled(false);//TODOS
        }

    }

    class clsDataHora implements Runnable {

        double cont = 0;

        @Override
        public void run() {
            while (true) {
                jlInformacao.setText("BEM VINDO!  " + login.getNomelogin());
                jlHoraAtual.setText(formatoHora.format(new Date()) + " ");
                cont++;
                if (cont == 2) {
                    VerificaMundanca();
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println("Thread não foi finalizada:" + ex);
                }
            }
        }
    }

    public void VerificaMundanca() {

        try {
            List<Cargos> c = DAOCargo.Pesquisa();
            c.forEach((result) -> {
                if (result.getNivel() >= 9) {
                    ret = false;
                }
            });

        } catch (Exception ex) {
            Logger.getLogger(JfLoginUi.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {

            if (Ajustes == null) {
                Ajustes = new JifAjusteParaAtualizacao();
                jDesktopPrincipal.add(Ajustes);
                Ajustes.setVisible(true);
                Ajustes.setPosicao();
            } else if (!Ajustes.isVisible()) {
                Ajustes = new JifAjusteParaAtualizacao();
                jDesktopPrincipal.add(Ajustes);
                Ajustes.setVisible(true);
                Ajustes.setPosicao();
            } else {
                JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
            }
        }

    }

    public void LogofDoSistema() {
        this.dispose();
        Logoff();
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
        jmpHome = new javax.swing.JMenu();
        jmConfiguracoes = new javax.swing.JMenuItem();
        jmLiberarTerminal = new javax.swing.JMenuItem();
        jmBackup = new javax.swing.JMenuItem();
        jmLogof = new javax.swing.JMenuItem();
        jmpGerencia = new javax.swing.JMenu();
        jmLoja = new javax.swing.JMenuItem();
        jmCargos = new javax.swing.JMenuItem();
        jmClientes = new javax.swing.JMenuItem();
        jmUsuarios = new javax.swing.JMenuItem();
        jmProdutos = new javax.swing.JMenuItem();
        jmpRelatoriosGerencial = new javax.swing.JMenu();
        jmRelatorioLoja = new javax.swing.JMenuItem();
        jmRelatorioUsuarios = new javax.swing.JMenuItem();
        jmRelatorioProdutos = new javax.swing.JMenuItem();
        jmRelatorioClientes = new javax.swing.JMenuItem();
        jmpControleLoja = new javax.swing.JMenu();
        jmAguaELuz = new javax.swing.JMenuItem();
        jmInventario = new javax.swing.JMenuItem();
        jmVasilhames = new javax.swing.JMenuItem();
        jmAlmoxarifado = new javax.swing.JMenuItem();
        jmIndisponiveis = new javax.swing.JMenuItem();
        jmAvariasKibom = new javax.swing.JMenuItem();
        jmAvariasYork = new javax.swing.JMenuItem();
        jmRegistrarTrocas = new javax.swing.JMenuItem();
        jmpReforco = new javax.swing.JMenu();
        jmRegistrarFaltas = new javax.swing.JMenuItem();
        jmRelatorioDeFaltas = new javax.swing.JMenuItem();
        jmRegistrosReforcoGeral = new javax.swing.JMenuItem();
        jmCampanhas = new javax.swing.JMenu();
        jmCampanhasCadProdutos = new javax.swing.JMenuItem();
        jmCampanhasMetasProd = new javax.swing.JMenuItem();
        jmRegistrodeVendasCampanhas = new javax.swing.JMenuItem();
        jmRelatoriosDeCampanhas = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jmPlanoDeVoo = new javax.swing.JMenuItem();
        jmpFaturamento = new javax.swing.JMenu();
        jmFaturamentoCaixa = new javax.swing.JMenuItem();
        jmMetas = new javax.swing.JMenuItem();
        jmCancelamentos = new javax.swing.JMenuItem();
        jmPendenciaCTF = new javax.swing.JMenuItem();
        jmPopular = new javax.swing.JMenuItem();
        jmpEmail = new javax.swing.JMenu();
        jmEnviaEmail = new javax.swing.JMenuItem();
        jmpRelatorios = new javax.swing.JMenu();
        jmRelatorioDeTrocasEDevolucoes = new javax.swing.JMenuItem();
        jmpDashboard = new javax.swing.JMenu();
        jmDashboardMenu = new javax.swing.JMenuItem();
        jmpDesemvolvimento = new javax.swing.JMenu();
        jmAdicionarNotaSistema = new javax.swing.JMenuItem();
        jmpAtalhos = new javax.swing.JMenu();
        jmpAjuda = new javax.swing.JMenu();
        jmNotasSistema = new javax.swing.JMenuItem();
        jmSobre = new javax.swing.JMenuItem();
        jmAjuda = new javax.swing.JMenuItem();

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

        jmpHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home.png"))); // NOI18N
        jmpHome.setText("Home");

        jmConfiguracoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/configuracao.png"))); // NOI18N
        jmConfiguracoes.setText("Configurações");
        jmConfiguracoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmConfiguracoesActionPerformed(evt);
            }
        });
        jmpHome.add(jmConfiguracoes);

        jmLiberarTerminal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/connect.png"))); // NOI18N
        jmLiberarTerminal.setText("Liberar Terminal");
        jmLiberarTerminal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmLiberarTerminalActionPerformed(evt);
            }
        });
        jmpHome.add(jmLiberarTerminal);

        jmBackup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/drive_disk.png"))); // NOI18N
        jmBackup.setText("Backup");
        jmBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmBackupActionPerformed(evt);
            }
        });
        jmpHome.add(jmBackup);

        jmLogof.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jmLogof.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logof2.png"))); // NOI18N
        jmLogof.setText("Logof");
        jmLogof.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmLogofActionPerformed(evt);
            }
        });
        jmpHome.add(jmLogof);

        jMenuBar1.add(jmpHome);

        jmpGerencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user_suit.png"))); // NOI18N
        jmpGerencia.setText("Gerência");

        jmLoja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/layers.png"))); // NOI18N
        jmLoja.setText("Loja");
        jmLoja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmLojaActionPerformed(evt);
            }
        });
        jmpGerencia.add(jmLoja);

        jmCargos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/status_online.png"))); // NOI18N
        jmCargos.setText("Cargos");
        jmCargos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmCargosActionPerformed(evt);
            }
        });
        jmpGerencia.add(jmCargos);

        jmClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user.png"))); // NOI18N
        jmClientes.setText("Clientes");
        jmClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmClientesActionPerformed(evt);
            }
        });
        jmpGerencia.add(jmClientes);

        jmUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/group.png"))); // NOI18N
        jmUsuarios.setText("Usuários");
        jmUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmUsuariosActionPerformed(evt);
            }
        });
        jmpGerencia.add(jmUsuarios);

        jmProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bricks.png"))); // NOI18N
        jmProdutos.setText("Produtos");
        jmProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmProdutosActionPerformed(evt);
            }
        });
        jmpGerencia.add(jmProdutos);

        jmpRelatoriosGerencial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/page.png"))); // NOI18N
        jmpRelatoriosGerencial.setText("Relatório");

        jmRelatorioLoja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/page.png"))); // NOI18N
        jmRelatorioLoja.setText("Relatório de Lojas");
        jmRelatorioLoja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmRelatorioLojaActionPerformed(evt);
            }
        });
        jmpRelatoriosGerencial.add(jmRelatorioLoja);

        jmRelatorioUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/page.png"))); // NOI18N
        jmRelatorioUsuarios.setText("Relatório de Usuários");
        jmRelatorioUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmRelatorioUsuariosActionPerformed(evt);
            }
        });
        jmpRelatoriosGerencial.add(jmRelatorioUsuarios);

        jmRelatorioProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/page.png"))); // NOI18N
        jmRelatorioProdutos.setText("Relatório de Produtos");
        jmRelatorioProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmRelatorioProdutosActionPerformed(evt);
            }
        });
        jmpRelatoriosGerencial.add(jmRelatorioProdutos);

        jmRelatorioClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/page.png"))); // NOI18N
        jmRelatorioClientes.setText("Relatorio de Clientes");
        jmpRelatoriosGerencial.add(jmRelatorioClientes);

        jmpGerencia.add(jmpRelatoriosGerencial);

        jMenuBar1.add(jmpGerencia);

        jmpControleLoja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/chart_line_add.png"))); // NOI18N
        jmpControleLoja.setText("Controle Loja");

        jmAguaELuz.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/lightning.png"))); // NOI18N
        jmAguaELuz.setText("Água e Luz");
        jmAguaELuz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmAguaELuzActionPerformed(evt);
            }
        });
        jmpControleLoja.add(jmAguaELuz);

        jmInventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/calculator_edit.png"))); // NOI18N
        jmInventario.setText("Invetário");
        jmInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmInventarioActionPerformed(evt);
            }
        });
        jmpControleLoja.add(jmInventario);

        jmVasilhames.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bricks.png"))); // NOI18N
        jmVasilhames.setText("Vasilhames");
        jmVasilhames.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmVasilhamesActionPerformed(evt);
            }
        });
        jmpControleLoja.add(jmVasilhames);

        jmAlmoxarifado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pill_go.png"))); // NOI18N
        jmAlmoxarifado.setText("Almoxarifado");
        jmAlmoxarifado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmAlmoxarifadoActionPerformed(evt);
            }
        });
        jmpControleLoja.add(jmAlmoxarifado);

        jmIndisponiveis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/arrow_refresh.png"))); // NOI18N
        jmIndisponiveis.setText("Indisponíveis");
        jmIndisponiveis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmIndisponiveisActionPerformed(evt);
            }
        });
        jmpControleLoja.add(jmIndisponiveis);

        jmAvariasKibom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/arrow_refresh.png"))); // NOI18N
        jmAvariasKibom.setText("Avarias Kibom");
        jmAvariasKibom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmAvariasKibomActionPerformed(evt);
            }
        });
        jmpControleLoja.add(jmAvariasKibom);

        jmAvariasYork.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/arrow_refresh.png"))); // NOI18N
        jmAvariasYork.setText("Avarias York");
        jmAvariasYork.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmAvariasYorkActionPerformed(evt);
            }
        });
        jmpControleLoja.add(jmAvariasYork);

        jmRegistrarTrocas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/arrow_refresh.png"))); // NOI18N
        jmRegistrarTrocas.setText("Troca e Devolução");
        jmRegistrarTrocas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmRegistrarTrocasActionPerformed(evt);
            }
        });
        jmpControleLoja.add(jmRegistrarTrocas);

        jMenuBar1.add(jmpControleLoja);

        jmpReforco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/shield_add.png"))); // NOI18N
        jmpReforco.setText("Reforço");

        jmRegistrarFaltas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/wand.png"))); // NOI18N
        jmRegistrarFaltas.setText("Registrar Falta");
        jmRegistrarFaltas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmRegistrarFaltasActionPerformed(evt);
            }
        });
        jmpReforco.add(jmRegistrarFaltas);

        jmRelatorioDeFaltas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/layout.png"))); // NOI18N
        jmRelatorioDeFaltas.setText("Relatório de Faltas");
        jmRelatorioDeFaltas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmRelatorioDeFaltasActionPerformed(evt);
            }
        });
        jmpReforco.add(jmRelatorioDeFaltas);

        jmRegistrosReforcoGeral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/page.png"))); // NOI18N
        jmRegistrosReforcoGeral.setText("Registros Reforço Geral");
        jmRegistrosReforcoGeral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmRegistrosReforcoGeralActionPerformed(evt);
            }
        });
        jmpReforco.add(jmRegistrosReforcoGeral);

        jMenuBar1.add(jmpReforco);

        jmCampanhas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/editar.png"))); // NOI18N
        jmCampanhas.setText("Campanhas");

        jmCampanhasCadProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/brick_go.png"))); // NOI18N
        jmCampanhasCadProdutos.setText("Cadastrar Produto de Campanha");
        jmCampanhasCadProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmCampanhasCadProdutosActionPerformed(evt);
            }
        });
        jmCampanhas.add(jmCampanhasCadProdutos);

        jmCampanhasMetasProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/brick_go.png"))); // NOI18N
        jmCampanhasMetasProd.setText("Cadastar Meta dos Produtos");
        jmCampanhasMetasProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmCampanhasMetasProdActionPerformed(evt);
            }
        });
        jmCampanhas.add(jmCampanhasMetasProd);

        jmRegistrodeVendasCampanhas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/chart_line_edit.png"))); // NOI18N
        jmRegistrodeVendasCampanhas.setText("Registros de Vendas");
        jmRegistrodeVendasCampanhas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmRegistrodeVendasCampanhasActionPerformed(evt);
            }
        });
        jmCampanhas.add(jmRegistrodeVendasCampanhas);

        jmRelatoriosDeCampanhas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/page.png"))); // NOI18N
        jmRelatoriosDeCampanhas.setText("Relatórios de Campanhas");
        jmRelatoriosDeCampanhas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmRelatoriosDeCampanhasActionPerformed(evt);
            }
        });
        jmCampanhas.add(jmRelatoriosDeCampanhas);

        jMenuBar1.add(jmCampanhas);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/money.png"))); // NOI18N
        jMenu1.setText("Plano de Voo");

        jmPlanoDeVoo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/money_add.png"))); // NOI18N
        jmPlanoDeVoo.setText("Plano de Voo");
        jmPlanoDeVoo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmPlanoDeVooActionPerformed(evt);
            }
        });
        jMenu1.add(jmPlanoDeVoo);

        jMenuBar1.add(jMenu1);

        jmpFaturamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/money.png"))); // NOI18N
        jmpFaturamento.setText("Faturamento");

        jmFaturamentoCaixa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/money_add.png"))); // NOI18N
        jmFaturamentoCaixa.setText("Faturamento");
        jmFaturamentoCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmFaturamentoCaixaActionPerformed(evt);
            }
        });
        jmpFaturamento.add(jmFaturamentoCaixa);

        jmMetas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/chart_line_edit.png"))); // NOI18N
        jmMetas.setText("Metas");
        jmMetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmMetasActionPerformed(evt);
            }
        });
        jmpFaturamento.add(jmMetas);

        jmCancelamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/money_delete.png"))); // NOI18N
        jmCancelamentos.setText("Cancelamentos");
        jmCancelamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmCancelamentosActionPerformed(evt);
            }
        });
        jmpFaturamento.add(jmCancelamentos);

        jmPendenciaCTF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/creditcards.png"))); // NOI18N
        jmPendenciaCTF.setText("Pendência CTF");
        jmPendenciaCTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmPendenciaCTFActionPerformed(evt);
            }
        });
        jmpFaturamento.add(jmPendenciaCTF);

        jmPopular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/rosette.png"))); // NOI18N
        jmPopular.setText("Popular");
        jmPopular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmPopularActionPerformed(evt);
            }
        });
        jmpFaturamento.add(jmPopular);

        jMenuBar1.add(jmpFaturamento);

        jmpEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pill_go.png"))); // NOI18N
        jmpEmail.setText("E-mail");
        jmpEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmpEmailActionPerformed(evt);
            }
        });

        jmEnviaEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/tick.png"))); // NOI18N
        jmEnviaEmail.setText("Enviar E-mail");
        jmEnviaEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmEnviaEmailActionPerformed(evt);
            }
        });
        jmpEmail.add(jmEnviaEmail);

        jMenuBar1.add(jmpEmail);

        jmpRelatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/page.png"))); // NOI18N
        jmpRelatorios.setText("Relatórios");

        jmRelatorioDeTrocasEDevolucoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/page.png"))); // NOI18N
        jmRelatorioDeTrocasEDevolucoes.setText("Relatório de Trocas e Devoluções");
        jmpRelatorios.add(jmRelatorioDeTrocasEDevolucoes);

        jMenuBar1.add(jmpRelatorios);

        jmpDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/layout_content.png"))); // NOI18N
        jmpDashboard.setText("Dashboard");

        jmDashboardMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/layout_content.png"))); // NOI18N
        jmDashboardMenu.setText("Dashboard");
        jmDashboardMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmDashboardMenuActionPerformed(evt);
            }
        });
        jmpDashboard.add(jmDashboardMenu);

        jMenuBar1.add(jmpDashboard);

        jmpDesemvolvimento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/flag_green.png"))); // NOI18N
        jmpDesemvolvimento.setText("Desenvolvimento");

        jmAdicionarNotaSistema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/comment_add.png"))); // NOI18N
        jmAdicionarNotaSistema.setText("Adicionar Notas");
        jmAdicionarNotaSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmAdicionarNotaSistemaActionPerformed(evt);
            }
        });
        jmpDesemvolvimento.add(jmAdicionarNotaSistema);

        jMenuBar1.add(jmpDesemvolvimento);

        jmpAtalhos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/page_find.png"))); // NOI18N
        jmpAtalhos.setText("Atalhos");
        jMenuBar1.add(jmpAtalhos);

        jmpAjuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ajuda.png"))); // NOI18N
        jmpAjuda.setText("Ajuda");

        jmNotasSistema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/comment.png"))); // NOI18N
        jmNotasSistema.setText("Notas do Sistema");
        jmNotasSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmNotasSistemaActionPerformed(evt);
            }
        });
        jmpAjuda.add(jmNotasSistema);

        jmSobre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/information.png"))); // NOI18N
        jmSobre.setText("Sobre");
        jmSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmSobreActionPerformed(evt);
            }
        });
        jmpAjuda.add(jmSobre);

        jmAjuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/layout_content.png"))); // NOI18N
        jmAjuda.setText("Ajuda");
        jmAjuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmAjudaActionPerformed(evt);
            }
        });
        jmpAjuda.add(jmAjuda);

        jMenuBar1.add(jmpAjuda);

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

    private void jmUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmUsuariosActionPerformed
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
    }//GEN-LAST:event_jmUsuariosActionPerformed

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

    private void jmRegistrarFaltasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmRegistrarFaltasActionPerformed
        if (jfFal == null) {
            jfFal = new JifFaltas();
            jDesktopPrincipal.add(jfFal);
            jfFal.setVisible(true);
            jfFal.setPosicao();
        } else if (!jfFal.isVisible()) {
            jfFal = new JifFaltas();
            jDesktopPrincipal.add(jfFal);
            jfFal.setVisible(true);
            jfFal.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmRegistrarFaltasActionPerformed

    private void jmLojaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmLojaActionPerformed
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
    }//GEN-LAST:event_jmLojaActionPerformed

    private void jmProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmProdutosActionPerformed
        if (jfPro == null) {
            jfPro = new JifProdutos();
            jDesktopPrincipal.add(jfPro);
            jfPro.setVisible(true);
            jfPro.setPosicao();
        } else if (!jfPro.isVisible()) {
            jfPro = new JifProdutos();
            jDesktopPrincipal.add(jfPro);
            jfPro.setVisible(true);
            jfPro.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmProdutosActionPerformed

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

    private void jmRelatorioDeFaltasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmRelatorioDeFaltasActionPerformed
        if (jfRel == null) {
            jfRel = new JifRelatorioFaltas();
            jDesktopPrincipal.add(jfRel);
            try {
                jfRel.setMaximum(true);

            } catch (PropertyVetoException ex) {
                Logger.getLogger(JfPrincipal.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            jfRel.setVisible(true);
            jfRel.setPosicao();
        } else if (!jfRel.isVisible()) {
            jfRel = new JifRelatorioFaltas();
            jDesktopPrincipal.add(jfRel);
            try {
                jfRel.setMaximum(true);

            } catch (PropertyVetoException ex) {
                Logger.getLogger(JfPrincipal.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            jfRel.setVisible(true);
            jfRel.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmRelatorioDeFaltasActionPerformed

    private void jmRegistrosReforcoGeralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmRegistrosReforcoGeralActionPerformed
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
    }//GEN-LAST:event_jmRegistrosReforcoGeralActionPerformed

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

    private void jmRegistrarTrocasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmRegistrarTrocasActionPerformed
        if (jfTroc == null) {
            jfTroc = new JifTrocas();
            jDesktopPrincipal.add(jfTroc);
            try {
                jfTroc.setMaximum(true);

            } catch (PropertyVetoException ex) {
                Logger.getLogger(JfPrincipal.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            jfTroc.setVisible(true);
            jfTroc.setPosicao();
        } else if (!jfTroc.isVisible()) {
            jfTroc = new JifTrocas();
            jDesktopPrincipal.add(jfTroc);
            try {
                jfTroc.setMaximum(true);

            } catch (PropertyVetoException ex) {
                Logger.getLogger(JfPrincipal.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            jfTroc.setVisible(true);
            jfTroc.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmRegistrarTrocasActionPerformed

    private void jmLogofActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmLogofActionPerformed
        LogofDoSistema();
    }//GEN-LAST:event_jmLogofActionPerformed

    private void jmConfiguracoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmConfiguracoesActionPerformed
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
    }//GEN-LAST:event_jmConfiguracoesActionPerformed

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

    private void jmAvariasKibomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmAvariasKibomActionPerformed
        if (jfAvKibom == null) {
            jfAvKibom = new JifAvariasKibom();
            jDesktopPrincipal.add(jfAvKibom);
            jfAvKibom.setVisible(true);
            jfAvKibom.setPosicao();
        } else if (!jfAvKibom.isVisible()) {
            jfAvKibom = new JifAvariasKibom();
            jDesktopPrincipal.add(jfAvKibom);
            jfAvKibom.setVisible(true);
            jfAvKibom.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmAvariasKibomActionPerformed

    private void jmAvariasYorkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmAvariasYorkActionPerformed
        if (jfAvYork == null) {
            jfAvYork = new JifAvariasYork();
            jDesktopPrincipal.add(jfAvYork);
            jfAvYork.setVisible(true);
            jfAvYork.setPosicao();
        } else if (!jfAvYork.isVisible()) {
            jfAvYork = new JifAvariasYork();
            jDesktopPrincipal.add(jfAvYork);
            jfAvYork.setVisible(true);
            jfAvYork.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmAvariasYorkActionPerformed

    private void jmVasilhamesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmVasilhamesActionPerformed
        if (jfControlVasi == null) {
            jfControlVasi = new JifControleVasilhames();
            jDesktopPrincipal.add(jfControlVasi);
            jfControlVasi.setVisible(true);
            jfControlVasi.setPosicao();
        } else if (!jfControlVasi.isVisible()) {
            jfControlVasi = new JifControleVasilhames();
            jDesktopPrincipal.add(jfControlVasi);
            jfControlVasi.setVisible(true);
            jfControlVasi.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmVasilhamesActionPerformed

    private void jmIndisponiveisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmIndisponiveisActionPerformed
        if (jfControlIndisponiveis == null) {
            jfControlIndisponiveis = new JifControleIndisponiveis();
            jDesktopPrincipal.add(jfControlIndisponiveis);
            jfControlIndisponiveis.setVisible(true);
            jfControlIndisponiveis.setPosicao();
        } else if (!jfControlIndisponiveis.isVisible()) {
            jfControlIndisponiveis = new JifControleIndisponiveis();
            jDesktopPrincipal.add(jfControlIndisponiveis);
            jfControlIndisponiveis.setVisible(true);
            jfControlIndisponiveis.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmIndisponiveisActionPerformed

    private void jmpEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmpEmailActionPerformed
        if (jfControlIndisponiveis == null) {
            jfControlIndisponiveis = new JifControleIndisponiveis();
            jDesktopPrincipal.add(jfControlIndisponiveis);
            jfControlIndisponiveis.setVisible(true);
            jfControlIndisponiveis.setPosicao();
        } else if (!jfControlIndisponiveis.isVisible()) {
            jfControlIndisponiveis = new JifControleIndisponiveis();
            jDesktopPrincipal.add(jfControlIndisponiveis);
            jfControlIndisponiveis.setVisible(true);
            jfControlIndisponiveis.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmpEmailActionPerformed

    private void jmPlanoDeVooActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmPlanoDeVooActionPerformed
        if (jfPlaVoo == null) {
            jfPlaVoo = new JifPlanodeVooBalcao();
            jDesktopPrincipal.add(jfPlaVoo);
            jfPlaVoo.setVisible(true);
            jfPlaVoo.setPosicao();
        } else if (!jfPlaVoo.isVisible()) {
            jfPlaVoo = new JifPlanodeVooBalcao();
            jDesktopPrincipal.add(jfPlaVoo);
            jfPlaVoo.setVisible(true);
            jfPlaVoo.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmPlanoDeVooActionPerformed

    private void jmPendenciaCTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmPendenciaCTFActionPerformed
        if (jfCtf == null) {
            jfCtf = new JifCTF();
            jDesktopPrincipal.add(jfCtf);
            jfCtf.setVisible(true);
            jfCtf.setPosicao();
        } else if (!jfCtf.isVisible()) {
            jfCtf = new JifCTF();
            jDesktopPrincipal.add(jfCtf);
            jfCtf.setVisible(true);
            jfCtf.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmPendenciaCTFActionPerformed

    private void jmPopularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmPopularActionPerformed
        if (jfPop == null) {
            jfPop = new JifPopular();
            jDesktopPrincipal.add(jfPop);
            jfPop.setVisible(true);
            jfPop.setPosicao();
        } else if (!jfPop.isVisible()) {
            jfPop = new JifPopular();
            jDesktopPrincipal.add(jfPop);
            jfPop.setVisible(true);
            jfPop.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmPopularActionPerformed

    private void jmAguaELuzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmAguaELuzActionPerformed
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
    }//GEN-LAST:event_jmAguaELuzActionPerformed

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

    private void jmInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmInventarioActionPerformed
        if (jfInv == null) {
            jfInv = new JifInventario();
            jDesktopPrincipal.add(jfInv);
            try {
                jfInv.setMaximum(true);

            } catch (PropertyVetoException ex) {
                Logger.getLogger(JfPrincipal.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            jfInv.setVisible(true);
            jfInv.setPosicao();
        } else if (!jfInv.isVisible()) {
            jfInv = new JifInventario();
            jDesktopPrincipal.add(jfInv);
            try {
                jfInv.setMaximum(true);

            } catch (PropertyVetoException ex) {
                Logger.getLogger(JfPrincipal.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            jfInv.setVisible(true);
            jfInv.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmInventarioActionPerformed

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
        if (jfMetas == null) {
            jfMetas = new JifMetas();
            jDesktopPrincipal.add(jfMetas);
            jfMetas.setVisible(true);
            jfMetas.setPosicao();
        } else if (!jfMetas.isVisible()) {
            jfMetas = new JifMetas();
            jDesktopPrincipal.add(jfMetas);
            jfMetas.setVisible(true);
            jfMetas.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmMetasActionPerformed

    private void jmAjudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmAjudaActionPerformed
        if (Ajud == null) {
            Ajud = new JifAjuda();
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
            Ajud = new JifAjuda();
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
    }//GEN-LAST:event_jmAjudaActionPerformed

    private void jmDashboardMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmDashboardMenuActionPerformed
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
    }//GEN-LAST:event_jmDashboardMenuActionPerformed

    private void jmLiberarTerminalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmLiberarTerminalActionPerformed
        if (jfSeguranca == null) {
            jfSeguranca = new JifSeguranca();
            jDesktopPrincipal.add(jfSeguranca);
            jfSeguranca.setVisible(true);
            jfSeguranca.setPosicao();
        } else if (!jfSeguranca.isVisible()) {
            jfSeguranca = new JifSeguranca();
            jDesktopPrincipal.add(jfSeguranca);
            jfSeguranca.setVisible(true);
            jfSeguranca.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmLiberarTerminalActionPerformed

    private void jmCampanhasCadProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmCampanhasCadProdutosActionPerformed
        if (jfCampanhasCadProd == null) {
            jfCampanhasCadProd = new JifCadastroDescricaoCampanhas();
            jDesktopPrincipal.add(jfCampanhasCadProd);
            jfCampanhasCadProd.setVisible(true);
            jfCampanhasCadProd.setPosicao();
        } else if (!jfCampanhasCadProd.isVisible()) {
            jfCampanhasCadProd = new JifCadastroDescricaoCampanhas();
            jDesktopPrincipal.add(jfCampanhasCadProd);
            jfCampanhasCadProd.setVisible(true);
            jfCampanhasCadProd.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmCampanhasCadProdutosActionPerformed

    private void jmCampanhasMetasProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmCampanhasMetasProdActionPerformed
        if (jfCampanhasMetas == null) {
            jfCampanhasMetas = new JifCadastroMetasCampanhas();
            jDesktopPrincipal.add(jfCampanhasMetas);
            jfCampanhasMetas.setVisible(true);
            jfCampanhasMetas.setPosicao();
        } else if (!jfCampanhasMetas.isVisible()) {
            jfCampanhasMetas = new JifCadastroMetasCampanhas();
            jDesktopPrincipal.add(jfCampanhasMetas);
            jfCampanhasMetas.setVisible(true);
            jfCampanhasMetas.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmCampanhasMetasProdActionPerformed

    private void jmRegistrodeVendasCampanhasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmRegistrodeVendasCampanhasActionPerformed
        if (jfCampanhasVendaDia == null) {
            jfCampanhasVendaDia = new JifAcompanhamentoCampanhas();
            jDesktopPrincipal.add(jfCampanhasVendaDia);
            jfCampanhasVendaDia.setVisible(true);
            jfCampanhasVendaDia.setPosicao();
        } else if (!jfCampanhasVendaDia.isVisible()) {
            jfCampanhasVendaDia = new JifAcompanhamentoCampanhas();
            jDesktopPrincipal.add(jfCampanhasVendaDia);
            jfCampanhasVendaDia.setVisible(true);
            jfCampanhasVendaDia.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmRegistrodeVendasCampanhasActionPerformed

    private void jmRelatoriosDeCampanhasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmRelatoriosDeCampanhasActionPerformed
        if (jfCampanhaRelatorio == null) {
            jfCampanhaRelatorio = new JifGeraRelatorios();
            jDesktopPrincipal.add(jfCampanhaRelatorio);
            try {
                jfCampanhaRelatorio.setMaximum(true);

            } catch (PropertyVetoException ex) {
                Logger.getLogger(JfPrincipal.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            jfCampanhaRelatorio.setVisible(true);
            jfCampanhaRelatorio.setPosicao();
        } else if (!jfCampanhaRelatorio.isVisible()) {
            jfCampanhaRelatorio = new JifGeraRelatorios();
            jDesktopPrincipal.add(jfCampanhaRelatorio);
            try {
                jfCampanhaRelatorio.setMaximum(true);

            } catch (PropertyVetoException ex) {
                Logger.getLogger(JfPrincipal.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            jfCampanhaRelatorio.setVisible(true);
            jfCampanhaRelatorio.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmRelatoriosDeCampanhasActionPerformed

    private void jmAlmoxarifadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmAlmoxarifadoActionPerformed
        if (jfAlmox == null) {
            jfAlmox = new JifAlmoxarifado();
            jDesktopPrincipal.add(jfAlmox);
            jfAlmox.setVisible(true);
            jfAlmox.setPosicao();
        } else if (!jfAlmox.isVisible()) {
            jfAlmox = new JifAlmoxarifado();
            jDesktopPrincipal.add(jfAlmox);
            jfAlmox.setVisible(true);
            jfAlmox.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmAlmoxarifadoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane jDesktopPrincipal;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jlHoraAtual;
    private javax.swing.JLabel jlInformacao;
    private javax.swing.JMenuItem jmAdicionarNotaSistema;
    private javax.swing.JMenuItem jmAguaELuz;
    private javax.swing.JMenuItem jmAjuda;
    private javax.swing.JMenuItem jmAlmoxarifado;
    private javax.swing.JMenuItem jmAvariasKibom;
    private javax.swing.JMenuItem jmAvariasYork;
    private javax.swing.JMenuItem jmBackup;
    private javax.swing.JMenu jmCampanhas;
    private javax.swing.JMenuItem jmCampanhasCadProdutos;
    private javax.swing.JMenuItem jmCampanhasMetasProd;
    private javax.swing.JMenuItem jmCancelamentos;
    private javax.swing.JMenuItem jmCargos;
    private javax.swing.JMenuItem jmClientes;
    private javax.swing.JMenuItem jmConfiguracoes;
    private javax.swing.JMenuItem jmDashboardMenu;
    private javax.swing.JMenuItem jmEnviaEmail;
    private javax.swing.JMenuItem jmFaturamentoCaixa;
    private javax.swing.JMenuItem jmIndisponiveis;
    private javax.swing.JMenuItem jmInventario;
    private javax.swing.JMenuItem jmLiberarTerminal;
    private javax.swing.JMenuItem jmLogof;
    private javax.swing.JMenuItem jmLoja;
    private javax.swing.JMenuItem jmMetas;
    private javax.swing.JMenuItem jmNotasSistema;
    private javax.swing.JMenuItem jmPendenciaCTF;
    private javax.swing.JMenuItem jmPlanoDeVoo;
    private javax.swing.JMenuItem jmPopular;
    private javax.swing.JMenuItem jmProdutos;
    private javax.swing.JMenuItem jmRegistrarFaltas;
    private javax.swing.JMenuItem jmRegistrarTrocas;
    private javax.swing.JMenuItem jmRegistrodeVendasCampanhas;
    private javax.swing.JMenuItem jmRegistrosReforcoGeral;
    private javax.swing.JMenuItem jmRelatorioClientes;
    private javax.swing.JMenuItem jmRelatorioDeFaltas;
    private javax.swing.JMenuItem jmRelatorioDeTrocasEDevolucoes;
    private javax.swing.JMenuItem jmRelatorioLoja;
    private javax.swing.JMenuItem jmRelatorioProdutos;
    private javax.swing.JMenuItem jmRelatorioUsuarios;
    private javax.swing.JMenuItem jmRelatoriosDeCampanhas;
    private javax.swing.JMenuItem jmSobre;
    private javax.swing.JMenuItem jmUsuarios;
    private javax.swing.JMenuItem jmVasilhames;
    private javax.swing.JMenu jmpAjuda;
    private javax.swing.JMenu jmpAtalhos;
    private javax.swing.JMenu jmpControleLoja;
    private javax.swing.JMenu jmpDashboard;
    private javax.swing.JMenu jmpDesemvolvimento;
    private javax.swing.JMenu jmpEmail;
    private javax.swing.JMenu jmpFaturamento;
    private javax.swing.JMenu jmpGerencia;
    private javax.swing.JMenu jmpHome;
    private javax.swing.JMenu jmpReforco;
    private javax.swing.JMenu jmpRelatorios;
    private javax.swing.JMenu jmpRelatoriosGerencial;
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
