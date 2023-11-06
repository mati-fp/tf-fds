package app.shop.adaptorsInterfaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.shop.adaptorsInterfaces.dto.PedidoDto;
import app.shop.aplicacao.relatorios.Relatorio;
import app.shop.aplicacao.useCases.EfetivarOrcamento;
import app.shop.aplicacao.useCases.GerarRelatorio;
import app.shop.aplicacao.useCases.ProdutosDisponiveis;
import app.shop.aplicacao.useCases.SolicitarOrcamento;
import app.shop.dominio.Orcamento;
import app.shop.dominio.Produto;



@RestController
public class Controller {
    @Autowired
    private ProdutosDisponiveis produtosDisponiveis;
    @Autowired
    private SolicitarOrcamento solicitarOrcamento;
    @Autowired
    private EfetivarOrcamento efetivarOrcamento;
    @Autowired
    private GerarRelatorio gerarRelatorio;

    @GetMapping("produtosDisponiveis")
    @CrossOrigin("*")
    public List<Produto> produtosDisponiveis(){
        return produtosDisponiveis.prodDisponiveis();
    }

    @PostMapping("fazPedido")
    @CrossOrigin("*")
    public Orcamento fazPedido(@RequestBody final PedidoDto pedidoDto){
        try {
            Orcamento orcamento = solicitarOrcamento.fazPedido(pedidoDto);
            return orcamento;
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("fazPagamento")
    @CrossOrigin("*")
    public ResponseEntity<String> fazPagamento(@RequestParam("orcamentoID") final String orcamentoId){
        try {
            String pagamento = efetivarOrcamento.fazPagamento(orcamentoId);
            return ResponseEntity.ok(pagamento);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Pagamento não efetivado: " + e.getMessage());
        }
    }

    @GetMapping("relatorio")
    @CrossOrigin("*")
    public List<Relatorio> relatorio(@RequestParam("n") final int n){
        return gerarRelatorio.gerarRelatorioUltimosOrcamentos(n);
    }

}
