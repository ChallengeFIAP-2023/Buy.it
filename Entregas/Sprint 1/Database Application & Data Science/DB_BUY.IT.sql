// Banco de Dados - Buy.it

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
VALUES (4, 'Eletrodomésticos', 'icone-eletrodomesticos.png');
INSERT INTO Categoria (id_categoria, nome_categoria, icone_categoria)
VALUES (5, 'Livros', 'icone-livros.png');

-- Tabela Produto OK
DROP TABLE Produto CASCADE CONSTRAINTS;
CREATE TABLE Produto (
    id_produto NUMBER(8) CONSTRAINT produto_id_produto_pk PRIMARY KEY,
    nome_produto VARCHAR2(255) CONSTRAINT produto_nm_produto_nn NOT NULL,
    descricao_produto VARCHAR2(255) CONSTRAINT produto_ds_produto_nn NOT NULL,
    imagem_url_produto VARCHAR2(255) CONSTRAINT produto_img_url_produto_nn NOT NULL,
    id_categoria NUMBER(8) CONSTRAINT produto_id_categoria_fk REFERENCES Categoria,
    menor_preco_produto NUMBER(10,2) CONSTRAINT produto_menor_preco_produto_nn NOT NULL
);

INSERT INTO Produto (id_produto, nome_produto, descricao_produto, imagem_url_produto, id_categoria, menor_preco_produto)
VALUES (1, 'Smartphone', 'Um smartphone de última geração', 'imagem-smartphone.png', 1, 799.99);
INSERT INTO Produto (id_produto, nome_produto, descricao_produto, imagem_url_produto, id_categoria, menor_preco_produto)
VALUES (2, 'Camiseta', 'Camiseta de algodão', 'imagem-camiseta.png', 2, 19.99);
INSERT INTO Produto (id_produto, nome_produto, descricao_produto, imagem_url_produto, id_categoria, menor_preco_produto)
VALUES (3, 'Sofá', 'Sofá confortável para sala de estar', 'imagem-sofa.png', 3, 499.99);
INSERT INTO Produto (id_produto, nome_produto, descricao_produto, imagem_url_produto, id_categoria, menor_preco_produto)
VALUES (4, 'Geladeira', 'Geladeira de última geração', 'imagem-geladeira.png', 4, 999.99);
INSERT INTO Produto (id_produto, nome_produto, descricao_produto, imagem_url_produto, id_categoria, menor_preco_produto)
VALUES (5, 'Livro de Ficção', 'Um ótimo livro de ficção', 'imagem-livro.png', 5, 24.99);


-- Tabela Variacao OK
DROP TABLE Variacao CASCADE CONSTRAINTS;
CREATE TABLE Variacao (
    id_variacao NUMBER(8) CONSTRAINT variacao_id_variacao_pk PRIMARY KEY,
    nome_variacao VARCHAR2(255) CONSTRAINT variacao_nome_variacao_nn NOT NULL
);

INSERT INTO Variacao (id_variacao, nome_variacao)
VALUES (1, 'Cor');
INSERT INTO Variacao (id_variacao, nome_variacao)
VALUES (2, 'Tamanho');
INSERT INTO Variacao (id_variacao, nome_variacao)
VALUES (3, 'Acordeão');
INSERT INTO Variacao (id_variacao, nome_variacao)
VALUES (4, 'Peso');
INSERT INTO Variacao (id_variacao, nome_variacao)
VALUES (5, 'Edição');

-- Tabela Opcao_Variacao OK
DROP TABLE Opcao_Variacao CASCADE CONSTRAINTS;
CREATE TABLE Opcao_Variacao (
    id_opcao_variacao NUMBER(8) CONSTRAINT opcao_variacao_id_opcao_variacao_pk PRIMARY KEY,
    nome_opcao_variacao VARCHAR2(255) CONSTRAINT opcao_variacao_nome_opcao_variacao_nn NOT NULL,
    id_variacao NUMBER(8) CONSTRAINT opcao_variacao_id_variacao_fk REFERENCES Variacao
);

