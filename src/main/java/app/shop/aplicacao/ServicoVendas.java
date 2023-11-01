package app.shop.aplicacao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public Orcamento geraOrcamento(PedidoDto pedidoDto, List<Produto> produtos) {
        try {
            // Criando o orçamento inicial
            Orcamento orcamento = new Orcamento();
            orcamento.setNomeCliente(pedidoDto.nomeCliente);
            orcamentoRep.save(orcamento);
        
            // Lista para armazenar os itens do pedido
            List<ItemPedido> itens = new ArrayList<>();
        
            // Variável para armazenar o custo total do pedido
            double custoTotalPedido = 0;
        
            // Processamento dos itens do pedido
            for (ItemPedidoDto itemPedidoDto : pedidoDto.itens) {
                Produto produto = produtos.stream()
                    .filter(p -> p.getCodigo().equals(itemPedidoDto.codigo_produto))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
            
                double custoItem = produto.getPrecoUnitario() * itemPedidoDto.quantidade;
                custoTotalPedido += custoItem;
            
                ItemPedido itemPedido = new ItemPedido(produto, itemPedidoDto.quantidade, orcamento);
                itemPedidoRep.save(itemPedido);
                itens.add(itemPedido);
            }
        
            // Calculando imposto e desconto
            double imposto = custoTotalPedido * 0.10;
            double desconto = (itens.size() > 5) ? custoTotalPedido * 0.05 : 0;
        
            // Calculando total a pagar
            double totalPagar = custoTotalPedido + imposto - desconto;
        
            // Atualizando o orçamento com os valores calculados
            orcamento.setCustoPedido(custoTotalPedido);
            orcamento.setCustoImposto(imposto);
            orcamento.setDesconto(desconto);
            orcamento.setTotalPagar(totalPagar);
            orcamentoRep.save(orcamento);
        
            // Associando o orçamento a cada item do pedido
            itens.forEach(item -> {
                item.setOrcamento(orcamento);
                itemPedidoRep.save(item);
            });
        
            return orcamento;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar orçamento", e);
        }
    }

    public Orcamento buscaOrcamento(String orcamentoIdStr) {
        try {
            UUID orcamentoId = UUID.fromString(orcamentoIdStr);
            Orcamento orcamento = orcamentoRep.findById(orcamentoId).orElseThrow(() -> new RuntimeException("Orçamento não encontrado"));
            return orcamento;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("ID de orçamento inválido: " + orcamentoIdStr);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar orçamento: " + e.getMessage());
        }
    }

    public void setEfetivado(Orcamento orcamento){
        orcamento.setEfetivado(true);
        orcamentoRep.save(orcamento);
    }
}
