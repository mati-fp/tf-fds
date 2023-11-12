package app.shop.aplicacao.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.shop.dominio.ServicoEstoque;
import app.shop.dominio.ServicoVendas;
import app.shop.dominio.dto.PedidoDto;
import app.shop.dominio.model.OrcamentoModel;
import app.shop.dominio.model.ProdutoModel;

@Component
public class SolicitarOrcamento {

    @Autowired
    private ServicoEstoque servicoEstoque;
    @Autowired
    private ServicoVendas servicoVendas;

    public OrcamentoModel fazPedido(PedidoDto pedidoDto){
        try {
            List<ProdutoModel> produtos = servicoEstoque.verificaProdutos(pedidoDto.itens);
            if(produtos.size() == pedidoDto.itens.size()){
                OrcamentoModel orcamento = servicoVendas.geraOrcamento(pedidoDto, produtos);
                return orcamento;
            }
        } catch (Exception e) {
            throw e;
        }
        throw new RuntimeException("Não tem produtos suficientes");
        
    }
    
}
