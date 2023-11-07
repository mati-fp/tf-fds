package app.shop.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        
        for (ItemPedidoDto item : itens) {
            Optional<ProdutoModel> produtoOpt = produtosRep.findById(item.codigo_produto);
            if (produtoOpt.isEmpty()) {
                throw new RuntimeException("Produto não encontrado");
            }

            ProdutoModel produto = produtoOpt.get();
            Optional<ItemDeEstoqueModel> itemDeEstoqueOpt = itemEstoqueRep.findByProduto(produto);

            if (itemDeEstoqueOpt.isEmpty() || itemDeEstoqueOpt.get().getQuantidadeAtual() < item.quantidade) {
                throw new RuntimeException("Quantidade insuficiente para o produto: " + produto.getDescricao());
            }

            produtos.add(produto);
        }

        return produtos;
    }

    public Boolean buscaProdutosPorNPedido(OrcamentoModel pedido) {
        List<ItemPedidoModel> itens = itemPedidoRep.findByOrcamento(pedido);
        for (ItemPedidoModel item : itens) {
            ProdutoModel codigoProduto = produtosRep.findById(item.getProduto().getCodigo()).get();
            Optional<ItemDeEstoqueModel> itemEstoqueOpt = itemEstoqueRep.findByProduto(codigoProduto);
            
            if (itemEstoqueOpt.isEmpty()) {
                return false;
            }
            ItemDeEstoqueModel itemEstoque = itemEstoqueOpt.get();
            if (itemEstoque.getQuantidadeAtual() < item.getQuantidade()) {
                return false;
            }
            itemEstoque.setQuantidadeAtual(itemEstoque.getQuantidadeAtual() - item.getQuantidade());
            itemEstoqueRep.save(itemEstoque);
        }
        return true;
    }
    
    
}