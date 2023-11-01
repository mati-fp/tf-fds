package app.shop.Persistencia;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import app.shop.dominio.IRepProdutos;
import app.shop.dominio.Produto;

@Repository

public class RepProdutos {

    @Autowired
    private IRepProdutos repProdutos;
    
}
