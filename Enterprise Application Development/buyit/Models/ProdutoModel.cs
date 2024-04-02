using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace Buyit.Models
{
    [Table("PRODUTO")]
    public class ProdutoModel
    {
        [Key]
        [Column("ID_PRODUTO")]
        public long Id { get; set; }

        [Required(ErrorMessage = "O campo nome não pode estar vazio.")]
        [Column("NOME_PRODUTO")]
        public string Nome { get; set; }

        [Column("MARCA_PRODUTO")]
        public string? Marca { get; set; }

        [Column("COR_PRODUTO")]
        public string? Cor { get; set; }

        [Column("TAMANHO_PRODUTO")]
        public string? Tamanho { get; set; }

        [Column("MATERIAL_PRODUTO")]
        public string? Material { get; set; }

        [MaxLength(500)]
        [Column("OBSERVACAO_PRODUTO")]
        public string? Observacao { get; set; }

        [ForeignKey("ID_DEPARTAMENTO")]
        public DepartamentoModel? Departamento { get; set; }

        public virtual ICollection<TagModel>? Tags { get; set; } = new HashSet<TagModel>();
    }
}