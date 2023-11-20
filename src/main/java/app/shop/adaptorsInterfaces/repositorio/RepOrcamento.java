package app.shop.adaptorsInterfaces.repositorio;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

        List<Orcamento> orcamentosEntity = orcamentoRep.findTopNByEfetivadoOrderByCreatedAtDesc(1, limit);

        List<OrcamentoModel> orcamentosModel = new ArrayList<>();

        for (Orcamento orcamento : orcamentosEntity) {
            orcamentosModel.add(ModelEntityMapper.orcamentoToModel(orcamento));
        }

        return orcamentosModel;
    }

    public Double getValorDas3UltimasComprasPorCliente(Long clienteId){
        Double valor =  orcamentoRep.findSumOfLastThreeEffectiveOrcamentos(clienteId, PageRequest.of(0, 3))
            .stream()
            .filter(Objects::nonNull)
            .limit(3)
            .mapToDouble(Double::doubleValue)
            .sum();
        return valor;
    }

    public Integer getQuantidadeDeOrcamentosNosUltimosSeisMeses(Long clienteId, LocalDateTime seisMesesAtras){
        Integer qtd = orcamentoRep.getQuantidadeDeOrcamentosNosUltimosSeisMeses(clienteId, seisMesesAtras);
        return qtd;
    }

    public List<OrcamentoModel> findByEfetivadoAndCreatedAtYear(int ano){
        List<Orcamento> orcamentoENtity = orcamentoRep.findByEfetivadoAndCreatedAtYear(1, ano);

        List<OrcamentoModel> orcamentoModels = new ArrayList<>();

        for (Orcamento orcamento : orcamentoENtity) {
            orcamentoModels.add(ModelEntityMapper.orcamentoToModel(orcamento));
        }

        return orcamentoModels;
    }

    public List<OrcamentoModel> findByClienteId(Long id) {
        List<Orcamento> orcamentoENtity = orcamentoRep.findByClienteId(id);

        List<OrcamentoModel> orcamentoModels = new ArrayList<>();

        for (Orcamento orcamento : orcamentoENtity) {
            orcamentoModels.add(ModelEntityMapper.orcamentoToModel(orcamento));
        }

        return orcamentoModels;
    }
}
