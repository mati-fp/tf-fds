package app.shop.aplicacao.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.shop.aplicacao.relatorios.RelatorioOrcamentosDto;
import app.shop.aplicacao.relatorios.RelatorioProdutoDto;
import app.shop.aplicacao.relatorios.ServicoRelatorio;

@Component
public class GerarRelatorio {
    
    @Autowired
    private ServicoRelatorio servicoRelatorio;

    public List<RelatorioOrcamentosDto> gerarRelatorioUltimosOrcamentos(int n) {
        return servicoRelatorio.gerarRelatorioUltimosOrcamentos(n);
    }

    public List<RelatorioProdutoDto> gerarRelatorioProdutosMaisVendidosNoAno(int ano){
        return servicoRelatorio.gerarRelatorioProdutosVendidosNoAno(ano, true);
    }

    public List<RelatorioProdutoDto> gerarRelatorioProdutosMenosVendidosNoAno(int ano){
        return servicoRelatorio.gerarRelatorioProdutosVendidosNoAno(ano, false);
    }

    public List<RelatorioProdutoDto> gerarRelatorioProdutosMaisCompradosPeloCliente(Long id) {
        return servicoRelatorio.gerarRelatorioProdutosMaisCompradosPeloCliente(id);
    }
}
