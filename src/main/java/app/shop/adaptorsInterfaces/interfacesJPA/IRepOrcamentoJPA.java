package app.shop.adaptorsInterfaces.interfacesJPA;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.shop.adaptorsInterfaces.entity.Orcamento;

public interface IRepOrcamentoJPA extends JpaRepository<Orcamento, UUID> {
    @Query("SELECT o FROM Orcamento o WHERE o.efetivado = :efetivado ORDER BY o.createdAt DESC")
    List<Orcamento> findTopNByEfetivadoOrderByCreatedAtDesc(@Param("efetivado") Integer efetivado, Pageable pageable);

    @Query("SELECT o.totalPagar FROM Orcamento o WHERE o.cliente.id = :clienteId AND o.efetivado = 1 ORDER BY o.createdAt DESC")
    List<Double> findSumOfLastThreeEffectiveOrcamentos(@Param("clienteId") Long clienteId, Pageable pageable);

    @Query("SELECT count(o) FROM Orcamento o WHERE o.cliente.id = :clienteId AND o.createdAt >= :seisMesesAtras and o.efetivado = 1")
    Integer getQuantidadeDeOrcamentosNosUltimosSeisMeses(@Param("clienteId") Long clienteId, @Param("seisMesesAtras") LocalDateTime seisMesesAtras);
    
    @Query("SELECT o FROM Orcamento o WHERE o.efetivado = :efetivado AND EXTRACT(YEAR FROM o.createdAt) = :ano")
    List<Orcamento> findByEfetivadoAndCreatedAtYear(@Param("efetivado") int efetivado, @Param("ano") int ano);

    List<Orcamento> findByClienteId(Long clienteId);

}
