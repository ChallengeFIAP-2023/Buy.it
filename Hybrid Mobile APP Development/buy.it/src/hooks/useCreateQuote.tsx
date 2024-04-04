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
import { QuoteQuery, ProductQuery, Department, Tag, TagQuery, Product, ProductPrices } from '@dtos/index';
import Toast from 'react-native-toast-message';
import { api } from '@services/api';
import { CreateQuoteRoutes } from '@screens/CreateQuote';
import { MainNavigationRoutes } from '@routes/index';
import { StackNavigationProp } from '@react-navigation/stack';

export type NavigationProp = StackNavigationProp<CreateQuoteRoutes & MainNavigationRoutes>;

interface CreateQuoteContextData {
  quote: QuoteQuery;
  product: ProductQuery;
  setQuote: Dispatch<React.SetStateAction<QuoteQuery>>;
  setProduct: Dispatch<React.SetStateAction<ProductQuery>>;
  handleNewQuote: (finalQueryData: QuoteQuery, navigation: NavigationProp) => Promise<void>;
  handleNewProduct: (finalQueryData: ProductQuery) => Promise<void>;
  handleNewTag: (finalQueryData: TagQuery) => Promise<void>;
  loading: boolean;
  departments: Department[];
  tags: Tag[];
  products: Product[];
  productPrices: ProductPrices;
  fetchDepartmentsAndTags(): Promise<void>;
  fetchProducts: () => Promise<void>
  fetchProductPrices: (productId: number) => Promise<void>
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
  const [products, setProducts] = useState<Product[]>([]);
  const [productPrices, setProductPrices] = useState<ProductPrices>({} as ProductPrices);
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

  const handleNewQuote = useCallback(async (
    quoteData: QuoteQuery, 
    navigation: NavigationProp
  ) => {
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

        return navigation.navigate("Main");
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
  }, []);

  const handleNewTag = useCallback(async (tagData: TagQuery) => {
    try {
      setLoading(true);
      const body = tagData;

      const { data } = await api.post('/tags', body);
      return data.id;
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

  return (
    <CreateQuoteContext.Provider
      value={{
        quote,
        setQuote,
        product,
        setProduct,
        departments,
        tags,
        products,
        productPrices,
        loading,
        fetchDepartmentsAndTags,
        fetchProducts,
        fetchProductPrices,
        handleNewProduct,
        handleNewQuote,
        handleNewTag
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
