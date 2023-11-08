package app.shop.dominio.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

public class OrcamentoModel {

    private UUID id;

    private Long pedidoId;

    private String nomeCliente;

    private Double custoPedido;

    private Double custoImposto;

    private Double desconto;

    private Double totalPagar;

    private Boolean efetivado;

    private List<ItemPedidoModel> itensPedido;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

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

    public List<ItemPedidoModel> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedidoModel> itensPedido) {
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

    public boolean isValid() {
        LocalDate now = LocalDate.now();
        long daysSinceCreation = ChronoUnit.DAYS.between(createdAt, now);

        // Janeiro e Fevereiro
        if (createdAt.getMonthValue() == 1 || createdAt.getMonthValue() == 2) {
            return daysSinceCreation <= 35;
        } else {
            return daysSinceCreation <= 21;
        }
    }
}
