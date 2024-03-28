using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace br.com.fiap.buy.it.model
{
    [Table("STATUS")]
    public class Status
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Sequence)]
        [Column("ID_STATUS")]
        public long Id { get; set; }

        [Required(ErrorMessage = "O campo nome não pode estar vazio.")]
        [Column("NOME_STATUS")]
        public string Nome { get; set; }
    }
}