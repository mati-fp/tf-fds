package app.shop.dominio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface IRepProdutos extends CrudRepository<Produto, String>{
    Produto save(Produto produto);
    List<Produto> findAll();
}


