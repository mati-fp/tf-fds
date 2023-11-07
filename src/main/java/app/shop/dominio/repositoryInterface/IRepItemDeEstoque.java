package app.shop.dominio.repositoryInterface;

import java.util.Optional;

import app.shop.dominio.model.ItemDeEstoqueModel;
import app.shop.dominio.model.ProdutoModel;


public interface IRepItemDeEstoque {

    public Optional<ItemDeEstoqueModel> findByProduto(ProdutoModel produto);

    public ItemDeEstoqueModel save(ItemDeEstoqueModel itemDeEstoque);
    
} 
