// Banco de Dados - Buy.it
SET SERVEROUTPUT ON;

-- Tabela Categoria OK
DROP TABLE Categoria CASCADE CONSTRAINTS;
CREATE TABLE Categoria (
    id_categoria NUMBER(8) CONSTRAINT categoria_id_categoria_pk PRIMARY KEY,
    nome_categoria VARCHAR2(255) CONSTRAINT categoria_nome_categoria_nn NOT NULL,
    icone_categoria VARCHAR2(255) CONSTRAINT categoria_icone_categoria_nn NOT NULL
);

INSERT INTO Categoria (id_categoria, nome_categoria, icone_categoria)
VALUES (1, 'Eletrônicos', 'icone-eletronicos.png');
INSERT INTO Categoria (id_categoria, nome_categoria, icone_categoria)
VALUES (2, 'Moda', 'icone-moda.png');
INSERT INTO Categoria (id_categoria, nome_categoria, icone_categoria)
VALUES (3, 'Móveis', 'icone-moveis.png');
INSERT INTO Categoria (id_categoria, nome_categoria, icone_categoria)
VALUES (4, 'Esportes', 'icone-esportes.png');
INSERT INTO Categoria (id_categoria, nome_categoria, icone_categoria)
VALUES (5, 'Alimentos', 'icone-alimentos.png');


-- Tabela Produto OK
DROP TABLE Produto CASCADE CONSTRAINTS;
CREATE TABLE Produto (
    id_produto NUMBER(8) CONSTRAINT produto_id_produto_pk PRIMARY KEY,
    nome_produto VARCHAR2(255) CONSTRAINT produto_nm_produto_nn NOT NULL,
    descricao_produto VARCHAR2(255) CONSTRAINT produto_ds_produto_nn NOT NULL,
    imagem_url_produto VARCHAR2(255) CONSTRAINT produto_img_url_produto_nn NOT NULL,
    id_categoria NUMBER(8) CONSTRAINT produto_id_categoria_fk REFERENCES Categoria,
    marca_produto VARCHAR2(255) CONSTRAINT produto_marca_nn NOT NULL,
    menor_preco_produto NUMBER(10,2) CONSTRAINT produto_menor_preco_produto_nn NOT NULL
);

INSERT INTO Produto (id_produto, nome_produto, descricao_produto, imagem_url_produto, id_categoria, marca_produto, menor_preco_produto)
VALUES (1, 'Smartphone', 'Um smartphone de última geração', 'imagem-smartphone.png', 1, 'Apple', 799.99);
INSERT INTO Produto (id_produto, nome_produto, descricao_produto, imagem_url_produto, id_categoria, marca_produto, menor_preco_produto)
VALUES (2, 'Camiseta', 'Camiseta de algodão', 'imagem-camiseta.png', 2, 'Nike', 19.99);
INSERT INTO Produto (id_produto, nome_produto, descricao_produto, imagem_url_produto, id_categoria, marca_produto, menor_preco_produto)
VALUES (3, 'Sofá', 'Sofá confortável para sala de estar', 'imagem-sofa.png', 3, 'IKEA', 499.99);
INSERT INTO Produto (id_produto, nome_produto, descricao_produto, imagem_url_produto, id_categoria, marca_produto, menor_preco_produto)
VALUES (4, 'Bola de Futebol', 'Bola oficial da FIFA', 'imagem-bola-futebol.png', 4, 'Adidas', 29.99);
INSERT INTO Produto (id_produto, nome_produto, descricao_produto, imagem_url_produto, id_categoria, marca_produto, menor_preco_produto)
VALUES (5, 'Cereais de Café da Manhã', 'Pacote de cereais saborosos', 'imagem-cereais.png', 5, 'Kelloggs', 4.99);

-- Tabela Tipo_Variacao OK
DROP TABLE Tipo_Variacao CASCADE CONSTRAINTS;
CREATE TABLE Tipo_Variacao (
    id_tipo_variacao NUMBER(8) CONSTRAINT tipo_variacao_id_pk PRIMARY KEY,
    nome_variacao VARCHAR2(255) CONSTRAINT variacao_nome_variacao_nn NOT NULL
);

