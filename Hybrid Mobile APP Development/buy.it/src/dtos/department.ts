import { Tag } from "./tag";

export interface Department {
  id: string;
  nome: string;
  icone: string;
  tags: Tag[];
}
