using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace Buyit.Migrations
{
    public partial class FirstOracleDatabase : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Departamento",
                columns: table => new
                {
                    Id = table.Column<long>(type: "NUMBER(19)", nullable: false)
                        .Annotation("Oracle:Identity", "START WITH 1 INCREMENT BY 1"),
                    Nome = table.Column<string>(type: "NVARCHAR2(2000)", nullable: false),
                    Icone = table.Column<string>(type: "NVARCHAR2(2000)", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Departamento", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Status",
                columns: table => new
                {
                    Id = table.Column<long>(type: "NUMBER(19)", nullable: false)
                        .Annotation("Oracle:Identity", "START WITH 1 INCREMENT BY 1"),
                    Nome = table.Column<string>(type: "NVARCHAR2(2000)", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Status", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Tag",
                columns: table => new
                {
                    Id = table.Column<long>(type: "NUMBER(19)", nullable: false)
                        .Annotation("Oracle:Identity", "START WITH 1 INCREMENT BY 1"),
                    Nome = table.Column<string>(type: "NVARCHAR2(2000)", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Tag", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Usuario",
                columns: table => new
                {
                    Id = table.Column<int>(type: "NUMBER(10)", nullable: false)
                        .Annotation("Oracle:Identity", "START WITH 1 INCREMENT BY 1"),
                    Nome = table.Column<string>(type: "NVARCHAR2(2000)", nullable: false),
                    Email = table.Column<string>(type: "NVARCHAR2(2000)", nullable: false),
                    Senha = table.Column<string>(type: "NVARCHAR2(2000)", nullable: false),
                    UrlImagem = table.Column<string>(type: "NVARCHAR2(2000)", nullable: true),
                    Cnpj = table.Column<string>(type: "NVARCHAR2(2000)", nullable: false),
                    IsFornecedor = table.Column<bool>(type: "NUMBER(1)", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Usuario", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Produto",
                columns: table => new
                {
                    Id = table.Column<long>(type: "NUMBER(19)", nullable: false)
                        .Annotation("Oracle:Identity", "START WITH 1 INCREMENT BY 1"),
                    Nome = table.Column<string>(type: "NVARCHAR2(2000)", nullable: false),
                    Marca = table.Column<string>(type: "NVARCHAR2(2000)", nullable: true),
                    Cor = table.Column<string>(type: "NVARCHAR2(2000)", nullable: true),
                    Tamanho = table.Column<string>(type: "NVARCHAR2(2000)", nullable: true),
                    Material = table.Column<string>(type: "NVARCHAR2(2000)", nullable: true),
                    Observacao = table.Column<string>(type: "NVARCHAR2(2000)", nullable: true),
                    DepartamentoId = table.Column<long>(type: "NUMBER(19)", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Produto", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Produto_Departamento_DepartamentoId",
                        column: x => x.DepartamentoId,
                        principalTable: "Departamento",
                        principalColumn: "Id");
                });

            migrationBuilder.CreateTable(
                name: "DepartamentoModelTagModel",
                columns: table => new
                {
                    DepartamentosId = table.Column<long>(type: "NUMBER(19)", nullable: false),
                    TagsId = table.Column<long>(type: "NUMBER(19)", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_DepartamentoModelTagModel", x => new { x.DepartamentosId, x.TagsId });
                    table.ForeignKey(
                        name: "FK_DepartamentoModelTagModel_Departamento_DepartamentosId",
                        column: x => x.DepartamentosId,
                        principalTable: "Departamento",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_DepartamentoModelTagModel_Tag_TagsId",
                        column: x => x.TagsId,
                        principalTable: "Tag",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Contato",
                columns: table => new
                {
                    Id = table.Column<long>(type: "NUMBER(19)", nullable: false)
                        .Annotation("Oracle:Identity", "START WITH 1 INCREMENT BY 1"),
                    Tipo = table.Column<string>(type: "NVARCHAR2(2000)", nullable: false),
                    Valor = table.Column<string>(type: "NVARCHAR2(2000)", nullable: false),
                    UsuarioId = table.Column<long>(type: "NUMBER(19)", nullable: false),
                    UsuarioId1 = table.Column<int>(type: "NUMBER(10)", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Contato", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Contato_Usuario_UsuarioId1",
                        column: x => x.UsuarioId1,
                        principalTable: "Usuario",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "TagModelUsuarioModel",
                columns: table => new
                {
                    TagsId = table.Column<long>(type: "NUMBER(19)", nullable: false),
                    UsuariosId = table.Column<int>(type: "NUMBER(10)", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_TagModelUsuarioModel", x => new { x.TagsId, x.UsuariosId });
                    table.ForeignKey(
                        name: "FK_TagModelUsuarioModel_Tag_TagsId",
                        column: x => x.TagsId,
                        principalTable: "Tag",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_TagModelUsuarioModel_Usuario_UsuariosId",
                        column: x => x.UsuariosId,
                        principalTable: "Usuario",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Cotacao",
                columns: table => new
                {
                    Id = table.Column<long>(type: "NUMBER(19)", nullable: false)
                        .Annotation("Oracle:Identity", "START WITH 1 INCREMENT BY 1"),
                    DataAbertura = table.Column<DateTime>(type: "TIMESTAMP(7)", nullable: false),
                    CompradorId = table.Column<int>(type: "NUMBER(10)", nullable: false),
                    ProdutoId = table.Column<long>(type: "NUMBER(19)", nullable: false),
                    QuantidadeProduto = table.Column<decimal>(type: "DECIMAL(18, 2)", nullable: false),
                    ValorProduto = table.Column<decimal>(type: "DECIMAL(18, 2)", nullable: false),
                    StatusId = table.Column<long>(type: "NUMBER(19)", nullable: false),
                    PrioridadeEntrega = table.Column<int>(type: "NUMBER(10)", nullable: false),
                    PrioridadeQualidade = table.Column<int>(type: "NUMBER(10)", nullable: false),
                    PrioridadePreco = table.Column<int>(type: "NUMBER(10)", nullable: false),
                    Prazo = table.Column<int>(type: "NUMBER(10)", nullable: false),
                    DataFechamento = table.Column<DateTime>(type: "TIMESTAMP(7)", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Cotacao", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Cotacao_Produto_ProdutoId",
                        column: x => x.ProdutoId,
                        principalTable: "Produto",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Cotacao_Status_StatusId",
                        column: x => x.StatusId,
                        principalTable: "Status",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Cotacao_Usuario_CompradorId",
                        column: x => x.CompradorId,
                        principalTable: "Usuario",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "ProdutoModelTagModel",
                columns: table => new
                {
                    ProdutosId = table.Column<long>(type: "NUMBER(19)", nullable: false),
                    TagsId = table.Column<long>(type: "NUMBER(19)", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_ProdutoModelTagModel", x => new { x.ProdutosId, x.TagsId });
                    table.ForeignKey(
                        name: "FK_ProdutoModelTagModel_Produto_ProdutosId",
                        column: x => x.ProdutosId,
                        principalTable: "Produto",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_ProdutoModelTagModel_Tag_TagsId",
                        column: x => x.TagsId,
                        principalTable: "Tag",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Avaliacao",
                columns: table => new
                {
                    Id = table.Column<long>(type: "NUMBER(19)", nullable: false)
                        .Annotation("Oracle:Identity", "START WITH 1 INCREMENT BY 1"),
                    CotacaoId = table.Column<long>(type: "NUMBER(19)", nullable: false),
                    Data = table.Column<DateTime>(type: "TIMESTAMP(7)", nullable: false),
                    NotaEntrega = table.Column<int>(type: "NUMBER(10)", nullable: false),
                    NotaQualidade = table.Column<int>(type: "NUMBER(10)", nullable: false),
                    NotaPreco = table.Column<int>(type: "NUMBER(10)", nullable: false),
                    Descricao = table.Column<string>(type: "NVARCHAR2(2000)", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Avaliacao", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Avaliacao_Cotacao_CotacaoId",
                        column: x => x.CotacaoId,
                        principalTable: "Cotacao",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Historico",
                columns: table => new
                {
                    Id = table.Column<long>(type: "NUMBER(19)", nullable: false)
                        .Annotation("Oracle:Identity", "START WITH 1 INCREMENT BY 1"),
                    CotacaoId = table.Column<long>(type: "NUMBER(19)", nullable: false),
                    FornecedorId = table.Column<int>(type: "NUMBER(10)", nullable: false),
                    StatusId = table.Column<long>(type: "NUMBER(19)", nullable: false),
                    RecusadoPorProduto = table.Column<bool>(type: "NUMBER(1)", nullable: false),
                    RecusadoPorQuantidade = table.Column<bool>(type: "NUMBER(1)", nullable: false),
                    RecusadoPorPreco = table.Column<bool>(type: "NUMBER(1)", nullable: false),
                    RecusadoPorPrazo = table.Column<bool>(type: "NUMBER(1)", nullable: false),
                    Descricao = table.Column<string>(type: "NVARCHAR2(2000)", nullable: true),
                    Data = table.Column<DateTime>(type: "TIMESTAMP(7)", nullable: false),
                    ValorOfertado = table.Column<decimal>(type: "DECIMAL(18, 2)", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Historico", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Historico_Cotacao_CotacaoId",
                        column: x => x.CotacaoId,
                        principalTable: "Cotacao",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Historico_Status_StatusId",
                        column: x => x.StatusId,
                        principalTable: "Status",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Historico_Usuario_FornecedorId",
                        column: x => x.FornecedorId,
                        principalTable: "Usuario",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_Avaliacao_CotacaoId",
                table: "Avaliacao",
                column: "CotacaoId");

            migrationBuilder.CreateIndex(
                name: "IX_Contato_UsuarioId1",
                table: "Contato",
                column: "UsuarioId1");

            migrationBuilder.CreateIndex(
                name: "IX_Cotacao_CompradorId",
                table: "Cotacao",
                column: "CompradorId");

            migrationBuilder.CreateIndex(
                name: "IX_Cotacao_ProdutoId",
                table: "Cotacao",
                column: "ProdutoId");

            migrationBuilder.CreateIndex(
                name: "IX_Cotacao_StatusId",
                table: "Cotacao",
                column: "StatusId");

            migrationBuilder.CreateIndex(
                name: "IX_DepartamentoModelTagModel_TagsId",
                table: "DepartamentoModelTagModel",
                column: "TagsId");

            migrationBuilder.CreateIndex(
                name: "IX_Historico_CotacaoId",
                table: "Historico",
                column: "CotacaoId");

            migrationBuilder.CreateIndex(
                name: "IX_Historico_FornecedorId",
                table: "Historico",
                column: "FornecedorId");

            migrationBuilder.CreateIndex(
                name: "IX_Historico_StatusId",
                table: "Historico",
                column: "StatusId");

            migrationBuilder.CreateIndex(
                name: "IX_Produto_DepartamentoId",
                table: "Produto",
                column: "DepartamentoId");

            migrationBuilder.CreateIndex(
                name: "IX_ProdutoModelTagModel_TagsId",
                table: "ProdutoModelTagModel",
                column: "TagsId");

            migrationBuilder.CreateIndex(
                name: "IX_TagModelUsuarioModel_UsuariosId",
                table: "TagModelUsuarioModel",
                column: "UsuariosId");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Avaliacao");

            migrationBuilder.DropTable(
                name: "Contato");

            migrationBuilder.DropTable(
                name: "DepartamentoModelTagModel");

            migrationBuilder.DropTable(
                name: "Historico");

            migrationBuilder.DropTable(
                name: "ProdutoModelTagModel");

            migrationBuilder.DropTable(
                name: "TagModelUsuarioModel");

            migrationBuilder.DropTable(
                name: "Cotacao");

            migrationBuilder.DropTable(
                name: "Tag");

            migrationBuilder.DropTable(
                name: "Produto");

            migrationBuilder.DropTable(
                name: "Status");

            migrationBuilder.DropTable(
                name: "Usuario");

            migrationBuilder.DropTable(
                name: "Departamento");
        }
    }
}
