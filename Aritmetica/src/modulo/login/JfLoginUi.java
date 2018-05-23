package modulo.login;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import modulo.usuarios.UsuarioDAO;
import modulo.metodos.ConvertMD5;
import modulo.metodos.CryptoBase64;
import modulo.configuracoes.DadosConfig;
import modulo.usuarios.Usuario;
import modulo.sobre.Versao;
import javax.swing.ImageIcon;
import modulo.configuracoes.JfConfig;
import modulo.view.principal.JfPrincipal;

/**
 *
 * @author Marcos Junior
 */
public final class JfLoginUi extends javax.swing.JFrame {

    private UsuarioDAO DAOUser;
    private Usuario objUser;
    private UserLogado login;
    private Versao ver;
    private ConvertMD5 md5;
    private DadosConfig dadosLocal;

    public JfLoginUi() {
        initComponents();
        ver = new Versao();
        dadosLocal = new DadosConfig();
        md5 = new ConvertMD5();
        DAOUser = new UsuarioDAO();
        login = new UserLogado();
        setTitle(ver.getNomesys() + " " + ver.getVersao());
        jlInfo.setText("Loja: " + dadosLocal.getLoja());
    }

    public Usuario CarregaUser() throws Exception {
        objUser = DAOUser.PesquisaPorMatricula(Integer.parseInt(jtMatricula.getText()));
        jtNomeDoUsuario.setText(objUser.getNome());
        return objUser;
    }

