package app.shop.dominio.desconto;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.shop.dominio.model.OrcamentoModel;
import app.shop.dominio.repositoryInterface.IRepOrcamento;

@Component
public class DescontoPorQtdCompras implements DescontoStrategy{
    /* Clientes com mais de 10 compras nos últimos 6 meses 
    recebem desconto de 25%, indiferente do valor.*/

    @Autowired
    private IRepOrcamento orcamentoRepository;

    @Override
    public Double calcularDesconto(OrcamentoModel orcamentoModel, Integer qtdCompras) {
        Long clienteId = orcamentoModel.getCliente().getId();
        LocalDateTime seisMesesAtras = LocalDateTime.now().minusMonths(6);
        Integer qtdOrcamentos = orcamentoRepository.getQuantidadeDeOrcamentosNosUltimosSeisMeses(clienteId, seisMesesAtras);
        if (qtdOrcamentos >= 10) {
            return 0.25;
        } else {
            return 0.0;
        }
    }
}
