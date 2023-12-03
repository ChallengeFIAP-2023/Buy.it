export interface User {
  nome: string | null;
  email: string | null;
  senha: string | null;
  urlImagem: string | null;
  cnpj: string | null;
  isFornecedor: boolean | null;
  idsTags: number[] | null;
}
