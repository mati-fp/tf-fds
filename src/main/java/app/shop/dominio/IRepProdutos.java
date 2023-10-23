package app.shop.dominio;

import java.util.List;

public  interface IRepProdutos {
    void save(Produto p);
    List<Produto> all();
}

