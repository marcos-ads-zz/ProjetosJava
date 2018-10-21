package modulo.troca;

import modulo.clientes.ClienteDAO;
import modulo.loja.LojaDAO;
import modulo.produtos.ProdutoDAO;
import modulo.faceamento.TrocasDAO;
import modulo.usuarios.UsuarioDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modulo.login.UserLogado;
import modulo.loja.Loja;
import modulo.produtos.Produto;
import modulo.usuarios.Usuario;
import modulo.versao.Versao;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modulo.clientes.Cliente;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import modulo.metodos.CryptoBase64;
import modulo.metodos.Funcao;

/**
 *
 * @author Marcos Junior
 */
public final class JifTrocas extends javax.swing.JInternalFrame {

    private UsuarioDAO DAOUSER;
    private Usuario objUSERVendedor;
    private Usuario objUSERCaixa;
    private ProdutoDAO DAOPRO;
    private Produto objPRO;
    private Produto objPRODeixado;
    private Produto objPROLevado;
    private ClienteDAO DAOCLI;
    private Cliente objCli;
    private CryptoBase64 cry;
    private TrocasDAO DAOTROCA;
    private Trocas objTroca;
    private LojaDAO DAOLOJA;
    private Loja objLOJAregistro;
    private Loja objLOJACupom;
    private Funcao fun;
    private UserLogado login;
    private List<ItensLevados> listaLevados;
    private List<ItensDeixados> listaDeixados;
    private Versao ver;
    private int xy;
    private int xx;

