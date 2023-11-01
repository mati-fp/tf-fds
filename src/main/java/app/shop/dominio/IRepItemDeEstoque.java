package app.shop.dominio;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepItemDeEstoque extends CrudRepository<ItemDeEstoque, Long>{
    Optional<ItemDeEstoque> findByProduto(Produto codProduto);
}
