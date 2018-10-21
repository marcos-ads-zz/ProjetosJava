package modulo.produtos;

import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import modulo.versao.Versao;
import modulo.view.principal.JfPrincipal;
import modulo.metodos.JifCarregamento;

/**
 *
 * @author Marcos Junior
 */
public final class JifProdutos extends javax.swing.JInternalFrame {

    private ProdutoDAO DAOPro;
    private Produto objPro;
    private Versao ver;
    private String opcao;
    private JifCarregamento jfC = null;
    private int acao;

    public JifProdutos() {
        initComponents();
        ver = new Versao();
        setTitle("Cadastro de Produtos: " + ver.getVersao());
        DAOPro = new ProdutoDAO();
    }

    public void limparCampos() {
        jtId.setText("");
        jtDescricao.setText("");
        jtCodigoInterno.setText("");
        jtCodigoProduto.setText("");
        jtObservacao.setText("");
        jtDescricao.setText("");
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    public void PesquisarItem() {
        Carregando();
        Thread t = new Thread() {
            @Override
            public void run() {
                PesquisaItem();
                fechaCarregamento();
            }
        };
        t.start();
    }

    public void Novo() {
        limparCampos();
        jtId.setEnabled(false);
        jtDescricao.setEnabled(true);
        jtCodigoInterno.setEnabled(true);
        jtCodigoProduto.setEnabled(true);
        jtObservacao.setEnabled(true);

        jbNovo.setEnabled(false);
        jbSalvar.setEnabled(true);
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        jbCancelar.setEnabled(true);
        jbPesquisar.setEnabled(false);

        acao = 1;
    }

    public void SetarCamposAoCancelar() {
        jtId.setEnabled(false);
        jtDescricao.setEnabled(false);
        jtCodigoInterno.setEnabled(false);
        jtCodigoProduto.setEnabled(false);
        jtObservacao.setEnabled(false);

        jbNovo.setEnabled(true);
        jbSalvar.setEnabled(false);
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        jbCancelar.setEnabled(true);
        jbPesquisar.setEnabled(true);
        jtCodigoInterno.setEnabled(true);
    }

    public void Cancelar() {
        limparCampos();
        jtId.setEnabled(false);
        jtDescricao.setEnabled(false);
        jtCodigoInterno.setEnabled(false);
        jtCodigoProduto.setEnabled(false);
        jtObservacao.setEnabled(false);

        jbNovo.setEnabled(true);
        jbSalvar.setEnabled(false);
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        jbCancelar.setEnabled(true);
        jbPesquisar.setEnabled(true);
        jtCodigoInterno.setEnabled(true);
        acao = 0;
    }

    public void Editar() {
        jtId.setEnabled(false);
        jtCodigoInterno.setEnabled(false);
        jtCodigoProduto.setEnabled(true);
        jtDescricao.setEnabled(true);
        jtObservacao.setEnabled(true);

        jbNovo.setEnabled(false);
        jbSalvar.setEnabled(true);
        jbEditar.setEnabled(false);

        jbPesquisar.setEnabled(false);
        jbCancelar.setEnabled(true);
        jtCodigoInterno.setEnabled(true);

        acao = 2;
    }

    public boolean validarCampos() {
        if (jtCodigoInterno.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Codigo Interno!");
            jtCodigoInterno.requestFocus();
            return false;
        }
        if (jtCodigoProduto.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Codigo do Produto!");
            jtCodigoProduto.requestFocus();
            return false;
        }
        if (jtDescricao.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Descrição!");
            jtDescricao.requestFocus();
            return false;
        }
        return true;
    }

    public void SalvarDados() {
        Carregando();
        Thread t = new Thread() {
            @Override
            public void run() {

                try {
                    if (acao == 1) {
                        if (validarCampos()) {
                            if (!DAOPro.CheckSelectCod_Interno(Integer.parseInt(jtCodigoInterno.getText()))) {
                                if (preencherObjetosSalvar()) {
                                    if (DAOPro.Insert(objPro)) {
                                        JOptionPane.showMessageDialog(null, "Salvo Com Sucesso!");
                                        preencheTabelaCodInterno();
                                        limparCampos();
                                        SetarCamposAoCancelar();
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Não Foi Possível Salvar!");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Produto ja Cadastrado!");
                            }

                        }
                    }
                    if (acao == 2) {
                        if (validarCampos()) {
                            //if(DAOPro.CheckSelectCod_Interno(Integer.parseInt(jtCodigoInterno.getText()))){
                            if (preencherObjetosEditar()) {
                                if (DAOPro.Update(objPro)) {
                                    JOptionPane.showMessageDialog(null, "Editado Com Sucesso!");
                                    preencheTabelaCodInterno();
                                    limparCampos();
                                    SetarCamposAoCancelar();
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Não Foi Possível Editar o Registro!");
                            }
                        }
                    }
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + erro.getMessage());
                }
                jtCodigoInterno.setEnabled(true);
                jtCodigoProduto.setEnabled(true);
                fechaCarregamento();
            }
        };
        t.start();
    }

    public boolean preencherObjetosSalvar() {
        objPro = new Produto();
        objPro.setCod_interno(Integer.parseInt(jtCodigoInterno.getText()));
        objPro.setCod_produto(jtCodigoProduto.getText());
        objPro.setDescricao(jtDescricao.getText());
        objPro.setObserv(jtObservacao.getText());
        return true;
    }

    public boolean preencherObjetosEditar() {
        objPro = new Produto();
        objPro.setId(Integer.parseInt(jtId.getText()));
        objPro.setCod_interno(Integer.parseInt(jtCodigoInterno.getText()));
        objPro.setCod_produto(jtCodigoProduto.getText());
        objPro.setDescricao(jtDescricao.getText());
        objPro.setObserv(jtObservacao.getText());
        return true;
    }

    public void SelecionaNaTabela() {
        Object cod_inter, cod_prod, descricao, obs;
        try {
            if (jtTabelaProdutos.getSelectedRow() != -1) {

                cod_inter = jtTabelaProdutos.getValueAt(jtTabelaProdutos.getSelectedRow(), 0);

                if (cod_inter == null) {
                    jtCodigoInterno.setText("");
                } else {
                    String codI = cod_inter.toString();
                    jtCodigoInterno.setText(codI);
                }

                cod_prod = jtTabelaProdutos.getValueAt(jtTabelaProdutos.getSelectedRow(), 1);
                if (cod_prod == null) {
                    jtCodigoProduto.setText("");
                } else {
                    String codP = cod_prod.toString();
                    jtCodigoProduto.setText(codP);
                }

                descricao = jtTabelaProdutos.getValueAt(jtTabelaProdutos.getSelectedRow(), 2);
                if (descricao == null) {
                    jtDescricao.setText("");
                } else {
                    String descri = descricao.toString();
                    jtDescricao.setText(descri);
                }

                obs = jtTabelaProdutos.getValueAt(jtTabelaProdutos.getSelectedRow(), 3);
                if (obs == null) {
                    jtObservacao.setText("");
                } else {
                    String observ = obs.toString();
                    jtObservacao.setText(observ);
                }
                jbNovo.setEnabled(true);
                jbPesquisar.setEnabled(true);
                jbEditar.setEnabled(true);
                jbExcluir.setEnabled(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro na Tabela: " + e);
        }
    }

    public void PesquisaItem() {

        if ("ean".equals(opcao)) {
            try {
                if (jtCodigoProduto.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Preencha o Campo Código do Produto!");
                    jtCodigoProduto.requestFocus();
                } else {
                    String t = jtCodigoProduto.getText();
                    objPro = DAOPro.PesquisarPorCodProduto(t);
                    if (objPro == null) {
                        JOptionPane.showMessageDialog(this, "Não Foi Possível Encontrar o Registro!");
                        jtCodigoProduto.setText("");//Limpa ID
                        jtCodigoProduto.requestFocus();
                    } else {
                        try {
                            preencheTabelaCodEAN();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Erro ao Pesquisar Por Codigo EAN." + ex.getMessage());
                        }
                        jtId.setText(Integer.toString(objPro.getId()));
                        jtCodigoInterno.setText(Integer.toString(objPro.getCod_interno()));
                        jtDescricao.setText(objPro.getDescricao());
                        jtObservacao.setText(objPro.getObserv());
                        jtId.setEnabled(false);
                        jtCodigoInterno.setEnabled(false);
                        jtCodigoProduto.setEnabled(false);
                        jtDescricao.setEnabled(false);
                        jtObservacao.setEnabled(false);
                        jbEditar.setEnabled(true);
                        jbCancelar.setEnabled(true);
                        jbExcluir.setEnabled(true);
                        jbPesquisar.setEnabled(true);
                    }
                }
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(this, "Erro ao Pesquisar: " + erro.getMessage());
            }
        }
        if ("interno".equals(opcao)) {
            try {
                if (jtCodigoInterno.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Preencha o Campo Codigo Interno!");
                    jtCodigoInterno.requestFocus();
                } else {
                    objPro = DAOPro.PesquisarPorCodInterno(Integer.parseInt(jtCodigoInterno.getText()));
                    if (objPro == null) {
                        JOptionPane.showMessageDialog(this, "Não Foi Possível Encontrar o Registro!");
                        jtCodigoInterno.setText("");//Limpa ID
                        jtCodigoInterno.requestFocus();
                    } else {
                        try {
                            preencheTabelaCodInterno();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Erro ao Iniciar readTable." + ex.getMessage());
                        }
                        jtId.setText(Integer.toString(objPro.getId()));
                        jtCodigoProduto.setText(objPro.getCod_produto());
                        jtDescricao.setText(objPro.getDescricao());
                        jtObservacao.setText(objPro.getObserv());
                        jtId.setEnabled(false);
                        jtCodigoInterno.setEnabled(false);
                        jtCodigoProduto.setEnabled(false);
                        jtDescricao.setEnabled(false);
                        jtObservacao.setEnabled(false);
                        jbEditar.setEnabled(true);
                        jbCancelar.setEnabled(true);
                        jbExcluir.setEnabled(true);
                        jbPesquisar.setEnabled(true);
                    }
                }
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(this, "Erro ao Pesquisar: " + erro.getMessage());
            }
        }
    }

    public void preencheTabelaTodos() throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jtTabelaProdutos.getModel();
        modelo.setNumRows(0);
        ProdutoDAO func = new ProdutoDAO();
        for (Produto p : func.TabelaPesquisa()) {
            modelo.addRow(new Object[]{
                p.getCod_interno(), p.getCod_produto(), p.getDescricao(), p.getObserv()
            });
        }
    }

    public void preencheTabelaCodInterno() throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jtTabelaProdutos.getModel();
        modelo.setNumRows(0);
        for (Produto p : DAOPro.SelectPesquisaCodInternoLista(Integer.parseInt(jtCodigoInterno.getText()))) {
            modelo.addRow(new Object[]{
                p.getCod_interno(), p.getCod_produto(), p.getDescricao(), p.getObserv()
            });
        }
    }

    public void preencheTabelaCodEAN() throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jtTabelaProdutos.getModel();
        modelo.setNumRows(0);
        ProdutoDAO func = new ProdutoDAO();
        for (Produto p : func.SelectPesquisaCodProdutoLista(jtCodigoProduto.getText())) {
            modelo.addRow(new Object[]{
                p.getCod_interno(), p.getCod_produto(), p.getDescricao(), p.getObserv()
            });
        }
    }

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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btGrupo = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jpPainelCadastroDeProdutos = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtId = new javax.swing.JTextField();
        jtCodigoInterno = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jtDescricao = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtObservacao = new javax.swing.JTextField();
        jtCodigoProduto = new javax.swing.JTextField();
        jrCodigoEAN = new javax.swing.JRadioButton();
        jrCodigoInterno = new javax.swing.JRadioButton();
        jbPesquisar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtTabelaProdutos = new javax.swing.JTable();
        jbNovo = new javax.swing.JButton();
        jbEditar = new javax.swing.JButton();
        jbSalvar = new javax.swing.JButton();
        jbExcluir = new javax.swing.JButton();
        jbCancelar = new javax.swing.JButton();
        jbAtualizar = new javax.swing.JButton();
        jbSair = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jPanel2.setBackground(new java.awt.Color(0, 0, 204));

        jpPainelCadastroDeProdutos.setBackground(new java.awt.Color(0, 0, 204));
        jpPainelCadastroDeProdutos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 204, 0)), "Cadastro de Produtos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 1, 12), new java.awt.Color(255, 204, 0))); // NOI18N

        jLabel1.setText("ID");

        jtId.setEditable(false);
        jtId.setEnabled(false);

        jtCodigoInterno.setEnabled(false);
        jtCodigoInterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtCodigoInternoActionPerformed(evt);
            }
        });

        jLabel2.setText("Codigo Interno");

        jLabel7.setText("Código do Produto");

        jtDescricao.setEnabled(false);
        jtDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtDescricaoActionPerformed(evt);
            }
        });

        jLabel5.setText("Descrição");

        jLabel3.setText("Observação");

        jtObservacao.setEnabled(false);
        jtObservacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtObservacaoActionPerformed(evt);
            }
        });

        jtCodigoProduto.setEnabled(false);
        jtCodigoProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtCodigoProdutoActionPerformed(evt);
            }
        });

        btGrupo.add(jrCodigoEAN);
        jrCodigoEAN.setSelected(true);
        jrCodigoEAN.setText("Pesquisar Pelo Código EAN");
        jrCodigoEAN.setActionCommand("ean");
        jrCodigoEAN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jrCodigoEANMouseClicked(evt);
            }
        });

        btGrupo.add(jrCodigoInterno);
        jrCodigoInterno.setText("Pesquisar Pelo Código Interno");
        jrCodigoInterno.setActionCommand("interno");
        jrCodigoInterno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jrCodigoInternoMouseClicked(evt);
            }
        });

        jbPesquisar.setText("Pesquisar");
        jbPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpPainelCadastroDeProdutosLayout = new javax.swing.GroupLayout(jpPainelCadastroDeProdutos);
        jpPainelCadastroDeProdutos.setLayout(jpPainelCadastroDeProdutosLayout);
        jpPainelCadastroDeProdutosLayout.setHorizontalGroup(
            jpPainelCadastroDeProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPainelCadastroDeProdutosLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jpPainelCadastroDeProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpPainelCadastroDeProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpPainelCadastroDeProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jtDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                        .addComponent(jtId, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtObservacao))
                    .addGroup(jpPainelCadastroDeProdutosLayout.createSequentialGroup()
                        .addGroup(jpPainelCadastroDeProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpPainelCadastroDeProdutosLayout.createSequentialGroup()
                                .addComponent(jtCodigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jrCodigoEAN))
                            .addGroup(jpPainelCadastroDeProdutosLayout.createSequentialGroup()
                                .addComponent(jtCodigoInterno, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jrCodigoInterno)))
                        .addGap(30, 30, 30)
                        .addComponent(jbPesquisar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpPainelCadastroDeProdutosLayout.setVerticalGroup(
            jpPainelCadastroDeProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPainelCadastroDeProdutosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPainelCadastroDeProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jpPainelCadastroDeProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpPainelCadastroDeProdutosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpPainelCadastroDeProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jtCodigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jrCodigoEAN))
                        .addGap(10, 10, 10)
                        .addGroup(jpPainelCadastroDeProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jtCodigoInterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jrCodigoInterno))
                        .addGap(10, 10, 10)
                        .addGroup(jpPainelCadastroDeProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jpPainelCadastroDeProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jtObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpPainelCadastroDeProdutosLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jbPesquisar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(0, 0, 204));

        jtTabelaProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Codigo EAN", "Descrição", "Obs"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtTabelaProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtTabelaProdutosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtTabelaProdutos);
        if (jtTabelaProdutos.getColumnModel().getColumnCount() > 0) {
            jtTabelaProdutos.getColumnModel().getColumn(0).setMaxWidth(100);
            jtTabelaProdutos.getColumnModel().getColumn(1).setMinWidth(120);
            jtTabelaProdutos.getColumnModel().getColumn(1).setMaxWidth(130);
            jtTabelaProdutos.getColumnModel().getColumn(3).setMinWidth(200);
            jtTabelaProdutos.getColumnModel().getColumn(3).setMaxWidth(200);
        }

        jbNovo.setText("Novo");
        jbNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNovoActionPerformed(evt);
            }
        });

        jbEditar.setText("Editar");
        jbEditar.setEnabled(false);
        jbEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditarActionPerformed(evt);
            }
        });

        jbSalvar.setText("Salvar");
        jbSalvar.setEnabled(false);
        jbSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvarActionPerformed(evt);
            }
        });
        jbSalvar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jbSalvarKeyPressed(evt);
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

        jbAtualizar.setText("Listar Todos");
        jbAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAtualizarActionPerformed(evt);
            }
        });

        jbSair.setText("Sair");
        jbSair.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbSair, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbNovo)
                    .addComponent(jbEditar)
                    .addComponent(jbSalvar)
                    .addComponent(jbExcluir)
                    .addComponent(jbCancelar)
                    .addComponent(jbAtualizar)
                    .addComponent(jbSair))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpPainelCadastroDeProdutos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpPainelCadastroDeProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtCodigoInternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtCodigoInternoActionPerformed
        jtDescricao.requestFocus();
    }//GEN-LAST:event_jtCodigoInternoActionPerformed

    private void jtDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtDescricaoActionPerformed
        jtObservacao.requestFocus();
    }//GEN-LAST:event_jtDescricaoActionPerformed

    private void jtObservacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtObservacaoActionPerformed
        jbSalvar.requestFocus();
    }//GEN-LAST:event_jtObservacaoActionPerformed

    private void jtCodigoProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtCodigoProdutoActionPerformed
        jtCodigoInterno.requestFocus();
    }//GEN-LAST:event_jtCodigoProdutoActionPerformed

    private void jrCodigoEANMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jrCodigoEANMouseClicked
        opcao = btGrupo.getSelection().getActionCommand();
        jtCodigoProduto.setEnabled(true);
        jtCodigoInterno.setEnabled(false);
        jtCodigoProduto.requestFocus();
    }//GEN-LAST:event_jrCodigoEANMouseClicked

    private void jrCodigoInternoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jrCodigoInternoMouseClicked
        opcao = btGrupo.getSelection().getActionCommand();
        jtCodigoInterno.setEnabled(true);
        jtCodigoProduto.setEnabled(false);
        jtCodigoInterno.requestFocus();
    }//GEN-LAST:event_jrCodigoInternoMouseClicked

    private void jbPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPesquisarActionPerformed
        PesquisarItem();
    }//GEN-LAST:event_jbPesquisarActionPerformed

    private void jtTabelaProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtTabelaProdutosMouseClicked
        SelecionaNaTabela();
    }//GEN-LAST:event_jtTabelaProdutosMouseClicked

    private void jbNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNovoActionPerformed
        jtCodigoProduto.requestFocus();
        Novo();
    }//GEN-LAST:event_jbNovoActionPerformed

    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarActionPerformed
        jtCodigoProduto.requestFocus();
        Editar();
    }//GEN-LAST:event_jbEditarActionPerformed

    private void jbSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarActionPerformed
        SalvarDados();
    }//GEN-LAST:event_jbSalvarActionPerformed

    private void jbSalvarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jbSalvarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            SalvarDados();
        }
    }//GEN-LAST:event_jbSalvarKeyPressed

    private void jbExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExcluirActionPerformed
        try {
            if (jtCodigoInterno.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Preencha o Campo Matricula");
            } else {
                if (DAOPro.Delete(Integer.parseInt(jtCodigoInterno.getText()))) {
                    JOptionPane.showMessageDialog(this, "Registro Excluido com Sucesso");
                    limparCampos();
                    preencheTabelaTodos();
                } else {
                    JOptionPane.showMessageDialog(this, "Não Foi Possível Excluir o Registro");

                }
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(this, "Erro ao Excluir: " + erro.getMessage());
        }
    }//GEN-LAST:event_jbExcluirActionPerformed

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        Cancelar();
    }//GEN-LAST:event_jbCancelarActionPerformed

    private void jbAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAtualizarActionPerformed
        jbPesquisar.setEnabled(true);
        Carregando();
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    preencheTabelaTodos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao Iniciar readTable. " + ex.getMessage());
                }
                fechaCarregamento();
            }
        };
        t.start();
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
    }//GEN-LAST:event_jbAtualizarActionPerformed

    private void jbSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_jbSairActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btGrupo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbAtualizar;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JButton jbNovo;
    private javax.swing.JButton jbPesquisar;
    private javax.swing.JButton jbSair;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JPanel jpPainelCadastroDeProdutos;
    private javax.swing.JRadioButton jrCodigoEAN;
    private javax.swing.JRadioButton jrCodigoInterno;
    private javax.swing.JTextField jtCodigoInterno;
    private javax.swing.JTextField jtCodigoProduto;
    private javax.swing.JTextField jtDescricao;
    private javax.swing.JTextField jtId;
    private javax.swing.JTextField jtObservacao;
    private javax.swing.JTable jtTabelaProdutos;
    // End of variables declaration//GEN-END:variables
}
