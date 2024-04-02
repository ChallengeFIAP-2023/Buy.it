using Buyit.Dtos;
using Buyit.Services;
using Microsoft.AspNetCore.Mvc;

namespace Buyit.Controllers
{
    [Route("/cotacoes")]
    [ApiController]
    public class CotacaoController : ControllerBase
    {
        private readonly CotacaoService _service;

        public CotacaoController(CotacaoService service)
        {
            _service = service;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<CotacaoDto>>> GetAll()
        {
            var list = await _service.ListAllAsync();
            return Ok(list);
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<CotacaoDto>> GetById(long id)
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
        public async Task<ActionResult<CotacaoDto>> Create([FromBody] CotacaoDto dto)
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
        public async Task<ActionResult<CotacaoDto>> Update(long id, [FromBody] CotacaoDto updatedCotacaoDto)
        {
            try
            {
                var updated = await _service.UpdateAsync(id, updatedCotacaoDto);
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

        [HttpGet("usuario/comprador/{id}")]
        public async Task<ActionResult<IEnumerable<CotacaoDto>>> GetByUsuarioId(long id)
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

        [HttpGet("produto/{id}")]
        public async Task<ActionResult<IEnumerable<CotacaoDto>>> GetByProdutoId(long id)
        {
            try
            {
                var list = await _service.FindByProdutoIdAsync(id);
                return Ok(list);
            }
            catch (System.Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpGet("status/{id}")]
        public async Task<ActionResult<IEnumerable<CotacaoDto>>> GetByStatusId(long id)
        {
            try
            {
                var list = await _service.FindByStatusIdAsync(id);
                return Ok(list);
            }
            catch (System.Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpGet("produto/info/{id}")]
        public async Task<ActionResult<InfoCotacaoDto>> GetInfoByProdutoId(long id)
        {
            try
            {
                var info = await _service.GetInfoByProdutoIdAsync(id);
                return Ok(info);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

    }
}