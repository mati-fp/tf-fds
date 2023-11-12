package app.shop.aplicacao.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.shop.dominio.ServicoEstoque;
import app.shop.dominio.ServicoVendas;
import app.shop.dominio.dto.OrcamentoDto;
import app.shop.dominio.dto.PedidoDto;
import app.shop.dominio.model.OrcamentoModel;
import app.shop.dominio.model.ProdutoModel;

@Component
public class SolicitarOrcamento {

    @Autowired
    private ServicoEstoque servicoEstoque;
    @Autowired
    private ServicoVendas servicoVendas;

    public OrcamentoDto fazPedido(PedidoDto pedidoDto){
        try {
            List<ProdutoModel> produtos = servicoEstoque.verificaProdutos(pedidoDto.itens);
            if(produtos.size() == pedidoDto.itens.size()){
                OrcamentoModel orcamentoModel = servicoVendas.geraOrcamento(pedidoDto, produtos);
                OrcamentoDto orcamento = new OrcamentoDto(
                    orcamentoModel.getId(), orcamentoModel.getCustoPedido(), 
                    orcamentoModel.getCustoImposto(), orcamentoModel.getDesconto(), 
                    orcamentoModel.getTotalPagar(), orcamentoModel.getEfetivado(), 
                    orcamentoModel.getCreatedAt(), orcamentoModel.getUpdatedAt()
                );
                return orcamento;
            }
        } catch (Exception e) {
            throw e;
        }
        throw new RuntimeException("Não tem produtos suficientes");
        
    }
    
}
