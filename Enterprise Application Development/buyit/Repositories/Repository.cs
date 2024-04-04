using Buyit.Context;

namespace Buyit.Repositories
{
    public class Repository<TEntity> where TEntity : class
    {
        protected BuyitContext _buyitContext;

        public Repository(BuyitContext buyitContext)
        {
            _buyitContext = buyitContext;
        }

        public IEnumerable<TEntity> FindAll()
        {
            return _buyitContext.Set<TEntity>().ToList();
        }

        public TEntity FindById(long id)
        {
            return _buyitContext.Set<TEntity>().Find(id);
        }

        public void Create(TEntity entity)
        {
            _buyitContext.Set<TEntity>().Add(entity);
            _buyitContext.SaveChanges();
        }

        public void Update(TEntity entity)
        {
            _buyitContext.Set<TEntity>().Update(entity);
            _buyitContext.SaveChanges();
        }

        public void Delete(long id)
        {
            var entity = FindById(id);
            _buyitContext.Set<TEntity>().Remove(entity);
            _buyitContext.SaveChanges();
        }
    }
}