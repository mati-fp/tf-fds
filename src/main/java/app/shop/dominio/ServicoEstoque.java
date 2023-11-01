package app.shop.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.shop.adaptorsInterfaces.dto.ItemPedidoDto;
import app.shop.adaptorsInterfaces.interfacesRepositorio.IRepItemDeEstoque;
import app.shop.adaptorsInterfaces.interfacesRepositorio.IRepItemPedido;
import app.shop.adaptorsInterfaces.interfacesRepositorio.IRepProdutos;

@Service
public class ServicoEstoque {
    @Autowired
    private IRepProdutos produtosRep;
    @Autowired
    private IRepItemDeEstoque itemEstoqueRep;
    @Autowired
    private IRepItemPedido itemPedidoRep;

    public List<Produto> produtosDisponiveis(){
        return (List<Produto>) produtosRep.findAll();
    }

    public List<Produto> verificaProdutos(List<ItemPedidoDto> itens) {
        List<Produto> produtos = new ArrayList<>();
        
        for (ItemPedidoDto item : itens) {
            Optional<Produto> produtoOpt = produtosRep.findById(item.codigo_produto.longValue());
            if (produtoOpt.isEmpty()) {
                throw new RuntimeException("Produto não encontrado");
            }

            Produto produto = produtoOpt.get();
            Optional<ItemDeEstoque> itemDeEstoqueOpt = itemEstoqueRep.findByProduto(produto);

            if (itemDeEstoqueOpt.isEmpty() || itemDeEstoqueOpt.get().getQuantidadeAtual() < item.quantidade) {
                throw new RuntimeException("Quantidade insuficiente para o produto: " + produto.getDescricao());
            }

            produtos.add(produto);
        }

        return produtos;
    }

    public Boolean buscaProdutosPorNPedido(Orcamento pedido) {
        List<ItemPedido> itens = itemPedidoRep.findByOrcamento(pedido);
        for (ItemPedido item : itens) {
            Produto codigoProduto = produtosRep.findById(item.getProduto().getCodigo()).get();
            Optional<ItemDeEstoque> itemEstoqueOpt = itemEstoqueRep.findByProduto(codigoProduto);
            
            if (itemEstoqueOpt.isEmpty()) {
                return false;
            }
            ItemDeEstoque itemEstoque = itemEstoqueOpt.get();
            if (itemEstoque.getQuantidadeAtual() < item.getQuantidade()) {
                return false;
            }
            itemEstoque.setQuantidadeAtual(itemEstoque.getQuantidadeAtual() - item.getQuantidade());
            itemEstoqueRep.save(itemEstoque);
        }
        return true;
    }
    
    
}