using Buyit.Context;
using Buyit.Dtos;
using Buyit.Models;
using Microsoft.EntityFrameworkCore;

namespace Buyit.Services;
public class TagService
{
    private readonly BuyitContext _context;

    public TagService(BuyitContext context)
    {
        _context = context;
    }

    public async Task<List<TagDto>> ListAllAsync()
    {
        var list = await _context.Tag
            .Include(x => x.Departamentos)
            .Include(x => x.Produtos)
            .Include(x => x.Usuarios)
            .ToListAsync();

        return list.Select(entity => ConvertToDto(entity)).ToList();
    }

    public async Task<TagDto> FindByIdAsync(long id)
    {
        var entity = await FindEntityByIdAsync(id);
        return ConvertToDto(entity);
    }

    public async Task<TagDto> CreateAsync(TagDto newData)
    {
        var entity = await ConvertToEntity(newData);
        _context.Tag.Add(entity);
        await _context.SaveChangesAsync();
        return ConvertToDto(entity);
    }

    public async Task<TagDto> UpdateAsync(long id, TagDto updatedData)
    {
        var entity = await _context.Tag
            .Include(x => x.Departamentos)
            .Include(x => x.Produtos)
            .Include(x => x.Usuarios)
            .FirstOrDefaultAsync(x => x.Id == id);

        if (entity == null)
        {
            throw new KeyNotFoundException($"Objeto não encontrado com o ID: {id}.");
        }

        entity.Departamentos.Clear();
        entity.Produtos.Clear();
        entity.Usuarios.Clear();

        if (updatedData.IdsDepartamentos != null)
        {
            var newDepartamentos = await _context.Departamento.Where(d => updatedData.IdsDepartamentos.Contains(d.Id)).ToListAsync();
            foreach (var departamento in newDepartamentos)
            {
                entity.Departamentos.Add(departamento);
            }
        }

        if (updatedData.IdsProdutos != null)
        {
            var newProdutos = await _context.Produto.Where(p => updatedData.IdsProdutos.Contains(p.Id)).ToListAsync();
            foreach (var produto in newProdutos)
            {
                entity.Produtos.Add(produto);
            }
        }

        if (updatedData.IdsUsuarios != null)
        {
            var newUsuarios = await _context.Usuario.Where(u => updatedData.IdsUsuarios.Contains(u.Id)).ToListAsync();
            foreach (var usuario in newUsuarios)
            {
                entity.Usuarios.Add(usuario);
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
        _context.Tag.Remove(entity);
        await _context.SaveChangesAsync();
    }

    public async Task<TagModel> FindEntityByIdAsync(long id)
    {
        var entity = await _context.Tag
            .Include(x => x.Departamentos)
            .Include(x => x.Produtos)
            .Include(x => x.Usuarios)
            .FirstOrDefaultAsync(x => x.Id == id);
        if (entity == null)
        {
            throw new KeyNotFoundException($"Objeto não encontrado com o ID: {id}");
        }
        return entity;
    }

    private TagDto ConvertToDto(TagModel entity)
    {
        return new TagDto
        {
            Id = entity.Id,
            Nome = entity.Nome,
            IdsDepartamentos = entity.Departamentos.Select(d => d.Id).ToList(),
            IdsProdutos = entity.Produtos.Select(p => p.Id).ToList(),
            IdsUsuarios = entity.Usuarios.Select(u => u.Id).ToList()
        };
    }

    private async Task<TagModel> ConvertToEntity(TagDto dto)
    {
        var departamentos = new List<DepartamentoModel>();
        if (dto.IdsDepartamentos != null && dto.IdsDepartamentos.Any())
        {
            departamentos = await _context.Departamento.Where(departamento => dto.IdsDepartamentos.Contains(departamento.Id)).ToListAsync();
        }

        var produtos = new List<ProdutoModel>();
        if (dto.IdsProdutos != null && dto.IdsProdutos.Any())
        {
            produtos = await _context.Produto.Where(produto => dto.IdsProdutos.Contains(produto.Id)).ToListAsync();
        }

        var usuarios = new List<UsuarioModel>();
        if (dto.IdsUsuarios != null && dto.IdsUsuarios.Any())
        {
            usuarios = await _context.Usuario.Where(usuario => dto.IdsUsuarios.Contains(usuario.Id)).ToListAsync();
        }

        return new TagModel
        {
            Id = dto.Id ?? 0,
            Nome = dto.Nome,
            Departamentos = departamentos,
            Produtos = produtos,
            Usuarios = usuarios
        };
    }
}