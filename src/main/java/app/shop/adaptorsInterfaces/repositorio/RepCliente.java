package app.shop.adaptorsInterfaces.repositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import app.shop.adaptorsInterfaces.entity.Cliente;
import app.shop.adaptorsInterfaces.interfacesJPA.IRepClienteJPA;
import app.shop.dominio.model.ClienteModel;
import app.shop.dominio.repositoryInterface.IRepCliente;

@Repository
@Primary
public class RepCliente implements IRepCliente{
    
    @Autowired
    private IRepClienteJPA repClientesJPA;

    @Override
    public ClienteModel findOrCreateByName(String nomeCliente) {
        String nome = nomeCliente.toUpperCase();
        Cliente cliente = repClientesJPA.findByNome(nome);
        if (cliente == null) {
            cliente = new Cliente();
            cliente.setNome(nome);
            cliente = repClientesJPA.save(cliente);
        }
        ClienteModel clienteModel = ModelEntityMapper.clienteToModel(cliente);
        return clienteModel;
    }
}
