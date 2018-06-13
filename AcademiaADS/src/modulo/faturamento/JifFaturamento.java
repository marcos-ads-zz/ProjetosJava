package modulo.faturamento;

import modulo.usuarios.UsuarioDAO;
import modulo.desenvolvimento.frames.JifCancelamentos;
import modulo.sobre.Versao;
import java.awt.Dimension;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modulo.usuarios.Usuario;
import modulo.metodos.Funcao;
import modulo.view.principal.JfPrincipal;

/**
 *
 * @author Marcos Junior
 */
public final class JifFaturamento extends javax.swing.JInternalFrame {

    private UsuarioDAO DAOUSER;
    private Usuario objUser;
    private Versao versao;
    private FaturamentoDAO DAOFATCAIXA;
    private Faturamento objFCaixa;
    private JFormattedTextField[] txts;
    private JifCancelamentos jfCancela = null;
    private Funcao fun;

    public JifFaturamento() {
        initComponents();
        versao = new Versao();
        DAOUSER = new UsuarioDAO();
        fun = new Funcao();
        DAOFATCAIXA = new FaturamentoDAO();
        setTitle("Faturamento Loja: " + versao.getVersao());
        jtMatricula.requestFocus();
        txts = new JFormattedTextField[]{
            jfSeusClientesCaixa,
            jfClientesCreditoDigital,
            jfClientesFatura,
            jfItensVendidos,
            jtMatricula,
            jfPDV,
            jfQuantidadeDeTrocas,
            jfQuantidadeDevolucao,
            jfValorCreditoDigital,
            jfValorCupomDescontoVip,
            jfValorDevolucao,
            jfValorTotalFita,
            jfSuaVendasCaixa,
            jfTotalClientes,
            jfValorFaturas,
            jfValorTrocas,
            jfValorDescontoVip,
            jfDataDaVenda};
        SetValores();
        Atualizar();
        IniciaSelecionaCaixa();
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    public void limpaCampos() {
        for (JFormattedTextField txt : txts) {
            if (txt.getText().equals("")) {
            } else {
                txt.setText("");
            }
        }
        jbSalvar.setText("SALVAR");
        jtId.setText("");
        jfTurno.setSelectedIndex(0);

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

    public void Atualizar() {
        try {
            PreencheTabelaDaView();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Carregar Dados: " + ex.getMessage());
        }
    }

    public void IniciaSelecionaCaixa() {
        jfValorCupomDescontoVip.setEnabled(false);
        jfValorDescontoVip.setEnabled(false);
        jfItensVendidos.setEnabled(false);
        jfValorCupomDescontoVip.setText("0,00");
        jfValorDescontoVip.setText("0,00");
        jfItensVendidos.setText("0");
    }

    public void SelecionaCaixa() {
        if (jbSalvar.getText().equals("SALVAR")) {
            if (btUltimoCaixa.isSelected()) {
                jfValorCupomDescontoVip.setEnabled(true);
                jfValorDescontoVip.setEnabled(true);
                jfItensVendidos.setEnabled(true);
                jfValorCupomDescontoVip.setText("");
                jfValorDescontoVip.setText("");
                jfItensVendidos.setText("");
            } else {
                jfValorCupomDescontoVip.setEnabled(false);
                jfValorDescontoVip.setEnabled(false);
                jfItensVendidos.setEnabled(false);
                jfValorCupomDescontoVip.setText("0,00");
                jfValorDescontoVip.setText("0,00");
                jfItensVendidos.setText("0");
            }
        } else if (jbSalvar.getText().equals("EDITAR")) {
            if (btUltimoCaixa.isSelected()) {
                jfValorCupomDescontoVip.setEnabled(true);
                jfValorDescontoVip.setEnabled(true);
                jfItensVendidos.setEnabled(true);
            } else {
                jfValorCupomDescontoVip.setEnabled(false);
                jfValorDescontoVip.setEnabled(false);
                jfItensVendidos.setEnabled(false);
            }
        }

    }

    public void SetValores() {
        jfDataDaVenda.setText(fun.dataAtualDate());
    }

    public boolean validaCamposBreak() {
        boolean check = true;
        for (JFormattedTextField txt : txts) {
            if (txt.getText().equals("")) {
                check = false;
                break;
            }
        }
        if (!VerificaMatricula()) {
            check = false;
            JOptionPane.showMessageDialog(this, "Matrícula Incorreta!");
        }

        return check;
    }

    public boolean validaCampos() {
        boolean check = true;
        for (JFormattedTextField txt : txts) {
            if (txt.getText().equals("")) {
                txt.setBackground(new java.awt.Color(255, 0, 0));
            } else {
                txt.setBackground(new java.awt.Color(214, 217, 223));
                check = true;
            }
        }
        if (jfTurno.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Selecione um Turno");
            check = false;
        }
        return check;
    }

    public void Teste() {
        for (JFormattedTextField txt : txts) {
            if (txt.getText().equals("")) {
                txt.setBackground(new java.awt.Color(255, 0, 0));
            } else {
                txt.setBackground(new java.awt.Color(214, 217, 223));
            }
        }
    }

    public boolean CarregarDadosParaSalvar() throws ParseException, Exception {
        objFCaixa = new Faturamento();
        objFCaixa.setDate_registro(fun.atualDateSQL());
        objFCaixa.setDate_venda(fun.convertDateStringToDateSQL(jfDataDaVenda.getText()));
        objFCaixa.setId_caixa(DAOUSER.PesquisaPorMatricula(Integer.parseInt(jtMatricula.getText())).getId());
        objFCaixa.setMatricula(Integer.parseInt(jtMatricula.getText()));
        objFCaixa.setPdv(Integer.parseInt(jfPDV.getText()));
        objFCaixa.setQtd_cli_caixa(Integer.parseInt(jfSeusClientesCaixa.getText()));
        objFCaixa.setQtd_cli_credito(Integer.parseInt(jfClientesCreditoDigital.getText()));
        objFCaixa.setQtd_cli_fatura(Integer.parseInt(jfClientesFatura.getText()));
        objFCaixa.setQtd_cli_geral(Integer.parseInt(jfTotalClientes.getText()));
        objFCaixa.setQtd_devolucao(Integer.parseInt(jfQuantidadeDevolucao.getText()));
        objFCaixa.setQtd_itens_vendidos(Integer.parseInt(jfItensVendidos.getText()));
        objFCaixa.setQtd_trocas(Integer.parseInt(jfQuantidadeDeTrocas.getText()));
        objFCaixa.setTurno(jfTurno.getSelectedItem().toString().toUpperCase());
        objFCaixa.setValor_credito_digital(fun.convertDoubleStringToDouble(jfValorCreditoDigital.getText()));
        objFCaixa.setValor_cupom_vip(fun.convertDoubleStringToDouble(jfValorCupomDescontoVip.getText()));
        objFCaixa.setValor_desc_vip(fun.convertDoubleStringToDouble(jfValorDescontoVip.getText()));
        objFCaixa.setValor_devolucoes(fun.convertDoubleStringToDouble(jfValorDevolucao.getText()));
        objFCaixa.setValor_faturas(fun.convertDoubleStringToDouble(jfValorFaturas.getText()));
        objFCaixa.setValor_fita(fun.convertDoubleStringToDouble(jfValorTotalFita.getText()));
        objFCaixa.setValor_trocas(fun.convertDoubleStringToDouble(jfValorTrocas.getText()));
        objFCaixa.setValor_venda_caixa(fun.convertDoubleStringToDouble(jfSuaVendasCaixa.getText()));
        return objFCaixa != null;
    }

    public boolean CarregarDadosParaEditar() throws ParseException, Exception {
        objFCaixa = new Faturamento();
        objFCaixa.setId_fat(fun.convertToInt(jtId.getText()));
        objFCaixa.setDate_registro(fun.atualDateSQL());
        objFCaixa.setDate_venda(fun.convertDateStringToDateSQL(jfDataDaVenda.getText()));
        objFCaixa.setId_caixa(DAOUSER.PesquisaPorMatricula(Integer.parseInt(jtMatricula.getText())).getId());
        objFCaixa.setMatricula(Integer.parseInt(jtMatricula.getText()));
        objFCaixa.setPdv(Integer.parseInt(jfPDV.getText()));
        objFCaixa.setQtd_cli_caixa(Integer.parseInt(jfSeusClientesCaixa.getText()));
        objFCaixa.setQtd_cli_credito(Integer.parseInt(jfClientesCreditoDigital.getText()));
        objFCaixa.setQtd_cli_fatura(Integer.parseInt(jfClientesFatura.getText()));
        objFCaixa.setQtd_cli_geral(Integer.parseInt(jfTotalClientes.getText()));
        objFCaixa.setQtd_devolucao(Integer.parseInt(jfQuantidadeDevolucao.getText()));
        objFCaixa.setQtd_itens_vendidos(Integer.parseInt(jfItensVendidos.getText()));
        objFCaixa.setQtd_trocas(Integer.parseInt(jfQuantidadeDeTrocas.getText()));
        objFCaixa.setTurno(jfTurno.getSelectedItem().toString().toUpperCase());
        objFCaixa.setValor_credito_digital(fun.convertDoubleStringToDouble(jfValorCreditoDigital.getText()));
        objFCaixa.setValor_cupom_vip(fun.convertDoubleStringToDouble(jfValorCupomDescontoVip.getText()));
        objFCaixa.setValor_desc_vip(fun.convertDoubleStringToDouble(jfValorDescontoVip.getText()));
        objFCaixa.setValor_devolucoes(fun.convertDoubleStringToDouble(jfValorDevolucao.getText()));
        objFCaixa.setValor_faturas(fun.convertDoubleStringToDouble(jfValorFaturas.getText()));
        objFCaixa.setValor_fita(fun.convertDoubleStringToDouble(jfValorTotalFita.getText()));
        objFCaixa.setValor_trocas(fun.convertDoubleStringToDouble(jfValorTrocas.getText()));
        objFCaixa.setValor_venda_caixa(fun.convertDoubleStringToDouble(jfSuaVendasCaixa.getText()));
        return objFCaixa != null;
    }

    public int InserirDados() throws Exception {
        int id = 0;
        if (CarregarDadosParaSalvar()) {
            id = DAOFATCAIXA.Insert(objFCaixa);
        }
        return id;
    }

    public boolean EditarDados() throws Exception {
        boolean check = false;
        if (CarregarDadosParaEditar()) {
            check = DAOFATCAIXA.Update(objFCaixa);
        }
        return check;
    }

    public boolean VerificaValores() {
        boolean check = true;
        double valor1, valor2, valor3;
        valor1 = fun.convertDoubleStringToDouble(jfValorTotalFita.getText());
        valor2 = fun.convertDoubleStringToDouble(jfValorCreditoDigital.getText());
        valor3 = fun.convertDoubleStringToDouble(jfValorFaturas.getText());

        int valor4, valor5, valor6;
        valor4 = Integer.parseInt(jfTotalClientes.getText());
        valor5 = Integer.parseInt(jfClientesCreditoDigital.getText());
        valor6 = Integer.parseInt(jfClientesFatura.getText());

        if (valor1 < (valor2 + valor3)) {
            check = false;
            JOptionPane.showMessageDialog(this, "Total da Fita Diverge dos Valores da Recarga e Fatura Informados!");
        } else if (valor4 < (valor5 + valor6)) {
            check = false;
            JOptionPane.showMessageDialog(this, "Total de Clientes Diverge dos Valores\n"
                    + "Cliente Recarga e Cliente Fatura Informados!");
        }
        return check;
    }

    public void HabilitaCamposEditar() {
        if (jbSalvar.getText().equals("EDITAR")) {
            jfValorCupomDescontoVip.setEnabled(true);
            jfValorDescontoVip.setEnabled(true);
            jfItensVendidos.setEnabled(true);
        }
    }

    public void EditaValores() {
        jbSalvar.setText("EDITAR");
        if (jTableDeDados.getSelectedRow() != -1) {
            Object id, pdv, matric, data, turno,
                    totaFita, clifat, cupvip, valortroca, devQtd,
                    totalCred, clicred, vipDesc, qtdTrocas, suaVenda,
                    totalFaturas, cliFaturas, itensVend, devValor, seuCli;

            id = jTableDeDados.getValueAt(jTableDeDados.getSelectedRow(), 0);
            jtId.setText(id.toString());

            pdv = jTableDeDados.getValueAt(jTableDeDados.getSelectedRow(), 1);
            jfPDV.setText(pdv.toString());

            turno = jTableDeDados.getValueAt(jTableDeDados.getSelectedRow(), 2);
            jfTurno.setSelectedItem(turno.toString());

            matric = jTableDeDados.getValueAt(jTableDeDados.getSelectedRow(), 3);
            jtMatricula.setText(matric.toString());

            data = jTableDeDados.getValueAt(jTableDeDados.getSelectedRow(), 4);
            jfDataDaVenda.setText(data.toString());

            totaFita = jTableDeDados.getValueAt(jTableDeDados.getSelectedRow(), 5);
            jfValorTotalFita.setText(totaFita.toString());

            clifat = jTableDeDados.getValueAt(jTableDeDados.getSelectedRow(), 6);
            jfTotalClientes.setText(clifat.toString());

            itensVend = jTableDeDados.getValueAt(jTableDeDados.getSelectedRow(), 7);
            jfItensVendidos.setText(itensVend.toString());

            totalCred = jTableDeDados.getValueAt(jTableDeDados.getSelectedRow(), 8);
            jfValorCreditoDigital.setText(totalCred.toString());

            clicred = jTableDeDados.getValueAt(jTableDeDados.getSelectedRow(), 9);
            jfClientesCreditoDigital.setText(clicred.toString());

            totalFaturas = jTableDeDados.getValueAt(jTableDeDados.getSelectedRow(), 10);
            jfValorFaturas.setText(totalFaturas.toString());

            cliFaturas = jTableDeDados.getValueAt(jTableDeDados.getSelectedRow(), 11);
            jfClientesFatura.setText(cliFaturas.toString());

            valortroca = jTableDeDados.getValueAt(jTableDeDados.getSelectedRow(), 12);
            jfValorTrocas.setText(valortroca.toString());

            qtdTrocas = jTableDeDados.getValueAt(jTableDeDados.getSelectedRow(), 13);
            jfQuantidadeDeTrocas.setText(qtdTrocas.toString());

            devValor = jTableDeDados.getValueAt(jTableDeDados.getSelectedRow(), 14);
            jfValorDevolucao.setText(devValor.toString());

            devQtd = jTableDeDados.getValueAt(jTableDeDados.getSelectedRow(), 15);
            jfQuantidadeDevolucao.setText(devQtd.toString());

            vipDesc = jTableDeDados.getValueAt(jTableDeDados.getSelectedRow(), 16);
            jfValorDescontoVip.setText(vipDesc.toString());

            cupvip = jTableDeDados.getValueAt(jTableDeDados.getSelectedRow(), 17);
            jfValorCupomDescontoVip.setText(cupvip.toString());

            suaVenda = jTableDeDados.getValueAt(jTableDeDados.getSelectedRow(), 18);
            jfSuaVendasCaixa.setText(suaVenda.toString());

            seuCli = jTableDeDados.getValueAt(jTableDeDados.getSelectedRow(), 19);
            jfSeusClientesCaixa.setText(seuCli.toString());
        }
        HabilitaCamposEditar();
    }

    public List<Faturamento> CalcularValores() throws Exception {
        List<Faturamento> fat = null;
        fat = DAOFATCAIXA.TabelaPesquisaData(fun.atualDateSQL(), fun.atualDateSQL());
        int cli_caixa = 0, cli_credito = 0, cli_fatura = 0,
                cli_geral = 0, cli_devolucao = 0, itens = 0,
                cli_trocas = 0;
        double v_cd = 0, v_cupomv = 0, v_dv = 0, v_devol = 0, v_faturas = 0,
                v_fita = 0, v_trocas = 0, v_caixa = 0;
        double fita = 0;
        for (Faturamento a : fat) {
            cli_caixa += a.getQtd_cli_caixa();
            cli_credito += a.getQtd_cli_credito();
            cli_fatura += a.getQtd_cli_fatura();
            cli_geral += a.getQtd_cli_geral();
            cli_devolucao += a.getQtd_devolucao();
            itens += a.getQtd_itens_vendidos();
            cli_trocas += a.getQtd_trocas();

            v_cd += a.getValor_credito_digital();
            v_cupomv += a.getValor_cupom_vip();
            v_dv += a.getValor_desc_vip();
            v_devol += a.getValor_devolucoes();
            v_faturas += a.getValor_faturas();
            v_fita += a.getValor_fita();
            v_trocas += a.getValor_trocas();
            v_caixa += a.getValor_venda_caixa();

        }
//        List<CTF> ctf = null;
//        ctf = DAOCTF.TabelaPesquisaDataAtual(fun.atualDateSQL(), fun.atualDateSQL());
//        jTotalQuantidadeDePendencias.setText(Integer.toString(ctf.size()));
        double valorctf = 0;
//        for (CTF ct : ctf) {
//            valorctf += ct.getValor();
//        }

        jTotalPendenciasCTF.setText(fun.convertDoubleToString(valorctf));
        jTotalFaturamento.setText(fun.convertDoubleToString(v_fita - (v_faturas + v_cd)));
        jTotalClientesGeral.setText(Integer.toString(cli_geral - (cli_credito + cli_fatura)));
        jTotalClientesFatura.setText(Integer.toString(cli_fatura));
        jTotalCreditoClientes.setText(Integer.toString(cli_credito));
        jTotalCreditoDigital.setText(fun.convertDoubleToString(v_cd));
        jTotalCupomVip.setText(fun.convertDoubleToString(v_cupomv));
        //jTotalDePopular.setText();
        jTotalDescontoVip.setText(fun.convertDoubleToString(v_dv));
        jTotalDevolucao.setText(fun.convertDoubleToString(v_devol));
        jTotalFatura.setText(fun.convertDoubleToString(v_faturas));
        jTotalItensVendidos.setText(Integer.toString(itens));
        jTotaldeTrocasValor.setText(fun.convertDoubleToString(v_trocas));
        jTotalQuantidadeDeTrocas.setText(Integer.toString(cli_trocas));
        jTotalQuantidadeDevolucoes.setText(Integer.toString(cli_devolucao));
        return fat;
    }

    public void PreencheTabelaDaView() throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jTableDeDados.getModel();
        modelo.setNumRows(0);
        List<Faturamento> fat = CalcularValores();
        fat.forEach((a) -> {
            try {
                modelo.addRow(new Object[]{
                    a.getId_fat(),
                    a.getPdv(), a.getTurno(), a.getMatricula(),
                    fun.convertDataSQLToDateString(a.getDate_venda()),
                    fun.convertDoubleToString(a.getValor_fita()),
                    a.getQtd_cli_geral(),
                    a.getQtd_itens_vendidos(),
                    fun.convertDoubleToString(a.getValor_credito_digital()),
                    a.getQtd_cli_credito(),
                    fun.convertDoubleToString(a.getValor_faturas()),
                    a.getQtd_cli_fatura(),
                    fun.convertDoubleToString(a.getValor_devolucoes()),
                    a.getQtd_devolucao(),
                    fun.convertDoubleToString(a.getValor_trocas()),
                    a.getQtd_trocas(),
                    fun.convertDoubleToString(a.getValor_cupom_vip()),
                    fun.convertDoubleToString(a.getValor_desc_vip()),
                    fun.convertDoubleToString(a.getValor_venda_caixa()),
                    a.getQtd_cli_caixa()
                });
            } catch (Exception ex) {
                Logger.getLogger(JifFaturamento.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

//    public void PreencheTabelaDaViewTodos() throws Exception {
//        DefaultTableModel modelo = (DefaultTableModel) jTableDeDados.getModel();
//        modelo.setNumRows(0);
//        List<Faturamento> fat = DAOFATCAIXA.TabelaPesquisa();
//        fat.forEach((a) -> {
//            try {
//                modelo.addRow(new Object[]{
//                    a.getId_fat(),
//                    a.getPdv(), a.getTurno(), a.getMatricula(),
//                    fun.convertDataSQLToDateString(a.getDate_venda()),
//                    fun.convertDoubleToString(a.getValor_fita()),
//                    a.getQtd_cli_geral(),
//                    a.getQtd_itens_vendidos(),
//                    fun.convertDoubleToString(a.getValor_credito_digital()),
//                    a.getQtd_cli_credito(),
//                    fun.convertDoubleToString(a.getValor_faturas()),
//                    a.getQtd_cli_fatura(),
//                    fun.convertDoubleToString(a.getValor_devolucoes()),
//                    a.getQtd_devolucao(),
//                    fun.convertDoubleToString(a.getValor_trocas()),
//                    a.getQtd_trocas(),
//                    fun.convertDoubleToString(a.getValor_cupom_vip()),
//                    fun.convertDoubleToString(a.getValor_desc_vip()),
//                    fun.convertDoubleToString(a.getValor_venda_caixa()),
//                    a.getQtd_cli_caixa()
//                });
//            } catch (Exception ex) {
//                Logger.getLogger(JifFaturamento.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTotalFaturamento = new javax.swing.JFormattedTextField();
        jLabel20 = new javax.swing.JLabel();
        jTotalClientesGeral = new javax.swing.JFormattedTextField();
        jTotalCreditoDigital = new javax.swing.JFormattedTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTotalCreditoClientes = new javax.swing.JFormattedTextField();
        jLabel23 = new javax.swing.JLabel();
        jTotalCupomVip = new javax.swing.JFormattedTextField();
        jTotalDescontoVip = new javax.swing.JFormattedTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jTotalClientesFatura = new javax.swing.JFormattedTextField();
        jTotalFatura = new javax.swing.JFormattedTextField();
        jLabel27 = new javax.swing.JLabel();
        jTotalPendenciasCTF = new javax.swing.JFormattedTextField();
        jTotalQuantidadeDePendencias = new javax.swing.JFormattedTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jTotalItensVendidos = new javax.swing.JFormattedTextField();
        jTotalDePopular = new javax.swing.JFormattedTextField();
        jLabel31 = new javax.swing.JLabel();
        jTotalDevolucao = new javax.swing.JFormattedTextField();
        jTotalQuantidadeDevolucoes = new javax.swing.JFormattedTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jTotalQuantidadeDeTrocas = new javax.swing.JFormattedTextField();
        jTotaldeTrocasValor = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jfValorTotalFita = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jfValorCreditoDigital = new javax.swing.JFormattedTextField();
        jfPDV = new javax.swing.JFormattedTextField();
        jtMatricula = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jfValorFaturas = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jfTotalClientes = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jfClientesFatura = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        jfClientesCreditoDigital = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jfValorCupomDescontoVip = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        jfValorDescontoVip = new javax.swing.JFormattedTextField();
        jbPopular = new javax.swing.JButton();
        jbCTF = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jfValorTrocas = new javax.swing.JFormattedTextField();
        jLabel15 = new javax.swing.JLabel();
        jfQuantidadeDeTrocas = new javax.swing.JFormattedTextField();
        jLabel16 = new javax.swing.JLabel();
        jfValorDevolucao = new javax.swing.JFormattedTextField();
        jLabel17 = new javax.swing.JLabel();
        jfQuantidadeDevolucao = new javax.swing.JFormattedTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jfSuaVendasCaixa = new javax.swing.JFormattedTextField();
        jfSeusClientesCaixa = new javax.swing.JFormattedTextField();
        jbSalvar = new javax.swing.JButton();
        jfItensVendidos = new javax.swing.JFormattedTextField();
        jLabel35 = new javax.swing.JLabel();
        jfTurno = new javax.swing.JComboBox<>();
        jbLimpar = new javax.swing.JButton();
        jfDataDaVenda = new javax.swing.JFormattedTextField();
        jbAtualizar = new javax.swing.JButton();
        btUltimoCaixa = new javax.swing.JCheckBox();
        jbCancelados = new javax.swing.JButton();
        jtId = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDeDados = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jPanel2.setBackground(new java.awt.Color(204, 255, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.MatteBorder(null), "Faturamento do Dia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 1, 14), new java.awt.Color(0, 153, 51))); // NOI18N

        jLabel1.setText("Total Faturamento");

        jTotalFaturamento.setEditable(false);
        jTotalFaturamento.setBackground(new java.awt.Color(102, 255, 102));
        jTotalFaturamento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTotalFaturamento.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N

        jLabel20.setText("Total Clientes");

        jTotalClientesGeral.setEditable(false);
        jTotalClientesGeral.setBackground(new java.awt.Color(102, 255, 102));
        jTotalClientesGeral.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTotalClientesGeral.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N

        jTotalCreditoDigital.setEditable(false);
        jTotalCreditoDigital.setBackground(new java.awt.Color(102, 255, 102));
        jTotalCreditoDigital.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTotalCreditoDigital.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N

        jLabel21.setText("Total Credito Digital");

        jLabel22.setText("Total Credito Clientes");

        jTotalCreditoClientes.setEditable(false);
        jTotalCreditoClientes.setBackground(new java.awt.Color(102, 255, 102));
        jTotalCreditoClientes.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTotalCreditoClientes.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N

        jLabel23.setText("Total Cupom Vip");

        jTotalCupomVip.setEditable(false);
        jTotalCupomVip.setBackground(new java.awt.Color(102, 255, 102));
        jTotalCupomVip.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTotalCupomVip.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N

        jTotalDescontoVip.setEditable(false);
        jTotalDescontoVip.setBackground(new java.awt.Color(102, 255, 102));
        jTotalDescontoVip.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTotalDescontoVip.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N

        jLabel24.setText("Total Desconto Vip");

        jLabel25.setText("Total Fatura");

        jLabel26.setText("Total Cientes Fatura");

        jTotalClientesFatura.setEditable(false);
        jTotalClientesFatura.setBackground(new java.awt.Color(102, 255, 102));
        jTotalClientesFatura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTotalClientesFatura.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N

        jTotalFatura.setEditable(false);
        jTotalFatura.setBackground(new java.awt.Color(102, 255, 102));
        jTotalFatura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTotalFatura.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N

        jLabel27.setText("Total Pendencias CTF");

        jTotalPendenciasCTF.setEditable(false);
        jTotalPendenciasCTF.setBackground(new java.awt.Color(102, 255, 102));
        jTotalPendenciasCTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTotalPendenciasCTF.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N

        jTotalQuantidadeDePendencias.setEditable(false);
        jTotalQuantidadeDePendencias.setBackground(new java.awt.Color(102, 255, 102));
        jTotalQuantidadeDePendencias.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTotalQuantidadeDePendencias.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N

        jLabel28.setText("Quantidade Pendencias");

        jLabel29.setText("Total Popular");

        jLabel30.setText("Total Itens Vendidos");

        jTotalItensVendidos.setEditable(false);
        jTotalItensVendidos.setBackground(new java.awt.Color(102, 255, 102));
        jTotalItensVendidos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTotalItensVendidos.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N

        jTotalDePopular.setEditable(false);
        jTotalDePopular.setBackground(new java.awt.Color(102, 255, 102));
        jTotalDePopular.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTotalDePopular.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N

        jLabel31.setText("Total Devolução");

        jTotalDevolucao.setEditable(false);
        jTotalDevolucao.setBackground(new java.awt.Color(102, 255, 102));
        jTotalDevolucao.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTotalDevolucao.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N

        jTotalQuantidadeDevolucoes.setEditable(false);
        jTotalQuantidadeDevolucoes.setBackground(new java.awt.Color(102, 255, 102));
        jTotalQuantidadeDevolucoes.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTotalQuantidadeDevolucoes.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N

        jLabel32.setText("Quantidade de Devoluções");

        jLabel33.setText("Total de Trocas");

        jLabel34.setText("Quantidade de Trocas");

        jTotalQuantidadeDeTrocas.setEditable(false);
        jTotalQuantidadeDeTrocas.setBackground(new java.awt.Color(102, 255, 102));
        jTotalQuantidadeDeTrocas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTotalQuantidadeDeTrocas.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N

        jTotaldeTrocasValor.setEditable(false);
        jTotaldeTrocasValor.setBackground(new java.awt.Color(102, 255, 102));
        jTotaldeTrocasValor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTotaldeTrocasValor.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTotalFaturamento, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTotalClientesGeral, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTotalCreditoDigital, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTotalCreditoClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTotalCupomVip, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTotalDescontoVip, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTotalFatura, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTotalClientesFatura, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTotalPendenciasCTF, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTotalQuantidadeDePendencias, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTotalDePopular, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTotalItensVendidos, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTotalDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTotalQuantidadeDevolucoes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTotaldeTrocasValor, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTotalQuantidadeDeTrocas, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(116, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(jTotalDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(jTotalQuantidadeDevolucoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(jTotaldeTrocasValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(jTotalQuantidadeDeTrocas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(jTotalPendenciasCTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(jTotalQuantidadeDePendencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(jTotalDePopular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(jTotalItensVendidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(jTotalCupomVip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(jTotalDescontoVip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(jTotalFatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(jTotalClientesFatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTotalFaturamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jTotalClientesGeral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(jTotalCreditoDigital, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(jTotalCreditoClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(14, 14, 14))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.MatteBorder(null), "Faturamento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 1, 14), new java.awt.Color(0, 153, 51))); // NOI18N

        jLabel2.setText("Matrícula do Operador");

        jLabel3.setText("Pdv");

        jLabel4.setText("Data");

        jLabel5.setText("Total Fita");

        jfValorTotalFita.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfValorTotalFita.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfValorTotalFita.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfValorTotalFita.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jfValorTotalFitaMouseClicked(evt);
            }
        });
        jfValorTotalFita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfValorTotalFitaActionPerformed(evt);
            }
        });

        jLabel6.setText("Credito Digital");

        jfValorCreditoDigital.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfValorCreditoDigital.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfValorCreditoDigital.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfValorCreditoDigital.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jfValorCreditoDigitalMouseClicked(evt);
            }
        });
        jfValorCreditoDigital.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfValorCreditoDigitalActionPerformed(evt);
            }
        });

        jfPDV.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfPDV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfPDV.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfPDV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jfPDVMouseClicked(evt);
            }
        });
        jfPDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfPDVActionPerformed(evt);
            }
        });

        jtMatricula.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jtMatricula.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtMatricula.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jtMatricula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtMatriculaMouseClicked(evt);
            }
        });
        jtMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtMatriculaActionPerformed(evt);
            }
        });

        jLabel7.setText("Valor Faturas");

        jfValorFaturas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfValorFaturas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfValorFaturas.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfValorFaturas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jfValorFaturasMouseClicked(evt);
            }
        });
        jfValorFaturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfValorFaturasActionPerformed(evt);
            }
        });

        jLabel8.setText("Clientes Geral");

        jfTotalClientes.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfTotalClientes.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfTotalClientes.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfTotalClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jfTotalClientesMouseClicked(evt);
            }
        });
        jfTotalClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfTotalClientesActionPerformed(evt);
            }
        });

        jLabel10.setText("Cliente Fatura");

        jfClientesFatura.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfClientesFatura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfClientesFatura.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfClientesFatura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jfClientesFaturaMouseClicked(evt);
            }
        });
        jfClientesFatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfClientesFaturaActionPerformed(evt);
            }
        });

        jLabel11.setText("Cliente Credito");

        jfClientesCreditoDigital.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfClientesCreditoDigital.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfClientesCreditoDigital.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfClientesCreditoDigital.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jfClientesCreditoDigitalMouseClicked(evt);
            }
        });
        jfClientesCreditoDigital.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfClientesCreditoDigitalActionPerformed(evt);
            }
        });

        jLabel12.setText("Valor Cupom Vip");

        jfValorCupomDescontoVip.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfValorCupomDescontoVip.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfValorCupomDescontoVip.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfValorCupomDescontoVip.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jfValorCupomDescontoVipMouseClicked(evt);
            }
        });
        jfValorCupomDescontoVip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfValorCupomDescontoVipActionPerformed(evt);
            }
        });

        jLabel13.setText("Vip Desconto");

        jfValorDescontoVip.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        jfValorDescontoVip.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfValorDescontoVip.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfValorDescontoVip.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jfValorDescontoVipMouseClicked(evt);
            }
        });
        jfValorDescontoVip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfValorDescontoVipActionPerformed(evt);
            }
        });

        jbPopular.setBackground(new java.awt.Color(255, 51, 51));
        jbPopular.setText("POPULAR");
        jbPopular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPopularActionPerformed(evt);
            }
        });

        jbCTF.setBackground(new java.awt.Color(51, 255, 51));
        jbCTF.setText("CTF");
        jbCTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCTFActionPerformed(evt);
            }
        });

        jLabel14.setText("Valor das Trocas");

        jfValorTrocas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfValorTrocas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfValorTrocas.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfValorTrocas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jfValorTrocasMouseClicked(evt);
            }
        });
        jfValorTrocas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfValorTrocasActionPerformed(evt);
            }
        });

        jLabel15.setText("Quant. Trocas");

        jfQuantidadeDeTrocas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfQuantidadeDeTrocas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfQuantidadeDeTrocas.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfQuantidadeDeTrocas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jfQuantidadeDeTrocasMouseClicked(evt);
            }
        });
        jfQuantidadeDeTrocas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfQuantidadeDeTrocasActionPerformed(evt);
            }
        });

        jLabel16.setText("Devolução Valor");

        jfValorDevolucao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfValorDevolucao.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfValorDevolucao.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfValorDevolucao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jfValorDevolucaoMouseClicked(evt);
            }
        });
        jfValorDevolucao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfValorDevolucaoActionPerformed(evt);
            }
        });

        jLabel17.setText("Devolução Qtd");

        jfQuantidadeDevolucao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfQuantidadeDevolucao.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfQuantidadeDevolucao.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfQuantidadeDevolucao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jfQuantidadeDevolucaoMouseClicked(evt);
            }
        });
        jfQuantidadeDevolucao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfQuantidadeDevolucaoActionPerformed(evt);
            }
        });

        jLabel18.setText("Sua Venda");

        jLabel19.setText("Clientes");

        jfSuaVendasCaixa.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jfSuaVendasCaixa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfSuaVendasCaixa.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfSuaVendasCaixa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jfSuaVendasCaixaMouseClicked(evt);
            }
        });
        jfSuaVendasCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfSuaVendasCaixaActionPerformed(evt);
            }
        });

        jfSeusClientesCaixa.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfSeusClientesCaixa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfSeusClientesCaixa.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfSeusClientesCaixa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jfSeusClientesCaixaMouseClicked(evt);
            }
        });
        jfSeusClientesCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfSeusClientesCaixaActionPerformed(evt);
            }
        });

        jbSalvar.setText("SALVAR");
        jbSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvarActionPerformed(evt);
            }
        });

        jfItensVendidos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jfItensVendidos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfItensVendidos.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfItensVendidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jfItensVendidosMouseClicked(evt);
            }
        });
        jfItensVendidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfItensVendidosActionPerformed(evt);
            }
        });

        jLabel35.setText("Itens Vendidos");

        jfTurno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TURNO", "MANHA", "TARDE" }));
        jfTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfTurnoActionPerformed(evt);
            }
        });

        jbLimpar.setText("LIMPAR");
        jbLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLimparActionPerformed(evt);
            }
        });

        try {
            jfDataDaVenda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jfDataDaVenda.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jfDataDaVenda.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        jfDataDaVenda.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jfDataDaVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfDataDaVendaActionPerformed(evt);
            }
        });

        jbAtualizar.setText("ATUALIZAR");
        jbAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAtualizarActionPerformed(evt);
            }
        });

        btUltimoCaixa.setText("Último Caixa");
        btUltimoCaixa.setActionCommand("ultimo");
        btUltimoCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUltimoCaixaActionPerformed(evt);
            }
        });

        jbCancelados.setBackground(new java.awt.Color(255, 255, 51));
        jbCancelados.setText("CANELADOS");
        jbCancelados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCanceladosActionPerformed(evt);
            }
        });

        jtId.setEditable(false);
        jtId.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jfValorCreditoDigital, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jfValorTotalFita, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jfValorFaturas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jfClientesFatura, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel35))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jfTotalClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jfClientesCreditoDigital, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jfValorDescontoVip, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(jfValorCupomDescontoVip, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(jfItensVendidos, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jfQuantidadeDeTrocas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jfValorTrocas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jfValorDevolucao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jfSeusClientesCaixa)
                            .addComponent(jfSuaVendasCaixa)
                            .addComponent(jfQuantidadeDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jbSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                            .addComponent(jbLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbAtualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jbCancelados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbCTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbPopular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jfPDV, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jfDataDaVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jfTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtId, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btUltimoCaixa)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jfPDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jfDataDaVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jfTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btUltimoCaixa))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jbPopular)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbCTF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbCancelados))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jfValorTotalFita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)
                                    .addComponent(jfTotalClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(jfValorCreditoDigital, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11)
                                    .addComponent(jfClientesCreditoDigital, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jfValorFaturas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)
                                    .addComponent(jfClientesFatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel35)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jfValorCupomDescontoVip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jfValorDescontoVip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jfItensVendidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16))))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jfValorTrocas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel17)
                                .addComponent(jfQuantidadeDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jbLimpar))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jfQuantidadeDeTrocas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel18)
                                .addComponent(jfSuaVendasCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jbSalvar))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jfValorDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel19)
                                .addComponent(jfSeusClientesCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jbAtualizar)))))
                .addGap(12, 12, 12))
        );

        jTableDeDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "PDV", "TURNO", "CAIXA", "DATA", "FATURAMENTO", "CLIENTES", "ITENS", "RECARGA", "CLIENTES", "FATURAS", "CLIENTES", "TROCAS", "QTD", "DEVOL.", "QTD", "V. CUP VIP", "DESC VIP", "M VENDA", "M CLIENT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableDeDados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDeDadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableDeDados);
        if (jTableDeDados.getColumnModel().getColumnCount() > 0) {
            jTableDeDados.getColumnModel().getColumn(3).setMaxWidth(80);
            jTableDeDados.getColumnModel().getColumn(4).setMinWidth(90);
            jTableDeDados.getColumnModel().getColumn(5).setMinWidth(100);
            jTableDeDados.getColumnModel().getColumn(6).setMinWidth(80);
            jTableDeDados.getColumnModel().getColumn(7).setMinWidth(70);
            jTableDeDados.getColumnModel().getColumn(8).setMinWidth(80);
            jTableDeDados.getColumnModel().getColumn(9).setMinWidth(80);
            jTableDeDados.getColumnModel().getColumn(10).setMinWidth(80);
            jTableDeDados.getColumnModel().getColumn(11).setMinWidth(80);
            jTableDeDados.getColumnModel().getColumn(12).setMinWidth(80);
            jTableDeDados.getColumnModel().getColumn(13).setMinWidth(50);
            jTableDeDados.getColumnModel().getColumn(14).setMinWidth(80);
            jTableDeDados.getColumnModel().getColumn(15).setMinWidth(50);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    private void jfValorFaturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfValorFaturasActionPerformed
        if (fun.testaNumerosDecimais(jfValorFaturas.getText())) {
            jfClientesFatura.requestFocus();
        } else {
            jfValorFaturas.setText("");
        }
    }//GEN-LAST:event_jfValorFaturasActionPerformed

    private void jtMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtMatriculaActionPerformed
        if (VerificaMatricula()) {
            if (fun.testaNumerosInteiros(jtMatricula.getText())) {
                jtMatricula.setBackground(new java.awt.Color(51, 51, 255));
                jfPDV.requestFocus();
            } else {
                jtMatricula.setText("");
            }
        }

    }//GEN-LAST:event_jtMatriculaActionPerformed

    private void jtMatriculaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtMatriculaMouseClicked
        if (fun.testaNumerosInteiros(jtMatricula.getText())) {
            jtMatricula.setText("");
        } else {
            jtMatricula.setText("");
        }
    }//GEN-LAST:event_jtMatriculaMouseClicked

    private void jfPDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfPDVActionPerformed
        if (fun.testaNumerosInteiros(jfPDV.getText())) {
            jfDataDaVenda.requestFocus();
        } else {
            jfPDV.setText("");
        }

    }//GEN-LAST:event_jfPDVActionPerformed

    private void jfPDVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jfPDVMouseClicked
        if (fun.testaNumerosInteiros(jfPDV.getText())) {
            jfPDV.setText("");
        } else {
            jfPDV.setText("");
        }

    }//GEN-LAST:event_jfPDVMouseClicked

    private void jfValorTotalFitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfValorTotalFitaActionPerformed
        if (fun.testaNumerosDecimais(jfValorTotalFita.getText())) {
            jfTotalClientes.requestFocus();
        } else {
            jfValorTotalFita.setText("");
        }
    }//GEN-LAST:event_jfValorTotalFitaActionPerformed

    private void jfValorTotalFitaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jfValorTotalFitaMouseClicked
        if (fun.testaNumerosInteiros(jfValorTotalFita.getText())) {
            jfValorTotalFita.setText("");
        } else {
            jfValorTotalFita.setText("");
        }
    }//GEN-LAST:event_jfValorTotalFitaMouseClicked

    private void jfValorCreditoDigitalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfValorCreditoDigitalActionPerformed
        if (fun.testaNumerosDecimais(jfValorCreditoDigital.getText())) {
            jfClientesCreditoDigital.requestFocus();
        } else {
            jfValorCreditoDigital.setText("");
        }
    }//GEN-LAST:event_jfValorCreditoDigitalActionPerformed

    private void jfValorCreditoDigitalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jfValorCreditoDigitalMouseClicked
        if (fun.testaNumerosInteiros(jfValorCreditoDigital.getText())) {
            jfValorCreditoDigital.setText("");
        } else {
            jfValorCreditoDigital.setText("");
        }
    }//GEN-LAST:event_jfValorCreditoDigitalMouseClicked

    private void jfValorFaturasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jfValorFaturasMouseClicked
        if (fun.testaNumerosInteiros(jfValorFaturas.getText())) {
            jfValorFaturas.setText("");
        } else {
            jfValorFaturas.setText("");
        }
    }//GEN-LAST:event_jfValorFaturasMouseClicked

    private void jfTotalClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfTotalClientesActionPerformed
        if (fun.testaNumerosInteiros(jfTotalClientes.getText())) {
            jfValorCreditoDigital.requestFocus();
        } else {
            jfTotalClientes.setText("");
        }
    }//GEN-LAST:event_jfTotalClientesActionPerformed

    private void jfTotalClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jfTotalClientesMouseClicked
        if (fun.testaNumerosInteiros(jfTotalClientes.getText())) {
            jfTotalClientes.setText("");
        } else {
            jfTotalClientes.setText("");
        }
    }//GEN-LAST:event_jfTotalClientesMouseClicked

    private void jfClientesCreditoDigitalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfClientesCreditoDigitalActionPerformed
        if (fun.testaNumerosInteiros(jfClientesCreditoDigital.getText())) {
            jfValorFaturas.requestFocus();
        } else {
            jfClientesCreditoDigital.setText("");
        }
    }//GEN-LAST:event_jfClientesCreditoDigitalActionPerformed

    private void jfClientesCreditoDigitalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jfClientesCreditoDigitalMouseClicked
        if (fun.testaNumerosInteiros(jfValorTotalFita.getText())) {
            jfClientesCreditoDigital.setText("");
        } else {
            jfClientesCreditoDigital.setText("");
        }
    }//GEN-LAST:event_jfClientesCreditoDigitalMouseClicked

    private void jfClientesFaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfClientesFaturaActionPerformed
        if (fun.testaNumerosInteiros(jfClientesFatura.getText())) {
            jfValorCupomDescontoVip.requestFocus();
        } else {
            jfClientesFatura.setText("");
        }
    }//GEN-LAST:event_jfClientesFaturaActionPerformed

    private void jfClientesFaturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jfClientesFaturaMouseClicked
        if (fun.testaNumerosInteiros(jfClientesFatura.getText())) {
            jfClientesFatura.setText("");
        } else {
            jfClientesFatura.setText("");
        }
    }//GEN-LAST:event_jfClientesFaturaMouseClicked

    private void jfValorCupomDescontoVipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfValorCupomDescontoVipActionPerformed
        if (fun.testaNumerosDecimais(jfValorCupomDescontoVip.getText())) {
            jfValorDescontoVip.requestFocus();
        } else {
            jfValorCupomDescontoVip.setText("");
        }
    }//GEN-LAST:event_jfValorCupomDescontoVipActionPerformed

    private void jfValorCupomDescontoVipMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jfValorCupomDescontoVipMouseClicked
        if (fun.testaNumerosInteiros(jfValorCupomDescontoVip.getText())) {
            jfValorCupomDescontoVip.setText("");
        } else {

        }
    }//GEN-LAST:event_jfValorCupomDescontoVipMouseClicked

    private void jfValorDescontoVipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfValorDescontoVipActionPerformed
        if (fun.testaNumerosInteiros(jfValorDescontoVip.getText())) {
            jfItensVendidos.requestFocus();
        } else {
            jfValorDescontoVip.setText("");
        }
    }//GEN-LAST:event_jfValorDescontoVipActionPerformed

    private void jfValorDescontoVipMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jfValorDescontoVipMouseClicked
        if (fun.testaNumerosInteiros(jfValorDescontoVip.getText())) {
            jfValorDescontoVip.setText("");
        } else {

        }
    }//GEN-LAST:event_jfValorDescontoVipMouseClicked

    private void jfItensVendidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfItensVendidosActionPerformed
        if (fun.testaNumerosInteiros(jfItensVendidos.getText())) {
            jfValorTrocas.requestFocus();
        } else {
        }
    }//GEN-LAST:event_jfItensVendidosActionPerformed

    private void jfItensVendidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jfItensVendidosMouseClicked
        if (fun.testaNumerosInteiros(jfItensVendidos.getText())) {
            jfItensVendidos.setText("");
        } else {

        }
    }//GEN-LAST:event_jfItensVendidosMouseClicked

    private void jfValorTrocasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfValorTrocasActionPerformed
        if (fun.testaNumerosDecimais(jfValorTrocas.getText())) {
            jfQuantidadeDeTrocas.requestFocus();
        } else {
            jfValorTrocas.setText("");
        }
    }//GEN-LAST:event_jfValorTrocasActionPerformed

    private void jfValorTrocasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jfValorTrocasMouseClicked
        if (fun.testaNumerosInteiros(jfValorTrocas.getText())) {
            jfValorTrocas.setText("");
        } else {
            jfValorTrocas.setText("");
        }
    }//GEN-LAST:event_jfValorTrocasMouseClicked

    private void jfQuantidadeDeTrocasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfQuantidadeDeTrocasActionPerformed
        if (fun.testaNumerosInteiros(jfQuantidadeDeTrocas.getText())) {
            jfValorDevolucao.requestFocus();
        } else {
            jfQuantidadeDeTrocas.setText("");
        }
    }//GEN-LAST:event_jfQuantidadeDeTrocasActionPerformed

    private void jfQuantidadeDeTrocasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jfQuantidadeDeTrocasMouseClicked
        if (fun.testaNumerosInteiros(jfQuantidadeDeTrocas.getText())) {
            jfQuantidadeDeTrocas.setText("");
        } else {
            jfQuantidadeDeTrocas.setText("");
        }
    }//GEN-LAST:event_jfQuantidadeDeTrocasMouseClicked

    private void jfValorDevolucaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfValorDevolucaoActionPerformed
        if (fun.testaNumerosDecimais(jfValorDevolucao.getText())) {
            jfQuantidadeDevolucao.requestFocus();
        } else {
            jfValorDevolucao.setText("");
        }
    }//GEN-LAST:event_jfValorDevolucaoActionPerformed

    private void jfValorDevolucaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jfValorDevolucaoMouseClicked
        if (fun.testaNumerosInteiros(jfValorDevolucao.getText())) {
            jfValorDevolucao.setText("");
        } else {
            jfValorDevolucao.setText("");
        }
    }//GEN-LAST:event_jfValorDevolucaoMouseClicked

    private void jfQuantidadeDevolucaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfQuantidadeDevolucaoActionPerformed
        if (fun.testaNumerosInteiros(jfQuantidadeDevolucao.getText())) {
            jfSuaVendasCaixa.requestFocus();
        } else {
            jfQuantidadeDevolucao.setText("");
        }
    }//GEN-LAST:event_jfQuantidadeDevolucaoActionPerformed

    private void jfQuantidadeDevolucaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jfQuantidadeDevolucaoMouseClicked
        if (fun.testaNumerosInteiros(jfQuantidadeDevolucao.getText())) {
            jfQuantidadeDevolucao.setText("");
        } else {
            jfQuantidadeDevolucao.setText("");
        }
    }//GEN-LAST:event_jfQuantidadeDevolucaoMouseClicked

    private void jfSuaVendasCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfSuaVendasCaixaActionPerformed
        if (fun.testaNumerosDecimais(jfSuaVendasCaixa.getText())) {
            jfSeusClientesCaixa.requestFocus();
        } else {
            jfSuaVendasCaixa.setText("");
        }
    }//GEN-LAST:event_jfSuaVendasCaixaActionPerformed

    private void jfSuaVendasCaixaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jfSuaVendasCaixaMouseClicked
        if (fun.testaNumerosInteiros(jfSuaVendasCaixa.getText())) {
            jfSuaVendasCaixa.setText("");
        } else {
            jfSuaVendasCaixa.setText("");
        }
    }//GEN-LAST:event_jfSuaVendasCaixaMouseClicked

    private void jfSeusClientesCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfSeusClientesCaixaActionPerformed
        if (fun.testaNumerosInteiros(jfSeusClientesCaixa.getText())) {
            jbCTF.requestFocus();
        } else {
            jfSeusClientesCaixa.setText("");
        }
    }//GEN-LAST:event_jfSeusClientesCaixaActionPerformed

    private void jfSeusClientesCaixaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jfSeusClientesCaixaMouseClicked
        if (fun.testaNumerosInteiros(jfSeusClientesCaixa.getText())) {
            jfSeusClientesCaixa.setText("");
        } else {
            jfSeusClientesCaixa.setText("");
        }
    }//GEN-LAST:event_jfSeusClientesCaixaMouseClicked

    private void jbSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarActionPerformed
        try {
            if (validaCampos()) {
                if (validaCamposBreak()) {
                    switch (jbSalvar.getText()) {
                        case "SALVAR":
                            if (VerificaValores()) {
                                if (InserirDados() != 0) {
                                    JOptionPane.showMessageDialog(this, "Salvo Com Sucesso!!");
                                    PreencheTabelaDaView();
                                    limpaCampos();
                                } else {
                                    JOptionPane.showMessageDialog(this, "Erro ao Inserir os Dados:");
                                }
                            }
                            break;
                        case "EDITAR":
                            if (VerificaValores()) {
                                if (EditarDados()) {
                                    JOptionPane.showMessageDialog(this, "Editado Com Sucesso!!");
                                    PreencheTabelaDaView();
                                    limpaCampos();
                                } else {
                                    JOptionPane.showMessageDialog(this, "Erro ao Editar Dados:");
                                }
                            }
                            break;
                        default:
                            JOptionPane.showMessageDialog(this, "Não Corresponde");
                            break;
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Verifique os Campos!!");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao Inserir dados: " + ex.getMessage());
        }
    }//GEN-LAST:event_jbSalvarActionPerformed

    private void jbLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLimparActionPerformed
        limpaCampos();
        SetValores();
    }//GEN-LAST:event_jbLimparActionPerformed

    private void jbPopularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPopularActionPerformed
        AbrePopular();
    }//GEN-LAST:event_jbPopularActionPerformed

    private void jfDataDaVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfDataDaVendaActionPerformed
        jfTurno.requestFocus();
    }//GEN-LAST:event_jfDataDaVendaActionPerformed

    private void jbAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAtualizarActionPerformed
        Atualizar();
    }//GEN-LAST:event_jbAtualizarActionPerformed

    private void jbCTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCTFActionPerformed
