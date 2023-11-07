package app.shop.dominio.repositoryInterface;

import app.shop.dominio.model.ItemDeEstoqueModel;
import app.shop.dominio.model.ProdutoModel;


public interface IRepItemDeEstoque {

    public ItemDeEstoqueModel findByProduto(ProdutoModel produto);

    public ItemDeEstoqueModel save(ItemDeEstoqueModel itemDeEstoque);
    
} 
