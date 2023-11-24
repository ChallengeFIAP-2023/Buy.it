-- SPRINT 01

-- 01. CRIAÇÃO DAS TABELAS:
CREATE TABLE pessoa (
    id_pessoa NUMBER(9) CONSTRAINT pk_id_pessoa PRIMARY KEY,
    nome_pessoa VARCHAR2(255) CONSTRAINT nn_nm_pessoa NOT NULL,
    imagem_pessoa VARCHAR2(255)
);

CREATE TABLE pessoa_juridica (
    id_pj NUMBER(9) CONSTRAINT pk_id_pj PRIMARY KEY,
    cnpj_pj CHAR(18) CONSTRAINT uk_cnpj_pj UNIQUE CONSTRAINT nn_cnpj_pj NOT NULL,
    is_fornecedor NUMBER(1) CONSTRAINT nn_is_fornecedor_pj NOT NULL,
    id_pessoa NUMBER(9) CONSTRAINT fk_pessoa_pj REFERENCES pessoa(id_pessoa) CONSTRAINT uk_pessoa_pj UNIQUE CONSTRAINT nn_pessoa_pj NOT NULL
);

CREATE TABLE usuario (
    id_usuario NUMBER(9) CONSTRAINT pk_id_usuario PRIMARY KEY,
    email_usuario VARCHAR2(255) CONSTRAINT uk_email_usuario UNIQUE CONSTRAINT nn_email_usuario NOT NULL,
    senha_usuario VARCHAR2(255) CONSTRAINT nn_senha_usuario NOT NULL,
    id_pessoa NUMBER(9) CONSTRAINT fk_pessoa_usuario REFERENCES pessoa(id_pessoa) CONSTRAINT uk_pessoa_usuario UNIQUE CONSTRAINT nn_pessoa_usuario NOT NULL
);

CREATE TABLE tipo_contato (
    id_tipo_contato NUMBER(9) CONSTRAINT pk_id_tipo_contato PRIMARY KEY,
    nome_tipo_contato VARCHAR2(255) CONSTRAINT uk_nm_tipo_contato UNIQUE CONSTRAINT nn_nm_tipo_contato NOT NULL
);

CREATE TABLE forma_contato (
    id_forma_contato NUMBER(9) CONSTRAINT pk_id_forma_contato PRIMARY KEY,
    id_tipo_contato NUMBER(9) CONSTRAINT fk_tp_ctt_forma_contato REFERENCES tipo_contato(id_tipo_contato) CONSTRAINT nn_tp_ctt_forma_contato NOT NULL,
    valor_forma_contato VARCHAR2(255) CONSTRAINT nn_vlr_forma_contato NOT NULL,
    id_pessoa NUMBER(9) CONSTRAINT fk_pessoa_forma_contato REFERENCES pessoa(id_pessoa) CONSTRAINT nn_pessoa_forma_contato NOT NULL
);

CREATE TABLE tag (
    id_tag NUMBER(9) CONSTRAINT pk_id_tag PRIMARY KEY,
    nome_tag VARCHAR2(255) CONSTRAINT uk_nm_tag UNIQUE CONSTRAINT nn_nm_tag NOT NULL
);

CREATE TABLE usuario_tag (
    id_usuario NUMBER(9) CONSTRAINT fk_id_usuario_tag REFERENCES usuario(id_usuario) CONSTRAINT nn_id_usuario_tag NOT NULL,
    id_tag NUMBER(9) CONSTRAINT fk_id_tag_usuario REFERENCES tag(id_tag) CONSTRAINT nn_id_tag_usuario NOT NULL
);

CREATE TABLE departamento (
    id_departamento NUMBER(9) CONSTRAINT pk_id_departamento PRIMARY KEY,
    nome_departamento VARCHAR2(255) CONSTRAINT uk_nm_departamento UNIQUE CONSTRAINT nn_nm_departamento NOT NULL,
    icone_departamento VARCHAR2(255)
);

CREATE TABLE tag_departamento (
    id_tag NUMBER(9) CONSTRAINT fk_id_tag_departamento REFERENCES tag(id_tag) CONSTRAINT nn_id_tag_departamento NOT NULL,
    id_departamento NUMBER(9) CONSTRAINT fk_id_departamento_tag REFERENCES departamento(id_departamento) CONSTRAINT nn_id_departamento_tag NOT NULL
);

CREATE TABLE produto (
    id_produto NUMBER(9) CONSTRAINT pk_id_produto PRIMARY KEY,
    id_departamento NUMBER(9) CONSTRAINT fk_departamento_produto REFERENCES departamento(id_departamento),
    nome_produto VARCHAR2(255) CONSTRAINT nn_nm_produto NOT NULL,
    marca_produto VARCHAR2(255),
    cor_produto VARCHAR2(255),
    tamanho_produto VARCHAR2(255),
    material_produto VARCHAR2(255),
    observacao_produto VARCHAR2(255)
);

CREATE TABLE produto_tag (
    id_produto NUMBER(9) CONSTRAINT fk_id_produto_tag REFERENCES produto(id_produto) CONSTRAINT nn_id_produto_tag NOT NULL,
    id_tag NUMBER(9) CONSTRAINT fk_id_tag_produto REFERENCES tag(id_tag) CONSTRAINT nn_id_tag_produto NOT NULL
);

CREATE TABLE status (
    id_status NUMBER(9) CONSTRAINT pk_id_status PRIMARY KEY,
    nome_status VARCHAR2(255) CONSTRAINT uk_nm_status UNIQUE CONSTRAINT nn_nm_status NOT NULL
);

CREATE TABLE cotacao (
    id_cotacao NUMBER(9) CONSTRAINT pk_id_cotacao PRIMARY KEY,
    data_abertura_cotacao DATE CONSTRAINT nn_dt_cotacao NOT NULL,
    id_comprador NUMBER(9) CONSTRAINT fk_comprador_cotacao REFERENCES usuario(id_usuario) CONSTRAINT nn_comprador_cotacao NOT NULL,
    id_produto NUMBER(9) CONSTRAINT fk_produto_cotacao REFERENCES produto(id_produto) CONSTRAINT nn_produto_cotacao NOT NULL,
    quantidade_produto NUMBER(8,2) CONSTRAINT nn_qtd_produto NOT NULL,
    valor_produto NUMBER(8,2) CONSTRAINT nn_vlr_produto NOT NULL,
    id_status NUMBER(9) CONSTRAINT fk_status_cotacao REFERENCES status(id_status) CONSTRAINT nn_status_cotacao NOT NULL,
    prioridade_entrega NUMBER(1) CONSTRAINT nn_pri_entr_cotacao NOT NULL,
    prioridade_qualidade NUMBER(1) CONSTRAINT nn_pri_qual_cotacao NOT NULL,
    prioridade_preco NUMBER(1) CONSTRAINT nn_pri_prec_cotacao NOT NULL,
    prazo_cotacao NUMBER(3) CONSTRAINT nn_prazo_cotacao NOT NULL,
    data_fechamento_cotacao DATE
);

CREATE TABLE avaliacao (
    id_avaliacao NUMBER(9) CONSTRAINT pk_id_avaliacao PRIMARY KEY,
    id_cotacao NUMBER(9) CONSTRAINT fk_cotacao_avaliacao REFERENCES cotacao(id_cotacao) CONSTRAINT uk_cotacao_avaliacao UNIQUE CONSTRAINT nn_cotacao_avaliacao NOT NULL,
    data_avaliacao DATE CONSTRAINT nn_dt_avaliacao NOT NULL,
    nota_entrega_avaliacao NUMBER(1) CONSTRAINT nn_nota_entr_avaliacao NOT NULL, 
    nota_qualidade_avaliacao NUMBER(1) CONSTRAINT nn_nota_qual_avaliacao NOT NULL,
    nota_preco_avaliacao NUMBER(1) CONSTRAINT nn_nota_prec_avaliacao NOT NULL,
    descricao_avaliacao VARCHAR2(255)
);

CREATE TABLE historico (
    id_historico NUMBER(9) CONSTRAINT pk_id_historico PRIMARY KEY,
    id_cotacao NUMBER(9) CONSTRAINT fk_cotacao_historico REFERENCES cotacao(id_cotacao) CONSTRAINT nn_cotacao_historico NOT NULL,
    data_historico DATE CONSTRAINT nn_dt_historico NOT NULL,
    id_status NUMBER(9) CONSTRAINT fk_status_historico REFERENCES status(id_status) CONSTRAINT nn_status_historico NOT NULL,
    id_fornecedor NUMBER(9) CONSTRAINT fk_fornecedor_historico REFERENCES usuario(id_usuario) CONSTRAINT nn_fornecedor_historico NOT NULL,
    valor_ofertado_historico NUMBER(8,2),
    recusado_por_produto NUMBER(1) CONSTRAINT nn_rec_prod_historico NOT NULL,
    recusado_por_quantidade NUMBER(1) CONSTRAINT nn_rec_qtd_historico NOT NULL,
    recusado_por_preco NUMBER(1) CONSTRAINT nn_rec_prec_historico NOT NULL,
    recusado_por_prazo NUMBER(1) CONSTRAINT nn_rec_praz_historico NOT NULL,
    descricao_historico VARCHAR2(255)
);

