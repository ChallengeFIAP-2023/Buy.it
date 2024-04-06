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
import { AppNavigatorRoutesProps } from '@routes/index';
import { api } from '@services/api';
import { useAuth } from './useAuth';
import Toast from 'react-native-toast-message';

interface QuoteProposalContextData {
  proposal: Proposal | undefined;
  handleProcessProposal: (type: 'approve' | 'decline') => Promise<void>;
  handleRedirectSuccessProposal: () => void;

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
  const { user } = useAuth();

  const [lastRouteNavigated, setLastRouteNavigated] =
    useState<keyof MainRoutes>('Home');

  const [proposal, setProposal] = useState<Proposal | undefined>(undefined);
  const [approveLoading, setApproveLoading] = useState(false);
  const [declineLoading, setDeclineLoading] = useState(false);

  useEffect(() => {
    const fetchProposals = async () => {
      try {
        const openProposalId = 1;
        const { data } = await api.get<{ content: Proposal[] }>(
          `/cotacoes/status/${openProposalId}`,
        );

        setProposal(data.content[0]);
      } catch (error) {
        console.log('Erro ao buscar dados do backend:', error);
      }
    };

    if (proposal || !user?.id) return;

    const oneMinuteInMilliseconds = 60 * 1000;
    const intervalId = setInterval(fetchProposals, oneMinuteInMilliseconds);

    return () => clearInterval(intervalId);
  }, [proposal, user]);

  const handleProcessProposal = useCallback(
    async (type: 'approve' | 'decline') => {
      if (type === 'approve') {
        try {
          if (proposal) {
            setApproveLoading(true);
            const approvedStatusId = 3;

            await api.put(`/cotacoes/${proposal.id}`, {
              dataAbertura: proposal.dataAbertura,
              idComprador: proposal.comprador.id,
              idProduto: proposal.produto.id,
              quantidadeProduto: proposal.quantidadeProduto,
              valorProduto: proposal.valorProduto,
              idStatus: approvedStatusId,
              prioridadeEntrega: proposal.prioridadeEntrega,
              prioridadeQualidade: proposal.prioridadeQualidade,
              prioridadePreco: proposal.prioridadePreco,
              prazo: proposal.prazo,
              dataFechamento: new Date(),
            });

            navigate('QuoteProposal', { screen: 'QuoteProposalSuccess' });
          }
        } catch (error) {
          return Toast.show({
            type: 'error',
            text1: 'Não foi possível aprovar esta cotação',
            text2: 'Tente novamente.',
          });
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
          return Toast.show({
            type: 'error',
            text1: 'Não foi possível recusar esta cotação',
            text2: 'Tente novamente.',
          });
        } finally {
          setDeclineLoading(false);
        }
      }
    },
    [lastRouteNavigated],
  );

  const handleRedirectSuccessProposal = useCallback(() => {
    setProposal(undefined);
    navigate('Main');
  }, []);

  return (
    <QuoteProposalContext.Provider
      value={{
        proposal,
        handleProcessProposal,
        handleRedirectSuccessProposal,

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
