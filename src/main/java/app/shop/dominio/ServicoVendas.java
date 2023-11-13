package app.shop.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.shop.dominio.desconto.ServicoDescontos;
import app.shop.dominio.dto.ItemPedidoDto;
import app.shop.dominio.dto.PedidoDto;
import app.shop.dominio.model.ClienteModel;
import app.shop.dominio.model.ItemPedidoModel;
import app.shop.dominio.model.OrcamentoModel;
import app.shop.dominio.model.ProdutoModel;
import app.shop.dominio.repositoryInterface.IRepCliente;
import app.shop.dominio.repositoryInterface.IRepItemPedido;
import app.shop.dominio.repositoryInterface.IRepOrcamento;

@Service
public class ServicoVendas {
    
    @Autowired
    private IRepOrcamento orcamentoRep;
    @Autowired
    private IRepItemPedido itemPedidoRep;
    @Autowired
    private IRepCliente clienteRep;
    @Autowired
    private ServicoDescontos servicoDescontos;

    public OrcamentoModel geraOrcamento(PedidoDto pedidoDto, List<ProdutoModel> produtos) {
        try {
            // Criando o orçamento inicial
            OrcamentoModel orcamento = new OrcamentoModel();
            ClienteModel clienteModel = clienteRep.findOrCreateByName(pedidoDto.nomeCliente);
            orcamento.setCliente(clienteModel);
            orcamento = orcamentoRep.save(orcamento);

            // Lista para armazenar os itens do pedido
            List<ItemPedidoModel> itens = new ArrayList<>();
        
            // Variável para armazenar o custo total do pedido
            double custoTotalPedido = 0;
        
            // Processamento dos itens do pedido
            for (ItemPedidoDto itemPedidoDto : pedidoDto.itens) {
                ProdutoModel produto = produtos.stream()
                    .filter(p -> p.getCodigo().equals(itemPedidoDto.codigoProduto))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
            
                double custoItem = produto.getPrecoUnitario() * itemPedidoDto.quantidade;
                custoTotalPedido += custoItem;
            
                ItemPedidoModel itemPedido = new ItemPedidoModel(produto, itemPedidoDto.quantidade, orcamento);
                itemPedido = itemPedidoRep.save(itemPedido);
                itens.add(itemPedido);
            }
        
            // Calculando o valor do imposto
            Double imposto = custoTotalPedido * 0.10;
            orcamento.setCustoPedido(custoTotalPedido);
            orcamento.setCustoImposto(imposto);
            // Calcula o valor do desconto
            Double desconto = servicoDescontos.calcularDesconto(orcamento, itens.size());
            orcamento.setDesconto(desconto);
            // Calcula o valor total a pagar
            Double totalPagar = custoTotalPedido + imposto - desconto;
            orcamento.setTotalPagar(totalPagar);
            // Salva o orçamento
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
        orcamento.setEfetivado(1);
        orcamentoRep.save(orcamento);
    }
}
