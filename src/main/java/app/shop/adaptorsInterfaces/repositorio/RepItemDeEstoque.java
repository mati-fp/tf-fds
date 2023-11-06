package app.shop.adaptorsInterfaces.repositorio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import app.shop.adaptorsInterfaces.interfacesRepositorio.IRepItemDeEstoque;
import app.shop.dominio.ItemDeEstoque;
import app.shop.dominio.Produto;

@Repository
@Primary
public class RepItemDeEstoque {
    
    @Autowired
    private IRepItemDeEstoque itemEstoqueRep;

    public Optional<ItemDeEstoque> findByProduto(Produto produto){
        return itemEstoqueRep.findByProduto(produto);
    }

    public void save(ItemDeEstoque itemDeEstoque){
        itemEstoqueRep.save(itemDeEstoque);
    }
}
