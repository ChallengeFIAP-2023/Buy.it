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
        [ForeignKey("USUARIO")]
        [Column("ID_USUARIO")]
        public long CompradorId { get; set; }
        public UsuarioModel Comprador { get; set; }

        [Required(ErrorMessage = "O campo produto não pode estar vazio.")]
        [ForeignKey("PRODUTO")]
        [Column("ID_PRODUTO")]
        public long ProdutoId { get; set; }
        public ProdutoModel Produto { get; set; }

        [Required(ErrorMessage = "O campo quantidadeProduto não pode estar vazio.")]
        [Column("QUANTIDADE_PRODUTO")]
        public decimal QuantidadeProduto { get; set; }

        [Required(ErrorMessage = "O campo valorProduto não pode estar vazio.")]
        [Column("VALOR_PRODUTO")]
        public decimal ValorProduto { get; set; }

        [Required(ErrorMessage = "O campo status não pode estar vazio.")]
        [ForeignKey("STATUS")]
        [Column("ID_STATUS")]
        public long StatusId { get; set; }
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