using Buyit.Context;
using Buyit.Models;
using Buyit.Repositories;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace Buyit.Controllers
{
    [Route("/historicos")]
    [ApiController]
    public class HistoricoController : ControllerBase
    {
        private readonly Repository<HistoricoModel> _repository;
        private readonly BuyitContext _context;

        public HistoricoController(Repository<HistoricoModel> repository, BuyitContext context)
        {
            _repository = repository;
            _context = context;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<HistoricoModel>>> GetAll()
        {
            var list = await _context.Historico.Include(x => x.Cotacao).Include(x => x.Fornecedor).Include(x => x.Status).ToListAsync();
            return Ok(list);
        }

        [HttpGet("{id}")]
        public ActionResult<HistoricoModel> GetById(long id)
        {
            var entity = _context.Historico.Include(x => x.Cotacao).Include(x => x.Fornecedor).Include(x => x.Status).FirstOrDefault(x => x.Id == id);
            if (entity == null)
            {
                return NotFound();
            }
            return Ok(entity);
        }

        [HttpPost]
        public ActionResult<HistoricoModel> Create([FromBody] HistoricoModel entity)
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
        public IActionResult Update(long id, [FromBody] HistoricoModel updatedEntity)
        {
            try
            {
                var existingEntity = _context.Historico.FirstOrDefault(x => x.Id == id);
                if (existingEntity == null)
                {
                    return NotFound();
                }

                existingEntity.Cotacao = updatedEntity.Cotacao;
                existingEntity.Fornecedor = updatedEntity.Fornecedor;
                existingEntity.Status = updatedEntity.Status;
                existingEntity.RecusadoPorProduto = updatedEntity.RecusadoPorProduto;
                existingEntity.RecusadoPorQuantidade = updatedEntity.RecusadoPorQuantidade;
                existingEntity.RecusadoPorPreco = updatedEntity.RecusadoPorPreco;
                existingEntity.RecusadoPorPrazo = updatedEntity.RecusadoPorPrazo;
                existingEntity.Descricao = updatedEntity.Descricao;
                existingEntity.Data = updatedEntity.Data;
                existingEntity.ValorOfertado = updatedEntity.ValorOfertado;

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