-- 02. INSERTS NAS TABELAS:
INSERT INTO pessoa VALUES (1, 'One Serviços Administrativos LTDA.', NULL);
INSERT INTO pessoa VALUES (2, 'Kalunga Comércio e Indústria Gráfica LTDA.', 'https://iguatemi.com.br/brasilia/sites/brasilia/files/2020-01/Kalunga_logo.png');
INSERT INTO pessoa VALUES (3, 'Kabum S.A.', NULL);
INSERT INTO pessoa VALUES (4, 'Kuará Capital Gestora de Recursos LTDA.', NULL);
INSERT INTO pessoa VALUES (5, 'Magazine Luiza S.A.', 'https://logodownload.org/wp-content/uploads/2014/06/magalu-logo-0.png');

INSERT INTO pessoa_juridica VALUES (1, '28.434.667/0001-11', 0, 1);
INSERT INTO pessoa_juridica VALUES (2, '43.283.811/0001-50', 1, 2);
INSERT INTO pessoa_juridica VALUES (3, '05.570.714/0001-59', 1, 3);
INSERT INTO pessoa_juridica VALUES (4, '41.179.663/0001-00', 0, 4);
INSERT INTO pessoa_juridica VALUES (5, '47.960.950/0001-21', 1, 5);

INSERT INTO usuario VALUES (1, 'comercial@oneservicos.com.br', 'oneserv123', 1);
INSERT INTO usuario VALUES (2, 'vendas@kalunga.com.br', 'kalung4', 2);
INSERT INTO usuario VALUES (3, 'adm@kabum.com.br', 'kabuuuuum', 3);
INSERT INTO usuario VALUES (4, 'operacional@kuaracapital.com', 'farialima', 4);
INSERT INTO usuario VALUES (5, 'magalu@magalu.com.br', 'vempromagalu', 5);

INSERT INTO tipo_contato VALUES (1, 'Email');
INSERT INTO tipo_contato VALUES (2, 'Telefone');
INSERT INTO tipo_contato VALUES (3, 'Celular');
INSERT INTO tipo_contato VALUES (4, 'Whatsapp');
INSERT INTO tipo_contato VALUES (5, 'Fax');

INSERT INTO forma_contato VALUES (1, 1, 'kaue@oneservicos.com.br', 1);
INSERT INTO forma_contato VALUES (2, 1, 'vendas@kalunga.com.br', 2);
INSERT INTO forma_contato VALUES (3, 2, '(11) 3200-0000', 2);
INSERT INTO forma_contato VALUES (4, 3, '(11) 98282-0000', 2);
INSERT INTO forma_contato VALUES (5, 4, '(11) 98585-0000', 3);

INSERT INTO tag VALUES (1, 'Periféricos');
INSERT INTO tag VALUES (2, 'Calças');
INSERT INTO tag VALUES (3, 'Eletrodomésticos');
INSERT INTO tag VALUES (4, 'Celulares');
INSERT INTO tag VALUES (5, 'Água');

INSERT INTO usuario_tag VALUES (5, 4);
INSERT INTO usuario_tag VALUES (5, 5);
INSERT INTO usuario_tag VALUES (2, 2);
INSERT INTO usuario_tag VALUES (2, 3);
INSERT INTO usuario_tag VALUES (3, 2);

INSERT INTO departamento VALUES (1, 'Informática', 'icone-informatica');
INSERT INTO departamento VALUES (2, 'Eletrônicos', 'icone-eletrônicos');
INSERT INTO departamento VALUES (3, 'Vestuário', 'icone-vestuario');
INSERT INTO departamento VALUES (4, 'Bebidas', 'icone-bebidas');
INSERT INTO departamento VALUES (5, 'Cozinha', NULL);

INSERT INTO tag_departamento VALUES (1, 1);
INSERT INTO tag_departamento VALUES (2, 1);
INSERT INTO tag_departamento VALUES (3, 5);
INSERT INTO tag_departamento VALUES (4, 1);
INSERT INTO tag_departamento VALUES (5, 4);

INSERT INTO produto VALUES (1, 1, 'Mouse', 'Logitech', 'Preto', NULL, NULL, NULL);
INSERT INTO produto VALUES (2, 4, 'Água', 'Lindóia', NULL, '1 Litro', NULL, NULL);
INSERT INTO produto VALUES (3, 2, 'Celular', 'Apple', 'Vermelho', NULL, NULL, NULL);
INSERT INTO produto VALUES (4, 3, 'Calça', 'Hering', 'Vermelho', 'P', 'Jeans', 'Modelo XYZ');
INSERT INTO produto VALUES (5, 5, 'Geladeira', NULL, NULL, NULL, NULL, NULL);

INSERT INTO produto_tag VALUES (1, 1);
INSERT INTO produto_tag VALUES (2, 5);
INSERT INTO produto_tag VALUES (3, 4);
INSERT INTO produto_tag VALUES (4, 2);
INSERT INTO produto_tag VALUES (5, 3);

INSERT INTO status VALUES (1, 'Em Andamento');
INSERT INTO status VALUES (2, 'Recusado');
INSERT INTO status VALUES (3, 'Aprovado');
INSERT INTO status VALUES (4, 'Fechado');
INSERT INTO status VALUES (5, 'Concluído');

INSERT INTO cotacao VALUES (1, TO_DATE('2023-11-13', 'YYYY-MM-DD'), 1, 1, 20, 50.99, 1, 3, 1, 2, 10, NULL); 
INSERT INTO cotacao VALUES (2, TO_DATE('2023-11-12', 'YYYY-MM-DD'), 1, 1, 10, 69.90, 5, 2, 1, 3, 5, TO_DATE('2023-11-13', 'YYYY-MM-DD')); 
INSERT INTO cotacao VALUES (3, TO_DATE('2023-11-11', 'YYYY-MM-DD'), 4, 3, 5, 1500.00, 1, 1, 2, 3, 2, NULL); 
INSERT INTO cotacao VALUES (4, TO_DATE('2023-11-10', 'YYYY-MM-DD'), 4, 2, 100, 2.00, 1, 2, 1, 3, 1, NULL); 
INSERT INTO cotacao VALUES (5, TO_DATE('2023-11-09', 'YYYY-MM-DD'), 1, 5, 2, 3.500, 5, 3, 1, 2, 7, TO_DATE('2023-11-10', 'YYYY-MM-DD'));

-- Valores inseridos somente para contemplar os 5 inserts solicitados, porém só serão avaliados as cotações de status concluído.
INSERT INTO avaliacao VALUES (1, 1, TO_DATE('2023-11-13', 'YYYY-MM-DD'), 5, 3, 3, 'Muito bom!');
INSERT INTO avaliacao VALUES (2, 2, TO_DATE('2023-11-12', 'YYYY-MM-DD'), 3, 4, 2, NULL); 
INSERT INTO avaliacao VALUES (3, 3, TO_DATE('2023-11-11', 'YYYY-MM-DD'), 3, 1, 5, 'Demora para chegar'); 
INSERT INTO avaliacao VALUES (4, 4, TO_DATE('2023-11-10', 'YYYY-MM-DD'), 1, 1, 1, 'Compra cancelada'); 
INSERT INTO avaliacao VALUES (5, 5, TO_DATE('2023-11-09', 'YYYY-MM-DD'), 5, 5, 5, 'Excelente'); 

INSERT INTO historico VALUES (1, 2, TO_DATE('2023-11-12', 'YYYY-MM-DD'), 2, 3, NULL, 0, 1, 0, 0, 'Não tenho a quantidade no momento');
INSERT INTO historico VALUES (2, 2, TO_DATE('2023-11-12', 'YYYY-MM-DD'), 4, 2, 59.90, 0, 0, 0, 0, NULL);
INSERT INTO historico VALUES (3, 2, TO_DATE('2023-11-12', 'YYYY-MM-DD'), 5, 5, 44.50, 0, 0, 0, 0, NULL);
INSERT INTO historico VALUES (4, 3, TO_DATE('2023-11-12', 'YYYY-MM-DD'), 2, 5, 2000, 0, 0, 1, 0, 'Recusado Automaticamente: Preço ofertado maior do que o preço da cotação');
INSERT INTO historico VALUES (5, 5, TO_DATE('2023-11-12', 'YYYY-MM-DD'), 5, 1, 2999.99, 0, 0, 0, 0, NULL);
    
