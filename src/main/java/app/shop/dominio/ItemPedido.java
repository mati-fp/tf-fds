package app.shop.dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemPedido {
    
    @Id
    @ManyToOne
    @JoinColumn(name = "orcamento")
    private Orcamento orcamento;
    @ManyToOne
    @Column(name = "codigo_produto")
    @JoinColumn(name = "codigo_produto")
    private Produto produto;
    @Column(name = "quantidade")
    private Integer quantidade;
    

    public ItemPedido() {}
    public ItemPedido(Produto produto, Integer quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    public Orcamento getOrcamento() {
        return orcamento;
    }
    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }
    
}