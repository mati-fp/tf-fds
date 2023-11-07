package app.shop.adaptorsInterfaces.interfacesJPA;

import org.springframework.data.repository.CrudRepository;

import app.shop.adaptorsInterfaces.entity.Produto;

public interface IRepProdutosJPA extends CrudRepository<Produto, Long>{

}


