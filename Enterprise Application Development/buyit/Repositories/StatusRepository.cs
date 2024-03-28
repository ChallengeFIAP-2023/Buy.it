using Buyit.Context;
using Buyit.Models;

namespace Buyit.Repositories
{
    public class StatusRepository : Repository<StatusModel>
    {
        public StatusRepository(BuyitContext buyitContext) : base(buyitContext) { }
    }
}