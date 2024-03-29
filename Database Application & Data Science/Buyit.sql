-- SELECTS CASO NECESS�RIO:
SELECT * FROM usuario;
SELECT * FROM contato;
SELECT * FROM tag;
SELECT * FROM usuario_tag;
SELECT * FROM departamento;
SELECT * FROM tag_departamento;
SELECT * FROM produto;
SELECT * FROM produto_tag;
SELECT * FROM status;
SELECT * FROM cotacao;
SELECT * FROM avaliacao;
SELECT * FROM historico;

-- DROP TABLES CASO NECESS�RIO:
DROP TABLE usuario CASCADE CONSTRAINTS;
DROP TABLE contato CASCADE CONSTRAINTS;
DROP TABLE tag CASCADE CONSTRAINTS;
DROP TABLE usuario_tag CASCADE CONSTRAINTS;
DROP TABLE departamento CASCADE CONSTRAINTS;
DROP TABLE tag_departamento CASCADE CONSTRAINTS;
DROP TABLE produto CASCADE CONSTRAINTS;
DROP TABLE produto_tag CASCADE CONSTRAINTS;
DROP TABLE status CASCADE CONSTRAINTS;
DROP TABLE cotacao CASCADE CONSTRAINTS;
DROP TABLE avaliacao CASCADE CONSTRAINTS;
DROP TABLE historico CASCADE CONSTRAINTS;

-- DROPANDO SEQU�NCIAS CASO NECESS�RIO
DECLARE
  v_sql VARCHAR2(1000);
BEGIN
  FOR cur IN (SELECT sequence_name
              FROM user_sequences) 
  LOOP
    v_sql := 'DROP SEQUENCE ' || cur.sequence_name;
    EXECUTE IMMEDIATE v_sql;
  END LOOP;
END;

-- SPRINT 01:
-- 01.01. CRIA��O DAS TABELAS:
CREATE TABLE usuario (
    id_usuario NUMBER(9) CONSTRAINT pk_id_usuario PRIMARY KEY,
    nome_usuario VARCHAR2(255) CONSTRAINT nn_nome_usuario NOT NULL,
    email_usuario VARCHAR2(255) CONSTRAINT uk_email_usuario UNIQUE CONSTRAINT nn_email_usuario NOT NULL,
    senha_usuario VARCHAR2(255) CONSTRAINT nn_senha_usuario NOT NULL,
    cnpj_usuario CHAR(14) CONSTRAINT uk_cnpj_usuario UNIQUE CONSTRAINT nn_cnpj_usuario NOT NULL,
    is_fornecedor NUMBER(1) CONSTRAINT nn_is_fornecedor NOT NULL,
    imagem_usuario VARCHAR2(255)
);

CREATE TABLE contato (
    id_contato NUMBER(9) CONSTRAINT pk_id_contato PRIMARY KEY,
    tipo_contato VARCHAR2(255) CONSTRAINT nn_tipo_contato NOT NULL,
    valor_contato VARCHAR2(255) CONSTRAINT nn_valor_contato NOT NULL,
    id_usuario NUMBER(9) CONSTRAINT fk_id_usuario_contato REFERENCES usuario(id_usuario) CONSTRAINT nn_usuario_contato NOT NULL
);

CREATE TABLE tag (
    id_tag NUMBER(9) CONSTRAINT pk_id_tag PRIMARY KEY,
    nome_tag VARCHAR2(255) CONSTRAINT uk_nome_tag UNIQUE CONSTRAINT nn_nome_tag NOT NULL
);

CREATE TABLE usuario_tag (
    id_usuario NUMBER(9) CONSTRAINT fk_id_usuario_tag REFERENCES usuario(id_usuario) CONSTRAINT nn_id_usuario_tag NOT NULL,
    id_tag NUMBER(9) CONSTRAINT fk_id_tag_usuario REFERENCES tag(id_tag) CONSTRAINT nn_id_tag_usuario NOT NULL
);

CREATE TABLE departamento (
    id_departamento NUMBER(9) CONSTRAINT pk_id_departamento PRIMARY KEY,
    nome_departamento VARCHAR2(255) CONSTRAINT uk_nome_departamento UNIQUE CONSTRAINT nn_nome_departamento NOT NULL,
    icone_departamento VARCHAR2(255)
);

CREATE TABLE tag_departamento (
    id_tag NUMBER(9) CONSTRAINT fk_id_tag_departamento REFERENCES tag(id_tag) CONSTRAINT nn_id_tag_departamento NOT NULL,
    id_departamento NUMBER(9) CONSTRAINT fk_id_departamento_tag REFERENCES departamento(id_departamento) CONSTRAINT nn_id_departamento_tag NOT NULL
);

CREATE TABLE produto (
    id_produto NUMBER(9) CONSTRAINT pk_id_produto PRIMARY KEY,
    id_departamento NUMBER(9) CONSTRAINT fk_id_departamento_produto REFERENCES departamento(id_departamento),
    nome_produto VARCHAR2(255) CONSTRAINT nn_nome_produto NOT NULL,
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
    nome_status VARCHAR2(255) CONSTRAINT uk_nome_status UNIQUE CONSTRAINT nn_nome_status NOT NULL
);

CREATE TABLE cotacao (
    id_cotacao NUMBER(9) CONSTRAINT pk_id_cotacao PRIMARY KEY,
    data_abertura_cotacao DATE CONSTRAINT nn_data_cotacao NOT NULL,
    id_usuario NUMBER(9) CONSTRAINT fk_id_usuario_cotacao REFERENCES usuario(id_usuario) CONSTRAINT nn_usuario_cotacao NOT NULL,
    id_produto NUMBER(9) CONSTRAINT fk_id_produto_cotacao REFERENCES produto(id_produto) CONSTRAINT nn_produto_cotacao NOT NULL,
    quantidade_produto NUMBER(8,2) CONSTRAINT nn_quantidade_produto NOT NULL,
    valor_produto NUMBER(8,2) CONSTRAINT nn_valor_produto NOT NULL,
    id_status NUMBER(9) CONSTRAINT fk_id_status_cotacao REFERENCES status(id_status) CONSTRAINT nn_status_cotacao NOT NULL,
    prioridade_entrega NUMBER(1) CONSTRAINT nn_pri_entr_cotacao NOT NULL,
    prioridade_qualidade NUMBER(1) CONSTRAINT nn_pri_qual_cotacao NOT NULL,
    prioridade_preco NUMBER(1) CONSTRAINT nn_pri_prec_cotacao NOT NULL,
    prazo_cotacao NUMBER(3) CONSTRAINT nn_prazo_cotacao NOT NULL,
    data_fechamento_cotacao DATE
);

