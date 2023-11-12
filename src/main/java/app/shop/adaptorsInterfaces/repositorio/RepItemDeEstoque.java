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

    public ItemDeEstoqueModel findByProduto(ProdutoModel produtoModel) throws RuntimeException{
        Produto produtoEntity = ModelEntityMapper.produtoToEntity(produtoModel);

        Optional<ItemDeEstoque> itemDeEstoque = itemEstoqueRep.findByProduto(produtoEntity);

        if (itemDeEstoque.isEmpty()) {
            throw new RuntimeException("Produto não encontrado no estoque");
        }

        return ModelEntityMapper.itemDeEstoqueToModel(itemDeEstoque.get());
    }

    public ItemDeEstoqueModel save(ItemDeEstoqueModel itemDeEstoque){
        ItemDeEstoque itemDeEstoqueEntity = ModelEntityMapper.itemDeEstoqueToEntity(itemDeEstoque);
        ItemDeEstoque existingItem = itemEstoqueRep.findById(itemDeEstoque.getId()).orElseThrow(() -> new RuntimeException("Item not found"));
        itemDeEstoqueEntity.setProduto(existingItem.getProduto());
        itemDeEstoqueEntity = itemEstoqueRep.save(itemDeEstoqueEntity);
        return ModelEntityMapper.itemDeEstoqueToModel(itemDeEstoqueEntity);
    }
}
