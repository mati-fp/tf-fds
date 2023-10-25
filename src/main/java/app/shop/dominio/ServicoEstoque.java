package app.shop.dominio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.shop.Interface.ItemPedidoDto;
import app.shop.Interface.ProdutoDto;

@Service
public class ServicoEstoque {
    @Autowired
    private IRepProdutos produtosRep;
    @Autowired
    private IRepItemPedido itemPedidoRep;

    public List<Produto> produtosDisponiveis(){
        return produtosRep.findAll();
    }

    public Produto salvaProdutoNovo(ProdutoDto produto){
        Produto produtoSalvo = new Produto();
        produtoSalvo.setDescricao(produto.descricao);
        produtoSalvo.setPreco(produto.precoUnitario);
        produtoSalvo.setQuantidadeAtual(produto.quantidadeAtual);
        produtoSalvo.setQuantidadeMaxima(produto.quantidadeMaxima);
        produtoSalvo.setQuantidadeMinima(produto.quantidadeMinima);
        produtoSalvo = produtosRep.save(produtoSalvo);
        
        return produtoSalvo;
    }

    public List<Produto> verificaProdutos(List<ItemPedidoDto> itens){
        List<Produto> produtos = List.of();
        for(ItemPedidoDto item : itens){
            Optional<Produto> produto = produtosRep.findById(item.codigo_produto);
            if(produto.isEmpty()){
                throw new RuntimeException("Produto não encontrado");
            }
            if(produto.get().getQuantidadeAtual() < item.quantidade){
                throw new RuntimeException("Quantidade insuficiente");
            }
            produtos.add(produto.get());
        }
        return produtos;
    }

    public Boolean buscaProdutosPorNPedido(Orcamento pedido) {
        List<ItemPedido> itens = (List<ItemPedido>) itemPedidoRep.findAllById((Iterable<Orcamento>) pedido);
        List<Produto> produtos = List.of();
        for(ItemPedido item : itens){
            Optional<Produto> produto = produtosRep.findById(item.getProduto().getCodigo());
            if(produto.isEmpty()){
                return false;
            }
            if(produto.get().getQuantidadeAtual() < item.getQuantidade()){
                return false;
            }
            produto.get().setQuantidadeAtual(produto.get().getQuantidadeAtual() - item.getQuantidade());
            produtos.add(produto.get());
            
        }
        produtosRep.saveAll(produtos);
        return true;
    }
}