package modulo.campanhas.meta;

import java.sql.Date;

/**
 *
 * @author Marcos Junior
 */
public class CadastroMetasCampanhas {

    private int id;
    private String descricao_Campanha;
    private Date data_inicio;
    private Date data_fim;
    private int meta_mes;
    private int meta_trimestre;
    private int meta_semestre;
    private int meta_anual;
    private String obs;
    private Date data_registro;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao_Campanha() {
        return descricao_Campanha;
    }

    public void setDescricao_Campanha(String descricao_Campanha) {
        this.descricao_Campanha = descricao_Campanha;
    }

    public Date getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Date getData_fim() {
        return data_fim;
    }

    public void setData_fim(Date data_fim) {
        this.data_fim = data_fim;
    }

    public int getMeta_mes() {
        return meta_mes;
    }

    public void setMeta_mes(int meta_mes) {
        this.meta_mes = meta_mes;
    }

    public int getMeta_trimestre() {
        return meta_trimestre;
    }

    public void setMeta_trimestre(int meta_trimestre) {
        this.meta_trimestre = meta_trimestre;
    }

    public int getMeta_semestre() {
        return meta_semestre;
    }

    public void setMeta_semestre(int meta_semestre) {
        this.meta_semestre = meta_semestre;
    }

    public int getMeta_anual() {
        return meta_anual;
    }

    public void setMeta_anual(int meta_anual) {
        this.meta_anual = meta_anual;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Date getData_registro() {
        return data_registro;
    }

    public void setData_registro(Date data_registro) {
        this.data_registro = data_registro;
    }

}
