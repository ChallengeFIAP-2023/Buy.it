import { Quote } from "./quote";

export interface ReviewQuery {
    idCotacao: number;
    data: string;
    notaEntrega: number;
    notaQualidade: number;
    notaPreco: number;
    descricao: string;
}

export interface Review {
    id: number;
    cotacao: Quote;
    data: string;
    notaEntrega: number;
    notaQualidade: number;
    notaPreco: number;
    descricao: string;
}
