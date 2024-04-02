using System.ComponentModel.DataAnnotations;

namespace Buyit.Dtos
{
    public class TagDto
    {
        public long? Id { get; set; }

        [Required(ErrorMessage = "O campo nome não pode estar vazio.")]
        public string Nome { get; set; }

        public List<long>? IdsDepartamentos { get; set; }
        public List<long>? IdsUsuarios { get; set; }
        public List<long>? IdsProdutos { get; set; }
    }
}