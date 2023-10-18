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
- `menor_preco_produto` (float): Menor preço do produto.

## Classe `Variacao`

Representa uma variação de produto.

**Atributos:**
- `nome_variacao ` (String): Nome da variação.

## Classe `Opcao_Variacao`

Representa uma opção de variação de produto.

**Atributos:**
- `nome_opcao_variacao `  (String): Nome da opção de variação.
- `id_variacao ` (int): ID da variação à qual a opção pertence.

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
- `timagem_url_usuario elefone_usuario  ` (String): URL da imagem do usuário.

## Classe `Estoque`

Representa o estoque de um produto.

**Atributos:**
- `id_produto ` (int): ID do produto no estoque.
- `id_variacao_produto ` (int): ID da variação de produto no estoque.
- `quantidade_estoque ` (int): Quantidade em estoque.
- `preco `  (String): URL da imagem do estoque.
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

## Classe `Possui`

Representa a associação entre tags e usuários.

**Atributos:**
- `id_tag` (int): ID da tag.
- `id_usuario` (int): ID do usuário.

## Classe `Pedido`

Representa um pedido feito por um usuário.

**Atributos:**
- `id_usuario` (int): ID do usuário que fez o pedido.
- `status_pedido` (String): Status do pedido.
- `data_pedido` (Date): Data do pedido.
- `valor_pedido` (float): Valor total do pedido.

## Classe `Contem`

Representa a relação entre pedidos e produtos.

**Atributos:**
- `id_pedido` (int): ID do pedido.

## Classe `Item_Pedido`

Representa um item dentro de um pedido.

**Atributos:**
- `id_pedido` (int): ID do pedido ao qual o item pertence.
- `id_variacao` (int): ID da variação do produto no item.
- `quantidade_item_pedido` (int): Quantidade do item no pedido.
- `valor` (float): Valor do item.

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
- `nota_preco_avaliacao` (int): Nota de preço na avaliação.
- `nota_qualidade_avaliacao` (int): Nota de qualidade na avaliação.
- `descricao_avaliacao` (String): Descrição da avaliação.
- `nota_entrega_avaliacao` (int): Nota de entrega na avaliação.

## Classe `Gera`

Representa a relação entre pedidos e avaliações.

