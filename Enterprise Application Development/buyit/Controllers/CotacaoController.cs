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
        public async Task<ActionResult<IEnumerable<CotacaoDto>>> FindAll()
        {
            var list = await _service.FindAll();
            return Ok(list);
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<CotacaoDto>> FindById(long id)
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
        public async Task<ActionResult<CotacaoDto>> Create([FromBody] CotacaoDto dto)
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
        public async Task<ActionResult<CotacaoDto>> Update(long id, [FromBody] CotacaoDto updatedCotacaoDto)
        {
            try
            {
                var updated = await _service.Update(id, updatedCotacaoDto);
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

        [HttpGet("usuario/comprador/{id}")]
        public async Task<ActionResult<IEnumerable<CotacaoDto>>> FindByUsuarioId(long id)
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
        public async Task<ActionResult<IEnumerable<CotacaoDto>>> FindByProdutoId(long id)
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

        [HttpGet("status/{id}")]
        public async Task<ActionResult<IEnumerable<CotacaoDto>>> FindByStatusId(long id)
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

        [HttpGet("produto/info/{id}")]
        public async Task<ActionResult<InfoCotacaoDto>> FindInfoByProdutoId(long id)
        {
            try
            {
                var info = await _service.FindInfoByProdutoId(id);
                return Ok(info);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

    }
}