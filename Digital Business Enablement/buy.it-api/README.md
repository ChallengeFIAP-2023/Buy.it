# Documentação das Classes

Este documento descreve as classes (tabelas), seus atributos e suas devidas requisições.

## Classe `Usuario`

Representa um usuário do sistema.

**Atributos:**
- `id_usuario` (NUMBER(9)): Identificador único do usuário.
- `email_usuario` (VARCHAR2(255)): Endereço de e-mail do usuário.
- `senha_usuario` (VARCHAR2(255)): Senha do usuário.
- `nome_usuario` (VARCHAR2(255)): Nome da pessoa.
- `imagem_usuario` (VARCHAR2(255)): URL da imagem da pessoa.
- `cnpj_pj` (CHAR(18)): CNPJ da pessoa jurídica.
- `is_fornecedor` (NUMBER(1)): Indicador se a entidade é fornecedora (0 = não, 1 = sim).

## Classe `Usuario_Tag`

Representa a associação entre um usuário e suas tags no sistema.

**Atributos:**
- `id_usuario` (NUMBER(9)): Identificador do usuário.
- `id_tag` (NUMBER(9)): Identificador da tag.

## Classe `Contato`

Representa um tipo de contato no sistema.

**Atributos:**
- `id_contato` (NUMBER(9)): Identificador único do tipo de contato.
- `tipo_contato` (VARCHAR2(255)): Nome do tipo de contato.
- `valor_forma_contato` (VARCHAR2(255)): Valor da forma de contato.
- `id_usuario` (NUMBER(9)): Identificador da usuário associada.


## Classe `Tag`

Representa uma tag no sistema.

**Atributos:**
- `id_tag` (NUMBER(9)): Identificador único da tag.
- `nome_tag` (VARCHAR2(255)): Nome da tag.

## Classe `Departamento`

Representa um departamento dentro da organização.

**Atributos:**
- `id_departamento` (NUMBER(9)): Identificador único do departamento.
- `nome_departamento` (VARCHAR2(255)): Nome do departamento.
- `icone_departamento` (VARCHAR2(255)): URL do ícone do departamento.

## Classe `Produto`

Representa um produto no sistema.

**Atributos:**
- `id_produto` (NUMBER(9)): Identificador único do produto.
- `id_departamento` (NUMBER(9)): Identificador do departamento associado.
- `nome_produto` (VARCHAR2(255)): Nome do produto.
- `marca_produto` (VARCHAR2(255)): Marca do produto.
- `cor_produto` (VARCHAR2(255)): Cor do produto.
- `tamanho_produto` (VARCHAR2(255)): Tamanho do produto.
- `material_produto` (VARCHAR2(255)): Material do produto.
- `observacao_produto` (VARCHAR2(255)): Observações adicionais sobre o produto.

## Classe `Status`

Representa o status de um processo ou atividade no sistema.

**Atributos:**
- `id_status` (NUMBER(9)): Identificador único do status.
- `nome_status` (VARCHAR2(255)): Nome do status.

## Classe `Cotacao`

Representa uma cotação para compra ou venda de produtos no sistema.

**Atributos:**
- `id_cotacao` (NUMBER(9)): Identificador único da cotação.
- `data_abertura_cotacao` (DATE): Data de abertura da cotação.
- `id_comprador` (NUMBER(9)): Identificador do usuário que é o comprador.
- `id_produto` (NUMBER(9)): Identificador do produto cotado.
- `quantidade_produto` (NUMBER(8,2)): Quantidade do produto na cotação.
- `valor_produto` (NUMBER(8,2)): Valor proposto na cotação.
- `id_status` (NUMBER(9)): Identificador do status da cotação.
- `prioridade_entrega` (NUMBER(1)): Prioridade de entrega (em escala definida).
- `prioridade_qualidade` (NUMBER(1)): Prioridade de qualidade (em escala definida).
- `prioridade_preco` (NUMBER(1)): Prioridade de preço (em escala definida).
- `prazo_cotacao` (NUMBER(3)): Prazo em dias para a cotação.
- `data_fechamento_cotacao` (DATE): Data de fechamento da cotação.

