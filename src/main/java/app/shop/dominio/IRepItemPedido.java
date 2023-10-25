package app.shop.dominio;

import org.springframework.data.repository.CrudRepository;

public interface IRepItemPedido extends CrudRepository<ItemPedido, Integer>{
    void saveAll(Iterable<ItemPedido> entities, int pedido);
}
