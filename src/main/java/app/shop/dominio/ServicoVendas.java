package app.shop.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.shop.dominio.dto.ItemPedidoDto;
import app.shop.dominio.dto.PedidoDto;
import app.shop.dominio.model.ItemPedidoModel;
import app.shop.dominio.model.OrcamentoModel;
import app.shop.dominio.model.ProdutoModel;
import app.shop.dominio.repositoryInterface.IRepItemPedido;
import app.shop.dominio.repositoryInterface.IRepOrcamento;

@Service
public class ServicoVendas {
    
    @Autowired
    private IRepOrcamento orcamentoRep;
    @Autowired
    private IRepItemPedido itemPedidoRep;

    public OrcamentoModel geraOrcamento(PedidoDto pedidoDto, List<ProdutoModel> produtos) {
        try {
            // Criando o orçamento inicial
            OrcamentoModel orcamento = new OrcamentoModel();
            orcamento.setNomeCliente(pedidoDto.nomeCliente);
            orcamento = orcamentoRep.save(orcamento);
        
            // Lista para armazenar os itens do pedido
            List<ItemPedidoModel> itens = new ArrayList<>();
        
            // Variável para armazenar o custo total do pedido
            double custoTotalPedido = 0;
        
            // Processamento dos itens do pedido
            for (ItemPedidoDto itemPedidoDto : pedidoDto.itens) {
                ProdutoModel produto = produtos.stream()
                    .filter(p -> p.getCodigo().equals(itemPedidoDto.codigo_produto))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
            
                double custoItem = produto.getPrecoUnitario() * itemPedidoDto.quantidade;
                custoTotalPedido += custoItem;
            
                ItemPedidoModel itemPedido = new ItemPedidoModel(produto, itemPedidoDto.quantidade, orcamento);
                itemPedido = itemPedidoRep.save(itemPedido);
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
            orcamento = orcamentoRep.save(orcamento);

            // Associando o orçamento a cada item do pedido
            for (ItemPedidoModel item : itens) {
                ItemPedidoModel currentItem = item;
                currentItem.setOrcamento(orcamento);
                itemPedidoRep.save(currentItem);
            }
        
            return orcamento;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar orçamento", e);
        }
    }

    public OrcamentoModel buscaOrcamento(String orcamentoIdStr) {
        try {
            UUID orcamentoId = UUID.fromString(orcamentoIdStr);
            OrcamentoModel orcamento = orcamentoRep.findById(orcamentoId);
            return orcamento;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("ID de orçamento inválido: " + orcamentoIdStr);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar orçamento: " + e.getMessage());
        }
    }

    public void setEfetivado(OrcamentoModel orcamento){
        orcamento.setEfetivado(true);
        orcamentoRep.save(orcamento);
    }
}
