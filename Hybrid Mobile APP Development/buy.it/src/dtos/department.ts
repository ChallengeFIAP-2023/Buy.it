import { Tag } from "./tag";
import { MaterialCommunityIcons } from '@expo/vector-icons';

export interface Department {
  id: string;
  nome: string;
  icone: typeof MaterialCommunityIcons['name'];
  tags: Tag[];
}

export interface DepartmentQuery {
  id: string;
  nome: string;
  icone: typeof MaterialCommunityIcons['name'];
  idsTags: number[];
}