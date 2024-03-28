using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace br.com.fiap.buy.it.model
{
    [Table("TAG")]
    public class Tag
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Sequence)]
        [Column("ID_TAG")]
        public long Id { get; set; }

        [Required(ErrorMessage = "O campo nome não pode estar vazio.")]
        [Column("NOME_TAG")]
        public string Nome { get; set; }

        public virtual ICollection<Departamento> Departamentos { get; set; } = new HashSet<Departamento>();
        public virtual ICollection<Usuario> Usuarios { get; set; } = new HashSet<Usuario>();
        public virtual ICollection<Produto> Produtos { get; set; } = new HashSet<Produto>();
    }
}