**Atributos:**
- `id_pedido` (int): ID do pedido.
- `id_avaliacao` (int): ID da avaliação.




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
        "menor_preco_produto": 19.99
    },
    {
        "nome_produto": "Produto2",
        "descricao_produto": "Descrição do Produto 2",
        "imagem_url_produto": "https://imagem2.png",
        "id_categoria": 2,
        "menor_preco_produto": 29.99
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
    "menor_preco_produto": 39.99
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
    "menor_preco_produto": 49.99
}
```

#### `DELETE /produto/{id}`

Deleta um produto.

---

---
## Endpoint **Variação**


#### `GET /variacoes`

Lista todas as variações.

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


#### `POST /variacao`

Cadastra uma variação.

**Exemplo do body da requisição:**
```json
{ 
    "nome_variacao": "NovaVariacao"
}
```

#### `UPDATE /variacao/{id}`

Atualiza uma variação.

**Exemplo do body da requisição:**
```json
{ 
    "nome_variacao": "VariacaoAtualizada"
}
```

#### `DELETE /variacao/{id}`

Deleta uma variação.

---

## Endpoint **Opção_Variação**


#### `GET /opcoes_variacao`

Lista todos as opções de variação.

**Exemplo de retorno:**
```json
[
    {
        "nome_opcao_variacao": "Opcao1",
        "id_variacao": 1
    },
    {
        "nome_opcao_variacao": "Opcao2",
        "id_variacao": 2
    }
]
```

#### `POST /opcao_variacao`

Cadastra uma opção de variação.

**Exemplo do body da requisição:**
```json
{ 
    "nome_opcao_variacao": "NovaOpcao",
    "id_variacao": 1
}
```

#### `UPDATE /opcao_variacao/{id}`

Atualiza uma opção de variação.

**Exemplo do body da requisição:**
```json
{ 
    "nome_opcao_variacao": "OpcaoAtualizada",
    "id_variacao": 2
}
```

#### `DELETE /opcao_variacao/{id}`

Deleta uma opção de variação.

---

---

## Endpoint **Usuario**


#### `GET /usuarios`

Lista todos os usuários.

**Exemplo de retorno:**
```json
[
    {
        "cnpj_usuario": "1234567890",
        "nome_usuario": "Usuario1",
        "cep_usuario": "12345-678",
        "logradouro_usuario": "Rua A",
        "complemento_usuario": "Apto 101",
        "numero_endereco_usuario": 123,
        "email_usuario": "usuario1@email.com",
        "telefone_usuario": "123-456-7890",
        "e_fornecedor": 1,
        "senha_usuario": "senha123",
        "imagem_url_usuario": "https://usuario1.png"
    },
    {
        "cnpj_usuario": "0987654321",
        "nome_usuario": "Usuario2",
        "cep_usuario": "98765-432",
        "logradouro_usuario": "Rua B",
        "complemento_usuario": "Casa",
        "numero_endereco_usuario": 456,
        "email_usuario": "usuario2@email.com",
        "telefone_usuario": "987-654-3210",
        "e_fornecedor": 0,
        "senha_usuario": "senha456",
        "imagem_url_usuario": "https://usuario2.png"
    }
]
```


#### `POST /usuario`

Cadastra um novo usuário.

**Exemplo do body da requisição:**
```json
{ 
    "cnpj_usuario": "1357924680",
    "nome_usuario": "NovoUsuario",
    "cep_usuario": "54321-987",
    "logradouro_usuario": "Rua C",
    "complemento_usuario": "Loja 3",
    "numero_endereco_usuario": 789,
    "email_usuario": "novousuario@email.com",
    "telefone_usuario": "987-654-3210",
    "e_fornecedor": 1,
    "senha_usuario": "novasenha",
    "imagem_url_usuario": "https://novousuario.png"
}
```

#### `UPDATE /usuario/{id}`

Atualiza um usuário.

**Exemplo do body da requisição:**
```json
{ 
    "cnpj_usuario": "2468013579",
    "nome_usuario": "UsuarioAtualizado",
    "cep_usuario": "98765-4321",
    "logradouro_usuario": "Rua D",
    "complemento_usuario": "Sala 5",
    "numero_endereco_usuario": 654,
    "email_usuario": "usuarioatualizado@email.com",
    "telefone_usuario": "123-987-6543",
    "e_fornecedor": 0,
    "senha_usuario": "senhaatualizada",
    "imagem_url_usuario": "https://usuarioatualizado.png"
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
        "id_variacao_produto": 1,
        "quantidade_estoque": 100,
        "preco": "49.99",
        "imagem_url_estoque": "https://estoque1.png",
        "id_usuario": 1
    },
    {
        "id_produto": 2,
        "id_variacao_produto": 2,
        "quantidade_estoque": 50,
        "preco": "39.99",
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
    "id_variacao_produto": 3,
    "quantidade_estoque": 75,
    "preco": "59.99",
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
    "id_variacao_produto": 2,
    "quantidade_estoque": 60,
    "preco": "44.99",
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

## Endpoint **Possui**
(ESSE EU ACHO QUE SAÍ)

#### `GET /possuem`

Lista todos os possuem.

**Exemplo de retorno:**
```json
[
    {
        "id_tag": 1,
        "id_usuario": 1
    },
    {
        "id_tag": 2,
        "id_usuario": 2
    }
]
```


#### `POST /possui`

Cadastra um novo possui.

**Exemplo do body da requisição:**
```json
{ 
    "id_tag": 3,
    "id_usuario": 1
}
```

#### `UPDATE /possui/{id}`

Atualiza um possui.

**Exemplo do body da requisição:**
```json
{ 
    "id_tag": 2,
    "id_usuario": 2
}
```

#### `DELETE /possui/{id}`

Deleta um possui.

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

## Endpoint **Contem**
(ESSE EU ACHO QUE SAÍ)


#### `GET /contem`

Lista todos os contem.

**Exemplo de retorno:**
```json
[
    {
        "id_pedido": 1
    },
    {
        "id_pedido": 2
    }
]
```


#### `POST /contem`

Cadastra um novo contem.

**Exemplo do body da requisição:**
```json
{ 
    "id_pedido": 3
}
```

#### `UPDATE /contem/{id}`

Atualiza um contem.

**Exemplo do body da requisição:**
```json
{ 
    "id_pedido": 2
}
```

#### `DELETE /contem/{id}`

Deleta um contem.

---

## Endpoint **Item_Pedido**


#### `GET /itens_pedido`

Lista todos os itens do pedido.

**Exemplo de retorno:**
```json
[
    {
        "id_pedido": 1,
        "id_variacao": 1,
        "quantidade_item_pedido": 5,
        "valor": 50.00
    },
    {
        "id_pedido": 2,
        "id_variacao": 2,
        "quantidade_item_pedido": 3,
        "valor": 30.00
    }
]
```


#### `POST /item_pedido`

Cadastra um novo item do pedido.

**Exemplo do body da requisição:**
```json
{ 
    "id_pedido": 1,
    "id_variacao": 3,
    "quantidade_item_pedido": 4,
    "valor": 40.00
}
```

#### `UPDATE /item_pedido/{id}`

Atualiza um item do pedido.

**Exemplo do body da requisição:**
```json
{ 
    "id_pedido": 2,
    "id_variacao": 2,
    "quantidade_item_pedido": 4,
    "valor": 35.00
}
```

#### `DELETE /item_pedido/{id}`

Deleta um item do pedido.

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
        "nota_preco_avaliacao": 4,
        "nota_qualidade_avaliacao": 5,
        "descricao_avaliacao": "Boa compra!",
        "nota_entrega_avaliacao": 5
    },
    {
        "id_usuario": 2,
        "id_pedido": 2,
        "nota_preco_avaliacao": 3,
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
    "nota_preco_avaliacao": 4,
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
    "nota_preco_avaliacao": 4,
    "nota_qualidade_avaliacao": 4,
    "descricao_avaliacao": "Boa qualidade",
    "nota_entrega_avaliacao": 3
}
```

#### `DELETE /avaliacao/{id}`

Deleta uma avaliação.

---

## Endpoint **Gera**
(ESSE EU ACHO QUE SAÍ)


#### `GET /gera`

Lista todos os gera.

**Exemplo de retorno:**
```json
[
    {
        "id_pedido": 1,
        "id_avaliacao": 1
    },
    {
        "id_pedido": 2,
        "id_avaliacao": 2
    }
]
```


#### `POST /gera`

Cadastra um novo gera.

**Exemplo do body da requisição:**
```json
{ 
    "id_pedido": 3,
    "id_avaliacao": 3
}
```

#### `UPDATE /gera/{id}`

Atualiza um gera.

**Exemplo do body da requisição:**
```json
{ 
    "id_pedido": 2,
    "id_avaliacao": 2
}
```

#### `DELETE /gera/{id}`

Deleta um gera.

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