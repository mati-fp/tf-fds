package app.shop.aplicacao.relatorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.shop.dominio.dto.ItemPedidoDto;
import app.shop.dominio.model.ItemPedidoModel;
import app.shop.dominio.model.OrcamentoModel;
import app.shop.dominio.repositoryInterface.IRepItemPedido;
import app.shop.dominio.repositoryInterface.IRepOrcamento;

@Component
public class ServicoRelatorio {
    @Autowired
    private IRepOrcamento repOrcamento;
    @Autowired
    private IRepItemPedido repItemPedido;

    public List<Relatorio> gerarRelatorioUltimosOrcamentos(int n) {
        List<OrcamentoModel> orcamentos = repOrcamento.findTopNByEfetivadoOrderByCreatedAtDesc(n);

        return orcamentos.stream().map(orcamento -> {
            Relatorio dto = new Relatorio();
            dto.dataOrcamento = orcamento.getCreatedAt();
            dto.valorBrutoPedido = orcamento.getCustoPedido();
            dto.valorImposto = orcamento.getCustoImposto();
            dto.totalDesconto = orcamento.getDesconto();
            dto.valorPago = orcamento.getTotalPagar();
            return dto;
        }).collect(Collectors.toList());
    }

    public List<RelatorioProdutoDto> gerarRelatorioProdutosMaisVendidosNoAno(int ano){
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
        
    }
}
