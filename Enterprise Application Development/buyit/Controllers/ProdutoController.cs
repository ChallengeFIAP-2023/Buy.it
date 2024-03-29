using Buyit.Context;
using Buyit.Models;
using Buyit.Repositories;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace Buyit.Controllers
{
    [Route("/produtos")]
    [ApiController]
    public class ProdutoController : ControllerBase
    {
        private readonly Repository<ProdutoModel> _repository;
        private readonly BuyitContext _context;

        public ProdutoController(Repository<ProdutoModel> repository, BuyitContext context)
        {
            _repository = repository;
            _context = context;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<ProdutoModel>>> GetAll()
        {
            var list = await _context.Produto.Include(x => x.Departamento).ToListAsync();
            return Ok(list);
        }

        [HttpGet("{id}")]
        public ActionResult<ProdutoModel> GetById(long id)
        {
            var entity = _context.Produto.Include(x => x.Departamento).FirstOrDefault(x => x.Id == id);
            if (entity == null)
            {
                return NotFound();
            }
            return Ok(entity);
        }

        [HttpPost]
        public ActionResult<ProdutoModel> Create([FromBody] ProdutoModel entity)
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
        public IActionResult Update(long id, [FromBody] ProdutoModel updatedEntity)
        {
            try
            {
                var existingEntity = _context.Produto.FirstOrDefault(x => x.Id == id);
                if (existingEntity == null)
                {
                    return NotFound();
                }

                existingEntity.Nome = updatedEntity.Nome;
                existingEntity.Marca = updatedEntity.Marca;
                existingEntity.Cor = updatedEntity.Cor;
                existingEntity.Tamanho = updatedEntity.Tamanho;
                existingEntity.Material = updatedEntity.Material;
                existingEntity.Observacao = updatedEntity.Observacao;
                existingEntity.Departamento = updatedEntity.Departamento;
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