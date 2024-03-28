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
            builder.Property(x => x.Id).HasColumnName("ID_HISTORICO");
            builder.Property(x => x.Data).HasColumnName("DATA_HISTORICO");
            builder.Property(x => x.ValorOfertado).HasColumnName("VALOR_OFERTADO_HISTORICO");
            builder.Property(x => x.RecusadoPorProduto).HasColumnName("RECUSADO_POR_PRODUTO");
            builder.Property(x => x.RecusadoPorQuantidade).HasColumnName("RECUSADO_POR_QUANTIDADE");
            builder.Property(x => x.RecusadoPorPreco).HasColumnName("RECUSADO_POR_PRECO");
            builder.Property(x => x.RecusadoPorPrazo).HasColumnName("RECUSADO_POR_PRAZO");
            builder.Property(x => x.Descricao).HasColumnName("DESCRICAO_HISTORICO").HasMaxLength(255);

            builder.HasOne(x => x.Cotacao)
                .WithMany()
                .HasForeignKey(x => x.Cotacao)
                .IsRequired();

            builder.HasOne(x => x.Fornecedor)
                .WithMany()
                .HasForeignKey(x => x.Fornecedor)
                .IsRequired();

            builder.HasOne(x => x.Status)
                .WithMany()
                .HasForeignKey(x => x.Status)
                .IsRequired();
        }
    }
}