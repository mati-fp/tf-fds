package app.shop.adaptorsInterfaces.interfacesJPA;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import app.shop.adaptorsInterfaces.entity.ItemPedido;
import app.shop.adaptorsInterfaces.entity.Orcamento;

public interface IRepItemPedidoJPA extends CrudRepository<ItemPedido, Long> {
    List<ItemPedido> findByOrcamento(Orcamento pedidoId);
}

