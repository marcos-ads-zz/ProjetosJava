/*
 * Para alterar esse cabeçalho de licença, escolha Cabeçalhos de licença em Propriedades do projeto.
 * Para alterar este arquivo de modelo, escolha Ferramentas | Modelos
 * e abra o modelo no editor.
 */
package modulo.entidades;

import java.sql.Date;

/**
 *
 * @author Marcos Júnior <marcosneri@outlook.com.br>
 */
public class Seguranca {

    private int id;
    private String seriralPlacaMae;
    private String serialDeAutorizacao;
    private String status;
    private Date dataRegistro;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeriralPlacaMae() {
        return seriralPlacaMae;
    }

    public void setSeriralPlacaMae(String seriralPlacaMae) {
        this.seriralPlacaMae = seriralPlacaMae;
    }

    public String getSerialDeAutorizacao() {
        return serialDeAutorizacao;
    }

    public void setSerialDeAutorizacao(String serialDeAutorizacao) {
        this.serialDeAutorizacao = serialDeAutorizacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

}
