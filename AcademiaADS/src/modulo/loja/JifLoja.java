package modulo.loja;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import modulo.sobre.Versao;

/**
 *
 * @author Marcos Junior
 */
public final class JifLoja extends javax.swing.JInternalFrame {

    private LojaDAO DAO;
    private Loja objFun;
    private Versao ver;

    public JifLoja() {
        initComponents();
        ver = new Versao();
        DAO = new LojaDAO();
        setTitle("Cadastrar Loja: " + ver.getVersao());
        CadastroView();
        IniciarFalse();
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    public void Editar() {
        jtNumero.setEnabled(true);
        jtRazao.setEnabled(true);
        jtN_Filial.setEnabled(true);
        jtBairro.setEnabled(true);
        jtCep.setEnabled(true);
        jtCidade.setEnabled(true);
        jtCnpj.setEnabled(true);
        jtEnd.setEnabled(true);
        jtIE.setEnabled(true);
        jtUf.setEnabled(true);
        jtTelefone.setEnabled(true);
        jtId.setEnabled(false);
    }

    public void IniciarFalse() {
        jtNumero.setEnabled(false);
        jtRazao.setEnabled(false);
        jtN_Filial.setEnabled(false);
        jtBairro.setEnabled(false);
        jtCep.setEnabled(false);
        jtCidade.setEnabled(false);
        jtCnpj.setEnabled(false);
        jtEnd.setEnabled(false);
        jtIE.setEnabled(false);
        jtUf.setEnabled(false);
        jtTelefone.setEnabled(false);
        jtId.setEnabled(false);
    }

    public boolean validarCampos() {
        if (jtNumero.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Número da Loja!");
            jtNumero.requestFocus();
            return false;
        }
        if (jtRazao.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Nome da Loja!");
            jtRazao.requestFocus();
            return false;
        }
        if (jtN_Filial.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Gerente da Loja!");
            jtN_Filial.requestFocus();
            return false;
        }
        if (jtNumero.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Número da Loja!");
            jtNumero.requestFocus();
            return false;
        }
        if (jtRazao.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Nome da Loja!");
            jtRazao.requestFocus();
            return false;
        }
        if (jtN_Filial.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Gerente da Loja!");
            jtN_Filial.requestFocus();
            return false;
        }
        if (jtNumero.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Número da Loja!");
            jtNumero.requestFocus();
            return false;
        }
        if (jtRazao.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Nome da Loja!");
            jtRazao.requestFocus();
            return false;
        }
        if (jtN_Filial.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Gerente da Loja!");
            jtN_Filial.requestFocus();
            return false;
        }

        return true;
    }

    public boolean preencherObjetosSalvar() {
        objFun = new Loja();
        objFun.setNome_loja(jtRazao.getText());
        objFun.setNumero_filial(Integer.parseInt(jtN_Filial.getText()));
        objFun.setNum_ponto(Integer.parseInt(jtNumero.getText()));
        objFun.setIs_estadual(jtIE.getText());
        objFun.setTelefone(jtTelefone.getText());
        objFun.setCnpj(jtCnpj.getText());
        objFun.setCep(jtCep.getText());
        objFun.setEstado(jtUf.getText());
        objFun.setCidade(jtCidade.getText());
        objFun.setBairro(jtBairro.getText());
        objFun.setLogradouro(jtEnd.getText());
        return true;
    }

    public boolean preencherObjetosEditar() {
        objFun = new Loja();
        objFun.setId(Integer.parseInt(jtId.getText()));
        objFun.setNome_loja(jtRazao.getText());
        objFun.setNumero_filial(Integer.parseInt(jtN_Filial.getText()));
        objFun.setNum_ponto(Integer.parseInt(jtNumero.getText()));
        objFun.setIs_estadual(jtIE.getText());
        objFun.setTelefone(jtTelefone.getText());
        objFun.setCnpj(jtCnpj.getText());
        objFun.setCep(jtCep.getText());
        objFun.setEstado(jtUf.getText());
        objFun.setCidade(jtCidade.getText());
        objFun.setBairro(jtBairro.getText());
        objFun.setLogradouro(jtEnd.getText());
        return true;
    }

    public void CadastroView() {
        Loja objFun1;
        try {
            objFun1 = DAO.PesquisaIdLoja();
            jtId.setText(Integer.toString(objFun1.getId()));
            jtRazao.setText(objFun1.getNome_loja());
            jtN_Filial.setText(Integer.toString(objFun1.getNumero_filial()));
            jtNumero.setText(Integer.toString(objFun1.getNum_ponto()));
            jtIE.setText(objFun1.getIs_estadual());
            jtTelefone.setText(objFun1.getTelefone());
            jtCnpj.setText(objFun1.getCnpj());
            jtCep.setText(objFun1.getCep());
            jtUf.setText(objFun1.getEstado());
            jtCidade.setText(objFun1.getCidade());
            jtBairro.setText(objFun1.getBairro());
            jtEnd.setText(objFun1.getLogradouro());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jtRazao = new javax.swing.JTextField();
        jbSalvar = new javax.swing.JButton();
        jbEditar = new javax.swing.JButton();
        jbCancelar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtNumero = new javax.swing.JTextField();
        jtN_Filial = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtEnd = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtCnpj = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jtIE = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jtCep = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jtBairro = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jtCidade = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jtUf = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jtTelefone = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jtId = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);

        jPanel1.setBackground(new java.awt.Color(51, 255, 204));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Razão");

        jbSalvar.setText("Salvar");
        jbSalvar.setEnabled(false);
        jbSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvarActionPerformed(evt);
            }
        });

        jbEditar.setText("Editar");
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

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nº");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Filial");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Endereço");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("CNPJ");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("I.E");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("CEP");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Bairro");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Cidade");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("UF");

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Telefone");

        jLabel1.setText("Registro");

        jtId.setEditable(false);
        jtId.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtUf, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jtEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jtRazao, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtIE, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jtN_Filial, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtId, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 15, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jbEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbCancelar)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtN_Filial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtRazao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jtCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jtIE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jtCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jtEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jtUf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbEditar)
                    .addComponent(jbCancelar)
                    .addComponent(jbSalvar))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
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

    private void jbSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarActionPerformed
        try {
            if (DAO.CheckSelect(Integer.parseInt(jtId.getText()))) {
                if (validarCampos()) {
                    if (preencherObjetosEditar()) {
                        if (DAO.Update(objFun)) {
                            JOptionPane.showMessageDialog(this, "Editado Com Sucesso!");
                            jbCancelarActionPerformed(evt);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Não Foi Possível Editar o Registro!");
                }
            } else {
                if (validarCampos()) {
                    if (preencherObjetosSalvar()) {
                        if (DAO.Insert(objFun)) {
                            JOptionPane.showMessageDialog(this, "Salvo Com Sucesso!");
                            jbCancelarActionPerformed(evt);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Não Foi Possível Salvar!");
                    }
                }
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(this, "Erro ao Salvar: " + erro.getMessage());
        }
    }//GEN-LAST:event_jbSalvarActionPerformed

    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarActionPerformed
        Editar();
        jbSalvar.setEnabled(true);
        jbEditar.setEnabled(false);
        jbCancelar.setEnabled(true);

    }//GEN-LAST:event_jbEditarActionPerformed

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        jbSalvar.setEnabled(false);
        jbEditar.setEnabled(true);
        jbCancelar.setEnabled(false);
        
        IniciarFalse();
        CadastroView();
    }//GEN-LAST:event_jbCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JTextField jtBairro;
    private javax.swing.JTextField jtCep;
    private javax.swing.JTextField jtCidade;
    private javax.swing.JTextField jtCnpj;
    private javax.swing.JTextField jtEnd;
    private javax.swing.JTextField jtIE;
    private javax.swing.JTextField jtId;
    private javax.swing.JTextField jtN_Filial;
    private javax.swing.JTextField jtNumero;
    private javax.swing.JTextField jtRazao;
    private javax.swing.JTextField jtTelefone;
    private javax.swing.JTextField jtUf;
    // End of variables declaration//GEN-END:variables
}
