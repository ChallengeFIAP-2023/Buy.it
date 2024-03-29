using Buyit.Models;
using Buyit.Repositories;
using Microsoft.AspNetCore.Mvc;

namespace Buyit.Controllers
{
    [Route("/cotacoes")]
    [ApiController]
    public class CotacaoController : ControllerBase
    {
        private readonly Repository<CotacaoModel> _repository;

        public CotacaoController(Repository<CotacaoModel> repository)
        {
            _repository = repository;
        }

        [HttpGet]
        public ActionResult<IEnumerable<CotacaoModel>> GetAll()
        {
            var list = _repository.GetAll();
            return Ok(list);
        }

        [HttpGet("{id}")]
        public ActionResult<CotacaoModel> GetById(long id)
        {
            var entity = _repository.GetById(id);
            if (entity == null)
            {
                return NotFound();
            }
            return Ok(entity);
        }

        [HttpPost]
        public ActionResult<CotacaoModel> Create(CotacaoModel entity)
        {
            try
            {
                _repository.Create(entity);
                return CreatedAtAction(nameof(GetById), new { id = entity.Id }, entity);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpPut("{id}")]
        public IActionResult Update(long id, CotacaoModel entity)
        {
            if (id != entity.Id)
            {
                return BadRequest("O Id fornecido na URL não corresponde.");
            }

            try
            {
                _repository.Update(entity);
                return NoContent();
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