using Buyit.Context;
using Buyit.Models;
using Buyit.Repositories;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace Buyit.Controllers
{
    [Route("/status")]
    [ApiController]
    public class StatusController : ControllerBase
    {
        private readonly Repository<StatusModel> _repository;
        private readonly BuyitContext _context;

        public StatusController(Repository<StatusModel> repository, BuyitContext context)
        {
            _repository = repository;
            _context = context;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<StatusModel>>> GetAll()
        {
            var list = await _context.Status.ToListAsync();
            return Ok(list);
        }

        [HttpGet("{id}")]
        public ActionResult<StatusModel> GetById(long id)
        {
            var entity = _context.Status.FirstOrDefault(x => x.Id == id);
            if (entity == null)
            {
                return NotFound();
            }
            return Ok(entity);
        }

        [HttpPost]
        public ActionResult<StatusModel> Create([FromBody] StatusModel entity)
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
        public IActionResult Update(long id, [FromBody] StatusModel updatedEntity)
        {
            try
            {
                var existingEntity = _context.Status.FirstOrDefault(x => x.Id == id);
                if (existingEntity == null)
                {
                    return NotFound();
                }

                existingEntity.Nome = updatedEntity.Nome;

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