//        AbreCTF();
    }//GEN-LAST:event_jbCTFActionPerformed

    private void btUltimoCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUltimoCaixaActionPerformed
        SelecionaCaixa();
    }//GEN-LAST:event_btUltimoCaixaActionPerformed

    private void jfTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfTurnoActionPerformed
        jfValorTotalFita.requestFocus();
    }//GEN-LAST:event_jfTurnoActionPerformed

    private void jbCanceladosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCanceladosActionPerformed
        AbreCancelamentos();
    }//GEN-LAST:event_jbCanceladosActionPerformed

    private void jTableDeDadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDeDadosMouseClicked
        EditaValores();
    }//GEN-LAST:event_jTableDeDadosMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox btUltimoCaixa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDeDados;
    private javax.swing.JFormattedTextField jTotalClientesFatura;
    private javax.swing.JFormattedTextField jTotalClientesGeral;
    private javax.swing.JFormattedTextField jTotalCreditoClientes;
    private javax.swing.JFormattedTextField jTotalCreditoDigital;
    private javax.swing.JFormattedTextField jTotalCupomVip;
    private javax.swing.JFormattedTextField jTotalDePopular;
    private javax.swing.JFormattedTextField jTotalDescontoVip;
    private javax.swing.JFormattedTextField jTotalDevolucao;
    private javax.swing.JFormattedTextField jTotalFatura;
    private javax.swing.JFormattedTextField jTotalFaturamento;
    private javax.swing.JFormattedTextField jTotalItensVendidos;
    private javax.swing.JFormattedTextField jTotalPendenciasCTF;
    private javax.swing.JFormattedTextField jTotalQuantidadeDePendencias;
    private javax.swing.JFormattedTextField jTotalQuantidadeDeTrocas;
    private javax.swing.JFormattedTextField jTotalQuantidadeDevolucoes;
    private javax.swing.JFormattedTextField jTotaldeTrocasValor;
    private javax.swing.JButton jbAtualizar;
    private javax.swing.JButton jbCTF;
    private javax.swing.JButton jbCancelados;
    private javax.swing.JButton jbLimpar;
    private javax.swing.JButton jbPopular;
    private javax.swing.JButton jbSalvar;
    private javax.swing.JFormattedTextField jfClientesCreditoDigital;
    private javax.swing.JFormattedTextField jfClientesFatura;
    private javax.swing.JFormattedTextField jfDataDaVenda;
    private javax.swing.JFormattedTextField jfItensVendidos;
    private javax.swing.JFormattedTextField jfPDV;
    private javax.swing.JFormattedTextField jfQuantidadeDeTrocas;
    private javax.swing.JFormattedTextField jfQuantidadeDevolucao;
    private javax.swing.JFormattedTextField jfSeusClientesCaixa;
    private javax.swing.JFormattedTextField jfSuaVendasCaixa;
    private javax.swing.JFormattedTextField jfTotalClientes;
    private javax.swing.JComboBox<String> jfTurno;
    private javax.swing.JFormattedTextField jfValorCreditoDigital;
    private javax.swing.JFormattedTextField jfValorCupomDescontoVip;
    private javax.swing.JFormattedTextField jfValorDescontoVip;
    private javax.swing.JFormattedTextField jfValorDevolucao;
    private javax.swing.JFormattedTextField jfValorFaturas;
    private javax.swing.JFormattedTextField jfValorTotalFita;
    private javax.swing.JFormattedTextField jfValorTrocas;
    private javax.swing.JTextField jtId;
    private javax.swing.JFormattedTextField jtMatricula;
    // End of variables declaration//GEN-END:variables