CREATE TABLE avaliacao (
    id_avaliacao NUMBER(9) CONSTRAINT pk_id_avaliacao PRIMARY KEY,
    id_cotacao NUMBER(9) CONSTRAINT fk_id_cotacao_avaliacao REFERENCES cotacao(id_cotacao) CONSTRAINT uk_cotacao_avaliacao UNIQUE CONSTRAINT nn_cotacao_avaliacao NOT NULL,
    data_avaliacao DATE CONSTRAINT nn_dt_avaliacao NOT NULL,
    nota_entrega_avaliacao NUMBER(1) CONSTRAINT nn_nota_entr_avaliacao NOT NULL, 
    nota_qualidade_avaliacao NUMBER(1) CONSTRAINT nn_nota_qual_avaliacao NOT NULL,
    nota_preco_avaliacao NUMBER(1) CONSTRAINT nn_nota_preco_avaliacao NOT NULL,
    descricao_avaliacao VARCHAR2(255)
);

CREATE TABLE historico (
    id_historico NUMBER(9) CONSTRAINT pk_id_historico PRIMARY KEY,
    id_cotacao NUMBER(9) CONSTRAINT fk_id_cotacao_historico REFERENCES cotacao(id_cotacao) CONSTRAINT nn_cotacao_historico NOT NULL,
    data_historico DATE CONSTRAINT nn_data_historico NOT NULL,
    id_status NUMBER(9) CONSTRAINT fk_id_status_historico REFERENCES status(id_status) CONSTRAINT nn_status_historico NOT NULL,
    id_fornecedor NUMBER(9) CONSTRAINT fk_id_fornecedor_historico REFERENCES usuario(id_usuario) CONSTRAINT nn_fornecedor_historico NOT NULL,
    valor_ofertado_historico NUMBER(8,2),
    recusado_por_produto NUMBER(1) CONSTRAINT nn_rec_prod_historico NOT NULL,
    recusado_por_quantidade NUMBER(1) CONSTRAINT nn_rec_qtd_historico NOT NULL,
    recusado_por_preco NUMBER(1) CONSTRAINT nn_rec_prec_historico NOT NULL,
    recusado_por_prazo NUMBER(1) CONSTRAINT nn_rec_praz_historico NOT NULL,
    descricao_historico VARCHAR2(255)
);

-- 01.02. INSERTS NAS TABELAS:
INSERT INTO usuario VALUES (1, 'One Servi�os Administrativos LTDA.', 'comercial@oneservicos.com.br', 'oneserv123', '28434667000111', 0, NULL);
INSERT INTO usuario VALUES (2, 'Kalunga Com�rcio e Ind�stria Gr�fica LTDA.', 'vendas@kalunga.com.br', 'kalung4', '43283811000150', 1, 'https://iguatemi.com.br/brasilia/sites/brasilia/files/2020-01/Kalunga_logo.png');
INSERT INTO usuario VALUES (3, 'Kabum S.A.', 'adm@kabum.com.br', 'kabuuuuum', '05570714000159', 1, NULL);
INSERT INTO usuario VALUES (4, 'Kuar� Capital Gestora de Recursos LTDA.', 'operacional@kuaracapital.com', 'farialima', '41179663000100', 0, NULL);
INSERT INTO usuario VALUES (5, 'Magazine Luiza S.A.', 'magalu@magalu.com.br', 'vempromagalu', '47960950000121', 1, 'https://logodownload.org/wp-content/uploads/2014/06/magalu-logo-0.png');

INSERT INTO contato VALUES (1, 'Email', 'kaue@oneservicos.com.br', 1);
INSERT INTO contato VALUES (2, 'Email', 'vendas@kalunga.com.br', 2);
INSERT INTO contato VALUES (3, 'Telefone', '(11) 3200-0000', 2);
INSERT INTO contato VALUES (4, 'Telefone', '(11) 98282-0000', 2);
INSERT INTO contato VALUES (5, 'Whatsapp', '(11) 98585-0000', 3);

INSERT INTO tag VALUES (1, 'Perif�ricos');
INSERT INTO tag VALUES (2, 'Cal�as');
INSERT INTO tag VALUES (3, 'Eletrodom�sticos');
INSERT INTO tag VALUES (4, 'Celulares');
INSERT INTO tag VALUES (5, '�gua');

INSERT INTO usuario_tag VALUES (5, 4);
INSERT INTO usuario_tag VALUES (5, 5);
INSERT INTO usuario_tag VALUES (2, 2);
INSERT INTO usuario_tag VALUES (2, 3);
INSERT INTO usuario_tag VALUES (3, 2);

INSERT INTO departamento VALUES (1, 'Inform�tica', 'icone-informatica');
INSERT INTO departamento VALUES (2, 'Eletr�nicos', 'icone-eletr�nicos');
INSERT INTO departamento VALUES (3, 'Vestu�rio', 'icone-vestuario');
INSERT INTO departamento VALUES (4, 'Bebidas', 'icone-bebidas');
INSERT INTO departamento VALUES (5, 'Cozinha', NULL);

INSERT INTO tag_departamento VALUES (1, 1);
INSERT INTO tag_departamento VALUES (2, 1);
INSERT INTO tag_departamento VALUES (3, 5);
INSERT INTO tag_departamento VALUES (4, 1);
INSERT INTO tag_departamento VALUES (5, 4);

INSERT INTO produto VALUES (1, 1, 'Mouse', 'Logitech', 'Preto', NULL, NULL, NULL);
INSERT INTO produto VALUES (2, 4, '�gua', 'Lind�ia', NULL, '1 Litro', NULL, NULL);
INSERT INTO produto VALUES (3, 2, 'Celular', 'Apple', 'Vermelho', NULL, NULL, NULL);
INSERT INTO produto VALUES (4, 3, 'Cal�a', 'Hering', 'Vermelho', 'P', 'Jeans', 'Modelo XYZ');
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
INSERT INTO status VALUES (5, 'Conclu�do');

INSERT INTO cotacao VALUES (1, TO_DATE('2023-11-13', 'YYYY-MM-DD'), 1, 1, 20, 50.99, 1, 3, 1, 2, 10, NULL); 
INSERT INTO cotacao VALUES (2, TO_DATE('2023-11-12', 'YYYY-MM-DD'), 1, 1, 10, 69.90, 5, 2, 1, 3, 5, TO_DATE('2023-11-13', 'YYYY-MM-DD')); 
INSERT INTO cotacao VALUES (3, TO_DATE('2023-11-11', 'YYYY-MM-DD'), 4, 3, 5, 1500.00, 1, 1, 2, 3, 2, NULL); 
INSERT INTO cotacao VALUES (4, TO_DATE('2023-11-10', 'YYYY-MM-DD'), 4, 2, 100, 2.00, 1, 2, 1, 3, 1, NULL); 
INSERT INTO cotacao VALUES (5, TO_DATE('2023-11-09', 'YYYY-MM-DD'), 1, 5, 2, 3.500, 5, 3, 1, 2, 7, TO_DATE('2023-11-10', 'YYYY-MM-DD'));

