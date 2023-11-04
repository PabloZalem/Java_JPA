package br.com.zalempablo.VO;

import java.time.LocalDate;

public class RelatorioDeVendas {
    private String nomeProduto;
    private Long quantidadeVendida;
    private LocalDate dataDaUltimaVenda;

    public RelatorioDeVendas(String nomeProduto, Long quantidadeVendida, LocalDate dataDaUltimaVenda) {
        this.nomeProduto = nomeProduto;
        this.quantidadeVendida = quantidadeVendida;
        this.dataDaUltimaVenda = dataDaUltimaVenda;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Long getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(Long quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    public LocalDate getDataDaUltimaVenda() {
        return dataDaUltimaVenda;
    }

    public void setDataDaUltimaVenda(LocalDate dataDaUltimaVenda) {
        this.dataDaUltimaVenda = dataDaUltimaVenda;
    }

    @Override
    public String toString() {
        return "RelatorioDeVendas{" +
                "nomeProduto='" + nomeProduto + '\'' +
                ", quantidadeVendida=" + quantidadeVendida +
                ", dataDaUltimaVenda=" + dataDaUltimaVenda +
                '}';
    }
}
