package app.shop.adaptorsInterfaces.interfacesJPA;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.shop.adaptorsInterfaces.entity.Orcamento;

public interface IRepOrcamentoJPA extends JpaRepository<Orcamento, UUID> {
    List<Orcamento> findTopNByEfetivadoOrderByCreatedAtDesc(boolean efetivado, Pageable pageable);

    @Query("SELECT SUM(o.totalPagar) FROM Orcamento o WHERE o.cliente.id = :clienteId AND o.efetivado = true ORDER BY o.createdAt DESC")
    List<Double> findSumOfLastThreeEffectiveOrcamentos(@Param("clienteId") Long clienteId, Pageable pageable);

    @Query("SELECT count(o) FROM Orcamento o WHERE o.cliente.id = :clienteId AND o.createdAt >= :seisMesesAtras and o.efetivado = true")
    Integer getQuantidadeDeOrcamentosNosUltimosSeisMeses(@Param("clienteId") Long clienteId, @Param("seisMesesAtras") LocalDate seisMesesAtras);  

}
