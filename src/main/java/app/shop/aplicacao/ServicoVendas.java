package app.shop.aplicacao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.shop.Interface.ItemPedidoDto;
import app.shop.Interface.PedidoDto;
import app.shop.dominio.IRepItemPedido;
import app.shop.dominio.IRepOrcamento;
import app.shop.dominio.ItemPedido;
import app.shop.dominio.Orcamento;
import app.shop.dominio.Produto;

@Service
public class ServicoVendas {
    
    @Autowired
    private IRepOrcamento orcamentoRep;
    @Autowired
    private IRepItemPedido itemPedidoRep;

    // public Orcamento geraOrcamento(PedidoDto pedidoDto, List<Produto> produtos){
    //     try {
    //         int pedido = (int) (orcamentoRep.count() + 1);
    //         List<ItemPedido> itens = List.of();
    //         for (ItemPedidoDto itemPedidoDto : pedidoDto.itens) {
    //             Produto produto = produtos.stream()
    //                 .filter(p -> p.getCodigo() == itemPedidoDto.codigo_produto)
    //                 .findFirst()
    //                 .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    //             ItemPedido itemPedido = new ItemPedido(produto, itemPedidoDto.quantidade);
    //             itemPedidoRep.save(itemPedido);
    //             itens.add(itemPedido);
    //         }
    //         Orcamento orcamento = new Orcamento(pedidoDto, produtos, pedido);
    //         orcamentoRep.save(orcamento);
    //         itens.forEach(item -> {
    //             item.setOrcamento(orcamento);
    //             itemPedidoRep.save(item);
    //         });
    //         return orcamento;
    //     } catch (Exception e) {
    //         throw new RuntimeException("Erro ao gerar orçamento");
    //     }
    // }

    // public Orcamento buscaOrcamento(String orcamentoId) {
    //     try {
    //         //int id = Integer.parseInt(orcamentoId);
    //         Orcamento orcamento = orcamentoRep.findById(orcamentoId).orElseThrow(() -> new RuntimeException("Orcamento não encontrado"));
    //         return orcamento;
    //     } catch (Exception e) {
    //         throw new RuntimeException("Erro ao buscar orçamento");
    //     }
    // }
}
