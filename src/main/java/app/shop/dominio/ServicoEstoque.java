package app.shop.dominio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.shop.dominio.dto.ItemPedidoDto;
import app.shop.dominio.model.ItemDeEstoqueModel;
import app.shop.dominio.model.ItemPedidoModel;
import app.shop.dominio.model.OrcamentoModel;
import app.shop.dominio.model.ProdutoModel;
import app.shop.dominio.repositoryInterface.IRepItemDeEstoque;
import app.shop.dominio.repositoryInterface.IRepItemPedido;
import app.shop.dominio.repositoryInterface.IRepProduto;

@Service
public class ServicoEstoque {
    @Autowired
    private IRepProduto produtosRep;
    @Autowired
    private IRepItemDeEstoque itemEstoqueRep;
    @Autowired
    private IRepItemPedido itemPedidoRep;

    public List<ProdutoModel> produtosDisponiveis(){
        return produtosRep.findAll();
    }

    public List<ProdutoModel> verificaProdutos(List<ItemPedidoDto> itens) {
        List<ProdutoModel> produtos = new ArrayList<>();
        try {
            for (ItemPedidoDto item : itens) {
                ProdutoModel produto = produtosRep.findById(item.codigoProduto);

                ItemDeEstoqueModel itemDeEstoqueOpt = itemEstoqueRep.findByProduto(produto);

                if (itemDeEstoqueOpt.getQuantidadeAtual() < item.quantidade) {
                    throw new RuntimeException("Quantidade insuficiente para o produto: " + produto.getDescricao());
                }

                produtos.add(produto);
            }
        } catch (Exception e) {
            throw e;
        }

        return produtos;
    }

    public Boolean buscaProdutosPorNPedido(OrcamentoModel orcamento) {
        try {
            List<ItemPedidoModel> itens = itemPedidoRep.findByOrcamento(orcamento);
            List<ItemDeEstoqueModel> itensEstoque = new ArrayList<>();
            for (ItemPedidoModel item : itens) {
                ProdutoModel codigoProduto = produtosRep.findById(item.getProduto().getCodigo());
                itensEstoque.add(itemEstoqueRep.findByProduto(codigoProduto));

                if (itensEstoque.get(itensEstoque.size() - 1).getQuantidadeAtual() < item.getQuantidade()) {
                    return false;
                }

                Integer qtdAtual = itensEstoque.get(itensEstoque.size() - 1).getQuantidadeAtual();
                itensEstoque.get(itensEstoque.size() - 1).setQuantidadeAtual(qtdAtual - item.getQuantidade());
            }

            for (ItemDeEstoqueModel item : itensEstoque) {
                itemEstoqueRep.save(item);
            }
            
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
}