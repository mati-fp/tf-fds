package app.shop.dominio.repositoryInterface;

import app.shop.dominio.model.ClienteModel;

public interface IRepCliente {

    ClienteModel findOrCreateByName(String nomeCliente);
}
