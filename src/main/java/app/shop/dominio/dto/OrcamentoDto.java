package app.shop.dominio.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class OrcamentoDto {
    public UUID id;

    public Double custoPedido;

    public Double custoImposto;

    public Double desconto;

    public Double totalPagar;

    public Integer efetivado;

    public LocalDateTime createdAt;

    public LocalDateTime updatedAt;

    public OrcamentoDto(UUID id, Double custoPedido, Double custoImposto, Double desconto, Double totalPagar,
            Integer efetivado, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.custoPedido = custoPedido;
        this.custoImposto = custoImposto;
        this.desconto = desconto;
        this.totalPagar = totalPagar;
        this.efetivado = efetivado;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
