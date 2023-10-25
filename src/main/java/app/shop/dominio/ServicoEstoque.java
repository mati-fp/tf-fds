package app.shop.dominio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoEstoque {
    private IRepProdutos produtosRep;

    @Autowired
    public ServicoEstoque(IRepProdutos produtosRep){
        this.produtosRep = produtosRep;
    }

    public List<Produto> produtosDisponiveis(){
        return produtosRep.findAll();
    }

    public Produto salvaProdutoNovo(Produto produto){
        Produto produtoSalvo = produtosRep.save(produto);
        return produtoSalvo;
    }

    public List<Produto> verificaProdutos(List<ItemPedido> itens){
        List<Produto> produtos = List.of();
        for(ItemPedido item : itens){
            Optional<Produto> produto = produtosRep.findById(item.getCodProduto().toString());
            if(produto.isEmpty()){
                throw new RuntimeException("Produto não encontrado");
            }
            if(produto.get().getQuantidadeAtual() < item.getQuantidade()){
                throw new RuntimeException("Quantidade insuficiente");
            }
            produtos.add(produto.get());
        }
        return produtos;
    }
}