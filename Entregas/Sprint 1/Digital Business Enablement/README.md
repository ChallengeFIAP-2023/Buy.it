# Documentação das Classes

## Classe `Categoria`

Representa uma categoria do sistema.

**Atributos:**
- `nome_categoria` (String): Nome da categoria.
- `icone_categoria` (String): URL do ícone da categoria.

## Classe `Produto`

Representa um produto no sistema.

**Atributos:**
- `nome_produto`  (String): Nome do produto.
- `descricao_produto`  (String): Descrição do produto.
- `imagem_url_produto` (String): URL da imagem do produto.
- `id_categoria` (int): ID da categoria à qual o produto pertence.
- `marca_produto` (String): Marca do produto
- `menor_preco_unitario_produto` (float): Menor preço do produto.

## Classe `Tipo_Variacao`

Representa um tipo de variação de produto.

**Atributos:**
- `nome_variacao ` (String): Nome da variação.

## Classe `Valor_Variacao`

Representa um valor de variação de produto.

**Atributos:**
- `nome_valor_variacao `  (String): Nome do valor de variação.
- `id_valor_variacao ` (int): ID do tipo de variação à qual a opção pertence.

## Classe `Usuario`

Representa um usuário no sistema.

**Atributos:**
- `cnpj_usuario ` (String): CNPJ do usuário.
- `nome_usuario ` (String): Nome do usuário.
- `cep_usuario ` (String): CEP do usuário.
- `logradouro_usuario  ` (String): Logradouro do usuário.
- `complemento_usuario  ` (String): Complemento do endereço do usuário.
- `numero_endereco_usuario  ` (int): Número do endereço do usuário.
- `email_usuario  ` (String): Endereço de e-mail do usuário.
- `telefone_usuario  ` (String): Número de telefone do usuário.
- `e_fornecedor  ` (int): Indica se o usuário é um fornecedor (0 para não, 1 para sim).
- `senha_usuario   ` (String): Senha do usuário.
- `imagem_url_usuario elefone_usuario  ` (String): URL da imagem do usuário.
- `regime_tributario_usuario   ` (String): Regime tributário do usuário.
- `valor_max_automatico_usuario   ` (float): Valor máximo automático do usuário.

## Classe `Estoque`

Representa o estoque de um produto.

**Atributos:**
- `id_produto ` (int): ID do produto no estoque.
- `id_valor_variacao ` (int): ID da variação de produto no estoque.
- `quantidade_estoque ` (int): Quantidade em estoque.
- `preco_unitario `  (String): URL da imagem do estoque.
- `imagem_url_estoque ` (Time): Time ao qual o jogador pertence.
- `id_usuario  ` (int): ID do usuário responsável pelo estoque.

## Classe `Desconto`

Representa os descontos disponíveis para produtos.

**Atributos:**
- `quantidade_minima_produto` (int): Quantidade mínima do produto para aplicar o desconto.
- `desconto` (String): Valor do desconto.
- `id_estoque` (int): ID do estoque ao qual o desconto está associado.

## Classe `Tag`

Representa as tags associadas a produtos.

**Atributos:**
- `nome_tag` (String): Nome da tag.

## Classe `Pedido`

Representa um pedido feito por um usuário.

**Atributos:**
- `id_usuario` (int): ID do usuário que fez o pedido.
- `status_pedido` (String): Status do pedido.
- `data_pedido` (Date): Data do pedido.
- `valor_pedido` (float): Valor total do pedido.

## Classe `Log`

Representa um registro de log associado a um pedido.

**Atributos:**
- `id_pedido` (int): ID do pedido ao qual o log está relacionado.
- `timestamp_log` (String): Timestamp do log.
- `nome_log` (String): Nome do log.
- `descricao_log` (String): Descrição do log.

## Classe `Avaliacao`

Representa uma avaliação feita por um usuário a um pedido.

**Atributos:**
- `id_usuario` (int): ID do usuário que fez a avaliação.
- `id_pedido` (int): ID do pedido avaliado.
- `nota_preco_unitario_avaliacao` (int): Nota de preço na avaliação.
- `nota_qualidade_avaliacao` (int): Nota de qualidade na avaliação.
- `descricao_avaliacao` (String): Descrição da avaliação.
- `nota_entrega_avaliacao` (int): Nota de entrega na avaliação.

---

### Exemplos de Requisições HTTP

Aqui estão exemplos de como interagir com a API usando os métodos HTTP (GET, POST, PUT, DELETE):

---
## Endpoint **Categoria**


#### `GET /categorias`

Lista todos as categorias.

