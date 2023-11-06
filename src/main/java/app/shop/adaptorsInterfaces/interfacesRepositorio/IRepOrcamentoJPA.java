package app.shop.adaptorsInterfaces.interfacesRepositorio;


import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import app.shop.adaptorsInterfaces.entity.Orcamento;

public interface IRepOrcamentoJPA extends JpaRepository<Orcamento, UUID> {
    List<Orcamento> findTopNByEfetivadoOrderByCreatedAtDesc(boolean efetivado, Pageable pageable);

}
