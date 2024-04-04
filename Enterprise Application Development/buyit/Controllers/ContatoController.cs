using Buyit.Dtos;
using Buyit.Services;
using Microsoft.AspNetCore.Mvc;

namespace Buyit.Controllers
{
    [Route("/contatos")]
    [ApiController]
    public class ContatoController : ControllerBase
    {
        private readonly ContatoService _service;

        public ContatoController(ContatoService service)
        {
            _service = service;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<ContatoDto>>> FindAll()
        {
            var list = await _service.FindAll();
            return Ok(list);
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<ContatoDto>> FindById(long id)
        {
            try
            {
                var dto = await _service.FindById(id);
                return Ok(dto);
            }
            catch (KeyNotFoundException)
            {
                return NotFound();
            }
        }

        [HttpPost]
        public async Task<ActionResult<ContatoDto>> Create([FromBody] ContatoDto dto)
        {
            try
            {
                var created = await _service.Create(dto);
                return CreatedAtAction(nameof(FindById), new { id = created.Id }, created);
            }
            catch (System.Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpPut("{id}")]
        public async Task<ActionResult<ContatoDto>> Update(long id, [FromBody] ContatoDto updatedContatoDto)
        {
            try
            {
                var updated = await _service.Update(id, updatedContatoDto);
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
                await _service.Delete(id);
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

        [HttpGet("usuario/{id}")]
        public async Task<ActionResult<IEnumerable<ContatoDto>>> FindByUsuarioId(long id)
        {
            try
            {
                var list = await _service.FindByUsuarioId(id);
                return Ok(list);
            }
            catch (System.Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }
    }
}