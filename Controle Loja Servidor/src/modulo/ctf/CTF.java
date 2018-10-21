package modulo.ctf;

import java.sql.Date;

/**
 *
 * @author Marcos Junior
 */
public class CTF {

    private int id_ctf;
    private int id_user;
    private int nsu;
    private int pdv;
    private double valor;
    private Date date_ctf;
    private Date date_registro;
    private String status;
    private int mac;

    public CTF() {
    }

    public CTF(int id_ctf, int id_user, int nsu, int pdv, double valor, Date date_ctf, Date date_registro, String status, int mac) {
        this.id_ctf = id_ctf;
        this.id_user = id_user;
        this.nsu = nsu;
        this.pdv = pdv;
        this.valor = valor;
        this.date_ctf = date_ctf;
        this.date_registro = date_registro;
        this.status = status;
        this.mac = mac;
    }

    public int getId_ctf() {
        return id_ctf;
    }

    public void setId_ctf(int id_ctf) {
        this.id_ctf = id_ctf;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getNsu() {
        return nsu;
    }

    public void setNsu(int nsu) {
        this.nsu = nsu;
    }

    public int getPdv() {
        return pdv;
    }

    public void setPdv(int pdv) {
        this.pdv = pdv;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDate_ctf() {
        return date_ctf;
    }

    public void setDate_ctf(Date date_ctf) {
        this.date_ctf = date_ctf;
    }

    public Date getDate_registro() {
        return date_registro;
    }

    public void setDate_registro(Date date_registro) {
        this.date_registro = date_registro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMac() {
        return mac;
    }

    public void setMac(int mac) {
        this.mac = mac;
    }

}
