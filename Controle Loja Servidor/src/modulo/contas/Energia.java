package modulo.contas;

import java.sql.Date;

/**
 *
 * @author Marcos Junior
 */
public class Energia {

    private int id_energ;
    private int codigo;
    private int notafiscal;
    private Date date_emissao;
    private Date date_vencimento;
    private Double valor_conta;
    private int consumo;
    private String ano;

    public int getId_energ() {
        return id_energ;
    }

    public void setId_energ(int id_energ) {
        this.id_energ = id_energ;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
