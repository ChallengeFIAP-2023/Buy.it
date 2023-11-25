# Documentação das Classes

## Classe `Telefone`

Representa um tipoContato no sistema.

**Atributos:**
- `id_telefone` (NUMBER): Identificador do tipoContato.
- `ddi_telefone` (VARCHAR): DDI do tipoContato.
- `ddd_telefone` (VARCHAR): DDD do tipoContato.
- `numero_telefone` (VARCHAR): Número do tipoContato.
- `id_contato` (NUMBER): Identificador do contato associado.
- `fk_Contato_id_contato` (NUMBER): Chave estrangeira para o contato.

## Classe `Contato`

Representa um contato no sistema.

**Atributos:**
- `id_contato` (NUMBER): Identificador do contato.
- `nome_contato` (VARCHAR): Nome do contato.
- `id_usuario` (NUMBER): Identificador do usuário associado.
- `fk_Usuario_id_usuario` (NUMBER): Chave estrangeira para o usuário.

## Classe `Email`

Representa um email no sistema.

**Atributos:**
- `id_email` (NUMBER): Identificador do email.
- `endereco_email` (VARCHAR): Endereço de email.
- `id_contato` (NUMBER): Identificador do contato associado.
- `fk_Contato_id_contato` (NUMBER): Chave estrangeira para o contato.

## Classe `Pessoa_Juridica`

Representa uma pessoa jurídica no sistema.

**Atributos:**
- `id_pj` (NUMBER): Identificador da pessoa jurídica.
- `is_fornecedor_pj` (NUMBER): Indica se é fornecedor (0 para não, 1 para sim).
- `cnpj_pj` (CHAR): CNPJ da pessoa jurídica.
- `fk_Pessoa_id_pessoa` (NUMBER): Chave estrangeira para a pessoa.

## Classe `Pessoa`

Representa uma pessoa no sistema.

**Atributos:**
- `id_pessoa` (NUMBER): ID único da pessoa.
- `nome_pessoa` (VARCHAR): Nome da pessoa.
- `imagem_pessoa` (VARCHAR): URL da imagem da pessoa.

## Classe `Usuario`

Representa um usuário no sistema.

**Atributos:**
- `id_usuario` (NUMBER): ID único do usuário.
- `email_usuario` (VARCHAR): Endereço de e-mail do usuário.
- `senha_usuario` (VARCHAR): Senha do usuário.
- `id_pessoa` (NUMBER): ID da pessoa associada ao usuário.

## Classe `Usuario_Tag`

Representa a relação entre usuários e tags.

**Atributos:**
- `fk_Usuario_id_usuario` (NUMBER): ID do usuário.
- `fk_Tag_id_tag` (NUMBER): ID da tag.

## Classe `Tag`

Representa uma tag no sistema.

**Atributos:**
- `id_tag` (NUMBER): ID único da tag.
- `nome_tag` (VARCHAR): Nome da tag.

## Classe `Tag_Departamento`

Representa a relação entre tags e departamentos.

**Atributos:**
- `fk_Departamento_id_departamento` (NUMBER): ID do departamento.
- `fk_Tag_id_tag` (NUMBER): ID da tag.

## Classe `Departamento`

Representa um departamento no sistema.

**Atributos:**
- `id_departamento` (NUMBER): ID único do departamento.
- `nome_departamento` (VARCHAR): Nome do departamento.
- `icone_departamento` (VARCHAR): URL do ícone do departamento.

## Classe `Produto_Departamento`

Representa a relação entre produtos e departamentos.

**Atributos:**
- `fk_Produto_id_produto` (NUMBER): ID do produto.
- `fk_Departamento_id_departamento` (NUMBER): ID do departamento.

## Classe `Produto`

Representa um produto no sistema.

