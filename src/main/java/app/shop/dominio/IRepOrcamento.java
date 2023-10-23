package app.shop.dominio;

import app.shop.dominio.Orcamento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IRepOrcamento extends JpaRepository<Orcamento, Long> {
    @Query("SELECT o FROM Orcamento o WHERE o.efetivado = true ORDER BY o.data DESC")
    List<Orcamento> findLastEfetivadoOrçamentos();
}
