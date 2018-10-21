package modulo.troca;

import modulo.troca.ItensLevados;
import modulo.troca.ItensDeixados;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Marcos Junior
 */
public class Trocas {

    private int id_troca;
    private int id_autorizador;
    private int id_caixa;
    private int id_lojaRegistro;
    private int id_lojaCupom;
    private int id_cli;
    private Date date_registro;
    private String hora_registro;
    private int n_cupom;
    private int n_pdv;
    private Date date_cupom;
    private String tipo;
    private String motivo_registro;
    private List<ItensLevados> listaLevados;
    private List<ItensDeixados> listaDeixados;

    public Trocas() {
    }

    public Trocas(int id_troca, int id_autorizador, int id_caixa, int id_lojaRegistro, int id_lojaCupom, int id_cli, Date date_registro, String hora_registro, int n_cupom, int n_pdv, Date date_cupom, String tipo, String motivo_registro, List<ItensLevados> listaLevados, List<ItensDeixados> listaDeixados) {
        this.id_troca = id_troca;
        this.id_autorizador = id_autorizador;
        this.id_caixa = id_caixa;
        this.id_lojaRegistro = id_lojaRegistro;
        this.id_lojaCupom = id_lojaCupom;
        this.id_cli = id_cli;
        this.date_registro = date_registro;
        this.hora_registro = hora_registro;
        this.n_cupom = n_cupom;
        this.n_pdv = n_pdv;
        this.date_cupom = date_cupom;
        this.tipo = tipo;
        this.motivo_registro = motivo_registro;
        this.listaLevados = listaLevados;
        this.listaDeixados = listaDeixados;
    }

    public int getId_troca() {
        return id_troca;
    }

    public void setId_troca(int id_troca) {
        this.id_troca = id_troca;
    }

    public int getId_autorizador() {
        return id_autorizador;
    }

    public void setId_autorizador(int id_autorizador) {
        this.id_autorizador = id_autorizador;
    }

    public int getId_caixa() {
        return id_caixa;
    }

    public void setId_caixa(int id_caixa) {
        this.id_caixa = id_caixa;
    }

    public int getId_lojaRegistro() {
        return id_lojaRegistro;
    }

    public void setId_lojaRegistro(int id_lojaRegistro) {
        this.id_lojaRegistro = id_lojaRegistro;
    }

    public int getId_lojaCupom() {
        return id_lojaCupom;
    }

    public void setId_lojaCupom(int id_lojaCupom) {
        this.id_lojaCupom = id_lojaCupom;
    }

    public int getId_cli() {
        return id_cli;
    }

    public void setId_cli(int id_cli) {
        this.id_cli = id_cli;
    }

    public Date getDate_registro() {
        return date_registro;
    }

    public void setDate_registro(Date date_registro) {
        this.date_registro = date_registro;
    }

    public String getHora_registro() {
        return hora_registro;
    }

    public void setHora_registro(String hora_registro) {
        this.hora_registro = hora_registro;
    }

    public int getN_cupom() {
        return n_cupom;
    }

    public void setN_cupom(int n_cupom) {
        this.n_cupom = n_cupom;
    }

    public int getN_pdv() {
        return n_pdv;
    }

    public void setN_pdv(int n_pdv) {
        this.n_pdv = n_pdv;
    }

    public Date getDate_cupom() {
        return date_cupom;
    }

    public void setDate_cupom(Date date_cupom) {
        this.date_cupom = date_cupom;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMotivo_registro() {
        return motivo_registro;
    }

    public void setMotivo_registro(String motivo_registro) {
        this.motivo_registro = motivo_registro;
    }

    public List<ItensLevados> getListaLevados() {
        return listaLevados;
    }

    public void setListaLevados(List<ItensLevados> listaLevados) {
        this.listaLevados = listaLevados;
    }

    public List<ItensDeixados> getListaDeixados() {
        return listaDeixados;
    }

    public void setListaDeixados(List<ItensDeixados> listaDeixados) {
        this.listaDeixados = listaDeixados;
    }

}
