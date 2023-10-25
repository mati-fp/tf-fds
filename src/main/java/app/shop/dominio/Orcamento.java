package app.shop.dominio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import app.shop.Interface.PedidoDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Orcamento {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;
    @Column(name = "data")
    private LocalDateTime data;
    @Column(name = "nome_cliente", length = 255)
    private String nomeCliente;
    @Column(name = "pedido")
    private Integer pedido;
    @Column(name = "valor_bruto", precision = 2)
    private double valorBruto;
    @Column(name = "valor_imposto", precision = 2)
    private double valorImposto;
    @Column(name = "valor_desconto", precision = 2)
    private double valorDesconto;
    @Column(name = "valor_final", precision = 2)
    private double valorFinal;
    @Column(name = "efetivado", columnDefinition = "boolean default false")
    private boolean efetivado;

    public Orcamento() {}
    public Orcamento(PedidoDto pedidoDto, List<Produto> produtos, int pedido) {
        this.id = UUID.randomUUID().toString();
        this.data = LocalDateTime.now();
        this.nomeCliente = pedidoDto.nomeCliente;
        this.pedido = pedido;
        this.valorBruto = 0;
        // aqui pega o valor bruto
        for(Produto produto : produtos){
            int quantidade = pedidoDto.itens.stream().filter(item -> item.getCodProduto().equals(produto.getCodigo())).findFirst().get().getQuantidade();
            this.valorBruto += produto.getPreco() * quantidade;
        }
        this.valorImposto = this.valorBruto * 0.1;
        // tem desconto se for mais de 5 itens
        if(pedidoDto.itens.size() > 5){
            this.valorDesconto = this.valorBruto * 0.05;
        } else {
            this.valorDesconto = 0;
        }
        this.valorFinal = this.valorBruto + this.valorImposto - this.valorDesconto;
        this.efetivado = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Integer getPedido() {
        return pedido;
    }

    public void setPedido(Integer pedido) {
        this.pedido = pedido;
    }

    public double getValorBruto() {
        return valorBruto;
    }

    public void setValorBruto(double valorBruto) {
        this.valorBruto = valorBruto;
    }

    public double getValorImposto() {
        return valorImposto;
    }

    public void setValorImposto(double valorImposto) {
        this.valorImposto = valorImposto;
    }

    public double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public boolean isEfetivado() {
        return efetivado;
    }

    public void setEfetivado(boolean efetivado) {
        this.efetivado = efetivado;
    }

    

    
}