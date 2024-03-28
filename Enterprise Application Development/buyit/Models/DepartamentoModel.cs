using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Buyit.Models
{
    [Table("DEPARTAMENTO")]
    public class DepartamentoModel
    {
        [Key]
        [Column("ID_DEPARTAMENTO")]
        public long Id { get; set; }

        [Required(ErrorMessage = "O campo nome não pode estar vazio.")]
        [Column("NOME_DEPARTAMENTO")]
        public string Nome { get; set; }

        [Column("ICONE_DEPARTAMENTO")]
        public string? Icone { get; set; }

        public virtual ICollection<TagModel> Tags { get; set; } = new List<TagModel>();
    }
}