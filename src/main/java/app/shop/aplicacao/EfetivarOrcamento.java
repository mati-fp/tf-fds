package app.shop.aplicacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.shop.dominio.ItemPedido;
import app.shop.dominio.Orcamento;
import app.shop.dominio.ServicoEstoque;
import app.shop.dominio.ServicoVendas;

@Component
public class EfetivarOrcamento {
    
    @Autowired
    private ServicoEstoque servicoEstoque;
    @Autowired
    private ServicoVendas servicoVendas;
        

    public String fazPagamento(String orcamentoId) {
        Orcamento orcamento = servicoVendas.buscaOrcamento(orcamentoId);
        Boolean efetiva = servicoEstoque.buscaProdutosPorNPedido(orcamento);
        if (efetiva) {
            orcamento.setEfetivado(true);
            return "Pagamento efetivado";
        }

        return "Não efetivado";
    }
}
