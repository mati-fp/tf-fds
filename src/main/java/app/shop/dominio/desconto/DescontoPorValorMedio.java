package app.shop.dominio.desconto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.shop.dominio.model.OrcamentoModel;
import app.shop.dominio.repositoryInterface.IRepOrcamento;

@Component
public class DescontoPorValorMedio implements DescontoStrategy{
    /*clientes com valor médio de suas últimas 3 compras superior a 
    R$ 10 mil recebem 10% de desconto e a cada R$ 10 mil adicionais 5% 
    adicionais até um limite de 30% de desconto */

    @Autowired
    private IRepOrcamento orcamentoRep;

    @Override
    public Double calcularDesconto(OrcamentoModel orcamentoModel, Integer qtdCompras) {
        
        String nomeCliente = orcamentoModel.getNomeCliente();
        Double valorMedio = orcamentoRep.getValorDas3UltimasComprasPorCliente(nomeCliente);
        
        return 0.0;
    }
}
