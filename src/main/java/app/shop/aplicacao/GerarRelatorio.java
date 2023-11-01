package app.shop.aplicacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GerarRelatorio {
    
    @Autowired
    private ServicoRelatorio servicoRelatorio;

    public List<Relatorio> gerarRelatorioUltimosOrcamentos(int n) {
        return servicoRelatorio.gerarRelatorioUltimosOrcamentos(n);
    }
}
