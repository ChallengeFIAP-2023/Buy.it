import React, { Dispatch, createContext, useContext, useState } from 'react';

// Service import
import GlobalRequestService from '@services/global-request';

// Type import
import { QuoteQuery, ProductQuery, Department, Tag } from '@dtos/index';
import Toast from 'react-native-toast-message';

interface CreateQuoteContextData {
  quote: QuoteQuery;
  product: ProductQuery;
  setQuote: Dispatch<React.SetStateAction<QuoteQuery>>;
  setProduct: Dispatch<React.SetStateAction<ProductQuery>>;
  // handleCreateQuote: (finalQueryData: QuoteQuery) => Promise<void>;
  loading: boolean;

  departments: Department[];
  tags: Tag[];
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
        type: 'success',
        text1: String(error),
      });
    } finally {
      setLoading(false);
    }
  }

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
