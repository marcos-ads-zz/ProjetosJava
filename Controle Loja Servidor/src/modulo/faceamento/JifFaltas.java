package modulo.faceamento;

import java.awt.HeadlessException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modulo.produtos.ProdutoDAO;
import modulo.usuarios.UsuarioDAO;
import modulo.usuarios.Usuario;
import modulo.produtos.Produto;
import java.awt.Dimension;
import modulo.configuracoes.DadosConfig;
import modulo.metodos.Funcao;
import modulo.versao.Versao;
import modulo.view.principal.JfPrincipal;
import modulo.metodos.JifCarregamento;

/**
 *
 * @author Marcos Junior
 */
public final class JifFaltas extends javax.swing.JInternalFrame {

    private ListaDeRupturaDAO DAOLISTA;
    private ListaDeRuptura objLista;
    private JifCarregamento jfC = null;
    private UsuarioDAO DAOUSER;
    private Usuario objUSER;
    private ProdutoDAO DAOPRO;
    private Produto objPRO;
    private Funcao fun;
    DateFormat formatoHora;
    DateFormat formatoDIA;

    private Versao ver;
    private DadosConfig dadosLocal;
    private int acao;

    public JifFaltas() {
        initComponents();
        DAOUSER = new UsuarioDAO();
        DAOPRO = new ProdutoDAO();
        DAOLISTA = new ListaDeRupturaDAO();
        dadosLocal = new DadosConfig();
        fun = new Funcao();
        ver = new Versao();
        this.formatoHora = new SimpleDateFormat("EEEEEEEEEEEEEE, dd/MM/yyyy    HH:mm:ss");
        this.formatoDIA = new SimpleDateFormat("dd/MM/yyyy");
        setTitle("Cadastro de Faltas: " + ver.getVersao());
        jtDiasAPesquisar.setText("0");
        Thread relogioThred = new Thread(new JifFaltas.clsDataHora());
        relogioThred.start();
        CarregaLoja();
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    class clsDataHora implements Runnable {

        @Override
        public void run() {
            while (true) {
                MostraDataAtual();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println("Thread não foi finalizada:" + ex);
                }
            }
        }
    }

    public void limparCampo() {
        jtCod_EAN.setText("");
        jtCod_Interno.setText("");
        jtID.setText("");
        jtDescricaoItem.setText("");
        jtLancado.setText("");
        jtMatricula.setText("");
        jtNomeUsuario.setText("");
        jtQTD_Perdida.setText("");
        jtObservacao.setSelectedIndex(0);
    }

    public void Novo() {
        jtMatricula.setEnabled(true);
        jtCod_Interno.setEnabled(true);
        jtCod_EAN.setEnabled(true);
        jtDescricaoItem.setEnabled(false);
        jtLancado.setEnabled(true);
        jtNomeUsuario.setEnabled(false);
        jtQTD_Perdida.setEnabled(true);
        jtObservacao.setEnabled(true);
        jtDiasAPesquisar.setEnabled(true);
        jbNovo.setEnabled(false);
        jbSalvar.setEnabled(true);
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        jbPesquisar.setEnabled(true);
        jbCancelar.setEnabled(true);
        acao = 1;
    }

    public void Cancelar() {
        jtCod_Interno.setEnabled(false);
        jtCod_EAN.setEnabled(false);
        jtDescricaoItem.setEnabled(false);
        jtLancado.setEnabled(false);
        jtNomeUsuario.setEnabled(false);
        jtQTD_Perdida.setEnabled(false);
        jtObservacao.setEnabled(false);
        jtObservacao.setSelectedIndex(0);

        jlNomeUser.setVisible(true);
        jtMatricula.setVisible(true);
        jlMat.setVisible(true);
        jtNomeUsuario.setVisible(true);

        jbListar.setEnabled(true);
        jbPesquisar.setEnabled(true);
        jbNovo.setEnabled(true);
        jbSalvar.setEnabled(false);
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        jbCancelar.setEnabled(true);
        jtMatricula.requestFocus();
        acao = 0;
    }

