package app.shop.adaptorsInterfaces.entity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "custo_pedido")
    private Double custoPedido;

    @Column(name = "custo_imposto")
    private Double custoImposto;

    @Column(name = "desconto")
    private Double desconto;

    @Column(name = "total_pagar")
    private Double totalPagar;

    @Column(name = "efetivado")
    private Integer efetivado;

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

    public Orcamento() {
        this.efetivado = 0;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Integer getEfetivado() {
        return efetivado;
    }

    public void setEfetivado(Integer efetivado) {
        this.efetivado = efetivado;
    }

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
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

    public void setCreadtedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Boolean isValid() {
        if (createdAt == null) {
            return false;
        }
        LocalDateTime now = LocalDateTime.now();
        long daysSinceCreation = ChronoUnit.DAYS.between(createdAt, now);

        // Janeiro e Fevereiro
        if (createdAt.getMonthValue() == 1 || createdAt.getMonthValue() == 2) {
            return daysSinceCreation <= 35;
        } else {
            return daysSinceCreation <= 21;
        }
    }

    @Override
    public String toString() {
        return "Orcamento [cliente=" + cliente + ", createdAt=" + createdAt + ", custoImposto=" + custoImposto
                + ", custoPedido=" + custoPedido + ", deletedAt=" + deletedAt + ", desconto=" + desconto
                + ", efetivado=" + efetivado + ", id=" + id + ", itensPedido=" + itensPedido + ", totalPagar="
                + totalPagar + ", updatedAt=" + updatedAt + "]";
    }
}

