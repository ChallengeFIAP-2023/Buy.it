namespace Buyit.Dtos
{
    public class InfoCotacaoDto
    {
        public decimal? MinValor { get; set; }
        public decimal? AvgValor { get; set; }
        public decimal? MaxValor { get; set; }

        public InfoCotacaoDto(decimal? minValor, decimal? avgValor, decimal? maxValor)
        {
            MinValor = minValor;
            AvgValor = avgValor;
            MaxValor = maxValor;
        }
    }
}