package app.shop.dominio;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.shop.Interface.PedidoDto;

@Service
public class ServicoVendas {
    
    @Autowired
    private IRepOrcamento orcamentoRep;
    @Autowired
    private IRepItemPedido itemPedidoRep;

    public Orcamento geraOrcamento(PedidoDto pedidoDto, List<Produto> produtos){
        try {
            // descobre o id do último pedido se houve e soma 1
            int pedido = (int) (orcamentoRep.count() + 1);
            itemPedidoRep.saveAll(pedidoDto.itens, pedido);
            Orcamento orcamento = new Orcamento(pedidoDto, produtos, pedido);
            orcamentoRep.save(orcamento);
            return orcamento;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar orçamento");
        }
    }
}
