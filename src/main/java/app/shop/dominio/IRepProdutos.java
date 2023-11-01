package app.shop.dominio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepProdutos extends CrudRepository<Produto, Long>{

}


