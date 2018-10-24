package modulo.view.principal;

import java.awt.HeadlessException;
import java.util.List;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modulo.DAO.FaltasDAO;
import modulo.DAO.LojaDAO;
import modulo.DAO.ProdutoDAO;
import modulo.DAO.UsuarioDAO;
import modulo.entidades.Faltas;
import modulo.entidades.Loja;
import modulo.entidades.Produto;
import modulo.entidades.Usuario;
import modulo.versao.Versao;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import modulo.camapanha.DAO.CadastroDescricaoCampanhasDAO;
import modulo.camapanha.DAO.CadastroCampanhaDiaDAO;
import modulo.camapanha.DAO.CadastroMetasCampanhasDAO;
import modulo.camapanha.DAO.CampanhaPlanoVooDAO;
import modulo.DAO.SegurancaDAO;
import modulo.campanhas.CadastroDescricaoCampanhas;
import modulo.campanhas.CadastroCampanhaDia;
import modulo.campanhas.CadastroMetasCampanhas;
import modulo.entidades.CampanhasPlanoDeVoo;
import modulo.metodos.Funcao;
import modulo.configuracoes.JfConfig;
import modulo.metodos.ConvertMD5;
import modulo.metodos.SegurancaFuncoes;
import modulo.view.painel.JfEducaFarma;
import modulo.view.painel.JfPainel;
import modulo.view.painel.JfPlanogramas;
import modulo.sobre.JfSobre;

/**
 *
 * @author Marcos Junior
 */
public final class JfPrincipal extends javax.swing.JFrame {

    private FaltasDAO DAOLista;
    private Faltas objLista;
    private Funcao fun;
    private UsuarioDAO DAOUSER;
    private Usuario objUSER;
    private LojaDAO DAOLOJA;
    private ProdutoDAO DAOPRO;
    private Produto objPRO;
    private DateFormat formatoHora;
    private DateFormat formatoDIA;
    private Versao ver;
    private int numeroLoja = 340;
    private boolean tipo = true;
    private String Aviso = "Atenção";

    private CadastroDescricaoCampanhasDAO ITENSCAMP_DAO;
    private CadastroCampanhaDiaDAO CAMP_DAO;
    private CadastroMetasCampanhasDAO CADCAMP_DAO;

    private CadastroCampanhaDia objCamp;
    private CadastroMetasCampanhas objCadCamp;
    private CadastroDescricaoCampanhas objCadIntesCamp;

    private CampanhasPlanoDeVoo objVoo;
    private CampanhaPlanoVooDAO DAOVOO;
    private JFormattedTextField[] campanha;
    private JFormattedTextField[] planoVoo;
    private SegurancaDAO SEGDAO;
    private SegurancaFuncoes segu;
    private ConvertMD5 MD5;

    public JfPrincipal() {
        initComponents();
        DAOUSER = new UsuarioDAO();
        DAOLOJA = new LojaDAO();
        DAOPRO = new ProdutoDAO();
        DAOLista = new FaltasDAO();
        ITENSCAMP_DAO = new CadastroDescricaoCampanhasDAO();
        CAMP_DAO = new CadastroCampanhaDiaDAO();
        CADCAMP_DAO = new CadastroMetasCampanhasDAO();
        DAOVOO = new CampanhaPlanoVooDAO();
        SEGDAO = new SegurancaDAO();
        segu = new SegurancaFuncoes();
        fun = new Funcao();
        ver = new Versao();
        MD5 = new ConvertMD5();
        setTitle("Registro De Vendas: " + ver.getVersao());
        this.formatoHora = new SimpleDateFormat("EEEEEEEEEEEEEE, dd/MM/yyyy    HH:mm:ss");
        this.formatoDIA = new SimpleDateFormat("dd/MM/yyyy");
        Thread relogioThred = new Thread(new JfPrincipal.clsDataHora());
        relogioThred.start();
        VerificaAcesso();
        carregaLoja();
        carregaDataNoForm();
        criarUmVetorDeCampos();
        listaCampanhasAtivas();
    }

    //*************************Geral Todos**************************************
    private void carregaDataNoForm() {
        jtDataDoRegistroCampanha.setText(formatoDIA.format(new Date()));
        jtDataDoRegistroVoo.setText(formatoDIA.format(new Date()));
        jtDataDoRegistro.setText(formatoDIA.format(new Date()));
    }

    class clsDataHora implements Runnable {

