package app.shop.dominio;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "orcamento")
public class Orcamento {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_id", unique = true, nullable = false)
    private Long pedidoId;

    @Column(name = "custo_pedido", precision = 10, scale = 2)
    private Double custoPedido;

    @Column(name = "custo_imposto", precision = 10, scale = 2)
    private Double custoImposto;

    @Column(name = "desconto", precision = 10, scale = 2)
    private Double desconto;

    @Column(name = "total_pagar", precision = 10, scale = 2)
    private Double totalPagar;

    @Column(name = "efetivado")
    private Boolean efetivado;

    @OneToMany(mappedBy = "orcamento")
    private List<ItemPedido> itensPedido;

    public Orcamento() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Double getCustoPedido() {
        return custoPedido;
    }

    public void setCustoPedido(Double custoPedido) {
        this.custoPedido = custoPedido;
    }

    public Double getCustoImposto() {
        return custoImposto;
    }

    public void setCustoImposto(Double custoImposto) {
        this.custoImposto = custoImposto;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(Double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public Boolean getEfetivado() {
        return efetivado;
    }

    public void setEfetivado(Boolean efetivado) {
        this.efetivado = efetivado;
    }

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

    
}

