package app.shop.dominio;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface IRepItemDeEstoque extends CrudRepository<ItemDeEstoque, Long>{
    Optional<ItemDeEstoque> findByCodProduto(Long codProduto);
}
