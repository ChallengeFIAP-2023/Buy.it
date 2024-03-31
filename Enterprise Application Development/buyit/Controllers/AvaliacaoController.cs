using Buyit.Context;
using Buyit.Models;
using Buyit.Repositories;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace Buyit.Controllers
{
    [Route("/avaliacoes")]
    [ApiController]
    public class AvaliacaoController : ControllerBase
    {
        private readonly Repository<AvaliacaoModel> _repository;
        private readonly BuyitContext _context;

        public AvaliacaoController(Repository<AvaliacaoModel> repository, BuyitContext context)
        {
            _repository = repository;
            _context = context;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<AvaliacaoModel>>> GetAll()
        {
            var list = await _context.Avaliacao.Include(x => x.Cotacao).ToListAsync();
            return Ok(list);
        }

        [HttpGet("{id}")]
        public ActionResult<AvaliacaoModel> GetById(long id)
        {
            var entity = _context.Avaliacao.Include(x => x.Cotacao).FirstOrDefault(x => x.Id == id);
            if (entity == null)
            {
                return NotFound();
            }
            return Ok(entity);
        }

        [HttpPost]
        public ActionResult<AvaliacaoModel> Create([FromBody] AvaliacaoModel entity)
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
        public IActionResult Update(long id, [FromBody] AvaliacaoModel updatedEntity)
        {
            try
            {
                var existingEntity = _context.Avaliacao.FirstOrDefault(x => x.Id == id);
                if (existingEntity == null)
                {
                    return NotFound();
                }

                existingEntity.Cotacao = updatedEntity.Cotacao;
                existingEntity.Data = updatedEntity.Data;
                existingEntity.NotaEntrega = updatedEntity.NotaEntrega;
                existingEntity.NotaQualidade = updatedEntity.NotaQualidade;
                existingEntity.NotaPreco = updatedEntity.NotaPreco;
                existingEntity.Descricao = updatedEntity.Descricao;

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