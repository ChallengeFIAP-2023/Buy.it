import { Tag } from "./tag";

export interface UserQuery {
  id?: string;
  nome: string | null;
  email: string | null;
  senha: string | null;
  urlImagem: string | null;
  cnpj: string | null;
  isFornecedor: boolean | null;
  idsTags: number[] | null;
}

export interface User {
  id: string;
  nome: string;
  email: string;
  senha: string;
  urlImagem: string;
  cnpj: string;
  isFornecedor: boolean;
  tags: Tag[];
}
