package app.shop.adaptorsInterfaces.repositorio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import app.shop.adaptorsInterfaces.entity.ItemDeEstoque;
import app.shop.adaptorsInterfaces.entity.ItemPedido;
import app.shop.adaptorsInterfaces.entity.Orcamento;
import app.shop.adaptorsInterfaces.entity.Produto;
import app.shop.dominio.model.ItemDeEstoqueModel;
import app.shop.dominio.model.ItemPedidoModel;
import app.shop.dominio.model.OrcamentoModel;
import app.shop.dominio.model.ProdutoModel;

@Component
public class ModelEntityMapper {
    
    public static ItemDeEstoque itemDeEstoqueToEntity (ItemDeEstoqueModel itemDeEstoqueModel){
        ItemDeEstoque itemDeEstoque = new ItemDeEstoque();
        itemDeEstoque.setEstoqueMaximo(itemDeEstoqueModel.getEstoqueMaximo());
        itemDeEstoque.setEstoqueMinimo(itemDeEstoqueModel.getEstoqueMinimo());
        itemDeEstoque.setQuantidadeAtual(itemDeEstoqueModel.getQuantidadeAtual());
        itemDeEstoque.setProduto(produtoToEntity(itemDeEstoqueModel.getProduto()));
        return itemDeEstoque;
    }

    public static ItemDeEstoqueModel itemDeEstoqueToModel (ItemDeEstoque itemDeEstoque){
        ItemDeEstoqueModel itemDeEstoqueModel = new ItemDeEstoqueModel();
        itemDeEstoqueModel.setId(itemDeEstoque.getId());
        itemDeEstoqueModel.setEstoqueMaximo(itemDeEstoque.getEstoqueMaximo());
        itemDeEstoqueModel.setEstoqueMinimo(itemDeEstoque.getEstoqueMinimo());
        itemDeEstoqueModel.setQuantidadeAtual(itemDeEstoque.getQuantidadeAtual());
        itemDeEstoqueModel.setProduto(produtoToModel(itemDeEstoque.getProduto()));
        return itemDeEstoqueModel;
    }

    public static ItemPedido itemPedidoToEntity (ItemPedidoModel itemPedidoModel){
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setProduto(produtoToEntity(itemPedidoModel.getProduto()));
        itemPedido.setQuantidade(itemPedidoModel.getQuantidade());
        itemPedido.setOrcamento(orcamentoToEntity(itemPedidoModel.getOrcamento()));
        return itemPedido;
    }

    public static ItemPedidoModel itemPedidoToModel (ItemPedido itemPedido){
        ItemPedidoModel itemPedidoModel = new ItemPedidoModel();
        itemPedidoModel.setId(itemPedido.getId());
        itemPedidoModel.setProduto(produtoToModel(itemPedido.getProduto()));
        itemPedidoModel.setQuantidade(itemPedido.getQuantidade());
        itemPedidoModel.setOrcamento(orcamentoToModel(itemPedido.getOrcamento()));
        return itemPedidoModel;
    }

    public static Orcamento orcamentoToEntity (OrcamentoModel orcamentoModel){
        Orcamento orcamento = new Orcamento();
        orcamento.setNomeCliente(orcamentoModel.getNomeCliente());
        List<ItemPedido> itensPedido = new ArrayList<ItemPedido>();
        for (ItemPedidoModel itemPedidoModel : orcamentoModel.getItensPedido()) {
            itensPedido.add(itemPedidoToEntity(itemPedidoModel));
        }
        orcamento.setItensPedido(itensPedido);
        orcamento.setCustoPedido(orcamentoModel.getCustoPedido());
        orcamento.setCustoImposto(orcamentoModel.getCustoImposto());
        orcamento.setDesconto(orcamentoModel.getDesconto());
        orcamento.setTotalPagar(orcamentoModel.getTotalPagar());
        orcamento.setEfetivado(orcamentoModel.getEfetivado());
        return orcamento;
    }

    public static OrcamentoModel orcamentoToModel (Orcamento orcamento) {
        OrcamentoModel orcamentoModel = new OrcamentoModel();
        orcamentoModel.setId(orcamento.getId());
        orcamentoModel.setPedidoId(orcamento.getPedidoId());
        orcamentoModel.setNomeCliente(orcamento.getNomeCliente());
        List<ItemPedidoModel> itensPedidoModel = new ArrayList<ItemPedidoModel>();
        for (ItemPedido itemPedido : orcamento.getItensPedido()) {
            itensPedidoModel.add(itemPedidoToModel(itemPedido));
        }
        orcamentoModel.setItensPedido(itensPedidoModel);
        orcamentoModel.setCustoPedido(orcamento.getCustoPedido());
        orcamentoModel.setCustoImposto(orcamento.getCustoImposto());
        orcamentoModel.setDesconto(orcamento.getDesconto());
        orcamentoModel.setTotalPagar(orcamento.getTotalPagar());
        orcamentoModel.setEfetivado(orcamento.getEfetivado());
        return orcamentoModel;
    }

    public static Produto produtoToEntity (ProdutoModel produtoModel){
        Produto produto = new Produto();
        produto.setDescricao(produtoModel.getDescricao());
        produto.setPrecoUnitario(produtoModel.getPrecoUnitario());
        produto.setItemDeEstoque(itemDeEstoqueToEntity(produtoModel.getItemDeEstoque()));
        return produto;
    }

    public static ProdutoModel produtoToModel (Produto produto) {
        ProdutoModel produtoModel = new ProdutoModel();
        produtoModel.setCodigo(produto.getCodigo());
        produtoModel.setDescricao(produto.getDescricao());
        produtoModel.setPrecoUnitario(produto.getPrecoUnitario());
        produtoModel.setItemDeEstoque(itemDeEstoqueToModel(produto.getItemDeEstoque()));
        return produtoModel;
    }

}