-- Valores inseridos somente para contemplar os 5 inserts solicitados, por�m s� ser�o avaliados as cota��es de status conclu�do.
INSERT INTO avaliacao VALUES (1, 1, TO_DATE('2023-11-13', 'YYYY-MM-DD'), 5, 3, 3, 'Muito bom!');
INSERT INTO avaliacao VALUES (2, 2, TO_DATE('2023-11-12', 'YYYY-MM-DD'), 3, 4, 2, NULL); 
INSERT INTO avaliacao VALUES (3, 3, TO_DATE('2023-11-11', 'YYYY-MM-DD'), 3, 1, 5, 'Demora para chegar'); 
INSERT INTO avaliacao VALUES (4, 4, TO_DATE('2023-11-10', 'YYYY-MM-DD'), 1, 1, 1, 'Compra cancelada'); 
INSERT INTO avaliacao VALUES (5, 5, TO_DATE('2023-11-09', 'YYYY-MM-DD'), 5, 5, 5, 'Excelente'); 

INSERT INTO historico VALUES (1, 2, TO_DATE('2023-11-12', 'YYYY-MM-DD'), 2, 3, NULL, 0, 1, 0, 0, 'N�o tenho a quantidade no momento');
INSERT INTO historico VALUES (2, 2, TO_DATE('2023-11-12', 'YYYY-MM-DD'), 4, 2, 59.90, 0, 0, 0, 0, NULL);
INSERT INTO historico VALUES (3, 2, TO_DATE('2023-11-12', 'YYYY-MM-DD'), 5, 5, 44.50, 0, 0, 0, 0, NULL);
INSERT INTO historico VALUES (4, 3, TO_DATE('2023-11-12', 'YYYY-MM-DD'), 2, 5, 2000, 0, 0, 1, 0, 'Recusado Automaticamente: Pre�o ofertado maior do que o pre�o da cota��o');
INSERT INTO historico VALUES (5, 5, TO_DATE('2023-11-12', 'YYYY-MM-DD'), 5, 1, 2999.99, 0, 0, 0, 0, NULL);

-- 01.03. CONSULTANDO COTA��ES, STATUS E AVALIA��ES:
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

-- 02.01. FUN��O PARA VALIDAR A ENTRADA DE UM EMAIL:
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

-- 02.01.01. TESTANDO ENTRADA DE EMAILS:
DECLARE
    email_valido VARCHAR2(100);
    email_invalido VARCHAR2(100);
BEGIN
    email_valido := 'exemplo@dominio.com';
    IF is_email_valid(email_valido) THEN
        DBMS_OUTPUT.PUT_LINE(email_valido || ' � v�lido');
    ELSE
        DBMS_OUTPUT.PUT_LINE(email_valido || ' � inv�lido');
    END IF;

    email_invalido := 'exemplo@dominio';
    IF is_email_valid(email_invalido) THEN
        DBMS_OUTPUT.PUT_LINE(email_invalido || ' � v�lido');
    ELSE
        DBMS_OUTPUT.PUT_LINE(email_invalido || ' � inv�lido');
    END IF;
END;

-- 02.02. FUN��O PARA VALIDAR A ENTRADA DE UM CNPJ:
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

-- 02.02.01. TESTANDO ENTRADA DE CNPJ:
DECLARE
    cnpj_valido VARCHAR2(14) := '28434667000111';
    cnpj_invalido VARCHAR2(14) := '12345678000100';
    resultado BOOLEAN;
BEGIN
    resultado := is_cnpj_valid(cnpj_valido);
    IF resultado THEN
        DBMS_OUTPUT.PUT_LINE('CNPJ v�lido: ' || cnpj_valido || ' � v�lido');
    ELSE
        DBMS_OUTPUT.PUT_LINE('CNPJ v�lido: ' || cnpj_valido || ' � inv�lido');
    END IF;

    resultado := is_cnpj_valid(cnpj_invalido);
    IF resultado THEN
        DBMS_OUTPUT.PUT_LINE('CNPJ inv�lido: ' || cnpj_invalido || ' � v�lido');
    ELSE
        DBMS_OUTPUT.PUT_LINE('CNPJ inv�lido: ' || cnpj_invalido || ' � inv�lido');
    END IF;
END;

-- 02.03 PROCEDURES DE INSERT, UPDATE e DELETE:
-- 02.03.01 USUARIO:
DROP SEQUENCE seq_usuario;
CREATE SEQUENCE seq_usuario START WITH 6 INCREMENT BY 1;

-- 02.03.01.01. INSERT E TESTE:
CREATE OR REPLACE PROCEDURE insert_usuario(
    p_nome_usuario IN usuario.nome_usuario%TYPE,
    p_email_usuario IN usuario.email_usuario%TYPE,
    p_senha_usuario IN usuario.senha_usuario%TYPE,
    p_cnpj_usuario IN usuario.cnpj_usuario%TYPE,
    p_is_fornecedor IN usuario.is_fornecedor%TYPE,
    p_imagem_usuario IN usuario.imagem_usuario%TYPE
) AS
    v_id_usuario usuario.id_usuario%TYPE;
BEGIN
    SELECT seq_usuario.NEXTVAL INTO v_id_usuario FROM DUAL;

    IF NOT is_email_valid(p_email_usuario) THEN
        RAISE_APPLICATION_ERROR(-20024, 'E-mail inv�lido');
    END IF;
    
    IF NOT is_cnpj_valid(p_cnpj_usuario) THEN
        RAISE_APPLICATION_ERROR(-20024, 'Cnpj inv�lido');
    END IF;

    INSERT INTO usuario (id_usuario, nome_usuario, email_usuario, senha_usuario, cnpj_usuario, is_fornecedor, imagem_usuario)
    VALUES (v_id_usuario, p_nome_usuario, p_email_usuario, p_senha_usuario, p_cnpj_usuario, p_is_fornecedor, p_imagem_usuario);

EXCEPTION
    WHEN VALUE_ERROR THEN
        RAISE_APPLICATION_ERROR(-20017, 'Erro de valor na inser��o');
    WHEN DUP_VAL_ON_INDEX THEN
        RAISE_APPLICATION_ERROR(-20018, 'Chave duplicada');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20019, SQLERRM);
END;

BEGIN
    insert_usuario('Usuario Cadastrado por Procedure','email@email.com', 'senha123', '02647408000185', 1, NULL);
    DBMS_OUTPUT.PUT_LINE('Inser��o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.01.02. UPDATE E TESTE:
CREATE OR REPLACE PROCEDURE update_usuario(
    p_id_usuario IN usuario.id_usuario%TYPE,
    p_nome_usuario IN usuario.nome_usuario%TYPE,
    p_email_usuario IN usuario.email_usuario%TYPE,
    p_senha_usuario IN usuario.senha_usuario%TYPE,
    p_cnpj_usuario IN usuario.cnpj_usuario%TYPE,
    p_is_fornecedor IN usuario.is_fornecedor%TYPE,
    p_imagem_usuario IN usuario.imagem_usuario%TYPE
) AS
BEGIN
    IF NOT is_email_valid(p_email_usuario) THEN
        RAISE_APPLICATION_ERROR(-20025, 'E-mail inv�lido');
    END IF;
    
    IF NOT is_cnpj_valid(p_cnpj_usuario) THEN
        RAISE_APPLICATION_ERROR(-20024, 'Cnpj inv�lido');
    END IF;

    UPDATE usuario
    SET nome_usuario = p_nome_usuario, email_usuario = p_email_usuario, senha_usuario = p_senha_usuario, cnpj_usuario = p_cnpj_usuario, is_fornecedor = p_is_fornecedor, imagem_usuario = p_imagem_usuario
    WHERE id_usuario = p_id_usuario;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20020, 'Registro n�o encontrado para atualiza��o');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20021, SQLERRM);