## Classe `Avaliacao`

Representa uma avaliação feita a uma cotação no sistema.

**Atributos:**
- `id_avaliacao` (NUMBER(9)): Identificador único da avaliação.
- `id_cotacao` (NUMBER(9)): Identificador da cotação avaliada.
- `data_avaliacao` (DATE): Data da realização da avaliação.
- `nota_entrega_avaliacao` (NUMBER(1)): Nota para a entrega na avaliação.
- `nota_qualidade_avaliacao` (NUMBER(1)): Nota para a qualidade na avaliação.
- `nota_preco_avaliacao` (NUMBER(1)): Nota para o preço na avaliação.
- `descricao_avaliacao` (VARCHAR2(255)): Descrição textual da avaliação.

## Classe `Historico`

Representa o histórico de cotações no sistema.

**Atributos:**
- `id_historico` (NUMBER(9)): Identificador único do histórico.
- `id_cotacao` (NUMBER(9)): Identificador da cotação associada.
- `data_historico` (DATE): Data do registro no histórico.
- `id_status` (NUMBER(9)): Identificador do status da cotação no histórico.
- `id_fornecedor` (NUMBER(9)): Identificador do fornecedor envolvido.
- `valor_ofertado_historico` (NUMBER(8,2)): Valor ofertado no histórico.
- `recusado_por_produto` (NUMBER(1)): Indicação se a recusa foi por questões do produto.
- `recusado_por_quantidade` (NUMBER(1)): Indicação se a recusa foi por questões de quantidade.
- `recusado_por_preco` (NUMBER(1)): Indicação se a recusa foi por questões de preço.
- `recusado_por_prazo` (NUMBER(1)): Indicação se a recusa foi por questões de prazo.
- `descricao_historico` (VARCHAR2(255)): Descrição do registro no histórico.

---

### Exemplos de Requisições HTTP

Aqui estão exemplos de como interagir com a API usando os métodos HTTP (GET, POST, PUT, DELETE):

Observação: Todos os Métodos estão protegidos pelo Spring Security e são necessários o envio do TOKEN com o prefixo Bearer através do Header da requisição.

Os únicos endpoints liberados são o de cadastrar usuário e efetuar login (o qual retorna o token necessário para acesso aos outros endpoints)

---
## Endpoint **Contato**


#### `GET /contatos`

Lista todos os contatos.

**Exemplo de retorno:**
```json
[
    {
        "tipo": "Email",
        "valor": "exemplo@email.com",
        "idUsuario": 1
    },
    {
        "tipo": "Telefone",
        "valor": "11987361034",
        "idUsuario": 2
    }
]
```

#### `GET /contatos/{id}`

Exibe o contato de acordo com seu ID.

**Exemplo de retorno:**
```json
[
    {
        "tipo": "Email",
        "valor": "exemplo@email.com",
        "idUsuario": 1
    }
]
```

#### `POST /contatos`

Cadastra um contato.

**Exemplo do body da requisição:**
```json
{
    "tipo": "Email",
    "valor": "exemplo@email.com",
    "idUsuario": 1
}

```

#### `UPDATE /contatos/{id}`

Atualiza um contato.

**Exemplo do body da requisição:**
```json
{
    "tipo": "Telefone",
    "valor": "123456789",
    "idUsuario": 1
}

```

#### `DELETE /contatos/{id}`

Deleta um contato.

---

## Endpoint **Usuario**


#### `GET /usuarios`

Lista todos os usuarios.

