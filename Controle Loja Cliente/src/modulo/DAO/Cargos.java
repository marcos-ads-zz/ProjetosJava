package modulo.DAO;

/**
 *
 * @author Marcos Junior
 */
public class Cargos {

    private int id;
    private String cargos;
    private int nivel;

    public Cargos() {
    }

    public Cargos(int id, String funcao, int nivel) {
        this.id = id;
        this.cargos = funcao;
        this.nivel = nivel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCargos() {
        return cargos;
    }

    public void setCargos(String cargos) {
        this.cargos = cargos;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

}
