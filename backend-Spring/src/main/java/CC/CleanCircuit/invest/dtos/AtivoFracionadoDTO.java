package CC.CleanCircuit.invest.dtos;

import CC.CleanCircuit.invest.enums.Tipo;

import java.time.LocalDate;

public class AtivoFracionadoDTO {
    private String sigla;
    private double cotas;
    private double valorCota;
    private String tipo;//venda ou compra
    private LocalDate dataAplicacao;
    private Tipo tipoAtivo;

    public Tipo getTipoAtivo() {
        return tipoAtivo;
    }

    public void setTipoAtivo(Tipo tipoAtivo) {
        this.tipoAtivo = tipoAtivo;
    }

    public LocalDate getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(LocalDate dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

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
