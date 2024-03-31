using Buyit.Context;
using Buyit.Models;

namespace Buyit.Repositories
{
    public class UsuarioRepository : Repository<UsuarioModel>
    {
        public UsuarioRepository(BuyitContext buyitContext) : base(buyitContext) { }
    }
}