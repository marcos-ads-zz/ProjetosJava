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
import javax.swing.JOptionPane;
import modulo.usuarios.JifCargos;
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
import modulo.base.dados.JifBaseDados;
import modulo.dashboard.Dashboard;
import modulo.medias.aritimeticas.JifMediaAritimetica;
import modulo.medias.aritimeticas.JifMediaGeometrica;
import modulo.medias.aritimeticas.JifMediaHarmonica;
import modulo.medias.aritimeticas.JifMediaPonderada;
import modulo.metodos.Funcao;
import modulo.usuarios.Usuario;

/**
 *
 * @author Marcos Junior
 */
public final class JfPrincipal extends javax.swing.JFrame {

    private Versao ver;
    private UserLogado login;
    private JifMediaAritimetica jfMedA = null;
    private JifMediaGeometrica jfMedG = null;
    private JifMediaHarmonica jfMedH = null;
    private JifMediaPonderada jfMedP = null;
    private JifBaseDados jfBase = null;
    private JifCarregamento jfC = null;
    private UsuarioDAO DAOUser;
    private Usuario userAut;
    private JifUsuarios jfUser = null;
    private JifSobre jfSob = null;
    private JifConfig jfConf = null;
    private JifCargos jfCargo = null;
    private Dashboard dash = null;
    private Funcao fun;
    private String funcao = null;
    private DateFormat formatoHora;
    private String assistente = "ASSISTENTE DE GERENTE",
            gerente = "GERENTE",
            farmaceuticos = "FARMACÊUTICO",
            balconista = "AUXILIAR DE FARMÁCIA",
            caixa = "CAIXA";

