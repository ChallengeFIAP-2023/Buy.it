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
            builder.Property(x => x.Id).HasColumnName("ID_DEPARTAMENTO").UseHiLo("SEQ_DEPARTAMENTO");
            builder.Property(x => x.Nome).HasColumnName("NOME_DEPARTAMENTO").HasMaxLength(255).IsRequired();
            builder.Property(x => x.Icone).HasColumnName("ICONE_DEPARTAMENTO").HasMaxLength(255);
        }
    }
}