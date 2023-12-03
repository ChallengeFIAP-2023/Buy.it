export interface User {
  nome: string;
  email: string;
  senha: string;
  urlImagem: string;
  cnpj: string;
  isFornecedor: boolean;
  idsTags: number[];
}
