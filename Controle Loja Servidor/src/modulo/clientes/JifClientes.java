package modulo.clientes;

import modulo.versao.Versao;
import java.awt.Dimension;
import java.text.ParseException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modulo.metodos.Funcao;

/**
 * @author Marcos Junior
 */
public final class JifClientes extends javax.swing.JInternalFrame {

    private int acao;
    private ClienteDAO DAOCLI;
    private Cliente objCli;
    private Funcao fun;
    private Versao ver = new Versao();

    public JifClientes() {
        initComponents();
        fun = new Funcao();
        DAOCLI = new ClienteDAO();
        Thread relogioThred = new Thread(new JifClientes.clsDataHora());
        relogioThred.start();
        setTitle("Cadastro de Clientes: " + ver.getVersao());
    }

    public void limparCampos() {
        jtId.setText("");
        jtNomeCliente.setText("");
        jtEndereco.setText("");
        jtTelefone.setText("");
    }

    public void HabilitaEdicao() {
        jtEndereco.setEnabled(true);
        jtTelefone.setEnabled(true);
    }

    public void Cancelar() {
        limparCampos();
        jtId.setEnabled(false);
        jtNomeCliente.setEnabled(true);
        jtEndereco.setEnabled(false);
        jtTelefone.setEnabled(false);
        jbNovo.setEnabled(true);
        jbSalvar.setEnabled(false);
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        jbCancelar.setEnabled(true);
        acao = 0;
    }

    public void Novo() {
        limparCampos();
        jtId.setEnabled(false);
        jtNomeCliente.setEnabled(true);
        jtEndereco.setEnabled(true);
        jtTelefone.setEnabled(true);
        jbNovo.setEnabled(false);
        jbSalvar.setEnabled(true);
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        jbCancelar.setEnabled(true);
        acao = 1;
    }

    public boolean validarCampos() {
        boolean check = true;
        if (jtNomeCliente.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Nome!");
            check = false;
        }
        if (jtEndereco.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Endereço!");
            check = false;
        }
        if (jtTelefone.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Telefone!");
            check = false;
        }
        return check;
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    public boolean Salvar() throws Exception {
        return DAOCLI.Insert(objCli) > 0;
    }

    public boolean Editar() throws Exception {
        return DAOCLI.Update(objCli);
    }

    public boolean Exluir() throws Exception {
        return DAOCLI.Delete(Integer.parseInt(jtId.getText()));
    }
    int cont = 0;

    public void Pesquisar() {
        List<Cliente> cliente;
        try {
            cliente = DAOCLI.PesquisaNome(jtNomeCliente.getText().toUpperCase());
            PreencheTabelaDaViewNome(cliente);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro Pesquisa: " + ex.getMessage());
        }

    }

    class clsDataHora implements Runnable {

        @Override
        public void run() {
            while (true) {
                SetValores();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println("Thread não foi finalizada:" + ex);
                }
            }
        }
    }

    public void SetValores() {
        jtDataRegistro.setText(fun.dataAtualDate());
    }

    public boolean preencherObjetosSalvar() throws ParseException {
        objCli = new Cliente();
        objCli.setNome_cli(jtNomeCliente.getText().toUpperCase());
        objCli.setEnd_cli(jtEndereco.getText().toUpperCase());
        objCli.setTelefone_cli(jtTelefone.getText());
        objCli.setData_registro(fun.convertDateStringToDateSQL(jtDataRegistro.getText()));
        return objCli != null;
    }

    public boolean preencherObjetosEditar() {
        objCli = new Cliente();
        objCli.setId(Integer.parseInt(jtId.getText()));
        objCli.setNome_cli(jtNomeCliente.getText().toUpperCase());
        objCli.setEnd_cli(jtEndereco.getText().toUpperCase());
        objCli.setTelefone_cli(jtTelefone.getText());
        return objCli != null;
    }