INSERT INTO Tipo_Variacao (id_tipo_variacao, nome_variacao)
VALUES (1, 'Cor');
INSERT INTO Tipo_Variacao (id_tipo_variacao, nome_variacao)
VALUES (2, 'Tamanho');
INSERT INTO Tipo_Variacao (id_tipo_variacao, nome_variacao)
VALUES (3, 'Validade');
INSERT INTO Tipo_Variacao (id_tipo_variacao, nome_variacao)
VALUES (4, 'Peso');
INSERT INTO Tipo_Variacao (id_tipo_variacao, nome_variacao)
VALUES (5, 'Material');


-- Tabela Valor_Variacao REVER
DROP TABLE Valor_Variacao CASCADE CONSTRAINTS;
CREATE TABLE Valor_Variacao (
    id_valor_variacao NUMBER(8) CONSTRAINT valor_variacao_id_pk PRIMARY KEY,
    nome_valor_variacao VARCHAR2(255) CONSTRAINT valor_variacao_nome_nn NOT NULL,
    id_tipo_variacao NUMBER(8) CONSTRAINT opcao_variacao_id_variacao_fk REFERENCES Tipo_Variacao
);

INSERT INTO Valor_Variacao (id_valor_variacao, nome_valor_variacao, id_tipo_variacao)
VALUES (1, 'Preto', 1);
INSERT INTO Valor_Variacao (id_valor_variacao, nome_valor_variacao, id_tipo_variacao)
VALUES (2, 'Branco', 1);
INSERT INTO Valor_Variacao (id_valor_variacao, nome_valor_variacao, id_tipo_variacao)
VALUES (3, 'Vermelho', 1);
INSERT INTO Valor_Variacao (id_valor_variacao, nome_valor_variacao, id_tipo_variacao)
VALUES (4, 'Azul', 1);
INSERT INTO Valor_Variacao (id_valor_variacao, nome_valor_variacao, id_tipo_variacao)
VALUES (5, 'Amarelo', 1);


-- Tabela Usuario OK
DROP TABLE Usuario CASCADE CONSTRAINTS;
CREATE TABLE Usuario (
    id_usuario NUMBER(8) CONSTRAINT usuario_id_usuario_pk PRIMARY KEY,
    cnpj_usuario CHAR(18) CONSTRAINT usuario_cnpj_usuario_nn NOT NULL,
    nome_usuario VARCHAR2(255) CONSTRAINT usuario_nome_usuario_nn NOT NULL,
    cep_usuario CHAR(9) CONSTRAINT usuario_cep_usuario_nn NOT NULL,
    logradouro_usuario VARCHAR2(255) CONSTRAINT usuario_logradouro_usuario_nn NOT NULL,
    complemento_usuario VARCHAR2(50) CONSTRAINT usuario_complemento_usuario_nn NOT NULL,
    numero_endereco_usuario NUMBER(8) CONSTRAINT usuario_numero_endereco_nn NOT NULL,
    email_usuario VARCHAR2(255) CONSTRAINT usuario_email_usuario_nn NOT NULL,
    senha_usuario VARCHAR2(20) CONSTRAINT usuario_senha_usuario_nn NOT NULL,
    telefone_usuario VARCHAR2(15) CONSTRAINT usuario_tel_usuario_nn NOT NULL,
    e_fornecedor NUMBER(1) CONSTRAINT usuario_e_fornecedor_nn NOT NULL,
    imagem_url_usuario VARCHAR2(255) CONSTRAINT usuario_img_url_usuario_nn NOT NULL,
    regime_tributario_usuario VARCHAR2(255) CONSTRAINT usuario_regime_tributario_nn NOT NULL,
    valor_max_automatico_usuario NUMBER(10,2) CONSTRAINT usuario_valor_max_automatico_nn NOT NULL
);