    public JifTrocas() {
        initComponents();
        fun = new Funcao();
        login = new UserLogado();
        cry = new CryptoBase64();
        DAOUSER = new UsuarioDAO();
        DAOLOJA = new LojaDAO();
        DAOPRO = new ProdutoDAO();
        DAOTROCA = new TrocasDAO();
        DAOCLI = new ClienteDAO();
        ver = new Versao();
        listaLevados = new ArrayList<>();
        listaDeixados = new ArrayList<>();
        jtMatriculaVendedor.requestFocus();
        setTitle("Troca e Devolução: " + ver.getVersao());
        Thread relogioThred = new Thread(new JifTrocas.clsDataHora());
        relogioThred.start();
        jbCarregarDados.setVisible(false);
        jtIdPesquisa.setVisible(false);
        AutoCompleteDecorator.decorate(jtTipo);
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

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    public void SetValores() {
        jtDataDaTroca.setText(fun.dataAtualDate());
        jtHoraDaTroca.setText(fun.horaAtualDate());
        jtLojaDaRegistro.setText(Integer.toString(login.getNumeroloja()));
        carregaLojaDaRegistro();
    }

    public void LimparCampos() {
        jtMatriculaVendedor.setText("");
        jtMatriculaCaixa.setText("");
        jtNomeVendedor.setText("");
        jtNomeCaixa.setText("");
        jtNumeroCupom.setText("");
        jtNumeroPDV.setText("");
        jtDataCupom.setText("");
        jtLojaDoCupom.setText("");
        jtNomeLoja.setText("");
        jtNomeCliente.setText("");
        jtEnderecoCliente.setText("");
        jtTelefoneCli.setText("");
        jtMotivoDaTroca.setText("");
        jtId.setText("");
        jbCarregarDados.setVisible(false);
        jtIdPesquisa.setVisible(false);
    }

    public void BloqueiaCampos() {
        boolean g = false;
        jtMatriculaVendedor.setEditable(g);
        jtMatriculaCaixa.setEditable(g);
        jtNumeroCupom.setEditable(g);
        jtNumeroPDV.setEditable(g);
        jtDataCupom.setEditable(g);
        jtLojaDoCupom.setEditable(g);
        jtNomeCliente.setEditable(g);
        jtEnderecoCliente.setEditable(g);
        jtTelefoneCli.setEditable(g);
        jtMotivoDaTroca.setEditable(g);
    }

    public void LiberaCampos() {
        boolean g = true;
        jtMatriculaVendedor.setEditable(g);
        jtMatriculaCaixa.setEditable(g);
        jtNumeroCupom.setEditable(g);
        jtNumeroPDV.setEditable(g);
        jtDataCupom.setEditable(g);
        jtLojaDoCupom.setEditable(g);
        jtNomeCliente.setEditable(g);
        jtEnderecoCliente.setEditable(g);
        jtTelefoneCli.setEditable(g);
        jtMotivoDaTroca.setEditable(g);
    }

    public void ResetaCampos() {
        if (jtMatriculaVendedor.getText().equals("")) {
            jtMatriculaVendedor.setText("AUTORIZADOR");
            jtNomeVendedor.setText("NOME AUTORIZADOR");
        }
        if (jtMatriculaCaixa.getText().equals("")) {
            jtMatriculaCaixa.setText("CAIXA");
            jtNomeCaixa.setText("NOME CAIXA");
        }
        if (jtNumeroCupom.getText().equals("")) {
            jtNumeroCupom.setText("Nº CUPOM");
        }
        if (jtNumeroPDV.getText().equals("")) {
            jtNumeroPDV.setText("Nº PDV");
        }
        if (jtDataCupom.getText().equals("")) {
            jtDataCupom.setText("DATA CUPOM");
        }
        if (jtLojaDoCupom.getText().equals("")) {
            jtNomeLoja.setText("NOME DA LOJA");
            jtLojaDoCupom.setText("Nº LOJA");
        }
        if (jtNomeCliente.getText().equals("")) {
            jtNomeCliente.setText("NOME DO CLIENTE");
        }
        if (jtEnderecoCliente.getText().equals("")) {
            jtEnderecoCliente.setText("ENDEREÇO DO CLIENTE");
        }
        if (jtTelefoneCli.getText().equals("")) {
            jtTelefoneCli.setText("TELEFONE");
        }
        if (jtMotivoDaTroca.getText().equals("")) {
            jtMotivoDaTroca.setText("MOTIVO DA TROCA OU DEVOLUÇÃO");
        }
        if (jtId.getText().equals("")) {
            jtId.setText("ID");
        }
    }

    public boolean NumeroCupom() {
        boolean check = false;
        if (!fun.testaNumerosInteiros(jtNumeroCupom.getText())) {
            jtNumeroCupom.setText("");
        } else {
            check = true;
        }
        return check;
    }

    public boolean NumeroPDV() {
        boolean check = false;
        if (!fun.testaNumerosInteiros(jtNumeroPDV.getText())) {
            jtNumeroPDV.setText("");
        } else {
            check = true;
        }
        return check;
    }

    public boolean NumeroLoja() {
        boolean check = false;
        if (!fun.testaNumerosInteiros(jtLojaDoCupom.getText())) {
            jtLojaDoCupom.setText("");
        } else {
            check = true;
        }
        return check;
    }

    public boolean VerificaItensLevados(int id) {
        boolean check = true;
        for (ItensLevados a : listaLevados) {
            if (a.getId_produto() == id) {
                check = false;
            }
        }
        return check;
    }

    public boolean VerificaItensDeixados(int id) {
        boolean check = true;
        for (ItensDeixados a : listaDeixados) {
            if (a.getId_produto() == id) {
                check = false;
            }
        }
        return check;
    }

    public void carregaVendedor() {
        if ("AUTORIZADOR".equals(jtMatriculaVendedor.getText())) {
            jtMatriculaVendedor.setText("");
        }
        try {
            objUSERVendedor = DAOUSER.PesquisaPorMatricula(Integer.parseInt(jtMatriculaVendedor.getText()));
            if (objUSERVendedor == null || jtMatriculaVendedor.getText().equals("")) {
                jtNomeVendedor.setText("Não localizado!");
                jtMatriculaVendedor.requestFocus();
            } else {
                jtNomeVendedor.setText(objUSERVendedor.getNome());
                jtMatriculaCaixa.requestFocus();
                jtMatriculaCaixa.setText("");
            }
        } catch (Exception ex) {
            jtNomeVendedor.setText("Erro: " + ex.getMessage());
        }
    }

    public void carregaCaixa() {
        try {
            objUSERCaixa = DAOUSER.PesquisaPorMatricula(Integer.parseInt(jtMatriculaCaixa.getText()));
            if (objUSERCaixa == null || jtMatriculaCaixa.getText().equals("")) {
                jtNomeCaixa.setText("Não localizado!");
                jtMatriculaCaixa.requestFocus();
            } else {
                jtNomeCaixa.setText(objUSERCaixa.getNome());
                jtNumeroCupom.requestFocus();
            }
        } catch (Exception ex) {
            jtNomeCaixa.setText("Erro: " + ex.getMessage());
        }
    }

    public void carregaProdutosLevados() {
        try {
            objPROLevado = DAOPRO.PesquisarPorCodInterno(Integer.parseInt(jtCodProdutos.getText()));
            if (objPROLevado == null || jtCodProdutos.getText().equals("")) {
                jtDescricaoProduto.setText("Não localizado!");
                jtCodProdutos.requestFocus();
            } else {
                jtDescricaoProduto.setText(objPROLevado.getDescricao());
                jtQuantidadeProduto.requestFocus();
            }
        } catch (Exception ex) {
            jtDescricaoProduto.setText("Erro: " + ex.getMessage());
        }
    }

    public void carregaProdutosDeixados() {
        try {
            objPRODeixado = DAOPRO.PesquisarPorCodInterno(Integer.parseInt(jtCodProdutos.getText()));
            if (objPRODeixado == null || jtCodProdutos.getText().equals("")) {
                jtDescricaoProduto.setText("Não localizado!");
                jtCodProdutos.requestFocus();
            } else {
                jtDescricaoProduto.setText(objPRODeixado.getDescricao());
                jtQuantidadeProduto.requestFocus();
            }
        } catch (Exception ex) {
            jtDescricaoProduto.setText("Erro: " + ex.getMessage());
        }
    }

    public void TestaItem() {
        String opcao = GrupoDeRadioButoes.getSelection().getActionCommand();

        if (opcao.equals("deixado")) {
            try {
                carregaProdutosDeixados();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao Incluir: " + ex.getMessage());
            }
        } else if (opcao.equals("levado")) {
            try {
                carregaProdutosLevados();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao Incluir: " + ex.getMessage());
            }
        }
    }

    public void carregaLojaDoCupom() {
        try {
            objLOJACupom = DAOLOJA.PesquisaNumeroLoja(Integer.parseInt(jtLojaDoCupom.getText()));
            if (objLOJACupom == null || jtLojaDoCupom.getText().equals("")) {
                jtNomeLoja.setText("Não localizado!");
                jtLojaDoCupom.requestFocus();
            } else {
                jtNomeLoja.setText(objLOJACupom.getNome_loja());
                jtNomeCliente.requestFocus();
                jtNomeCliente.setText("");
            }
        } catch (Exception ex) {
            jtNomeLoja.setText("Erro: " + ex.getMessage());
        }
    }

    public void carregaLojaDaRegistro() {
        try {
            objLOJAregistro = DAOLOJA.PesquisaNumeroLoja(Integer.parseInt(Integer.toString(login.getNumeroloja())));
            if (objLOJAregistro == null || jtLojaDaRegistro.getText().equals("")) {
                jtNomeLojaDaTroca.setText("Não localizado!");
            } else {
                jtNomeLojaDaTroca.setText(objLOJAregistro.getNome_loja());
            }
        } catch (Exception ex) {
            jtNomeLojaDaTroca.setText("Erro: " + ex.getMessage());
        }
    }

    public void RemoveDeixados(int id) throws Exception {
        for (int i = 0; i < listaDeixados.size(); i++) {
            ItensDeixados p = listaDeixados.get(i);
            if (p.getId_produto() == id) {
                listaDeixados.remove(p);
                // Sai do loop.
                PreencheTabelaDaViewDeixados();
                ContaTotal();
                break;
            }
        }
        ContaTotal();
    }

    public void RemoveLevados(int id) throws Exception {
        for (int i = 0; i < listaLevados.size(); i++) {
            ItensLevados p = listaLevados.get(i);
            if (p.getId_produto() == id) {
                listaLevados.remove(p);
                PreencheTabelaDaViewLevados();
                ContaTotal();
                break;
            }
        }
        ContaTotal();
    }

    public void ContaTotal() {
        if (listaDeixados.size() >= 1) {
            listaDeixados.forEach((a) -> {
                jtValorTotalDeixados.setText(Double.toString(a.getValorTotalDeixado() * listaDeixados.size()));
            });
        } else {
            jtValorTotalDeixados.setText("0");
        }
        if (listaLevados.size() >= 1) {
            listaLevados.forEach((a) -> {
                jtValorTotalLevados.setText(Double.toString(a.getValorTotalLevados() * listaLevados.size()));
            });
        } else {
            jtValorTotalLevados.setText("0");
        }
    }

    public List<ItensDeixados> IncluiItensDeixados() throws ParseException {
        ItensDeixados itens = new ItensDeixados();
        itens.setId_produto(objPRODeixado.getId());
        itens.setDate_cupom(fun.convertDateStringToDateSQL(jtDataCupom.getText()));
        itens.setDate_registro(fun.atualDateSQL());
        int qtd = Integer.parseInt(jtQuantidadeProduto.getText());
        itens.setQuatidade(qtd);
        String x = jtValorProduto.getText();
        x = x.replaceAll("R|\\$|\\.", "").replaceAll("\\.", ".");
        x = x.replace(',', '.');
        double und = Double.parseDouble(x);
        itens.setValorUnitario(und);
        double total = und * qtd;
        itens.setValorTotalDeixado(total);
        listaDeixados.add(itens);
        ContaTotal();
        return listaDeixados;
    }

    public List<ItensLevados> IncluiItensLevados() throws ParseException {
        ItensLevados itens = new ItensLevados();
        itens.setId_produto(objPROLevado.getId());
        itens.setDate_cupom(fun.convertDateStringToDateSQL(jtDataCupom.getText()));
        itens.setDate_registro(fun.atualDateSQL());
        int qtd = Integer.parseInt(jtQuantidadeProduto.getText());
        itens.setQuatidade(qtd);
        String x = jtValorProduto.getText();
        x = x.replaceAll("R|\\$|\\.", "").replaceAll("\\.", ".");
        x = x.replace(',', '.');
        double und = Double.parseDouble(x);
        itens.setValorUnitario(und);
        double total = und * qtd;
        itens.setValorTotalLevados(total);
        listaLevados.add(itens);
        ContaTotal();
        return listaLevados;
    }

    public void PreencheTabelaDaViewLevados() throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jtTableLevados.getModel();
        modelo.setNumRows(0);
        listaLevados.forEach((a) -> {
            try {
                modelo.addRow(new Object[]{
                    DAOPRO.PesquisarPorId(a.getId_produto()).getCod_interno(),
                    DAOPRO.PesquisarPorId(a.getId_produto()).getDescricao(),
                    a.getQuatidade(),
                    a.getValorUnitario(),
                    a.getValorTotalLevados()
                });
            } catch (Exception ex) {
                Logger.getLogger(JifTrocas.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void PreencheTabelaDaViewDeixados() throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jtTableDeixados.getModel();
        modelo.setNumRows(0);
        listaDeixados.forEach((a) -> {
            try {
                modelo.addRow(new Object[]{
                    DAOPRO.PesquisarPorId(a.getId_produto()).getCod_interno(),
                    DAOPRO.PesquisarPorId(a.getId_produto()).getDescricao(),
                    a.getQuatidade(),
                    a.getValorUnitario(),
                    a.getValorTotalDeixado()
                });
            } catch (Exception ex) {
                Logger.getLogger(JifTrocas.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void doMove(MouseEvent evt) {
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

//-----------------------------Inserir Dados------------------------------------
    public void InserirDados() throws ParseException, Exception {
        objTroca = new Trocas();
        objTroca.setId_autorizador(objUSERVendedor.getId());
        objTroca.setId_caixa(objUSERCaixa.getId());
        objTroca.setId_lojaCupom(objLOJACupom.getId());
        objTroca.setId_lojaRegistro(objLOJAregistro.getId());
        //objTroca.setId_cli(objCli.getId());
        objTroca.setId_cli(1);
        objTroca.setDate_registro(fun.atualDateSQL());
        objTroca.setHora_registro(jtHoraDaTroca.getText());
        objTroca.setN_cupom(Integer.parseInt(jtNumeroCupom.getText()));
        objTroca.setN_pdv(Integer.parseInt(jtNumeroPDV.getText()));
        objTroca.setDate_cupom(fun.convertDateStringToDateSQL(jtDataCupom.getText()));
        objTroca.setTipo(jtTipo.getSelectedItem().toString());
        objTroca.setMotivo_registro(jtMotivoDaTroca.getText());
        objTroca.setListaDeixados(listaDeixados);
        objTroca.setListaLevados(listaLevados);
        DAOTROCA.Insert(objTroca);
    }

    public void EditarDados() {

    }

    public void ExluirDados() {

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GrupoDeRadioButoes = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lbl_inbox1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbl_inbox2 = new javax.swing.JLabel();
        jbDigitarProdutos = new javax.swing.JButton();
        jbSalvar = new javax.swing.JButton();
        jbLimpar = new javax.swing.JButton();
        jbPesquisar = new javax.swing.JButton();
        content = new javax.swing.JPanel();
        img = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jtMatriculaVendedor = new javax.swing.JTextField();
        jtNomeVendedor = new javax.swing.JTextField();
        jtDataDaTroca = new javax.swing.JTextField();
        jtHoraDaTroca = new javax.swing.JTextField();
        jtMatriculaCaixa = new javax.swing.JTextField();
        jtNomeCaixa = new javax.swing.JTextField();
        jtLojaDaRegistro = new javax.swing.JTextField();
        jtNomeLojaDaTroca = new javax.swing.JTextField();
        jtIdPesquisa = new javax.swing.JTextField();
        jbCarregarDados = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jtNumeroCupom = new javax.swing.JTextField();
        jtNumeroPDV = new javax.swing.JTextField();
        jtLojaDoCupom = new javax.swing.JTextField();
        jtNomeLoja = new javax.swing.JTextField();
        jtDataCupom = new javax.swing.JFormattedTextField();
        jtTipo = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jtNomeCliente = new javax.swing.JTextField();
        jtEnderecoCliente = new javax.swing.JTextField();
        jtMotivoDaTroca = new javax.swing.JTextField();
        jtId = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jbSalvarDados = new javax.swing.JButton();
        jtTelefoneCli = new javax.swing.JFormattedTextField();
        jbEditar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtTableDeixados = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtTableLevados = new javax.swing.JTable();
        jtCodProdutos = new javax.swing.JTextField();
        jtDescricaoProduto = new javax.swing.JTextField();
        jrProdutoDeixado = new javax.swing.JRadioButton();
        jrProdutoLevado = new javax.swing.JRadioButton();
        jtQuantidadeProduto = new javax.swing.JTextField();
        jbIncluirProduto = new javax.swing.JButton();
        jtValorTotalDeixados = new javax.swing.JTextField();
        jtValorTotalLevados = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtValorProduto = new javax.swing.JFormattedTextField();
        jbRemoverItem = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jPanel1.setBackground(new java.awt.Color(0, 102, 204));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        lbl_inbox1.setBackground(new java.awt.Color(22, 42, 57));
        lbl_inbox1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_inbox1.setForeground(new java.awt.Color(255, 255, 255));
        lbl_inbox1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_inbox1.setText("REALIZAR");
        lbl_inbox1.setIconTextGap(10);
        lbl_inbox1.setOpaque(true);
        lbl_inbox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_inbox1MouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/logopmenos.png"))); // NOI18N

        lbl_inbox2.setBackground(new java.awt.Color(22, 42, 57));
        lbl_inbox2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_inbox2.setForeground(new java.awt.Color(255, 255, 255));
        lbl_inbox2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_inbox2.setText("TROCA");
        lbl_inbox2.setIconTextGap(10);
        lbl_inbox2.setOpaque(true);
        lbl_inbox2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_inbox2MouseClicked(evt);
            }
        });

        jbDigitarProdutos.setBackground(new java.awt.Color(255, 0, 0));
        jbDigitarProdutos.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jbDigitarProdutos.setForeground(new java.awt.Color(204, 255, 204));
        jbDigitarProdutos.setText("DIGITAR PRODUTOS");

        jbSalvar.setBackground(new java.awt.Color(102, 255, 102));
        jbSalvar.setText("SALVAR");
        jbSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvarActionPerformed(evt);
            }
        });

        jbLimpar.setBackground(new java.awt.Color(102, 153, 0));
        jbLimpar.setText("LIMPAR");
        jbLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLimparActionPerformed(evt);
            }
        });

        jbPesquisar.setBackground(new java.awt.Color(255, 255, 51));
        jbPesquisar.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jbPesquisar.setText("PESQUISAR");
        jbPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_inbox2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbl_inbox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(38, 38, 38))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jbPesquisar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbLimpar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbDigitarProdutos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                            .addComponent(jbSalvar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(213, 213, 213)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_inbox1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_inbox2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbDigitarProdutos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbSalvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbLimpar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbPesquisar)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        content.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(51, 255, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 0, 0)), "Dados Colaboradores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        jtMatriculaVendedor.setBackground(new java.awt.Color(36, 47, 65));
        jtMatriculaVendedor.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jtMatriculaVendedor.setForeground(new java.awt.Color(255, 255, 255));
        jtMatriculaVendedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtMatriculaVendedor.setText("AUTORIZADOR");
        jtMatriculaVendedor.setBorder(null);
        jtMatriculaVendedor.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        jtMatriculaVendedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtMatriculaVendedorMouseClicked(evt);
            }
        });
        jtMatriculaVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtMatriculaVendedorActionPerformed(evt);
            }
        });
        jtMatriculaVendedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtMatriculaVendedorKeyPressed(evt);
            }
        });

        jtNomeVendedor.setEditable(false);
        jtNomeVendedor.setBackground(new java.awt.Color(36, 47, 65));
        jtNomeVendedor.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jtNomeVendedor.setForeground(new java.awt.Color(255, 255, 255));
        jtNomeVendedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtNomeVendedor.setBorder(null);
        jtNomeVendedor.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        jtNomeVendedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtNomeVendedorMouseClicked(evt);
            }
        });

        jtDataDaTroca.setEditable(false);
        jtDataDaTroca.setBackground(new java.awt.Color(153, 255, 153));
        jtDataDaTroca.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jtDataDaTroca.setForeground(new java.awt.Color(204, 0, 0));
        jtDataDaTroca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtDataDaTroca.setText("DATA");
        jtDataDaTroca.setBorder(null);
        jtDataDaTroca.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        jtDataDaTroca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtDataDaTrocaMouseClicked(evt);
            }
        });

        jtHoraDaTroca.setEditable(false);
        jtHoraDaTroca.setBackground(new java.awt.Color(153, 255, 153));
        jtHoraDaTroca.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jtHoraDaTroca.setForeground(new java.awt.Color(204, 0, 0));
        jtHoraDaTroca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtHoraDaTroca.setText("HORA");
        jtHoraDaTroca.setBorder(null);
        jtHoraDaTroca.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        jtHoraDaTroca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtHoraDaTrocaMouseClicked(evt);
            }
        });

        jtMatriculaCaixa.setBackground(new java.awt.Color(0, 153, 153));
        jtMatriculaCaixa.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jtMatriculaCaixa.setForeground(new java.awt.Color(255, 255, 255));
        jtMatriculaCaixa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtMatriculaCaixa.setText("CAIXA");
        jtMatriculaCaixa.setBorder(null);
        jtMatriculaCaixa.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        jtMatriculaCaixa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtMatriculaCaixaMouseClicked(evt);
            }
        });
        jtMatriculaCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtMatriculaCaixaActionPerformed(evt);
            }
        });
        jtMatriculaCaixa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtMatriculaCaixaKeyPressed(evt);
            }
        });

        jtNomeCaixa.setEditable(false);
        jtNomeCaixa.setBackground(new java.awt.Color(0, 153, 153));
        jtNomeCaixa.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jtNomeCaixa.setForeground(new java.awt.Color(255, 255, 255));
        jtNomeCaixa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtNomeCaixa.setBorder(null);
        jtNomeCaixa.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        jtNomeCaixa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtNomeCaixaMouseClicked(evt);
            }
        });

        jtLojaDaRegistro.setEditable(false);
        jtLojaDaRegistro.setBackground(new java.awt.Color(153, 255, 153));
        jtLojaDaRegistro.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jtLojaDaRegistro.setForeground(new java.awt.Color(204, 0, 0));
        jtLojaDaRegistro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtLojaDaRegistro.setText("LOJA");
        jtLojaDaRegistro.setBorder(null);
        jtLojaDaRegistro.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        jtLojaDaRegistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtLojaDaRegistroMouseClicked(evt);
            }
        });

        jtNomeLojaDaTroca.setEditable(false);
        jtNomeLojaDaTroca.setBackground(new java.awt.Color(153, 255, 153));
        jtNomeLojaDaTroca.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jtNomeLojaDaTroca.setForeground(new java.awt.Color(204, 0, 0));
        jtNomeLojaDaTroca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtNomeLojaDaTroca.setText("NOME LOJA");
        jtNomeLojaDaTroca.setBorder(null);
        jtNomeLojaDaTroca.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        jtNomeLojaDaTroca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtNomeLojaDaTrocaMouseClicked(evt);
            }
        });

        jtIdPesquisa.setEditable(false);
        jtIdPesquisa.setBackground(new java.awt.Color(36, 47, 65));
        jtIdPesquisa.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jtIdPesquisa.setForeground(new java.awt.Color(255, 255, 255));
        jtIdPesquisa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtIdPesquisa.setText("ID");
        jtIdPesquisa.setBorder(null);
        jtIdPesquisa.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        jtIdPesquisa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtIdPesquisaMouseClicked(evt);
            }
        });

        jbCarregarDados.setBackground(new java.awt.Color(102, 255, 102));
        jbCarregarDados.setText("Carregar Dados");
        jbCarregarDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCarregarDadosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jtMatriculaVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtNomeVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtDataDaTroca, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtHoraDaTroca, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jtMatriculaCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtNomeCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtLojaDaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtNomeLojaDaTroca)))
                .addGap(70, 70, 70)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbCarregarDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtIdPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtMatriculaVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtNomeVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtDataDaTroca, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtHoraDaTroca, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtIdPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtMatriculaCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtNomeCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtLojaDaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtNomeLojaDaTroca, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbCarregarDados))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Dados Cupom", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        jtNumeroCupom.setBackground(new java.awt.Color(36, 47, 65));
        jtNumeroCupom.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jtNumeroCupom.setForeground(new java.awt.Color(255, 255, 255));
        jtNumeroCupom.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtNumeroCupom.setText("Nº CUPOM");
        jtNumeroCupom.setBorder(null);
        jtNumeroCupom.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        jtNumeroCupom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtNumeroCupomMouseClicked(evt);
            }
        });
        jtNumeroCupom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtNumeroCupomActionPerformed(evt);
            }
        });
        jtNumeroCupom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtNumeroCupomKeyPressed(evt);
            }
        });

        jtNumeroPDV.setBackground(new java.awt.Color(36, 47, 65));
        jtNumeroPDV.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jtNumeroPDV.setForeground(new java.awt.Color(255, 255, 255));
        jtNumeroPDV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtNumeroPDV.setText("Nº PDV");
        jtNumeroPDV.setBorder(null);
        jtNumeroPDV.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        jtNumeroPDV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtNumeroPDVMouseClicked(evt);
            }
        });
        jtNumeroPDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtNumeroPDVActionPerformed(evt);
            }
        });
        jtNumeroPDV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtNumeroPDVKeyPressed(evt);
            }
        });

        jtLojaDoCupom.setBackground(new java.awt.Color(36, 47, 65));
        jtLojaDoCupom.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jtLojaDoCupom.setForeground(new java.awt.Color(255, 255, 255));
        jtLojaDoCupom.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtLojaDoCupom.setText("LOJA");
        jtLojaDoCupom.setBorder(null);
        jtLojaDoCupom.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        jtLojaDoCupom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtLojaDoCupomMouseClicked(evt);
            }
        });
        jtLojaDoCupom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtLojaDoCupomActionPerformed(evt);
            }
        });
        jtLojaDoCupom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtLojaDoCupomKeyPressed(evt);
            }
        });

        jtNomeLoja.setEditable(false);
        jtNomeLoja.setBackground(new java.awt.Color(153, 255, 153));
        jtNomeLoja.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jtNomeLoja.setForeground(new java.awt.Color(204, 0, 0));
        jtNomeLoja.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtNomeLoja.setText("NOME LOJA");
        jtNomeLoja.setBorder(null);
        jtNomeLoja.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        jtNomeLoja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtNomeLojaMouseClicked(evt);
            }
        });
        jtNomeLoja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtNomeLojaActionPerformed(evt);
            }
        });
        jtNomeLoja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtNomeLojaKeyPressed(evt);
            }
        });

        jtDataCupom.setBackground(new java.awt.Color(36, 47, 65));
        jtDataCupom.setForeground(new java.awt.Color(255, 255, 255));
        try {
            jtDataCupom.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtDataCupom.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtDataCupom.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jtDataCupom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtDataCupomActionPerformed(evt);
            }
        });

        jtTipo.setEditable(true);
        jtTipo.setMaximumRowCount(10);
        jtTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Troca", "Devolução" }));
        jtTipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtTipoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtNumeroCupom, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jtNumeroPDV, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtDataCupom, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtLojaDoCupom, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtNomeLoja, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtNumeroCupom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtNumeroPDV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtLojaDoCupom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtNomeLoja, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtDataCupom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(204, 255, 153));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DADOS DO CLIENTE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        jtNomeCliente.setBackground(new java.awt.Color(36, 47, 65));
        jtNomeCliente.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jtNomeCliente.setForeground(new java.awt.Color(255, 255, 255));
        jtNomeCliente.setText("NOME DO CLIENTE");
        jtNomeCliente.setBorder(null);
        jtNomeCliente.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        jtNomeCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtNomeClienteActionPerformed(evt);
            }
        });
        jtNomeCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtNomeClienteKeyPressed(evt);
            }
        });

        jtEnderecoCliente.setBackground(new java.awt.Color(36, 47, 65));
        jtEnderecoCliente.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jtEnderecoCliente.setForeground(new java.awt.Color(255, 255, 255));
        jtEnderecoCliente.setText("ENDEREÇO DO CLIENTE");
        jtEnderecoCliente.setBorder(null);
        jtEnderecoCliente.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        jtEnderecoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtEnderecoClienteActionPerformed(evt);
            }
        });
        jtEnderecoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtEnderecoClienteKeyPressed(evt);
            }
        });

        jtMotivoDaTroca.setBackground(new java.awt.Color(36, 47, 65));
        jtMotivoDaTroca.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jtMotivoDaTroca.setForeground(new java.awt.Color(255, 255, 255));
        jtMotivoDaTroca.setText("MOTIVO DA TROCA");
        jtMotivoDaTroca.setBorder(null);
        jtMotivoDaTroca.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        jtMotivoDaTroca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtMotivoDaTrocaActionPerformed(evt);
            }
        });
        jtMotivoDaTroca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtMotivoDaTrocaKeyPressed(evt);
            }
        });

        jtId.setEditable(false);
        jtId.setBackground(new java.awt.Color(153, 255, 153));
        jtId.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jtId.setForeground(new java.awt.Color(204, 0, 0));
        jtId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtId.setText("ID");
        jtId.setBorder(null);
        jtId.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        jtId.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtIdMouseClicked(evt);
            }
        });

        jLabel4.setText("Nº DOC");

        jbSalvarDados.setBackground(new java.awt.Color(102, 255, 102));
        jbSalvarDados.setText("Gerar ID");
        jbSalvarDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvarDadosActionPerformed(evt);
            }
        });

        jtTelefoneCli.setBackground(new java.awt.Color(36, 47, 65));
        jtTelefoneCli.setForeground(new java.awt.Color(255, 255, 255));
        try {
            jtTelefoneCli.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) # ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtTelefoneCli.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtTelefoneCli.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jtTelefoneCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtTelefoneCliActionPerformed(evt);
            }
        });

        jbEditar.setBackground(new java.awt.Color(102, 255, 102));
        jbEditar.setText("Editar");
        jbEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtEnderecoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jtMotivoDaTroca, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtId, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtTelefoneCli, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbSalvarDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtEnderecoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtTelefoneCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbSalvarDados))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtMotivoDaTroca, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jbEditar))
                .addGap(54, 54, 54))
        );

        jPanel5.setBackground(new java.awt.Color(102, 255, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PRODUTOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        jtTableDeixados.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12))); // NOI18N
        jtTableDeixados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Cod", "Descrição", "Qtd", "Valor", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtTableDeixados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtTableDeixadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtTableDeixados);
        if (jtTableDeixados.getColumnModel().getColumnCount() > 0) {
            jtTableDeixados.getColumnModel().getColumn(1).setMinWidth(250);
            jtTableDeixados.getColumnModel().getColumn(1).setMaxWidth(250);
        }

        jtTableLevados.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12))); // NOI18N
        jtTableLevados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Cod", "Descrição", "Qtd", "Valor", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtTableLevados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtTableLevadosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtTableLevados);
        if (jtTableLevados.getColumnModel().getColumnCount() > 0) {
            jtTableLevados.getColumnModel().getColumn(1).setMinWidth(250);
            jtTableLevados.getColumnModel().getColumn(1).setMaxWidth(250);
        }

        jtCodProdutos.setBackground(new java.awt.Color(36, 47, 65));
        jtCodProdutos.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jtCodProdutos.setForeground(new java.awt.Color(255, 255, 255));
        jtCodProdutos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtCodProdutos.setText("CODIGO");
        jtCodProdutos.setBorder(null);
        jtCodProdutos.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        jtCodProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtCodProdutosMouseClicked(evt);
            }
        });
        jtCodProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtCodProdutosActionPerformed(evt);
            }
        });
        jtCodProdutos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtCodProdutosKeyPressed(evt);
            }
        });

        jtDescricaoProduto.setBackground(new java.awt.Color(36, 47, 65));
        jtDescricaoProduto.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jtDescricaoProduto.setForeground(new java.awt.Color(255, 255, 255));
        jtDescricaoProduto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtDescricaoProduto.setBorder(null);
        jtDescricaoProduto.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        jtDescricaoProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtDescricaoProdutoMouseClicked(evt);
            }
        });

        jrProdutoDeixado.setBackground(new java.awt.Color(102, 255, 0));
        GrupoDeRadioButoes.add(jrProdutoDeixado);
        jrProdutoDeixado.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jrProdutoDeixado.setSelected(true);
        jrProdutoDeixado.setText("Produto Deixado");
        jrProdutoDeixado.setActionCommand("deixado");

        jrProdutoLevado.setBackground(new java.awt.Color(102, 255, 0));
        GrupoDeRadioButoes.add(jrProdutoLevado);
        jrProdutoLevado.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jrProdutoLevado.setText("Produto Levado");
        jrProdutoLevado.setActionCommand("levado");

        jtQuantidadeProduto.setBackground(new java.awt.Color(36, 47, 65));
        jtQuantidadeProduto.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jtQuantidadeProduto.setForeground(new java.awt.Color(255, 255, 255));
        jtQuantidadeProduto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtQuantidadeProduto.setText("QTD");
        jtQuantidadeProduto.setBorder(null);
        jtQuantidadeProduto.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        jtQuantidadeProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtQuantidadeProdutoMouseClicked(evt);
            }
        });
        jtQuantidadeProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtQuantidadeProdutoActionPerformed(evt);
            }
        });
        jtQuantidadeProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtQuantidadeProdutoKeyPressed(evt);
            }
        });

        jbIncluirProduto.setBackground(new java.awt.Color(102, 255, 102));
        jbIncluirProduto.setText("Incluir");
        jbIncluirProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbIncluirProdutoActionPerformed(evt);
            }
        });
        jbIncluirProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jbIncluirProdutoKeyPressed(evt);
            }
        });

        jtValorTotalDeixados.setBackground(new java.awt.Color(204, 255, 204));
        jtValorTotalDeixados.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jtValorTotalDeixados.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtValorTotalDeixados.setText("TOTAL");
        jtValorTotalDeixados.setBorder(null);
        jtValorTotalDeixados.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        jtValorTotalDeixados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtValorTotalDeixadosMouseClicked(evt);
            }
        });

        jtValorTotalLevados.setBackground(new java.awt.Color(204, 255, 204));
        jtValorTotalLevados.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jtValorTotalLevados.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtValorTotalLevados.setText("TOTAL");
        jtValorTotalLevados.setBorder(null);
        jtValorTotalLevados.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        jtValorTotalLevados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtValorTotalLevadosMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel2.setText("TOTAL DOS PRODUTOS DEIXADOS");

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel3.setText("TOTAL DOS PRODUTOS LEVADOS");

        jtValorProduto.setBackground(new java.awt.Color(36, 47, 65));
        jtValorProduto.setForeground(new java.awt.Color(255, 255, 255));
        jtValorProduto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jtValorProduto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtValorProduto.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jtValorProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtValorProdutoActionPerformed(evt);
            }
        });

        jbRemoverItem.setBackground(new java.awt.Color(102, 255, 102));
        jbRemoverItem.setText("Remover");
        jbRemoverItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRemoverItemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtCodProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtDescricaoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtQuantidadeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jtValorProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jrProdutoDeixado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jrProdutoLevado)
                        .addGap(18, 18, 18)
                        .addComponent(jbIncluirProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbRemoverItem, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(jtValorTotalLevados, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(jtValorTotalDeixados, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2)
                .addGap(6, 6, 6))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtCodProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtDescricaoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jrProdutoDeixado)
                    .addComponent(jrProdutoLevado)
                    .addComponent(jtQuantidadeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbIncluirProduto)
                    .addComponent(jtValorProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbRemoverItem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtValorTotalDeixados, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jtValorTotalLevados, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(img)
                .addContainerGap())
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(img)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(contentLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(1, 1, 1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_inbox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_inbox1MouseClicked
        // TODO add your handling code here:

        setLblColor(lbl_inbox1);
        //        resetLblColor(lbl_mark);
        //        resetLblColor(lbl_sent);
        //        resetLblColor(lbl_trash);
        //        resetLblColor(lbl_outbox);
    }//GEN-LAST:event_lbl_inbox1MouseClicked

    private void lbl_inbox2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_inbox2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_inbox2MouseClicked

    private void jbLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLimparActionPerformed
        LimparCampos();
        ResetaCampos();
        LiberaCampos();
    }//GEN-LAST:event_jbLimparActionPerformed

    private void jbPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPesquisarActionPerformed
        jbCarregarDados.setVisible(true);
        jtIdPesquisa.setVisible(true);
    }//GEN-LAST:event_jbPesquisarActionPerformed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        // TODO add your handling code here:

        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_jPanel1MouseDragged

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        // TODO add your handling code here:
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_jPanel1MousePressed

    private void jtMatriculaVendedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtMatriculaVendedorMouseClicked
        carregaVendedor();
    }//GEN-LAST:event_jtMatriculaVendedorMouseClicked

    private void jtMatriculaVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtMatriculaVendedorActionPerformed
        carregaVendedor();
    }//GEN-LAST:event_jtMatriculaVendedorActionPerformed

    private void jtMatriculaVendedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtMatriculaVendedorKeyPressed
        if ("AUTORIZADOR".equals(jtMatriculaVendedor.getText())) {
            jtMatriculaVendedor.setText("");
        }
    }//GEN-LAST:event_jtMatriculaVendedorKeyPressed

    private void jtNomeVendedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtNomeVendedorMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtNomeVendedorMouseClicked

    private void jtDataDaTrocaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtDataDaTrocaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtDataDaTrocaMouseClicked

    private void jtHoraDaTrocaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtHoraDaTrocaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtHoraDaTrocaMouseClicked

    private void jtMatriculaCaixaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtMatriculaCaixaMouseClicked

    }//GEN-LAST:event_jtMatriculaCaixaMouseClicked

    private void jtMatriculaCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtMatriculaCaixaActionPerformed
        carregaCaixa();
    }//GEN-LAST:event_jtMatriculaCaixaActionPerformed

    private void jtMatriculaCaixaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtMatriculaCaixaKeyPressed
        if ("CAIXA".equals(jtMatriculaCaixa.getText())) {
            jtMatriculaCaixa.setText("");
        }
    }//GEN-LAST:event_jtMatriculaCaixaKeyPressed

    private void jtNomeCaixaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtNomeCaixaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtNomeCaixaMouseClicked

    private void jtLojaDaRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtLojaDaRegistroMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtLojaDaRegistroMouseClicked

    private void jtNomeLojaDaTrocaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtNomeLojaDaTrocaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtNomeLojaDaTrocaMouseClicked

    private void jtIdPesquisaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtIdPesquisaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtIdPesquisaMouseClicked

    private void jbCarregarDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCarregarDadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbCarregarDadosActionPerformed

    private void jtNumeroCupomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtNumeroCupomMouseClicked
        if ("".equals(jtNumeroCupom.getText())) {
            jtNumeroCupom.setText("Nº CUPOM");
        } else {
            NumeroCupom();
        }
    }//GEN-LAST:event_jtNumeroCupomMouseClicked

    private void jtNumeroCupomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtNumeroCupomActionPerformed
        if (NumeroCupom()) {
            jtNumeroPDV.requestFocus();
        }
    }//GEN-LAST:event_jtNumeroCupomActionPerformed

    private void jtNumeroCupomKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtNumeroCupomKeyPressed
        if ("Nº CUPOM".equals(jtNumeroCupom.getText())) {
            jtNumeroCupom.setText("");
        } else if (NumeroCupom()) {

        }
    }//GEN-LAST:event_jtNumeroCupomKeyPressed

    private void jtNumeroPDVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtNumeroPDVMouseClicked
        if ("".equals(jtNumeroPDV.getText())) {
            jtNumeroPDV.setText("Nº PDV");
        } else {
            NumeroPDV();
        }
    }//GEN-LAST:event_jtNumeroPDVMouseClicked

    private void jtNumeroPDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtNumeroPDVActionPerformed
        if (NumeroPDV()) {
            jtDataCupom.requestFocus();
        }
    }//GEN-LAST:event_jtNumeroPDVActionPerformed

    private void jtNumeroPDVKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtNumeroPDVKeyPressed
        if ("Nº PDV".equals(jtNumeroPDV.getText())) {
            jtNumeroPDV.setText("");
        } else if (NumeroPDV()) {

        }
    }//GEN-LAST:event_jtNumeroPDVKeyPressed

    private void jtLojaDoCupomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtLojaDoCupomMouseClicked
        if ("".equals(jtLojaDoCupom.getText())) {
            jtLojaDoCupom.setText("LOJA");
        }
    }//GEN-LAST:event_jtLojaDoCupomMouseClicked

    private void jtLojaDoCupomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtLojaDoCupomActionPerformed
        carregaLojaDoCupom();
    }//GEN-LAST:event_jtLojaDoCupomActionPerformed

    private void jtLojaDoCupomKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtLojaDoCupomKeyPressed
        if ("LOJA".equals(jtLojaDoCupom.getText())) {
            jtLojaDoCupom.setText("");
        }
    }//GEN-LAST:event_jtLojaDoCupomKeyPressed

    private void jtNomeLojaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtNomeLojaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtNomeLojaMouseClicked

    private void jtNomeLojaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtNomeLojaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtNomeLojaActionPerformed

    private void jtNomeLojaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtNomeLojaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtNomeLojaKeyPressed

    private void jtDataCupomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtDataCupomActionPerformed
        jtLojaDoCupom.requestFocus();
    }//GEN-LAST:event_jtDataCupomActionPerformed

    private void jtNomeClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtNomeClienteActionPerformed
        if (null == jtNomeCliente.getText()) {
            jtEnderecoCliente.requestFocus();
        } else {
            switch (jtNomeCliente.getText()) {
                case "":
                    jtNomeCliente.setText("NOME DO CLIENTE");
                    break;
                case "NOME DO CLIENTE":
                    jtNomeCliente.setText("");
                    break;
                default:
                    jtEnderecoCliente.requestFocus();
                    break;
            }
        }
    }//GEN-LAST:event_jtNomeClienteActionPerformed

    private void jtEnderecoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtEnderecoClienteActionPerformed
        if (null == jtEnderecoCliente.getText()) {
            jtTelefoneCli.requestFocus();
        } else {
            switch (jtEnderecoCliente.getText()) {
                case "":
                    jtEnderecoCliente.setText("ENDEREÇO DO CLIENTE");
                    break;
                case "ENDEREÇO DO CLIENTE":
                    jtEnderecoCliente.setText("");
                    break;
                default:
                    jtTelefoneCli.requestFocus();
                    break;
            }
        }
    }//GEN-LAST:event_jtEnderecoClienteActionPerformed

    private void jtMotivoDaTrocaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtMotivoDaTrocaActionPerformed
        if (null == jtMotivoDaTroca.getText()) {
            jbSalvarDados.requestFocus();
        } else {
            switch (jtMotivoDaTroca.getText()) {
                case "":
                    jtMotivoDaTroca.setText("MOTIVO DA TROCA");
                    break;
                case "MOTIVO DA TROCA":
                    jtMotivoDaTroca.setText("");
                    break;
                default:
                    jbSalvarDados.requestFocus();
                    break;
            }
        }
    }//GEN-LAST:event_jtMotivoDaTrocaActionPerformed

    private void jtIdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtIdMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtIdMouseClicked

    private void jbSalvarDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarDadosActionPerformed
        BloqueiaCampos();
    }//GEN-LAST:event_jbSalvarDadosActionPerformed

    private void jtTelefoneCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtTelefoneCliActionPerformed
        jtMotivoDaTroca.requestFocus();
    }//GEN-LAST:event_jtTelefoneCliActionPerformed

    private void jtCodProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtCodProdutosMouseClicked
        if ("".equals(jtCodProdutos.getText())) {
            jtCodProdutos.setText("CODIGO");
        }
    }//GEN-LAST:event_jtCodProdutosMouseClicked

    private void jtCodProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtCodProdutosActionPerformed
        TestaItem();
    }//GEN-LAST:event_jtCodProdutosActionPerformed

    private void jtCodProdutosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtCodProdutosKeyPressed
        if ("CODIGO".equals(jtCodProdutos.getText())) {
            jtCodProdutos.setText("");
        }
    }//GEN-LAST:event_jtCodProdutosKeyPressed

    private void jtDescricaoProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtDescricaoProdutoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtDescricaoProdutoMouseClicked

    private void jtQuantidadeProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtQuantidadeProdutoMouseClicked
        if ("".equals(jtQuantidadeProduto.getText())) {
            jtQuantidadeProduto.setText("QTD");
        }
    }//GEN-LAST:event_jtQuantidadeProdutoMouseClicked

    private void jtQuantidadeProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtQuantidadeProdutoActionPerformed
        jtValorProduto.requestFocus();
    }//GEN-LAST:event_jtQuantidadeProdutoActionPerformed

    private void jtQuantidadeProdutoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtQuantidadeProdutoKeyPressed
        if ("QTD".equals(jtQuantidadeProduto.getText())) {
            jtQuantidadeProduto.setText("");
        }
    }//GEN-LAST:event_jtQuantidadeProdutoKeyPressed

    private void jbIncluirProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbIncluirProdutoActionPerformed
        String opcao = GrupoDeRadioButoes.getSelection().getActionCommand();
        TestaItem();
        if (opcao.equals("deixado")) {
            try {
                if (VerificaItensDeixados(DAOPRO.PesquisarPorCodInterno(Integer.parseInt(jtCodProdutos.getText())).getId())) {
                    try {
                        IncluiItensDeixados();
                        PreencheTabelaDaViewDeixados();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Erro ao Incluir: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Produto Duplicado");
                }
            } catch (Exception ex) {
                Logger.getLogger(JifTrocas.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (opcao.equals("levado")) {
            try {
                if (VerificaItensDeixados(DAOPRO.PesquisarPorCodInterno(Integer.parseInt(jtCodProdutos.getText())).getId())) {
                    try {
                        IncluiItensLevados();
                        PreencheTabelaDaViewLevados();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Erro ao Incluir: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Produto Duplicado");
                }
            } catch (Exception ex) {
                Logger.getLogger(JifTrocas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        jtCodProdutos.requestFocus();
    }//GEN-LAST:event_jbIncluirProdutoActionPerformed

    private void jtValorTotalDeixadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtValorTotalDeixadosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtValorTotalDeixadosMouseClicked

    private void jtValorTotalLevadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtValorTotalLevadosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtValorTotalLevadosMouseClicked

    private void jbRemoverItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRemoverItemActionPerformed
        String opcao = GrupoDeRadioButoes.getSelection().getActionCommand();

        if (opcao.equals("deixado")) {
            try {
                RemoveDeixados(DAOPRO.PesquisarPorCodInterno(Integer.parseInt(jtCodProdutos.getText())).getId());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao Remover: " + ex.getMessage());
            }
        } else if (opcao.equals("levado")) {
            try {
                RemoveLevados(DAOPRO.PesquisarPorCodInterno(Integer.parseInt(jtCodProdutos.getText())).getId());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao Remover: " + ex.getMessage());
            }
        }
        jtCodProdutos.requestFocus();
    }//GEN-LAST:event_jbRemoverItemActionPerformed

    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarActionPerformed
        LiberaCampos();
    }//GEN-LAST:event_jbEditarActionPerformed

    private void jtEnderecoClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtEnderecoClienteKeyPressed
        if ("ENDEREÇO DO CLIENTE".equals(jtEnderecoCliente.getText())) {
            jtEnderecoCliente.setText("");
        }
    }//GEN-LAST:event_jtEnderecoClienteKeyPressed

    private void jtMotivoDaTrocaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtMotivoDaTrocaKeyPressed
        if ("MOTIVO DA TROCA".equals(jtMotivoDaTroca.getText())) {
            jtMotivoDaTroca.setText("");
        }
    }//GEN-LAST:event_jtMotivoDaTrocaKeyPressed

    private void jtNomeClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtNomeClienteKeyPressed
        if ("NOME DO CLIENTE".equals(jtNomeCliente.getText())) {
            jtNomeCliente.setText("");
        }
    }//GEN-LAST:event_jtNomeClienteKeyPressed

    private void jbSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarActionPerformed
        try {
            InserirDados();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Salvar no Banco: " + ex.getMessage());
        }
    }//GEN-LAST:event_jbSalvarActionPerformed

    private void jtValorProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtValorProdutoActionPerformed
        jbIncluirProduto.requestFocus();
    }//GEN-LAST:event_jtValorProdutoActionPerformed

    private void jtTableDeixadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtTableDeixadosMouseClicked
        if (jtTableDeixados.getSelectedRow() != -1) {
            Object cod, descricao;

            cod = jtTableDeixados.getValueAt(jtTableDeixados.getSelectedRow(), 0);
            String coD = cod.toString();
            jtCodProdutos.setText(coD);

            descricao = jtTableDeixados.getValueAt(jtTableDeixados.getSelectedRow(), 1);
            String descr = descricao.toString();
            jtDescricaoProduto.setText(descr);
        }
        jtQuantidadeProduto.setText("");
        jtValorProduto.setText("");
    }//GEN-LAST:event_jtTableDeixadosMouseClicked

    private void jtTableLevadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtTableLevadosMouseClicked
        if (jtTableLevados.getSelectedRow() != -1) {
            Object cod, descricao;

            cod = jtTableLevados.getValueAt(jtTableLevados.getSelectedRow(), 0);
            String coD = cod.toString();
            jtCodProdutos.setText(coD);

            descricao = jtTableLevados.getValueAt(jtTableLevados.getSelectedRow(), 1);
            String descr = descricao.toString();
            jtDescricaoProduto.setText(descr);
        }
        jtQuantidadeProduto.setText("");
        jtValorProduto.setText("");
    }//GEN-LAST:event_jtTableLevadosMouseClicked

    private void jbIncluirProdutoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jbIncluirProdutoKeyPressed
        String opcao = GrupoDeRadioButoes.getSelection().getActionCommand();
        TestaItem();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TestaItem();
            if (opcao.equals("deixado")) {
                try {
                    if (VerificaItensDeixados(DAOPRO.PesquisarPorCodInterno(Integer.parseInt(jtCodProdutos.getText())).getId())) {
                        try {
                            IncluiItensDeixados();
                            PreencheTabelaDaViewDeixados();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(this, "Erro ao Incluir: " + ex.getMessage());
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Produto Duplicado");
                    }
                } catch (Exception ex) {
                    Logger.getLogger(JifTrocas.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (opcao.equals("levado")) {
                try {
                    if (VerificaItensDeixados(DAOPRO.PesquisarPorCodInterno(Integer.parseInt(jtCodProdutos.getText())).getId())) {
                        try {
                            IncluiItensLevados();
                            PreencheTabelaDaViewLevados();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(this, "Erro ao Incluir: " + ex.getMessage());
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Produto Duplicado");
                    }
                } catch (Exception ex) {
                    Logger.getLogger(JifTrocas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            jtCodProdutos.requestFocus();
        }

    }//GEN-LAST:event_jbIncluirProdutoKeyPressed

    private void jtTipoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtTipoKeyReleased


    }//GEN-LAST:event_jtTipoKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup GrupoDeRadioButoes;
    private javax.swing.JPanel content;
    private javax.swing.JLabel img;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbCarregarDados;
    private javax.swing.JButton jbDigitarProdutos;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbIncluirProduto;
    private javax.swing.JButton jbLimpar;
    private javax.swing.JButton jbPesquisar;
    private javax.swing.JButton jbRemoverItem;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JButton jbSalvarDados;
    private javax.swing.JRadioButton jrProdutoDeixado;
    private javax.swing.JRadioButton jrProdutoLevado;
    private javax.swing.JTextField jtCodProdutos;
    private javax.swing.JFormattedTextField jtDataCupom;
    private javax.swing.JTextField jtDataDaTroca;
    private javax.swing.JTextField jtDescricaoProduto;
    private javax.swing.JTextField jtEnderecoCliente;
    private javax.swing.JTextField jtHoraDaTroca;
    private javax.swing.JTextField jtId;
    private javax.swing.JTextField jtIdPesquisa;
    private javax.swing.JTextField jtLojaDaRegistro;
    private javax.swing.JTextField jtLojaDoCupom;
    private javax.swing.JTextField jtMatriculaCaixa;
    private javax.swing.JTextField jtMatriculaVendedor;
    private javax.swing.JTextField jtMotivoDaTroca;
    private javax.swing.JTextField jtNomeCaixa;
    private javax.swing.JTextField jtNomeCliente;
    private javax.swing.JTextField jtNomeLoja;
    private javax.swing.JTextField jtNomeLojaDaTroca;
    private javax.swing.JTextField jtNomeVendedor;
    private javax.swing.JTextField jtNumeroCupom;
    private javax.swing.JTextField jtNumeroPDV;
    private javax.swing.JTextField jtQuantidadeProduto;
    private javax.swing.JTable jtTableDeixados;
    private javax.swing.JTable jtTableLevados;
    private javax.swing.JFormattedTextField jtTelefoneCli;
    private javax.swing.JComboBox<String> jtTipo;
    private javax.swing.JFormattedTextField jtValorProduto;
    private javax.swing.JTextField jtValorTotalDeixados;
    private javax.swing.JTextField jtValorTotalLevados;
    private javax.swing.JLabel lbl_inbox1;
    private javax.swing.JLabel lbl_inbox2;
    // End of variables declaration//GEN-END:variables
}
