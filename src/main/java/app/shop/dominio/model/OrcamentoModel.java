package app.shop.dominio.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

public class OrcamentoModel {

    private UUID id;

    private ClienteModel cliente;

    private Double custoPedido;

    private Double custoImposto;

    private Double desconto;

    private Double totalPagar;

    private Integer efetivado;

    private List<ItemPedidoModel> itensPedido;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    public OrcamentoModel() {
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

    public List<ItemPedidoModel> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedidoModel> itensPedido) {
        this.itensPedido = itensPedido;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public ClienteModel getCliente() {
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
        return "OrcamentoModel [cliente=" + cliente + ", createdAt=" + createdAt + ", custoImposto=" + custoImposto
                + ", custoPedido=" + custoPedido + ", deletedAt=" + deletedAt + ", desconto=" + desconto
                + ", efetivado=" + efetivado + ", id=" + id + ", itensPedido=" + itensPedido + ", totalPagar="
                + totalPagar + ", updatedAt=" + updatedAt + "]";
    }
}
