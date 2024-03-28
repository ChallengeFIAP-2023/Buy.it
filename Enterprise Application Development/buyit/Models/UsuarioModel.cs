using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Buyit.Models
{
    [Table("USUARIO")]
    public class UsuarioModel
    {
        [Key]
        [Column("ID_USUARIO")]
        public int Id { get; set; }

        [Required(ErrorMessage = "O campo nome não pode estar vazio.")]
        [Column("NOME_USUARIO")]
        public string Nome { get; set; }

        [Required(ErrorMessage = "O campo email não pode estar vazio.")]
        [EmailAddress(ErrorMessage = "Endereço de e-mail inválido.")]
        [Column("EMAIL_USUARIO")]
        public string Email { get; set; }

        [Required(ErrorMessage = "O campo senha não pode estar vazio.")]
        [Column("SENHA_USUARIO")]
        public string Senha { get; set; }

        [Column("IMAGEM_USUARIO")]
        public string? UrlImagem { get; set; }

        [Required(ErrorMessage = "O campo cnpj não pode estar vazio.")]
        [Column("CNPJ_USUARIO")]
        public string Cnpj { get; set; }

        [Required]
        [Column("IS_FORNECEDOR")]
        public bool IsFornecedor { get; set; }

        public virtual ICollection<TagModel> Tags { get; set; } = new HashSet<TagModel>();
    }
}