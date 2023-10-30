package app.shop.dominio;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo", updatable = false, nullable = false)
    private Long codigo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "preco_unitario")
    private Double precoUnitario;

    @OneToMany(mappedBy = "produto")
    private List<ItemPedido> itensPedido;

    @OneToOne(mappedBy = "produto")
    private ItemDeEstoque itemDeEstoque;

    public Produto() {}

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

    public ItemDeEstoque getItemDeEstoque() {
        return itemDeEstoque;
    }

    public void setItemDeEstoque(ItemDeEstoque itemDeEstoque) {
        this.itemDeEstoque = itemDeEstoque;
    }

    
}