-- 03. CONSULTANDO COTAÇÕES, STATUS E AVALIAÇÕES:
SELECT 
    c.id_cotacao, 
    c.data_abertura_cotacao, 
    c.quantidade_produto, 
    c.valor_produto, 
    s.nome_status, 
    a.nota_entrega_avaliacao, 
    a.nota_qualidade_avaliacao, 
    a.nota_preco_avaliacao
FROM 
    cotacao c
JOIN 
    status s ON c.id_status = s.id_status
LEFT JOIN 
    avaliacao a ON c.id_cotacao = a.id_cotacao;

--------------------------------------------------------------------------------

-- SPRINT 02:
SET SERVEROUTPUT ON;

-- 01.01. FUNÇÃO PARA VALIDAR A ENTRADA DE UM EMAIL:
CREATE OR REPLACE FUNCTION is_email_valid(email IN VARCHAR2) RETURN BOOLEAN IS
BEGIN
    IF REGEXP_LIKE(email, '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$') THEN
        RETURN TRUE;
    ELSE
        RETURN FALSE;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        RETURN FALSE;
END;

-- 01.01.01. TESTANDO ENTRADA DE EMAILS:
DECLARE
    email_valido VARCHAR2(100);
    email_invalido VARCHAR2(100);
BEGIN
    email_valido := 'exemplo@dominio.com';
    IF is_email_valid(email_valido) THEN
        DBMS_OUTPUT.PUT_LINE(email_valido || ' é válido');
    ELSE
        DBMS_OUTPUT.PUT_LINE(email_valido || ' é inválido');
    END IF;

    email_invalido := 'exemplo@dominio';
    IF is_email_valid(email_invalido) THEN
        DBMS_OUTPUT.PUT_LINE(email_invalido || ' é válido');
    ELSE
        DBMS_OUTPUT.PUT_LINE(email_invalido || ' é inválido');
    END IF;
END;

-- 01.02. FUNÇÃO PARA VALIDAR A ENTRADA DE UM CNPJ:
CREATE OR REPLACE FUNCTION is_cnpj_valid(p_cnpj IN VARCHAR2) RETURN BOOLEAN IS
    v_soma          NUMBER := 0;
    v_indice        NUMBER := 1;
    v_digito_1      NUMBER;
    v_digito_2      NUMBER;
    v_var1          NUMBER := 5;
    v_var2          NUMBER := 9;
    v_digito_1_cnpj NUMBER := TO_NUMBER(SUBSTR(p_cnpj, LENGTH(p_cnpj) - 1, 1));
    v_digito_2_cnpj NUMBER := TO_NUMBER(SUBSTR(p_cnpj, LENGTH(p_cnpj), 1));
    v_resultado     BOOLEAN := FALSE;
BEGIN
    IF p_cnpj IN ('00000000000000', '11111111111111', '22222222222222', '33333333333333',
                  '44444444444444', '55555555555555', '66666666666666', '77777777777777',
                  '88888888888888', '99999999999999') THEN
        RETURN FALSE;
    END IF;

    IF LENGTH(TRIM(p_cnpj)) <> 14 THEN
        RETURN FALSE;
    END IF;

    FOR i IN 1..4 LOOP
        v_soma := v_soma + TO_NUMBER(SUBSTR(p_cnpj, v_indice, 1)) * v_var1;
        v_indice := v_indice + 1;
        v_var1 := v_var1 - 1;
    END LOOP;

    FOR i IN 1..8 LOOP
        v_soma := v_soma + TO_NUMBER(SUBSTR(p_cnpj, v_indice, 1)) * v_var2;
        v_indice := v_indice + 1;
        v_var2 := v_var2 - 1;
    END LOOP;

    v_digito_1 := MOD(v_soma, 11);
    IF v_digito_1 < 2 THEN
        v_digito_1 := 0;
    ELSE
        v_digito_1 := 11 - v_digito_1;
    END IF;

    v_soma := 0;
    v_indice := 1;
    v_var1 := 6;
    v_var2 := 9;

    FOR i IN 1..5 LOOP
        v_soma := v_soma + TO_NUMBER(SUBSTR(p_cnpj, v_indice, 1)) * v_var1;
        v_indice := v_indice + 1;
        v_var1 := v_var1 - 1;
    END LOOP;

    FOR i IN 1..8 LOOP
        v_soma := v_soma + TO_NUMBER(SUBSTR(p_cnpj, v_indice, 1)) * v_var2;
        v_indice := v_indice + 1;
        v_var2 := v_var2 - 1;
    END LOOP;

    v_digito_2 := MOD(v_soma, 11);
    IF v_digito_2 < 2 THEN
        v_digito_2 := 0;
    ELSE
        v_digito_2 := 11 - v_digito_2;
    END IF;

    IF v_digito_1 = v_digito_1_cnpj AND v_digito_2 = v_digito_2_cnpj THEN
        v_resultado := TRUE;
    ELSE
        v_resultado := FALSE;
    END IF;

    RETURN v_resultado;
END;

-- 01.02.01. TESTANDO ENTRADA DE CNPJ:
DECLARE
    cnpj_valido VARCHAR2(14) := '28434667000111';
    cnpj_invalido VARCHAR2(14) := '12345678000100';
    resultado BOOLEAN;
BEGIN
    resultado := is_cnpj_valid(cnpj_valido);
    IF resultado THEN
        DBMS_OUTPUT.PUT_LINE('CNPJ válido: ' || cnpj_valido || ' é válido');
    ELSE
        DBMS_OUTPUT.PUT_LINE('CNPJ válido: ' || cnpj_valido || ' é inválido');
    END IF;

    resultado := is_cnpj_valid(cnpj_invalido);
    IF resultado THEN
        DBMS_OUTPUT.PUT_LINE('CNPJ inválido: ' || cnpj_invalido || ' é válido');
    ELSE
        DBMS_OUTPUT.PUT_LINE('CNPJ inválido: ' || cnpj_invalido || ' é inválido');
    END IF;
END;

-- 02. PROCEDURES DE INSERT, UPDATE e DELETE:

-- 02.01. PESSOA:
-- 02.01.01. INSERT E TESTE:
CREATE OR REPLACE PROCEDURE insert_pessoa(
    p_id_pessoa IN pessoa.id_pessoa%TYPE,
    p_nome_pessoa IN pessoa.nome_pessoa%TYPE,
    p_imagem_pessoa IN pessoa.imagem_pessoa%TYPE
) AS
BEGIN
    INSERT INTO pessoa (id_pessoa, nome_pessoa, imagem_pessoa)
    VALUES (p_id_pessoa, p_nome_pessoa, p_imagem_pessoa);

EXCEPTION
    WHEN VALUE_ERROR THEN
        RAISE_APPLICATION_ERROR(-20001, 'Erro de valor na inserção');
    WHEN DUP_VAL_ON_INDEX THEN
        RAISE_APPLICATION_ERROR(-20002, 'ID duplicado');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20003, SQLERRM);
END;

BEGIN
    insert_pessoa(6, 'Nome Teste', 'imagem_teste.jpg');
    DBMS_OUTPUT.PUT_LINE('Inserção bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.01.02. UPDATE E TESTE:
CREATE OR REPLACE PROCEDURE update_pessoa(
    p_id_pessoa IN pessoa.id_pessoa%TYPE,
    p_nome_pessoa IN pessoa.nome_pessoa%TYPE,
    p_imagem_pessoa IN pessoa.imagem_pessoa%TYPE
) AS
BEGIN
    UPDATE pessoa
    SET nome_pessoa = p_nome_pessoa, imagem_pessoa = p_imagem_pessoa
    WHERE id_pessoa = p_id_pessoa;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20004, 'Registro não encontrado para atualização');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20005, SQLERRM);
END;

BEGIN
    update_pessoa(6, 'Nome Atualizado', 'imagem_atualizada.jpg');
    DBMS_OUTPUT.PUT_LINE('Atualização bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.01.03. DELETE E TESTE:
CREATE OR REPLACE PROCEDURE delete_pessoa(
    p_id_pessoa IN pessoa.id_pessoa%TYPE
) AS
BEGIN
    DELETE FROM pessoa
    WHERE id_pessoa = p_id_pessoa;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20006, 'Registro não encontrado para exclusão');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20007, SQLERRM);
END;

BEGIN
    delete_pessoa(6);
    DBMS_OUTPUT.PUT_LINE('Exclusão bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.02. PESSOA_JURIDICA:
-- 02.02.01. INSERT E TESTE:
CREATE OR REPLACE PROCEDURE insert_pessoa_juridica(
    p_id_pj IN pessoa_juridica.id_pj%TYPE,
    p_cnpj_pj IN pessoa_juridica.cnpj_pj%TYPE,
    p_is_fornecedor IN pessoa_juridica.is_fornecedor%TYPE,
    p_id_pessoa IN pessoa_juridica.id_pessoa%TYPE
) AS
BEGIN
    IF NOT is_cnpj_valid(p_cnpj_pj) THEN
        RAISE_APPLICATION_ERROR(-20015, 'CNPJ inválido');
    END IF;

    INSERT INTO pessoa_juridica (id_pj, cnpj_pj, is_fornecedor, id_pessoa)
    VALUES (p_id_pj, p_cnpj_pj, p_is_fornecedor, p_id_pessoa);

EXCEPTION
    WHEN VALUE_ERROR THEN
        RAISE_APPLICATION_ERROR(-20008, 'Erro de valor na inserção');
    WHEN DUP_VAL_ON_INDEX THEN
        RAISE_APPLICATION_ERROR(-20009, 'Chave duplicada');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20010, SQLERRM);
