package app.shop.aplicacao.relatorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import app.shop.dominio.model.ItemPedidoModel;
import app.shop.dominio.model.OrcamentoModel;
import app.shop.dominio.model.ProdutoModel;
import app.shop.dominio.repositoryInterface.IRepItemPedido;
import app.shop.dominio.repositoryInterface.IRepOrcamento;
import app.shop.dominio.repositoryInterface.IRepProduto;

@ExtendWith(MockitoExtension.class)
public class ServicoRelatorioTest {
    @InjectMocks
    private ServicoRelatorio servicoRelatorio;

    @Mock
    private IRepOrcamento repOrcamento;

    @Mock
    private IRepItemPedido repItemPedido;

    @Mock
    private IRepProduto repProduto;

    @Test
    public void testGerarRelatorioUltimosOrcamentos() {
        // Mock de OrcamentoModel
        OrcamentoModel orcamento1 = new OrcamentoModel();
        orcamento1.setCreadtedAt(LocalDateTime.now());
        orcamento1.setCustoPedido(100.0);
        orcamento1.setCustoImposto(10.0);
        orcamento1.setDesconto(5.0);
        orcamento1.setTotalPagar(105.0);

        OrcamentoModel orcamento2 = new OrcamentoModel();
        orcamento2.setCreadtedAt(LocalDateTime.now().minusDays(1));
        orcamento2.setCustoPedido(200.0);
        orcamento2.setCustoImposto(20.0);
        orcamento2.setDesconto(10.0);
        orcamento2.setTotalPagar(210.0);

        List<OrcamentoModel> orcamentosMock = Arrays.asList(orcamento1, orcamento2);

        when(repOrcamento.findTopNByEfetivadoOrderByCreatedAtDesc(2)).thenReturn(orcamentosMock);

        // Chamada do método a ser testado
        List<RelatorioOrcamentosDto> resultado = servicoRelatorio.gerarRelatorioUltimosOrcamentos(2);

        // Verificações
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(100.0, resultado.get(0).valorBrutoPedido);
        assertEquals(10.0, resultado.get(0).valorImposto);
        assertEquals(5.0, resultado.get(0).totalDesconto);
        assertEquals(105.0, resultado.get(0).valorPago);

        assertEquals(200.0, resultado.get(1).valorBrutoPedido);
        assertEquals(20.0, resultado.get(1).valorImposto);
        assertEquals(10.0, resultado.get(1).totalDesconto);
        assertEquals(210.0, resultado.get(1).valorPago);

        // Verifica as interações com os mocks
        verify(repOrcamento, times(1)).findTopNByEfetivadoOrderByCreatedAtDesc(2);
    }

    @Test
    public void testGerarRelatorioProdutosVendidosNoAnoMaisVendidos() {
        // Mock de OrcamentoModel e ItemPedidoModel
        OrcamentoModel orcamento = new OrcamentoModel();
        orcamento.setEfetivado(1);
        orcamento.setCreadtedAt(LocalDateTime.of(2023, 1, 1, 0, 0));

        ProdutoModel produto1 = new ProdutoModel();
        produto1.setCodigo(1L);
        ItemPedidoModel itemPedido1 = new ItemPedidoModel(produto1, 10, orcamento);

        ProdutoModel produto2 = new ProdutoModel();
        produto2.setCodigo(2L);
        ItemPedidoModel itemPedido2 = new ItemPedidoModel(produto2, 5, orcamento);

        when(repOrcamento.findByEfetivadoAndCreatedAtYear(2023)).thenReturn(Arrays.asList(orcamento));
        when(repItemPedido.findByOrcamento(orcamento)).thenReturn(Arrays.asList(itemPedido1, itemPedido2));
        when(repProduto.findById(1L)).thenReturn(produto1);
        when(repProduto.findById(2L)).thenReturn(produto2);

        // Chamada do método a ser testado
        List<RelatorioProdutoDto> resultado = servicoRelatorio.gerarRelatorioProdutosVendidosNoAno(2023, true);

        // Verificações
        assertNotNull(resultado);
        assertEquals(2, resultado.size()); // Dependendo da quantidade de produtos mockados
        assertEquals(1L, resultado.get(0).codigo_produto); // Verifica se o produto mais vendido está no topo
        assertEquals(10, resultado.get(0).quantidadeVendida); // Verifica a quantidade do produto mais vendido

        // Verifique as interações com os mocks
        verify(repOrcamento, times(1)).findByEfetivadoAndCreatedAtYear(2023);
        verify(repItemPedido, times(1)).findByOrcamento(orcamento);
        verify(repProduto, times(1)).findById(1L);
        verify(repProduto, times(1)).findById(2L);
    }

