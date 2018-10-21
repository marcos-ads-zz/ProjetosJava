package modulo.contas;

import modulo.versao.Versao;
import java.awt.Dimension;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modulo.metodos.Funcao;
import modulo.login.UserLogado;

/**
 *
 * @author Marcos Junior
 */
public final class JifControleDeContas extends javax.swing.JInternalFrame {

    private Versao ver;
    private JFormattedTextField[] txts;
    private JFormattedTextField[] txts2;
    private Funcao fun;
    private UserLogado login;
    private EnergiaDAO DAOENERG;
    private Energia objEnergia;
    private AguaDAO DAOAGUA;
    private Agua objAgua;
    private int num;

    public JifControleDeContas() {
        initComponents();
        ver = new Versao();
        login = new UserLogado();
        fun = new Funcao();
        DAOENERG = new EnergiaDAO();
        DAOAGUA = new AguaDAO();
        setTitle("Controle de Consumo de Agua e Luz: " + ver.getVersao());
        txts = new JFormattedTextField[]{
            jfCodigo,
            jfNotaFiscal,
            jfDataEmissao,
            jfDataVencimento,
            jfValorTotal,
            jfConsumoKWH};
        txts2 = new JFormattedTextField[]{
            jfCodigo1,
            jfNotaFiscal1,
            jfDataEmissao1,
            jfDataVencimento1,
            jfValorTotal1,
            jfConsumo};
        listaNaTabela();
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    public void HabilitaEdicao() {
        num = jTabbedPane1.getSelectedIndex();
        switch (num) {
            case 0:
                jbSalvar.setEnabled(false);
                jbEditar.setEnabled(true);
                jbExcluir.setEnabled(true);
                break;
            case 1:
                jbSalvar1.setEnabled(false);
                jbEditar1.setEnabled(true);
                jbExcluir1.setEnabled(true);
                break;
            default:
                break;
        }

    }

    public void HabilitaTudo() {
        num = jTabbedPane1.getSelectedIndex();
        switch (num) {
            case 0:
                for (JFormattedTextField txt : txts) {
                    txt.setEnabled(true);
                }
                break;
            case 1:
                for (JFormattedTextField txt : txts2) {
                    txt.setEnabled(true);
                }
                break;
            default:

                break;
        }
    }

    public void DesabilitaTudo() {
        num = jTabbedPane1.getSelectedIndex();
        switch (num) {
            case 0:
                for (JFormattedTextField txt : txts) {
                    txt.setEnabled(false);
                }
                break;
            case 1:
                for (JFormattedTextField txt : txts2) {
                    txt.setEnabled(false);
                }
                break;
            default:

                break;
        }
    }

    public void listaNaTabela() {
        try {
            SetaDados();
            ListarTudo();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro Dados: " + ex.getMessage());
        }
    }

    public void limpaCampos() {
        num = jTabbedPane1.getSelectedIndex();
        switch (num) {
            case 0:
                for (JFormattedTextField txt : txts) {
                    if (txt.getText().equals("")) {
                    } else {
                        txt.setText("");
                    }
                }
                jfId.setText("");
                jbSalvar.setEnabled(true);
                jbEditar.setEnabled(false);
                jbExcluir.setEnabled(false);
                break;
            case 1:
                for (JFormattedTextField txt : txts2) {
                    if (txt.getText().equals("")) {
                    } else {
                        txt.setText("");
                    }
                }
                jfId1.setText("");
                jbSalvar1.setEnabled(true);
                jbEditar1.setEnabled(false);
                jbExcluir1.setEnabled(false);
                break;
            default:

                break;
        }
    }

    public boolean validaCampos() {
        num = jTabbedPane1.getSelectedIndex();
        switch (num) {
            case 0:
                for (JFormattedTextField txt : txts) {
                    if (txt.getText().equals("")) {
                        txt.setBackground(new java.awt.Color(255, 0, 0));
                    } else {
                        txt.setBackground(new java.awt.Color(214, 217, 223));
                    }
                }
                break;
            case 1:
                for (JFormattedTextField txt : txts2) {
                    if (txt.getText().equals("")) {
                        txt.setBackground(new java.awt.Color(255, 0, 0));
                    } else {
                        txt.setBackground(new java.awt.Color(214, 217, 223));
                    }
                }
                break;
            default:

                break;
        }
        return true;
    }

    public boolean validaCamposBreak() {
        boolean check = true;
        num = jTabbedPane1.getSelectedIndex();
        switch (num) {
            case 0:
                for (JFormattedTextField txt : txts) {
                    if (txt.getText().equals("")) {
                        check = false;
                        break;
                    }
                }
                break;
            case 1:
                for (JFormattedTextField txt : txts2) {
                    if (txt.getText().equals("")) {
                        check = false;
                        break;
                    }
                }
                break;
            default:

                break;
        }

        return check;
    }

    public void SetaDados() throws Exception {
        int loja = login.getNumeroloja();
        String matricula = login.getMatricula();

    }

    public boolean Salvar() throws Exception {
        boolean check;
        num = jTabbedPane1.getSelectedIndex();
        if (validaCampos()) {
            if (validaCamposBreak()) {
                switch (num) {
                    case 0:
                        if (PreencheDadosSalvar()) {
                            check = DAOENERG.Insert(objEnergia);
                            JOptionPane.showMessageDialog(this, "Salvo Com Sucesso!");
                            listaNaTabela();
                            limpaCampos();
                        } else {
                            check = false;
                        }
                        break;
                    case 1:
                        if (PreencheDadosSalvar()) {
                            check = DAOAGUA.Insert(objAgua);
                            JOptionPane.showMessageDialog(this, "Salvo Com Sucesso!");
                            listaNaTabela();
                            limpaCampos();
                        } else {
                            check = false;
                        }
                        break;
                    default:
                        check = false;
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Preencha os Campos em Vermelho");
                check = false;
            }
        } else {
            check = false;
        }
        return check;

    }

    public boolean Editar() throws Exception {
        boolean check;
        num = jTabbedPane1.getSelectedIndex();
        if (validaCampos()) {
            if (validaCamposBreak()) {
                switch (num) {
                    case 0:
                        if (PreencheDadosEditar()) {
                            check = DAOENERG.Update(objEnergia);
                            JOptionPane.showMessageDialog(this, "Editado Com Sucesso!");
                            listaNaTabela();
                            limpaCampos();
                        } else {
                            check = false;
                        }
                        break;
                    case 1:
                        if (PreencheDadosEditar()) {
                            check = DAOAGUA.Update(objAgua);
                            JOptionPane.showMessageDialog(this, "Editado Com Sucesso!");
                            listaNaTabela();
                            limpaCampos();
                        } else {
                            check = false;
                        }
                        break;
                    default:
                        check = false;
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Preencha os Campos em Vermelho");
                check = false;
            }
        } else {
            check = false;
        }
        return check;
    }

    public void ListarTudo() throws Exception {
        List<Energia> energia;
        List<Agua> agua;
        num = jTabbedPane1.getSelectedIndex();
        switch (num) {
            case 0:
                energia = DAOENERG.TabelaPesquisaTodos();
                ListaPesquisaAnoEnergia(energia);
                break;
            case 1:
                agua = DAOAGUA.TabelaPesquisaTodos();
                ListaPesquisaAnoAgua(agua);
                break;
            default:

                break;
        }

    }

    public void ListarAuto() throws Exception {
        List<Energia> energia;
        List<Agua> agua;
        num = jTabbedPane1.getSelectedIndex();
        switch (num) {
            case 0:
                energia = DAOENERG.TabelaPesquisaPorAno(fun.convertToDouble(fun.anoAtualDate()));
                ListaPesquisaAnoEnergia(energia);
                break;
            case 1:
                agua = DAOAGUA.TabelaPesquisaPorAno(fun.convertToDouble(fun.anoAtualDate()));
                ListaPesquisaAnoAgua(agua);
                break;
            default:

                break;
        }

    }

    public void PesquisarAno(String ano) throws Exception {
        List<Energia> energia;
        List<Agua> agua;
        num = jTabbedPane1.getSelectedIndex();
        switch (num) {
            case 0:
                if (!jfCodigo.getText().equals("")) {
                    energia = DAOENERG.TabelaPesquisaPorAno(fun.convertToDouble(ano));
                    ListaPesquisaAnoEnergia(energia);
                }
                break;
            case 1:
                if (!jfCodigo1.getText().equals("")) {
                    agua = DAOAGUA.TabelaPesquisaPorAno(fun.convertToDouble(ano));
                    ListaPesquisaAnoAgua(agua);
                }
                break;
            default:

                break;
        }

    }

    public boolean Excluir() throws Exception {
        boolean check;
        num = jTabbedPane1.getSelectedIndex();
        switch (num) {
            case 0:
                if (!jfId.getText().equals("")) {
                    check = DAOENERG.Delete(Integer.parseInt(jfId.getText()));
                    JOptionPane.showMessageDialog(this, "Exluido Com Sucesso!");
                    listaNaTabela();
                } else {
                    check = false;
                }
                break;
            case 1:
                if (!jfId1.getText().equals("")) {
                    check = DAOAGUA.Delete(Integer.parseInt(jfId1.getText()));
                    JOptionPane.showMessageDialog(this, "Exluido Com Sucesso!");
                    listaNaTabela();
                } else {
                    check = false;
                }
                break;
            default:
                check = false;
                break;
        }

        return check;
    }

    public boolean testaDados() {
        num = jTabbedPane1.getSelectedIndex();
        switch (num) {
            case 0:
                for (JFormattedTextField txt : txts) {
                    if (txt.getText().equals("")) {
                        txt.setBackground(new java.awt.Color(255, 0, 0));
                    } else {
                        txt.setBackground(new java.awt.Color(214, 217, 223));
                    }
                }
                break;
            case 1:
                for (JFormattedTextField txt : txts2) {
                    if (txt.getText().equals("")) {
                        txt.setBackground(new java.awt.Color(255, 0, 0));
                    } else {
                        txt.setBackground(new java.awt.Color(214, 217, 223));
                    }
                }
                break;
            default:

                break;
        }
        return true;
    }

    public void CarregaDados() {
        num = jTabbedPane1.getSelectedIndex();
        switch (num) {
            case 0:
                if (jTabela.getRowCount() >= 1) {
                    if (jTabela.getSelectedRow() != -1) {
                        HabilitaEdicao();
                        Object id, cod, nota, emissao, vencimento,
                                total, consumo;

                        id = jTabela.getValueAt(jTabela.getSelectedRow(), 0);
                        jfId.setText(id.toString());

                        cod = jTabela.getValueAt(jTabela.getSelectedRow(), 1);
                        jfCodigo.setText(cod.toString());

                        nota = jTabela.getValueAt(jTabela.getSelectedRow(), 2);
                        jfNotaFiscal.setText(nota.toString());

                        emissao = jTabela.getValueAt(jTabela.getSelectedRow(), 3);
                        jfDataEmissao.setText(emissao.toString());

                        vencimento = jTabela.getValueAt(jTabela.getSelectedRow(), 4);
                        jfDataVencimento.setText(vencimento.toString());

                        total = jTabela.getValueAt(jTabela.getSelectedRow(), 5);
                        jfValorTotal.setText(total.toString());

                        consumo = jTabela.getValueAt(jTabela.getSelectedRow(), 6);
                        jfConsumoKWH.setText(consumo.toString());
                    }
                }
                break;
            case 1:
                if (jTabela1.getRowCount() >= 1) {
                    if (jTabela1.getSelectedRow() != -1) {
                        HabilitaEdicao();
                        Object id, cod, nota, emissao, vencimento,
                                total, consumo;

                        id = jTabela1.getValueAt(jTabela1.getSelectedRow(), 0);
                        jfId1.setText(id.toString());

                        cod = jTabela1.getValueAt(jTabela1.getSelectedRow(), 1);
                        jfCodigo1.setText(cod.toString());

                        nota = jTabela1.getValueAt(jTabela1.getSelectedRow(), 2);
                        jfNotaFiscal1.setText(nota.toString());

                        emissao = jTabela1.getValueAt(jTabela1.getSelectedRow(), 3);
                        jfDataEmissao1.setText(emissao.toString());

                        vencimento = jTabela1.getValueAt(jTabela1.getSelectedRow(), 4);
                        jfDataVencimento1.setText(vencimento.toString());

                        total = jTabela1.getValueAt(jTabela1.getSelectedRow(), 5);
                        jfValorTotal1.setText(total.toString());

                        consumo = jTabela1.getValueAt(jTabela1.getSelectedRow(), 6);
                        jfConsumo.setText(consumo.toString());
                    }
                }
                break;
            default:

                break;
        }

    }

    public boolean PreencheDadosSalvar() throws ParseException {
        boolean check;
        num = jTabbedPane1.getSelectedIndex();
        switch (num) {
            case 0:
                objEnergia = new Energia();
                objEnergia.setCodigo(Integer.parseInt(jfCodigo.getText()));
                objEnergia.setNotafiscal(Integer.parseInt(jfNotaFiscal.getText()));
                objEnergia.setDate_emissao(fun.convertDateStringToDateSQL(jfDataEmissao.getText()));
                objEnergia.setDate_vencimento(fun.convertDateStringToDateSQL(jfDataVencimento.getText()));
                objEnergia.setValor_conta(fun.convertToDouble(jfValorTotal.getText()));
                objEnergia.setConsumo(Integer.parseInt(jfConsumoKWH.getText()));
                objEnergia.setAno(fun.anoAtualDate());
                check = objEnergia != null;
                break;
            case 1:
                objAgua = new Agua();
                objAgua.setMatricula(Integer.parseInt(jfCodigo1.getText()));
                objAgua.setNotafiscal(Integer.parseInt(jfNotaFiscal1.getText()));
                objAgua.setDate_emissao(fun.convertDateStringToDateSQL(jfDataEmissao1.getText()));
                objAgua.setDate_vencimento(fun.convertDateStringToDateSQL(jfDataVencimento1.getText()));
                objAgua.setValor_conta(fun.convertToDouble(jfValorTotal1.getText()));
                objAgua.setConsumo(Integer.parseInt(jfConsumo.getText()));
                objAgua.setAno(fun.anoAtualDate());
                check = objAgua != null;
                break;
            default:
                check = false;
                break;
        }

        return check;
    }

    public boolean PreencheDadosEditar() throws ParseException {
        boolean check;
        num = jTabbedPane1.getSelectedIndex();
        switch (num) {
            case 0:
                objEnergia = new Energia();
                objEnergia.setCodigo(Integer.parseInt(jfCodigo.getText()));
                objEnergia.setNotafiscal(Integer.parseInt(jfNotaFiscal.getText()));
                objEnergia.setDate_emissao(fun.convertDateStringToDateSQL(jfDataEmissao.getText()));
                objEnergia.setDate_vencimento(fun.convertDateStringToDateSQL(jfDataVencimento.getText()));
                objEnergia.setValor_conta(fun.convertToDouble(jfValorTotal.getText()));
                objEnergia.setConsumo(Integer.parseInt(jfConsumoKWH.getText()));
                objEnergia.setAno(fun.anoAtualDate());
                objEnergia.setId_energ(Integer.parseInt(jfId.getText()));
                check = objEnergia != null;
                break;
            case 1:
                objAgua = new Agua();
                objAgua.setMatricula(Integer.parseInt(jfCodigo1.getText()));
                objAgua.setNotafiscal(Integer.parseInt(jfNotaFiscal1.getText()));
                objAgua.setDate_emissao(fun.convertDateStringToDateSQL(jfDataEmissao1.getText()));
                objAgua.setDate_vencimento(fun.convertDateStringToDateSQL(jfDataVencimento1.getText()));
                objAgua.setValor_conta(fun.convertToDouble(jfValorTotal1.getText()));
                objAgua.setConsumo(Integer.parseInt(jfConsumo.getText()));
                objAgua.setAno(fun.anoAtualDate());
                objAgua.setId_agua(Integer.parseInt(jfId1.getText()));
                check = objAgua != null;
                break;
            default:
                check = false;
                break;
        }
        return check;
    }

    public void ListaPesquisaAnoEnergia(List<Energia> energ) throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jTabela.getModel();
        modelo.setNumRows(0);
        energ.forEach((p) -> {
            try {
                modelo.addRow(new Object[]{
                    p.getId_energ(), p.getCodigo(), p.getNotafiscal(),
                    fun.convertDataSQLToDateString(p.getDate_emissao()),
                    fun.convertDataSQLToDateString(p.getDate_vencimento()),
                    fun.convertDoubleToStringMoeda(p.getValor_conta()),
                    p.getConsumo(), p.getAno()

                });
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro Pesquisa Ano: " + ex.getMessage());
            }
        });
    }

    public void ListaPesquisaAnoAgua(List<Agua> agua) throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jTabela1.getModel();
        modelo.setNumRows(0);
        agua.forEach((p) -> {
            try {
                modelo.addRow(new Object[]{
                    p.getId_agua(), p.getMatricula(), p.getNotafiscal(),
                    fun.convertDataSQLToDateString(p.getDate_emissao()),
                    fun.convertDataSQLToDateString(p.getDate_vencimento()),
                    fun.convertDoubleToStringMoeda(p.getValor_conta()),
                    p.getConsumo(), p.getAno()

                });
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro Pesquisa Ano: " + ex.getMessage());
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabela = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabelCodigo = new javax.swing.JLabel();
        jfCodigo = new javax.swing.JFormattedTextField();
        jfDataEmissao = new javax.swing.JFormattedTextField();
        jfDataVencimento = new javax.swing.JFormattedTextField();
        jfValorTotal = new javax.swing.JFormattedTextField();
        jfConsumoKWH = new javax.swing.JFormattedTextField();
        jfNotaFiscal = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jfId = new javax.swing.JFormattedTextField();
        jbSalvar = new javax.swing.JButton();
        jbEditar = new javax.swing.JButton();
        jbExcluir = new javax.swing.JButton();
        jCheckBoxPesquisa = new javax.swing.JCheckBox();
        jbCancelar = new javax.swing.JButton();
        jbListarEnergia = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTabela1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabelCodigo1 = new javax.swing.JLabel();
        jfCodigo1 = new javax.swing.JFormattedTextField();
        jfDataEmissao1 = new javax.swing.JFormattedTextField();
        jfDataVencimento1 = new javax.swing.JFormattedTextField();
        jfValorTotal1 = new javax.swing.JFormattedTextField();
        jfConsumo = new javax.swing.JFormattedTextField();
        jfNotaFiscal1 = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jfId1 = new javax.swing.JFormattedTextField();
        jbSalvar1 = new javax.swing.JButton();
        jbEditar1 = new javax.swing.JButton();
        jbExcluir1 = new javax.swing.JButton();
        jCheckBoxPesquisa1 = new javax.swing.JCheckBox();
        jbCancelar1 = new javax.swing.JButton();
        jbListarAgua = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jTabbedPane1.setBackground(new java.awt.Color(51, 51, 255));
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        jTabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CÓDIGO", "NOTA FISCAL", "EMISSÃO", "VENCIMENTO", "VALOR", "CONSUMO Kwh", "LANCAMENTO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTabela);
        if (jTabela.getColumnModel().getColumnCount() > 0) {
            jTabela.getColumnModel().getColumn(0).setMaxWidth(45);
            jTabela.getColumnModel().getColumn(1).setMinWidth(85);
            jTabela.getColumnModel().getColumn(2).setMinWidth(100);
            jTabela.getColumnModel().getColumn(3).setMinWidth(100);
        }

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel1.setText("Data de Emissão");

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel2.setText("Data de Vencimento");

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel3.setText("Valor Total");

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel4.setText("Consumo KWH");

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel5.setText("Nº Nota Fiscal");

        jLabelCodigo.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabelCodigo.setText("Código Único");

        jfCodigo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfCodigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfCodigo.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfCodigoActionPerformed(evt);
            }
        });

        try {
            jfDataEmissao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jfDataEmissao.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfDataEmissao.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfDataEmissao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfDataEmissaoActionPerformed(evt);
            }
        });

        try {
            jfDataVencimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jfDataVencimento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfDataVencimento.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfDataVencimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfDataVencimentoActionPerformed(evt);
            }
        });

        jfValorTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfValorTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfValorTotal.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfValorTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfValorTotalActionPerformed(evt);
            }
        });

        jfConsumoKWH.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfConsumoKWH.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfConsumoKWH.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfConsumoKWH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfConsumoKWHActionPerformed(evt);
            }
        });

        jfNotaFiscal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfNotaFiscal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfNotaFiscal.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfNotaFiscal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfNotaFiscalActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel7.setText("ID");

        jfId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfId.setEnabled(false);
        jfId.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);

        jbSalvar.setText("Salvar");
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

        jCheckBoxPesquisa.setText("Habilitar Pesquisa");
        jCheckBoxPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxPesquisaActionPerformed(evt);
            }
        });

        jbCancelar.setText("Cancelar");
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
            }
        });

        jbListarEnergia.setBackground(new java.awt.Color(51, 0, 255));
        jbListarEnergia.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jbListarEnergia.setForeground(new java.awt.Color(51, 255, 51));
        jbListarEnergia.setText("Listar Todos");
        jbListarEnergia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbListarEnergiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jfNotaFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jfDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jfDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jfId, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jfConsumoKWH, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jfValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxPesquisa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbCancelar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbListarEnergia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelCodigo)
                            .addComponent(jfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jfNotaFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jfDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jfDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jfValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBoxPesquisa)
                            .addComponent(jbCancelar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jbSalvar)
                                .addComponent(jbEditar)
                                .addComponent(jbExcluir)
                                .addComponent(jbListarEnergia))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(jfConsumoKWH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Energia", jPanel1);

        jPanel2.setBackground(new java.awt.Color(0, 0, 255));

        jTabela1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CÓDIGO", "NOTA FISCAL", "EMISSÃO", "VENCIMENTO", "VALOR", "CONSUMO Ml", "LANCAMENTO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTabela1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabela1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTabela1);
        if (jTabela1.getColumnModel().getColumnCount() > 0) {
            jTabela1.getColumnModel().getColumn(0).setMaxWidth(45);
            jTabela1.getColumnModel().getColumn(1).setMinWidth(85);
            jTabela1.getColumnModel().getColumn(2).setMinWidth(100);
            jTabela1.getColumnModel().getColumn(3).setMinWidth(100);
        }

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel6.setText("Data de Emissão");
        jLabel6.setMaximumSize(new java.awt.Dimension(74, 16));
        jLabel6.setMinimumSize(new java.awt.Dimension(74, 16));
        jLabel6.setPreferredSize(new java.awt.Dimension(74, 16));

        jLabel8.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel8.setText("Data de Vencimento");

        jLabel9.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel9.setText("Valor Total");

        jLabel10.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel10.setText("Consumo ML");

        jLabel11.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel11.setText("Nº Nota Fiscal");
        jLabel11.setMaximumSize(new java.awt.Dimension(74, 16));
        jLabel11.setMinimumSize(new java.awt.Dimension(74, 16));
        jLabel11.setPreferredSize(new java.awt.Dimension(74, 16));

        jLabelCodigo1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabelCodigo1.setText("Código Único");

        jfCodigo1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfCodigo1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfCodigo1.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfCodigo1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jfCodigo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfCodigo1ActionPerformed(evt);
            }
        });

        try {
            jfDataEmissao1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jfDataEmissao1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfDataEmissao1.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfDataEmissao1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jfDataEmissao1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfDataEmissao1ActionPerformed(evt);
            }
        });

        try {
            jfDataVencimento1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jfDataVencimento1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfDataVencimento1.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfDataVencimento1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jfDataVencimento1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfDataVencimento1ActionPerformed(evt);
            }
        });

        jfValorTotal1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfValorTotal1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfValorTotal1.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfValorTotal1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jfValorTotal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfValorTotal1ActionPerformed(evt);
            }
        });

        jfConsumo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfConsumo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfConsumo.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfConsumo.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jfConsumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfConsumoActionPerformed(evt);
            }
        });

        jfNotaFiscal1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfNotaFiscal1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfNotaFiscal1.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfNotaFiscal1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jfNotaFiscal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfNotaFiscal1ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel12.setText("ID");

        jfId1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfId1.setEnabled(false);
        jfId1.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);

        jbSalvar1.setText("Salvar");
        jbSalvar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvar1ActionPerformed(evt);
            }
        });

        jbEditar1.setText("Editar");
        jbEditar1.setEnabled(false);
        jbEditar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditar1ActionPerformed(evt);
            }
        });

        jbExcluir1.setText("Excluir");
        jbExcluir1.setEnabled(false);
        jbExcluir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExcluir1ActionPerformed(evt);
            }
        });

        jCheckBoxPesquisa1.setText("Habilitar Pesquisa");
        jCheckBoxPesquisa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxPesquisa1ActionPerformed(evt);
            }
        });

        jbCancelar1.setText("Cancelar");
        jbCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelar1ActionPerformed(evt);
            }
        });

        jbListarAgua.setBackground(new java.awt.Color(255, 255, 0));
        jbListarAgua.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jbListarAgua.setText("Listar Todos");
        jbListarAgua.setToolTipText("");
        jbListarAgua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbListarAguaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCodigo1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jfNotaFiscal1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jfDataEmissao1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jfCodigo1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jfDataVencimento1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jfId1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jfConsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbSalvar1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbEditar1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbExcluir1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jfValorTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jCheckBoxPesquisa1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbCancelar1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbListarAgua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 871, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jfCodigo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelCodigo1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jfNotaFiscal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jfDataEmissao1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jfDataVencimento1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(jfId1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jfValorTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBoxPesquisa1)
                            .addComponent(jbCancelar1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jbSalvar1)
                                .addComponent(jbEditar1)
                                .addComponent(jbExcluir1)
                                .addComponent(jbListarAgua))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(jfConsumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Água", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarActionPerformed
        try {
            Salvar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Salvar Dados!! " + ex.getMessage());
        }
    }//GEN-LAST:event_jbSalvarActionPerformed

    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarActionPerformed
        try {
            Editar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Editar Dados!! " + ex.getMessage());
        }
    }//GEN-LAST:event_jbEditarActionPerformed

    private void jbExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExcluirActionPerformed
        try {
            int resposta;
            resposta = JOptionPane.showConfirmDialog(this, "Deseja Gerar o Relatório? ", "Aviso", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                Excluir();
            } else {
                limpaCampos();
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Excluir Dados!! " + ex.getMessage());
        }
    }//GEN-LAST:event_jbExcluirActionPerformed

    private void jfCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfCodigoActionPerformed
        if (jCheckBoxPesquisa.isSelected()) {
            if (!jfCodigo.getText().equals("")) {
                try {
                    PesquisarAno(jfCodigo.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao Pesquisar Dados!! " + ex.getMessage());
                }
            }
        } else {
            if (!jfCodigo.getText().equals("")) {
                jfNotaFiscal.requestFocus();
            }
        }
    }//GEN-LAST:event_jfCodigoActionPerformed

    private void jfNotaFiscalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfNotaFiscalActionPerformed
        if (!jfNotaFiscal.getText().equals("")) {
            jfDataEmissao.requestFocus();
        }
    }//GEN-LAST:event_jfNotaFiscalActionPerformed

    private void jfDataEmissaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfDataEmissaoActionPerformed
        if (!jfDataEmissao.getText().equals("")) {
            jfDataVencimento.requestFocus();
        }
    }//GEN-LAST:event_jfDataEmissaoActionPerformed

    private void jfDataVencimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfDataVencimentoActionPerformed
        if (!jfDataVencimento.getText().equals("")) {
            jfValorTotal.requestFocus();
        }
    }//GEN-LAST:event_jfDataVencimentoActionPerformed

    private void jfValorTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfValorTotalActionPerformed
        if (!jfValorTotal.getText().equals("")) {
            jfConsumoKWH.requestFocus();
        }
    }//GEN-LAST:event_jfValorTotalActionPerformed

    private void jfConsumoKWHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfConsumoKWHActionPerformed
        if (!jfConsumoKWH.getText().equals("")) {
            jbSalvar.requestFocus();
        }
    }//GEN-LAST:event_jfConsumoKWHActionPerformed

    private void jCheckBoxPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxPesquisaActionPerformed
        if (jCheckBoxPesquisa.isSelected()) {
            try {
                jLabelCodigo.setText("Ano Da Pesquisa");
                DesabilitaTudo();
                limpaCampos();
                jfCodigo.setEnabled(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao Pesquisar Dados!! " + ex.getMessage());
            }
        } else {
            HabilitaTudo();
            limpaCampos();
            jLabelCodigo.setText("Código Único");
        }
    }//GEN-LAST:event_jCheckBoxPesquisaActionPerformed

    private void jTabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabelaMouseClicked
        CarregaDados();
    }//GEN-LAST:event_jTabelaMouseClicked

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        limpaCampos();
    }//GEN-LAST:event_jbCancelarActionPerformed

    private void jTabela1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabela1MouseClicked
        CarregaDados();
    }//GEN-LAST:event_jTabela1MouseClicked

    private void jfCodigo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfCodigo1ActionPerformed
        if (jCheckBoxPesquisa1.isSelected()) {
            if (!jfCodigo1.getText().equals("")) {
                try {
                    PesquisarAno(jfCodigo1.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao Pesquisar Dados!! " + ex.getMessage());
                }
            }
        } else {
            if (!jfCodigo1.getText().equals("")) {
                jfNotaFiscal1.requestFocus();
            }
        }
    }//GEN-LAST:event_jfCodigo1ActionPerformed

    private void jfDataEmissao1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfDataEmissao1ActionPerformed
        if (!jfDataEmissao1.getText().equals("")) {
            jfDataVencimento1.requestFocus();
        }
    }//GEN-LAST:event_jfDataEmissao1ActionPerformed

    private void jfDataVencimento1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfDataVencimento1ActionPerformed
        if (!jfDataVencimento1.getText().equals("")) {
            jfValorTotal1.requestFocus();
        }
    }//GEN-LAST:event_jfDataVencimento1ActionPerformed

    private void jfValorTotal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfValorTotal1ActionPerformed
        if (!jfValorTotal1.getText().equals("")) {
            jfConsumo.requestFocus();
        }
    }//GEN-LAST:event_jfValorTotal1ActionPerformed

    private void jfConsumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfConsumoActionPerformed
        if (!jfConsumo.getText().equals("")) {
            jbSalvar1.requestFocus();
        }
    }//GEN-LAST:event_jfConsumoActionPerformed

    private void jfNotaFiscal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfNotaFiscal1ActionPerformed
        if (!jfNotaFiscal1.getText().equals("")) {
            jfDataEmissao1.requestFocus();
        }
    }//GEN-LAST:event_jfNotaFiscal1ActionPerformed

    private void jbSalvar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvar1ActionPerformed
        try {
            Salvar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Salvar Dados!! " + ex.getMessage());
        }
    }//GEN-LAST:event_jbSalvar1ActionPerformed

    private void jbEditar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditar1ActionPerformed
        try {
            Editar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Editar Dados!! " + ex.getMessage());
        }
    }//GEN-LAST:event_jbEditar1ActionPerformed

    private void jbExcluir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExcluir1ActionPerformed
        try {
            int resposta;
            resposta = JOptionPane.showConfirmDialog(this, "Deseja Gerar o Relatório? ", "Aviso", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                Excluir();
            } else {
                limpaCampos();
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Excluir Dados!! " + ex.getMessage());
        }
    }//GEN-LAST:event_jbExcluir1ActionPerformed

    private void jCheckBoxPesquisa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxPesquisa1ActionPerformed
        if (jCheckBoxPesquisa1.isSelected()) {
            try {
                jLabelCodigo1.setText("Ano Da Pesquisa");
                DesabilitaTudo();
                jfCodigo1.setEnabled(true);
                limpaCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao Pesquisar Dados!! " + ex.getMessage());
            }
        } else {
            HabilitaTudo();
            limpaCampos();
            jLabelCodigo1.setText("Matrícula");
        }
    }//GEN-LAST:event_jCheckBoxPesquisa1ActionPerformed

    private void jbCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelar1ActionPerformed
        limpaCampos();
    }//GEN-LAST:event_jbCancelar1ActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        num = jTabbedPane1.getSelectedIndex();
        listaNaTabela();
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jbListarEnergiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbListarEnergiaActionPerformed
        try {
            ListarTudo();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Pesquisar Todos Dados!! " + ex.getMessage());
        }
    }//GEN-LAST:event_jbListarEnergiaActionPerformed

    private void jbListarAguaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbListarAguaActionPerformed
        try {
            ListarTudo();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Pesquisar Todos Dados!! " + ex.getMessage());
        }
    }//GEN-LAST:event_jbListarAguaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBoxPesquisa;
    private javax.swing.JCheckBox jCheckBoxPesquisa1;
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
    private javax.swing.JLabel jLabelCodigo;
    private javax.swing.JLabel jLabelCodigo1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTabela;
    private javax.swing.JTable jTabela1;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbCancelar1;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbEditar1;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JButton jbExcluir1;
    private javax.swing.JButton jbListarAgua;
    private javax.swing.JButton jbListarEnergia;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JButton jbSalvar1;
    private javax.swing.JFormattedTextField jfCodigo;
    private javax.swing.JFormattedTextField jfCodigo1;
    private javax.swing.JFormattedTextField jfConsumo;
    private javax.swing.JFormattedTextField jfConsumoKWH;
    private javax.swing.JFormattedTextField jfDataEmissao;
    private javax.swing.JFormattedTextField jfDataEmissao1;
    private javax.swing.JFormattedTextField jfDataVencimento;
    private javax.swing.JFormattedTextField jfDataVencimento1;
    private javax.swing.JFormattedTextField jfId;
    private javax.swing.JFormattedTextField jfId1;
    private javax.swing.JFormattedTextField jfNotaFiscal;
    private javax.swing.JFormattedTextField jfNotaFiscal1;
    private javax.swing.JFormattedTextField jfValorTotal;
    private javax.swing.JFormattedTextField jfValorTotal1;
    // End of variables declaration//GEN-END:variables
}
