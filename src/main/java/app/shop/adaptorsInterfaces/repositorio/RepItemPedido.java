package app.shop.adaptorsInterfaces.repositorio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import app.shop.adaptorsInterfaces.entity.ItemPedido;
import app.shop.adaptorsInterfaces.entity.Orcamento;
import app.shop.adaptorsInterfaces.interfacesRepositorio.IRepItemPedidoJPA;
import app.shop.dominio.IRepItemPedido;

@Repository
@Primary
public class RepItemPedido implements IRepItemPedido{

    @Autowired
    private IRepItemPedidoJPA itemPedidoRep;

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
