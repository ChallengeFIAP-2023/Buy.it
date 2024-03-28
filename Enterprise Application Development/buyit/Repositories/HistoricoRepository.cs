using Buyit.Context;
using Buyit.Models;

namespace Buyit.Repositories
{
    public class HistoricoRepository : Repository<HistoricoModel>
    {
        public HistoricoRepository(BuyitContext buyitContext) : base(buyitContext) { }
    }
}