**Atributos:**
- `id_produto` (NUMBER): Identificador único do produto.
- `nome_produto` (VARCHAR): Nome do produto.
- `marca_produto` (VARCHAR): Marca do produto.
- `cor_produto` (VARCHAR): Cor do produto.
- `tamanho_produto` (VARCHAR): Tamanho do produto.
- `material_produto` (VARCHAR): Material do produto.
- `observacao_produto` (VARCHAR): Observações adicionais sobre o produto.

## Classe `Cotacao`

Representa uma cotação de compra ou venda de um produto.

**Atributos:**
- `id_cotacao` (NUMBER): Identificador único da cotação.
- `data_abertura_cotacao` (DATE): Data de abertura da cotação.
- `id_comprador` (NUMBER): Identificador do comprador.
- `id_fornecedor` (NUMBER): Identificador do fornecedor.
- `id_produto` (NUMBER): Identificador do produto cotado.
- `quantidade_produto` (NUMBER): Quantidade do produto cotado.
- `valor_cotacao` (NUMBER): Valor da cotação.
- `id_status` (NUMBER): Status da cotação.
- `prioridade_entrega` (NUMBER): Prioridade de entrega.
- `prioridade_qualidade` (NUMBER): Prioridade de qualidade.
- `prioridade_preco` (NUMBER): Prioridade de preço.
- `prazo_cotacao` (NUMBER): Prazo para a cotação.
- `data_fechamento_cotacao` (DATE): Data de fechamento da cotação.
- `fk_Usuario_id_usuario` (NUMBER): Chave estrangeira para o usuário.
- `fk_Status_id_status` (NUMBER): Chave estrangeira para o status.
- `fk_Produto_id_produto` (NUMBER): Chave estrangeira para o produto.

## Classe `Historico`

Representa o histórico de cotações.

**Atributos:**
- `id_historico` (NUMBER(9)): ID primário do histórico.
- `id_cotacao` (NUMBER(9)): ID da cotação relacionada.
- `id_status` (NUMBER(9)): ID do status atual da cotação.
- `recusado_por_produto` (NUMBER(1)): Indica se a cotação foi recusada por questões relativas ao produto.
- `recusado_por_quantidade` (NUMBER(1)): Indica se a cotação foi recusada por questões de quantidade.
- `recusado_por_preco` (NUMBER(1)): Indica se a cotação foi recusada por questões de preço.
- `recusado_por_prazo` (NUMBER(1)): Indica se a cotação foi recusada por questões de prazo.
- `descricao_historico` (VARCHAR(255)): Descrição do registro no histórico.
- `data_historico` (DATE): Data do registro no histórico.
- `fk_Cotacao_id_cotacao` (NUMBER(9)): Chave estrangeira referenciando a cotação.
- `fk_Status_id_status` (NUMBER(9)): Chave estrangeira referenciando o status.

## Classe `Status`

Representa o status de uma cotação ou pedido.

**Atributos:**
- `id_status` (NUMBER(9)): ID primário do status.
- `nome_status` (VARCHAR(255)): Nome do status.

## Classe `Avaliacao`

Representa uma avaliação feita por um usuário a uma cotação.

**Atributos:**
- `id_avaliacao` (int): ID da avaliação.
- `descricao_avaliacao` (String): Descrição da avaliação.
- `nota_qualidade_avaliacao` (int): Nota de qualidade na avaliação.
- `nota_preco_avaliacao` (int): Nota de preço na avaliação.
- `nota_entrega_avaliacao` (int): Nota de entrega na avaliação.
- `id_cotacao` (int): ID da cotação avaliada.
- `data_avaliacao` (Date): Data em que a avaliação foi realizada.

---

### Exemplos de Requisições HTTP

Aqui estão exemplos de como interagir com a API usando os métodos HTTP (GET, POST, PUT, DELETE):

---
## Endpoint **Telefone**


#### `GET /telefones`

Lista todos os telefones.

