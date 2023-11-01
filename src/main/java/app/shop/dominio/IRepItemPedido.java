package app.shop.dominio;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepItemPedido extends CrudRepository<ItemPedido, Long> {
    List<ItemPedido> findByOrcamento(Orcamento pedidoId);
}

