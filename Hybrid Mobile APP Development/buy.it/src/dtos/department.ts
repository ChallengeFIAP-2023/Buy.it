import { Tag } from "./tag";

export interface Department {
  id: string;
  nome: string;
  icone: string;
  tags: Tag[];
}

export interface DepartmentQuery {
  id: string;
  nome: string;
  icone: string;
  idsTags: number[];
}