**Exemplo de retorno:**
```json
[
    {
        "id_telefone": 1,
        "ddi_telefone": "55",
        "ddd_telefone": "11",
        "numero_telefone": "999999999",
        "id_contato": 10,
    },
    {
        "id_telefone": 2,
        "ddi_telefone": "55",
        "ddd_telefone": "11",
        "numero_telefone": "999999998",
        "id_contato": 4,
    }
]
```


#### `POST /tipoContato`

Cadastra um tipoContato.

**Exemplo do body da requisição:**
```json
{
    "ddi_telefone": "55",
    "ddd_telefone": "21",
    "numero_telefone": "888888888",
    "id_contato": 20
}

```

#### `UPDATE /tipoContato/{id}`

Atualiza um tipoContato.

**Exemplo do body da requisição:**
```json
{
    "ddi_telefone": "55",
    "ddd_telefone": "11",
    "numero_telefone": "777777777"
}

```

#### `DELETE /tipoContato/{id}`

Deleta um tipoContato.

---

---
## Endpoint **Contato**


#### `GET /contatos`

Lista todos os contatos.

**Exemplo de retorno:**
```json
[
    {
        "id_contato": 1,
        "nome_contato": "Mariana",
        "id_usuario": 5,
    },
    {
        "id_contato": 2,
        "nome_contato": "Mario",
        "id_usuario": 6,
    }
]

```


#### `POST /contato`

Cadastra um contato.

**Exemplo do body da requisição:**
```json
{
    "nome_contato": "Novo Contato",
    "id_usuario": 6
}

```

#### `UPDATE /contato/{id}`

Atualiza um contato.

**Exemplo do body da requisição:**
```json
{
    "nome_contato": "Contato Atualizado"
}

```

#### `DELETE /contato/{id}`

Deleta um contato.

---

## Endpoint **Email**


#### `GET /emails`

Lista todos os emails.

**Exemplo de retorno:**
```json
[
    {
        "id_email": 1,
        "endereco_email": "exemplo@email.com",
        "id_contato": 5,
    },
    {
        "id_email": 2,
        "endereco_email": "exemplo2@email.com",
        "id_contato": 6,
    }
]
```


#### `POST /email`

Cadastra um email.

**Exemplo do body da requisição:**
```json
{
    "endereco_email": "novoemail@email.com",
    "id_contato": 10
}
```

#### `UPDATE /email/{id}`

Atualiza um email.

**Exemplo do body da requisição:**
```json
{
    "endereco_email": "emailatualizado@email.com"
}
```

#### `DELETE /email/{id}`

Deleta um email.

---

## Endpoint **Pessoa_Juridica**


#### `GET /pessoas_juridicas`

Lista todos as pessoas jurídicas..

**Exemplo de retorno:**
```json
[
    {
        "id_pj": 1,
        "is_fornecedor_pj": 1,
        "cnpj_pj": "00.000.000/0001-00",
        "fk_Pessoa_id_pessoa": 2
    },
    {
        "id_pj": 2,
        "is_fornecedor_pj": 0,
        "cnpj_pj": "00.000.000/0002-00",
        "fk_Pessoa_id_pessoa": 1
    }
]

```

#### `POST /pessoa_juridica`

Cadastra uma pessoa jurídica.

**Exemplo do body da requisição:**
```json
{
    "is_fornecedor_pj": 1,
    "cnpj_pj": "11.111.111/1111-11",
    "fk_Pessoa_id_pessoa": 3
}
```

#### `UPDATE /pessoa_juridica/{id}`

Atualiza uma pessoa jurídica.

**Exemplo do body da requisição:**
```json
{
    "is_fornecedor_pj": 0,
    "cnpj_pj": "22.222.222/2222-22"
}
```

#### `DELETE /pessoa_juridica/{id}`

Deleta uma pessoa jurídica.

---

## Endpoint **Pessoa**


#### `GET /pessoas`

Lista todas as pessoas.

