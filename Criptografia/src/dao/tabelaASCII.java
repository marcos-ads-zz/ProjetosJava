package dao;

/**
 *
 * @author Marcos Júnior <marcosneri@outlook.com.br>
 */
public class tabelaASCII {

    private int id;
    private int DecimalASCII;
    private int Binario;
    private String HexASCII;
    private String Referencia;
    private String referenciaCifrada;
    private int DecimalASCIICifrado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDecimalASCII() {
        return DecimalASCII;
    }

    public void setDecimalASCII(int DecimalASCII) {
        this.DecimalASCII = DecimalASCII;
    }

    public int getBinario() {
        return Binario;
    }

    public void setBinario(int Binario) {
        this.Binario = Binario;
    }

    public String getHexASCII() {
        return HexASCII;
    }

    public void setHexASCII(String HexASCII) {
        this.HexASCII = HexASCII;
    }

    public String getReferencia() {
        return Referencia;
    }

    public void setReferencia(String Referencia) {
        this.Referencia = Referencia;
    }

    public String getReferenciaCifrada() {
        return referenciaCifrada;
    }

    public void setReferenciaCifrada(String referenciaCifrada) {
        this.referenciaCifrada = referenciaCifrada;
    }

    public int getDecimalASCIICifrado() {
        return DecimalASCIICifrado;
    }

    public void setDecimalASCIICifrado(int DecimalASCIICifrado) {
        this.DecimalASCIICifrado = DecimalASCIICifrado;
    }

}
