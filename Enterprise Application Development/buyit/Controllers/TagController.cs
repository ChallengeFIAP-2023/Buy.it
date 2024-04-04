using Buyit.Dtos;
using Buyit.Services;
using Microsoft.AspNetCore.Mvc;

namespace Buyit.Controllers
{
    [Route("/tags")]
    [ApiController]
    public class TagController : ControllerBase
    {
        private readonly TagService _service;

        public TagController(TagService service)
        {
            _service = service;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<TagDto>>> FindAll()
        {
            var list = await _service.FindAll();
            return Ok(list);
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<TagDto>> FindById(long id)
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
        public async Task<ActionResult<TagDto>> Create([FromBody] TagDto dto)
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
        public async Task<ActionResult<TagDto>> Update(long id, [FromBody] TagDto updatedTagDto)
        {
            try
            {
                var updated = await _service.Update(id, updatedTagDto);
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

        [HttpGet("departamento/{id}")]
        public async Task<ActionResult<IEnumerable<TagDto>>> FindByDepartamentoId(long id)
        {
            try
            {
                var list = await _service.FindByDepartamentoId(id);
                return Ok(list);
            }
            catch (System.Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpGet("usuario/{id}")]
        public async Task<ActionResult<IEnumerable<TagDto>>> FindByUsuarioId(long id)
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

        [HttpGet("produto/{id}")]
        public async Task<ActionResult<IEnumerable<TagDto>>> FindByProdutoId(long id)
        {
            try
            {
                var list = await _service.FindByProdutoId(id);
                return Ok(list);
            }
            catch (System.Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }
    }
}