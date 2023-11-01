package app.shop.dominio;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface IRepItemPedido extends CrudRepository<ItemPedido, Long> {
    List<ItemPedido> findByPedidoId(Long pedidoId);
}

