package modulo.metas;

import java.sql.Date;

/**
 *
 * @author Marcos Junior
 */
public class Metas {

    private int id_metas;
    private double valor_meta;
    private double valor_recarga;
    private double valor_renta;
    private double valor_vip;
    private int meta_power;
    private int meta_gnano;
    private int meta_gero;
    private int meta_clinic;
    private int meta_anotacao;
    private String mes;
    private int ano;
    private int n_loja;
    private Date date_registro;

    public Metas() {
    }

    public Metas(int id_metas, double valor_meta, double valor_recarga, double valor_renta, double valor_vip, int meta_power, int meta_gnano, int meta_gero, int meta_clinic, int meta_anotacao, String mes, int ano, int n_loja, Date date_registro) {
        this.id_metas = id_metas;
        this.valor_meta = valor_meta;
        this.valor_recarga = valor_recarga;
        this.valor_renta = valor_renta;
        this.valor_vip = valor_vip;
        this.meta_power = meta_power;
        this.meta_gnano = meta_gnano;
        this.meta_gero = meta_gero;
        this.meta_clinic = meta_clinic;
        this.meta_anotacao = meta_anotacao;
        this.mes = mes;
        this.ano = ano;
        this.n_loja = n_loja;
        this.date_registro = date_registro;
    }

    public int getId_metas() {
        return id_metas;
    }

    public void setId_metas(int id_metas) {
        this.id_metas = id_metas;
    }

    public double getValor_meta() {
        return valor_meta;
    }

    public void setValor_meta(double valor_meta) {
        this.valor_meta = valor_meta;
    }

    public double getValor_recarga() {
        return valor_recarga;
    }

    public void setValor_recarga(double valor_recarga) {
        this.valor_recarga = valor_recarga;
    }

    public double getValor_renta() {
        return valor_renta;
    }

    public void setValor_renta(double valor_renta) {
        this.valor_renta = valor_renta;
    }

    public double getValor_vip() {
        return valor_vip;
    }

    public void setValor_vip(double valor_vip) {
        this.valor_vip = valor_vip;
    }

    public int getMeta_power() {
        return meta_power;
    }

    public void setMeta_power(int meta_power) {
        this.meta_power = meta_power;
    }

    public int getMeta_gnano() {
        return meta_gnano;
    }

    public void setMeta_gnano(int meta_gnano) {
        this.meta_gnano = meta_gnano;
    }

    public int getMeta_gero() {
        return meta_gero;
    }

    public void setMeta_gero(int meta_gero) {
        this.meta_gero = meta_gero;
    }

    public int getMeta_clinic() {
        return meta_clinic;
    }

    public void setMeta_clinic(int meta_clinic) {
        this.meta_clinic = meta_clinic;
    }

    public int getMeta_anotacao() {
        return meta_anotacao;
    }

    public void setMeta_anotacao(int meta_anotacao) {
        this.meta_anotacao = meta_anotacao;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getN_loja() {
        return n_loja;
    }

    public void setN_loja(int n_loja) {
        this.n_loja = n_loja;
    }

    public Date getDate_registro() {
        return date_registro;
    }

    public void setDate_registro(Date date_registro) {
        this.date_registro = date_registro;
    }

}
