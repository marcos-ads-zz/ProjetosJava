package modulo.faturamento;

import java.sql.Date;

/**
 *
 * @author Marcos Junior
 */
public class Faturamento {

    private int id_fat;
    private int id_caixa;
    private int matricula;
    private int pdv;
    private String turno;
    private Date date_registro;
    private Date date_venda;
    private double valor_fita;
    private double valor_credito_digital;
    private double valor_faturas;
    private int qtd_cli_geral;
    private int qtd_cli_credito;
    private int qtd_cli_fatura;
    private double valor_cupom_vip;
    private double valor_desc_vip;
    private int qtd_itens_vendidos;
    private double valor_trocas;
    private int qtd_trocas;
    private double valor_devolucoes;
    private int qtd_devolucao;
    private double valor_venda_caixa;
    private int qtd_cli_caixa;

    public Faturamento() {
    }

    public Faturamento(int id_cli, int id_caixa, int matricula, int pdv, String turno, Date date_registro, Date date_venda, double valor_fita, double valor_credito, double valor_faturas, int qtd_cli_geral, int qtd_cli_credito, int qtd_cli_fatura, double valor_cupom_vip, double valor_desc_vip, int qtd_itens_vendidos, double valor_trocas, int qtd_trocas, double valor_devolucoes, int qtd_devolucao, double valor_venda_caixa, int qtd_cli_caixa) {
        this.id_fat = id_cli;
        this.id_caixa = id_caixa;
        this.matricula = matricula;
        this.pdv = pdv;
        this.turno = turno;
        this.date_registro = date_registro;
        this.date_venda = date_venda;
        this.valor_fita = valor_fita;
        this.valor_credito_digital = valor_credito;
        this.valor_faturas = valor_faturas;
        this.qtd_cli_geral = qtd_cli_geral;
        this.qtd_cli_credito = qtd_cli_credito;
        this.qtd_cli_fatura = qtd_cli_fatura;
        this.valor_cupom_vip = valor_cupom_vip;
        this.valor_desc_vip = valor_desc_vip;
        this.qtd_itens_vendidos = qtd_itens_vendidos;
        this.valor_trocas = valor_trocas;
        this.qtd_trocas = qtd_trocas;
        this.valor_devolucoes = valor_devolucoes;
        this.qtd_devolucao = qtd_devolucao;
        this.valor_venda_caixa = valor_venda_caixa;
        this.qtd_cli_caixa = qtd_cli_caixa;
    }

    public int getId_fat() {
        return id_fat;
    }

    public void setId_fat(int id_fat) {
        this.id_fat = id_fat;
    }

    public int getId_caixa() {
        return id_caixa;
    }

    public void setId_caixa(int id_caixa) {
        this.id_caixa = id_caixa;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public int getPdv() {
        return pdv;
    }

    public void setPdv(int pdv) {
        this.pdv = pdv;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Date getDate_registro() {
        return date_registro;
    }

    public void setDate_registro(Date date_registro) {
        this.date_registro = date_registro;
    }

    public Date getDate_venda() {
        return date_venda;
    }

    public void setDate_venda(Date date_venda) {
        this.date_venda = date_venda;
    }

    public double getValor_fita() {
        return valor_fita;
    }

    public void setValor_fita(double valor_fita) {
        this.valor_fita = valor_fita;
    }

    public double getValor_credito_digital() {
        return valor_credito_digital;
    }

    public void setValor_credito_digital(double valor_credito_digital) {
        this.valor_credito_digital = valor_credito_digital;
    }

    public double getValor_faturas() {
        return valor_faturas;
    }

    public void setValor_faturas(double valor_faturas) {
        this.valor_faturas = valor_faturas;
    }

    public int getQtd_cli_geral() {
        return qtd_cli_geral;
    }

    public void setQtd_cli_geral(int qtd_cli_geral) {
        this.qtd_cli_geral = qtd_cli_geral;
    }

    public int getQtd_cli_credito() {
        return qtd_cli_credito;
    }

    public void setQtd_cli_credito(int qtd_cli_credito) {
        this.qtd_cli_credito = qtd_cli_credito;
    }

    public int getQtd_cli_fatura() {
        return qtd_cli_fatura;
    }

    public void setQtd_cli_fatura(int qtd_cli_fatura) {
        this.qtd_cli_fatura = qtd_cli_fatura;
    }

    public double getValor_cupom_vip() {
        return valor_cupom_vip;
    }

    public void setValor_cupom_vip(double valor_cupom_vip) {
        this.valor_cupom_vip = valor_cupom_vip;
    }

    public double getValor_desc_vip() {
        return valor_desc_vip;
    }

    public void setValor_desc_vip(double valor_desc_vip) {
        this.valor_desc_vip = valor_desc_vip;
    }

    public int getQtd_itens_vendidos() {
        return qtd_itens_vendidos;
    }

    public void setQtd_itens_vendidos(int qtd_itens_vendidos) {
        this.qtd_itens_vendidos = qtd_itens_vendidos;
    }

    public double getValor_trocas() {
        return valor_trocas;
    }

    public void setValor_trocas(double valor_trocas) {
        this.valor_trocas = valor_trocas;
    }

    public int getQtd_trocas() {
        return qtd_trocas;
    }

    public void setQtd_trocas(int qtd_trocas) {
        this.qtd_trocas = qtd_trocas;
    }

    public double getValor_devolucoes() {
        return valor_devolucoes;
    }

    public void setValor_devolucoes(double valor_devolucoes) {
        this.valor_devolucoes = valor_devolucoes;
    }

    public int getQtd_devolucao() {
        return qtd_devolucao;
    }

    public void setQtd_devolucao(int qtd_devolucao) {
        this.qtd_devolucao = qtd_devolucao;
    }

    public double getValor_venda_caixa() {
        return valor_venda_caixa;
    }

    public void setValor_venda_caixa(double valor_venda_caixa) {
        this.valor_venda_caixa = valor_venda_caixa;
    }

    public int getQtd_cli_caixa() {
        return qtd_cli_caixa;
    }

    public void setQtd_cli_caixa(int qtd_cli_caixa) {
        this.qtd_cli_caixa = qtd_cli_caixa;
    }

}
