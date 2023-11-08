package app.shop.dominio.repositoryInterface;

import java.util.List;
import java.util.UUID;

import app.shop.dominio.model.OrcamentoModel;


public interface IRepOrcamento {
    
    public OrcamentoModel save(OrcamentoModel orcamento);

    public OrcamentoModel findById(UUID orcamentoId) throws RuntimeException;

    public List<OrcamentoModel> findTopNByEfetivadoOrderByCreatedAtDesc(int n);

    public Double getValorDas3UltimasComprasPorCliente(String nomeCliente);
}