INSERT INTO Opcao_Variacao (id_opcao_variacao, nome_opcao_variacao, id_variacao)
VALUES (1, 'Preto', 1);
INSERT INTO Opcao_Variacao (id_opcao_variacao, nome_opcao_variacao, id_variacao)
VALUES (2, 'Branco', 1);
INSERT INTO Opcao_Variacao (id_opcao_variacao, nome_opcao_variacao, id_variacao)
VALUES (3, 'Vermelho', 1);
INSERT INTO Opcao_Variacao (id_opcao_variacao, nome_opcao_variacao, id_variacao)
VALUES (4, '5 kg', 4);
INSERT INTO Opcao_Variacao (id_opcao_variacao, nome_opcao_variacao, id_variacao)
VALUES (5, 'Edição de Luxo', 5);

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
    imagem_url_usuario VARCHAR2(255) CONSTRAINT usuario_img_url_usuario_nn NOT NULL
);

INSERT INTO Usuario (id_usuario, cnpj_usuario, nome_usuario, cep_usuario, logradouro_usuario, complemento_usuario, numero_endereco_usuario, email_usuario, senha_usuario, telefone_usuario, e_fornecedor, imagem_url_usuario)
VALUES (1, '12345678901234', 'João Silva', '12345-678', 'Rua A', 'Apto 101', 123, 'joao@example.com', 'senha123', '(123) 456-7890', 0, 'joao.png');
INSERT INTO Usuario (id_usuario, cnpj_usuario, nome_usuario, cep_usuario, logradouro_usuario, complemento_usuario, numero_endereco_usuario, email_usuario, senha_usuario, telefone_usuario, e_fornecedor, imagem_url_usuario)
VALUES (2, '98765432109876', 'Maria Santos', '54321-876', 'Rua B', 'Casa 102', 456, 'maria@example.com', 'senha456', '(987) 654-3210', 1, 'maria.png');
INSERT INTO Usuario (id_usuario, cnpj_usuario, nome_usuario, cep_usuario, logradouro_usuario, complemento_usuario, numero_endereco_usuario, email_usuario, senha_usuario, telefone_usuario, e_fornecedor, imagem_url_usuario)
VALUES (3, '11122233344455', 'Carlos Oliveira', '54321-654', 'Rua C', 'Apto 202', 789, 'carlos@example.com', 'senha789', '(555) 123-4567', 0, 'carlos.png');
INSERT INTO Usuario (id_usuario, cnpj_usuario, nome_usuario, cep_usuario, logradouro_usuario, complemento_usuario, numero_endereco_usuario, email_usuario, senha_usuario, telefone_usuario, e_fornecedor, imagem_url_usuario)
VALUES (4, '22233344455566', 'Ana Sousa', '67890-123', 'Rua D', 'Apto 303', 456, 'ana@example.com', 'senha456', '(555) 987-6543', 1, 'ana.png');
INSERT INTO Usuario (id_usuario, cnpj_usuario, nome_usuario, cep_usuario, logradouro_usuario, complemento_usuario, numero_endereco_usuario, email_usuario, senha_usuario, telefone_usuario, e_fornecedor, imagem_url_usuario)
VALUES (5, '77788899900011', 'Mariano Rodrigues', '12345-678', 'Rua E', 'Casa 404', 789, 'mariano@example.com', 'senha789', '(555) 234-5678', 0, 'mariano.png');

-- Tabela Estoque OK
DROP TABLE Estoque CASCADE CONSTRAINTS;
CREATE TABLE Estoque (
    id_estoque NUMBER(8) CONSTRAINT estoque_id_estoque_pk PRIMARY KEY,
    id_produto NUMBER(8) CONSTRAINT estoque_id_produto_fk REFERENCES Produto,
    id_variacao_produto NUMBER(8) CONSTRAINT estoque_id_variacao_produto_fk REFERENCES Opcao_Variacao,
    quantidade_estoque NUMBER(8) CONSTRAINT estoque_qtd_estoque_nn NOT NULL,
    preco NUMBER(10,2) CONSTRAINT estoque_preco_nn NOT NULL,
    imagem_url_estoque VARCHAR2(255) CONSTRAINT estoque_img_url_estoque_nn NOT NULL,
    id_usuario NUMBER(8) CONSTRAINT estoque_id_usuario_fk REFERENCES Usuario
);

