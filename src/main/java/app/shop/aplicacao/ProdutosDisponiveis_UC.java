package app.shop.aplicacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.shop.Interface.ProdutoDto;
import app.shop.dominio.Produto;
import app.shop.dominio.ServicoEstoque;

@Component
public class ProdutosDisponiveis_UC {
    @Autowired
    private ServicoEstoque servicoEstoque;

    public Produto salvaProdutoNovo(ProdutoDto produto){
        Produto produtoSalvo =  servicoEstoque.salvaProdutoNovo(produto);
        return produtoSalvo;
    }

    public List<Produto> prodDisponiveis(){
        return servicoEstoque.produtosDisponiveis();
    }

}
