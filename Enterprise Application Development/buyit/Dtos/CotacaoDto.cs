using System;
using System.ComponentModel.DataAnnotations;

namespace Buyit.Dtos
{
    public class CotacaoDto
    {
        public long? Id { get; set; }

        [Required(ErrorMessage = "O campo data não pode estar vazio.")]
        [DataType(DataType.Date)]
        public DateTime DataAbertura { get; set; }

        [Required(ErrorMessage = "O campo idComprador não pode estar vazio.")]
        public long IdComprador { get; set; }

        [Required(ErrorMessage = "O campo idProduto não pode estar vazio.")]
        public long IdProduto { get; set; }

        [Required(ErrorMessage = "O campo quantidadeProduto não pode estar vazio.")]
        public decimal QuantidadeProduto { get; set; }

        [Required(ErrorMessage = "O campo valorProduto não pode estar vazio.")]
        public decimal ValorProduto { get; set; }

        [Required(ErrorMessage = "O campo idStatus não pode estar vazio.")]
        public long IdStatus { get; set; }

        [Required(ErrorMessage = "O campo prioridadeEntrega não pode estar vazio.")]
        [Range(1, 3)]
        public long PrioridadeEntrega { get; set; }

        [Required(ErrorMessage = "O campo prioridadeQualidade não pode estar vazio.")]
        [Range(1, 3)]
        public long PrioridadeQualidade { get; set; }

        [Required(ErrorMessage = "O campo prioridadePreco não pode estar vazio.")]
        [Range(1, 3)]
        public long PrioridadePreco { get; set; }

        [Required(ErrorMessage = "O campo prazo não pode estar vazio.")]
        public long Prazo { get; set; }

        [DataType(DataType.Date)]
        public DateTime? DataFechamento { get; set; }
    }
}