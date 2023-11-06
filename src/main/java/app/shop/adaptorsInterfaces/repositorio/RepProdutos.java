package app.shop.adaptorsInterfaces.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import app.shop.adaptorsInterfaces.entity.Produto;
import app.shop.adaptorsInterfaces.interfacesRepositorio.IRepProdutosJPA;
import app.shop.dominio.IRepProduto;

@Repository
@Primary
public class RepProdutos implements IRepProduto{

    @Autowired
    private IRepProdutosJPA repProdutos;

    public List<Produto> findAll(){
        return (List<Produto>) repProdutos.findAll();
    }
    
    public Optional<Produto> findById(Long id){
        return repProdutos.findById(id);
    }
}
