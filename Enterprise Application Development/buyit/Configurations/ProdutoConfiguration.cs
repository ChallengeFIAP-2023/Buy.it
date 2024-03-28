﻿using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using Buyit.Models;

namespace Buyit.Configurations
{
    public class ProdutoConfiguration : IEntityTypeConfiguration<ProdutoModel>
    {
        public void Configure(EntityTypeBuilder<ProdutoModel> builder)
        {
            builder.ToTable("PRODUTO");

            builder.HasKey(x => x.Id);
            builder.Property(x => x.Id).HasColumnName("ID_PRODUTO");
            builder.Property(x => x.Nome).HasColumnName("NOME_PRODUTO").HasMaxLength(100).IsRequired();
            builder.Property(x => x.Marca).HasColumnName("MARCA_PRODUTO").HasMaxLength(100);
            builder.Property(x => x.Cor).HasColumnName("COR_PRODUTO").HasMaxLength(50);
            builder.Property(x => x.Tamanho).HasColumnName("TAMANHO_PRODUTO").HasMaxLength(50);
            builder.Property(x => x.Material).HasColumnName("MATERIAL_PRODUTO").HasMaxLength(50);
            builder.Property(x => x.Observacao).HasColumnName("OBSERVACAO_PRODUTO").HasMaxLength(255);
            builder.Property(x => x.DepartamentoId).HasColumnName("ID_DEPARTAMENTO");

            builder.HasOne(x => x.Departamento)
                .WithMany()
                .HasForeignKey(x => x.DepartamentoId)
                .IsRequired(false);

            builder.HasMany(p => p.Tags)
                .WithMany(t => t.Produtos)
                .UsingEntity(j => j.ToTable("PRODUTO_TAG"));
        }
    }
}