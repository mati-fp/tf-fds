package app.shop.dominio;

import app.shop.dominio.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepItemPedido extends JpaRepository<ItemPedido, Long> {
}