INSERT INTO Usuario (id_usuario, cnpj_usuario, nome_usuario, cep_usuario, logradouro_usuario, complemento_usuario, numero_endereco_usuario, email_usuario, senha_usuario, telefone_usuario, e_fornecedor, imagem_url_usuario, regime_tributario_usuario, valor_max_automatico_usuario)
VALUES (1, '33344455566677', 'Laura Rodrigues', '54321-999', 'Rua D', 'Casa 303', 101, 'laura@example.com', 'senha456', '(555) 987-6543', 0, 'laura.png', 'Simples Nacional', 50000.00);
INSERT INTO Usuario (id_usuario, cnpj_usuario, nome_usuario, cep_usuario, logradouro_usuario, complemento_usuario, numero_endereco_usuario, email_usuario, senha_usuario, telefone_usuario, e_fornecedor, imagem_url_usuario, regime_tributario_usuario, valor_max_automatico_usuario)
VALUES (2, '77788899900011', 'Marcos Oliveira', '12345-678', 'Rua E', 'Apto 404', 202, 'marcos@example.com', 'senha789', '(999) 111-2222', 1, 'marcos.png', 'Lucro Real', 75000.00);
INSERT INTO Usuario (id_usuario, cnpj_usuario, nome_usuario, cep_usuario, logradouro_usuario, complemento_usuario, numero_endereco_usuario, email_usuario, senha_usuario, telefone_usuario, e_fornecedor, imagem_url_usuario, regime_tributario_usuario, valor_max_automatico_usuario)
VALUES (3, '44455566677788', 'Ana Pereira', '98765-432', 'Rua F', 'Casa 505', 303, 'ana@example.com', 'senha123', '(777) 222-3333', 0, 'ana.png', 'Simples Nacional', 55000.00);
INSERT INTO Usuario (id_usuario, cnpj_usuario, nome_usuario, cep_usuario, logradouro_usuario, complemento_usuario, numero_endereco_usuario, email_usuario, senha_usuario, telefone_usuario, e_fornecedor, imagem_url_usuario, regime_tributario_usuario, valor_max_automatico_usuario)
VALUES (4, '99900011122233', 'Paulo Santos', '87654-321', 'Rua G', 'Apto 606', 404, 'paulo@example.com', 'senha456', '(888) 333-4444', 1, 'paulo.png', 'Lucro Real', 80000.00);
INSERT INTO Usuario (id_usuario, cnpj_usuario, nome_usuario, cep_usuario, logradouro_usuario, complemento_usuario, numero_endereco_usuario, email_usuario, senha_usuario, telefone_usuario, e_fornecedor, imagem_url_usuario, regime_tributario_usuario, valor_max_automatico_usuario)
VALUES (5, '11122233344455', 'Isabela Lima', '34567-890', 'Rua H', 'Casa 707', 505, 'isabela@example.com', 'senha789', '(999) 555-6666', 0, 'isabela.png', 'Simples Nacional', 60000.00);


-- Tabela Estoque OK
DROP TABLE Estoque CASCADE CONSTRAINTS;
CREATE TABLE Estoque (
    id_estoque NUMBER(8) CONSTRAINT estoque_id_estoque_pk PRIMARY KEY,
    id_produto NUMBER(8) CONSTRAINT estoque_id_produto_fk REFERENCES Produto,
    id_valor_variacao NUMBER(8) CONSTRAINT estoque_id_valor_variacao_fk REFERENCES Valor_Variacao,
    quantidade_estoque NUMBER(8) CONSTRAINT estoque_qtd_estoque_nn NOT NULL,
    preco_unitario NUMBER(10,2) CONSTRAINT estoque_preco_unitario_nn NOT NULL,
    imagem_url_estoque VARCHAR2(255) CONSTRAINT estoque_img_url_estoque_nn NOT NULL,
    id_usuario NUMBER(8) CONSTRAINT estoque_id_usuario_fk REFERENCES Usuario
);

