import React, {
  createContext,
  useCallback,
  useContext,
  useState,
  Dispatch,
} from 'react';
import Toast from 'react-native-toast-message';

// Service import
import { api } from '@services/api';

// Type import
import { TagQuery } from '@dtos/index';

interface TagsContextData {
  tags: TagQuery[];
  handleGetTags: () => Promise<void>;
  tagsLoading: boolean;
}

interface TagsProviderProps {
  children: React.ReactNode;
}

const TagsContext = createContext<TagsContextData>(
  {} as TagsContextData,
);

const TagsProvider: React.FC<TagsProviderProps> = ({
  children,
}) => {
  const [tagsLoading, setTagsLoading] = useState(false);
  const [tags, setTags] = useState<TagQuery[]>([] as TagQuery[]);

  const handleGetTags = useCallback(async () => {
    try {
      setTagsLoading(true);
      const { data } = await api.get('/tags');
      setTags(data.content);
    } catch (error) {
      console.log(error);
      Toast.show({
        type: 'error',
        text1: 'Erro',
        text2: 'Não foi possível buscar as tags',
      });
    } finally {
      setTagsLoading(false);
    }
  }, []);

  return (
    <TagsContext.Provider
      value={{
        tagsLoading,
        tags,
        handleGetTags,
      }}
    >
      {children}
    </TagsContext.Provider>
  );
};

function useTags(): TagsContextData {
  const context = useContext(TagsContext);

  if (!context)
    throw new Error('useTags must be used within a TagsContextProvider');

  return context;
}

export { TagsProvider, useTags };
