package app.shop.adaptorsInterfaces.repositorio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import app.shop.adaptorsInterfaces.entity.ItemDeEstoque;
import app.shop.adaptorsInterfaces.entity.Produto;
import app.shop.adaptorsInterfaces.interfacesJPA.IRepItemDeEstoqueJPA;
import app.shop.dominio.model.ItemDeEstoqueModel;
import app.shop.dominio.model.ProdutoModel;
import app.shop.dominio.repositoryInterface.IRepItemDeEstoque;

@Repository
@Primary
public class RepItemDeEstoque implements IRepItemDeEstoque{
    
    @Autowired
    private IRepItemDeEstoqueJPA itemEstoqueRep;

    public Optional<ItemDeEstoqueModel> findByProduto(ProdutoModel produtoModel){
        Produto produtoEntity = ModelEntityMapper.produtoToEntity(produtoModel);

        Optional<ItemDeEstoque> itemDeEstoque = itemEstoqueRep.findByProduto(produtoEntity);

        return Optional.of(ModelEntityMapper.itemDeEstoqueToModel(itemDeEstoque.get()));
    }

    public ItemDeEstoqueModel save(ItemDeEstoqueModel itemDeEstoque){
        ItemDeEstoque itemDeEstoqueEntity = ModelEntityMapper.itemDeEstoqueToEntity(itemDeEstoque);
        itemDeEstoqueEntity = itemEstoqueRep.save(itemDeEstoqueEntity);
        return ModelEntityMapper.itemDeEstoqueToModel(itemDeEstoqueEntity);
    }
}