INSERT INTO Estoque (id_estoque, id_produto, id_variacao_produto, quantidade_estoque, preco, imagem_url_estoque, id_usuario)
VALUES (1, 1, 1, 50, 699.99, 'estoque1.png', 3);
INSERT INTO Estoque (id_estoque, id_produto, id_variacao_produto, quantidade_estoque, preco, imagem_url_estoque, id_usuario)
VALUES (2, 2, 2, 30, 29.99, 'estoque2.png', 2);
INSERT INTO Estoque (id_estoque, id_produto, id_variacao_produto, quantidade_estoque, preco, imagem_url_estoque, id_usuario)
VALUES (3, 3, 1, 25, 599.99, 'estoque3.png', 1);
INSERT INTO Estoque (id_estoque, id_produto, id_variacao_produto, quantidade_estoque, preco, imagem_url_estoque, id_usuario)
VALUES (4, 2, 1, 40, 24.99, 'estoque4.png', 1);
INSERT INTO Estoque (id_estoque, id_produto, id_variacao_produto, quantidade_estoque, preco, imagem_url_estoque, id_usuario)
VALUES (5, 3, 2, 15, 549.99, 'estoque5.png', 2);

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
VALUES (4, 8, 15.00, 1);
INSERT INTO Desconto (id_desconto, quantidade_minima_produto, desconto, id_estoque)
VALUES (5, 12, 25.00, 3);


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
VALUES (4, 'Acessórios');
INSERT INTO Tag (id_tag, nome_tag)
VALUES (5, 'Decoração');

-- Tabela Possui OK
DROP TABLE Possui CASCADE CONSTRAINTS;
CREATE TABLE Possui (
    id_tag NUMBER(8) CONSTRAINT possui_tag_id_fk REFERENCES Tag,
    id_usuario NUMBER(8) CONSTRAINT possui_usuario_id_fk REFERENCES Usuario
);

INSERT INTO Possui (id_tag, id_usuario)
VALUES (1, 3);
INSERT INTO Possui (id_tag, id_usuario)
VALUES (2, 1);
INSERT INTO Possui (id_tag, id_usuario)
VALUES (3, 2);
INSERT INTO Possui (id_tag, id_usuario)
VALUES (4, 1);
INSERT INTO Possui (id_tag, id_usuario)
VALUES (5, 3);

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
VALUES (4, 2, 'Concluído', TO_DATE('2023-10-13', 'YYYY-MM-DD'), 120.00);
INSERT INTO Pedido (id_pedido, id_usuario, status_pedido, data_pedido, valor_pedido)
VALUES (5, 1, 'Em andamento', TO_DATE('2023-10-12', 'YYYY-MM-DD'), 75.00);

-- Tabela Contem OK
DROP TABLE Contem CASCADE CONSTRAINTS;
CREATE TABLE Contem (
    id_pedido NUMBER(8) CONSTRAINT contem_pedido_id_fk REFERENCES Pedido
);

INSERT INTO Contem (id_pedido)
VALUES (1);
INSERT INTO Contem (id_pedido)
VALUES (2);
INSERT INTO Contem (id_pedido)
VALUES (3);
INSERT INTO Contem (id_pedido)
VALUES (4);
INSERT INTO Contem (id_pedido)
VALUES (5);

