using Microsoft.EntityFrameworkCore.Metadata.Builders;
using Microsoft.EntityFrameworkCore;
using Buyit.Models;

namespace Buyit.Configurations
{
    public class AvaliacaoConfiguration : IEntityTypeConfiguration<AvaliacaoModel>
    {
        public void Configure(EntityTypeBuilder<AvaliacaoModel> builder)
        {
            builder.ToTable("AVALIACAO");

            builder.HasKey(x => x.Id);
            builder.Property(x => x.Id).HasColumnName("ID_AVALIACAO").UseHiLo("SEQ_AVALIACAO");
            builder.Property(x => x.Data).HasColumnName("DATA_AVALIACAO").IsRequired();
            builder.Property(x => x.NotaEntrega).HasColumnName("NOTA_ENTREGA_AVALIACAO").IsRequired();
            builder.Property(x => x.NotaQualidade).HasColumnName("NOTA_QUALIDADE_AVALIACAO").IsRequired();
            builder.Property(x => x.NotaPreco).HasColumnName("NOTA_PRECO_AVALIACAO").IsRequired();
            builder.Property(x => x.Descricao).HasColumnName("DESCRICAO_AVALIACAO").HasColumnType("VARCHAR(255)");
        }
    }
}