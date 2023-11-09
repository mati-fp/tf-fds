package app.shop.dominio.repositoryInterface;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import app.shop.dominio.model.OrcamentoModel;


public interface IRepOrcamento {
    
    OrcamentoModel save(OrcamentoModel orcamento);
    OrcamentoModel findById(UUID orcamentoId) throws RuntimeException;
    List<OrcamentoModel> findTopNByEfetivadoOrderByCreatedAtDesc(int n);

    Double getValorDas3UltimasComprasPorCliente(Long clienteId);

    Integer getQuantidadeDeOrcamentosNosUltimosSeisMeses(Long clienteId, LocalDate seisMesesAtras);
}
