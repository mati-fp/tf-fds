package app.shop.adaptorsInterfaces.interfacesRepositorio;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import app.shop.dominio.ItemPedido;
import app.shop.dominio.Orcamento;

@Repository
public interface IRepItemPedido extends CrudRepository<ItemPedido, Long> {
    List<ItemPedido> findByOrcamento(Orcamento pedidoId);
}

