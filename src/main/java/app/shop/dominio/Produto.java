package app.shop.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String codigo;
    private String descricao;
    private double preco;

    public Produto() {}

    public Produto(String codigo, String descricao, double preco) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.preco = preco;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return "Produto [codigo=" + codigo + ", descricao=" + descricao + ", preco=" + preco + "]";
    }
}