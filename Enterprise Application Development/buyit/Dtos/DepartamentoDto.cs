using System.ComponentModel.DataAnnotations;

namespace Buyit.Dtos
{
    public class DepartamentoDto
    {
        public long? Id { get; set; }

        [Required(ErrorMessage = "O campo nome não pode estar vazio.")]
        public string Nome { get; set; }

        public string? Icone { get; set; }

        public List<long>? IdsTags { get; set; }
    }
}