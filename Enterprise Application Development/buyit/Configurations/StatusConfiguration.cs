using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using Buyit.Models;

namespace Buyit.Configurations
{
    public class StatusConfiguration : IEntityTypeConfiguration<StatusModel>
    {
        public void Configure(EntityTypeBuilder<StatusModel> builder)
        {
            builder.ToTable("STATUS");

            builder.HasKey(x => x.Id);
            builder.Property(x => x.Id).HasColumnName("ID_STATUS");
            builder.Property(x => x.Nome).HasColumnName("NOME_STATUS").HasMaxLength(50).IsRequired();
        }
    }
}