**Exemplo de retorno:**
```json
[
    {
			"id": 1,
			"email": "novopemail@email.com",
			"nome": "Novo Nome do Usuário",
			"urlImagem": "https://novosite.com/imagem.jpg",
			"cnpj": "98765432109876",
			"isFornecedor": false,
			"tags": []
    },
    {
			"id": 2,
			"email": "novopemail2@email.com",
			"nome": "Novo Nome do Usuário2",
			"urlImagem": "https://novosite2.com/imagem.jpg",
			"cnpj": "98765432109877",
			"isFornecedor": true,
			"tags": []
    }
]
```

#### `GET /usuarios/{id}`

Exibe o usuário de acordo com seu ID.

**Exemplo de retorno:**
```json
[
    {
			"id": 1,
			"email": "novopemail@email.com",
			"nome": "Novo Nome do Usuário",
			"urlImagem": "https://novosite.com/imagem.jpg",
			"cnpj": "98765432109876",
			"isFornecedor": false,
			"tags": []
    }
]
```

#### `POST /usuarios`

Cadastra um usuario.

**Exemplo do body da requisição:**
```json
{
    "email": "exemplo@email.com",
    "senha": "senha123",
    "nome": "Nome do Usuário",
    "urlImagem": "https://exemplo.com/imagem.jpg",
    "cnpj": "12345678901234",
    "isFornecedor": true,
    "idsTags": []
}
```

#### `UPDATE /usuarios/{id}`

Atualiza um usuario.

**Exemplo do body da requisição:**
```json
{
    "email": "novopemail@email.com",
    "senha": "novasenha456",
    "nome": "Novo Nome do Usuário",
    "urlImagem": "https://novosite.com/imagem.jpg",
    "cnpj": "98765432109876",
    "isFornecedor": false,
    "idsTags": []
}
```

#### `DELETE /usuarios/{id}`

Deleta um usuario.

---

#### `POST /usuarios/login`

Atualiza um usuario.

**Exemplo do body da requisição:**
```json
{
	"email": "exemplo@email.com",
    "senha": "senha123"
}
```

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

#### `GET /tags/{id}`

Exibe a tag de acordo com seu ID.

**Exemplo de retorno:**
```json
[
    {
        "id_tag": 1,
        "nome_tag": "Iluminação "
    }
]

```


#### `POST /tags`

Cadastra uma nova tag.

**Exemplo do body da requisição:**
```json
{
  "nome": "Cadastrando Nova Tag"
}
```

#### `UPDATE /tags/{id}`

Atualiza uma tag.

**Exemplo do body da requisição:**
```json
{
  "nome": "Editando Tag Cadastrada"
}
```

#### `DELETE /tags/{id}`

Deleta uma tag.

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

#### `GET /departamentos/{id}`

Exibe o departamento de acordo com seu ID.

**Exemplo de retorno:**
```json
[
    {
        "id_departamento": 1,
        "nome_departamento": "Periféricos",
        "icone_departamento": "https://icone-departamento-periféricos.png"
    }
]
```

#### `POST /departamentos`

Cadastra um departamento.

**Exemplo do body da requisição:**
```json
{
    "nome": "Cadastrando Novo Departamento",
	"icone": "novo_icone.png"
}
```

#### `UPDATE /departamentos/{id}`

Atualiza um departamento.

**Exemplo do body da requisição:**
```json
{
    "nome": "Editando Departamento",
	"icone": "icone_editado.png",
	"idsTags": [1]
}
```

#### `DELETE /departamentos/{id}`

Deleta um departamento.

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
        "idDepartamento": 1,
        "idsTags": []
    },
    {
        "id_produto": 2,
        "nome_produto": "Produto 2",
        "marca_produto": "Marca 2",
        "cor_produto": "Azul",
        "tamanho_produto": "Grande",
        "material_produto": "Ferro",
        "observação_produto": "Perfeito para casas grandes",
        "idDepartamento": 2,
        "idsTags": []
    }
]
```

#### `GET /produtos/{id}`

Exibe o produto de acordo com seu ID.

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
        "idDepartamento": 1,
        "idsTags": []
    }
]
```

