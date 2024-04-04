using Buyit.Context;
using Buyit.Dtos;
using Buyit.Models;
using Microsoft.EntityFrameworkCore;

namespace Buyit.Services;
public class DepartamentoService
{
    private readonly BuyitContext _context;

    public DepartamentoService(BuyitContext context)
    {
        _context = context;
    }

    public async Task<List<DepartamentoDto>> FindAll()
    {
        var list = await _context.Departamento
            .Include(x => x.Tags)
            .ToListAsync();

        return list.Select(entity => ConvertToDto(entity)).ToList();
    }

    public async Task<DepartamentoDto> FindById(long id)
    {
        var entity = await FindEntityById(id);
        return ConvertToDto(entity);
    }

    public async Task<DepartamentoDto> Create(DepartamentoDto newData)
    {
        var entity = await ConvertToEntity(newData);
        _context.Departamento.Add(entity);
        await _context.SaveChangesAsync();
        return ConvertToDto(entity);
    }

    public async Task<DepartamentoDto> Update(long id, DepartamentoDto updatedData)
    {
        var entity = await _context.Departamento
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

    public async Task Delete(long id)
    {
        var entity = await FindEntityById(id);
        _context.Departamento.Remove(entity);
        await _context.SaveChangesAsync();
    }

    public async Task<DepartamentoModel> FindEntityById(long id)
    {
        var entity = await _context.Departamento
            .Include(x => x.Tags)
            .FirstOrDefaultAsync(x => x.Id == id);
        if (entity == null)
        {
            throw new KeyNotFoundException($"Objeto não encontrado com o ID: {id}");
        }
        return entity;
    }

    public async Task<List<DepartamentoDto>> FindByTagId(long id)
    {
        var list = await _context.Departamento
            .Where(d => d.Tags.Any(t => t.Id == id))
            .Include(x => x.Tags)
            .ToListAsync();

        return list.Select(x => ConvertToDto(x)).ToList();
    }

    private DepartamentoDto ConvertToDto(DepartamentoModel entity)
    {
        return new DepartamentoDto
        {
            Id = entity.Id,
            Nome = entity.Nome,
            Icone = entity.Icone,
            IdsTags = entity.Tags.Select(t => t.Id).ToList()
        };
    }

    private async Task<DepartamentoModel> ConvertToEntity(DepartamentoDto dto)
    {
        var tags = new List<TagModel>();
        if (dto.IdsTags != null && dto.IdsTags.Any())
        {
            tags = await _context.Tag.Where(tag => dto.IdsTags.Contains(tag.Id)).ToListAsync();
        }

        return new DepartamentoModel
        {
            Id = dto.Id ?? 0,
            Nome = dto.Nome,
            Icone = dto.Icone,
            Tags = tags
        };
    }
}