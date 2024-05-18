export interface LogQuery {
    data: string;
    idCotacao: number;
    idFornecedor: number;
    idStatus: number;
    recusadoPorProduto: boolean;
    recusadoPorQuantidade: boolean;
    recusadoPorPreco: boolean;
    recusadoPorPrazo: boolean;
    descricao: string | null;
    valorOfertado: number | null;
}
