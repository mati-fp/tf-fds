package app.shop.dominio;

import java.util.List;

import app.shop.adaptorsInterfaces.entity.ItemPedido;
import app.shop.adaptorsInterfaces.entity.Orcamento;

public interface IRepItemPedido {
    
    public void saveAll(Iterable<ItemPedido> entities, Orcamento pedido);

    public List<ItemPedido> findByOrcamento(Orcamento orcamento);

    public void save(ItemPedido itemPedido);
}
