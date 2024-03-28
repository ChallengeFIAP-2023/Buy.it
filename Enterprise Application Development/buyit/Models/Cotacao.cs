using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace br.com.fiap.buy.it.model
{
    [Table("COTACAO")]
    public class Cotacao
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        [Column("ID_COTACAO")]
        public long Id { get; set; }

        [Required(ErrorMessage = "O campo data não pode estar vazio.")]
        [Column("DATA_ABERTURA_COTACAO", TypeName = "date")]
        public DateTime DataAbertura { get; set; }

        [Required(ErrorMessage = "O campo comprador não pode estar vazio.")]
        public Usuario Comprador { get; set; }

        [Required(ErrorMessage = "O campo produto não pode estar vazio.")]
        public Produto Produto { get; set; }

        [Required(ErrorMessage = "O campo quantidadeProduto não pode estar vazio.")]
        [Column("QUANTIDADE_PRODUTO")]
        public decimal QuantidadeProduto { get; set; }

        [Required(ErrorMessage = "O campo valorProduto não pode estar vazio.")]
        [Column("VALOR_PRODUTO")]
        public decimal ValorProduto { get; set; }

        [Required(ErrorMessage = "O campo status não pode estar vazio.")]
        public Status Status { get; set; }

        [Required(ErrorMessage = "O campo prioridadeEntrega não pode estar vazio.")]
        [Column("PRIORIDADE_ENTREGA")]
        public int PrioridadeEntrega { get; set; }

        [Required(ErrorMessage = "O campo prioridadeQualidade não pode estar vazio.")]
        [Column("PRIORIDADE_QUALIDADE")]
        public int PrioridadeQualidade { get; set; }

        [Required(ErrorMessage = "O campo prioridadePreco não pode estar vazio.")]
        [Column("PRIORIDADE_PRECO")]
        public int PrioridadePreco { get; set; }

        [Required(ErrorMessage = "O campo prazo não pode estar vazio.")]
        [Column("PRAZO_COTACAO")]
        public int Prazo { get; set; }

        [Column("DATA_FECHAMENTO_COTACAO", TypeName = "date")]
        public DateTime? DataFechamento { get; set; }
    }
}