    public void Salvar() {
        try {
            if (acao == 1) {
                if (validarCampoNovo()) {
                    //if (!PesquisaUltmos30Dias()) {
                    if (true) {
                        if (preencherObjetosSalvar()) {
                            if (DAOLISTA.Insert(objLista)) {
                                JOptionPane.showMessageDialog(this, "Salvo Com Sucesso!");
                                limparCampo();
                                Cancelar();
                                preencheTabela();
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Não Foi Possível Salvar!");
                        }
                    }
//                     else {
//                        JOptionPane.showMessageDialog(null, "Produto ja Cadastrado nos Últimos 30 Dias!");
//                    }
                }
            }
            if (acao == 2) {
                if (validarCampo()) {
                    if (preencherObjetosEditar()) {
                        if (DAOLISTA.Update(objLista)) {
                            JOptionPane.showMessageDialog(this, "Editado Com Sucesso!");
                            limparCampo();
                            Cancelar();
                            preencheTabela();
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

    public void Pesquisar() {
        if (validarCampoNovoRelatorio()) {
            try {
                PesquisaPorDataTodos();
                jbEditar.setEnabled(true);
                jbCancelar.setEnabled(true);
                jbExcluir.setEnabled(true);
                jbPesquisar.setEnabled(true);
            } catch (HeadlessException erro) {
                JOptionPane.showMessageDialog(this, "Erro ao Pesquisar: " + erro.getMessage());
            }
        } else if (!validarCampoNovoRelatorio()) {
            pesquisaPorDataEMatricula();
            jbEditar.setEnabled(true);
            jbCancelar.setEnabled(true);
            jbExcluir.setEnabled(true);
            jbPesquisar.setEnabled(true);

        } else {
            JOptionPane.showMessageDialog(this, "Erro ao Pesquisar!");
            jtMatricula.requestFocus();
        }
    }

    public void Editar() {
        jtMatricula.setEnabled(false);
        jtCod_Interno.setEnabled(true);
        jtCod_EAN.setEnabled(true);
        jtDescricaoItem.setEnabled(true);
        jtLancado.setEnabled(true);
        jtNomeUsuario.setEnabled(false);
        jtQTD_Perdida.setEnabled(true);
        jtObservacao.setEnabled(true);
        jbNovo.setEnabled(false);
        jbSalvar.setEnabled(true);
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);
        jbCancelar.setEnabled(true);
        acao = 2;
    }

    public void Excluir() {
        try {
            if (jtID.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Preencha o Campo ID");
            } else {
                if (DAOLISTA.Delete(Integer.parseInt(jtID.getText()))) {
                    JOptionPane.showMessageDialog(this, "Registro Excluido com Sucesso");
                    limparCampo();

                    preencheTabela();
                } else {
                    JOptionPane.showMessageDialog(this, "Não Foi Possível Excluir o Registro");

                }
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(this, "Erro ao Excluir: " + erro.getMessage());
        }
    }

    public void Listar() {
        Carregando();
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    preencheTabela();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao Iniciar readTable." + ex.getMessage());
                }
                fechaCarregamento();
            }
        };
        t.start();

        jbEditar.setEnabled(true);
        jbExcluir.setEnabled(true);
    }

    public void CarregaMatricula() {
        try {
            objUSER = DAOUSER.PesquisaPorMatricula(Integer.parseInt(jtMatricula.getText()));
            if (objUSER == null || jtMatricula.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Não Foi Possível Encontrar o Registro!");
                jtMatricula.setText("");//Limpa ID
                jtMatricula.requestFocus();
            } else {
                jtNomeUsuario.setText(objUSER.getNome());
                jtCod_EAN.requestFocus();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Iniciar readTable Matricula." + ex.getMessage());
        }
    }

    public void CarregaEAN() throws Exception {
        if (!DAOPRO.CheckSelectEAN(jtCod_EAN.getText()) || jtCod_EAN.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Produto Não Cadastrado!");
            jtCod_EAN.setText("");
            jtCod_Interno.requestFocus();
        } else {
            objPRO = DAOPRO.PesquisarPorCodProduto(jtCod_EAN.getText());
            jtDescricaoItem.setText(objPRO.getDescricao());
            jtCod_Interno.setText(Integer.toString(objPRO.getCod_interno()));
            jtObservacao.requestFocus();
            jtCod_Interno.setEnabled(false);
        }
    }

    public void CarregaLoja() {
        try {
            jtLoja.setText(dadosLocal.getLoja());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Iniciar readTable Matricula." + ex.getMessage());
        }
    }

    public boolean validarCampoNovo() {
        if (jtCod_Interno.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Código Interno!");
            jtMatricula.requestFocus();
            return false;
        }
        if (jtMatricula.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Matrícula!");
            jtMatricula.requestFocus();
            return false;
        }
        if (jtDescricaoItem.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Campo Descrição Não Pode Ser Vazio!");
            jtCod_Interno.requestFocus();
            return false;
        }
        if (jtQTD_Perdida.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Quantidade Perdida!");
            jtQTD_Perdida.requestFocus();
            return false;
        }
        if (jtLancado.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Lançado!");
            jtLancado.requestFocus();
            return false;
        }
        if (jtObservacao.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma observação!");
            jtObservacao.requestFocus();
            return false;
        }
        return true;
    }

    public void carregarCodigoInterno() throws Exception {
        if (!DAOPRO.CheckSelectCod_Interno(Integer.parseInt(jtCod_Interno.getText())) || jtCod_Interno.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Produto Não Cadastrado!");
            jtCod_Interno.setText("");//Limpa ID
            jtCod_Interno.requestFocus();
        } else {
            objPRO = DAOPRO.PesquisarPorCodInterno(Integer.parseInt(jtCod_Interno.getText()));
            jtDescricaoItem.setText(objPRO.getDescricao());
            jtCod_EAN.setText(objPRO.getCod_produto());
            jtObservacao.requestFocus();
            jtCod_EAN.setEnabled(false);
        }
    }

    public boolean validarCampoNovoRelatorio() {
        boolean chek = false;
        if (jtMatricula.getText().equals("") || jtMatricula.getText().trim().equals("")) {
            return true;
        }
        return chek;
    }

    public boolean validarCampo2() {
        if (jtMatricula.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Matrícula!");
            jtMatricula.requestFocus();
            return false;
        }
        return true;
    }

    public boolean validarCampo() {
        if (jtMatricula.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Matrícula!");
            jtMatricula.requestFocus();
            return false;
        }
        if (jtDescricaoItem.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Descrição!");
            jtCod_Interno.requestFocus();
            return false;
        }
        if (jtQTD_Perdida.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Quantidade Perdida!");
            jtQTD_Perdida.requestFocus();
            return false;
        }
        if (jtLancado.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Lançado!");
            jtLancado.requestFocus();
            return false;
        }
        return true;
    }

    public boolean preencherObjetosSalvar() {
        objLista = new ListaDeRuptura();
        objLista.setCod_interno(Integer.parseInt(jtCod_Interno.getText()));
        objLista.setCod_produto(jtCod_EAN.getText());
        objLista.setData(fun.atualDateSQL());
        objLista.setDescricao(jtDescricaoItem.getText());
        objLista.setLancado(Integer.parseInt(jtLancado.getText()));
        objLista.setMatricula(Integer.parseInt(jtMatricula.getText()));
        objLista.setObservacao(jtObservacao.getSelectedItem().toString());
        objLista.setQtd_perdida(Integer.parseInt(jtQTD_Perdida.getText()));
        return true;
    }

    public boolean preencherObjetosEditar() {
        objLista = new ListaDeRuptura();
        objLista.setId(Integer.parseInt(jtID.getText()));
        objLista.setCod_interno(Integer.parseInt(jtCod_Interno.getText()));
        objLista.setCod_produto(jtCod_EAN.getText());
        objLista.setData(fun.atualDateSQL());
        objLista.setDescricao(jtDescricaoItem.getText());
        objLista.setLancado(Integer.parseInt(jtLancado.getText()));
        objLista.setMatricula(Integer.parseInt(jtMatricula.getText()));
        objLista.setObservacao(jtObservacao.getSelectedItem().toString());
        objLista.setQtd_perdida(Integer.parseInt(jtQTD_Perdida.getText()));

        return true;
    }

    public void preencheTabela() throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jtTabela.getModel();
        modelo.setNumRows(0);
        ListaDeRupturaDAO listaR = new ListaDeRupturaDAO();
        for (ListaDeRuptura p : listaR.TabelaPesquisa()) {
            modelo.addRow(new Object[]{
                p.getId(), p.getCod_interno(), p.getCod_produto(), p.getDescricao(),
                p.getQtd_perdida(), p.getLancado(), p.getMatricula(), p.getData(), p.getObservacao()
            });

        }
    }

    public void preencheTabela2() throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jtTabela.getModel();
        modelo.setNumRows(0);
        ListaDeRupturaDAO listaR = new ListaDeRupturaDAO();
        for (ListaDeRuptura p : listaR.TabelaPesquisadaPorID(Integer.parseInt(jtID.getText()))) {
            modelo.addRow(new Object[]{
                p.getId(), p.getCod_interno(), p.getCod_produto(), p.getDescricao(),
                p.getQtd_perdida(), p.getLancado(), p.getMatricula(), p.getData(), p.getObservacao()
            });
        }
    }

    public void MostraDataAtual() {
        jtDataDoRegistro.setText("Data Fim: " + fun.atualDateSQL());
    }

    public boolean PesquisaUltmos30Dias() {
        boolean chek = false;
        int cod_int = Integer.parseInt(jtCod_Interno.getText());
        try {
            chek = DAOLISTA.CheckSelect(cod_int, fun.dataAtualDias(), fun.atualDateSQL());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Carregar Data!" + ex);
        }
        return chek;
    }

    public void pesquisaPorDataEMatricula() {
        int valor = Integer.parseInt(jtDiasAPesquisar.getText());
        int matric = Integer.parseInt(jtMatricula.getText());
        jtData2.setText("Data Inicio: " + fun.dataIniciosDias(valor).toString());
        DefaultTableModel modelo = (DefaultTableModel) jtTabela.getModel();
        modelo.setNumRows(0);
        try {
            for (ListaDeRuptura p : DAOLISTA.SelectMatriculaDataInicioEFim(matric, fun.atualDateSQL(), fun.dataIniciosDias(valor))) {
                modelo.addRow(new Object[]{
                    p.getId(), p.getCod_interno(), p.getCod_produto(), p.getDescricao(),
                    p.getQtd_perdida(), p.getLancado(), p.getMatricula(), p.getData(), p.getObservacao()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro na pesquisa! " + ex.getMessage());
        }
    }

    public void PesquisaPorDataTodos() {
        int valor = Integer.parseInt(jtDiasAPesquisar.getText());
        DefaultTableModel modelo = (DefaultTableModel) jtTabela.getModel();
        modelo.setNumRows(0);
        jtData2.setText("Data Inicio: " + fun.dataIniciosDias(valor).toString());
        try {
            for (ListaDeRuptura p : DAOLISTA.SelectDataInicioEFim(fun.dataIniciosDias(valor), fun.atualDateSQL())) {
                modelo.addRow(new Object[]{
                    p.getId(), p.getCod_interno(), p.getCod_produto(), p.getDescricao(),
                    p.getQtd_perdida(), p.getLancado(), p.getMatricula(), p.getData(), p.getObservacao()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro na pesquisa! " + ex.getMessage());

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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtCod_EAN = new javax.swing.JTextField();
        jtCod_Interno = new javax.swing.JTextField();
        jbNovo = new javax.swing.JButton();
        jbSalvar = new javax.swing.JButton();
        jbEditar = new javax.swing.JButton();
        jbExcluir = new javax.swing.JButton();
        jbCancelar = new javax.swing.JButton();
        jbPesquisar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtTabela = new javax.swing.JTable();
        jbListar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jlMat = new javax.swing.JLabel();
        jtDescricaoItem = new javax.swing.JTextField();
        jtMatricula = new javax.swing.JTextField();
        jlNomeUser = new javax.swing.JLabel();
        jtNomeUsuario = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jtQTD_Perdida = new javax.swing.JTextField();
        jtLancado = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jtID = new javax.swing.JTextField();
        jtDataDoRegistro = new javax.swing.JTextField();
        jlDataPesquisa = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtLoja = new javax.swing.JTextField();
        jtDiasAPesquisar = new javax.swing.JTextField();
        jtData2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtObservacao = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));

        jLabel1.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 255, 0));
        jLabel1.setText("Codigo EAN");

        jLabel5.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 255, 0));
        jLabel5.setText("Codigo Interno");

        jtCod_EAN.setEnabled(false);
        jtCod_EAN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtCod_EANActionPerformed(evt);
            }
        });

        jtCod_Interno.setEnabled(false);
        jtCod_Interno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtCod_InternoActionPerformed(evt);
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
                "ID", "CODIGO", "CODIGO EAN", "DESCRIÇÃO", "QTD PERDIDA", "LANÇADO", "MATRÍCULA", "DATA", "OBS"
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
        jScrollPane2.setViewportView(jtTabela);
        if (jtTabela.getColumnModel().getColumnCount() > 0) {
            jtTabela.getColumnModel().getColumn(0).setMinWidth(60);
            jtTabela.getColumnModel().getColumn(0).setMaxWidth(60);
            jtTabela.getColumnModel().getColumn(1).setMinWidth(60);
            jtTabela.getColumnModel().getColumn(1).setMaxWidth(60);
            jtTabela.getColumnModel().getColumn(2).setMinWidth(100);
            jtTabela.getColumnModel().getColumn(2).setMaxWidth(100);
            jtTabela.getColumnModel().getColumn(4).setMinWidth(100);
            jtTabela.getColumnModel().getColumn(4).setMaxWidth(100);
            jtTabela.getColumnModel().getColumn(5).setMinWidth(70);
            jtTabela.getColumnModel().getColumn(5).setMaxWidth(70);
            jtTabela.getColumnModel().getColumn(6).setMinWidth(80);
            jtTabela.getColumnModel().getColumn(6).setMaxWidth(80);
            jtTabela.getColumnModel().getColumn(7).setMinWidth(90);
            jtTabela.getColumnModel().getColumn(7).setMaxWidth(90);
        }

        jbListar.setText("Listar Todos");
        jbListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbListarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 255, 0));
        jLabel2.setText("Descrição");

