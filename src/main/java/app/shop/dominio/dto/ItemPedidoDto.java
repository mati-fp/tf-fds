package app.shop.dominio.dto;

public class ItemPedidoDto {
    public Long codigoProduto;
    public Integer quantidade;

    public ItemPedidoDto(Long codigoProduto, Integer quantidade) {
        this.codigoProduto = codigoProduto;
        this.quantidade = quantidade;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
