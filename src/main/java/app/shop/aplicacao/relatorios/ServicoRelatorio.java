package app.shop.aplicacao.relatorios;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.shop.dominio.dto.ItemPedidoDto;
import app.shop.dominio.model.ItemPedidoModel;
import app.shop.dominio.model.OrcamentoModel;
import app.shop.dominio.model.ProdutoModel;
import app.shop.dominio.repositoryInterface.IRepItemPedido;
import app.shop.dominio.repositoryInterface.IRepOrcamento;
import app.shop.dominio.repositoryInterface.IRepProduto;

@Service
public class ServicoRelatorio {
    @Autowired
    private IRepOrcamento repOrcamento;
    @Autowired
    private IRepItemPedido repItemPedido;
    @Autowired
    private IRepProduto repProduto;

    public List<RelatorioOrcamentosDto> gerarRelatorioUltimosOrcamentos(int n) {
        List<OrcamentoModel> orcamentos = repOrcamento.findTopNByEfetivadoOrderByCreatedAtDesc(n);

        return orcamentos.stream().map(orcamento -> {
            RelatorioOrcamentosDto dto = new RelatorioOrcamentosDto();
            dto.dataOrcamento = orcamento.getCreatedAt();
            dto.valorBrutoPedido = orcamento.getCustoPedido();
            dto.valorImposto = orcamento.getCustoImposto();
            dto.totalDesconto = orcamento.getDesconto();
            dto.valorPago = orcamento.getTotalPagar();
            return dto;
        }).collect(Collectors.toList());
    }

    public List<RelatorioProdutoDto> gerarRelatorioProdutosVendidosNoAno(int ano, boolean maisVendidos) {
        List<OrcamentoModel> orcamentos = repOrcamento.findByEfetivadoAndCreatedAtYear(ano);

        //Busca agora em itens_pedido qual os 10 mais vendidos
        List<ItemPedidoDto> itens = new ArrayList<>();
        for (OrcamentoModel orcamento : orcamentos) {
            List<ItemPedidoModel> itensNoOrcamento = new ArrayList<>();
            itensNoOrcamento = repItemPedido.findByOrcamento(orcamento);
            for (ItemPedidoModel item : itensNoOrcamento) {
                Boolean itemNExiste = true;
                for (ItemPedidoDto itemDto : itens) {
                    if (itemDto.codigoProduto == item.getProduto().getCodigo()) {
                        itemDto.quantidade += item.getQuantidade();
                        itemNExiste = false;
                        break;
                    }
                }
                if (itemNExiste) {
                    itens.add(new ItemPedidoDto(item.getProduto().getCodigo(), item.getQuantidade()));
                }
            }
        }
        if (maisVendidos) { // Se for true retorna no final os itens mais vendidos
            itens = itens.stream()
                .sorted(Comparator.comparing(ItemPedidoDto::getQuantidade).reversed())
                .limit(10)
                .collect(Collectors.toList());
        } else { // Ser for false retorna os produtos menos vendidos
            itens = itens.stream()
                .sorted(Comparator.comparing(ItemPedidoDto::getQuantidade))
                .limit(10)
                .collect(Collectors.toList());
        }

        List<RelatorioProdutoDto> relatorio = new ArrayList<>();
        for (ItemPedidoDto item : itens) {
            ProdutoModel produto = repProduto.findById(item.codigoProduto);
            RelatorioProdutoDto relatorioProdutoDto = new RelatorioProdutoDto(
                produto.getCodigo(),
                produto.getDescricao(),
                produto.getPrecoUnitario(),
                item.quantidade
            );
            relatorio.add(relatorioProdutoDto);
        }
        
        return relatorio;
    }

    public List<RelatorioProdutoDto> gerarRelatorioProdutosMaisCompradosPeloCliente(Long id) {
        List<OrcamentoModel> orcamentos = repOrcamento.findByClienteId(id);

        //Busca agora em itens_pedido qual os 10 mais vendidos
        List<ItemPedidoDto> itens = new ArrayList<>();
        for (OrcamentoModel orcamento : orcamentos) {
            List<ItemPedidoModel> itensNoOrcamento = new ArrayList<>();
            itensNoOrcamento = repItemPedido.findByOrcamento(orcamento);
            for (ItemPedidoModel item : itensNoOrcamento) {
                Boolean itemNExiste = true;
                for (ItemPedidoDto itemDto : itens) {
                    if (itemDto.codigoProduto == item.getProduto().getCodigo()) {
                        itemDto.quantidade += item.getQuantidade();
                        itemNExiste = false;
                        break;
                    }
                }
                if (itemNExiste) {
                    itens.add(new ItemPedidoDto(item.getProduto().getCodigo(), item.getQuantidade()));
                }
            }
        }
        itens = itens.stream()
            .sorted(Comparator.comparing(ItemPedidoDto::getQuantidade).reversed())
            .limit(20)
            .collect(Collectors.toList());

        List<RelatorioProdutoDto> relatorio = new ArrayList<>();
        for (ItemPedidoDto item : itens) {
            ProdutoModel produto = repProduto.findById(item.codigoProduto);
            RelatorioProdutoDto relatorioProdutoDto = new RelatorioProdutoDto(
                produto.getCodigo(),
                produto.getDescricao(),
                produto.getPrecoUnitario(),
                item.quantidade
            );
            relatorio.add(relatorioProdutoDto);
        }
        
        return relatorio;
    }
}
