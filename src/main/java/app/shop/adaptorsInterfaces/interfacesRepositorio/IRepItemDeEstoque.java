package app.shop.adaptorsInterfaces.interfacesRepositorio;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import app.shop.dominio.ItemDeEstoque;
import app.shop.dominio.Produto;

@Repository
public interface IRepItemDeEstoque extends CrudRepository<ItemDeEstoque, Long>{
    Optional<ItemDeEstoque> findByProduto(Produto codProduto);
}
