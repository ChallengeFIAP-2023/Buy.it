using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace br.com.fiap.buy.it.model
{
    [Table("DEPARTAMENTO")]
    public class Departamento
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Sequence)]
        [Column("ID_DEPARTAMENTO")]
        public long Id { get; set; }

        [Required(ErrorMessage = "O campo nome não pode estar vazio.")]
        [Column("NOME_DEPARTAMENTO")]
        public string Nome { get; set; }

        [Column("ICONE_DEPARTAMENTO")]
        public string Icone { get; set; }

        public virtual ICollection<Tag> Tags { get; set; } = new HashSet<Tag>();
    }
}