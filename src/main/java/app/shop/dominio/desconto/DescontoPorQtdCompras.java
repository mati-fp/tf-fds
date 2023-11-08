package app.shop.dominio.desconto;

import org.springframework.stereotype.Component;

import app.shop.dominio.model.OrcamentoModel;

@Component
public class DescontoPorQtdCompras implements DescontoStrategy{
    /* Clientes com mais de 10 compras nos últimos 6 meses 
    recebem desconto de 25%, indiferente do valor.*/

    @Override
    public Double calcularDesconto(OrcamentoModel orcamentoModel, Integer qtdCompras) {
        return 0.0;
    }
}
