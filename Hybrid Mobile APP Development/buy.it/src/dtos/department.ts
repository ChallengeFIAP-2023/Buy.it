import { Tag } from "./tag";
import { MaterialCommunityIcons } from '@expo/vector-icons';

export interface Department {
  id: string;
  nome: string;
  icone: keyof typeof MaterialCommunityIcons.glyphMap;
  tags: Tag[];
}

export interface DepartmentQuery {
  id: string;
  nome: string;
  icone: keyof typeof MaterialCommunityIcons.glyphMap;
  idsTags: number[];
}