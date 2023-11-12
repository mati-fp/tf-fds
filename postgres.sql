
DROP TABLE IF EXISTS item_de_estoque CASCADE;
DROP TABLE IF EXISTS produto CASCADE;
DROP TABLE IF EXISTS item_pedido CASCADE; 
DROP TABLE IF EXISTS orcamento CASCADE;
DROP TABLE IF EXISTS cliente CASCADE;

-- Tabela cliente
CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) UNIQUE NOT NULL
);

-- Tabela orcamento
CREATE TABLE orcamento (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    cliente_id INTEGER,
    custo_pedido NUMERIC(10, 2),
    custo_imposto NUMERIC(10, 2),
    desconto NUMERIC(10, 2),
    total_pagar NUMERIC(10, 2),
    efetivado SMALLINT DEFAULT 0 CHECK (efetivado IN (0, 1)),
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    deleted_at TIMESTAMP WITHOUT TIME ZONE,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);


-- Tabela produto
CREATE TABLE produto (
    codigo SERIAL PRIMARY KEY,
    descricao VARCHAR(255),
    preco_unitario NUMERIC(10, 2)
);

-- Tabela item_pedido
CREATE TABLE item_pedido (
    id SERIAL PRIMARY KEY,
    quantidade INTEGER,
    orcamento_id UUID,
    cod_produto INTEGER
);

-- Tabela item_de_estoque
CREATE TABLE item_de_estoque (
    id SERIAL PRIMARY KEY,
    quantidade_atual INTEGER,
    estoque_minimo INTEGER,
    estoque_maximo INTEGER,
    cod_produto INTEGER UNIQUE
);

-- Adicionar chaves estrangeiras para item_pedido
ALTER TABLE item_pedido
ADD CONSTRAINT fk_item_pedido_pedido_id FOREIGN KEY (orcamento_id) REFERENCES orcamento (id);

ALTER TABLE item_pedido
ADD CONSTRAINT fk_item_pedido_cod_produto FOREIGN KEY (cod_produto) REFERENCES produto (codigo);

-- Adicionar chave estrangeira para item_de_estoque
ALTER TABLE item_de_estoque
ADD CONSTRAINT fk_item_de_estoque_cod_produto FOREIGN KEY (cod_produto) REFERENCES produto (codigo);




