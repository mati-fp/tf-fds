package app.shop.aplicacao.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.shop.adaptorsInterfaces.entity.Produto;
import app.shop.dominio.ServicoEstoque;

@Component
public class ProdutosDisponiveis {
    @Autowired
    private ServicoEstoque servicoEstoque;


    public List<Produto> prodDisponiveis(){
        return servicoEstoque.produtosDisponiveis();
    }

}
