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
        

    public String fazPagamento(String orcamentoId) throws RuntimeException{
        OrcamentoModel orcamento = servicoVendas.buscaOrcamento(orcamentoId);
        if (!orcamento.isValid()) {
            throw new RuntimeException("Orcamento expirado");
        }
        if (orcamento.getEfetivado() == 1) {
            throw new RuntimeException("Orcamento já foi efetivado anteriormente");
        }
        Boolean efetiva = servicoEstoque.buscaProdutosPorNPedido(orcamento);
        if (efetiva) {
            servicoVendas.setEfetivado(orcamento);
            return "Pagamento efetivado";
        }

        throw new RuntimeException("Não efetivado");
    }
}
