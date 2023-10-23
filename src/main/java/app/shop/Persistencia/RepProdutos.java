package app.shop.Persistencia;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import app.shop.dominio.IRepProdutos;
import app.shop.dominio.Produto;

@Repository
@Primary
public class RepProdutos implements IRepProdutos{

    @Autowired
    private IRepProdutos repProdutos;
    
    @Override
    public Produto save(Produto produto){
        Produto produtoSalvo = repProdutos.save(produto);
        return produtoSalvo;
    }

    @Override
    public List<Produto> findAll(){
        return repProdutos.findAll();
    }

    @Override
    public <S extends Produto> Iterable<S> saveAll(Iterable<S> entities) {
        List<S> produtosSalvos = (List<S>) repProdutos.saveAll(entities);
        return produtosSalvos;
    }

    @Override
    public Optional<Produto> findById(String id) {
        Optional<Produto> produto = repProdutos.findById(id);
        return produto;
    }

    @Override
    public boolean existsById(String id) {
        return repProdutos.existsById(id);
    }

    @Override
    public Iterable<Produto> findAllById(Iterable<String> ids) {
        Iterable<Produto> produtos = repProdutos.findAllById(ids);
        return produtos;
    }

    @Override
    public long count() {
        long i = repProdutos.count();
        return i;
    }

    @Override
    public void deleteById(String id) {
        Optional<Produto> p = repProdutos.findById(id);
        if(p.isPresent()){
            repProdutos.deleteById(id);
        }

    }

    @Override
    public void delete(Produto entity) {
        try {
            repProdutos.delete(entity);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void deleteAllById(Iterable<? extends String> ids) {
        try {
            repProdutos.deleteAllById(ids);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void deleteAll(Iterable<? extends Produto> entities) {
        try {
            repProdutos.deleteAll(entities);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void deleteAll() {
        try {
            repProdutos.deleteAll();
        } catch (Exception e) {
            throw e;
        }
    }
}
