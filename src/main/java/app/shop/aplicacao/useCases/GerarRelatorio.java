package app.shop.aplicacao.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.shop.aplicacao.relatorios.Relatorio;
import app.shop.aplicacao.relatorios.ServicoRelatorio;

@Component
public class GerarRelatorio {
    
    @Autowired
    private ServicoRelatorio servicoRelatorio;

    public List<Relatorio> gerarRelatorioUltimosOrcamentos(int n) {
        return servicoRelatorio.gerarRelatorioUltimosOrcamentos(n);
    }
}