END;

BEGIN
    insert_pessoa_juridica(6, '59291534000167', 1, 6);
    DBMS_OUTPUT.PUT_LINE('Inserção bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.02.02. UPDATE E TESTE:
CREATE OR REPLACE PROCEDURE update_pessoa_juridica(
    p_id_pj IN pessoa_juridica.id_pj%TYPE,
    p_cnpj_pj IN pessoa_juridica.cnpj_pj%TYPE,
    p_is_fornecedor IN pessoa_juridica.is_fornecedor%TYPE,
    p_id_pessoa IN pessoa_juridica.id_pessoa%TYPE
) AS
BEGIN
    IF NOT is_cnpj_valid(p_cnpj_pj) THEN
        RAISE_APPLICATION_ERROR(-20016, 'CNPJ inválido');
    END IF;

    UPDATE pessoa_juridica
    SET cnpj_pj = p_cnpj_pj, is_fornecedor = p_is_fornecedor, id_pessoa = p_id_pessoa
    WHERE id_pj = p_id_pj;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20011, 'Registro não encontrado para atualização');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20012, SQLERRM);
END;

BEGIN
    update_pessoa_juridica(6, '59291534000167', 0, 6);
    DBMS_OUTPUT.PUT_LINE('Atualização bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.02.03. DELETE E TESTE:
CREATE OR REPLACE PROCEDURE delete_pessoa_juridica(
    p_id_pj IN pessoa_juridica.id_pj%TYPE
) AS
BEGIN
    DELETE FROM pessoa_juridica
    WHERE id_pj = p_id_pj;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20013, 'Registro não encontrado para exclusão');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20014, SQLERRM);
END;

BEGIN
    delete_pessoa_juridica(6);
    DBMS_OUTPUT.PUT_LINE('Exclusão bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03. USUARIO:
-- 02.03.01. INSERT E TESTE:
CREATE OR REPLACE PROCEDURE insert_usuario(
    p_id_usuario IN usuario.id_usuario%TYPE,
    p_email_usuario IN usuario.email_usuario%TYPE,
    p_senha_usuario IN usuario.senha_usuario%TYPE,
    p_id_pessoa IN usuario.id_pessoa%TYPE
) AS
BEGIN
    IF NOT is_email_valid(p_email_usuario) THEN
        RAISE_APPLICATION_ERROR(-20024, 'E-mail inválido');
    END IF;

    INSERT INTO usuario (id_usuario, email_usuario, senha_usuario, id_pessoa)
    VALUES (p_id_usuario, p_email_usuario, p_senha_usuario, p_id_pessoa);

EXCEPTION
    WHEN VALUE_ERROR THEN
        RAISE_APPLICATION_ERROR(-20017, 'Erro de valor na inserção');
    WHEN DUP_VAL_ON_INDEX THEN
        RAISE_APPLICATION_ERROR(-20018, 'Chave duplicada');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20019, SQLERRM);
END;

BEGIN
    insert_usuario(6, 'teste@exemplo.com', 'senha123', 6);
    DBMS_OUTPUT.PUT_LINE('Inserção bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;


-- 02.03.02. UPDATE E TESTE:
CREATE OR REPLACE PROCEDURE update_usuario(
    p_id_usuario IN usuario.id_usuario%TYPE,
    p_email_usuario IN usuario.email_usuario%TYPE,
    p_senha_usuario IN usuario.senha_usuario%TYPE,
    p_id_pessoa IN usuario.id_pessoa%TYPE
) AS
BEGIN
    IF NOT is_email_valid(p_email_usuario) THEN
        RAISE_APPLICATION_ERROR(-20025, 'E-mail inválido');
    END IF;

    UPDATE usuario
    SET email_usuario = p_email_usuario, senha_usuario = p_senha_usuario, id_pessoa = p_id_pessoa
    WHERE id_usuario = p_id_usuario;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20020, 'Registro não encontrado para atualização');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20021, SQLERRM);
END;

BEGIN
    update_usuario(6, 'atualizado@exemplo.com', 'senhaNova123', 6);
    DBMS_OUTPUT.PUT_LINE('Atualização bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.03. DELETE E TESTE:
CREATE OR REPLACE PROCEDURE delete_usuario(
    p_id_usuario IN usuario.id_usuario%TYPE
) AS
BEGIN
    DELETE FROM usuario
    WHERE id_usuario = p_id_usuario;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20022, 'Registro não encontrado para exclusão');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20023, SQLERRM);
END;

BEGIN
    delete_usuario(6);
    DBMS_OUTPUT.PUT_LINE('Exclusão bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.04. TIPO_CONTATO:
-- 02.04.01. INSERT E TESTE:
CREATE OR REPLACE PROCEDURE insert_tipo_contato(
    p_id_tipo_contato IN tipo_contato.id_tipo_contato%TYPE,
    p_nome_tipo_contato IN tipo_contato.nome_tipo_contato%TYPE
) AS
BEGIN    
    INSERT INTO tipo_contato (id_tipo_contato, nome_tipo_contato)
    VALUES (p_id_tipo_contato, p_nome_tipo_contato);

EXCEPTION
    WHEN VALUE_ERROR THEN
        RAISE_APPLICATION_ERROR(-20026, 'Erro de valor na inserção');
    WHEN DUP_VAL_ON_INDEX THEN
        RAISE_APPLICATION_ERROR(-20027, 'Chave duplicada');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20028, SQLERRM);
END;

BEGIN
    insert_tipo_contato(6, 'Caixa Postal');
    DBMS_OUTPUT.PUT_LINE('Inserção bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.04.02. UPDATE E TESTE:
CREATE OR REPLACE PROCEDURE update_tipo_contato(
    p_id_tipo_contato IN tipo_contato.id_tipo_contato%TYPE,
    p_nome_tipo_contato IN tipo_contato.nome_tipo_contato%TYPE
) AS
BEGIN
    UPDATE tipo_contato
    SET nome_tipo_contato = p_nome_tipo_contato
    WHERE id_tipo_contato = p_id_tipo_contato;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20029, 'Registro não encontrado para atualização');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20030, SQLERRM);
END;

BEGIN
    update_tipo_contato(6, 'Tipo de Contato Atualizado');
    DBMS_OUTPUT.PUT_LINE('Atualização bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.04.03. DELETE E TESTE:
CREATE OR REPLACE PROCEDURE delete_tipo_contato(
    p_id_tipo_contato IN tipo_contato.id_tipo_contato%TYPE
) AS
BEGIN
    DELETE FROM tipo_contato
    WHERE id_tipo_contato = p_id_tipo_contato;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20031, 'Registro não encontrado para exclusão');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20032, SQLERRM);
END;

BEGIN
    delete_tipo_contato(6);
    DBMS_OUTPUT.PUT_LINE('Exclusão bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.05. FORMA_CONTATO:
-- 02.05.01. INSERT E TESTE:
CREATE OR REPLACE PROCEDURE insert_forma_contato(
    p_id_forma_contato IN forma_contato.id_forma_contato%TYPE,
    p_id_tipo_contato IN forma_contato.id_tipo_contato%TYPE,
    p_valor_forma_contato IN forma_contato.valor_forma_contato%TYPE,
    p_id_pessoa IN forma_contato.id_pessoa%TYPE
) AS
BEGIN
    IF p_id_tipo_contato = 1 THEN
        IF NOT is_email_valid(p_valor_forma_contato) THEN
            RAISE_APPLICATION_ERROR(-20025, 'E-mail inválido');
        END IF;
    END IF;
    
    INSERT INTO forma_contato (id_forma_contato, id_tipo_contato, valor_forma_contato, id_pessoa)
    VALUES (p_id_forma_contato, p_id_tipo_contato, p_valor_forma_contato, p_id_pessoa);

EXCEPTION
    WHEN VALUE_ERROR THEN
        RAISE_APPLICATION_ERROR(-20026, 'Erro de valor na inserção');
    WHEN DUP_VAL_ON_INDEX THEN
        RAISE_APPLICATION_ERROR(-20027, 'Chave duplicada');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20028, SQLERRM);
END;

