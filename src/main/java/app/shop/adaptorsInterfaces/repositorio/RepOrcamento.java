package app.shop.adaptorsInterfaces.repositorio;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import app.shop.adaptorsInterfaces.interfacesRepositorio.IRepOrcamento;
import app.shop.dominio.Orcamento;

@Repository
@Primary
public class RepOrcamento {

    @Autowired
    private IRepOrcamento orcamentoRep;

    public void save(Orcamento orcamento){
        orcamentoRep.save(orcamento);
    }

    public Orcamento findById(UUID orcamentoId) throws RuntimeException{
        Optional<Orcamento> orcamentoOpt = orcamentoRep.findById(orcamentoId);
        if (orcamentoOpt.isEmpty()) {
            throw new RuntimeException("Orcamento não encontrado");
        }
        return orcamentoOpt.get();
    }
    
    public List<Orcamento> findTopNByEfetivadoOrderByCreatedAtDesc(int n){
        Pageable limit = PageRequest.of(0, n);
        return orcamentoRep.findTopNByEfetivadoOrderByCreatedAtDesc(true, limit);
    }
}