INSERT INTO Estoque (id_estoque, id_produto, id_valor_variacao, quantidade_estoque, preco_unitario, imagem_url_estoque, id_usuario)
VALUES (1, 1, 1, 100, 799.99, 'imagem-produto1.png', 1);
INSERT INTO Estoque (id_estoque, id_produto, id_valor_variacao, quantidade_estoque, preco_unitario, imagem_url_estoque, id_usuario)
VALUES (2, 2, 4, 50, 29.99, 'imagem-produto2.png', 2);
INSERT INTO Estoque (id_estoque, id_produto, id_valor_variacao, quantidade_estoque, preco_unitario, imagem_url_estoque, id_usuario)
VALUES (3, 3, 5, 30, 499.99, 'imagem-produto3.png', 3);
INSERT INTO Estoque (id_estoque, id_produto, id_valor_variacao, quantidade_estoque, preco_unitario, imagem_url_estoque, id_usuario)
VALUES (4, 4, 1, 75, 19.99, 'imagem-produto4.png', 4);
INSERT INTO Estoque (id_estoque, id_produto, id_valor_variacao, quantidade_estoque, preco_unitario, imagem_url_estoque, id_usuario)
VALUES (5, 5, 3, 200, 4.99, 'imagem-produto5.png', 5);


-- Tabela Desconto OK
DROP TABLE Desconto CASCADE CONSTRAINTS;
CREATE TABLE Desconto (
    id_desconto NUMBER(8) CONSTRAINT desconto_id_desconto_pk PRIMARY KEY,
    quantidade_minima_produto NUMBER(8) CONSTRAINT desconto_qtd_min_produto_nn NOT NULL,
    desconto NUMBER(5,2) CONSTRAINT desconto_desconto_nn NOT NULL,
    id_estoque NUMBER(8) CONSTRAINT desconto_id_estoque_fk REFERENCES Estoque
);

INSERT INTO Desconto (id_desconto, quantidade_minima_produto, desconto, id_estoque)
VALUES (1, 5, 10.00, 1);
INSERT INTO Desconto (id_desconto, quantidade_minima_produto, desconto, id_estoque)
VALUES (2, 10, 20.00, 2);
INSERT INTO Desconto (id_desconto, quantidade_minima_produto, desconto, id_estoque)
VALUES (3, 15, 15.00, 3);
INSERT INTO Desconto (id_desconto, quantidade_minima_produto, desconto, id_estoque)
VALUES (4, 7, 12.50, 4);
INSERT INTO Desconto (id_desconto, quantidade_minima_produto, desconto, id_estoque)
VALUES (5, 12, 25.00, 5);



-- Tabela Tag OK
DROP TABLE Tag CASCADE CONSTRAINTS;
CREATE TABLE Tag (
    id_tag NUMBER(8) CONSTRAINT tag_id_tag_pk PRIMARY KEY,
    nome_tag VARCHAR2(255) CONSTRAINT tag_nome_tag_nn NOT NULL
);

INSERT INTO Tag (id_tag, nome_tag)
VALUES (1, 'Eletrônicos');
INSERT INTO Tag (id_tag, nome_tag)
VALUES (2, 'Roupas');
INSERT INTO Tag (id_tag, nome_tag)
VALUES (3, 'Móveis');
INSERT INTO Tag (id_tag, nome_tag)
VALUES (4, 'Decoração');
INSERT INTO Tag (id_tag, nome_tag)
VALUES (5, 'Saúde');


-- Tabela usuario_tag_possui OK
DROP TABLE usuario_tag CASCADE CONSTRAINTS;
CREATE TABLE usuario_tag (
    id_tag NUMBER(8) CONSTRAINT possui_tag_id_fk REFERENCES Tag,
    id_usuario NUMBER(8) CONSTRAINT possui_usuario_id_fk REFERENCES Usuario
);

INSERT INTO usuario_tag (id_tag, id_usuario)
VALUES (1, 3);
INSERT INTO usuario_tag (id_tag, id_usuario)
VALUES (2, 1);
INSERT INTO usuario_tag (id_tag, id_usuario)
VALUES (3, 2);
INSERT INTO usuario_tag (id_tag, id_usuario)
VALUES (4, 5);
INSERT INTO usuario_tag (id_tag, id_usuario)
VALUES (5, 4);


