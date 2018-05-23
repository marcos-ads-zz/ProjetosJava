package modulo.base.dados;

/**
 *
 * @author Marcos
 */
public class Base {

    private int id;
    private int dados;
    private int peso;
    private int valor0;
    private int valor1;
    private int valor2;
    private int valor3;

    public Base() {
    }

    public Base(int id, int dados, int peso, int valor0, int valor1, int valor2, int valor3) {
        this.id = id;
        this.dados = dados;
        this.peso = peso;
        this.valor0 = valor0;
        this.valor1 = valor1;
        this.valor2 = valor2;
        this.valor3 = valor3;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDados() {
        return dados;
    }

    public void setDados(int dados) {
        this.dados = dados;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getValor0() {
        return valor0;
    }

    public void setValor0(int valor0) {
        this.valor0 = valor0;
    }

    public int getValor1() {
        return valor1;
    }

    public void setValor1(int valor1) {
        this.valor1 = valor1;
    }

    public int getValor2() {
        return valor2;
    }

    public void setValor2(int valor2) {
        this.valor2 = valor2;
    }

    public int getValor3() {
        return valor3;
    }

    public void setValor3(int valor3) {
        this.valor3 = valor3;
    }

}
