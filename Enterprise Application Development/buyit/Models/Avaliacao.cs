using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace br.com.fiap.buy.it.model
{
    [Table("AVALIACAO")]
    public class Avaliacao
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Sequence)]
        [Column("ID_AVALIACAO")]
        public long Id { get; set; }

        [Required(ErrorMessage = "O campo cotacao não pode estar vazio.")]
        [ForeignKey("ID_COTACAO")]
        public long CotacaoId { get; set; }
        public Cotacao Cotacao { get; set; }

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
        public string Descricao { get; set; }
    }
}