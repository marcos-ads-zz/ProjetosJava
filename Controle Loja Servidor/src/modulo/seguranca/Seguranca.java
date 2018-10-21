/*
 * Para alterar esse cabeçalho de licença, escolha Cabeçalhos de licença em Propriedades do projeto.
 * Para alterar este arquivo de modelo, escolha Ferramentas | Modelos
 * e abra o modelo no editor.
 */
package modulo.seguranca;

import java.sql.Date;

/**
 *
 * @author Marcos Júnior <marcosneri@outlook.com.br>
 */
public class Seguranca {

    private int id;
    private String seriralPlacaMae;
    private String MD5DeAutorizacao;
    private int autorizador;
    private String status;
    private String obs;
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

    public String getMD5DeAutorizacao() {
        return MD5DeAutorizacao;
    }

    public void setMD5DeAutorizacao(String MD5DeAutorizacao) {
        this.MD5DeAutorizacao = MD5DeAutorizacao;
    }

    public int getAutorizador() {
        return autorizador;
    }

    public void setAutorizador(int autorizador) {
        this.autorizador = autorizador;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

}
