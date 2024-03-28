using Buyit.Context;
using Buyit.Models;

namespace Buyit.Repositories
{
    public class ContatoRepository : Repository<ContatoModel>
    {
        public ContatoRepository(BuyitContext buyitContext) : base(buyitContext) { }
    }
}