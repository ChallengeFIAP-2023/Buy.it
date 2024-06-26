﻿using Buyit.Context;
using Buyit.Dtos;
using Buyit.Models;
using Microsoft.EntityFrameworkCore;

namespace Buyit.Services;
public class HistoricoService
{
    private readonly BuyitContext _context;

    public HistoricoService(BuyitContext context)
    {
        _context = context;
    }

    public async Task<List<HistoricoDto>> FindAll()
    {
        var list = await _context.Historico
            .Include(x => x.Cotacao)
            .Include(x => x.Fornecedor)
            .Include(x => x.Status)
            .ToListAsync();

        return list.Select(entity => ConvertToDto(entity)).ToList();
    }

    public async Task<HistoricoDto> FindById(long id)
    {
        var entity = await FindEntityById(id);
        return ConvertToDto(entity);
    }

    public async Task<HistoricoDto> Create(HistoricoDto newData)
    {
        var entity = await ConvertToEntity(newData);
        _context.Historico.Add(entity);
        await _context.SaveChangesAsync();
        return ConvertToDto(entity);
    }

    public async Task<HistoricoDto> Update(long id, HistoricoDto updatedData)
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
        _context.Historico.Remove(entity);
        await _context.SaveChangesAsync();
    }

    public async Task<HistoricoModel> FindEntityById(long id)
    {
        var entity = await _context.Historico
            .Include(x => x.Cotacao)
            .Include(x => x.Fornecedor)
            .Include(x => x.Status)
            .FirstOrDefaultAsync(x => x.Id == id);
        if (entity == null)
        {
            throw new KeyNotFoundException($"Objeto não encontrado com o ID: {id}");
        }
        return entity;
    }

    public async Task<List<HistoricoDto>> FindByCotacaoId(long id)
    {
        var list = await _context.Historico
            .Where(x => x.Cotacao.Id == id)
            .Include(x => x.Cotacao).ThenInclude(x => x.Comprador)
            .Include(x => x.Fornecedor)
            .Include(x => x.Status)
            .ToListAsync();

        return list.Select(x => ConvertToDto(x)).ToList();
    }

    public async Task<List<HistoricoDto>> FindByCompradorId(long id)
    {
        var list = await _context.Historico
            .Where(x => x.Cotacao.Comprador.Id == id)
            .Include(x => x.Cotacao).ThenInclude(x => x.Comprador)
            .Include(x => x.Fornecedor)
            .Include(x => x.Status)
            .ToListAsync();

        return list.Select(x => ConvertToDto(x)).ToList();
    }

    public async Task<List<HistoricoDto>> FindByFornecedorId(long id)
    {
        var list = await _context.Historico
            .Where(x => x.Fornecedor.Id == id)
            .Include(x => x.Cotacao).ThenInclude(x => x.Comprador)
            .Include(x => x.Fornecedor)
            .Include(x => x.Status)
            .ToListAsync();

        return list.Select(x => ConvertToDto(x)).ToList();
    }

    public async Task<List<HistoricoDto>> FindByStatusId(long id)
    {
        var list = await _context.Historico
            .Where(x => x.Status.Id == id)
            .Include(x => x.Cotacao).ThenInclude(x => x.Comprador)
            .Include(x => x.Fornecedor)
            .Include(x => x.Status)
            .ToListAsync();

        return list.Select(x => ConvertToDto(x)).ToList();
    }

    private HistoricoDto ConvertToDto(HistoricoModel entity)
    {
        return new HistoricoDto
        {
            Id = entity.Id,
            IdCotacao = entity.Cotacao.Id,
            IdFornecedor = entity.Fornecedor.Id,
            IdStatus = entity.Status.Id,
            RecusadoPorProduto = entity.RecusadoPorProduto,
            RecusadoPorQuantidade = entity.RecusadoPorQuantidade,
            RecusadoPorPreco = entity.RecusadoPorPreco,
            RecusadoPorPrazo = entity.RecusadoPorPrazo,
            Descricao = entity.Descricao,
            Data = entity.Data,
            ValorOfertado = entity.ValorOfertado
        };
    }

    private async Task<HistoricoModel> ConvertToEntity(HistoricoDto dto)
    {
        var cotacao = await _context.Cotacao.FindAsync(dto.IdCotacao);
        if (cotacao == null)
        {
            throw new KeyNotFoundException($"Objeto não encontrado com o ID: {dto.IdCotacao}");
        }

        var fornecedor = await _context.Usuario.FindAsync(dto.IdFornecedor);
        if (fornecedor == null)
        {
            throw new KeyNotFoundException($"Objeto não encontrado com o ID: {dto.IdFornecedor}");
        }

        var status = await _context.Status.FindAsync(dto.IdStatus);
        if (status == null)
        {
            throw new KeyNotFoundException($"Objeto não encontrado com o ID: {dto.IdStatus}");
        }

        return new HistoricoModel
        {
            Id = dto.Id ?? 0,
            Cotacao = cotacao,
            Fornecedor = fornecedor,
            Status = status,
            RecusadoPorProduto = dto.RecusadoPorProduto,
            RecusadoPorQuantidade = dto.RecusadoPorQuantidade,
            RecusadoPorPreco = dto.RecusadoPorPreco,
            RecusadoPorPrazo = dto.RecusadoPorPrazo,
            Descricao = dto.Descricao,
            Data = dto.Data,
            ValorOfertado = dto.ValorOfertado
        };
    }
}