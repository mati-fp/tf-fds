package app.shop.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import app.shop.dominio.dto.ItemPedidoDto;
import app.shop.dominio.model.ItemDeEstoqueModel;
import app.shop.dominio.model.ItemPedidoModel;
import app.shop.dominio.model.OrcamentoModel;
import app.shop.dominio.model.ProdutoModel;
import app.shop.dominio.repositoryInterface.IRepItemDeEstoque;
import app.shop.dominio.repositoryInterface.IRepItemPedido;
import app.shop.dominio.repositoryInterface.IRepProduto;

@ExtendWith(MockitoExtension.class)
public class ServicoEstoqueTest {
    @InjectMocks
    private ServicoEstoque servicoEstoque;

    @Mock
    private IRepItemDeEstoque itemEstoqueRep;

    @Mock
    private IRepItemPedido itemPedidoRep;

    @Mock
    private IRepProduto produtosRep;

    private ProdutoModel produtoModel;
    private ItemDeEstoqueModel itemEstoqueModel;
    private ItemPedidoModel itemPedidoModel;
    private OrcamentoModel orcamentoModel;

    @BeforeEach
    public void inicializar() {
        produtoModel = mock(ProdutoModel.class);
        itemEstoqueModel = mock(ItemDeEstoqueModel.class);
        itemPedidoModel = mock(ItemPedidoModel.class);
        orcamentoModel = mock(OrcamentoModel.class);
    }

    @Test
    public void testProdutosDisponiveis() {
        List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
        produtos.add(produtoModel);
        when(produtosRep.findAll()).thenReturn(produtos);

        List<ProdutoModel> produtosDisponiveis = servicoEstoque.produtosDisponiveis();

        assertEquals(produtos, produtosDisponiveis);
    }

    @Test
    public void testVerificaProdutos() {
        // Primeiro produto
        ItemPedidoDto itemDto1 = mock(ItemPedidoDto.class);
        when(itemDto1.codigoProduto).thenReturn(produtoModel.getCodigo());
        when(itemDto1.quantidade).thenReturn(10);

        // Segundo produto
        ItemPedidoDto itemDto2 = mock(ItemPedidoDto.class);
        ProdutoModel produtoModel2 = mock(ProdutoModel.class);
        when(itemDto2.codigoProduto).thenReturn(produtoModel2.getCodigo());
        when(itemDto2.quantidade).thenReturn(20);

        // Configura o repositório para também retornar o segundo produto
        ItemDeEstoqueModel itemEstoqueModel2 = mock(ItemDeEstoqueModel.class);

        when(itemEstoqueModel.getProduto()).thenReturn(produtoModel);
        when(itemEstoqueModel.getQuantidadeAtual()).thenReturn(100);
        when(itemEstoqueRep.findByProduto(produtoModel)).thenReturn(itemEstoqueModel);

        when(itemEstoqueModel2.getProduto()).thenReturn(produtoModel2);
        when(itemEstoqueModel2.getQuantidadeAtual()).thenReturn(250);
        when(itemEstoqueRep.findByProduto(produtoModel2)).thenReturn(itemEstoqueModel2);

        List<ItemPedidoDto> itensPedidoDto = new ArrayList<ItemPedidoDto>();
        itensPedidoDto.add(itemDto1);
        itensPedidoDto.add(itemDto2);

        List<ProdutoModel> produtosDisponiveis = servicoEstoque.verificaProdutos(itensPedidoDto);

        assertEquals(2, produtosDisponiveis.size()); // Espera que dois produtos estejam disponíveis
        assertEquals(produtoModel, produtosDisponiveis.get(0));
        assertEquals(produtoModel2, produtosDisponiveis.get(1));
    }

    @Test
    public void testBuscaProdutosPorNPedido() {
        // Configura o mock para retornar um produto quando findById for chamado
        when(produtosRep.findById(produtoModel.getCodigo())).thenReturn(produtoModel);

        // Configura o mock para retornar um item de estoque quando findByProduto for chamado
        when(itemEstoqueRep.findByProduto(produtoModel)).thenReturn(itemEstoqueModel);

        // Configura o mock para retornar uma quantidade suficiente quando getQuantidadeAtual for chamado
        when(itemEstoqueModel.getQuantidadeAtual()).thenReturn(itemPedidoModel.getQuantidade() + 1);

        // Configura o mock para retornar um item de pedido quando findByOrcamento for chamado
        List<ItemPedidoModel> itensPedido = new ArrayList<ItemPedidoModel>();
        itensPedido.add(itemPedidoModel);
        when(itemPedidoRep.findByOrcamento(orcamentoModel)).thenReturn(itensPedido);

        // Chama a função e verifica se ela retorna true
        Boolean produtosEncontrados = servicoEstoque.buscaProdutosPorNPedido(orcamentoModel);
        assertEquals(true, produtosEncontrados);

        // Verifica se save foi chamado no repositório de itens de estoque
        verify(itemEstoqueRep, times(1)).save(any(ItemDeEstoqueModel.class));
    }
}
