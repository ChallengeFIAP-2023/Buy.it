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

        public IEnumerable<TEntity> GetAll()
        {
            return _buyitContext.Set<TEntity>().ToList();
        }

        public TEntity GetById(int id)
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

        public void Delete(int id)
        {
            var entity = GetById(id);
            _buyitContext.Set<TEntity>().Remove(entity);
            _buyitContext.SaveChanges();
        }
    }
}