END;

BEGIN
    update_usuario(6, 'Usuario Editado por Procedure','gmail@gmail.com', '123senha', '15436940000103', 0, NULL);
    DBMS_OUTPUT.PUT_LINE('Atualiza��o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.01.03. DELETE E TESTE:
CREATE OR REPLACE PROCEDURE delete_usuario(
    p_id_usuario IN usuario.id_usuario%TYPE
) AS
BEGIN
    DELETE FROM usuario
    WHERE id_usuario = p_id_usuario;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20022, 'Registro n�o encontrado para exclus�o');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20023, SQLERRM);
END;

BEGIN
    delete_usuario(6);
    DBMS_OUTPUT.PUT_LINE('Exclus�o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.02. CONTATO:
DROP SEQUENCE seq_contato;
CREATE SEQUENCE seq_contato START WITH 6 INCREMENT BY 1;

-- 02.03.02.01. INSERT E TESTE:
CREATE OR REPLACE PROCEDURE insert_contato(
    p_tipo_contato IN contato.tipo_contato%TYPE,
    p_valor_contato IN contato.valor_contato%TYPE,
    p_id_usuario IN contato.id_usuario%TYPE
) AS
    v_id_contato contato.id_contato%TYPE;
BEGIN
    SELECT seq_contato.NEXTVAL INTO v_id_contato FROM DUAL;
    
    INSERT INTO contato (id_contato, tipo_contato, valor_contato, id_usuario)
    VALUES (v_id_contato, p_tipo_contato, p_valor_contato, p_id_usuario);

EXCEPTION
    WHEN VALUE_ERROR THEN
        RAISE_APPLICATION_ERROR(-20026, 'Erro de valor na inser��o');
    WHEN DUP_VAL_ON_INDEX THEN
        RAISE_APPLICATION_ERROR(-20027, 'Chave duplicada');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20028, SQLERRM);
END;

BEGIN
    insert_contato('Whatsapp', '(21) 98383-0000', 3);
    DBMS_OUTPUT.PUT_LINE('Inser��o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.02.02. UPDATE E TESTE:
CREATE OR REPLACE PROCEDURE update_contato(
    p_id_contato IN contato.id_contato%TYPE,
    p_tipo_contato IN contato.tipo_contato%TYPE,
    p_valor_contato IN contato.valor_contato%TYPE,
    p_id_usuario IN contato.id_usuario%TYPE
) AS
BEGIN
    UPDATE contato
    SET tipo_contato = p_tipo_contato, valor_contato = p_valor_contato, id_usuario = p_id_usuario
    WHERE id_contato = p_id_contato;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20029, 'Registro n�o encontrado para atualiza��o');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20030, SQLERRM);
END;

BEGIN
    update_contato(6, 'Whatsapp Editado', '(21) 00000-1111', 2);
    DBMS_OUTPUT.PUT_LINE('Atualiza��o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.02.03. DELETE E TESTE:
CREATE OR REPLACE PROCEDURE delete_contato(
    p_id_contato IN contato.id_contato%TYPE
) AS
BEGIN
    DELETE FROM contato
    WHERE id_contato = p_id_contato;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20031, 'Registro n�o encontrado para exclus�o');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20032, SQLERRM);
END;

BEGIN
    delete_contato(6);
    DBMS_OUTPUT.PUT_LINE('Exclus�o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.03. TAG:
DROP SEQUENCE seq_tag;
CREATE SEQUENCE seq_tag START WITH 6 INCREMENT BY 1;

-- 02.03.03.01. NSERT E TESTE:
CREATE OR REPLACE PROCEDURE insert_tag(
    p_nome_tag IN tag.nome_tag%TYPE
) AS
    v_id_tag tag.id_tag%TYPE;
BEGIN
    SELECT seq_tag.NEXTVAL INTO v_id_tag FROM DUAL;
    
    INSERT INTO tag (id_tag, nome_tag)
    VALUES (v_id_tag, p_nome_tag);

EXCEPTION
    WHEN VALUE_ERROR THEN
        RAISE_APPLICATION_ERROR(-20049, 'Erro de valor na inser��o');
    WHEN DUP_VAL_ON_INDEX THEN
        RAISE_APPLICATION_ERROR(-20050, 'Chave duplicada');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20051, SQLERRM);
END;

BEGIN
    insert_tag('Tag Exemplo Procedure');
    DBMS_OUTPUT.PUT_LINE('Inser��o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.03.02. UPDATE E TESTE:
CREATE OR REPLACE PROCEDURE update_tag(
    p_id_tag IN tag.id_tag%TYPE,
    p_nome_tag IN tag.nome_tag%TYPE
) AS
BEGIN
    UPDATE tag
    SET nome_tag = p_nome_tag
    WHERE id_tag = p_id_tag;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20052, 'Registro n�o encontrado para atualiza��o');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20053, SQLERRM);
END;

BEGIN
    update_tag(6, 'Tag Atualizada');
    DBMS_OUTPUT.PUT_LINE('Atualiza��o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.03.03. DELETE E TESTE:
CREATE OR REPLACE PROCEDURE delete_tag(
    p_id_tag IN tag.id_tag%TYPE
) AS
BEGIN
    DELETE FROM tag
    WHERE id_tag = p_id_tag;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20054, 'Registro n�o encontrado para exclus�o');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20055, SQLERRM);
END;

BEGIN
    delete_tag(6);
    DBMS_OUTPUT.PUT_LINE('Exclus�o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.04. USUARIO_TAG:
-- 02.03.04.01. INSERT E TESTE:
CREATE OR REPLACE PROCEDURE insert_usuario_tag(
    p_id_usuario IN usuario_tag.id_usuario%TYPE,
    p_id_tag IN usuario_tag.id_tag%TYPE
) AS
BEGIN
    INSERT INTO usuario_tag (id_usuario, id_tag)
    VALUES (p_id_usuario, p_id_tag);

EXCEPTION
    WHEN VALUE_ERROR THEN
        RAISE_APPLICATION_ERROR(-20056, 'Erro de valor na inser��o');
    WHEN DUP_VAL_ON_INDEX THEN
        RAISE_APPLICATION_ERROR(-20057, 'Rela��o duplicada');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20058, SQLERRM);
END;

BEGIN
    insert_usuario_tag(1, 1);
    DBMS_OUTPUT.PUT_LINE('Inser��o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.04.02. UPDATE E TESTE:
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
        RAISE_APPLICATION_ERROR(-20061, 'Rela��o n�o encontrada para atualiza��o');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20062, SQLERRM);
END;

