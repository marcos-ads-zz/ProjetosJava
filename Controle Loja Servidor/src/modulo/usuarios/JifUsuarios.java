package modulo.usuarios;

import java.awt.HeadlessException;
import java.text.ParseException;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modulo.metodos.Funcao;
import modulo.versao.Versao;

/**
 *
 * @author Marcos Junior
 */
public final class JifUsuarios extends javax.swing.JInternalFrame {

    private final UsuarioDAO DAOUSER;
    private CargosDAO DAOCAR;
    private List<Cargos> objCar;

    private Usuario objUser;
    private Funcao fun;
    private int acao;
    private Versao ver;

    public JifUsuarios() {
        initComponents();
        DAOUSER = new UsuarioDAO();
        DAOCAR = new CargosDAO();
        fun = new Funcao();
        ver = new Versao();
        setTitle("Cadastro de Usuários: " + ver.getVersao());
        carregaCargos();
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    public void limparCampos() {
        jtId.setText("");
        jtMatricula.setText("");
        jtNome.setText("");
        jtEmail.setText("");
        jtSenhaPricipal.setText("");
        jtSenhaDeConfimacao.setText("");
        jtTelefone.setText("");
        jdDataDeNascimento.setText("");
        jcFuncoes.setSelectedIndex(0);
    }

    public void Cancelar() {
        limparCampos();
        jtId.setEnabled(false);
        jtMatricula.setEnabled(true);
        jtNome.setEnabled(false);
        jtEmail.setEnabled(false);
        jdDataDeNascimento.setEnabled(false);
        jtTelefone.setEnabled(false);
        jcFuncoes.setEnabled(false);

        jbNovo.setEnabled(true);
        jbSalvar.setEnabled(false);
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        jbPesquisar.setEnabled(true);
        jbCancelar.setEnabled(true);
        jdDataDeNascimento.setEnabled(true);
        jtSenhaPricipal.setEnabled(false);
        jtSenhaDeConfimacao.setEnabled(false);
        jbEditarSenha.setEnabled(false);
    }

    public void Editar() {
        jtId.setEnabled(false);
        jtMatricula.setEnabled(false);
        jtNome.setEnabled(true);
        jtEmail.setEnabled(true);
        jtTelefone.setEnabled(true);
        jdDataDeNascimento.setEnabled(true);
        jcFuncoes.setEnabled(true);

        jbNovo.setEnabled(false);
        jbSalvar.setEnabled(true);
        jbEditar.setEnabled(false);

        jbPesquisar.setEnabled(false);
        jbCancelar.setEnabled(true);

        acao = 2;
    }

    public void Novo() {
        limparCampos();
        jtId.setEnabled(false);
        jtMatricula.setEnabled(true);
        jtNome.setEnabled(true);
        jtEmail.setEnabled(true);
        jtTelefone.setEnabled(true);
        jdDataDeNascimento.setEnabled(true);
        jcFuncoes.setEnabled(true);
        jcFuncoes.setSelectedIndex(0);

        jbNovo.setEnabled(false);
        jbSalvar.setEnabled(true);
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        jbPesquisar.setEnabled(false);
        jbCancelar.setEnabled(true);
        jtSenhaPricipal.setEnabled(true);
        jtSenhaDeConfimacao.setEnabled(true);

        acao = 1;
    }

    public void Excluir() {
        try {
            if (jtMatricula.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Preencha o Campo Matricula");
            } else {
                if (DAOUSER.Delete(Integer.parseInt(jtMatricula.getText()))) {
                    JOptionPane.showMessageDialog(this, "Registro Excluido com Sucesso");
                    limparCampos();
                    preencheTabela();
                } else {
                    JOptionPane.showMessageDialog(this, "Não Foi Possível Excluir o Registro");

                }
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(this, "Erro: " + erro.getMessage());
        }
    }

    public void EditarSenha() {
        jtId.setEnabled(false);
        jtMatricula.setEnabled(false);
        jtNome.setEnabled(false);
        jtEmail.setEnabled(false);
        jtTelefone.setEnabled(false);
        jdDataDeNascimento.setEnabled(false);
        jcFuncoes.setEnabled(false);

        jtSenhaPricipal.setEnabled(true);
        jtSenhaDeConfimacao.setEnabled(true);

        jbNovo.setEnabled(false);
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        jbPesquisar.setEnabled(false);

        jbSalvar.setEnabled(true);
        jbCancelar.setEnabled(true);
        acao = 3;
    }

    public void CarregaDadosDaTabela() {
        Object id0, matricula, nome_user, email, telefone, data_nascm, cargo;

        try {
            if (jtTabela.getSelectedRow() != -1) {

                id0 = jtTabela.getValueAt(jtTabela.getSelectedRow(), 0);
                if (id0 == null) {
                    jtId.setText("");
                } else {
                    String id = id0.toString();
                    jtId.setText(id);
                }

                matricula = jtTabela.getValueAt(jtTabela.getSelectedRow(), 1);
                if (matricula == null) {
                    jtMatricula.setText("");
                } else {
                    String mat = matricula.toString();
                    jtMatricula.setText(mat);
                }

                nome_user = jtTabela.getValueAt(jtTabela.getSelectedRow(), 2);
                if (nome_user == null) {
                    jtNome.setText("");
                } else {
                    String nom = nome_user.toString();
                    jtNome.setText(nom);
                }

                email = jtTabela.getValueAt(jtTabela.getSelectedRow(), 3);
                if (email == null) {
                    jtEmail.setText("");
                } else {
                    String mail = email.toString();
                    jtEmail.setText(mail);
                }

                data_nascm = jtTabela.getValueAt(jtTabela.getSelectedRow(), 4);
                if (data_nascm == null) {
                    jdDataDeNascimento.setText("");
                } else {
                    String dat = (String) data_nascm;
                    jdDataDeNascimento.setText(dat);
                }

                telefone = jtTabela.getValueAt(jtTabela.getSelectedRow(), 5);
                if (telefone == null) {
                    jtTelefone.setText("");
                } else {
                    String tel = telefone.toString();
                    jtTelefone.setText(tel);
                }

                cargo = jtTabela.getValueAt(jtTabela.getSelectedRow(), 6);
                jcFuncoes.setSelectedItem(cargo);

                jbNovo.setEnabled(false);
                jbPesquisar.setEnabled(false);
                jbEditar.setEnabled(true);
                jbExcluir.setEnabled(true);
                jbEditarSenha.setEnabled(true);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(this, "Erro na Tabela: " + e);
        }
    }

    public void Salvar() {
        try {
            if (acao == 1) {
                if (validarCampos()) {
                    if (!DAOUSER.CheckSelect(Integer.parseInt(jtMatricula.getText()))) {
                        if (preencherObjetosInsert()) {
                            if (DAOUSER.Insert(objUser)) {
                                JOptionPane.showMessageDialog(this, "Salvo Com Sucesso!");
                                Cancelar();
                                preencheTabela();
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Não Foi Possível Salvar!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Matricula ja cadastrada!");
                    }
                }
            }
            if (acao == 2) {
                if (validarCampos()) {
                    if (DAOUSER.CheckSelect(Integer.parseInt(jtMatricula.getText()))) {
                        if (preencherObjetosUpdate()) {
                            if (DAOUSER.Update(objUser)) {
                                JOptionPane.showMessageDialog(this, "Editado Com Sucesso!");
                                Cancelar();
                                preencheTabela();
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Matricula ja cadastrada!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Não Foi Possível Editar o Registro!");
                }
            }
            if (acao == 3) {
                if (validarCamposSenha()) {
                    if (preencherObjetosUpdate()) {
                        if (DAOUSER.UpdateSenha(objUser)) {
                            JOptionPane.showMessageDialog(this, "Senha Modificada Com Sucesso!");
                            Cancelar();
                            preencheTabela();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "As Senhas Devem Ser Iguais!");
                }
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(this, "Erro ao Salvar: " + erro.getMessage());
        }
    }

    public void Pesquisar() {
        try {
            if (jtMatricula.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Preencha o Campo Matricula!");
                jtMatricula.requestFocus();
            } else {
                Usuario objFun = DAOUSER.PesquisaPorMatricula(Integer.parseInt(jtMatricula.getText()));
                if (objFun == null) {
                    JOptionPane.showMessageDialog(this, "Não Foi Possível Encontrar o Registro!");
                    jtMatricula.setText("");//Limpa ID
                    jtMatricula.requestFocus();
                } else {
                    try {
                        preencheTabela2();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao Iniciar readTable." + ex.getMessage());
                    }
                    jtId.setText(Integer.toString(objFun.getId()));
                    jtNome.setText(objFun.getNome());
                    jtEmail.setText(objFun.getEmail());
                    jtTelefone.setText(objFun.getTelefone());
                    jdDataDeNascimento.setText(fun.convertDataSQLToDateString(objFun.getDatnasc()));
                    jbEditar.setEnabled(true);
                    jbCancelar.setEnabled(true);
                    jbExcluir.setEnabled(true);
                    jbPesquisar.setEnabled(true);
                    jbEditarSenha.setEnabled(true);
                }
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(this, "Erro: " + erro.getMessage());
        }
    }

    public boolean validarCampos() {
        if (jtMatricula.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Matrícula!");
            jtMatricula.requestFocus();
            return false;
        }
        if (jtNome.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Nome!");
            jtNome.requestFocus();
            return false;
        }
        if (jtEmail.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo E-Mail!");
            jtEmail.requestFocus();
            return false;
        }
        if (jtTelefone.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Telefone!");
            jtTelefone.requestFocus();
            return false;
        }
        if (jdDataDeNascimento.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Idade!");
            jdDataDeNascimento.requestFocus();
            return false;
        }

        if (jcFuncoes.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma função!");
            jcFuncoes.requestFocus();
            return false;
        }
        return true;
    }

    public boolean validarCamposSenha() {
        if (jtSenhaPricipal.getText().equals(jtSenhaDeConfimacao.getText())) {
            jtMatricula.requestFocus();
            return true;
        }
        if (jtSenhaPricipal.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Senha!");
            jtMatricula.requestFocus();
            return false;
        }
        if (jtSenhaDeConfimacao.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Confirme Sua Senha!");
            jtNome.requestFocus();
            return false;
        }
        return false;
    }

    public void carregaCargos() {
        try {
            objCar = DAOCAR.Pesquisa();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Pesquisar Cargos! " + ex);
        }
        objCar.forEach((c) -> {
            jcFuncoes.addItem(c.getCargos());
        });
    }

    public boolean preencherObjetosInsert() throws ParseException {
        objUser = new Usuario();
        objUser.setNome(jtNome.getText());
        objUser.setMatricula(Integer.parseInt(jtMatricula.getText()));
        objUser.setEmail(jtEmail.getText());
        objUser.setDatnasc(fun.convertDateStringToDateSQL(jdDataDeNascimento.getText()));
        objUser.setTelefone(jtTelefone.getText());
        objUser.setFuncao(jcFuncoes.getSelectedItem().toString());
        objUser.setSenha(jtSenhaPricipal.getText());
        return true;
    }

    public boolean preencherObjetosUpdate() throws ParseException {
        objUser = new Usuario();
        objUser.setId(Integer.parseInt(jtId.getText()));
        objUser.setMatricula(Integer.parseInt(jtMatricula.getText()));
        objUser.setNome(jtNome.getText());
        objUser.setEmail(jtEmail.getText());
        objUser.setDatnasc(fun.convertDateStringToDateSQL(jdDataDeNascimento.getText()));
        objUser.setTelefone(jtTelefone.getText());
        objUser.setFuncao(jcFuncoes.getSelectedItem().toString());
        objUser.setSenha(jtSenhaPricipal.getText());
        return true;
    }

    public void preencheTabela() throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jtTabela.getModel();
        modelo.setNumRows(0);
        UsuarioDAO func = new UsuarioDAO();
        for (Usuario p : func.TabelaPesquisa()) {
            modelo.addRow(new Object[]{
                p.getId(), p.getMatricula(), p.getNome(), p.getEmail(),
                fun.convertDataSQLToDateString(p.getDatnasc()),
                p.getTelefone(), p.getFuncao()
            });
        }
    }

    public void preencheTabela2() throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jtTabela.getModel();
        modelo.setNumRows(0);
        UsuarioDAO func = new UsuarioDAO();
        for (Usuario p : func.TabelaPesquisa2(Integer.parseInt(jtMatricula.getText()))) {
            modelo.addRow(new Object[]{
                p.getId(), p.getMatricula(), p.getNome(), p.getEmail(),
                fun.convertDataSQLToDateString(p.getDatnasc()),
                p.getTelefone(), p.getFuncao()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtId = new javax.swing.JTextField();
        jtMatricula = new javax.swing.JTextField();
        jtNome = new javax.swing.JTextField();
        jtEmail = new javax.swing.JTextField();
        jbNovo = new javax.swing.JButton();
        jbSalvar = new javax.swing.JButton();
        jbEditar = new javax.swing.JButton();
        jbExcluir = new javax.swing.JButton();
        jbCancelar = new javax.swing.JButton();
        jbPesquisar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtTabela = new javax.swing.JTable();
        jbAtualizar = new javax.swing.JButton();
        jtSenhaPricipal = new javax.swing.JPasswordField();
        jtSenhaDeConfimacao = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jbEditarSenha = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jcFuncoes = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jtTelefone = new javax.swing.JFormattedTextField();
        jdDataDeNascimento = new javax.swing.JFormattedTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(102, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(896, 436));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ID");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nome");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("E-Mail");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 102));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Data de Nascimento");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 102));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Matrícula");

        jtId.setBackground(new java.awt.Color(204, 255, 204));
        jtId.setEnabled(false);

        jtMatricula.setBackground(new java.awt.Color(204, 255, 204));
        jtMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtMatriculaActionPerformed(evt);
            }
        });

        jtNome.setBackground(new java.awt.Color(204, 255, 204));
        jtNome.setEnabled(false);
        jtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtNomeActionPerformed(evt);
            }
        });

        jtEmail.setEnabled(false);
        jtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtEmailActionPerformed(evt);
            }
        });

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