        @Override
        public void run() {
            while (true) {
                somaValores();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println("Thread não foi finalizada:" + ex);
                }
            }
        }
    }

    private void carregaLoja() {
        try {
            Loja f = DAOLOJA.PesquisaNumeroLoja(numeroLoja);
            String nl = Integer.toString(f.getNumero_loja());
            jtLoja.setText(nl);
            jtLoja1Campanha.setText(nl);
            jtLoja5.setText(nl);
            jlTituloRuptura.setText("Lista de Ruptura");
            jlTituloCampanha.setText("Campanhas");
            jlTituloPlanoDeVoo.setText("Plano de Voo");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Carregar Dados Loja. " + ex.getMessage());
            jTabbedPane1.setVisible(false);
            AbreConfig();
        }
    }

    private void limparCampos() {
        int v = jTabbedPane1.getSelectedIndex();

        switch (v) {
            case 0: {
                //Ruptura
                jtCod_Interno.setText("");
                jtDescricaoItem.setText("");
                jtQTD_Perdida.setText("");
                jtDataDoRegistro.setText(formatoDIA.format(new Date()));
                break;
            }
            case 1: {
                //Campanhas
                jtMatriculaCampanha.setText("");
                jtNomeUsuarioCampanha.setText("");
                jtQtdProdutoCampanha.setText("");
                jcCampanhaObs.setSelectedIndex(0);
                jtDataDoRegistroCampanha.setText(formatoDIA.format(new Date()));
                break;
            }
            case 2: {
                //Plano de Voo
                jfVooCli1.setText("");
                jfVooCli2.setText("");
                jfVooCli3.setText("");
                jfVooCli4.setText("");
                jfVooVendaTotal.setText("");
                jfVooVenda1.setText("");
                jfVooVenda2.setText("");
                jfVooVenda3.setText("");
                jfVooVenda4.setText("");
                jfVooCliTotal.setText("");
                jtDataDoRegistroVoo.setText(formatoDIA.format(new Date()));
                break;
            }
            default:
                break;
        }
    }

    private void AbreConfig() {
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
    }

    private boolean metodoDeBusca(String valor, JTextField NomeUser, JTextField campo) {
        boolean chek = false;
        try {
            if (fun.testaNumerosInteiros(valor)) {
                objUSER = DAOUSER.PesquisaMatriculaR(Integer.parseInt(valor));
                if (objUSER == null) {
                    NomeUser.setText("Não localizado!");
                    campo.requestFocus();
                    chek = false;
                } else {
                    NomeUser.setText(objUSER.getNome());
                    chek = true;
                }
            } else {
                NomeUser.setText("Não localizado!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Falha ao buscar: " + ex);
        }
        return chek;
    }

    public boolean pesquisarUsuarioNoBanco() {
        int v = jTabbedPane1.getSelectedIndex();
        boolean chek = false;
        switch (v) {
            case 0: {
                chek = metodoDeBusca(jtMatriculaRuptura.getText(), jtNomeUsuarioR, jtMatriculaRuptura);
                break;
            }
            case 1: {
                chek = metodoDeBusca(jtMatriculaCampanha.getText(), jtNomeUsuarioCampanha, jtMatriculaCampanha);
                break;
            }
            case 2: {
                chek = metodoDeBusca(jtMatriculaVoo.getText(), jtNomeUsuarioV, jtMatriculaVoo);
                break;
            }
            default:
                break;
        }
        return chek;
    }

    private void verificaOpcaoSelecionada(KeyEvent ke, String test) {
        if (ke.getKeyCode() == KeyEvent.VK_ENTER & "obs".equals(test)) {
            jtQTD_Perdida.requestFocus();
        }
        if (ke.getKeyCode() == KeyEvent.VK_TAB || ke.getKeyCode() == KeyEvent.VK_ENTER & "matri".equals(test)) {
            jtCod_Interno.requestFocus();
        }
        if (ke.getKeyCode() == KeyEvent.VK_TAB || ke.getKeyCode() == KeyEvent.VK_ENTER & "matriCamp".equals(test)) {
            jtQtdProdutoCampanha.requestFocus();
        }
        if (ke.getKeyCode() == KeyEvent.VK_TAB || ke.getKeyCode() == KeyEvent.VK_ENTER & "matriVoo".equals(test)) {
            jfVooVenda1.requestFocus();
        }
        if (ke.getKeyCode() == KeyEvent.VK_TAB || ke.getKeyCode() == KeyEvent.VK_ENTER & "obsCamp".equals(test)) {
            jbSalvarCampanha.requestFocus();
        }
    }

    private boolean verificaMatriculaAntesDeSalvar() {
        int v = jTabbedPane1.getSelectedIndex();
        boolean check = false;
        try {
            switch (v) {
                case 0: {
                    if (!jtMatriculaRuptura.getText().equals("")) {
                        check = DAOUSER.CheckSelect(fun.convertToInt(jtMatriculaRuptura.getText()));
                    }
                    break;
                }
                case 1: {
                    if (!jtMatriculaCampanha.getText().equals("")) {
                        check = DAOUSER.CheckSelect(fun.convertToInt(jtMatriculaCampanha.getText()));
                    }
                    break;
                }
                case 2: {
                    if (!jtMatriculaVoo.getText().equals("")) {
                        check = DAOUSER.CheckSelect(fun.convertToInt(jtMatriculaVoo.getText()));
                    }
                    break;
                }
                default:
                    break;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Falha ao verificaMatriculaAntesDeSalvar: " + ex);
        }
        return check;
    }

    private void VerificaAcesso() {

        try {
            String serial, MD1, MD2;
            serial = segu.getHDSerial();
            if (SEGDAO.CheckSelect(serial)) {
                MD1 = MD5.MD5String(serial);
                MD2 = SEGDAO.PesquisaRegistro(segu.getHDSerial()).getSerialDeAutorizacao();
                if (MD1 == null ? MD2 == null : MD1.equals(MD2)) {

                } else {
                    JOptionPane.showMessageDialog(this, "Sistema Pendente de Liberação:");
                    jTabbedPane1.setVisible(false);
                    AbreConfig();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Sistema Pendente de Liberação:");
                jTabbedPane1.setVisible(false);
                AbreConfig();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro de Autorização: IOE: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro de Autorização: EX: " + ex.getMessage());
        }
    }
    //*************************Fim**********************************************

    //*************************Campanhas****************************************
    private boolean validadeCampanha(java.sql.Date data) throws Exception {
        String a, b;
        a = fun.converteDataSQLemDateString(data);
        b = fun.converteDataSQLemDateString(fun.atualDateSQL());
        //System.out.println("Compare " + a + " com " + b + " Tipo " + (data.compareTo(fun.atualDateSQL())) + " Equal " + a.equals(b));
        return data.compareTo(fun.atualDateSQL()) == 1 || a.equals(b);
    }

    private boolean carregaVariaveisCamapanha() throws ParseException {
        objCamp = new CadastroCampanhaDia();
        objCamp.setDesc_campanha(jcCampanhaObs.getSelectedItem().toString());
        objCamp.setData_registro(fun.converteDateStringEmSQL(jtDataDoRegistroCampanha.getText()));
        objCamp.setMatricula(fun.convertToInt(jtMatriculaCampanha.getText()));
        objCamp.setQuantidade(fun.convertToInt(jtQtdProdutoCampanha.getText()));
        return objCamp != null;
    }

    private void listaCampanhasAtivas() {
        List<CadastroMetasCampanhas> CadCamp;
        try {
            CadCamp = CADCAMP_DAO.TabelaPesquisaTodos();
            CadCamp.forEach((c) -> {
                try {
                    if (validadeCampanha(c.getData_fim())) {
                        jcCampanhaObs.addItem(c.getDescricao_Campanha());
                    }
                } catch (Exception ex) {
                    Logger.getLogger(JfPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Pesquisar Campanhas! " + ex);
        }
    }

    private boolean validaCamposCampanha() {
        boolean check = true;
        jtQtdProdutoCampanha.setBackground(new java.awt.Color(255, 255, 255));
        //jcCampanhaObs.setBackground(new java.awt.Color(214, 217, 223));
        if (jtQtdProdutoCampanha.getText().equals("") || Integer.parseInt(jtQtdProdutoCampanha.getText()) <= 0) {
            jtQtdProdutoCampanha.setBackground(new java.awt.Color(255, 0, 0));
            jtQtdProdutoCampanha.requestFocus();
            jLabelAvisos.setText("Quantidade de Produtos Incorreta!");
            check = false;
        }
        if (jcCampanhaObs.getSelectedIndex() == 0) {
            //jcCampanhaObs.setBackground(new java.awt.Color(255, 0, 0));
            jcCampanhaObs.requestFocus();
            jLabelAvisos.setText("Selecione Uma Campanha Vigente!");
            check = false;
        }
        if (!fun.testaNumerosDecimais(jtQtdProdutoCampanha.getText())) {
            jtQtdProdutoCampanha.setText("");
            jtQtdProdutoCampanha.requestFocus();
            check = false;
        }
        return check;
    }

    private void salvarCamapanha() {
        try {
            if (validaCamposCampanha()) {
                if (verificaMatriculaAntesDeSalvar()) {
                    if (carregaVariaveisCamapanha()) {
                        if (CAMP_DAO.Insert(objCamp)) {
                            JOptionPane.showMessageDialog(this, "Salvo com Sucesso!");
                            limparCampos();
                            jtQtdProdutoCampanha.requestFocus();
                            jLabelAvisos.setText("Salvo com Sucesso!");
                        } else {
                            jLabelAvisos.setText("Não foi possível salvar!");
                        }
                    }
                } else {
                    jLabelAvisos.setText("Matrícula Incorreta!");
                    jtMatriculaCampanha.requestFocus();
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Falha ao salvarDadosColaborador: " + ex);
        }
    }

    //*************************Fim**********************************************
    //*************************Plano de Voo*************************************
    private void criarUmVetorDeCampos() {//Função Secundária
        campanha = new JFormattedTextField[]{
            jtQtdProdutoCampanha};

        planoVoo = new JFormattedTextField[]{
            jfVooCli1,
            jfVooCli2,
            jfVooCli3,
            jfVooCli4,
            jfVooVendaTotal,
            jfVooVenda1,
            jfVooVenda2,
            jfVooVenda3,
            jfVooVenda4,
            jfVooCliTotal};

    }

    private boolean setZeroNosCamposLimposVoo() {
        boolean check = true;
        for (JFormattedTextField voo : planoVoo) {
            if (voo.getText().equals("") || voo.getText().equals("0")) {
                voo.setText("0");
                voo.setBackground(new java.awt.Color(0, 0, 255));
            } else {
                voo.setBackground(new java.awt.Color(255, 255, 255));
            }
        }
        return check;
    }

    private boolean carregaDadosNaVariavelVoo() throws ParseException {//Função Secundária
        objVoo = new CampanhasPlanoDeVoo();
        objVoo.setCli1(fun.convertToInt(jfVooCli1.getText()));
        objVoo.setCli2(fun.convertToInt(jfVooCli2.getText()));
        objVoo.setCli3(fun.convertToInt(jfVooCli3.getText()));
        objVoo.setCli4(fun.convertToInt(jfVooCli4.getText()));
        objVoo.setTotalcli(fun.convertToInt(jfVooCliTotal.getText()));
        objVoo.setPdv1(fun.convertToDouble(jfVooVenda1.getText()));
        objVoo.setPdv2(fun.convertToDouble(jfVooVenda2.getText()));
        objVoo.setPdv3(fun.convertToDouble(jfVooVenda3.getText()));
        objVoo.setPdv4(fun.convertToDouble(jfVooVenda4.getText()));
        objVoo.setTotalpdvs(fun.convertToDouble(jfVooVendaTotal.getText()));
        objVoo.setMatricula(fun.convertToInt(jtMatriculaVoo.getText()));
        objVoo.setData_registro(fun.converteDateStringEmSQL(jtDataDoRegistroVoo.getText()));

        return objVoo != null;
    }

    private boolean validaCamposVoo() {
        boolean check;
        int cont = 0;
        for (JFormattedTextField voo : planoVoo) {
            if (voo.getText().equals("")) {
                voo.setBackground(new java.awt.Color(255, 0, 0));
                cont++;
            } else {
                if (!fun.testaNumerosDecimais(voo.getText())) {
                    voo.setText("");
                }
                voo.setBackground(new java.awt.Color(255, 255, 255));
            }
        }
        check = cont == 0;
        return check;
    }

    private void somaValores() {
        double db1 = fun.convertToDouble(jfVooVenda1.getText());
        double db2 = fun.convertToDouble(jfVooVenda2.getText());
        double db3 = fun.convertToDouble(jfVooVenda3.getText());
        double db4 = fun.convertToDouble(jfVooVenda4.getText());
        double somadb = db1 + db2 + db3 + db4;
        int it1 = fun.convertToInt(jfVooCli1.getText());
        int it2 = fun.convertToInt(jfVooCli2.getText());
        int it3 = fun.convertToInt(jfVooCli3.getText());
        int it4 = fun.convertToInt(jfVooCli4.getText());
        int somait = it1 + it2 + it3 + it4;

        jfVooVendaTotal.setText(fun.convertDoubleToString(somadb));
        jfVooCliTotal.setText(Integer.toString(somait));
    }

    private void salvarPlanoDeVoo() {//Função Principal
        try {
            if (validaCamposVoo()) {
                if (verificaMatriculaAntesDeSalvar()) {
                    if (setZeroNosCamposLimposVoo()) {
                        if (carregaDadosNaVariavelVoo()) {
                            if (DAOVOO.Insert(objVoo)) {
                                JOptionPane.showMessageDialog(this, "Salvo com Sucesso!");
                                limparCampos();
                            } else {
                                JOptionPane.showMessageDialog(this, "Não foi possível salvar!");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Não pode salvar se todos os campos forem zero!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Matrícula Incorreta!");
                    jtMatriculaVoo.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Teste!");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Falha ao salvarDadosColaborador: " + ex);
        }
    }

    //*************************Fim**********************************************
    //*************************Ruptura******************************************
    private void pesquisaProdutoNoBDRuptura() {
        try {
            if (DAOPRO.CheckSelect(Integer.parseInt(jtCod_Interno.getText()))) {
                objPRO = DAOPRO.PesquisarPorCodInterno(Integer.parseInt(jtCod_Interno.getText()));
                jtDescricaoItem.setText(objPRO.getDescricao());
            } else {
                jtDescricaoItem.setText("Código Não Cadastrado!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Falha CarregaPro: " + ex.getMessage());
        }
    }

    private boolean preencherObjetosSalvarProduto() {
        objPRO = new Produto();
        objPRO.setCod_interno(Integer.parseInt(jtCod_Interno.getText()));
        objPRO.setDescricao(jtDescricaoItem.getText());
        objPRO.setObserv(jtObservacao.getSelectedItem().toString());
        return true;
    }

    private boolean preencherObjetosSalvarLista() {
        objLista = new Faltas();
        objLista.setCod_interno(Integer.parseInt(jtCod_Interno.getText()));
        objLista.setData(fun.atualDateSQL());
        objLista.setDescricao(jtDescricaoItem.getText());
        objLista.setMatricula(Integer.parseInt(jtMatriculaRuptura.getText()));
        objLista.setObservacao(jtObservacao.getSelectedItem().toString());
        objLista.setQtd_perdida(Integer.parseInt(jtQTD_Perdida.getText()));
        return true;
    }

    private void cancelarRuptura() {
        if ("Limpar".equals(jbCancelarRuptura.getText())) {
            limparCampos();
        } else if ("Cancelar".equals(jbCancelarRuptura.getText())) {
            limparCampos();
            jbSalvarRuptura.setText("Salvar Falta");
            jbCancelarRuptura.setText("Limpar");
            jtObservacao.setEnabled(true);
            jtDescricaoItem.setEditable(false);
            jtCod_Interno.setEnabled(true);
            jtMatriculaRuptura.setEnabled(true);
            jtQTD_Perdida.setEnabled(true);
            jtCod_Interno.requestFocus();
            tipo = true;
        }
    }

    public void verificarDadosAoSalvarRuptura() {
        if ("Salvar Falta".equals(jbSalvarRuptura.getText())) {
            if (validarCamposAoRegistrarRuptura()) {
                try {
                    pesquisaProdutoNoBDRuptura();
                    salvarDadosDaFaltaNoBanco();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao Carregar Produto: " + ex.getMessage());
                }
            }
        } else if ("Cadastrar!".equals(jbSalvarRuptura.getText())) {
            inserirUmNovoProdutoNoBanco();
        }
    }

    private boolean validarCamposAoCadastarProduto() {
        if (jtCod_Interno.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Código Interno!");
            jtMatriculaRuptura.requestFocus();
            return false;
        }
        if (jtDescricaoItem.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Não é possível cadastrar um produto sem descrição!");
            jtCod_Interno.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validarCamposAoRegistrarRuptura() {
        if (jtCod_Interno.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Código Interno!");
            jtMatriculaRuptura.requestFocus();
            return false;
        }
        if (jtMatriculaRuptura.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Matrícula!");
            jtMatriculaRuptura.requestFocus();
            return false;
        }
        if (jtDescricaoItem.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Não é possível cadastrar um produto sem descrição!");
            jtCod_Interno.requestFocus();
            return false;
        }
        if (jtQTD_Perdida.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha o Campo Quantidade Perdida!");
            jtQTD_Perdida.requestFocus();
            return false;
        }
        if (Integer.parseInt(jtQTD_Perdida.getText()) <= 0) {
            JOptionPane.showMessageDialog(this, "Não pode ser 0!");
            jtQTD_Perdida.setText("");
            jtQTD_Perdida.requestFocus();
            return false;
        }
        if (jtObservacao.getSelectedItem().equals("Selecione Uma Observação")) {
            JOptionPane.showMessageDialog(this, "Selecione Uma Observação!");
            jtObservacao.requestFocus();
            return false;
        }
        return true;
    }

    private void verificaSeProdutoEstaCadastradoRuptura() {
        boolean check;
        try {
            if (jtCod_Interno.getText().equals("")) {
                jtDescricaoItem.setText("Código Não Cadastrado!");
                check = false;
            } else if (DAOPRO.PesquisarPorCodInterno(Integer.parseInt(jtCod_Interno.getText())) != null) {
                objPRO = DAOPRO.PesquisarPorCodInterno(Integer.parseInt(jtCod_Interno.getText()));
                jtDescricaoItem.setText(objPRO.getDescricao());
                jtObservacao.requestFocus();
            } else {
                jtDescricaoItem.setText("");
                tipo = false;
                int resposta;
                resposta = JOptionPane.showConfirmDialog(this, "Deseja Cadadastrar o Produto? ", Aviso, JOptionPane.YES_NO_OPTION);
                if (resposta == 0) {
                    jtDescricaoItem.setText("");
                    jtDescricaoItem.requestFocus();
                    jbSalvarRuptura.setText("Cadastrar!");
                    jbCancelarRuptura.setText("Cancelar");
                    jtCod_Interno.setEnabled(false);
                    jtDescricaoItem.setEditable(true);
                    jtObservacao.setEnabled(false);
                    jtMatriculaRuptura.setEnabled(false);
                    jtQTD_Perdida.setEnabled(false);
                } else if (resposta == 1) {
                    jbSalvarRuptura.setText("Salvar Falta");
                    jbCancelarRuptura.setText("Limpar");
                    jtCod_Interno.setEnabled(true);
                    jtMatriculaRuptura.setEnabled(true);
                    jtQTD_Perdida.setEnabled(true);
                    jtCod_Interno.requestFocus();
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Pesquiar Código Interno." + ex.getMessage());
        }
    }

    private void inserirUmNovoProdutoNoBanco() {
        try {
            if (validarCamposAoCadastarProduto()) {
                if (!DAOPRO.CheckSelect(Integer.parseInt(jtCod_Interno.getText()))) {
                    if (preencherObjetosSalvarProduto()) {
                        if (DAOPRO.Insert(objPRO)) {
                            JOptionPane.showMessageDialog(this, "Salvo Com Sucesso!");
                            jtDescricaoItem.setEditable(true);
                            jtQTD_Perdida.setEnabled(true);
                            jbSalvarRuptura.setText("Salvar Falta");
                            jbCancelarRuptura.setText("Cancelar");
                            jtObservacao.setEnabled(true);
                            jtDescricaoItem.setEditable(false);
                            jtObservacao.requestFocus();
                            tipo = true;
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Não Foi Possível Salvar!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Produto ja Cadastrado!");
                    pesquisaProdutoNoBDRuptura();
                }
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(this, "Erro ao Salvar: " + erro.getMessage());
        }
    }

    private void salvarDadosDaFaltaNoBanco() {
        try {
            try {
                if (verificaMatriculaAntesDeSalvar()) {
                    if (validarCamposAoRegistrarRuptura()) {
                        pesquisarUsuarioNoBanco();
                        verificaSeProdutoEstaCadastradoRuptura();
                        if (preencherObjetosSalvarLista()) {
                            if (DAOLista.Insert(objLista)) {
                                limparCampos();
                                jtMatriculaRuptura.setEnabled(true);
                                jtCod_Interno.setEnabled(true);
                                jtObservacao.setEnabled(true);
                                jtQTD_Perdida.setEnabled(true);
                                jbCancelarRuptura.setText("Limpar");
                                jtObservacao.setSelectedIndex(0);
                                jtCod_Interno.requestFocus();
                                JOptionPane.showMessageDialog(this, "Salvo Com Sucesso!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Não Foi Possível Salvar!");
                            jtObservacao.setSelectedIndex(0);
                            jtCod_Interno.requestFocus();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Matrícula Incorreta!");
                    jtMatriculaRuptura.requestFocus();
                }
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(this, "Erro ao Salvar: " + erro.getMessage());
            }
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Iniciar readTable. " + ex.getMessage());
        }
    }

//*************************Fim**************************************************
//    public boolean PesquisaUltmos30Dias() {
//        boolean chek = false;
//        int cod_int = Integer.parseInt(jtCod_Interno.getText());
//        try {
//            chek = DAOLista.CheckSelect(cod_int, fun.Ultimos30Dias(), fun.converteDataUtilToSql());
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(this, "Erro ao Carregar Data!" + ex);
//        }
//        return chek;
//    }
//******************************************************************Segunda Parte
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1Ruptura = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jtCod_Interno = new javax.swing.JTextField();
        jbSalvarRuptura = new javax.swing.JButton();
        jbCancelarRuptura = new javax.swing.JButton();
        jlMat = new javax.swing.JLabel();
        jtDescricaoItem = new javax.swing.JTextField();
        jtMatriculaRuptura = new javax.swing.JTextField();
        jtNomeUsuarioR = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jtQTD_Perdida = new javax.swing.JTextField();
        jtDataDoRegistro = new javax.swing.JTextField();
        jtLoja = new javax.swing.JTextField();
        jtObservacao = new javax.swing.JComboBox<>();
        jlTituloRuptura = new javax.swing.JLabel();
        jPanel2Campanhas = new javax.swing.JPanel();
        jlTituloCampanha = new javax.swing.JLabel();
        jlMat1 = new javax.swing.JLabel();
        jtMatriculaCampanha = new javax.swing.JTextField();
        jtNomeUsuarioCampanha = new javax.swing.JTextField();
        jlMat23 = new javax.swing.JLabel();
        jtQtdProdutoCampanha = new javax.swing.JFormattedTextField();
        jbSalvarCampanha = new javax.swing.JButton();
        jbCancelarCampanha = new javax.swing.JButton();
        jtLoja1Campanha = new javax.swing.JTextField();
        jtDataDoRegistroCampanha = new javax.swing.JFormattedTextField();
        jcCampanhaObs = new javax.swing.JComboBox<>();
        jLabelAvisos = new javax.swing.JLabel();
        jPanel3PlanoDeVoo = new javax.swing.JPanel();
        jlTituloPlanoDeVoo = new javax.swing.JLabel();
        jtNomeUsuarioV = new javax.swing.JTextField();
        jtMatriculaVoo = new javax.swing.JTextField();
        jlMat26 = new javax.swing.JLabel();
        jbSalvarVoo = new javax.swing.JButton();
        jbCancelarVoo = new javax.swing.JButton();
        jtLoja5 = new javax.swing.JTextField();
        jlMat28 = new javax.swing.JLabel();
        jlMat29 = new javax.swing.JLabel();
        jfVooVenda1 = new javax.swing.JFormattedTextField();
        jfVooVenda2 = new javax.swing.JFormattedTextField();
        jfVooVenda4 = new javax.swing.JFormattedTextField();
        jfVooVenda3 = new javax.swing.JFormattedTextField();
        jfVooCli1 = new javax.swing.JFormattedTextField();
        jfVooCli2 = new javax.swing.JFormattedTextField();
        jfVooCli3 = new javax.swing.JFormattedTextField();
        jfVooCli4 = new javax.swing.JFormattedTextField();
        jfVooVendaTotal = new javax.swing.JFormattedTextField();
        jfVooCliTotal = new javax.swing.JFormattedTextField();
        jtDataDoRegistroVoo = new javax.swing.JFormattedTextField();
        jPanel4Utilidades = new javax.swing.JPanel();
        jbGraficos = new javax.swing.JButton();
        jbConfigurar = new javax.swing.JButton();
        jbDetalhamento = new javax.swing.JButton();
        jbPlanogramas = new javax.swing.JButton();
        jbEducaFarma = new javax.swing.JButton();
        jbSobre = new javax.swing.JButton();
        jbPopular = new javax.swing.JButton();
        jbEditarPlanoDeVoo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 51, 51));
        setIconImage(new ImageIcon(getClass().getResource("/icons/icone_sistema.png")).getImage());
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(153, 255, 153));

        jTabbedPane1.setBackground(new java.awt.Color(102, 102, 255));
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel1Ruptura.setBackground(new java.awt.Color(204, 204, 255));

        jLabel5.setBackground(new java.awt.Color(153, 255, 0));
        jLabel5.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 153));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Código");

        jtCod_Interno.setBackground(new java.awt.Color(255, 255, 102));
        jtCod_Interno.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jtCod_Interno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtCod_Interno.setDisabledTextColor(new java.awt.Color(255, 153, 51));
        jtCod_Interno.setFocusTraversalPolicyProvider(true);
        jtCod_Interno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtCod_InternoMouseClicked(evt);
            }
        });
        jtCod_Interno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtCod_InternoActionPerformed(evt);
            }
        });
        jtCod_Interno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtCod_InternoKeyPressed(evt);
            }
        });

        jbSalvarRuptura.setBackground(new java.awt.Color(0, 0, 102));
        jbSalvarRuptura.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jbSalvarRuptura.setForeground(new java.awt.Color(255, 255, 255));
        jbSalvarRuptura.setText("Salvar Falta");
        jbSalvarRuptura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbSalvarRupturaMouseClicked(evt);
            }
        });
        jbSalvarRuptura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvarRupturaActionPerformed(evt);
            }
        });
        jbSalvarRuptura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jbSalvarRupturaKeyPressed(evt);
            }
        });

        jbCancelarRuptura.setBackground(new java.awt.Color(204, 0, 0));
        jbCancelarRuptura.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jbCancelarRuptura.setForeground(new java.awt.Color(255, 255, 255));
        jbCancelarRuptura.setText("Limpar");
        jbCancelarRuptura.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbCancelarRuptura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarRupturaActionPerformed(evt);
            }
        });

        jlMat.setBackground(new java.awt.Color(153, 255, 0));
        jlMat.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jlMat.setForeground(new java.awt.Color(0, 0, 153));
        jlMat.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlMat.setText("Matrícula");

        jtDescricaoItem.setEditable(false);
        jtDescricaoItem.setBackground(new java.awt.Color(102, 255, 153));
        jtDescricaoItem.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jtDescricaoItem.setForeground(new java.awt.Color(0, 153, 51));
        jtDescricaoItem.setDisabledTextColor(new java.awt.Color(255, 153, 51));
        jtDescricaoItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtDescricaoItemActionPerformed(evt);
            }
        });

        jtMatriculaRuptura.setBackground(new java.awt.Color(255, 255, 102));
        jtMatriculaRuptura.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jtMatriculaRuptura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtMatriculaRuptura.setDisabledTextColor(new java.awt.Color(255, 153, 51));
        jtMatriculaRuptura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtMatriculaRupturaMouseClicked(evt);
            }
        });
        jtMatriculaRuptura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtMatriculaRupturaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtMatriculaRupturaKeyReleased(evt);
            }
        });

        jtNomeUsuarioR.setEditable(false);
        jtNomeUsuarioR.setBackground(new java.awt.Color(102, 255, 153));
        jtNomeUsuarioR.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jtNomeUsuarioR.setForeground(new java.awt.Color(255, 0, 0));
        jtNomeUsuarioR.setDisabledTextColor(new java.awt.Color(255, 153, 51));

        jLabel7.setBackground(new java.awt.Color(153, 255, 0));
        jLabel7.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 153));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Perdida");

        jtQTD_Perdida.setBackground(new java.awt.Color(255, 255, 102));
        jtQTD_Perdida.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jtQTD_Perdida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtQTD_Perdida.setDisabledTextColor(new java.awt.Color(255, 153, 51));
        jtQTD_Perdida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtQTD_PerdidaActionPerformed(evt);
            }
        });

        jtDataDoRegistro.setEditable(false);
        jtDataDoRegistro.setBackground(new java.awt.Color(255, 153, 102));
        jtDataDoRegistro.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jtDataDoRegistro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtDataDoRegistro.setDisabledTextColor(new java.awt.Color(255, 153, 51));

        jtLoja.setEditable(false);
        jtLoja.setBackground(new java.awt.Color(255, 153, 102));
        jtLoja.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jtLoja.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtLoja.setDisabledTextColor(new java.awt.Color(255, 153, 51));

        jtObservacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione Uma Observação", "Falta", "Deixei de Vender", "Desativado", "Muita Procura", "Encomenda", "Falta Temporaria Pelo Fabricante", "Falta no CD" }));
        jtObservacao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtObservacaoKeyPressed(evt);
            }
        });

        jlTituloRuptura.setBackground(new java.awt.Color(51, 255, 51));
        jlTituloRuptura.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jlTituloRuptura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTituloRuptura.setText("Título");

        javax.swing.GroupLayout jPanel1RupturaLayout = new javax.swing.GroupLayout(jPanel1Ruptura);
        jPanel1Ruptura.setLayout(jPanel1RupturaLayout);
        jPanel1RupturaLayout.setHorizontalGroup(
            jPanel1RupturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1RupturaLayout.createSequentialGroup()
                .addGroup(jPanel1RupturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1RupturaLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1RupturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1RupturaLayout.createSequentialGroup()
                                .addGroup(jPanel1RupturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jlMat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1RupturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtQTD_Perdida, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                                    .addComponent(jtCod_Interno)
                                    .addComponent(jtMatriculaRuptura))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1RupturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtDescricaoItem)
                                    .addComponent(jtObservacao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtNomeUsuarioR)))
                            .addGroup(jPanel1RupturaLayout.createSequentialGroup()
                                .addComponent(jbSalvarRuptura, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jbCancelarRuptura, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jtLoja, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtDataDoRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))))
                    .addComponent(jlTituloRuptura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1RupturaLayout.setVerticalGroup(
            jPanel1RupturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1RupturaLayout.createSequentialGroup()
                .addComponent(jlTituloRuptura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1RupturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtNomeUsuarioR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtMatriculaRuptura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlMat, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1RupturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtCod_Interno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtDescricaoItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1RupturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtQTD_Perdida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1RupturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtLoja, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtDataDoRegistro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1RupturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbSalvarRuptura)
                        .addComponent(jbCancelarRuptura)))
                .addGap(8, 8, 8))
        );

        jTabbedPane1.addTab("Ruptura", jPanel1Ruptura);

        jPanel2Campanhas.setBackground(new java.awt.Color(255, 255, 204));
        jPanel2Campanhas.setPreferredSize(new java.awt.Dimension(460, 156));

        jlTituloCampanha.setBackground(new java.awt.Color(51, 255, 51));
        jlTituloCampanha.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jlTituloCampanha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTituloCampanha.setText("Título");

        jlMat1.setBackground(new java.awt.Color(153, 255, 0));
        jlMat1.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jlMat1.setForeground(new java.awt.Color(0, 0, 153));
        jlMat1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlMat1.setText("Matrícula");

        jtMatriculaCampanha.setBackground(new java.awt.Color(255, 255, 102));
        jtMatriculaCampanha.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jtMatriculaCampanha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtMatriculaCampanha.setDisabledTextColor(new java.awt.Color(255, 153, 51));
        jtMatriculaCampanha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtMatriculaCampanhaMouseClicked(evt);
            }
        });
        jtMatriculaCampanha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtMatriculaCampanhaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtMatriculaCampanhaKeyReleased(evt);
            }
        });

        jtNomeUsuarioCampanha.setEditable(false);
        jtNomeUsuarioCampanha.setBackground(new java.awt.Color(102, 255, 153));
        jtNomeUsuarioCampanha.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jtNomeUsuarioCampanha.setForeground(new java.awt.Color(255, 0, 0));
        jtNomeUsuarioCampanha.setDisabledTextColor(new java.awt.Color(255, 153, 51));

        jlMat23.setBackground(new java.awt.Color(153, 255, 0));
        jlMat23.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jlMat23.setForeground(new java.awt.Color(0, 0, 153));
        jlMat23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlMat23.setText("QTD PROD");

        jtQtdProdutoCampanha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jtQtdProdutoCampanha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtQtdProdutoCampanha.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jtQtdProdutoCampanha.setMinimumSize(new java.awt.Dimension(12, 26));
        jtQtdProdutoCampanha.setPreferredSize(new java.awt.Dimension(12, 26));
        jtQtdProdutoCampanha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtQtdProdutoCampanhaActionPerformed(evt);
            }
        });

        jbSalvarCampanha.setBackground(new java.awt.Color(0, 0, 102));
        jbSalvarCampanha.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jbSalvarCampanha.setForeground(new java.awt.Color(255, 255, 255));
        jbSalvarCampanha.setText("Salvar Campanha");
        jbSalvarCampanha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvarCampanhaActionPerformed(evt);
            }
        });
        jbSalvarCampanha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jbSalvarCampanhaKeyPressed(evt);
            }
        });

        jbCancelarCampanha.setBackground(new java.awt.Color(204, 0, 0));
        jbCancelarCampanha.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jbCancelarCampanha.setForeground(new java.awt.Color(255, 255, 255));
        jbCancelarCampanha.setText("Limpar");
        jbCancelarCampanha.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbCancelarCampanha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarCampanhaActionPerformed(evt);
            }
        });

        jtLoja1Campanha.setEditable(false);
        jtLoja1Campanha.setBackground(new java.awt.Color(255, 153, 102));
        jtLoja1Campanha.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jtLoja1Campanha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtLoja1Campanha.setDisabledTextColor(new java.awt.Color(255, 153, 51));

        jtDataDoRegistroCampanha.setBackground(new java.awt.Color(255, 204, 204));
        try {
            jtDataDoRegistroCampanha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtDataDoRegistroCampanha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtDataDoRegistroCampanha.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jtDataDoRegistroCampanha.setMinimumSize(new java.awt.Dimension(12, 26));
        jtDataDoRegistroCampanha.setPreferredSize(new java.awt.Dimension(127, 26));
        jtDataDoRegistroCampanha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtDataDoRegistroCampanhaKeyPressed(evt);
            }
        });

        jcCampanhaObs.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione Uma Campanha" }));
        jcCampanhaObs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcCampanhaObsKeyPressed(evt);
            }
        });

        jLabelAvisos.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabelAvisos.setForeground(new java.awt.Color(204, 0, 0));
        jLabelAvisos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAvisos.setText(".");

        javax.swing.GroupLayout jPanel2CampanhasLayout = new javax.swing.GroupLayout(jPanel2Campanhas);
        jPanel2Campanhas.setLayout(jPanel2CampanhasLayout);
        jPanel2CampanhasLayout.setHorizontalGroup(
            jPanel2CampanhasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2CampanhasLayout.createSequentialGroup()
                .addGroup(jPanel2CampanhasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelAvisos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlTituloCampanha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2CampanhasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2CampanhasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2CampanhasLayout.createSequentialGroup()
                                .addGroup(jPanel2CampanhasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2CampanhasLayout.createSequentialGroup()
                                        .addComponent(jlMat23, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtQtdProdutoCampanha, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2CampanhasLayout.createSequentialGroup()
                                        .addComponent(jlMat1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtMatriculaCampanha, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2CampanhasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtNomeUsuarioCampanha)
                                    .addComponent(jcCampanhaObs, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2CampanhasLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jbSalvarCampanha)
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addComponent(jbCancelarCampanha, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addComponent(jtLoja1Campanha, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jtDataDoRegistroCampanha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel2CampanhasLayout.setVerticalGroup(
            jPanel2CampanhasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2CampanhasLayout.createSequentialGroup()
                .addComponent(jlTituloCampanha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2CampanhasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtNomeUsuarioCampanha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtMatriculaCampanha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlMat1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2CampanhasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlMat23, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtQtdProdutoCampanha, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcCampanhaObs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelAvisos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2CampanhasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2CampanhasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtLoja1Campanha, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtDataDoRegistroCampanha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2CampanhasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbSalvarCampanha)
                        .addComponent(jbCancelarCampanha)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Campanhas", jPanel2Campanhas);

        jPanel3PlanoDeVoo.setBackground(new java.awt.Color(0, 153, 153));

        jlTituloPlanoDeVoo.setBackground(new java.awt.Color(51, 255, 51));
        jlTituloPlanoDeVoo.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jlTituloPlanoDeVoo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTituloPlanoDeVoo.setText("Título");

        jtNomeUsuarioV.setEditable(false);
        jtNomeUsuarioV.setBackground(new java.awt.Color(102, 255, 153));
        jtNomeUsuarioV.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jtNomeUsuarioV.setForeground(new java.awt.Color(255, 0, 0));
        jtNomeUsuarioV.setDisabledTextColor(new java.awt.Color(255, 153, 51));

        jtMatriculaVoo.setBackground(new java.awt.Color(255, 255, 102));
        jtMatriculaVoo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jtMatriculaVoo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtMatriculaVoo.setDisabledTextColor(new java.awt.Color(255, 153, 51));
        jtMatriculaVoo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtMatriculaVooMouseClicked(evt);
            }
        });
        jtMatriculaVoo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtMatriculaVooKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtMatriculaVooKeyReleased(evt);
            }
        });

        jlMat26.setBackground(new java.awt.Color(153, 255, 0));
        jlMat26.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jlMat26.setForeground(new java.awt.Color(0, 0, 153));
        jlMat26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlMat26.setText("Matrícula");

        jbSalvarVoo.setBackground(new java.awt.Color(0, 0, 102));
        jbSalvarVoo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jbSalvarVoo.setForeground(new java.awt.Color(255, 255, 255));
        jbSalvarVoo.setText("Salvar");
        jbSalvarVoo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvarVooActionPerformed(evt);
            }
        });
        jbSalvarVoo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jbSalvarVooKeyPressed(evt);
            }
        });

        jbCancelarVoo.setBackground(new java.awt.Color(204, 0, 0));
        jbCancelarVoo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jbCancelarVoo.setForeground(new java.awt.Color(255, 255, 255));
        jbCancelarVoo.setText("Limpar");
        jbCancelarVoo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbCancelarVoo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarVooActionPerformed(evt);
            }
        });

        jtLoja5.setEditable(false);
        jtLoja5.setBackground(new java.awt.Color(255, 153, 102));
        jtLoja5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jtLoja5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtLoja5.setDisabledTextColor(new java.awt.Color(255, 153, 51));

        jlMat28.setBackground(new java.awt.Color(153, 255, 0));
        jlMat28.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jlMat28.setForeground(new java.awt.Color(0, 0, 153));
        jlMat28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlMat28.setText("Venda");

        jlMat29.setBackground(new java.awt.Color(153, 255, 0));
        jlMat29.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jlMat29.setForeground(new java.awt.Color(0, 0, 153));
        jlMat29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlMat29.setText("Cliente");

        jfVooVenda1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfVooVenda1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVooVenda1.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVooVenda1.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVooVenda1.setPreferredSize(new java.awt.Dimension(12, 26));
        jfVooVenda1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfVooVenda1ActionPerformed(evt);
            }
        });

        jfVooVenda2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfVooVenda2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVooVenda2.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVooVenda2.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVooVenda2.setPreferredSize(new java.awt.Dimension(12, 26));
        jfVooVenda2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfVooVenda2ActionPerformed(evt);
            }
        });

        jfVooVenda4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfVooVenda4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVooVenda4.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVooVenda4.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVooVenda4.setPreferredSize(new java.awt.Dimension(12, 26));
        jfVooVenda4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfVooVenda4ActionPerformed(evt);
            }
        });

        jfVooVenda3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfVooVenda3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVooVenda3.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVooVenda3.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVooVenda3.setPreferredSize(new java.awt.Dimension(12, 26));
        jfVooVenda3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfVooVenda3ActionPerformed(evt);
            }
        });

        jfVooCli1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfVooCli1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVooCli1.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVooCli1.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVooCli1.setPreferredSize(new java.awt.Dimension(12, 26));
        jfVooCli1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfVooCli1ActionPerformed(evt);
            }
        });

        jfVooCli2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfVooCli2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVooCli2.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVooCli2.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVooCli2.setPreferredSize(new java.awt.Dimension(12, 26));
        jfVooCli2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfVooCli2ActionPerformed(evt);
            }
        });

        jfVooCli3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfVooCli3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVooCli3.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVooCli3.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVooCli3.setPreferredSize(new java.awt.Dimension(12, 26));
        jfVooCli3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfVooCli3ActionPerformed(evt);
            }
        });

        jfVooCli4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfVooCli4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVooCli4.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVooCli4.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVooCli4.setPreferredSize(new java.awt.Dimension(12, 26));
        jfVooCli4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfVooCli4ActionPerformed(evt);
            }
        });

        jfVooVendaTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfVooVendaTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVooVendaTotal.setEnabled(false);
        jfVooVendaTotal.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVooVendaTotal.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jfVooVendaTotal.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVooVendaTotal.setPreferredSize(new java.awt.Dimension(12, 26));

        jfVooCliTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfVooCliTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfVooCliTotal.setEnabled(false);
        jfVooCliTotal.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfVooCliTotal.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jfVooCliTotal.setMinimumSize(new java.awt.Dimension(12, 26));
        jfVooCliTotal.setPreferredSize(new java.awt.Dimension(12, 26));

        try {
            jtDataDoRegistroVoo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtDataDoRegistroVoo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtDataDoRegistroVoo.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jtDataDoRegistroVoo.setMinimumSize(new java.awt.Dimension(12, 26));
        jtDataDoRegistroVoo.setPreferredSize(new java.awt.Dimension(127, 26));
        jtDataDoRegistroVoo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtDataDoRegistroVooActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3PlanoDeVooLayout = new javax.swing.GroupLayout(jPanel3PlanoDeVoo);
        jPanel3PlanoDeVoo.setLayout(jPanel3PlanoDeVooLayout);
        jPanel3PlanoDeVooLayout.setHorizontalGroup(
            jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3PlanoDeVooLayout.createSequentialGroup()
                .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlTituloPlanoDeVoo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3PlanoDeVooLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3PlanoDeVooLayout.createSequentialGroup()
                                .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3PlanoDeVooLayout.createSequentialGroup()
                                        .addComponent(jlMat26, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtMatriculaVoo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jlMat29))
                                .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3PlanoDeVooLayout.createSequentialGroup()
                                        .addGap(72, 72, 72)
                                        .addComponent(jfVooVenda3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jfVooVenda4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jfVooCli4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jfVooCliTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                                            .addComponent(jfVooVendaTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel3PlanoDeVooLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtNomeUsuarioV))))
                            .addGroup(jPanel3PlanoDeVooLayout.createSequentialGroup()
                                .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3PlanoDeVooLayout.createSequentialGroup()
                                        .addComponent(jlMat28, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jfVooVenda1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jfVooCli1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, 0)
                                        .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jfVooCli2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jfVooVenda2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, 0)
                                        .addComponent(jfVooCli3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3PlanoDeVooLayout.createSequentialGroup()
                                        .addComponent(jbSalvarVoo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jbCancelarVoo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jtLoja5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtDataDoRegistroVoo, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel3PlanoDeVooLayout.setVerticalGroup(
            jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3PlanoDeVooLayout.createSequentialGroup()
                .addComponent(jlTituloPlanoDeVoo)
                .addGap(0, 0, 0)
                .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtNomeUsuarioV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtMatriculaVoo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlMat26, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3PlanoDeVooLayout.createSequentialGroup()
                        .addComponent(jfVooVendaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jfVooCliTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3PlanoDeVooLayout.createSequentialGroup()
                        .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jfVooVenda1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jfVooVenda2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlMat28, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jfVooVenda3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jfVooVenda4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jfVooCli1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jfVooCli2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlMat29, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3PlanoDeVooLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jfVooCli3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jfVooCli4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3PlanoDeVooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbSalvarVoo)
                    .addComponent(jbCancelarVoo)
                    .addComponent(jtLoja5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtDataDoRegistroVoo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Plano de Voo", jPanel3PlanoDeVoo);

        jPanel4Utilidades.setBackground(new java.awt.Color(153, 255, 0));

        jbGraficos.setBackground(new java.awt.Color(51, 255, 204));
        jbGraficos.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jbGraficos.setForeground(new java.awt.Color(0, 0, 102));
        jbGraficos.setText("Gráficos");
        jbGraficos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGraficosActionPerformed(evt);
            }
        });

        jbConfigurar.setBackground(new java.awt.Color(255, 0, 0));
        jbConfigurar.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jbConfigurar.setForeground(new java.awt.Color(0, 0, 102));
        jbConfigurar.setText("Configurar");
        jbConfigurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbConfigurarActionPerformed(evt);
            }
        });

        jbDetalhamento.setBackground(new java.awt.Color(51, 255, 204));
        jbDetalhamento.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jbDetalhamento.setForeground(new java.awt.Color(0, 0, 102));
        jbDetalhamento.setText("Detalhamento");
        jbDetalhamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDetalhamentoActionPerformed(evt);
            }
        });

        jbPlanogramas.setBackground(new java.awt.Color(51, 255, 204));
        jbPlanogramas.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jbPlanogramas.setForeground(new java.awt.Color(0, 0, 102));
        jbPlanogramas.setText("Planogramas");
        jbPlanogramas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPlanogramasActionPerformed(evt);
            }
        });

        jbEducaFarma.setBackground(new java.awt.Color(51, 255, 204));
        jbEducaFarma.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jbEducaFarma.setForeground(new java.awt.Color(0, 0, 102));
        jbEducaFarma.setText("EducaFarma");
        jbEducaFarma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEducaFarmaActionPerformed(evt);
            }
        });

        jbSobre.setBackground(new java.awt.Color(51, 255, 204));
        jbSobre.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jbSobre.setForeground(new java.awt.Color(0, 0, 102));
        jbSobre.setText("Sobre");
        jbSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSobreActionPerformed(evt);
            }
        });

        jbPopular.setBackground(new java.awt.Color(51, 255, 204));
        jbPopular.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jbPopular.setForeground(new java.awt.Color(0, 0, 102));
        jbPopular.setText("Popular");
        jbPopular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPopularActionPerformed(evt);
            }
        });

        jbEditarPlanoDeVoo.setBackground(new java.awt.Color(51, 255, 204));
        jbEditarPlanoDeVoo.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jbEditarPlanoDeVoo.setForeground(new java.awt.Color(0, 0, 102));
        jbEditarPlanoDeVoo.setText("Plano de Voo");
        jbEditarPlanoDeVoo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditarPlanoDeVooActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4UtilidadesLayout = new javax.swing.GroupLayout(jPanel4Utilidades);
        jPanel4Utilidades.setLayout(jPanel4UtilidadesLayout);
        jPanel4UtilidadesLayout.setHorizontalGroup(
            jPanel4UtilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4UtilidadesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4UtilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4UtilidadesLayout.createSequentialGroup()
                        .addGroup(jPanel4UtilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbGraficos, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbPopular, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(jPanel4UtilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbEditarPlanoDeVoo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbDetalhamento, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(jPanel4UtilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbConfigurar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbSobre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jbPlanogramas, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbEducaFarma, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4UtilidadesLayout.setVerticalGroup(
            jPanel4UtilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4UtilidadesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4UtilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbConfigurar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbGraficos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbDetalhamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4UtilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbPopular)
                    .addComponent(jbSobre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbEditarPlanoDeVoo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbPlanogramas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbEducaFarma)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Aplicativos", jPanel4Utilidades);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSobreActionPerformed
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfSobre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new JfSobre().setVisible(true);
        });
    }//GEN-LAST:event_jbSobreActionPerformed

    private void jbEducaFarmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEducaFarmaActionPerformed
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfEducaFarma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new JfEducaFarma().setVisible(true);
        });
    }//GEN-LAST:event_jbEducaFarmaActionPerformed

    private void jbPlanogramasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPlanogramasActionPerformed
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfPlanogramas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new JfPlanogramas().setVisible(true);
        });
    }//GEN-LAST:event_jbPlanogramasActionPerformed

    private void jbDetalhamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDetalhamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbDetalhamentoActionPerformed

    private void jbConfigurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbConfigurarActionPerformed
        AbreConfig();
    }//GEN-LAST:event_jbConfigurarActionPerformed

    private void jbGraficosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGraficosActionPerformed
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfPainel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new JfPainel().setVisible(true);
        });
    }//GEN-LAST:event_jbGraficosActionPerformed

    private void jtDataDoRegistroVooActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtDataDoRegistroVooActionPerformed
        jbSalvarVoo.requestFocus();
    }//GEN-LAST:event_jtDataDoRegistroVooActionPerformed

    private void jfVooCli4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfVooCli4ActionPerformed
        jtDataDoRegistroVoo.requestFocus();
    }//GEN-LAST:event_jfVooCli4ActionPerformed

    private void jfVooCli3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfVooCli3ActionPerformed
        jfVooVenda4.requestFocus();
    }//GEN-LAST:event_jfVooCli3ActionPerformed

    private void jfVooCli2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfVooCli2ActionPerformed
        jfVooVenda3.requestFocus();
    }//GEN-LAST:event_jfVooCli2ActionPerformed

    private void jfVooCli1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfVooCli1ActionPerformed
        jfVooVenda2.requestFocus();
    }//GEN-LAST:event_jfVooCli1ActionPerformed

    private void jfVooVenda3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfVooVenda3ActionPerformed
        jfVooCli3.requestFocus();
    }//GEN-LAST:event_jfVooVenda3ActionPerformed

    private void jfVooVenda4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfVooVenda4ActionPerformed
        jfVooCli4.requestFocus();
    }//GEN-LAST:event_jfVooVenda4ActionPerformed

    private void jfVooVenda2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfVooVenda2ActionPerformed
        jfVooCli2.requestFocus();
    }//GEN-LAST:event_jfVooVenda2ActionPerformed

    private void jfVooVenda1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfVooVenda1ActionPerformed
        jfVooCli1.requestFocus();
    }//GEN-LAST:event_jfVooVenda1ActionPerformed

    private void jbCancelarVooActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarVooActionPerformed
        limparCampos();
    }//GEN-LAST:event_jbCancelarVooActionPerformed

    private void jbSalvarVooKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jbSalvarVooKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                salvarPlanoDeVoo();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Falha: " + ex);
            }
        }
    }//GEN-LAST:event_jbSalvarVooKeyPressed

    private void jbSalvarVooActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarVooActionPerformed
        try {
            salvarPlanoDeVoo();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Falha: " + ex);
        }
    }//GEN-LAST:event_jbSalvarVooActionPerformed

    private void jtMatriculaVooKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtMatriculaVooKeyReleased
        pesquisarUsuarioNoBanco();
    }//GEN-LAST:event_jtMatriculaVooKeyReleased

    private void jtDataDoRegistroCampanhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtDataDoRegistroCampanhaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jbSalvarCampanha.requestFocus();
        }
    }//GEN-LAST:event_jtDataDoRegistroCampanhaKeyPressed

    private void jbCancelarCampanhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarCampanhaActionPerformed
        limparCampos();
    }//GEN-LAST:event_jbCancelarCampanhaActionPerformed

    private void jbSalvarCampanhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jbSalvarCampanhaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                salvarCamapanha();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Falha: " + ex);
            }
        }
    }//GEN-LAST:event_jbSalvarCampanhaKeyPressed

    private void jbSalvarCampanhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarCampanhaActionPerformed
        try {
            salvarCamapanha();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Falha: " + ex);
        }
    }//GEN-LAST:event_jbSalvarCampanhaActionPerformed

    private void jtQtdProdutoCampanhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtQtdProdutoCampanhaActionPerformed
        if (jtQtdProdutoCampanha.getText().equals("")) {
            jtQtdProdutoCampanha.requestFocus();
        } else {
            jcCampanhaObs.requestFocus();
        }
    }//GEN-LAST:event_jtQtdProdutoCampanhaActionPerformed

    private void jtMatriculaCampanhaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtMatriculaCampanhaKeyReleased
        pesquisarUsuarioNoBanco();
    }//GEN-LAST:event_jtMatriculaCampanhaKeyReleased

    private void jtObservacaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtObservacaoKeyPressed
        verificaOpcaoSelecionada(evt, "obs");
    }//GEN-LAST:event_jtObservacaoKeyPressed

    private void jtQTD_PerdidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtQTD_PerdidaActionPerformed
        jbSalvarRuptura.requestFocus();
    }//GEN-LAST:event_jtQTD_PerdidaActionPerformed

    private void jtMatriculaRupturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtMatriculaRupturaKeyReleased
        pesquisarUsuarioNoBanco();
    }//GEN-LAST:event_jtMatriculaRupturaKeyReleased

    private void jtMatriculaRupturaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtMatriculaRupturaKeyPressed
        verificaOpcaoSelecionada(evt, "matri");
    }//GEN-LAST:event_jtMatriculaRupturaKeyPressed

    private void jtMatriculaRupturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtMatriculaRupturaMouseClicked
        jtMatriculaRuptura.setText("");
        jtNomeUsuarioR.setText("");
    }//GEN-LAST:event_jtMatriculaRupturaMouseClicked

    private void jtDescricaoItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtDescricaoItemActionPerformed
        if ("Salvar Falta".equals(jbSalvarRuptura.getText())) {
            jtObservacao.requestFocus();
        } else if ("Cadastrar!".equals(jbSalvarRuptura.getText())) {
            jbSalvarRuptura.requestFocus();
        }
    }//GEN-LAST:event_jtDescricaoItemActionPerformed

    private void jbCancelarRupturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarRupturaActionPerformed
        cancelarRuptura();
    }//GEN-LAST:event_jbCancelarRupturaActionPerformed

    private void jbSalvarRupturaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jbSalvarRupturaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            verificarDadosAoSalvarRuptura();
