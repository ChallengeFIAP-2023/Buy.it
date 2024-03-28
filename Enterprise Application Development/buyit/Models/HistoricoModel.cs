using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace Buyit.Models
{
    [Table("HISTORICO")]
    public class HistoricoModel
    {
        [Key]
        [Column("ID_HISTORICO")]
        public long Id { get; set; }

        [Required(ErrorMessage = "O campo cotacao não pode estar vazio.")]
        [ForeignKey("COTACAO")]
        [Column("ID_COTACAO")]
        public CotacaoModel Cotacao { get; set; }

        [Required(ErrorMessage = "O campo fornecedor não pode estar vazio.")]
        [ForeignKey("USUARIO")]
        [Column("ID_FORNECEDOR")]
        public UsuarioModel Fornecedor { get; set; }

        [Required(ErrorMessage = "O campo status não pode estar vazio.")]
        [ForeignKey("STATUS")]
        [Column("ID_STATUS")]
        public StatusModel Status { get; set; }

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

        [Column("DESCRICAO_HISTORICO")]
        public string? Descricao { get; set; }

        [Required(ErrorMessage = "O campo data não pode estar vazio.")]
        [Column("DATA_HISTORICO", TypeName = "date")]
        public DateTime Data { get; set; }

        [Column("VALOR_OFERTADO_HISTORICO")]
        public decimal? ValorOfertado { get; set; }
    }
}