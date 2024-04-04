using Buyit.Context;
using Buyit.Dtos;
using Buyit.Models;
using Buyit.Repositories;
using Microsoft.EntityFrameworkCore;

namespace Buyit.Services;
public class StatusService
{
    private readonly BuyitContext _context;

    public StatusService(BuyitContext context)
    {
        _context = context;
    }

    public async Task<List<StatusDto>> FindAll()
    {
        var list = await _context.Status
            .ToListAsync();

        return list.Select(entity => ConvertToDto(entity)).ToList();
    }

    public async Task<StatusDto> FindById(long id)
    {
        var entity = await FindEntityById(id);
        return ConvertToDto(entity);
    }

    public async Task<StatusDto> Create(StatusDto newData)
    {
        var entity = await ConvertToEntity(newData);
        _context.Status.Add(entity);
        await _context.SaveChangesAsync();
        return ConvertToDto(entity);
    }

    public async Task<StatusDto> Update(long id, StatusDto updatedData)
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
        _context.Status.Remove(entity);
        await _context.SaveChangesAsync();
    }

    public async Task<StatusModel> FindEntityById(long id)
    {
        var entity = await _context.Status
            .FirstOrDefaultAsync(x => x.Id == id);
        if (entity == null)
        {
            throw new KeyNotFoundException($"Objeto não encontrado com o ID: {id}");
        }
        return entity;
    }

    private StatusDto ConvertToDto(StatusModel entity)
    {
        return new StatusDto
        {
            Id = entity.Id,
            Nome = entity.Nome,
        };
    }

    private async Task<StatusModel> ConvertToEntity(StatusDto dto)
    {
        return new StatusModel
        {
            Id = dto.Id ?? 0,
            Nome = dto.Nome
        };
    }
}