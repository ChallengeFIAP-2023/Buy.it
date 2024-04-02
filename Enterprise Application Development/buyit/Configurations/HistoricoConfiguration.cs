using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using Buyit.Models;

namespace Buyit.Configurations
{
    public class HistoricoConfiguration : IEntityTypeConfiguration<HistoricoModel>
    {
        public void Configure(EntityTypeBuilder<HistoricoModel> builder)
        {
            builder.ToTable("HISTORICO");

            builder.HasKey(x => x.Id);
            builder.Property(x => x.Id).HasColumnName("ID_HISTORICO").UseHiLo("SEQ_HISTORICO");
            builder.Property(x => x.Data).HasColumnName("DATA_HISTORICO").IsRequired();
            builder.Property(x => x.ValorOfertado).HasColumnName("VALOR_OFERTADO_HISTORICO");
            builder.Property(x => x.RecusadoPorProduto).HasColumnName("RECUSADO_POR_PRODUTO").IsRequired();
            builder.Property(x => x.RecusadoPorQuantidade).HasColumnName("RECUSADO_POR_QUANTIDADE").IsRequired();
            builder.Property(x => x.RecusadoPorPreco).HasColumnName("RECUSADO_POR_PRECO").IsRequired();
            builder.Property(x => x.RecusadoPorPrazo).HasColumnName("RECUSADO_POR_PRAZO").IsRequired();
            builder.Property(x => x.Descricao).HasColumnName("DESCRICAO_HISTORICO").HasMaxLength(255);
        }
    }
}