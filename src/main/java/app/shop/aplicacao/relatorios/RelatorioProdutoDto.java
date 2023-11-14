package app.shop.aplicacao.relatorios;

public class RelatorioProdutoDto {
    public Long codigo_produto;
    public String descricao;
    public Double precoUnitario;
    public Integer quantidadeVendida;

    public RelatorioProdutoDto(Long codigo_produto, String descricao, Double precoUnitario, Integer quantidadeVendida) {
        this.codigo_produto = codigo_produto;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
        this.quantidadeVendida = quantidadeVendida;
    }
}
