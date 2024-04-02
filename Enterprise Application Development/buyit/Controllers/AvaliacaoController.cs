using Buyit.Dtos;
using Buyit.Services;
using Microsoft.AspNetCore.Mvc;

namespace Buyit.Controllers
{
    [Route("/avaliacoes")]
    [ApiController]
    public class AvaliacaoController : ControllerBase
    {
        private readonly AvaliacaoService _service;

        public AvaliacaoController(AvaliacaoService service)
        {
            _service = service;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<AvaliacaoDto>>> GetAll()
        {
            var list = await _service.ListAllAsync();
            return Ok(list);
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<AvaliacaoDto>> GetById(long id)
        {
            try
            {
                var dto = await _service.FindByIdAsync(id);
                return Ok(dto);
            }
            catch (KeyNotFoundException)
            {
                return NotFound();
            }
        }

        [HttpPost]
        public async Task<ActionResult<AvaliacaoDto>> Create([FromBody] AvaliacaoDto dto)
        {
            try
            {
                var created = await _service.CreateAsync(dto);
                return CreatedAtAction(nameof(GetById), new { id = created.Id }, created);
            }
            catch (System.Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpPut("{id}")]
        public async Task<ActionResult<AvaliacaoDto>> Update(long id, [FromBody] AvaliacaoDto updatedAvaliacaoDto)
        {
            try
            {
                var updated = await _service.UpdateAsync(id, updatedAvaliacaoDto);
                return Ok(updated);
            }
            catch (KeyNotFoundException)
            {
                return NotFound();
            }
            catch (System.Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> Delete(long id)
        {
            try
            {
                await _service.DeleteAsync(id);
                return NoContent();
            }
            catch (KeyNotFoundException)
            {
                return NotFound();
            }
            catch (System.Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpGet("cotacao/{id}")]
        public async Task<ActionResult<IEnumerable<AvaliacaoDto>>> GetByCotacaoId(long id)
        {
            try
            {
                var list = await _service.FindByCotacaoIdAsync(id);
                return Ok(list);
            }
            catch (System.Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpGet("usuario/{id}")]
        public async Task<ActionResult<IEnumerable<AvaliacaoDto>>> GetByUsuarioId(long id)
        {
            try
            {
                var list = await _service.FindByUsuarioIdAsync(id);
                return Ok(list);
            }
            catch (System.Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }
    }
}