-- Tabela Pedido OK
DROP TABLE Pedido CASCADE CONSTRAINTS;
CREATE TABLE Pedido (
    id_pedido NUMBER(8) CONSTRAINT pedido_id_pedido_pk PRIMARY KEY,
    id_usuario NUMBER(8) CONSTRAINT pedido_usuario_id_fk REFERENCES Usuario,
    status_pedido VARCHAR2(255) CONSTRAINT pedido_status_nn NOT NULL,
    data_pedido DATE CONSTRAINT pedido_data_nn NOT NULL,
    valor_pedido NUMBER(10,2) CONSTRAINT pedido_valor_nn NOT NULL
);

INSERT INTO Pedido (id_pedido, id_usuario, status_pedido, data_pedido, valor_pedido)
VALUES (1, 3, 'Em andamento', TO_DATE('2023-10-16', 'YYYY-MM-DD'), 150.00);
INSERT INTO Pedido (id_pedido, id_usuario, status_pedido, data_pedido, valor_pedido)
VALUES (2, 2, 'Concluído', TO_DATE('2023-10-15', 'YYYY-MM-DD'), 250.00);
INSERT INTO Pedido (id_pedido, id_usuario, status_pedido, data_pedido, valor_pedido)
VALUES (3, 1, 'Em andamento', TO_DATE('2023-10-14', 'YYYY-MM-DD'), 200.00);
INSERT INTO Pedido (id_pedido, id_usuario, status_pedido, data_pedido, valor_pedido)
VALUES (4, 5, 'Concluído', TO_DATE('2023-10-13', 'YYYY-MM-DD'), 175.50);
INSERT INTO Pedido (id_pedido, id_usuario, status_pedido, data_pedido, valor_pedido)
VALUES (5, 4, 'Em andamento', TO_DATE('2023-10-12', 'YYYY-MM-DD'), 300.00);


-- Tabela pedido_estoque_contem OK
DROP TABLE pedido_estoque CASCADE CONSTRAINTS;
CREATE TABLE pedido_estoque (
    id_pedido NUMBER(8) CONSTRAINT contem_pedido_id_fk REFERENCES Pedido,
    id_estoque NUMBER(8) CONSTRAINT contem_estoque_id_fk REFERENCES Estoque
);

INSERT INTO pedido_estoque (id_pedido, id_estoque)
VALUES (1, 1);
INSERT INTO pedido_estoque (id_pedido, id_estoque)
VALUES (2, 2);
INSERT INTO pedido_estoque (id_pedido, id_estoque)
VALUES (3, 3);
INSERT INTO pedido_estoque (id_pedido, id_estoque)
VALUES (4, 4);
INSERT INTO pedido_estoque (id_pedido, id_estoque)
VALUES (5, 5);

-- Tabela Log OK
DROP TABLE Log CASCADE CONSTRAINTS;
CREATE TABLE Log (
    id_log NUMBER(8) CONSTRAINT log_id_log_pk PRIMARY KEY,
    id_pedido NUMBER(8) CONSTRAINT log_pedido_id_fk REFERENCES Pedido,
    timestamp_log VARCHAR2(20) CONSTRAINT log_timestamp_nn NOT NULL,
    nome_log VARCHAR2(255) CONSTRAINT log_nome_log_nn NOT NULL,
    descricao_log VARCHAR2(255) CONSTRAINT log_descricao_log_nn NOT NULL
);

INSERT INTO Log (id_log, id_pedido, timestamp_log, nome_log, descricao_log)
VALUES (1, 1, '2023-10-16 10:15:00', 'Pedido Criado', 'Um novo pedido foi criado');
INSERT INTO Log (id_log, id_pedido, timestamp_log, nome_log, descricao_log)
VALUES (2, 2, '2023-10-15 14:30:00', 'Pedido Concluído', 'O pedido foi concluído e enviado');
INSERT INTO Log (id_log, id_pedido, timestamp_log, nome_log, descricao_log)
VALUES (3, 3, '2023-10-14 09:45:00', 'Pedido Atualizado', 'O status do pedido foi atualizado');
INSERT INTO Log (id_log, id_pedido, timestamp_log, nome_log, descricao_log)
VALUES (4, 4, '2023-10-13 16:20:00', 'Pagamento Recebido', 'O pagamento do pedido foi recebido');
INSERT INTO Log (id_log, id_pedido, timestamp_log, nome_log, descricao_log)
VALUES (5, 5, '2023-10-12 11:55:00', 'Pedido Enviado', 'O pedido foi enviado ao destinatário');



