package app.shop.dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemPedido {
    @Id
    @Column(name = "codigo_produto")
    private Integer codProduto;
    @ManyToOne
    @JoinColumn(name = "codigo_produto")
    private Produto produto;
    @Column(name = "quantidade")
    private Integer quantidade;
    @Column(name = "pedido")
    private Integer pedido;
    @ManyToOne
    @JoinColumn(name = "pedido")
    private Orcamento orcamento;

    public ItemPedido() {}
    public ItemPedido(Integer codProduto, Integer quantidade) {
        this.codProduto = codProduto;
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
    public Integer getCodProduto() {
        return codProduto;
    }
    public void setCodProduto(Integer codProduto) {
        this.codProduto = codProduto;
    }
    public Integer getPedido() {
        return pedido;
    }
    public void setPedido(Integer pedido) {
        this.pedido = pedido;
    }
    public Orcamento getOrcamento() {
        return orcamento;
    }
    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }
    
    
}