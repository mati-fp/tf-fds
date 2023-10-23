package app.shop.dominio;

import app.shop.dominio.Estoque;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IRepEstoque extends JpaRepository<Estoque, Integer> {
    @Query("SELECT o FROM Orcamento o WHERE o.efetivado = true ORDER BY o.data DESC")
    List<Orcamento> findLastEfetivadoOrçamentos();
}
