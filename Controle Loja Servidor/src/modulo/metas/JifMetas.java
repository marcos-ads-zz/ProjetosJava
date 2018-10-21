package modulo.metas;

import modulo.loja.LojaDAO;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modulo.metodos.Funcao;
import modulo.login.UserLogado;
import modulo.versao.Versao;

/**
 *
 * @author Marcos Junior
 */
public final class JifMetas extends javax.swing.JInternalFrame {

    private Versao ver;
    private LojaDAO DAOLOJA;
    private MetasDAO DAOMETAS;
    private Metas objMetas;
    private UserLogado login;
    private Funcao fun;
    private JFormattedTextField[] txts;

    public JifMetas() {
        initComponents();
        ver = new Versao();
        DAOLOJA = new LojaDAO();
        DAOMETAS = new MetasDAO();
        login = new UserLogado();
        fun = new Funcao();
        txts = new JFormattedTextField[]{
            jfAno,
            jfDescontoVip,
            jfMetaAnotacao,
            jfMetaClinicFarma,
            jfMetaGerovital,
            jfMetaGnano,
            jfMetaPower,
            jfMetaRecarga,
            jfMetaVenda,
            jfRentabilidade};
        setTitle("Cadastro de Metas: " + ver.getVersao());
        listaNaTabela();

    }

    public void HabilitaEdicao() {
        jbSalvar.setEnabled(false);
        jbEditar.setEnabled(true);
        jbExcluir.setEnabled(true);
    }

