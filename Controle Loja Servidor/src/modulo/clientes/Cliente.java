package modulo.clientes;

import java.sql.Date;

/**
 *
 * @author Marcos Junior
 */
public class Cliente {

    private int id;
    private String nome_cli;
    private String end_cli;
    private String telefone_cli;
    private Date data_registro;

    public Cliente() {
    }

    public Cliente(int id, String nome_cli, String end_cli, String telefone_cli, Date data_registro) {
        this.id = id;
        this.nome_cli = nome_cli;
        this.end_cli = end_cli;
        this.telefone_cli = telefone_cli;
        this.data_registro = data_registro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_cli() {
        return nome_cli;
    }

    public void setNome_cli(String nome_cli) {
        this.nome_cli = nome_cli;
    }

    public String getEnd_cli() {
        return end_cli;
    }

    public void setEnd_cli(String end_cli) {
        this.end_cli = end_cli;
    }

    public String getTelefone_cli() {
        return telefone_cli;
    }

    public void setTelefone_cli(String telefone_cli) {
        this.telefone_cli = telefone_cli;
    }

    public Date getData_registro() {
        return data_registro;
    }

    public void setData_registro(Date data_registro) {
        this.data_registro = data_registro;
    }

}