BEGIN
    insert_forma_contato(6, 1, 'kaue@kaue.com', 1);
    DBMS_OUTPUT.PUT_LINE('Inserção bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.05.02. UPDATE E TESTE:
CREATE OR REPLACE PROCEDURE update_forma_contato(
    p_id_forma_contato IN forma_contato.id_forma_contato%TYPE,
    p_id_tipo_contato IN forma_contato.id_tipo_contato%TYPE,
    p_valor_forma_contato IN forma_contato.valor_forma_contato%TYPE,
    p_id_pessoa IN forma_contato.id_pessoa%TYPE
) AS
BEGIN
    IF p_id_tipo_contato = 1 THEN
        IF NOT is_email_valid(p_valor_forma_contato) THEN
            RAISE_APPLICATION_ERROR(-20025, 'E-mail inválido');
        END IF;
    END IF;
    
    UPDATE forma_contato
    SET id_tipo_contato = p_id_tipo_contato, valor_forma_contato = p_valor_forma_contato, id_pessoa = p_id_pessoa
    WHERE id_forma_contato = p_id_forma_contato;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20029, 'Registro não encontrado para atualização');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20030, SQLERRM);
END;

BEGIN
    update_forma_contato(6, 1, 'Contatoatualizado@email.com', 1);
    DBMS_OUTPUT.PUT_LINE('Atualização bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.05.03. DELETE E TESTE:
CREATE OR REPLACE PROCEDURE delete_forma_contato(
    p_id_forma_contato IN forma_contato.id_forma_contato%TYPE
) AS
BEGIN
    DELETE FROM forma_contato
    WHERE id_forma_contato = p_id_forma_contato;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20031, 'Registro não encontrado para exclusão');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20032, SQLERRM);
END;

BEGIN
    delete_forma_contato(6);
    DBMS_OUTPUT.PUT_LINE('Exclusão bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.06. TAG:
-- 02.06.01. INSERT E TESTE:
CREATE OR REPLACE PROCEDURE insert_tag(
    p_id_tag IN tag.id_tag%TYPE,
    p_nome_tag IN tag.nome_tag%TYPE
) AS
BEGIN
    INSERT INTO tag (id_tag, nome_tag)
    VALUES (p_id_tag, p_nome_tag);

EXCEPTION
    WHEN VALUE_ERROR THEN
        RAISE_APPLICATION_ERROR(-20049, 'Erro de valor na inserção');
    WHEN DUP_VAL_ON_INDEX THEN
        RAISE_APPLICATION_ERROR(-20050, 'Chave duplicada');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20051, SQLERRM);
END;

BEGIN
    insert_tag(6, 'Tag Exemplo');
    DBMS_OUTPUT.PUT_LINE('Inserção bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.06.02. UPDATE E TESTE:
CREATE OR REPLACE PROCEDURE update_tag(
    p_id_tag IN tag.id_tag%TYPE,
    p_nome_tag IN tag.nome_tag%TYPE
) AS
BEGIN
    UPDATE tag
    SET nome_tag = p_nome_tag
    WHERE id_tag = p_id_tag;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20052, 'Registro não encontrado para atualização');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20053, SQLERRM);
END;

BEGIN
    update_tag(6, 'Tag Atualizada');
    DBMS_OUTPUT.PUT_LINE('Atualização bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.06.03. DELETE E TESTE:
CREATE OR REPLACE PROCEDURE delete_tag(
    p_id_tag IN tag.id_tag%TYPE
) AS
BEGIN
    DELETE FROM tag
    WHERE id_tag = p_id_tag;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20054, 'Registro não encontrado para exclusão');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20055, SQLERRM);
END;

BEGIN
    delete_tag(6);
    DBMS_OUTPUT.PUT_LINE('Exclusão bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.07. USUARIO_TAG:
-- 02.07.01. INSERT E TESTE:
CREATE OR REPLACE PROCEDURE insert_usuario_tag(
    p_id_usuario IN usuario_tag.id_usuario%TYPE,
    p_id_tag IN usuario_tag.id_tag%TYPE
) AS
BEGIN
    INSERT INTO usuario_tag (id_usuario, id_tag)
    VALUES (p_id_usuario, p_id_tag);

EXCEPTION
    WHEN VALUE_ERROR THEN
        RAISE_APPLICATION_ERROR(-20056, 'Erro de valor na inserção');
    WHEN DUP_VAL_ON_INDEX THEN
        RAISE_APPLICATION_ERROR(-20057, 'Relação duplicada');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20058, SQLERRM);
END;

BEGIN
    insert_usuario_tag(1, 1);
    DBMS_OUTPUT.PUT_LINE('Inserção bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.07.02. UPDATE E TESTE:
CREATE OR REPLACE PROCEDURE update_usuario_tag(
    p_id_usuario_antigo IN usuario_tag.id_usuario%TYPE,
    p_id_tag_antigo IN usuario_tag.id_tag%TYPE,
    p_id_usuario_novo IN usuario_tag.id_usuario%TYPE,
    p_id_tag_novo IN usuario_tag.id_tag%TYPE
) AS
BEGIN
    UPDATE usuario_tag
    SET id_usuario = p_id_usuario_novo, id_tag = p_id_tag_novo
    WHERE id_usuario = p_id_usuario_antigo AND id_tag = p_id_tag_antigo;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20061, 'Relação não encontrada para atualização');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20062, SQLERRM);
END;

BEGIN
    update_usuario_tag(1, 1, 1, 3);
    DBMS_OUTPUT.PUT_LINE('Atualização bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.07.03. DELETE E TESTE:
CREATE OR REPLACE PROCEDURE delete_usuario_tag(
    p_id_usuario IN usuario_tag.id_usuario%TYPE,
    p_id_tag IN usuario_tag.id_tag%TYPE
) AS
BEGIN
    DELETE FROM usuario_tag
    WHERE id_usuario = p_id_usuario AND id_tag = p_id_tag;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20059, 'Relação não encontrada para exclusão');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20060, SQLERRM);
END;

BEGIN
    delete_usuario_tag(1, 3);
    DBMS_OUTPUT.PUT_LINE('Exclusão bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.08. DEPARTAMENTO:
-- 02.08.01. INSERT E TESTE:
CREATE OR REPLACE PROCEDURE insert_departamento(
    p_id_departamento IN departamento.id_departamento%TYPE,
    p_nome_departamento IN departamento.nome_departamento%TYPE,
    p_icone_departamento IN departamento.icone_departamento%TYPE
) AS
BEGIN
    INSERT INTO departamento (id_departamento, nome_departamento, icone_departamento)
    VALUES (p_id_departamento, p_nome_departamento, p_icone_departamento);

EXCEPTION
    WHEN VALUE_ERROR THEN
        RAISE_APPLICATION_ERROR(-20063, 'Erro de valor na inserção');
    WHEN DUP_VAL_ON_INDEX THEN
        RAISE_APPLICATION_ERROR(-20064, 'Chave duplicada');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20065, SQLERRM);
END;

BEGIN
    insert_departamento(6, 'TI', 'icone_ti.png');
    DBMS_OUTPUT.PUT_LINE('Inserção bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.08.02. UPDATE E TESTE:
CREATE OR REPLACE PROCEDURE update_departamento(
    p_id_departamento IN departamento.id_departamento%TYPE,
    p_nome_departamento IN departamento.nome_departamento%TYPE,
    p_icone_departamento IN departamento.icone_departamento%TYPE
) AS
BEGIN
    UPDATE departamento
    SET nome_departamento = p_nome_departamento, icone_departamento = p_icone_departamento
    WHERE id_departamento = p_id_departamento;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20066, 'Registro não encontrado para atualização');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20067, SQLERRM);
END;

BEGIN
    update_departamento(6, 'Tecnologia da Informação', 'icone_ti_novo.png');
    DBMS_OUTPUT.PUT_LINE('Atualização bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.08.03. DELETE E TESTE:
CREATE OR REPLACE PROCEDURE delete_departamento(
    p_id_departamento IN departamento.id_departamento%TYPE
) AS
BEGIN
    DELETE FROM departamento
    WHERE id_departamento = p_id_departamento;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20068, 'Registro não encontrado para exclusão');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20069, SQLERRM);
END;

BEGIN
    delete_departamento(6);
    DBMS_OUTPUT.PUT_LINE('Exclusão bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.09. TAG_DEPARTAMENTO:
-- 02.09.01. INSERT E TESTE:
CREATE OR REPLACE PROCEDURE insert_tag_departamento(
    p_id_tag IN tag_departamento.id_tag%TYPE,
    p_id_departamento IN tag_departamento.id_departamento%TYPE
) AS
BEGIN
    INSERT INTO tag_departamento (id_tag, id_departamento)
    VALUES (p_id_tag, p_id_departamento);

EXCEPTION
    WHEN VALUE_ERROR THEN
        RAISE_APPLICATION_ERROR(-20070, 'Erro de valor na inserção');
    WHEN DUP_VAL_ON_INDEX THEN
        RAISE_APPLICATION_ERROR(-20071, 'Relação duplicada');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20072, SQLERRM);
