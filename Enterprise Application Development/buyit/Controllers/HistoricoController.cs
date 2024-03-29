﻿using Buyit.Models;
using Buyit.Repositories;
using Microsoft.AspNetCore.Mvc;

namespace Buyit.Controllers
{
    [Route("/historicos")]
    [ApiController]
    public class HistoricoController : ControllerBase
    {
        private readonly Repository<HistoricoModel> _repository;

        public HistoricoController(Repository<HistoricoModel> repository)
        {
            _repository = repository;
        }

        [HttpGet]
        public ActionResult<IEnumerable<HistoricoModel>> GetAll()
        {
            var list = _repository.GetAll();
            return Ok(list);
        }

        [HttpGet("{id}")]
        public ActionResult<HistoricoModel> GetById(long id)
        {
            var entity = _repository.GetById(id);
            if (entity == null)
            {
                return NotFound();
            }
            return Ok(entity);
        }

        [HttpPost]
        public ActionResult<HistoricoModel> Create(HistoricoModel entity)
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
        public IActionResult Update(long id, HistoricoModel entity)
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