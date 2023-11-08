package app.shop.dominio.desconto;

import org.springframework.stereotype.Component;

import app.shop.dominio.model.OrcamentoModel;

@Component
public interface DescontoStrategy {
    Double calcularDesconto(OrcamentoModel orcamentoModel, Integer qtdCompras);
}

