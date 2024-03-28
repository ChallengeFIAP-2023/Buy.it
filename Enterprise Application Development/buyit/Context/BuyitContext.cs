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
        public DbSet<UsuarioModel> USUARIO { get; set; }

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
        }
    }
}