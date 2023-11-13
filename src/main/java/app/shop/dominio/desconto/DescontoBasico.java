package app.shop.dominio.desconto;

import org.springframework.stereotype.Component;

import app.shop.dominio.model.OrcamentoModel;

@Component
public class DescontoBasico implements DescontoStrategy{
    /* 5% de desconto se tiver mais que 5 pedidos */

    @Override
    public Double calcularDesconto(OrcamentoModel orcamentoModel, Integer qtdCompras) {
        if (qtdCompras > 5){
            Double desconto = orcamentoModel.getCustoPedido() * 0.05;
            return desconto;
        }
        return 0.0;
    }
}
