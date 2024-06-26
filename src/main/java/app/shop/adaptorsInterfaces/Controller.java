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

import app.shop.aplicacao.relatorios.RelatorioOrcamentosDto;
import app.shop.aplicacao.relatorios.RelatorioProdutoDto;
import app.shop.aplicacao.useCases.EfetivarOrcamento;
import app.shop.aplicacao.useCases.GerarRelatorio;
import app.shop.aplicacao.useCases.ProdutosDisponiveis;
import app.shop.aplicacao.useCases.SolicitarOrcamento;
import app.shop.dominio.dto.OrcamentoDto;
import app.shop.dominio.dto.PedidoDto;
import app.shop.dominio.model.ProdutoModel;



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
    public List<ProdutoModel> produtosDisponiveis(){
        return produtosDisponiveis.prodDisponiveis();
    }

    @PostMapping("fazPedido")
    @CrossOrigin("*")
    public OrcamentoDto fazPedido(@RequestBody final PedidoDto pedidoDto){
        try {
            OrcamentoDto orcamento = solicitarOrcamento.fazPedido(pedidoDto);
            return orcamento;
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("fazPagamento")
    @CrossOrigin("*")
    public ResponseEntity<String> fazPagamento(@RequestParam("orcamentoId") final String orcamentoId){
        try {
            String pagamento = efetivarOrcamento.fazPagamento(orcamentoId);
            return ResponseEntity.ok(pagamento);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Pagamento não efetivado: " + e.getMessage());
        }
    }

    @GetMapping("relatorioUltimosOrcamentos")
    @CrossOrigin("*")
    public List<RelatorioOrcamentosDto> relatorioUltimosOrcamentos(@RequestParam("nOrcamentos") final int nOrcamentos){
        return gerarRelatorio.gerarRelatorioUltimosOrcamentos(nOrcamentos);
    }

    @GetMapping("relatorioProdutosMaisVendidosNoAno")
    @CrossOrigin("*")
    public List<RelatorioProdutoDto> relatorioProdutosMaisVendidosNoAno(@RequestParam("ano") final int ano){
        return gerarRelatorio.gerarRelatorioProdutosMaisVendidosNoAno(ano);
    }

    @GetMapping("relatorioProdutosMenosVendidosNoAno")
    @CrossOrigin("*")
    public List<RelatorioProdutoDto> relatorioProdutosMenosVendidosNoAno(@RequestParam("ano") final int ano){
        return gerarRelatorio.gerarRelatorioProdutosMenosVendidosNoAno(ano);
    }

    @GetMapping("produtosMaisCompradosPeloCliente")
    @CrossOrigin("*")
    public List<RelatorioProdutoDto> produtosMaisCompradosPeloCliente(@RequestParam("clienteId") final Long clienteId){
        return gerarRelatorio.gerarRelatorioProdutosMaisCompradosPeloCliente(clienteId);
    }

}
