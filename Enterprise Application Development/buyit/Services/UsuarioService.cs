using Buyit.Context;
using Buyit.Dtos;
using Buyit.Models;
using Microsoft.EntityFrameworkCore;

namespace Buyit.Services;
public class UsuarioService
{
    private readonly BuyitContext _context;

    public UsuarioService(BuyitContext context)
    {
        _context = context;
    }

    public async Task<List<UsuarioDto>> ListAllAsync()
    {
        var list = await _context.Usuario
            .Include(x => x.Tags)
            .ToListAsync();

        return list.Select(entity => ConvertToDto(entity)).ToList();
    }

    public async Task<UsuarioDto> FindByIdAsync(long id)
    {
        var entity = await FindEntityByIdAsync(id);
        return ConvertToDto(entity);
    }

    public async Task<UsuarioDto> CreateAsync(UsuarioDto newData)
    {
        var entity = await ConvertToEntity(newData);
        _context.Usuario.Add(entity);
        await _context.SaveChangesAsync();
        return ConvertToDto(entity);
    }

    public async Task<UsuarioDto> UpdateAsync(long id, UsuarioDto updatedData)
    {
        var entity = await _context.Usuario
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
        _context.Usuario.Remove(entity);
        await _context.SaveChangesAsync();
    }

    public async Task<UsuarioModel> FindEntityByIdAsync(long id)
    {
        var entity = await _context.Usuario
            .Include(x => x.Tags)
            .FirstOrDefaultAsync(x => x.Id == id);
        if (entity == null)
        {
            throw new KeyNotFoundException($"Objeto não encontrado com o ID: {id}");
        }
        return entity;
    }

    private UsuarioDto ConvertToDto(UsuarioModel entity)
    {
        return new UsuarioDto
        {
            Id = entity.Id,
            Nome = entity.Nome,
            Email = entity.Email,
            Senha = entity.Senha,
            UrlImagem = entity.UrlImagem,
            Cnpj = entity.Cnpj,
            IsFornecedor = entity.IsFornecedor,
            IdsTags = entity.Tags.Select(t => t.Id).ToList()
        };
    }

    private async Task<UsuarioModel> ConvertToEntity(UsuarioDto dto)
    {
        var tags = new List<TagModel>();
        if (dto.IdsTags != null && dto.IdsTags.Any())
        {
            tags = await _context.Tag.Where(tag => dto.IdsTags.Contains(tag.Id)).ToListAsync();
        }

        return new UsuarioModel
        {
            Id = dto.Id ?? 0,
            Nome = dto.Nome,
            Email = dto.Email,
            Senha = dto.Senha,
            UrlImagem = dto.UrlImagem,
            Cnpj = dto.Cnpj,
            IsFornecedor = dto.IsFornecedor,
            Tags = tags
        };
    }
}