        jlMat.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jlMat.setForeground(new java.awt.Color(204, 255, 0));
        jlMat.setText("Matrícula");

        jtDescricaoItem.setEnabled(false);
        jtDescricaoItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtDescricaoItemActionPerformed(evt);
            }
        });

        jtMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtMatriculaActionPerformed(evt);
            }
        });

        jlNomeUser.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jlNomeUser.setForeground(new java.awt.Color(204, 255, 0));
        jlNomeUser.setText("Nome");

        jtNomeUsuario.setEditable(false);
        jtNomeUsuario.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 255, 0));
        jLabel7.setText("Qtd Perdida");

        jLabel8.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 255, 0));
        jLabel8.setText("Lançado");

        jtQTD_Perdida.setEnabled(false);
        jtQTD_Perdida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtQTD_PerdidaActionPerformed(evt);
            }
        });

        jtLancado.setEnabled(false);
        jtLancado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtLancadoActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 255, 0));
        jLabel9.setText("Observação");

        jLabel11.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 255, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("ID");

        jtID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtID.setEnabled(false);

        jtDataDoRegistro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtDataDoRegistro.setEnabled(false);

        jlDataPesquisa.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jlDataPesquisa.setForeground(new java.awt.Color(204, 255, 0));
        jlDataPesquisa.setText("Últimos");

        jLabel3.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 255, 0));
        jLabel3.setText("Loja");

        jtLoja.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtLoja.setEnabled(false);

        jtData2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtData2.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 255, 0));
        jLabel4.setText("Dias");

        jtObservacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione Uma Observação", "Falta", "Deixei de Vender", "Desativado", "Muita Procura", "Encomenda" }));
        jtObservacao.setEnabled(false);
        jtObservacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtObservacaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlDataPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jtQTD_Perdida, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jtDiasAPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel4))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtLancado, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jtID, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jtData2, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                            .addComponent(jtDataDoRegistro)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlMat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtCod_EAN, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtCod_Interno, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                                    .addComponent(jtMatricula))
                                .addGap(11, 11, 11)
                                .addComponent(jlNomeUser)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jtDescricaoItem, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)
                                    .addComponent(jtLoja, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jtObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jbNovo)
                        .addGap(18, 18, 18)
                        .addComponent(jbSalvar)
                        .addGap(18, 18, 18)
                        .addComponent(jbEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(jbListar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(286, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlMat)
                    .addComponent(jtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlNomeUser)
                    .addComponent(jtNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtCod_Interno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtCod_EAN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtDescricaoItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jtObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtLoja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtQTD_Perdida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtData2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jtLancado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtDataDoRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlDataPesquisa)
                    .addComponent(jtDiasAPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbCancelar)
                    .addComponent(jbExcluir)
                    .addComponent(jbListar)
                    .addComponent(jbPesquisar)
                    .addComponent(jbEditar)
                    .addComponent(jbSalvar)
                    .addComponent(jbNovo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addGap(9, 9, 9))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtCod_EANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtCod_EANActionPerformed

        try {
            CarregaEAN();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Carregar EAN! " + ex.getMessage());
            jtCod_Interno.requestFocus();
        }
    }//GEN-LAST:event_jtCod_EANActionPerformed

    private void jtCod_InternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtCod_InternoActionPerformed
        try {
            carregarCodigoInterno();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Carregar Codigo Interno! " + ex.getMessage());
        }
    }//GEN-LAST:event_jtCod_InternoActionPerformed

    private void jbNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNovoActionPerformed
        limparCampo();
        Novo();
        jtMatricula.requestFocus();
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
        limparCampo();
        Cancelar();
    }//GEN-LAST:event_jbCancelarActionPerformed

    private void jbPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPesquisarActionPerformed
        Pesquisar();
    }//GEN-LAST:event_jbPesquisarActionPerformed

    private void jtTabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtTabelaMouseClicked
        Object id0, cod_inter, cod_prod, descricao, qtd_perdida, lancado, matricula, observacao;
        try {
            if (jtTabela.getSelectedRow() != -1) {

                id0 = jtTabela.getValueAt(jtTabela.getSelectedRow(), 0);
                if (id0 == null) {
                    jtID.setText("");
                } else {
                    String id = id0.toString();
                    jtID.setText(id);
                }

                cod_inter = jtTabela.getValueAt(jtTabela.getSelectedRow(), 1);

                if (cod_inter == null) {
                    jtCod_Interno.setText("");
                } else {
                    String codI = cod_inter.toString();
                    jtCod_Interno.setText(codI);
                }

                cod_prod = jtTabela.getValueAt(jtTabela.getSelectedRow(), 2);
                if (cod_prod == null) {
                    jtCod_EAN.setText("");
                } else {
                    String codP = cod_prod.toString();
                    jtCod_EAN.setText(codP);
                }

                descricao = jtTabela.getValueAt(jtTabela.getSelectedRow(), 3);
                if (descricao == null) {
                    jtDescricaoItem.setText("");
                } else {
                    String descri = descricao.toString();
                    jtDescricaoItem.setText(descri);
                }

                qtd_perdida = jtTabela.getValueAt(jtTabela.getSelectedRow(), 4);
                if (qtd_perdida == null) {
                    jtQTD_Perdida.setText("");
                } else {
                    String gt_p = qtd_perdida.toString();
                    jtQTD_Perdida.setText(gt_p);
                }
                lancado = jtTabela.getValueAt(jtTabela.getSelectedRow(), 5);
                if (lancado == null) {
                    jtLancado.setText("");
                } else {
                    String lanc = lancado.toString();
                    jtLancado.setText(lanc);
                }

                matricula = jtTabela.getValueAt(jtTabela.getSelectedRow(), 6);
                if (matricula == null) {
                    jtMatricula.setText("");
                } else {
                    String mat = matricula.toString();
                    jtMatricula.setText(mat);
                    try {
                        objUSER = DAOUSER.PesquisaPorMatricula(Integer.parseInt(jtMatricula.getText()));
                        if (objUSER == null || jtMatricula.getText().equals("")) {
                            JOptionPane.showMessageDialog(this, "Matrícula Não Encontrada!");
                        } else {
                            jtNomeUsuario.setText(objUSER.getNome());
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao Iniciar readTable Matricula." + ex.getMessage());
                    }
                }
                jbEditar.setEnabled(true);
                jbExcluir.setEnabled(true);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(this, "Erro na Tabela: " + e);
        }
    }//GEN-LAST:event_jtTabelaMouseClicked

    private void jbListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbListarActionPerformed
        Listar();
    }//GEN-LAST:event_jbListarActionPerformed

    private void jtDescricaoItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtDescricaoItemActionPerformed
        jtObservacao.requestFocus();
    }//GEN-LAST:event_jtDescricaoItemActionPerformed

    private void jtMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtMatriculaActionPerformed
        CarregaMatricula();
    }//GEN-LAST:event_jtMatriculaActionPerformed

    private void jtQTD_PerdidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtQTD_PerdidaActionPerformed
        jtLancado.requestFocus();
    }//GEN-LAST:event_jtQTD_PerdidaActionPerformed

    private void jtLancadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtLancadoActionPerformed
        jbSalvar.requestFocus();
    }//GEN-LAST:event_jtLancadoActionPerformed

    private void jtObservacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtObservacaoActionPerformed
        jbSalvar.requestFocus();
    }//GEN-LAST:event_jtObservacaoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JButton jbListar;
    private javax.swing.JButton jbNovo;
    private javax.swing.JButton jbPesquisar;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JLabel jlDataPesquisa;
    private javax.swing.JLabel jlMat;
    private javax.swing.JLabel jlNomeUser;
    private javax.swing.JTextField jtCod_EAN;
    private javax.swing.JTextField jtCod_Interno;
    private javax.swing.JTextField jtData2;
    private javax.swing.JTextField jtDataDoRegistro;
    private javax.swing.JTextField jtDescricaoItem;
    private javax.swing.JTextField jtDiasAPesquisar;
    private javax.swing.JTextField jtID;
    private javax.swing.JTextField jtLancado;
    private javax.swing.JTextField jtLoja;
    private javax.swing.JTextField jtMatricula;
    private javax.swing.JTextField jtNomeUsuario;
    private javax.swing.JComboBox<String> jtObservacao;
    private javax.swing.JTextField jtQTD_Perdida;
    private javax.swing.JTable jtTabela;
    // End of variables declaration//GEN-END:variables
}