**Exemplo de retorno:**
```json
[
    {
        "id_pessoa": 1,
        "nome_pessoa": "João da Silva",
        "imagem_pessoa": "https://imagem-pessoa1.png"
    },
    {
        "id_pessoa": 2,
        "nome_pessoa": "João da Costa",
        "imagem_pessoa": "https://imagem-pessoa2.png"
    }
]
```


#### `POST /pessoa`

Cadastra uma pessoa.

**Exemplo do body da requisição:**
```json
{
    "nome_pessoa": "Maria de Souza",
    "imagem_pessoa": "https://imagem-pessoa3.png"
}
```

#### `UPDATE /pessoa/{id}`

Atualiza uma pessoa.

**Exemplo do body da requisição:**
```json
{
    "nome_pessoa": "Maria de Souza Atualizada",
    "imagem_pessoa": "https://imagem-pessoa3-atualizada.png"
}
```

#### `DELETE /pessoa/{id}`

Deleta uma pessoa.

---

## Endpoint **Usuario**


#### `GET /usuarios`

Lista todos os usuarios.

**Exemplo de retorno:**
```json
[
    {
        "id_usuario": 1,
        "email_usuario": "usuario1@email.com",
        "senha_usuario": "senha1",
        "id_pessoa": 2
    },
    {
        "id_usuario": 2,
        "email_usuario": "usuario2@email.com",
        "senha_usuario": "senha2",
        "id_pessoa": 1
    }
]
```


#### `POST /usuario`

Cadastra um usuario.

**Exemplo do body da requisição:**
```json
{
    "email_usuario": "novousuario@email.com",
    "senha_usuario": "novasenha",
    "id_pessoa": 3
}
```

#### `UPDATE /usuario/{id}`

Atualiza um usuario.

**Exemplo do body da requisição:**
```json
{
    "email_usuario": "emailatualizado@email.com",
    "senha_usuario": "senhaatualizada"
}
```

#### `DELETE /usuario/{id}`

Deleta um usuario.

---

## Endpoint **Usuario_Tag**


#### `GET /usuario_tags`

Lista todas as relações entre usuários e tags.

**Exemplo de retorno:**
```json
[
    {
        "fk_Usuario_id_usuario": 1,
        "fk_Tag_id_tag": 2
    },
    {
        "fk_Usuario_id_usuario": 2,
        "fk_Tag_id_tag": 1
    }
]
```


#### `POST /usuario_tag`

Cria uma relação entre um usuário e uma tag.

**Exemplo do body da requisição:**
```json
{
    "fk_Usuario_id_usuario": 3,
    "fk_Tag_id_tag": 4
}
```

#### `UPDATE /usuario_tag/{id}`

Atualiza uma relação entre um usuário e uma tag.

**Exemplo do body da requisição:**
```json
{
    "fk_Usuario_id_usuario": 3,
    "fk_Tag_id_tag": 5
}
```

#### `DELETE /usuario_tag/{id}`

Deleta uma relação entre um usuário e uma tag.

---

## Endpoint **Tag**


#### `GET /tags`

Lista todas as tags.

**Exemplo de retorno:**
```json
[
    {
        "id_tag": 1,
        "nome_tag": "Iluminação "
    },
    {
        "id_tag": 2,
        "nome_tag": "Fechaduras"
    }
]

```


#### `POST /tag`

Cadastra uma nova tag.

**Exemplo do body da requisição:**
```json
{
    "nome_tag": "Nova Tag"
}
```

#### `UPDATE /tag/{id}`

Atualiza uma tag.

**Exemplo do body da requisição:**
```json
{ 
    "nome_tag": "TagAtualizada"
}
```

#### `DELETE /tag/{id}`

Deleta uma tag.

---

## Endpoint **Tag_Departamento**


#### `GET /tag_departamentos`

Lista todas as relações entre tags e departamentos.