**Exemplo de retorno:**
```json
[
    {
        "nome_categoria": "Categoria1",
        "icone_categoria": "https://icone1.png"
    },
    {
        "nome_categoria": "Categoria2",
        "icone_categoria": "https://icone2.png"
    }
]
```


#### `POST /categoria`

Cadastra uma categoria.

**Exemplo do body da requisição:**
```json
{ 
    "nome_categoria": "NovaCategoria",
    "icone_categoria": "https://novocategoria.png"
}
```

#### `UPDATE /categoria/{id}`

Atualiza uma categoria.

**Exemplo do body da requisição:**
```json
{ 
    "nome_categoria": "CategoriaAtualizada",
    "icone_categoria": "https://categoriaatualizada.png"
}
```

#### `DELETE /categoria/{id}`

Deleta uma categoria.

---

---
## Endpoint **Produto**


#### `GET /produtos`

Lista todos os produtos.

**Exemplo de retorno:**
```json
[
    {
        "nome_produto": "Produto1",
        "descricao_produto": "Descrição do Produto 1",
        "imagem_url_produto": "https://imagem1.png",
        "id_categoria": 1,
        "marca_produto": "Marca3",
        "menor_preco_unitario_produto": 19.99
    },
    {
        "nome_produto": "Produto2",
        "descricao_produto": "Descrição do Produto 2",
        "imagem_url_produto": "https://imagem2.png",
        "id_categoria": 2,
        "marca_produto": "Marca2",
        "menor_preco_unitario_produto": 29.99
    }
]


```


#### `POST /produto`

Cadastra um produto.

**Exemplo do body da requisição:**
```json
{ 
    "nome_produto": "NovoProduto",
    "descricao_produto": "Descrição do Novo Produto",
    "imagem_url_produto": "https://novoproduto.png",
    "id_categoria": 1,
    "marca_produto": "MarcaNova",
    "menor_preco_unitario_produto": 39.99
}
```

#### `UPDATE /produto/{id}`

Atualiza um produto.

**Exemplo do body da requisição:**
```json
{ 
    "nome_produto": "ProdutoAtualizado",
    "descricao_produto": "Descrição do Produto Atualizada",
    "imagem_url_produto": "https://produtoatualizado.png",
    "id_categoria": 2,
    "marca_produto": "MarcaAtualizada",
    "menor_preco_unitario_produto": 49.99
}
```

#### `DELETE /produto/{id}`

Deleta um produto.

---

---
## Endpoint **Tipo_Variacao**


#### `GET /tipo_variacoes`

Lista todas os tipos de variações.

**Exemplo de retorno:**
```json
[
    {
        "nome_variacao": "Variacao1"
    },
    {
        "nome_variacao": "Variacao2"
    }
]
```


#### `POST /tipo_variacao`

Cadastra um tipo de variação.

**Exemplo do body da requisição:**
```json
{ 
    "nome_variacao": "NovaVariacao"
}
```

#### `UPDATE /tipo_variacao/{id}`

Atualiza um tipo de variação.

**Exemplo do body da requisição:**
```json
{ 
    "nome_variacao": "VariacaoAtualizada"
}
```

#### `DELETE /tipo_variacao/{id}`

Deleta um tipo de variação.

---

## Endpoint **Valor_Variacao**


#### `GET /valor_variacaoes`

Lista todos os valores de variação.

**Exemplo de retorno:**
```json
[
    {
        "nome_valor_variacao": "Opcao1",
        "id_valor_variacao ": 1
    },
    {
        "nome_valor_variacao": "Opcao2",
        "id_valor_variacao ": 2
    }
]
```

#### `POST /valor_variacao`

Cadastra um valor de variação.

**Exemplo do body da requisição:**
```json
{ 
    "nome_valor_variacao": "NovaOpcao",
    "id_valor_variacao ": 1
}
```

#### `UPDATE /valor_variacao/{id}`

Atualiza um valor de variação.

**Exemplo do body da requisição:**
```json
{ 
    "nome_valor_variacao": "OpcaoAtualizada",
    "id_valor_variacao ": 2
}
```

#### `DELETE /valor_variacao/{id}`

Deleta um valor de variação.

---

---

## Endpoint **Usuario**


#### `GET /usuarios`

Lista todos os usuários.

