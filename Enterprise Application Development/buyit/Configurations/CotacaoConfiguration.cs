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
            builder.Property(x => x.Id).HasColumnName("ID_COTACAO").UseHiLo("SEQ_COTACAO");
            builder.Property(x => x.DataAbertura).HasColumnName("DATA_ABERTURA_COTACAO").IsRequired();
            builder.Property(x => x.QuantidadeProduto).HasColumnName("QUANTIDADE_PRODUTO").IsRequired();
            builder.Property(x => x.ValorProduto).HasColumnName("VALOR_PRODUTO").IsRequired();
            builder.Property(x => x.PrioridadeEntrega).HasColumnName("PRIORIDADE_ENTREGA").IsRequired();
            builder.Property(x => x.PrioridadeQualidade).HasColumnName("PRIORIDADE_QUALIDADE").IsRequired();
            builder.Property(x => x.PrioridadePreco).HasColumnName("PRIORIDADE_PRECO").IsRequired();
            builder.Property(x => x.Prazo).HasColumnName("PRAZO_COTACAO").IsRequired();
            builder.Property(x => x.DataFechamento).HasColumnName("DATA_FECHAMENTO_COTACAO");
        }
    }
}