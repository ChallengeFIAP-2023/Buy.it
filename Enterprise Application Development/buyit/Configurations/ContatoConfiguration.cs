using Microsoft.EntityFrameworkCore.Metadata.Builders;
using Microsoft.EntityFrameworkCore;
using Buyit.Models;

namespace Buyit.Configurations
{
    public class ContatoConfiguration : IEntityTypeConfiguration<ContatoModel>
    {
        public void Configure(EntityTypeBuilder<ContatoModel> builder)
        {
            builder.ToTable("CONTATO");

            builder.HasKey(x => x.Id);
            builder.Property(x => x.Id).HasColumnName("ID_CONTATO");
            builder.Property(x => x.Tipo).HasColumnName("TIPO_CONTATO").HasColumnType("VARCHAR(50)").IsRequired();
            builder.Property(x => x.Valor).HasColumnName("VALOR_CONTATO").HasColumnType("VARCHAR(255)").IsRequired();

            builder.HasOne(x => x.Usuario)
                .WithMany()
                .HasForeignKey(x => x.Usuario)
                .HasPrincipalKey(x => x.Id)
                .IsRequired();
        }
    }
}