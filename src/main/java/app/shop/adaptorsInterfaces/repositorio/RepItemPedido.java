package app.shop.adaptorsInterfaces.repositorio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import app.shop.adaptorsInterfaces.interfacesRepositorio.IRepItemPedido;
import app.shop.dominio.ItemPedido;
import app.shop.dominio.Orcamento;

@Repository
@Primary
public class RepItemPedido {

    @Autowired
    private IRepItemPedido itemPedidoRep;

    public void saveAll(Iterable<ItemPedido> entities, Orcamento pedido){
        itemPedidoRep.saveAll(entities);
    }
    
    public List<ItemPedido> findByOrcamento(Orcamento orcamento){
        return itemPedidoRep.findByOrcamento(orcamento);
    }

    public void save(ItemPedido itemPedido){
        itemPedidoRep.save(itemPedido);
    }
}
