package CC.CleanCircuit.invest.dtos;

public class ResumoAtivoDTO {
    private String sigla;
    private String nome;
    private double valorAplicado;      // cotas * valorTotal
    private double valorAtual;       // pega o valor atual do ativo
    private double valorAtualTotal;
    private double dividendos;
    private double lucroPrejuizo;
    private int cotas;

    public double getValorAtualTotal() {
        return valorAtualTotal;
    }

    public void setValorAtualTotal(double valorAtualTotal) {
        this.valorAtualTotal = valorAtualTotal;
    }

    public int getCotas() {
        return cotas;
    }

    public void setCotas(int cotas) {
        this.cotas = cotas;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValorAplicado() {
        return valorAplicado;
    }

    public void setValorAplicado(double valorAplicado) {
        this.valorAplicado = valorAplicado;
    }

    public double getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(double valorAtual) {
        this.valorAtual = valorAtual;
    }

    public double getDividendos() {
        return dividendos;
    }

    public void setDividendos(double dividendos) {
        this.dividendos = dividendos;
    }

    public double getLucroPrejuizo() {
        return lucroPrejuizo;
    }

    public void setLucroPrejuizo(double lucroPrejuizo) {
        this.lucroPrejuizo = lucroPrejuizo;
    }
}
