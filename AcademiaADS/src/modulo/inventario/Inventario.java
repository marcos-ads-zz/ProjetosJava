package modulo.inventario;

import java.sql.Date;

/**
 *
 * @author Marcos Junior
 */
public class Inventario {

    private int id_inv;
    private int loja;
    private Date data_inventario;
    private String ano;
    private int prazo;
    private int total_produtos;
    private int total_unidades;
    private double valor_ima;
    private double valor_venda_estoque;
    private double valor_venda_falta;
    private double valor_venda_sobra;
    private double valor_custo_estoque;
    private double valor_custo_falta;
    private double valor_custo_sobra;

    public int getId_inv() {
        return id_inv;
    }

    public void setId_inv(int id_inv) {
        this.id_inv = id_inv;
    }

    public int getLoja() {
        return loja;
    }

    public void setLoja(int loja) {
        this.loja = loja;
    }

    public Date getData_inventario() {
        return data_inventario;
    }

    public void setData_inventario(Date data_inventario) {
        this.data_inventario = data_inventario;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public int getPrazo() {
        return prazo;
    }

    public void setPrazo(int prazo) {
        this.prazo = prazo;
    }

    public int getTotal_produtos() {
        return total_produtos;
    }

    public void setTotal_produtos(int total_produtos) {
        this.total_produtos = total_produtos;
    }

    public int getTotal_unidades() {
        return total_unidades;
    }

    public void setTotal_unidades(int total_unidades) {
        this.total_unidades = total_unidades;
    }

    public double getValor_ima() {
        return valor_ima;
    }

    public void setValor_ima(double valor_ima) {
        this.valor_ima = valor_ima;
    }

    public double getValor_venda_estoque() {
        return valor_venda_estoque;
    }

    public void setValor_venda_estoque(double valor_venda_estoque) {
        this.valor_venda_estoque = valor_venda_estoque;
    }

    public double getValor_venda_falta() {
        return valor_venda_falta;
    }

    public void setValor_venda_falta(double valor_venda_falta) {
        this.valor_venda_falta = valor_venda_falta;
    }

    public double getValor_venda_sobra() {
        return valor_venda_sobra;
    }

    public void setValor_venda_sobra(double valor_venda_sobra) {
        this.valor_venda_sobra = valor_venda_sobra;
    }

    public double getValor_custo_estoque() {
        return valor_custo_estoque;
    }

    public void setValor_custo_estoque(double valor_custo_estoque) {
        this.valor_custo_estoque = valor_custo_estoque;
    }

    public double getValor_custo_falta() {
        return valor_custo_falta;
    }

    public void setValor_custo_falta(double valor_custo_falta) {
        this.valor_custo_falta = valor_custo_falta;
    }

    public double getValor_custo_sobra() {
        return valor_custo_sobra;
    }

    public void setValor_custo_sobra(double valor_custo_sobra) {
        this.valor_custo_sobra = valor_custo_sobra;
    }

    
}
