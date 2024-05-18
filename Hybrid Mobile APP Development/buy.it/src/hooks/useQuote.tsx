import React, {
  Dispatch,
  createContext,
  useCallback,
  useContext,
  useState,
} from 'react';

// Service import
import GlobalRequestService from '@services/global-request';

// Type import
import {
  QuoteQuery,
  ProductQuery,
  Department,
  Tag,
  TagQuery,
  Product,
  ProductPrices,
  Quote,
  ReviewQuery
} from '@dtos/index';

import Toast from 'react-native-toast-message';
import { api } from '@services/api';
import { CreateQuoteRoutes } from '@screens/CreateQuote';
import { MainNavigationRoutes } from '@routes/index';
import { StackNavigationProp } from '@react-navigation/stack';

export type NavigationProp = StackNavigationProp<
  CreateQuoteRoutes & MainNavigationRoutes
>;

interface QuoteContextData {
  quote: QuoteQuery;
  product: ProductQuery;
  setQuote: Dispatch<React.SetStateAction<QuoteQuery>>;
  setProduct: Dispatch<React.SetStateAction<ProductQuery>>;
  handleNewQuote: (
    finalQueryData: QuoteQuery,
    navigation: NavigationProp,
  ) => Promise<void>;
  handleUpdateQuote: (body: QuoteQuery, id: number) => Promise<void>;
  handleNewProduct: (finalQueryData: ProductQuery) => Promise<void>;
  handleNewTag: (finalQueryData: TagQuery) => Promise<void>;
  handleNewReview: (finalQueryData: ReviewQuery) => Promise<void>;
  loading: boolean;
  departments: Department[];
  tags: Tag[];
  products: Product[];
  productPrices: ProductPrices;
  retrievedQuote: Quote;
  quotes: Quote[];
  fetchDepartmentsAndTags(): Promise<void>;
  fetchProducts: () => Promise<void>;
  fetchQuoteById: (id: number) => Promise<void>;
  fetchQuotesByBuyer: (id: string) => Promise<void>;
  fetchProductPrices: (productId: number) => Promise<void>;
}

interface QuoteProviderProps {
  children: React.ReactNode;
}

const QuoteContext = createContext<QuoteContextData>(
  {} as QuoteContextData,
);

