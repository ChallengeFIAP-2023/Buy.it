using Buyit.Dtos;
using Buyit.Services;
using Microsoft.AspNetCore.Mvc;

namespace Buyit.Controllers
{
    [Route("/historicos")]
    [ApiController]
    public class HistoricoController : ControllerBase
    {
        private readonly HistoricoService _service;

        public HistoricoController(HistoricoService service)
        {
            _service = service;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<HistoricoDto>>> FindAll()
        {
            var list = await _service.FindAll();
            return Ok(list);
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<HistoricoDto>> FindById(long id)
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
        public async Task<ActionResult<HistoricoDto>> Create([FromBody] HistoricoDto dto)
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
        public async Task<ActionResult<HistoricoDto>> Update(long id, [FromBody] HistoricoDto updatedHistoricoDto)
        {
            try
            {
                var updated = await _service.Update(id, updatedHistoricoDto);
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

        [HttpGet("cotacao/{id}")]
        public async Task<ActionResult<IEnumerable<HistoricoDto>>> FindByCotacaoId(long id)
        {
            try
            {
                var list = await _service.FindByCotacaoId(id);
                return Ok(list);
            }
            catch (System.Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpGet("usuario/comprador/{id}")]
        public async Task<ActionResult<IEnumerable<HistoricoDto>>> FindByCompradorId(long id)
        {
            try
            {
                var list = await _service.FindByCompradorId(id);
                return Ok(list);
            }
            catch (System.Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpGet("usuario/fornecedor/{id}")]
        public async Task<ActionResult<IEnumerable<HistoricoDto>>> FindByFornecedorId(long id)
        {
            try
            {
                var list = await _service.FindByFornecedorId(id);
                return Ok(list);
            }
            catch (System.Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpGet("status/{id}")]
        public async Task<ActionResult<IEnumerable<HistoricoDto>>> FindByStatusId(long id)
        {
            try
            {
                var list = await _service.FindByStatusId(id);
                return Ok(list);
            }
            catch (System.Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }
    }
}