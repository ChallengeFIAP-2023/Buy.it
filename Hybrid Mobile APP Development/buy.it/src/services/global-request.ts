import { api } from './api';

import { Department } from '@dtos/department';
import { Tag } from '@dtos/tag';

class GlobalRequestService {
  async getDepartments(): Promise<Department[]> {
    try {
      const { data } = await api.get('/departamentos');
      return data.content;
    } catch (error) {
      throw new Error('Não foi possível buscar os departamentos');
    }
  }

  async getTags(): Promise<Tag[]> {
    try {
      const { data } = await api.get('/tags');
      return data.content;
    } catch (error) {
      throw new Error('Não foi possível buscar as tags');
    }
  }
}

export default new GlobalRequestService();
