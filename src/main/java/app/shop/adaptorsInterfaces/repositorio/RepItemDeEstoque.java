package app.shop.adaptorsInterfaces.repositorio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import app.shop.adaptorsInterfaces.entity.ItemDeEstoque;
import app.shop.adaptorsInterfaces.entity.Produto;
import app.shop.adaptorsInterfaces.interfacesRepositorio.IRepItemDeEstoqueJPA;
import app.shop.dominio.IRepItemDeEstoque;

@Repository
@Primary
public class RepItemDeEstoque implements IRepItemDeEstoque{
    
    @Autowired
    private IRepItemDeEstoqueJPA itemEstoqueRep;

    public Optional<ItemDeEstoque> findByProduto(Produto produto){
        return itemEstoqueRep.findByProduto(produto);
    }

    public void save(ItemDeEstoque itemDeEstoque){
        itemEstoqueRep.save(itemDeEstoque);
    }
}
