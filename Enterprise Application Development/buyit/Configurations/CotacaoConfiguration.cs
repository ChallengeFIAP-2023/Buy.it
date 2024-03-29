using Microsoft.EntityFrameworkCore.Metadata.Builders;
using Microsoft.EntityFrameworkCore;
using Buyit.Models;

namespace Buyit.Configurations
{
    public class CotacaoConfiguration : IEntityTypeConfiguration<CotacaoModel>
    {
        public void Configure(EntityTypeBuilder<CotacaoModel> builder)
        {
            builder.ToTable("COTACAO");

            builder.HasKey(x => x.Id);
            builder.Property(x => x.Id).HasColumnName("ID_COTACAO");
            builder.Property(x => x.DataAbertura).HasColumnName("DATA_ABERTURA_COTACAO");
            builder.Property(x => x.QuantidadeProduto).HasColumnName("QUANTIDADE_PRODUTO");
            builder.Property(x => x.ValorProduto).HasColumnName("VALOR_PRODUTO");
            builder.Property(x => x.PrioridadeEntrega).HasColumnName("PRIORIDADE_ENTREGA");
            builder.Property(x => x.PrioridadeQualidade).HasColumnName("PRIORIDADE_QUALIDADE");
            builder.Property(x => x.PrioridadePreco).HasColumnName("PRIORIDADE_PRECO");
            builder.Property(x => x.Prazo).HasColumnName("PRAZO_COTACAO");
            builder.Property(x => x.DataFechamento).HasColumnName("DATA_FECHAMENTO_COTACAO");

            builder.HasOne(x => x.Comprador)
                .WithMany()
                .HasForeignKey(x => x.Comprador)
                .HasPrincipalKey(x => x.Id);

            builder.HasOne(x => x.Produto)
                .WithMany()
                .HasForeignKey(x => x.Produto)
                .HasPrincipalKey(x => x.Id);

            builder.HasOne(x => x.Status)
                .WithMany()
                .HasForeignKey(x => x.Status)
                .HasPrincipalKey(x => x.Id);
        }
    }
}