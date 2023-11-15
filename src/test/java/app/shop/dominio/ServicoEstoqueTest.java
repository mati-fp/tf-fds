package app.shop.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @Test
    public void testVerificaProdutosComQuantidadeSuficienteDoisProdutos() {
        // Dados de entrada para dois produtos
        List<ItemPedidoDto> itensPedido = new ArrayList<>();
        itensPedido.add(new ItemPedidoDto(1L, 3)); // Produto 1
        itensPedido.add(new ItemPedidoDto(2L, 2)); // Produto 2

        // Mock de dois produtos
        ProdutoModel produto1 = new ProdutoModel();
        produto1.setCodigo(1L);
        produto1.setDescricao("Produto 1");

        ProdutoModel produto2 = new ProdutoModel();
        produto2.setCodigo(2L);
        produto2.setDescricao("Produto 2");

        // Mock de itens de estoque
        ItemDeEstoqueModel itemEstoque1 = new ItemDeEstoqueModel();
        itemEstoque1.setQuantidadeAtual(5); // Quantidade suficiente para produto 1

        ItemDeEstoqueModel itemEstoque2 = new ItemDeEstoqueModel();
        itemEstoque2.setQuantidadeAtual(4); // Quantidade suficiente para produto 2

        // Configuração dos mocks
        when(produtosRep.findById(1L)).thenReturn(produto1);
        when(produtosRep.findById(2L)).thenReturn(produto2);
        when(itemEstoqueRep.findByProduto(produto1)).thenReturn(itemEstoque1);
        when(itemEstoqueRep.findByProduto(produto2)).thenReturn(itemEstoque2);

        // Chama o método a ser testado
        List<ProdutoModel> produtos = servicoEstoque.verificaProdutos(itensPedido);

        // Verificações
        assertNotNull(produtos);
        assertEquals(2, produtos.size());
        assertTrue(produtos.contains(produto1));
        assertTrue(produtos.contains(produto2));

        // Verifica se os métodos mockados foram chamados
        verify(produtosRep, times(1)).findById(1L);
        verify(produtosRep, times(1)).findById(2L);
        verify(itemEstoqueRep, times(1)).findByProduto(produto1);
        verify(itemEstoqueRep, times(1)).findByProduto(produto2);
    }

    @Test
    public void testVerificaProdutosComQuantidadeInsuficienteEmUmDosDoisProdutos() {
        // Dados de entrada para dois produtos
        List<ItemPedidoDto> itensPedido = new ArrayList<>();
        itensPedido.add(new ItemPedidoDto(1L, 3)); // Produto 1 com quantidade suficiente
        itensPedido.add(new ItemPedidoDto(2L, 6)); // Produto 2 com quantidade insuficiente

        // Mock de dois produtos
        ProdutoModel produto1 = new ProdutoModel();
        produto1.setCodigo(1L);
        produto1.setDescricao("Produto 1");

        ProdutoModel produto2 = new ProdutoModel();
        produto2.setCodigo(2L);
        produto2.setDescricao("Produto 2");

        // Mock de itens de estoque
        ItemDeEstoqueModel itemEstoque1 = new ItemDeEstoqueModel();
        itemEstoque1.setQuantidadeAtual(5); // Quantidade suficiente para produto 1

        ItemDeEstoqueModel itemEstoque2 = new ItemDeEstoqueModel();
        itemEstoque2.setQuantidadeAtual(4); // Quantidade insuficiente para produto 2

        // Configura o comportamento dos mocks
        when(produtosRep.findById(1L)).thenReturn(produto1);
        when(produtosRep.findById(2L)).thenReturn(produto2);
        when(itemEstoqueRep.findByProduto(produto1)).thenReturn(itemEstoque1);
        when(itemEstoqueRep.findByProduto(produto2)).thenReturn(itemEstoque2);

        // Chama o método e verifique se a exceção é lançada
        Exception exception = assertThrows(RuntimeException.class, () -> {
            servicoEstoque.verificaProdutos(itensPedido);
        });

        // Verifica a mensagem da exceção
        assertTrue(exception.getMessage().contains("Quantidade insuficiente para o produto"));

        // Verifica se os métodos mockados foram chamados
        verify(produtosRep, times(1)).findById(1L);
        verify(produtosRep, times(1)).findById(2L);
        verify(itemEstoqueRep, times(1)).findByProduto(produto1);
        // O teste deve parar antes de verificar o segundo produto, pois a exceção é lançada
    }

    @Test
    public void testBuscaProdutosPorNPedidoRetornaTrueQuandoProdutosDisponiveis() {
        // Mock do Orçamento
        OrcamentoModel orcamento = new OrcamentoModel();
        UUID uuid = UUID.randomUUID();
        orcamento.setId(uuid);

        // Mock de itens do pedido
        List<ItemPedidoModel> itensPedido = new ArrayList<>();
        ItemPedidoModel item1 = new ItemPedidoModel();
        item1.setQuantidade(2);
        ProdutoModel produto1 = new ProdutoModel();
        produto1.setCodigo(1L);
        item1.setProduto(produto1);

        ItemPedidoModel item2 = new ItemPedidoModel();
        item2.setQuantidade(3);
        ProdutoModel produto2 = new ProdutoModel();
        produto2.setCodigo(2L);
        item2.setProduto(produto2);

        itensPedido.add(item1);
        itensPedido.add(item2);

        // Mock de itens de estoque
        ItemDeEstoqueModel estoque1 = new ItemDeEstoqueModel();
        estoque1.setQuantidadeAtual(5); // Quantidade suficiente para o item 1

        ItemDeEstoqueModel estoque2 = new ItemDeEstoqueModel();
        estoque2.setQuantidadeAtual(6); // Quantidade suficiente para o item 2

        // Configure o comportamento dos mocks
        when(itemPedidoRep.findByOrcamento(orcamento)).thenReturn(itensPedido);
        when(produtosRep.findById(1L)).thenReturn(produto1);
        when(produtosRep.findById(2L)).thenReturn(produto2);
        when(itemEstoqueRep.findByProduto(produto1)).thenReturn(estoque1);
        when(itemEstoqueRep.findByProduto(produto2)).thenReturn(estoque2);

        // Chama o método a ser testado
        Boolean resultado = servicoEstoque.buscaProdutosPorNPedido(orcamento);

        // Verificações
        assertTrue(resultado);

        // Verifica se os métodos mockados foram chamados
        verify(itemPedidoRep, times(1)).findByOrcamento(orcamento);
        verify(produtosRep, times(1)).findById(1L);
        verify(produtosRep, times(1)).findById(2L);
        verify(itemEstoqueRep, times(1)).findByProduto(produto1);
        verify(itemEstoqueRep, times(1)).findByProduto(produto2);
        // Verifica se o save foi chamado para cada item de estoque
        verify(itemEstoqueRep, times(2)).save(any(ItemDeEstoqueModel.class)); 
    }

    @Test
    public void testBuscaProdutosPorNPedidoRetornaFalseQuandoQuantidadeInsuficiente() {
        // Mock do Orçamento
        OrcamentoModel orcamento = new OrcamentoModel();
        UUID uuid = UUID.randomUUID();
        orcamento.setId(uuid);

        // Mock de itens do pedido
        List<ItemPedidoModel> itensPedido = new ArrayList<>();
        ItemPedidoModel item1 = new ItemPedidoModel();
        item1.setQuantidade(4); // Quantidade maior do que a disponível para o item 1
        ProdutoModel produto1 = new ProdutoModel();
        produto1.setCodigo(1L);
        item1.setProduto(produto1);

        ItemPedidoModel item2 = new ItemPedidoModel();
        item2.setQuantidade(1); // Quantidade menor ou igual à disponível para o item 2
        ProdutoModel produto2 = new ProdutoModel();
        produto2.setCodigo(2L);
        item2.setProduto(produto2);

        itensPedido.add(item1);
        itensPedido.add(item2);

        // Mock de itens de estoque
        ItemDeEstoqueModel estoque1 = new ItemDeEstoqueModel();
        estoque1.setQuantidadeAtual(3); // Quantidade insuficiente para o item 1

        ItemDeEstoqueModel estoque2 = new ItemDeEstoqueModel();
        estoque2.setQuantidadeAtual(2); // Quantidade suficiente para o item 2

        // Configura o comportamento dos mocks
        when(itemPedidoRep.findByOrcamento(orcamento)).thenReturn(itensPedido);
        when(produtosRep.findById(1L)).thenReturn(produto1);
        when(itemEstoqueRep.findByProduto(produto1)).thenReturn(estoque1);

        // Chama o método a ser testado
        Boolean resultado = servicoEstoque.buscaProdutosPorNPedido(orcamento);

        // Verificações
        assertFalse(resultado);

        // Verifica se os métodos mockados foram chamados
        verify(itemPedidoRep, times(1)).findByOrcamento(orcamento);
        verify(produtosRep, times(1)).findById(1L);
        verify(produtosRep, never()).findById(2L);
        verify(itemEstoqueRep, times(1)).findByProduto(produto1);
        verify(itemEstoqueRep, never()).findByProduto(produto2);
        // Verifica se o save NÃO foi chamado
        verify(itemEstoqueRep, never()).save(any(ItemDeEstoqueModel.class)); 
    }

}
