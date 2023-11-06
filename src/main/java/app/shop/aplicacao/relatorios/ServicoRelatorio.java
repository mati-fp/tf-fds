package app.shop.aplicacao.relatorios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.shop.adaptorsInterfaces.repositorio.RepOrcamento;
import app.shop.dominio.Orcamento;

@Component
public class ServicoRelatorio {
    @Autowired
    private RepOrcamento repOrcamento;

    public List<Relatorio> gerarRelatorioUltimosOrcamentos(int n) {
        List<Orcamento> orcamentos = repOrcamento.findTopNByEfetivadoOrderByCreatedAtDesc(n);

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
