import { Tag } from './tag';

export interface UserQuery {
  id?: string;
  nome: string;
  email: string;
  senha: string;
  urlImagem: string;
  cnpj: string;
  isFornecedor: boolean;
  idsTags: number[];
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
