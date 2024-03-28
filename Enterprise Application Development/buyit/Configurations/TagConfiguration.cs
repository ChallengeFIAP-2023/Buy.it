using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using Buyit.Models;

namespace Buyit.Configurations
{
    public class TagConfiguration : IEntityTypeConfiguration<TagModel>
    {
        public void Configure(EntityTypeBuilder<TagModel> builder)
        {
            builder.ToTable("TAG");

            builder.HasKey(x => x.Id);
            builder.Property(x => x.Id).HasColumnName("ID_TAG");
            builder.Property(x => x.Nome).HasColumnName("NOME_TAG").HasMaxLength(50).IsRequired();

            builder.HasMany(t => t.Departamentos)
                   .WithMany(d => d.Tags)
                   .UsingEntity(j => j.ToTable("TAG_DEPARTAMENTO"));

            builder.HasMany(t => t.Usuarios)
                   .WithMany(u => u.Tags)
                   .UsingEntity(j => j.ToTable("USUARIO_TAG"));

            builder.HasMany(t => t.Produtos)
                   .WithMany(p => p.Tags)
                   .UsingEntity(j => j.ToTable("PRODUTO_TAG"));
        }
    }
}