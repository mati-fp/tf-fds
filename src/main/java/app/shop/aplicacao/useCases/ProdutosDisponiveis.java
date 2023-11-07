package app.shop.aplicacao.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.shop.dominio.ServicoEstoque;
import app.shop.dominio.model.ProdutoModel;

@Component
public class ProdutosDisponiveis {
    @Autowired
    private ServicoEstoque servicoEstoque;


    public List<ProdutoModel> prodDisponiveis(){
        return servicoEstoque.produtosDisponiveis();
    }

}
