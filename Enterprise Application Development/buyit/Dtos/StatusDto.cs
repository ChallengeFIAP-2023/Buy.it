using System.ComponentModel.DataAnnotations;

namespace Buyit.Dtos
{
    public class StatusDto
    {
        public long? Id { get; set; }

        [Required(ErrorMessage = "O campo nome não pode estar vazio.")]
        public string Nome { get; set; }
    }
}