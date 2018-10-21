package modulo.troca;

import java.sql.Date;

/**
 *
 * @author Marcos Junior
 */
public class ItensDeixados {

    private int id;
    private int id_troca;
    private int id_produto;
    private int quatidade;
    private Date date_cupom;
    private Date date_registro;
    private Double valorUnitario;
    private Double valorTotalDeixado;

    public ItensDeixados() {
    }

    public ItensDeixados(int id, int id_troca, int id_produto, int quatidade, Date date_cupom, Date date_registro, Double valorUnitario, Double valorTotalDeixado) {
        this.id = id;
        this.id_troca = id_troca;
        this.id_produto = id_produto;
        this.quatidade = quatidade;
        this.date_cupom = date_cupom;
        this.date_registro = date_registro;
        this.valorUnitario = valorUnitario;
        this.valorTotalDeixado = valorTotalDeixado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_troca() {
        return id_troca;
    }

    public void setId_troca(int id_troca) {
        this.id_troca = id_troca;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public int getQuatidade() {
        return quatidade;
    }

    public void setQuatidade(int quatidade) {
        this.quatidade = quatidade;
    }

    public Date getDate_cupom() {
        return date_cupom;
    }

    public void setDate_cupom(Date date_cupom) {
        this.date_cupom = date_cupom;
    }

    public Date getDate_registro() {
        return date_registro;
    }

    public void setDate_registro(Date date_registro) {
        this.date_registro = date_registro;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Double getValorTotalDeixado() {
        return valorTotalDeixado;
    }

    public void setValorTotalDeixado(Double valorTotalDeixado) {
        this.valorTotalDeixado = valorTotalDeixado;
    }

   
}
