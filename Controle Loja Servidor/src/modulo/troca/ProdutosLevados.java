package modulo.troca;

import modulo.troca.Trocas;
import java.math.BigDecimal;

/**
 *
 * @author Marcos Junior
 */
public class ProdutosLevados {

    private int id;
    private int cod_prod;
    private String descricao;
    private int qtd;
    private Double valor;
    private Double valor_total;
    private String tipo;
    private Trocas id_troca;

    public ProdutosLevados() {
    }

    public ProdutosLevados(int id, int cod_prod, String descricao, int qtd, Double valor, Double valor_total, String tipo, Trocas id_trocas) {
        this.id = id;
        this.cod_prod = cod_prod;
        this.descricao = descricao;
        this.qtd = qtd;
        this.valor = valor;
        this.valor_total = valor_total;
        this.tipo = tipo;
        this.id_troca = id_trocas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCod_prod() {
        return cod_prod;
    }

    public void setCod_prod(int cod_prod) {
        this.cod_prod = cod_prod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getValor_total() {
        return valor_total;
    }

    public void setValor_total(Double valor_total) {
        this.valor_total = valor_total;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Trocas getId_troca() {
        return id_troca;
    }

    public void setId_troca(Trocas id_troca) {
        this.id_troca = id_troca;
    }
    
}
