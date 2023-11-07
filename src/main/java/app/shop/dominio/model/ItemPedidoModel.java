package app.shop.dominio.model;


public class ItemPedidoModel {

    private Long id;

    private Integer quantidade;

    private OrcamentoModel orcamento;

    private ProdutoModel produto;

    public ItemPedidoModel(ProdutoModel produto, Integer quantidade, OrcamentoModel orcamento) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.orcamento = orcamento;
    }

    public ItemPedidoModel() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public OrcamentoModel getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(OrcamentoModel orcamento) {
        this.orcamento = orcamento;
    }

    public ProdutoModel getProduto() {
        return produto;
    }

    public void setProduto(ProdutoModel produto) {
        this.produto = produto;
    }

    
}
