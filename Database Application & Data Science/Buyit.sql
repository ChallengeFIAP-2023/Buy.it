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

CREATE TABLE contato (
    id_contato NUMBER(9) CONSTRAINT pk_id_contato PRIMARY KEY,
    nome_contato VARCHAR2(255) CONSTRAINT nn_nm_contato NOT NULL,
    id_usuario NUMBER(9) CONSTRAINT fk_usuario_contato REFERENCES usuario(id_usuario) CONSTRAINT nn_usuario_contato NOT NULL
);

CREATE TABLE telefone (
    id_telefone NUMBER(9) CONSTRAINT pk_id_telefone PRIMARY KEY,
    ddi_telefone VARCHAR2(25) CONSTRAINT nn_ddi_telefone NOT NULL,
    ddd_telefone VARCHAR2(25),
    numero_telefone VARCHAR2(25) CONSTRAINT nn_num_telefone NOT NULL,
    id_contato NUMBER(9) CONSTRAINT fk_contato_telefone REFERENCES contato(id_contato) CONSTRAINT nn_contato_telefone NOT NULL
);

CREATE TABLE email (
    id_email NUMBER(9) CONSTRAINT pk_id_email PRIMARY KEY,
    endereco_email VARCHAR2(255) CONSTRAINT uk_end_email UNIQUE CONSTRAINT nn_end_email NOT NULL,
    id_contato NUMBER(9) CONSTRAINT fk_contato_email REFERENCES contato(id_contato) CONSTRAINT nn_contato_email NOT NULL
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
    id_fornecedor NUMBER(9) CONSTRAINT fk_fornecedor_cotacao REFERENCES usuario(id_usuario),
    id_produto NUMBER(9) CONSTRAINT fk_produto_cotacao REFERENCES produto(id_produto) CONSTRAINT nn_produto_cotacao NOT NULL,
    quantidade_produto NUMBER(8,2) CONSTRAINT nn_qtd_produto NOT NULL,
    valor_cotacao NUMBER(8,2) CONSTRAINT nn_vlr_cotacao NOT NULL,
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
    compra_finalizada NUMBER(1) CONSTRAINT nn_compra_fin_avaliacao NOT NULL,
    descricao_avaliacao VARCHAR2(255)
);

