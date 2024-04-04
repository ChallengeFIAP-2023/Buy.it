import { Department } from './department';
import { Tag } from './tag';

export interface Product {
    id: number;
    nome: string;
    marca: string | null;
    cor: string | null;
    tamanho: string | null;
    material: string | null;
    observacao: string | null,
    departamento: Department,
    tags: Tag[]
}

export interface ProductQuery {
    nome: string;
    marca: string | null;
    cor: string | null;
    tamanho: string | null;
    material: string | null;
    observacao: string | null,
    idDepartamento: number,
    idsTags: number[]
}

export interface ProductPrices {
    minValor: number | null;
    avgValor: number | null;
    maxValor: number | null;
    produto: Product;
}
