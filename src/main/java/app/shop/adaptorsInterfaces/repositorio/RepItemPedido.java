package app.shop.adaptorsInterfaces.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import app.shop.adaptorsInterfaces.interfacesRepositorio.IRepItemPedido;
import app.shop.dominio.ItemPedido;
import app.shop.dominio.Orcamento;

@Repository

public class RepItemPedido {

    @Autowired
    private IRepItemPedido itemPedidoRep;

    public void saveAll(Iterable<ItemPedido> entities, Orcamento pedido){
        itemPedidoRep.saveAll(entities);
    }
    
}
