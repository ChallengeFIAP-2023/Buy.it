using System.ComponentModel.DataAnnotations;

namespace Buyit.Dtos
{
    public class ProdutoDto
    {
        public long? Id { get; set; }

        [Required(ErrorMessage = "O campo nome não pode estar vazio.")]
        public string? Nome { get; set; }

        public string? Marca { get; set; }
        public string? Cor { get; set; }
        public string? Tamanho { get; set; }
        public string? Material { get; set; }
        public string? Observacao { get; set; }
        public long? IdDepartamento { get; set; }

        public List<long>? IdsTags { get; set; }
    }
}