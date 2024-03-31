using Buyit.Context;
using Buyit.Models;
using Buyit.Repositories;
using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);
var configuration = builder.Configuration;

builder.Services.AddControllers();
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

builder.Services.AddDbContext<BuyitContext>(options => options.UseOracle(configuration.GetConnectionString("OracleConnection")));
builder.Services.AddScoped<Repository<AvaliacaoModel>>();
builder.Services.AddScoped<Repository<ContatoModel>>();
builder.Services.AddScoped<Repository<CotacaoModel>>();
builder.Services.AddScoped<Repository<DepartamentoModel>>();
builder.Services.AddScoped<Repository<HistoricoModel>>();
builder.Services.AddScoped<Repository<ProdutoModel>>();
builder.Services.AddScoped<Repository<StatusModel>>();
builder.Services.AddScoped<Repository<TagModel>>();
builder.Services.AddScoped<Repository<UsuarioModel>>();

var app = builder.Build();

app.UseSwagger();
app.UseSwaggerUI();

//app.UseHttpsRedirection();
//app.UseAuthorization();
app.MapControllers();

app.Run();