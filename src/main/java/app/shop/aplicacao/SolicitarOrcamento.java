package app.shop.aplicacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.shop.Interface.PedidoDto;
import app.shop.dominio.Orcamento;
import app.shop.dominio.Produto;
import app.shop.dominio.ServicoEstoque;
import app.shop.dominio.ServicoVendas;

@Component
public class SolicitarOrcamento {

    @Autowired
    private ServicoEstoque servicoEstoque;
    @Autowired
    private ServicoVendas servicoVendas;

    public Orcamento fazPedido(PedidoDto pedidoDto){
        List<Produto> produtos = servicoEstoque.verificaProdutos(pedidoDto.itens);
        if(produtos.size() == pedidoDto.itens.size()){
            Orcamento orcamento = servicoVendas.geraOrcamento(pedidoDto, produtos);
            return orcamento;
        }

        throw new RuntimeException("Não tem produtos suficientes");
        
    }
    
}
