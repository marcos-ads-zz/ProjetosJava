package modulo.dashboardD;

/**
 *
 * @author Marcos Junior
 */
public class ListaDeRupturaP {

    private int id;
    private int cod_interno;
    private String descricao;
    private Object qtd_perdida;

    public ListaDeRupturaP() {
    }

    public ListaDeRupturaP(int id, int cod_interno, String descricao, Object qtd_perdida) {
        this.id = id;
        this.cod_interno = cod_interno;
        this.descricao = descricao;
        this.qtd_perdida = qtd_perdida;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Object getQtd_perdida() {
        return qtd_perdida;
    }

    public void setQtd_perdida(Object qtd_perdida) {
        this.qtd_perdida = qtd_perdida;
    }

}
