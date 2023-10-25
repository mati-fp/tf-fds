package app.shop.dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "descricao", length = 255)
    private String descricao;
    @Column(precision = 2, name = "preco_unitario")
    private Double precoUnitario;
    @Column(name = "quantidade_maxima")
    private Integer quantidadeMaxima;
    @Column(name = "quantidade_minima")
    private Integer quantidadeMinima;
    @Column(name = "quantidade_atual")
    private Integer quantidadeAtual;

    protected Produto() {}

    public Produto(Integer codigo, String descricao, Double preco, Integer quantidadeMaxima, Integer quantidadeMinima,
            Integer quantidadeAtual) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.precoUnitario = preco;
        this.quantidadeMaxima = quantidadeMaxima;
        this.quantidadeMinima = quantidadeMinima;
        this.quantidadeAtual = quantidadeAtual;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(double preco) {
        this.precoUnitario = preco;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return precoUnitario;
    }

    public Integer getQuantidadeMaxima() {
        return quantidadeMaxima;
    }

    public void setQuantidadeMaxima(Integer quantidadeMaxima) {
        this.quantidadeMaxima = quantidadeMaxima;
    }

    public Integer getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(Integer quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public Integer getQuantidadeAtual() {
        return quantidadeAtual;
    }

    public void setQuantidadeAtual(Integer quantidadeAtual) {
        this.quantidadeAtual = quantidadeAtual;
    }

    @Override
    public String toString() {
        return "Produto [codigo=" + codigo + ", descricao=" + descricao + ", preco=" + precoUnitario + ", quantidadeAtual="
                + quantidadeAtual + ", quantidadeMaxima=" + quantidadeMaxima + ", quantidadeMinima=" + quantidadeMinima
                + "]";
    }
}