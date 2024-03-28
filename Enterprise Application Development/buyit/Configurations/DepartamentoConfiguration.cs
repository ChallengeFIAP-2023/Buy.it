using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using Buyit.Models;

namespace Buyit.Configurations
{
    public class DepartamentoConfiguration : IEntityTypeConfiguration<DepartamentoModel>
    {
        public void Configure(EntityTypeBuilder<DepartamentoModel> builder)
        {
            builder.ToTable("DEPARTAMENTO");

            builder.HasKey(x => x.Id);
            builder.Property(x => x.Id).HasColumnName("ID_DEPARTAMENTO");
            builder.Property(x => x.Nome).HasColumnName("NOME_DEPARTAMENTO").HasMaxLength(255);
            builder.Property(x => x.Icone).HasColumnName("ICONE_DEPARTAMENTO").HasMaxLength(255);

            builder
                .HasMany(d => d.Tags)
                .WithMany(t => t.Departamentos)
                .UsingEntity(j => j.ToTable("TAG_DEPARTAMENTO"));
        }
    }
}