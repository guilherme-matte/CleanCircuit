package CC.CleanCircuit.invest.dtos;

public class AtivoDTO {
private String sigla;
private int cotas;
private double valorCota;
private String tipo;//venda ou compra

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public int getCotas() {
        return cotas;
    }

    public void setCotas(int cotas) {
        this.cotas = cotas;
    }

    public double getValorCota() {
        return valorCota;
    }

    public void setValorCota(double valorCota) {
        this.valorCota = valorCota;
    }
}
