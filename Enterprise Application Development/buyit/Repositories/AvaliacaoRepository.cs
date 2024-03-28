using Buyit.Context;
using Buyit.Models;

namespace Buyit.Repositories
{
    public class AvaliacaoRepository : Repository<AvaliacaoModel>
    {
        public AvaliacaoRepository(BuyitContext buyitContext) : base(buyitContext) { }
    }
}