#### `POST /produtos`

Cadastra um produto.

**Exemplo do body da requisição:**
```json
{
    "nome": "Nome do Produto",
    "marca": "Marca X",
    "cor": "Azul",
    "tamanho": "M",
    "material": "Algodão",
    "observacao": "Observações sobre o produto",
    "idDepartamento": 1,
    "idsTags": []
}
```

#### `UPDATE /produtos/{id}`

Atualiza um produto.

**Exemplo do body da requisição:**
```json
{
    "nome": "Novo Nome do Produto",
    "marca": "Nova Marca",
    "cor": "Vermelho",
    "tamanho": "G",
    "material": "Linho",
    "observacao": "Novas observações sobre o produto",
    "idDepartamento": 2,
    "idsTags": []
}
```

#### `DELETE /produtos/{id}`

Deleta um produto.

---
## Endpoint **Cotacao**


#### `GET /cotacoes`

Lista todos as cotacoes.

**Exemplo de retorno:**
```json
[
    {
    	"id": 1,
    	"dataAbertura": "2023-12-01T09:00:00.000+00:00",
    	"comprador": {
    		"id": 1,
    		"email": "exemplo@email.com",
    		"nome": "Nome do Usuário",
    		"urlImagem": "https://exemplo.com/imagem.jpg",
    		"cnpj": "12345678901234",
    		"isFornecedor": true,
    		"tags": []
    	},
    	"produto": {
    		"id": 1,
    		"nome": "Nome do Produto",
    		"marca": "Marca X",
    		"cor": "Azul",
    		"tamanho": "M",
    		"material": "Algodão",
    		"observacao": "Observações sobre o produto",
    		"departamento": {
    			"id": 1,
    			"nome": "Cadastrando Novo Departamento",
    			"icone": "novo_icone.png",
    			"tags": []
    		},
    		"tags": []
    	},
    	"quantidadeProduto": 100,
    	"valorProduto": 150.50,
    	"status": {
    		"id": 1,
    		"nome": "Cadastrando Novo Status"
    	},
    	"prioridadeEntrega": 2,
    	"prioridadeQualidade": 1,
    	"prioridadePreco": 3,
    	"prazo": 7,
    	"dataFechamento": null
    },...
]
```

#### `GET /cotacoes/{id}`

Exibe a cotação de acordo com seu ID.

**Exemplo de retorno:**
```json
[
    {
    	"id": 1,
    	"dataAbertura": "2023-12-01T09:00:00.000+00:00",
    	"comprador": {
    		"id": 1,
    		"email": "exemplo@email.com",
    		"nome": "Nome do Usuário",
    		"urlImagem": "https://exemplo.com/imagem.jpg",
    		"cnpj": "12345678901234",
    		"isFornecedor": true,
    		"tags": []
    	},
    	"produto": {
    		"id": 1,
    		"nome": "Nome do Produto",
    		"marca": "Marca X",
    		"cor": "Azul",
    		"tamanho": "M",
    		"material": "Algodão",
    		"observacao": "Observações sobre o produto",
    		"departamento": {
    			"id": 1,
    			"nome": "Cadastrando Novo Departamento",
    			"icone": "novo_icone.png",
    			"tags": []
    		},
    		"tags": []
    	},
    	"quantidadeProduto": 100,
    	"valorProduto": 150.50,
    	"status": {
    		"id": 1,
    		"nome": "Cadastrando Novo Status"
    	},
    	"prioridadeEntrega": 2,
    	"prioridadeQualidade": 1,
    	"prioridadePreco": 3,
    	"prazo": 7,
    	"dataFechamento": null
    }
]
```

#### `GET /cotacoes/usuario{id}`

Lista todas as cotações de acordo com um ID de usuário.

