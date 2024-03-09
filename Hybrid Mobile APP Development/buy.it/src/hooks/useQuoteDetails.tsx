import React, { createContext, useContext } from 'react';

interface QuoteDetailsContextData {}

interface QuoteDetailsProviderProps {
  children: React.ReactNode;
}

const QuoteDetailsContext = createContext<QuoteDetailsContextData>(
  {} as QuoteDetailsContextData,
);

const QuoteDetailsProvider: React.FC<QuoteDetailsProviderProps> = ({
  children,
}) => {
  return (
    <QuoteDetailsContext.Provider value={{}}>
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