**Exemplo de retorno:**
```json
[
    {
        "id_usuario": 1,
        "cnpj_usuario": "12345678901234",
        "nome_usuario": "João Silva",
        "cep_usuario": "12345-678",
        "logradouro_usuario": "Rua A",
        "complemento_usuario": "Apto 101",
        "numero_endereco_usuario": 123,
        "email_usuario": "joao@example.com",
        "senha_usuario": "*****",
        "telefone_usuario": "(123) 456-7890",
        "e_fornecedor": 0,
        "imagem_url_usuario": "joao.png",
        "regime_tributario_usuario": "Simples Nacional",
        "valor_max_automatico_usuario": 1000.00
    },
    {
        "id_usuario": 2,
        "cnpj_usuario": "11122233344455",
        "nome_usuario": "Carlos Oliveira",
        "cep_usuario": "54321-654",
        "logradouro_usuario": "Rua C, Bloco D",
        "complemento_usuario": "Apto 302",
        "numero_endereco_usuario": 789,
        "email_usuario": "carlos@example.com",
        "senha_usuario": "*****",
        "telefone_usuario": "(555) 123-4567",
        "e_fornecedor": 0,
        "imagem_url_usuario": "carlos.png",
        "regime_tributario_usuario": "Lucro Real",
        "valor_max_automatico_usuario": 2500.00
    }

]
```


#### `POST /usuario`

Cadastra um novo usuário.

**Exemplo do body da requisição:**
```json
{ 
    "cnpj_usuario": "98765432109876",
    "nome_usuario": "Maria Santos",
    "cep_usuario": "54321-876",
    "logradouro_usuario": "Rua B",
    "complemento_usuario": "Casa 102",
    "numero_endereco_usuario": 456,
    "email_usuario": "maria@example.com",
    "senha_usuario": "*****",
    "telefone_usuario": "(987) 654-3210",
    "e_fornecedor": 1,
    "imagem_url_usuario": "maria.png",
    "regime_tributario_usuario": "Lucro Real",
    "valor_max_automatico_usuario": 5000.00
}
```

#### `UPDATE /usuario/{id}`

Atualiza um usuário.

**Exemplo do body da requisição:**
```json
{ 
    "cnpj_usuario": "12345678901234",
    "nome_usuario": "João Silva",
    "cep_usuario": "12345-678",
    "logradouro_usuario": "Rua A, Bloco B",
    "complemento_usuario": "Apto 201",
    "numero_endereco_usuario": 123,
    "email_usuario": "joao@example.com",
    "senha_usuario": "*****",
    "telefone_usuario": "(123) 456-7890",
    "e_fornecedor": 0,
    "imagem_url_usuario": "joao_novo.png",
    "regime_tributario_usuario": "Simples Nacional",
    "valor_max_automatico_usuario": 1500.00
}
```

#### `DELETE /usuario/{id}`

Deleta um usuário.

---

---

## Endpoint **Estoque**


#### `GET /estoques`

Lista todos os estoques.

**Exemplo de retorno:**
```json
[
    {
        "id_produto": 1,
        "id_valor_variacao": 1,
        "quantidade_estoque": 100,
        "preco_unitario": "49.99",
        "imagem_url_estoque": "https://estoque1.png",
        "id_usuario": 1
    },
    {
        "id_produto": 2,
        "id_valor_variacao": 2,
        "quantidade_estoque": 50,
        "preco_unitario": "39.99",
        "imagem_url_estoque": "https://estoque2.png",
        "id_usuario": 2
    }
]
```


#### `POST /estoque`

Cadastra um novo estoque.

**Exemplo do body da requisição:**
```json
{ 
    "id_produto": 3,
    "id_valor_variacao": 3,
    "quantidade_estoque": 75,
    "preco_unitario": "59.99",
    "imagem_url_estoque": "https://estoque3.png",
    "id_usuario": 1
}
```

#### `UPDATE /estoque/{id}`

Atualiza um estoque.

**Exemplo do body da requisição:**
```json
{ 
    "id_produto": 2,
    "id_valor_variacao": 2,
    "quantidade_estoque": 60,
    "preco_unitario": "44.99",
    "imagem_url_estoque": "https://estoque2-atualizado.png",
    "id_usuario": 2
}
```

#### `DELETE /estoque/{id}`

Deleta um estoque.

---
## Endpoint **Desconto**


#### `GET /descontos`

Lista todos os descontos.

**Exemplo de retorno:**
```json
[
    {
        "quantidade_minima_produto": 5,
        "desconto": "10.00",
        "id_estoque": 1
    },
    {
        "quantidade_minima_produto": 10,
        "desconto": "20.00",
        "id_estoque": 2
    }
]
```


#### `POST /desconto`

Cadastra um novo desconto.

**Exemplo do body da requisição:**
```json
{ 
    "quantidade_minima_produto": 8,
    "desconto": "15.00",
    "id_estoque": 1
}
```

