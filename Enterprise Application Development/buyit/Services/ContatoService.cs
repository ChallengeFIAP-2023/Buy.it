﻿using Buyit.Context;
using Buyit.Dtos;
using Buyit.Models;
using Microsoft.EntityFrameworkCore;

namespace Buyit.Services;
public class ContatoService
{
    private readonly BuyitContext _context;

    public ContatoService(BuyitContext context)
    {
        _context = context;
    }

    public async Task<List<ContatoDto>> FindAll()
    {
        var list = await _context.Contato
            .Include(x => x.Usuario)
            .ToListAsync();

        return list.Select(entity => ConvertToDto(entity)).ToList();
    }

    public async Task<ContatoDto> FindById(long id)
    {
        var entity = await FindEntityById(id);
        return ConvertToDto(entity);
    }

    public async Task<ContatoDto> Create(ContatoDto newData)
    {
        var entity = await ConvertToEntity(newData);
        _context.Contato.Add(entity);
        await _context.SaveChangesAsync();
        return ConvertToDto(entity);
    }

    public async Task<ContatoDto> Update(long id, ContatoDto updatedData)
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
        _context.Contato.Remove(entity);
        await _context.SaveChangesAsync();
    }

    public async Task<ContatoModel> FindEntityById(long id)
    {
        var entity = await _context.Contato
            .Include(x => x.Usuario)
            .FirstOrDefaultAsync(x => x.Id == id);
        if (entity == null)
        {
            throw new KeyNotFoundException($"Objeto não encontrado com o ID: {id}");
        }
        return entity;
    }

    public async Task<List<ContatoDto>> FindByUsuarioId(long id)
    {
        var list = await _context.Contato
            .Where(x => x.Usuario.Id == id)
            .Include(x => x.Usuario)
            .ToListAsync();

        return list.Select(x => ConvertToDto(x)).ToList();
    }

    private ContatoDto ConvertToDto(ContatoModel entity)
    {
        return new ContatoDto
        {
            Id = entity.Id,
            Tipo = entity.Tipo,
            Valor = entity.Valor,
            IdUsuario = entity.Usuario.Id
        };
    }

    private async Task<ContatoModel> ConvertToEntity(ContatoDto dto)
    {
        var usuario = await _context.Usuario.FindAsync(dto.IdUsuario);
        if (usuario == null)
        {
            throw new KeyNotFoundException($"Objeto não encontrado com o ID: {dto.IdUsuario}");
        }

        return new ContatoModel
        {
            Id = dto.Id ?? 0,
            Tipo = dto.Tipo,
            Valor = dto.Valor,
            Usuario = usuario
        };
    }
}