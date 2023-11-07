package app.shop.dominio.repositoryInterface;

import java.util.List;
import java.util.Optional;

import app.shop.dominio.model.ProdutoModel;

public interface IRepProduto {
    
    public List<ProdutoModel> findAll();

    public Optional<ProdutoModel> findById(Long id);
}