const QuoteProvider: React.FC<QuoteProviderProps> = ({
  children,
}) => {
  const [quote, setQuote] = useState<QuoteQuery>({} as QuoteQuery);
  const [retrievedQuote, setRetrievedQuote] = useState<Quote>({} as Quote);
  const [quotes, setQuotes] = useState<Quote[]>([]);
  const [product, setProduct] = useState<ProductQuery>({} as ProductQuery);
  const [departments, setDepartments] = useState<Department[]>([]);
  const [tags, setTags] = useState<Tag[]>([]);
  const [products, setProducts] = useState<Product[]>([]);
  const [productPrices, setProductPrices] = useState<ProductPrices>(
    {} as ProductPrices,
  );
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

  const fetchProducts = async () => {
    try {
      const { data } = await api.get('/produtos');
      setProducts(data.content);
    } catch (error) {
      Toast.show({
        type: 'error',
        text1: 'Erro',
        text2: 'Não foi possível buscar os produtos',
      });
    } finally {
      setLoading(false);
    }
  };

  const fetchProductPrices = async (productId: number) => {
    try {
      const { data } = await api.get(`/cotacoes/produto/info/${productId}`);
      setProductPrices(data);
    } catch (error) {
      Toast.show({
        type: 'error',
        text1: 'Erro',
        text2: 'Não foi possível buscar os preços do produto',
      });
    } finally {
      setLoading(false);
    }
  };

  const handleNewProduct = useCallback(async (productData: ProductQuery) => {
    try {
      setLoading(true);

      const body = productData;
      const { data } = await api.post('/produtos', body);

      if (data.id) {
        Toast.show({
          type: 'success',
          text1: 'Produto criado com sucesso!',
        });
        fetchProducts();
      }
    } catch (error) {
      Toast.show({
        type: 'error',
        text1: 'Erro',
        text2: 'Não foi possível cadastrar este produto.',
      });

      throw error;
    } finally {
      setLoading(false);
    }
  }, []);

  const fetchQuoteById = async (id: number) => {
    try {
      const { data } = await api.get(`/cotacoes/${id}`);
      setRetrievedQuote(data);
    } catch (error) {
      Toast.show({
        type: 'error',
        text1: 'Erro',
        text2: 'Não foi possível carregar a cotação',
      });
    } finally {
      setLoading(false);
    }
  };

  const fetchQuotesByBuyer = async (id: string) => {
    try {
      const { data } = await api.get(
        `/cotacoes/usuario/comprador/${id}`,
      );
      setQuotes(data.content);
    } catch (error) {
      Toast.show({
        type: 'error',
        text1: 'Erro',
        text2: 'Não foi possível buscar as cotações',
      });
    } finally {
      setLoading(false);
    }
  };

  const handleNewQuote = useCallback(
    async (quoteData: QuoteQuery, navigation: NavigationProp) => {
      try {
        setLoading(true);

        const body = quoteData;
        const { data } = await api.post('/cotacoes', body);

        if (data.id) {
          Toast.show({
            type: 'success',
            text1: 'Cotação enviada com sucesso!',
            text2: 'Assim que alguem aceitá-la você será notificado.',
          });

          return navigation.navigate('Main');
        }
      } catch (error) {
        Toast.show({
          type: 'error',
          text1: 'Erro',
          text2: 'Não foi possível criar esta cotação.',
        });

        throw error;
      } finally {
        setLoading(false);
      }
    },
    [],
  );

  const handleNewTag = useCallback(async (tagData: TagQuery) => {
    try {
      setLoading(true);
      const body = tagData;
      const { data } = await api.post('/tags', body);

      if (data.id) {
        Toast.show({
          type: 'success',
          text1: 'Tag criada com sucesso!',
        });
        fetchDepartmentsAndTags();
      }

      fetchDepartmentsAndTags();
    } catch (error) {
      Toast.show({
        type: 'error',
        text1: 'Erro',
        text2: 'Não foi possível criar esta tag.',
      });

      throw error;
    } finally {
      setLoading(false);
    }
  }, []);

  const handleNewReview = useCallback(async (reviewData: ReviewQuery) => {
    try {
      setLoading(true);
      const body = reviewData;
      const { data } = await api.post('/avaliacoes', body);

      if (data.id) {
        Toast.show({
          type: 'success',
          text1: 'Avaliação registrada com sucesso!',
        });
      }
    } catch (error) {
      Toast.show({
        type: 'error',
        text1: 'Erro',
        text2: 'Não foi possível registrar sua avaliação.',
      });

      throw error;
    } finally {
      setLoading(false);
    }
  }, []);

  const handleUpdateQuote = async (body: QuoteQuery, id: number) => {
    try {
      const { data } = await api.put(`/cotacoes/${id}`, body);
      
      if (data.id) {
        Toast.show({
          type: 'success',
          text1: 'Cotação atualizada com sucesso',
        });
      }
    } catch (error) {
      Toast.show({
        type: 'error',
        text1: 'Erro',
        text2: 'Não foi possível atualizar a cotação',
      });
    }
  };

  return (
    <QuoteContext.Provider
      value={{
        quote,
        setQuote,
        product,
        setProduct,
        departments,
        tags,
        products,
        productPrices,
        retrievedQuote,
        quotes,
        loading,
        fetchDepartmentsAndTags,
        fetchProducts,
        fetchProductPrices,
        fetchQuoteById,
        fetchQuotesByBuyer,
        handleNewProduct,
        handleNewQuote,
        handleNewTag,
        handleNewReview,
        handleUpdateQuote
      }}
    >
      {children}
    </QuoteContext.Provider>
  );
};

function useQuote(): QuoteContextData {
  const context = useContext(QuoteContext);

  if (!context)
    throw new Error('useQuote must be used within a QuoteProvider');

  return context;
}

export { QuoteProvider, useQuote };
