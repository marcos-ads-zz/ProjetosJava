package modulo.atualizacao;

import java.awt.Dimension;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modulo.login.UserLogado;
import modulo.versao.Versao;
import modulo.usuarios.Cargos;
import modulo.usuarios.CargosDAO;

/**
 *
 * @author Marcos Júnior <marcosneri@outlook.com.br>
 */
public final class JifAjusteParaAtualizacao extends javax.swing.JInternalFrame {

    private Versao ver;
    private UserLogado login;
    private CargosDAO DAOCARGO;
    private int cont = 0;

    public JifAjusteParaAtualizacao() {
        initComponents();
        ver = new Versao();
        login = new UserLogado();
        DAOCARGO = new CargosDAO();
        setTitle("Ajustes do Sistema: " + ver.getVersao());
        jtMatricula.setText(login.getMatricula());
        jtNome.setText(login.getNomelogin());
        try {
            PreencheTabelaDaView();
            PreencheTabelaDaViewAtual();
        } catch (Exception ex) {
            Logger.getLogger(JifAjusteParaAtualizacao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Cargos> TabelaNova() {
        Cargos cargos1 = new Cargos();
        Cargos cargos2 = new Cargos();
        Cargos cargos3 = new Cargos();
        Cargos cargos4 = new Cargos();
        Cargos cargos5 = new Cargos();
        Cargos cargos6 = new Cargos();
        Cargos cargos7 = new Cargos();
        Cargos cargos8 = new Cargos();
        Cargos cargos9 = new Cargos();

        List<Cargos> carg = new ArrayList<>();

        cargos1.setCargos("GERENTE");
        cargos1.setNivel(9);
        carg.add(cargos1);

        cargos2.setCargos("ASSISTENTE DE GERENTE");
        cargos2.setNivel(8);
        carg.add(cargos2);

        cargos3.setCargos("FARMACÊUTICO");
        cargos3.setNivel(7);
        carg.add(cargos3);

        cargos4.setCargos("AUXILIAR DE FARMÁCIA");
        cargos4.setNivel(6);
        carg.add(cargos4);

        cargos5.setCargos("DERMOCONSULTORA");
        cargos5.setNivel(5);
        carg.add(cargos5);

        cargos6.setCargos("CAIXA");
        cargos6.setNivel(4);
        carg.add(cargos6);

        cargos7.setCargos("AUXILIAR DE LOJA");
        cargos7.setNivel(3);
        carg.add(cargos7);

        cargos8.setCargos("MENOR APRENDIZ");
        cargos8.setNivel(2);
        carg.add(cargos8);

        cargos9.setCargos("SERVIÇOS GERAIS");
        cargos9.setNivel(2);
        carg.add(cargos9);

        return carg;
    }

    public void SalvarDados() {
        Cargos objCarg = new Cargos();

        List<Cargos> cargos = TabelaNova();
        cargos.forEach((p) -> {
            cont++;
            jlAviso.setText("Atualizando Registros Aguarde!!: " + cont);
            try {

                if (DAOCARGO.CheckSelect(p.getCargos())) {
                    objCarg.setCargos(p.getCargos());
                    objCarg.setNivel(p.getNivel());
                    if (DAOCARGO.UpdateNome(objCarg)) {

                    } else {
                        JOptionPane.showMessageDialog(this, "Erro ao Atualizar");
                    }
                } else if (!DAOCARGO.CheckSelect(p.getCargos())) {
                    objCarg.setCargos(p.getCargos());
                    objCarg.setNivel(p.getNivel());
                    if (DAOCARGO.Insert(objCarg)) {

                    } else {
                        JOptionPane.showMessageDialog(this, "Erro ao Inserir Novos Dados");
                    }
                } else {
                    jlAviso.setText("Dados Já Atualizados");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Falha Geral: " + ex.getMessage());
            }
        });
        cont = 0;
    }

    public void PreencheTabelaDaView() throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jtTabelaAtualizada.getModel();
        modelo.setNumRows(0);
        List<Cargos> cliente = TabelaNova();
        cliente.forEach((p) -> {
            modelo.addRow(new Object[]{
                p.getCargos(), p.getNivel()
            });
        });
    }

    public void PreencheTabelaDaViewAtual() throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jtTabelaAtual.getModel();
        modelo.setNumRows(0);
        List<Cargos> cargo = DAOCARGO.Pesquisa();
        cargo.forEach((p) -> {
            modelo.addRow(new Object[]{
                p.getId(), p.getCargos(), p.getNivel()
            });
        });
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    public void Sair() {
        exit(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jtMatricula = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtNome = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jbFinalizar = new javax.swing.JButton();
        jlAviso = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtTabelaAtual = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtTabelaAtualizada = new javax.swing.JTable();

        setResizable(true);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 102));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Matrícula");

        jtMatricula.setEditable(false);
        jtMatricula.setBackground(new java.awt.Color(204, 255, 204));
        jtMatricula.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nome");

        jtNome.setBackground(new java.awt.Color(204, 255, 204));
        jtNome.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ajuste de Nível de Acesso Clique em Atualizar.");

        jbFinalizar.setBackground(new java.awt.Color(255, 0, 0));
        jbFinalizar.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jbFinalizar.setForeground(new java.awt.Color(0, 255, 204));
        jbFinalizar.setText("Atualizar");
        jbFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbFinalizarActionPerformed(evt);
            }
        });

        jlAviso.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jlAviso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlAviso.setText("****");

        jtTabelaAtual.setBorder(new javax.swing.border.MatteBorder(null));
        jtTabelaAtual.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "CARGO (BASE ATUAL)", "NÍVEL"
            }
        ));
        jScrollPane1.setViewportView(jtTabelaAtual);
        if (jtTabelaAtual.getColumnModel().getColumnCount() > 0) {
            jtTabelaAtual.getColumnModel().getColumn(0).setMaxWidth(60);
            jtTabelaAtual.getColumnModel().getColumn(2).setMaxWidth(60);
        }

        jtTabelaAtualizada.setBorder(new javax.swing.border.MatteBorder(null));
        jtTabelaAtualizada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "CARGO (BASE ATUALIZADA)", "NÍVEL"
            }
        ));
        jScrollPane2.setViewportView(jtTabelaAtualizada);
        if (jtTabelaAtualizada.getColumnModel().getColumnCount() > 0) {
            jtTabelaAtualizada.getColumnModel().getColumn(1).setMaxWidth(60);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlAviso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtNome))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jlAviso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbFinalizar)
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbFinalizarActionPerformed

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    jbFinalizar.setEnabled(false);
                    jlAviso.setText("Aguarde!!");
                    SalvarDados();
                    jbFinalizar.setEnabled(true);
                    sleep(3000);
                    jlAviso.setText("Registros Atualizados!!");
                    PreencheTabelaDaView();
                    PreencheTabelaDaViewAtual();
                    sleep(4000);
                    jlAviso.setText("Fechando o Sistema Aguarde!!");
                    sleep(5000);
                    Sair();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Falha Geral ao Atualizar: " + ex.getMessage());
                }
            }

        };
        t.start();


    }//GEN-LAST:event_jbFinalizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbFinalizar;
    private javax.swing.JLabel jlAviso;
    private javax.swing.JTextField jtMatricula;
    private javax.swing.JTextField jtNome;
    private javax.swing.JTable jtTabelaAtual;
    private javax.swing.JTable jtTabelaAtualizada;
    // End of variables declaration//GEN-END:variables
}