END;

BEGIN
    insert_tag_departamento(1, 4);
    DBMS_OUTPUT.PUT_LINE('Inserção bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.09.02. UPDATE E TESTE:
CREATE OR REPLACE PROCEDURE update_tag_departamento(
    p_id_tag_antigo IN tag_departamento.id_tag%TYPE,
    p_id_departamento_antigo IN tag_departamento.id_departamento%TYPE,
    p_id_tag_novo IN tag_departamento.id_tag%TYPE,
    p_id_departamento_novo IN tag_departamento.id_departamento%TYPE
) AS
BEGIN
    UPDATE tag_departamento
    SET id_tag = p_id_tag_novo, id_departamento = p_id_departamento_novo
    WHERE id_tag = p_id_tag_antigo AND id_departamento = p_id_departamento_antigo;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20075, 'Relação não encontrada para atualização');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20076, SQLERRM);
END;

BEGIN
    update_tag_departamento(1, 4, 1, 2);
    DBMS_OUTPUT.PUT_LINE('Atualização bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.09.03. DELETE E TESTE:
CREATE OR REPLACE PROCEDURE delete_tag_departamento(
    p_id_tag IN tag_departamento.id_tag%TYPE,
    p_id_departamento IN tag_departamento.id_departamento%TYPE
) AS
BEGIN
    DELETE FROM tag_departamento
    WHERE id_tag = p_id_tag AND id_departamento = p_id_departamento;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20073, 'Relação não encontrada para exclusão');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20074, SQLERRM);
END;

BEGIN
    delete_tag_departamento(1, 2);
    DBMS_OUTPUT.PUT_LINE('Exclusão bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.10. PRODUTO:
-- 02.10.01. INSERT E TESTE:
CREATE OR REPLACE PROCEDURE insert_produto(
    p_id_produto IN produto.id_produto%TYPE,
    p_id_departamento IN produto.id_departamento%TYPE,
    p_nome_produto IN produto.nome_produto%TYPE,
    p_marca_produto IN produto.marca_produto%TYPE,
    p_cor_produto IN produto.cor_produto%TYPE,
    p_tamanho_produto IN produto.tamanho_produto%TYPE,
    p_material_produto IN produto.material_produto%TYPE,
    p_observacao_produto IN produto.observacao_produto%TYPE
) AS
BEGIN
    INSERT INTO produto (id_produto, id_departamento, nome_produto, marca_produto, cor_produto, tamanho_produto, material_produto, observacao_produto)
    VALUES (p_id_produto, p_id_departamento, p_nome_produto, p_marca_produto, p_cor_produto, p_tamanho_produto, p_material_produto, p_observacao_produto);

EXCEPTION
    WHEN VALUE_ERROR THEN
        RAISE_APPLICATION_ERROR(-20077, 'Erro de valor na inserção');
    WHEN DUP_VAL_ON_INDEX THEN
        RAISE_APPLICATION_ERROR(-20078, 'Chave duplicada');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20079, SQLERRM);
END;

BEGIN
    insert_produto(6, 1, 'Produto Teste', 'Marca Teste', 'Azul', 'M', 'Algodão', 'Sem observações');
    DBMS_OUTPUT.PUT_LINE('Inserção bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.10.02. UPDATE E TESTE:
CREATE OR REPLACE PROCEDURE update_produto(
    p_id_produto IN produto.id_produto%TYPE,
    p_id_departamento IN produto.id_departamento%TYPE,
    p_nome_produto IN produto.nome_produto%TYPE,
    p_marca_produto IN produto.marca_produto%TYPE,
    p_cor_produto IN produto.cor_produto%TYPE,
    p_tamanho_produto IN produto.tamanho_produto%TYPE,
    p_material_produto IN produto.material_produto%TYPE,
    p_observacao_produto IN produto.observacao_produto%TYPE
) AS
BEGIN
    UPDATE produto
    SET id_departamento = p_id_departamento, nome_produto = p_nome_produto, marca_produto = p_marca_produto, cor_produto = p_cor_produto, tamanho_produto = p_tamanho_produto, material_produto = p_material_produto, observacao_produto = p_observacao_produto
    WHERE id_produto = p_id_produto;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20080, 'Registro não encontrado para atualização');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20081, SQLERRM);
END;

BEGIN
    update_produto(6, 2, 'Produto Atualizado', 'Marca Nova', 'Vermelho', 'G', 'Poliéster', 'Atualizado');
    DBMS_OUTPUT.PUT_LINE('Atualização bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.10.03. DELETE E TESTE:
CREATE OR REPLACE PROCEDURE delete_produto(
    p_id_produto IN produto.id_produto%TYPE
) AS
BEGIN
    DELETE FROM produto
    WHERE id_produto = p_id_produto;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20082, 'Registro não encontrado para exclusão');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20083, SQLERRM);
END;

BEGIN
    delete_produto(6);
    DBMS_OUTPUT.PUT_LINE('Exclusão bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.11. PRODUTO_TAG:
-- 02.11.01. INSERT E TESTE:
CREATE OR REPLACE PROCEDURE insert_produto_tag(
    p_id_produto IN produto_tag.id_produto%TYPE,
    p_id_tag IN produto_tag.id_tag%TYPE
) AS
BEGIN
    INSERT INTO produto_tag (id_produto, id_tag)
    VALUES (p_id_produto, p_id_tag);

EXCEPTION
    WHEN VALUE_ERROR THEN
        RAISE_APPLICATION_ERROR(-20084, 'Erro de valor na inserção');
    WHEN DUP_VAL_ON_INDEX THEN
        RAISE_APPLICATION_ERROR(-20085, 'Relação duplicada');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20086, SQLERRM);
END;

BEGIN
    insert_produto_tag(1, 4);
    DBMS_OUTPUT.PUT_LINE('Inserção bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.11.02. UPDATE E TESTE:
CREATE OR REPLACE PROCEDURE update_produto_tag(
    p_id_produto_antigo IN produto_tag.id_produto%TYPE,
    p_id_tag_antigo IN produto_tag.id_tag%TYPE,
    p_id_produto_novo IN produto_tag.id_produto%TYPE,
    p_id_tag_novo IN produto_tag.id_tag%TYPE
) AS
BEGIN
    UPDATE produto_tag
    SET id_produto = p_id_produto_novo, id_tag = p_id_tag_novo
    WHERE id_produto = p_id_produto_antigo AND id_tag = p_id_tag_antigo;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20087, 'Relação não encontrada para atualização');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20088, SQLERRM);
END;

BEGIN
    update_produto_tag(1, 4, 1, 2);
    DBMS_OUTPUT.PUT_LINE('Atualização bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.11.03. DELETE E TESTE:
CREATE OR REPLACE PROCEDURE delete_produto_tag(
    p_id_produto IN produto_tag.id_produto%TYPE,
    p_id_tag IN produto_tag.id_tag%TYPE
) AS
BEGIN
    DELETE FROM produto_tag
    WHERE id_produto = p_id_produto AND id_tag = p_id_tag;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20089, 'Relação não encontrada para exclusão');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20090, SQLERRM);
END;

BEGIN
    delete_produto_tag(1, 2);
    DBMS_OUTPUT.PUT_LINE('Exclusão bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.12. STATUS:
-- 02.12.01. INSERT E TESTE:
CREATE OR REPLACE PROCEDURE insert_status(
    p_id_status IN status.id_status%TYPE,
    p_nome_status IN status.nome_status%TYPE
) AS
BEGIN
    INSERT INTO status (id_status, nome_status)
    VALUES (p_id_status, p_nome_status);

EXCEPTION
    WHEN VALUE_ERROR THEN
        RAISE_APPLICATION_ERROR(-20091, 'Erro de valor na inserção');
    WHEN DUP_VAL_ON_INDEX THEN
        RAISE_APPLICATION_ERROR(-20092, 'Chave duplicada');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20093, SQLERRM);
END;

BEGIN
    insert_status(6, 'Em Processamento');
    DBMS_OUTPUT.PUT_LINE('Inserção bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.12.02. UPDATE E TESTE:
CREATE OR REPLACE PROCEDURE update_status(
    p_id_status IN status.id_status%TYPE,
    p_nome_status IN status.nome_status%TYPE
) AS
BEGIN
    UPDATE status
    SET nome_status = p_nome_status
    WHERE id_status = p_id_status;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20094, 'Registro não encontrado para atualização');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20095, SQLERRM);
END;

BEGIN
    update_status(6, 'OK');
    DBMS_OUTPUT.PUT_LINE('Atualização bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.12.03. DELETE E TESTE:
CREATE OR REPLACE PROCEDURE delete_status(
    p_id_status IN status.id_status%TYPE
) AS
BEGIN
    DELETE FROM status
    WHERE id_status = p_id_status;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20096, 'Registro não encontrado para exclusão');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20097, SQLERRM);