BEGIN
    update_usuario_tag(1, 1, 1, 3);
    DBMS_OUTPUT.PUT_LINE('Atualiza��o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.04.03. DELETE E TESTE:
CREATE OR REPLACE PROCEDURE delete_usuario_tag(
    p_id_usuario IN usuario_tag.id_usuario%TYPE,
    p_id_tag IN usuario_tag.id_tag%TYPE
) AS
BEGIN
    DELETE FROM usuario_tag
    WHERE id_usuario = p_id_usuario AND id_tag = p_id_tag;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20059, 'Rela��o n�o encontrada para exclus�o');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20060, SQLERRM);
END;

BEGIN
    delete_usuario_tag(1, 3);
    DBMS_OUTPUT.PUT_LINE('Exclus�o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.05. DEPARTAMENTO:
DROP SEQUENCE seq_departamento;
CREATE SEQUENCE seq_departamento START WITH 6 INCREMENT BY 1;

-- 02.03.05.01. INSERT E TESTE:
CREATE OR REPLACE PROCEDURE insert_departamento(
    p_nome_departamento IN departamento.nome_departamento%TYPE,
    p_icone_departamento IN departamento.icone_departamento%TYPE
) AS
    v_id_departamento departamento.id_departamento%TYPE;
BEGIN
    SELECT seq_departamento.NEXTVAL INTO v_id_departamento FROM DUAL;
    
    INSERT INTO departamento (id_departamento, nome_departamento, icone_departamento)
    VALUES (v_id_departamento, p_nome_departamento, p_icone_departamento);

EXCEPTION
    WHEN VALUE_ERROR THEN
        RAISE_APPLICATION_ERROR(-20063, 'Erro de valor na inser��o');
    WHEN DUP_VAL_ON_INDEX THEN
        RAISE_APPLICATION_ERROR(-20064, 'Chave duplicada');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20065, SQLERRM);
END;

BEGIN
    insert_departamento('TI', 'icone_ti.png');
    DBMS_OUTPUT.PUT_LINE('Inser��o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.05.02. UPDATE E TESTE:
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
        RAISE_APPLICATION_ERROR(-20066, 'Registro n�o encontrado para atualiza��o');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20067, SQLERRM);
END;

BEGIN
    update_departamento(6, 'Tecnologia da Informa��o', 'icone_ti_novo.png');
    DBMS_OUTPUT.PUT_LINE('Atualiza��o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.05.03. DELETE E TESTE:
CREATE OR REPLACE PROCEDURE delete_departamento(
    p_id_departamento IN departamento.id_departamento%TYPE
) AS
BEGIN
    DELETE FROM departamento
    WHERE id_departamento = p_id_departamento;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20068, 'Registro n�o encontrado para exclus�o');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20069, SQLERRM);
END;

BEGIN
    delete_departamento(6);
    DBMS_OUTPUT.PUT_LINE('Exclus�o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.06. TAG_DEPARTAMENTO:
-- 02.03.06.01. INSERT E TESTE:
CREATE OR REPLACE PROCEDURE insert_tag_departamento(
    p_id_tag IN tag_departamento.id_tag%TYPE,
    p_id_departamento IN tag_departamento.id_departamento%TYPE
) AS
BEGIN
    INSERT INTO tag_departamento (id_tag, id_departamento)
    VALUES (p_id_tag, p_id_departamento);

EXCEPTION
    WHEN VALUE_ERROR THEN
        RAISE_APPLICATION_ERROR(-20070, 'Erro de valor na inser��o');
    WHEN DUP_VAL_ON_INDEX THEN
        RAISE_APPLICATION_ERROR(-20071, 'Rela��o duplicada');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20072, SQLERRM);
END;

BEGIN
    insert_tag_departamento(1, 4);
    DBMS_OUTPUT.PUT_LINE('Inser��o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.06.02. UPDATE E TESTE:
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
        RAISE_APPLICATION_ERROR(-20075, 'Rela��o n�o encontrada para atualiza��o');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20076, SQLERRM);
END;

BEGIN
    update_tag_departamento(1, 4, 1, 2);
    DBMS_OUTPUT.PUT_LINE('Atualiza��o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.06.03. DELETE E TESTE:
CREATE OR REPLACE PROCEDURE delete_tag_departamento(
    p_id_tag IN tag_departamento.id_tag%TYPE,
    p_id_departamento IN tag_departamento.id_departamento%TYPE
) AS
BEGIN
    DELETE FROM tag_departamento
    WHERE id_tag = p_id_tag AND id_departamento = p_id_departamento;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20073, 'Rela��o n�o encontrada para exclus�o');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20074, SQLERRM);
END;

BEGIN
    delete_tag_departamento(1, 2);
    DBMS_OUTPUT.PUT_LINE('Exclus�o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.07. PRODUTO:
DROP SEQUENCE seq_produto;
CREATE SEQUENCE seq_produto START WITH 6 INCREMENT BY 1;

-- 02.03.07.01. INSERT E TESTE:
CREATE OR REPLACE PROCEDURE insert_produto(
    p_id_departamento IN produto.id_departamento%TYPE,
    p_nome_produto IN produto.nome_produto%TYPE,
    p_marca_produto IN produto.marca_produto%TYPE,
    p_cor_produto IN produto.cor_produto%TYPE,
    p_tamanho_produto IN produto.tamanho_produto%TYPE,
    p_material_produto IN produto.material_produto%TYPE,
    p_observacao_produto IN produto.observacao_produto%TYPE
) AS
    v_id_produto produto.id_produto%TYPE;
BEGIN
    SELECT seq_produto.NEXTVAL INTO v_id_produto FROM DUAL;
    
    INSERT INTO produto (id_produto, id_departamento, nome_produto, marca_produto, cor_produto, tamanho_produto, material_produto, observacao_produto)
    VALUES (v_id_produto, p_id_departamento, p_nome_produto, p_marca_produto, p_cor_produto, p_tamanho_produto, p_material_produto, p_observacao_produto);

EXCEPTION
    WHEN VALUE_ERROR THEN
        RAISE_APPLICATION_ERROR(-20077, 'Erro de valor na inser��o');
    WHEN DUP_VAL_ON_INDEX THEN
        RAISE_APPLICATION_ERROR(-20078, 'Chave duplicada');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20079, SQLERRM);
END;

BEGIN
    insert_produto(1, 'Produto Teste', 'Marca Teste', 'Azul', 'M', 'Algod�o', 'Sem observa��es');
    DBMS_OUTPUT.PUT_LINE('Inser��o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.07.02. UPDATE E TESTE:
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
        RAISE_APPLICATION_ERROR(-20080, 'Registro n�o encontrado para atualiza��o');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20081, SQLERRM);
END;

BEGIN
    update_produto(6, 2, 'Produto Atualizado', 'Marca Nova', 'Vermelho', 'G', 'Poli�ster', 'Atualizado');
    DBMS_OUTPUT.PUT_LINE('Atualiza��o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.07.03. DELETE E TESTE:
CREATE OR REPLACE PROCEDURE delete_produto(
    p_id_produto IN produto.id_produto%TYPE
) AS
BEGIN
    DELETE FROM produto
    WHERE id_produto = p_id_produto;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20082, 'Registro n�o encontrado para exclus�o');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20083, SQLERRM);