//    public void AbreCTF() {
//        if (jfCtf == null) {
//            jfCtf = new JifCTF();
//            JfPrincipal.jDesktopPrincipal.add(jfCtf);
//            jfCtf.setVisible(true);
//            jfCtf.setPosicao();
//        } else if (!jfCtf.isVisible()) {
//            jfCtf = new JifCTF();
//            JfPrincipal.jDesktopPrincipal.add(jfCtf);
//            jfCtf.setVisible(true);
//            jfCtf.setPosicao();
//        } else {
//            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
//        }
//    }
    public void AbrePopular() {
//        if (jfPop == null) {
//            jfPop = new JifPopular();
//            JfPrincipal.jDesktopPrincipal.add(jfPop);
//            jfPop.setVisible(true);
//            jfPop.setPosicao();
//        } else if (!jfPop.isVisible()) {
//            jfPop = new JifPopular();
//            JfPrincipal.jDesktopPrincipal.add(jfPop);
//            jfPop.setVisible(true);
//            jfPop.setPosicao();
//        } else {
//            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
//        }
    }

    public void AbreCancelamentos() {
        if (jfCancela == null) {
            jfCancela = new JifCancelamentos();
            JfPrincipal.jDesktopPrincipal.add(jfCancela);
            jfCancela.setVisible(true);
            jfCancela.setPosicao();
        } else if (!jfCancela.isVisible()) {
            jfCancela = new JifCancelamentos();
            JfPrincipal.jDesktopPrincipal.add(jfCancela);
            jfCancela.setVisible(true);
            jfCancela.setPosicao();
        } else {
            JOptionPane.showMessageDialog(this, "Janela ja esta aberta!");
        }
    }
}