    public JfPrincipal() {
        initComponents();
        ver = new Versao();
        DAOUser = new UsuarioDAO();
        fun = new Funcao();
        login = new UserLogado();
        setTitle(ver.getNomesys() + " " + ver.getVersao());
        this.formatoHora = new SimpleDateFormat("EEEEEEEEEEEEEE, dd/MM/yyyy    HH:mm:ss");
        Thread TabThred = new Thread(new JfPrincipal.clsDataHora());
        TabThred.start();
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
        if (funcao.equals(assistente)) {
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
        jMenuItem6 = new javax.swing.JMenuItem();
        jmCadastro = new javax.swing.JMenu();
        jmCargos = new javax.swing.JMenuItem();
        jmGerencia_User = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jmEditarAmostras = new javax.swing.JMenuItem();
        jmImportacao = new javax.swing.JMenuItem();
        jmExportalcao = new javax.swing.JMenuItem();
        jmFaturamento = new javax.swing.JMenu();
        jmMediaAritimetica = new javax.swing.JMenuItem();
        jmMediaPonderada = new javax.swing.JMenuItem();
        jmMediaHarmonica = new javax.swing.JMenuItem();
        jmMediaGeometrica = new javax.swing.JMenuItem();
        jmCalc1 = new javax.swing.JMenuItem();
        jmCalc2 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuPainel = new javax.swing.JMenuItem();
        jmMenuAjuda = new javax.swing.JMenu();
        jmSobre = new javax.swing.JMenuItem();

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
        jmArquivo.setText("Home");

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/configuracao.png"))); // NOI18N
        jMenuItem7.setText("Configurações");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jmArquivo.add(jMenuItem7);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/stop.png"))); // NOI18N
        jMenuItem6.setText("Logof");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jmArquivo.add(jMenuItem6);

        jMenuBar1.add(jmArquivo);

        jmCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user_suit.png"))); // NOI18N
        jmCadastro.setText("Cadastros");

        jmCargos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/status_online.png"))); // NOI18N
        jmCargos.setText("Cargos");
        jmCargos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmCargosActionPerformed(evt);
            }
        });
        jmCadastro.add(jmCargos);

        jmGerencia_User.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/group.png"))); // NOI18N
        jmGerencia_User.setText("Usuários");
        jmGerencia_User.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmGerencia_UserActionPerformed(evt);
            }
        });
        jmCadastro.add(jmGerencia_User);

        jMenuBar1.add(jmCadastro);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bricks.png"))); // NOI18N
        jMenu2.setText("Amostras de Dados");

        jmEditarAmostras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/wand.png"))); // NOI18N
        jmEditarAmostras.setText("Editar Dados");
        jmEditarAmostras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmEditarAmostrasActionPerformed(evt);
            }
        });
        jMenu2.add(jmEditarAmostras);

        jmImportacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logof.png"))); // NOI18N
        jmImportacao.setText("Importação");
        jMenu2.add(jmImportacao);

        jmExportalcao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logof2.png"))); // NOI18N
        jmExportalcao.setText("Exportação");
        jMenu2.add(jmExportalcao);

        jMenuBar1.add(jMenu2);

        jmFaturamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/calculator_edit.png"))); // NOI18N
        jmFaturamento.setText("Calculadoras");

        jmMediaAritimetica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/calculator_edit.png"))); // NOI18N
        jmMediaAritimetica.setText("Média Aritmética");
        jmMediaAritimetica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmMediaAritimeticaActionPerformed(evt);
            }
        });
        jmFaturamento.add(jmMediaAritimetica);

        jmMediaPonderada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/calculator_edit.png"))); // NOI18N
        jmMediaPonderada.setText("Média Ponderada");
        jmMediaPonderada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmMediaPonderadaActionPerformed(evt);
            }
        });
        jmFaturamento.add(jmMediaPonderada);

        jmMediaHarmonica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/chart_line_edit.png"))); // NOI18N
        jmMediaHarmonica.setText("Média Harmônica");
        jmMediaHarmonica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmMediaHarmonicaActionPerformed(evt);
            }
        });
        jmFaturamento.add(jmMediaHarmonica);

        jmMediaGeometrica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/chart_line_edit.png"))); // NOI18N
        jmMediaGeometrica.setText("Média Geométrica");
        jmMediaGeometrica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmMediaGeometricaActionPerformed(evt);
            }
        });
        jmFaturamento.add(jmMediaGeometrica);

        jmCalc1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/chart_line_edit.png"))); // NOI18N
        jmCalc1.setText("Calc1");
        jmCalc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmCalc1ActionPerformed(evt);
            }
        });
        jmFaturamento.add(jmCalc1);

        jmCalc2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/chart_line_edit.png"))); // NOI18N
        jmCalc2.setText("Calc2");
        jmCalc2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmCalc2ActionPerformed(evt);
            }
        });
        jmFaturamento.add(jmCalc2);

        jMenuBar1.add(jmFaturamento);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/layout_content.png"))); // NOI18N
        jMenu1.setText("Gráficos");

        jMenuPainel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/chart_line_add.png"))); // NOI18N
        jMenuPainel.setText("Painel");
        jMenuPainel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuPainelActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuPainel);

        jMenuBar1.add(jMenu1);

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

    private void jmEditarAmostrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmEditarAmostrasActionPerformed
        if (jfBase == null) {
            jfBase = new JifBaseDados();
            jDesktopPrincipal.add(jfBase);
            jfBase.setVisible(true);
            jfBase.setPosicao();
        } else if (!jfBase.isVisible()) {
            jfBase = new JifBaseDados();
            jDesktopPrincipal.add(jfBase);
            jfBase.setVisible(true);
            jfBase.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmEditarAmostrasActionPerformed

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

    private void jmMediaAritimeticaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmMediaAritimeticaActionPerformed
        if (jfMedA == null) {
            jfMedA = new JifMediaAritimetica();
            jDesktopPrincipal.add(jfMedA);
            jfMedA.setVisible(true);
            jfMedA.setPosicao();
        } else if (!jfMedA.isVisible()) {
            jfMedA = new JifMediaAritimetica();
            jDesktopPrincipal.add(jfMedA);
            jfMedA.setVisible(true);
            jfMedA.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmMediaAritimeticaActionPerformed

    private void jmMediaPonderadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmMediaPonderadaActionPerformed
        if (jfMedP == null) {
            jfMedP = new JifMediaPonderada();
            jDesktopPrincipal.add(jfMedP);
            jfMedP.setVisible(true);
            jfMedP.setPosicao();
        } else if (!jfMedP.isVisible()) {
            jfMedP = new JifMediaPonderada();
            jDesktopPrincipal.add(jfMedP);
            jfMedP.setVisible(true);
            jfMedP.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmMediaPonderadaActionPerformed

    private void jmCalc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmCalc1ActionPerformed

    }//GEN-LAST:event_jmCalc1ActionPerformed

    private void jmCalc2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmCalc2ActionPerformed

    }//GEN-LAST:event_jmCalc2ActionPerformed

    private void jmMediaGeometricaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmMediaGeometricaActionPerformed
        if (jfMedG == null) {
            jfMedG = new JifMediaGeometrica();
            jDesktopPrincipal.add(jfMedG);
            jfMedG.setVisible(true);
            jfMedG.setPosicao();
        } else if (!jfMedG.isVisible()) {
            jfMedG = new JifMediaGeometrica();
            jDesktopPrincipal.add(jfMedG);
            jfMedG.setVisible(true);
            jfMedG.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmMediaGeometricaActionPerformed

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

    private void jmMediaHarmonicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmMediaHarmonicaActionPerformed
        if (jfMedH == null) {
            jfMedH = new JifMediaHarmonica();
            jDesktopPrincipal.add(jfMedH);
            jfMedH.setVisible(true);
            jfMedH.setPosicao();
        } else if (!jfMedH.isVisible()) {
            jfMedH = new JifMediaHarmonica();
            jDesktopPrincipal.add(jfMedH);
            jfMedH.setVisible(true);
            jfMedH.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }//GEN-LAST:event_jmMediaHarmonicaActionPerformed

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane jDesktopPrincipal;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuPainel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jlHoraAtual;
    private javax.swing.JLabel jlInformacao;
    private javax.swing.JMenu jmArquivo;
    private javax.swing.JMenu jmCadastro;
    private javax.swing.JMenuItem jmCalc1;
    private javax.swing.JMenuItem jmCalc2;
    private javax.swing.JMenuItem jmCargos;
    private javax.swing.JMenuItem jmEditarAmostras;
    private javax.swing.JMenuItem jmExportalcao;
    private javax.swing.JMenu jmFaturamento;
    private javax.swing.JMenuItem jmGerencia_User;
    private javax.swing.JMenuItem jmImportacao;
    private javax.swing.JMenuItem jmMediaAritimetica;
    private javax.swing.JMenuItem jmMediaGeometrica;
    private javax.swing.JMenuItem jmMediaHarmonica;
    private javax.swing.JMenuItem jmMediaPonderada;
    private javax.swing.JMenu jmMenuAjuda;
    private javax.swing.JMenuItem jmSobre;
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