END;

BEGIN
    delete_produto(6);
    DBMS_OUTPUT.PUT_LINE('Exclus�o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.08 PRODUTO_TAG:
-- 02.03.08.01. INSERT E TESTE:
CREATE OR REPLACE PROCEDURE insert_produto_tag(
    p_id_produto IN produto_tag.id_produto%TYPE,
    p_id_tag IN produto_tag.id_tag%TYPE
) AS
BEGIN
    INSERT INTO produto_tag (id_produto, id_tag)
    VALUES (p_id_produto, p_id_tag);

EXCEPTION
    WHEN VALUE_ERROR THEN
        RAISE_APPLICATION_ERROR(-20084, 'Erro de valor na inser��o');
    WHEN DUP_VAL_ON_INDEX THEN
        RAISE_APPLICATION_ERROR(-20085, 'Rela��o duplicada');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20086, SQLERRM);
END;

BEGIN
    insert_produto_tag(1, 4);
    DBMS_OUTPUT.PUT_LINE('Inser��o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.08.02. UPDATE E TESTE:
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
        RAISE_APPLICATION_ERROR(-20087, 'Rela��o n�o encontrada para atualiza��o');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20088, SQLERRM);
END;

BEGIN
    update_produto_tag(1, 4, 1, 2);
    DBMS_OUTPUT.PUT_LINE('Atualiza��o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.08.03. DELETE E TESTE:
CREATE OR REPLACE PROCEDURE delete_produto_tag(
    p_id_produto IN produto_tag.id_produto%TYPE,
    p_id_tag IN produto_tag.id_tag%TYPE
) AS
BEGIN
    DELETE FROM produto_tag
    WHERE id_produto = p_id_produto AND id_tag = p_id_tag;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20089, 'Rela��o n�o encontrada para exclus�o');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20090, SQLERRM);
END;

BEGIN
    delete_produto_tag(1, 2);
    DBMS_OUTPUT.PUT_LINE('Exclus�o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.09. STATUS:
DROP SEQUENCE seq_status;
CREATE SEQUENCE seq_status START WITH 6 INCREMENT BY 1;

-- 02.03.09.01. INSERT E TESTE:
CREATE OR REPLACE PROCEDURE insert_status(
    p_nome_status IN status.nome_status%TYPE
) AS
    v_id_status status.id_status%TYPE;
BEGIN
    SELECT seq_status.NEXTVAL INTO v_id_status FROM DUAL;

    INSERT INTO status (id_status, nome_status)
    VALUES (v_id_status, p_nome_status);

EXCEPTION
    WHEN VALUE_ERROR THEN
        RAISE_APPLICATION_ERROR(-20091, 'Erro de valor na inser��o');
    WHEN DUP_VAL_ON_INDEX THEN
        RAISE_APPLICATION_ERROR(-20092, 'Chave duplicada');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20093, SQLERRM);
END;

BEGIN
    insert_status('Em Processamento');
    DBMS_OUTPUT.PUT_LINE('Inser��o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.09.02. UPDATE E TESTE:
CREATE OR REPLACE PROCEDURE update_status(
    p_id_status IN status.id_status%TYPE,
    p_nome_status IN status.nome_status%TYPE
) AS
BEGIN
    UPDATE status
    SET nome_status = p_nome_status
    WHERE id_status = p_id_status;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20094, 'Registro n�o encontrado para atualiza��o');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20095, SQLERRM);
END;

BEGIN
    update_status(6, 'OK');
    DBMS_OUTPUT.PUT_LINE('Atualiza��o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.09.03. DELETE E TESTE:
CREATE OR REPLACE PROCEDURE delete_status(
    p_id_status IN status.id_status%TYPE
) AS
BEGIN
    DELETE FROM status
    WHERE id_status = p_id_status;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20096, 'Registro n�o encontrado para exclus�o');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20097, SQLERRM);
END;

BEGIN
    delete_status(6);
    DBMS_OUTPUT.PUT_LINE('Exclus�o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.10. COTACAO:
DROP SEQUENCE seq_cotacao;
CREATE SEQUENCE seq_cotacao START WITH 6 INCREMENT BY 1;

-- 02.03.10.01. INSERT E TESTE:
CREATE OR REPLACE PROCEDURE insert_cotacao(
    p_data_abertura_cotacao IN cotacao.data_abertura_cotacao%TYPE,
    p_id_usuario IN cotacao.id_usuario%TYPE,
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
    v_id_cotacao cotacao.id_cotacao%TYPE;
BEGIN
    SELECT seq_cotacao.NEXTVAL INTO v_id_cotacao FROM DUAL;
    
    INSERT INTO cotacao (id_cotacao, data_abertura_cotacao, id_usuario, id_produto, quantidade_produto, valor_produto, id_status, prioridade_entrega, prioridade_qualidade, prioridade_preco, prazo_cotacao, data_fechamento_cotacao)
    VALUES (v_id_cotacao, p_data_abertura_cotacao, p_id_usuario, p_id_produto, p_quantidade_produto, p_valor_produto, p_id_status, p_prioridade_entrega, p_prioridade_qualidade, p_prioridade_preco, p_prazo_cotacao, p_data_fechamento_cotacao);

EXCEPTION
    WHEN VALUE_ERROR THEN
        RAISE_APPLICATION_ERROR(-20098, 'Erro de valor na inser��o');
    WHEN DUP_VAL_ON_INDEX THEN
        RAISE_APPLICATION_ERROR(-20099, 'Chave duplicada');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20100, SQLERRM);
END;

BEGIN
    insert_cotacao(SYSDATE, 1, 1, 100, 500, 1, 1, 1, 1, 30, SYSDATE);
    DBMS_OUTPUT.PUT_LINE('Inser��o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.10.02. UPDATE E TESTE:
CREATE OR REPLACE PROCEDURE update_cotacao(
    p_id_cotacao IN cotacao.id_cotacao%TYPE,
    p_data_abertura_cotacao IN cotacao.data_abertura_cotacao%TYPE,
    p_id_usuario IN cotacao.id_usuario%TYPE,
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
    SET data_abertura_cotacao = p_data_abertura_cotacao, id_usuario = p_id_usuario, id_produto = p_id_produto, quantidade_produto = p_quantidade_produto, valor_produto = p_valor_produto, id_status = p_id_status, prioridade_entrega = p_prioridade_entrega, prioridade_qualidade = p_prioridade_qualidade, prioridade_preco = p_prioridade_preco, prazo_cotacao = p_prazo_cotacao, data_fechamento_cotacao = p_data_fechamento_cotacao
    WHERE id_cotacao = p_id_cotacao;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20101, 'Registro n�o encontrado para atualiza��o');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20102, SQLERRM);
END;

BEGIN
    update_cotacao(6, SYSDATE, 1, 2, 150, 750, 2, 0, 1, 0, 45, SYSDATE);
    DBMS_OUTPUT.PUT_LINE('Atualiza��o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.10.03. DELETE E TESTE:
CREATE OR REPLACE PROCEDURE delete_cotacao(
    p_id_cotacao IN cotacao.id_cotacao%TYPE
) AS
BEGIN
    DELETE FROM cotacao
    WHERE id_cotacao = p_id_cotacao;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20103, 'Registro n�o encontrado para exclus�o');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20104, SQLERRM);
