using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace br.com.fiap.buy.it.model
{
    [Table("PRODUTO")]
    public class Produto
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Sequence)]
        [Column("ID_PRODUTO")]
        public long Id { get; set; }

        [Required(ErrorMessage = "O campo nome não pode estar vazio.")]
        [Column("NOME_PRODUTO")]
        public string Nome { get; set; }

        [Column("MARCA_PRODUTO")]
        public string Marca { get; set; }

        [Column("COR_PRODUTO")]
        public string Cor { get; set; }

        [Column("TAMANHO_PRODUTO")]
        public string Tamanho { get; set; }

        [Column("MATERIAL_PRODUTO")]
        public string Material { get; set; }

        [MaxLength(500)]
        [Column("OBSERVACAO_PRODUTO")]
        public string Observacao { get; set; }

        [ForeignKey("ID_DEPARTAMENTO")]
        public long DepartamentoId { get; set; }
        public Departamento Departamento { get; set; }

        public virtual ICollection<Tag> Tags { get; set; } = new HashSet<Tag>();
    }
}