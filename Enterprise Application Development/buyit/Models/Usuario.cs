using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Microsoft.AspNetCore.Identity;

namespace br.com.fiap.buy.it.model
{
    [Table("USUARIO")]
    public class Usuario : IdentityUser<long>
    {
        [Required(ErrorMessage = "O campo nome não pode estar vazio.")]
        [Column("NOME_USUARIO")]
        public string Nome { get; set; }

        [Required(ErrorMessage = "O campo email não pode estar vazio.")]
        [EmailAddress(ErrorMessage = "Endereço de e-mail inválido.")]
        [Column("EMAIL_USUARIO")]
        public override string Email { get; set; }

        [Required(ErrorMessage = "O campo senha não pode estar vazio.")]
        [Column("SENHA_USUARIO")]
        public string Senha { get; set; }

        [Column("IMAGEM_USUARIO")]
        public string UrlImagem { get; set; }

        [Required(ErrorMessage = "O campo cnpj não pode estar vazio.")]
        [Column("CNPJ_USUARIO")]
        public string Cnpj { get; set; }

        [Required]
        [Column("IS_FORNECEDOR")]
        public bool IsFornecedor { get; set; }

        public virtual ICollection<Tag> Tags { get; set; } = new HashSet<Tag>();
    }
}