**Exemplo de retorno:**
```json
[
    {
    	"id": 1,
    	"dataAbertura": "2023-12-01T09:00:00.000+00:00",
    	"comprador": {
    		"id": 1,
    		"email": "exemplo@email.com",
    		"nome": "Nome do Usuário",
    		"urlImagem": "https://exemplo.com/imagem.jpg",
    		"cnpj": "12345678901234",
    		"isFornecedor": true,
    		"tags": []
    	},
    	"produto": {
    		"id": 1,
    		"nome": "Nome do Produto",
    		"marca": "Marca X",
    		"cor": "Azul",
    		"tamanho": "M",
    		"material": "Algodão",
    		"observacao": "Observações sobre o produto",
    		"departamento": {
    			"id": 1,
    			"nome": "Cadastrando Novo Departamento",
    			"icone": "novo_icone.png",
    			"tags": []
    		},
    		"tags": []
    	},
    	"quantidadeProduto": 100,
    	"valorProduto": 150.50,
    	"status": {
    		"id": 1,
    		"nome": "Cadastrando Novo Status"
    	},
    	"prioridadeEntrega": 2,
    	"prioridadeQualidade": 1,
    	"prioridadePreco": 3,
    	"prazo": 7,
    	"dataFechamento": null
    },...
]
```

#### `GET /cotacoes/info/{id}`

Exibe informações de valores mínimo, média e máximo para todas as cotações que contém o produto com o ID especificado.

**Exemplo de retorno:**
```json
[
    {
	"minValor": 69.90,
	"avgValor": 128.00,
	"maxValor": 500
    }
]
```

#### `POST /cotacoes`

Cadastra uma cotação.

**Exemplo do body da requisição:**
```json
{
    "dataAbertura": "2023-12-01T09:00:00Z",
    "idComprador": 1,
    "idProduto": 1,
    "quantidadeProduto": 100,
    "valorProduto": 150.50,
    "idStatus": 1,
    "prioridadeEntrega": 2,
    "prioridadeQualidade": 1,
    "prioridadePreco": 3,
    "prazo": 7
}

```

#### `UPDATE /cotacoes/{id}`

Atualiza uma cotação.

**Exemplo do body da requisição:**
```json
{
    "dataAbertura": "2023-12-01T10:30:00Z",
    "idComprador": 1,
    "idProduto": 1,
    "quantidadeProduto": 200,
    "valorProduto": 180.25,
    "idStatus": 1,
    "prioridadeEntrega": 3,
    "prioridadeQualidade": 2,
    "prioridadePreco": 1,
    "prazo": 5,
    "dataFechamento": "2023-12-01T15:45:00Z"
}
```

#### `DELETE /cotacoes/{id}`

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
			"id": 1,
			"cotacao": {
				"id": 1,
				"dataAbertura": "2023-12-01",
				"comprador": {
					"id": 1,
					"email": "novopemail@email.com",
					"nome": "Novo Nome do Usuário",
					"urlImagem": "https://novosite.com/imagem.jpg",
					"cnpj": "98765432109876",
					"isFornecedor": false,
					"tags": []
				},
				"produto": {
					"id": 1,
					"nome": "Nome do Produto",
					"marca": "Marca X",
					"cor": "Azul",
					"tamanho": "M",
					"material": "Algodão",
					"observacao": "Observações sobre o produto",
					"departamento": {
						"id": 1,
						"nome": "Cadastrando Novo Departamento",
						"icone": "novo_icone.png",
						"tags": []
					},
					"tags": []
				},
				"quantidadeProduto": 100,
				"valorProduto": 150.5,
				"status": {
					"id": 1,
					"nome": "Editando Status"
				},
				"prioridadeEntrega": 2,
				"prioridadeQualidade": 1,
				"prioridadePreco": 3,
				"prazo": 7,
				"dataFechamento": null
			},
			"fornecedor": {
				"id": 1,
				"email": "novopemail@email.com",
				"nome": "Novo Nome do Usuário",
				"urlImagem": "https://novosite.com/imagem.jpg",
				"cnpj": "98765432109876",
				"isFornecedor": false,
				"tags": []
			},
			"status": {
				"id": 1,
				"nome": "Editando Status"
			},
			"recusadoPorProduto": false,
			"recusadoPorQuantidade": true,
			"recusadoPorPreco": false,
			"recusadoPorPrazo": false,
			"descricao": "Descrição do histórico",
			"data": "2023-12-01T10:00:00.000+00:00",
			"valorOfertado": 150.75
		}
]

