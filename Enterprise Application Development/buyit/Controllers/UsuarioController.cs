using Buyit.Context;
using Buyit.Models;
using Buyit.Repositories;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace Buyit.Controllers
{
    [Route("/usuarios")]
    [ApiController]
    public class UsuarioController : ControllerBase
    {
        private readonly Repository<UsuarioModel> _repository;
        private readonly BuyitContext _context;

        public UsuarioController(Repository<UsuarioModel> repository, BuyitContext context)
        {
            _repository = repository;
            _context = context;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<UsuarioModel>>> GetAll()
        {
            var list = await _context.Usuario.ToListAsync();
            return Ok(list);
        }

        [HttpGet("{id}")]
        public ActionResult<UsuarioModel> GetById(long id)
        {
            var entity = _context.Usuario.FirstOrDefault(x => x.Id == id);
            if (entity == null)
            {
                return NotFound();
            }
            return Ok(entity);
        }

        [HttpPost]
        public ActionResult<UsuarioModel> Create([FromBody] UsuarioModel entity)
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
        public IActionResult Update(long id, [FromBody] UsuarioModel updatedEntity)
        {
            try
            {
                var existingEntity = _context.Usuario.FirstOrDefault(x => x.Id == id);
                if (existingEntity == null)
                {
                    return NotFound();
                }

                existingEntity.Nome = updatedEntity.Nome;
                existingEntity.Email = updatedEntity.Email;
                existingEntity.Senha = updatedEntity.Senha;
                existingEntity.UrlImagem = updatedEntity.UrlImagem;
                existingEntity.Cnpj = updatedEntity.Cnpj;
                existingEntity.IsFornecedor = updatedEntity.IsFornecedor;
                existingEntity.Tags = updatedEntity.Tags;

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