using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace Buyit.Migrations
{
    public partial class FirstDataBase : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Avaliacao_Cotacao_CotacaoId",
                table: "Avaliacao");

            migrationBuilder.DropForeignKey(
                name: "FK_Contato_Usuario_UsuarioId1",
                table: "Contato");

            migrationBuilder.DropForeignKey(
                name: "FK_Cotacao_Produto_ProdutoId",
                table: "Cotacao");

            migrationBuilder.DropForeignKey(
                name: "FK_Cotacao_Status_StatusId",
                table: "Cotacao");

            migrationBuilder.DropForeignKey(
                name: "FK_Cotacao_Usuario_CompradorId",
                table: "Cotacao");

            migrationBuilder.DropForeignKey(
                name: "FK_DepartamentoModelTagModel_Departamento_DepartamentosId",
                table: "DepartamentoModelTagModel");

            migrationBuilder.DropForeignKey(
                name: "FK_DepartamentoModelTagModel_Tag_TagsId",
                table: "DepartamentoModelTagModel");

            migrationBuilder.DropForeignKey(
                name: "FK_Historico_Cotacao_CotacaoId",
                table: "Historico");

            migrationBuilder.DropForeignKey(
                name: "FK_Historico_Status_StatusId",
                table: "Historico");

            migrationBuilder.DropForeignKey(
                name: "FK_Historico_Usuario_FornecedorId",
                table: "Historico");

            migrationBuilder.DropForeignKey(
                name: "FK_Produto_Departamento_DepartamentoId",
                table: "Produto");

            migrationBuilder.DropForeignKey(
                name: "FK_ProdutoModelTagModel_Produto_ProdutosId",
                table: "ProdutoModelTagModel");

            migrationBuilder.DropForeignKey(
                name: "FK_ProdutoModelTagModel_Tag_TagsId",
                table: "ProdutoModelTagModel");

            migrationBuilder.DropForeignKey(
                name: "FK_TagModelUsuarioModel_Tag_TagsId",
                table: "TagModelUsuarioModel");

            migrationBuilder.DropForeignKey(
                name: "FK_TagModelUsuarioModel_Usuario_UsuariosId",
                table: "TagModelUsuarioModel");

            migrationBuilder.DropPrimaryKey(
                name: "PK_Usuario",
                table: "Usuario");

            migrationBuilder.DropPrimaryKey(
                name: "PK_Tag",
                table: "Tag");

            migrationBuilder.DropPrimaryKey(
                name: "PK_Status",
                table: "Status");

            migrationBuilder.DropPrimaryKey(
                name: "PK_Produto",
                table: "Produto");

            migrationBuilder.DropIndex(
                name: "IX_Produto_DepartamentoId",
                table: "Produto");

            migrationBuilder.DropPrimaryKey(
                name: "PK_Historico",
                table: "Historico");

            migrationBuilder.DropIndex(
                name: "IX_Historico_FornecedorId",
                table: "Historico");

            migrationBuilder.DropPrimaryKey(
                name: "PK_Departamento",
                table: "Departamento");

            migrationBuilder.DropPrimaryKey(
                name: "PK_Cotacao",
                table: "Cotacao");

            migrationBuilder.DropIndex(
                name: "IX_Cotacao_CompradorId",
                table: "Cotacao");

            migrationBuilder.DropPrimaryKey(
                name: "PK_Contato",
                table: "Contato");

            migrationBuilder.DropIndex(
                name: "IX_Contato_UsuarioId1",
                table: "Contato");

            migrationBuilder.DropPrimaryKey(
                name: "PK_Avaliacao",
                table: "Avaliacao");

            migrationBuilder.DropColumn(
                name: "DepartamentoId",
                table: "Produto");

            migrationBuilder.DropColumn(
                name: "FornecedorId",
                table: "Historico");

            migrationBuilder.DropColumn(
                name: "CompradorId",
                table: "Cotacao");

            migrationBuilder.DropColumn(
                name: "UsuarioId1",
                table: "Contato");

            migrationBuilder.RenameTable(
                name: "Usuario",
                newName: "USUARIO");

            migrationBuilder.RenameTable(
                name: "Tag",
                newName: "TAG");

            migrationBuilder.RenameTable(
                name: "Status",
                newName: "STATUS");

            migrationBuilder.RenameTable(
                name: "Produto",
                newName: "PRODUTO");

            migrationBuilder.RenameTable(
                name: "Historico",
                newName: "HISTORICO");

            migrationBuilder.RenameTable(
                name: "Departamento",
                newName: "DEPARTAMENTO");

            migrationBuilder.RenameTable(
                name: "Cotacao",
                newName: "COTACAO");

            migrationBuilder.RenameTable(
                name: "Contato",
                newName: "CONTATO");

            migrationBuilder.RenameTable(
                name: "Avaliacao",
                newName: "AVALIACAO");

            migrationBuilder.RenameColumn(
                name: "UrlImagem",
                table: "USUARIO",
                newName: "IMAGEM_USUARIO");

            migrationBuilder.RenameColumn(
                name: "Senha",
                table: "USUARIO",
                newName: "SENHA_USUARIO");

            migrationBuilder.RenameColumn(
                name: "Nome",
                table: "USUARIO",
                newName: "NOME_USUARIO");

            migrationBuilder.RenameColumn(
                name: "IsFornecedor",
                table: "USUARIO",
                newName: "IS_FORNECEDOR");

            migrationBuilder.RenameColumn(
                name: "Email",
                table: "USUARIO",
                newName: "EMAIL_USUARIO");

            migrationBuilder.RenameColumn(
                name: "Cnpj",
                table: "USUARIO",
                newName: "CNPJ_USUARIO");

            migrationBuilder.RenameColumn(
                name: "Id",
                table: "USUARIO",
                newName: "ID_USUARIO");

            migrationBuilder.RenameColumn(
                name: "Nome",
                table: "TAG",
                newName: "NOME_TAG");

            migrationBuilder.RenameColumn(
                name: "Id",
                table: "TAG",
                newName: "ID_TAG");

            migrationBuilder.RenameColumn(
                name: "Nome",
                table: "STATUS",
                newName: "NOME_STATUS");

            migrationBuilder.RenameColumn(
                name: "Id",
                table: "STATUS",
                newName: "ID_STATUS");

            migrationBuilder.RenameColumn(
                name: "Tamanho",
                table: "PRODUTO",
                newName: "TAMANHO_PRODUTO");

            migrationBuilder.RenameColumn(
                name: "Observacao",
                table: "PRODUTO",
                newName: "OBSERVACAO_PRODUTO");

            migrationBuilder.RenameColumn(
                name: "Nome",
                table: "PRODUTO",
                newName: "NOME_PRODUTO");

            migrationBuilder.RenameColumn(
                name: "Material",
                table: "PRODUTO",
                newName: "MATERIAL_PRODUTO");

            migrationBuilder.RenameColumn(
                name: "Marca",
                table: "PRODUTO",
                newName: "MARCA_PRODUTO");

            migrationBuilder.RenameColumn(
                name: "Cor",
                table: "PRODUTO",
                newName: "COR_PRODUTO");

            migrationBuilder.RenameColumn(
                name: "Id",
                table: "PRODUTO",
                newName: "ID_PRODUTO");

            migrationBuilder.RenameColumn(
                name: "ValorOfertado",
                table: "HISTORICO",
                newName: "VALOR_OFERTADO_HISTORICO");

            migrationBuilder.RenameColumn(
                name: "RecusadoPorQuantidade",
                table: "HISTORICO",
                newName: "RECUSADO_POR_QUANTIDADE");

            migrationBuilder.RenameColumn(
                name: "RecusadoPorProduto",
                table: "HISTORICO",
                newName: "RECUSADO_POR_PRODUTO");

            migrationBuilder.RenameColumn(
                name: "RecusadoPorPreco",
                table: "HISTORICO",
                newName: "RECUSADO_POR_PRECO");

            migrationBuilder.RenameColumn(
                name: "RecusadoPorPrazo",
                table: "HISTORICO",
                newName: "RECUSADO_POR_PRAZO");

            migrationBuilder.RenameColumn(
                name: "Descricao",
                table: "HISTORICO",
                newName: "DESCRICAO_HISTORICO");

            migrationBuilder.RenameColumn(
                name: "Data",
                table: "HISTORICO",
                newName: "DATA_HISTORICO");

            migrationBuilder.RenameColumn(
                name: "Id",
                table: "HISTORICO",
                newName: "ID_HISTORICO");

            migrationBuilder.RenameColumn(
                name: "StatusId",
                table: "HISTORICO",
                newName: "ID_STATUS");

            migrationBuilder.RenameColumn(
                name: "CotacaoId",
                table: "HISTORICO",
                newName: "ID_FORNECEDOR");

            migrationBuilder.RenameIndex(
                name: "IX_Historico_StatusId",
                table: "HISTORICO",
                newName: "IX_HISTORICO_ID_STATUS");

            migrationBuilder.RenameIndex(
                name: "IX_Historico_CotacaoId",
                table: "HISTORICO",
                newName: "IX_HISTORICO_ID_FORNECEDOR");

            migrationBuilder.RenameColumn(
                name: "Nome",
                table: "DEPARTAMENTO",
                newName: "NOME_DEPARTAMENTO");

            migrationBuilder.RenameColumn(
                name: "Icone",
                table: "DEPARTAMENTO",
                newName: "ICONE_DEPARTAMENTO");

            migrationBuilder.RenameColumn(
                name: "Id",
                table: "DEPARTAMENTO",
                newName: "ID_DEPARTAMENTO");

            migrationBuilder.RenameColumn(
                name: "ValorProduto",
                table: "COTACAO",
                newName: "VALOR_PRODUTO");

            migrationBuilder.RenameColumn(
                name: "QuantidadeProduto",
                table: "COTACAO",
                newName: "QUANTIDADE_PRODUTO");

            migrationBuilder.RenameColumn(
                name: "PrioridadeQualidade",
                table: "COTACAO",
                newName: "PRIORIDADE_QUALIDADE");

            migrationBuilder.RenameColumn(
                name: "PrioridadePreco",
                table: "COTACAO",
                newName: "PRIORIDADE_PRECO");

            migrationBuilder.RenameColumn(
                name: "PrioridadeEntrega",
                table: "COTACAO",
                newName: "PRIORIDADE_ENTREGA");

            migrationBuilder.RenameColumn(
                name: "Prazo",
                table: "COTACAO",
                newName: "PRAZO_COTACAO");

            migrationBuilder.RenameColumn(
                name: "DataFechamento",
                table: "COTACAO",
                newName: "DATA_FECHAMENTO_COTACAO");

            migrationBuilder.RenameColumn(
                name: "DataAbertura",
                table: "COTACAO",
                newName: "DATA_ABERTURA_COTACAO");

            migrationBuilder.RenameColumn(
                name: "Id",
                table: "COTACAO",
                newName: "ID_COTACAO");

            migrationBuilder.RenameColumn(
                name: "StatusId",
                table: "COTACAO",
                newName: "ID_USUARIO");

            migrationBuilder.RenameColumn(
                name: "ProdutoId",
                table: "COTACAO",
                newName: "ID_STATUS");

            migrationBuilder.RenameIndex(
                name: "IX_Cotacao_StatusId",
                table: "COTACAO",
                newName: "IX_COTACAO_ID_USUARIO");

            migrationBuilder.RenameIndex(
                name: "IX_Cotacao_ProdutoId",
                table: "COTACAO",
                newName: "IX_COTACAO_ID_STATUS");

            migrationBuilder.RenameColumn(
                name: "Valor",
                table: "CONTATO",
                newName: "VALOR_CONTATO");

            migrationBuilder.RenameColumn(
                name: "Tipo",
                table: "CONTATO",
                newName: "TIPO_CONTATO");

            migrationBuilder.RenameColumn(
                name: "Id",
                table: "CONTATO",
                newName: "ID_CONTATO");

            migrationBuilder.RenameColumn(
                name: "UsuarioId",
                table: "CONTATO",
                newName: "ID_USUARIO");

            migrationBuilder.RenameColumn(
                name: "NotaQualidade",
                table: "AVALIACAO",
                newName: "NOTA_QUALIDADE_AVALIACAO");

            migrationBuilder.RenameColumn(
                name: "NotaPreco",
                table: "AVALIACAO",
                newName: "NOTA_PRECO_AVALIACAO");

            migrationBuilder.RenameColumn(
                name: "NotaEntrega",
                table: "AVALIACAO",
                newName: "NOTA_ENTREGA_AVALIACAO");

            migrationBuilder.RenameColumn(
                name: "Descricao",
                table: "AVALIACAO",
                newName: "DESCRICAO_AVALIACAO");

            migrationBuilder.RenameColumn(
                name: "Data",
                table: "AVALIACAO",
                newName: "DATA_AVALIACAO");

            migrationBuilder.RenameColumn(
                name: "Id",
                table: "AVALIACAO",
                newName: "ID_AVALIACAO");

            migrationBuilder.RenameColumn(
                name: "CotacaoId",
                table: "AVALIACAO",
                newName: "ID_COTACAO");

            migrationBuilder.RenameIndex(
                name: "IX_Avaliacao_CotacaoId",
                table: "AVALIACAO",
                newName: "IX_AVALIACAO_ID_COTACAO");

            migrationBuilder.AlterColumn<long>(
                name: "ID_USUARIO",
                table: "USUARIO",
                type: "NUMBER(19)",
                nullable: false,
                oldClrType: typeof(int),
                oldType: "NUMBER(10)")
                .Annotation("Oracle:Identity", "START WITH 1 INCREMENT BY 1")
                .OldAnnotation("Oracle:Identity", "START WITH 1 INCREMENT BY 1");

            migrationBuilder.AlterColumn<long>(
                name: "UsuariosId",
                table: "TagModelUsuarioModel",
                type: "NUMBER(19)",
                nullable: false,
                oldClrType: typeof(int),
                oldType: "NUMBER(10)");

            migrationBuilder.AlterColumn<string>(
                name: "OBSERVACAO_PRODUTO",
                table: "PRODUTO",
                type: "NVARCHAR2(500)",
                maxLength: 500,
                nullable: true,
                oldClrType: typeof(string),
                oldType: "NVARCHAR2(2000)",
                oldNullable: true);

            migrationBuilder.AddColumn<long>(
                name: "ID_DEPARTAMENTO",
                table: "PRODUTO",
                type: "NUMBER(19)",
                nullable: false,
                defaultValue: 0L);

            migrationBuilder.AlterColumn<decimal>(
                name: "VALOR_OFERTADO_HISTORICO",
                table: "HISTORICO",
                type: "decimal(18,2)",
                nullable: true,
                oldClrType: typeof(decimal),
                oldType: "DECIMAL(18,2)",
                oldNullable: true);

            migrationBuilder.AlterColumn<DateTime>(
                name: "DATA_HISTORICO",
                table: "HISTORICO",
                type: "date",
                nullable: false,
                oldClrType: typeof(DateTime),
                oldType: "TIMESTAMP(7)");

            migrationBuilder.AddColumn<long>(
                name: "ID_COTACAO",
                table: "HISTORICO",
                type: "NUMBER(19)",
                nullable: false,
                defaultValue: 0L);

            migrationBuilder.AlterColumn<decimal>(
                name: "VALOR_PRODUTO",
                table: "COTACAO",
                type: "decimal(18,2)",
                nullable: false,
                oldClrType: typeof(decimal),
                oldType: "DECIMAL(18,2)");

            migrationBuilder.AlterColumn<decimal>(
                name: "QUANTIDADE_PRODUTO",
                table: "COTACAO",
                type: "decimal(18,2)",
                nullable: false,
                oldClrType: typeof(decimal),
                oldType: "DECIMAL(18,2)");

            migrationBuilder.AlterColumn<long>(
                name: "PRIORIDADE_QUALIDADE",
                table: "COTACAO",
                type: "NUMBER(19)",
                nullable: false,
                oldClrType: typeof(int),
                oldType: "NUMBER(10)");

            migrationBuilder.AlterColumn<long>(
                name: "PRIORIDADE_PRECO",
                table: "COTACAO",
                type: "NUMBER(19)",
                nullable: false,
                oldClrType: typeof(int),
                oldType: "NUMBER(10)");

            migrationBuilder.AlterColumn<long>(
                name: "PRIORIDADE_ENTREGA",
                table: "COTACAO",
                type: "NUMBER(19)",
                nullable: false,
                oldClrType: typeof(int),
                oldType: "NUMBER(10)");

            migrationBuilder.AlterColumn<long>(
                name: "PRAZO_COTACAO",
                table: "COTACAO",
                type: "NUMBER(19)",
                nullable: false,
                oldClrType: typeof(int),
                oldType: "NUMBER(10)");

            migrationBuilder.AlterColumn<DateTime>(
                name: "DATA_ABERTURA_COTACAO",
                table: "COTACAO",
                type: "date",
                nullable: false,
                oldClrType: typeof(DateTime),
                oldType: "TIMESTAMP(7)");

            migrationBuilder.AddColumn<long>(
                name: "ID_PRODUTO",
                table: "COTACAO",
                type: "NUMBER(19)",
                nullable: false,
                defaultValue: 0L);

            migrationBuilder.AlterColumn<long>(
                name: "NOTA_QUALIDADE_AVALIACAO",
                table: "AVALIACAO",
                type: "NUMBER(19)",
                nullable: false,
                oldClrType: typeof(int),
                oldType: "NUMBER(10)");

            migrationBuilder.AlterColumn<long>(
                name: "NOTA_PRECO_AVALIACAO",
                table: "AVALIACAO",
                type: "NUMBER(19)",
                nullable: false,
                oldClrType: typeof(int),
                oldType: "NUMBER(10)");

            migrationBuilder.AlterColumn<long>(
                name: "NOTA_ENTREGA_AVALIACAO",
                table: "AVALIACAO",
                type: "NUMBER(19)",
                nullable: false,
                oldClrType: typeof(int),
                oldType: "NUMBER(10)");

            migrationBuilder.AlterColumn<string>(
                name: "DESCRICAO_AVALIACAO",
                table: "AVALIACAO",
                type: "NVARCHAR2(400)",
                maxLength: 400,
                nullable: true,
                oldClrType: typeof(string),
                oldType: "NVARCHAR2(2000)",
                oldNullable: true);

            migrationBuilder.AlterColumn<DateTime>(
                name: "DATA_AVALIACAO",
                table: "AVALIACAO",
                type: "date",
                nullable: false,
                oldClrType: typeof(DateTime),
                oldType: "TIMESTAMP(7)");

            migrationBuilder.AddPrimaryKey(
                name: "PK_USUARIO",
                table: "USUARIO",
                column: "ID_USUARIO");

            migrationBuilder.AddPrimaryKey(
                name: "PK_TAG",
                table: "TAG",
                column: "ID_TAG");

            migrationBuilder.AddPrimaryKey(
                name: "PK_STATUS",
                table: "STATUS",
                column: "ID_STATUS");

            migrationBuilder.AddPrimaryKey(
                name: "PK_PRODUTO",
                table: "PRODUTO",
                column: "ID_PRODUTO");

            migrationBuilder.AddPrimaryKey(
                name: "PK_HISTORICO",
                table: "HISTORICO",
                column: "ID_HISTORICO");

            migrationBuilder.AddPrimaryKey(
                name: "PK_DEPARTAMENTO",
                table: "DEPARTAMENTO",
                column: "ID_DEPARTAMENTO");

            migrationBuilder.AddPrimaryKey(
                name: "PK_COTACAO",
                table: "COTACAO",
                column: "ID_COTACAO");

            migrationBuilder.AddPrimaryKey(
                name: "PK_CONTATO",
                table: "CONTATO",
                column: "ID_CONTATO");

            migrationBuilder.AddPrimaryKey(
                name: "PK_AVALIACAO",
                table: "AVALIACAO",
                column: "ID_AVALIACAO");

            migrationBuilder.CreateIndex(
                name: "IX_PRODUTO_ID_DEPARTAMENTO",
                table: "PRODUTO",
                column: "ID_DEPARTAMENTO");

            migrationBuilder.CreateIndex(
                name: "IX_HISTORICO_ID_COTACAO",
                table: "HISTORICO",
                column: "ID_COTACAO");

            migrationBuilder.CreateIndex(
                name: "IX_COTACAO_ID_PRODUTO",
                table: "COTACAO",
                column: "ID_PRODUTO");

            migrationBuilder.CreateIndex(
                name: "IX_CONTATO_ID_USUARIO",
                table: "CONTATO",
                column: "ID_USUARIO");

            migrationBuilder.AddForeignKey(
                name: "FK_AVALIACAO_COTACAO_ID_COTACAO",
                table: "AVALIACAO",
                column: "ID_COTACAO",
                principalTable: "COTACAO",
                principalColumn: "ID_COTACAO",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_CONTATO_USUARIO_ID_USUARIO",
                table: "CONTATO",
                column: "ID_USUARIO",
                principalTable: "USUARIO",
                principalColumn: "ID_USUARIO",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_COTACAO_PRODUTO_ID_PRODUTO",
                table: "COTACAO",
                column: "ID_PRODUTO",
                principalTable: "PRODUTO",
                principalColumn: "ID_PRODUTO",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_COTACAO_STATUS_ID_STATUS",
                table: "COTACAO",
                column: "ID_STATUS",
                principalTable: "STATUS",
                principalColumn: "ID_STATUS",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_COTACAO_USUARIO_ID_USUARIO",
                table: "COTACAO",
                column: "ID_USUARIO",
                principalTable: "USUARIO",
                principalColumn: "ID_USUARIO",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_DepartamentoModelTagModel_DEPARTAMENTO_DepartamentosId",
                table: "DepartamentoModelTagModel",
                column: "DepartamentosId",
                principalTable: "DEPARTAMENTO",
                principalColumn: "ID_DEPARTAMENTO",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_DepartamentoModelTagModel_TAG_TagsId",
                table: "DepartamentoModelTagModel",
                column: "TagsId",
                principalTable: "TAG",
                principalColumn: "ID_TAG",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_HISTORICO_COTACAO_ID_COTACAO",
                table: "HISTORICO",
                column: "ID_COTACAO",
                principalTable: "COTACAO",
                principalColumn: "ID_COTACAO",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_HISTORICO_STATUS_ID_STATUS",
                table: "HISTORICO",
                column: "ID_STATUS",
                principalTable: "STATUS",
                principalColumn: "ID_STATUS",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_HISTORICO_USUARIO_ID_FORNECEDOR",
                table: "HISTORICO",
                column: "ID_FORNECEDOR",
                principalTable: "USUARIO",
                principalColumn: "ID_USUARIO",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_PRODUTO_DEPARTAMENTO_ID_DEPARTAMENTO",
                table: "PRODUTO",
                column: "ID_DEPARTAMENTO",
                principalTable: "DEPARTAMENTO",
                principalColumn: "ID_DEPARTAMENTO",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_ProdutoModelTagModel_PRODUTO_ProdutosId",
                table: "ProdutoModelTagModel",
                column: "ProdutosId",
                principalTable: "PRODUTO",
                principalColumn: "ID_PRODUTO",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_ProdutoModelTagModel_TAG_TagsId",
                table: "ProdutoModelTagModel",
                column: "TagsId",
                principalTable: "TAG",
                principalColumn: "ID_TAG",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_TagModelUsuarioModel_TAG_TagsId",
                table: "TagModelUsuarioModel",
                column: "TagsId",
                principalTable: "TAG",
                principalColumn: "ID_TAG",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_TagModelUsuarioModel_USUARIO_UsuariosId",
                table: "TagModelUsuarioModel",
                column: "UsuariosId",
                principalTable: "USUARIO",
                principalColumn: "ID_USUARIO",
                onDelete: ReferentialAction.Cascade);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_AVALIACAO_COTACAO_ID_COTACAO",
                table: "AVALIACAO");

            migrationBuilder.DropForeignKey(
                name: "FK_CONTATO_USUARIO_ID_USUARIO",
                table: "CONTATO");

            migrationBuilder.DropForeignKey(
                name: "FK_COTACAO_PRODUTO_ID_PRODUTO",
                table: "COTACAO");

            migrationBuilder.DropForeignKey(
                name: "FK_COTACAO_STATUS_ID_STATUS",
                table: "COTACAO");

            migrationBuilder.DropForeignKey(
                name: "FK_COTACAO_USUARIO_ID_USUARIO",
                table: "COTACAO");

            migrationBuilder.DropForeignKey(
                name: "FK_DepartamentoModelTagModel_DEPARTAMENTO_DepartamentosId",
                table: "DepartamentoModelTagModel");

            migrationBuilder.DropForeignKey(
                name: "FK_DepartamentoModelTagModel_TAG_TagsId",
                table: "DepartamentoModelTagModel");

            migrationBuilder.DropForeignKey(
                name: "FK_HISTORICO_COTACAO_ID_COTACAO",
                table: "HISTORICO");

            migrationBuilder.DropForeignKey(
                name: "FK_HISTORICO_STATUS_ID_STATUS",
                table: "HISTORICO");

            migrationBuilder.DropForeignKey(
                name: "FK_HISTORICO_USUARIO_ID_FORNECEDOR",
                table: "HISTORICO");

            migrationBuilder.DropForeignKey(
                name: "FK_PRODUTO_DEPARTAMENTO_ID_DEPARTAMENTO",
                table: "PRODUTO");

            migrationBuilder.DropForeignKey(
                name: "FK_ProdutoModelTagModel_PRODUTO_ProdutosId",
                table: "ProdutoModelTagModel");

            migrationBuilder.DropForeignKey(
                name: "FK_ProdutoModelTagModel_TAG_TagsId",
                table: "ProdutoModelTagModel");

            migrationBuilder.DropForeignKey(
                name: "FK_TagModelUsuarioModel_TAG_TagsId",
                table: "TagModelUsuarioModel");

            migrationBuilder.DropForeignKey(
                name: "FK_TagModelUsuarioModel_USUARIO_UsuariosId",
                table: "TagModelUsuarioModel");

            migrationBuilder.DropPrimaryKey(
                name: "PK_USUARIO",
                table: "USUARIO");

            migrationBuilder.DropPrimaryKey(
                name: "PK_TAG",
                table: "TAG");

            migrationBuilder.DropPrimaryKey(
                name: "PK_STATUS",
                table: "STATUS");

            migrationBuilder.DropPrimaryKey(
                name: "PK_PRODUTO",
                table: "PRODUTO");

            migrationBuilder.DropIndex(
                name: "IX_PRODUTO_ID_DEPARTAMENTO",
                table: "PRODUTO");

            migrationBuilder.DropPrimaryKey(
                name: "PK_HISTORICO",
                table: "HISTORICO");

            migrationBuilder.DropIndex(
                name: "IX_HISTORICO_ID_COTACAO",
                table: "HISTORICO");

            migrationBuilder.DropPrimaryKey(
                name: "PK_DEPARTAMENTO",
                table: "DEPARTAMENTO");

            migrationBuilder.DropPrimaryKey(
                name: "PK_COTACAO",
                table: "COTACAO");

            migrationBuilder.DropIndex(
                name: "IX_COTACAO_ID_PRODUTO",
                table: "COTACAO");

            migrationBuilder.DropPrimaryKey(
                name: "PK_CONTATO",
                table: "CONTATO");

            migrationBuilder.DropIndex(
                name: "IX_CONTATO_ID_USUARIO",
                table: "CONTATO");

            migrationBuilder.DropPrimaryKey(
                name: "PK_AVALIACAO",
                table: "AVALIACAO");

            migrationBuilder.DropColumn(
                name: "ID_DEPARTAMENTO",
                table: "PRODUTO");

            migrationBuilder.DropColumn(
                name: "ID_COTACAO",
                table: "HISTORICO");

            migrationBuilder.DropColumn(
                name: "ID_PRODUTO",
                table: "COTACAO");

            migrationBuilder.RenameTable(
                name: "USUARIO",
                newName: "Usuario");

            migrationBuilder.RenameTable(
                name: "TAG",
                newName: "Tag");

            migrationBuilder.RenameTable(
                name: "STATUS",
                newName: "Status");

            migrationBuilder.RenameTable(
                name: "PRODUTO",
                newName: "Produto");

            migrationBuilder.RenameTable(
                name: "HISTORICO",
                newName: "Historico");

            migrationBuilder.RenameTable(
                name: "DEPARTAMENTO",
                newName: "Departamento");

            migrationBuilder.RenameTable(
                name: "COTACAO",
                newName: "Cotacao");

            migrationBuilder.RenameTable(
                name: "CONTATO",
                newName: "Contato");

            migrationBuilder.RenameTable(
                name: "AVALIACAO",
                newName: "Avaliacao");

            migrationBuilder.RenameColumn(
                name: "SENHA_USUARIO",
                table: "Usuario",
                newName: "Senha");

            migrationBuilder.RenameColumn(
                name: "NOME_USUARIO",
                table: "Usuario",
                newName: "Nome");

            migrationBuilder.RenameColumn(
                name: "IS_FORNECEDOR",
                table: "Usuario",
                newName: "IsFornecedor");

            migrationBuilder.RenameColumn(
                name: "IMAGEM_USUARIO",
                table: "Usuario",
                newName: "UrlImagem");

            migrationBuilder.RenameColumn(
                name: "EMAIL_USUARIO",
                table: "Usuario",
                newName: "Email");

            migrationBuilder.RenameColumn(
                name: "CNPJ_USUARIO",
                table: "Usuario",
                newName: "Cnpj");

            migrationBuilder.RenameColumn(
                name: "ID_USUARIO",
                table: "Usuario",
                newName: "Id");

            migrationBuilder.RenameColumn(
                name: "NOME_TAG",
                table: "Tag",
                newName: "Nome");

            migrationBuilder.RenameColumn(
                name: "ID_TAG",
                table: "Tag",
                newName: "Id");

            migrationBuilder.RenameColumn(
                name: "NOME_STATUS",
                table: "Status",
                newName: "Nome");

            migrationBuilder.RenameColumn(
                name: "ID_STATUS",
                table: "Status",
                newName: "Id");

            migrationBuilder.RenameColumn(
                name: "TAMANHO_PRODUTO",
                table: "Produto",
                newName: "Tamanho");

            migrationBuilder.RenameColumn(
                name: "OBSERVACAO_PRODUTO",
                table: "Produto",
                newName: "Observacao");

            migrationBuilder.RenameColumn(
                name: "NOME_PRODUTO",
                table: "Produto",
                newName: "Nome");

            migrationBuilder.RenameColumn(
                name: "MATERIAL_PRODUTO",
                table: "Produto",
                newName: "Material");

            migrationBuilder.RenameColumn(
                name: "MARCA_PRODUTO",
                table: "Produto",
                newName: "Marca");

            migrationBuilder.RenameColumn(
                name: "COR_PRODUTO",
                table: "Produto",
                newName: "Cor");

            migrationBuilder.RenameColumn(
                name: "ID_PRODUTO",
                table: "Produto",
                newName: "Id");

            migrationBuilder.RenameColumn(
                name: "VALOR_OFERTADO_HISTORICO",
                table: "Historico",
                newName: "ValorOfertado");

            migrationBuilder.RenameColumn(
                name: "RECUSADO_POR_QUANTIDADE",
                table: "Historico",
                newName: "RecusadoPorQuantidade");

            migrationBuilder.RenameColumn(
                name: "RECUSADO_POR_PRODUTO",
                table: "Historico",
                newName: "RecusadoPorProduto");

            migrationBuilder.RenameColumn(
                name: "RECUSADO_POR_PRECO",
                table: "Historico",
                newName: "RecusadoPorPreco");

            migrationBuilder.RenameColumn(
                name: "RECUSADO_POR_PRAZO",
                table: "Historico",
                newName: "RecusadoPorPrazo");

            migrationBuilder.RenameColumn(
                name: "DESCRICAO_HISTORICO",
                table: "Historico",
                newName: "Descricao");

            migrationBuilder.RenameColumn(
                name: "DATA_HISTORICO",
                table: "Historico",
                newName: "Data");

            migrationBuilder.RenameColumn(
                name: "ID_HISTORICO",
                table: "Historico",
                newName: "Id");

            migrationBuilder.RenameColumn(
                name: "ID_STATUS",
                table: "Historico",
                newName: "StatusId");

            migrationBuilder.RenameColumn(
                name: "ID_FORNECEDOR",
                table: "Historico",
                newName: "CotacaoId");

            migrationBuilder.RenameIndex(
                name: "IX_HISTORICO_ID_STATUS",
                table: "Historico",
                newName: "IX_Historico_StatusId");

            migrationBuilder.RenameIndex(
                name: "IX_HISTORICO_ID_FORNECEDOR",
                table: "Historico",
                newName: "IX_Historico_CotacaoId");

            migrationBuilder.RenameColumn(
                name: "NOME_DEPARTAMENTO",
                table: "Departamento",
                newName: "Nome");

            migrationBuilder.RenameColumn(
                name: "ICONE_DEPARTAMENTO",
                table: "Departamento",
                newName: "Icone");

            migrationBuilder.RenameColumn(
                name: "ID_DEPARTAMENTO",
                table: "Departamento",
                newName: "Id");

            migrationBuilder.RenameColumn(
                name: "VALOR_PRODUTO",
                table: "Cotacao",
                newName: "ValorProduto");

            migrationBuilder.RenameColumn(
                name: "QUANTIDADE_PRODUTO",
                table: "Cotacao",
                newName: "QuantidadeProduto");

            migrationBuilder.RenameColumn(
                name: "PRIORIDADE_QUALIDADE",
                table: "Cotacao",
                newName: "PrioridadeQualidade");

            migrationBuilder.RenameColumn(
                name: "PRIORIDADE_PRECO",
                table: "Cotacao",
                newName: "PrioridadePreco");

            migrationBuilder.RenameColumn(
                name: "PRIORIDADE_ENTREGA",
                table: "Cotacao",
                newName: "PrioridadeEntrega");

            migrationBuilder.RenameColumn(
                name: "PRAZO_COTACAO",
                table: "Cotacao",
                newName: "Prazo");

            migrationBuilder.RenameColumn(
                name: "DATA_FECHAMENTO_COTACAO",
                table: "Cotacao",
                newName: "DataFechamento");

            migrationBuilder.RenameColumn(
                name: "DATA_ABERTURA_COTACAO",
                table: "Cotacao",
                newName: "DataAbertura");

            migrationBuilder.RenameColumn(
                name: "ID_COTACAO",
                table: "Cotacao",
                newName: "Id");

            migrationBuilder.RenameColumn(
                name: "ID_USUARIO",
                table: "Cotacao",
                newName: "StatusId");

            migrationBuilder.RenameColumn(
                name: "ID_STATUS",
                table: "Cotacao",
                newName: "ProdutoId");

            migrationBuilder.RenameIndex(
                name: "IX_COTACAO_ID_USUARIO",
                table: "Cotacao",
                newName: "IX_Cotacao_StatusId");

            migrationBuilder.RenameIndex(
                name: "IX_COTACAO_ID_STATUS",
                table: "Cotacao",
                newName: "IX_Cotacao_ProdutoId");

            migrationBuilder.RenameColumn(
                name: "VALOR_CONTATO",
                table: "Contato",
                newName: "Valor");

            migrationBuilder.RenameColumn(
                name: "TIPO_CONTATO",
                table: "Contato",
                newName: "Tipo");

            migrationBuilder.RenameColumn(
                name: "ID_CONTATO",
                table: "Contato",
                newName: "Id");

            migrationBuilder.RenameColumn(
                name: "ID_USUARIO",
                table: "Contato",
                newName: "UsuarioId");

            migrationBuilder.RenameColumn(
                name: "NOTA_QUALIDADE_AVALIACAO",
                table: "Avaliacao",
                newName: "NotaQualidade");

            migrationBuilder.RenameColumn(
                name: "NOTA_PRECO_AVALIACAO",
                table: "Avaliacao",
                newName: "NotaPreco");

            migrationBuilder.RenameColumn(
                name: "NOTA_ENTREGA_AVALIACAO",
                table: "Avaliacao",
                newName: "NotaEntrega");

            migrationBuilder.RenameColumn(
                name: "DESCRICAO_AVALIACAO",
                table: "Avaliacao",
                newName: "Descricao");

            migrationBuilder.RenameColumn(
                name: "DATA_AVALIACAO",
                table: "Avaliacao",
                newName: "Data");

            migrationBuilder.RenameColumn(
                name: "ID_AVALIACAO",
                table: "Avaliacao",
                newName: "Id");

            migrationBuilder.RenameColumn(
                name: "ID_COTACAO",
                table: "Avaliacao",
                newName: "CotacaoId");

            migrationBuilder.RenameIndex(
                name: "IX_AVALIACAO_ID_COTACAO",
                table: "Avaliacao",
                newName: "IX_Avaliacao_CotacaoId");

            migrationBuilder.AlterColumn<int>(
                name: "Id",
                table: "Usuario",
                type: "NUMBER(10)",
                nullable: false,
                oldClrType: typeof(long),
                oldType: "NUMBER(19)")
                .Annotation("Oracle:Identity", "START WITH 1 INCREMENT BY 1")
                .OldAnnotation("Oracle:Identity", "START WITH 1 INCREMENT BY 1");

            migrationBuilder.AlterColumn<int>(
                name: "UsuariosId",
                table: "TagModelUsuarioModel",
                type: "NUMBER(10)",
                nullable: false,
                oldClrType: typeof(long),
                oldType: "NUMBER(19)");

            migrationBuilder.AlterColumn<string>(
                name: "Observacao",
                table: "Produto",
                type: "NVARCHAR2(2000)",
                nullable: true,
                oldClrType: typeof(string),
                oldType: "NVARCHAR2(500)",
                oldMaxLength: 500,
                oldNullable: true);

            migrationBuilder.AddColumn<long>(
                name: "DepartamentoId",
                table: "Produto",
                type: "NUMBER(19)",
                nullable: true);

            migrationBuilder.AlterColumn<decimal>(
                name: "ValorOfertado",
                table: "Historico",
                type: "DECIMAL(18,2)",
                nullable: true,
                oldClrType: typeof(decimal),
                oldType: "decimal(18,2)",
                oldNullable: true);

            migrationBuilder.AlterColumn<DateTime>(
                name: "Data",
                table: "Historico",
                type: "TIMESTAMP(7)",
                nullable: false,
                oldClrType: typeof(DateTime),
                oldType: "date");

            migrationBuilder.AddColumn<int>(
                name: "FornecedorId",
                table: "Historico",
                type: "NUMBER(10)",
                nullable: false,
                defaultValue: 0);

            migrationBuilder.AlterColumn<decimal>(
                name: "ValorProduto",
                table: "Cotacao",
                type: "DECIMAL(18,2)",
                nullable: false,
                oldClrType: typeof(decimal),
                oldType: "decimal(18,2)");

            migrationBuilder.AlterColumn<decimal>(
                name: "QuantidadeProduto",
                table: "Cotacao",
                type: "DECIMAL(18,2)",
                nullable: false,
                oldClrType: typeof(decimal),
                oldType: "decimal(18,2)");

            migrationBuilder.AlterColumn<int>(
                name: "PrioridadeQualidade",
                table: "Cotacao",
                type: "NUMBER(10)",
                nullable: false,
                oldClrType: typeof(long),
                oldType: "NUMBER(19)");

            migrationBuilder.AlterColumn<int>(
                name: "PrioridadePreco",
                table: "Cotacao",
                type: "NUMBER(10)",
                nullable: false,
                oldClrType: typeof(long),
                oldType: "NUMBER(19)");

            migrationBuilder.AlterColumn<int>(
                name: "PrioridadeEntrega",
                table: "Cotacao",
                type: "NUMBER(10)",
                nullable: false,
                oldClrType: typeof(long),
                oldType: "NUMBER(19)");

            migrationBuilder.AlterColumn<int>(
                name: "Prazo",
                table: "Cotacao",
                type: "NUMBER(10)",
                nullable: false,
                oldClrType: typeof(long),
                oldType: "NUMBER(19)");

            migrationBuilder.AlterColumn<DateTime>(
                name: "DataAbertura",
                table: "Cotacao",
                type: "TIMESTAMP(7)",
                nullable: false,
                oldClrType: typeof(DateTime),
                oldType: "date");

            migrationBuilder.AddColumn<int>(
                name: "CompradorId",
                table: "Cotacao",
                type: "NUMBER(10)",
                nullable: false,
                defaultValue: 0);

            migrationBuilder.AddColumn<int>(
                name: "UsuarioId1",
                table: "Contato",
                type: "NUMBER(10)",
                nullable: false,
                defaultValue: 0);

            migrationBuilder.AlterColumn<int>(
                name: "NotaQualidade",
                table: "Avaliacao",
                type: "NUMBER(10)",
                nullable: false,
                oldClrType: typeof(long),
                oldType: "NUMBER(19)");

            migrationBuilder.AlterColumn<int>(
                name: "NotaPreco",
                table: "Avaliacao",
                type: "NUMBER(10)",
                nullable: false,
                oldClrType: typeof(long),
                oldType: "NUMBER(19)");

            migrationBuilder.AlterColumn<int>(
                name: "NotaEntrega",
                table: "Avaliacao",
                type: "NUMBER(10)",
                nullable: false,
                oldClrType: typeof(long),
                oldType: "NUMBER(19)");

            migrationBuilder.AlterColumn<string>(
                name: "Descricao",
                table: "Avaliacao",
                type: "NVARCHAR2(2000)",
                nullable: true,
                oldClrType: typeof(string),
                oldType: "NVARCHAR2(400)",
                oldMaxLength: 400,
                oldNullable: true);

            migrationBuilder.AlterColumn<DateTime>(
                name: "Data",
                table: "Avaliacao",
                type: "TIMESTAMP(7)",
                nullable: false,
                oldClrType: typeof(DateTime),
                oldType: "date");

            migrationBuilder.AddPrimaryKey(
                name: "PK_Usuario",
                table: "Usuario",
                column: "Id");

            migrationBuilder.AddPrimaryKey(
                name: "PK_Tag",
                table: "Tag",
                column: "Id");

            migrationBuilder.AddPrimaryKey(
                name: "PK_Status",
                table: "Status",
                column: "Id");

            migrationBuilder.AddPrimaryKey(
                name: "PK_Produto",
                table: "Produto",
                column: "Id");

            migrationBuilder.AddPrimaryKey(
                name: "PK_Historico",
                table: "Historico",
                column: "Id");

            migrationBuilder.AddPrimaryKey(
                name: "PK_Departamento",
                table: "Departamento",
                column: "Id");

            migrationBuilder.AddPrimaryKey(
                name: "PK_Cotacao",
                table: "Cotacao",
                column: "Id");

            migrationBuilder.AddPrimaryKey(
                name: "PK_Contato",
                table: "Contato",
                column: "Id");

            migrationBuilder.AddPrimaryKey(
                name: "PK_Avaliacao",
                table: "Avaliacao",
                column: "Id");

            migrationBuilder.CreateIndex(
                name: "IX_Produto_DepartamentoId",
                table: "Produto",
                column: "DepartamentoId");

            migrationBuilder.CreateIndex(
                name: "IX_Historico_FornecedorId",
                table: "Historico",
                column: "FornecedorId");

            migrationBuilder.CreateIndex(
                name: "IX_Cotacao_CompradorId",
                table: "Cotacao",
                column: "CompradorId");

            migrationBuilder.CreateIndex(
                name: "IX_Contato_UsuarioId1",
                table: "Contato",
                column: "UsuarioId1");

            migrationBuilder.AddForeignKey(
                name: "FK_Avaliacao_Cotacao_CotacaoId",
                table: "Avaliacao",
                column: "CotacaoId",
                principalTable: "Cotacao",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_Contato_Usuario_UsuarioId1",
                table: "Contato",
                column: "UsuarioId1",
                principalTable: "Usuario",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_Cotacao_Produto_ProdutoId",
                table: "Cotacao",
                column: "ProdutoId",
                principalTable: "Produto",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_Cotacao_Status_StatusId",
                table: "Cotacao",
                column: "StatusId",
                principalTable: "Status",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_Cotacao_Usuario_CompradorId",
                table: "Cotacao",
                column: "CompradorId",
                principalTable: "Usuario",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_DepartamentoModelTagModel_Departamento_DepartamentosId",
                table: "DepartamentoModelTagModel",
                column: "DepartamentosId",
                principalTable: "Departamento",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_DepartamentoModelTagModel_Tag_TagsId",
                table: "DepartamentoModelTagModel",
                column: "TagsId",
                principalTable: "Tag",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_Historico_Cotacao_CotacaoId",
                table: "Historico",
                column: "CotacaoId",
                principalTable: "Cotacao",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_Historico_Status_StatusId",
                table: "Historico",
                column: "StatusId",
                principalTable: "Status",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_Historico_Usuario_FornecedorId",
                table: "Historico",
                column: "FornecedorId",
                principalTable: "Usuario",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_Produto_Departamento_DepartamentoId",
                table: "Produto",
                column: "DepartamentoId",
                principalTable: "Departamento",
                principalColumn: "Id");

            migrationBuilder.AddForeignKey(
                name: "FK_ProdutoModelTagModel_Produto_ProdutosId",
                table: "ProdutoModelTagModel",
                column: "ProdutosId",
                principalTable: "Produto",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_ProdutoModelTagModel_Tag_TagsId",
                table: "ProdutoModelTagModel",
                column: "TagsId",
                principalTable: "Tag",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_TagModelUsuarioModel_Tag_TagsId",
                table: "TagModelUsuarioModel",
                column: "TagsId",
                principalTable: "Tag",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_TagModelUsuarioModel_Usuario_UsuariosId",
                table: "TagModelUsuarioModel",
                column: "UsuariosId",
                principalTable: "Usuario",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }
    }
}
