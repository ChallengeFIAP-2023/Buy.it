import React, {
  createContext,
  useCallback,
  useContext,
  useState,
} from 'react';
import { StackActions, useNavigation } from '@react-navigation/native';

// Type import
import { Proposal } from '@dtos/proposal';

// Service import
import { MainRoutes } from '@screens/Main';
import { AppNavigatorRoutesProps } from '@routes/index';
import { api } from '@services/api';
import Toast from 'react-native-toast-message';
import { Contact, LogQuery } from '@dtos/index';

interface QuoteProposalContextData {
  proposal: Proposal | undefined;
  contacts: Contact[] | undefined;
  handleProcessProposal: (type: 'approve' | 'decline') => Promise<void>;
  handleRedirectSuccessProposal: () => void;
  handleNewLog: (logData: LogQuery) => Promise<void>;
  fetchProposals: () => Promise<void>;
  fetchContacts: () => Promise<void>;
  approveLoading: boolean;
  declineLoading: boolean;
  logLoading: boolean;

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
  const { dispatch } = useNavigation<AppNavigatorRoutesProps>();

  const [lastRouteNavigated, setLastRouteNavigated] =
    useState<keyof MainRoutes>('Home');

  const [proposal, setProposal] = useState<Proposal | undefined>();
  const [contacts, setContacts] = useState<Contact[] | undefined>();
  const [approveLoading, setApproveLoading] = useState(false);
  const [declineLoading, setDeclineLoading] = useState(false);
  const [logLoading, setLogLoading] = useState(false);

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

  const fetchContacts = async () => {
    try {
      const { data } = await api.get<{ content: Contact[] }>(
        `/contatos/usuario/${proposal?.comprador.id}`,
      );

      setContacts(data.content);
    } catch (error) {
      console.log('Erro ao buscar dados do backend:', error);
    }
  };
  
  const handleNewLog = useCallback(async (logData: LogQuery) => {
    try {
      setLogLoading(true);

      const body = logData;
      const { data } = await api.post('/historicos', body);

      if (data.id) {
        Toast.show({
          type: 'success',
          text1: 'Respostas registradas com sucesso!',
        });
      }
    } catch (error) {
      Toast.show({
        type: 'error',
        text1: 'Erro',
        text2: 'Não foi possível registrar sua resposta.',
      });

      throw error;
    } finally {
      setLogLoading(false);
    }
  }, []);

  const handleProcessProposal = async (type: 'approve' | 'decline') => {
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

          dispatch(
            StackActions.replace('QuoteProposal', { screen: "QuoteProposalSuccess" }),
          );
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

        dispatch(
          StackActions.replace('Main', { screen: lastRouteNavigated }),
        );
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
  }

  const handleRedirectSuccessProposal = useCallback(() => {
    setProposal(undefined);
    dispatch(
      StackActions.replace('Main', { screen: "Home" }),
    );
  }, []);

  return (
    <QuoteProposalContext.Provider
      value={{
        proposal,
        contacts,
        fetchProposals,
        fetchContacts,
        handleProcessProposal,
        handleRedirectSuccessProposal,
        handleNewLog,

        approveLoading,
        declineLoading,
        logLoading,

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
