import React, {
  createContext,
  useCallback,
  useContext,
  useState,
} from 'react';
import Toast from 'react-native-toast-message';

// Service import
import { api } from '@services/api';

// Type import
import { DepartmentQuery } from '@dtos/index';

interface DepartmentsContextData {
  departments: DepartmentQuery[];
  handleGetDepartments: () => Promise<void>;
  departmentsLoading: boolean;
}

interface DepartmentsProviderProps {
  children: React.ReactNode;
}

const TagsContext = createContext<DepartmentsContextData>(
  {} as DepartmentsContextData,
);

const DepartmentsProvider: React.FC<DepartmentsProviderProps> = ({
  children,
}) => {
  const [departmentsLoading, setDepartmentsLoading] = useState(false);
  const [departments, setDepartments] = useState<DepartmentQuery[]>([] as DepartmentQuery[]);

  const handleGetDepartments = useCallback(async () => {
    try {
      setDepartmentsLoading(true);
      const { data } = await api.get('/departamentos');
      setDepartments(data.content);
    } catch (error) {
      console.log(error);
      Toast.show({
        type: 'error',
        text1: 'Erro',
        text2: 'Não foi possível buscar os departamentos',
      });
    } finally {
      setDepartmentsLoading(false);
    }
  }, []);

  return (
    <TagsContext.Provider
      value={{
        departmentsLoading,
        departments,
        handleGetDepartments,
      }}
    >
      {children}
    </TagsContext.Provider>
  );
};

function useDepartments(): DepartmentsContextData {
  const context = useContext(TagsContext);

  if (!context)
    throw new Error('useTags must be used within a TagsContextProvider');

  return context;
}

export { DepartmentsProvider, useDepartments };
