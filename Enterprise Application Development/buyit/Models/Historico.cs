using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace br.com.fiap.buy.it.model
{
    [Table("HISTORICO")]
    public class Historico
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        [Column("ID_HISTORICO")]
        public long Id { get; set; }

        [Required(ErrorMessage = "O campo cotacao não pode estar vazio.")]
        public Cotacao Cotacao { get; set; }

        [Required(ErrorMessage = "O campo fornecedor não pode estar vazio.")]
        public Usuario Fornecedor { get; set; }

        [Required(ErrorMessage = "O campo status não pode estar vazio.")]
        public Status Status { get; set; }

        [Required(ErrorMessage = "O campo recusadoPorProduto não pode estar vazio.")]
        [Column("RECUSADO_POR_PRODUTO")]
        public bool RecusadoPorProduto { get; set; }

        [Required(ErrorMessage = "O campo recusadoPorQuantidade não pode estar vazio.")]
        [Column("RECUSADO_POR_QUANTIDADE")]
        public bool RecusadoPorQuantidade { get; set; }

        [Required(ErrorMessage = "O campo recusadoPorPreco não pode estar vazio.")]
        [Column("RECUSADO_POR_PRECO")]
        public bool RecusadoPorPreco { get; set; }

        [Required(ErrorMessage = "O campo recusadoPorPrazo não pode estar vazio.")]
        [Column("RECUSADO_POR_PRAZO")]
        public bool RecusadoPorPrazo { get; set; }

        [MaxLength(400)]
        [Column("DESCRICAO_HISTORICO")]
        public string Descricao { get; set; }

        [Required(ErrorMessage = "O campo data não pode estar vazio.")]
        [Column("DATA_HISTORICO", TypeName = "date")]
        public DateTime Data { get; set; }

        [Column("VALOR_OFERTADO_HISTORICO")]
        public decimal? ValorOfertado { get; set; }
    }
}