    public void VerificaPass() {
        try {
            if (objUser != null) {
                String result = md5.MD5Char(jtSenha.getPassword());
                if (result == null ? objUser.getSenha() == null : result.equals(objUser.getSenha())) {
                    loader.show();
                    loginP.hide();
                    new java.util.Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            JfPrincipal m = new JfPrincipal();
                            m.setExtendedState(MAXIMIZED_BOTH);
                            m.show();
                            dispose();
                        }
                    }, 50 * 5);
                    login.setNomelogin(objUser.getNome());
                    login.setMatricula(Integer.toString(objUser.getMatricula()));
                    login.setNumeroloja(Integer.parseInt(dadosLocal.getLoja()));
                    objUser = null;
                } else {
                    jtSenha.setText("");
                    JOptionPane.showMessageDialog(this, "Senha Incorreta!\nTente Novamente!");
                    jtSenha.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Falha no processo de login o sistema será fechado.");
                System.exit(0);
            }
        } catch (HeadlessException | UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            JOptionPane.showMessageDialog(this, "Erro Dados Incorretos!");
        }
    }

    public void PassDireto() {
        loader.show();
        loginP.hide();
        new java.util.Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                JfPrincipal m = new JfPrincipal();
                m.setExtendedState(MAXIMIZED_BOTH);
                m.show();
                dispose();
            }
        }, 50 * 2);
        login.setNomelogin(objUser.getNome());
        login.setMatricula(Integer.toString(objUser.getMatricula()));
        objUser = null;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_bg = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        loginP = new javax.swing.JPanel();
        jtMatricula = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jbLogin = new java.awt.Button();
        lbl_close = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtSenha = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        jtNomeDoUsuario = new javax.swing.JTextField();
        jlInfo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        loader = new javax.swing.JPanel();
        img_loader = new javax.swing.JLabel();
        lbl_loader = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/icons/icone_sistema.png")).getImage());
        setLocationByPlatform(true);
        setUndecorated(true);

        pnl_bg.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.CardLayout());

        loginP.setBackground(new java.awt.Color(0, 255, 0));
        loginP.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                loginPMouseDragged(evt);
            }
        });
        loginP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                loginPMousePressed(evt);
            }
        });

        jtMatricula.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jtMatricula.setBorder(null);
        jtMatricula.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtMatriculaFocusGained(evt);
            }
        });
        jtMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtMatriculaActionPerformed(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(41, 168, 73));
        jSeparator1.setForeground(new java.awt.Color(41, 168, 73));

        jSeparator2.setBackground(new java.awt.Color(41, 168, 73));
        jSeparator2.setForeground(new java.awt.Color(41, 168, 73));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/unlock_18px.png"))); // NOI18N
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/contacts_18px.png"))); // NOI18N

        jbLogin.setBackground(new java.awt.Color(107, 159, 234));
        jbLogin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jbLogin.setForeground(new java.awt.Color(0, 0, 0));
        jbLogin.setLabel("Login");
        jbLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbLoginMouseClicked(evt);
            }
        });
        jbLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLoginActionPerformed(evt);
            }
        });
        jbLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jbLoginKeyPressed(evt);
            }
        });

        lbl_close.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        lbl_close.setForeground(new java.awt.Color(255, 0, 0));
        lbl_close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_close.setText("X");
        lbl_close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbl_closeMousePressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setText("Esqueceu a senha?");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jtSenha.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jtSenha.setText("jPasswordField1");
        jtSenha.setBorder(null);
        jtSenha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtSenhaFocusGained(evt);
            }
        });
        jtSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtSenhaMouseClicked(evt);
            }
        });
        jtSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtSenhaActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI Semilight", 1, 24)); // NOI18N
        jLabel6.setText("Login");

        jtNomeDoUsuario.setEditable(false);
        jtNomeDoUsuario.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jtNomeDoUsuario.setBorder(null);
        jtNomeDoUsuario.setEnabled(false);

        jlInfo.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jlInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlInfo.setText("Loja: 340");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/configuracao.png"))); // NOI18N
        jLabel1.setBorder(new javax.swing.border.MatteBorder(null));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout loginPLayout = new javax.swing.GroupLayout(loginP);
        loginP.setLayout(loginPLayout);
        loginPLayout.setHorizontalGroup(
            loginPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbl_close, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(loginPLayout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addGroup(loginPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(loginPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, loginPLayout.createSequentialGroup()
                        .addGroup(loginPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, loginPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jtNomeDoUsuario, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jtSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jtMatricula)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(loginPLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                        .addComponent(jbLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlInfo)
                .addContainerGap())
        );
        loginPLayout.setVerticalGroup(
            loginPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPLayout.createSequentialGroup()
                .addGroup(loginPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(loginPLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(loginPLayout.createSequentialGroup()
                        .addComponent(lbl_close)
                        .addGap(44, 44, 44)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addGroup(loginPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtMatricula, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(loginPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(loginPLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtNomeDoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPLayout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel5)
                        .addGap(11, 11, 11)))
                .addGap(46, 46, 46)
                .addGroup(loginPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlInfo)
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        jPanel1.add(loginP, "card2");

        loader.setBackground(new java.awt.Color(204, 255, 204));

        img_loader.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/ring.gif"))); // NOI18N

        lbl_loader.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_loader.setForeground(new java.awt.Color(41, 168, 73));
        lbl_loader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_loader.setText("Loggin in....");

        javax.swing.GroupLayout loaderLayout = new javax.swing.GroupLayout(loader);
        loader.setLayout(loaderLayout);
        loaderLayout.setHorizontalGroup(
            loaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loaderLayout.createSequentialGroup()
                .addContainerGap(166, Short.MAX_VALUE)
                .addGroup(loaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(img_loader, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addComponent(lbl_loader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(166, Short.MAX_VALUE))
        );
        loaderLayout.setVerticalGroup(
            loaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loaderLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(img_loader, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(lbl_loader)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        jPanel1.add(loader, "card3");

        javax.swing.GroupLayout pnl_bgLayout = new javax.swing.GroupLayout(pnl_bg);
        pnl_bg.setLayout(pnl_bgLayout);
        pnl_bgLayout.setHorizontalGroup(
            pnl_bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnl_bgLayout.setVerticalGroup(
            pnl_bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void jbLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLoginActionPerformed
        VerificaPass();
    }//GEN-LAST:event_jbLoginActionPerformed

    private void jtMatriculaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtMatriculaFocusGained
        jtNomeDoUsuario.setText("");
    }//GEN-LAST:event_jtMatriculaFocusGained

    private void jtSenhaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtSenhaFocusGained
        jtSenha.setText("");
    }//GEN-LAST:event_jtSenhaFocusGained

    private void lbl_closeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_closeMousePressed
        System.exit(0);
    }//GEN-LAST:event_lbl_closeMousePressed
    int xy, xx;
    private void loginPMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginPMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_loginPMouseDragged

    private void loginPMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginPMousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_loginPMousePressed

    private void jtMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtMatriculaActionPerformed
        if (jtMatricula.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Matrícula Incorreta!");
        } else {
            try {
                if (CarregaUser() != null) {
                    jtSenha.requestFocus();
                } else {
                    jtMatricula.requestFocus();
                }
            } catch (Exception ex) {
                jtMatricula.requestFocus();
                jtMatricula.setText("");
                JOptionPane.showMessageDialog(this, "Usuário Inválido!\n"
                        + ex.getMessage());
            }
        }
    }//GEN-LAST:event_jtMatriculaActionPerformed

    private void jtSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtSenhaActionPerformed
        jbLogin.requestFocus();
    }//GEN-LAST:event_jtSenhaActionPerformed

    private void jbLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbLoginMouseClicked
        VerificaPass();
    }//GEN-LAST:event_jbLoginMouseClicked

    private void jbLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jbLoginKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            VerificaPass();
        }
    }//GEN-LAST:event_jbLoginKeyPressed

    private void jtSenhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtSenhaMouseClicked
        if (jtMatricula.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Matrícula Incorreta!");
        } else {
            try {
                if (CarregaUser() != null) {
                    jtSenha.requestFocus();
                } else {
                    jtMatricula.requestFocus();
                }
            } catch (Exception ex) {
                jtMatricula.requestFocus();
                jtMatricula.setText("");
                JOptionPane.showMessageDialog(this, "Erro ao Carregar Usuário: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_jtSenhaMouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        JOptionPane.showMessageDialog(this, "Função ainda não disponível!\n\n"
                + "Caso nescessário chame o desenvolvedor do sistema!\n"
                + "Tel: (89) 9 8805-6444, Marcos Neri");
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfConfig.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new JfConfig().setVisible(true);
        });        
    }//GEN-LAST:event_jLabel1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel img_loader;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private java.awt.Button jbLogin;
    private javax.swing.JLabel jlInfo;
    private javax.swing.JTextField jtMatricula;
    private javax.swing.JTextField jtNomeDoUsuario;
    private javax.swing.JPasswordField jtSenha;
    private javax.swing.JLabel lbl_close;
    private javax.swing.JLabel lbl_loader;
    private javax.swing.JPanel loader;
    private javax.swing.JPanel loginP;
    private javax.swing.JPanel pnl_bg;
    // End of variables declaration//GEN-END:variables
}