//            if ("Salvar Falta".equals(jbSalvarRuptura.getText())) {
//                salvarDadosDaFaltaNoBanco();
//            } else if ("Cadastrar Produto".equals(jbSalvarRuptura.getText())) {
//                inserirUmNovoProdutoNoBanco();
//            }
        }
    }//GEN-LAST:event_jbSalvarRupturaKeyPressed

    private void jbSalvarRupturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarRupturaActionPerformed
        verificarDadosAoSalvarRuptura();
    }//GEN-LAST:event_jbSalvarRupturaActionPerformed

    private void jbSalvarRupturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbSalvarRupturaMouseClicked
        verificarDadosAoSalvarRuptura();
    }//GEN-LAST:event_jbSalvarRupturaMouseClicked

    private void jtCod_InternoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtCod_InternoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            verificaSeProdutoEstaCadastradoRuptura();
        }
    }//GEN-LAST:event_jtCod_InternoKeyPressed

    private void jtCod_InternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtCod_InternoActionPerformed
        verificaSeProdutoEstaCadastradoRuptura();
    }//GEN-LAST:event_jtCod_InternoActionPerformed

    private void jtMatriculaCampanhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtMatriculaCampanhaKeyPressed
        verificaOpcaoSelecionada(evt, "matriCamp");
    }//GEN-LAST:event_jtMatriculaCampanhaKeyPressed

    private void jtMatriculaCampanhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtMatriculaCampanhaMouseClicked
        jtMatriculaCampanha.setText("");
        jtNomeUsuarioCampanha.setText("");
    }//GEN-LAST:event_jtMatriculaCampanhaMouseClicked

    private void jtMatriculaVooKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtMatriculaVooKeyPressed
        verificaOpcaoSelecionada(evt, "matriVoo");
    }//GEN-LAST:event_jtMatriculaVooKeyPressed

    private void jtMatriculaVooMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtMatriculaVooMouseClicked
        jtMatriculaVoo.setText("");
        jtNomeUsuarioV.setText("");
    }//GEN-LAST:event_jtMatriculaVooMouseClicked

    private void jcCampanhaObsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcCampanhaObsKeyPressed
        verificaOpcaoSelecionada(evt, "obsCamp");
    }//GEN-LAST:event_jcCampanhaObsKeyPressed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        int v = jTabbedPane1.getSelectedIndex();
        switch (v) {
            case 0: {

                break;
            }
            case 1: {
                //listaCampanhasAtivas();
                break;
            }
            case 2: {

                break;
            }
            default:
                break;
        }
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jtCod_InternoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtCod_InternoMouseClicked
        jtCod_Interno.setText("");
        jtDescricaoItem.setText("");
    }//GEN-LAST:event_jtCod_InternoMouseClicked

    private void jbPopularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPopularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbPopularActionPerformed

    private void jbEditarPlanoDeVooActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarPlanoDeVooActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbEditarPlanoDeVooActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelAvisos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel1Ruptura;
    private javax.swing.JPanel jPanel2Campanhas;
    private javax.swing.JPanel jPanel3PlanoDeVoo;
    private javax.swing.JPanel jPanel4Utilidades;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton jbCancelarCampanha;
    private javax.swing.JButton jbCancelarRuptura;
    private javax.swing.JButton jbCancelarVoo;
    private javax.swing.JButton jbConfigurar;
    private javax.swing.JButton jbDetalhamento;
    private javax.swing.JButton jbEditarPlanoDeVoo;
    private javax.swing.JButton jbEducaFarma;
    private javax.swing.JButton jbGraficos;
    private javax.swing.JButton jbPlanogramas;
    private javax.swing.JButton jbPopular;
    private javax.swing.JButton jbSalvarCampanha;
    private javax.swing.JButton jbSalvarRuptura;
    private javax.swing.JButton jbSalvarVoo;
    private javax.swing.JButton jbSobre;
    private javax.swing.JComboBox<String> jcCampanhaObs;
    private javax.swing.JFormattedTextField jfVooCli1;
    private javax.swing.JFormattedTextField jfVooCli2;
    private javax.swing.JFormattedTextField jfVooCli3;
    private javax.swing.JFormattedTextField jfVooCli4;
    private javax.swing.JFormattedTextField jfVooCliTotal;
    private javax.swing.JFormattedTextField jfVooVenda1;
    private javax.swing.JFormattedTextField jfVooVenda2;
    private javax.swing.JFormattedTextField jfVooVenda3;
    private javax.swing.JFormattedTextField jfVooVenda4;
    private javax.swing.JFormattedTextField jfVooVendaTotal;
    private javax.swing.JLabel jlMat;
    private javax.swing.JLabel jlMat1;
    private javax.swing.JLabel jlMat23;
    private javax.swing.JLabel jlMat26;
    private javax.swing.JLabel jlMat28;
    private javax.swing.JLabel jlMat29;
    private javax.swing.JLabel jlTituloCampanha;
    private javax.swing.JLabel jlTituloPlanoDeVoo;
    private javax.swing.JLabel jlTituloRuptura;
    private javax.swing.JTextField jtCod_Interno;
    private javax.swing.JTextField jtDataDoRegistro;
    private javax.swing.JFormattedTextField jtDataDoRegistroCampanha;
    private javax.swing.JFormattedTextField jtDataDoRegistroVoo;
    private javax.swing.JTextField jtDescricaoItem;
    private javax.swing.JTextField jtLoja;
    private javax.swing.JTextField jtLoja1Campanha;
    private javax.swing.JTextField jtLoja5;
    private javax.swing.JTextField jtMatriculaCampanha;
    private javax.swing.JTextField jtMatriculaRuptura;
    private javax.swing.JTextField jtMatriculaVoo;
    private javax.swing.JTextField jtNomeUsuarioCampanha;
    private javax.swing.JTextField jtNomeUsuarioR;
    private javax.swing.JTextField jtNomeUsuarioV;
    private javax.swing.JComboBox<String> jtObservacao;
    private javax.swing.JTextField jtQTD_Perdida;
    private javax.swing.JFormattedTextField jtQtdProdutoCampanha;
    // End of variables declaration//GEN-END:variables

}