    @Test
    public void testGerarRelatorioProdutosVendidosNoAnoMenosVendidos() {
        // Mock de OrcamentoModel e ItemPedidoModel
        OrcamentoModel orcamento = new OrcamentoModel();
        orcamento.setEfetivado(1);
        orcamento.setCreadtedAt(LocalDateTime.of(2023, 1, 1, 0, 0));

        ProdutoModel produto1 = new ProdutoModel();
        produto1.setCodigo(1L);
        ItemPedidoModel itemPedido1 = new ItemPedidoModel(produto1, 5, orcamento);

        ProdutoModel produto2 = new ProdutoModel();
        produto2.setCodigo(2L);
        ItemPedidoModel itemPedido2 = new ItemPedidoModel(produto2, 10, orcamento);

        when(repOrcamento.findByEfetivadoAndCreatedAtYear(2023)).thenReturn(Arrays.asList(orcamento));
        when(repItemPedido.findByOrcamento(orcamento)).thenReturn(Arrays.asList(itemPedido1, itemPedido2));
        when(repProduto.findById(1L)).thenReturn(produto1);
        when(repProduto.findById(2L)).thenReturn(produto2);

        // Chamada do método a ser testado
        List<RelatorioProdutoDto> resultado = servicoRelatorio.gerarRelatorioProdutosVendidosNoAno(2023, false);

        // Verificações
        assertNotNull(resultado);
        assertEquals(2, resultado.size()); // Dependendo da quantidade de produtos mockados
        assertEquals(1L, resultado.get(0).codigo_produto); // Verifica se o produto menos vendido está no topo
        assertEquals(5, resultado.get(0).quantidadeVendida); // Verifica a quantidade do produto menos vendido

        // Verifique as interações com os mocks
        verify(repOrcamento, times(1)).findByEfetivadoAndCreatedAtYear(2023);
        verify(repItemPedido, times(1)).findByOrcamento(orcamento);
        verify(repProduto, times(1)).findById(1L);
        verify(repProduto, times(1)).findById(2L);
    }

    @Test
    public void testGerarRelatorioProdutosMaisCompradosPeloCliente() {
        Long clienteId = 1L; // ID do cliente para teste

        // Mock de OrcamentoModel
        OrcamentoModel orcamento1 = new OrcamentoModel();
        OrcamentoModel orcamento2 = new OrcamentoModel();

        // Mock de ProdutoModel
        ProdutoModel produto1 = new ProdutoModel();
        produto1.setCodigo(1L);
        ProdutoModel produto2 = new ProdutoModel();
        produto2.setCodigo(2L);

        // Mock de ItemPedidoModel para orcamento1
        ItemPedidoModel itemPedido1Orc1 = new ItemPedidoModel(produto1, 3, orcamento1);
        ItemPedidoModel itemPedido2Orc1 = new ItemPedidoModel(produto2, 2, orcamento1);

        // Mock de ItemPedidoModel para orcamento2
        ItemPedidoModel itemPedido1Orc2 = new ItemPedidoModel(produto1, 4, orcamento2);
        ItemPedidoModel itemPedido2Orc2 = new ItemPedidoModel(produto2, 1, orcamento2);

        // Configurando mocks
        when(repOrcamento.findByClienteId(clienteId)).thenReturn(Arrays.asList(orcamento1, orcamento2));
        when(repItemPedido.findByOrcamento(orcamento1)).thenReturn(Arrays.asList(itemPedido1Orc1, itemPedido2Orc1));
        when(repItemPedido.findByOrcamento(orcamento2)).thenReturn(Arrays.asList(itemPedido1Orc2, itemPedido2Orc2));
        when(repProduto.findById(1L)).thenReturn(produto1);
        when(repProduto.findById(2L)).thenReturn(produto2);

        // Chamada do método a ser testado
        List<RelatorioProdutoDto> resultado = servicoRelatorio.gerarRelatorioProdutosMaisCompradosPeloCliente(clienteId);

        // Verificações
        assertNotNull(resultado);
        assertTrue(resultado.size() <= 20); // O resultado deve conter até 20 produtos
        assertEquals(1L, resultado.get(0).codigo_produto); // O produto mais comprado (produto1) deve estar no topo
        assertEquals(7, resultado.get(0).quantidadeVendida); // A quantidade total do produto mais comprado

        // Verifique as interações com os mocks
        verify(repOrcamento, times(1)).findByClienteId(clienteId);
        verify(repItemPedido, times(1)).findByOrcamento(orcamento1);
        verify(repItemPedido, times(1)).findByOrcamento(orcamento2);
        verify(repProduto, times(1)).findById(1L);
        verify(repProduto, times(1)).findById(2L);
    }
}
