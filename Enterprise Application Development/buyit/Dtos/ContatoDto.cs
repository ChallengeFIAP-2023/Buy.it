using System.ComponentModel.DataAnnotations;

namespace Buyit.Dtos
{
    public class ContatoDto
    {
        public long? Id { get; set; }

        [Required(ErrorMessage = "O campo tipo não pode estar vazio.")]
        public string Tipo { get; set; }

        [Required(ErrorMessage = "O campo valor não pode estar vazio.")]
        [StringLength(255)]
        public string Valor { get; set; }

        [Required(ErrorMessage = "O campo idUsuario não pode estar vazio.")]
        public long IdUsuario { get; set; }
    }
}