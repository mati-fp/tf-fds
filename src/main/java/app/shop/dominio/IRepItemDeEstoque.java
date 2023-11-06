package app.shop.dominio;

import java.util.Optional;

import app.shop.adaptorsInterfaces.entity.ItemDeEstoque;
import app.shop.adaptorsInterfaces.entity.Produto;

public interface IRepItemDeEstoque {

    public Optional<ItemDeEstoque> findByProduto(Produto produto);

    public void save(ItemDeEstoque itemDeEstoque);
    
} 
