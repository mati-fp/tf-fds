package app.shop.adaptorsInterfaces.repositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import app.shop.adaptorsInterfaces.entity.Cliente;
import app.shop.adaptorsInterfaces.interfacesJPA.IRepClientesJPA;
import app.shop.dominio.model.ClienteModel;
import app.shop.dominio.repositoryInterface.IRepClientes;

@Repository
@Primary
public class RepClientes implements IRepClientes{
    
    @Autowired
    private IRepClientesJPA repClientesJPA;

    @Override
    public ClienteModel findOrCreateByName(String nomeCliente) {

        Cliente cliente = repClientesJPA.findByNome(nomeCliente);
        if (cliente == null) {
            cliente = new Cliente();
            cliente.setNome(nomeCliente);
            cliente = repClientesJPA.save(cliente);
        }
        ClienteModel clienteModel = ModelEntityMapper.clienteToModel(cliente);
        return clienteModel;
    }
}
