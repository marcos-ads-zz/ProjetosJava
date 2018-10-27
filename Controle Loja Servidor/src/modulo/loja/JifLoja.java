package modulo.loja;

import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modulo.versao.Versao;

/**
 *
 * @author Marcos Junior
 */
public class JifLoja extends javax.swing.JInternalFrame {

    private LojaDAO DAO;
    private Loja objFun;
    private int acao;
    private Versao ver = new Versao();

    public JifLoja() {
        initComponents();
        setTitle("Cadastrar Loja: " + ver.getVersao());
        DAO = new LojaDAO();
        jtId.setText("1");
        carregarLojaCampos();
        iniciaCampos();
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    private void carregarLojaCampos() {
        try {
            if (verificaCadastroLoja()) {
                Loja dados = DAO.PesquisaNumeroLoja(Integer.parseInt(jtId.getText()));
                jtNomeDaLoja.setText(dados.getNome_loja());
                jtNumeroDaLoja.setText(Integer.toString(dados.getNumero_loja()));
                jtGerenteDaLoja.setText(dados.getGerente_loja());
            } else {
                ajustarCampos();
            }

        } catch (Exception ex) {
            Logger.getLogger(JifLoja.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void iniciaCampos() {
        jtNomeDaLoja.setEnabled(false);
        jtNumeroDaLoja.setEnabled(false);
        jtGerenteDaLoja.setEnabled(false);
        jbSalvar.setEnabled(false);
        jbEditar.setEnabled(true);
        jbCancelar.setEnabled(false);
    }

    private void habilitaCampos() {
        jtNomeDaLoja.setEnabled(true);
        jtNumeroDaLoja.setEnabled(true);
        jtGerenteDaLoja.setEnabled(true);
        jbSalvar.setEnabled(true);
        jbEditar.setEnabled(false);
        jbCancelar.setEnabled(true);
    }

    private void ajustarCampos() {
        if (jtNomeDaLoja.getText().equals("")) {
            jtNomeDaLoja.setText("Nome da Loja?");
        }
        if (jtNumeroDaLoja.getText().equals("")) {
            jtNumeroDaLoja.setText("1");
        }
        if (jtGerenteDaLoja.getText().equals("")) {
            jtGerenteDaLoja.setText("Nome do Gerente?");
        }
    }

    private boolean verificaCadastroLoja() throws Exception {
        return DAO.CheckSelect(Integer.parseInt(jtId.getText()));
    }

    private void editarEsalvar() {
        try {
            if (!verificaCadastroLoja()) {
                if (validarCampos()) {
                    if (preencherObjetosSalvar()) {
                        if (DAO.Insert(objFun)) {
                            JOptionPane.showMessageDialog(this, "Salvo Com Sucesso!");
                            iniciaCampos();
                            carregarLojaCampos();
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Não Foi Possível Salvar!");
                    }
                }
            } else {
                if (validarCampos()) {
                    if (preencherObjetosEditar()) {
                        if (DAO.Update(objFun)) {
                            JOptionPane.showMessageDialog(this, "Editado Com Sucesso!");
                            iniciaCampos();
                            carregarLojaCampos();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Não Foi Possível Editar o Registro!");
                }
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(this, "Erro ao Salvar: " + erro.getMessage());
        }
    }

    private boolean validarCampos() {
        if (jtNumeroDaLoja.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Número da Loja!");
            jtNumeroDaLoja.requestFocus();
            return false;
        }
        if (jtNomeDaLoja.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Nome da Loja!");
            jtNomeDaLoja.requestFocus();
            return false;
        }
        if (jtGerenteDaLoja.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Gerente da Loja!");
            jtGerenteDaLoja.requestFocus();
            return false;
        }
        return true;
    }

    private boolean preencherObjetosSalvar() {
        objFun = new Loja();
        objFun.setNome_loja(jtNomeDaLoja.getText());
        objFun.setNumero_loja(Integer.parseInt(jtNumeroDaLoja.getText()));
        objFun.setGerente_loja(jtGerenteDaLoja.getText());
        return true;
    }

    private boolean preencherObjetosEditar() {
        objFun = new Loja();
        objFun.setId(Integer.parseInt(jtId.getText()));
        objFun.setNome_loja(jtNomeDaLoja.getText());
        objFun.setNumero_loja(Integer.parseInt(jtNumeroDaLoja.getText()));
        objFun.setGerente_loja(jtGerenteDaLoja.getText());
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtId = new javax.swing.JTextField();
        jtNomeDaLoja = new javax.swing.JTextField();
        jbSalvar = new javax.swing.JButton();
        jbEditar = new javax.swing.JButton();
        jbCancelar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtNumeroDaLoja = new javax.swing.JTextField();
        jtGerenteDaLoja = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jPanel1.setBackground(new java.awt.Color(0, 0, 204));

        jLabel1.setText("ID");

        jLabel5.setText("Nome da Loja");

        jtId.setEnabled(false);

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

        jbCancelar.setText("Cancelar");
        jbCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
            }
        });

        jLabel2.setText("Número da Loja");

        jLabel3.setText("Gerente da Loja");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtId, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtNumeroDaLoja, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtNomeDaLoja, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jbSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)))
                    .addComponent(jtGerenteDaLoja))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbSalvar))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jtNomeDaLoja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jtNumeroDaLoja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbCancelar)))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtGerenteDaLoja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarActionPerformed
        editarEsalvar();
    }//GEN-LAST:event_jbSalvarActionPerformed

    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarActionPerformed
        habilitaCampos();
    }//GEN-LAST:event_jbEditarActionPerformed

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        ajustarCampos();
        iniciaCampos();
    }//GEN-LAST:event_jbCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JTextField jtGerenteDaLoja;
    private javax.swing.JTextField jtId;
    private javax.swing.JTextField jtNomeDaLoja;
    private javax.swing.JTextField jtNumeroDaLoja;
    // End of variables declaration//GEN-END:variables
}
