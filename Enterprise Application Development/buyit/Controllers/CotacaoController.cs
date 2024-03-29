using Buyit.Context;
using Buyit.Models;
using Buyit.Repositories;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace Buyit.Controllers
{
    [Route("/cotacoes")]
    [ApiController]
    public class CotacaoController : ControllerBase
    {
        private readonly Repository<CotacaoModel> _repository;
        private readonly BuyitContext _context;

        public CotacaoController(Repository<CotacaoModel> repository, BuyitContext context)
        {
            _repository = repository;
            _context = context;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<CotacaoModel>>> GetAll()
        {
            var list = await _context.Cotacao.Include(x => x.Comprador).Include(x => x.Produto).Include(x => x.Status).ToListAsync();
            return Ok(list);
        }

        [HttpGet("{id}")]
        public ActionResult<CotacaoModel> GetById(long id)
        {
            var entity = _context.Cotacao.Include(x => x.Comprador).Include(x => x.Produto).Include(x => x.Status).FirstOrDefault(x => x.Id == id);
            if (entity == null)
            {
                return NotFound();
            }
            return Ok(entity);
        }

        [HttpPost]
        public ActionResult<CotacaoModel> Create([FromBody] CotacaoModel entity)
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
        public IActionResult Update(long id, [FromBody] CotacaoModel updatedEntity)
        {
            try
            {
                var existingEntity = _context.Cotacao.FirstOrDefault(x => x.Id == id);
                if (existingEntity == null)
                {
                    return NotFound();
                }

                existingEntity.DataAbertura = updatedEntity.DataAbertura;
                existingEntity.Comprador = updatedEntity.Comprador;
                existingEntity.Produto = updatedEntity.Produto;
                existingEntity.QuantidadeProduto = updatedEntity.QuantidadeProduto;
                existingEntity.ValorProduto = updatedEntity.ValorProduto;
                existingEntity.Status = updatedEntity.Status;
                existingEntity.PrioridadeEntrega = updatedEntity.PrioridadeEntrega;
                existingEntity.PrioridadeQualidade = updatedEntity.PrioridadeQualidade;
                existingEntity.PrioridadePreco = updatedEntity.PrioridadePreco;
                existingEntity.Prazo = updatedEntity.Prazo;
                existingEntity.DataFechamento = updatedEntity.DataFechamento;

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