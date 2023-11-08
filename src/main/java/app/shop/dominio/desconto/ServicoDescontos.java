package app.shop.dominio.desconto;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.shop.dominio.model.OrcamentoModel;

@Service
public class ServicoDescontos {

    private final List<DescontoStrategy> descontoStrategies;

    @Autowired
    public ServicoDescontos(List<DescontoStrategy> descontoStrategies) {
        this.descontoStrategies = descontoStrategies;
    }

    public Double calcularDesconto(OrcamentoModel orcamentoModel, Integer qtdCompras) {
        return descontoStrategies.stream()
            .map(desconto -> desconto.calcularDesconto(orcamentoModel, qtdCompras))
            .max(Comparator.naturalOrder())
            .orElse(0.0);
    }
    
}
