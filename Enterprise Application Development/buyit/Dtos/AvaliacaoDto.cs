using System.ComponentModel.DataAnnotations;

namespace Buyit.Dtos
{
    public class AvaliacaoDto
    {
        public long? Id { get; set; }

        [Required(ErrorMessage = "O campo idCotacao não pode estar vazio.")]
        public long IdCotacao { get; set; }

        [Required(ErrorMessage = "O campo data não pode estar vazio.")]
        [DataType(DataType.Date)]
        public DateTime Data { get; set; }

        [Required(ErrorMessage = "O campo notaEntrega não pode estar vazio.")]
        [Range(1, 5)]
        public long NotaEntrega { get; set; }

        [Required(ErrorMessage = "O campo notaQualidade não pode estar vazio.")]
        [Range(1, 5)]
        public long NotaQualidade { get; set; }

        [Required(ErrorMessage = "O campo notaPreco não pode estar vazio.")]
        [Range(1, 5)]
        public long NotaPreco { get; set; }

        public string? Descricao { get; set; }
    }
}