END;

BEGIN
    delete_status(6);
    DBMS_OUTPUT.PUT_LINE('Exclusão bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.13. COTACAO:
-- 02.13.01. INSERT E TESTE:
CREATE OR REPLACE PROCEDURE insert_cotacao(
    p_id_cotacao IN cotacao.id_cotacao%TYPE,
    p_data_abertura_cotacao IN cotacao.data_abertura_cotacao%TYPE,
    p_id_comprador IN cotacao.id_comprador%TYPE,
    p_id_produto IN cotacao.id_produto%TYPE,
    p_quantidade_produto IN cotacao.quantidade_produto%TYPE,
    p_valor_produto IN cotacao.valor_produto%TYPE,
    p_id_status IN cotacao.id_status%TYPE,
    p_prioridade_entrega IN cotacao.prioridade_entrega%TYPE,
    p_prioridade_qualidade IN cotacao.prioridade_qualidade%TYPE,
    p_prioridade_preco IN cotacao.prioridade_preco%TYPE,
    p_prazo_cotacao IN cotacao.prazo_cotacao%TYPE,
    p_data_fechamento_cotacao IN cotacao.data_fechamento_cotacao%TYPE
) AS
BEGIN
    INSERT INTO cotacao (id_cotacao, data_abertura_cotacao, id_comprador, id_produto, quantidade_produto, valor_produto, id_status, prioridade_entrega, prioridade_qualidade, prioridade_preco, prazo_cotacao, data_fechamento_cotacao)
    VALUES (p_id_cotacao, p_data_abertura_cotacao, p_id_comprador, p_id_produto, p_quantidade_produto, p_valor_produto, p_id_status, p_prioridade_entrega, p_prioridade_qualidade, p_prioridade_preco, p_prazo_cotacao, p_data_fechamento_cotacao);

EXCEPTION
    WHEN VALUE_ERROR THEN
        RAISE_APPLICATION_ERROR(-20098, 'Erro de valor na inserção');
    WHEN DUP_VAL_ON_INDEX THEN
        RAISE_APPLICATION_ERROR(-20099, 'Chave duplicada');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20100, SQLERRM);
END;

BEGIN
    insert_cotacao(6, SYSDATE, 1, 1, 100, 500, 1, 1, 1, 1, 30, SYSDATE);
    DBMS_OUTPUT.PUT_LINE('Inserção bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.13.02. UPDATE E TESTE:
CREATE OR REPLACE PROCEDURE update_cotacao(
    p_id_cotacao IN cotacao.id_cotacao%TYPE,
    p_data_abertura_cotacao IN cotacao.data_abertura_cotacao%TYPE,
    p_id_comprador IN cotacao.id_comprador%TYPE,
    p_id_produto IN cotacao.id_produto%TYPE,
    p_quantidade_produto IN cotacao.quantidade_produto%TYPE,
    p_valor_produto IN cotacao.valor_produto%TYPE,
    p_id_status IN cotacao.id_status%TYPE,
    p_prioridade_entrega IN cotacao.prioridade_entrega%TYPE,
    p_prioridade_qualidade IN cotacao.prioridade_qualidade%TYPE,
    p_prioridade_preco IN cotacao.prioridade_preco%TYPE,
    p_prazo_cotacao IN cotacao.prazo_cotacao%TYPE,
    p_data_fechamento_cotacao IN cotacao.data_fechamento_cotacao%TYPE
) AS
BEGIN
    UPDATE cotacao
    SET data_abertura_cotacao = p_data_abertura_cotacao, id_comprador = p_id_comprador, id_produto = p_id_produto, quantidade_produto = p_quantidade_produto, valor_produto = p_valor_produto, id_status = p_id_status, prioridade_entrega = p_prioridade_entrega, prioridade_qualidade = p_prioridade_qualidade, prioridade_preco = p_prioridade_preco, prazo_cotacao = p_prazo_cotacao, data_fechamento_cotacao = p_data_fechamento_cotacao
    WHERE id_cotacao = p_id_cotacao;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20101, 'Registro não encontrado para atualização');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20102, SQLERRM);
END;

BEGIN
    update_cotacao(6, SYSDATE, 1, 2, 150, 750, 2, 0, 1, 0, 45, SYSDATE);
    DBMS_OUTPUT.PUT_LINE('Atualização bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.13.03. DELETE E TESTE:
CREATE OR REPLACE PROCEDURE delete_cotacao(
    p_id_cotacao IN cotacao.id_cotacao%TYPE
) AS
BEGIN
    DELETE FROM cotacao
    WHERE id_cotacao = p_id_cotacao;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20103, 'Registro não encontrado para exclusão');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20104, SQLERRM);
END;

BEGIN
    delete_cotacao(6);
    DBMS_OUTPUT.PUT_LINE('Exclusão bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.14. AVALIACAO:
-- 02.14.01. INSERT E TESTE:
CREATE OR REPLACE PROCEDURE insert_avaliacao(
    p_id_avaliacao IN avaliacao.id_avaliacao%TYPE,
    p_id_cotacao IN avaliacao.id_cotacao%TYPE,
    p_data_avaliacao IN avaliacao.data_avaliacao%TYPE,
    p_nota_entrega_avaliacao IN avaliacao.nota_entrega_avaliacao%TYPE,
    p_nota_qualidade_avaliacao IN avaliacao.nota_qualidade_avaliacao%TYPE,
    p_nota_preco_avaliacao IN avaliacao.nota_preco_avaliacao%TYPE,
    p_descricao_avaliacao IN avaliacao.descricao_avaliacao%TYPE
) AS
BEGIN
    INSERT INTO avaliacao (id_avaliacao, id_cotacao, data_avaliacao, nota_entrega_avaliacao, nota_qualidade_avaliacao, nota_preco_avaliacao, descricao_avaliacao)
    VALUES (p_id_avaliacao, p_id_cotacao, p_data_avaliacao, p_nota_entrega_avaliacao, p_nota_qualidade_avaliacao, p_nota_preco_avaliacao, p_descricao_avaliacao);

EXCEPTION
    WHEN VALUE_ERROR THEN
        RAISE_APPLICATION_ERROR(-20105, 'Erro de valor na inserção');
    WHEN DUP_VAL_ON_INDEX THEN
        RAISE_APPLICATION_ERROR(-20106, 'Chave duplicada');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20107, SQLERRM);
END;

BEGIN
    insert_avaliacao(6, 6, SYSDATE, 4, 5, 4, 'Avaliação positiva');
    DBMS_OUTPUT.PUT_LINE('Inserção bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.14.02. UPDATE E TESTE:
CREATE OR REPLACE PROCEDURE update_avaliacao(
    p_id_avaliacao IN avaliacao.id_avaliacao%TYPE,
    p_id_cotacao IN avaliacao.id_cotacao%TYPE,
    p_data_avaliacao IN avaliacao.data_avaliacao%TYPE,
    p_nota_entrega_avaliacao IN avaliacao.nota_entrega_avaliacao%TYPE,
    p_nota_qualidade_avaliacao IN avaliacao.nota_qualidade_avaliacao%TYPE,
    p_nota_preco_avaliacao IN avaliacao.nota_preco_avaliacao%TYPE,
    p_descricao_avaliacao IN avaliacao.descricao_avaliacao%TYPE
) AS
BEGIN
    UPDATE avaliacao
    SET id_cotacao = p_id_cotacao, data_avaliacao = p_data_avaliacao, nota_entrega_avaliacao = p_nota_entrega_avaliacao, nota_qualidade_avaliacao = p_nota_qualidade_avaliacao, nota_preco_avaliacao = p_nota_preco_avaliacao, descricao_avaliacao = p_descricao_avaliacao
    WHERE id_avaliacao = p_id_avaliacao;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20108, 'Registro não encontrado para atualização');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20109, SQLERRM);
END;

BEGIN
    update_avaliacao(6, 6, SYSDATE, 3, 4, 5, 'Avaliação atualizada');
    DBMS_OUTPUT.PUT_LINE('Atualização bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.14.03. DELETE E TESTE:
CREATE OR REPLACE PROCEDURE delete_avaliacao(
    p_id_avaliacao IN avaliacao.id_avaliacao%TYPE
) AS
BEGIN
    DELETE FROM avaliacao
    WHERE id_avaliacao = p_id_avaliacao;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20110, 'Registro não encontrado para exclusão');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20111, SQLERRM);
END;