#### `UPDATE /desconto/{id}`

Atualiza um desconto.

**Exemplo do body da requisição:**
```json
{ 
    "quantidade_minima_produto": 12,
    "desconto": "25.00",
    "id_estoque": 2
}
```

#### `DELETE /desconto/{id}`

Deleta um desconto.

---

## Endpoint **Tag**


#### `GET /tags`

Lista todas as tags.

**Exemplo de retorno:**
```json
[
    {
        "nome_tag": "Tag1"
    },
    {
        "nome_tag": "Tag2"
    }
]
```


#### `POST /tag`

Cadastra uma nova tag.

**Exemplo do body da requisição:**
```json
{ 
    "nome_tag": "NovaTag"
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

## Endpoint **Pedido**


#### `GET /pedidos`

Lista todos os pedidos.

**Exemplo de retorno:**
```json
[
    {
        "id_usuario": 1,
        "status_pedido": "Em andamento",
        "data_pedido": "2023-10-18",
        "valor_pedido": 100.00
    },
    {
        "id_usuario": 2,
        "status_pedido": "Concluído",
        "data_pedido": "2023-10-17",
        "valor_pedido": 75.50
    }
]
```


#### `POST /pedido`

Cadastra um novo pedido.

**Exemplo do body da requisição:**
```json
{ 
    "id_usuario": 1,
    "status_pedido": "Em andamento",
    "data_pedido": "2023-10-19",
    "valor_pedido": 120.00
}
```

#### `UPDATE /pedido/{id}`

Atualiza um pedido.

**Exemplo do body da requisição:**
```json
{ 
    "id_usuario": 2,
    "status_pedido": "Concluído",
    "data_pedido": "2023-10-18",
    "valor_pedido": 80.50
}
```

#### `DELETE /pedido/{id}`

Deleta um pedido.

---

## Endpoint **Log**


#### `GET /logs`

Lista todos os logs.

**Exemplo de retorno:**
```json
[
    {
        "id_pedido": 1,
        "timestamp_log": "2023-10-18 14:30:00",
        "nome_log": "Log1",
        "descricao_log": "Descrição1"
    },
    {
        "id_pedido": 2,
        "timestamp_log": "2023-10-17 10:15:00",
        "nome_log": "Log2",
        "descricao_log": "Descrição2"
    }
]
```


#### `POST /log`

Cadastra um novo log.

**Exemplo do body da requisição:**
```json
{ 
    "id_pedido": 1,
    "timestamp_log": "2023-10-19 16:45:00",
    "nome_log": "Log3",
    "descricao_log": "Descrição3"
}
```

#### `UPDATE /log/{id}`

Atualiza um log.

**Exemplo do body da requisição:**
```json
{ 
    "id_pedido": 2,
    "timestamp_log": "2023-10-17 10:30:00",
    "nome_log": "Log2Atualizado",
    "descricao_log": "Descrição2Atualizada"
}
```

#### `DELETE /log/{id}`

Deleta um log.

---

## Endpoint **Avaliacao**


#### `GET /avaliacoes`

Lista todas as avaliações.

**Exemplo de retorno:**
```json
[
    {
        "id_usuario": 1,
        "id_pedido": 1,
        "nota_preco_unitario_avaliacao": 4,
        "nota_qualidade_avaliacao": 5,
        "descricao_avaliacao": "Boa compra!",
        "nota_entrega_avaliacao": 5
    },
    {
        "id_usuario": 2,
        "id_pedido": 2,
        "nota_preco_unitario_avaliacao": 3,
        "nota_qualidade_avaliacao": 4,
        "descricao_avaliacao": "Produto OK",
        "nota_entrega_avaliacao": 4
    }
]
```


#### `POST /avaliacao`

Cadastra uma nova avaliação.

**Exemplo do body da requisição:**
```json
{ 
    "id_usuario": 1,
    "id_pedido": 3,
    "nota_preco_unitario_avaliacao": 4,
    "nota_qualidade_avaliacao": 5,
    "descricao_avaliacao": "Excelente serviço!",
    "nota_entrega_avaliacao": 5
}
```

#### `UPDATE /avaliacao/{id}`

Atualiza uma avaliação.

**Exemplo do body da requisição:**
```json
{ 
    "id_usuario": 2,
    "id_pedido": 2,
    "nota_preco_unitario_avaliacao": 4,
    "nota_qualidade_avaliacao": 4,
    "descricao_avaliacao": "Boa qualidade",
    "nota_entrega_avaliacao": 3
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
