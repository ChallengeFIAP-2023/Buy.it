using Buyit.Context;
using Buyit.Models;
using Buyit.Repositories;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace Buyit.Controllers
{
    [Route("/departamentos")]
    [ApiController]
    public class DepartamentoController : ControllerBase
    {
        private readonly Repository<DepartamentoModel> _repository;
        private readonly BuyitContext _context;

        public DepartamentoController(Repository<DepartamentoModel> repository, BuyitContext context)
        {
            _repository = repository;
            _context = context;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<DepartamentoModel>>> GetAll()
        {
            var list = await _context.Departamento.ToListAsync();
            return Ok(list);
        }

        [HttpGet("{id}")]
        public ActionResult<DepartamentoModel> GetById(long id)
        {
            var entity = _context.Departamento.FirstOrDefault(x => x.Id == id);
            if (entity == null)
            {
                return NotFound();
            }
            return Ok(entity);
        }

        [HttpPost]
        public ActionResult<DepartamentoModel> Create([FromBody] DepartamentoModel entity)
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
        public IActionResult Update(long id, [FromBody] DepartamentoModel updatedEntity)
        {
            try
            {
                var existingEntity = _context.Departamento.FirstOrDefault(x => x.Id == id);
                if (existingEntity == null)
                {
                    return NotFound();
                }

                existingEntity.Nome = updatedEntity.Nome;
                existingEntity.Icone = updatedEntity.Icone;
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