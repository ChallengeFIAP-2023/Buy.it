import React, {
  createContext,
  useCallback,
  useContext,
  useState,
  useEffect,
} from 'react';
import { useNavigation } from '@react-navigation/native';

// Type import
import { Proposal } from '@dtos/proposal';

// Service import
import { MainRoutes } from '@screens/Main';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { AppNavigatorRoutesProps } from '@routes/index';

interface QuoteProposalContextData {
  proposal: Proposal | undefined;
  handleProcessProposal: (type: 'approve' | 'decline') => void;

  approveLoading: boolean;
  declineLoading: boolean;

  setLastRouteNavigated: React.Dispatch<React.SetStateAction<keyof MainRoutes>>;
}

interface QuoteProposalProviderProps {
  children: React.ReactNode;
}

const QuoteProposalContext = createContext<QuoteProposalContextData>(
  {} as QuoteProposalContextData,
);

const QuoteProposalProvider: React.FC<QuoteProposalProviderProps> = ({
  children,
}) => {
  // Hook
  const { navigate } = useNavigation<AppNavigatorRoutesProps>();

  const [lastRouteNavigated, setLastRouteNavigated] =
    useState<keyof MainRoutes>('Home');

  const [proposal, setProposal] = useState<Proposal | undefined>(undefined);
  const [approveLoading, setApproveLoading] = useState(false);
  const [declineLoading, setDeclineLoading] = useState(false);

  useEffect(() => {
    const fetchProposals = async () => {
      try {
        setProposal({
          id: 4,
          dataAbertura: '16-03-2024',
          comprador: {
            id: 6,
            nome: 'vitor',
            email: 'vitor@gmail.com',
            urlImagem:
              'https://media.licdn.com/dms/image/D4D03AQF4qATl2eRnOQ/profile-displayphoto-shrink_200_200/0/1687370806150?e=1717632000&v=beta&t=FfvjQO0ImZNU38FH_mgnEJv0mZG56q2HKguV980JDdw',
            cnpj: '29883217000175',
            isFornecedor: false,
          },
          produto: {
            id: 4,
            nome: 'Calça',
            marca: 'Hering',
            cor: 'Vermelho',
            tamanho: 'P',
            material: 'Jeans',
            observacao: 'Modelo XYZ',
            tags: [
              {
                id: 1,
                nome: 'Roupa',
              },
            ],
          },
          quantidadeProduto: 100,
          valorProduto: 2.0,
          prazo: 2,
        });
      } catch (error) {
        console.error('Erro ao buscar dados do backend:', error);
      }
    };

    if (proposal) return;

    const twentyFiveMilliseconds = 2 * 1000;
    const intervalId = setInterval(fetchProposals, twentyFiveMilliseconds);

    return () => clearInterval(intervalId);
  }, [proposal]);

  const handleProcessProposal = useCallback(
    (type: 'approve' | 'decline') => {
      if (type === 'approve') {
        try {
          setApproveLoading(true);
          console.log('aprovou a proposta');
        } catch (error) {
        } finally {
          setApproveLoading(false);
        }
      }

      if (type === 'decline') {
        try {
          setDeclineLoading(true);
          console.log('recusou a proposta', lastRouteNavigated);

          navigate('Main', { screen: lastRouteNavigated });
          setProposal(undefined);
        } catch (error) {
        } finally {
          setDeclineLoading(false);
        }
      }
    },
    [lastRouteNavigated],
  );

  return (
    <QuoteProposalContext.Provider
      value={{
        proposal,
        handleProcessProposal,

        approveLoading,
        declineLoading,

        setLastRouteNavigated,
      }}
    >
      {children}
    </QuoteProposalContext.Provider>
  );
};

function useQuoteProposal(): QuoteProposalContextData {
  const context = useContext(QuoteProposalContext);

  if (!context)
    throw new Error(
      'useQuoteProposal must be used within a QuoteProposalProvider',
    );

  return context;
}

export { QuoteProposalProvider, useQuoteProposal };
