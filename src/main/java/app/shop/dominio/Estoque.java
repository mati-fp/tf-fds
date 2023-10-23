package app.shop.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    private Integer quantidadeMaxima;
    private Integer quantidadeMinima;
    private Integer quantidadeAtual;

    public Estoque() {}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
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


}
