package app.shop.dominio;

import java.util.List;

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
}