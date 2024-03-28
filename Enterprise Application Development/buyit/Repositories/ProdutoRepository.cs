using Buyit.Context;
using Buyit.Models;

namespace Buyit.Repositories
{
    public class ProdutoRepository : Repository<ProdutoModel>
    {
        public ProdutoRepository(BuyitContext buyitContext) : base(buyitContext) { }
    }
}