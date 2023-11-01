package app.shop.dominio;

import java.time.LocalDateTime;
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
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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

    @Column(name = "nome_cliente")
    private String nomeCliente;

    @Column(name = "custo_pedido")
    private Double custoPedido;

    @Column(name = "custo_imposto")
    private Double custoImposto;

    @Column(name = "desconto")
    private Double desconto;

    @Column(name = "total_pagar")
    private Double totalPagar;

    @Column(name = "efetivado")
    private Boolean efetivado;

    @OneToMany(mappedBy = "orcamento")
    private List<ItemPedido> itensPedido;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

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

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }
}

