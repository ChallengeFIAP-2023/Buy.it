namespace Buyit.Models
{
    public class TagDepartamentoModel
    {
        public long IdTag { get; set; }
        public TagModel Tag { get; set; }

        public long IdDepartamento { get; set; }
        public DepartamentoModel Departamento { get; set; }
    }
}
