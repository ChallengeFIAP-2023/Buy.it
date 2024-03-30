import React, { Dispatch, createContext, useCallback, useContext, useState } from 'react';

// Service import
import GlobalRequestService from '@services/global-request';

// Type import
import { QuoteQuery, ProductQuery, Department, Tag } from '@dtos/index';
import Toast from 'react-native-toast-message';
import { api } from '@services/api';

interface CreateQuoteContextData {
  quote: QuoteQuery;
  product: ProductQuery;
  setQuote: Dispatch<React.SetStateAction<QuoteQuery>>;
  setProduct: Dispatch<React.SetStateAction<ProductQuery>>;
  handleCreateQuote: (finalQueryData: QuoteQuery) => Promise<void>;
  handleCreateProduct: (finalQueryData: ProductQuery) => Promise<void>;
  loading: boolean;
  departments: Department[];
  tags: Tag[];
  fetchDepartmentsAndTags(): Promise<void>
}

interface CreateQuoteProviderProps {
  children: React.ReactNode;
}

const CreateQuoteContext = createContext<CreateQuoteContextData>(
  {} as CreateQuoteContextData,
);

const CreateQuoteProvider: React.FC<CreateQuoteProviderProps> = ({
  children,
}) => {
  const [quote, setQuote] = useState<QuoteQuery>({} as QuoteQuery);
  const [product, setProduct] = useState<ProductQuery>({} as ProductQuery);
  const [departments, setDepartments] = useState<Department[]>([]);
  const [tags, setTags] = useState<Tag[]>([]);
  const [loading, setLoading] = useState(false);

  async function fetchDepartmentsAndTags() {
    try {
      setLoading(true);

      const [departments, tags] = await Promise.all([
        GlobalRequestService.getDepartments(),
        GlobalRequestService.getTags(),
      ]);

      setDepartments(departments);
      setTags(tags);
    } catch (error) {      
      return Toast.show({
        type: 'error',
        text1: String(error),
      });
    } finally {
      setLoading(false);
    }
  }

  const handleCreateProduct = useCallback(async (productData: ProductQuery) => {
    try {
      setLoading(true);

      const body = productData;
      const { data } = await api.post('/produtos', body);

      console.debug(data);

    } catch (error) {
      Toast.show({
        type: 'error',
        text1: 'Erro',
        text2: 'Não foi possível cadastrar este produto.',
      });

      throw error;
    } finally {
      setLoading(true);
    }
  }, []);

  const handleCreateQuote = useCallback(async (data: QuoteQuery) => {
    try {
      setLoading(true);

      const values = Object.values(data);
      const insufficientInformation = values.some(
        value =>
          typeof value === 'undefined' ||
          typeof value === null,
      );

      if (insufficientInformation)
        throw new Error('Um ou mais atributos estão vazios.');

      const body = data;

      await api.post('/cotacoes', body);

    } catch (error) {
      Toast.show({
        type: 'error',
        text1: 'Erro',
        text2: 'Não foi possível criar esta cotação.',
      });

      throw error;
    } finally {
      setLoading(true);
    }
  }, []);

  return (
    <CreateQuoteContext.Provider
      value={{
        quote,
        setQuote,
        product,
        setProduct,
        departments,
        tags,
        loading,
        fetchDepartmentsAndTags,
        handleCreateProduct,
        handleCreateQuote
      }}
    >
      {children}
    </CreateQuoteContext.Provider>
  );
};

function useCreateQuote(): CreateQuoteContextData {
  const context = useContext(CreateQuoteContext);

  if (!context)
    throw new Error('useCreateQuote must be used within a CreateQuoteProvider');

  return context;
}

export { CreateQuoteProvider, useCreateQuote };
