package app.shop.dominio;


import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepOrcamento extends JpaRepository<Orcamento, UUID> {
    List<Orcamento> findTopNByEfetivadoOrderByCreatedAtDesc(boolean efetivado, Pageable pageable);

}
