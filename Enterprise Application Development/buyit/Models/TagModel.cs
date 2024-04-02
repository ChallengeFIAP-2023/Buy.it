using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace Buyit.Models
{
    [Table("TAG")]
    public class TagModel
    {
        [Key]
        [Column("ID_TAG")]
        public long Id { get; set; }

        [Required(ErrorMessage = "O campo nome não pode estar vazio.")]
        [Column("NOME_TAG")]
        public string Nome { get; set; }

        public virtual ICollection<DepartamentoModel>? Departamentos { get; set; } = new HashSet<DepartamentoModel>();

        public virtual ICollection<UsuarioModel>? Usuarios { get; set; } = new HashSet<UsuarioModel>();

        public virtual ICollection<ProdutoModel>? Produtos { get; set; } = new HashSet<ProdutoModel>();
    }
}