```


#### `POST /historicos`

Cadastra um registro histórico de cotação.

**Exemplo do body da requisição:**
```json
{
    "idCotacao": 1,
    "idFornecedor": 1,
    "idStatus": 1,
    "recusadoPorProduto": false,
    "recusadoPorQuantidade": true,
    "recusadoPorPreco": false,
    "recusadoPorPrazo": false,
    "descricao": "Descrição do histórico",
    "data": "2023-12-01T10:00:00Z",
    "valorOfertado": 150.75
}

```

#### `UPDATE /historicos/{id}`

Atualiza um registro histórico de cotação.

**Exemplo do body da requisição:**
```json
{
    "idCotacao": 1,
    "idFornecedor": 1,
    "idStatus": 1,
    "recusadoPorProduto": false,
    "recusadoPorQuantidade": false,
    "recusadoPorPreco": true,
    "recusadoPorPrazo": false,
    "descricao": "Nova descrição do histórico",
    "data": "2023-12-02T08:30:00Z",
    "valorOfertado": 180.50
}
```

#### `DELETE /historicos/{id}`

Deleta um registro histórico.

---

## Endpoint **Avaliacao**


#### `GET /avaliacoes`

Lista todas as avaliações.

**Exemplo de retorno:**
```json
[
    {
			"id": 1,
			"cotacao": {
				"id": 1,
				"dataAbertura": "2023-12-01",
				"comprador": {
					"id": 1,
					"email": "novopemail@email.com",
					"nome": "Novo Nome do Usuário",
					"urlImagem": "https://novosite.com/imagem.jpg",
					"cnpj": "98765432109876",
					"isFornecedor": false,
					"tags": []
				},
				"produto": {
					"id": 1,
					"nome": "Nome do Produto",
					"marca": "Marca X",
					"cor": "Azul",
					"tamanho": "M",
					"material": "Algodão",
					"observacao": "Observações sobre o produto",
					"departamento": {
						"id": 1,
						"nome": "Editando Departamento",
						"icone": "icone_editado.png",
						"tags": []
					},
					"tags": []
				},
				"quantidadeProduto": 100,
				"valorProduto": 150.5,
				"status": {
					"id": 1,
					"nome": "Editando Status"
				},
				"prioridadeEntrega": 2,
				"prioridadeQualidade": 1,
				"prioridadePreco": 3,
				"prazo": 7,
				"dataFechamento": null
			},
			"data": "2023-12-02",
			"notaEntrega": 1,
			"notaQualidade": 4,
			"notaPreco": 2,
			"descricao": "Nova avaliação sobre a cotação"
    }
]

```


#### `POST /avaliacoes`

Cadastra uma avaliação.

**Exemplo do body da requisição:**
```json
{
    "idCotacao": 1,
    "data": "2023-12-01T09:00:00Z",
    "notaEntrega": 4,
    "notaQualidade": 5,
    "notaPreco": 3,
    "descricao": "Avaliação sobre a cotação"
}

```

#### `UPDATE /avaliacoes/{id}`

Atualiza uma avaliação.

**Exemplo do body da requisição:**
```json
{
    "idCotacao": 1,
    "data": "2023-12-02T10:30:00Z",
    "notaEntrega": 1,
    "notaQualidade": 4,
    "notaPreco": 2,
    "descricao": "Nova avaliação sobre a cotação"
}
```

#### `DELETE /avaliacoes/{id}`

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