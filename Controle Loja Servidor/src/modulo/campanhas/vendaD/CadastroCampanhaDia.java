package modulo.campanhas.vendaD;

import java.sql.Date;

/**
 *
 * @author Marcos Junior
 */
public class CadastroCampanhaDia {

    private int id;
    private int matricula;
    private String desc_campanha;
    private int quantidade;
    private Date data_registro;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getDesc_campanha() {
        return desc_campanha;
    }

    public void setDesc_campanha(String desc_campanha) {
        this.desc_campanha = desc_campanha;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Date getData_registro() {
        return data_registro;
    }

    public void setData_registro(Date data_registro) {
        this.data_registro = data_registro;
    }

}
