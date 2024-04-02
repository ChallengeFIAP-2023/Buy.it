using Buyit.Configurations;
using Buyit.Models;
using Microsoft.EntityFrameworkCore;

namespace Buyit.Context
{
    public class BuyitContext : DbContext
    {
        private readonly IConfiguration _configuration;

        public BuyitContext(DbContextOptions<BuyitContext> options, IConfiguration configuration) : base(options)
        {
            _configuration = configuration;
        }

        public DbSet<AvaliacaoModel> Avaliacao { get; set; }
        public DbSet<ContatoModel> Contato { get; set; }
        public DbSet<CotacaoModel> Cotacao { get; set; }
        public DbSet<DepartamentoModel> Departamento { get; set; }
        public DbSet<HistoricoModel> Historico { get; set; }
        public DbSet<ProdutoModel> Produto { get; set; }
        public DbSet<StatusModel> Status { get; set; }
        public DbSet<TagModel> Tag { get; set; }
        public DbSet<UsuarioModel> Usuario { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {
                optionsBuilder.UseOracle(_configuration.GetConnectionString("OracleConnection"));
            }
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            modelBuilder.Entity<AvaliacaoModel>()
                .HasOne(x => x.Cotacao)
                .WithMany()
                .HasForeignKey("ID_COTACAO")
                .IsRequired();

            modelBuilder.Entity<ContatoModel>()
                .HasOne(x => x.Usuario)
                .WithMany()
                .HasForeignKey("ID_USUARIO")
                .IsRequired();

            modelBuilder.Entity<CotacaoModel>()
                .HasOne(x => x.Comprador)
                .WithMany()
                .HasForeignKey("ID_USUARIO")
                .IsRequired();

            modelBuilder.Entity<CotacaoModel>()
                .HasOne(x => x.Produto)
                .WithMany()
                .HasForeignKey("ID_PRODUTO")
                .IsRequired();

            modelBuilder.Entity<CotacaoModel>()
                .HasOne(x => x.Status)
                .WithMany()
                .HasForeignKey("ID_STATUS")
                .IsRequired();

            modelBuilder.Entity<DepartamentoModel>()
                .HasMany(x => x.Tags)
                .WithMany(t => t.Departamentos)
                .UsingEntity<Dictionary<string, object>>(
                    "TagDepartamento",
                    j => j.HasOne<TagModel>().WithMany().HasForeignKey("ID_TAG"),
                    j => j.HasOne<DepartamentoModel>().WithMany().HasForeignKey("ID_DEPARTAMENTO"),
                    j =>
                        {
                            j.ToTable("TAG_DEPARTAMENTO");
                        }
                );

            modelBuilder.Entity<HistoricoModel>()
                .HasOne(x => x.Cotacao)
                .WithMany()
                .HasForeignKey("ID_COTACAO")
                .IsRequired();

            modelBuilder.Entity<HistoricoModel>()
                .HasOne(x => x.Fornecedor)
                .WithMany()
                .HasForeignKey("ID_FORNECEDOR")
                .IsRequired();

            modelBuilder.Entity<HistoricoModel>()
                .HasOne(x => x.Status)
                .WithMany()
                .HasForeignKey("ID_STATUS")
                .IsRequired();

            modelBuilder.Entity<ProdutoModel>()
                .HasOne(x => x.Departamento)
                .WithMany()
                .HasForeignKey("ID_DEPARTAMENTO");

            modelBuilder.Entity<ProdutoModel>()
                .HasMany(x => x.Tags)
                .WithMany(t => t.Produtos)
                .UsingEntity<Dictionary<string, object>>(
                    "ProdutoTag",
                    j => j.HasOne<TagModel>().WithMany().HasForeignKey("ID_TAG"),
                    j => j.HasOne<ProdutoModel>().WithMany().HasForeignKey("ID_PRODUTO"),
                    j =>
                    {
                        j.ToTable("PRODUTO_TAG");
                    }
                );

            modelBuilder.Entity<UsuarioModel>()
                .HasMany(x => x.Tags)
                .WithMany(t => t.Usuarios)
                .UsingEntity<Dictionary<string, object>>(
                    "UsuarioTag",
                    j => j.HasOne<TagModel>().WithMany().HasForeignKey("ID_TAG"),
                    j => j.HasOne<UsuarioModel>().WithMany().HasForeignKey("ID_USUARIO"),
                    j =>
                    {
                        j.ToTable("USUARIO_TAG");
                    }
                );

            modelBuilder.ApplyConfiguration(new AvaliacaoConfiguration());
            modelBuilder.ApplyConfiguration(new ContatoConfiguration());
            modelBuilder.ApplyConfiguration(new CotacaoConfiguration());
            modelBuilder.ApplyConfiguration(new DepartamentoConfiguration());
            modelBuilder.ApplyConfiguration(new HistoricoConfiguration());
            modelBuilder.ApplyConfiguration(new ProdutoConfiguration());
            modelBuilder.ApplyConfiguration(new StatusConfiguration());
            modelBuilder.ApplyConfiguration(new TagConfiguration());
            modelBuilder.ApplyConfiguration(new UsuarioConfiguration());
        }
    }
}