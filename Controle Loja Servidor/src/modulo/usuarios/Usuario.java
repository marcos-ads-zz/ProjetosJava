package modulo.usuarios;

import java.sql.Date;

/**
 *
 * @author Marcos Junior
 */
public class Usuario {

    private int id;
    private String nome;
    private int matricula;
    private String email;
    private Date datnasc;
    private String telefone;
    private String funcao;
    private String senha;

    public Usuario() {
    }

    public Usuario(int id, String nome, int matricula, String email, Date datnasc, String telefone, String funcao, String senha) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.email = email;
        this.datnasc = datnasc;
        this.telefone = telefone;
        this.funcao = funcao;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDatnasc() {
        return datnasc;
    }

    public void setDatnasc(Date datnasc) {
        this.datnasc = datnasc;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