        jbPesquisar.setText("Pesquisar");
        jbPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPesquisarActionPerformed(evt);
            }
        });

        jtTabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Matrícula", "Nome", "E-Mail", "Data de Nasc", "Telefone", "Função"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
        jScrollPane2.setViewportView(jtTabela);
        if (jtTabela.getColumnModel().getColumnCount() > 0) {
            jtTabela.getColumnModel().getColumn(0).setMinWidth(40);
            jtTabela.getColumnModel().getColumn(0).setMaxWidth(40);
            jtTabela.getColumnModel().getColumn(1).setMinWidth(80);
            jtTabela.getColumnModel().getColumn(1).setMaxWidth(80);
            jtTabela.getColumnModel().getColumn(4).setMinWidth(90);
            jtTabela.getColumnModel().getColumn(4).setMaxWidth(90);
            jtTabela.getColumnModel().getColumn(5).setMinWidth(120);
            jtTabela.getColumnModel().getColumn(5).setMaxWidth(120);
        }

        jbAtualizar.setText("Listar Todos");
        jbAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAtualizarActionPerformed(evt);
            }
        });

        jtSenhaPricipal.setBackground(new java.awt.Color(204, 255, 204));
        jtSenhaPricipal.setEnabled(false);
        jtSenhaPricipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtSenhaPricipalActionPerformed(evt);
            }
        });

        jtSenhaDeConfimacao.setBackground(new java.awt.Color(204, 255, 204));
        jtSenhaDeConfimacao.setEnabled(false);
        jtSenhaDeConfimacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtSenhaDeConfimacaoActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 102));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Digite Uma Senha");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 102));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Confirme a Senha");

        jbEditarSenha.setText("Editar Senha");
        jbEditarSenha.setEnabled(false);
        jbEditarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditarSenhaActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 102));
        jLabel7.setText("Telefone");

        jcFuncoes.setBackground(new java.awt.Color(204, 255, 204));
        jcFuncoes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECIONE UM CARGO" }));
        jcFuncoes.setEnabled(false);
        jcFuncoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcFuncoesActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 102));
        jLabel10.setText("Cargo");

        try {
            jtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)# ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtTelefone.setEnabled(false);
        jtTelefone.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);

        try {
            jdDataDeNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jdDataDeNascimento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jdDataDeNascimento.setFocusCycleRoot(true);
        jdDataDeNascimento.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jtId)
                                        .addGap(768, 768, 768))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jdDataDeNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jbNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jbSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jbEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jbExcluir)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jbPesquisar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jbCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jbAtualizar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(476, 476, 476)
                                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jtSenhaPricipal, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jtSenhaDeConfimacao, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel7)
                                                .addGap(18, 18, 18)
                                                .addComponent(jtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel10)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jcFuncoes, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addComponent(jbEditarSenha)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 277, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtSenhaPricipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jcFuncoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jtSenhaDeConfimacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jbEditarSenha))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jbNovo)
                    .addComponent(jbSalvar)
                    .addComponent(jbEditar)
                    .addComponent(jbExcluir)
                    .addComponent(jbAtualizar)
                    .addComponent(jbCancelar)
                    .addComponent(jbPesquisar)
                    .addComponent(jdDataDeNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1084, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtMatriculaActionPerformed
        jtNome.requestFocus();
    }//GEN-LAST:event_jtMatriculaActionPerformed

    private void jtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtNomeActionPerformed
        jtEmail.requestFocus();
    }//GEN-LAST:event_jtNomeActionPerformed

    private void jtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtEmailActionPerformed
        jtTelefone.requestFocus();
    }//GEN-LAST:event_jtEmailActionPerformed

    private void jbNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNovoActionPerformed
        Novo();
    }//GEN-LAST:event_jbNovoActionPerformed

    private void jbSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarActionPerformed
        Salvar();
    }//GEN-LAST:event_jbSalvarActionPerformed

    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarActionPerformed
        Editar();
    }//GEN-LAST:event_jbEditarActionPerformed

    private void jbExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExcluirActionPerformed
        Excluir();
    }//GEN-LAST:event_jbExcluirActionPerformed

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        Cancelar();
    }//GEN-LAST:event_jbCancelarActionPerformed

    private void jbPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPesquisarActionPerformed
        Pesquisar();
    }//GEN-LAST:event_jbPesquisarActionPerformed

    private void jtTabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtTabelaMouseClicked
        CarregaDadosDaTabela();
    }//GEN-LAST:event_jtTabelaMouseClicked

    private void jbAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAtualizarActionPerformed
        Cancelar();
        try {
            preencheTabela();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Iniciar readTable." + ex);
        }
    }//GEN-LAST:event_jbAtualizarActionPerformed

    private void jtSenhaPricipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtSenhaPricipalActionPerformed
        jtSenhaDeConfimacao.requestFocus();
    }//GEN-LAST:event_jtSenhaPricipalActionPerformed

    private void jtSenhaDeConfimacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtSenhaDeConfimacaoActionPerformed
        jcFuncoes.requestFocus();
    }//GEN-LAST:event_jtSenhaDeConfimacaoActionPerformed

    private void jbEditarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarSenhaActionPerformed
        EditarSenha();
    }//GEN-LAST:event_jbEditarSenhaActionPerformed

    private void jcFuncoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcFuncoesActionPerformed
        jdDataDeNascimento.requestFocus();
    }//GEN-LAST:event_jcFuncoesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbAtualizar;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbEditarSenha;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JButton jbNovo;
    private javax.swing.JButton jbPesquisar;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JComboBox<String> jcFuncoes;
    private javax.swing.JFormattedTextField jdDataDeNascimento;
    private javax.swing.JTextField jtEmail;
    private javax.swing.JTextField jtId;
    private javax.swing.JTextField jtMatricula;
    private javax.swing.JTextField jtNome;
    private javax.swing.JPasswordField jtSenhaDeConfimacao;
    private javax.swing.JPasswordField jtSenhaPricipal;
    private javax.swing.JTable jtTabela;
    private javax.swing.JFormattedTextField jtTelefone;
    // End of variables declaration//GEN-END:variables
}
