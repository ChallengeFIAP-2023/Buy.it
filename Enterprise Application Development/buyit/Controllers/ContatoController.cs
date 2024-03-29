using Buyit.Context;
using Buyit.Models;
using Buyit.Repositories;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace Buyit.Controllers
{
    [Route("/contatos")]
    [ApiController]
    public class ContatoController : ControllerBase
    {
        private readonly Repository<ContatoModel> _repository;
        private readonly BuyitContext _context;

        public ContatoController(Repository<ContatoModel> repository, BuyitContext context)
        {
            _repository = repository;
            _context = context;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<ContatoModel>>> GetAll()
        {
            var list = await _context.Contato.Include(x => x.Usuario).ToListAsync();
            return Ok(list);
        }

        [HttpGet("{id}")]
        public ActionResult<ContatoModel> GetById(long id)
        {
            var entity = _context.Contato.Include(c => c.Usuario).FirstOrDefault(x => x.Id == id);
            if (entity == null)
            {
                return NotFound();
            }
            return Ok(entity);
        }

        [HttpPost]
        public ActionResult<ContatoModel> Create([FromBody] ContatoModel entity)
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
        public IActionResult Update(long id, [FromBody] ContatoModel updatedEntity)
        {
            try
            {
                var existingEntity = _context.Contato.FirstOrDefault(x => x.Id == id);
                if (existingEntity == null)
                {
                    return NotFound();
                }

                existingEntity.Tipo = updatedEntity.Tipo;
                existingEntity.Valor = updatedEntity.Valor;
                existingEntity.Usuario = updatedEntity.Usuario;

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