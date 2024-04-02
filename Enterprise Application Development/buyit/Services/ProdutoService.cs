using Buyit.Context;
using Buyit.Dtos;
using Buyit.Models;
using Microsoft.EntityFrameworkCore;
using System.Data;

namespace Buyit.Services;
public class ProdutoService
{
    private readonly BuyitContext _context;

    public ProdutoService(BuyitContext context)
    {
        _context = context;
    }

    public async Task<List<ProdutoDto>> ListAllAsync()
    {
        var list = await _context.Produto
            .Include(x => x.Departamento)
            .Include(x => x.Tags)
            .ToListAsync();

        return list.Select(entity => ConvertToDto(entity)).ToList();
    }

    public async Task<ProdutoDto> FindByIdAsync(long id)
    {
        var entity = await FindEntityByIdAsync(id);
        return ConvertToDto(entity);
    }

    public async Task<ProdutoDto> CreateAsync(ProdutoDto newData)
    {
        var entity = await ConvertToEntity(newData);
        _context.Produto.Add(entity);
        await _context.SaveChangesAsync();
        return ConvertToDto(entity);
    }

    public async Task<ProdutoDto> UpdateAsync(long id, ProdutoDto updatedData)
    {
        var entity = await _context.Produto
            .Include(x => x.Departamento)
            .Include(x => x.Tags)
            .FirstOrDefaultAsync(x => x.Id == id);

        if (entity == null)
        {
            throw new KeyNotFoundException($"Objeto não encontrado com o ID: {id}.");
        }

        entity.Tags.Clear();

        if (updatedData.IdsTags != null)
        {
            var newTags = await _context.Tag.Where(t => updatedData.IdsTags.Contains(t.Id)).ToListAsync();
            foreach (var tag in newTags)
            {
                entity.Tags.Add(tag);
            }
        }

        updatedData.Id = entity.Id;
        var updatedEntity = await ConvertToEntity(updatedData);
        _context.Entry(entity).CurrentValues.SetValues(updatedEntity);
        await _context.SaveChangesAsync();
        return ConvertToDto(entity);
    }

    public async Task DeleteAsync(long id)
    {
        var entity = await FindEntityByIdAsync(id);
        _context.Produto.Remove(entity);
        await _context.SaveChangesAsync();
    }

    public async Task<ProdutoModel> FindEntityByIdAsync(long id)
    {
        var entity = await _context.Produto
            .Include(x => x.Departamento)
            .Include(x => x.Tags)
            .FirstOrDefaultAsync(x => x.Id == id);
        if (entity == null)
        {
            throw new KeyNotFoundException($"Objeto não encontrado com o ID: {id}");
        }
        return entity;
    }

    private ProdutoDto ConvertToDto(ProdutoModel entity)
    {
        return new ProdutoDto
        {
            Id = entity.Id,
            Nome = entity.Nome,
            Marca = entity.Marca,
            Cor = entity.Cor,
            Tamanho = entity.Tamanho,
            Material = entity.Material,
            Observacao = entity.Observacao,
            IdDepartamento = entity.Departamento.Id,
            IdsTags = entity.Tags.Select(t => t.Id).ToList()
        };
    }

    private async Task<ProdutoModel> ConvertToEntity(ProdutoDto dto)
    {
        var departamento = await _context.Departamento.FindAsync(dto.IdDepartamento);
        if (departamento == null)
        {
            throw new KeyNotFoundException($"Objeto não encontrado com o ID: {dto.IdDepartamento}");
        }

        var tags = new List<TagModel>();
        if (dto.IdsTags != null && dto.IdsTags.Any())
        {
            tags = await _context.Tag.Where(tag => dto.IdsTags.Contains(tag.Id)).ToListAsync();
        }

        return new ProdutoModel
        {
            Id = dto.Id ?? 0,
            Nome = dto.Nome,
            Marca = dto.Marca,
            Cor = dto.Cor,
            Tamanho = dto.Tamanho,
            Material = dto.Material,
            Observacao = dto.Observacao,
            Departamento = departamento,
            Tags = tags
        };
    }
}