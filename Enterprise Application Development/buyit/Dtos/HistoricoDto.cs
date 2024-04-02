using System.ComponentModel.DataAnnotations;

namespace Buyit.Dtos
{
    public class HistoricoDto
    {
        public long? Id { get; set; }

        [Required(ErrorMessage = "O campo idCotacao não pode estar vazio.")]
        public long IdCotacao { get; set; }

        [Required(ErrorMessage = "O campo idFornecedor não pode estar vazio.")]
        public long IdFornecedor { get; set; }

        [Required(ErrorMessage = "O campo idStatus não pode estar vazio.")]
        public long IdStatus { get; set; }

        [Required(ErrorMessage = "O campo recusadoPorProduto não pode estar vazio.")]
        public bool RecusadoPorProduto { get; set; }

        [Required(ErrorMessage = "O campo recusadoPorQuantidade não pode estar vazio.")]
        public bool RecusadoPorQuantidade { get; set; }

        [Required(ErrorMessage = "O campo recusadoPorPreco não pode estar vazio.")]
        public bool RecusadoPorPreco { get; set; }

        [Required(ErrorMessage = "O campo recusadoPorPrazo não pode estar vazio.")]
        public bool RecusadoPorPrazo { get; set; }

        public string? Descricao { get; set; }

        [Required(ErrorMessage = "O campo data não pode estar vazio.")]
        [DataType(DataType.Date)]
        public DateTime Data { get; set; }

        public decimal? ValorOfertado { get; set; }
    }
}