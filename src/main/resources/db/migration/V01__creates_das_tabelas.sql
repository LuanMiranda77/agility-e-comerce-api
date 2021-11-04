
-- DROP TABLE categoria;

CREATE TABLE categoria (
	id bigserial NOT NULL,
	nome varchar(255) NULL,
	CONSTRAINT categoria_pkey PRIMARY KEY (id)
);

-- usuario definition

-- Drop table

-- DROP TABLE usuario;

CREATE TABLE usuario (
	id bigserial NOT NULL,
	data_atualizacao timestamp NULL,
	data_criacao timestamp NULL,
	email varchar(255) NULL,
	login varchar(255) NULL,
	nome varchar(255) NULL,
	"password" varchar(255) NULL,
	"role" varchar(255) NOT NULL,
	status varchar(255) NOT NULL,
	CONSTRAINT usuario_pkey PRIMARY KEY (id)
);


-- DROP TABLE cliente;

CREATE TABLE cliente (
	id bigserial NOT NULL,
	celular varchar(255) NULL,
	cpf_cnpj varchar(255) NULL,
	telefone varchar(255) NULL,
	tipo varchar(255) NOT NULL,
	usuario_id int8 NULL,
	CONSTRAINT cliente_pkey PRIMARY KEY (id)
);


-- cliente foreign keys

ALTER TABLE cliente ADD CONSTRAINT fkc3u631ocxdrtm3ccpme0kjlmu FOREIGN KEY (usuario_id) REFERENCES usuario(id);


-- DROP TABLE endereco;

CREATE TABLE endereco (
	id bigserial NOT NULL,
	bairro varchar(255) NULL,
	cep varchar(255) NULL,
	cidade varchar(255) NULL,
	complemento varchar(255) NULL,
	logradouro varchar(255) NULL,
	numero varchar(255) NULL,
	uf varchar(255) NULL,
	cliente_id int8 NULL,
	CONSTRAINT endereco_pkey PRIMARY KEY (id)
);


-- endereco foreign keys

ALTER TABLE endereco ADD CONSTRAINT fk8s7ivtl4foyhrfam9xqom73n9 FOREIGN KEY (cliente_id) REFERENCES cliente(id);

-- endereco_de_entrega definition

-- Drop table

-- DROP TABLE endereco_de_entrega;

CREATE TABLE endereco_de_entrega (
	id bigserial NOT NULL,
	bairro varchar(255) NULL,
	cep varchar(255) NULL,
	cidade varchar(255) NULL,
	complemento varchar(255) NULL,
	logradouro varchar(255) NULL,
	numero varchar(255) NULL,
	uf varchar(255) NULL,
	CONSTRAINT endereco_de_entrega_pkey PRIMARY KEY (id)
);


-- pagamento definition

-- Drop table

-- DROP TABLE pagamento;

CREATE TABLE pagamento (
	id bigserial NOT NULL,
	data_pagamento timestamp NULL,
	data_vencimento timestamp NULL,
	estatus varchar(255) NOT NULL,
	numero_de_parcelas int4 NULL,
	tipo varchar(255) NOT NULL,
	CONSTRAINT pagamento_pkey PRIMARY KEY (id)
);

-- pedido definition

-- Drop table

-- DROP TABLE pedido;

CREATE TABLE pedido (
	id bigserial NOT NULL,
	data_de_criacao timestamp NULL,
	data_fechamento timestamp NULL,
	estatus varchar(255) NOT NULL,
	valor_desconto numeric(19, 2) NULL,
	valor_frete numeric(19, 2) NULL,
	valor_total numeric(19, 2) NULL,
	cliente_id int8 NOT NULL,
	endereco_id int8 NOT NULL,
	pagamento_id int8 NOT NULL,
	CONSTRAINT pedido_pkey PRIMARY KEY (id)
);

-- pedido foreign keys

ALTER TABLE pedido ADD CONSTRAINT fk30s8j2ktpay6of18lbyqn3632 FOREIGN KEY (cliente_id) REFERENCES cliente(id);
ALTER TABLE pedido ADD CONSTRAINT fkd8wwc5gy5hnijj7iki22jeorn FOREIGN KEY (pagamento_id) REFERENCES pagamento(id);
ALTER TABLE pedido ADD CONSTRAINT fkrff8anq79ua154e28h3l7usnk FOREIGN KEY (endereco_id) REFERENCES endereco_de_entrega(id);

-- produto definition

-- Drop table

-- DROP TABLE produto;

CREATE TABLE produto (
	id bigserial NOT NULL,
	altura varchar(255) NULL,
	codigo_barras varchar(255) NULL,
	comprimento varchar(255) NULL,
	descricao varchar(255) NULL,
	estrelas int4 NULL,
	largura varchar(255) NULL,
	peso varchar(255) NULL,
	preco_atacado numeric(19, 2) NOT NULL,
	preco_varejo numeric(19, 2) NOT NULL,
	quantidade int4 NOT NULL,
	titulo varchar(255) NULL,
	CONSTRAINT produto_pkey PRIMARY KEY (id)
);

-- imagem_produto definition

-- Drop table

-- DROP TABLE imagem_produto;

CREATE TABLE imagem_produto (
	id bigserial NOT NULL,
	hash varchar(255) NULL,
	nome varchar(255) NULL,
	objecturl varchar(255) NULL,
	"size" float8 NOT NULL,
	tam varchar(255) NULL,
	produto_id int8 NULL,
	CONSTRAINT imagem_produto_pkey PRIMARY KEY (id)
);
-- imagem_produto foreign keys

ALTER TABLE imagem_produto ADD CONSTRAINT fkjqedldpqac4hasxuw3evjr9im FOREIGN KEY (produto_id) REFERENCES produto(id);

-- produto_categoria definition

-- Drop table

-- DROP TABLE produto_categoria;

CREATE TABLE produto_categoria (
	produto_id int8 NOT NULL,
	categoria_id int8 NOT NULL
);


-- produto_categoria foreign keys

ALTER TABLE produto_categoria ADD CONSTRAINT fk1c0y58d3n6x3m6euv2j3h64vt FOREIGN KEY (produto_id) REFERENCES produto(id);
ALTER TABLE produto_categoria ADD CONSTRAINT fkq3g33tp7xk2juh53fbw6y4y57 FOREIGN KEY (categoria_id) REFERENCES categoria(id);

-- item_pedido definition

-- Drop table

-- DROP TABLE item_pedido;

CREATE TABLE item_pedido (
	id bigserial NOT NULL,
	data_venda timestamp NULL,
	quantidade_vendida int4 NOT NULL,
	pedido_id int8 NULL,
	produto_id int8 NULL,
	CONSTRAINT item_pedido_pkey PRIMARY KEY (id)
);


-- item_pedido foreign keys

ALTER TABLE item_pedido ADD CONSTRAINT fk60ym08cfoysa17wrn1swyiuda FOREIGN KEY (pedido_id) REFERENCES pedido(id);
ALTER TABLE item_pedido ADD CONSTRAINT fktk55mn6d6bvl5h0no5uagi3sf FOREIGN KEY (produto_id) REFERENCES produto(id);