**Exemplo de retorno:**
```json
[
    {
        "fk_Departamento_id_departamento": 1,
        "fk_Tag_id_tag": 2
    },
    {
        "fk_Departamento_id_departamento": 2,
        "fk_Tag_id_tag": 1
    }
]
```


#### `POST /tag_departamento`

Cria uma relação entre uma tag e um departamento.

**Exemplo do body da requisição:**
```json
{
    "fk_Departamento_id_departamento": 3,
    "fk_Tag_id_tag": 4
}
```

#### `UPDATE /tag_departamento/{id}`

Atualiza uma relação entre uma tag e um departamento.

**Exemplo do body da requisição:**
```json
{
    "fk_Departamento_id_departamento": 3,
    "fk_Tag_id_tag": 5
}
```

#### `DELETE /tag_departamento/{id}`

Deleta uma relação entre uma tag e um departamento.

---

## Endpoint **Departamento**


#### `GET /departamentos`

Lista todos os departamentos.

**Exemplo de retorno:**
```json
[
    {
        "id_departamento": 1,
        "nome_departamento": "Periféricos",
        "icone_departamento": "https://icone-departamento-periféricos.png"
    },
    {
        "id_departamento": 2,
        "nome_departamento": "Eletrodomésticos",
        "icone_departamento": "https://icone-departamento-eletrodomésticos.png"
    }
]
```


#### `POST /departamento`

Cadastra um departamento.

**Exemplo do body da requisição:**
```json
{
    "nome_departamento": "Novo Departamento",
    "icone_departamento": "https://icone-novo-departamento.png"
}
```

#### `UPDATE /departamento/{id}`

Atualiza um departamento.

**Exemplo do body da requisição:**
```json
{
    "nome_departamento": "Departamento Atualizado",
    "icone_departamento": "https://icone-departamento-atualizado.png"
}
```

#### `DELETE /departamento/{id}`

Deleta um departamento.

---

## Endpoint **Produto_Departamento**


#### `GET /produto_departamentos`

Lista todas as relações entre produtos e departamentos.

**Exemplo de retorno:**
```json
[
    {
        "fk_Produto_id_produto": 1,
        "fk_Departamento_id_departamento": 2
    },
    {
        "fk_Produto_id_produto": 2,
        "fk_Departamento_id_departamento": 1
    }
]
```


#### `POST /produto_departamento`

Cria uma relação entre um produto e um departamento.

**Exemplo do body da requisição:**
```json
{
    "fk_Produto_id_produto": 3,
    "fk_Departamento_id_departamento": 4
}
```

#### `UPDATE /produto_departamento/{id}`

Atualiza uma relação entre um produto e um departamento.

**Exemplo do body da requisição:**
```json
{
    "fk_Produto_id_produto": 3,
    "fk_Departamento_id_departamento": 5
}
```

#### `DELETE /produto_departamento/{id}`

Deleta uma relação entre um produto e um departamento.

---

## Endpoint **Produto**


#### `GET /produtos`

Lista todos os produtos.

**Exemplo de retorno:**
```json
[
    {
        "id_produto": 1,
        "nome_produto": "Produto 1",
        "marca_produto": "Marca 1",
        "cor_produto": "Vermelho",
        "tamanho_produto": "Pequeno",
        "material_produto": "Poliester",
        "observação_produto": "Perfeito para casas pequenas",
    },
    {
        "id_produto": 2,
        "nome_produto": "Produto 2",
        "marca_produto": "Marca 2",
        "cor_produto": "Azul",
        "tamanho_produto": "Grande",
        "material_produto": "Ferro",
        "observação_produto": "Perfeito para casas grandes",
    }
]
```


#### `POST /produto`

Cadastra um produto.

**Exemplo do body da requisição:**
```json
{
    "id_produto": 3,
    "nome_produto": "Produto 3",
    "marca_produto": "Marca 3",
    "cor_produto": "Roxo",
    "tamanho_produto": "Médio",
    "material_produto": "Ouro",
    "observação_produto": "Sem observação",
}
```

