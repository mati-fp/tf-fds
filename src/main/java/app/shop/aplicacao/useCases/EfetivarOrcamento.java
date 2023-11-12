package app.shop.aplicacao.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.shop.dominio.ServicoEstoque;
import app.shop.dominio.ServicoVendas;
import app.shop.dominio.model.OrcamentoModel;

@Component
public class EfetivarOrcamento {
    
    @Autowired
    private ServicoEstoque servicoEstoque;
    @Autowired
    private ServicoVendas servicoVendas;
        

    public String fazPagamento(String orcamentoId) {
        OrcamentoModel orcamento = servicoVendas.buscaOrcamento(orcamentoId);
        if (!orcamento.isValid()) {
            return "Orcamento expirado";
        }
        if (orcamento.getEfetivado() == 1) {
            return "Orcamento já foi efetivado anteriormente";
        }
        Boolean efetiva = servicoEstoque.buscaProdutosPorNPedido(orcamento);
        if (efetiva) {
            servicoVendas.setEfetivado(orcamento);
            return "Pagamento efetivado";
        }

        return "Não efetivado";
    }
}