BEGIN
    delete_avaliacao(6);
    DBMS_OUTPUT.PUT_LINE('Exclusão bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.15. HISTORICO (OBS: NÃO FUNCIONA COM NUMERO DECIAL NO VALOR_OFERTADO_HISTORICO. NÃO CONSEGUIMOS RESOLVER)
-- 02.15.01. INSERT E TESTE:
CREATE OR REPLACE PROCEDURE insert_historico(
    p_id_historico IN historico.id_historico%TYPE,
    p_id_cotacao IN historico.id_cotacao%TYPE,
    p_data_historico IN historico.data_historico%TYPE,
    p_id_status IN historico.id_status%TYPE,
    p_id_fornecedor IN historico.id_fornecedor%TYPE,
    p_valor_ofertado_historico IN historico.valor_ofertado_historico%TYPE,
    p_recusado_por_produto IN historico.recusado_por_produto%TYPE,
    p_recusado_por_quantidade IN historico.recusado_por_quantidade%TYPE,
    p_recusado_por_preco IN historico.recusado_por_preco%TYPE,
    p_recusado_por_prazo IN historico.recusado_por_prazo%TYPE,
    p_descricao_historico IN historico.descricao_historico%TYPE
) AS
BEGIN
    
    INSERT INTO historico (id_historico, id_cotacao, data_historico, id_status, id_fornecedor, valor_ofertado_historico, recusado_por_produto, recusado_por_quantidade, recusado_por_preco, recusado_por_prazo, descricao_historico)
    VALUES (p_id_historico, p_id_cotacao, p_data_historico, p_id_status, p_id_fornecedor, p_valor_ofertado_historico, p_recusado_por_produto, p_recusado_por_quantidade, p_recusado_por_preco, p_recusado_por_prazo, p_descricao_historico);

EXCEPTION
    WHEN VALUE_ERROR THEN
        RAISE_APPLICATION_ERROR(-20112, 'Erro de valor na inserção');
    WHEN DUP_VAL_ON_INDEX THEN
        RAISE_APPLICATION_ERROR(-20113, 'Chave duplicada');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20114, SQLERRM);
END;

BEGIN
    insert_historico(6, 2, SYSDATE, 1, 2, 15000, 0, 0, 0, 0, 'Histórico inicial da cotação');
    DBMS_OUTPUT.PUT_LINE('Inserção bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.15.02. UPDATE E TESTE:
CREATE OR REPLACE PROCEDURE update_historico(
    p_id_historico IN historico.id_historico%TYPE,
    p_id_cotacao IN historico.id_cotacao%TYPE,
    p_data_historico IN historico.data_historico%TYPE,
    p_id_status IN historico.id_status%TYPE,
    p_id_fornecedor IN historico.id_fornecedor%TYPE,
    p_valor_ofertado_historico IN historico.valor_ofertado_historico%TYPE,
    p_recusado_por_produto IN historico.recusado_por_produto%TYPE,
    p_recusado_por_quantidade IN historico.recusado_por_quantidade%TYPE,
    p_recusado_por_preco IN historico.recusado_por_preco%TYPE,
    p_recusado_por_prazo IN historico.recusado_por_prazo%TYPE,
    p_descricao_historico IN historico.descricao_historico%TYPE
) AS
BEGIN
    UPDATE historico
    SET id_cotacao = p_id_cotacao, data_historico = p_data_historico, id_status = p_id_status, id_fornecedor = p_id_fornecedor, valor_ofertado_historico = p_valor_ofertado_historico, recusado_por_produto = p_recusado_por_produto, recusado_por_quantidade = p_recusado_por_quantidade, recusado_por_preco = p_recusado_por_preco, recusado_por_prazo = p_recusado_por_prazo, descricao_historico = p_descricao_historico
    WHERE id_historico = p_id_historico;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20115, 'Registro não encontrado para atualização');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20116, SQLERRM);
END;

BEGIN
    update_historico(6, 2, SYSDATE, 2, 3, 550, 1, 0, 1, 0, 'Atualização do histórico da cotação');
    DBMS_OUTPUT.PUT_LINE('Atualização bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.15.03. DELETE E TESTE:
CREATE OR REPLACE PROCEDURE delete_historico(
    p_id_historico IN historico.id_historico%TYPE
) AS
BEGIN
    DELETE FROM historico
    WHERE id_historico = p_id_historico;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20117, 'Registro não encontrado para exclusão');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20118, SQLERRM);
END;

BEGIN
    delete_historico(6);
    DBMS_OUTPUT.PUT_LINE('Exclusão bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 03. BLOCO ANÔNIMO COM CURSOR PARA CONSULTA COM JOIN:
DECLARE
    CURSOR cotacao_avaliacao_cur IS
        SELECT c.id_cotacao, c.data_abertura_cotacao, a.nota_entrega_avaliacao, a.nota_qualidade_avaliacao, a.nota_preco_avaliacao
        FROM cotacao c
        JOIN avaliacao a ON c.id_cotacao = a.id_cotacao;

    cotacao_avaliacao_rec cotacao_avaliacao_cur%ROWTYPE;
BEGIN
    OPEN cotacao_avaliacao_cur;

    LOOP
        FETCH cotacao_avaliacao_cur INTO cotacao_avaliacao_rec;
        EXIT WHEN cotacao_avaliacao_cur%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE('ID Cotação: ' || cotacao_avaliacao_rec.id_cotacao ||
                             ', Data Abertura: ' || cotacao_avaliacao_rec.data_abertura_cotacao ||
                             ', Nota Entrega: ' || cotacao_avaliacao_rec.nota_entrega_avaliacao ||
                             ', Nota Qualidade: ' || cotacao_avaliacao_rec.nota_qualidade_avaliacao ||
                             ', Nota Preço: ' || cotacao_avaliacao_rec.nota_preco_avaliacao);
    END LOOP;

    CLOSE cotacao_avaliacao_cur;
END;

-- 04. PROCEDURE IMPRIMINDO RELATÓRIO QUE CONTENHA FUNÇÕES, INNER JOIN, ORDER BY, SUM OU COUNT
CREATE OR REPLACE PROCEDURE print_cotacao_report IS
    v_total_cotacoes NUMBER;
    v_valor_medio NUMBER;
    v_id_cotacao cotacao.id_cotacao%TYPE;
    v_data_abertura DATE;
    v_valor_produto cotacao.valor_produto%TYPE;

BEGIN
    SELECT COUNT(*), AVG(valor_produto)
    INTO v_total_cotacoes, v_valor_medio
    FROM cotacao c
    JOIN status s ON c.id_status = s.id_status
    WHERE s.nome_status = 'Concluído';
    DBMS_OUTPUT.PUT_LINE('Total de Cotações Concluídas: ' || v_total_cotacoes);
    DBMS_OUTPUT.PUT_LINE('Valor Médio das Cotações Cocluídas: ' || v_valor_medio);

    FOR r IN (
        SELECT id_cotacao, data_abertura_cotacao, valor_produto
        FROM cotacao c
        JOIN status s ON c.id_status = s.id_status
        WHERE s.nome_status = 'Concluído'
        ORDER BY data_abertura_cotacao DESC
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('ID da Cotação: ' || r.id_cotacao || 
                             ', Data de Abertura: ' || r.data_abertura_cotacao || 
                             ', Valor: ' || r.valor_produto);
    END LOOP;

EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro ao gerar relatório de cotações.');
END;

-- 04.01. TESTANDO PROCEDURE COM RELATÓRIO:
BEGIN
    print_cotacao_report;
END;

--------------------------------------------------------------------------------
-- SELECTS CASO NECESSÁRIO:
SELECT * FROM avaliacao;
SELECT * FROM cotacao;
SELECT * FROM departamento;
SELECT * FROM forma_contato;
SELECT * FROM historico;
SELECT * FROM pessoa;
SELECT * FROM pessoa_juridica;
SELECT * FROM produto;
SELECT * FROM produto_tag;
SELECT * FROM status;
SELECT * FROM tag;
SELECT * FROM tag_departamento;
SELECT * FROM tipo_contato;
SELECT * FROM usuario;
SELECT * FROM usuario_tag;

-- DROP TABLES CASO NECESSÁRIO:

DROP TABLE avaliacao CASCADE CONSTRAINTS;
DROP TABLE cotacao CASCADE CONSTRAINTS;
DROP TABLE departamento CASCADE CONSTRAINTS;
DROP TABLE forma_contato CASCADE CONSTRAINTS;
DROP TABLE historico CASCADE CONSTRAINTS;
DROP TABLE pessoa CASCADE CONSTRAINTS;
DROP TABLE pessoa_juridica CASCADE CONSTRAINTS;
DROP TABLE produto CASCADE CONSTRAINTS;
DROP TABLE produto_tag CASCADE CONSTRAINTS;
DROP TABLE status CASCADE CONSTRAINTS;
DROP TABLE tag CASCADE CONSTRAINTS;
DROP TABLE tag_departamento CASCADE CONSTRAINTS;
DROP TABLE tipo_contato CASCADE CONSTRAINTS;
DROP TABLE usuario CASCADE CONSTRAINTS;
DROP TABLE usuario_tag CASCADE CONSTRAINTS;