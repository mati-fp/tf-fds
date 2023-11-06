package app.shop.dominio;

import java.util.List;
import java.util.UUID;

import app.shop.adaptorsInterfaces.entity.Orcamento;

public interface IRepOrcamento {
    
    public void save(Orcamento orcamento);

    public Orcamento findById(UUID orcamentoId) throws RuntimeException;

    public List<Orcamento> findTopNByEfetivadoOrderByCreatedAtDesc(int n);
}