#### `UPDATE /produto/{id}`

Atualiza um produto.

**Exemplo do body da requisição:**
```json
{
    "id_produto": 3,
    "nome_produto": "Produto Atualizado",
    "marca_produto": "Marca Atualizado",
    "cor_produto": "Amarelo",
    "tamanho_produto": "Médio",
    "material_produto": "Ouro",
    "observação_produto": "Observações atualizadas"
}
```

#### `DELETE /produto/{id}`

Deleta um produto.

---
## Endpoint **Cotacao**


#### `GET /cotacoes`

Lista todos as cotacoes.

**Exemplo de retorno:**
```json
[
    {
        "id_cotacao": 1,
        "data_abertura_cotacao": "2023-01-01",
        "id_usuario": 2,
        "id_fornecedor": 3,
        "id_produto": 4,
        "quantidade_produto": 100,
        "valor_cotacao": 500.00,
        "id_status": 1,
        "prioridade_entrega": 1,
        "prioridade_qualidade": 2,
        "prioridade_preco": 3,
        "prazo_cotacao": 30,
        "data_fechamento_cotacao": "2024-01-31",
        "fk_Usuario_id_usuario": 5,
        "fk_Status_id_status": 1,
        "fk_Produto_id_produto": 4
    },
    {
        "id_cotacao": 2,
        "data_abertura_cotacao": "2023-12-01",
        "id_usuario": 1,
        "id_fornecedor": 2,
        "id_produto": 3,
        "quantidade_produto": 50,
        "valor_cotacao": 400.00,
        "id_status": 2,
        "prioridade_entrega": 3,
        "prioridade_qualidade": 2,
        "prioridade_preco": 1,
        "prazo_cotacao": 10,
        "data_fechamento_cotacao": "2024-01-31",
    }
]
```


#### `POST /cotacao`

Cadastra uma cotação.

**Exemplo do body da requisição:**
```json
{
    "data_abertura_cotacao": "2023-02-01",
    "id_usuario": 5,
    "id_fornecedor": 6,
    "id_produto": 7,
    "quantidade_produto": 200,
    "valor_cotacao": 1000.00,
    "id_status": 2,
    "prioridade_entrega": 2,
    "prioridade_qualidade": 2,
    "prioridade_preco": 2,
    "prazo_cotacao": 60,
    "data_fechamento_cotacao": "2023-03-02",
}

```

#### `UPDATE /cotacao/{id}`

Atualiza uma cotação.

**Exemplo do body da requisição:**
```json
{
    "data_abertura_cotacao": "2023-02-01",
    "id_usuario": 5,
    "id_fornecedor": 6,
    "id_produto": 7,
    "quantidade_produto": 200,
    "valor_cotacao": 2000.00,
    "id_status": 2,
    "prioridade_entrega": 2,
    "prioridade_qualidade": 2,
    "prioridade_preco": 2,
    "prazo_cotacao": 60,
    "data_fechamento_cotacao": "2023-03-04",
}
```

#### `DELETE /cotacao/{id}`

Deleta uma cotação.

---
## Endpoint **Status**


#### `GET /status`

Lista todos os status.

**Exemplo de retorno:**
```json
[
    {
        "id_status": 1,
        "nome_status": "Em análise"
    },
    {
        "id_status": 2,
        "nome_status": "Recusado"
    },
    {
        "id_status": 3,
        "nome_status": "Aceito"
    }
]
```


#### `POST /status`

Cadastra um status.

**Exemplo do body da requisição:**
```json
{
    "nome_status": "Em Andamento"
}

```

#### `UPDATE /status/{id}`

Atualiza um status.

**Exemplo do body da requisição:**
```json
{
    "nome_status": "Concluído"
}
```

#### `DELETE /status/{id}`

Deleta um status.

---

## Endpoint **Historico**


#### `GET /historicos`

Lista todos os registros históricos das cotações.

