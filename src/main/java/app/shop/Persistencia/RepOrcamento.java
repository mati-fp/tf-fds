package app.shop.Persistencia;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import app.shop.dominio.IRepOrcamento;
import app.shop.dominio.Orcamento;

@Repository

public class RepOrcamento {

    @Autowired
    private IRepOrcamento orcamentoRep;

    
}
