using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace Buyit.Models
{
    [Table("AVALIACAO")]
    public class AvaliacaoModel
    {
        [Key]
        [Column("ID_AVALIACAO")]
        public long Id { get; set; }

        [Required(ErrorMessage = "O campo cotacao não pode estar vazio.")]
        [ForeignKey("COTACAO")]
        [Column("ID_COTACAO")]
        public long CotacaoId { get; set; }
        public CotacaoModel Cotacao { get; set; }

        [Required(ErrorMessage = "O campo data não pode estar vazio.")]
        [Column("DATA_AVALIACAO", TypeName = "date")]
        public DateTime Data { get; set; }

        [Required(ErrorMessage = "O campo notaEntrega não pode estar vazio.")]
        [Range(1, 5)]
        [Column("NOTA_ENTREGA_AVALIACAO")]
        public int NotaEntrega { get; set; }

        [Required(ErrorMessage = "O campo notaQualidade não pode estar vazio.")]
        [Range(1, 5)]
        [Column("NOTA_QUALIDADE_AVALIACAO")]
        public int NotaQualidade { get; set; }

        [Required(ErrorMessage = "O campo notaPreco não pode estar vazio.")]
        [Range(1, 5)]
        [Column("NOTA_PRECO_AVALIACAO")]
        public int NotaPreco { get; set; }

        [MaxLength(400)]
        [Column("DESCRICAO_AVALIACAO")]
        public string? Descricao { get; set; }
    }
}