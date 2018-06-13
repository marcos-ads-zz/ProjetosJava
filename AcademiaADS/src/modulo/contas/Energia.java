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
    private Date date_vendimento;
    private Double valor_conta;
    private int consumno;
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

    public Date getDate_vendimento() {
        return date_vendimento;
    }

    public void setDate_vendimento(Date date_vendimento) {
        this.date_vendimento = date_vendimento;
    }

    public Double getValor_conta() {
        return valor_conta;
    }

    public void setValor_conta(Double valor_conta) {
        this.valor_conta = valor_conta;
    }

    public int getConsumno() {
        return consumno;
    }

    public void setConsumno(int consumno) {
        this.consumno = consumno;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

}
