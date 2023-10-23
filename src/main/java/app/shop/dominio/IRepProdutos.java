package app.shop.dominio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IRepProdutos extends JpaRepository<Produto, String>{
    @Query("SELECT p FROM Produto p JOIN Estoque e ON p.codigo = e.produto.codigo WHERE e.quantidadeAtual > 0")
    List<Produto> findAllAvailableInStock();
}


