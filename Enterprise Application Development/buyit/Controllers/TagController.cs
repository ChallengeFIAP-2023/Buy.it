﻿using Buyit.Context;
using Buyit.Models;
using Buyit.Repositories;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace Buyit.Controllers
{
    [Route("/tags")]
    [ApiController]
    public class TagController : ControllerBase
    {
        private readonly Repository<TagModel> _repository;
        private readonly BuyitContext _context;

        public TagController(Repository<TagModel> repository, BuyitContext context)
        {
            _repository = repository;
            _context = context;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<TagModel>>> GetAll()
        {
            var list = await _context.Tag.ToListAsync();
            return Ok(list);
        }

        [HttpGet("{id}")]
        public ActionResult<TagModel> GetById(long id)
        {
            var entity = _context.Tag.FirstOrDefault(x => x.Id == id);
            if (entity == null)
            {
                return NotFound();
            }
            return Ok(entity);
        }

        [HttpPost]
        public ActionResult<TagModel> Create([FromBody] TagModel entity)
        {
            try
            {
                _repository.Create(entity);
                _context.SaveChanges();
                return CreatedAtAction(nameof(GetById), new { id = entity.Id }, entity);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpPut("{id}")]
        public IActionResult Update(long id, [FromBody] TagModel updatedEntity)
        {
            try
            {
                var existingEntity = _context.Tag.FirstOrDefault(x => x.Id == id);
                if (existingEntity == null)
                {
                    return NotFound();
                }

                existingEntity.Nome = updatedEntity.Nome;
                existingEntity.Departamentos = updatedEntity.Departamentos;
                existingEntity.Usuarios = updatedEntity.Usuarios;
                existingEntity.Produtos = updatedEntity.Produtos;

                _context.SaveChanges();
                return Ok(existingEntity);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpDelete("{id}")]
        public IActionResult Delete(long id)
        {
            var entity = _repository.GetById(id);
            if (entity == null)
            {
                return NotFound();
            }

            try
            {
                _repository.Delete(id);
                return NoContent();
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }
    }
}