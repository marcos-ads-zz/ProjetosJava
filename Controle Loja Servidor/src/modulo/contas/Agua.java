/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.contas;

import java.sql.Date;

/**
 *
 * @author Marcos Junior
 */
public class Agua {

    private int id_agua;
    private int matricula;
    private int notafiscal;
    private Date date_emissao;
    private Date date_vencimento;
    private Double valor_conta;
    private int consumo;
    private String ano;

    public int getId_agua() {
        return id_agua;
    }

    public void setId_agua(int id_agua) {
        this.id_agua = id_agua;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public int getNotafiscal() {
        return notafiscal;
    }

    public void setNotafiscal(int notafiscal) {
        this.notafiscal = notafiscal;
    }

    public Date getDate_emissao() {
        return date_emissao;
    }

    public void setDate_emissao(Date date_emissao) {
        this.date_emissao = date_emissao;
    }

    public Date getDate_vencimento() {
        return date_vencimento;
    }

    public void setDate_vencimento(Date date_vencimento) {
        this.date_vencimento = date_vencimento;
    }

    public Double getValor_conta() {
        return valor_conta;
    }

    public void setValor_conta(Double valor_conta) {
        this.valor_conta = valor_conta;
    }

    public int getConsumo() {
        return consumo;
    }

    public void setConsumo(int consumo) {
        this.consumo = consumo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

}
