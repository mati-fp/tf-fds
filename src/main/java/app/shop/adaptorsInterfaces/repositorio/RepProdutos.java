package app.shop.adaptorsInterfaces.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import app.shop.adaptorsInterfaces.entity.Produto;
import app.shop.adaptorsInterfaces.interfacesJPA.IRepProdutosJPA;
import app.shop.dominio.model.ProdutoModel;
import app.shop.dominio.repositoryInterface.IRepProduto;

@Repository
@Primary
public class RepProdutos implements IRepProduto{

    @Autowired
    private IRepProdutosJPA repProdutos;

    public List<ProdutoModel> findAll(){
        List<Produto> produtosEntity = (List<Produto>) repProdutos.findAll();
        
        List<ProdutoModel> produtosModel = List.of();

        for (Produto produto : produtosEntity) {
            produtosModel.add(ModelEntityMapper.produtoToModel(produto));
        }

        return produtosModel;
    }
    
    public Optional<ProdutoModel> findById(Long id){
        Optional<Produto> produtoEntity = repProdutos.findById(id);

        return Optional.of(ModelEntityMapper.produtoToModel(produtoEntity.get()));
    }
}