    public void PreencheTabelaDaView() throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jtTabela.getModel();
        modelo.setNumRows(0);
        List<Cliente> cliente = DAOCLI.TabelaPesquisa();
        cliente.forEach((p) -> {
            modelo.addRow(new Object[]{
                p.getId(), p.getNome_cli(), p.getEnd_cli(), p.getTelefone_cli()
            });
        });
    }

    public void PreencheTabelaDaViewNome(List<Cliente> cliente) throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jtTabela.getModel();
        modelo.setNumRows(0);
        if (acao != 1) {
            cliente.forEach((p) -> {
                modelo.addRow(new Object[]{
                    p.getId(), p.getNome_cli(), p.getEnd_cli(), p.getTelefone_cli()
                });
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtId = new javax.swing.JTextField();
        jbNovo = new javax.swing.JButton();
        jbSalvar = new javax.swing.JButton();
        jbEditar = new javax.swing.JButton();
        jbExcluir = new javax.swing.JButton();
        jbCancelar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtTabela = new javax.swing.JTable();
        jbAtualizar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtEndereco = new javax.swing.JTextField();
        jtTelefone = new javax.swing.JTextField();
        jtDataRegistro = new javax.swing.JFormattedTextField();
        jtNomeCliente = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(153, 255, 204));

        jLabel1.setText("ID");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Nome");

        jtId.setEnabled(false);

        jbNovo.setText("Novo");
        jbNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNovoActionPerformed(evt);
            }
        });

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

        jbExcluir.setText("Excluir");
        jbExcluir.setEnabled(false);
        jbExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExcluirActionPerformed(evt);
            }
        });

        jbCancelar.setText("Cancelar");
        jbCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
            }
        });

        jtTabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Endereço", "Telefone"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtTabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtTabelaMouseClicked(evt);
            }
        });
        jtTabela.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtTabelaKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jtTabela);

        jbAtualizar.setText("Listar");
        jbAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAtualizarActionPerformed(evt);
            }
        });

        jLabel2.setText("Endereço");

        jLabel3.setText("Telefone");

        jtEndereco.setEnabled(false);

        jtTelefone.setEnabled(false);

        jtDataRegistro.setEditable(false);
        try {
            jtDataRegistro.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtDataRegistro.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jtNomeCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtNomeClienteKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jbNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(jbAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtEndereco)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtId, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(jtDataRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 249, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtDataRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbCancelar)
                    .addComponent(jbExcluir)
                    .addComponent(jbAtualizar)
                    .addComponent(jbEditar)
                    .addComponent(jbSalvar)
                    .addComponent(jbNovo))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                .addGap(18, 18, 18))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNovoActionPerformed
        Novo();
    }//GEN-LAST:event_jbNovoActionPerformed

    private void jbSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarActionPerformed
        try {
            if (acao == 1) {
                if (validarCampos()) {
                    if (preencherObjetosSalvar()) {
                        if (Salvar()) {
                            JOptionPane.showMessageDialog(this, "Salvo Com Sucesso!");
                            Cancelar();
                            PreencheTabelaDaView();
                        } else {
                            JOptionPane.showMessageDialog(this, "Não Foi Possível Salvar!");
                        }
                    }
                }
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(this, "Erro ao Salvar: " + erro.getMessage());
        }
    }//GEN-LAST:event_jbSalvarActionPerformed

    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarActionPerformed
        try {
            if (validarCampos() & !jtId.getText().equals("")) {
                if (preencherObjetosEditar()) {
                    if (Editar()) {
                        JOptionPane.showMessageDialog(this, "Editado Com Sucesso!");
                        Pesquisar();
                        Cancelar();
                    } else {
                        JOptionPane.showMessageDialog(this, "Não Foi Possível Editar o Registro!");
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro!" + e.getMessage());
        }
    }//GEN-LAST:event_jbEditarActionPerformed

    private void jbExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExcluirActionPerformed
        try {
            if (!jtId.getText().equals("")) {
                if (Exluir()) {
                    JOptionPane.showMessageDialog(this, "Registro Excluido com Sucesso");
                    Cancelar();
                    PreencheTabelaDaView();
                } else {
                    JOptionPane.showMessageDialog(this, "Não Foi Possível Excluir o Registro");
                }
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(this, "Erro: " + erro.getMessage());
        }
    }//GEN-LAST:event_jbExcluirActionPerformed

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        Cancelar();
    }//GEN-LAST:event_jbCancelarActionPerformed

    private void jtTabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtTabelaMouseClicked
        HabilitaEdicao();
        if (jtTabela.getSelectedRow() != -1) {
            Object id0, nome1, endereco2, telefone3;

            id0 = jtTabela.getValueAt(jtTabela.getSelectedRow(), 0);
            String id = id0.toString();
            jtId.setText(id);

            nome1 = jtTabela.getValueAt(jtTabela.getSelectedRow(), 1);
            String nome = nome1.toString();
            jtNomeCliente.setText(nome);

            endereco2 = jtTabela.getValueAt(jtTabela.getSelectedRow(), 2);
            String endereco = endereco2.toString();
            jtEndereco.setText(endereco);

            telefone3 = jtTabela.getValueAt(jtTabela.getSelectedRow(), 3);
            String telefone = telefone3.toString();
            jtTelefone.setText(telefone);

            jbEditar.setEnabled(true);
            jbExcluir.setEnabled(true);
        }
    }//GEN-LAST:event_jtTabelaMouseClicked

    private void jtTabelaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtTabelaKeyReleased
        HabilitaEdicao();
        if (jtTabela.getSelectedRow() != -1) {
            Object id0, nome1, endereco2, telefone3;

            id0 = jtTabela.getValueAt(jtTabela.getSelectedRow(), 0);
            String id = id0.toString();
            jtId.setText(id);

            nome1 = jtTabela.getValueAt(jtTabela.getSelectedRow(), 1);
            String nome = nome1.toString();
            jtNomeCliente.setText(nome);

            endereco2 = jtTabela.getValueAt(jtTabela.getSelectedRow(), 2);
            String endereco = endereco2.toString();
            jtEndereco.setText(endereco);

            telefone3 = jtTabela.getValueAt(jtTabela.getSelectedRow(), 3);
            String telefone = telefone3.toString();
            jtTelefone.setText(telefone);

            jbEditar.setEnabled(true);
            jbExcluir.setEnabled(true);
        }
    }//GEN-LAST:event_jtTabelaKeyReleased

    private void jbAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAtualizarActionPerformed
        acao = 1;
        try {
            PreencheTabelaDaView();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Iniciar readTable." + ex.getMessage());
        }
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
    }//GEN-LAST:event_jbAtualizarActionPerformed

    private void jtNomeClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtNomeClienteKeyPressed
        Pesquisar();
    }//GEN-LAST:event_jtNomeClienteKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbAtualizar;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JButton jbNovo;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JFormattedTextField jtDataRegistro;
    private javax.swing.JTextField jtEndereco;
    private javax.swing.JTextField jtId;
    private javax.swing.JTextField jtNomeCliente;
    private javax.swing.JTable jtTabela;
    private javax.swing.JTextField jtTelefone;
    // End of variables declaration//GEN-END:variables
}
