package app.shop.adaptorsInterfaces.repositorio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import app.shop.adaptorsInterfaces.entity.Cliente;
import app.shop.adaptorsInterfaces.entity.ItemDeEstoque;
import app.shop.adaptorsInterfaces.entity.ItemPedido;
import app.shop.adaptorsInterfaces.entity.Orcamento;
import app.shop.adaptorsInterfaces.entity.Produto;
import app.shop.dominio.model.ClienteModel;
import app.shop.dominio.model.ItemDeEstoqueModel;
import app.shop.dominio.model.ItemPedidoModel;
import app.shop.dominio.model.OrcamentoModel;
import app.shop.dominio.model.ProdutoModel;

@Component
public class ModelEntityMapper {
    
    public static ItemDeEstoque itemDeEstoqueToEntity (ItemDeEstoqueModel itemDeEstoqueModel){
        ItemDeEstoque itemDeEstoque = new ItemDeEstoque();
        itemDeEstoque.setId(itemDeEstoqueModel.getId());
        itemDeEstoque.setEstoqueMaximo(itemDeEstoqueModel.getEstoqueMaximo());
        itemDeEstoque.setEstoqueMinimo(itemDeEstoqueModel.getEstoqueMinimo());
        itemDeEstoque.setQuantidadeAtual(itemDeEstoqueModel.getQuantidadeAtual());
        // itemDeEstoque.setProduto(produtoToEntity(itemDeEstoqueModel.getProduto()));
        return itemDeEstoque;
    }

    public static ItemDeEstoqueModel itemDeEstoqueToModel (ItemDeEstoque itemDeEstoque){
        ItemDeEstoqueModel itemDeEstoqueModel = new ItemDeEstoqueModel();
        itemDeEstoqueModel.setId(itemDeEstoque.getId());
        itemDeEstoqueModel.setEstoqueMaximo(itemDeEstoque.getEstoqueMaximo());
        itemDeEstoqueModel.setEstoqueMinimo(itemDeEstoque.getEstoqueMinimo());
        itemDeEstoqueModel.setQuantidadeAtual(itemDeEstoque.getQuantidadeAtual());
        // Cuida loop infinito que foi primeira vez visto aqui
        // itemDeEstoqueModel.setProduto(produtoToModel(itemDeEstoque.getProduto()));
        return itemDeEstoqueModel;
    }

    public static ItemPedido itemPedidoToEntity (ItemPedidoModel itemPedidoModel){
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(itemPedidoModel.getId());
        itemPedido.setProduto(produtoToEntity(itemPedidoModel.getProduto()));
        itemPedido.setQuantidade(itemPedidoModel.getQuantidade());
        // Cuida com loop infinito
        itemPedido.setOrcamento(orcamentoToEntity(itemPedidoModel.getOrcamento()));
        return itemPedido;
    }

    public static ItemPedidoModel itemPedidoToModel (ItemPedido itemPedido){
        ItemPedidoModel itemPedidoModel = new ItemPedidoModel();
        itemPedidoModel.setId(itemPedido.getId());
        itemPedidoModel.setProduto(produtoToModel(itemPedido.getProduto()));
        itemPedidoModel.setQuantidade(itemPedido.getQuantidade());
        // Cuida com loop infinito
        itemPedidoModel.setOrcamento(orcamentoToModel(itemPedido.getOrcamento()));
        return itemPedidoModel;
    }

    public static Orcamento orcamentoToEntity (OrcamentoModel orcamentoModel){
        Orcamento orcamento = new Orcamento();
        if (orcamentoModel.getId() != null) {
            orcamento.setId(orcamentoModel.getId());
        }
        orcamento.setCliente(clienteToEntity(orcamentoModel.getCliente()));
        // if (orcamentoModel.getItensPedido() != null) {
        //     List<ItemPedido> itensPedido = new ArrayList<ItemPedido>();
        //     for (ItemPedidoModel itemPedidoModel : orcamentoModel.getItensPedido()) {
        //         itensPedido.add(itemPedidoToEntity(itemPedidoModel));
        //     }
        //     orcamento.setItensPedido(itensPedido);
        // }   
        orcamento.setCustoPedido(orcamentoModel.getCustoPedido());
        orcamento.setCustoImposto(orcamentoModel.getCustoImposto());
        orcamento.setDesconto(orcamentoModel.getDesconto());
        orcamento.setTotalPagar(orcamentoModel.getTotalPagar());
        orcamento.setEfetivado(orcamentoModel.getEfetivado());
        orcamento.setCreadtedAt(orcamentoModel.getCreatedAt());
        orcamento.setUpdatedAt(orcamentoModel.getUpdatedAt());
        orcamento.setDeletedAt(orcamentoModel.getDeletedAt());
        
        return orcamento;
    }

    public static OrcamentoModel orcamentoToModel (Orcamento orcamento) {
        OrcamentoModel orcamentoModel = new OrcamentoModel();
        orcamentoModel.setId(orcamento.getId());
        orcamentoModel.setCliente(clienteToModel(orcamento.getCliente()));
        if (orcamento.getItensPedido() != null) {
            List<ItemPedidoModel> itensPedidoModel = new ArrayList<ItemPedidoModel>();
            for (ItemPedido itemPedido : orcamento.getItensPedido()) {
                itensPedidoModel.add(itemPedidoToModel(itemPedido));
            }
            orcamentoModel.setItensPedido(itensPedidoModel);
        }
        orcamentoModel.setCustoPedido(orcamento.getCustoPedido());
        orcamentoModel.setCustoImposto(orcamento.getCustoImposto());
        orcamentoModel.setDesconto(orcamento.getDesconto());
        orcamentoModel.setTotalPagar(orcamento.getTotalPagar());
        orcamentoModel.setEfetivado(orcamento.getEfetivado());
        orcamentoModel.setCreadtedAt(orcamento.getCreatedAt());
        orcamentoModel.setUpdatedAt(orcamento.getUpdatedAt());
        orcamentoModel.setDeletedAt(orcamento.getDeletedAt());
        
        return orcamentoModel;
    }

    public static Produto produtoToEntity (ProdutoModel produtoModel){
        Produto produto = new Produto();
        produto.setCodigo(produtoModel.getCodigo());
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

    public static Cliente clienteToEntity (ClienteModel clienteModel) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteModel.getId());
        cliente.setNome(clienteModel.getNome());
        return cliente;
    }

    public static ClienteModel clienteToModel (Cliente clienteEntity) {
        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setId(clienteEntity.getId());
        clienteModel.setNome(clienteEntity.getNome());
        return clienteModel;
    }

}
