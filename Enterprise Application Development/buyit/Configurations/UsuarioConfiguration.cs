using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using Buyit.Models;

namespace Buyit.Configurations
{
    public class UsuarioConfiguration : IEntityTypeConfiguration<UsuarioModel>
    {
        public void Configure(EntityTypeBuilder<UsuarioModel> builder)
        {
            builder.ToTable("USUARIO");

            builder.HasKey(x => x.Id);
            builder.Property(x => x.Id).HasColumnName("ID_USUARIO");
            builder.Property(x => x.Nome).HasColumnName("NOME_USUARIO").HasMaxLength(100).IsRequired();
            builder.Property(x => x.Email).HasColumnName("EMAIL_USUARIO").HasMaxLength(100).IsRequired();
            builder.Property(x => x.Senha).HasColumnName("SENHA_USUARIO").HasMaxLength(100).IsRequired();
            builder.Property(x => x.UrlImagem).HasColumnName("IMAGEM_USUARIO").HasMaxLength(255);
            builder.Property(x => x.Cnpj).HasColumnName("CNPJ_USUARIO").HasMaxLength(14).IsRequired();
            builder.Property(x => x.IsFornecedor).HasColumnName("IS_FORNECEDOR").IsRequired();

            builder.HasMany(u => u.Tags)
                   .WithMany(t => t.Usuarios)
                   .UsingEntity(j => j.ToTable("USUARIO_TAG"));
        }
    }
}