    public void listaNaTabela() {
        try {
            SetaDados();
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
        jfMes.setSelectedIndex(0);
        jbSalvar.setEnabled(true);
        jbEditar.setEnabled(false);
        jbExcluir.setEnabled(false);

    }

    public void bloqueiaCampos() {
        for (JFormattedTextField txt : txts) {
            txt.setText("");
            txt.setEnabled(false);
        }
        jfAno.setEnabled(true);
    }

    public void desbloqueiaCampos() {
        for (JFormattedTextField txt : txts) {
            txt.setText("");
            txt.setEnabled(true);
        }
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
        if (jfMes.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Selecione um Turno");
            check = false;
        }

        return check;
    }

    public void SetaDados() throws Exception {
        int loja = login.getNumeroloja();
        String nome = DAOLOJA.PesquisaNumeroLoja(loja).getNome_loja();
        jfNumeroDaLoja.setText(Integer.toString(loja));
        jfNomeDaLoja.setText(nome);
        jfAno.setText(fun.anoAtualDate());
        jfDataRegistro.setText(fun.dataAtualDate());
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    public boolean Salvar() throws Exception {
        boolean check;
        if (validaCampos()) {
            if (validaCamposBreak()) {
                if (PreencheDadosSalvar()) {
                    check = DAOMETAS.Insert(objMetas);
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
                    check = DAOMETAS.Update(objMetas);
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
        List<Metas> metas;
        if (!jfAno.getText().equals("")) {
            metas = DAOMETAS.TabelaPesquisaPorAno(Integer.parseInt(fun.anoAtualDate()));
            ListaPesquisaAno(metas);
        }
    }

    public void PesquisarAno() throws Exception {
        List<Metas> metas;
        if (!jfAno.getText().equals("")) {
            metas = DAOMETAS.TabelaPesquisaPorAno(Integer.parseInt(jfAno.getText()));
            ListaPesquisaAno(metas);
        }
    }

    public boolean PesquisarMes() throws Exception {
        List<Metas> metas = null;
        if (!jfAno.getText().equals("")) {
            String f = jfMes.getSelectedItem().toString().toUpperCase();
            int g = Integer.parseInt(jfAno.getText());
            metas = DAOMETAS.PesquisaMetaPorMes(f, g);
            ListaPesquisaMesEMeta(metas);
        }
        return metas == null;
    }

    public boolean Excluir() throws Exception {
        boolean check;
        if (!jfId.getText().equals("")) {
            check = DAOMETAS.Delete(Integer.parseInt(jfId.getText()));
            JOptionPane.showMessageDialog(this, "Exluido Com Sucesso!");
            listaNaTabela();
        } else {
            check = false;
        }
        return check;
    }

    public boolean dados() {
        for (JFormattedTextField txt : txts) {
            if (txt.getText().equals("")) {
                txt.setBackground(new java.awt.Color(255, 0, 0));
            } else {
                txt.setBackground(new java.awt.Color(214, 217, 223));
            }
        }
        return true;
    }

    public void CarregaDados() {
        if (jTabela.getRowCount() >= 1) {
            if (jTabela.getSelectedRow() != -1) {
                HabilitaEdicao();
                Object id, mes, ano, meta, recarga,
                        rent, power, ganano, anotacao, clinic, gero, desc_vip;

                id = jTabela.getValueAt(jTabela.getSelectedRow(), 0);
                jfId.setText(id.toString());

                mes = jTabela.getValueAt(jTabela.getSelectedRow(), 1);
                jfMes.setSelectedItem(mes.toString());

                ano = jTabela.getValueAt(jTabela.getSelectedRow(), 2);
                jfAno.setText(ano.toString());

                meta = jTabela.getValueAt(jTabela.getSelectedRow(), 3);
                jfMetaVenda.setText(meta.toString());

                recarga = jTabela.getValueAt(jTabela.getSelectedRow(), 4);
                jfMetaRecarga.setText(recarga.toString());

                rent = jTabela.getValueAt(jTabela.getSelectedRow(), 5);
                jfRentabilidade.setText(rent.toString());

                power = jTabela.getValueAt(jTabela.getSelectedRow(), 6);
                jfMetaPower.setText(power.toString());

                ganano = jTabela.getValueAt(jTabela.getSelectedRow(), 7);
                jfMetaGnano.setText(ganano.toString());

                anotacao = jTabela.getValueAt(jTabela.getSelectedRow(), 8);
                jfMetaAnotacao.setText(anotacao.toString());

                clinic = jTabela.getValueAt(jTabela.getSelectedRow(), 9);
                jfMetaClinicFarma.setText(clinic.toString());

                gero = jTabela.getValueAt(jTabela.getSelectedRow(), 10);
                jfMetaGerovital.setText(gero.toString());

                desc_vip = jTabela.getValueAt(jTabela.getSelectedRow(), 11);
                jfDescontoVip.setText(desc_vip.toString());
            }
        }
    }

    public boolean PreencheDadosSalvar() {
        objMetas = new Metas();
        objMetas.setValor_meta(fun.convertDoubleStringToDouble(jfMetaVenda.getText()));
        objMetas.setValor_recarga(fun.convertDoubleStringToDouble(jfMetaRecarga.getText()));
        objMetas.setValor_renta(fun.convertDoubleStringToDouble(jfRentabilidade.getText()));
        objMetas.setValor_vip(fun.convertDoubleStringToDouble(jfDescontoVip.getText()));
        objMetas.setMeta_anotacao(Integer.parseInt(jfMetaAnotacao.getText()));
        objMetas.setMeta_clinic(Integer.parseInt(jfMetaClinicFarma.getText()));
        objMetas.setMeta_gero(Integer.parseInt(jfMetaGerovital.getText()));
        objMetas.setMeta_gnano(Integer.parseInt(jfMetaGnano.getText()));
        objMetas.setMeta_power(Integer.parseInt(jfMetaPower.getText()));
        objMetas.setMes(jfMes.getSelectedItem().toString().toUpperCase());
        objMetas.setAno(Integer.parseInt(jfAno.getText()));
        objMetas.setN_loja(Integer.parseInt(jfNumeroDaLoja.getText()));
        objMetas.setDate_registro(fun.atualDateSQL());
        return objMetas != null;
    }

    public boolean PreencheDadosEditar() {
        objMetas = new Metas();
        objMetas.setId_metas(Integer.parseInt(jfId.getText()));
        objMetas.setValor_meta(fun.convertDoubleStringToDouble(jfMetaVenda.getText()));
        objMetas.setValor_recarga(fun.convertDoubleStringToDouble(jfMetaRecarga.getText()));
        objMetas.setValor_renta(fun.convertDoubleStringToDouble(jfRentabilidade.getText()));
        objMetas.setValor_vip(fun.convertDoubleStringToDouble(jfDescontoVip.getText()));
        objMetas.setMeta_anotacao(Integer.parseInt(jfMetaAnotacao.getText()));
        objMetas.setMeta_clinic(Integer.parseInt(jfMetaClinicFarma.getText()));
        objMetas.setMeta_gero(Integer.parseInt(jfMetaGerovital.getText()));
        objMetas.setMeta_gnano(Integer.parseInt(jfMetaGnano.getText()));
        objMetas.setMeta_power(Integer.parseInt(jfMetaPower.getText()));
        objMetas.setMes(jfMes.getSelectedItem().toString().toUpperCase());
        objMetas.setAno(Integer.parseInt(jfAno.getText()));
        objMetas.setN_loja(Integer.parseInt(jfNumeroDaLoja.getText()));
        objMetas.setDate_registro(fun.atualDateSQL());
        return objMetas != null;
    }

    public void ListaPesquisaAno(List<Metas> metas) throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jTabela.getModel();
        modelo.setNumRows(0);
        metas.forEach((p) -> {
            modelo.addRow(new Object[]{
                p.getId_metas(), p.getMes(), p.getAno(),
                fun.convertDoubleToStringMoeda(p.getValor_meta()),
                fun.convertDoubleToStringMoeda(p.getValor_recarga()),
                fun.convertDoubleToStringMoeda(p.getValor_renta()), p.getMeta_power(),
                p.getMeta_gnano(), p.getMeta_anotacao(), p.getMeta_clinic(), p.getMeta_gero(),
                fun.convertDoubleToStringMoeda(p.getValor_vip())

            });
        });
    }

    public void ListaPesquisaMesEMeta(List<Metas> metas) throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jTabela.getModel();
        modelo.setNumRows(0);
        metas.forEach((p) -> {
            modelo.addRow(new Object[]{
                p.getId_metas(), p.getMes(), p.getAno(),
                fun.convertDoubleToStringMoeda(p.getValor_meta()),
                fun.convertDoubleToStringMoeda(p.getValor_recarga()),
                fun.convertDoubleToStringMoeda(p.getValor_renta()), p.getMeta_power(),
                p.getMeta_gnano(), p.getMeta_anotacao(), p.getMeta_clinic(), p.getMeta_gero(),
                fun.convertDoubleToStringMoeda(p.getValor_vip())

            });
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabela = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jfMetaVenda = new javax.swing.JFormattedTextField();
        jfMetaRecarga = new javax.swing.JFormattedTextField();
        jfRentabilidade = new javax.swing.JFormattedTextField();
        jfDescontoVip = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jfMetaPower = new javax.swing.JFormattedTextField();
        jfMetaGnano = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jfMetaClinicFarma = new javax.swing.JFormattedTextField();
        jfMetaGerovital = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jfMetaAnotacao = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jfDataRegistro = new javax.swing.JFormattedTextField();
        jfAno = new javax.swing.JFormattedTextField();
        jfMes = new javax.swing.JComboBox<>();
        jfNumeroDaLoja = new javax.swing.JFormattedTextField();
        jfNomeDaLoja = new javax.swing.JFormattedTextField();
        jbSalvar = new javax.swing.JButton();
        jbEditar = new javax.swing.JButton();
        jbLimpar = new javax.swing.JButton();
        jbPesquisar = new javax.swing.JButton();
        jfId = new javax.swing.JFormattedTextField();
        jbExcluir = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(153, 255, 204));

        jTabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "MÊS", "ANO", "META", "RECARGA", "RENTAB", "POWER", "GNANO", "ANOTAÇÃO", "CLINIC", "GEROVITAL", "DESC. VIP"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
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
            jTabela.getColumnModel().getColumn(0).setMinWidth(30);
            jTabela.getColumnModel().getColumn(0).setMaxWidth(30);
            jTabela.getColumnModel().getColumn(1).setMinWidth(60);
            jTabela.getColumnModel().getColumn(1).setMaxWidth(60);
            jTabela.getColumnModel().getColumn(2).setMinWidth(60);
            jTabela.getColumnModel().getColumn(2).setMaxWidth(60);
            jTabela.getColumnModel().getColumn(3).setMinWidth(100);
            jTabela.getColumnModel().getColumn(3).setMaxWidth(100);
            jTabela.getColumnModel().getColumn(4).setMinWidth(100);
            jTabela.getColumnModel().getColumn(4).setMaxWidth(100);
            jTabela.getColumnModel().getColumn(5).setMinWidth(90);
            jTabela.getColumnModel().getColumn(5).setMaxWidth(90);
            jTabela.getColumnModel().getColumn(6).setMinWidth(75);
            jTabela.getColumnModel().getColumn(6).setMaxWidth(75);
            jTabela.getColumnModel().getColumn(7).setMinWidth(75);
            jTabela.getColumnModel().getColumn(7).setMaxWidth(75);
            jTabela.getColumnModel().getColumn(8).setMinWidth(85);
            jTabela.getColumnModel().getColumn(8).setMaxWidth(85);
            jTabela.getColumnModel().getColumn(9).setMinWidth(60);
            jTabela.getColumnModel().getColumn(9).setMaxWidth(60);
            jTabela.getColumnModel().getColumn(10).setMinWidth(85);
            jTabela.getColumnModel().getColumn(10).setMaxWidth(85);
        }

        jPanel2.setBackground(new java.awt.Color(153, 255, 153));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Meta de Venda");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Meta Recarga");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Rentabilidade");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Desconto Vip");

        jfMetaVenda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfMetaVenda.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfMetaVenda.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfMetaVenda.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jfMetaVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfMetaVendaActionPerformed(evt);
            }
        });

        jfMetaRecarga.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfMetaRecarga.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfMetaRecarga.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfMetaRecarga.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jfMetaRecarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfMetaRecargaActionPerformed(evt);
            }
        });

        jfRentabilidade.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfRentabilidade.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfRentabilidade.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfRentabilidade.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jfRentabilidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfRentabilidadeActionPerformed(evt);
            }
        });

        jfDescontoVip.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfDescontoVip.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfDescontoVip.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfDescontoVip.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jfDescontoVip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfDescontoVipActionPerformed(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Meta Power Vita");

        jfMetaPower.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfMetaPower.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfMetaPower.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfMetaPower.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jfMetaPower.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfMetaPowerActionPerformed(evt);
            }
        });

        jfMetaGnano.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfMetaGnano.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfMetaGnano.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfMetaGnano.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jfMetaGnano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfMetaGnanoActionPerformed(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Meta Gnano");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Meta Gerovital");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Meta Clinic Farma");

        jfMetaClinicFarma.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfMetaClinicFarma.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfMetaClinicFarma.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfMetaClinicFarma.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jfMetaClinicFarma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfMetaClinicFarmaActionPerformed(evt);
            }
        });

        jfMetaGerovital.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfMetaGerovital.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfMetaGerovital.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfMetaGerovital.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jfMetaGerovital.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfMetaGerovitalActionPerformed(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Meta Anotação");

        jfMetaAnotacao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfMetaAnotacao.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfMetaAnotacao.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfMetaAnotacao.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jfMetaAnotacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfMetaAnotacaoActionPerformed(evt);
            }
        });

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Mês");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Ano");

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Data de Registro");

        jfDataRegistro.setEditable(false);
        try {
            jfDataRegistro.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jfDataRegistro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfDataRegistro.setEnabled(false);
        jfDataRegistro.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfDataRegistro.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N

        try {
            jfAno.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jfAno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfAno.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfAno.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jfAno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfAnoActionPerformed(evt);
            }
        });

        jfMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "JANEIRO", "FEVEREIRO", "MARÇO", "ABRIL", "MAIO", "JUNHO", "JULHO", "AGOSTO", "SETEMBRO", "OUTUBRO", "NOVEMBRO", "DEZEMBRO" }));
        jfMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfMesActionPerformed(evt);
            }
        });

        jfNumeroDaLoja.setEditable(false);
        jfNumeroDaLoja.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfNumeroDaLoja.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfNumeroDaLoja.setText("Loja");
        jfNumeroDaLoja.setEnabled(false);
        jfNumeroDaLoja.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfNumeroDaLoja.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N

        jfNomeDaLoja.setEditable(false);
        jfNomeDaLoja.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfNomeDaLoja.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfNomeDaLoja.setText("Nome Loja");
        jfNomeDaLoja.setEnabled(false);
        jfNomeDaLoja.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfNomeDaLoja.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N

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

        jbLimpar.setText("Limpar");
        jbLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLimparActionPerformed(evt);
            }
        });

        jbPesquisar.setText("Pesquisar");
        jbPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPesquisarActionPerformed(evt);
            }
        });

        jfId.setEditable(false);
        jfId.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfId.setText("ID");
        jfId.setEnabled(false);
        jfId.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfId.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N

        jbExcluir.setText("Excluir");
        jbExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExcluirActionPerformed(evt);
            }
        });

        jCheckBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jfMetaVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jfMetaRecarga, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jfRentabilidade, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jfDescontoVip, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jfMetaPower, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jfMetaGnano, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jfMetaGerovital, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jfMetaClinicFarma, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jfMetaAnotacao)
                    .addComponent(jfAno)
                    .addComponent(jfMes, 0, 110, Short.MAX_VALUE)
                    .addComponent(jfDataRegistro, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jbSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jbEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbExcluir))
                            .addComponent(jbPesquisar)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jfNomeDaLoja, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jfId, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jfNumeroDaLoja)))))
                .addGap(29, 29, 29))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jfMetaPower, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(jfMetaAnotacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jfMetaVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jfNumeroDaLoja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jfMetaRecarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(jfMetaGnano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)
                                    .addComponent(jfMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jfNomeDaLoja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox1)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(jfRentabilidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7)
                                        .addComponent(jfMetaGerovital, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel11)
                                        .addComponent(jfAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jbSalvar)
                                .addComponent(jbEditar)
                                .addComponent(jbExcluir)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jfDescontoVip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jfMetaClinicFarma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(jfDataRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbLimpar)
                            .addComponent(jbPesquisar))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Metas Loja", jPanel1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 956, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Meta Balconistas", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
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

    private void jbLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLimparActionPerformed
        limpaCampos();
    }//GEN-LAST:event_jbLimparActionPerformed

    private void jbPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPesquisarActionPerformed
        try {
            if (PesquisarMes()) {

            } else {
                JOptionPane.showMessageDialog(this, "Não há dados!! ");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Pesquisar Dados!! " + ex.getMessage());
        }
    }//GEN-LAST:event_jbPesquisarActionPerformed

    private void jTabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabelaMouseClicked
        CarregaDados();
    }//GEN-LAST:event_jTabelaMouseClicked

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

    private void jfAnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfAnoActionPerformed

        if (jCheckBox1.isSelected()) {
            try {
                PesquisarAno();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao Pesquisar Dados!! " + ex.getMessage());
            }
        } else {
            if (!jfAno.getText().equals("")) {
                jbSalvar.requestFocus();
            }
        }
    }//GEN-LAST:event_jfAnoActionPerformed

    private void jfMetaVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfMetaVendaActionPerformed
        if (!jfMetaVenda.getText().equals("")) {
            jfMetaRecarga.requestFocus();
        }
    }//GEN-LAST:event_jfMetaVendaActionPerformed

    private void jfMetaRecargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfMetaRecargaActionPerformed
        if (!jfMetaRecarga.getText().equals("")) {
            jfRentabilidade.requestFocus();
        }
    }//GEN-LAST:event_jfMetaRecargaActionPerformed

    private void jfRentabilidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfRentabilidadeActionPerformed
        if (!jfRentabilidade.getText().equals("")) {
            jfDescontoVip.requestFocus();
        }
    }//GEN-LAST:event_jfRentabilidadeActionPerformed

    private void jfDescontoVipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfDescontoVipActionPerformed
        if (!jfDescontoVip.getText().equals("")) {
            jfMetaPower.requestFocus();
        }
    }//GEN-LAST:event_jfDescontoVipActionPerformed

    private void jfMetaPowerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfMetaPowerActionPerformed
        if (!jfMetaPower.getText().equals("")) {
            jfMetaGnano.requestFocus();
        }
    }//GEN-LAST:event_jfMetaPowerActionPerformed

    private void jfMetaGnanoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfMetaGnanoActionPerformed
        if (!jfMetaGnano.getText().equals("")) {
            jfMetaGerovital.requestFocus();
        }
    }//GEN-LAST:event_jfMetaGnanoActionPerformed

    private void jfMetaGerovitalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfMetaGerovitalActionPerformed
        if (!jfMetaGerovital.getText().equals("")) {
            jfMetaClinicFarma.requestFocus();
        }
    }//GEN-LAST:event_jfMetaGerovitalActionPerformed

    private void jfMetaClinicFarmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfMetaClinicFarmaActionPerformed
        if (!jfMetaClinicFarma.getText().equals("")) {
            jfMetaAnotacao.requestFocus();
        }
    }//GEN-LAST:event_jfMetaClinicFarmaActionPerformed

    private void jfMetaAnotacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfMetaAnotacaoActionPerformed
        if (!jfMetaAnotacao.getText().equals("")) {
            jfMes.requestFocus();
        }
    }//GEN-LAST:event_jfMetaAnotacaoActionPerformed

    private void jfMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfMesActionPerformed
        jfAno.requestFocus();
    }//GEN-LAST:event_jfMesActionPerformed

    private void jCheckBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox1MouseClicked
        if (jCheckBox1.isSelected()) {
            bloqueiaCampos();
        } else if (!jCheckBox1.isSelected()) {
            desbloqueiaCampos();
        }
    }//GEN-LAST:event_jCheckBox1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTabela;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbExcluir;
    private javax.swing.JButton jbLimpar;
    private javax.swing.JButton jbPesquisar;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JFormattedTextField jfAno;
    private javax.swing.JFormattedTextField jfDataRegistro;
    private javax.swing.JFormattedTextField jfDescontoVip;
    private javax.swing.JFormattedTextField jfId;
    private javax.swing.JComboBox<String> jfMes;
    private javax.swing.JFormattedTextField jfMetaAnotacao;
    private javax.swing.JFormattedTextField jfMetaClinicFarma;
    private javax.swing.JFormattedTextField jfMetaGerovital;
    private javax.swing.JFormattedTextField jfMetaGnano;
    private javax.swing.JFormattedTextField jfMetaPower;
    private javax.swing.JFormattedTextField jfMetaRecarga;
    private javax.swing.JFormattedTextField jfMetaVenda;
    private javax.swing.JFormattedTextField jfNomeDaLoja;
    private javax.swing.JFormattedTextField jfNumeroDaLoja;
    private javax.swing.JFormattedTextField jfRentabilidade;
    // End of variables declaration//GEN-END:variables
}
