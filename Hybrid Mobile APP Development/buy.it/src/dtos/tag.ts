export interface Tag {
  id: number;
  nome: string;
}

export interface TagQuery {
  id: number;
  nome: string;
  idsDepartamentos: number[];
  idsUsuarios: number[];
  idsProdutos: number[];
}
