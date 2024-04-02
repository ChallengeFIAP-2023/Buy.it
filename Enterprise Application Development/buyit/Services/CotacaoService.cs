using Buyit.Context;
using Buyit.Dtos;
using Buyit.Models;
using Microsoft.EntityFrameworkCore;

namespace Buyit.Services;
public class CotacaoService
{
    private readonly BuyitContext _context;

    public CotacaoService(BuyitContext context)
    {
        _context = context;
    }

    public async Task<List<CotacaoDto>> ListAllAsync()
    {
        var list = await _context.Cotacao
            .Include(x => x.Comprador)
            .Include(x => x.Produto)
            .Include(x => x.Status)
            .ToListAsync();

        return list.Select(entity => ConvertToDto(entity)).ToList();
    }

    public async Task<CotacaoDto> FindByIdAsync(long id)
    {
        var entity = await FindEntityByIdAsync(id);
        return ConvertToDto(entity);
    }

    public async Task<CotacaoDto> CreateAsync(CotacaoDto newData)
    {
        var entity = await ConvertToEntity(newData);
        _context.Cotacao.Add(entity);
        await _context.SaveChangesAsync();
        return ConvertToDto(entity);
    }

    public async Task<CotacaoDto> UpdateAsync(long id, CotacaoDto updatedData)
    {
        var entity = await FindEntityByIdAsync(id);
        updatedData.Id = entity.Id;
        var updatedEntity = await ConvertToEntity(updatedData);
        _context.Entry(entity).CurrentValues.SetValues(updatedEntity);
        await _context.SaveChangesAsync();
        return ConvertToDto(entity);
    }

    public async Task DeleteAsync(long id)
    {
        var entity = await FindEntityByIdAsync(id);
        _context.Cotacao.Remove(entity);
        await _context.SaveChangesAsync();
    }

    public async Task<CotacaoModel> FindEntityByIdAsync(long id)
    {
        var entity = await _context.Cotacao
            .Include(x => x.Comprador)
            .Include(x => x.Produto)
            .Include(x => x.Status)
            .FirstOrDefaultAsync(x => x.Id == id);
        if (entity == null)
        {
            throw new KeyNotFoundException($"Objeto não encontrado com o ID: {id}");
        }
        return entity;
    }

    private CotacaoDto ConvertToDto(CotacaoModel entity)
    {
        return new CotacaoDto
        {
            Id = entity.Id,
            DataAbertura = entity.DataAbertura,
            IdComprador = entity.Comprador.Id,
            IdProduto = entity.Produto.Id,
            QuantidadeProduto = entity.QuantidadeProduto,
            ValorProduto = entity.ValorProduto,
            IdStatus = entity.Status.Id,
            PrioridadeEntrega = entity.PrioridadeEntrega,
            PrioridadeQualidade = entity.PrioridadeQualidade,
            PrioridadePreco = entity.PrioridadePreco,
            Prazo = entity.Prazo,
            DataFechamento = entity.DataFechamento
        };
    }

    private async Task<CotacaoModel> ConvertToEntity(CotacaoDto dto)
    {
        var comprador = await _context.Usuario.FindAsync(dto.IdComprador);
        if (comprador == null)
        {
            throw new KeyNotFoundException($"Objeto não encontrado com o ID: {dto.IdComprador}");
        }

        var produto = await _context.Produto.FindAsync(dto.IdProduto);
        if (produto == null)
        {
            throw new KeyNotFoundException($"Objeto não encontrado com o ID: {dto.IdProduto}");
        }

        var status = await _context.Status.FindAsync(dto.IdStatus);
        if (status == null)
        {
            throw new KeyNotFoundException($"Objeto não encontrado com o ID: {dto.IdStatus}");
        }

        return new CotacaoModel
        {
            Id = dto.Id ?? 0,
            DataAbertura = dto.DataAbertura,
            Comprador = comprador,
            Produto = produto,
            QuantidadeProduto = dto.QuantidadeProduto,
            ValorProduto = dto.ValorProduto,
            Status = status,
            PrioridadeEntrega = dto.PrioridadeEntrega,
            PrioridadeQualidade = dto.PrioridadeQualidade,
            PrioridadePreco = dto.PrioridadePreco,
            Prazo = dto.Prazo,
            DataFechamento = dto.DataFechamento
        };
    }
}