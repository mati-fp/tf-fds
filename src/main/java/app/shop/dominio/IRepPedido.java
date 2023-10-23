package app.shop.dominio;

import app.shop.dominio.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepPedido extends JpaRepository<Pedido, String> {
}