END;

BEGIN
    delete_cotacao(6);
    DBMS_OUTPUT.PUT_LINE('Exclus�o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.11. AVALIACAO:
-- POR SER 1 �NICA AVALIA��O POR COTA��O, INSIRA UMA COTA��O DE ID 6 PARA REALIZAR ESSE TESTE
DROP SEQUENCE seq_avaliacao;
CREATE SEQUENCE seq_avaliacao START WITH 6 INCREMENT BY 1;

-- 02.03.11.01. INSERT E TESTE:
CREATE OR REPLACE PROCEDURE insert_avaliacao(
    p_id_cotacao IN avaliacao.id_cotacao%TYPE,
    p_data_avaliacao IN avaliacao.data_avaliacao%TYPE,
    p_nota_entrega_avaliacao IN avaliacao.nota_entrega_avaliacao%TYPE,
    p_nota_qualidade_avaliacao IN avaliacao.nota_qualidade_avaliacao%TYPE,
    p_nota_preco_avaliacao IN avaliacao.nota_preco_avaliacao%TYPE,
    p_descricao_avaliacao IN avaliacao.descricao_avaliacao%TYPE
) AS
    v_id_avaliacao avaliacao.id_avaliacao%TYPE;
BEGIN
    SELECT seq_avaliacao.NEXTVAL INTO v_id_avaliacao FROM DUAL;
    
    INSERT INTO avaliacao (id_avaliacao, id_cotacao, data_avaliacao, nota_entrega_avaliacao, nota_qualidade_avaliacao, nota_preco_avaliacao, descricao_avaliacao)
    VALUES (v_id_avaliacao, p_id_cotacao, p_data_avaliacao, p_nota_entrega_avaliacao, p_nota_qualidade_avaliacao, p_nota_preco_avaliacao, p_descricao_avaliacao);

EXCEPTION
    WHEN VALUE_ERROR THEN
        RAISE_APPLICATION_ERROR(-20105, 'Erro de valor na inser��o');
    WHEN DUP_VAL_ON_INDEX THEN
        RAISE_APPLICATION_ERROR(-20106, 'Chave duplicada');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20107, SQLERRM);
END;

BEGIN
    insert_avaliacao(6, SYSDATE, 4, 5, 4, 'Avalia��o positiva');
    DBMS_OUTPUT.PUT_LINE('Inser��o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.11.02. UPDATE E TESTE:
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
        RAISE_APPLICATION_ERROR(-20108, 'Registro n�o encontrado para atualiza��o');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20109, SQLERRM);
END;

BEGIN
    update_avaliacao(6, 6, SYSDATE, 3, 4, 5, 'Avalia��o atualizada');
    DBMS_OUTPUT.PUT_LINE('Atualiza��o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.11.03. DELETE E TESTE:
CREATE OR REPLACE PROCEDURE delete_avaliacao(
    p_id_avaliacao IN avaliacao.id_avaliacao%TYPE
) AS
BEGIN
    DELETE FROM avaliacao
    WHERE id_avaliacao = p_id_avaliacao;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20110, 'Registro n�o encontrado para exclus�o');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20111, SQLERRM);
END;

BEGIN
    delete_avaliacao(6);
    DBMS_OUTPUT.PUT_LINE('Exclus�o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.12. HISTORICO (OBS: N�O FUNCIONA COM NUMERO DECIMAL NO VALOR_OFERTADO_HISTORICO. N�O CONSEGUIMOS RESOLVER)
DROP SEQUENCE seq_historico;
CREATE SEQUENCE seq_historico START WITH 6 INCREMENT BY 1;

-- 02.03.12.01. INSERT E TESTE:
CREATE OR REPLACE PROCEDURE insert_historico(
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
    v_id_historico historico.id_historico%TYPE;
BEGIN
    SELECT seq_historico.NEXTVAL INTO v_id_historico FROM DUAL;
    
    INSERT INTO historico (id_historico, id_cotacao, data_historico, id_status, id_fornecedor, valor_ofertado_historico, recusado_por_produto, recusado_por_quantidade, recusado_por_preco, recusado_por_prazo, descricao_historico)
    VALUES (v_id_historico, p_id_cotacao, p_data_historico, p_id_status, p_id_fornecedor, p_valor_ofertado_historico, p_recusado_por_produto, p_recusado_por_quantidade, p_recusado_por_preco, p_recusado_por_prazo, p_descricao_historico);

EXCEPTION
    WHEN VALUE_ERROR THEN
        RAISE_APPLICATION_ERROR(-20112, 'Erro de valor na inser��o');
    WHEN DUP_VAL_ON_INDEX THEN
        RAISE_APPLICATION_ERROR(-20113, 'Chave duplicada');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20114, SQLERRM);
END;

BEGIN
    insert_historico(2, SYSDATE, 1, 2, 15000, 0, 0, 0, 0, 'Hist�rico inicial da cota��o');
    DBMS_OUTPUT.PUT_LINE('Inser��o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.12.02. UPDATE E TESTE:
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
        RAISE_APPLICATION_ERROR(-20115, 'Registro n�o encontrado para atualiza��o');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20116, SQLERRM);
END;

BEGIN
    update_historico(6, 2, SYSDATE, 2, 3, 550, 1, 0, 1, 0, 'Atualiza��o do hist�rico da cota��o');
    DBMS_OUTPUT.PUT_LINE('Atualiza��o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.03.12.03. DELETE E TESTE:
CREATE OR REPLACE PROCEDURE delete_historico(
    p_id_historico IN historico.id_historico%TYPE
) AS
BEGIN
    DELETE FROM historico
    WHERE id_historico = p_id_historico;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20117, 'Registro n�o encontrado para exclus�o');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20118, SQLERRM);
END;

BEGIN
    delete_historico(6);
    DBMS_OUTPUT.PUT_LINE('Exclus�o bem-sucedida');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro: ' || SQLERRM);
END;

-- 02.04. BLOCO AN�NIMO COM CURSOR PARA CONSULTA COM JOIN:
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

        DBMS_OUTPUT.PUT_LINE('ID Cota��o: ' || cotacao_avaliacao_rec.id_cotacao ||
                             ', Data Abertura: ' || cotacao_avaliacao_rec.data_abertura_cotacao ||
                             ', Nota Entrega: ' || cotacao_avaliacao_rec.nota_entrega_avaliacao ||
                             ', Nota Qualidade: ' || cotacao_avaliacao_rec.nota_qualidade_avaliacao ||
                             ', Nota Pre�o: ' || cotacao_avaliacao_rec.nota_preco_avaliacao);
    END LOOP;

    CLOSE cotacao_avaliacao_cur;
