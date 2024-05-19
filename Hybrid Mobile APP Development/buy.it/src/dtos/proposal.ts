export interface User {
  id: number;
  nome: string;
  email: string;
  urlImagem: string;
  cnpj: string;
  isFornecedor: boolean;
}

interface Tag {
  id: number;
  nome: string;
}

interface Product {
  id: number;
  nome: string;
  marca: string;
  cor: string | null;
  tamanho: string;
  material: string | null;
  observacao: string | null;
  tags: Tag[];
}

export interface Proposal {
  id: number;
  dataAbertura: string;
  quantidadeProduto: number;
  valorProduto: number;
  prazo: number;
  prioridadeEntrega: number;
  prioridadeQualidade: number;
  prioridadePreco: number;

  comprador: User;
  produto: Product;
}
