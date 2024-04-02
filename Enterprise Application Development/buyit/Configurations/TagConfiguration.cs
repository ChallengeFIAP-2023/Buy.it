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
            builder.Property(x => x.Id).HasColumnName("ID_TAG").UseHiLo("SEQ_TAG");
            builder.Property(x => x.Nome).HasColumnName("NOME_TAG").HasMaxLength(50).IsRequired();
        }
    }
}