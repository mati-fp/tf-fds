package app.shop.adaptorsInterfaces.repositorio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import app.shop.adaptorsInterfaces.entity.ItemPedido;
import app.shop.adaptorsInterfaces.entity.Orcamento;
import app.shop.adaptorsInterfaces.interfacesJPA.IRepItemPedidoJPA;
import app.shop.dominio.model.ItemPedidoModel;
import app.shop.dominio.model.OrcamentoModel;
import app.shop.dominio.repositoryInterface.IRepItemPedido;

@Repository
@Primary
public class RepItemPedido implements IRepItemPedido{

    @Autowired
    private IRepItemPedidoJPA itemPedidoRep;
    
    public List<ItemPedidoModel> findByOrcamento(OrcamentoModel orcamentoModel){
        Orcamento orcamentoEntity = ModelEntityMapper.orcamentoToEntity(orcamentoModel);

        List<ItemPedido> itensPedidoEntity = itemPedidoRep.findByOrcamento(orcamentoEntity);

        List<ItemPedidoModel> itensPedidoModel = List.of();

        for (ItemPedido itemPedido : itensPedidoEntity) {
            itensPedidoModel.add(ModelEntityMapper.itemPedidoToModel(itemPedido));
        }

        return itensPedidoModel;
    }

    public ItemPedidoModel save(ItemPedidoModel itemPedidoModel){
        ItemPedido itemPedidoEntity = ModelEntityMapper.itemPedidoToEntity(itemPedidoModel);
        itemPedidoEntity = itemPedidoRep.save(itemPedidoEntity);
        return ModelEntityMapper.itemPedidoToModel(itemPedidoEntity);
    }
}