-- Tabela Avaliacao OK
DROP TABLE Avaliacao CASCADE CONSTRAINTS;
CREATE TABLE Avaliacao (
    id_avaliacao NUMBER(8) CONSTRAINT avaliacao_id_avaliacao_pk PRIMARY KEY,
    id_usuario NUMBER(8) CONSTRAINT avaliacao_usuario_id_fk REFERENCES Usuario,
    id_pedido NUMBER(8) CONSTRAINT avaliacao_pedido_id_fk REFERENCES Pedido,
    nota_preco_avaliacao NUMBER(1) CONSTRAINT avaliacao_nota_preco_nn NOT NULL,
    nota_qualidade_avaliacao NUMBER(1) CONSTRAINT avaliacao_nota_qualidade_nn NOT NULL,
    descricao_avaliacao VARCHAR2(255) CONSTRAINT avaliacao_ds_nn NOT NULL,
    nota_entrega_avaliacao NUMBER(1)CONSTRAINT avaliacao_nota_entrega_nn NOT NULL
);

INSERT INTO Avaliacao (id_avaliacao, id_usuario, id_pedido, nota_preco_avaliacao, nota_qualidade_avaliacao, descricao_avaliacao, nota_entrega_avaliacao)
VALUES (1, 3, 1, 4, 5, 'Excelente produto', 4);
INSERT INTO Avaliacao (id_avaliacao, id_usuario, id_pedido, nota_preco_avaliacao, nota_qualidade_avaliacao, descricao_avaliacao, nota_entrega_avaliacao)
VALUES (2, 2, 2, 3, 4, 'Bom serviço', 5);
INSERT INTO Avaliacao (id_avaliacao, id_usuario, id_pedido, nota_preco_avaliacao, nota_qualidade_avaliacao, descricao_avaliacao, nota_entrega_avaliacao)
VALUES (3, 1, 3, 5, 4, 'Produto de alta qualidade', 5);
INSERT INTO Avaliacao (id_avaliacao, id_usuario, id_pedido, nota_preco_avaliacao, nota_qualidade_avaliacao, descricao_avaliacao, nota_entrega_avaliacao)
VALUES (4, 4, 4, 4, 4, 'Ótima experiência de compra', 4);
INSERT INTO Avaliacao (id_avaliacao, id_usuario, id_pedido, nota_preco_avaliacao, nota_qualidade_avaliacao, descricao_avaliacao, nota_entrega_avaliacao)
VALUES (5, 5, 5, 3, 5, 'Entrega rápida e eficiente', 5);


-- Inner Joins

SELECT
    P.id_pedido,
    U.nome_usuario AS nome_cliente,
    P.data_pedido,
    P.valor_pedido,
    E.id_estoque,
    PR.nome_produto AS nome_produto,
    E.quantidade_estoque,
    E.preco_unitario
FROM Pedido P
JOIN Usuario U ON P.id_usuario = U.id_usuario
JOIN pedido_estoque PC ON P.id_pedido = PC.id_pedido
JOIN Estoque E ON PC.id_estoque = E.id_estoque
JOIN Produto PR ON E.id_produto = PR.id_produto;

SELECT
    P.nome_produto AS produto,
    C.nome_categoria AS categoria,
    V.nome_valor_variacao AS variacao_valor,
    E.quantidade_estoque AS quantidade_em_estoque,
    E.preco_unitario AS preco_unitario
FROM Produto P
JOIN Categoria C ON P.id_categoria = C.id_categoria
JOIN Estoque E ON P.id_produto = E.id_produto
JOIN Valor_Variacao V ON E.id_valor_variacao = V.id_valor_variacao;


