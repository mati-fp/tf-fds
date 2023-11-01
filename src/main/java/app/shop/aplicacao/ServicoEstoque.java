package app.shop.aplicacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.shop.Interface.ItemPedidoDto;
import app.shop.Interface.ProdutoDto;
import app.shop.dominio.IRepItemDeEstoque;
import app.shop.dominio.IRepItemPedido;
import app.shop.dominio.IRepProdutos;
import app.shop.dominio.ItemDeEstoque;
import app.shop.dominio.ItemPedido;
import app.shop.dominio.Orcamento;
import app.shop.dominio.Produto;

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

    public Produto salvaProdutoNovo(ProdutoDto produto){
        Produto produtoSalvo = new Produto();
        produtoSalvo.setDescricao(produto.descricao);
        produtoSalvo.setPrecoUnitario(produto.precoUnitario);
        produtoSalvo = produtosRep.save(produtoSalvo);

        ItemDeEstoque itemEstoque = new ItemDeEstoque();
        itemEstoque.setProduto(produtoSalvo);
        itemEstoque.setQuantidadeAtual(1000);
        itemEstoque.setEstoqueMinimo(50);
        itemEstoque.setEstoqueMaximo(5000);
        itemEstoqueRep.save(itemEstoque);
        
        return produtoSalvo;
    }

     public List<Produto> verificaProdutos(List<ItemPedidoDto> itens) {
        List<Produto> produtos = new ArrayList<>();
        
        for (ItemPedidoDto item : itens) {
            Optional<Produto> produtoOpt = produtosRep.findById(item.codigo_produto.longValue());
            if (produtoOpt.isEmpty()) {
                throw new RuntimeException("Produto não encontrado");
            }

            Produto produto = produtoOpt.get();
            Optional<ItemDeEstoque> itemDeEstoqueOpt = itemEstoqueRep.findByCodProduto(produto.getCodigo());

            if (itemDeEstoqueOpt.isEmpty() || itemDeEstoqueOpt.get().getQuantidadeAtual() < item.quantidade) {
                throw new RuntimeException("Quantidade insuficiente para o produto: " + produto.getDescricao());
            }

            produtos.add(produto);
        }

        return produtos;
    }

    public Boolean buscaProdutosPorNPedido(Orcamento pedido) {
        List<ItemPedido> itens = itemPedidoRep.findByPedidoId(pedido.getPedidoId());
        for (ItemPedido item : itens) {
            Long codigoProduto = item.getProduto().getCodigo();
            Optional<ItemDeEstoque> itemEstoqueOpt = itemEstoqueRep.findByCodProduto(codigoProduto);
            
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