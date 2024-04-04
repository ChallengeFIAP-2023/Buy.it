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

    public async Task<List<AvaliacaoDto>> FindAll()
    {
        var list = await _context.Avaliacao
            .Include(x => x.Cotacao)
            .ToListAsync();

        return list.Select(entity => ConvertToDto(entity)).ToList();
    }

    public async Task<AvaliacaoDto> FindById(long id)
    {
        var entity = await FindEntityById(id);
        return ConvertToDto(entity);
    }

    public async Task<AvaliacaoDto> Create(AvaliacaoDto newData)
    {
        var entity = await ConvertToEntity(newData);
        _context.Avaliacao.Add(entity);
        await _context.SaveChangesAsync();
        return ConvertToDto(entity);
    }

    public async Task<AvaliacaoDto> Update(long id, AvaliacaoDto updatedData)
    {
        var entity = await FindEntityById(id);
        updatedData.Id = entity.Id;
        var updatedEntity = await ConvertToEntity(updatedData);
        _context.Entry(entity).CurrentValues.SetValues(updatedEntity);
        await _context.SaveChangesAsync();
        return ConvertToDto(entity);
    }

    public async Task Delete(long id)
    {
        var entity = await FindEntityById(id);
        _context.Avaliacao.Remove(entity);
        await _context.SaveChangesAsync();
    }

    public async Task<AvaliacaoModel> FindEntityById(long id)
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

    public async Task<List<AvaliacaoDto>> FindByCotacaoId(long id)
    {
        var list = await _context.Avaliacao
            .Where(x => x.Cotacao.Id == id)
            .Include(x => x.Cotacao)
            .ToListAsync();

        return list.Select(x => ConvertToDto(x)).ToList();
    }

    public async Task<List<AvaliacaoDto>> FindByUsuarioId(long id)
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