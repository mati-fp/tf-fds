package app.shop.adaptorsInterfaces.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import app.shop.adaptorsInterfaces.interfacesRepositorio.IRepProdutos;
import app.shop.dominio.Produto;

@Repository
@Primary
public class RepProdutos {

    @Autowired
    private IRepProdutos repProdutos;

    public List<Produto> findAll(){
        return (List<Produto>) repProdutos.findAll();
    }
    
    public Optional<Produto> findById(Long id){
        return repProdutos.findById(id);
    }
}
