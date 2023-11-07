package app.shop.dominio.repositoryInterface;

import java.util.List;

import app.shop.dominio.model.ProdutoModel;

public interface IRepProduto {
    
    public List<ProdutoModel> findAll();

    public ProdutoModel findById(Long id);
}
