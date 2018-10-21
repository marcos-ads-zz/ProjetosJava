package modulo.ctf;

import modulo.usuarios.UsuarioDAO;
import modulo.versao.Versao;
import java.awt.Dimension;
import java.text.ParseException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modulo.metodos.Funcao;

/**
 *
 * @author Marcos Junior
 */
public final class JifCTF extends javax.swing.JInternalFrame {

    private Versao ver = new Versao();
    private UsuarioDAO DAOUSER;
    private CtfDAO DAOCTF;
    private CTF objCTF;
    private Funcao fun;

    public JifCTF() {
        initComponents();
        fun = new Funcao();
        DAOCTF = new CtfDAO();
        DAOUSER = new UsuarioDAO();
        setTitle("Registro de Pendencias do CTF: " + ver.getVersao());
        try {
            PreencheTabelaDaView();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro Pesquisa: " + ex.getMessage());
        }
        jtDataCTF.setText(fun.dataAtualDate());
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    public void limparCampos() {
        jtId.setText(null);
        jtPdv.setText(null);
        jtNSU.setText(null);
        jtMatricula.setText(null);
        jtValor.setText(null);
        jtDataCTF.setText(null);
        jtMac.setText(null);
    }

    public void HabilitaEdicao() {
        jbSalvar.setEnabled(false);
        jbEditar.setEnabled(true);
        jbExcluir.setEnabled(true);
    }

    public void Cancelar() {
        limparCampos();
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        jbSalvar.setEnabled(true);
        jtMatricula.setBackground(new java.awt.Color(214, 217, 223));
    }

    public boolean validarCampos() {
        boolean check = true;
        if (jtPdv.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo PDV!");
            check = false;
        }
        if (jtNSU.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo NSU!");
            check = false;
        }
        if (jtMatricula.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Matrícula!");
            check = false;
        }
        if (jtValor.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Valor!");
            check = false;
        }
        if (jtDataCTF.getText().equals("  /  /    ")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo CTF!");
            check = false;
        }
        if (jtMac.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Mac!");
            check = false;
        }
        if (!VerificaMatricula()) {
            JOptionPane.showMessageDialog(this, "Matrícula Incorreta!");
            check = false;
        }
        return check;
    }

    public boolean VerificaMatricula() {
        boolean check = false;
        if (!jtMatricula.getText().equals("")) {
            try {
                if (DAOUSER.CheckSelect(Integer.parseInt(jtMatricula.getText()))) {
                    jtMatricula.setBackground(new java.awt.Color(51, 51, 255));
                    check = true;
                } else {
                    jtMatricula.setBackground(new java.awt.Color(255, 0, 0));
                    check = false;
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao Verificar Matricula: " + ex.getMessage());
            }
        }
        return check;
    }

    public void SelecionaItensTabela() {
        if (jtTabela.getSelectedRow() != -1) {
            Object id, pdv, nsu, mac, valor, operador, datactf, status;

            id = jtTabela.getValueAt(jtTabela.getSelectedRow(), 0);
            jtId.setText(id.toString());

            pdv = jtTabela.getValueAt(jtTabela.getSelectedRow(), 1);
            jtPdv.setText(pdv.toString());

            nsu = jtTabela.getValueAt(jtTabela.getSelectedRow(), 2);
            jtNSU.setText(nsu.toString());

            mac = jtTabela.getValueAt(jtTabela.getSelectedRow(), 3);
            jtMac.setText(mac.toString());

            valor = jtTabela.getValueAt(jtTabela.getSelectedRow(), 4);
            jtValor.setText(valor.toString());

            operador = jtTabela.getValueAt(jtTabela.getSelectedRow(), 5);
            jtMatricula.setText(operador.toString());

            datactf = jtTabela.getValueAt(jtTabela.getSelectedRow(), 6);
            jtDataCTF.setText(datactf.toString());

            status = jtTabela.getValueAt(jtTabela.getSelectedRow(), 8);
            jtSituacao.setSelectedItem(status.toString());

            HabilitaEdicao();
        }
    }

    public boolean Salvar() throws Exception {
        return DAOCTF.Insert(objCTF) > 0;
    }

    public boolean Editar() throws Exception {
        return DAOCTF.Update(objCTF);
    }

    public boolean Exluir() throws Exception {
        return DAOCTF.Delete(Integer.parseInt(jtId.getText()));
    }
    int cont = 0;

    public void Pesquisar() {
        List<CTF> ctf;
        try {
            ctf = DAOCTF.PesquisaDataCTF(fun.convertDateStringToDateSQL(jtDataCTF.getText()));
            PreencheTabelaDaViewDataCTF(ctf);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro Pesquisa: " + ex.getMessage());
        }
    }

    public boolean preencherObjetosSalvar() throws ParseException, Exception {
        objCTF = new CTF();
        objCTF.setPdv(Integer.parseInt(jtPdv.getText()));
        objCTF.setNsu(Integer.parseInt(jtNSU.getText()));
        objCTF.setMac(Integer.parseInt(jtMac.getText()));
        objCTF.setValor(fun.convertDoubleStringToDouble(jtValor.getText()));
        objCTF.setDate_ctf(fun.convertDateStringToDateSQL(jtDataCTF.getText()));
        objCTF.setDate_registro(fun.atualDateSQL());
        objCTF.setStatus(jtSituacao.getSelectedItem().toString());
        int id = DAOUSER.PesquisaPorMatricula(Integer.parseInt(jtMatricula.getText())).getId();
        objCTF.setId_user(id);
        return objCTF != null;
    }

    public boolean preencherObjetosEditar() throws ParseException, Exception {
        objCTF = new CTF();
        objCTF.setId_ctf(Integer.parseInt(jtId.getText()));
        objCTF.setPdv(Integer.parseInt(jtPdv.getText()));
        objCTF.setNsu(Integer.parseInt(jtNSU.getText()));
        objCTF.setMac(Integer.parseInt(jtMac.getText()));
        objCTF.setValor(fun.convertDoubleStringToDouble(jtValor.getText()));
        objCTF.setDate_ctf(fun.convertDateStringToDateSQL(jtDataCTF.getText()));
        objCTF.setStatus(jtSituacao.getSelectedItem().toString());
        int id = DAOUSER.PesquisaPorMatricula(Integer.parseInt(jtMatricula.getText())).getId();
        objCTF.setId_user(id);
        return objCTF != null;
    }

    public void PreencheTabelaDaView() throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jtTabela.getModel();
        modelo.setNumRows(0);
        List<CTF> ctf = DAOCTF.TabelaPesquisaDataAtual(fun.atualDateSQL(), fun.atualDateSQL());
        ctf.forEach((p) -> {
            try {
                modelo.addRow(new Object[]{
                    p.getId_ctf(), p.getPdv(), p.getNsu(), p.getMac(),
                    fun.convertDoubleToStringMoeda(p.getValor()),
                    DAOUSER.PesquisaID(p.getId_user()).getMatricula(),
                    fun.convertDataSQLToDateString(p.getDate_ctf()),
                    fun.convertDataSQLToDateString(p.getDate_registro()), p.getStatus()
                });
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao Pesquisar Id Usuário: " + ex.getMessage());
            }
        });
    }

    public void PreencheTabelaDaViewDataCTF(List<CTF> ctf) throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jtTabela.getModel();
        modelo.setNumRows(0);
        ctf.forEach((p) -> {
            try {
                modelo.addRow(new Object[]{
                    p.getId_ctf(), p.getPdv(), p.getNsu(), p.getMac(),
                    fun.convertDoubleToStringMoeda(p.getValor()),
                    DAOUSER.PesquisaID(p.getId_user()).getMatricula(),
                    fun.convertDataSQLToDateString(p.getDate_ctf()),
                    fun.convertDataSQLToDateString(p.getDate_registro()), p.getStatus()
                });
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao Pesquisar Id Usuário: " + ex.getMessage());
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtPdv = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jtNSU = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jtValor = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jtDataCTF = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jtMac = new javax.swing.JFormattedTextField();
        jbSalvar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtTabela = new javax.swing.JTable();
        jtSituacao = new javax.swing.JComboBox<>();
        jbEditar = new javax.swing.JButton();
        jbExcluir = new javax.swing.JButton();
        jbPesquisar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jtId = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jtMatricula = new javax.swing.JFormattedTextField();
        jbCancelar = new javax.swing.JButton();

        setClosable(true);

        jLabel1.setText("PDV");

        jtPdv.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jtPdv.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtPdv.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jtPdv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtPdvActionPerformed(evt);
            }
        });

        jLabel2.setText("NSU CTF");

        jtNSU.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jtNSU.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtNSU.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jtNSU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtNSUActionPerformed(evt);
            }
        });

        jLabel3.setText("VALOR");

        jtValor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jtValor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtValor.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jtValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtValorActionPerformed(evt);
            }
        });

        jLabel4.setText("DATA CTF");

        try {
            jtDataCTF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtDataCTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtDataCTF.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jtDataCTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtDataCTFActionPerformed(evt);
            }
        });

        jLabel5.setText("STATUS");

        jLabel6.setText("MAC");

        try {
            jtMac.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtMac.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtMac.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jtMac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtMacActionPerformed(evt);
            }
        });

        jbSalvar.setText("Salvar");
        jbSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvarActionPerformed(evt);
            }
        });

        jtTabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "PDV", "NSU", "MAC", "VALOR", "MATRÍCULA", "DATA CTF", "REGISTRO", "STATUS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
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
        jScrollPane1.setViewportView(jtTabela);
        if (jtTabela.getColumnModel().getColumnCount() > 0) {
            jtTabela.getColumnModel().getColumn(0).setMaxWidth(60);
        }

        jtSituacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SITUAÇÃO", "PENDENTE", "OUTRO ERRO" }));
        jtSituacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtSituacaoActionPerformed(evt);
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

        jbPesquisar.setText("Pesquisar");
        jbPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPesquisarActionPerformed(evt);
            }
        });

        jLabel7.setText("ID");

        jtId.setEditable(false);
        jtId.setEnabled(false);

        jLabel8.setText("OPERADOR");

        jtMatricula.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtMatriculaActionPerformed(evt);
            }
        });

        jbCancelar.setText("Cancelar");
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtNSU, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                    .addComponent(jtPdv))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtDataCTF, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtMac, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbEditar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtId, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbPesquisar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 26, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtPdv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jtSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbPesquisar)
                    .addComponent(jLabel7)
                    .addComponent(jtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbCancelar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtMac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jtDataCTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jtNSU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jbSalvar)
                    .addComponent(jbEditar)
                    .addComponent(jbExcluir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarActionPerformed

        try {
            if (validarCampos()) {
                if (preencherObjetosSalvar()) {
                    if (Salvar()) {
                        PreencheTabelaDaView();
                        limparCampos();
                        JOptionPane.showMessageDialog(this, "Salvo com Sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Não Foi Possível Salvar!");
                    }
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Salvar: " + ex.getMessage());
        }
    }//GEN-LAST:event_jbSalvarActionPerformed

    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarActionPerformed
        try {
            if (validarCampos()) {
                if (preencherObjetosEditar()) {
                    if (Editar()) {
                        PreencheTabelaDaView();
                        limparCampos();
                        JOptionPane.showMessageDialog(this, "Editado com Sucesso!");
                        Cancelar();
                    } else {
                        JOptionPane.showMessageDialog(this, "Não Foi Possível Editar!");
                    }
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Editar: " + ex.getMessage());
        }
    }//GEN-LAST:event_jbEditarActionPerformed

    private void jbExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExcluirActionPerformed
        try {
            if (!jtId.getText().equals("")) {
                if (Exluir()) {
                    PreencheTabelaDaView();
                    JOptionPane.showMessageDialog(this, "Excluido com Sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Não Foi Possível Excluir!");
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Excluir: " + ex.getMessage());
        }
    }//GEN-LAST:event_jbExcluirActionPerformed

    private void jbPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPesquisarActionPerformed
        Pesquisar();
    }//GEN-LAST:event_jbPesquisarActionPerformed

    private void jtPdvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtPdvActionPerformed
        if (!jtPdv.getText().equals("")) {
            jtMatricula.requestFocus();
        }
    }//GEN-LAST:event_jtPdvActionPerformed

    private void jtMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtMatriculaActionPerformed
        if (VerificaMatricula()) {
            jtSituacao.requestFocus();
        }
    }//GEN-LAST:event_jtMatriculaActionPerformed

    private void jtSituacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtSituacaoActionPerformed
        jtNSU.requestFocus();
    }//GEN-LAST:event_jtSituacaoActionPerformed

    private void jtNSUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtNSUActionPerformed
        if (!jtNSU.getText().equals("")) {
            jtValor.requestFocus();
        }
    }//GEN-LAST:event_jtNSUActionPerformed

    private void jtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtValorActionPerformed
        if (!jtValor.getText().equals("")) {
            jtDataCTF.requestFocus();
        }
    }//GEN-LAST:event_jtValorActionPerformed

    private void jtDataCTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtDataCTFActionPerformed
        if (!jtDataCTF.getText().equals("")) {
            jtMac.requestFocus();
        }
    }//GEN-LAST:event_jtDataCTFActionPerformed

    private void jtMacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtMacActionPerformed
        if (!jtMac.getText().equals("")) {
            jbSalvar.requestFocus();
        }
    }//GEN-LAST:event_jtMacActionPerformed

    private void jtTabelaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtTabelaKeyReleased
        SelecionaItensTabela();
    }//GEN-LAST:event_jtTabelaKeyReleased

    private void jtTabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtTabelaMouseClicked
        SelecionaItensTabela();
    }//GEN-LAST:event_jtTabelaMouseClicked

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        Cancelar();
    }//GEN-LAST:event_jbCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JButton jbPesquisar;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JFormattedTextField jtDataCTF;
    private javax.swing.JTextField jtId;
    private javax.swing.JFormattedTextField jtMac;
    private javax.swing.JFormattedTextField jtMatricula;
    private javax.swing.JFormattedTextField jtNSU;
    private javax.swing.JFormattedTextField jtPdv;
    private javax.swing.JComboBox<String> jtSituacao;
    private javax.swing.JTable jtTabela;
    private javax.swing.JFormattedTextField jtValor;
    // End of variables declaration//GEN-END:variables
}
