using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace Buyit.Models
{
    [Table("STATUS")]
    public class StatusModel
    {
        [Key]
        [Column("ID_STATUS")]
        public long Id { get; set; }

        [Required(ErrorMessage = "O campo nome não pode estar vazio.")]
        [Column("NOME_STATUS")]
        public string Nome { get; set; }
    }
}