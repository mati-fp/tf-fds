package app.shop.dominio;

import java.util.List;
import java.util.Optional;

import app.shop.adaptorsInterfaces.entity.Produto;

public interface IRepProduto {
    
    public List<Produto> findAll();

    public Optional<Produto> findById(Long id);
}
