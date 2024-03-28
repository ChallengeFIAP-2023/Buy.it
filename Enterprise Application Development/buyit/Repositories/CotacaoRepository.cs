using Buyit.Context;
using Buyit.Models;

namespace Buyit.Repositories
{
    public class CotacaoRepository : Repository<CotacaoModel>
    {
        public CotacaoRepository(BuyitContext buyitContext) : base(buyitContext) { }
    }
}