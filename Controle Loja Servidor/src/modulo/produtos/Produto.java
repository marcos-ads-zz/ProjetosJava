/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.produtos;


/**
 *
 * @author Marcos Junior
 */
public class Produto {

    private int id;
    private int cod_interno;
    private String cod_produto;
    private String descricao;
    private String observ;

    public Produto() {

    }

    public Produto(int id, int cod_interno, String cod_produto, String descricao, String observ) {
        this.id = id;
        this.cod_interno = cod_interno;
        this.cod_produto = cod_produto;
        this.descricao = descricao;
        this.observ = observ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getObserv() {
        return observ;
    }

    public void setObserv(String observ) {
        this.observ = observ;
    }

}