-- Tabela Item Pedido OK
DROP TABLE Item_Pedido CASCADE CONSTRAINTS;
CREATE TABLE Item_Pedido (
    id_pedido NUMBER(8) CONSTRAINT item_pedido_pedido_id_fk REFERENCES Pedido,
    id_variacao NUMBER(8) CONSTRAINT item_pedido_variacao_id_fk REFERENCES Variacao,
    quantidade_item_pedido NUMBER(8) CONSTRAINT item_pedido_quantidade_item_pedido_nn NOT NULL,
    valor NUMBER(10,2) CONSTRAINT item_pedido_valor_nn NOT NULL
);

INSERT INTO Item_Pedido (id_pedido, id_variacao, quantidade_item_pedido, valor)
VALUES (1, 1, 2, 50.00);
INSERT INTO Item_Pedido (id_pedido, id_variacao, quantidade_item_pedido, valor)
VALUES (2, 2, 3, 75.00);
INSERT INTO Item_Pedido (id_pedido, id_variacao, quantidade_item_pedido, valor)
VALUES (3, 3, 1, 30.00);
INSERT INTO Item_Pedido (id_pedido, id_variacao, quantidade_item_pedido, valor)
VALUES (4, 3, 2, 60.00);
INSERT INTO Item_Pedido (id_pedido, id_variacao, quantidade_item_pedido, valor)
VALUES (5, 2, 4, 120.00);

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
VALUES (1, 1, '2023-10-16 10:30:00', 'Pedido Entregue', 'O pedido foi entregue com sucesso.');
INSERT INTO Log (id_log, id_pedido, timestamp_log, nome_log, descricao_log)
VALUES (2, 2, '2023-10-15 11:45:00', 'Pagamento Confirmado', 'O pagamento do pedido foi confirmado.');
INSERT INTO Log (id_log, id_pedido, timestamp_log, nome_log, descricao_log)
VALUES (3, 3, '2023-10-14 09:15:00', 'Pedido Enviado', 'O pedido foi enviado para entrega.');
INSERT INTO Log (id_log, id_pedido, timestamp_log, nome_log, descricao_log)
VALUES (4, 4, '2023-10-16 10:30:00', 'Pedido Entregue', 'O pedido foi entregue com sucesso.');
INSERT INTO Log (id_log, id_pedido, timestamp_log, nome_log, descricao_log)
VALUES (5, 5, '2023-10-15 11:45:00', 'Pagamento Confirmado', 'O pagamento do pedido foi confirmado.');

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
VALUES (4, 3, 4, 5, 5, 'Produto incrível!', 5);
INSERT INTO Avaliacao (id_avaliacao, id_usuario, id_pedido, nota_preco_avaliacao, nota_qualidade_avaliacao, descricao_avaliacao, nota_entrega_avaliacao)
VALUES (5, 2, 5, 4, 4, 'Bom atendimento', 4);

-- Tabela Gera OK
DROP TABLE Gera CASCADE CONSTRAINTS;
CREATE TABLE Gera (
    id_pedido NUMBER(8) CONSTRAINT gera_pedido_id_fk REFERENCES Pedido,
    id_avaliacao NUMBER(8) CONSTRAINT gera_avaliacao_id_fk REFERENCES Avaliacao
);

INSERT INTO Gera (id_pedido, id_avaliacao)
VALUES (1, 1);
INSERT INTO Gera (id_pedido, id_avaliacao)
VALUES (2, 2);
INSERT INTO Gera (id_pedido, id_avaliacao)
VALUES (3, 3);
INSERT INTO Gera (id_pedido, id_avaliacao)
VALUES (4, 4);
INSERT INTO Gera (id_pedido, id_avaliacao)
VALUES (5, 5);

-- Inner Joins

SELECT 
    P.id_pedido,
    U.nome_usuario AS nome_cliente,
    IP.id_variacao,
    IP.quantidade_item_pedido,
    A.nota_preco_avaliacao,
    A.nota_qualidade_avaliacao
FROM Pedido P
JOIN Usuario U ON P.id_usuario = U.id_usuario
JOIN Item_Pedido IP ON P.id_pedido = IP.id_pedido
LEFT JOIN Avaliacao A ON P.id_pedido = A.id_pedido;

