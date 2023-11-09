package app.shop.adaptorsInterfaces.interfacesJPA;

import org.springframework.data.repository.CrudRepository;

import app.shop.adaptorsInterfaces.entity.Cliente;

public interface IRepClientesJPA extends CrudRepository<Cliente, Long>{
    
    Cliente findByNome(String nome);
}