CREATE TABLE historico (
    id_historico NUMBER(9) CONSTRAINT pk_id_historico PRIMARY KEY,
    id_cotacao NUMBER(9) CONSTRAINT fk_cotacao_historico REFERENCES cotacao(id_cotacao) CONSTRAINT nn_cotacao_historico NOT NULL,
    data_historico DATE CONSTRAINT nn_dt_historico NOT NULL,
    id_status NUMBER(9) CONSTRAINT fk_status_historico REFERENCES status(id_status) CONSTRAINT nn_status_historico NOT NULL,
    id_fornecedor NUMBER(9) CONSTRAINT fk_fornecedor_historico REFERENCES usuario(id_usuario) CONSTRAINT nn_fornecedor_historico NOT NULL,
    recusado_por_produto NUMBER(1) CONSTRAINT nn_rec_prod_historico NOT NULL,
    recusado_por_quantidade NUMBER(1) CONSTRAINT nn_rec_qtd_historico NOT NULL,
    recusado_por_preco NUMBER(1) CONSTRAINT nn_rec_prec_historico NOT NULL,
    recusado_por_prazo NUMBER(1) CONSTRAINT nn_rec_praz_historico NOT NULL,
    descricao_historio VARCHAR2(255)
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

INSERT INTO contato VALUES (1, 'Kaue Caponero', 1);
INSERT INTO contato VALUES (2, 'Mariana Santos', 1);
INSERT INTO contato VALUES (3, 'Natan Cruz', 4);
INSERT INTO contato VALUES (4, 'Gustavo Sorrilha', 4);
INSERT INTO contato VALUES (5, 'Vitor Rubim', 4);

INSERT INTO telefone VALUES (1, '+55', '011', '98309-0659', 1);
INSERT INTO telefone VALUES (2, '+55', '021', '98264-3445', 1);
INSERT INTO telefone VALUES (3, '+1', NULL, '98105-0169', 3);
INSERT INTO telefone VALUES (4, '+55', '083', '98000-0129', 3);
INSERT INTO telefone VALUES (5, '+55', '011', '98308-9529', 2);

INSERT INTO email VALUES (1, 'kaue@one.com.br', 1);
INSERT INTO email VALUES (2, 'maryjane@hotmal.com', 2);
INSERT INTO email VALUES (3, 'natan@nates.com.br', 3);
INSERT INTO email VALUES (4, 'gustavao_vaicorinthians@gmail.com', 4);
INSERT INTO email VALUES (5, 'vitao@rubim.dev', 5);

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

INSERT INTO status VALUES (1, 'Pendente');
INSERT INTO status VALUES (2, 'Concluído');
INSERT INTO status VALUES (3, 'Recusado');
INSERT INTO status VALUES (4, 'Aguardando Avaliação');
INSERT INTO status VALUES (5, 'Exemplo 05');

INSERT INTO cotacao VALUES (1, TO_DATE('2023-11-13', 'YYYY-MM-DD'), 1, 3, 3, 10, 50.99, 1, 3, 1, 2, 10, NULL); 
INSERT INTO cotacao VALUES (2, TO_DATE('2023-11-12', 'YYYY-MM-DD'), 1, 2, 2, 20, 99.90, 2, 2, 1, 3, 5, TO_DATE('2023-11-13', 'YYYY-MM-DD')); 
INSERT INTO cotacao VALUES (3, TO_DATE('2023-11-11', 'YYYY-MM-DD'), 4, 2, 2, 50, 150.00, 3, 1, 2, 3, 2, NULL); 
INSERT INTO cotacao VALUES (4, TO_DATE('2023-11-10', 'YYYY-MM-DD'), 4, 3, 5, 30, 2.00, 4, 2, 1, 3, 1, TO_DATE('2023-11-10', 'YYYY-MM-DD')); 
INSERT INTO cotacao VALUES (5, TO_DATE('2023-11-09', 'YYYY-MM-DD'), 1, 5, 5, 100, 5.00, 4, 3, 1, 2, 7, NULL);

INSERT INTO avaliacao VALUES (1, 1, TO_DATE('2023-11-13', 'YYYY-MM-DD'), 5, 3, 3, 1, 'Muito bom!');
INSERT INTO avaliacao VALUES (2, 2, TO_DATE('2023-11-12', 'YYYY-MM-DD'), 3, 4, 2, 1, NULL); 
INSERT INTO avaliacao VALUES (3, 3, TO_DATE('2023-11-11', 'YYYY-MM-DD'), 3, 1, 5, 1, 'Demora para chegar'); 
INSERT INTO avaliacao VALUES (4, 4, TO_DATE('2023-11-10', 'YYYY-MM-DD'), 1, 1, 1, 0, 'Compra cancelada'); 
INSERT INTO avaliacao VALUES (5, 5, TO_DATE('2023-11-09', 'YYYY-MM-DD'), 5, 5, 5, 1, 'Excelente'); 

INSERT INTO historico VALUES (1, 2, TO_DATE('2023-11-12', 'YYYY-MM-DD'), 3, 3, 0, 1, 0, 0, 'Não tenho a quantidade no momento');
INSERT INTO historico VALUES (2, 2, TO_DATE('2023-11-12', 'YYYY-MM-DD'), 3, 2, 1, 1, 0, 0, 'Não trabalho mais com este produto');
INSERT INTO historico VALUES (3, 2, TO_DATE('2023-11-12', 'YYYY-MM-DD'), 2, 5, 0, 0, 0, 0, NULL);
INSERT INTO historico VALUES (4, 2, TO_DATE('2023-11-12', 'YYYY-MM-DD'), 4, 5, 0, 0, 0, 0, NULL);
INSERT INTO historico VALUES (5, 2, TO_DATE('2023-11-12', 'YYYY-MM-DD'), 4, 1, 0, 0, 0, 0, NULL);

-- 03.01. CONSULTANDO USUÁRIOS, CONTATOS E TELEFONES:
SELECT
    u.id_usuario, 
    u.email_usuario, 
    c.nome_contato, 
    t.ddi_telefone, 
    t.ddd_telefone, 
    t.numero_telefone
FROM 
    usuario u
JOIN 
    contato c ON u.id_usuario = c.id_usuario
JOIN 
    telefone t ON c.id_contato = t.id_contato;
    
-- 03.02. CONSULTANDO COTAÇÕES, STATUS E AVALIAÇÕES:
SELECT 
    c.id_cotacao, 
    c.data_abertura_cotacao, 
    c.quantidade_produto, 
    c.valor_cotacao, 
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
    v_valor_cotacao cotacao.valor_cotacao%TYPE;

BEGIN
    SELECT COUNT(*), AVG(valor_cotacao)
    INTO v_total_cotacoes, v_valor_medio
    FROM cotacao c
    JOIN status s ON c.id_status = s.id_status
    WHERE s.nome_status = 'Concluído';
    DBMS_OUTPUT.PUT_LINE('Total de Cotações Concluídas: ' || v_total_cotacoes);
    DBMS_OUTPUT.PUT_LINE('Valor Médio das Cotações Cocluídas: ' || v_valor_medio);

    FOR r IN (
        SELECT id_cotacao, data_abertura_cotacao, valor_cotacao
        FROM cotacao c
        JOIN status s ON c.id_status = s.id_status
        WHERE s.nome_status = 'Concluído'
        ORDER BY data_abertura_cotacao DESC
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('ID da Cotação: ' || r.id_cotacao || 
                             ', Data de Abertura: ' || r.data_abertura_cotacao || 
                             ', Valor: ' || r.valor_cotacao);
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
-- DROP TABLES CASO NECESSÁRIO:

DROP TABLE avaliacao CASCADE CONSTRAINTS;
DROP TABLE contato CASCADE CONSTRAINTS;
DROP TABLE cotacao CASCADE CONSTRAINTS;
DROP TABLE departamento CASCADE CONSTRAINTS;
DROP TABLE email CASCADE CONSTRAINTS;
DROP TABLE historico CASCADE CONSTRAINTS;
DROP TABLE pessoa CASCADE CONSTRAINTS;
DROP TABLE pessoa_juridica CASCADE CONSTRAINTS;
DROP TABLE produto CASCADE CONSTRAINTS;
DROP TABLE produto_departamento CASCADE CONSTRAINTS;
DROP TABLE status CASCADE CONSTRAINTS;
DROP TABLE tag CASCADE CONSTRAINTS;
DROP TABLE tag_departamento CASCADE CONSTRAINTS;
DROP TABLE telefone CASCADE CONSTRAINTS;
DROP TABLE usuario CASCADE CONSTRAINTS;
DROP TABLE usuario_tag CASCADE CONSTRAINTS;

