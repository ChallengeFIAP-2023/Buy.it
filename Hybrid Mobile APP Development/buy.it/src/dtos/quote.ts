import { Product } from './product';
import { Status } from './status';
import { User } from './user';

export interface QuoteQuery {
    dataAbertura: string;
    idComprador: number;
    idProduto: number;
    quantidadeProduto: number;
    valorProduto: number;
    idStatus: number;
    prioridadeEntrega: number;
    prioridadeQualidade: number;
    prioridadePreco: number;
    prazo: number;
    dataFechamento: string | null;
}

export interface Quote {
    id: number;
    dataAbertura: string;
    comprador: User;
    produto: Product;
    quantidadeProduto: number;
    valorProduto: number;
    status: Status;
    prioridadeEntrega: number;
    prioridadeQualidade: number;
    prioridadePreco: number;
    prazo: number;
    dataFechamento: string | null;
}
