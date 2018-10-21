/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.entidades;

/**
 *
 * @author Marcos Junior
 */
public class Loja {

    private int id;
    private String nome_loja;
    private int numero_loja;
    private String gerente_loja;

    public Loja() {
    }

    public Loja(int id, String nome_loja, int numero_loja, String gerente_loja) {
        this.id = id;
        this.nome_loja = nome_loja;
        this.numero_loja = numero_loja;
        this.gerente_loja = gerente_loja;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_loja() {
        return nome_loja;
    }

    public void setNome_loja(String nome_loja) {
        this.nome_loja = nome_loja;
    }

    public int getNumero_loja() {
        return numero_loja;
    }

    public void setNumero_loja(int numero_loja) {
        this.numero_loja = numero_loja;
    }

    public String getGerente_loja() {
        return gerente_loja;
    }

    public void setGerente_loja(String gerente_loja) {
        this.gerente_loja = gerente_loja;
    }

}
