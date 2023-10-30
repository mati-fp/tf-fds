package app.shop.Interface;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.shop.aplicacao.EfetivarOrcamento;
import app.shop.aplicacao.ProdutosDisponiveis_UC;
import app.shop.aplicacao.SolicitarOrcamento;
import app.shop.dominio.Orcamento;
import app.shop.dominio.Produto;



@RestController
public class Controller {
    @Autowired
    private ProdutosDisponiveis_UC produtosDisponiveis_UC;
    @Autowired
    private SolicitarOrcamento solicitarOrcamento;
    @Autowired
    private EfetivarOrcamento efetivarOrcamento;

    @GetMapping("produtosDisponiveis")
    @CrossOrigin("*")
    public List<Produto> produtosDisponiveis(){
        return produtosDisponiveis_UC.prodDisponiveis();
    }


    @PostMapping("cadastrarProdutos")
    @CrossOrigin("*")
    public Produto cadastraProduto(@RequestBody final ProdutoDto produto){
        Produto produtoSalvo = produtosDisponiveis_UC.salvaProdutoNovo(produto);
        return produtoSalvo;

    }

    // @PostMapping("fazPedido")
    // @CrossOrigin("*")
    // public Orcamento fazPedido(@RequestBody final PedidoDto pedidoDto){
    //     try {
    //         Orcamento orcamento = solicitarOrcamento.fazPedido(pedidoDto);
    //         return orcamento;
    //     } catch (Exception e) {
    //         throw e;
    //     }
    // }

    // @GetMapping("fazPagemento")
    // @CrossOrigin("*")
    // public String fazPagemnto(@RequestParam("orcamentoID") final String orcamentoId){
    //     try {
    //         String pagamento = efetivarOrcamento.fazPagamento(orcamentoId);
    //         return pagamento;
    //     } catch (Exception e) {
    //         return "Pagemento não efetivado";
    //     }
    // }
}
