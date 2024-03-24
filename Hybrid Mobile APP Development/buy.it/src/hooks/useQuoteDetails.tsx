import React, { 
  Dispatch, 
  createContext, 
  useContext, 
  useState 
} from 'react';

// Service import
import { api } from '@services/api';

// Type import
import { QuoteQuery } from '@dtos/quote';

interface QuoteDetailsContextData {
  quote: QuoteQuery;
  setQuote: Dispatch<React.SetStateAction<QuoteQuery>>;
  // handleCreateQuote: (finalQueryData: QuoteQuery) => Promise<void>;
  loading: boolean;
}

interface QuoteDetailsProviderProps {
  children: React.ReactNode;
}

const QuoteDetailsContext = createContext<QuoteDetailsContextData>(
  {} as QuoteDetailsContextData,
);

const QuoteDetailsProvider: React.FC<QuoteDetailsProviderProps> = ({
  children,
}) => {

  const [quote, setQuote] = useState<QuoteQuery>({} as QuoteQuery);
  const [loading, setLoading] = useState(false);

  return (
    <QuoteDetailsContext.Provider value={{
      quote,
      setQuote,
      loading
    }}>
      {children}
    </QuoteDetailsContext.Provider>
  );
};

function useQuoteDetails(): QuoteDetailsContextData {
  const context = useContext(QuoteDetailsContext);

  if (!context)
    throw new Error(
      'useQuoteDetails must be used within a QuoteDetailsProvider',
    );

  return context;
}

export { QuoteDetailsProvider, useQuoteDetails };
