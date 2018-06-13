package modulo.inventario;

import modulo.sobre.Versao;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.List;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modulo.metodos.Funcao;
import modulo.login.UserLogado;

/**
 *
 * @author Marcos Junior
 */
public final class JifInventario extends javax.swing.JInternalFrame {

    private Versao ver;
    private JFormattedTextField[] txts;
    private Funcao fun;
    private UserLogado login;
    private InventarioDAO DAOINV;
    private Inventario objInv;

    public JifInventario() {
        initComponents();
        ver = new Versao();
        login = new UserLogado();
        fun = new Funcao();
        DAOINV = new InventarioDAO();
        setTitle("Registro de Inventários: " + ver.getVersao());
        txts = new JFormattedTextField[]{
            jfDataInventario,
            jfPrazo,
            jfAno,
            jfDataInventario,
            jfTotalDeProduto,
            jfTotalDeUnidades,
            jfResultadoIMA,
            jfValorVendaEstoque,
            jfValorVendaFalta,
            jfValorVendaSobra,
            jfValorCustoEstoque,
            jfValorCustoFalta,
            jfValorCustoSobra};
        listaNaTabela();
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    public void HabilitaEdicao() {
        jbSalvar.setEnabled(false);
        jbEditar.setEnabled(true);
        jbExcluir.setEnabled(true);
    }

    public void HabilitaTudo() {
        for (JFormattedTextField txt : txts) {
            txt.setEnabled(true);
        }
    }

    public void DesabilitaTudo() {
        for (JFormattedTextField txt : txts) {
            txt.setEnabled(false);
        }
    }

    public void listaNaTabela() {
        try {
            matriculaSys();
            lojaSys();
            ListarAuto();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro Dados: " + ex.getMessage());
        }
    }

    public void limpaCampos() {
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

    }

    public boolean validaCampos() {
        for (JFormattedTextField txt : txts) {
            if (txt.getText().equals("")) {
                txt.setBackground(new java.awt.Color(255, 0, 0));
            } else {
                txt.setBackground(new java.awt.Color(214, 217, 223));
            }
        }
        return true;
    }

    public boolean validaCamposBreak() {
        boolean check = true;
        for (JFormattedTextField txt : txts) {
            if (txt.getText().equals("")) {
                check = false;
                break;
            }
        }
        return check;
    }

    public String matriculaSys() {
        String matricula = login.getMatricula();
        return matricula;
    }

    public int lojaSys() {
        int loja = login.getNumeroloja();
        return loja;
    }

    public boolean Salvar() throws Exception {
        boolean check;
        if (validaCampos()) {
            if (validaCamposBreak()) {
                if (PreencheDadosSalvar()) {
                    check = DAOINV.Insert(objInv);
                    JOptionPane.showMessageDialog(this, "Salvo Com Sucesso!");
                    listaNaTabela();
                    limpaCampos();
                } else {
                    check = false;
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
        if (validaCampos()) {
            if (validaCamposBreak()) {
                if (PreencheDadosEditar()) {
                    check = DAOINV.Update(objInv);
                    JOptionPane.showMessageDialog(this, "Editado Com Sucesso!");
                    listaNaTabela();
                    limpaCampos();
                } else {
                    check = false;
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

    public void ListarAuto() throws Exception {
        List<Inventario> inventario;
        String ano = fun.anoAtualDate();
        jfAno.setText(ano);
        inventario = DAOINV.TabelaPesquisaPorAno(ano);
        ListaPesquisaAno(inventario);
    }

    public void PesquisarAno(String ano) throws Exception {
        List<Inventario> agua;
        if (!jfPrazo.getText().equals("")) {
            agua = DAOINV.TabelaPesquisaPorAno(ano);
            ListaPesquisaAno(agua);
        }
    }

    public boolean Excluir() throws Exception {
        boolean check;
        if (!jfId.getText().equals("")) {
            check = DAOINV.Delete(Integer.parseInt(jfId.getText()));
            JOptionPane.showMessageDialog(this, "Exluido Com Sucesso!");
            listaNaTabela();
        } else {
            check = false;
        }
        return check;
    }

    public boolean testaDados() {
        for (JFormattedTextField txt : txts) {
            if (txt.getText().equals("")) {
                txt.setBackground(new java.awt.Color(255, 0, 0));
            } else {
                txt.setBackground(new java.awt.Color(214, 217, 223));
            }
        }
        return true;
    }

    public void VerificaSelecionado() {

        if (jCheckBoxPesquisa.isSelected()) {
            try {
                jLabelAno.setText("Ano em Curso");
                HabilitaTudo();
                jfAno.setEnabled(true);
                limpaCampos();
                jCheckBoxPesquisa.setSelected(false);
                jfDataInventario.requestFocus();
                jfAno.setText(fun.anoAtualDate());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao Pesquisar Dados!! " + ex.getMessage());
            }
        } else {
            HabilitaTudo();
            limpaCampos();
            jLabelAno.setText("Ano em Curso");
            jfDataInventario.requestFocus();
            jfAno.setText(fun.anoAtualDate());
        }
    }

    public void CarregaDados() {
        if (jTabela.getRowCount() >= 1) {
            if (jTabela.getSelectedRow() != -1) {
                HabilitaEdicao();
                Object id, itens, unidades, ima, valor_vestoque,
                        valor_vfalta, valor_vsobra, valor_cestoque,
                        valor_cfalta, valor_csobra, prazo, data, ano;

                id = jTabela.getValueAt(jTabela.getSelectedRow(), 0);
                jfId.setText(id.toString());

                itens = jTabela.getValueAt(jTabela.getSelectedRow(), 1);
                jfTotalDeProduto.setText(itens.toString());

                unidades = jTabela.getValueAt(jTabela.getSelectedRow(), 2);
                jfTotalDeUnidades.setText(unidades.toString());

                ima = jTabela.getValueAt(jTabela.getSelectedRow(), 3);
                jfResultadoIMA.setText(ima.toString());

                valor_vestoque = jTabela.getValueAt(jTabela.getSelectedRow(), 4);
                jfValorVendaEstoque.setText(valor_vestoque.toString());

                valor_vfalta = jTabela.getValueAt(jTabela.getSelectedRow(), 5);
                jfValorVendaFalta.setText(valor_vfalta.toString());

                valor_vsobra = jTabela.getValueAt(jTabela.getSelectedRow(), 6);
                jfValorVendaSobra.setText(valor_vsobra.toString());

                valor_cestoque = jTabela.getValueAt(jTabela.getSelectedRow(), 7);
                jfValorCustoEstoque.setText(valor_cestoque.toString());

                valor_cfalta = jTabela.getValueAt(jTabela.getSelectedRow(), 8);
                jfValorCustoFalta.setText(valor_cfalta.toString());

                valor_csobra = jTabela.getValueAt(jTabela.getSelectedRow(), 9);
                jfValorCustoSobra.setText(valor_csobra.toString());

                prazo = jTabela.getValueAt(jTabela.getSelectedRow(), 10);
                jfPrazo.setText(prazo.toString());

                data = jTabela.getValueAt(jTabela.getSelectedRow(), 11);
                jfDataInventario.setText(data.toString());

                ano = jTabela.getValueAt(jTabela.getSelectedRow(), 12);
                jfAno.setText(ano.toString());
            }
        }
    }

    public boolean PreencheDadosSalvar() throws ParseException {
        objInv = new Inventario();
        objInv.setLoja(lojaSys());
        objInv.setData_inventario(fun.convertDateStringToDateSQL(jfDataInventario.getText()));
        objInv.setAno(fun.anoAtualDate());
        objInv.setPrazo(Integer.parseInt(jfPrazo.getText()));
        objInv.setTotal_produtos(fun.convertToInt(jfTotalDeProduto.getText()));
        objInv.setTotal_unidades(fun.convertToInt(jfTotalDeUnidades.getText()));
        objInv.setValor_ima(fun.convertToDouble(jfResultadoIMA.getText()));
        objInv.setValor_venda_estoque(fun.convertToDouble(jfValorVendaEstoque.getText()));
        objInv.setValor_venda_falta(fun.convertToDouble(jfValorVendaFalta.getText()));
        objInv.setValor_venda_sobra(fun.convertToDouble(jfValorVendaSobra.getText()));
        objInv.setValor_custo_estoque(fun.convertToDouble(jfValorCustoEstoque.getText()));
        objInv.setValor_custo_falta(fun.convertToDouble(jfValorCustoFalta.getText()));
        objInv.setValor_custo_sobra(fun.convertToDouble(jfValorCustoSobra.getText()));
        return objInv != null;
    }

    public boolean PreencheDadosEditar() throws ParseException {
        objInv = new Inventario();
        objInv.setLoja(lojaSys());
        objInv.setData_inventario(fun.convertDateStringToDateSQL(jfDataInventario.getText()));
        objInv.setAno(fun.anoAtualDate());
        objInv.setPrazo(Integer.parseInt(jfPrazo.getText()));
        objInv.setTotal_produtos(fun.convertToInt(jfTotalDeProduto.getText()));
        objInv.setTotal_unidades(fun.convertToInt(jfTotalDeUnidades.getText()));
        objInv.setValor_ima(fun.convertToDouble(jfResultadoIMA.getText()));
        objInv.setValor_venda_estoque(fun.convertToDouble(jfValorVendaEstoque.getText()));
        objInv.setValor_venda_falta(fun.convertToDouble(jfValorVendaFalta.getText()));
        objInv.setValor_venda_sobra(fun.convertToDouble(jfValorVendaSobra.getText()));
        objInv.setValor_custo_estoque(fun.convertToDouble(jfValorCustoEstoque.getText()));
        objInv.setValor_custo_falta(fun.convertToDouble(jfValorCustoFalta.getText()));
        objInv.setValor_custo_sobra(fun.convertToDouble(jfValorCustoSobra.getText()));
        objInv.setId_inv(Integer.parseInt(jfId.getText()));
        return objInv != null;
    }

    public void ListaPesquisaAno(List<Inventario> inventario) throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jTabela.getModel();
        modelo.setNumRows(0);
        inventario.forEach((p) -> {
            try {
                modelo.addRow(new Object[]{
                    p.getId_inv(), p.getTotal_produtos(), p.getTotal_unidades(),
                    p.getValor_ima(),
                    fun.convertDoubleToString(p.getValor_venda_estoque()),
                    fun.convertDoubleToString(p.getValor_venda_falta()),
                    fun.convertDoubleToString(p.getValor_venda_sobra()),
                    fun.convertDoubleToString(p.getValor_custo_estoque()),
                    fun.convertDoubleToString(p.getValor_custo_falta()),
                    fun.convertDoubleToString(p.getValor_custo_sobra()),
                    p.getPrazo(),
                    fun.convertDataSQLToDateString(p.getData_inventario()),
                    p.getAno()
                });
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro Pesquisa Ano: " + ex.getMessage());
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabela = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jfValorCustoEstoque = new javax.swing.JFormattedTextField();
        jfValorCustoFalta = new javax.swing.JFormattedTextField();
        jfValorCustoSobra = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jfValorVendaEstoque = new javax.swing.JFormattedTextField();
        jfValorVendaFalta = new javax.swing.JFormattedTextField();
        jfValorVendaSobra = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jfTotalDeProduto = new javax.swing.JFormattedTextField();
        jfTotalDeUnidades = new javax.swing.JFormattedTextField();
        jfResultadoIMA = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jfDataInventario = new javax.swing.JFormattedTextField();
        jLabelPrazo = new javax.swing.JLabel();
        jfPrazo = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jfId = new javax.swing.JFormattedTextField();
        jbSalvar = new javax.swing.JButton();
        jbEditar = new javax.swing.JButton();
        jbCancelar = new javax.swing.JButton();
        jbExcluir = new javax.swing.JButton();
        jCheckBoxPesquisa = new javax.swing.JCheckBox();
        jLabelAno = new javax.swing.JLabel();
        jfAno = new javax.swing.JFormattedTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(204, 204, 0));

        jTabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "ITENS", "UND", "IMA", "VALOR V_ESTOQ", "VALOR V_FALTA", "VALOR V_SOBRA", "VALOR C_ESTOQ", "VALOR C_FALTA", "VALOR C_SOBRA", "PRAZO", "DATA", "ANO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
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
            jTabela.getColumnModel().getColumn(0).setMaxWidth(40);
            jTabela.getColumnModel().getColumn(3).setMaxWidth(70);
            jTabela.getColumnModel().getColumn(4).setMinWidth(120);
            jTabela.getColumnModel().getColumn(5).setMinWidth(120);
            jTabela.getColumnModel().getColumn(6).setMinWidth(120);
            jTabela.getColumnModel().getColumn(7).setMinWidth(120);
            jTabela.getColumnModel().getColumn(8).setMinWidth(120);
            jTabela.getColumnModel().getColumn(9).setMinWidth(120);
        }

        jPanel2.setBackground(new java.awt.Color(102, 255, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Valores de Custo"));

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel3.setText("Valor Estoque");

        jLabel8.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel8.setText("Valor Total Falta");

        jLabel9.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel9.setText("Valor Total Sobra");

        jfValorCustoEstoque.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfValorCustoEstoque.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfValorCustoEstoque.setToolTipText("");
        jfValorCustoEstoque.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfValorCustoEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfValorCustoEstoqueActionPerformed(evt);
            }
        });

        jfValorCustoFalta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfValorCustoFalta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfValorCustoFalta.setToolTipText("");
        jfValorCustoFalta.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfValorCustoFalta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfValorCustoFaltaActionPerformed(evt);
            }
        });

        jfValorCustoSobra.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfValorCustoSobra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfValorCustoSobra.setToolTipText("");
        jfValorCustoSobra.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfValorCustoSobra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfValorCustoSobraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jfValorCustoEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jfValorCustoSobra, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jfValorCustoFalta, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jfValorCustoEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jfValorCustoFalta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jfValorCustoSobra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Valores de Venda"));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel1.setText("Valor Estoque");

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel6.setText("Valor Total Falta");

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel4.setText("Valor Total Sobra");

        jfValorVendaEstoque.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfValorVendaEstoque.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfValorVendaEstoque.setToolTipText("");
        jfValorVendaEstoque.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfValorVendaEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfValorVendaEstoqueActionPerformed(evt);
            }
        });

        jfValorVendaFalta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfValorVendaFalta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfValorVendaFalta.setToolTipText("");
        jfValorVendaFalta.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfValorVendaFalta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfValorVendaFaltaActionPerformed(evt);
            }
        });

        jfValorVendaSobra.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfValorVendaSobra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfValorVendaSobra.setToolTipText("");
        jfValorVendaSobra.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfValorVendaSobra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfValorVendaSobraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jfValorVendaFalta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jfValorVendaSobra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jfValorVendaEstoque, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jfValorVendaEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jfValorVendaFalta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jfValorVendaSobra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 255, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultado"));

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel2.setText("Total de Produtos");

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel5.setText("Total de Unidades");

        jLabel7.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel7.setText("Resultado do IMA");

        jfTotalDeProduto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfTotalDeProduto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfTotalDeProduto.setToolTipText("");
        jfTotalDeProduto.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfTotalDeProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfTotalDeProdutoActionPerformed(evt);
            }
        });

        jfTotalDeUnidades.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfTotalDeUnidades.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfTotalDeUnidades.setToolTipText("");
        jfTotalDeUnidades.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfTotalDeUnidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfTotalDeUnidadesActionPerformed(evt);
            }
        });

        jfResultadoIMA.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfResultadoIMA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfResultadoIMA.setToolTipText("");
        jfResultadoIMA.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfResultadoIMA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfResultadoIMAActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jfTotalDeUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jfTotalDeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jfResultadoIMA, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jfTotalDeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jfTotalDeUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jfResultadoIMA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel10.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel10.setText("Data da Realização do Inventário");

        try {
            jfDataInventario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jfDataInventario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfDataInventario.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfDataInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfDataInventarioActionPerformed(evt);
            }
        });

        jLabelPrazo.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabelPrazo.setText("Prazo do Inventário");

        jfPrazo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfPrazo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfPrazo.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfPrazo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfPrazoActionPerformed(evt);
            }
        });

        jLabel12.setText("ID");

        jfId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfId.setEnabled(false);
        jfId.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);

        jbSalvar.setText("Salvar");
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

        jbEditar.setText("Editar");
        jbEditar.setEnabled(false);
        jbEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditarActionPerformed(evt);
            }
        });

        jbCancelar.setText("Cancelar");
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
            }
        });

        jbExcluir.setText("Excluir");
        jbExcluir.setEnabled(false);
        jbExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExcluirActionPerformed(evt);
            }
        });

        jCheckBoxPesquisa.setText("Pesquisar");
        jCheckBoxPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxPesquisaActionPerformed(evt);
            }
        });

        jLabelAno.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabelAno.setText("Ano em Curso");

        jfAno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfAno.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfAno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfAnoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jfDataInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelPrazo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jfPrazo, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelAno)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jfAno, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jfId, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jCheckBoxPesquisa)
                        .addGap(28, 28, 28)
                        .addComponent(jbSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbExcluir)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jfDataInventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPrazo)
                    .addComponent(jfPrazo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAno)
                    .addComponent(jfAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbSalvar)
                    .addComponent(jbEditar)
                    .addComponent(jbCancelar)
                    .addComponent(jbExcluir)
                    .addComponent(jCheckBoxPesquisa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        limpaCampos();
        VerificaSelecionado();
    }//GEN-LAST:event_jbCancelarActionPerformed

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

    private void jfDataInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfDataInventarioActionPerformed
        if (!jfDataInventario.getText().equals("")) {
            jfPrazo.requestFocus();
        }
    }//GEN-LAST:event_jfDataInventarioActionPerformed

    private void jfPrazoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfPrazoActionPerformed
        if (!jfPrazo.getText().equals("")) {
            jfTotalDeProduto.requestFocus();
        }
    }//GEN-LAST:event_jfPrazoActionPerformed

    private void jfTotalDeProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfTotalDeProdutoActionPerformed
        if (!jfTotalDeProduto.getText().equals("")) {
            jfTotalDeUnidades.requestFocus();
        }
    }//GEN-LAST:event_jfTotalDeProdutoActionPerformed

    private void jfTotalDeUnidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfTotalDeUnidadesActionPerformed
        if (!jfTotalDeUnidades.getText().equals("")) {
            jfResultadoIMA.requestFocus();
        }
    }//GEN-LAST:event_jfTotalDeUnidadesActionPerformed

    private void jfResultadoIMAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfResultadoIMAActionPerformed
        if (!jfResultadoIMA.getText().equals("")) {
            jfValorVendaEstoque.requestFocus();
        }
    }//GEN-LAST:event_jfResultadoIMAActionPerformed

    private void jfValorVendaEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfValorVendaEstoqueActionPerformed
        if (!jfValorVendaEstoque.getText().equals("")) {
            jfValorVendaFalta.requestFocus();
        }
    }//GEN-LAST:event_jfValorVendaEstoqueActionPerformed

    private void jfValorVendaFaltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfValorVendaFaltaActionPerformed
        if (!jfValorVendaFalta.getText().equals("")) {
            jfValorVendaSobra.requestFocus();
        }
    }//GEN-LAST:event_jfValorVendaFaltaActionPerformed

    private void jfValorVendaSobraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfValorVendaSobraActionPerformed
        if (!jfValorVendaSobra.getText().equals("")) {
            jfValorCustoEstoque.requestFocus();
        }
    }//GEN-LAST:event_jfValorVendaSobraActionPerformed

    private void jfValorCustoEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfValorCustoEstoqueActionPerformed
        if (!jfValorCustoEstoque.getText().equals("")) {
            jfValorCustoFalta.requestFocus();
        }
    }//GEN-LAST:event_jfValorCustoEstoqueActionPerformed

    private void jfValorCustoFaltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfValorCustoFaltaActionPerformed
        if (!jfValorCustoFalta.getText().equals("")) {
            jfValorCustoSobra.requestFocus();
        }
    }//GEN-LAST:event_jfValorCustoFaltaActionPerformed

    private void jfValorCustoSobraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfValorCustoSobraActionPerformed
        if (!jfValorCustoSobra.getText().equals("")) {
            jbSalvar.requestFocus();
        }
    }//GEN-LAST:event_jfValorCustoSobraActionPerformed

    private void jCheckBoxPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxPesquisaActionPerformed
        if (jCheckBoxPesquisa.isSelected()) {
            try {
                jLabelAno.setText("Pesquisar Ano");
                DesabilitaTudo();
                jfAno.setEnabled(true);
                limpaCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao Pesquisar Dados!! " + ex.getMessage());
            }
        } else {
            HabilitaTudo();
            limpaCampos();
            jfAno.setText(fun.anoAtualDate());
            jLabelAno.setText("Ano em Curso");
        }
    }//GEN-LAST:event_jCheckBoxPesquisaActionPerformed

    private void jTabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabelaMouseClicked
        CarregaDados();
    }//GEN-LAST:event_jTabelaMouseClicked

    private void jfAnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfAnoActionPerformed

        if (jCheckBoxPesquisa.isSelected()) {
            if (!jfAno.getText().equals("")) {
                try {
                    PesquisarAno(jfAno.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao Pesquisar Dados!! " + ex.getMessage());
                }
            }
        } else {
            if (!jfAno.getText().equals("")) {
                jfTotalDeProduto.requestFocus();
            }
        }

    }//GEN-LAST:event_jfAnoActionPerformed

    private void jbSalvarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jbSalvarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                Salvar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao Salvar Dados!! " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_jbSalvarKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBoxPesquisa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAno;
    private javax.swing.JLabel jLabelPrazo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTabela;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JFormattedTextField jfAno;
    private javax.swing.JFormattedTextField jfDataInventario;
    private javax.swing.JFormattedTextField jfId;
    private javax.swing.JFormattedTextField jfPrazo;
    private javax.swing.JFormattedTextField jfResultadoIMA;
    private javax.swing.JFormattedTextField jfTotalDeProduto;
    private javax.swing.JFormattedTextField jfTotalDeUnidades;
    private javax.swing.JFormattedTextField jfValorCustoEstoque;
    private javax.swing.JFormattedTextField jfValorCustoFalta;
    private javax.swing.JFormattedTextField jfValorCustoSobra;
    private javax.swing.JFormattedTextField jfValorVendaEstoque;
    private javax.swing.JFormattedTextField jfValorVendaFalta;
    private javax.swing.JFormattedTextField jfValorVendaSobra;
    // End of variables declaration//GEN-END:variables
}
