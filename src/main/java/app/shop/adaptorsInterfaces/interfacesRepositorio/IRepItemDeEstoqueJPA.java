package app.shop.adaptorsInterfaces.interfacesRepositorio;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import app.shop.adaptorsInterfaces.entity.ItemDeEstoque;
import app.shop.adaptorsInterfaces.entity.Produto;

public interface IRepItemDeEstoqueJPA extends CrudRepository<ItemDeEstoque, Long>{
    Optional<ItemDeEstoque> findByProduto(Produto codProduto);
}
