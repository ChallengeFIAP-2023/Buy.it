using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Buyit.Models
{
    [Table("CONTATO")]
    public class ContatoModel
    {
        [Key]
        [Column("ID_CONTATO")]
        public long Id { get; set; }

        [Required(ErrorMessage = "O campo tipo não pode estar vazio.")]
        [Column("TIPO_CONTATO")]
        public string Tipo { get; set; }

        [Required(ErrorMessage = "O campo valor não pode estar vazio.")]
        [Column("VALOR_CONTATO")]
        public string Valor { get; set; }

        [Required(ErrorMessage = "O campo usuario não pode estar vazio.")]
        [ForeignKey("ID_USUARIO")]
        public UsuarioModel Usuario { get; set; }
    }
}