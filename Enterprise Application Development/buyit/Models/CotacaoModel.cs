using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Buyit.Models
{
    [Table("COTACAO")]
    public class CotacaoModel
    {
        [Key]
        [Column("ID_COTACAO")]
        public long Id { get; set; }

        [Required(ErrorMessage = "O campo data não pode estar vazio.")]
        [Column("DATA_ABERTURA_COTACAO", TypeName = "date")]
        public DateTime DataAbertura { get; set; }

        [Required(ErrorMessage = "O campo comprador não pode estar vazio.")]
        [ForeignKey("ID_USUARIO")]
        public UsuarioModel Comprador { get; set; }

        [Required(ErrorMessage = "O campo produto não pode estar vazio.")]
        [ForeignKey("ID_PRODUTO")]
        public ProdutoModel Produto { get; set; }

        [Required(ErrorMessage = "O campo quantidadeProduto não pode estar vazio.")]
        [Column("QUANTIDADE_PRODUTO", TypeName = "decimal(18,2)")]
        public decimal QuantidadeProduto { get; set; }

        [Required(ErrorMessage = "O campo valorProduto não pode estar vazio.")]
        [Column("VALOR_PRODUTO", TypeName = "decimal(18,2)")]
        public decimal ValorProduto { get; set; }

        [Required(ErrorMessage = "O campo status não pode estar vazio.")]
        [ForeignKey("ID_STATUS")]
        public StatusModel Status { get; set; }

        [Required(ErrorMessage = "O campo prioridadeEntrega não pode estar vazio.")]
        [Column("PRIORIDADE_ENTREGA")]
        public long PrioridadeEntrega { get; set; }

        [Required(ErrorMessage = "O campo prioridadeQualidade não pode estar vazio.")]
        [Column("PRIORIDADE_QUALIDADE")]
        public long PrioridadeQualidade { get; set; }

        [Required(ErrorMessage = "O campo prioridadePreco não pode estar vazio.")]
        [Column("PRIORIDADE_PRECO")]
        public long PrioridadePreco { get; set; }

        [Required(ErrorMessage = "O campo prazo não pode estar vazio.")]
        [Column("PRAZO_COTACAO")]
        public long Prazo { get; set; }

        [Column("DATA_FECHAMENTO_COTACAO")]
        public DateTime? DataFechamento { get; set; }
    }
}