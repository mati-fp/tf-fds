package app.shop.aplicacao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import app.shop.dominio.IRepOrcamento;
import app.shop.dominio.Orcamento;

@Component
public class ServicoRelatorio {
    @Autowired
    private IRepOrcamento repOrcamento;

    public List<Relatorio> gerarRelatorioUltimosOrcamentos(int n) {
        Pageable limit = PageRequest.of(0, n);
        List<Orcamento> orcamentos = repOrcamento.findTopNByEfetivadoOrderByDataOrcamentoDesc(true, limit);

        return orcamentos.stream().map(orcamento -> {
            Relatorio dto = new Relatorio();
            dto.dataOrcamento = orcamento.getCreatedAt();
            dto.valorBrutoPedido = orcamento.getCustoPedido();
            dto.valorImposto = orcamento.getCustoImposto();
            dto.totalDesconto = orcamento.getDesconto();
            dto.valorPago = orcamento.getTotalPagar();
            return dto;
        }).collect(Collectors.toList());
    }
}