END;

-- 02.05. PROCEDURE IMPRIMINDO RELAT�RIO QUE CONTENHA FUN��ES, INNER JOIN, ORDER BY, SUM OU COUNT
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
    WHERE s.nome_status = 'Conclu�do';
    DBMS_OUTPUT.PUT_LINE('Total de Cota��es Conclu�das: ' || v_total_cotacoes);
    DBMS_OUTPUT.PUT_LINE('Valor M�dio das Cota��es Coclu�das: ' || v_valor_medio);

    FOR r IN (
        SELECT id_cotacao, data_abertura_cotacao, valor_produto
        FROM cotacao c
        JOIN status s ON c.id_status = s.id_status
        WHERE s.nome_status = 'Conclu�do'
        ORDER BY data_abertura_cotacao DESC
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('ID da Cota��o: ' || r.id_cotacao || 
                             ', Data de Abertura: ' || r.data_abertura_cotacao || 
                             ', Valor: ' || r.valor_produto);
    END LOOP;

EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro ao gerar relat�rio de cota��es.');
END;

-- 02.05.01. TESTANDO PROCEDURE COM RELAT�RIO:
BEGIN
    print_cotacao_report;
END;

--------------------------------------------------------------------------------

-- SPRINT 03:
-- 03.01. PROCEDIMENTO 01
CREATE OR REPLACE PROCEDURE listar_cotacoes_pendentes(
    p_id_usuario IN cotacao.id_usuario%TYPE
) AS
BEGIN
    FOR r IN (
        SELECT c.id_cotacao, c.data_abertura_cotacao, c.quantidade_produto, c.valor_produto, s.nome_status
        FROM cotacao c
        JOIN status s ON c.id_status = s.id_status
        WHERE c.id_usuario = p_id_usuario AND s.nome_status IN ('Em Andamento', 'Recusado')
        ORDER BY c.data_abertura_cotacao DESC
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('ID da Cota��o: ' || r.id_cotacao || 
                             ', Data de Abertura: ' || r.data_abertura_cotacao || 
                             ', Quantidade: ' || r.quantidade_produto || 
                             ', Valor: ' || r.valor_produto ||
                             ', Status: ' || r.nome_status);
    END LOOP;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro ao listar cota��es pendentes.');
END;

BEGIN
    listar_cotacoes_pendentes(1);
END;

-- 03.02. PROCEDIMENTO 02
CREATE OR REPLACE PROCEDURE concluir_cotacao(
    p_id_cotacao IN cotacao.id_cotacao%TYPE
) AS
    v_status_concluido NUMBER;
BEGIN
    SELECT id_status INTO v_status_concluido FROM status WHERE nome_status = 'Conclu�do';
    
    UPDATE cotacao
    SET id_status = v_status_concluido, data_fechamento_cotacao = SYSDATE
    WHERE id_cotacao = p_id_cotacao;
    
    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Cota��o n�o encontrada ou j� est� conclu�da.');
    END IF;
    
    DBMS_OUTPUT.PUT_LINE('Cota��o conclu�da com sucesso.');
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20003, 'Status "Conclu�do" n�o encontrado.');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20004, SQLERRM);
END;

BEGIN
    concluir_cotacao(1); 
END;

-- 03.03. FUN��O 01
CREATE OR REPLACE FUNCTION valor_total_cotacoes_usuario(p_id_usuario IN cotacao.id_usuario%TYPE)
RETURN NUMBER IS
    v_total NUMBER(10,2) := 0;
BEGIN
    SELECT SUM(valor_produto * quantidade_produto)
    INTO v_total
    FROM cotacao
    WHERE id_usuario = p_id_usuario;

    RETURN NVL(v_total, 0);
EXCEPTION
    WHEN OTHERS THEN
        RETURN NULL;
END;

SELECT valor_total_cotacoes_usuario(1) FROM dual;

-- 03.04. FUN��O 02
CREATE OR REPLACE FUNCTION verificar_existencia_email(p_email_usuario IN usuario.email_usuario%TYPE)
RETURN BOOLEAN IS
    v_contagem NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_contagem
    FROM usuario
    WHERE email_usuario = p_email_usuario;

    IF v_contagem > 0 THEN
        RETURN TRUE;
    ELSE
        RETURN FALSE;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        RETURN FALSE;
END;

DECLARE
    v_existe BOOLEAN;
BEGIN
    v_existe := verificar_existencia_email('email@exemplo.com');
    IF v_existe THEN
        DBMS_OUTPUT.PUT_LINE('Usu�rio existe.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Usu�rio n�o existe.');
    END IF;
END;

-- 03.05. CRIA��O DE TABELAS PARA FUNCIONAMENTO DO GATILHO
DROP TABLE monitoramento_atualizacoes CASCADE CONSTRAINTS;
DROP TABLE log_erros CASCADE CONSTRAINTS;

CREATE TABLE monitoramento_atualizacoes (
    id_monitoramento NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    id_cotacao NUMBER(9),
    valor_antigo NUMBER(8,2),
    valor_novo NUMBER(8,2),
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    usuario VARCHAR2(255)
);

CREATE TABLE log_erros (
    id_erro NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    codigo_erro VARCHAR2(255),
    nome_erro VARCHAR2(255),
    data_ocorrencia TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    usuario VARCHAR2(255)
);

-- 03.05.01. GATILHO PARA MONITORAMENTO DE ATUALIZA��ES
CREATE OR REPLACE TRIGGER trg_monitora_atualizacao_cotacao
AFTER UPDATE OF valor_produto ON cotacao
FOR EACH ROW
WHEN (OLD.valor_produto != NEW.valor_produto)
DECLARE
    v_usuario VARCHAR2(255) := 'UsuarioTeste';
    v_id_cotacao cotacao.id_cotacao%TYPE := :OLD.id_cotacao;
    v_valor_antigo cotacao.valor_produto%TYPE := :OLD.valor_produto;
    v_valor_novo cotacao.valor_produto%TYPE := :NEW.valor_produto;
    v_codigo_erro NUMBER;
    v_nome_erro VARCHAR2(2000);
BEGIN
    INSERT INTO monitoramento_atualizacoes(id_cotacao, valor_antigo, valor_novo, usuario)
    VALUES (v_id_cotacao, v_valor_antigo, v_valor_novo, v_usuario);
EXCEPTION
    WHEN OTHERS THEN
        v_codigo_erro := SQLCODE;
        v_nome_erro := SQLERRM;
        INSERT INTO log_erros(codigo_erro, nome_erro, usuario)
        VALUES (TO_CHAR(v_codigo_erro), v_nome_erro, v_usuario);
END;

-- 03.05.02. TESTANDO O GATILHO
UPDATE cotacao SET valor_produto = 150 WHERE id_cotacao = 1;

-- 03.05.03. VALIDANDO O GATILHO
SELECT * FROM monitoramento_atualizacoes;
SELECT * FROM log_erros;

--------------------------------------------------------------------------------