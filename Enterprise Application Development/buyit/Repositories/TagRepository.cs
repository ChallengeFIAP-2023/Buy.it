using Buyit.Context;
using Buyit.Models;

namespace Buyit.Repositories
{
    public class TagRepository : Repository<TagModel>
    {
        public TagRepository(BuyitContext buyitContext) : base(buyitContext) { }
    }
}