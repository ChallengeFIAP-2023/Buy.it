using Buyit.Context;
using Buyit.Models;

namespace Buyit.Repositories
{
    public class DepartamentoRepository : Repository<DepartamentoModel>
    {
        public DepartamentoRepository(BuyitContext buyitContext) : base(buyitContext) { }
    }
}