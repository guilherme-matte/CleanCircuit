package CC.CleanCircuit.invest.dtos;

public class AtivoFracionadoDTO {
    private String sigla;
    private double cotas;
    private double valorCota;
    private String tipo;//venda ou compra

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public double getCotas() {
        return cotas;
    }

    public void setCotas(double cotas) {
        this.cotas = cotas;
    }

    public double getValorCota() {
        return valorCota;
    }

    public void setValorCota(double valorCota) {
        this.valorCota = valorCota;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
