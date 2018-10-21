package modulo.faceamento;

import java.sql.Date;


/**
 *
 * @author Marcos Junior
 */
public class ListaDeRuptura {

    private int id;
    private Date data;
    private int cod_interno;
    private String cod_produto;
    private String descricao;
    private int qtd_perdida;
    private int lancado;
    private int matricula;
    private String observacao;

    public ListaDeRuptura() {
    }

    public ListaDeRuptura(int id, Date data, int cod_interno, String cod_produto, String descricao, int qtd_perdida, int lancado, int matricula, String observacao) {
        this.id = id;
        this.data = data;
        this.cod_interno = cod_interno;
        this.cod_produto = cod_produto;
        this.descricao = descricao;
        this.qtd_perdida = qtd_perdida;
        this.lancado = lancado;
        this.matricula = matricula;
        this.observacao = observacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getCod_interno() {
        return cod_interno;
    }

    public void setCod_interno(int cod_interno) {
        this.cod_interno = cod_interno;
    }

    public String getCod_produto() {
        return cod_produto;
    }

    public void setCod_produto(String cod_produto) {
        this.cod_produto = cod_produto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQtd_perdida() {
        return qtd_perdida;
    }

    public void setQtd_perdida(int qtd_perdida) {
        this.qtd_perdida = qtd_perdida;
    }

    public int getLancado() {
        return lancado;
    }

    public void setLancado(int lancado) {
        this.lancado = lancado;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

}
