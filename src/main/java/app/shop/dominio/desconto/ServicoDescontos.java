package app.shop.dominio.desconto;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.shop.dominio.model.OrcamentoModel;

@Service
public class ServicoDescontos {

    @Autowired
    private final List<DescontoStrategy> descontoStrategies;
    public ServicoDescontos(List<DescontoStrategy> descontoStrategies) {
        this.descontoStrategies = descontoStrategies;
    }

    public Double calcularDesconto(OrcamentoModel orcamentoModel, Integer qtdCompras) {
        Double descontoRetornar =  descontoStrategies.stream()
            .map(desconto -> desconto.calcularDesconto(orcamentoModel, qtdCompras))
            .max(Comparator.naturalOrder())
            .orElse(0.0);

        return descontoRetornar;
    }
    
}
