package app.shop.adaptorsInterfaces.interfacesRepositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import app.shop.dominio.Produto;

@Repository
public interface IRepProdutos extends CrudRepository<Produto, Long>{

}


