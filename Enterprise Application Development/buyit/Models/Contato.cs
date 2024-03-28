using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace br.com.fiap.buy.it.model
{
    [Table("CONTATO")]
    public class Contato
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Sequence)]
        [Column("ID_CONTATO")]
        public long Id { get; set; }

        [Required(ErrorMessage = "O campo tipo não pode estar vazio.")]
        [Column("TIPO_CONTATO")]
        public string Tipo { get; set; }

        [Required(ErrorMessage = "O campo valor não pode estar vazio.")]
        [Column("VALOR_CONTATO")]
        public string Valor { get; set; }

        [ForeignKey("ID_USUARIO")]
        public long UsuarioId { get; set; }
        public Usuario Usuario { get; set; }
    }
}