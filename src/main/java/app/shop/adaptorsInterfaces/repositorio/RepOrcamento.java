package app.shop.adaptorsInterfaces.repositorio;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import app.shop.adaptorsInterfaces.entity.Orcamento;
import app.shop.adaptorsInterfaces.interfacesJPA.IRepOrcamentoJPA;
import app.shop.dominio.model.OrcamentoModel;
import app.shop.dominio.repositoryInterface.IRepOrcamento;
import jakarta.persistence.criteria.CriteriaBuilder.In;

@Repository
@Primary
public class RepOrcamento implements IRepOrcamento{

    @Autowired
    private IRepOrcamentoJPA orcamentoRep;

    public OrcamentoModel save(OrcamentoModel orcamentoModel){
        Orcamento orcamentoEntity = ModelEntityMapper.orcamentoToEntity(orcamentoModel);
        orcamentoEntity = orcamentoRep.save(orcamentoEntity);
        return ModelEntityMapper.orcamentoToModel(orcamentoEntity);
    }

    public OrcamentoModel findById(UUID orcamentoId) throws RuntimeException{
        Optional<Orcamento> orcamentoOpt = orcamentoRep.findById(orcamentoId);
        if (orcamentoOpt.isEmpty()) {
            throw new RuntimeException("Orcamento não encontrado");
        }
        return ModelEntityMapper.orcamentoToModel(orcamentoOpt.get());
    }
    
    public List<OrcamentoModel> findTopNByEfetivadoOrderByCreatedAtDesc(int n){
        Pageable limit = PageRequest.of(0, n);

        List<Orcamento> orcamentosEntity = orcamentoRep.findTopNByEfetivadoOrderByCreatedAtDesc(true, limit);

        List<OrcamentoModel> orcamentosModel = List.of();

        for (Orcamento orcamento : orcamentosEntity) {
            orcamentosModel.add(ModelEntityMapper.orcamentoToModel(orcamento));
        }

        return orcamentosModel;
    }

    public Double getValorDas3UltimasComprasPorCliente(String nomeCliente){
        Double valor =  orcamentoRep.findSumOfLastThreeEffectiveOrcamentos(nomeCliente, PageRequest.of(0, 3))
            .stream()
            .filter(Objects::nonNull)
            .findFirst()
            .orElse(0.0);

        return valor;
    }

    public Integer getQuantidadeDeOrcamentosNosUltimosSeisMeses(String nomeCliente, LocalDate seisMesesAtras){
        Integer qtd = orcamentoRep.getQuantidadeDeOrcamentosNosUltimosSeisMeses(nomeCliente, seisMesesAtras);
        return qtd;
    }
}
