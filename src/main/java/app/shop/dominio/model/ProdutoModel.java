package app.shop.dominio.model;

import java.util.List;

public class ProdutoModel {

    private Long codigo;
    
    private String descricao;

    private Double precoUnitario;

    private List<ItemPedidoModel> itensPedido;

    private ItemDeEstoqueModel itemDeEstoque;

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

    public List<ItemPedidoModel> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedidoModel> itensPedido) {
        this.itensPedido = itensPedido;
    }

    public ItemDeEstoqueModel getItemDeEstoque() {
        return itemDeEstoque;
    }

    public void setItemDeEstoque(ItemDeEstoqueModel itemDeEstoque) {
        this.itemDeEstoque = itemDeEstoque;
    }

    
}
