package app.shop.dominio.repositoryInterface;

import app.shop.dominio.model.ClienteModel;

public interface IRepClientes {

    ClienteModel findOrCreateByName(String nomeCliente);
}
