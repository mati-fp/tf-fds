package app.shop.dominio.repositoryInterface;

import java.util.List;

import app.shop.dominio.model.ItemPedidoModel;
import app.shop.dominio.model.OrcamentoModel;


public interface IRepItemPedido {

    public List<ItemPedidoModel> findByOrcamento(OrcamentoModel orcamento);

    public ItemPedidoModel save(ItemPedidoModel itemPedido);
}