**Exemplo de retorno:**
```json
[
    {
        "id_historico": 1,
        "id_cotacao": 1,
        "id_status": 1,
        "recusado_por_produto": 0,
        "recusado_por_quantidade": 0,
        "recusado_por_preco": 0,
        "recusado_por_prazo": 0,
        "descricao_historico": "Descrição do histórico",
        "data_historico": "2023-01-01"
    },
    {
        "id_historico": 2,
        "id_cotacao": 2,
        "id_status": 2,
        "recusado_por_produto": 1,
        "recusado_por_quantidade": 1,
        "recusado_por_preco": 1,
        "recusado_por_prazo": 1,
        "descricao_historico": "Descrição do histórico",
        "data_historico": "2023-01-08"
    }
]

```


#### `POST /historico`

Cadastra um registro histórico de cotação.

**Exemplo do body da requisição:**
```json
{
    "id_historico": 3,
    "id_cotacao": 3,
    "id_status": 3,
    "recusado_por_produto": 1,
    "recusado_por_quantidade": 1,
    "recusado_por_preco": 0,
    "recusado_por_prazo": 1,
    "descricao_historico": "Descrição do histórico",
    "data_historico": "2024-01-20"
}

```

#### `UPDATE /historico/{id}`

Atualiza um registro histórico de cotação.

**Exemplo do body da requisição:**
```json
{
    "id_historico": 3,
    "id_cotacao": 3,
    "id_status": 3,
    "recusado_por_produto": 0,
    "recusado_por_quantidade": 0,
    "recusado_por_preco": 1,
    "recusado_por_prazo": 0,
    "descricao_historico": "Descrição do histórico",
    "data_historico": "2024-01-20"
}
```

#### `DELETE /historico/{id}`

Deleta um registro histórico.

---

## Endpoint **Avaliacao**


#### `GET /avaliacoes`

Lista todas as avaliações.

**Exemplo de retorno:**
```json
[
    {
        "id_avaliacao": 1,
        "descricao_avaliacao": "Excelente serviço",
        "nota_qualidade_avaliacao": 5,
        "nota_preco_avaliacao": 4,
        "nota_entrega_avaliacao": 5,
        "id_cotacao": 1,
        "data_avaliacao": "2023-01-10"
    },
    {
        "id_avaliacao": 2,
        "descricao_avaliacao": "Serviço ruim",
        "nota_qualidade_avaliacao": 1,
        "nota_preco_avaliacao": 2,
        "nota_entrega_avaliacao": 3,
        "id_cotacao": 2,
        "data_avaliacao": "2023-01-10"
    }
]

```


#### `POST /avaliacao`

Cadastra uma avaliação.

**Exemplo do body da requisição:**
```json
{
    "descricao_avaliacao": "Bom, mas pode melhorar",
    "nota_qualidade_avaliacao": 3,
    "nota_preco_avaliacao": 3,
    "nota_entrega_avaliacao": 4,
    "id_cotacao": 3,
    "data_avaliacao": "2023-02-05"
}

```

#### `UPDATE /avaliacao/{id}`

Atualiza uma avaliação.

**Exemplo do body da requisição:**
```json
{
    "descricao_avaliacao": "Bom, mas pode melhorar",
    "nota_qualidade_avaliacao": 4,
    "nota_preco_avaliacao": 3,
    "nota_entrega_avaliacao": 4,
    "id_cotacao": 3,
    "data_avaliacao": "2023-02-06"
}
```

#### `DELETE /avaliacao/{id}`

Deleta uma avaliação.

---

## Possíveis status code das requisições

| Código | Descrição
|-|-
| 200 | Requisição bem-sucedida
| 201 | Cadastrado com sucesso
| 204 | A requisição foi bem-sucedida, mas não há conteúdo para retornar.
| 400 | Os campos enviados são inválidos
| 404 | Página não encontrada
| 405 | Método não permitido
| 500 | Erro interno do servidor
