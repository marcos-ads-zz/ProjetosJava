package modulo.entidades;

import java.sql.Date;

/**
 *
 * @author Marcos Junior
 */
public class CampanhasPlanoDeVoo {

    private int id_voo;
    private int matricula;
    private double pdv1;
    private double pdv2;
    private double pdv3;
    private double pdv4;
    private double totalpdvs;
    private int cli1;
    private int cli2;
    private int cli3;
    private int cli4;
    private int totalcli;
    private Date data_registro;

    public int getId_voo() {
        return id_voo;
    }

    public void setId_voo(int id_voo) {
        this.id_voo = id_voo;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public double getPdv1() {
        return pdv1;
    }

    public void setPdv1(double pdv1) {
        this.pdv1 = pdv1;
    }

    public double getPdv2() {
        return pdv2;
    }

    public void setPdv2(double pdv2) {
        this.pdv2 = pdv2;
    }

    public double getPdv3() {
        return pdv3;
    }

    public void setPdv3(double pdv3) {
        this.pdv3 = pdv3;
    }

    public double getPdv4() {
        return pdv4;
    }

    public void setPdv4(double pdv4) {
        this.pdv4 = pdv4;
    }

    public double getTotalpdvs() {
        return totalpdvs;
    }

    public void setTotalpdvs(double totalpdvs) {
        this.totalpdvs = totalpdvs;
    }

    public int getCli1() {
        return cli1;
    }

    public void setCli1(int cli1) {
        this.cli1 = cli1;
    }

    public int getCli2() {
        return cli2;
    }

    public void setCli2(int cli2) {
        this.cli2 = cli2;
    }

    public int getCli3() {
        return cli3;
    }

    public void setCli3(int cli3) {
        this.cli3 = cli3;
    }

    public int getCli4() {
        return cli4;
    }

    public void setCli4(int cli4) {
        this.cli4 = cli4;
    }

    public int getTotalcli() {
        return totalcli;
    }

    public void setTotalcli(int totalcli) {
        this.totalcli = totalcli;
    }

    public Date getData_registro() {
        return data_registro;
    }

    public void setData_registro(Date data_registro) {
        this.data_registro = data_registro;
    }

}
