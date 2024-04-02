using Buyit.Context;
using Buyit.Dtos;
using Buyit.Models;
using Microsoft.EntityFrameworkCore;

namespace Buyit.Services;
public class AvaliacaoService
{
    private readonly BuyitContext _context;

    public AvaliacaoService(BuyitContext context)
    {
        _context = context;
    }

    public async Task<List<AvaliacaoDto>> ListAllAsync()
    {
        var list = await _context.Avaliacao
            .Include(x => x.Cotacao)
            .ToListAsync();

        return list.Select(entity => ConvertToDto(entity)).ToList();
    }

    public async Task<AvaliacaoDto> FindByIdAsync(long id)
    {
        var entity = await FindEntityByIdAsync(id);
        return ConvertToDto(entity);
    }

    public async Task<AvaliacaoDto> CreateAsync(AvaliacaoDto newData)
    {
        var entity = await ConvertToEntity(newData);
        _context.Avaliacao.Add(entity);
        await _context.SaveChangesAsync();
        return ConvertToDto(entity);
    }

    public async Task<AvaliacaoDto> UpdateAsync(long id, AvaliacaoDto updatedData)
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
        _context.Avaliacao.Remove(entity);
        await _context.SaveChangesAsync();
    }

    public async Task<AvaliacaoModel> FindEntityByIdAsync(long id)
    {
        var entity = await _context.Avaliacao
            .Include(x => x.Cotacao)
            .FirstOrDefaultAsync(x => x.Id == id);
        if (entity == null)
        {
            throw new KeyNotFoundException($"Objeto não encontrado com o ID: {id}");
        }
        return entity;
    }

    public async Task<List<AvaliacaoDto>> FindByCotacaoIdAsync(long id)
    {
        var list = await _context.Avaliacao
            .Where(x => x.Cotacao.Id == id)
            .Include(x => x.Cotacao)
            .ToListAsync();

        return list.Select(x => ConvertToDto(x)).ToList();
    }

    public async Task<List<AvaliacaoDto>> FindByUsuarioIdAsync(long id)
    {
        var list = await _context.Avaliacao
            .Where(x => x.Cotacao.Comprador.Id == id)
            .Include(x => x.Cotacao)
            .ToListAsync();

        return list.Select(x => ConvertToDto(x)).ToList();
    }

    private AvaliacaoDto ConvertToDto(AvaliacaoModel entity)
    {
        return new AvaliacaoDto
        {
            Id = entity.Id,
            IdCotacao = entity.Cotacao.Id,
            Data = entity.Data,
            NotaEntrega = entity.NotaEntrega,
            NotaQualidade = entity.NotaQualidade,
            NotaPreco = entity.NotaPreco,
            Descricao = entity.Descricao
        };
    }

    private async Task<AvaliacaoModel> ConvertToEntity(AvaliacaoDto dto)
    {
        var cotacao = await _context.Cotacao.FindAsync(dto.IdCotacao);
        if (cotacao == null)
        {
            throw new KeyNotFoundException($"Objeto não encontrado com o ID: {dto.IdCotacao}");
        }

        return new AvaliacaoModel
        {
            Id = dto.Id ?? 0,
            Cotacao = cotacao,
            Data = dto.Data,
            NotaEntrega = dto.NotaEntrega,
            NotaQualidade = dto.NotaQualidade,
            NotaPreco = dto.NotaPreco,
            Descricao = dto.Descricao
        };
    }
}