package app.shop.dominio;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Orcamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime data;
    private String nomeCliente;
    
    @OneToOne
    @JoinColumn(name = "codigo_pedido")
    private Pedido pedido;
    private double valorBruto;
    private double valorImposto;
    private double valorDesconto;
    private double valorFinal;
    private boolean efetivado;

    public Orcamento() {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
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
    public Pedido getPedido() {
        return pedido;
    }
    public void setPedido(Pedido pedido) {
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